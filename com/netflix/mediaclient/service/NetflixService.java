// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;
import android.os.Binder;
import android.util.SparseArray;
import com.netflix.mediaclient.servicemgr.VideoList;
import com.netflix.mediaclient.servicemgr.ShowDetails;
import com.netflix.mediaclient.servicemgr.SeasonDetails;
import com.netflix.mediaclient.servicemgr.SearchResults;
import com.netflix.mediaclient.servicemgr.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.MovieDetails;
import com.netflix.mediaclient.servicemgr.LoLoMo;
import com.netflix.mediaclient.servicemgr.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.Genre;
import com.netflix.mediaclient.servicemgr.GenreList;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.CWVideo;
import android.os.Process;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import com.netflix.mediaclient.servicemgr.IPushNotification;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.servicemgr.NrdpComponent;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.servicemgr.UserProfile;
import java.util.List;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.service.browse.BrowseAgentCallbackWrapper;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.servicemgr.INetflixServiceCallback;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.ConnectivityUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import android.util.Pair;
import android.app.Notification;
import com.netflix.mediaclient.ui.pin.PinVerifier;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.service.user.UserAgent;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.pushnotification.PushNotificationAgent;
import com.netflix.mediaclient.service.player.PlayerAgent;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import java.util.ArrayList;
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
    private static final long SERVICE_INIT_TIMEOUT_MS = 90000L;
    private static final long SERVICE_KILL_DELAY_MS = 28800000L;
    private static final String TAG = "NetflixService";
    private static boolean fetchErrorsEnabled;
    private final ServiceAgent.AgentContext agentContext;
    private Handler handler;
    private final Runnable initTimeoutRunnable;
    private final IBinder mBinder;
    private BrowseAgent mBrowseAgent;
    private final ClientCallbacks mClientCallbacks;
    private LoggingAgent mClientLoggingAgent;
    private ConfigurationAgent mConfigurationAgent;
    private final ArrayList<InitCallback> mInitCallbacks;
    private volatile boolean mInitComplete;
    private int mInitStatusCode;
    private MdxAgent mMdxAgent;
    private boolean mMdxEnabled;
    private final BroadcastReceiver mMdxReceiver;
    private final BroadcastReceiver mMdxShowPlayerIntent;
    private final BroadcastReceiver mNetworkChangeReceiver;
    private NrdController mNrdController;
    private PlayerAgent mPlayerAgent;
    private PushNotificationAgent mPushAgent;
    private ResourceFetcher mResourceFetcher;
    private UserAgent mUserAgent;
    private final Runnable stopServiceRunnable;
    
    static {
        NetflixService.fetchErrorsEnabled = false;
    }
    
    public NetflixService() {
        this.mClientCallbacks = new ClientCallbacks();
        this.mInitComplete = false;
        this.mInitStatusCode = -1;
        this.mInitCallbacks = new ArrayList<InitCallback>();
        this.mMdxEnabled = false;
        this.agentContext = new ServiceAgent.AgentContext() {
            @Override
            public NetflixApplication getApplication() {
                return (NetflixApplication)NetflixService.this.getApplication();
            }
            
            @Override
            public BrowseAgentInterface getBrowseAgent() {
                return NetflixService.this.mBrowseAgent;
            }
            
            @Override
            public ConfigurationAgentInterface getConfigurationAgent() {
                return NetflixService.this.mConfigurationAgent;
            }
            
            @Override
            public NrdController getNrdController() {
                return NetflixService.this.mNrdController;
            }
            
            @Override
            public ResourceFetcher getResourceFetcher() {
                return NetflixService.this.mResourceFetcher;
            }
            
            @Override
            public NetflixService getService() {
                return NetflixService.this;
            }
            
            @Override
            public UserAgentInterface getUserAgent() {
                return NetflixService.this.mUserAgent;
            }
        };
        this.mBinder = (IBinder)new LocalBinder();
        this.stopServiceRunnable = new Runnable() {
            @Override
            public void run() {
                Log.i("NetflixService", "Stopping service via runnable");
                NetflixService.this.stopSelf();
            }
        };
        this.initTimeoutRunnable = new Runnable() {
            @Override
            public void run() {
                NetflixService.this.initTimeout();
            }
        };
        this.mMdxReceiver = new BroadcastReceiver() {
            private Asset getMdxAgentVideoAsset() {
                final VideoDetails videoDetail = NetflixService.this.mMdxAgent.getVideoDetail();
                if (videoDetail != null && StringUtils.isNotEmpty(videoDetail.getId())) {
                    return Asset.create(videoDetail, PlayContext.EMPTY_CONTEXT, PlayerActivity.PIN_VERIFIED);
                }
                return null;
            }
            
            public void onReceive(final Context context, final Intent intent) {
                boolean b = false;
                final boolean b2 = false;
                final boolean b3 = false;
                final String action = intent.getAction();
                if ("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKEND".equals(action)) {
                    final boolean inPostPlay = MdxAgent.Utils.isInPostPlay(intent);
                    final NetflixService this$0 = NetflixService.this;
                    if (!inPostPlay) {
                        b = true;
                    }
                    this$0.stopForeground(b);
                    if (Log.isLoggable("NetflixService", 3)) {
                        Log.d("NetflixService", "mdx exit, stop service in 28800000ms");
                    }
                    NetflixService.this.handler.postDelayed(NetflixService.this.stopServiceRunnable, 28800000L);
                    final Asset mdxAgentVideoAsset = this.getMdxAgentVideoAsset();
                    boolean pinProtected = b3;
                    if (mdxAgentVideoAsset != null) {
                        pinProtected = mdxAgentVideoAsset.isPinProtected();
                    }
                    PinVerifier.getInstance().registerPlayEvent(pinProtected);
                    Log.d("NetflixService", "Refreshing CW for MDXUPDATE_PLAYBACKEND...");
                    NetflixService.this.mBrowseAgent.refreshCW();
                }
                else if ("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKSTART".equals(action)) {
                    if (NetflixService.this.mMdxAgent == null || !NetflixService.this.mMdxAgent.hasActiveSession()) {
                        Log.e("NetflixService", "false MDXUPDATE_PLAYBACKSTART");
                        return;
                    }
                    Log.i("NetflixService", "start mdx notification");
                    NetflixService.this.handler.removeCallbacks(NetflixService.this.stopServiceRunnable);
                    final Pair<Integer, Notification> mdxNotification = NetflixService.this.mMdxAgent.getMdxNotification(false);
                    NetflixService.this.startForeground((int)mdxNotification.first, (Notification)mdxNotification.second);
                    final Asset mdxAgentVideoAsset2 = this.getMdxAgentVideoAsset();
                    if (mdxAgentVideoAsset2 != null) {
                        Log.d("NetflixService", "refreshing episodes data on play start");
                        NetflixService.this.mBrowseAgent.refreshEpisodesData(mdxAgentVideoAsset2);
                    }
                }
                else if ("com.netflix.mediaclient.intent.action.MDXUPDATE_STATE".equals(action)) {
                    final int intExtra = intent.getIntExtra("time", -1);
                    Log.v("NetflixService", "on MDX state update - received updated mdx position: " + intExtra);
                    final Asset mdxAgentVideoAsset3 = this.getMdxAgentVideoAsset();
                    boolean pinProtected2 = b2;
                    if (mdxAgentVideoAsset3 != null) {
                        mdxAgentVideoAsset3.setPlaybackBookmark(intExtra);
                        Log.v("NetflixService", "updating cached video position");
                        NetflixService.this.mBrowseAgent.updateCachedVideoPosition(mdxAgentVideoAsset3);
                        pinProtected2 = mdxAgentVideoAsset3.isPinProtected();
                    }
                    PinVerifier.getInstance().registerPlayEvent(pinProtected2);
                }
            }
        };
        this.mMdxShowPlayerIntent = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (intent == null || !"com.netflix.mediaclient.service.ACTION_SHOW_MDX_PLAYER".equals(intent.getAction())) {
                    Log.v("NetflixService", "Invalid intent: ");
                    AndroidUtils.logIntent("NetflixService", intent);
                    return;
                }
                Log.v("NetflixService", "Sending show app intent");
                NetflixService.this.startActivity(NetflixApplication.createShowApplicationIntent((Context)NetflixService.this).addFlags(268435456));
                NetflixService.this.handler.postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        Log.v("NetflixService", "Sending show mini player intent");
                        NetflixService.this.sendBroadcast(new Intent("com.netflix.mediaclient.service.ACTION_EXPAND_MDX_MINI_PLAYER"));
                    }
                }, 400L);
            }
        };
        this.mNetworkChangeReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                NetflixService.this.mNrdController.setNetworkInterfaces();
                NetflixService.this.mPlayerAgent.handleConnectivityChange(intent);
                NetflixService.this.mClientLoggingAgent.handleConnectivityChange(intent);
            }
        };
    }
    
    public static boolean areFetchErrorsEnabled() {
        return false;
    }
    
    public static Intent createShowMdxPlayerBroadcastIntent() {
        return new Intent("com.netflix.mediaclient.service.ACTION_SHOW_MDX_PLAYER");
    }
    
    private void doStartCommand(final Intent intent, final int n, final int n2) {
        if (Log.isLoggable("NetflixService", 4)) {
            Log.i("NetflixService", "Received start command intent " + intent);
        }
        if (!StringUtils.isEmpty(intent.getAction())) {
            this.handler.removeCallbacks(this.stopServiceRunnable);
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.MDX") && this.mMdxEnabled) {
                Log.d("NetflixService", "MDX command intent ");
                this.mMdxAgent.handleCommand(intent);
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.PUSH") && this.mPushAgent.isSupported()) {
                Log.d("NetflixService", "Push notification command intent ");
                this.mPushAgent.handleCommand(intent);
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.LOGGING")) {
                Log.d("NetflixService", "Client logging command intent ");
                this.mClientLoggingAgent.handleCommand(intent);
            }
        }
    }
    
    private void enableMdxAgentAndInit(final ServiceAgent.AgentContext agentContext, final ServiceAgent.InitCallback initCallback) {
        this.mMdxEnabled = (AndroidUtils.getAndroidVersion() > 8 && !this.mConfigurationAgent.isDisableMdx());
        if (this.mMdxEnabled) {
            this.mMdxAgent = new MdxAgent();
            this.registerMdxReceiver();
            this.mMdxAgent.init(agentContext, initCallback);
        }
    }
    
    private void init() {
        final ServiceAgent.InitCallback initCallback = new ServiceAgent.InitCallback() {
            private final ArrayList<ServiceAgent> agentsToInitBatch1 = new ArrayList<ServiceAgent>() {
                {
                    ((ArrayList<NrdController>)this).add(NetflixService.this.mNrdController);
                    ((ArrayList<ResourceFetcher>)this).add(NetflixService.this.mResourceFetcher);
                    ((ArrayList<LoggingAgent>)this).add(NetflixService.this.mClientLoggingAgent);
                }
            };
            private final ArrayList<ServiceAgent> agentsToInitBatch2 = new ArrayList<ServiceAgent>() {
                {
                    ((ArrayList<BrowseAgent>)this).add(NetflixService.this.mBrowseAgent);
                    ((ArrayList<UserAgent>)this).add(NetflixService.this.mUserAgent);
                    ((ArrayList<PlayerAgent>)this).add(NetflixService.this.mPlayerAgent);
                    ((ArrayList<PushNotificationAgent>)this).add(NetflixService.this.mPushAgent);
                }
            };
            
            @Override
            public void onInitComplete(final ServiceAgent serviceAgent, final int n) {
                ThreadUtils.assertOnMain();
                if (n < 0) {
                    Log.e("NetflixService", "NetflixService init failed with ServiceAgent " + serviceAgent.getClass().getSimpleName() + " statusCode=" + n);
                    NetflixService.this.mInitStatusCode = n;
                    NetflixService.this.initCompleted();
                    NetflixService.this.stopSelf();
                }
                else {
                    Log.i("NetflixService", "NetflixService successfully inited ServiceAgent " + serviceAgent.getClass().getSimpleName());
                    if (serviceAgent == NetflixService.this.mConfigurationAgent) {
                        final Iterator<ServiceAgent> iterator = this.agentsToInitBatch1.iterator();
                        while (iterator.hasNext()) {
                            iterator.next().init(NetflixService.this.agentContext, (ServiceAgent.InitCallback)this);
                        }
                    }
                    else if (this.agentsToInitBatch1.contains(serviceAgent)) {
                        this.agentsToInitBatch1.remove(serviceAgent);
                        if (this.agentsToInitBatch1.isEmpty()) {
                            Log.d("NetflixService", "NetflixService successfully inited batch1 of ServiceAgents");
                            final Iterator<ServiceAgent> iterator2 = this.agentsToInitBatch2.iterator();
                            while (iterator2.hasNext()) {
                                iterator2.next().init(NetflixService.this.agentContext, (ServiceAgent.InitCallback)this);
                            }
                            NetflixService.this.enableMdxAgentAndInit(NetflixService.this.agentContext, this);
                        }
                    }
                    else {
                        this.agentsToInitBatch2.remove(serviceAgent);
                        if (this.agentsToInitBatch2.isEmpty()) {
                            Log.i("NetflixService", "NetflixService successfully inited all ServiceAgents ");
                            NetflixService.this.mInitStatusCode = n;
                            if (NetflixService.this.mInitStatusCode == 0) {
                                if (NetflixService.this.mConfigurationAgent.isAppVersionObsolete()) {
                                    NetflixService.this.mInitStatusCode = -5;
                                    Log.w("NetflixService", "Current app is obsolete. It should not run!");
                                }
                                else if (!NetflixService.this.mConfigurationAgent.isAppVersionRecommended()) {
                                    Log.w("NetflixService", "Current app is not recommended. User should be warned!");
                                    NetflixService.this.mInitStatusCode = 1;
                                }
                            }
                            NetflixService.this.initCompleted();
                        }
                        for (final ServiceAgent serviceAgent2 : this.agentsToInitBatch2) {
                            if (!serviceAgent2.isReady()) {
                                Log.d("NetflixService", "NetflixService still waiting for init of ServiceAgent " + serviceAgent2.getClass().getSimpleName());
                            }
                        }
                    }
                }
            }
        };
        if (!ConnectivityUtils.isConnectedOrConnecting((Context)this)) {
            this.mInitStatusCode = -11;
            this.initCompleted();
            this.stopSelf();
            return;
        }
        Log.i("NetflixService", "NetflixService initing...");
        this.mConfigurationAgent.init(this.agentContext, (ServiceAgent.InitCallback)initCallback);
        Log.i("NetflixService", "Service has " + 90000L / 1000L + " seconds to init or else we fail...");
        this.handler.postDelayed(this.initTimeoutRunnable, 90000L);
    }
    
    private void initCompleted() {
        ThreadUtils.assertOnMain();
        this.handler.removeCallbacks(this.initTimeoutRunnable);
        this.postApplicationStartedEvent();
        Log.d("NetflixService", "Invoking InitCallbacks...");
        final Iterator<InitCallback> iterator = this.mInitCallbacks.iterator();
        while (iterator.hasNext()) {
            iterator.next().onInitComplete();
        }
        this.mInitCallbacks.clear();
        this.mInitComplete = true;
        if (isServiceReady(this.mInitStatusCode)) {
            this.registerReceiver(this.mNetworkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.registerReceiver(this.mMdxShowPlayerIntent, new IntentFilter("com.netflix.mediaclient.service.ACTION_SHOW_MDX_PLAYER"));
            this.mNrdController.setNetworkInterfaces();
            Log.d("NetflixService", "Send local intent that Netflix service is ready");
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_INIT_COMPLETE");
            intent.putExtra("status_code", this.mInitStatusCode);
            intent.addCategory("com.netflix.mediaclient.intent.category.NETFLIX_SERVICE");
            LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
        }
        Log.i("NetflixService", "StopService runnable posted - service will die in " + 60 + " seconds unless bound to or started...");
        this.handler.postDelayed(this.stopServiceRunnable, (long)60000);
    }
    
    private void initTimeout() {
        Log.i("NetflixService", "Service init has timed out");
        this.mInitStatusCode = -9;
        this.initCompleted();
        this.stopSelf();
    }
    
    public static boolean isServiceReady(final int n) {
        return n >= 0;
    }
    
    private void notifyServiceReady(final int n, final int n2) {
        Log.d("NetflixService", "Notifying client " + n + " that service is ready, status code: " + n2);
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.mClientCallbacks.get(n);
        if (netflixServiceCallback != null) {
            netflixServiceCallback.onServiceReady(n, n2);
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
        this.mClientLoggingAgent.getApplicationPerformanceMetricsLogging().startUserSession(ApplicationPerformanceMetricsLogging.Trigger.appStart);
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
    
    public static boolean toggleFetchErrorsEnabled() {
        return NetflixService.fetchErrorsEnabled = !NetflixService.fetchErrorsEnabled;
    }
    
    private BrowseAgentCallback wrapCallback(final BrowseAgentCallback browseAgentCallback) {
        return new BrowseAgentCallbackWrapper(browseAgentCallback);
    }
    
    public void addToQueue(final String s, final int n, final int n2, final int n3) {
        this.mBrowseAgent.addToQueue(s, n, this.wrapCallback(new BrowseAgentClientCallback(n2, n3)));
    }
    
    public void connectWithFacebook(final String s, final int n, final int n2) {
        this.mUserAgent.connectWithFacebook(s, (UserAgent.UserAgentCallback)new UserAgentClientCallback(n, n2));
    }
    
    public void dumpHomeLoLoMosAndVideos(final String s, final String s2) {
        this.mBrowseAgent.dumpHomeLoLoMosAndVideos(s, s2);
    }
    
    public void dumpHomeLoLoMosAndVideosToLog() {
        this.mBrowseAgent.dumpHomeLoLoMosAndVideosToLog();
    }
    
    public void dumpHomeLoMos() {
        this.mBrowseAgent.dumpHomeLoMos();
    }
    
    public void fetchCWVideos(final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchCWVideos(n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    public void fetchEpisodeDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchEpisodeDetails(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    public void fetchEpisodes(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchEpisodes(s, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    public void fetchGenreLists(final int n, final int n2) {
        this.mBrowseAgent.fetchGenreList(this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchGenreVideos(loMo, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    public void fetchGenres(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchGenres(s, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    public void fetchIQVideos(final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchIQVideos(n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    public void fetchKidsCharacterDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchKidsCharacterDetails(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    public void fetchLoLoMoSummary(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchLoLoMoSummary(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    public void fetchLoMos(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchLoMos(s, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    public void fetchMovieDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchMovieDetails(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    public void fetchPostPlayVideos(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchPostPlayVideos(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    public void fetchResource(final String s, final IClientLogging.AssetType assetType, final int n, final int n2) {
        this.mResourceFetcher.fetchResource(s, assetType, new ResourceFetcherClientCallback(n, n2));
    }
    
    public void fetchSeasonDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchSeasonDetails(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    public void fetchSeasons(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchSeasons(s, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    public void fetchShowDetails(final String s, final String s2, final int n, final int n2) {
        this.mBrowseAgent.fetchShowDetails(s, s2, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    public void fetchSimilarVideosForPerson(final String s, final int n, final int n2, final int n3) {
        this.mBrowseAgent.fetchSimilarVideosForPerson(s, n, this.wrapCallback(new BrowseAgentClientCallback(n2, n3)));
    }
    
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final int n2, final int n3) {
        this.mBrowseAgent.fetchSimilarVideosForQuerySuggestion(s, n, this.wrapCallback(new BrowseAgentClientCallback(n2, n3)));
    }
    
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchVideos(loMo, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    public void flushCaches() {
        this.mBrowseAgent.flushCaches();
    }
    
    public List<? extends UserProfile> getAllProfiles() {
        return this.mUserAgent.getAllProfiles();
    }
    
    public IClientLogging getClientLogging() {
        return this.mClientLoggingAgent;
    }
    
    public ServiceAgent.ConfigurationAgentInterface getConfiguration() {
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
    
    public String getCurrentProfileUserId() {
        return this.mUserAgent.getCurrentProfileUserId();
    }
    
    public DeviceCategory getDeviceCategory() {
        return this.mConfigurationAgent.getDeviceCategory();
    }
    
    public EsnProvider getESN() {
        return this.mConfigurationAgent.getEsnProvider();
    }
    
    public ImageLoader getImageLoader() {
        return this.mResourceFetcher.getImageLoader();
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
        return new SignUpParams() {
            @Override
            public String getSignUpBootloader() {
                return NetflixService.this.mConfigurationAgent.getSignUpConfiguration().getSignUpBootloader();
            }
            
            @Override
            public long getSignUpTimeout() {
                return NetflixService.this.mConfigurationAgent.getSignUpConfiguration().getSignUpTimeout();
            }
            
            @Override
            public boolean isSignUpEnabled() {
                return NetflixService.this.mConfigurationAgent.getSignUpConfiguration().isSignEnabled();
            }
        };
    }
    
    public String getSoftwareVersion() {
        return this.mConfigurationAgent.getSoftwareVersion();
    }
    
    public String getUserId() {
        return this.mUserAgent.getUserId();
    }
    
    public void hideVideo(final String s, final int n, final int n2) {
        this.mBrowseAgent.hideVideo(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
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
    
    public void logBillboardActivity(final Video video, final BrowseAgent.BillboardActivityType billboardActivityType) {
        this.mBrowseAgent.logBillboardActivity(video, billboardActivityType);
    }
    
    public void loginUser(final String s, final String s2, final int n, final int n2) {
        this.mUserAgent.loginUser(s, s2, (UserAgent.UserAgentCallback)new UserAgentClientCallback(n, n2));
    }
    
    public void loginUserByTokens(final ActivationTokens activationTokens, final int n, final int n2) {
        this.mUserAgent.tokenActivate(activationTokens, (UserAgent.UserAgentCallback)new UserAgentClientCallback(n, n2));
    }
    
    public void logoutUser(final int n, final int n2) {
        this.mUserAgent.logoutUser((UserAgent.UserAgentCallback)new UserAgentClientCallback(n, n2));
    }
    
    public IBinder onBind(final Intent intent) {
        Log.d("NetflixService", "NetflixService is onBind");
        this.handler.removeCallbacks(this.stopServiceRunnable);
        return this.mBinder;
    }
    
    public void onCreate() {
        Log.i("NetflixService", "NetflixService.onCreate.");
        super.onCreate();
        this.handler = new Handler();
        this.mClientLoggingAgent = new LoggingAgent();
        this.mConfigurationAgent = new ConfigurationAgent();
        this.mNrdController = new NrdController();
        this.mUserAgent = new UserAgent();
        this.mBrowseAgent = new BrowseAgent();
        this.mResourceFetcher = new ResourceFetcher();
        this.mPlayerAgent = new PlayerAgent();
        this.mPushAgent = new PushNotificationAgent();
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
        this.mBrowseAgent.destroy();
        this.mPlayerAgent.destroy();
        this.mUserAgent.destroy();
        this.mNrdController.destroy();
        this.mConfigurationAgent.destroy();
        this.mResourceFetcher.destroy();
        this.mClientLoggingAgent.destroy();
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
                this.mInitCallbacks.add((InitCallback)new StartCommandInitCallback(intent, n, n2));
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
        this.handler.postDelayed(this.stopServiceRunnable, 28800000L);
        return true;
    }
    
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final int n5, final int n6) {
        this.mBrowseAgent.prefetchGenreLoLoMo(s, n, n2, n3, n4, b, this.wrapCallback(new BrowseAgentClientCallback(n5, n6)));
    }
    
    public void prefetchLoLoMo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final int n7, final int n8) {
        this.mBrowseAgent.prefetchLoLoMo(n, n2, n3, n4, n5, n6, b, b2, this.wrapCallback(new BrowseAgentClientCallback(n7, n8)));
    }
    
    public void refreshProfileSwitchingStatus() {
        this.mUserAgent.refreshProfileSwitchingStatus();
    }
    
    public void registerCallback(final INetflixServiceCallback netflixServiceCallback) {
        ThreadUtils.assertOnMain();
        this.handler.removeCallbacks(this.stopServiceRunnable);
        if (netflixServiceCallback == null) {
            throw new IllegalStateException(" registerCallback - cb is null");
        }
        final int put = this.mClientCallbacks.put(netflixServiceCallback);
        Log.i("NetflixService", "registerCallback, client: " + netflixServiceCallback.hashCode());
        if (this.mInitComplete) {
            this.notifyServiceReady(put, this.mInitStatusCode);
            return;
        }
        this.mInitCallbacks.add((InitCallback)new NotifyServiceReadyInitCallback(put));
    }
    
    public void removeFromQueue(final String s, final int n, final int n2) {
        this.mBrowseAgent.removeFromQueue(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    public void searchNetflix(final String s, final int n, final int n2) {
        this.mBrowseAgent.searchNetflix(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    public void selectProfile(final String s) {
        this.mUserAgent.selectProfile(s);
    }
    
    public void setCurrentAppLocale(final String currentAppLocale) {
        this.mUserAgent.setCurrentAppLocale(currentAppLocale);
    }
    
    public void setVideoRating(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.setVideoRating(s, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
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
        this.mUserAgent.verifyPin(s, (UserAgent.UserAgentCallback)new UserAgentClientCallback(n, n2));
    }
    
    private class BrowseAgentClientCallback implements BrowseAgentCallback
    {
        private final int clientId;
        private final int requestId;
        
        BrowseAgentClientCallback(final int clientId, final int requestId) {
            this.clientId = clientId;
            this.requestId = requestId;
        }
        
        @Override
        public void onCWListRefresh(final int n) {
            throw new IllegalStateException("not implemented");
        }
        
        @Override
        public void onCWVideosFetched(final List<CWVideo> list, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onCWVideosFetched");
                return;
            }
            netflixServiceCallback.onCWVideosFetched(this.requestId, list, n);
        }
        
        @Override
        public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onEpisodeDetailsFetched");
                return;
            }
            netflixServiceCallback.onEpisodeDetailsFetched(this.requestId, episodeDetails, n);
        }
        
        @Override
        public void onEpisodesFetched(final List<EpisodeDetails> list, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onEpisodesFetched");
                return;
            }
            netflixServiceCallback.onEpisodesFetched(this.requestId, list, n);
        }
        
        @Override
        public void onGenreListsFetched(final List<GenreList> list, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onGenreListsFetched");
                return;
            }
            netflixServiceCallback.onGenreListsFetched(this.requestId, list, n);
        }
        
        @Override
        public void onGenreLoLoMoPrefetched(final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for client onGenreLoLoMoPrefetched");
                return;
            }
            netflixServiceCallback.onGenreLoLoMoPrefetched(this.requestId, n);
        }
        
        @Override
        public void onGenresFetched(final List<Genre> list, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onGenresFetched");
                return;
            }
            netflixServiceCallback.onGenresFetched(this.requestId, list, n);
        }
        
        @Override
        public void onIQListRefresh(final int n) {
            throw new IllegalStateException("not implemented");
        }
        
        @Override
        public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onKidsCharacterDetailsFetched");
                return;
            }
            netflixServiceCallback.onKidsCharacterDetailsFetched(this.requestId, kidsCharacterDetails, b, n);
        }
        
        @Override
        public void onLoLoMoPrefetched(final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for client onLoLoMoPrefetched");
                return;
            }
            netflixServiceCallback.onLoLoMoPrefetched(this.requestId, n);
        }
        
        @Override
        public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onLoLoMoSummaryFetched");
                return;
            }
            netflixServiceCallback.onLoLoMoSummaryFetched(this.requestId, loLoMo, n);
        }
        
        @Override
        public void onLoMosFetched(final List<LoMo> list, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onLoMosFetched");
                return;
            }
            netflixServiceCallback.onLoMosFetched(this.requestId, list, n);
        }
        
        @Override
        public void onMovieDetailsFetched(final MovieDetails movieDetails, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onMovieDetailsFetched");
                return;
            }
            netflixServiceCallback.onMovieDetailsFetched(this.requestId, movieDetails, n);
        }
        
        @Override
        public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onPostPlayVideosFetched");
                return;
            }
            netflixServiceCallback.onPostPlayVideosFetched(this.requestId, list, n);
        }
        
        @Override
        public void onQueueAdd(final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onQueueAdd");
                return;
            }
            netflixServiceCallback.onQueueAdd(this.requestId, n);
        }
        
        @Override
        public void onQueueRemove(final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onQueueRemove");
                return;
            }
            netflixServiceCallback.onQueueRemove(this.requestId, n);
        }
        
        @Override
        public void onSearchResultsFetched(final SearchResults searchResults, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onSearchResultsFetched");
                return;
            }
            netflixServiceCallback.onSearchResultsFetched(this.requestId, searchResults, n);
        }
        
        @Override
        public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onSeasonDetailsFetched");
                return;
            }
            netflixServiceCallback.onSeasonDetailsFetched(this.requestId, seasonDetails, n);
        }
        
        @Override
        public void onSeasonsFetched(final List<SeasonDetails> list, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onSeasonsFetched");
                return;
            }
            netflixServiceCallback.onSeasonsFetched(this.requestId, list, n);
        }
        
        @Override
        public void onShowDetailsFetched(final ShowDetails showDetails, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onShowDetailsFetched");
                return;
            }
            netflixServiceCallback.onShowDetailsFetched(this.requestId, showDetails, n);
        }
        
        @Override
        public void onSimilarVideosFetched(final VideoList list, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onSimilarVideosFetched");
                return;
            }
            netflixServiceCallback.onSimilarVideosFetched(this.requestId, list, n);
        }
        
        @Override
        public void onVideoHide(final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onVideoHide");
                return;
            }
            netflixServiceCallback.onVideoHide(this.requestId, n);
        }
        
        @Override
        public void onVideoRatingSet(final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onVideoRatingSet");
                return;
            }
            netflixServiceCallback.onVideoRatingSet(this.requestId, n);
        }
        
        @Override
        public void onVideosFetched(final List<Video> list, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onVideosFetched");
                return;
            }
            netflixServiceCallback.onVideosFetched(this.requestId, list, n);
        }
    }
    
    private static class ClientCallbacks extends SparseArray<INetflixServiceCallback>
    {
        public int put(final INetflixServiceCallback netflixServiceCallback) {
            ThreadUtils.assertOnMain();
            final int hashCode = netflixServiceCallback.hashCode();
            super.put(hashCode, (Object)netflixServiceCallback);
            return hashCode;
        }
        
        public INetflixServiceCallback remove(INetflixServiceCallback netflixServiceCallback) {
            ThreadUtils.assertOnMain();
            final int hashCode = netflixServiceCallback.hashCode();
            netflixServiceCallback = (INetflixServiceCallback)this.get(hashCode);
            super.remove(hashCode);
            return netflixServiceCallback;
        }
    }
    
    private interface InitCallback
    {
        void onInitComplete();
    }
    
    public class LocalBinder extends Binder
    {
        public NetflixService getService() {
            return NetflixService.this;
        }
    }
    
    private class NotifyServiceReadyInitCallback implements InitCallback
    {
        private final int clientId;
        
        public NotifyServiceReadyInitCallback(final int clientId) {
            this.clientId = clientId;
        }
        
        @Override
        public void onInitComplete() {
            NetflixService.this.notifyServiceReady(this.clientId, NetflixService.this.mInitStatusCode);
        }
    }
    
    private class ResourceFetcherClientCallback extends LoggingResourceFetcherCallback
    {
        private final int clientId;
        private final int requestId;
        
        ResourceFetcherClientCallback(final int clientId, final int requestId) {
            this.clientId = clientId;
            this.requestId = requestId;
        }
        
        @Override
        public void onResourceFetched(final String s, final String s2, final int n) {
            super.onResourceFetched(s, s2, n);
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onResourceFetched");
                return;
            }
            netflixServiceCallback.onResourceFetched(this.requestId, s, s2, n);
        }
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
            NetflixService.this.doStartCommand(this.intent, this.flags, this.startId);
        }
    }
    
    private class UserAgentClientCallback implements UserAgentCallback
    {
        private final int clientId;
        private final int requestId;
        
        UserAgentClientCallback(final int clientId, final int requestId) {
            this.clientId = clientId;
            this.requestId = requestId;
        }
        
        @Override
        public void onConnectWithFacebook(final int n, final String s) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onConnectWithFacebook");
                return;
            }
            Log.d("NetflixService", "Notified onConnectWithFacebook");
            netflixServiceCallback.onConnectWithFacebookComplete(this.requestId, n, s);
        }
        
        @Override
        public void onLoginComplete(final int n, final String s) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onLoginComplete");
                return;
            }
            Log.d("NetflixService", "Notified onLoginComplete");
            netflixServiceCallback.onLoginComplete(this.requestId, n, s);
        }
        
        @Override
        public void onLogoutComplete(final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onLogoutComplete");
                return;
            }
            Log.d("NetflixService", "Notified onLogoutComplete");
            netflixServiceCallback.onLogoutComplete(this.requestId, n);
        }
        
        @Override
        public void onPinVerified(final boolean b, final int n) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)NetflixService.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixService", "No client callback found for onPinVerified");
                return;
            }
            Log.d("NetflixService", "Notified onPinVerified");
            netflixServiceCallback.onPinVerified(this.requestId, b, n);
        }
    }
}
