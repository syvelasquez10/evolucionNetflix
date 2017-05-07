// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import android.os.IBinder;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.SystemClock;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Context;
import android.app.PendingIntent;
import com.netflix.mediaclient.Log;
import android.app.AlarmManager;
import android.content.Intent;
import java.util.ArrayList;
import android.app.Service;

public class PService extends Service
{
    public static final String ACTION_ACCOUNT_DEACTIVATED_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.action.ACTION_ACCOUNT_DEACTIVATED_FROM_PREAPP_AGENT";
    public static final String ACTION_ALL_MEMBER_UPDATED_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.action.ALL_MEMBER_UPDATED_FROM_PREAPP_AGENT";
    public static final String ACTION_CW_UPDATED_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.action.CW_UPDATED_FROM_PREAPP_AGENT";
    public static final String ACTION_DETAILS_1_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.DETAILS_1_FROM_PREAPP_WIDGET";
    public static final String ACTION_DETAILS_2_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.DETAILS_2_FROM_PREAPP_WIDGET";
    public static final String ACTION_DETAILS_3_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.DETAILS_3_FROM_PREAPP_WIDGET";
    public static final String ACTION_HOME_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.HOME_FROM_PREAPP_WIDGET";
    public static final String ACTION_INSTALLED_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.INSTALLED_FROM_PREAPP_WIDGET";
    public static final String ACTION_IQ_UPDATED_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.action.IQ_UPDATED_FROM_PREAPP_AGENT";
    public static final String ACTION_NON_MEMBER_UPDATED_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.action.NON_MEMBER_UPDATED_FROM_PREAPP_AGENT";
    public static final String ACTION_PLAY_1_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.PLAY_1_FROM_PREAPP_WIDGET";
    public static final String ACTION_PLAY_2_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.PLAY_2_FROM_PREAPP_WIDGET";
    public static final String ACTION_PLAY_3_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.PLAY_3_FROM_PREAPP_WIDGET";
    public static final String ACTION_REFRESH_DATA = "com.netflix.mediaclient.intent.action.REFRESH_DATA";
    public static final String ACTION_REFRESH_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.REFRESH_FROM_PREAPP_WIDGET";
    public static final String ACTION_REFRESH_NON_MEMBER_DATA = "com.netflix.mediaclient.intent.action.REFRESH_NON_MEMBER_DATA";
    public static final String ACTION_RESIZED_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.ACTION_RESIZED_FROM_PREAPP_WIDGET";
    public static final String ACTION_UNINSTALL_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.action.UNINSTALL_FROM_PREAPP_WIDGET";
    private static final String ACTION_WIDGET_REFRESH_ALARM_INTENT = "com.netflix.mediaclient.service.pservice.ACTION_REFRESH_WIDGET";
    public static final String CATEGORY_FROM_PREAPP_AGENT = "com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_AGENT";
    public static final String CATEGORY_FROM_PREAPP_PSERVICE = "com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_PSERVICE";
    public static final String CATEGORY_FROM_PREAPP_WIDGET = "com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_WIDGET";
    public static final String EXTRA_ACTION_NAME = "actionName";
    public static final String EXTRA_LIST_TYPE = "listType";
    public static final String EXTRA_VIDEO_ID = "videoId";
    public static final String EXTRA_WIDGET_ID = "widgetId";
    public static final Integer INVALID_WIDGET_ID;
    private static final String TAG = "nf_preapp_service";
    private static final long WIDGET_REFRESH_DELAY_MS = 7200000L;
    private final PServiceAgent$AgentContext agentContext;
    private PServiceFetchAgent mFetchAgent;
    private final ArrayList<PService$InitCallback> mInitCallbacks;
    private volatile boolean mInitComplete;
    private PServiceWidgetAgent mWidgetAgent;
    
    static {
        INVALID_WIDGET_ID = Integer.MIN_VALUE;
    }
    
    public PService() {
        this.mInitComplete = false;
        this.mInitCallbacks = new ArrayList<PService$InitCallback>();
        this.agentContext = new PService$2(this);
    }
    
    private void cancelWidgetRefreshAlarm() {
        final AlarmManager alarmManager = (AlarmManager)this.getSystemService("alarm");
        if (alarmManager == null) {
            Log.w("nf_preapp_service", "Can't access alarm manager to set widget refresh alarm");
            return;
        }
        Log.d("nf_preapp_service", "Cancelling widget refresh alarm");
        alarmManager.cancel(this.createTileExpiryAlarmPendingIntent());
    }
    
    private PendingIntent createTileExpiryAlarmPendingIntent() {
        return PendingIntent.getService((Context)this, 0, new Intent("com.netflix.mediaclient.service.pservice.ACTION_REFRESH_WIDGET").setClass((Context)this, (Class)PService.class), 134217728);
    }
    
    private Intent createToNetflixServiceIntent(final String s) {
        final Intent intent = new Intent(s);
        intent.setClass((Context)this, (Class)NetflixService.class);
        intent.addCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_PSERVICE");
        return intent;
    }
    
    private void doStartCommand(final Intent intent, int n, final int n2) {
        if (Log.isLoggable()) {
            Log.i("nf_preapp_service", "Received start command intent " + intent);
        }
        final String action = intent.getAction();
        if (!StringUtils.isEmpty(action)) {
            if ("com.netflix.mediaclient.service.pservice.ACTION_REFRESH_WIDGET".equals(action)) {
                Log.i("nf_preapp_service", "handling widget refresh alarm expiry...");
                this.mWidgetAgent.handleWidgetRefreshReq((Context)this, intent);
                this.updateWidgetRefreshAlarm(7200000L);
                return;
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_AGENT")) {
                Log.d("nf_preapp_service", "PREAPP_AGENT command intent ");
                this.handleCommandFromNetflixService(intent);
                this.updateWidgetRefreshAlarm(7200000L);
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_WIDGET")) {
                Log.d("nf_preapp_service", String.format("PREAPP_WIDGET command intent ..action:%s ", intent.getAction()));
                final String action2 = intent.getAction();
                n = -1;
                switch (action2.hashCode()) {
                    case 117936931: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.INSTALLED_FROM_PREAPP_WIDGET")) {
                            n = 0;
                            break;
                        }
                        break;
                    }
                    case 1477311290: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.ACTION_RESIZED_FROM_PREAPP_WIDGET")) {
                            n = 1;
                            break;
                        }
                        break;
                    }
                    case -881854046: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.REFRESH_FROM_PREAPP_WIDGET")) {
                            n = 2;
                            break;
                        }
                        break;
                    }
                    case 1292326626: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.HOME_FROM_PREAPP_WIDGET")) {
                            n = 3;
                            break;
                        }
                        break;
                    }
                    case 630655721: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.DETAILS_1_FROM_PREAPP_WIDGET")) {
                            n = 4;
                            break;
                        }
                        break;
                    }
                    case 221831496: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.DETAILS_2_FROM_PREAPP_WIDGET")) {
                            n = 5;
                            break;
                        }
                        break;
                    }
                    case -186992729: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.DETAILS_3_FROM_PREAPP_WIDGET")) {
                            n = 6;
                            break;
                        }
                        break;
                    }
                    case -54517253: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.PLAY_1_FROM_PREAPP_WIDGET")) {
                            n = 7;
                            break;
                        }
                        break;
                    }
                    case -463341478: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.PLAY_2_FROM_PREAPP_WIDGET")) {
                            n = 8;
                            break;
                        }
                        break;
                    }
                    case -872165703: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.PLAY_3_FROM_PREAPP_WIDGET")) {
                            n = 9;
                            break;
                        }
                        break;
                    }
                    case -61073221: {
                        if (action2.equals("com.netflix.mediaclient.intent.action.UNINSTALL_FROM_PREAPP_WIDGET")) {
                            n = 10;
                            break;
                        }
                        break;
                    }
                }
                switch (n) {
                    default: {
                        Log.d("nf_preapp_service", "unexpected action: drop");
                    }
                    case 0: {
                        Log.d("nf_preapp_service", "widget installed");
                        this.mWidgetAgent.handleWidgetRefreshReq((Context)this, intent);
                        this.updateWidgetRefreshAlarm(7200000L);
                    }
                    case 1: {
                        Log.d("nf_preapp_service", "widget resized");
                        this.mWidgetAgent.handleWidgetResizeReq((Context)this, intent);
                    }
                    case 2: {
                        this.mWidgetAgent.handleWidgetRefreshReq((Context)this, intent);
                        this.mWidgetAgent.logWidgetRefreshEvent((Context)this, intent);
                        this.updateWidgetRefreshAlarm(7200000L);
                    }
                    case 3: {
                        this.mWidgetAgent.handleWidgetHomeReq((Context)this, intent);
                    }
                    case 4:
                    case 5:
                    case 6: {
                        this.mWidgetAgent.handlePlayOrDetailsReq((Context)this, intent, false);
                    }
                    case 7:
                    case 8:
                    case 9: {
                        this.mWidgetAgent.handlePlayOrDetailsReq((Context)this, intent, true);
                    }
                    case 10: {
                        this.cancelWidgetRefreshAlarm();
                    }
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
        if (!this.isWidgetInstalled() && !isRemoteUiDevice()) {
            Log.d("nf_preapp_service", "stopping service - !widgetInstalled & !isRemoteUiDevice & !TestCode");
            this.stopSelf();
            return;
        }
        this.updateWidgetRefreshAlarm(7200000L);
    }
    
    public static boolean isRemoteUiDevice() {
        return false;
    }
    
    private void updateWidgetRefreshAlarm(final long n) {
        final AlarmManager alarmManager = (AlarmManager)this.getSystemService("alarm");
        if (alarmManager == null) {
            Log.w("nf_preapp_service", "Can't access alarm manager to set widget refresh alarm");
            return;
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final long n2 = elapsedRealtime + n;
        if (Log.isLoggable()) {
            Log.v("nf_preapp_service", String.format("updating widget refresh alarm - fireIn %d ms, time sinceBoot %d (ms), widgetRefreshMs: %d ms", n2, elapsedRealtime, n));
        }
        alarmManager.set(2, n2, this.createTileExpiryAlarmPendingIntent());
    }
    
    public void handleCommandFromNetflixService(final Intent intent) {
        if (intent == null) {
            Log.w("nf_preapp_service", "Intent is null");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_preapp_service", String.format("received intent: %s", intent.getAction()));
        }
        if ("com.netflix.mediaclient.intent.action.ALL_MEMBER_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction()) || "com.netflix.mediaclient.intent.action.CW_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction()) || "com.netflix.mediaclient.intent.action.IQ_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction()) || "com.netflix.mediaclient.intent.action.ACTION_ACCOUNT_DEACTIVATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
            this.mFetchAgent.refreshDataAndNotify(intent);
            return;
        }
        if (!"com.netflix.mediaclient.intent.action.NON_MEMBER_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
            Log.e("nf_preapp_service", "Unknown command!");
            return;
        }
        if (PDiskData.isMemberDataAvailable(this.agentContext.getFetchAgent().getDiskData())) {
            Log.d("nf_preapp_service", "member data available - ignore ACTION_NON_MEMBER_UPDATED_FROM_PREAPP_AGENT");
            return;
        }
        this.mFetchAgent.refreshDataAndNotify(intent);
    }
    
    public boolean isWidgetInstalled() {
        return AndroidUtils.isWidgetInstalled((Context)this);
    }
    
    protected void notifyToFetchData() {
        final Intent toNetflixServiceIntent = this.createToNetflixServiceIntent("com.netflix.mediaclient.intent.action.REFRESH_DATA");
        Log.d("nf_preapp_service", "Sending command to start NetflixService...ACTION_REFRESH_DATA");
        this.startService(toNetflixServiceIntent);
        Log.d("nf_preapp_service", "Sending command to NetflixService done.");
    }
    
    protected void notifyToFetchNonMemberData() {
        final Intent toNetflixServiceIntent = this.createToNetflixServiceIntent("com.netflix.mediaclient.intent.action.REFRESH_NON_MEMBER_DATA");
        Log.d("nf_preapp_service", "Sending command to start NetflixService...ACTION_REFRESH_NON_MEMBER_DATA");
        this.startService(toNetflixServiceIntent);
        Log.d("nf_preapp_service", "Sending command to NetflixService done.");
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
