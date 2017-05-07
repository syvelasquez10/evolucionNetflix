// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import android.os.IBinder;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import java.util.ArrayList;
import android.app.Service;

public class PService extends Service
{
    public static final String ACTION_ACCOUNT_DEACTIVATED_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.action.ACTION_ACCOUNT_DEACTIVATED_FROM_PREAPP_AGENT";
    public static final String ACTION_ALL_UPDATED_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.action.ALL_UPDATED_FROM_PREAPP_AGENT";
    public static final String ACTION_CW_UPDATED_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.action.CW_UPDATED_FROM_PREAPP_AGENT";
    public static final String ACTION_HOME_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.HOME_FROM_PREAPP_WIDGET";
    public static final String ACTION_INSTALLED_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.INSTALLED_FROM_PREAPP_WIDGET";
    public static final String ACTION_IQ_UPDATED_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.action.IQ_UPDATED_FROM_PREAPP_AGENT";
    public static final String ACTION_PLAY_OR_DETAILS_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.PLAY_OR_DETAILS_FROM_PREAPP_WIDGET";
    public static final String ACTION_REFRESH_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.REFRESH_FROM_PREAPP_WIDGET";
    public static final String ACTION_RESIZED_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.INSTALLED_FROM_PREAPP_WIDGET";
    public static final String CATEGORY_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_AGENT";
    public static final String CATEGORY_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_WIDGET";
    public static final String EXTRA_ACTION_NAME = "actionName";
    public static final String EXTRA_LIST_NAME = "listName";
    public static final String EXTRA_VIDEO_ID = "videoId";
    public static final String EXTRA_WIDGET_ID = "widgetId";
    public static final Integer INVALID_WIDGET_ID;
    private static final String TAG = "nf_preapp_service";
    public static final Boolean WIDGET_ENABLED_FOR_TEST;
    private final PServiceAgent$AgentContext agentContext;
    private PServiceFetchAgent mFetchAgent;
    private final ArrayList<PService$InitCallback> mInitCallbacks;
    private volatile boolean mInitComplete;
    private PServiceWidgetAgent mWidgetAgent;
    
    static {
        INVALID_WIDGET_ID = Integer.MIN_VALUE;
        WIDGET_ENABLED_FOR_TEST = false;
    }
    
    public PService() {
        this.mInitComplete = false;
        this.mInitCallbacks = new ArrayList<PService$InitCallback>();
        this.agentContext = new PService$2(this);
    }
    
    private void doStartCommand(final Intent intent, final int n, final int n2) {
        if (Log.isLoggable()) {
            Log.i("nf_preapp_service", "Received start command intent " + intent);
        }
        if (!StringUtils.isEmpty(intent.getAction())) {
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_AGENT")) {
                Log.d("nf_preapp_service", "PREAPP_AGENT command intent ");
                this.handleCommand(intent);
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_WIDGET")) {
                Log.d("nf_preapp_service", "PREAPP_WIDGET command intent .. ");
                if ("com.netflix.mediaclient.intent.action.INSTALLED_FROM_PREAPP_WIDGET".equals(intent.getAction())) {
                    Log.d("nf_preapp_service", "widget installed");
                    this.mWidgetAgent.handleWidgetRefreshReq((Context)this, intent);
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.INSTALLED_FROM_PREAPP_WIDGET".equals(intent.getAction())) {
                    Log.d("nf_preapp_service", "widget resized");
                    this.mWidgetAgent.handleWidgetRefreshReq((Context)this, intent);
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.REFRESH_FROM_PREAPP_WIDGET".equals(intent.getAction())) {
                    this.mWidgetAgent.handleWidgetRefreshReq((Context)this, intent);
                    this.mWidgetAgent.logWidgetRefreshEvent((Context)this, intent);
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.HOME_FROM_PREAPP_WIDGET".equals(intent.getAction())) {
                    this.mWidgetAgent.handleWidgetHomeReq((Context)this, intent);
                    return;
                }
                if ("com.netflix.mediaclient.intent.action.PLAY_OR_DETAILS_FROM_PREAPP_WIDGET".equals(intent.getAction())) {
                    this.mWidgetAgent.handlePlayOrDetailsReq((Context)this, intent);
                }
            }
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
    
    public void handleCommand(final Intent intent) {
        if (intent == null) {
            Log.w("nf_preapp_service", "Intent is null");
            return;
        }
        if ("com.netflix.mediaclient.intent.action.ALL_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction()) || "com.netflix.mediaclient.intent.action.CW_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction()) || "com.netflix.mediaclient.intent.action.IQ_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
            if (Log.isLoggable()) {
                Log.d("nf_preapp_service", String.format("received intent: %s", intent.getAction()));
            }
            this.mFetchAgent.refreshDataAndNotify(intent);
            return;
        }
        if ("com.netflix.mediaclient.intent.action.ACTION_ACCOUNT_DEACTIVATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
            this.mWidgetAgent.updateWidgetWithNonMemberData(intent);
            return;
        }
        Log.e("nf_preapp_service", "Uknown command!");
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
        this.mWidgetAgent = new PServiceWidgetAgent();
        this.init();
    }
    
    public void onDestroy() {
        super.onDestroy();
        Log.i("nf_preapp_service", "PService.onDestroy.");
        this.mFetchAgent.destroy();
        this.mWidgetAgent.destroy();
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
