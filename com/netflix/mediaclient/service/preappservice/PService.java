// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import android.os.IBinder;
import android.content.ComponentName;
import com.netflix.mediaclient.ui.homescreen.NetflixAppWidgetProvider;
import android.content.Context;
import android.appwidget.AppWidgetManager;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import java.util.ArrayList;
import android.app.Service;

public class PService extends Service
{
    public static final String PREAPP_AGENT_FROM_ALL_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_ALL_UPDATED";
    public static final String PREAPP_AGENT_FROM_CATEGORY = "com.netflix.mediaclient.intent.category.PREAPP_AGENT_FROM_CATEGORY";
    public static final String PREAPP_AGENT_FROM_CW_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_CW_UPDATED";
    public static final String PREAPP_AGENT_FROM_IQ_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_IQ_UPDATED";
    private static final String TAG = "nf_preapp_service";
    public static final Boolean WIDGET_ENABLED_FOR_TEST;
    private final PServiceAgent.AgentContext agentContext;
    private PServiceFetchAgent mFetchAgent;
    private final ArrayList<InitCallback> mInitCallbacks;
    private volatile boolean mInitComplete;
    
    static {
        WIDGET_ENABLED_FOR_TEST = false;
    }
    
    public PService() {
        this.mInitComplete = false;
        this.mInitCallbacks = new ArrayList<InitCallback>();
        this.agentContext = new PServiceAgent.AgentContext() {
            @Override
            public PService getService() {
                return PService.this;
            }
        };
    }
    
    private void doStartCommand(final Intent intent, final int n, final int n2) {
        if (Log.isLoggable("nf_preapp_service", 4)) {
            Log.i("nf_preapp_service", "Received start command intent " + intent);
        }
        if (!StringUtils.isEmpty(intent.getAction()) && intent.hasCategory("com.netflix.mediaclient.intent.category.PREAPP_AGENT_FROM_CATEGORY")) {
            Log.d("nf_preapp_service", "PREAPP_AGENT command intent ");
            this.mFetchAgent.handleCommand(intent);
        }
    }
    
    private void init() {
        final PServiceAgent.InitCallback initCallback = new PServiceAgent.InitCallback() {
            @Override
            public void onInitComplete(final PServiceAgent pServiceAgent, final Status status) {
                ThreadUtils.assertOnMain();
                if (status.isError()) {
                    if (Log.isLoggable("nf_preapp_service", 6)) {
                        Log.e("nf_preapp_service", String.format("PService init failed with PServiceAgent: %s, statusCode=%s", pServiceAgent.getClass().getSimpleName(), status.getStatusCode()));
                    }
                    PService.this.initCompleted();
                    PService.this.stopSelf();
                }
                else {
                    if (Log.isLoggable("nf_preapp_service", 6)) {
                        Log.i("nf_preapp_service", String.format("PService successfully inited PServiceAgent: %s", pServiceAgent.getClass().getSimpleName()));
                    }
                    if (pServiceAgent == PService.this.mFetchAgent) {
                        PService.this.initCompleted();
                    }
                }
            }
        };
        Log.i("nf_preapp_service", "PService initing...");
        this.mFetchAgent.init(this.agentContext, (PServiceAgent.InitCallback)initCallback);
    }
    
    private void initCompleted() {
        ThreadUtils.assertOnMain();
        Log.d("nf_preapp_service", "Invoking InitCallbacks...");
        final Iterator<InitCallback> iterator = this.mInitCallbacks.iterator();
        while (iterator.hasNext()) {
            iterator.next().onInitComplete();
        }
        this.mInitCallbacks.clear();
        this.mInitComplete = true;
        if (!this.isWidgetInstalled() && !isRemoteUiDevice() && !PService.WIDGET_ENABLED_FOR_TEST) {
            Log.d("nf_preapp_service", "stopping service - !widgetInstalled & !isRemoteUiDevice & !TestCode");
            this.stopSelf();
        }
    }
    
    public static boolean isRemoteUiDevice() {
        return false;
    }
    
    public boolean isWidgetInstalled() {
        final int[] appWidgetIds = AppWidgetManager.getInstance((Context)this).getAppWidgetIds(new ComponentName((Context)this, (Class)NetflixAppWidgetProvider.class));
        if (Log.isLoggable("nf_preapp_service", 3)) {
            Log.d("nf_preapp_service", String.format("found widget: %b, num widgets installed: %d", appWidgetIds.length > 0, appWidgetIds.length));
        }
        return appWidgetIds.length > 0;
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onCreate() {
        Log.i("nf_preapp_service", "PService.onCreate.");
        super.onCreate();
        this.mFetchAgent = new PServiceFetchAgent();
        this.init();
    }
    
    public void onDestroy() {
        super.onDestroy();
        Log.i("nf_preapp_service", "PService.onDestroy.");
        this.mFetchAgent.destroy();
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        if (intent != null) {
            if (this.mInitComplete) {
                this.doStartCommand(intent, n, n2);
            }
            else {
                this.mInitCallbacks.add((InitCallback)new StartCommandInitCallback(intent, n, n2));
            }
        }
        return 2;
    }
    
    private interface InitCallback
    {
        void onInitComplete();
    }
    
    private final class StartCommandInitCallback implements InitCallback
    {
        private final int flags;
        private final Intent intent;
        private final int startId;
        
        public StartCommandInitCallback(final Intent intent, final int flags, final int startId) {
            this.intent = intent;
            this.flags = flags;
            this.startId = startId;
        }
        
        @Override
        public void onInitComplete() {
            PService.this.doStartCommand(this.intent, this.flags, this.startId);
        }
    }
}
