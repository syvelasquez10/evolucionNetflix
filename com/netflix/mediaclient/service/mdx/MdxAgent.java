// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import android.app.Service;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import android.text.TextUtils;
import android.app.Notification;
import android.util.Pair;
import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.TransactionId;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import android.content.IntentFilter;
import android.annotation.SuppressLint;
import android.os.PowerManager;
import android.net.wifi.WifiManager;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.mdx.notification.MdxNotificationManagerFactory;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.PendingIntent;
import android.content.Intent;
import com.netflix.mediaclient.javabridge.ui.Mdx$Events;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.Log;
import android.net.wifi.WifiManager$WifiLock;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import java.util.ArrayList;
import android.content.BroadcastReceiver;
import android.os.PowerManager$WakeLock;
import com.netflix.mediaclient.service.mdx.notification.IMdxNotificationManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.service.mdx.cast.CastManager;
import android.graphics.Bitmap;
import com.netflix.mediaclient.media.BifManager;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.service.mdx.notification.MdxNotificationManager$MdxNotificationIntentRetriever;
import com.netflix.mediaclient.service.mdx.cast.CastAgent;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController$PropertyUpdateListener;
import com.netflix.mediaclient.service.ServiceAgent;

public class MdxAgent extends ServiceAgent implements MdxController$PropertyUpdateListener, MdxImageLoader$MdxImageLoaderInterface, MdxSessionWatchDog$SessionWatchDogInterface, SwitchTarget$SwitchTargetInterface, TargetSelector$TargetSelectorInterface, CastAgent, MdxNotificationManager$MdxNotificationIntentRetriever, IMdx
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
    private final Runnable mInitMdxNative;
    private final Handler mMdxAgentWorkerHandler;
    private HandlerThread mMdxAgentWorkerThread;
    private MdxImageLoader mMdxBoxartLoader;
    private MdxController mMdxController;
    private final AtomicBoolean mMdxNativeExitCompleted;
    private IMdxNotificationManager mMdxNotificationManager;
    private MdxNrdpLogger mMdxNrdpLogger;
    private MdxSessionWatchDog mMdxSessionWatchDog;
    private MediaSessionController mMediaSessionController;
    private ClientNotifier mNotifier;
    private PowerManager$WakeLock mPartialWakeLock;
    private final AtomicBoolean mReady;
    private RemoteControlClientManager mRemoteControlClientManager;
    private final BroadcastReceiver mStartStopErrorReceiver;
    private int mStartTime;
    private final EventListener mStateEventListener;
    private SwitchTarget mSwitchTarget;
    private String mTargetDialUuid;
    private String mTargetFriendlyName;
    private TargetManager mTargetManager;
    private final ArrayList<RemoteDevice> mTargetMap;
    private final ArrayList<String> mTargetRestartingList;
    private TargetSelector mTargetSelector;
    private String mTargetUuid;
    private int mTrackId;
    private final BroadcastReceiver mUserAgentReceiver;
    private boolean mUserIsLogin;
    private VideoDetails mVideoDetails;
    private VideoDetails mVideoDetailsPostplay;
    private WebApiUtils$VideoIds mVideoIds;
    private WebApiUtils$VideoIds mVideoIdsPostplay;
    private WifiManager$WifiLock mWifiLock;
    
    public MdxAgent() {
        this.mUserIsLogin = false;
        this.mTargetMap = new ArrayList<RemoteDevice>();
        this.mVideoIds = new WebApiUtils$VideoIds();
        this.mVideoIdsPostplay = new WebApiUtils$VideoIds();
        this.mDisableWebSocket = false;
        this.mEnableCast = false;
        this.mTargetRestartingList = new ArrayList<String>();
        this.mInitMdxNative = new MdxAgent$2(this);
        this.mStateEventListener = new MdxAgent$StateEventListener(this);
        this.mDiscoveryEventListener = new MdxAgent$DiscoveryEventListener(this);
        this.mStartStopErrorReceiver = new MdxAgent$3(this);
        this.mUserAgentReceiver = new MdxAgent$4(this);
        Log.d("nf_mdx_agent", "MdxAgent: start");
        this.mReady = new AtomicBoolean(false);
        this.mMdxNativeExitCompleted = new AtomicBoolean(true);
        this.mCurrentTargetUuid = new String();
        (this.mMdxAgentWorkerThread = new HandlerThread("MdxAgentWorker")).start();
        this.mMdxAgentWorkerHandler = new Handler(this.mMdxAgentWorkerThread.getLooper());
    }
    
    private void addDiscoveryEventListener() {
        this.mMdxController.addEventListener(Mdx$Events.mdx_discovery_devicefound.getName(), this.mDiscoveryEventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_discovery_devicelost.getName(), this.mDiscoveryEventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_discovery_remoteDeviceReady.getName(), this.mDiscoveryEventListener);
    }
    
    private void addPairingEventListener(final EventListener eventListener) {
        this.mMdxController.addEventListener(Mdx$Events.mdx_pair_pairingresponse.getName(), eventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_pair_regpairresponse.getName(), eventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_pair_pairingdeleted.getName(), eventListener);
    }
    
    private void addSessionEventListener(final EventListener eventListener) {
        this.mMdxController.addEventListener(Mdx$Events.mdx_session_startSessionResponse.getName(), eventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_session_messagedelivered.getName(), eventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_session_message.getName(), eventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_session_messagingerror.getName(), eventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_session_sessionended.getName(), eventListener);
    }
    
    private void addStateEventListener() {
        this.removeStateEventListener();
        this.mMdxController.addEventListener(Mdx$Events.mdx_init.getName(), this.mStateEventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_initerror.getName(), this.mStateEventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_mdxstate.getName(), this.mStateEventListener);
        this.mMdxController.addEventListener(Mdx$Events.mdx_targetrestarting.getName(), this.mStateEventListener);
    }
    
    private void clearVideoDetails() {
        this.mVideoIds = new WebApiUtils$VideoIds();
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
    
    private void ensureManagers() {
        if (this.mRemoteControlClientManager == null && AndroidUtils.getAndroidVersion() < 21) {
            this.mRemoteControlClientManager = new RemoteControlClientManager(this.getContext(), this.getConfigurationAgent().getMdxConfiguration());
        }
        if (this.mMdxNotificationManager == null) {
            this.mMdxNotificationManager = MdxNotificationManagerFactory.create(this.getContext(), true, this, this.mMediaSessionController);
        }
    }
    
    private void fetchVideoDetail(final boolean b, final boolean b2) {
        if ((b2 && this.mVideoIdsPostplay.isEpisode) || (!b2 && this.mVideoIds.isEpisode)) {
            int n;
            if (b2) {
                n = this.mVideoIdsPostplay.episodeId;
            }
            else {
                n = this.mVideoIds.episodeId;
            }
            this.getBrowseAgent().fetchEpisodeDetails(String.valueOf(n), new MdxAgent$EpisodeBrowseAgentCallback(this, b, b2, n));
            return;
        }
        this.getBrowseAgent().fetchMovieDetails(String.valueOf(this.mVideoIds.catalogId), new MdxAgent$MovieBrowseAgentCallback(this, b));
    }
    
    private String getCurrentEpisodeTitle() {
        if (this.mVideoDetails == null) {
            return null;
        }
        return this.getContext().getString(2131493236, new Object[] { this.mVideoDetails.getPlayable().getSeasonNumber(), this.mVideoDetails.getPlayable().getEpisodeNumber(), this.mVideoDetails.getTitle() });
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
    
    private String getNextEpisodeTitle() {
        if (this.mVideoDetailsPostplay == null) {
            return null;
        }
        final EpisodeDetails episodeDetails = (EpisodeDetails)this.mVideoDetailsPostplay;
        return this.getContext().getString(2131493236, new Object[] { episodeDetails.getSeasonNumber(), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle() });
    }
    
    private void handleAccountConfig() {
        this.mDisableWebSocket = this.getConfigurationAgent().getMdxConfiguration().isDisableWebsocket();
        this.mEnableCast = this.getConfigurationAgent().getMdxConfiguration().isEnableCast();
        if (this.mEnableCast) {
            (this.mCastManager = new CastManager(this.getContext(), this.getMainHandler(), this.mMdxAgentWorkerHandler, this.getConfigurationAgent().getEsnProvider().getEsn(), this.mMdxNrdpLogger)).setCastWhiteList(this.getConfigurationAgent().getMdxConfiguration().getCastWhiteList());
            if (StringUtils.isNotEmpty(this.mCurrentTargetUuid)) {
                this.mCastManager.setTargetId(this.mCurrentTargetUuid);
            }
            return;
        }
        this.mCastManager = null;
    }
    
    private boolean isSameDevice(final String s, final String s2) {
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(s2)) {
            return false;
        }
        if (s.equals(s2)) {
            return true;
        }
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
            return false;
        }
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
                if (this.getDeviceFromUuid(this.mTargetUuid) != null) {
                    remoteDevice = this.getDeviceFromUuid(this.mTargetUuid);
                    this.mCurrentTargetUuid = this.mTargetUuid;
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
                    if (Log.isLoggable()) {
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
                if (!uuid.equals(this.mTargetUuid)) {
                    this.mTargetUuid = uuid;
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
                this.mTargetSelector.updateSelectedTarget(this.mCurrentTargetUuid, this.mTargetUuid, this.mTargetDialUuid, this.mTargetFriendlyName);
            }
        }
    }
    
    private void registerStartStopReceiver() {
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKEND");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_PLAYBACKSTART");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_POSTPLAY");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_SIMPLE_PLAYBACKSTATE");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_ERROR");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.MDXUPDATE_STATE");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.MDX");
        intentFilter.setPriority(999);
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
        this.mMdxController.removeEventListener(Mdx$Events.mdx_discovery_devicefound.getName(), this.mDiscoveryEventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_discovery_devicelost.getName(), this.mDiscoveryEventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_discovery_remoteDeviceReady.getName(), this.mDiscoveryEventListener);
    }
    
    private void removePairingEventListener(final EventListener eventListener) {
        this.mMdxController.removeEventListener(Mdx$Events.mdx_pair_pairingresponse.getName(), eventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_pair_regpairresponse.getName(), eventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_pair_pairingdeleted.getName(), eventListener);
    }
    
    private void removeSessionEventListener(final EventListener eventListener) {
        this.mMdxController.removeEventListener(Mdx$Events.mdx_session_startSessionResponse.getName(), eventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_session_messagedelivered.getName(), eventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_session_message.getName(), eventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_session_messagingerror.getName(), eventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_session_sessionended.getName(), eventListener);
    }
    
    private void removeStateEventListener() {
        this.mMdxController.removeEventListener(Mdx$Events.mdx_init.getName(), this.mStateEventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_initerror.getName(), this.mStateEventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_mdxstate.getName(), this.mStateEventListener);
        this.mMdxController.removeEventListener(Mdx$Events.mdx_targetrestarting.getName(), this.mStateEventListener);
    }
    
    private void resetTargetSelection() {
        Log.e("nf_mdx_agent", "MdxAgent: resetTargetSelection");
        this.mCurrentTargetUuid = new String();
        this.mTargetUuid = new String();
        this.mTargetDialUuid = new String();
        this.mTargetFriendlyName = new String();
        if (this.mTargetSelector != null) {
            this.mTargetSelector.selectNewTarget(this.mCurrentTargetUuid, this.mTargetUuid, this.mTargetDialUuid, this.mTargetFriendlyName);
        }
    }
    
    private void sendVolumeUpdateBroadcast(final Context context, final int n) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.MDX_SETVOLUME");
        intent.putExtra("volume", n);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    private void sessionGone() {
        if (this.mTargetManager != null) {
            this.mTargetManager.targetGone(this.mCurrentTargetUuid);
        }
        this.mNotifier.error(this.mCurrentTargetUuid, 201, "stop connecting to target");
        this.mNotifier.playbackEnd(this.mCurrentTargetUuid, null);
    }
    
    private void unregisterStartStopReceiver() {
        try {
            this.getContext().unregisterReceiver(this.mStartStopErrorReceiver);
        }
        catch (Exception ex) {
            Log.i("nf_mdx_agent", "unregistermStartStopReceiver " + ex);
        }
    }
    
    private void updateMdxNotification(final boolean b, String s, final String s2, final boolean b2) {
        while (true) {
            this.ensureManagers();
            while (true) {
                Label_0056: {
                    synchronized (this.mMdxNotificationManager) {
                        this.mMdxNotificationManager.setTitlesNotify(b, s, s2);
                        if (this.mMediaSessionController != null) {
                            if (s2 != null) {
                                break Label_0056;
                            }
                            this.mMediaSessionController.updateMetadata(s, b2);
                        }
                        return;
                    }
                }
                s = s2;
                continue;
            }
        }
    }
    
    private void updateMdxRemoteClient(final boolean b) {
        if ((!b || this.mVideoDetailsPostplay != null) && (b || this.mVideoDetails != null)) {
            VideoDetails videoDetails;
            if (b) {
                videoDetails = this.mVideoDetailsPostplay;
            }
            else {
                videoDetails = this.mVideoDetails;
            }
            if (videoDetails.getType() == VideoType.EPISODE) {
                String s;
                if (b) {
                    s = this.getContext().getString(2131493230);
                }
                else {
                    s = videoDetails.getPlayable().getParentTitle();
                }
                String s2;
                if (b && videoDetails instanceof EpisodeDetails) {
                    s2 = this.getNextEpisodeTitle();
                }
                else {
                    s2 = this.getCurrentEpisodeTitle();
                }
                if (AndroidUtils.getAndroidVersion() < 21) {
                    this.mRemoteControlClientManager.start(b, videoDetails, this.mCurrentTargetUuid);
                    this.mRemoteControlClientManager.setTitles(s, s2);
                }
            }
            else if (AndroidUtils.getAndroidVersion() < 21) {
                this.mRemoteControlClientManager.setTitles(videoDetails.getTitle(), null);
            }
        }
    }
    
    @Override
    public void destroy() {
    Label_0133_Outer:
        while (true) {
            this.getMainHandler().removeCallbacks(this.mInitMdxNative);
            this.mMdxAgentWorkerThread.quit();
            while (true) {
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
                        if (AndroidUtils.getAndroidVersion() < 21) {
                            if (this.mRemoteControlClientManager != null) {
                                this.mRemoteControlClientManager.stop();
                                this.mRemoteControlClientManager.destroy();
                                this.mRemoteControlClientManager = null;
                            }
                            if (this.mCastManager != null) {
                                this.mCastManager.destroy();
                            }
                            this.unregisterStartStopReceiver();
                            super.destroy();
                            return;
                        }
                    }
                    catch (InterruptedException ex) {
                        Log.e("nf_mdx_agent", "MdxAgent: mMdxAgentWorkerThread interrupted");
                        continue Label_0133_Outer;
                    }
                    break;
                }
                if (this.mMediaSessionController != null) {
                    this.mMediaSessionController.destroy();
                    continue;
                }
                continue;
            }
        }
    }
    
    @Override
    public void disconnectFromCast() {
        if (this.mCastManager != null) {
            this.mCastManager.disconnect();
        }
    }
    
    @Override
    protected void doInit() {
        boolean b = true;
        Log.e("nf_mdx_agent", "MdxAgent: doInit");
        if (this.getNrdController() == null || this.getNrdController().getNrdp() == null) {
            this.initCompleted(CommonStatus.NRD_ERROR);
            return;
        }
        this.mMdxController = this.getNrdController().getNrdp().getMdxController();
        this.mMdxNrdpLogger = new MdxNrdpLogger(this.getNrdController().getNrdp());
        this.mNotifier = new ClientNotifier(this.getContext());
        this.mTargetManager = new TargetManager(this.mNotifier, this.mMdxController, this.getConfigurationAgent().getEsnProvider().getEsn(), this.mMdxNrdpLogger);
        this.mCommandHandler = new CommandHandler(this.mTargetManager);
        if (this.mMdxController == null || this.mNotifier == null || this.mTargetManager == null || this.mCommandHandler == null) {
            this.initCompleted(CommonStatus.INTERNAL_ERROR);
            return;
        }
        this.mReady.set(false);
        this.mMdxNativeExitCompleted.set(true);
        this.mTargetMap.clear();
        this.mTargetRestartingList.clear();
        this.mMdxController.setPropertyUpdateListener(this);
        TransactionId.setTransactionIdSource(this.getNrdController().getNrdp());
        if (Log.isLoggable()) {
            Log.d("nf_mdx_agent", "MdxAgent: change XID base from " + System.currentTimeMillis() + " ==> " + this.getNrdController().getNrdp().now());
        }
        this.mMdxAgentWorkerHandler.post((Runnable)new MdxAgent$1(this));
        this.mSwitchTarget = new SwitchTarget(this.mTargetManager, this);
        if (Log.isLoggable()) {
            Log.d("nf_mdx_agent", "MdxAgent: doInit mCurrentTargetUuid: " + this.mCurrentTargetUuid);
        }
        this.mMdxBoxartLoader = new MdxImageLoader((Context)this.getService(), this.getResourceFetcher(), this, this.mMdxAgentWorkerHandler);
        this.mMdxSessionWatchDog = new MdxSessionWatchDog(this, this.mMdxAgentWorkerHandler);
        this.registerUserAgentReceiver();
        final ServiceAgent$UserAgentInterface userAgent = this.getUserAgent();
        if (userAgent != null) {
            if (!StringUtils.isNotEmpty(userAgent.getCurrentProfileGuid()) || !userAgent.isUserLoggedIn()) {
                b = false;
            }
            this.notifyIsUserLogin(b);
        }
        else {
            Log.e("nf_mdx_agent", "MdxAgent: userAgent is not ready yet, skip init");
        }
        this.registerStartStopReceiver();
        if (AndroidUtils.getAndroidVersion() < 21) {
            this.mRemoteControlClientManager = new RemoteControlClientManager(this.getContext(), this.getConfigurationAgent().getMdxConfiguration());
        }
        else {
            this.mMediaSessionController = new MediaSessionController(this, this.getConfigurationAgent().getMdxConfiguration());
        }
        this.initCompleted(CommonStatus.OK);
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
        if (Log.isLoggable()) {
            Log.d("nf_mdx_agent", "MdxAgent: getCurrentTarget : " + this.mCurrentTargetUuid);
        }
        return this.mCurrentTargetUuid;
    }
    
    @Override
    public MdxTargetCapabilities getCurrentTargetCapabilities() {
        return this.mTargetManager.getTargetCapabilities();
    }
    
    public Pair<Integer, Notification> getMdxNotification(final boolean b) {
        final WebApiUtils$VideoIds videoIds = this.mTargetManager.getVideoIds();
        this.ensureManagers();
        if (videoIds != null) {
            if (videoIds.isEpisode != this.mVideoIds.isEpisode || (videoIds.isEpisode && videoIds.episodeId != this.mVideoIds.episodeId) || videoIds.catalogId != this.mVideoIds.catalogId) {
                this.mVideoIds = videoIds;
                this.fetchVideoDetail(false, b);
                return this.mMdxNotificationManager.getNotification(b);
            }
            Log.d("nf_mdx_agent", "MdxAgent: videoIds are all same");
            this.mNotifier.movieMetaDataAvailable(this.mCurrentTargetUuid);
            if (this.mBoxartBitmap != null) {
                this.mMdxNotificationManager.setBoxart(this.mBoxartBitmap);
            }
            if (this.mVideoDetails != null && !this.mVideoIds.isEpisode) {
                this.mMdxNotificationManager.setTitlesNotify(false, this.mVideoDetails.getTitle(), null);
            }
        }
        else {
            Log.d("nf_mdx_agent", "MdxAgent: new videoIds is null");
        }
        return this.mMdxNotificationManager.getNotification(b);
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
    public PendingIntent getPlayNextIntent() {
        if (this.mVideoDetails == null || !(this.mVideoDetails instanceof EpisodeDetails)) {
            return null;
        }
        final EpisodeDetails episodeDetails = (EpisodeDetails)this.mVideoDetails;
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.MDX_PLAY_VIDEOIDS");
        final String nextEpisodeId = episodeDetails.getNextEpisodeId();
        final String parentId = this.mVideoDetails.getPlayable().getParentId();
        if (TextUtils.isEmpty((CharSequence)parentId) || TextUtils.isEmpty((CharSequence)nextEpisodeId)) {
            return null;
        }
        intent.putExtra("episodeId", Integer.parseInt(nextEpisodeId));
        intent.putExtra("catalogId", Integer.parseInt(parentId));
        intent.putExtra("playNext", true);
        return this.createNotificationButtonIntent(intent);
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
                Label_0315: {
                    final RemoteDevice remoteDevice;
                    synchronized (this.mTargetMap) {
                        if (this.mTargetMap.isEmpty()) {
                            return null;
                        }
                        array = new Pair[this.mTargetMap.size()];
                        n = 0;
                        final Iterator<RemoteDevice> iterator = this.mTargetMap.iterator();
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
                            if (Log.isLoggable()) {
                                Log.d("nf_mdx_agent", "MdxAgent: getTargetList : " + dialUuid + " : " + friendlyName);
                                n = n2;
                                break Label_0315;
                            }
                            break Label_0315;
                        }
                        else if (StringUtils.isNotEmpty(uuid)) {
                            final int n3 = n + 1;
                            array[n] = Pair.create((Object)uuid, (Object)friendlyName);
                            n = n3;
                            if (Log.isLoggable()) {
                                Log.d("nf_mdx_agent", "MdxAgent: getTargetList : " + uuid + " : " + friendlyName);
                                n = n3;
                                break Label_0315;
                            }
                            break Label_0315;
                        }
                    }
                    Log.e("nf_mdx_agent", "MdxAgent: uuid and dialUuid are invalid " + remoteDevice);
                }
                continue;
            }
        }
        if (Log.isLoggable()) {
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
    public WebApiUtils$VideoIds getVideoIds() {
        return this.mVideoIds;
    }
    
    public WebApiUtils$VideoIds getVideoIdsPostplay() {
        return this.mVideoIdsPostplay;
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
            final boolean isEpisode = intExtra2 != -1;
            if (Log.isLoggable()) {
                Log.d("nf_mdx_agent", "MdxAgent: PLAYER_PLAY existing: " + this.mVideoIds.isEpisode + ",catalogId: " + this.mVideoIds.catalogId + ",episodeId:" + this.mVideoIds.episodeId);
                Log.d("nf_mdx_agent", "MdxAgent: PLAYER_PLAY request: " + isEpisode + ",catalogId: " + intExtra + ",episodeId:" + intExtra2);
            }
            if (intent.getBooleanExtra("playNext", false)) {
                this.stopAllNotifications();
            }
            if (this.mVideoIds.isEpisode != isEpisode || this.mVideoIds.catalogId != intExtra || (isEpisode && this.mVideoIds.episodeId != intExtra2)) {
                this.mNotifier.commandPlayReceived(this.mCurrentTargetUuid);
                this.mVideoIds.isEpisode = isEpisode;
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
                    if (this.mRemoteControlClientManager != null && AndroidUtils.getAndroidVersion() < 21) {
                        if (this.mRemoteControlClientManager.isPaused()) {
                            intent.setAction("com.netflix.mediaclient.intent.action.MDX_RESUME");
                        }
                        else {
                            intent.setAction("com.netflix.mediaclient.intent.action.MDX_PAUSE");
                        }
                    }
                }
            }
            if ("com.netflix.mediaclient.intent.action.MDX_STOP".equals(intent.getAction())) {
                this.stopAllNotifications();
            }
            else if ("com.netflix.mediaclient.intent.action.MDX_SETVOLUME".equals(intent.getAction())) {
                this.sendVolumeUpdateBroadcast(this.getContext(), intent.getIntExtra("volume", -1));
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
        return this.mRemoteControlClientManager != null && AndroidUtils.getAndroidVersion() < 21 && this.mRemoteControlClientManager.isPaused();
    }
    
    @Override
    public boolean isReady() {
        return this.mReady.get();
    }
    
    @Override
    public boolean isTargetLaunchingOrLaunched() {
        if (this.mTargetManager != null) {
            Log.v("nf_mdx_agent", "checking isTargetLaunchingOrLaunched");
            return this.mTargetManager.isTargetLaunchingOrLaunched(this.mCurrentTargetUuid);
        }
        return false;
    }
    
    void logPlaystart(final boolean b) {
        String s = "";
        if (b) {
            s = "local_playback_transfer";
        }
        this.getService().getClientLogging().getCustomerEventLogging().logMdxPlaybackStart(this.mVideoIds.catalogIdUrl, this.mVideoIds.episodeIdUrl, s, this.mTrackId);
    }
    
    public void notifyIsUserLogin(final boolean mUserIsLogin) {
        while (true) {
            Label_0048: {
                synchronized (this) {
                    if (this.mUserIsLogin != mUserIsLogin) {
                        this.mUserIsLogin = mUserIsLogin;
                        if (!this.mUserIsLogin) {
                            break Label_0048;
                        }
                        this.handleAccountConfig();
                        this.mInitMdxNative.run();
                    }
                    return;
                }
            }
            this.getMainHandler().removeCallbacks(this.mInitMdxNative);
            if (!this.mReady.get()) {
                Log.d("nf_mdx_agent", "notifyIsUserLogin: logout, was not ready ignore");
                return;
            }
            Log.d("nf_mdx_agent", "notifyIsUserLogin: logout, exit native");
            this.resetTargetSelection();
            if (this.mCastManager != null) {
                this.mCastManager.stop();
            }
            this.mReady.set(false);
            this.mMdxNativeExitCompleted.set(false);
            this.mMdxController.exit();
            this.removeDiscoveryEventListener();
            this.removePairingEventListener(this.mTargetManager);
            this.removeSessionEventListener(this.mTargetManager);
            this.clearVideoDetails();
            this.sessionGone();
            this.mTargetMap.clear();
            this.mTargetRestartingList.clear();
            if (this.mNotifier != null) {
                this.mNotifier.notready();
            }
        }
    }
    
    @Override
    public void onBitmapReady(final Bitmap mBoxartBitmap) {
        this.mBoxartBitmap = mBoxartBitmap;
        if (AndroidUtils.getAndroidVersion() < 21) {
            if (this.mRemoteControlClientManager != null) {
                this.mRemoteControlClientManager.setBoxart(this.mBoxartBitmap);
            }
        }
        else if (this.mMediaSessionController != null) {
            this.mMediaSessionController.updateMetadata(this.mBoxartBitmap);
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
        if (Log.isLoggable()) {
            Log.d("nf_mdx_agent", "MdxAgent: onIsReady " + b);
        }
    }
    
    @Override
    public void onRemoteDeviceMap(final ArrayList<RemoteDevice> list) {
        if (Log.isLoggable()) {
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
            this.mNotifier.playbackEnd(this.mCurrentTargetUuid, null);
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
    public void onTargetSelectorLoaded(final String mCurrentTargetUuid, final String mTargetUuid, final String mTargetDialUuid, final String mTargetFriendlyName) {
        this.mCurrentTargetUuid = mCurrentTargetUuid;
        this.mTargetUuid = mTargetUuid;
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
            this.clearVideoDetails();
            this.getService().getClientLogging().getCustomerEventLogging().logMdxTargetSelection("local playback");
            this.resetTargetSelection();
        }
        else if (!mCurrentTargetUuid.equals(this.mCurrentTargetUuid)) {
            this.clearVideoDetails();
            this.mCurrentTargetUuid = mCurrentTargetUuid;
            final RemoteDevice deviceFromUuid = this.getDeviceFromUuid(this.mCurrentTargetUuid);
            if (deviceFromUuid == null) {
                Log.e("nf_mdx_agent", "MdxAgent: no such device for " + this.mCurrentTargetUuid);
                this.resetTargetSelection();
                return;
            }
            this.mTargetManager.targetSelected(deviceFromUuid);
            this.getService().getClientLogging().getCustomerEventLogging().logMdxTargetSelection("target playback");
            this.mTargetUuid = deviceFromUuid.uuid;
            this.mTargetDialUuid = deviceFromUuid.dialUuid;
            this.mTargetFriendlyName = deviceFromUuid.friendlyName;
            if (this.mTargetSelector != null) {
                this.mTargetSelector.selectNewTarget(this.mCurrentTargetUuid, this.mTargetUuid, this.mTargetDialUuid, this.mTargetFriendlyName);
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
    
    public void stopAllNotifications() {
        Log.i("nf_mdx_agent", "Stop all notifications");
        this.ensureManagers();
        if (AndroidUtils.getAndroidVersion() < 21) {
            this.mRemoteControlClientManager.stop();
        }
        else if (this.mMediaSessionController != null) {
            this.mMediaSessionController.stopMediaSession();
        }
        this.mMdxNotificationManager.stopNotification(this.getService());
        this.mMdxNotificationManager.cancelNotification();
        this.mMdxNotificationManager = null;
        this.mBoxartBitmap = null;
        MDXControllerActivity.finishMDXController(this.getContext());
        final MdxSharedState mdxSharedState = (MdxSharedState)this.getSharedState();
        if (mdxSharedState instanceof MdxSharedState) {
            mdxSharedState.resetPostplayState();
        }
    }
    
    public void stopPostplayNotification() {
        this.ensureManagers();
        this.mMdxNotificationManager.stopPostplayNotification(this.getService());
    }
    
    @Override
    public void switchPlaybackFromTarget(final String s, final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_mdx_agent", "switchPlaybackFromTarget to " + s + ", @" + n);
        }
        if (this.mSwitchTarget != null) {
            this.mSwitchTarget.startSwitch(this.mCurrentTargetUuid, s, this.mVideoIds, n, this.mTrackId);
        }
        if (StringUtils.isEmpty(s)) {
            this.getService().getClientLogging().getCustomerEventLogging().logMdxTargetSelection("local playback");
        }
        else {
            this.getService().getClientLogging().getCustomerEventLogging().logMdxTargetSelection("target playback");
            if (StringUtils.isEmpty(this.mCurrentTargetUuid)) {
                Log.d("nf_mdx_agent", "fling playback from local to target");
                this.logPlaystart(true);
            }
        }
    }
    
    @Override
    public void transferPlaybackFromLocal() {
        Log.d("nf_mdx_agent", "transfer playback from local to target");
        this.logPlaystart(true);
    }
    
    public void uiComingToForeground() {
        if (this.mUserIsLogin && this.mCastManager != null) {
            Log.d("nf_mdx_agent", "MdxAgent: UI coming to foreground, try restart discovery");
            this.mCastManager.restartCastDiscoveryIfNeeded();
        }
    }
    
    void unregisterUserAgentReceiver() {
        try {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.mUserAgentReceiver);
        }
        catch (Exception ex) {
            Log.i("nf_mdx_agent", "unregisterUserAgenReceiver " + ex);
        }
    }
    
    public void updateMdxNotificationAndLockscreenWithNextSeries(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            this.mVideoIdsPostplay = new WebApiUtils$VideoIds();
            this.mVideoIdsPostplay.isEpisode = true;
            final WebApiUtils$VideoIds mVideoIdsPostplay = this.mVideoIdsPostplay;
            int intValue;
            if (StringUtils.isNumeric(s)) {
                intValue = Integer.valueOf(s);
            }
            else {
                intValue = -1;
            }
            mVideoIdsPostplay.episodeId = intValue;
            this.ensureManagers();
            this.fetchVideoDetail(false, true);
            if (AndroidUtils.getAndroidVersion() < 21) {
                this.mRemoteControlClientManager.start(true, this.mVideoDetailsPostplay, this.mCurrentTargetUuid);
                this.mRemoteControlClientManager.setState(false, false, true);
            }
            else if (this.mMediaSessionController != null) {
                this.mMediaSessionController.updateState(false, true);
            }
            this.mMdxNotificationManager.startNotification((Notification)this.getMdxNotification(true).second, this.getService(), true);
            this.mMdxNotificationManager.setUpNextStateNotify(false, false, true);
        }
    }
}
