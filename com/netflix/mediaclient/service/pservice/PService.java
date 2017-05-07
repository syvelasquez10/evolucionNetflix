// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import android.os.IBinder;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import java.util.ArrayList;
import android.app.Service;

public class PService extends Service
{
    public static final String PREAPP_AGENT_FROM_ACTIVE_DISK_WRITE = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_ACTIVE_DISK_WRITE";
    public static final String PREAPP_AGENT_FROM_ALL_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_ALL_UPDATED";
    public static final String PREAPP_AGENT_FROM_CATEGORY = "com.netflix.mediaclient.intent.category.PREAPP_AGENT_FROM_CATEGORY";
    public static final String PREAPP_AGENT_FROM_CW_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_CW_UPDATED";
    public static final String PREAPP_AGENT_FROM_IQ_UPDATED = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_IQ_UPDATED";
    private static final String TAG = "nf_preapp_service";
    public static final Boolean WIDGET_ENABLED_FOR_TEST;
    private final PServiceAgent$AgentContext agentContext;
    private PServiceFetchAgent mFetchAgent;
    private final ArrayList<PService$InitCallback> mInitCallbacks;
    private volatile boolean mInitComplete;
    
    static {
        WIDGET_ENABLED_FOR_TEST = false;
    }
    
    public PService() {
        this.mInitComplete = false;
        this.mInitCallbacks = new ArrayList<PService$InitCallback>();
        this.agentContext = new PService$2(this);
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
        final PService$1 pService$1 = new PService$1(this);
        Log.i("nf_preapp_service", "PService initing...");
        this.mFetchAgent.init(this.agentContext, pService$1);
    }
    
    private void initCompleted() {
        ThreadUtils.assertOnMain();
        Log.d("nf_preapp_service", "Invoking InitCallbacks...");
        final Iterator<PService$InitCallback> iterator = this.mInitCallbacks.iterator();
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
        return AndroidUtils.isWidgetInstalled((Context)this);
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
                this.mInitCallbacks.add(new PService$StartCommandInitCallback(this, intent, n, n2));
            }
        }
        return 2;
    }
}
