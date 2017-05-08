// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import android.app.Notification;
import com.netflix.mediaclient.service.logging.perf.Events;
import android.os.Process;
import com.netflix.mediaclient.media.BookmarkStore;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.service.job.NetflixJobSchedulerSelector;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import com.netflix.mediaclient.servicemgr.IPushNotification;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.servicemgr.NrdpComponent;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.servicemgr.IDiagnosis;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.IBrowseInterface;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import java.util.List;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.mediaclient.service.user.UserAgent$UserAgentCallback;
import com.netflix.mediaclient.servicemgr.IMSLClient$NetworkRequestInspector;
import android.os.SystemClock;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.service.pservice.logging.PServiceLogging;
import com.netflix.mediaclient.servicemgr.INetflixServiceCallback;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.app.PendingIntent;
import com.netflix.mediaclient.Log;
import android.app.AlarmManager;
import android.content.Intent;
import java.util.HashSet;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.HashMap;
import com.netflix.mediaclient.service.voip.WhistleVoipAgent;
import com.netflix.mediaclient.service.user.UserAgent;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.pushnotification.PushNotificationAgent;
import com.netflix.mediaclient.service.preapp.PreAppAgent;
import java.util.Set;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.pdslogging.PdsAgent;
import com.netflix.mediaclient.service.player.exoplayback.ExoPlayback;
import com.netflix.mediaclient.service.offline.agent.OfflineAgent;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import com.netflix.mediaclient.service.job.NetflixJobExecutor;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import java.util.Map;
import com.netflix.mediaclient.service.msl.MSLAgent;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import com.netflix.mediaclient.android.app.Status;
import java.util.ArrayList;
import com.netflix.mediaclient.service.falkor.FalkorAgent;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.error.ErrorAgent;
import com.netflix.mediaclient.service.diagnostics.DiagnosisAgent;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import android.os.IBinder;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.INetflixService;
import android.app.Service;

public final class NetflixService extends Service implements INetflixService
{
    public static final String ACTION_CLOSE_MDX_MINI_PLAYER_INTENT = "com.netflix.mediaclient.service.ACTION_CLOSE_MINI_PLAYER";
    public static final String ACTION_EXPAND_HOME_MDX_MINI_PLAYER_INTENT = "com.netflix.mediaclient.service.ACTION_EXPAND_HOME_MDX_MINI_PLAYER";
    public static final String ACTION_EXPAND_MDX_MINI_PLAYER_INTENT = "com.netflix.mediaclient.service.ACTION_EXPAND_MDX_MINI_PLAYER";
    private static final String ACTION_REFRESH_WIDGET_CONTENT_ALARM_INTENT = "com.netflix.mediaclient.service.ACTION_REFRESH_WIDGET_CONTENT";
    private static final String ACTION_SHOW_MDX_PLAYER_INTENT = "com.netflix.mediaclient.service.ACTION_SHOW_MDX_PLAYER";
    private static final String ACTION_SHUTDOWN_SERVICE_PENDING_INTENT = "com.netflix.mediaclient.service.ACTION_SHUTDOWN_SERVICE";
    public static final String INTENT_EXTRA_ALREADY_RUNNING = "isRunning";
    private static final long SERVICE_INIT_TIMEOUT_MS = 90000L;
    private static final long SERVICE_KILL_DELAY_MS = 28800000L;
    private static final String TAG = "NetflixService";
    private static final boolean TEST_DELAY_INIT_BY_5_SECONDS = false;
    private static final long WIDGET_CONTENT_REFRESH_DELAY_MS = 172800000L;
    private static boolean fetchErrorsEnabled;
    private static boolean isCreated;
    private static NetflixService$JobExecutionMonitor sJobExecutionMonitor;
    private final ServiceAgent$AgentContext agentContext;
    private Handler handler;
    boolean hasLoggedAgent;
    private final Runnable initTimeoutRunnable;
    private final IBinder mBinder;
    private final NetflixService$ClientCallbacks mClientCallbacks;
    private LoggingAgent mClientLoggingAgent;
    private ConfigurationAgent mConfigurationAgent;
    private DiagnosisAgent mDiagnosisAgent;
    private ErrorAgent mErrorAgent;
    private FalkorAccess mFalkorAccess;
    private FalkorAgent mFalkorAgent;
    private final ArrayList<NetflixService$InitCallback> mInitCallbacks;
    private volatile boolean mInitComplete;
    private Status mInitStatusCode;
    private MdxAgent mMdxAgent;
    private boolean mMdxEnabled;
    private final BroadcastReceiver mMdxReceiver;
    private final BroadcastReceiver mMdxShowPlayerIntent;
    private MSLAgent mMslAgent;
    private final Map<NetflixJob$NetflixJobId, NetflixJobExecutor> mNetflixJobMap;
    private NetflixJobScheduler mNetflixJobScheduler;
    private NetflixPowerManager mNetflixPowerManager;
    private final BroadcastReceiver mNetworkChangeReceiver;
    private NrdController mNrdController;
    private OfflineAgent mOfflineAgent;
    private ExoPlayback mOfflinePlayerAgent;
    private PdsAgent mPdsAgent;
    private PlayerAgent mPlayerAgent;
    private final Set<Integer> mPostedNotificationSet;
    private PreAppAgent mPreAppAgent;
    private PushNotificationAgent mPushAgent;
    private ResourceFetcher mResourceFetcher;
    private long mServiceStartedTimeInMs;
    private UserAgent mUserAgent;
    private WhistleVoipAgent mVoipAgent;
    
    static {
        NetflixService.fetchErrorsEnabled = false;
    }
    
    public NetflixService() {
        this.mNetflixJobMap = new HashMap<NetflixJob$NetflixJobId, NetflixJobExecutor>();
        this.mClientCallbacks = new NetflixService$ClientCallbacks();
        this.mInitComplete = false;
        this.mInitStatusCode = CommonStatus.UNKNOWN;
        this.mInitCallbacks = new ArrayList<NetflixService$InitCallback>();
        this.mMdxEnabled = false;
        this.mPostedNotificationSet = new HashSet<Integer>();
        this.agentContext = new NetflixService$4(this);
        this.mBinder = (IBinder)new NetflixService$LocalBinder(this);
        this.initTimeoutRunnable = new NetflixService$5(this);
        this.mMdxReceiver = new NetflixService$6(this);
        this.mMdxShowPlayerIntent = new NetflixService$7(this);
        this.mNetworkChangeReceiver = new NetflixService$8(this);
    }
    
    public static void TEST_ONLY_setJobExecutionMonitor(final NetflixService$JobExecutionMonitor netflixService$JobExecutionMonitor) {
    }
    
    public static boolean areFetchErrorsEnabled() {
        return false;
    }
    
    private void cancelPendingSelfStop() {
        final AlarmManager alarmManager = (AlarmManager)this.getSystemService("alarm");
        if (alarmManager == null) {
            Log.w("NetflixService", "Can't access alarm manager to cancel shutdown alarm");
            return;
        }
        if (Log.isLoggable()) {
            Log.v("NetflixService", "Canceling service shutdown alarm");
        }
        alarmManager.cancel(this.createShutdownServiceAlarmPendingIntent());
    }
    
    public static Intent createShowMdxPlayerBroadcastIntent() {
        return new Intent("com.netflix.mediaclient.service.ACTION_SHOW_MDX_PLAYER");
    }
    
    private PendingIntent createShutdownServiceAlarmPendingIntent() {
        return PendingIntent.getService((Context)this, 0, new Intent("com.netflix.mediaclient.service.ACTION_SHUTDOWN_SERVICE").setClass((Context)this, (Class)NetflixService.class), 134217728);
    }
    
    private PendingIntent createWidgetContentRefreshPendingIntent() {
        return PendingIntent.getService((Context)this, 0, new Intent("com.netflix.mediaclient.service.ACTION_REFRESH_WIDGET_CONTENT").setClass((Context)this, (Class)NetflixService.class), 134217728);
    }
    
    private void doStartCommand(final Intent intent, final int n, final int n2) {
        Log.d("NetflixService", "Received start command intent ", intent);
        final String action = intent.getAction();
        if (!StringUtils.isEmpty(action)) {
            if ("com.netflix.mediaclient.service.ACTION_SHUTDOWN_SERVICE".equals(action)) {
                Log.i("NetflixService", "Stopping service via shutdown intent...");
                NetflixService.isCreated = false;
                this.stopSelf();
                return;
            }
            this.cancelPendingSelfStop();
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.offline")) {
                Log.d("NetflixService", "Offline command intent ");
                if (this.mOfflineAgent.isReady() && this.mOfflineAgent.isOfflineFeatureEnabled()) {
                    this.mOfflineAgent.getCommandHandler().handleCommand(intent);
                }
                else {
                    Log.e("NetflixService", "received a command while offline agent is not ready");
                }
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.MDX") && this.mMdxEnabled) {
                Log.d("NetflixService", "MDX command intent ");
                this.mMdxAgent.handleCommand(intent);
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.PUSH") && (this.mPushAgent.isSupported() || intent.hasExtra("swiped_notification_id"))) {
                Log.d("NetflixService", "Push notification command intent ");
                this.mPushAgent.handleCommand(intent);
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.LOGGING")) {
                Log.d("NetflixService", "Client logging command intent ");
                this.mClientLoggingAgent.handleCommand(intent);
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.USER")) {
                Log.d("NetflixService", "User agent command intent ");
                this.mUserAgent.handleCommand(intent);
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_PSERVICE")) {
                Log.d("NetflixService", "Preapp service command intent ");
                this.mPreAppAgent.handleCommand(intent);
            }
            if ("com.netflix.mediaclient.service.ACTION_REFRESH_WIDGET_CONTENT".equals(action)) {
                Log.i("NetflixService", "handling widget content refresh alarm expiry...");
                if (AndroidUtils.isWidgetInstalled(this.getApplicationContext())) {
                    this.updateWidgetContentAlarm(172800000L);
                    if (this.isUserLoggedIn()) {
                        PreAppAgent.informMemberUpdated(this.getApplicationContext());
                        return;
                    }
                    PreAppAgent.informNonMemberVideosUpdated(this.getApplicationContext());
                }
            }
        }
    }
    
    private void enableMdxAgentAndInit(final ServiceAgent$AgentContext serviceAgent$AgentContext, final ServiceAgent$InitCallback serviceAgent$InitCallback) {
        this.mMdxEnabled = (AndroidUtils.getAndroidVersion() > 8 && !this.mConfigurationAgent.getMdxConfiguration().isDisableMdx());
        if (this.mMdxEnabled) {
            this.mMdxAgent = new MdxAgent();
            this.registerMdxReceiver();
            this.mMdxAgent.init(serviceAgent$AgentContext, serviceAgent$InitCallback);
        }
    }
    
    private void init() {
        synchronized (this) {
            final NetflixService$2 netflixService$2 = new NetflixService$2(this, new NetflixService$1(this));
            Log.i("NetflixService", "NetflixService initing...");
            this.mConfigurationAgent.init(this.agentContext, netflixService$2);
            Log.i("NetflixService", "Service has " + 90000L / 1000L + " seconds to init or else we fail...");
            this.handler.postDelayed(this.initTimeoutRunnable, 90000L);
        }
    }
    
    private void initCompleted() {
        ThreadUtils.assertOnMain();
        this.handler.removeCallbacks(this.initTimeoutRunnable);
        this.postApplicationStartedEvent();
        Log.d("NetflixService", "Invoking InitCallbacks...");
        final Iterator<NetflixService$InitCallback> iterator = this.mInitCallbacks.iterator();
        while (iterator.hasNext()) {
            iterator.next().onInitComplete();
        }
        this.mInitCallbacks.clear();
        this.mInitComplete = true;
        if (this.mInitStatusCode.isSucces()) {
            this.registerReceiver(this.mNetworkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.registerReceiver(this.mMdxShowPlayerIntent, new IntentFilter("com.netflix.mediaclient.service.ACTION_SHOW_MDX_PLAYER"));
            this.mNrdController.setNetworkInterfaces();
            Log.d("NetflixService", "Send local intent that Netflix service is ready");
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_INIT_COMPLETE");
            intent.putExtra("status_code", (Serializable)this.mInitStatusCode.getStatusCode());
            intent.addCategory("com.netflix.mediaclient.intent.category.NETFLIX_SERVICE");
            LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
        }
        Log.i("NetflixService", "StopService runnable posted - service will die in " + 60 + " seconds unless bound to or started...");
        this.stopSelfInMs(60000);
        if (AndroidUtils.isWidgetInstalled(this.getApplicationContext())) {
            if (Log.isLoggable()) {
                Log.d("NetflixService", "start alarm to wake up in WIDGET_CONTENT_REFRESH_DELAY_MS to refresh content ");
            }
            this.updateWidgetContentAlarm(172800000L);
        }
    }
    
    private void initTimeout() {
        Log.i("NetflixService", "Service init has timed out");
        this.mInitStatusCode = CommonStatus.INIT_SERVICE_TIMEOUT;
        this.initCompleted();
        this.stopSelf();
    }
    
    public static boolean isInstanceCreated() {
        return NetflixService.isCreated;
    }
    
    private void notifyMdxAgentUiComingToForeground() {
        if (this.mMdxEnabled && this.mMdxAgent != null && !this.mMdxAgent.hasActiveSession()) {
            this.mMdxAgent.uiComingToForeground();
        }
    }
    
    private void notifyServiceReady(final int n, final Status status) {
        Log.d("NetflixService", "Notifying client " + n + " that service is ready, status code: " + status.getStatusCode());
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.mClientCallbacks.get(n);
        if (netflixServiceCallback != null) {
            netflixServiceCallback.onServiceReady(n, status);
        }
    }
    
    private void postApplicationStartedEvent() {
        if (this.mClientLoggingAgent == null) {
            Log.e("NetflixService", "Unable to post application started event. Logging agent is null!");
            return;
        }
        if (this.mClientLoggingAgent.getApplicationPerformanceMetricsLogging() == null) {
            Log.e("NetflixService", "Unable to post application started event. APM manager is null!");
            return;
        }
        PServiceLogging.reportStoredLogEvents((Context)this, this.isUserLoggedIn());
        this.mClientLoggingAgent.getApplicationPerformanceMetricsLogging().startApplicationSession(!ErrorLoggingManager.didCrashOnLastLoad());
        this.mClientLoggingAgent.getApplicationPerformanceMetricsLogging().startUserSession(ApplicationPerformanceMetricsLogging$Trigger.appStart);
        this.mClientLoggingAgent.getApplicationPerformanceMetricsLogging().handleConnectivityChange((Context)this);
    }
    
    private void registerMdxReceiver() {
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKEND");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKSTART");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_STATE");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.MDX");
        this.registerReceiver(this.mMdxReceiver, intentFilter);
    }
    
    private void safeUnregisterReceiver(final BroadcastReceiver broadcastReceiver, final String s) {
        if (broadcastReceiver == null) {
            Log.d("NetflixService", "Unable to unregister, receiver is null");
            return;
        }
        try {
            if (Log.isLoggable()) {
                Log.d("NetflixService", "Unregister " + s);
            }
            this.unregisterReceiver(broadcastReceiver);
        }
        catch (Throwable t) {
            Log.e("NetflixService", "Unregister " + s + " failed.", t);
        }
    }
    
    private void stopSelfInMs(final long n) {
        final AlarmManager alarmManager = (AlarmManager)this.getSystemService("alarm");
        if (alarmManager == null) {
            Log.w("NetflixService", "Can't access alarm manager to set shutdown alarm");
            return;
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final long n2 = elapsedRealtime + n;
        if (Log.isLoggable()) {
            Log.v("NetflixService", "Setting service shutdown alarm, current time (ms): " + elapsedRealtime + ", kill delay (ms): " + n + ", alarm set for (ms): " + n2);
        }
        try {
            alarmManager.set(2, n2, this.createShutdownServiceAlarmPendingIntent());
        }
        catch (Exception ex) {
            ErrorLoggingManager.logHandledException("SPY-8729 - Exception trying to schedule an AlarmManager: " + ex);
        }
    }
    
    public static void toggleFetchErrorsEnabled() {
        NetflixService.fetchErrorsEnabled = !NetflixService.fetchErrorsEnabled;
    }
    
    private void updateWidgetContentAlarm(final long n) {
        final AlarmManager alarmManager = (AlarmManager)this.getSystemService("alarm");
        if (alarmManager == null) {
            Log.w("NetflixService", "Can't access alarm manager to set widget content refresh alarm");
            return;
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final long n2 = elapsedRealtime + n;
        if (Log.isLoggable()) {
            Log.v("NetflixService", String.format("updating widget refresh alarm - fireIn %d ms, time sinceBoot %d (ms), widgetRefreshMs: %d ms", n2, elapsedRealtime, n));
        }
        alarmManager.set(2, n2, this.createWidgetContentRefreshPendingIntent());
    }
    
    public void addNetworkRequestInspector(final IMSLClient$NetworkRequestInspector imslClient$NetworkRequestInspector, final Class[] array) {
        if (this.mMslAgent == null) {
            Log.e("NetflixService", "MSLAgent unavailable. Unable to add a network request inspector");
            return;
        }
        this.mMslAgent.addNetworkRequestInspector(imslClient$NetworkRequestInspector, array);
    }
    
    public void addProfile(final String s, final boolean b, final String s2, final int n, final int n2) {
        this.mUserAgent.addWebUserProfile(s, b, s2, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void consumeUmaAlert() {
        this.mUserAgent.consumeUmaAlert();
    }
    
    public void createAutoLoginToken(final long n, final int n2, final int n3) {
        this.mUserAgent.createAutoLoginToken(n, new NetflixService$UserAgentClientCallback(this, n2, n3));
    }
    
    public boolean deleteLocalResource(final String s) {
        return this.mResourceFetcher.deleteLocalResource(s);
    }
    
    public void doOnRampEligibilityAction(final OnRampEligibility$Action onRampEligibility$Action, final int n, final int n2) {
        this.mUserAgent.doOnRampEligibilityAction(onRampEligibility$Action, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void editProfile(final String s, final String s2, final boolean b, final String s3, final int n, final int n2) {
        this.mUserAgent.editWebUserProfile(s, s2, b, s3, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void fetchAndCacheResource(final String s, final IClientLogging$AssetType clientLogging$AssetType, final int n, final int n2) {
        this.mResourceFetcher.fetchAndCacheResource(s, clientLogging$AssetType, new NetflixService$ResourceFetcherClientCallback(this, n, n2));
    }
    
    public void fetchResource(final String s, final IClientLogging$AssetType clientLogging$AssetType, final int n, final int n2) {
        this.mResourceFetcher.fetchResource(s, clientLogging$AssetType, new NetflixService$ResourceFetcherClientCallback(this, n, n2));
    }
    
    public void fetchResource(final String s, final IClientLogging$AssetType clientLogging$AssetType, final long n, final long n2, final int n3, final int n4) {
        this.mResourceFetcher.fetchResource(s, clientLogging$AssetType, n, n2, new NetflixService$ResourceFetcherClientCallback(this, n3, n4));
    }
    
    public void fetchSurvey(final int n, final int n2) {
        this.mUserAgent.fetchSurvey(new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public String getAccountOwnerToken() {
        return this.mUserAgent.getAccountOwnerToken();
    }
    
    public List<? extends UserProfile> getAllProfiles() {
        return this.mUserAgent.getAllProfiles();
    }
    
    public void getAvailableAvatarsList(final int n, final int n2) {
        this.mUserAgent.fetchAvailableAvatarsList(new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public IBrowseInterface getBrowse() {
        return this.mFalkorAccess;
    }
    
    public IClientLogging getClientLogging() {
        return this.mClientLoggingAgent;
    }
    
    public ServiceAgent$ConfigurationAgentInterface getConfiguration() {
        return this.mConfigurationAgent;
    }
    
    public String getCurrentAppLocale() {
        return this.mUserAgent.getCurrentAppLocale().getRaw();
    }
    
    public UserProfile getCurrentProfile() {
        return this.mUserAgent.getCurrentProfile();
    }
    
    public String getCurrentProfileEmail() {
        return this.mUserAgent.getCurrentProfileEmail();
    }
    
    public String getCurrentProfileFirstName() {
        return this.mUserAgent.getCurrentProfileFirstName();
    }
    
    public String getCurrentProfileLastName() {
        return this.mUserAgent.getCurrentProfileLastName();
    }
    
    public String getCurrentProfileToken() {
        return this.mUserAgent.getCurrentProfileToken();
    }
    
    public DeviceCategory getDeviceCategory() {
        return this.mConfigurationAgent.getDeviceCategory();
    }
    
    public IDiagnosis getDiagnosis() {
        return this.mDiagnosisAgent;
    }
    
    public EsnProvider getESN() {
        return this.mConfigurationAgent.getEsnProvider();
    }
    
    public EogAlert getEndOfGrandfatheringAlert() {
        return this.mUserAgent.getEogAlert();
    }
    
    public IErrorHandler getErrorHandler() {
        return this.mErrorAgent;
    }
    
    public FalkorAccess getFalkorAgent() {
        return this.mFalkorAccess;
    }
    
    public Handler getHandler() {
        return this.handler;
    }
    
    public ImageLoader getImageLoader() {
        return this.mResourceFetcher.getImageLoader((Context)this);
    }
    
    public NetflixJobScheduler getJobScheduler() {
        return this.mNetflixJobScheduler;
    }
    
    public IMSLClient getMSLClient() {
        return this.mMslAgent;
    }
    
    public IMdx getMdx() {
        return this.mMdxAgent;
    }
    
    public NetflixPowerManager getNetflixPowerManager() {
        return this.mNetflixPowerManager;
    }
    
    public IPlayer getNflxPlayer() {
        return this.mPlayerAgent;
    }
    
    public String getNrdDeviceModel() {
        return this.mConfigurationAgent.getNrdDeviceModel();
    }
    
    public String getNrdpComponentVersion(final NrdpComponent nrdpComponent) {
        if (nrdpComponent == NrdpComponent.NrdLib) {
            return SecurityRepository.getNrdLibVersion();
        }
        if (nrdpComponent == NrdpComponent.NrdApp) {
            return SecurityRepository.getNrdAppVersion();
        }
        if (nrdpComponent == NrdpComponent.MdxLib) {
            return SecurityRepository.getMdxLibVersion();
        }
        return null;
    }
    
    public OfflineAgentInterface getOfflineAgent() {
        return this.mOfflineAgent;
    }
    
    public IPlayer getOfflinePlayer() {
        return this.mOfflinePlayerAgent;
    }
    
    public IPushNotification getPushNotification() {
        return this.mPushAgent;
    }
    
    public ResourceFetcher getResourceFetcher() {
        return this.mResourceFetcher;
    }
    
    public SignUpParams getSignUpParams() {
        return new NetflixService$3(this);
    }
    
    public String getSoftwareVersion() {
        return this.mConfigurationAgent.getSoftwareVersion();
    }
    
    public long getStartedTimeInMs() {
        return this.mServiceStartedTimeInMs;
    }
    
    public String getUserEmail() {
        return this.mUserAgent.getEmail();
    }
    
    public UmaAlert getUserMessageAlert() {
        return this.mUserAgent.getUserMessageAlert();
    }
    
    public IVoip getVoip() {
        return this.mVoipAgent;
    }
    
    public boolean isCurrentProfileIQEnabled() {
        return this.mUserAgent.isCurrentProfileIQEnabled();
    }
    
    public boolean isDeviceHd() {
        return this.mConfigurationAgent.isDeviceHd();
    }
    
    public boolean isNonMemberPlayback() {
        return this.mUserAgent.isNonMemberPlayback();
    }
    
    public boolean isProfileSwitchingDisabled() {
        return this.mUserAgent.isProfileSwitchingDisabled();
    }
    
    public boolean isTablet() {
        return this.mConfigurationAgent.isTablet();
    }
    
    public boolean isUserAgeVerified() {
        return this.mUserAgent.isAgeVerified();
    }
    
    public boolean isUserLoggedIn() {
        return this.mUserAgent.isUserLoggedIn();
    }
    
    public void loginUser(final String s, final String s2, final int n, final int n2) {
        this.mUserAgent.loginUser(s, s2, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void loginUserByTokens(final ActivationTokens activationTokens, final int n, final int n2) {
        this.mUserAgent.tokenActivate(activationTokens, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void logoutUser(final int n, final int n2) {
        this.mUserAgent.logoutUser(new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void markSurveysAsRead() {
        this.mUserAgent.markSurveysAsRead();
    }
    
    public IBinder onBind(final Intent intent) {
        Log.d("NetflixService", "NetflixService is onBind");
        this.cancelPendingSelfStop();
        return this.mBinder;
    }
    
    public void onCreate() {
        Log.i("NetflixService", "NetflixService.onCreate.");
        PerformanceProfiler.getInstance().startSession(Sessions.NETFLIX_SERVICE_LOADED, null);
        super.onCreate();
        NetflixService.isCreated = true;
        this.mServiceStartedTimeInMs = System.currentTimeMillis();
        this.handler = new Handler();
        this.mNetflixJobScheduler = NetflixJobSchedulerSelector.createNetflixJobScheduler(this.getApplicationContext());
        this.mConfigurationAgent = new ConfigurationAgent();
        this.mMslAgent = new MSLAgent();
        this.mNrdController = new NrdController();
        this.mUserAgent = new UserAgent();
        this.mResourceFetcher = new ResourceFetcher();
        this.mPlayerAgent = new PlayerAgent();
        this.mPushAgent = new PushNotificationAgent();
        this.mClientLoggingAgent = new LoggingAgent();
        this.mDiagnosisAgent = new DiagnosisAgent();
        this.mFalkorAgent = new FalkorAgent();
        this.mFalkorAccess = new FalkorAccess(this.mFalkorAgent, this.mClientCallbacks);
        this.mPreAppAgent = new PreAppAgent();
        this.mErrorAgent = new ErrorAgent();
        this.mVoipAgent = new WhistleVoipAgent(this.getApplicationContext(), this.mUserAgent);
        this.mOfflineAgent = new OfflineAgent(this.mConfigurationAgent, this.mUserAgent);
        this.mOfflinePlayerAgent = new ExoPlayback(this.getApplicationContext(), this.getHandler(), this.mOfflineAgent, this.mClientLoggingAgent);
        this.mPdsAgent = new PdsAgent(this.mOfflineAgent);
        this.mNetflixPowerManager = new NetflixPowerManager(this.getApplicationContext());
        BookmarkStore.getInstance().init(this.getApplicationContext());
        this.init();
    }
    
    public void onDestroy() {
        super.onDestroy();
        Log.i("NetflixService", "NetflixService.onDestroy.");
        this.cancelPendingSelfStop();
        Log.d("NetflixService", "Send local intent that Netflix service is destroyed");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_DESTROYED");
        intent.addCategory("com.netflix.mediaclient.intent.category.NETFLIX_SERVICE");
        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
        if (this.mMdxEnabled) {
            this.safeUnregisterReceiver(this.mMdxReceiver, "MDX receiver");
        }
        this.safeUnregisterReceiver(this.mNetworkChangeReceiver, "network receiver");
        this.safeUnregisterReceiver(this.mMdxShowPlayerIntent, "mdx show player receiver");
        this.mClientCallbacks.clear();
        if (this.mMdxEnabled && this.mMdxAgent != null) {
            this.mMdxAgent.destroy();
        }
        if (this.mFalkorAgent != null) {
            this.mFalkorAgent.destroy();
        }
        if (this.mPlayerAgent != null) {
            this.mPlayerAgent.destroy();
        }
        if (this.mUserAgent != null) {
            this.mUserAgent.destroy();
        }
        if (this.mNrdController != null) {
            this.mNrdController.destroy();
        }
        if (this.mConfigurationAgent != null) {
            this.mConfigurationAgent.destroy();
        }
        if (this.mResourceFetcher != null) {
            this.mResourceFetcher.destroy();
        }
        if (this.mClientLoggingAgent != null) {
            this.mClientLoggingAgent.destroy();
        }
        if (this.mDiagnosisAgent != null) {
            this.mDiagnosisAgent.destroy();
        }
        if (this.mVoipAgent != null) {
            this.mVoipAgent.destroy();
        }
        if (this.mOfflineAgent != null) {
            this.mOfflineAgent.destroy();
        }
        if (this.mMslAgent != null) {
            this.mMslAgent.destroy();
        }
        if (this.mNetflixPowerManager != null) {
            this.mNetflixPowerManager.forceReleasePartialWakeLock();
        }
        this.mNetflixJobScheduler = null;
        this.mNetflixJobMap.clear();
        NetflixService.isCreated = false;
        final int myPid = Process.myPid();
        Log.d("NetflixService", "Destroying app process " + myPid + "...");
        Process.killProcess(myPid);
        Log.d("NetflixService", "Destroying app process " + myPid + " done.");
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        PerformanceProfiler.getInstance().logEvent(Events.NETFLIX_SERVICE_STARTED_COMMAND, null);
        if (intent != null) {
            if (this.mInitComplete) {
                this.doStartCommand(intent, n, n2);
            }
            else {
                this.mInitCallbacks.add(new NetflixService$StartCommandInitCallback(this, intent, n, n2));
            }
        }
        return 2;
    }
    
    public void onTaskRemoved(final Intent intent) {
        super.onTaskRemoved(intent);
        if (Log.isLoggable()) {
            Log.d("NetflixService", "onTaskRemoved: Invoked on NetflixService");
        }
        if (this.mInitComplete) {
            this.mFalkorAgent.serializeFalcorCache();
        }
    }
    
    public void onTrimMemory(final int n) {
        super.onTrimMemory(n);
        if (n >= 60 && this.mInitComplete) {
            if (Log.isLoggable()) {
                Log.d("NetflixService", "onTrimMemory: level - " + n);
            }
            this.mFalkorAgent.serializeFalkorMetadataAsync();
            this.mOfflineAgent.onTrimMemory(n);
        }
    }
    
    public boolean onUnbind(final Intent intent) {
        Log.d("NetflixService", "NetflixService is onUnbind");
        final int size = this.mClientCallbacks.size();
        if (size > 0) {
            Log.i("NetflixService", "We still have " + size + " callbacks - not stopping service");
            return true;
        }
        if (this.mMdxEnabled && this.mMdxAgent.hasActiveSession()) {
            Log.i("NetflixService", "has active mdx session");
            return true;
        }
        if (this.mInitStatusCode == CommonStatus.NO_CONNECTIVITY) {
            Log.i("NetflixService", "Service init failed due to no connectivity - calling stopSelf()");
            this.stopSelf();
            return true;
        }
        if (Log.isLoggable()) {
            Log.i("NetflixService", "No callbacks left - stopping service after delay of: 28800 seconds");
        }
        this.stopSelfInMs(28800000L);
        return true;
    }
    
    public void recordPlanSelection(final String s, final String s2) {
        this.mUserAgent.recordPlanSelection(s, s2);
    }
    
    public void recordUserMessageImpression(final String s, final String s2) {
        this.mUserAgent.recordUmsImpression(s, s2);
    }
    
    public void refreshCurrentUserMessageArea() {
        this.mUserAgent.refreshUserMessage();
    }
    
    public void refreshProfileSwitchingStatus() {
        this.mUserAgent.refreshProfileSwitchingStatus();
    }
    
    public void registerCallback(final INetflixServiceCallback netflixServiceCallback) {
        ThreadUtils.assertOnMain();
        this.cancelPendingSelfStop();
        if (netflixServiceCallback == null) {
            throw new IllegalStateException(" registerCallback - cb is null");
        }
        final int put = this.mClientCallbacks.put(netflixServiceCallback);
        Log.i("NetflixService", "registerCallback, client: " + netflixServiceCallback.hashCode());
        if (this.mInitComplete) {
            this.notifyServiceReady(put, this.mInitStatusCode);
            if (this.mClientCallbacks.size() == 1) {
                Log.d("NetflixService", "UI started, notify MDX");
                this.notifyMdxAgentUiComingToForeground();
            }
            return;
        }
        this.mInitCallbacks.add(new NetflixService$NotifyServiceReadyInitCallback(this, put));
    }
    
    public void registerJobExecutor(final NetflixJob$NetflixJobId netflixJob$NetflixJobId, final NetflixJobExecutor netflixJobExecutor) {
        synchronized (this.mNetflixJobMap) {
            this.mNetflixJobMap.put(netflixJob$NetflixJobId, netflixJobExecutor);
        }
    }
    
    public void removeProfile(final String s, final int n, final int n2) {
        this.mUserAgent.removeWebUserProfile(s, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void requestBackgroundForNotification(final int n, final boolean b) {
        ThreadUtils.assertOnMain();
        this.mPostedNotificationSet.remove(n);
        if (this.mPostedNotificationSet.size() == 0) {
            if (Log.isLoggable()) {
                Log.i("NetflixService", "stopForeground removeNotification=" + b);
            }
            this.stopForeground(b);
        }
    }
    
    public void requestForegroundForNotification(final int n, final Notification notification) {
        ThreadUtils.assertOnMain();
        if (!this.mPostedNotificationSet.contains(n)) {
            this.mPostedNotificationSet.add(n);
            if (Log.isLoggable()) {
                Log.i("NetflixService", "startForeground notificationId=" + n);
            }
            this.startForeground(n, notification);
        }
    }
    
    public void requestServiceShutdownAfterDelay(final long n) {
        this.stopSelfInMs(n);
    }
    
    public void selectProfile(final String s) {
        this.mUserAgent.selectProfile(s);
    }
    
    public void setCurrentAppLocale(final String currentAppLocale) {
        this.mUserAgent.setCurrentAppLocale(currentAppLocale);
    }
    
    public void setNonMemberPlayback(final boolean nonMemberPlayback) {
        this.mUserAgent.setNonMemberPlayback(nonMemberPlayback);
    }
    
    public void startJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        synchronized (this.mNetflixJobMap) {
            final NetflixJobExecutor netflixJobExecutor = this.mNetflixJobMap.get(netflixJob$NetflixJobId);
            // monitorexit(this.mNetflixJobMap)
            if (netflixJobExecutor != null) {
                netflixJobExecutor.onNetflixStartJob(netflixJob$NetflixJobId);
            }
        }
    }
    
    public void stopJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        synchronized (this.mNetflixJobMap) {
            final NetflixJobExecutor netflixJobExecutor = this.mNetflixJobMap.get(netflixJob$NetflixJobId);
            // monitorexit(this.mNetflixJobMap)
            if (netflixJobExecutor != null) {
                netflixJobExecutor.onNetflixStopJob(netflixJob$NetflixJobId);
            }
        }
    }
    
    public void uiComingFromBackground() {
        Log.d("NetflixService", "UI coming from background, notify MDX");
        this.notifyMdxAgentUiComingToForeground();
    }
    
    public void unregisterCallback(INetflixServiceCallback remove) {
        if (remove == null) {
            return;
        }
        remove = this.mClientCallbacks.remove(remove);
        if (remove == null) {
            Log.w("NetflixService", "Client callback was either not-registered/removed");
            return;
        }
        Log.i("NetflixService", "unregisterCallback, client: " + remove.hashCode());
    }
    
    public void verifyAge(final int n, final int n2) {
        this.mUserAgent.verifyAge(new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void verifyPin(final String s, final int n, final int n2) {
        this.mUserAgent.verifyPin(s, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
}
