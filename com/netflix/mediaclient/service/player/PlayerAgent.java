// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import android.view.SurfaceHolder;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import android.graphics.Point;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import java.util.concurrent.Executors;
import com.netflix.mediaclient.media.MediaPlayerHelperFactory;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.TimerTask;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.media.Subtitle;
import android.content.Intent;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.media.JPlayer2Helper;
import com.netflix.mediaclient.service.preapp.PreAppAgentDataHandler;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import android.content.Context;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.javabridge.ui.IMedia$MediaEventEnum;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import android.annotation.SuppressLint;
import android.media.AudioDeviceInfo;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.AudioManager;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import com.netflix.mediaclient.event.nrdp.media.Warning;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.BufferRange;
import com.netflix.mediaclient.event.nrdp.media.Statechanged;
import com.netflix.mediaclient.event.nrdp.media.AudioTrackChanged;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.event.nrdp.media.Buffering;
import com.netflix.mediaclient.event.nrdp.media.OpenComplete;
import com.netflix.mediaclient.event.nrdp.media.GenericMediaEvent;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import android.os.PowerManager$WakeLock;
import android.content.BroadcastReceiver;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Timer;
import android.view.Surface;
import com.netflix.mediaclient.service.player.subtitles.SubtitleDownloadManager;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.IPlayerFileCache;
import java.util.concurrent.ExecutorService;
import com.netflix.mediaclient.util.PlaybackVolumeMetric;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.servicemgr.IManifestCache;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.media.MediaPlayerHelper;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthDelayedBifDownload;
import com.netflix.mediaclient.media.BifManager;
import com.netflix.mediaclient.media.bitrate.AudioBitrateRange;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent$ConfigAgentListener;
import com.netflix.mediaclient.service.ServiceAgent;

public class PlayerAgent extends ServiceAgent implements ConfigurationAgent$ConfigAgentListener, IPlayer
{
    private static final int BANDWITH_CHECK_INTERVAL = 30000;
    private static final int DELAY_SEEKCOMPLETE_MS = 300;
    private static final int EOS_DELTA = 10000;
    private static final int IntialLowBRThreshold = 200;
    public static final int MAX_BR_THRESHOLD_DEFAULT_KBPS = 20000;
    private static final int MAX_CELLULAR_DOWNLOAD_LIMIT = 150000;
    private static final int MAX_WIFI_DOWNLOAD_LIMIT = 300000;
    private static int MaxBRThreshold = 0;
    private static final int NETWORK_CHECK_INTERVAL = 1000;
    private static final int NETWORK_CHECK_TIMEOUT = 30000;
    private static final int SEEKTO_DELTA_IN_MS = 60000;
    private static final int STATE_CLOSED = 4;
    private static final int STATE_CREATED = -1;
    private static final int STATE_OPENING = 0;
    private static final int STATE_PAUSED = 2;
    private static final int STATE_PLAYING = 1;
    private static final int STATE_PRECLOSE = 8;
    private static final int STATE_PREOPEN = 5;
    private static final int STATE_PREPLAY = 6;
    private static final int STATE_PRESTOP = 7;
    private static final int STATE_STOPPED = 3;
    private static final String TAG;
    private static int TimeToWaitBeforeLowBRStreamsEnabled = 0;
    private static final int TimeToWaitBeforeShutdown = 30000;
    private static final int TimeToWaitBeforeUnmute = 10000;
    private boolean ignoreErrorsWhileActionId12IsProcessed;
    private boolean inPlaybackSession;
    private NccpError mActionId12Error;
    private AudioBitrateRange mAudioBitrateRange;
    private BifManager mBifManager;
    private long mBookmark;
    private boolean mBufferingCompleted;
    private PlayerAgent$CloseTimeoutTask mCloseTimeoutTask;
    private BandwidthDelayedBifDownload mDelayedBifDowloadForDataSaver;
    private boolean mForcedRebuffer;
    private int mFuzz;
    private MediaPlayerHelper mHelper;
    private boolean mInPlayback;
    private PlayerAgent$InitialVideoBitrateRangeTimeoutTask mInitVBRTimeoutTask;
    private volatile JPlayer$JplayerListener mJPlayerListener;
    private long mLastBandwidthCheck;
    private IManifestCache mManifestCache;
    private IMedia mMedia;
    private PlayerAgent$GenericMediaEventListener mMediaEventListener;
    private long mMovieId;
    private final int mNetworkProfile;
    private Nrdp mNrdp;
    private PlayContext mPlayContext;
    private PlayParamsReceiver mPlayParamsRecvr;
    private PlaybackVolumeMetric mPlaybackVolumeMetric;
    private ExecutorService mPlayerExecutor;
    private IPlayerFileCache mPlayerFileManager;
    private PlayerListenerManager mPlayerListenerManager;
    private PlayerType mPlayerType;
    private int mRelativeSeekPosition;
    private boolean mScreenOnWhilePlaying;
    private long mStartPlayPositionInTitleInMs;
    private PlayerAgent$StartPlayTimeoutTask mStartPlayTimeoutTask;
    private volatile int mState;
    private boolean mStayAwake;
    private SubtitleConfiguration mSubtitleConfiguration;
    private SubtitleDownloadManager mSubtitles;
    private Surface mSurface;
    private Timer mTimer;
    private AtomicBoolean mUpdatePlaybackVolumeMetric;
    private final BroadcastReceiver mUserAgentReceiver;
    private final PowerManager$WakeLock mWakeLock;
    private String mXid;
    private boolean muted;
    private final Runnable onCloseRunnable;
    private final Runnable onOpenRunnable;
    private final Runnable onPlayRunnable;
    private final Runnable onSeekRunnable;
    private NccpError pendingError;
    private final BroadcastReceiver playerChangesReceiver;
    private boolean preparedCompleted;
    private int prevEndPosition;
    private int seekedToPosition;
    private boolean seeking;
    private long sessionInitRxBytes;
    private long sessionInitTxBytes;
    private boolean splashScreenRemoved;
    private boolean toCancelOpen;
    private volatile boolean toOpenAfterClose;
    private boolean toPlayAfterStop;
    private boolean validPtsRecieved;
    private final UserAgentWebCallback webClientCallback;
    
    static {
        TAG = PlayerAgent.class.getSimpleName();
        PlayerAgent.TimeToWaitBeforeLowBRStreamsEnabled = 15000;
        PlayerAgent.MaxBRThreshold = 20000;
    }
    
    public PlayerAgent() {
        this.mPlayerListenerManager = new PlayerListenerManager(this);
        this.mWakeLock = null;
        this.seeking = false;
        this.validPtsRecieved = false;
        this.preparedCompleted = false;
        this.mNetworkProfile = 2;
        this.splashScreenRemoved = false;
        this.mBufferingCompleted = false;
        this.ignoreErrorsWhileActionId12IsProcessed = false;
        this.mState = -1;
        this.toPlayAfterStop = false;
        this.toOpenAfterClose = false;
        this.toCancelOpen = false;
        this.mAudioBitrateRange = new AudioBitrateRange(0, 64);
        this.mUpdatePlaybackVolumeMetric = new AtomicBoolean(false);
        this.onOpenRunnable = new PlayerAgent$1(this);
        this.onPlayRunnable = new PlayerAgent$2(this);
        this.onSeekRunnable = new PlayerAgent$3(this);
        this.onCloseRunnable = new PlayerAgent$4(this);
        this.webClientCallback = new PlayerAgent$5(this);
        this.muted = false;
        this.playerChangesReceiver = new PlayerAgent$6(this);
        this.mUserAgentReceiver = new PlayerAgent$9(this);
    }
    
    private boolean canStartBifDownload() {
        if (this.mInPlayback && this.mBifManager == null) {
            final PlayoutMetadata playoutMetadata = this.getPlayoutMetadata();
            if (playoutMetadata != null) {
                if (playoutMetadata.targetBitRate >= 500) {
                    return true;
                }
                if (this.mDelayedBifDowloadForDataSaver != null && BandwidthUtility.shouldDelayBifForPlay(this.getContext())) {
                    return this.mDelayedBifDowloadForDataSaver.shouldDownloadBif(this.isBufferingCompleted());
                }
            }
        }
        return false;
    }
    
    private void clearBifs() {
        Log.d(PlayerAgent.TAG, "preRelease()");
        if (this.mBifManager != null) {
            this.mBifManager.release();
            this.mBifManager = null;
        }
    }
    
    private void close2() {
        synchronized (this) {
            this.mState = 8;
            final int n = (int)((ConnectivityUtils.getApplicationRx() - this.sessionInitRxBytes) / 1024L);
            final int n2 = (int)((ConnectivityUtils.getApplicationTx() - this.sessionInitTxBytes) / 1024L);
            if (Log.isLoggable()) {
                Log.d(PlayerAgent.TAG, "Bytes Tx: " + n2);
                Log.d(PlayerAgent.TAG, "Bytes Rx: " + n);
            }
            this.mMedia.setBytesReport(n2, n);
            final String audioSinkType = this.getAudioSinkType();
            if (Log.isLoggable()) {
                Log.d(PlayerAgent.TAG, "has audioSinkType: " + audioSinkType);
            }
            if (this.mPlaybackVolumeMetric == null) {
                Log.w(PlayerAgent.TAG, "playbackVolumeMetric is null, create a new one even if may not be correct if we reeleased media stream when loosing focus");
                new PlaybackVolumeMetric(this.getContext());
            }
            this.mUpdatePlaybackVolumeMetric.set(false);
            this.mMedia.close(audioSinkType, this.mPlaybackVolumeMetric);
            this.mNrdp.getLog().flush();
        }
    }
    
    private SubtitleConfiguration findSubtitleConfiguration() {
        SubtitleConfiguration subtitleConfiguration = null;
        final ServiceAgent$ConfigurationAgentInterface configurationAgent = this.getConfigurationAgent();
        if (configurationAgent != null) {
            subtitleConfiguration = configurationAgent.getSubtitleConfiguration();
        }
        SubtitleConfiguration default1;
        if ((default1 = subtitleConfiguration) == null) {
            default1 = SubtitleConfiguration.DEFAULT;
        }
        return default1;
    }
    
    @SuppressLint({ "NewApi" })
    private String getAudioSinkType() {
        int n = 0;
        if (this.getContext() != null) {
            final AudioManager audioManager = (AudioManager)this.getContext().getSystemService("audio");
            if (AndroidUtils.getAndroidVersion() > 22) {
                final AudioDeviceInfo[] devices = audioManager.getDevices(2);
                if (devices == null || devices.length == 0) {
                    return PlayerAgent$AudioSinkType.AUDIOSINK_DEAULT.getDecriptionString();
                }
                final int length = devices.length;
                int i = 0;
                int n2 = 0;
                int n3 = 0;
                int n4 = 0;
                int n5 = 0;
                while (i < length) {
                    final AudioDeviceInfo audioDeviceInfo = devices[i];
                    int n6 = n2;
                    int n7 = n;
                    int n8 = n3;
                    int n9 = n4;
                    int n10 = n5;
                    if (audioDeviceInfo.isSink()) {
                        n6 = n2;
                        n7 = n;
                        n8 = n3;
                        n9 = n4;
                        n10 = n5;
                        switch (audioDeviceInfo.getType()) {
                            default: {
                                n10 = n5;
                                n9 = n4;
                                n8 = n3;
                                n7 = n;
                                n6 = n2;
                                break;
                            }
                            case 11:
                            case 12: {
                                n7 = 1;
                                n6 = n2;
                                n8 = n3;
                                n9 = n4;
                                n10 = n5;
                                break;
                            }
                            case 13: {
                                n6 = 1;
                                n7 = n;
                                n8 = n3;
                                n9 = n4;
                                n10 = n5;
                                break;
                            }
                            case 1:
                            case 2: {
                                n9 = 1;
                                n6 = n2;
                                n7 = n;
                                n8 = n3;
                                n10 = n5;
                                break;
                            }
                            case 3:
                            case 4: {
                                n8 = 1;
                                n6 = n2;
                                n7 = n;
                                n9 = n4;
                                n10 = n5;
                                break;
                            }
                            case 7:
                            case 8: {
                                n10 = 1;
                                n6 = n2;
                                n7 = n;
                                n8 = n3;
                                n9 = n4;
                            }
                            case 5:
                            case 6:
                            case 9:
                            case 10: {
                                break;
                            }
                        }
                    }
                    ++i;
                    n2 = n6;
                    n = n7;
                    n3 = n8;
                    n4 = n9;
                    n5 = n10;
                }
                if (n5 != 0) {
                    return PlayerAgent$AudioSinkType.AUDIOSINK_BT.getDecriptionString();
                }
                if (n3 != 0) {
                    return PlayerAgent$AudioSinkType.AUDIOSINK_HEADPHONE.getDecriptionString();
                }
                if (n2 != 0) {
                    return PlayerAgent$AudioSinkType.AUDIOSINK_DOCK.getDecriptionString();
                }
                if (n != 0) {
                    return PlayerAgent$AudioSinkType.AUDIOSINK_USB.getDecriptionString();
                }
                if (n4 != 0) {
                    return PlayerAgent$AudioSinkType.AUDIOSINK_BUILTIN.getDecriptionString();
                }
                return PlayerAgent$AudioSinkType.AUDIOSINK_OTHERS.getDecriptionString();
            }
            else {
                if (audioManager.isBluetoothA2dpOn()) {
                    return PlayerAgent$AudioSinkType.AUDIOSINK_BT.getDecriptionString();
                }
                if (audioManager.isBluetoothScoOn()) {
                    return PlayerAgent$AudioSinkType.AUDIOSINK_BT.getDecriptionString();
                }
                if (audioManager.isSpeakerphoneOn()) {
                    return PlayerAgent$AudioSinkType.AUDIOSINK_BUILTIN.getDecriptionString();
                }
                if (audioManager.isWiredHeadsetOn()) {
                    return PlayerAgent$AudioSinkType.AUDIOSINK_HEADPHONE.getDecriptionString();
                }
            }
        }
        return PlayerAgent$AudioSinkType.AUDIOSINK_DEAULT.getDecriptionString();
    }
    
    private long getPreferredPeakBpsForLogging() {
        long n;
        if (PlayerAgent.MaxBRThreshold != 20000) {
            n = PlayerAgent.MaxBRThreshold + 100;
        }
        else {
            n = PlayerAgent.MaxBRThreshold;
        }
        return n * 1000L;
    }
    
    private void handleAudioTrackChanged(final AudioTrackChanged audioTrackChanged) {
        Log.d(PlayerAgent.TAG, "MEDIA_AUDIO_CHANGE 53");
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnAudioChangeHandler(), audioTrackChanged.getTrackIndex());
    }
    
    private void handleBufferRange(final BufferRange bufferRange) {
        if (Log.isLoggable()) {
            Log.d(PlayerAgent.TAG, "MEDIA_BANDWIDTH_UPDATE :" + bufferRange.getBandwidth());
        }
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnBandwidthChangeHandler(), bufferRange.getBandwidth());
    }
    
    private void handleBufferingComplete() {
        Log.d(PlayerAgent.TAG, "BUFFERING COMPLETE 100");
        this.mBufferingCompleted = true;
        this.handlePlayback();
    }
    
    private void handleBufferring(final Buffering buffering) {
        if (Log.isLoggable()) {
            Log.d(PlayerAgent.TAG, "MEDIA_BANDWIDTH_UPDATE :" + buffering.getPercentage());
        }
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnBufferingUpdateHandler(), buffering.getPercentage());
    }
    
    private void handleEndOfPlayback() {
        Log.d(PlayerAgent.TAG, "MEDIA_PLAYBACK_COMPLETE 2");
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnCompletionHandler(), new Object[0]);
    }
    
    private void handleError(final NccpError mActionId12Error) {
        Log.d(PlayerAgent.TAG, "Nccp error receieved");
        if (!this.ignoreErrorsWhileActionId12IsProcessed) {
            if (!(mActionId12Error instanceof NccpActionId)) {
                Log.w(PlayerAgent.TAG, "We will ignore received NccpNetworkingError/NetworkError, since if we need to handle it will be followed with action ID.");
            }
            else {
                final NccpActionId nccpActionId = (NccpActionId)mActionId12Error;
                if (nccpActionId.getActionId() == 11) {
                    Log.w(PlayerAgent.TAG, "ActionID 11 NFErr_MC_Abort Playback.");
                    this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnNccpErrorHandler(), mActionId12Error);
                    return;
                }
                if (this.inPlaybackSession) {
                    Log.d(PlayerAgent.TAG, "We are in playback. Ignore all errors, except 11.");
                    if (Log.isLoggable()) {
                        Log.d(PlayerAgent.TAG, "Error in Playback, being ignored " + nccpActionId);
                    }
                }
                else {
                    if (nccpActionId.getActionId() == 12) {
                        Log.w(PlayerAgent.TAG, "ActionID 12 NFErr_MC_StaleCredentials");
                        this.ignoreErrorsWhileActionId12IsProcessed = true;
                        this.mActionId12Error = mActionId12Error;
                        this.getUserAgent().doDummyWebCall(this.webClientCallback);
                        return;
                    }
                    Log.d(PlayerAgent.TAG, "Handle all errors except if they are for background events, such as logblob, ping, playdata or heartbeat...");
                    final String transaction = nccpActionId.getTransaction();
                    if ("heartbeat".equalsIgnoreCase(transaction) || "logblob".equalsIgnoreCase(transaction) || "playdata".equalsIgnoreCase(transaction) || "ping".equalsIgnoreCase(transaction)) {
                        if (Log.isLoggable()) {
                            Log.d(PlayerAgent.TAG, "Ignore action id on " + transaction + ". We will deal with only licence and authorize here when not in playback");
                        }
                    }
                    else {
                        if ("background".equals(mActionId12Error.getType())) {
                            Log.d(PlayerAgent.TAG, "We received background nccp error. Ignoring!");
                            return;
                        }
                        if (Log.isLoggable()) {
                            Log.d(PlayerAgent.TAG, "Handling error: " + mActionId12Error);
                        }
                        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnNccpErrorHandler(), mActionId12Error);
                    }
                }
            }
        }
    }
    
    private void handleGenericMediaEvent(final GenericMediaEvent genericMediaEvent) {
        final String type = genericMediaEvent.getType();
        if (IMedia$MediaEventEnum.media_endOfStream.getName().equalsIgnoreCase(type)) {
            this.handleEndOfPlayback();
        }
        else {
            if (IMedia$MediaEventEnum.media_bufferingComplete.getName().equalsIgnoreCase(type)) {
                this.handleBufferingComplete();
                return;
            }
            if (IMedia$MediaEventEnum.media_underflow.getName().equalsIgnoreCase(type)) {
                this.handleUnderflow();
                return;
            }
            if (Log.isLoggable()) {
                Log.e(PlayerAgent.TAG, "Tags not handled yet " + type);
            }
        }
    }
    
    private void handleMediaError(final Error error) {
        Log.d(PlayerAgent.TAG, "Media error receieved");
        if (!this.ignoreErrorsWhileActionId12IsProcessed) {
            this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnMediaErrorHandler(), error);
        }
    }
    
    private void handleMediaWarning(final Warning warning) {
        Log.d(PlayerAgent.TAG, "Media warning receieved");
        if (warning.containsInStack("NFErr_MC_SubtitleFailure")) {
            Log.e(PlayerAgent.TAG, "=====> Subtitle failed!");
            this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnSubtitleFailedHandler(), new Object[0]);
        }
    }
    
    private void handlePlayback() {
        synchronized (this) {
            Log.d(PlayerAgent.TAG, "handlePlayback starts...");
            if (this.seeking) {
                Log.d(PlayerAgent.TAG, "MEDIA_SEEK_COMPLETE 4");
                this.seeking = false;
                this.handlePlayerListenerWithDelay(this.mPlayerListenerManager.getPlayerListenerOnSeekCompleteHandler(), 300L, new Object[0]);
            }
            else {
                Log.d(PlayerAgent.TAG, "MEDIA_PLAYBACK_STARTED 6");
                this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnPlayingHandler(), new Object[0]);
            }
            Log.d(PlayerAgent.TAG, "handlePlayback end");
        }
    }
    
    private void handlePlayerListener(final PlayerListenerManager$PlayerListenerHandler playerListenerManager$PlayerListenerHandler, final Object... array) {
        synchronized (this.mPlayerListenerManager) {
            for (final IPlayer$PlayerListener player$PlayerListener : this.mPlayerListenerManager.getListeners()) {
                if (player$PlayerListener != null && player$PlayerListener.isListening()) {
                    this.getMainHandler().post((Runnable)new PlayerAgent$7(this, playerListenerManager$PlayerListenerHandler, player$PlayerListener, array));
                }
            }
        }
    }
    // monitorexit(playerListenerManager)
    
    private void handlePlayerListenerWithDelay(final PlayerListenerManager$PlayerListenerHandler playerListenerManager$PlayerListenerHandler, final long n, final Object... array) {
        synchronized (this.mPlayerListenerManager) {
            for (final IPlayer$PlayerListener player$PlayerListener : this.mPlayerListenerManager.getListeners()) {
                if (player$PlayerListener != null && player$PlayerListener.isListening()) {
                    this.getMainHandler().postDelayed((Runnable)new PlayerAgent$8(this, playerListenerManager$PlayerListenerHandler, player$PlayerListener, array), n);
                }
            }
        }
    }
    // monitorexit(playerListenerManager)
    
    private void handlePrepare(final OpenComplete openComplete) {
        synchronized (this) {
            if (this.preparedCompleted) {
                Log.w(PlayerAgent.TAG, "openComplete already executed");
            }
            else {
                Log.d(PlayerAgent.TAG, "handle openComplete starts...");
                this.preparedCompleted = true;
                if (!this.toCancelOpen) {
                    this.mXid = openComplete.getSessionId();
                    Log.d(PlayerAgent.TAG, "handle openComplete notifying client");
                    this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerPrepareHandler(), openComplete.getWatermark());
                    if (Log.isLoggable()) {
                        Log.d(PlayerAgent.TAG, "MEDIA_SET_VIDEO_SIZE 5, w " + this.mMedia.getVideoWidth() + ", h " + this.mMedia.getVideoHeight() + ", watermark " + openComplete.getWatermark());
                    }
                    Log.d(PlayerAgent.TAG, "handle openComplete end");
                }
            }
        }
    }
    
    private void handleStatechanged(final Statechanged statechanged) {
    Label_0068_Outer:
        while (true) {
            boolean b = false;
            while (true) {
            Label_0230:
                while (true) {
                    Label_0227: {
                        Label_0197: {
                            Label_0167: {
                                Label_0141: {
                                    synchronized (this) {
                                        switch (statechanged.getState()) {
                                            case 0: {
                                                Log.d(PlayerAgent.TAG, "State OPENING");
                                                if (this.mState != 0) {
                                                    this.transitToOpeningState();
                                                    this.mState = 0;
                                                }
                                                final Context context = this.getContext();
                                                if (this.mState != 2 && this.mState != 3) {
                                                    this.notifyWidgetServiceOfPlayState(context, b, this.mMovieId);
                                                    return;
                                                }
                                                break Label_0230;
                                            }
                                            case 1: {
                                                break;
                                            }
                                            case 2: {
                                                break Label_0141;
                                            }
                                            case 3: {
                                                break Label_0167;
                                            }
                                            case 4: {
                                                break Label_0197;
                                            }
                                            default: {
                                                break Label_0227;
                                            }
                                        }
                                    }
                                    Log.d(PlayerAgent.TAG, "State PLAYING");
                                    if (this.mState != 1) {
                                        this.mState = 1;
                                        continue Label_0068_Outer;
                                    }
                                    continue Label_0068_Outer;
                                }
                                Log.d(PlayerAgent.TAG, "State PAUSED");
                                if (this.mState != 2) {
                                    this.mState = 2;
                                    continue Label_0068_Outer;
                                }
                                continue Label_0068_Outer;
                            }
                            Log.d(PlayerAgent.TAG, "State STOPPED");
                            if (this.mState != 3) {
                                this.transitToStoppedState();
                                this.mState = 3;
                                continue Label_0068_Outer;
                            }
                            continue Label_0068_Outer;
                        }
                        Log.d(PlayerAgent.TAG, "State CLOSED");
                        if (this.mState != 4) {
                            this.transitToClosedState();
                            this.mState = 4;
                            continue Label_0068_Outer;
                        }
                        continue Label_0068_Outer;
                    }
                    continue Label_0068_Outer;
                }
                b = true;
                continue;
            }
        }
    }
    
    private void handleSubtitleData(final SubtitleData subtitleData) {
        Log.d(PlayerAgent.TAG, "MEDIA_SUBTITLE_DATA 100");
        this.mSubtitles.changeSubtitle(subtitleData, this.mMedia.getDisplayAspectRatio(), this.mBookmark, this.mStartPlayPositionInTitleInMs);
    }
    
    private void handleSubtitleUpdate(final int n) {
        while (true) {
            Label_0087: {
                synchronized (this) {
                    if (Log.isLoggable()) {
                        Log.d(PlayerAgent.TAG, "Update PTS received " + n);
                    }
                    if (this.mMedia.getCurrentSubtitleTrack() == null) {
                        Log.d(PlayerAgent.TAG, "Subtitles are not visible, do not send any update");
                    }
                    else {
                        if (this.mSubtitles != null) {
                            break Label_0087;
                        }
                        Log.d(PlayerAgent.TAG, "Subtitle manager is not available.");
                    }
                    return;
                }
            }
            final SubtitleDownloadManager subtitleDownloadManager;
            if (subtitleDownloadManager.getSubtitleParser() == null) {
                Log.d(PlayerAgent.TAG, "Subtitle data is not available.");
                return;
            }
            if (!subtitleDownloadManager.getSubtitleParser().isReady()) {
                Log.d(PlayerAgent.TAG, "Subtitle data is not ready yet!");
                return;
            }
            if (!this.isPlaying()) {
                Log.d(PlayerAgent.TAG, "Not playing, do NOT send subtitle screen update");
                return;
            }
            if (!this.canUpdatePosition(n)) {
                Log.d(PlayerAgent.TAG, "Can not update position, do NOT send subtitle screen update");
                return;
            }
            this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnSubtitleChangeHandler(), subtitleDownloadManager.getSubtitleParser().getSubtitlesForPosition(n));
        }
    }
    
    private void handleUnderflow() {
        Log.w(PlayerAgent.TAG, "MEDIA_PLAYBACK_STALLED 7");
        this.mBufferingCompleted = false;
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnStalledHandler(), new Object[0]);
    }
    
    private void handleUpdatePts(final int n) {
        this.mInPlayback = true;
        this.inPlaybackSession = true;
        if (!this.splashScreenRemoved) {
            this.muteAudio(false);
            this.splashScreenRemoved = true;
            this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnStartedHandler(), new Object[0]);
        }
        if (this.canStartBifDownload()) {
            this.startBif();
        }
        this.handleSubtitleUpdate(n);
        this.handleVolumeChange();
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnUpdatePtsHandler(), n);
    }
    
    private void handleVolumeChange() {
        if (!this.mUpdatePlaybackVolumeMetric.get()) {
            Log.w(PlayerAgent.TAG, "Playback is not in progress, do not update volume!");
            return;
        }
        final PlaybackVolumeMetric mPlaybackVolumeMetric = this.mPlaybackVolumeMetric;
        if (mPlaybackVolumeMetric == null) {
            Log.e(PlayerAgent.TAG, "handleVolumeChange:: This should not happen, playback metric should be created on open()");
            this.mPlaybackVolumeMetric = new PlaybackVolumeMetric(this.getContext());
            return;
        }
        final PlaybackVolumeMetric mPlaybackVolumeMetric2 = new PlaybackVolumeMetric(this.getContext());
        if (mPlaybackVolumeMetric2.equals(this.mPlaybackVolumeMetric)) {
            Log.d(PlayerAgent.TAG, "handleVolumeChange:: no change");
            return;
        }
        if (Log.isLoggable()) {
            Log.d(PlayerAgent.TAG, "handleVolumeChange:: change from " + mPlaybackVolumeMetric.getVolumeMetric() + " to " + mPlaybackVolumeMetric2.getVolumeMetric());
        }
        this.mMedia.volumeChange(mPlaybackVolumeMetric, mPlaybackVolumeMetric2);
        this.mPlaybackVolumeMetric = mPlaybackVolumeMetric2;
    }
    
    private boolean isMPPlayerType() {
        final PlayerType currentType = PlayerTypeFactory.getCurrentType(this.getContext());
        return currentType == PlayerType.device12 || currentType == PlayerType.device10 || currentType == PlayerType.device8;
    }
    
    private void muteAudio(final boolean muted) {
        synchronized (this) {
            if (muted != this.muted && this.getContext() != null) {
                final AudioManager audioManager = (AudioManager)this.getContext().getSystemService("audio");
                if (audioManager != null) {
                    audioManager.setStreamMute(3, muted);
                    this.muted = muted;
                    if (muted) {
                        Log.d(PlayerAgent.TAG, "MUTED");
                    }
                    else {
                        Log.d(PlayerAgent.TAG, "UN-MUTED");
                    }
                }
            }
        }
    }
    
    private void notifyWidgetServiceOfPlayState(final Context context, final boolean b, final long n) {
        if (Log.isLoggable()) {
            Log.d(PlayerAgent.TAG, String.format(" notifyWidgetServiceOfPlayState - inPause:%b, playableId:%s", b, n));
        }
        PreAppAgentDataHandler.notifyPServiceOfPlayState(context, b, String.valueOf(n));
    }
    
    private void playWithBookmarkCheck() {
        this.seekedToPosition = (int)(Object)Long.valueOf(this.mBookmark);
        final int duration = this.getDuration();
        if (Log.isLoggable()) {
            Log.d(PlayerAgent.TAG, "movie duration = " + duration + ", and bookmark = " + this.seekedToPosition);
        }
        this.mState = 6;
        this.mMedia.play(this.mBookmark);
        this.toPlayAfterStop = false;
    }
    
    private void preparePlayerType(final PlayerType playerType) {
        if (playerType == PlayerType.device10 || playerType == PlayerType.device11) {
            this.mHelper.prepareJPlayer(this.mMedia, this.mSurface, this.mJPlayerListener, this.isPropertyStreamingVideoDrs(), this.getConfigurationAgent().getJPlayerConfig());
        }
        else {
            this.mHelper.prepare(this.mMedia, this.mSurface, this.getContext());
            if (this.mHelper != null && this.mHelper instanceof JPlayer2Helper) {
                ((JPlayer2Helper)this.mHelper).setMaxVideoHeight(this.getConfigurationAgent().getVideoResolutionRange());
            }
        }
    }
    
    private void registerReceivers() {
    }
    
    private void registerUserAgentReceiver() {
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mUserAgentReceiver, UserAgentBroadcastIntents.getNotificationIntentFilter());
    }
    
    private void release() {
        Log.d(PlayerAgent.TAG, "release()");
        this.reportPlaybackEnded();
        if (this.mHelper != null) {
            this.mHelper.release();
            this.mHelper = null;
        }
        if (this.mJPlayerListener != null) {
            this.mJPlayerListener = null;
        }
        this.mBookmark = 0L;
        this.preparedCompleted = false;
        this.mXid = "";
        this.splashScreenRemoved = false;
        this.seekedToPosition = 0;
        this.mBufferingCompleted = false;
        this.pendingError = null;
        this.mPlaybackVolumeMetric = null;
        this.mUpdatePlaybackVolumeMetric.set(false);
        this.muteAudio(false);
        this.clearBifs();
    }
    
    private void reportPlaybackEnded() {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.PLAYER");
        LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(intent);
    }
    
    private void reportPlaybackPaused() {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_PAUSED");
        intent.addCategory("com.netflix.mediaclient.intent.category.PLAYER");
        LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(intent);
    }
    
    private void reportPlaybackStarted() {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.PLAYER");
        LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(intent);
    }
    
    private void reportPlaybackUnpaused() {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PLAYER_LOCAL_PLAYBACK_UNPAUSED");
        intent.addCategory("com.netflix.mediaclient.intent.category.PLAYER");
        LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(intent);
    }
    
    private void reportSubtitleQoeIfSubtitleIsChanged(final Subtitle subtitle) {
        while (true) {
            while (true) {
                Label_0166: {
                    Label_0153: {
                        synchronized (this) {
                            if (this.shouldReportSubtitleQoe(subtitle)) {
                                if (this.mSubtitles == null) {
                                    Log.e(PlayerAgent.TAG, "Subtitles are null, can not get parser!");
                                }
                                else {
                                    final SubtitleParser subtitleParser = this.mSubtitles.getSubtitleParser();
                                    if (subtitleParser == null) {
                                        break Label_0153;
                                    }
                                    final int numberOfSubtitlesExpectedToBeDisplayed = subtitleParser.getNumberOfSubtitlesExpectedToBeDisplayed();
                                    final int numberOfDisplayedSubtitles = subtitleParser.getNumberOfDisplayedSubtitles();
                                    final SubtitleUrl subtitleUrl = subtitleParser.getSubtitleUrl();
                                    if (subtitleUrl == null) {
                                        break Label_0166;
                                    }
                                    final String downloadableId = subtitleUrl.getDownloadableId();
                                    if (!StringUtils.isNotEmpty(downloadableId)) {
                                        break Label_0166;
                                    }
                                    if (Log.isLoggable()) {
                                        Log.d(PlayerAgent.TAG, "QoE: for subtitle " + downloadableId + " we where expected to show " + numberOfSubtitlesExpectedToBeDisplayed + " and we showed " + numberOfDisplayedSubtitles + " subtitles.");
                                    }
                                    this.reportSubtitleQoe(downloadableId, numberOfSubtitlesExpectedToBeDisplayed, numberOfDisplayedSubtitles);
                                }
                            }
                            return;
                        }
                    }
                    Log.w(PlayerAgent.TAG, "Parser is null, nothing to report!");
                    return;
                }
                final String downloadableId = "";
                continue;
            }
        }
    }
    
    private boolean shouldReportSubtitleQoe(final Subtitle subtitle) {
        final Subtitle currentSubtitleTrack = this.mMedia.getCurrentSubtitleTrack();
        if (currentSubtitleTrack == null) {
            Log.d(PlayerAgent.TAG, "isNewSubtitle: current subtitle is null, can not report anything...");
            return false;
        }
        if (currentSubtitleTrack.equals(subtitle)) {
            Log.d(PlayerAgent.TAG, "isNewSubtitle: subtitle is not changed, do not report anything...");
            return false;
        }
        Log.d(PlayerAgent.TAG, "isNewSubtitle: subtitle is changed, report QoE...");
        return true;
    }
    
    private void startBif() {
        synchronized (this) {
            if (this.mBifManager == null && this.mMedia.getTrickplayUrlList() != null) {
                this.mBifManager = new BifManager(this.getContext(), this.mMedia.getTrickplayUrlList(), this.seekedToPosition);
            }
        }
    }
    
    private void transitToClosedState() {
        if (this.mCloseTimeoutTask != null) {
            this.mCloseTimeoutTask.cancel();
            this.mCloseTimeoutTask = null;
        }
        if (this.mTimer != null) {
            this.mTimer.purge();
        }
        this.muteAudio(false);
        this.toCancelOpen = false;
        this.toPlayAfterStop = false;
        if (this.toOpenAfterClose) {
            if (this.mHelper != null) {
                this.mHelper.release();
            }
            this.toOpenAfterClose = false;
            this.mState = 5;
            if (this.mStartPlayTimeoutTask != null) {
                this.mStartPlayTimeoutTask.cancel();
            }
            if (this.mInitVBRTimeoutTask != null) {
                this.mInitVBRTimeoutTask.cancel();
            }
            if (this.mTimer != null) {
                this.mTimer.purge();
            }
            this.reloadPlayer();
            this.clearBifs();
            this.mUpdatePlaybackVolumeMetric.set(true);
            final PlaybackVolumeMetric mPlaybackVolumeMetric = new PlaybackVolumeMetric(this.getContext());
            this.mMedia.setStreamingQoe(this.getConfigurationAgent().getStreamingQoe(), this.getConfigurationAgent().enableHTTPSAuth(), this.isMPPlayerType());
            this.mMedia.open(this.mMovieId, this.mPlayContext, ConnectivityUtils.getCurrentNetType(this.getContext()), this.mBookmark, this.getConfigurationAgent().isPreviewContentEnabled(), mPlaybackVolumeMetric, PlayerAgent.MaxBRThreshold * 1000);
            this.mPlaybackVolumeMetric = mPlaybackVolumeMetric;
            return;
        }
        this.release();
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerPlaybackClosedHandler(), new Object[0]);
    }
    
    private void transitToOpeningState() {
        Log.d(PlayerAgent.TAG, "MP: Set audio bitrange to 64 Kbps");
        this.mMedia.setAudioBitrateRange(this.mAudioBitrateRange);
        this.mMedia.setVideoResolutionRange(this.getConfigurationAgent().getVideoResolutionRange());
        this.mMedia.setThrotteled(false);
        this.mMedia.setNetworkProfile(2);
        this.muteAudio(true);
    }
    
    private void transitToStoppedState() {
        if (this.mState == 0) {
            this.mMedia.setAudioBitrateRange(this.mAudioBitrateRange);
            if (this.isMPPlayerType()) {
                if (ConnectivityUtils.isNetworkTypeCellular(this.getContext()) && Coppola1Utils.isLowBitratePlaybackOnMobileNetwork(this.getContext()) && DeviceUtils.isPortrait(this.getContext())) {
                    this.mMedia.setVideoBitrateRange(0, 100);
                    Log.i(PlayerAgent.TAG, "Pushing 100kbps bitrate for faster playback launch at Coppola portrait MDP in low bitrate test cell");
                    PlayerAgent.TimeToWaitBeforeLowBRStreamsEnabled = 7000;
                }
                else {
                    this.mMedia.setVideoBitrateRange(200, PlayerAgent.MaxBRThreshold);
                }
                if (this.mTimer != null) {
                    this.mInitVBRTimeoutTask = new PlayerAgent$InitialVideoBitrateRangeTimeoutTask(this, null);
                    this.mTimer.schedule(this.mInitVBRTimeoutTask, PlayerAgent.TimeToWaitBeforeLowBRStreamsEnabled);
                }
            }
            this.mMedia.setVideoResolutionRange(this.getConfigurationAgent().getVideoResolutionRange());
            this.mMedia.setThrotteled(false);
            this.mMedia.setNetworkProfile(2);
        }
        if (this.toPlayAfterStop) {
            this.playWithBookmarkCheck();
        }
    }
    
    private void unRegisterReceivers() {
    }
    
    private void unregisterUserAgentReceiver() {
        try {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.mUserAgentReceiver);
        }
        catch (Exception ex) {
            Log.i(PlayerAgent.TAG, "unregisterUserAgenReceiver " + ex);
        }
    }
    
    private void updateSubtitleSettings(final boolean b) {
        final SubtitleConfiguration subtitleConfiguration = this.findSubtitleConfiguration();
        if (Log.isLoggable()) {
            Log.d(PlayerAgent.TAG, "Subtitle configuration was " + this.mSubtitleConfiguration);
            Log.d(PlayerAgent.TAG, "Sets subtitle configuration to " + subtitleConfiguration);
        }
        if (this.mSubtitleConfiguration == subtitleConfiguration && !b) {
            if (Log.isLoggable()) {
                Log.d(PlayerAgent.TAG, "Already used subtitle configuration, do nothing ");
            }
            return;
        }
        if (b) {
            Log.d(PlayerAgent.TAG, "Forced set of subtitle configuration");
        }
        this.mMedia.setSubtitleProfile(subtitleConfiguration.getProfile());
        this.mMedia.setSubtitleOutputMode(subtitleConfiguration.getMode());
        this.mSubtitleConfiguration = subtitleConfiguration;
    }
    
    private void updateSubtitleSettingsFromQaLocalOverride(final int n) {
        final SubtitleConfiguration lookup = SubtitleConfiguration.lookup(n);
        if (Log.isLoggable()) {
            Log.d(PlayerAgent.TAG, "Received local override " + n);
            Log.d(PlayerAgent.TAG, "Subtitle configuration was " + this.mSubtitleConfiguration);
            Log.d(PlayerAgent.TAG, "Sets subtitle configuration to " + lookup);
        }
        if (this.mSubtitleConfiguration == lookup) {
            if (Log.isLoggable()) {
                Log.d(PlayerAgent.TAG, "Already used subtitle configuration, do nothing ");
            }
            return;
        }
        this.mMedia.setSubtitleProfile(lookup.getProfile());
        this.mMedia.setSubtitleOutputMode(lookup.getMode());
        this.mSubtitleConfiguration = lookup;
    }
    
    @Override
    public void addPlayerListener(final IPlayer$PlayerListener player$PlayerListener) {
        this.mPlayerListenerManager.addPlayerListener(player$PlayerListener);
    }
    
    @Override
    public boolean canUpdatePosition(final int n) {
        if (this.seeking) {
            Log.d(PlayerAgent.TAG, "canUpdatePosition:: seeking in progress, can not update position");
        }
        else {
            if (n >= this.seekedToPosition) {
                if (Log.isLoggable()) {
                    Log.d(PlayerAgent.TAG, "canUpdatePosition:: pts [" + n + "] >= seekedToPosition [" + this.seekedToPosition + "] , can update position");
                }
                if (!this.validPtsRecieved) {
                    if (this.prevEndPosition > this.seekedToPosition && n >= this.prevEndPosition - 2000) {
                        if (Log.isLoggable()) {
                            Log.d(PlayerAgent.TAG, "canUpdatePosition:: pts [" + n + "] >= prevEndPosition [" + this.prevEndPosition + "] , invlalid PTS");
                            return false;
                        }
                        return false;
                    }
                    else {
                        this.validPtsRecieved = true;
                    }
                }
                return true;
            }
            if (Log.isLoggable()) {
                Log.w(PlayerAgent.TAG, "canUpdatePosition:: pts [" + n + "] < seekedToPosition [" + this.seekedToPosition + "] , can NOT update position");
                return false;
            }
        }
        return false;
    }
    
    @Override
    public void close() {
        this.mUpdatePlaybackVolumeMetric.set(false);
        this.mPlaybackVolumeMetric = new PlaybackVolumeMetric(this.getContext());
        Log.d(PlayerAgent.TAG, "close()");
        this.mSurface = null;
        if (this.mSubtitles != null) {
            this.mSubtitles.close();
        }
        this.inPlaybackSession = false;
        this.muteAudio(true);
        this.mPlayerExecutor.execute(this.onCloseRunnable);
    }
    
    @Override
    public void destroy() {
        this.unregisterUserAgentReceiver();
        this.unRegisterReceivers();
        if (this.mPlayerExecutor != null) {
            this.mPlayerExecutor.shutdown();
        }
        super.destroy();
        if (Log.isLoggable()) {
            Log.d(PlayerAgent.TAG, "Destroying " + this.getClass().getSimpleName());
        }
    }
    
    @Override
    protected void doInit() {
        this.mNrdp = this.getNrdController().getNrdp();
        if (this.mNrdp == null || !this.mNrdp.isReady()) {
            this.initCompleted(CommonStatus.NRD_ERROR);
            Log.e(PlayerAgent.TAG, "NRDP is NOT READY");
            return;
        }
        this.mMedia = this.mNrdp.getMedia();
        this.mMediaEventListener = new PlayerAgent$GenericMediaEventListener(this, null);
        final IMedia$MediaEventEnum[] values = IMedia$MediaEventEnum.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final IMedia$MediaEventEnum media$MediaEventEnum = values[i];
            if (Log.isLoggable()) {
                Log.d(PlayerAgent.TAG, "Registering as listener for " + media$MediaEventEnum.getName());
            }
            this.mMedia.addEventListener(media$MediaEventEnum.getName(), this.mMediaEventListener);
        }
        this.mPlayerType = PlayerTypeFactory.getCurrentType(this.getContext());
        this.mState = -1;
        this.toCancelOpen = false;
        if (this.mPlayerType == null) {
            Log.e(PlayerAgent.TAG, "This should not happen, player type was null at this point! Use default.");
            this.mPlayerType = PlayerTypeFactory.findDefaultPlayerType();
        }
        else if (Log.isLoggable()) {
            Log.d(PlayerAgent.TAG, "Player type is " + this.mPlayerType.getDescription());
        }
        this.mHelper = MediaPlayerHelperFactory.getInstance(this.getContext(), this.mPlayerType);
        Log.d(PlayerAgent.TAG, "MP: Set audio bitrange to 64 Kbps");
        this.mMedia.setAudioBitrateRange(this.mAudioBitrateRange);
        this.mMedia.setVideoResolutionRange(this.getConfigurationAgent().getVideoResolutionRange());
        this.mMedia.setStreamingQoe(this.getConfigurationAgent().getStreamingQoe(), this.getConfigurationAgent().enableHTTPSAuth(), this.isMPPlayerType());
        this.mMedia.setThrotteled(false);
        this.mMedia.setNetworkProfile(2);
        Log.d(PlayerAgent.TAG, "MP: Set to Mobile network Profile");
        this.mManifestCache = new ManifestCache(this.mMedia);
        this.updateSubtitleSettings(true);
        this.mTimer = new Timer("watchdog timer");
        this.registerReceivers();
        this.registerUserAgentReceiver();
        this.mPlayerExecutor = Executors.newSingleThreadExecutor();
        this.mPlayerFileManager = new PlayerFileManager(this.getContext());
        this.mSubtitles = new SubtitleDownloadManager(this, this.getUserAgent());
        this.initCompleted(CommonStatus.OK);
    }
    
    void excuteOnPlayExecutor(final Runnable runnable) {
        this.mPlayerExecutor.execute(runnable);
    }
    
    @Override
    public AudioSubtitleDefaultOrderInfo[] getAudioSubtitleDefaultOrderInfo() {
        return this.mMedia.getAudioSubtitleDefaultOrderInfo();
    }
    
    @Override
    public AudioSource[] getAudioTrackList() {
        return this.mMedia.getAudioTrackList();
    }
    
    @Override
    public ByteBuffer getBifFrame(final int n) {
        if (this.mBifManager != null) {
            return this.mBifManager.getIndexFrame(n);
        }
        return null;
    }
    
    @Override
    public AudioSource getCurrentAudioTrack() {
        return this.mMedia.getCurrentAudioTrack();
    }
    
    @Override
    public long getCurrentPlayableId() {
        return this.mMovieId;
    }
    
    @Override
    public int getCurrentPositionMs() {
        return this.mMedia.getMediaPosition();
    }
    
    @Override
    public int getCurrentProgress() {
        final int currentPosition = this.mMedia.getCurrentPosition();
        if (currentPosition < this.seekedToPosition) {
            final int abs = Math.abs(this.seekedToPosition - currentPosition);
            if (abs <= 60000) {
                if (Log.isLoggable()) {
                    Log.w(PlayerAgent.TAG, "Stick to seekToPosition" + this.seekedToPosition);
                }
                return this.seekedToPosition;
            }
            if (Log.isLoggable()) {
                Log.d(PlayerAgent.TAG, "Gap is bigger than 1 minute" + abs);
            }
        }
        else if (!this.validPtsRecieved) {
            if (this.prevEndPosition - 2000 > this.seekedToPosition && currentPosition >= this.seekedToPosition + 1500) {
                if (Log.isLoggable()) {
                    Log.d(PlayerAgent.TAG, "pts [" + currentPosition + "] >= prevEndPosition [" + this.prevEndPosition + "] , invlalid PTS");
                }
                return this.seekedToPosition;
            }
            Log.d(PlayerAgent.TAG, "Valid PTS was received");
            this.validPtsRecieved = true;
            return currentPosition;
        }
        return currentPosition;
    }
    
    @Override
    public Subtitle getCurrentSubtitleTrack() {
        return this.mMedia.getCurrentSubtitleTrack();
    }
    
    @Override
    public Point getDisplayAspectRatioDimension() {
        return this.mMedia.getDisplayAspectRatioDimension();
    }
    
    @Override
    public int getDuration() {
        return this.mMedia.getDuration();
    }
    
    @Override
    public IManifestCache getManifestCache() {
        return this.mManifestCache;
    }
    
    @Override
    public IPlayerFileCache getPlayerFileCache() {
        return this.mPlayerFileManager;
    }
    
    @Override
    public PlayoutMetadata getPlayoutMetadata() {
        return this.mMedia.getPlayoutMetadata();
    }
    
    @Override
    public SubtitleConfiguration getSubtitleConfiguration() {
        return this.mSubtitleConfiguration;
    }
    
    @Override
    public IMedia$SubtitleProfile getSubtitleProfileFromMetadata() {
        final SubtitleDownloadManager mSubtitles = this.mSubtitles;
        if (mSubtitles != null && mSubtitles.getSubtitleParser() != null) {
            return mSubtitles.getSubtitleParser().getSubtitleProfile();
        }
        return null;
    }
    
    @Override
    public Subtitle[] getSubtitleTrackList() {
        return this.mMedia.getSubtitleTrackList();
    }
    
    @Override
    public int getVideoHeight() {
        return this.mMedia.getVideoHeight();
    }
    
    @Override
    public int getVideoWidth() {
        return this.mMedia.getVideoWidth();
    }
    
    @Override
    public String getXid() {
        return this.mXid;
    }
    
    public void handleConnectivityChange(final Intent intent) {
        if (ConnectivityUtils.isNetworkTypeCellular(this.getContext())) {
            if (BandwidthUtility.shouldLimitCellularVideoBitrate(this.getContext())) {
                final int cellularVideoBitrateKbps = BandwidthUtility.getCellularVideoBitrateKbps(this.getContext(), this.getConfigurationAgent().getBWSaveConfigData());
                if (cellularVideoBitrateKbps > 0 && cellularVideoBitrateKbps < PlayerAgent.MaxBRThreshold) {
                    this.setVideoBitrateRange(0, cellularVideoBitrateKbps);
                }
            }
            this.setVideoStreamingBufferSize(150000);
        }
        else {
            this.setVideoStreamingBufferSize(300000);
        }
        this.getManifestCache().cacheFlush();
    }
    
    @Override
    public boolean isBufferingCompleted() {
        return this.mBufferingCompleted && this.mInPlayback;
    }
    
    @Override
    public boolean isManifestCacheEnabled() {
        return PlayerTypeFactory.isJPlayer2(this.mPlayerType) && !this.getConfigurationAgent().isDisableMcQueenV2();
    }
    
    @Override
    public boolean isPlaying() {
        return this.mMedia.getState() == 1;
    }
    
    public boolean isPropertyStreamingVideoDrs() {
        return !PlayerTypeFactory.isJPlayerBase(PlayerTypeFactory.getCurrentType(this.getContext())) && AndroidUtils.isPropertyStreamingVideoDrs();
    }
    
    @Override
    public void onConfigRefreshed(final Status status) {
        if (status.isSucces()) {
            this.updateSubtitleSettings(false);
        }
    }
    
    @Override
    public void open(final long mMovieId, final PlayContext mPlayContext, final long n) {
        int maxBRThreshold = 0;
        Label_0084_Outer:Label_0203_Outer:
        while (true) {
            while (true) {
                while (true) {
                    Label_0257: {
                        synchronized (this) {
                            while (true) {
                                if (!BandwidthUtility.isBWSavingEnabledForPlay(this.getContext())) {
                                    PlayerAgent.MaxBRThreshold = 20000;
                                    if (Log.isLoggable()) {
                                        Log.d(PlayerAgent.TAG, String.format("nf_bw MaxBRThreshold : %d ", PlayerAgent.MaxBRThreshold));
                                    }
                                    this.mMovieId = mMovieId;
                                    this.mPlayContext = mPlayContext;
                                    this.mBookmark = n;
                                    this.mStartPlayPositionInTitleInMs = n;
                                    if (Log.isLoggable()) {
                                        Log.d(PlayerAgent.TAG, "Open called movieId:" + mMovieId + " trackId:" + mPlayContext.getTrackId() + " bookmark:" + n + " StartPlayPositionInTitleInMs:" + this.mStartPlayPositionInTitleInMs);
                                    }
                                    this.mPlayerExecutor.execute(this.onOpenRunnable);
                                    this.reportPlaybackStarted();
                                    return;
                                }
                                this.mDelayedBifDowloadForDataSaver = new BandwidthDelayedBifDownload();
                                maxBRThreshold = BandwidthUtility.getCellularVideoBitrateKbps(this.getContext(), this.getConfigurationAgent().getBWSaveConfigData());
                                if (Log.isLoggable()) {
                                    Log.d(PlayerAgent.TAG, String.format("nf_bw bwOverride: %d,MaxBRThreshold : %d ", maxBRThreshold, PlayerAgent.MaxBRThreshold));
                                }
                                break Label_0257;
                                PlayerAgent.MaxBRThreshold = maxBRThreshold;
                                continue Label_0084_Outer;
                            }
                            maxBRThreshold = PlayerAgent.MaxBRThreshold;
                            continue Label_0203_Outer;
                        }
                    }
                    if (maxBRThreshold > 0) {
                        continue Label_0203_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    @Override
    public void pause() {
        this.mMedia.pause();
        this.reportPlaybackPaused();
    }
    
    @Override
    public void play() {
        this.mPlayerExecutor.execute(this.onPlayRunnable);
    }
    
    public void reloadPlayer() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: getstatic       com/netflix/mediaclient/service/player/PlayerAgent.TAG:Ljava/lang/String;
        //     5: ldc_w           "reloadPlayer if required"
        //     8: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    11: pop            
        //    12: aload_0        
        //    13: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getContext:()Landroid/content/Context;
        //    16: invokestatic    com/netflix/mediaclient/service/configuration/PlayerTypeFactory.getCurrentType:(Landroid/content/Context;)Lcom/netflix/mediaclient/media/PlayerType;
        //    19: astore_1       
        //    20: aload_0        
        //    21: aload_0        
        //    22: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getContext:()Landroid/content/Context;
        //    25: aload_1        
        //    26: invokestatic    com/netflix/mediaclient/media/MediaPlayerHelperFactory.getInstance:(Landroid/content/Context;Lcom/netflix/mediaclient/media/PlayerType;)Lcom/netflix/mediaclient/media/MediaPlayerHelper;
        //    29: putfield        com/netflix/mediaclient/service/player/PlayerAgent.mHelper:Lcom/netflix/mediaclient/media/MediaPlayerHelper;
        //    32: aload_1        
        //    33: aload_0        
        //    34: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mPlayerType:Lcom/netflix/mediaclient/media/PlayerType;
        //    37: if_acmpne       93
        //    40: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    43: ifeq            85
        //    46: getstatic       com/netflix/mediaclient/service/player/PlayerAgent.TAG:Ljava/lang/String;
        //    49: new             Ljava/lang/StringBuilder;
        //    52: dup            
        //    53: invokespecial   java/lang/StringBuilder.<init>:()V
        //    56: ldc_w           "Player type is not changed! It is still "
        //    59: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    62: aload_0        
        //    63: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mPlayerType:Lcom/netflix/mediaclient/media/PlayerType;
        //    66: invokevirtual   com/netflix/mediaclient/media/PlayerType.getDescription:()Ljava/lang/String;
        //    69: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    72: ldc_w           ". Preparing players!"
        //    75: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    78: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    81: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    84: pop            
        //    85: aload_0        
        //    86: aload_1        
        //    87: invokespecial   com/netflix/mediaclient/service/player/PlayerAgent.preparePlayerType:(Lcom/netflix/mediaclient/media/PlayerType;)V
        //    90: aload_0        
        //    91: monitorexit    
        //    92: return         
        //    93: aload_0        
        //    94: aload_1        
        //    95: putfield        com/netflix/mediaclient/service/player/PlayerAgent.mPlayerType:Lcom/netflix/mediaclient/media/PlayerType;
        //    98: aload_0        
        //    99: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mPlayerType:Lcom/netflix/mediaclient/media/PlayerType;
        //   102: ifnonnull       218
        //   105: getstatic       com/netflix/mediaclient/service/player/PlayerAgent.TAG:Ljava/lang/String;
        //   108: ldc_w           "This should not happen, player type was null at this point! Use default."
        //   111: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   114: pop            
        //   115: aload_0        
        //   116: invokestatic    com/netflix/mediaclient/service/configuration/PlayerTypeFactory.findDefaultPlayerType:()Lcom/netflix/mediaclient/media/PlayerType;
        //   119: putfield        com/netflix/mediaclient/service/player/PlayerAgent.mPlayerType:Lcom/netflix/mediaclient/media/PlayerType;
        //   122: aload_0        
        //   123: aload_0        
        //   124: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mPlayerType:Lcom/netflix/mediaclient/media/PlayerType;
        //   127: invokespecial   com/netflix/mediaclient/service/player/PlayerAgent.preparePlayerType:(Lcom/netflix/mediaclient/media/PlayerType;)V
        //   130: aload_0        
        //   131: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mMedia:Lcom/netflix/mediaclient/javabridge/ui/IMedia;
        //   134: aload_0        
        //   135: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mPlayerType:Lcom/netflix/mediaclient/media/PlayerType;
        //   138: invokeinterface com/netflix/mediaclient/javabridge/ui/IMedia.changePlayer:(Lcom/netflix/mediaclient/media/PlayerType;)V
        //   143: aload_0        
        //   144: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mMedia:Lcom/netflix/mediaclient/javabridge/ui/IMedia;
        //   147: aload_0        
        //   148: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mAudioBitrateRange:Lcom/netflix/mediaclient/media/bitrate/AudioBitrateRange;
        //   151: invokeinterface com/netflix/mediaclient/javabridge/ui/IMedia.setAudioBitrateRange:(Lcom/netflix/mediaclient/media/bitrate/AudioBitrateRange;)V
        //   156: aload_0        
        //   157: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mMedia:Lcom/netflix/mediaclient/javabridge/ui/IMedia;
        //   160: aload_0        
        //   161: invokevirtual   com/netflix/mediaclient/service/player/PlayerAgent.getConfigurationAgent:()Lcom/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface;
        //   164: invokeinterface com/netflix/mediaclient/service/ServiceAgent$ConfigurationAgentInterface.getVideoResolutionRange:()Lcom/netflix/mediaclient/media/VideoResolutionRange;
        //   169: invokeinterface com/netflix/mediaclient/javabridge/ui/IMedia.setVideoResolutionRange:(Lcom/netflix/mediaclient/media/VideoResolutionRange;)V
        //   174: aload_0        
        //   175: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mMedia:Lcom/netflix/mediaclient/javabridge/ui/IMedia;
        //   178: iconst_0       
        //   179: invokeinterface com/netflix/mediaclient/javabridge/ui/IMedia.setThrotteled:(Z)V
        //   184: aload_0        
        //   185: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mMedia:Lcom/netflix/mediaclient/javabridge/ui/IMedia;
        //   188: iconst_2       
        //   189: invokeinterface com/netflix/mediaclient/javabridge/ui/IMedia.setNetworkProfile:(I)V
        //   194: ldc2_w          400
        //   197: invokestatic    java/lang/Thread.sleep:(J)V
        //   200: getstatic       com/netflix/mediaclient/service/player/PlayerAgent.TAG:Ljava/lang/String;
        //   203: ldc_w           "Player changed done"
        //   206: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   209: pop            
        //   210: goto            90
        //   213: astore_1       
        //   214: aload_0        
        //   215: monitorexit    
        //   216: aload_1        
        //   217: athrow         
        //   218: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   221: ifeq            122
        //   224: getstatic       com/netflix/mediaclient/service/player/PlayerAgent.TAG:Ljava/lang/String;
        //   227: new             Ljava/lang/StringBuilder;
        //   230: dup            
        //   231: invokespecial   java/lang/StringBuilder.<init>:()V
        //   234: ldc_w           "Player type is "
        //   237: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   240: aload_0        
        //   241: getfield        com/netflix/mediaclient/service/player/PlayerAgent.mPlayerType:Lcom/netflix/mediaclient/media/PlayerType;
        //   244: invokevirtual   com/netflix/mediaclient/media/PlayerType.getDescription:()Ljava/lang/String;
        //   247: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   250: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   253: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   256: pop            
        //   257: goto            122
        //   260: astore_1       
        //   261: getstatic       com/netflix/mediaclient/service/player/PlayerAgent.TAG:Ljava/lang/String;
        //   264: ldc_w           "ReloadPlayer "
        //   267: aload_1        
        //   268: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   271: pop            
        //   272: goto            200
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  2      85     213    218    Any
        //  85     90     213    218    Any
        //  93     122    213    218    Any
        //  122    194    213    218    Any
        //  194    200    260    275    Ljava/lang/InterruptedException;
        //  194    200    213    218    Any
        //  200    210    213    218    Any
        //  218    257    213    218    Any
        //  261    272    213    218    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0200:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void removePlayerListener(final IPlayer$PlayerListener player$PlayerListener) {
        this.mPlayerListenerManager.removePlayerListener(player$PlayerListener);
    }
    
    public void reportFailedSubtitle(final String s, final SubtitleUrl subtitleUrl, final IMedia$SubtitleFailure media$SubtitleFailure, final boolean b, final Status status, final String[] array) {
        if (Log.isLoggable()) {
            if (status != null) {
                Log.e(PlayerAgent.TAG, "Failed to download subtitles metadata from " + s + ", because " + media$SubtitleFailure + ", details: " + status);
            }
            else {
                Log.e(PlayerAgent.TAG, "Failed to download subtitles metadata from " + s + ", because " + media$SubtitleFailure);
            }
        }
        this.getService().getClientLogging().getErrorLogging().logHandledException("Failed to download subtitle metadata");
        this.mMedia.reportFailedSubtitle(s, subtitleUrl, media$SubtitleFailure, b, status, array);
        this.handlePlayerListener(this.mPlayerListenerManager.getPlayerListenerOnSubtitleFailedHandler(), new Object[0]);
    }
    
    public void reportHandledException(final Exception ex) {
        this.getService().getClientLogging().getErrorLogging().logHandledException(ex);
    }
    
    @Override
    public void reportSubtitleQoe(final String s, final int n, final int n2) {
        this.mMedia.reportSubtitleQoe(s, n, n2);
    }
    
    @Override
    public void seekTo(final int seekedToPosition, final boolean mForcedRebuffer) {
        this.seekedToPosition = seekedToPosition;
        this.mForcedRebuffer = mForcedRebuffer;
        this.mRelativeSeekPosition = 0;
        this.mFuzz = 0;
        this.mPlayerExecutor.execute(this.onSeekRunnable);
        if (this.mSubtitles != null && this.mSubtitles.getSubtitleParser() != null) {
            this.mSubtitles.getSubtitleParser().seeked(this.seekedToPosition);
        }
    }
    
    @Override
    public void seekWithFuzzRange(final int mRelativeSeekPosition, final int mFuzz) {
        this.seekedToPosition = this.getCurrentPositionMs() + mRelativeSeekPosition;
        this.mForcedRebuffer = false;
        this.mRelativeSeekPosition = mRelativeSeekPosition;
        this.mFuzz = mFuzz;
        this.mPlayerExecutor.execute(this.onSeekRunnable);
        if (this.mSubtitles != null && this.mSubtitles.getSubtitleParser() != null) {
            this.mSubtitles.getSubtitleParser().seeked(this.seekedToPosition);
        }
    }
    
    @Override
    public boolean selectTracks(final AudioSource audioSource, final Subtitle subtitle, final boolean b) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d(PlayerAgent.TAG, "Selected track Audio: " + audioSource);
                Log.d(PlayerAgent.TAG, "Selected track Subtitle: " + subtitle);
            }
            this.mMedia.selectTracks(audioSource, subtitle);
            if (b) {
                this.reportSubtitleQoeIfSubtitleIsChanged(subtitle);
                this.mStartPlayPositionInTitleInMs = this.mMedia.getCurrentPosition();
            }
            if (subtitle == null) {
                Log.d(PlayerAgent.TAG, "Removing subtitles");
            }
            return true;
        }
    }
    
    @Override
    public void setJPlayerListener(final JPlayer$JplayerListener mjPlayerListener) {
        this.mJPlayerListener = mjPlayerListener;
    }
    
    @Override
    public void setSurface(final Surface surface) {
        if (Log.isLoggable()) {
            final String tag = PlayerAgent.TAG;
            final StringBuilder append = new StringBuilder().append("Surface is being set: ");
            String s;
            if (this.mSurface != null) {
                s = "SurfaceExisted";
            }
            else {
                s = "No Surface Existed";
            }
            Log.d(tag, append.append(s).toString());
        }
        if (this.mHelper != null && this.mHelper instanceof JPlayer2Helper) {
            ((JPlayer2Helper)this.mHelper).updateSurface(surface);
        }
        if (surface == null) {
            return;
        }
        this.mSurface = surface;
        this.mMedia.setSurface(surface);
    }
    
    @Override
    public void setSurfaceHolder(final SurfaceHolder surfaceHolder) {
    }
    
    public void setVOapi(final long n, final long n2) {
        this.mMedia.setVOapi(n, n2);
    }
    
    @Override
    public void setVideoBitrateRange(final int n, final int maxBRThreshold) {
        if (this.mMedia != null) {
            Log.d(PlayerAgent.TAG, String.format("nf_bw setVideoBitrateRange :(%d, %d)", n, maxBRThreshold));
            PlayerAgent.MaxBRThreshold = maxBRThreshold;
            this.mMedia.setVideoBitrateRange(n, maxBRThreshold);
        }
    }
    
    void setVideoStreamingBufferSize(final int maxVideoBufferSize) {
        if (this.mMedia != null) {
            this.mMedia.setMaxVideoBufferSize(maxVideoBufferSize);
        }
    }
    
    @Override
    public void unpause() {
        this.mMedia.unpause();
        this.reportPlaybackUnpaused();
    }
}
