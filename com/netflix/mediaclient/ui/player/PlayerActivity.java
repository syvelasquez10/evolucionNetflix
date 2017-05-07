// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.ShowDetails;
import com.netflix.mediaclient.servicemgr.MovieDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.util.LanguageChoice;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import java.io.Serializable;
import android.widget.Toast;
import android.os.PowerManager;
import android.os.Process;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.util.LogUtils;
import android.os.Bundle;
import android.content.res.Configuration;
import com.netflix.mediaclient.net.LogMobileType;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.view.Surface;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.annotation.SuppressLint;
import android.view.TextureView;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.javabridge.ui.IMedia;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import android.os.Debug;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.widget.ImageView;
import android.util.Pair;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.view.KeyEvent;
import android.os.Parcelable;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.content.Intent;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.MotionEvent;
import android.media.AudioManager;
import android.widget.SeekBar;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.details.EpisodeListFrag;
import android.os.SystemClock;
import android.view.View;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.view.SurfaceHolder$Callback;
import com.netflix.mediaclient.media.JPlayer.SecondSurface;
import com.netflix.mediaclient.ui.common.Social;
import android.content.BroadcastReceiver;
import android.os.Handler;
import com.netflix.mediaclient.ui.details.EpisodeRowView;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.media.Language;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.media.JPlayer.JPlayer;
import com.netflix.mediaclient.servicemgr.IPlayer;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;

@TargetApi(14)
public class PlayerActivity extends NetflixActivity implements AudioManager$OnAudioFocusChangeListener, PlayerListener, JplayerListener, DialogCanceledListenerProvider
{
    private static final int DELAY_POST = 1000;
    static final String EXTRA_GET_DETAILS_HAS_NEXT_EPISODE = "extra_get_details_has_next_episode";
    static final String EXTRA_GET_DETAILS_PLAY_CONTEXT = "extra_get_details_play_context";
    static final String EXTRA_GET_DETAILS_VIDEO_ID = "extra_get_details_video_id";
    static final String EXTRA_GET_DETAILS_VIDEO_TYPE = "extra_get_details_video_type";
    public static final int INACTIVITY_TIMEOUT = 5000;
    private static final int INVALID_TRACK_INDEX = -1;
    private static final int PAUSE_LOCK_SCREEN_TIMEOUT = 120000;
    private static final long PAUSE_TIMEOUT = 3600000L;
    private static final int SKIP_DELTA_MS = 30000;
    private static final String TAG = "PlayerActivity";
    public static final String TRACK_ID_PREFIX_TAG = "TRACK_ID: ";
    private static final int VOLUME_TIMEOUT = 500;
    private final Runnable allowScreenLockTimeout;
    private final SeekBar$OnSeekBarChangeListener audioPositionListener;
    private final View$OnClickListener episodesListener;
    private ErrorManager errorManager;
    Runnable exitButtonHandler;
    private Language language;
    private int mActionId12Count;
    private Asset mAsset;
    private ServiceAgent.ConfigurationAgentInterface mConfig;
    private DialogCanceledListener mDialogCancedledListener;
    private EpisodeRowListener mEpisodeRowListener;
    private String mErrorDialogId;
    protected Handler mHandler;
    private int mHeight;
    private boolean mIsAssetReady;
    private boolean mIsSurfaceReady;
    private boolean mIsZoomedOut;
    private final boolean mKillOnDestroy;
    private String mMaxStreamsReachedDialogId;
    private final BroadcastReceiver mNetworkChangeReceiver;
    private IPlayer mPlayer;
    private boolean mReloadOnAudioTrackChange;
    private ResourceHelper mResources;
    private boolean mRestartPlayback;
    private PlayScreen mScreen;
    private final Social.SocialProviderCallback mSocialProviderCallback;
    private final PlayerWorkflowState mState;
    private SubtitleManager mSubtitleManager;
    private SecondSurface mSurface2;
    private int mWidth;
    private final Runnable onEverySecond;
    private final Runnable pauseTimeout;
    private final View$OnClickListener playPauseListener;
    private final View$OnClickListener skipBackListener;
    private final SurfaceHolder$Callback surfaceListener;
    private final TappableSurfaceView.SurfaceMeasureListener surfaceMeasureListener;
    private final TappableSurfaceView.TapListener tapListener;
    private final SeekBar$OnSeekBarChangeListener videoPositionListener;
    private final View$OnClickListener zoomListener;
    
    public PlayerActivity() {
        this.mWidth = 0;
        this.mHeight = 0;
        this.mHandler = new Handler();
        this.mState = new PlayerWorkflowState();
        this.mReloadOnAudioTrackChange = false;
        this.mIsZoomedOut = false;
        this.mKillOnDestroy = false;
        this.mActionId12Count = 0;
        this.mRestartPlayback = false;
        this.allowScreenLockTimeout = new Runnable() {
            @Override
            public void run() {
                Log.d("PlayerActivity", "Pause, release awake clock");
                PlayerActivity.this.releaseLockOnScreen();
            }
        };
        this.playPauseListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                PlayerActivity.this.mState.setLastActionTime(SystemClock.elapsedRealtime());
                PlayerActivity.this.mState.userInteraction();
                if (PlayerActivity.this.mPlayer.isPlaying()) {
                    PlayerActivity.this.doPause(true);
                    return;
                }
                PlayerActivity.this.doUnpause();
            }
        };
        this.episodesListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (PlayerActivity.this.destroyed()) {
                    return;
                }
                if (PlayerActivity.this.mAsset == null) {
                    Log.e("PlayerActivity", "mPlayable is null!");
                    return;
                }
                if (!PlayerActivity.this.mAsset.isEpisode()) {
                    Log.e("PlayerActivity", "mPlayable is not episode detail!");
                    return;
                }
                Log.d("PlayerActivity", "Start episodes dialog");
                PlayerActivity.this.stopScreenUpdateTask();
                final NetflixDialogFrag create = EpisodeListFrag.create(PlayerActivity.this.mAsset.getParentId(), PlayerActivity.this.mAsset.getPlayableId(), false);
                create.onManagerReady(PlayerActivity.this.getServiceManager(), 0);
                create.setCancelable(true);
                PlayerActivity.this.showDialog(create);
            }
        };
        this.zoomListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                PlayerActivity.this.mState.setLastActionTime(SystemClock.elapsedRealtime());
                PlayerActivity.this.mState.userInteraction();
                if (PlayerActivity.this.mIsZoomedOut) {
                    PlayerActivity.this.doZoomIn();
                }
                else {
                    PlayerActivity.this.doZoomOut();
                }
                PlayerActivity.this.mIsZoomedOut = !PlayerActivity.this.mIsZoomedOut;
            }
        };
        this.skipBackListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                PlayerActivity.this.skipBack();
            }
        };
        this.videoPositionListener = (SeekBar$OnSeekBarChangeListener)new SeekBar$OnSeekBarChangeListener() {
            private boolean skipSeek(final NetflixSeekBar netflixSeekBar) {
                if (netflixSeekBar == null) {
                    return false;
                }
                boolean b = false;
                if (netflixSeekBar.inSnapPosition()) {
                    Log.d("PlayerActivity", "Back to start position after snap, do NOT seek!");
                    b = true;
                    PlayerActivity.this.mState.timelineExitOnSnap = true;
                }
                else if (PlayerActivity.this.mState.timelineInSnapZone) {
                    Log.d("PlayerActivity", "We are in snap zone, reset progress bar to it and update labels");
                    b = true;
                    PlayerActivity.this.mState.timelineExitOnSnap = true;
                }
                Log.d("PlayerActivity", "tate.timelineExitOnSnap " + PlayerActivity.this.mState.timelineExitOnSnap);
                return b;
            }
            
            public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
                final boolean b2 = false;
                Log.d("PlayerActivity", "Progress: " + n + ", fromUser " + b);
                if (b && PlayerActivity.this.mState.draggingInProgress && PlayerActivity.this.mScreen != null && PlayerActivity.this.mPlayer != null) {
                    final boolean access$800 = PlayerActivity.this.isSeekInSnapZone(n, seekBar, PlayerActivity.this.mPlayer.getDuration());
                    if (access$800) {
                        if (PlayerActivity.this.mState.timelineInSnapZone) {
                            if (seekBar instanceof NetflixSeekBar) {
                                final NetflixSeekBar netflixSeekBar = (NetflixSeekBar)seekBar;
                                if (Log.isLoggable("PlayerActivity", 3)) {
                                    Log.d("PlayerActivity", "Snap to position was in progress " + netflixSeekBar.isSnapInProgress());
                                }
                                if (netflixSeekBar.isSnapInProgress()) {
                                    Log.d("PlayerActivity", "We were already in snap zone. Do nothing! Just return!");
                                    return;
                                }
                                Log.d("PlayerActivity", "We were already in snap zone. Update labels!");
                                PlayerActivity.this.mScreen.setProgress(n, -1, false);
                                PlayerActivity.this.mState.updatePosition(n);
                            }
                            else {
                                Log.e("PlayerActivity", "Not NETFLIX seekbar!");
                            }
                        }
                        else {
                            Log.d("PlayerActivity", "In snap zone. Snap!");
                            PlayerActivity.this.mScreen.snapToPosition(PlayerActivity.this.mState.timelineStartSeekPosition, -1);
                            PlayerActivity.this.mState.updatePosition(PlayerActivity.this.mState.timelineStartSeekPosition);
                        }
                    }
                    else {
                        Log.d("PlayerActivity", "Not in snap zone.");
                        PlayerActivity.this.mScreen.setProgress(n, -1, false);
                        PlayerActivity.this.mState.updatePosition(n);
                    }
                    PlayerActivity.this.mScreen.showBif(PlayerActivity.this.mPlayer.getBifFrame(seekBar.getProgress()));
                    Log.d("PlayerActivity", "onProgressChange showing bif");
                    final PlayScreen access$801 = PlayerActivity.this.mScreen;
                    boolean b3 = b2;
                    if (!PlayerActivity.this.mIsTablet) {
                        b3 = true;
                    }
                    access$801.moveCurrentTimeWithTimeline(b3, true);
                    PlayerActivity.this.mState.timelineInSnapZone = access$800;
                    return;
                }
                if (!b && PlayerActivity.this.mState.draggingInProgress && PlayerActivity.this.mScreen != null) {
                    Log.d("PlayerActivity", "Not from user and state.draggingInProgress is true ");
                    PlayerActivity.this.mScreen.setProgress(n, -1, false);
                    PlayerActivity.this.mState.updatePosition(n);
                }
            }
            
            public void onStartTrackingTouch(final SeekBar seekBar) {
                synchronized (this) {
                    PlayerActivity.this.mState.draggingInProgress = true;
                    int timelineStartSeekPosition;
                    if (seekBar instanceof NetflixSeekBar) {
                        timelineStartSeekPosition = ((NetflixSeekBar)seekBar).setDentVisible(true);
                    }
                    else {
                        Log.e("PlayerActivity", "Not a Netflix seekbar!");
                        timelineStartSeekPosition = seekBar.getProgress();
                    }
                    PlayerActivity.this.mState.resetTimeline();
                    PlayerActivity.this.mState.timelineStartSeekPosition = timelineStartSeekPosition;
                    PlayerActivity.this.mState.updatePosition(timelineStartSeekPosition);
                    PlayerActivity.this.mState.timelineInSnapZone = true;
                    Log.d("PlayerActivity", "Pause playback");
                    PlayerActivity.this.doPause(true);
                    Log.d("PlayerActivity", "Start seek, get awake clock");
                    PlayerActivity.this.keepScreenOn();
                    PlayerActivity.this.stopScreenUpdateTask();
                    PlayerActivity.this.mScreen.setLastTimeState(true);
                    PlayerActivity.this.mScreen.startCurrentTime(PlayerActivity.this.mPlayer.getBifFrame(timelineStartSeekPosition));
                    if (PlayerActivity.this.mScreen != null) {
                        PlayerActivity.this.mScreen.changeActionState(false, true);
                        PlayerActivity.this.mScreen.setTopPanelVisibility(false);
                    }
                }
            }
            
            public void onStopTrackingTouch(final SeekBar seekBar) {
            Label_0158_Outer:
                while (true) {
                    boolean b = true;
                    while (true) {
                    Label_0218:
                        while (true) {
                            NetflixSeekBar netflixSeekBar;
                            synchronized (this) {
                                Log.d("PlayerActivity", "onStopTrackingTouch called");
                                netflixSeekBar = null;
                                if (seekBar instanceof NetflixSeekBar) {
                                    netflixSeekBar = (NetflixSeekBar)seekBar;
                                }
                                else {
                                    Log.e("PlayerActivity", "Not a Netflix seekbar!");
                                }
                                final boolean skipSeek = this.skipSeek(netflixSeekBar);
                                if (!skipSeek) {
                                    final int access$1100 = PlayerActivity.this.toBifAjustedProgress(seekBar);
                                    Log.d("PlayerActivity", "Seek!");
                                    PlayerActivity.this.doSeek(access$1100);
                                    PlayerActivity.this.mScreen.setLastTimeState(false);
                                    if (PlayerActivity.this.mScreen != null) {
                                        PlayerActivity.this.mScreen.setTopPanelVisibility(true);
                                    }
                                    if (netflixSeekBar != null) {
                                        netflixSeekBar.setDentVisible(false);
                                    }
                                    if (Log.isLoggable("PlayerActivity", 3)) {
                                        Log.d("PlayerActivity", "Stop current time " + skipSeek);
                                    }
                                    final PlayScreen access$1101 = PlayerActivity.this.mScreen;
                                    if (!skipSeek) {
                                        access$1101.stopCurrentTime(b);
                                        PlayerActivity.this.mState.resetTimeline();
                                        return;
                                    }
                                    break Label_0218;
                                }
                            }
                            Log.d("PlayerActivity", "Do not seek!");
                            netflixSeekBar.setProgress(PlayerActivity.this.mState.timelineStartSeekPosition);
                            continue Label_0158_Outer;
                        }
                        b = false;
                        continue;
                    }
                }
            }
        };
        this.audioPositionListener = (SeekBar$OnSeekBarChangeListener)new SeekBar$OnSeekBarChangeListener() {
            public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
                if (b && PlayerActivity.this.mState.draggingAudioInProgress && PlayerActivity.this.mScreen != null) {
                    final AudioManager audioManager = (AudioManager)PlayerActivity.this.getSystemService("audio");
                    if (audioManager == null) {
                        Log.e("PlayerActivity", "Audio manager is not available, can not change volume");
                        return;
                    }
                    audioManager.setStreamVolume(3, n, 0);
                }
            }
            
            public void onStartTrackingTouch(final SeekBar seekBar) {
                PlayerActivity.this.mState.draggingAudioInProgress = true;
                Log.d("PlayerActivity", "Start volume change, get awake clock");
                PlayerActivity.this.keepScreenOn();
                PlayerActivity.this.stopScreenUpdateTask();
            }
            
            public void onStopTrackingTouch(final SeekBar seekBar) {
                Log.d("PlayerActivity", "volume::onStopTrackingTouch called");
                PlayerActivity.this.mState.draggingAudioInProgress = false;
                PlayerActivity.this.mState.audioSeekToInProgress = false;
                PlayerActivity.this.startScreenUpdateTask();
            }
        };
        this.tapListener = new TappableSurfaceView.TapListener() {
            @Override
            public void onTap(final MotionEvent motionEvent) {
                Log.d("PlayerActivity", "PA tap");
                if (PlayerActivity.this.mState.seekToInProgress || PlayerActivity.this.mState.audioSeekToInProgress) {
                    Log.d("PlayerActivity", "Seekto in progress, ignore");
                    return;
                }
                if (PlayerActivity.this.mScreen.inInterruptedOrPendingInterrupted()) {
                    Log.d("PlayerActivity", "In interrupted state, ignore");
                    return;
                }
                final boolean b = motionEvent != null;
                PlayerActivity.this.mState.setLastActionTime(SystemClock.elapsedRealtime());
                if (!PlayerActivity.this.mScreen.inPostPlayOrPendingPostplay()) {
                    PlayerActivity.this.mState.userInteraction();
                }
                PlayerActivity.this.showControlScreenOverlay(b);
            }
        };
        this.onEverySecond = new Runnable() {
            @Override
            public void run() {
                if (PlayerActivity.this.destroyed() || PlayerActivity.this.mState.draggingInProgress || PlayerActivity.this.mState.draggingAudioInProgress) {
                    Log.d("PlayerActivity", "METADATA exit");
                    return;
                }
                synchronized (PlayerActivity.this) {
                    if (PlayerActivity.this.mScreen != null && !PlayerActivity.this.mState.draggingInProgress && !PlayerActivity.this.mState.draggingAudioInProgress) {
                        if (PlayerActivity.this.mState.getLastActionTime() > 0L && SystemClock.elapsedRealtime() - PlayerActivity.this.mState.getLastActionTime() > 5000L && PlayerActivity.this.mScreen.getState() != PlayerUiState.PostPlay) {
                            Log.d("PlayerActivity", "Time to remove panel");
                            PlayerActivity.this.clearPanel();
                        }
                        PlayerActivity.this.setProgress();
                        PlayerActivity.this.updateMetadata();
                    }
                    PlayerActivity.this.repostOnEverySecondRunnable(1000);
                }
            }
        };
        this.surfaceListener = (SurfaceHolder$Callback)new SurfaceHolder$Callback() {
            public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
                if (Log.isLoggable("PlayerActivity", 3)) {
                    Log.d("PlayerActivity", "Surface changed, format: " + n + ", width: " + n2 + ", height: " + n3);
                    if (surfaceHolder != null && surfaceHolder.getSurface() != null) {
                        Log.d("PlayerActivity", "Holder: " + surfaceHolder);
                        Log.d("PlayerActivity", "Native surface: " + surfaceHolder.getSurface());
                    }
                }
            }
            
            public void surfaceCreated(final SurfaceHolder surfaceHolder) {
                synchronized (this) {
                    Log.d("PlayerActivity", "Surface created");
                    if (surfaceHolder != null && surfaceHolder.getSurface() != null && PlayerActivity.this.mScreen != null) {
                        PlayerActivity.this.mIsSurfaceReady = true;
                        PlayerActivity.this.mScreen.getSurfaceView().setVisibility(0);
                        if (Log.isLoggable("PlayerActivity", 3)) {
                            Log.d("PlayerActivity", "Native surface: " + surfaceHolder.getSurface());
                        }
                        PlayerActivity.this.completeInitIfReady();
                    }
                    else {
                        PlayerActivity.this.mIsSurfaceReady = false;
                        if (PlayerActivity.this.mAsset == null) {
                            Log.e("PlayerActivity", "surfaceCreated again, playout already set to null");
                        }
                        Log.d("PlayerActivity", "SurfaceCreated again, no playback");
                    }
                }
            }
            
            public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
                Log.d("PlayerActivity", "Surface destroyed, exit if we are not already in it");
                PlayerActivity.this.mIsSurfaceReady = false;
                if (!PlayerActivity.this.mScreen.canExitPlaybackEndOfPlay()) {
                    Log.d("PlayerActivity", "In posplay when surface is destroyed, do not exit");
                    return;
                }
                PlayerActivity.this.cleanupAndExit();
            }
        };
        this.surfaceMeasureListener = new TappableSurfaceView.SurfaceMeasureListener() {
            @Override
            public void onSurfaceMeasureChange(final int n, final int n2) {
                if (PlayerActivity.this.mSurface2 != null) {
                    PlayerActivity.this.mSurface2.setSurfaceSize(n, n2);
                }
            }
        };
        this.pauseTimeout = new Runnable() {
            @Override
            public void run() {
                Log.d("PlayerActivity", "pause has timed out, exit playback");
                PlayerActivity.this.cleanupAndExit();
            }
        };
        this.exitButtonHandler = new Runnable() {
            @Override
            public void run() {
                Log.d("PlayerActivity", "Playback canceled when not longer on WiFi");
                PlayerActivity.this.cleanupAndExit();
            }
        };
        this.mNetworkChangeReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                PlayerActivity.this.handleConnectivityCheck();
            }
        };
        this.mSocialProviderCallback = new Social.SocialProviderCallback() {
            @Override
            public void doNotShare() {
                final ServiceManager serviceManager = PlayerActivity.this.getServiceManager();
                if (serviceManager == null || !serviceManager.isReady()) {
                    Log.e("PlayerActivity", "Service manager is NOT ready. This should NOT happen!");
                }
                else if (!PlayerActivity.this.destroyed()) {
                    PlayerActivity.this.mScreen.getTopPanel().getSocial().changeActionState(false);
                    serviceManager.hideVideo(PlayerActivity.this.mAsset.getPlayableId(), new SimpleManagerCallback() {
                        @Override
                        public void onVideoHide(final int n) {
                            if (Log.isLoggable("PlayerActivity", 3)) {
                                Log.d("PlayerActivity", "Video is hidden status code " + n);
                            }
                        }
                    });
                }
            }
            
            @Override
            public void extendTimeoutTimer() {
                PlayerActivity.this.extendTimeoutTimer();
            }
        };
        this.mEpisodeRowListener = new EpisodeRowListener() {
            @Override
            public void onEpisodeSelectedForPlayback(final EpisodeDetails episodeDetails, final PlayContext playContext) {
                if (PlayerActivity.this.destroyed()) {
                    return;
                }
                if (Log.isLoggable("PlayerActivity", 3)) {
                    Log.d("PlayerActivity", "Start playback from episode selector " + episodeDetails);
                }
                if (PlayerActivity.this.mAsset != null && PlayerActivity.this.mAsset.getPlayableId() != null && PlayerActivity.this.mAsset.getPlayableId().equals(episodeDetails.getPlayableId())) {
                    Log.d("PlayerActivity", "Request to play same episode, do nothing");
                    PlayerActivity.this.startScreenUpdateTask();
                    if (PlayerActivity.this.isDialogFragmentVisible()) {
                        PlayerActivity.this.removeDialogFrag();
                    }
                    PlayerActivity.this.doUnpause();
                    return;
                }
                PlayerActivity.this.cleanupAndExit();
                PlaybackLauncher.startPlayback(PlayerActivity.this, episodeDetails, playContext);
            }
        };
        this.mDialogCancedledListener = new DialogCanceledListener() {
            @Override
            public void onDialogCanceled(final NetflixDialogFrag netflixDialogFrag) {
                if (netflixDialogFrag instanceof EpisodeListFrag && !PlayerActivity.this.destroyed()) {
                    PlayerActivity.this.mState.setLastActionTime(SystemClock.elapsedRealtime());
                    PlayerActivity.this.mState.userInteraction();
                    Log.d("PlayerActivity", "Episode selector was dismissed");
                    if (PlayerActivity.this.mPlayer.isPlaying()) {
                        PlayerActivity.this.doPause(true);
                    }
                    else {
                        PlayerActivity.this.doUnpause();
                    }
                    PlayerActivity.this.startScreenUpdateTask();
                }
            }
        };
    }
    
    private void adjustVolume(final boolean b) {
        final PlayScreen mScreen = this.mScreen;
        if (mScreen != null) {
            final AudioManager audioManager = (AudioManager)this.getSystemService("audio");
            if (audioManager != null) {
                if (!b || audioManager.getStreamVolume(3) > 0) {
                    int n;
                    if (b) {
                        n = -1;
                    }
                    else {
                        n = 1;
                    }
                    audioManager.adjustStreamVolume(3, n, 0);
                    mScreen.setAudioProgress(audioManager.getStreamVolume(3));
                    return;
                }
                Log.d("PlayerActivity", "adjustVolume at lowest");
            }
            return;
        }
        Log.w("PlayerActivity", "Unable to adjust volume, leave it to default");
    }
    
    private void clearPanel() {
        if (this.mScreen.getState() == PlayerUiState.PostPlay) {
            Log.d("PlayerActivity", "When in post play do NOT clear panel.");
            return;
        }
        this.mState.setLastActionTime(0L);
        this.mScreen.clearPanel();
    }
    
    private void completeInitIfReady() {
        if (!this.mIsSurfaceReady) {
            Log.d("PlayerActivity", "Surface not ready - cannot complete init");
        }
        else {
            if (!this.mIsAssetReady) {
                Log.d("PlayerActivity", "Asset not ready - cannot complete init");
                return;
            }
            if (this.mAsset == null) {
                Log.d("PlayerActivity", "Asset is null - cannot complete init");
                return;
            }
            Log.v("PlayerActivity", "Completing init process...");
            this.mScreen.setTitles(this.mAsset.getTitle(), this.getParentTitleForScreen(this.mAsset.getParentTitle()));
            this.mScreen.getTopPanel().getSocial().setSharingDisabled(!this.mAsset.isSocialDoNotShareVisible());
            if (!this.mState.videoLoaded && this.loadVideo()) {
                this.mState.activityState = PlayerActivityState.ACTIVITY_PLAYER_READY;
            }
        }
    }
    
    private PlayScreen.Listeners createListeners() {
        final PlayScreen.Listeners listeners = new PlayScreen.Listeners();
        listeners.videoPositionListener = this.videoPositionListener;
        listeners.playPauseListener = this.playPauseListener;
        listeners.surfaceListener = this.surfaceListener;
        listeners.tapListener = this.tapListener;
        listeners.audioPositionListener = this.audioPositionListener;
        listeners.surfaceMeasureListener = this.surfaceMeasureListener;
        listeners.skipBackListener = this.skipBackListener;
        listeners.zoomListener = this.zoomListener;
        listeners.episodeSelectorListener = this.episodesListener;
        return listeners;
    }
    
    private void doPause(final boolean b) {
        if (this.mState.activityState != PlayerActivityState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerActivity", "doPause: Invalid state, exit...:" + this.mState.activityState.getName());
            this.cleanupAndExit();
        }
        else {
            if (this.isVolumeChangeInProgress()) {
                Log.i("PlayerActivity", "doPause: volume up or down is pressed, do not pause...");
                return;
            }
            Log.i("PlayerActivity", "doPause: paused");
            if (b || (this.mPlayer.isBufferingCompleted() && this.mPlayer.isPlaying())) {
                if (this.mScreen != null && this.mScreen.getMedia() != null) {
                    this.mScreen.getMedia().setImageResource(this.mResources.play);
                }
                this.mSubtitleManager.clearPendingUpdates();
                this.mPlayer.pause();
                Log.d("PlayerActivity", "Pause, release awake clock after 2 minutes.");
                this.mHandler.postDelayed(this.allowScreenLockTimeout, 120000L);
                this.mHandler.postDelayed(this.pauseTimeout, 3600000L);
            }
        }
    }
    
    private void doSeek(final int n, final boolean b) {
        if (this.mState.activityState != PlayerActivityState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerActivity", "doSeek: Invalid state, exit...:" + this.mState.activityState.getName());
            this.cleanupAndExit();
        }
        else if (this.mScreen != null) {
            Log.w("PlayerActivity", "Playout seek...");
            if (this.mScreen != null) {
                this.mScreen.changeActionState(false, false);
            }
            this.mState.seekToInProgress = true;
            this.onSeek();
            if (Log.isLoggable("PlayerActivity", 3)) {
                Log.d("PlayerActivity", "==> seekTo: " + n);
            }
            if (this.mScreen != null && this.mScreen.getMedia() != null) {
                this.mScreen.getMedia().setImageResource(this.mResources.pause);
            }
            this.mPlayer.seekTo(n, b);
            this.mSubtitleManager.onSubtitleRemove();
        }
    }
    
    private int getDentDelta(final int n) {
        return n * 2 / 100;
    }
    
    public static void getDetailsAndPlayVideo(final Activity activity, final VideoDetails videoDetails, final PlayContext playContext) {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "Starting activity, will get details first for videoId: " + videoDetails.getId());
        }
        final Intent intent = new Intent((Context)activity, (Class)PlayerActivity.class);
        intent.addFlags(131072);
        intent.putExtra("extra_get_details_video_id", videoDetails.getId());
        intent.putExtra("extra_get_details_video_type", videoDetails.getType().getValue());
        intent.putExtra("extra_get_details_play_context", (Parcelable)playContext);
        intent.putExtra("extra_get_details_has_next_episode", videoDetails.isNextPlayableEpisode());
        activity.startActivity(intent);
    }
    
    private String getParentTitleForScreen(final String s) {
        if (this.isTablet()) {
            return s;
        }
        return null;
    }
    
    private PostPlay getPostPlaySafely() {
        final PlayScreen mScreen = this.mScreen;
        if (mScreen == null) {
            return null;
        }
        return mScreen.getPostPlay();
    }
    
    private boolean handleControlButtonPress(final int n, final KeyEvent keyEvent) {
        if (n == 96) {
            if (keyEvent.getRepeatCount() <= 0) {
                Log.v("PlayerActivity", "A button pressed");
                this.showControlScreenOverlay(false);
                this.playPauseListener.onClick((View)null);
                return true;
            }
        }
        else if (n == 21 || n == 102) {
            if (this.mState.activityState == PlayerActivityState.ACTIVITY_PLAYER_READY) {
                this.showControlScreenOverlay(false);
                this.skipBack();
                return true;
            }
        }
        else if ((n == 22 || n == 103) && this.mState.activityState == PlayerActivityState.ACTIVITY_PLAYER_READY) {
            this.showControlScreenOverlay(false);
            this.skipForward();
            return true;
        }
        return false;
    }
    
    private void initSurfaceFixedSize(final int n, final int n2) {
        if (this.mScreen.getHolder() == null) {
            return;
        }
        this.mScreen.getHolder().setFixedSize(n, n2);
    }
    
    private boolean isSeekInSnapZone(final int n, final SeekBar seekBar, int dentDelta) {
        dentDelta = this.getDentDelta(dentDelta);
        final int timelineStartSeekPosition = this.mState.timelineStartSeekPosition;
        return n >= timelineStartSeekPosition - dentDelta && n <= timelineStartSeekPosition + dentDelta;
    }
    
    private boolean isVolumeChangeInProgress() {
        return System.currentTimeMillis() - this.mState.volumeChangeInProgress < 500L;
    }
    
    private boolean isZoomEnabledByPlayerType() {
        return true;
    }
    
    private void keepScreenOn() {
        this.getWindow().addFlags(128);
        Log.d("PlayerActivity", "KEEP_SCREEN: ON");
        this.mHandler.removeCallbacks(this.pauseTimeout);
        this.mHandler.removeCallbacks(this.allowScreenLockTimeout);
    }
    
    private boolean loadVideo() {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "Do load Video " + this.hashCode());
        }
        if (this.destroyed()) {
            return false;
        }
        if (!ConnectivityUtils.isConnectedOrConnecting((Context)this)) {
            Log.d("PlayerActivity", "Raising no connectivity warning");
            this.noConnectivityWarning();
            return false;
        }
        if (!this.handleConnectivityCheck()) {
            Log.d("PlayerActivity", "Network check fails");
            return false;
        }
        final Asset mAsset = this.mAsset;
        if (mAsset == null) {
            Log.e("PlayerActivity", "asset is null, this should not happen!");
            return false;
        }
        this.mState.videoLoaded = true;
        try {
            final PlayerType currentType = PlayerTypeFactory.getCurrentType(this.getBaseContext());
            if (currentType == PlayerType.device10 || currentType == PlayerType.device11) {
                if (Log.isLoggable("PlayerActivity", 3)) {
                    Log.d("PlayerActivity", "Set JPlayerListener: " + this);
                }
                this.mPlayer.setJPlayerListener(this);
            }
            final int playbackBookmark = mAsset.getPlaybackBookmark();
            if (Log.isLoggable("PlayerActivity", 3)) {
                Log.d("PlayerActivity", this.hashCode() + "Do Play from position " + playbackBookmark);
                Log.d("PlayerActivity", this.hashCode() + "Do Play asset " + mAsset.toString());
            }
            int n;
            if ((n = playbackBookmark) < 0) {
                Log.d("PlayerActivity", this.hashCode() + "Invalid bookmark, reset to 0");
                n = 0;
            }
            this.mPlayer.setSurface(this.mScreen.getHolder().getSurface());
            this.mPlayer.setSurfaceHolder(this.mScreen.getHolder());
            Thread.sleep(30L);
            this.mPlayer.open(this.toLongSafe(mAsset.getPlayableId()), mAsset, n * 1000);
            this.notifyOthersOfPlayStart();
            return true;
        }
        catch (Throwable t) {
            Log.e("PlayerActivity", "Exception in video preparation", t);
            this.errorManager.addError(new NccpActionId(3, "", this.getString(2131493111), "handleActionId", "ACTION_ID", 0, null));
            return false;
        }
    }
    
    private void noConnectivityWarning() {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory.AlertDialogDescriptor(null, this.getString(2131493127), this.getString(17039370), this.exitButtonHandler)));
    }
    
    private void nonWifiPlayWarning() {
        ThreadUtils.assertOnMain();
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory.AlertDialogDescriptor(null, this.getString(2131493131), this.getString(17039370), this.exitButtonHandler)));
    }
    
    private void onSeek() {
        ThreadUtils.assertOnMain();
        Log.d("PlayerActivity", "onSeek");
        this.mState.stalled = true;
        this.mScreen.setBufferingOverlayVisibility(true);
    }
    
    public static void playVideo(final Activity activity, final Playable playable, final PlayContext playContext) {
        playVideo(activity, Asset.create(playable, playContext));
    }
    
    public static void playVideo(final Activity activity, final Asset asset) {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "Asset to playback: " + asset);
        }
        if (asset == null) {
            return;
        }
        activity.startActivity(toIntent(activity, asset));
    }
    
    private void releaseLockOnScreen() {
        this.getWindow().clearFlags(128);
        Log.d("PlayerActivity", "KEEP_SCREEN: OFF");
    }
    
    private void repostOnEverySecondRunnable(final int n) {
        this.mHandler.removeCallbacks(this.onEverySecond);
        this.mHandler.postDelayed(this.onEverySecond, (long)n);
    }
    
    private void setProgress() {
        if (this.mPlayer != null && !this.mState.draggingInProgress) {
            final int currentProgress = this.mPlayer.getCurrentProgress();
            final int duration = this.mPlayer.getDuration();
            if (!this.mState.draggingInProgress && this.mPlayer.canUpdatePosition(currentProgress)) {
                if (Log.isLoggable("PlayerActivity", 3)) {
                    Log.d("PlayerActivity", "PA#setProgress:: Position: " + currentProgress + ", duration: " + duration);
                }
                this.mScreen.setProgress(currentProgress, duration, true);
                return;
            }
            if (Log.isLoggable("PlayerActivity", 5)) {
                Log.w("PlayerActivity", "PA#setProgress:: Draging in progress? " + currentProgress + ", duration: " + duration);
            }
        }
    }
    
    private void setSurface(final int videoWidth, final int videoHeight, final boolean b) {
        if (this.mScreen.getSurfaceView() == null) {
            return;
        }
        this.mScreen.getSurfaceView().setVideoWidth(videoWidth);
        this.mScreen.getSurfaceView().setVideoHeight(videoHeight);
    }
    
    private void setTargetSelection() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null || !serviceManager.isReady() || serviceManager.getMdx() == null) {
            this.mScreen.getBottomPanel().setMdxTargetSelector(null);
            return;
        }
        final Pair<String, String>[] targetList = serviceManager.getMdx().getTargetList();
        if (targetList == null || targetList.length < 1) {
            this.mScreen.getBottomPanel().setMdxTargetSelector(null);
            return;
        }
        this.mScreen.getBottomPanel().setMdxTargetSelector(this.createMdxTargetSelection(targetList, serviceManager.getMdx().getCurrentTarget()));
    }
    
    private void showControlScreenOverlay(final boolean b) {
        final PlayScreen mScreen = this.mScreen;
        if (mScreen != null) {
            mScreen.onTap(b);
            return;
        }
        Log.w("PlayerActivity", "Screen is null!");
    }
    
    private void skip(int n) {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "Skip back " + n + " ms");
        }
        this.mState.setLastActionTime(SystemClock.elapsedRealtime());
        this.mState.userInteraction();
        if ((n += this.mPlayer.getCurrentProgress()) < 0) {
            Log.d("PlayerActivity", "Go back to start, instead of trying to go minus!");
            n = 0;
        }
        this.doSeek(n, true);
    }
    
    private void skipBack() {
        this.skip(-30000);
    }
    
    private void skipForward() {
        this.skip(30000);
    }
    
    private void slowNetworkWarning() {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory.AlertDialogDescriptor(null, this.getString(2131493004), this.getString(17039370), this.exitButtonHandler)));
    }
    
    private void startScreenUpdate() {
        if (this.mScreen != null && !this.destroyed()) {
            final AudioManager audioManager = (AudioManager)this.getSystemService("audio");
            if (audioManager != null) {
                audioManager.setStreamMute(3, false);
                this.mScreen.initAudioProgress(audioManager.getStreamVolume(3));
            }
            else {
                Log.e("PlayerActivity", "Audio manager not found. Unable to unmute!");
            }
            this.mState.setLastActionTime(SystemClock.elapsedRealtime());
            this.startScreenUpdateTask();
        }
    }
    
    private void stopPlayback() {
        Log.d("PlayerActivity", "stopPlayback");
        if (this.mState.activityState == PlayerActivityState.ACTIVITY_SRVCMNGR_READY || this.mState.activityState == PlayerActivityState.ACTIVITY_PLAYER_READY) {
            this.mPlayer.close();
            this.mPlayer.removePlayerListener((IPlayer.PlayerListener)this);
            this.mState.activityState = PlayerActivityState.ACTIVITY_NOTREADY;
            final ImageView media = this.mScreen.getMedia();
            if (media != null) {
                media.setEnabled(false);
            }
            if (this.mState.playStarted) {
                this.notifyOthersOfPlayStop();
            }
        }
        this.mAsset = null;
        this.mIsAssetReady = false;
        this.mReloadOnAudioTrackChange = false;
    }
    
    private int toBifAjustedProgress(final SeekBar seekBar) {
        final int progress = seekBar.getProgress();
        final int progress2 = progress / 10000 * 10000;
        if (progress2 == progress) {
            if (Log.isLoggable("PlayerActivity", 3)) {
                Log.d("PlayerActivity", "Right on target, no need to ajust seekbar position " + progress + " [ms]");
            }
            return progress2;
        }
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "Progres : " + progress + " [ms] vs. bif position " + progress2 + " [ms]");
        }
        seekBar.setProgress(progress2);
        return progress2;
    }
    
    private static Intent toIntent(final Activity activity, final Asset asset) {
        final Intent intent = new Intent((Context)activity, (Class)PlayerActivity.class);
        intent.addFlags(131072);
        intent.addFlags(268435456);
        asset.toIntent(intent);
        return intent;
    }
    
    private long toLong(final String s) throws NumberFormatException, NullPointerException {
        return Long.parseLong(s);
    }
    
    private long toLongSafe(final String s) {
        if (s != null) {
            final String trim = s.trim();
            if (!"".equals(trim) && !"null".equalsIgnoreCase(trim)) {
                try {
                    return this.toLong(s);
                }
                catch (Throwable t) {
                    return 0L;
                }
            }
        }
        return 0L;
    }
    
    private void updateMetadata() {
        if (!PreferenceUtils.getBooleanPref(this.getBaseContext(), "ui.playeroverlay", false)) {
            this.mScreen.setDebugDataVisibility(false);
        }
        else {
            this.mScreen.setDebugDataVisibility(true);
            final int n = 0;
            final int n2 = 0;
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            String string = "N/A";
            final String s = "N/A";
            int n3 = n2;
            int n4 = n;
            String s2 = string;
            String nccpCode = s;
            if (this.mPlayer != null) {
                final PlayoutMetadata playoutMetadata = this.mPlayer.getPlayoutMetadata();
                n3 = n2;
                n4 = n;
                s2 = string;
                nccpCode = s;
                if (playoutMetadata != null) {
                    final int n5 = playoutMetadata.position / 60000;
                    final int n6 = playoutMetadata.duration / 60000;
                    sb.append(playoutMetadata.instantBitRate).append("/");
                    sb.append(playoutMetadata.targetBitRate).append("/");
                    if (playoutMetadata.isSuperHD) {
                        sb.append(this.getString(2131493238));
                    }
                    else if (playoutMetadata.isHD) {
                        sb.append(this.getString(2131493102));
                    }
                    else {
                        sb.append(this.getString(2131493101));
                    }
                    sb2.append(playoutMetadata.language).append("/");
                    sb2.append(playoutMetadata.getAudioChannel()).append("/");
                    sb2.append(playoutMetadata.getAudioTrackType());
                    final SubtitleConfiguration subtitleConfiguration = this.mPlayer.getSubtitleConfiguration();
                    if (subtitleConfiguration != null) {
                        string = subtitleConfiguration.getString((Context)this);
                    }
                    Log.d("PlayerActivity", "Subtitle config: " + string);
                    final IMedia.SubtitleProfile subtitleProfileFromMetadata = this.mPlayer.getSubtitleProfileFromMetadata();
                    n3 = n6;
                    n4 = n5;
                    s2 = string;
                    nccpCode = s;
                    if (subtitleProfileFromMetadata != null) {
                        nccpCode = subtitleProfileFromMetadata.getNccpCode();
                        s2 = string;
                        n4 = n5;
                        n3 = n6;
                    }
                }
            }
            final int n7 = (int)(Debug.getNativeHeapAllocatedSize() / 1048576L);
            String s3 = "N/A";
            if (this.mSubtitleManager instanceof EnhancedSubtitleManager) {
                s3 = this.getString(2131493285);
            }
            else if (this.mSubtitleManager instanceof SimpleSubtitleManager) {
                s3 = this.getString(2131493287);
            }
            final String string2 = this.getString(2131493100, new Object[] { "Release", AndroidManifestUtils.getVersionCode(this.getBaseContext()), n7, "UI Version", n4, n3, sb.toString(), sb2.toString(), PlayerTypeFactory.getCurrentType(this.getBaseContext()).getDescription(), s2, nccpCode, s3 });
            if (this.mScreen != null) {
                this.mScreen.setDebugData(string2);
            }
        }
    }
    
    public void changeLanguage(final Language language, final boolean b) {
        ThreadUtils.assertOnMain();
        if (language != null) {
            this.setLanguage(language);
            this.mPlayer.selectTracks(language.getSelectedAudio(), language.getSelectedSubtitle());
            if (language.getSelectedSubtitle() == null) {
                Log.d("PlayerActivity", "Disable subtitles, none is selected");
                this.mSubtitleManager.clear();
            }
            language.commit();
            if (b) {
                Log.d("PlayerActivity", "Starting playback by seek with forceRebuffer to current position");
                this.mReloadOnAudioTrackChange = true;
                this.onStalled();
            }
        }
        Log.d("PlayerActivity", "Language change should be completed");
    }
    
    protected void cleanupAndExit() {
        if (this.mState.activityState == PlayerActivityState.ACTIVITY_PLAYER_READY) {
            this.stopPlayback();
        }
        this.stopScreenUpdateTask();
        this.mState.activityState = PlayerActivityState.ACTIVITY_NOTREADY;
        this.setResult(-1);
        if (!this.isFinishing()) {
            Log.d("PlayerActivity", "cleanupAndExit calling finish");
            this.finish();
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            private Boolean handleBillboard(final ServiceManager serviceManager) {
                final Intent intent = PlayerActivity.this.getIntent();
                if (!intent.hasExtra("extra_get_details_video_id")) {
                    PlayerActivity.this.mIsAssetReady = true;
                    Log.d("PlayerActivity", "Regular playback");
                    return null;
                }
                final String stringExtra = intent.getStringExtra("extra_get_details_video_id");
                final VideoType create = VideoType.create(intent.getStringExtra("extra_get_details_video_type"));
                final PlayContext playContext = (PlayContext)intent.getParcelableExtra("extra_get_details_play_context");
                if (create == VideoType.MOVIE) {
                    serviceManager.fetchMovieDetails(stringExtra, new FetchVideoDetailsForPlaybackCallback(playContext));
                    return Boolean.TRUE;
                }
                if (create == VideoType.SHOW) {
                    serviceManager.fetchShowDetails(stringExtra, null, new FetchVideoDetailsForPlaybackCallback(playContext));
                    return Boolean.FALSE;
                }
                throw new IllegalStateException("Invalid billboard video type: " + create);
            }
            
            @SuppressLint({ "NewApi" })
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                final boolean b = false;
                if (Log.isLoggable("PlayerActivity", 3)) {
                    Log.d("PlayerActivity", "ServiceManager ready: " + n);
                }
                ThreadUtils.assertOnMain();
                PlayerActivity.this.mResources = ResourceHelper.newInstance(PlayerActivity.this.mIsTablet);
                final boolean b2 = this.handleBillboard(serviceManager) != null;
                final PostPlay.PostPlayType postPlayType = PostPlay.getPostPlayType(PlayerActivity.this, b2);
                PlayerActivity.this.setContentView(PlayScreen.resolveContentView(postPlayType));
                PlayerActivity.this.mPlayer = serviceManager.getPlayer();
                PlayerActivity.this.mConfig = serviceManager.getConfiguration();
                if (PlayerActivity.this.mPlayer == null || PlayerActivity.this.mConfig == null) {
                    Log.d("PlayerActivity", "Unable to receive handle to player object, finishing activity ");
                    PlayerActivity.this.finish();
                    return;
                }
                serviceManager.cancelAllImageLoaderRequests();
                PlayerActivity.this.mPlayer.addPlayerListener((IPlayer.PlayerListener)PlayerActivity.this);
                PlayerActivity.this.mScreen = PlayScreen.createInstance(PlayerActivity.this, PlayerActivity.this.createListeners(), postPlayType);
                PlayerActivity.this.mSubtitleManager = SubtitleManagerFactory.createInstance(PlayerActivity.this.mPlayer.getSubtitleConfiguration(), PlayerActivity.this);
                if (!b2) {
                    Log.d("PlayerActivity", "Regular launch, check episode visibility");
                    final Asset currentAsset = PlayerActivity.this.getCurrentAsset();
                    final TopPanel topPanel = PlayerActivity.this.mScreen.getTopPanel();
                    boolean episodeSelectorVisibility = b;
                    if (currentAsset != null) {
                        episodeSelectorVisibility = b;
                        if (currentAsset.isEpisode()) {
                            episodeSelectorVisibility = true;
                        }
                    }
                    topPanel.setEpisodeSelectorVisibility(episodeSelectorVisibility);
                    PlayerActivity.this.mScreen.getPostPlay().init(currentAsset.getPlayableId());
                }
                if (AndroidUtils.getAndroidVersion() > 18) {
                    PlayerActivity.this.mScreen.getSurfaceView().setSecure(true);
                }
                PlayerActivity.this.setTargetSelection();
                PlayerActivity.this.errorManager = new ErrorManager(PlayerActivity.this.handler, PlayerActivity.this, PlayerActivity.this.mConfig);
                PlayerActivity.this.registerReceiverWithAutoUnregister(PlayerActivity.this.mNetworkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                if (AndroidUtils.getAndroidVersion() >= 16 && (PlayerTypeFactory.isJPlayerBase(PlayerTypeFactory.getCurrentType((Context)PlayerActivity.this)) || PlayerTypeFactory.isJPlayer(PlayerTypeFactory.getCurrentType((Context)PlayerActivity.this)))) {
                    PlayerActivity.this.mSurface2 = new SecondSurface((TextureView)PlayerActivity.this.findViewById(2131165493));
                }
                PlayerActivity.this.mState.activityState = PlayerActivityState.ACTIVITY_SRVCMNGR_READY;
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
                Log.e("PlayerActivity", "NetflixService is NOT available!");
                PlayerActivity.this.cleanupAndExit();
            }
        };
    }
    
    MdxTargetSelection createMdxTargetSelection(final Pair<String, String>[] array, final String s) {
        return new MdxTargetSelection(array, s);
    }
    
    @Override
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        boolean dispatchKeyEvent = true;
        if (keyEvent.getAction() == 0) {
            switch (keyEvent.getKeyCode()) {
                case 20:
                case 25: {
                    if (this.mScreen != null) {
                        this.mScreen.setSoundBarVisibility(true);
                    }
                    this.adjustVolume(true);
                    return true;
                }
                case 19:
                case 24: {
                    if (this.mScreen != null) {
                        this.mScreen.setSoundBarVisibility(true);
                    }
                    this.adjustVolume(false);
                    return true;
                }
            }
        }
        if (keyEvent.getAction() == 1) {
            switch (keyEvent.getKeyCode()) {
                case 20:
                case 25: {
                    Log.d("PlayerActivity", "dispatched key action up on volume down");
                    if (this.mScreen != null) {
                        this.mScreen.setSoundBarVisibility(false);
                        return true;
                    }
                    return dispatchKeyEvent;
                }
                case 19:
                case 24: {
                    Log.d("PlayerActivity", "dispatched key action up on volume up");
                    if (this.mScreen != null) {
                        this.mScreen.setSoundBarVisibility(false);
                        return true;
                    }
                    return dispatchKeyEvent;
                }
            }
        }
        dispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
        return dispatchKeyEvent;
    }
    
    public void doPause() {
        this.doPause(false);
    }
    
    public void doPlaying() {
        ThreadUtils.assertOnMain();
        if (this.mState.stalled) {
            Log.d("PlayerActivity", "Dismissing buffering progress bar...");
            this.mState.seekToInProgress = false;
            this.mState.audioSeekToInProgress = false;
            this.mState.stalled = false;
            this.keepScreenOn();
            if (this.mScreen != null) {
                this.mScreen.changeActionState(true, false);
                this.mScreen.setBufferingOverlayVisibility(false);
                Log.d("PlayerActivity", "Remove bif image if it was visible. Only for phones!!!");
                if (!this.mIsTablet) {
                    this.mScreen.stopBif();
                }
                if (this.mIsTablet && this.mScreen.getState() == PlayerUiState.PlayingWithTrickPlayOverlay) {
                    Log.d("PlayerActivity", "onPlaying:: screen tapped: moveCurrentTimeWithTimeline(true, true)");
                    this.mScreen.moveCurrentTimeWithTimeline(true, true);
                }
            }
            this.startScreenUpdate();
        }
        if (!this.hasWindowFocus() && !this.destroyed()) {
            Log.d("PlayerActivity", "App is not in focus, pause");
            this.doPause();
        }
    }
    
    public void doSeek(final int n) {
        this.doSeek(n, false);
    }
    
    public void doUnpause() {
        if (this.mState.activityState != PlayerActivityState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerActivity", "doUnPause: Invalid state, exit...:" + this.mState.activityState.getName());
            this.cleanupAndExit();
        }
        else {
            Log.i("PlayerActivity", "doPlay: resume");
            if (!this.mPlayer.isPlaying()) {
                if (this.mScreen != null && this.mScreen.getMedia() != null) {
                    this.mScreen.getMedia().setImageResource(this.mResources.pause);
                }
                this.keepScreenOn();
                this.mPlayer.unpause();
            }
        }
    }
    
    public void doZoomIn() {
        if (this.mState.activityState != PlayerActivityState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerActivity", "doZoomIn: Invalid state, exit...:" + this.mState.activityState.getName());
            this.cleanupAndExit();
        }
        else {
            Log.i("PlayerActivity", "doZoomIn: start");
            final PlayScreen mScreen = this.mScreen;
            if (mScreen != null) {
                mScreen.setZoom(false);
                if (mScreen.getZoom() != null) {
                    mScreen.getZoom().setImageResource(this.mResources.zoomOut);
                }
            }
        }
    }
    
    public void doZoomOut() {
        if (this.mState.activityState != PlayerActivityState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerActivity", "doZoomIn: Invalid state, exit...:" + this.mState.activityState.getName());
            this.cleanupAndExit();
        }
        else {
            Log.d("PlayerActivity", "doZoomOut: start");
            final PlayScreen mScreen = this.mScreen;
            if (mScreen != null) {
                mScreen.setZoom(true);
                if (mScreen.getZoom() != null) {
                    mScreen.getZoom().setImageResource(this.mResources.zoomIn);
                }
            }
        }
    }
    
    public void extendTimeoutTimer() {
        this.mState.setLastActionTime(SystemClock.elapsedRealtime());
        this.mState.userInteraction();
    }
    
    public Asset getCurrentAsset() {
        return this.mAsset;
    }
    
    public String getCurrentTitle() {
        final PlayScreen screen = this.getScreen();
        if (screen == null) {
            return null;
        }
        return screen.getCurrentTitle();
    }
    
    @Override
    protected DataContext getDataContext() {
        DataContext dataContext = null;
        if (this.mAsset != null) {
            dataContext = new DataContext();
            dataContext.setRow(this.mAsset.getListPos());
            dataContext.setRank(this.mAsset.getVideoPos());
            dataContext.setRequestId(this.mAsset.getRequestId());
            dataContext.setTrackId(this.mAsset.getTrackId());
        }
        return dataContext;
    }
    
    public DialogCanceledListener getDialogCanceledListener() {
        return this.mDialogCancedledListener;
    }
    
    @Override
    public EpisodeRowListener getEpisodeRowListener() {
        return this.mEpisodeRowListener;
    }
    
    ErrorManager getErrorManager() {
        return this.errorManager;
    }
    
    Handler getHandler() {
        return this.mHandler;
    }
    
    public Language getLanguage() {
        return this.language;
    }
    
    public IPlayer getPlayer() {
        return this.mPlayer;
    }
    
    public PlayScreen getScreen() {
        return this.mScreen;
    }
    
    public Surface getSecondSurface() {
        if (this.mSurface2 != null) {
            return this.mSurface2.getSurface();
        }
        return null;
    }
    
    Social.SocialProviderCallback getSocialProviderCallback() {
        return this.mSocialProviderCallback;
    }
    
    protected PlayerWorkflowState getState() {
        return this.mState;
    }
    
    SubtitleManager getSubtitleManager() {
        return this.mSubtitleManager;
    }
    
    public SecondSurface getSurface2() {
        return this.mSurface2;
    }
    
    public ResourceHelper getUiResources() {
        return this.mResources;
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.playback;
    }
    
    @Override
    protected boolean handleBackPressed() {
        if (this.mScreen != null && this.mScreen.inInterruptedOrPendingInterrupted() && this.mScreen.getPostPlay() != null) {
            this.mScreen.getPostPlay().moveFromInterruptedToPlaying();
            return true;
        }
        return false;
    }
    
    boolean handleConnectivityCheck() {
        Log.i("PlayerActivity", "Check connection");
        final LogMobileType connectionType = ConnectivityUtils.getConnectionType((Context)this);
        if (connectionType == null) {
            Log.i("PlayerActivity", "No internet connection. Since this is expected state on Verizons' phones, skip");
            return true;
        }
        if (connectionType == LogMobileType._2G) {
            Log.i("PlayerActivity", "2G only, alert");
            this.slowNetworkWarning();
            return false;
        }
        if (connectionType == LogMobileType.WIFI) {
            Log.i("PlayerActivity", "WiFi connection, do playback");
            return true;
        }
        final boolean booleanPref = PreferenceUtils.getBooleanPref((Context)this, "nf_play_no_wifi_warning", false);
        Log.i("PlayerActivity", "3G Preference setting: " + booleanPref);
        if (booleanPref) {
            Log.w("PlayerActivity", "We should warn user if he is on NON WIFI network!");
            this.nonWifiPlayWarning();
            return false;
        }
        Log.d("PlayerActivity", "Warning is not required, proceed with playback");
        return true;
    }
    
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
        Log.d("PlayerActivity", "Check if MDX status is changed");
        if (this.mScreen == null) {
            return;
        }
        this.setTargetSelection();
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    public boolean isPaused() {
        return this.mPlayer != null && !this.mPlayer.isPlaying();
    }
    
    public boolean isSeeking() {
        return this.mPlayer != null && this.mState.seekToInProgress;
    }
    
    public boolean isStalled() {
        return this.mState.stalled;
    }
    
    public void notifyOthersOfPlayStart() {
        this.sendBroadcast(this.mAsset.toIntent(new Intent("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START")));
        Log.v("PlayerActivity", "Intent PLAYER_PLAY_START sent");
    }
    
    public void notifyOthersOfPlayStop() {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP");
        this.mAsset.setPlaybackBookmark(this.mPlayer.getCurrentPositionMs() / 1000);
        this.sendBroadcast(this.mAsset.toIntent(intent));
        Log.v("PlayerActivity", "Intent PLAYER_PLAY_STOP sent");
    }
    
    public void onAudioChange(final int n) {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "onAudioChange" + n);
        }
        if (this.mReloadOnAudioTrackChange) {
            Log.d("PlayerActivity", "Starting playback by seek with forceRebuffer to current position");
            this.mReloadOnAudioTrackChange = false;
            this.doSeek(this.mPlayer.getCurrentPositionMs(), true);
        }
    }
    
    public void onAudioFocusChange(final int n) {
        String string = null;
        switch (n) {
            default: {
                string = "uknowwn " + n;
                break;
            }
            case 1: {
                string = "AUDIOFOCUS_GAIN";
                break;
            }
            case 2: {
                string = "AUDIOFOCUS_GAIN_TRANSIENT";
                break;
            }
            case 3: {
                string = "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK";
                break;
            }
            case -1: {
                string = "AUDIOFOCUS_LOSS";
                break;
            }
            case -2: {
                string = "AUDIOFOCUS_LOSS_TRANSIENT";
                break;
            }
            case -3: {
                string = "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK";
                break;
            }
        }
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "onAudioFocusChange " + string);
        }
    }
    
    public void onBandwidthChange(final int n) {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "bandwidth changed to [Kbps]: " + n);
        }
    }
    
    public void onBufferingUpdate(final int n) {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "MP onBufferingUpdate " + n + "%");
        }
    }
    
    public void onCompletion() {
        Log.d("PlayerActivity", "onCompletion, check if we are in postplay");
        this.stopScreenUpdateTask();
        if (this.mScreen != null && this.mScreen.canExitPlaybackEndOfPlay()) {
            this.cleanupAndExit();
            return;
        }
        Log.d("PlayerActivity", "In PostPlay, allow screen to lock after timeout...");
        this.mHandler.postDelayed(this.allowScreenLockTimeout, 120000L);
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.hardKeyboardHidden == 1) {
            Log.d("PlayerActivity", "keyboard out");
        }
        else if (configuration.hardKeyboardHidden == 2) {
            Log.d("PlayerActivity", "keyboard in");
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "onCreate started " + this.hashCode());
        }
        this.setRequestedOrientation(6);
        AndroidUtils.logDeviceDensity(this);
        this.keepScreenOn();
        this.getWindow().getAttributes().buttonBrightness = 0.0f;
        this.mState.reset();
        LogUtils.reportPlayActionStarted((Context)this, null, this.getUiScreen());
        this.mState.playStartInProgress.set(true);
        LogUtils.pauseReporting((Context)this);
        Log.d("PlayerActivity", "onCreate done");
    }
    
    @Override
    protected void onDestroy() {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "====> Destroying PlayActivity " + this.hashCode());
        }
        this.getWindow().getAttributes().buttonBrightness = -1.0f;
        this.releaseLockOnScreen();
        this.mHandler.removeCallbacks(this.pauseTimeout);
        this.mHandler.removeCallbacks(this.allowScreenLockTimeout);
        if (this.mScreen != null) {
            this.mScreen.destroy();
        }
        if (this.errorManager != null) {
            this.errorManager.destroy();
        }
        if (this.mSubtitleManager != null) {
            this.mSubtitleManager.clear();
        }
        if (this.mSurface2 != null) {
            this.mSurface2 = null;
        }
        LogUtils.resumeReporting((Context)this, false);
        super.onDestroy();
        Log.d("PlayerActivity", "====> Destroying PlayActivity done");
    }
    
    public Surface onGetTextureSurface() {
        return this.mSurface2.getSurface();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        final boolean b = true;
        if (Log.isLoggable("PlayerActivity", 2)) {
            Log.v("PlayerActivity", "onKeyDown: " + keyEvent);
        }
        this.mState.setLastActionTime(SystemClock.elapsedRealtime());
        this.mState.userInteraction();
        boolean onKeyDown;
        if (n == 4) {
            if (this.mScreen == null || !this.mScreen.inInterruptedOrPendingInterrupted()) {
                Log.d("PlayerActivity", "Back...");
                this.cleanupAndExit();
                return true;
            }
            Log.d("PlayerActivity", "Back used to dismiss interrupter overlay, send it back to framework");
            onKeyDown = super.onKeyDown(n, keyEvent);
        }
        else {
            onKeyDown = b;
            if (n != 84) {
                if (n == 82) {
                    keyEvent.startTracking();
                    return true;
                }
                if (n == 25 || n == 24 || n == 20 || n == 19) {
                    Log.d("PlayerActivity", "Volume key is pressed");
                    this.mState.volumeChangeInProgress = System.currentTimeMillis();
                    return super.onKeyDown(n, keyEvent);
                }
                onKeyDown = b;
                if (!this.handleControlButtonPress(n, keyEvent)) {
                    return super.onKeyDown(n, keyEvent);
                }
            }
        }
        return onKeyDown;
    }
    
    public boolean onKeyLongPress(final int n, final KeyEvent keyEvent) {
        switch (n) {
            default: {
                return super.onKeyLongPress(n, keyEvent);
            }
            case 82: {
                return true;
            }
        }
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        if (n == 82) {
            this.openOptionsMenu();
            return true;
        }
        if (n == 25 || n == 24 || n == 20 || n == 19) {
            Log.d("PlayerActivity", "Volume key is released");
            return super.onKeyUp(n, keyEvent);
        }
        return super.onKeyUp(n, keyEvent);
    }
    
    public void onMediaError(final Error error) {
        if (Log.isLoggable("PlayerActivity", 6)) {
            Log.e("PlayerActivity", "Media Error " + error);
        }
        this.errorManager.addMediaError(error);
    }
    
    public void onNccpError(final NccpError nccpError) {
        if (Log.isLoggable("PlayerActivity", 6)) {
            Log.e("PlayerActivity", "Nccp Error " + nccpError);
        }
        this.errorManager.addError(nccpError);
    }
    
    public void onNrdFatalError() {
        Log.w("PlayerActivity", "onNrdFatalError");
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.mHandler, new AlertDialogFactory.AlertDialogDescriptor("", this.getString(2131493144), null, new Runnable() {
            @Override
            public void run() {
                Log.d("PlayerActivity", "===fatal error, shutdown===");
                final int myPid = Process.myPid();
                Log.d("PlayerActivity", "Destroying app proces " + myPid + "...");
                Process.killProcess(myPid);
                Log.d("PlayerActivity", "Destroying app proces " + myPid + " done.");
            }
        })));
    }
    
    @Override
    protected void onPause() {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "onPause called..." + this.hashCode());
        }
        final PowerManager powerManager = (PowerManager)this.getSystemService("power");
        if (powerManager != null && !powerManager.isScreenOn()) {
            Log.d("PlayerActivity", "Screen is off, go back to UI ");
            this.cleanupAndExit();
        }
        else if (this.mState.activityState == PlayerActivityState.ACTIVITY_PLAYER_READY) {
            Log.d("PlayerActivity", "Screen is on, just pause");
            this.doPause();
        }
        final PostPlay postPlaySafely = this.getPostPlaySafely();
        if (postPlaySafely != null) {
            postPlaySafely.onPause();
        }
        Log.d("PlayerActivity", "onPause called done");
        super.onPause();
    }
    
    public void onPlaying() {
        synchronized (this) {
            Log.d("PlayerActivity", "Playout (re)started");
            if (this.mState.activityState == PlayerActivityState.ACTIVITY_PLAYER_READY) {
                this.doPlaying();
            }
            else {
                Log.e("PlayerActivity", "onPlaying not in correct state, destroyed:" + this.destroyed() + " ActivityState: " + this.mState.activityState.getName());
                this.cleanupAndExit();
            }
        }
    }
    
    public void onPrepared() {
        Log.d("PlayerActivity", "onPrepared called");
        ThreadUtils.assertOnMain();
        if (this.mState.activityState == PlayerActivityState.ACTIVITY_PLAYER_READY) {
            try {
                this.mState.videoPrepared = true;
                this.mWidth = this.mPlayer.getVideoWidth();
                this.mHeight = this.mPlayer.getVideoHeight();
                if (this.mWidth != 0 && this.mHeight != 0 && this.mScreen != null) {
                    if (Log.isLoggable("PlayerActivity", 3)) {
                        Log.d("PlayerActivity", "====> width = " + this.mWidth + ", height" + this.mHeight);
                    }
                    this.setSurface(this.mWidth, this.mHeight, true);
                    this.initSurfaceFixedSize(this.mWidth, this.mHeight);
                    Log.d("PlayerActivity", "Play");
                    this.mPlayer.play();
                    final int currentProgress = this.mPlayer.getCurrentProgress();
                    final int duration = this.mPlayer.getDuration();
                    if (Log.isLoggable("PlayerActivity", 3)) {
                        Log.d("PlayerActivity", "========> Duration = " + duration);
                    }
                    this.mScreen.initProgress(duration);
                    if (Log.isLoggable("PlayerActivity", 3)) {
                        Log.d("PlayerActivity", "Position: " + currentProgress + ", duration: " + duration);
                    }
                    this.mScreen.setProgress(currentProgress, duration, true, true);
                    this.mScreen.setZoomEnabledByPlayertype(this.isZoomEnabledByPlayerType());
                }
                this.selectInitialTracks();
                return;
            }
            catch (Exception ex) {
                Log.e("PlayerActivity", "Failed to start player", ex);
                this.cleanupAndExit();
                return;
            }
        }
        Log.e("PlayerActivity", "onPrepared not in correct state, destroyed:" + this.destroyed() + " ActivityState: " + this.mState.activityState.getName());
        this.cleanupAndExit();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "onResume: back " + this.hashCode());
        }
        final PostPlay postPlaySafely = this.getPostPlaySafely();
        if (postPlaySafely != null) {
            postPlaySafely.onResume();
        }
    }
    
    public boolean onSearchRequested() {
        return false;
    }
    
    public void onSeekComplete() {
        Log.d("PlayerActivity", "MP onSeekComplete");
        if (this.mState.activityState == PlayerActivityState.ACTIVITY_PLAYER_READY) {
            this.mState.draggingInProgress = false;
            this.startScreenUpdateTask();
            this.doPlaying();
            return;
        }
        Log.e("PlayerActivity", "onSeekComplete not in correct state, destroyed:" + this.destroyed() + " ActivityState: " + this.mState.activityState.getName());
        this.cleanupAndExit();
    }
    
    public void onStalled() {
        synchronized (this) {
            Log.w("PlayerActivity", "Playout stalled");
            ThreadUtils.assertOnMain();
            if (this.mScreen != null && !this.destroyed()) {
                Log.d("PlayerActivity", "Playout stalled, clear pending updates");
                this.mSubtitleManager.clearPendingUpdates();
                if (this.mState.seekToInProgress || this.mState.audioSeekToInProgress) {
                    Log.d("PlayerActivity", "Seek in progress...");
                }
                else {
                    this.mState.stalled = true;
                    if (this.mScreen != null) {
                        this.mScreen.changeActionState(false, false);
                    }
                    if (this.mScreen != null && this.mScreen.getMedia() != null) {
                        this.mScreen.getMedia().setImageResource(this.mResources.pause);
                    }
                    if (this.mState.lowBandwidth) {
                        Log.d("PlayerActivity", "Enabled Toast");
                        Toast.makeText(this.getBaseContext(), 2131493112, 1).show();
                    }
                    this.mScreen.setBufferingOverlayVisibility(true);
                }
            }
        }
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "onStart " + this.hashCode());
        }
        final AudioManager audioManager = (AudioManager)this.getSystemService("audio");
        if (audioManager != null) {
            audioManager.requestAudioFocus((AudioManager$OnAudioFocusChangeListener)this, 3, 1);
        }
        else {
            Log.e("PlayerActivity", "Audio manager not found. Unable to ask for audio focus!");
        }
        final Intent intent = this.getIntent();
        if (intent == null) {
            Log.e("PlayerActivity", "This should NEVER happen, intent is null!");
            this.setResult(0);
            this.finish();
        }
        else {
            final Parcelable parcelableExtra = intent.getParcelableExtra("PAYLOAD");
            if (parcelableExtra != null && !(parcelableExtra instanceof Asset)) {
                Log.e("PlayerActivity", "This should NEVER happen, payload is not Asset!");
                this.setResult(0);
                this.finish();
                return;
            }
            Asset fromIntent;
            if (parcelableExtra != null && parcelableExtra instanceof Asset) {
                Log.d("PlayerActivity", "Payload is Asset...");
                fromIntent = (Asset)parcelableExtra;
            }
            else {
                Log.d("PlayerActivity", "Payload is in multiple properties, extract it...");
                fromIntent = Asset.fromIntent(intent);
            }
            if (fromIntent == null) {
                Log.e("PlayerActivity", "This should NEVER happen, asset is null!");
                this.setResult(0);
                this.finish();
            }
            else if (Log.isLoggable("PlayerActivity", 3)) {
                Log.d("PlayerActivity", "Asset received: " + fromIntent.toString());
            }
            this.mAsset = fromIntent;
            Log.d("PlayerActivity", "onStart done");
            if (Log.isLoggable("PlayerActivity", 2)) {
                final StringBuilder append = new StringBuilder().append("TRACK_ID: ");
                Serializable value;
                if (this.mAsset == null) {
                    value = "n/a";
                }
                else {
                    value = this.mAsset.getTrackId();
                }
                Log.v("PlayerActivity", append.append(value).toString());
            }
        }
    }
    
    public void onStarted() {
        synchronized (this) {
            Log.d("PlayerActivity", "Playout started");
            ThreadUtils.assertOnMain();
            if (this.mState.activityState == PlayerActivityState.ACTIVITY_PLAYER_READY) {
                this.mState.playStarted = true;
                this.mScreen.removeSplashScreen();
                this.startScreenUpdate();
                LogUtils.reportPlayActionEnded((Context)this, IClientLogging.CompletionReason.success, null, null);
                this.mState.playStartInProgress.set(false);
            }
            else {
                Log.e("PlayerActivity", "onStarted not in correct state, destroyed:" + this.destroyed() + " ActivityState: " + this.mState.activityState.getName());
                LogUtils.reportPlayActionEnded((Context)this, IClientLogging.CompletionReason.failed, new UIError(RootCause.clientFailure, ActionOnUIError.handledSilently, null, null), null);
                this.mState.playStartInProgress.set(false);
                this.cleanupAndExit();
            }
        }
    }
    
    @Override
    protected void onStop() {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "PlayerActivity::onStop called " + this.hashCode());
        }
        super.onStop();
        if (this.mState.playStartInProgress.getAndSet(false)) {
            Log.d("PlayerActivity", "Start play is in progress and user canceled playback");
            LogUtils.reportPlayActionEnded((Context)this, IClientLogging.CompletionReason.canceled, null, null);
        }
        final String mMaxStreamsReachedDialogId = this.mMaxStreamsReachedDialogId;
        if (mMaxStreamsReachedDialogId != null) {
            Log.d("PlayerActivity", "Report max stream reach dialog ended");
            this.reportUiModelessViewSessionEnded(IClientLogging.ModalView.maxStreamsReached, mMaxStreamsReachedDialogId);
        }
        final String mErrorDialogId = this.mErrorDialogId;
        if (mErrorDialogId != null) {
            Log.d("PlayerActivity", "Report end of error dialog ended");
            this.reportUiModelessViewSessionEnded(IClientLogging.ModalView.errorDialog, mErrorDialogId);
        }
        final AudioManager audioManager = (AudioManager)this.getSystemService("audio");
        if (audioManager != null) {
            audioManager.abandonAudioFocus((AudioManager$OnAudioFocusChangeListener)this);
        }
        else {
            Log.e("PlayerActivity", "Audio manager not found. Unable to abandon audio focus!");
        }
        Log.d("PlayerActivity", "Regular exit from player");
        this.cleanupAndExit();
        Log.d("PlayerActivity", "PlayerActivity::onStop done");
    }
    
    public void onSubtitleChange(final SubtitleScreen subtitleScreen) {
        if (this.destroyed()) {
            return;
        }
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "Update subtitles " + subtitleScreen);
        }
        this.mSubtitleManager.onSubtitleChange(subtitleScreen);
    }
    
    public void onSubtitleFailed() {
        if (this.destroyed()) {
            return;
        }
        Log.d("PlayerActivity", "We failed to change subtitle");
        Toast.makeText(this.getBaseContext(), 2131493288, 1).show();
        if (this.language != null) {
            Log.d("PlayerActivity", "Try to restore previous subtitle");
            this.language.restorePreviousSubtitle();
            return;
        }
        Log.w("PlayerActivity", "Unable to restore previous subtitle, lang is null. This should NOT happen!");
    }
    
    public void onSubtitleRemove() {
        ThreadUtils.assertOnMain();
        Log.d("PlayerActivity", "onSubtitleRemove");
        this.mSubtitleManager.onSubtitleRemove();
    }
    
    public void onSubtitleShow(final String s) {
        ThreadUtils.assertOnMain();
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "setSubtitle: " + s);
        }
        this.mSubtitleManager.onSubtitleShow(s);
    }
    
    public void onSurface2Visibility(final boolean b) {
        if (Log.isLoggable("PlayerActivity", 3)) {
            final StringBuilder append = new StringBuilder().append("set surface2 ");
            String s;
            if (b) {
                s = "visible";
            }
            else {
                s = "invisible";
            }
            Log.d("PlayerActivity", append.append(s).toString());
        }
        if (b) {
            this.runInUiThread(new Runnable() {
                @Override
                public void run() {
                    PlayerActivity.this.mSurface2.setSurfaceVisible();
                }
            });
            return;
        }
        this.runInUiThread(new Runnable() {
            @Override
            public void run() {
                PlayerActivity.this.mSurface2.setSurfaceInvisible();
            }
        });
    }
    
    public void onUpdatePts(final int n) {
        this.mScreen.getPostPlay().updatePlaybackPosition(n);
    }
    
    protected void onUserLeaveHint() {
        Log.d("PlayerActivity", "onUserLeaveHint ");
    }
    
    public void onVideoSizeChanged(final int mWidth, final int mHeight) {
        if (mWidth == 0 || mHeight == 0) {
            Log.e("PlayerActivity", "invalid aspect ratio width(" + mWidth + ") or aspect ratio height(" + mHeight + ")");
            return;
        }
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "MP onVideoSizeChanged: aspect ratio width " + mWidth + ", aspect ratio height " + mHeight);
        }
        this.setSurface(this.mWidth = mWidth, this.mHeight = mHeight, false);
    }
    
    public void onWindowFocusChanged(final boolean b) {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "====> In focus: " + b);
        }
        if (b || this.destroyed()) {
            return;
        }
        final PlayScreen mScreen = this.mScreen;
        if (mScreen == null || PlayerUiState.Loading == mScreen.getState()) {
            Log.d("PlayerActivity", "UI is not in focus on splash screen. Do NOT pause, ignore.");
            return;
        }
        Log.d("PlayerActivity", "Alert from some other activity is in front of us. Pause.");
        this.runInUiThread(new Runnable() {
            @Override
            public void run() {
                PlayerActivity.this.doPause();
                Log.d("PlayerActivity", "onWindowFocusChanged done");
            }
        });
    }
    
    public void playNextVideo(final Playable playable, final PlayContext playContext, final boolean b) {
        if (this.destroyed()) {
            Log.d("PlayerActivity", "Activity already destroyed, ignore next!");
            return;
        }
        int n2;
        final int n = n2 = 0;
        if (this.mAsset != null) {
            n2 = n;
            if (b) {
                n2 = n;
                if (this.mState.noUserInteraction()) {
                    n2 = this.mAsset.getPostPlayVideoPlayed() + 1;
                }
            }
        }
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "Play next video, count " + n2 + ", from auto play " + b + ", no user interaction " + this.mState.noUserInteraction());
        }
        this.cleanupAndExit();
        final Asset forPostPlay = Asset.createForPostPlay(playable, playContext, n2);
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "Asset to play next: " + forPostPlay);
        }
        this.startActivity(toIntent(this, forPostPlay));
    }
    
    public void playbackClosed() {
        Log.d("PlayerActivity", "playbackClosed");
        if (this.mRestartPlayback) {
            Log.d("PlayerActivity", "Reloading Video to start playback");
            this.loadVideo();
            this.mRestartPlayback = false;
        }
    }
    
    public void restartPlayback(final NccpError nccpError) {
        Log.e("PlayerActivity", "Restarting playback");
        ++this.mActionId12Count;
        if (this.mActionId12Count > 1) {
            this.errorManager.addError(nccpError);
            return;
        }
        this.mRestartPlayback = true;
        this.mPlayer.close();
    }
    
    public void restorePlaybackAfterSnap() {
        Log.d("PlayerActivity", "restorePlaybackAfterSnap.");
        this.keepScreenOn();
        if (this.mScreen != null && !this.destroyed()) {
            final AudioManager audioManager = (AudioManager)this.getSystemService("audio");
            if (audioManager != null) {
                audioManager.setStreamMute(3, false);
                this.mScreen.initAudioProgress(audioManager.getStreamVolume(3));
            }
            else {
                Log.e("PlayerActivity", "Audio manager not found. Unable to unmute!");
            }
            this.mState.setLastActionTime(SystemClock.elapsedRealtime());
            this.mState.userInteraction();
            this.repostOnEverySecondRunnable(0);
            this.startScreenUpdateTask();
        }
        if (this.mScreen != null) {
            this.mScreen.changeActionState(true, true);
        }
        this.doUnpause();
    }
    
    public void selectInitialTracks() {
        final Subtitle[] subtitleTrackList = this.mPlayer.getSubtitleTrackList();
        final AudioSource[] audioTrackList = this.mPlayer.getAudioTrackList();
        final AudioSubtitleDefaultOrderInfo[] audioSubtitleDefaultOrderInfo = this.mPlayer.getAudioSubtitleDefaultOrderInfo();
        Log.d("PlayerActivity", "Create localization manager");
        final LocalizationManager localizationManager = new LocalizationManager(subtitleTrackList, audioTrackList, audioSubtitleDefaultOrderInfo, null);
        boolean b = false;
        final LanguageChoice initialLanguage = localizationManager.findInitialLanguage();
        final AudioSource audio = initialLanguage.getAudio();
        int nccpOrderNumber = -1;
        if (audio != null) {
            if (Log.isLoggable("PlayerActivity", 3)) {
                Log.d("PlayerActivity", "Changing initial audio to " + audio);
            }
            nccpOrderNumber = audio.getNccpOrderNumber();
        }
        else {
            Log.d("PlayerActivity", "No need to set initial audio source");
        }
        final Subtitle subtitle = initialLanguage.getSubtitle();
        int nccpOrderNumber2 = -1;
        if (subtitle != null) {
            if (Log.isLoggable("PlayerActivity", 3)) {
                Log.d("PlayerActivity", "Changing initial subtitle to " + subtitle);
            }
            nccpOrderNumber2 = subtitle.getNccpOrderNumber();
            b = true;
        }
        else {
            Log.d("PlayerActivity", "No need to set initial subtitle");
        }
        this.mPlayer.selectTracks(audio, subtitle);
        this.setLanguage(new Language(audioTrackList, nccpOrderNumber, subtitleTrackList, nccpOrderNumber2, b));
    }
    
    void setErrorDialogId(final String mErrorDialogId) {
        this.mErrorDialogId = mErrorDialogId;
    }
    
    public void setLanguage(final Language language) {
        if (language == null) {
            Log.w("PlayerActivity", "Language is null!");
            return;
        }
        Log.d("PlayerActivity", "Sets language");
        this.language = language;
    }
    
    void setMaxStreamsReachedDialogId(final String mMaxStreamsReachedDialogId) {
        this.mMaxStreamsReachedDialogId = mMaxStreamsReachedDialogId;
    }
    
    public boolean showMdxInMenu() {
        return true;
    }
    
    @Override
    protected boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSignOutInMenu() {
        return false;
    }
    
    void startScreenUpdateTask() {
        this.repostOnEverySecondRunnable(1000);
    }
    
    void stopScreenUpdateTask() {
        this.mHandler.removeCallbacks(this.onEverySecond);
        Log.d("PlayerActivity", "===>> Screen update thread canceled");
    }
    
    private class FetchVideoDetailsForPlaybackCallback extends LoggingManagerCallback
    {
        private final PlayContext playContext;
        
        public FetchVideoDetailsForPlaybackCallback(final PlayContext playContext) {
            super("PlayerActivity");
            this.playContext = playContext;
        }
        
        private void handleResponse(final VideoDetails videoDetails, final int n) {
            PlayerActivity.this.mIsAssetReady = false;
            final PlayerActivity this$0 = PlayerActivity.this;
            if (this$0.destroyed()) {
                return;
            }
            if (n != 0 || videoDetails == null) {
                Log.w("PlayerActivity", "Error loading video details for video playback");
                Toast.makeText((Context)this$0, 2131493111, 1).show();
                return;
            }
            PlayerActivity.this.mAsset = Asset.create(videoDetails, this.playContext);
            PlayerActivity.this.mIsAssetReady = true;
            PlayerActivity.this.completeInitIfReady();
            Log.d("PlayerActivity", "Bilboard playback");
            PlayerActivity.this.mScreen.getPostPlay().init(PlayerActivity.this.mAsset.getPlayableId());
            Log.d("PlayerActivity", "Bilboard playback, check episode visibility");
            PlayerActivity.this.mScreen.getTopPanel().setEpisodeSelectorVisibility(PlayerActivity.this.mAsset.isEpisode());
        }
        
        @Override
        public void onMovieDetailsFetched(final MovieDetails movieDetails, final int n) {
            super.onMovieDetailsFetched(movieDetails, n);
            this.handleResponse(movieDetails, n);
        }
        
        @Override
        public void onShowDetailsFetched(final ShowDetails showDetails, final int n) {
            super.onShowDetailsFetched(showDetails, n);
            this.handleResponse(showDetails, n);
        }
    }
    
    protected enum PlayerActivityState
    {
        ACTIVITY_NOTREADY(0, "NOTREADY"), 
        ACTIVITY_PLAYER_READY(2, "PLAYER_READY"), 
        ACTIVITY_SRVCMNGR_READY(1, "SRVCMNGR_READY");
        
        int mActivityState;
        String mName;
        
        private PlayerActivityState(final int mActivityState, final String mName) {
            this.mActivityState = mActivityState;
            this.mName = mName;
        }
        
        protected String getName() {
            return this.mName;
        }
        
        protected int getState() {
            return this.mActivityState;
        }
    }
}
