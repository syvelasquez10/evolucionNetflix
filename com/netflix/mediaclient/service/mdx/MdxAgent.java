// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Activity;
import com.netflix.mediaclient.event.nrdp.mdx.StateEvent;
import com.netflix.mediaclient.event.nrdp.mdx.InitErrorEvent;
import com.netflix.mediaclient.event.nrdp.mdx.InitEvent;
import com.netflix.mediaclient.event.nrdp.mdx.discovery.RemoteDeviceReadyEvent;
import com.netflix.mediaclient.event.nrdp.mdx.discovery.DeviceLostEvent;
import com.netflix.mediaclient.event.nrdp.mdx.discovery.DeviceFoundEvent;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import android.app.Notification;
import android.util.Pair;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.TransactionId;
import android.content.IntentFilter;
import android.annotation.SuppressLint;
import android.os.PowerManager;
import android.net.wifi.WifiManager;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.MovieDetails;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import android.content.res.Resources;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;
import android.app.PendingIntent;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.servicemgr.MdxPostplayState;
import android.net.wifi.WifiManager$WifiLock;
import com.netflix.mediaclient.util.WebApiUtils;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import java.util.ArrayList;
import android.content.BroadcastReceiver;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.PowerManager$WakeLock;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.service.mdx.cast.CastManager;
import android.graphics.Bitmap;
import com.netflix.mediaclient.media.BifManager;
import com.netflix.mediaclient.service.mdx.notification.MdxNotificationManager;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.service.ServiceAgent;

public class MdxAgent extends ServiceAgent implements IMdx, PropertyUpdateListener, MdxNotificationIntentRetriever, TargetSelectorInterface, SwitchTargetInterface, MdxImageLoaderInterface, SessionWatchDogInterface
{
    private static final int DEFAULT_INTEGER = -1;
    public static final String EVENT485_TRANSFERFROM_LOCAL = "local_playback_transfer";
    public static final String EVENT526_TYPE_FAIL = "association_failed";
    public static final String EVENT526_TYPE_FOUND = "found";
    public static final String EVENT526_TYPE_LOST = "lost";
    public static final String EVENT537_TYPE_CANCEL_PLAYBACK = "cancel playback";
    public static final String EVENT537_TYPE_LOCAL_PLAYBACK = "local playback";
    public static final String EVENT537_TYPE_TARGET_PLAYBACK = "target playback";
    private static final String TAG = "nf_mdx_agent";
    private BifManager mBifManager;
    private Bitmap mBoxartBitmap;
    private CastManager mCastManager;
    private CommandHandler mCommandHandler;
    private String mCurrentTargetUuid;
    private boolean mDisableWebSocket;
    private final EventListener mDiscoveryEventListener;
    private boolean mEnableCast;
    private final Handler mMdxAgentWorkerHandler;
    private HandlerThread mMdxAgentWorkerThread;
    private MdxImageLoader mMdxBoxartLoader;
    private MdxController mMdxController;
    private MdxNotificationManager mMdxNotificationManager;
    private MdxSessionWatchDog mMdxSessionWatchDog;
    private ClientNotifier mNotifier;
    private PowerManager$WakeLock mPartialWakeLock;
    private final AtomicBoolean mReady;
    private RemoteControlClientManager mRemoteControlClientManager;
    private final BroadcastReceiver mStartStopErrorReceiver;
    private int mStartTime;
    private final EventListener mStateEventListener;
    private SwitchTarget mSwitchTarget;
    private String mTaregtUuid;
    private String mTargetDialUuid;
    private String mTargetFriendlyName;
    private TargetManager mTargetManager;
    private final ArrayList<RemoteDevice> mTargetMap;
    private TargetSelector mTargetSelector;
    private int mTrackId;
    private final BroadcastReceiver mUserAgentReceiver;
    private boolean mUserIsLogin;
    private VideoDetails mVideoDetails;
    private WebApiUtils.VideoIds mVideoIds;
    private WifiManager$WifiLock mWifiLock;
    
    public MdxAgent() {
        this.mUserIsLogin = false;
        this.mTargetMap = new ArrayList<RemoteDevice>();
        this.mVideoIds = new WebApiUtils.VideoIds();
        this.mDisableWebSocket = false;
        this.mEnableCast = false;
        this.mStateEventListener = new StateEventListener();
        this.mDiscoveryEventListener = new DiscoveryEventListener();
        this.mStartStopErrorReceiver = new BroadcastReceiver() {
            private void updateMdxNotificationController(final String s) {
                final MdxPostplayState mdxPostplayState = new MdxPostplayState(s);
                if (mdxPostplayState.isInCountdown()) {
                    final MdxPostplayState.PostplayTitle[] postplayTitle = mdxPostplayState.getPostplayTitle();
                    if (postplayTitle.length > 0 && postplayTitle[0].isEpisode() && postplayTitle[0].getId() > 0) {
                        MdxAgent.this.mVideoIds = new WebApiUtils.VideoIds();
                        MdxAgent.this.mVideoIds.episode = true;
                        MdxAgent.this.mVideoIds.episodeId = postplayTitle[0].getId();
                        MdxAgent.this.fetchVideoDetail(false, true);
                    }
                }
            }
            
            private void updateMdxRemoteClient(String parentTitle) {
                if (MdxAgent.this.mVideoDetails != null) {
                    if (MdxAgent.this.mVideoDetails.getType() != VideoType.EPISODE) {
                        MdxAgent.this.mRemoteControlClientManager.setTitles(MdxAgent.this.mVideoDetails.getTitle(), null);
                        return;
                    }
                    final String string = MdxAgent.this.getContext().getString(2131493248, new Object[] { MdxAgent.this.mVideoDetails.getSeasonNumber(), MdxAgent.this.mVideoDetails.getEpisodeNumber(), MdxAgent.this.mVideoDetails.getTitle() });
                    if (TextUtils.isEmpty((CharSequence)parentTitle)) {
                        parentTitle = MdxAgent.this.mVideoDetails.getParentTitle();
                    }
                    MdxAgent.this.mRemoteControlClientManager.setTitles(string, parentTitle);
                }
            }
            
            public void onReceive(final Context context, final Intent intent) {
                if (intent.hasCategory("com.netflix.mediaclient.intent.category.MDX")) {
                    if (intent.getAction().equals("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKEND")) {
                        Log.d("nf_mdx_agent", "MdxAgent: receive MDXUPDATE_PLAYBACKEND");
                        MdxAgent.this.mMdxSessionWatchDog.stop();
                        MdxAgent.this.clearVideoDetails();
                        if (MdxAgent.this.mRemoteControlClientManager != null) {
                            MdxAgent.this.mRemoteControlClientManager.stop();
                        }
                        if (MdxAgent.this.mMdxNotificationManager != null) {
                            MdxAgent.this.mMdxNotificationManager.cancelNotification();
                            MdxAgent.this.mMdxNotificationManager = null;
                            MdxAgent.this.mBoxartBitmap = null;
                        }
                        MdxAgent.this.releaseWiFi();
                        if (MdxAgent.this.mTargetSelector != null) {
                            MdxAgent.this.mTargetSelector.targetBecomeInactive(MdxAgent.this.mCurrentTargetUuid);
                        }
                        if (MdxAgent.this.mSwitchTarget != null) {
                            MdxAgent.this.mSwitchTarget.targetPlaybackStopped(MdxAgent.this.mCurrentTargetUuid);
                        }
                    }
                    else if (intent.getAction().equals("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKSTART")) {
                        Log.d("nf_mdx_agent", "MdxAgent: receive MDXUPDATE_PLAYBACKSTART");
                        MdxAgent.this.mMdxSessionWatchDog.start();
                        MdxAgent.this.lockWiFi();
                        if (MdxAgent.this.mRemoteControlClientManager != null) {
                            MdxAgent.this.mRemoteControlClientManager.start();
                            if (MdxAgent.this.mBoxartBitmap != null) {
                                MdxAgent.this.mRemoteControlClientManager.setBoxart(MdxAgent.this.mBoxartBitmap);
                            }
                            this.updateMdxRemoteClient(null);
                        }
                        if (MdxAgent.this.mMdxNotificationManager != null) {
                            MdxAgent.this.mMdxNotificationManager.setBoxartNotify(MdxAgent.this.mBoxartBitmap);
                        }
                        if (MdxAgent.this.mTargetSelector != null) {
                            MdxAgent.this.mTargetSelector.targetBecomeActive(MdxAgent.this.mCurrentTargetUuid);
                        }
                    }
                    else if ("com.netflix.mediaclient.intent.action.MDXUPDATE_POSTPLAY".equals(intent.getAction())) {
                        final String string = intent.getExtras().getString("postplayState");
                        if (!StringUtils.isEmpty(string)) {
                            this.updateMdxNotificationController(string);
                            this.updateMdxRemoteClient(MdxAgent.this.getContext().getString(2131493240));
                            if (MdxAgent.this.mMdxNotificationManager != null) {
                                MdxAgent.this.mMdxNotificationManager.showSkipBack(false);
                            }
                        }
                    }
                    else if (intent.getAction().equals("com.netflix.mediaclient.intent.action.MDXUPDATE_SIMPLE_PLAYBACKSTATE")) {
                        final boolean booleanExtra = intent.getBooleanExtra("paused", false);
                        final boolean booleanExtra2 = intent.getBooleanExtra("transitioning", false);
                        if (Log.isLoggable("nf_mdx_agent", 3)) {
                            Log.d("nf_mdx_agent", "MdxAgent: simplePlaybackState : paused " + booleanExtra + ", transitioning " + booleanExtra2);
                        }
                        if (MdxAgent.this.mRemoteControlClientManager != null) {
                            MdxAgent.this.mRemoteControlClientManager.setState(booleanExtra, booleanExtra2);
                        }
                        if (MdxAgent.this.mMdxNotificationManager != null) {
                            MdxAgent.this.mMdxNotificationManager.setPauseStateNotify(booleanExtra, booleanExtra2);
                        }
                    }
                    else if (intent.getAction().equals("com.netflix.mediaclient.intent.action.MDXUPDATE_ERROR")) {
                        final int intExtra = intent.getIntExtra("errorCode", 0);
                        if (MdxAgent.this.mNotifier != null) {
                            final MdxSharedState sharedState = MdxAgent.this.mNotifier.getSharedState(MdxAgent.this.mCurrentTargetUuid);
                            if (sharedState != null) {
                                boolean b = false;
                                if (IMdxSharedState.MdxPlaybackState.Loading == sharedState.getMdxPlaybackState() || IMdxSharedState.MdxPlaybackState.Transitioning == sharedState.getMdxPlaybackState()) {
                                    b = true;
                                }
                                if (intExtra >= 100 && intExtra < 200 && b) {
                                    Log.d("nf_mdx_agent", "MdxAgent: received error, clear video detail");
                                    MdxAgent.this.clearVideoDetails();
                                }
                            }
                        }
                    }
                }
            }
        };
        this.mUserAgentReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (intent == null) {
                    Log.v("nf_mdx_agent", "Null intent");
                }
                else {
                    final String action = intent.getAction();
                    if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_ACTIVE".equals(action)) {
                        Log.d("nf_mdx_agent", "useprofile is active");
                        MdxAgent.this.notifyIsUserLogin(true);
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_DEACTIVE".equals(action)) {
                        Log.d("nf_mdx_agent", "useprofile is not active");
                        MdxAgent.this.notifyIsUserLogin(false);
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_ACTIVE".equals(action)) {
                        Log.d("nf_mdx_agent", "user account is activated");
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_DEACTIVE".equals(action)) {
                        Log.d("nf_mdx_agent", "user account is deactivated");
                    }
                }
            }
        };
        Log.d("nf_mdx_agent", "MdxAgent: start");
        this.mReady = new AtomicBoolean(false);
        this.mCurrentTargetUuid = new String();
        (this.mMdxAgentWorkerThread = new HandlerThread("MdxAgentWorker")).start();
        this.mMdxAgentWorkerHandler = new Handler(this.mMdxAgentWorkerThread.getLooper());
    }
    
    private void addDiscoveryEventListener() {
        this.mMdxController.addEventListener(Events.mdx_discovery_devicefound.getName(), this.mDiscoveryEventListener);
        this.mMdxController.addEventListener(Events.mdx_discovery_devicelost.getName(), this.mDiscoveryEventListener);
        this.mMdxController.addEventListener(Events.mdx_discovery_remoteDeviceReady.getName(), this.mDiscoveryEventListener);
    }
    
    private void addPairingEventListener(final EventListener eventListener) {
        this.mMdxController.addEventListener(Events.mdx_pair_pairingresponse.getName(), eventListener);
        this.mMdxController.addEventListener(Events.mdx_pair_regpairresponse.getName(), eventListener);
        this.mMdxController.addEventListener(Events.mdx_pair_pairingdeleted.getName(), eventListener);
    }
    
    private void addSessionEventListener(final EventListener eventListener) {
        this.mMdxController.addEventListener(Events.mdx_session_startSessionResponse.getName(), eventListener);
        this.mMdxController.addEventListener(Events.mdx_session_messagedelivered.getName(), eventListener);
        this.mMdxController.addEventListener(Events.mdx_session_message.getName(), eventListener);
        this.mMdxController.addEventListener(Events.mdx_session_messagingerror.getName(), eventListener);
        this.mMdxController.addEventListener(Events.mdx_session_sessionended.getName(), eventListener);
    }
    
    private void addStateEventListener() {
        this.mMdxController.addEventListener(Events.mdx_init.getName(), this.mStateEventListener);
        this.mMdxController.addEventListener(Events.mdx_initerror.getName(), this.mStateEventListener);
        this.mMdxController.addEventListener(Events.mdx_mdxstate.getName(), this.mStateEventListener);
    }
    
    private void clearVideoDetails() {
        this.mVideoIds = new WebApiUtils.VideoIds();
        this.mVideoDetails = null;
    }
    
    private void createBifManager(final String s) {
        if (this.mBifManager != null) {
            this.mBifManager.release();
        }
        this.mBifManager = new BifManager(this.getContext(), s);
    }
    
    private PendingIntent createNotificationButtonIntent(final Intent intent) {
        intent.setClass(this.getContext(), (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").putExtra("uuid", this.mCurrentTargetUuid);
        return PendingIntent.getService(this.getContext(), 0, intent, 134217728);
    }
    
    private void fetchVideoDetail(final boolean b, final boolean b2) {
        if (this.mVideoIds.episode) {
            this.getBrowseAgent().fetchEpisodeDetails(String.valueOf(this.mVideoIds.episodeId), new SimpleBrowseAgentCallback() {
                @Override
                public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, int n) {
                    if (n == 0) {
                        MdxAgent.this.mVideoDetails = episodeDetails;
                        final String highResolutionPortraitBoxArtUrl = episodeDetails.getHighResolutionPortraitBoxArtUrl();
                        if (MdxAgent.this.mMdxBoxartLoader != null) {
                            MdxAgent.this.mMdxBoxartLoader.fetchImage(highResolutionPortraitBoxArtUrl);
                        }
                        final String bifUrl = episodeDetails.getBifUrl();
                        if (StringUtils.isNotEmpty(bifUrl)) {
                            MdxAgent.this.createBifManager(bifUrl);
                        }
                        MdxAgent.this.mNotifier.movieMetaDataAvailable(MdxAgent.this.mCurrentTargetUuid);
                        if (b) {
                            MdxAgent.this.mVideoIds = new WebApiUtils.VideoIds(episodeDetails.isPlayableEpisode(), episodeDetails.getEpisodeIdUrl(), episodeDetails.getCatalogIdUrl(), Integer.parseInt(episodeDetails.getId()), Integer.parseInt(episodeDetails.getShowId()));
                            MdxAgent.this.mTargetManager.playerPlay(MdxAgent.this.mCurrentTargetUuid, MdxAgent.this.mVideoIds.catalogIdUrl, MdxAgent.this.mTrackId, MdxAgent.this.mVideoIds.episodeIdUrl, MdxAgent.this.mStartTime);
                            MdxAgent.this.logPlaystart(false);
                        }
                        final String string = MdxAgent.this.getContext().getString(2131493248, new Object[] { MdxAgent.this.mVideoDetails.getSeasonNumber(), MdxAgent.this.mVideoDetails.getEpisodeNumber(), MdxAgent.this.mVideoDetails.getTitle() });
                        final Resources resources = MdxAgent.this.getContext().getResources();
                        if (b2) {
                            n = 2131493240;
                        }
                        else {
                            n = 2131493239;
                        }
                        MdxAgent.this.updateMdxNotification(true, MdxAgent.this.mVideoDetails.getParentTitle(), string, resources.getString(n));
                    }
                }
            });
            return;
        }
        this.getBrowseAgent().fetchMovieDetails(String.valueOf(this.mVideoIds.catalogId), new SimpleBrowseAgentCallback() {
            @Override
            public void onMovieDetailsFetched(final MovieDetails movieDetails, int n) {
                if (n == 0) {
                    MdxAgent.this.mVideoDetails = movieDetails;
                    final String highResolutionPortraitBoxArtUrl = movieDetails.getHighResolutionPortraitBoxArtUrl();
                    if (MdxAgent.this.mMdxBoxartLoader != null) {
                        MdxAgent.this.mMdxBoxartLoader.fetchImage(highResolutionPortraitBoxArtUrl);
                    }
                    final String bifUrl = movieDetails.getBifUrl();
                    if (StringUtils.isNotEmpty(bifUrl)) {
                        MdxAgent.this.createBifManager(bifUrl);
                    }
                    MdxAgent.this.mNotifier.movieMetaDataAvailable(MdxAgent.this.mCurrentTargetUuid);
                    if (b) {
                        MdxAgent.this.mVideoIds = new WebApiUtils.VideoIds(movieDetails.isPlayableEpisode(), null, movieDetails.getCatalogIdUrl(), 0, Integer.parseInt(movieDetails.getId()));
                        MdxAgent.this.mTargetManager.playerPlay(MdxAgent.this.mCurrentTargetUuid, MdxAgent.this.mVideoIds.catalogIdUrl, MdxAgent.this.mTrackId, MdxAgent.this.mVideoIds.episodeIdUrl, MdxAgent.this.mStartTime);
                        MdxAgent.this.logPlaystart(false);
                    }
                    final Resources resources = MdxAgent.this.getContext().getResources();
                    if (false) {
                        n = 2131493240;
                    }
                    else {
                        n = 2131493239;
                    }
                    MdxAgent.this.updateMdxNotification(false, MdxAgent.this.mVideoDetails.getTitle(), null, resources.getString(n));
                }
            }
        });
    }
    
    private RemoteDevice getDeviceFromUuid(final String s) {
        while (true) {
        Label_0112:
            while (true) {
                final RemoteDevice remoteDevice;
                final String uuid;
                synchronized (this.mTargetMap) {
                    if (this.mTargetMap.isEmpty()) {
                        return null;
                    }
                    final Iterator<RemoteDevice> iterator = this.mTargetMap.iterator();
                    if (!iterator.hasNext()) {
                        break Label_0112;
                    }
                    remoteDevice = iterator.next();
                    uuid = remoteDevice.uuid;
                    final String dialUuid = remoteDevice.dialUuid;
                    if (StringUtils.isNotEmpty(dialUuid) && dialUuid.equals(s)) {
                        return remoteDevice;
                    }
                }
                if (StringUtils.isNotEmpty(uuid) && uuid.equals(s)) {
                    // monitorexit(list)
                    return remoteDevice;
                }
                continue;
            }
            // monitorexit(list)
            return null;
        }
    }
    
    private void handleAccountConfig() {
        this.mDisableWebSocket = this.getConfigurationAgent().isDisableWebsocket();
        this.mEnableCast = this.getConfigurationAgent().isEnableCast();
        if (this.mEnableCast) {
            (this.mCastManager = new CastManager(this.getContext(), this.getMainHandler(), this.mMdxAgentWorkerHandler)).setCastWhiteList(this.getConfigurationAgent().getCastWhiteList());
            if (StringUtils.isNotEmpty(this.mCurrentTargetUuid)) {
                this.mCastManager.setTargetId(this.mCurrentTargetUuid);
            }
            return;
        }
        this.mCastManager = null;
    }
    
    private boolean isSameDevice(final String s, final String s2) {
        boolean b = true;
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(s2)) {
            b = false;
        }
        else if (!s.equals(s2)) {
            synchronized (this.mTargetMap) {
                if (this.mTargetMap.isEmpty()) {
                    return false;
                }
                for (final RemoteDevice remoteDevice : this.mTargetMap) {
                    final String uuid = remoteDevice.uuid;
                    final String dialUuid = remoteDevice.dialUuid;
                    if ((s.equals(dialUuid) && s2.equals(uuid)) || (s.equals(uuid) && s2.equals(dialUuid))) {
                        return true;
                    }
                }
            }
            // monitorexit(list)
            return false;
        }
        return b;
    }
    
    @SuppressLint({ "InlinedApi" })
    private void lockWiFi() {
        this.releaseWiFi();
        final WifiManager wifiManager = (WifiManager)this.getContext().getSystemService("wifi");
        if (wifiManager != null) {
            Log.d("nf_mdx_agent", "WiFi lock acquiring...");
            (this.mWifiLock = wifiManager.createWifiLock(3, "nf_mdx_agent")).acquire();
            Log.d("nf_mdx_agent", "WiFi lock acquired.");
        }
        final PowerManager powerManager = (PowerManager)this.getContext().getSystemService("power");
        if (powerManager != null && this.mPartialWakeLock == null) {
            this.mPartialWakeLock = powerManager.newWakeLock(1, "nf_mdx_agent");
        }
        if (this.mPartialWakeLock != null && !this.mPartialWakeLock.isHeld()) {
            this.mPartialWakeLock.acquire();
        }
    }
    
    private void reconcileSelectedTargetInfo() {
        if (!StringUtils.isEmpty(this.mCurrentTargetUuid)) {
            final boolean b = false;
            final RemoteDevice deviceFromUuid = this.getDeviceFromUuid(this.mCurrentTargetUuid);
            RemoteDevice remoteDevice;
            boolean b2;
            if (deviceFromUuid == null) {
                if (this.getDeviceFromUuid(this.mTaregtUuid) != null) {
                    remoteDevice = this.getDeviceFromUuid(this.mTaregtUuid);
                    this.mCurrentTargetUuid = this.mTaregtUuid;
                    b2 = true;
                }
                else if (this.getDeviceFromUuid(this.mTargetDialUuid) != null) {
                    remoteDevice = this.getDeviceFromUuid(this.mTargetDialUuid);
                    this.mCurrentTargetUuid = this.mTargetDialUuid;
                    b2 = true;
                }
                else {
                    for (final RemoteDevice remoteDevice2 : this.mTargetMap) {
                        if (StringUtils.isNotEmpty(this.mTargetFriendlyName) && this.mTargetFriendlyName.equals(remoteDevice2.friendlyName)) {
                            if (StringUtils.isNotEmpty(remoteDevice2.dialUuid)) {
                                this.mCurrentTargetUuid = remoteDevice2.dialUuid;
                                break;
                            }
                            this.mCurrentTargetUuid = remoteDevice2.uuid;
                            break;
                        }
                    }
                    if (Log.isLoggable("nf_mdx_agent", 3)) {
                        Log.d("nf_mdx_agent", "MdxAgent: taregt no longer exist " + this.mCurrentTargetUuid);
                    }
                    return;
                }
            }
            else {
                remoteDevice = deviceFromUuid;
                b2 = b;
                if (StringUtils.isNotEmpty(deviceFromUuid.dialUuid)) {
                    remoteDevice = deviceFromUuid;
                    b2 = b;
                    if (!deviceFromUuid.dialUuid.equals(this.mCurrentTargetUuid)) {
                        this.mCurrentTargetUuid = deviceFromUuid.dialUuid;
                        b2 = true;
                        remoteDevice = deviceFromUuid;
                    }
                }
            }
            final String uuid = remoteDevice.uuid;
            final String dialUuid = remoteDevice.dialUuid;
            final String friendlyName = remoteDevice.friendlyName;
            boolean b3 = b2;
            if (StringUtils.isNotEmpty(uuid)) {
                b3 = b2;
                if (!uuid.equals(this.mTaregtUuid)) {
                    this.mTaregtUuid = uuid;
                    b3 = true;
                }
            }
            boolean b4 = b3;
            if (StringUtils.isNotEmpty(dialUuid)) {
                b4 = b3;
                if (!uuid.equals(this.mTargetDialUuid)) {
                    this.mTargetDialUuid = dialUuid;
                    b4 = true;
                }
            }
            boolean b5 = b4;
            if (StringUtils.isNotEmpty(friendlyName)) {
                b5 = b4;
                if (!uuid.equals(this.mTargetFriendlyName)) {
                    this.mTargetFriendlyName = friendlyName;
                    b5 = true;
                }
            }
            if (b5 && this.mTargetSelector != null) {
                this.mTargetSelector.updateSelectedTarget(this.mCurrentTargetUuid, this.mTaregtUuid, this.mTargetDialUuid, this.mTargetFriendlyName);
            }
        }
    }
    
    private void registerStartStopReceiver() {
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKEND");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKSTART");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_POSTPLAY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_SIMPLE_PLAYBACKSTATE");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_ERROR");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.MDX");
        this.getContext().registerReceiver(this.mStartStopErrorReceiver, intentFilter);
    }
    
    private void releaseWiFi() {
        if (this.mWifiLock != null && this.mWifiLock.isHeld()) {
            Log.d("nf_mdx_agent", "WiFi lock was held, release...");
            this.mWifiLock.release();
            Log.d("nf_mdx_agent", "WiFi lock released.");
        }
        if (this.mPartialWakeLock != null && this.mPartialWakeLock.isHeld()) {
            this.mPartialWakeLock.release();
        }
    }
    
    private void removeDiscoveryEventListener() {
        this.mMdxController.removeEventListener(Events.mdx_discovery_devicefound.getName(), this.mDiscoveryEventListener);
        this.mMdxController.removeEventListener(Events.mdx_discovery_devicelost.getName(), this.mDiscoveryEventListener);
        this.mMdxController.removeEventListener(Events.mdx_discovery_remoteDeviceReady.getName(), this.mDiscoveryEventListener);
    }
    
    private void removePairingEventListener(final EventListener eventListener) {
        this.mMdxController.removeEventListener(Events.mdx_pair_pairingresponse.getName(), eventListener);
        this.mMdxController.removeEventListener(Events.mdx_pair_regpairresponse.getName(), eventListener);
        this.mMdxController.removeEventListener(Events.mdx_pair_pairingdeleted.getName(), eventListener);
    }
    
    private void removeSessionEventListener(final EventListener eventListener) {
        this.mMdxController.removeEventListener(Events.mdx_session_startSessionResponse.getName(), eventListener);
        this.mMdxController.removeEventListener(Events.mdx_session_messagedelivered.getName(), eventListener);
        this.mMdxController.removeEventListener(Events.mdx_session_message.getName(), eventListener);
        this.mMdxController.removeEventListener(Events.mdx_session_messagingerror.getName(), eventListener);
        this.mMdxController.removeEventListener(Events.mdx_session_sessionended.getName(), eventListener);
    }
    
    private void removeStateEventListener() {
        this.mMdxController.removeEventListener(Events.mdx_init.getName(), this.mStateEventListener);
        this.mMdxController.removeEventListener(Events.mdx_initerror.getName(), this.mStateEventListener);
        this.mMdxController.removeEventListener(Events.mdx_mdxstate.getName(), this.mStateEventListener);
    }
    
    private void sessionGone() {
        if (this.mTargetManager != null) {
            this.mTargetManager.targetGone(this.mCurrentTargetUuid);
        }
        this.mNotifier.error(this.mCurrentTargetUuid, 201, "stop connecting to target");
        this.mNotifier.playbackEnd(this.mCurrentTargetUuid);
    }
    
    private void unregisterStartStopReceiver() {
        try {
            this.getContext().unregisterReceiver(this.mStartStopErrorReceiver);
        }
        catch (Exception ex) {
            Log.i("nf_mdx_agent", "unregistermStartStopReceiver " + ex);
        }
    }
    
    private void updateMdxNotification(final boolean b, final String s, final String s2, final String s3) {
        if (this.mRemoteControlClientManager != null) {
            this.mRemoteControlClientManager.setTitles(s, s2);
        }
        if (this.mMdxNotificationManager == null) {
            return;
        }
        synchronized (this.mMdxNotificationManager) {
            this.mMdxNotificationManager.setTitlesNotify(b, s, s2, s3);
        }
    }
    
    @Override
    public void destroy() {
        this.mMdxAgentWorkerThread.quit();
        while (true) {
            try {
                this.mMdxAgentWorkerThread.join();
                this.mMdxAgentWorkerThread = null;
                this.unregisterUserAgentReceiver();
                if (this.mMdxController != null) {
                    this.mMdxController.removePropertyUpdateListener();
                    this.removeStateEventListener();
                    this.removeDiscoveryEventListener();
                    this.removePairingEventListener(this.mTargetManager);
                    this.removeSessionEventListener(this.mTargetManager);
                    this.mMdxController = null;
                }
                if (this.mBifManager != null) {
                    this.mBifManager.release();
                    this.mBifManager = null;
                }
                if (this.mRemoteControlClientManager != null) {
                    this.mRemoteControlClientManager.stop();
                    this.mRemoteControlClientManager = null;
                }
                if (this.mCastManager != null) {
                    this.mCastManager.destroy();
                }
                this.unregisterStartStopReceiver();
                super.destroy();
            }
            catch (InterruptedException ex) {
                Log.e("nf_mdx_agent", "MdxAgent: mMdxAgentWorkerThread interrupted");
                continue;
            }
            break;
        }
    }
    
    @Override
    protected void doInit() {
        Log.e("nf_mdx_agent", "MdxAgent: doInit");
        if (this.getNrdController() == null || this.getNrdController().getNrdp() == null) {
            this.initCompleted(-4);
            return;
        }
        this.mMdxController = this.getNrdController().getNrdp().getMdxController();
        this.mNotifier = new ClientNotifier(this.getContext());
        this.mTargetManager = new TargetManager(this.mNotifier, this.mMdxController, this.getConfigurationAgent().getEsnProvider().getEsn());
        this.mCommandHandler = new CommandHandler(this.mTargetManager);
        if (this.mMdxController == null || this.mNotifier == null || this.mTargetManager == null || this.mCommandHandler == null) {
            this.initCompleted(-2);
            return;
        }
        this.mReady.set(false);
        this.mTargetMap.clear();
        this.mMdxController.setPropertyUpdateListener((MdxController.PropertyUpdateListener)this);
        TransactionId.setTransactionIdSource(this.getNrdController().getNrdp());
        if (Log.isLoggable("nf_mdx_agent", 3)) {
            Log.d("nf_mdx_agent", "MdxAgent: change XID base from " + System.currentTimeMillis() + " ==> " + this.getNrdController().getNrdp().now());
        }
        this.mMdxAgentWorkerHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                MdxAgent.this.mTargetSelector = new TargetSelector(MdxAgent.this.getContext(), (TargetSelector.TargetSelectorInterface)MdxAgent.this);
            }
        });
        this.mSwitchTarget = new SwitchTarget(this.mTargetManager, (SwitchTarget.SwitchTargetInterface)this);
        if (Log.isLoggable("nf_mdx_agent", 3)) {
            Log.d("nf_mdx_agent", "MdxAgent: doInit mCurrentTargetUuid: " + this.mCurrentTargetUuid);
        }
        this.mMdxBoxartLoader = new MdxImageLoader(this.getResourceFetcher(), (MdxImageLoader.MdxImageLoaderInterface)this, this.mMdxAgentWorkerHandler);
        this.mMdxSessionWatchDog = new MdxSessionWatchDog((MdxSessionWatchDog.SessionWatchDogInterface)this, this.mMdxAgentWorkerHandler);
        this.registerUserAgentReceiver();
        final UserAgentInterface userAgent = this.getUserAgent();
        if (userAgent != null) {
            this.notifyIsUserLogin(StringUtils.isNotEmpty(userAgent.getCurrentProfileId()) && userAgent.isUserLoggedIn());
        }
        else {
            Log.e("nf_mdx_agent", "MdxAgent: userAgent is not ready yet, skip init");
        }
        this.registerStartStopReceiver();
        this.mRemoteControlClientManager = new RemoteControlClientManager(this.getContext());
        this.initCompleted(0);
    }
    
    @Override
    public ByteBuffer getBifFrame(final int n) {
        if (this.mBifManager != null) {
            return this.mBifManager.getIndexFrame(n);
        }
        return null;
    }
    
    @Override
    public String getCurrentTarget() {
        if (Log.isLoggable("nf_mdx_agent", 3)) {
            Log.d("nf_mdx_agent", "MdxAgent: getCurrentTarget : " + this.mCurrentTargetUuid);
        }
        return this.mCurrentTargetUuid;
    }
    
    public Pair<Integer, Notification> getMdxNotification(final boolean b) {
        final WebApiUtils.VideoIds videoIds = this.mTargetManager.getVideoIds();
        if (videoIds != null) {
            if (videoIds.episode != this.mVideoIds.episode || (videoIds.episode && videoIds.episodeId != this.mVideoIds.episodeId) || videoIds.catalogId != this.mVideoIds.catalogId) {
                this.mVideoIds = videoIds;
                this.fetchVideoDetail(false, b);
                if (this.mMdxNotificationManager == null) {
                    this.mMdxNotificationManager = new MdxNotificationManager(this.getContext(), this.mVideoIds.episode, this);
                }
                return this.mMdxNotificationManager.getNotification();
            }
            Log.d("nf_mdx_agent", "MdxAgent: videoIds are all same");
            this.mNotifier.movieMetaDataAvailable(this.mCurrentTargetUuid);
            if (this.mMdxNotificationManager == null) {
                this.mMdxNotificationManager = new MdxNotificationManager(this.getContext(), this.mVideoIds.episode, this);
            }
            if (this.mBoxartBitmap != null) {
                this.mMdxNotificationManager.setBoxart(this.mBoxartBitmap);
            }
            if (this.mVideoDetails != null) {
                final Resources resources = this.getContext().getResources();
                int n;
                if (b) {
                    n = 2131493240;
                }
                else {
                    n = 2131493239;
                }
                final String string = resources.getString(n);
                if (this.mVideoIds.episode) {
                    this.mMdxNotificationManager.setTitlesNotify(true, this.mVideoDetails.getParentTitle(), this.getContext().getString(2131493248, new Object[] { this.mVideoDetails.getSeasonNumber(), this.mVideoDetails.getEpisodeNumber(), this.mVideoDetails.getTitle() }), string);
                }
                else {
                    this.mMdxNotificationManager.setTitlesNotify(false, this.mVideoDetails.getTitle(), null, string);
                }
            }
        }
        else {
            Log.d("nf_mdx_agent", "MdxAgent: new videoIds is null");
            if (this.mMdxNotificationManager == null) {
                this.mMdxNotificationManager = new MdxNotificationManager(this.getContext(), true, this);
            }
        }
        return this.mMdxNotificationManager.getNotification();
    }
    
    @Override
    public PendingIntent getNoActionIntent() {
        return PendingIntent.getService(this.getContext(), 0, new Intent(), 0);
    }
    
    @Override
    public PendingIntent getPauseIntent() {
        return this.createNotificationButtonIntent(new Intent("com.netflix.mediaclient.intent.action.MDX_PAUSE"));
    }
    
    @Override
    public PendingIntent getResumeIntent() {
        return this.createNotificationButtonIntent(new Intent("com.netflix.mediaclient.intent.action.MDX_RESUME"));
    }
    
    @Override
    public IMdxSharedState getSharedState() {
        if (StringUtils.isNotEmpty(this.mCurrentTargetUuid)) {
            return this.mNotifier.getSharedState(this.mCurrentTargetUuid);
        }
        return null;
    }
    
    @Override
    public PendingIntent getSkipbackIntent(final int n) {
        return this.createNotificationButtonIntent(new Intent("com.netflix.mediaclient.intent.action.MDX_SKIP").putExtra("time", n));
    }
    
    @Override
    public PendingIntent getStopIntent() {
        return this.createNotificationButtonIntent(new Intent("com.netflix.mediaclient.intent.action.MDX_STOP"));
    }
    
    @Override
    public Pair<String, String>[] getTargetList() {
        Pair[] array;
        int n;
        while (true) {
            while (true) {
                Label_0324: {
                    final RemoteDevice remoteDevice;
                    synchronized (this.mTargetMap) {
                        if (this.mTargetMap.isEmpty()) {
                            return null;
                        }
                        array = new Pair[this.mTargetMap.size()];
                        final Iterator<RemoteDevice> iterator = this.mTargetMap.iterator();
                        n = 0;
                        if (!iterator.hasNext()) {
                            break;
                        }
                        remoteDevice = iterator.next();
                        final String uuid = remoteDevice.uuid;
                        final String dialUuid = remoteDevice.dialUuid;
                        final String friendlyName = remoteDevice.friendlyName;
                        if (StringUtils.isNotEmpty(dialUuid)) {
                            final int n2 = n + 1;
                            array[n] = Pair.create((Object)dialUuid, (Object)friendlyName);
                            n = n2;
                            if (Log.isLoggable("nf_mdx_agent", 3)) {
                                Log.d("nf_mdx_agent", "MdxAgent: getTargetList : " + dialUuid + " : " + friendlyName);
                                n = n2;
                                break Label_0324;
                            }
                            break Label_0324;
                        }
                        else if (StringUtils.isNotEmpty(uuid)) {
                            final int n3 = n + 1;
                            array[n] = Pair.create((Object)uuid, (Object)friendlyName);
                            n = n3;
                            if (Log.isLoggable("nf_mdx_agent", 3)) {
                                Log.d("nf_mdx_agent", "MdxAgent: getTargetList : " + uuid + " : " + friendlyName);
                                n = n3;
                                break Label_0324;
                            }
                            break Label_0324;
                        }
                    }
                    Log.e("nf_mdx_agent", "MdxAgent: uuid and dialUuid are invalid " + remoteDevice);
                }
                continue;
            }
        }
        if (Log.isLoggable("nf_mdx_agent", 3)) {
            Log.d("nf_mdx_agent", "MdxAgent: getTargetList has " + n + " targets");
        }
        // monitorexit(list)
        return (Pair<String, String>[])array;
    }
    
    @Override
    public VideoDetails getVideoDetail() {
        return this.mVideoDetails;
    }
    
    @Override
    public WebApiUtils.VideoIds getVideoIds() {
        return this.mVideoIds;
    }
    
    public boolean handleCommand(final Intent intent) {
        if (StringUtils.isNotEmpty(this.mCurrentTargetUuid) && !this.mTargetManager.isTargetHaveContext(this.mCurrentTargetUuid)) {
            this.mTargetManager.targetSelected(this.getDeviceFromUuid(this.mCurrentTargetUuid));
        }
        if (intent.hasCategory("com.netflix.mediaclient.intent.category.MDX") && "com.netflix.mediaclient.intent.action.MDX_PLAY_VIDEOIDS".equals(intent.getAction())) {
            final String stringExtra = intent.getStringExtra("uuid");
            if (StringUtils.isEmpty(stringExtra) || !stringExtra.equals(this.mCurrentTargetUuid)) {
                Log.e("nf_mdx_agent", "MdxAgent: MDX_PLAY_VIDEOIDS is for uuid: " + stringExtra + "vs. " + this.mCurrentTargetUuid);
                return true;
            }
            final int intExtra = intent.getIntExtra("catalogId", -1);
            final int intExtra2 = intent.getIntExtra("episodeId", -1);
            int intExtra3;
            if ((intExtra3 = intent.getIntExtra("trackId", -1)) == -1) {
                Log.w("nf_mdx_agent", "MdxAgent: MDX_PLAY_VIDEOIDS has invalid trackId");
                intExtra3 = 13804431;
            }
            final int intExtra4 = intent.getIntExtra("time", -1);
            this.mTrackId = intExtra3;
            this.mStartTime = intExtra4;
            boolean episode = true;
            if (intExtra2 == -1) {
                episode = false;
            }
            if (Log.isLoggable("nf_mdx_agent", 3)) {
                Log.d("nf_mdx_agent", "MdxAgent: PLAYER_PLAY existing: " + this.mVideoIds.episode + ",catalogId: " + this.mVideoIds.catalogId + ",episodeId:" + this.mVideoIds.episodeId);
                Log.d("nf_mdx_agent", "MdxAgent: PLAYER_PLAY request: " + episode + ",catalogId: " + intExtra + ",episodeId:" + intExtra2);
            }
            if (this.mVideoIds.episode != episode || this.mVideoIds.catalogId != intExtra || (episode && this.mVideoIds.episodeId != intExtra2)) {
                this.mNotifier.commandPlayReceived(this.mCurrentTargetUuid);
                this.mVideoIds.episode = episode;
                this.mVideoIds.catalogId = intExtra;
                this.mVideoIds.episodeId = intExtra2;
                if (this.mBifManager != null) {
                    this.mBifManager.release();
                    this.mBifManager = null;
                }
                this.fetchVideoDetail(true, "com.netflix.mediaclient.intent.action.MDXUPDATE_POSTPLAY".equals(intent.getAction()));
                return true;
            }
            Log.d("nf_mdx_agent", "MdxAgent: videoIds are same, start play");
            this.mTargetManager.playerPlay(this.mCurrentTargetUuid, this.mVideoIds.catalogIdUrl, this.mTrackId, this.mVideoIds.episodeIdUrl, this.mStartTime);
            this.mNotifier.movieMetaDataAvailable(this.mCurrentTargetUuid);
            this.logPlaystart(false);
            return true;
        }
        else {
            if ("com.netflix.mediaclient.intent.action.MDX_SELECT_TARGET".equals(intent.getAction())) {
                final String stringExtra2 = intent.getStringExtra("uuid");
                Log.d("nf_mdx_agent", "MdxAgent: select target " + stringExtra2);
                this.setCurrentTarget(stringExtra2);
                return true;
            }
            if (intent.hasCategory("com.netflix.mediaclient.intent.category.MDXRCC")) {
                Log.d("nf_mdx_agent", "MdxAgent: get nf_mdx_RemoteControlClient intent");
                intent.putExtra("uuid", this.mCurrentTargetUuid);
                if ("com.netflix.mediaclient.intent.action.MDX_TOGGLE_PAUSE".equals(intent.getAction())) {
                    Log.d("nf_mdx_agent", "MdxAgent: get nf_mdx_RemoteControlClient intent toggle pause");
                    if (this.mRemoteControlClientManager != null) {
                        if (this.mRemoteControlClientManager.isPaused()) {
                            intent.setAction("com.netflix.mediaclient.intent.action.MDX_RESUME");
                        }
                        else {
                            intent.setAction("com.netflix.mediaclient.intent.action.MDX_PAUSE");
                        }
                    }
                }
            }
            this.mCommandHandler.handleCommandIntent(intent);
            return true;
        }
    }
    
    public boolean hasActiveSession() {
        return this.mTargetManager != null && this.mTargetManager.hasActiveSession();
    }
    
    @Override
    public boolean isBifReady() {
        return this.mBifManager != null && this.mBifManager.isBifReady();
    }
    
    @Override
    public boolean isPaused() {
        return this.mRemoteControlClientManager != null && this.mRemoteControlClientManager.isPaused();
    }
    
    @Override
    public boolean isReady() {
        return this.mReady.get();
    }
    
    void logPlaystart(final boolean b) {
        String s = null;
        if (b) {
            s = "local_playback_transfer";
        }
        this.getService().getClientLogging().getCustomerEventLogging().logMdxPlaybackStart(this.mVideoIds.catalogIdUrl, this.mVideoIds.episodeIdUrl, s, this.mTrackId);
    }
    
    public void notifyIsUserLogin(final boolean mUserIsLogin) {
        while (true) {
            Label_0110: {
                synchronized (this) {
                    if (this.mUserIsLogin != mUserIsLogin) {
                        this.mUserIsLogin = mUserIsLogin;
                        if (!this.mUserIsLogin) {
                            break Label_0110;
                        }
                        this.handleAccountConfig();
                        this.mReady.set(false);
                        this.addStateEventListener();
                        this.addDiscoveryEventListener();
                        this.addPairingEventListener(this.mTargetManager);
                        this.addSessionEventListener(this.mTargetManager);
                        this.mMdxController.init(new HashMap<String, String>(), this.mDisableWebSocket, this.getService().getConfiguration().getMdxBlackListTargets());
                        this.mTargetMap.clear();
                    }
                    return;
                }
            }
            if (this.mCastManager != null) {
                this.mCastManager.stop();
            }
            this.mReady.set(false);
            this.mMdxController.exit();
            this.removeStateEventListener();
            this.removeDiscoveryEventListener();
            this.removePairingEventListener(this.mTargetManager);
            this.removeSessionEventListener(this.mTargetManager);
            this.clearVideoDetails();
            this.sessionGone();
            this.mTargetMap.clear();
        }
    }
    
    @Override
    public void onBitmapReady(final Bitmap mBoxartBitmap) {
        this.mBoxartBitmap = mBoxartBitmap;
        if (this.mRemoteControlClientManager != null) {
            this.mRemoteControlClientManager.setBoxart(this.mBoxartBitmap);
        }
        if (this.mMdxNotificationManager != null) {
            this.mMdxNotificationManager.setBoxartNotify(this.mBoxartBitmap);
        }
    }
    
    @Override
    public long onGetTimeOfMostRecentIncomingMessage() {
        if (this.mTargetManager != null) {
            return this.mTargetManager.getTimeOfMostRecentIncomingMessage();
        }
        return 0L;
    }
    
    @Override
    public void onIsReady(final boolean b) {
        if (Log.isLoggable("nf_mdx_agent", 3)) {
            Log.d("nf_mdx_agent", "MdxAgent: onIsReady " + b);
        }
    }
    
    @Override
    public void onRemoteDeviceMap(final ArrayList<RemoteDevice> list) {
        if (Log.isLoggable("nf_mdx_agent", 3)) {
            Log.d("nf_mdx_agent", "MdxAgent: onRemoteDeviceMap " + list);
        }
        synchronized (this.mTargetMap) {
            this.mTargetMap.clear();
            this.mTargetMap.addAll(list);
            this.reconcileSelectedTargetInfo();
            // monitorexit(this.mTargetMap)
            if (this.mNotifier != null) {
                this.mNotifier.targetList();
            }
        }
    }
    
    @Override
    public void onSessionWatchDogExpired() {
        if (this.mNotifier != null) {
            this.mNotifier.playbackEnd(this.mCurrentTargetUuid);
        }
    }
    
    @Override
    public void onSetToNewTarget(final String currentTarget) {
        this.setCurrentTarget(currentTarget);
    }
    
    @Override
    public void onStickinessExpired() {
        this.setCurrentTarget(new String());
        if (this.mNotifier != null) {
            this.mNotifier.targetList();
        }
    }
    
    @Override
    public void onTargetSelectorLoaded(final String mCurrentTargetUuid, final String mTaregtUuid, final String mTargetDialUuid, final String mTargetFriendlyName) {
        this.mCurrentTargetUuid = mCurrentTargetUuid;
        this.mTaregtUuid = mTaregtUuid;
        this.mTargetDialUuid = mTargetDialUuid;
        this.mTargetFriendlyName = mTargetFriendlyName;
        if (this.mCastManager != null && StringUtils.isNotEmpty(this.mCurrentTargetUuid)) {
            this.mCastManager.setTargetId(this.mCurrentTargetUuid);
        }
    }
    
    void registerUserAgentReceiver() {
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mUserAgentReceiver, UserAgentBroadcastIntents.getNotificationIntentFilter());
    }
    
    @Override
    public void setCurrentTarget(final String mCurrentTargetUuid) {
        if (StringUtils.isEmpty(mCurrentTargetUuid)) {
            this.sessionGone();
            this.mCurrentTargetUuid = new String();
            this.mTaregtUuid = new String();
            this.mTargetDialUuid = new String();
            this.mTargetFriendlyName = new String();
            this.clearVideoDetails();
            this.getService().getClientLogging().getCustomerEventLogging().logMdxTargetSelection("local playback");
            if (this.mTargetSelector != null) {
                this.mTargetSelector.selectNewTarget(this.mCurrentTargetUuid, this.mTaregtUuid, this.mTargetDialUuid, this.mTargetFriendlyName);
            }
        }
        else if (!mCurrentTargetUuid.equals(this.mCurrentTargetUuid)) {
            this.clearVideoDetails();
            this.mCurrentTargetUuid = mCurrentTargetUuid;
            final RemoteDevice deviceFromUuid = this.getDeviceFromUuid(this.mCurrentTargetUuid);
            if (deviceFromUuid == null) {
                Log.e("nf_mdx_agent", "MdxAgent: no such device for " + this.mCurrentTargetUuid);
                this.mCurrentTargetUuid = new String();
                this.mTaregtUuid = new String();
                this.mTargetDialUuid = new String();
                this.mTargetFriendlyName = new String();
            }
            else {
                this.mTargetManager.targetSelected(deviceFromUuid);
                this.getService().getClientLogging().getCustomerEventLogging().logMdxTargetSelection("target playback");
                this.mTaregtUuid = deviceFromUuid.uuid;
                this.mTargetDialUuid = deviceFromUuid.dialUuid;
                this.mTargetFriendlyName = deviceFromUuid.friendlyName;
            }
            if (this.mTargetSelector != null) {
                this.mTargetSelector.selectNewTarget(this.mCurrentTargetUuid, this.mTaregtUuid, this.mTargetDialUuid, this.mTargetFriendlyName);
            }
        }
    }
    
    @Override
    public boolean setDialUuidAsCurrentTarget(final String currentTarget) {
        if (StringUtils.isNotEmpty(currentTarget)) {
            if (currentTarget.equals(this.mCurrentTargetUuid)) {
                return true;
            }
            if (this.getDeviceFromUuid(currentTarget) != null) {
                this.setCurrentTarget(currentTarget);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void switchPlaybackFromTarget(final String s, final int n) {
        if (Log.isLoggable("nf_mdx_agent", 3)) {
            Log.d("nf_mdx_agent", "switchPlaybackFromTarget to " + s + ", @" + n);
        }
        if (this.mSwitchTarget != null) {
            this.mSwitchTarget.startSwitch(this.mCurrentTargetUuid, s, this.mVideoIds, n, this.mTrackId);
        }
        if (StringUtils.isEmpty(s)) {
            this.getService().getClientLogging().getCustomerEventLogging().logMdxTargetSelection("local playback");
            return;
        }
        this.getService().getClientLogging().getCustomerEventLogging().logMdxTargetSelection("target playback");
    }
    
    void unregisterUserAgentReceiver() {
        try {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.mUserAgentReceiver);
        }
        catch (Exception ex) {
            Log.i("nf_mdx_agent", "unregisterUserAgenReceiver " + ex);
        }
    }
    
    class DiscoveryEventListener implements EventListener
    {
        @Override
        public void received(final UIEvent uiEvent) {
            if (uiEvent instanceof DeviceFoundEvent) {
                final DeviceFoundEvent deviceFoundEvent = (DeviceFoundEvent)uiEvent;
                final String uuid = deviceFoundEvent.getRemoteDevice().uuid;
                final String dialUuid = deviceFoundEvent.getRemoteDevice().dialUuid;
                if (MdxAgent.this.isSameDevice(uuid, MdxAgent.this.mCurrentTargetUuid) || MdxAgent.this.isSameDevice(dialUuid, MdxAgent.this.mCurrentTargetUuid)) {
                    final RemoteDevice access$900 = MdxAgent.this.getDeviceFromUuid(MdxAgent.this.mCurrentTargetUuid);
                    if (access$900 != null) {
                        MdxAgent.this.mTargetManager.targetFound(access$900);
                    }
                }
                if (MdxAgent.this.mNotifier != null) {
                    MdxAgent.this.mNotifier.targetList();
                }
                MdxAgent.this.getService().getClientLogging().getCustomerEventLogging().logMdxTarget("found", uuid, dialUuid, deviceFoundEvent.getRemoteDevice().serviceType);
            }
            else if (uiEvent instanceof DeviceLostEvent) {
                final String[] devices = ((DeviceLostEvent)uiEvent).getDevices();
                for (int length = devices.length, i = 0; i < length; ++i) {
                    final String s = devices[i];
                    if (MdxAgent.this.isSameDevice(s, MdxAgent.this.mCurrentTargetUuid) || MdxAgent.this.mTargetManager.isTargetHaveContext(s)) {
                        if (MdxAgent.this.mNotifier != null) {
                            MdxAgent.this.mNotifier.error(MdxAgent.this.mCurrentTargetUuid, 200, "device lost");
                        }
                        MdxAgent.this.sessionGone();
                    }
                    MdxAgent.this.getService().getClientLogging().getCustomerEventLogging().logMdxTarget("lost", s, null, null);
                }
                if (MdxAgent.this.mNotifier != null) {
                    MdxAgent.this.mNotifier.targetList();
                }
            }
            else if (uiEvent instanceof RemoteDeviceReadyEvent) {
                final RemoteDeviceReadyEvent remoteDeviceReadyEvent = (RemoteDeviceReadyEvent)uiEvent;
                if (MdxAgent.this.isSameDevice(remoteDeviceReadyEvent.getUuid(), MdxAgent.this.mCurrentTargetUuid)) {
                    if (remoteDeviceReadyEvent.getLaunchStatus() == 1) {
                        Log.d("nf_mdx_agent", "MdxAgent: RemoteDeviceReadyEvent, app's launched");
                        MdxAgent.this.mTargetManager.targetLaunched(MdxAgent.this.mCurrentTargetUuid, true);
                        return;
                    }
                    Log.d("nf_mdx_agent", "MdxAgent: RemoteDeviceReadyEvent, app's launch failed");
                    MdxAgent.this.mTargetManager.targetLaunched(MdxAgent.this.mCurrentTargetUuid, false);
                }
            }
        }
    }
    
    class StateEventListener implements EventListener
    {
        @Override
        public void received(final UIEvent uiEvent) {
            if (uiEvent instanceof InitEvent) {
                MdxAgent.this.mReady.set(true);
                MdxAgent.this.mTargetMap.clear();
                MdxAgent.this.mNotifier.ready();
                if (MdxAgent.this.mCastManager != null) {
                    MdxAgent.this.mCastManager.start();
                }
            }
            else if (uiEvent instanceof InitErrorEvent) {
                MdxAgent.this.mReady.set(false);
                if (MdxAgent.this.mNotifier != null) {
                    MdxAgent.this.mNotifier.error(MdxAgent.this.mCurrentTargetUuid, 103, ((InitErrorEvent)uiEvent).getErrorDesc());
                }
            }
            else if (uiEvent instanceof StateEvent) {
                if (((StateEvent)uiEvent).isReady()) {
                    MdxAgent.this.mReady.set(true);
                    MdxAgent.this.mTargetMap.clear();
                    if (MdxAgent.this.mNotifier != null) {
                        MdxAgent.this.mNotifier.ready();
                    }
                    if (MdxAgent.this.mCastManager != null) {
                        MdxAgent.this.mCastManager.start();
                    }
                }
                else {
                    MdxAgent.this.mReady.set(false);
                    MdxAgent.this.mTargetMap.clear();
                    if (MdxAgent.this.mNotifier != null) {
                        MdxAgent.this.mNotifier.notready();
                    }
                    MdxAgent.this.sessionGone();
                    if (MdxAgent.this.mCastManager != null) {
                        MdxAgent.this.mCastManager.stop();
                    }
                }
            }
        }
    }
    
    public static class Utils
    {
        public static Intent createIntent(final Activity activity, final String s, final String s2) {
            final Intent intent = new Intent(s);
            intent.setClass((Context)activity, (Class)NetflixService.class);
            intent.addCategory("com.netflix.mediaclient.intent.category.MDX");
            intent.putExtra("uuid", s2);
            return intent;
        }
        
        private static boolean isSameAsCurrentlyPlaying(final String s, final String s2, final WebApiUtils.VideoIds videoIds) {
            if (StringUtils.isEmpty(s2) && StringUtils.isNotEmpty(s) && videoIds.catalogId == Integer.valueOf(s)) {
                Log.v("nf_mdx_agent", "same movie");
                return true;
            }
            if (StringUtils.isNotEmpty(s2) && videoIds.episodeId == Integer.valueOf(s2)) {
                Log.v("nf_mdx_agent", "same show");
                return true;
            }
            return false;
        }
        
        public static boolean playVideo(final NetflixActivity netflixActivity, final Asset asset, final boolean b) {
            if (asset.isEpisode()) {
                Log.d("nf_mdx_agent", "Playing episode");
                return playVideo(netflixActivity, asset.getParentId(), asset.getPlayableId(), asset.getTrackId(), asset.getPlaybackBookmark(), b);
            }
            Log.d("nf_mdx_agent", "Playing movie");
            return playVideo(netflixActivity, asset.getPlayableId(), null, asset.getTrackId(), asset.getPlaybackBookmark(), b);
        }
        
        private static boolean playVideo(final NetflixActivity netflixActivity, final String s, final String s2, final int n, final int n2, final boolean b) {
            if (Log.isLoggable("nf_mdx_agent", 2)) {
                Log.v("nf_mdx_agent", "Starting playback movieId " + s + ", epId " + s2 + ", trackId " + n + ", bookmark " + n2);
            }
            final ServiceManager serviceManager = netflixActivity.getServiceManager();
            if (!ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
                Log.w("nf_mdx_agent", "MDX agent not available - can't play video");
            }
            else {
                final WebApiUtils.VideoIds videoIds = serviceManager.getMdx().getVideoIds();
                if (b || videoIds == null || !isSameAsCurrentlyPlaying(s, s2, videoIds)) {
                    final String currentTarget = serviceManager.getMdx().getCurrentTarget();
                    final Intent intent = createIntent(netflixActivity, "com.netflix.mediaclient.intent.action.MDX_PLAY_VIDEOIDS", currentTarget);
                    if (s != null) {
                        intent.putExtra("catalogId", Integer.parseInt(s));
                    }
                    if (s2 != null) {
                        intent.putExtra("episodeId", Integer.parseInt(s2));
                    }
                    intent.putExtra("trackId", n);
                    intent.putExtra("time", n2);
                    netflixActivity.startService(intent);
                    Log.v("nf_mdx_agent", "play done");
                    netflixActivity.startService(createIntent(netflixActivity, "com.netflix.mediaclient.intent.action.MDX_GETCAPABILITY", currentTarget));
                    return true;
                }
            }
            return false;
        }
    }
}
