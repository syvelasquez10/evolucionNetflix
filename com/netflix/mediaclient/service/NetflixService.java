// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.Set;
import android.os.Process;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import com.netflix.mediaclient.servicemgr.IPushNotification;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.servicemgr.NrdpComponent;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.servicemgr.IDiagnosis;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.IBrowseInterface;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import java.util.List;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.user.UserAgent$UserAgentCallback;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.servicemgr.INetflixServiceCallback;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.user.UserAgent;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.pushnotification.PushNotificationAgent;
import com.netflix.mediaclient.service.preapp.PreAppAgent;
import com.netflix.mediaclient.service.player.PlayerAgent;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import com.netflix.mediaclient.android.app.Status;
import java.util.ArrayList;
import com.netflix.mediaclient.service.falkor.FalkorAgent;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.diagnostics.DiagnosisAgent;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import android.os.IBinder;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.INetflixService;
import android.app.Service;

public final class NetflixService extends Service implements INetflixService
{
    public static final String ACTION_EXPAND_MDX_MINI_PLAYER_INTENT = "com.netflix.mediaclient.service.ACTION_EXPAND_MDX_MINI_PLAYER";
    private static final String ACTION_SHOW_MDX_PLAYER_INTENT = "com.netflix.mediaclient.service.ACTION_SHOW_MDX_PLAYER";
    public static final boolean ENABLE_FALKOR_AGENT = false;
    public static final String INTENT_EXTRA_ALREADY_RUNNING = "isRunning";
    private static final long SERVICE_INIT_TIMEOUT_MS = 90000L;
    private static final long SERVICE_KILL_DELAY_MS = 28800000L;
    private static final String TAG = "NetflixService";
    private static boolean fetchErrorsEnabled;
    private static boolean isCreated;
    private final ServiceAgent$AgentContext agentContext;
    private Handler handler;
    private final Runnable initTimeoutRunnable;
    private final IBinder mBinder;
    private BrowseAccess mBrowseAccess;
    private BrowseAgent mBrowseAgent;
    private final NetflixService$ClientCallbacks mClientCallbacks;
    private LoggingAgent mClientLoggingAgent;
    private ConfigurationAgent mConfigurationAgent;
    private DiagnosisAgent mDiagnosisAgent;
    private FalkorAccess mFalkorAccess;
    private FalkorAgent mFalkorAgent;
    private final ArrayList<NetflixService$InitCallback> mInitCallbacks;
    private volatile boolean mInitComplete;
    private Status mInitStatusCode;
    private MdxAgent mMdxAgent;
    private boolean mMdxEnabled;
    private final BroadcastReceiver mMdxReceiver;
    private final BroadcastReceiver mMdxShowPlayerIntent;
    private final BroadcastReceiver mNetworkChangeReceiver;
    private NrdController mNrdController;
    private PlayerAgent mPlayerAgent;
    private PreAppAgent mPreAppAgent;
    private PushNotificationAgent mPushAgent;
    private ResourceFetcher mResourceFetcher;
    private UserAgent mUserAgent;
    private final Runnable stopServiceRunnable;
    
    static {
        NetflixService.fetchErrorsEnabled = false;
    }
    
    public NetflixService() {
        this.mClientCallbacks = new NetflixService$ClientCallbacks();
        this.mInitComplete = false;
        this.mInitStatusCode = CommonStatus.UNKNOWN;
        this.mInitCallbacks = new ArrayList<NetflixService$InitCallback>();
        this.mMdxEnabled = false;
        this.agentContext = new NetflixService$3(this);
        this.mBinder = (IBinder)new NetflixService$LocalBinder(this);
        this.stopServiceRunnable = new NetflixService$4(this);
        this.initTimeoutRunnable = new NetflixService$5(this);
        this.mMdxReceiver = new NetflixService$6(this);
        this.mMdxShowPlayerIntent = new NetflixService$7(this);
        this.mNetworkChangeReceiver = new NetflixService$8(this);
    }
    
    public static boolean areFetchErrorsEnabled() {
        return false;
    }
    
    private void cancelPendingSelfStop() {
        this.handler.removeCallbacks(this.stopServiceRunnable);
    }
    
    public static Intent createShowMdxPlayerBroadcastIntent() {
        return new Intent("com.netflix.mediaclient.service.ACTION_SHOW_MDX_PLAYER");
    }
    
    private void doStartCommand(final Intent intent, final int n, final int n2) {
        Log.d("NetflixService", "Received start command intent ", intent);
        if (!StringUtils.isEmpty(intent.getAction())) {
            this.cancelPendingSelfStop();
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.MDX") && this.mMdxEnabled) {
                Log.d("NetflixService", "MDX command intent ");
                this.mMdxAgent.handleCommand(intent);
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.PUSH") && (this.mPushAgent.isSupported() || intent.hasExtra("swiped_social_notification_id"))) {
                Log.d("NetflixService", "Push notification command intent ");
                this.mPushAgent.handleCommand(intent);
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.LOGGING")) {
                Log.d("NetflixService", "Client logging command intent ");
                this.mClientLoggingAgent.handleCommand(intent);
            }
        }
    }
    
    private void enableMdxAgentAndInit(final ServiceAgent$AgentContext serviceAgent$AgentContext, final ServiceAgent$InitCallback serviceAgent$InitCallback) {
        this.mMdxEnabled = (AndroidUtils.getAndroidVersion() > 8 && !this.mConfigurationAgent.isDisableMdx());
        if (this.mMdxEnabled) {
            this.mMdxAgent = new MdxAgent();
            this.registerMdxReceiver();
            this.mMdxAgent.init(serviceAgent$AgentContext, serviceAgent$InitCallback);
        }
    }
    
    private void init() {
        final NetflixService$1 netflixService$1 = new NetflixService$1(this);
        if (!ConnectivityUtils.isConnectedOrConnecting((Context)this)) {
            this.mInitStatusCode = CommonStatus.NO_CONNECTIVITY;
            this.initCompleted();
            this.stopSelf();
            return;
        }
        Log.i("NetflixService", "NetflixService initing...");
        this.mConfigurationAgent.init(this.agentContext, netflixService$1);
        Log.i("NetflixService", "Service has " + 90000L / 1000L + " seconds to init or else we fail...");
        this.handler.postDelayed(this.initTimeoutRunnable, 90000L);
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
        this.mClientLoggingAgent.getApplicationPerformanceMetricsLogging().startApplicationSession(true);
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
            if (Log.isLoggable("NetflixService", 3)) {
                Log.d("NetflixService", "Unregister " + s);
            }
            this.unregisterReceiver(broadcastReceiver);
        }
        catch (Throwable t) {
            Log.e("NetflixService", "Unregister " + s + " failed.", t);
        }
    }
    
    public static void toggleFetchErrorsEnabled() {
        NetflixService.fetchErrorsEnabled = !NetflixService.fetchErrorsEnabled;
    }
    
    public void addProfile(final String s, final boolean b, final String s2, final int n, final int n2) {
        this.mUserAgent.addWebUserProfile(s, b, s2, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void connectWithFacebook(final String s, final int n, final int n2) {
        this.mUserAgent.connectWithFacebook(s, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void editProfile(final String s, final String s2, final boolean b, final String s3, final int n, final int n2) {
        this.mUserAgent.editWebUserProfile(s, s2, b, s3, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void fetchResource(final String s, final IClientLogging$AssetType clientLogging$AssetType, final int n, final int n2) {
        this.mResourceFetcher.fetchResource(s, clientLogging$AssetType, new NetflixService$ResourceFetcherClientCallback(this, n, n2));
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
        return this.mBrowseAccess;
    }
    
    public BrowseAccess getBrowseAgent() {
        return this.mBrowseAccess;
    }
    
    public IClientLogging getClientLogging() {
        return this.mClientLoggingAgent;
    }
    
    public ServiceAgent$ConfigurationAgentInterface getConfiguration() {
        return this.mConfigurationAgent;
    }
    
    public String getCurrentAppLocale() {
        return this.mUserAgent.getCurrentAppLocale();
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
    
    public FalkorAccess getFalkorAgent() {
        return this.mFalkorAccess;
    }
    
    public void getFriendsForRecommendationList(final String s, final int n, final String s2, final int n2, final int n3) {
        this.mUserAgent.fetchFriendsForRecommendations(s, n, s2, new NetflixService$UserAgentClientCallback(this, n2, n3));
    }
    
    public Handler getHandler() {
        return this.handler;
    }
    
    public ImageLoader getImageLoader() {
        return this.mResourceFetcher.getImageLoader((Context)this);
    }
    
    public IMdx getMdx() {
        return this.mMdxAgent;
    }
    
    public IPlayer getNflxPlayer() {
        return this.mPlayerAgent;
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
    
    public IPushNotification getPushNotification() {
        return this.mPushAgent;
    }
    
    public SignUpParams getSignUpParams() {
        return new NetflixService$2(this);
    }
    
    public String getSoftwareVersion() {
        return this.mConfigurationAgent.getSoftwareVersion();
    }
    
    public boolean isCurrentProfileFacebookConnected() {
        return this.mUserAgent.isCurrentProfileFacebookConnected();
    }
    
    public boolean isCurrentProfileIQEnabled() {
        return this.mUserAgent.isCurrentProfileIQEnabled();
    }
    
    public boolean isDeviceHd() {
        return this.mConfigurationAgent.isDeviceHd();
    }
    
    public boolean isProfileSwitchingDisabled() {
        return this.mUserAgent.isProfileSwitchingDisabled();
    }
    
    public boolean isTablet() {
        return this.mConfigurationAgent.isTablet();
    }
    
    public boolean isUserLoggedIn() {
        return this.mUserAgent.isUserLoggedIn();
    }
    
    public boolean isWidgetInstalled() {
        return this.agentContext.getPreAppAgent().isWidgetInstalled();
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
    
    public IBinder onBind(final Intent intent) {
        Log.d("NetflixService", "NetflixService is onBind");
        this.cancelPendingSelfStop();
        return this.mBinder;
    }
    
    public void onCreate() {
        Log.i("NetflixService", "NetflixService.onCreate.");
        super.onCreate();
        NetflixService.isCreated = true;
        this.handler = new Handler();
        this.mConfigurationAgent = new ConfigurationAgent();
        this.mNrdController = new NrdController();
        this.mUserAgent = new UserAgent();
        this.mResourceFetcher = new ResourceFetcher();
        this.mPlayerAgent = new PlayerAgent();
        this.mPushAgent = new PushNotificationAgent();
        this.mClientLoggingAgent = new LoggingAgent();
        this.mDiagnosisAgent = new DiagnosisAgent();
        this.mBrowseAgent = new BrowseAgent();
        this.mBrowseAccess = new BrowseAccess(this.mBrowseAgent, this.mClientCallbacks);
        this.mPreAppAgent = new PreAppAgent();
        this.init();
    }
    
    public void onDestroy() {
        super.onDestroy();
        Log.i("NetflixService", "NetflixService.onDestroy.");
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
        if (this.mMdxEnabled) {
            this.mMdxAgent.destroy();
        }
        if (this.mBrowseAgent != null) {
            this.mBrowseAgent.destroy();
        }
        if (this.mFalkorAgent != null) {
            this.mFalkorAgent.destroy();
        }
        this.mPlayerAgent.destroy();
        this.mUserAgent.destroy();
        this.mNrdController.destroy();
        this.mConfigurationAgent.destroy();
        this.mResourceFetcher.destroy();
        this.mClientLoggingAgent.destroy();
        this.mDiagnosisAgent.destroy();
        NetflixService.isCreated = false;
        final int myPid = Process.myPid();
        Log.d("NetflixService", "Destroying app proces " + myPid + "...");
        Process.killProcess(myPid);
        Log.d("NetflixService", "Destroying app proces " + myPid + " done.");
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
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
        Log.i("NetflixService", "No callbacks left - stopping service after delay of: 28800 seconds");
        this.stopSelfInMs(28800000L);
        return true;
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
            return;
        }
        this.mInitCallbacks.add(new NetflixService$NotifyServiceReadyInitCallback(this, put));
    }
    
    public void removeProfile(final String s, final int n, final int n2) {
        this.mUserAgent.removeWebUserProfile(s, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
    
    public void selectProfile(final String s) {
        this.mUserAgent.selectProfile(s);
    }
    
    public void sendRecommendationsToFriends(final String s, final Set<FriendForRecommendation> set, final String s2, final String s3) {
        this.mUserAgent.sendRecommendationsToFriends(s, set, s2, s3);
    }
    
    public void setCurrentAppLocale(final String currentAppLocale) {
        this.mUserAgent.setCurrentAppLocale(currentAppLocale);
    }
    
    public void stopSelfInMs(final long n) {
        this.handler.postDelayed(this.stopServiceRunnable, n);
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
    
    public void verifyPin(final String s, final int n, final int n2) {
        this.mUserAgent.verifyPin(s, new NetflixService$UserAgentClientCallback(this, n, n2));
    }
}
