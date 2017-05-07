// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.util.LanguageChoice;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import java.io.Serializable;
import android.media.AudioManager;
import android.widget.Toast;
import android.app.DialogFragment;
import android.view.MenuItem;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.app.Activity;
import android.os.Bundle;
import android.content.res.Configuration;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier;
import com.netflix.mediaclient.service.net.LogMobileType;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.view.Surface;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifier;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault$PlayInvokedFrom;
import android.annotation.SuppressLint;
import android.view.TextureView;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.Toolbar;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManager;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManagerFactory;
import android.os.Debug;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.os.SystemClock;
import android.util.Pair;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.view.View;
import android.view.KeyEvent;
import android.content.Context;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import android.os.Parcelable;
import android.content.Intent;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$TapListener;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$SurfaceMeasureListener;
import android.view.SurfaceHolder$Callback;
import com.netflix.mediaclient.media.JPlayer.SecondSurface;
import com.netflix.mediaclient.service.player.subtitles.SafeSubtitleManager;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import android.content.BroadcastReceiver;
import android.os.Handler;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.media.Language;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.annotation.TargetApi;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;

@TargetApi(14)
public class PlayerActivity extends NetflixActivity implements AudioManager$OnAudioFocusChangeListener, NetflixDialogFrag$DialogCanceledListenerProvider, ErrorWrapper$Callback, JPlayer$JplayerListener, IPlayer$PlayerListener, PlayContextProvider
{
    private static final int DELAY_POST = 1000;
    static final String EXTRA_GET_DETAILS_EPISODE_DETAILS = "extra_get_details_EPISODE_DETAILS";
    static final String EXTRA_GET_DETAILS_IS_EPISODE = "extra_get_details_is_episode";
    static final String EXTRA_GET_DETAILS_PLAY_CONTEXT = "extra_get_details_play_context";
    static final String EXTRA_GET_DETAILS_VIDEO_ID = "extra_get_details_video_id";
    static final String EXTRA_GET_DETAILS_VIDEO_TYPE = "extra_get_details_video_type";
    public static final int INACTIVITY_TIMEOUT = 5000;
    public static final String INTENT_PLAY = "com.netflix.mediaclient.intent.action.NOTIFICATION_PLAY";
    private static final int INVALID_TRACK_INDEX = -1;
    private static final int PAUSE_LOCK_SCREEN_TIMEOUT = 120000;
    private static final long PAUSE_TIMEOUT = 900000L;
    public static final Boolean PIN_VERIFIED;
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
    private ServiceAgent$ConfigurationAgentInterface mConfig;
    private final NetflixDialogFrag$DialogCanceledListener mDialogCancedledListener;
    private final AbsEpisodeView$EpisodeRowListener mEpisodeRowListener;
    private String mErrorDialogId;
    protected Handler mHandler;
    private int mHeight;
    private boolean mIsAssetReady;
    private boolean mIsBufferingOnPause;
    private boolean mIsListening;
    private boolean mIsSurfaceReady;
    private boolean mIsZoomedOut;
    private String mMaxStreamsReachedDialogId;
    private final BroadcastReceiver mNetworkChangeReceiver;
    private IPlayer mPlayer;
    private boolean mPlayerBackgrounded;
    private final BroadcastReceiver mPlayerSuspendIntentReceiver;
    private PlayerSuspendNotification mPlayerSuspendNotification;
    private Menu mPostponedPanelMenu;
    private boolean mReloadOnAudioTrackChange;
    private ResourceHelper mResources;
    private boolean mRestartPlayback;
    private PlayScreen mScreen;
    private final PlayerWorkflowState mState;
    private SafeSubtitleManager mSubtitleManager;
    private SecondSurface mSurface2;
    private int mWidth;
    private final Runnable onEverySecond;
    private final Runnable pauseTimeout;
    private final View$OnClickListener playPauseListener;
    private final View$OnClickListener skipBackListener;
    private final SurfaceHolder$Callback surfaceListener;
    private final TappableSurfaceView$SurfaceMeasureListener surfaceMeasureListener;
    private final TappableSurfaceView$TapListener tapListener;
    private final View$OnClickListener zoomListener;
    
    static {
        PIN_VERIFIED = true;
    }
    
    public PlayerActivity() {
        this.mWidth = 0;
        this.mHeight = 0;
        this.mHandler = new Handler();
        this.mState = new PlayerWorkflowState();
        this.mReloadOnAudioTrackChange = false;
        this.mIsZoomedOut = true;
        this.mActionId12Count = 0;
        this.mRestartPlayback = false;
        this.allowScreenLockTimeout = new PlayerActivity$1(this);
        this.playPauseListener = (View$OnClickListener)new PlayerActivity$2(this);
        this.episodesListener = (View$OnClickListener)new PlayerActivity$3(this);
        this.zoomListener = (View$OnClickListener)new PlayerActivity$4(this);
        this.skipBackListener = (View$OnClickListener)new PlayerActivity$5(this);
        this.audioPositionListener = (SeekBar$OnSeekBarChangeListener)new PlayerActivity$6(this);
        this.tapListener = new PlayerActivity$8(this);
        this.onEverySecond = new PlayerActivity$9(this);
        this.surfaceListener = (SurfaceHolder$Callback)new PlayerActivity$10(this);
        this.mPlayerSuspendIntentReceiver = new PlayerActivity$11(this);
        this.surfaceMeasureListener = new PlayerActivity$13(this);
        this.pauseTimeout = new PlayerActivity$16(this);
        this.exitButtonHandler = new PlayerActivity$17(this);
        this.mNetworkChangeReceiver = new PlayerActivity$18(this);
        this.mEpisodeRowListener = new PlayerActivity$20(this);
        this.mDialogCancedledListener = new PlayerActivity$21(this);
    }
    
    private boolean canPlayerBeBackgrounded() {
        return !this.isFinishing() && this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY && this.mState.isPlayStarted() && !this.isStalled() && !this.isSeeking() && !this.mIsBufferingOnPause && this.mScreen != null && this.mScreen.getState() != PlayerUiState.PostPlay && PlayerTypeFactory.isJPlayer2(PlayerTypeFactory.getCurrentType(this.getBaseContext())) && this.mConfig != null && !this.mConfig.isDeviceLowMem() && this.mConfig.getPlaybackConfiguration().isSuspendPlaybackEnabled();
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
            return;
        }
        if (!this.mIsAssetReady) {
            Log.d("PlayerActivity", "Asset not ready - cannot complete init");
            return;
        }
        if (this.mAsset == null) {
            Log.d("PlayerActivity", "Asset is null - cannot complete init");
            return;
        }
        verifyPlayToContinue(this, this.mAsset);
    }
    
    private void continueInitAfterPlayVerify() {
        Log.v("PlayerActivity", "Completing init process...");
        this.mScreen.setTitle(this.getTitleForScreen(this.mAsset));
        if (!this.mState.videoLoaded && this.loadVideo()) {
            this.mState.activityState = PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY;
        }
    }
    
    public static Intent createColdStartIntent(final NetflixActivity netflixActivity, final String s, final VideoType videoType, final PlayContext playContext) {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Performing 'cold start' - activity itself will get details for videoId: " + s);
        }
        final Intent intent = createIntent(netflixActivity);
        intent.addFlags(131072);
        intent.putExtra("extra_get_details_video_id", s);
        intent.putExtra("extra_get_details_video_type", videoType.getValue());
        intent.putExtra("extra_get_details_play_context", (Parcelable)playContext);
        return intent;
    }
    
    public static Intent createColdStartIntent(final String s, final String s2, final VideoType videoType, final PlayContext playContext, final MessageData messageData) {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Performing 'cold start' - activity itself will get details for videoId: " + s2);
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NOTIFICATION_PLAY");
        intent.addFlags(131072);
        intent.putExtra("extra_get_details_video_id", s2);
        intent.putExtra("extra_get_details_video_type", videoType.getValue());
        intent.putExtra("extra_get_details_play_context", (Parcelable)playContext);
        intent.putExtra("g", s);
        MessageData.addMessageDataToIntent(intent, messageData);
        return intent;
    }
    
    private static Intent createIntent(final NetflixActivity netflixActivity) {
        return new Intent((Context)netflixActivity, (Class)PlayerActivity.class);
    }
    
    private PlayScreen$Listeners createListeners() {
        final PlayScreen$Listeners playScreen$Listeners = new PlayScreen$Listeners();
        playScreen$Listeners.videoPositionListener = (SeekBar$OnSeekBarChangeListener)new PlayerActivity$VideoPositionListener(this);
        playScreen$Listeners.playPauseListener = this.playPauseListener;
        playScreen$Listeners.surfaceListener = this.surfaceListener;
        playScreen$Listeners.tapListener = this.tapListener;
        playScreen$Listeners.audioPositionListener = this.audioPositionListener;
        playScreen$Listeners.surfaceMeasureListener = this.surfaceMeasureListener;
        playScreen$Listeners.skipBackListener = this.skipBackListener;
        playScreen$Listeners.zoomListener = this.zoomListener;
        playScreen$Listeners.episodeSelectorListener = this.episodesListener;
        return playScreen$Listeners;
    }
    
    private void doPause(final boolean b) {
        this.doPause(b, false);
    }
    
    private void doPause(final boolean b, final boolean b2) {
        if (this.mState.activityState != PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
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
                this.mSubtitleManager.clearPendingUpdates();
                if (!b2 && this.mScreen != null) {
                    this.mScreen.getBottomPanel().setMediaImage(true);
                }
                this.mPlayer.pause();
                Log.d("PlayerActivity", "Pause, release awake clock after 2 minutes.");
                this.mHandler.postDelayed(this.allowScreenLockTimeout, 120000L);
                this.mHandler.postDelayed(this.pauseTimeout, 900000L);
            }
        }
    }
    
    private void doSeek(final int n, final boolean b) {
        if (this.mState.activityState != PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerActivity", "doSeek: Invalid state, exit...:" + this.mState.activityState.getName());
            this.cleanupAndExit();
        }
        else if (this.mScreen != null) {
            Log.w("PlayerActivity", "Playout seek...");
            if (this.mScreen != null) {
                this.mScreen.changeActionState(false);
            }
            this.mState.seekToInProgress = true;
            this.onSeek();
            if (Log.isLoggable()) {
                Log.d("PlayerActivity", "==> seekTo: " + n);
            }
            if (this.mScreen != null) {
                this.mScreen.getBottomPanel().setMediaImage(false);
            }
            this.mPlayer.seekTo(n, b);
            this.mSubtitleManager.onSubtitleRemove();
        }
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
            if (this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
                this.showControlScreenOverlay(false);
                this.skipBack();
                return true;
            }
        }
        else if ((n == 22 || n == 103) && this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
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
        if (Log.isLoggable()) {
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
        while (true) {
            this.mState.videoLoaded = true;
            while (true) {
                Label_0411: {
                    try {
                        final PlayerType currentType = PlayerTypeFactory.getCurrentType(this.getBaseContext());
                        if (currentType == PlayerType.device10 || currentType == PlayerType.device11) {
                            if (Log.isLoggable()) {
                                Log.d("PlayerActivity", "Set JPlayerListener: " + this);
                            }
                            this.mPlayer.setJPlayerListener(this);
                        }
                        int playbackBookmark = mAsset.getPlaybackBookmark();
                        if (Log.isLoggable()) {
                            Log.d("PlayerActivity", this.hashCode() + " Do Play from position " + playbackBookmark);
                            Log.d("PlayerActivity", this.hashCode() + " Do Play asset " + mAsset.toString());
                        }
                        if (playbackBookmark < 0) {
                            Log.d("PlayerActivity", this.hashCode() + " Invalid bookmark, reset to 0");
                            playbackBookmark = 0;
                            this.mPlayer.setSurface(this.mScreen.getHolder().getSurface());
                            this.mPlayer.setSurfaceHolder(this.mScreen.getHolder());
                            Thread.sleep(30L);
                            this.mPlayer.open(this.toLongSafe(mAsset.getPlayableId()), mAsset, playbackBookmark * 1000);
                            this.notifyOthersOfPlayStart();
                            return true;
                        }
                        break Label_0411;
                    }
                    catch (Throwable t) {
                        Log.e("PlayerActivity", "Exception in video preparation", t);
                        this.errorManager.addError(new NccpActionId(3, "", this.getString(2131493118), "handleActionId", "ACTION_ID", 0, null));
                        return false;
                    }
                }
                continue;
            }
        }
    }
    
    private void noConnectivityWarning() {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, this.getString(2131493132), this.getString(2131493003), this.exitButtonHandler)));
    }
    
    private void nonWifiPlayWarning() {
        ThreadUtils.assertOnMain();
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, this.getString(2131493136), this.getString(2131493003), this.exitButtonHandler)));
    }
    
    private void onSeek() {
        ThreadUtils.assertOnMain();
        Log.d("PlayerActivity", "onSeek");
        this.mState.stalled = true;
        this.mScreen.setBufferingOverlayVisibility(true);
    }
    
    public static void playVideo(final NetflixActivity netflixActivity, final Asset asset) {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Asset to playback: " + asset);
        }
        if (asset == null) {
            return;
        }
        netflixActivity.startActivity(toIntent(netflixActivity, asset));
    }
    
    public static void playVideo(final NetflixActivity netflixActivity, final Playable playable, final PlayContext playContext) {
        playVideo(netflixActivity, Asset.create(playable, playContext, !PlayerActivity.PIN_VERIFIED));
    }
    
    private void releaseLockOnScreen() {
        this.getWindow().clearFlags(128);
        Log.d("PlayerActivity", "KEEP_SCREEN: OFF");
    }
    
    private void removeDialogFragmentIfShown() {
        if (this.isDialogFragmentVisible()) {
            this.removeDialogFrag();
        }
    }
    
    private void repostOnEverySecondRunnable(final int n) {
        this.mHandler.removeCallbacks(this.onEverySecond);
        this.mHandler.postDelayed(this.onEverySecond, (long)n);
    }
    
    private boolean requestDetailsIfNeeded(final ServiceManager serviceManager) {
        final Intent intent = this.getIntent();
        if (!intent.hasExtra("extra_get_details_video_id")) {
            this.mIsAssetReady = true;
            Log.d("PlayerActivity", "Regular playback");
            return false;
        }
        Log.d("PlayerActivity", "Intent has EXTRA_GET_DETAILS_VIDEO_ID - fetching details...");
        final String stringExtra = intent.getStringExtra("extra_get_details_video_id");
        final VideoType create = VideoType.create(intent.getStringExtra("extra_get_details_video_type"));
        final PlayContext playContext = (PlayContext)intent.getParcelableExtra("extra_get_details_play_context");
        if (create == VideoType.MOVIE) {
            serviceManager.getBrowse().fetchMovieDetails(stringExtra, new PlayerActivity$FetchVideoDetailsForPlaybackCallback(this, playContext));
            return true;
        }
        if (create == VideoType.SHOW) {
            serviceManager.getBrowse().fetchShowDetails(stringExtra, null, BrowseExperience.shouldLoadKubrickLeavesInDetails(), new PlayerActivity$FetchVideoDetailsForPlaybackCallback(this, playContext));
            return true;
        }
        throw new IllegalStateException("Invalid billboard video type: " + create);
    }
    
    private void setProgress() {
        if (this.mPlayer != null && !this.mState.draggingInProgress) {
            final int currentProgress = this.mPlayer.getCurrentProgress();
            final int duration = this.mPlayer.getDuration();
            if (!this.mState.draggingInProgress && this.mPlayer.canUpdatePosition(currentProgress)) {
                if (Log.isLoggable()) {
                    Log.d("PlayerActivity", "PA#setProgress:: Position: " + currentProgress + ", duration: " + duration);
                }
                this.mScreen.setProgress(currentProgress, duration, true);
                return;
            }
            if (Log.isLoggable()) {
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
            this.mScreen.getTopPanel().setMdxTargetSelector(null);
            return;
        }
        final Pair<String, String>[] targetList = serviceManager.getMdx().getTargetList();
        if (targetList == null || targetList.length < 1) {
            this.mScreen.getTopPanel().setMdxTargetSelector(null);
            return;
        }
        this.mScreen.getTopPanel().setMdxTargetSelector(this.createMdxTargetSelection(targetList, serviceManager.getMdx().getCurrentTarget()));
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
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Skip back " + n + " ms");
        }
        this.mState.setLastActionTime(SystemClock.elapsedRealtime());
        this.mState.userInteraction();
        if ((n += this.mPlayer.getCurrentProgress()) < 0) {
            Log.d("PlayerActivity", "Go back to start, instead of trying to go minus!");
            n = 0;
        }
        this.doSeek(n, false);
    }
    
    private void skipBack() {
        this.skip(-30000);
    }
    
    private void skipForward() {
        this.skip(30000);
    }
    
    private void slowNetworkWarning() {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, this.getString(2131493022), this.getString(2131493003), this.exitButtonHandler)));
    }
    
    private void startScreenUpdate() {
        if (this.mScreen != null && !this.destroyed()) {
            this.mState.setLastActionTime(SystemClock.elapsedRealtime());
            this.startScreenUpdateTask();
        }
    }
    
    private void stopPlayback() {
        Log.d("PlayerActivity", "stopPlayback");
        if (this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_SRVCMNGR_READY || this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
            this.mPlayer.close();
            this.mIsListening = false;
            this.mPlayer.removePlayerListener(this);
            this.mState.activityState = PlayerActivity$PlayerActivityState.ACTIVITY_NOTREADY;
            this.mScreen.getBottomPanel().enableButtons(false);
            if (this.mState.playStarted) {
                this.notifyOthersOfPlayStop();
            }
        }
        this.mAsset = null;
        this.mIsAssetReady = false;
        this.mReloadOnAudioTrackChange = false;
    }
    
    private int toBifAjustedProgress(final int n) {
        final int n2 = n / 10000 * 10000;
        if (n2 == n) {
            if (Log.isLoggable()) {
                Log.d("PlayerActivity", "Right on target, no need to ajust seekbar position " + n + " [ms]");
            }
        }
        else if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Progres : " + n + " [ms] vs. bif position " + n2 + " [ms]");
            return n2;
        }
        return n2;
    }
    
    private static Intent toIntent(final NetflixActivity netflixActivity, final Asset asset) {
        final Intent intent = createIntent(netflixActivity);
        intent.addFlags(131072);
        intent.addFlags(268435456);
        asset.toIntent(intent);
        return intent;
    }
    
    private long toLong(final String s) {
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
        if (PreferenceUtils.getBooleanPref(this.getBaseContext(), "ui.playeroverlay", false)) {
            this.mScreen.setDebugDataVisibility(true);
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            String string = "N/A";
            while (true) {
                Label_0480: {
                    if (this.mPlayer == null) {
                        break Label_0480;
                    }
                    final PlayoutMetadata playoutMetadata = this.mPlayer.getPlayoutMetadata();
                    if (playoutMetadata == null) {
                        break Label_0480;
                    }
                    final int n = playoutMetadata.position / 60000;
                    final int n2 = playoutMetadata.duration / 60000;
                    sb.append(playoutMetadata.instantBitRate).append("/");
                    sb.append(playoutMetadata.targetBitRate).append("/");
                    if (playoutMetadata.isSuperHD) {
                        sb.append(this.getString(2131493233));
                    }
                    else if (playoutMetadata.isHD) {
                        sb.append(this.getString(2131493115));
                    }
                    else {
                        sb.append(this.getString(2131493114));
                    }
                    sb2.append(playoutMetadata.language).append("/");
                    sb2.append(playoutMetadata.getAudioChannel()).append("/");
                    sb2.append(playoutMetadata.getAudioTrackType());
                    final SubtitleConfiguration subtitleConfiguration = this.mPlayer.getSubtitleConfiguration();
                    if (subtitleConfiguration != null) {
                        string = subtitleConfiguration.getString((Context)this);
                    }
                    Log.d("PlayerActivity", "Subtitle config: " + string);
                    final IMedia$SubtitleProfile subtitleProfileFromMetadata = this.mPlayer.getSubtitleProfileFromMetadata();
                    String s;
                    String s2;
                    if (subtitleProfileFromMetadata != null) {
                        final String nccpCode = subtitleProfileFromMetadata.getNccpCode();
                        s = string;
                        s2 = nccpCode;
                    }
                    else {
                        s = string;
                        s2 = "N/A";
                    }
                    final String string2 = this.getString(2131493113, new Object[] { "Release", AndroidManifestUtils.getVersionCode(this.getBaseContext()), (int)(Debug.getNativeHeapAllocatedSize() / 1048576L), "UI Version", n, n2, sb.toString(), sb2.toString(), PlayerTypeFactory.getCurrentType(this.getBaseContext()).getDescription(), s, s2, SubtitleManagerFactory.getSubtitleManagerLabel(this.mSubtitleManager) });
                    if (this.mScreen != null) {
                        this.mScreen.setDebugData(string2);
                        return;
                    }
                    return;
                }
                final int n2 = 0;
                final int n = 0;
                String s = "N/A";
                String s2 = "N/A";
                continue;
            }
        }
        this.mScreen.setDebugDataVisibility(false);
    }
    
    @SuppressLint({ "NewApi" })
    private void updateUI() {
        final PostPlayFactory$PostPlayType postPlayType = PostPlay.getPostPlayType(this);
        this.setContentView(PlayScreen.resolveContentView(postPlayType));
        this.setSupportActionBar((Toolbar)this.findViewById(2131427714));
        final ServiceManager serviceManager = this.getServiceManager();
        this.mPlayer = serviceManager.getPlayer();
        this.mConfig = serviceManager.getConfiguration();
        if (this.mPlayer == null || this.mConfig == null) {
            Log.d("PlayerActivity", "Unable to receive handle to player object, finishing activity ");
            this.finish();
            return;
        }
        serviceManager.cancelAllImageLoaderRequests();
        this.mPlayer.addPlayerListener(this);
        this.mIsListening = true;
        this.mScreen = PlayScreen.createInstance(this, this.createListeners(), postPlayType);
        Log.d("PlayerActivity", "Checking episode visibility");
        final Asset currentAsset = this.getCurrentAsset();
        this.mScreen.getTopPanel().setEpisodeSelectorVisibility(currentAsset != null && currentAsset.isEpisode());
        if (this.mPostponedPanelMenu != null) {
            this.mScreen.getTopPanel().onCreateOptionsMenu(this.mPostponedPanelMenu);
            this.mPostponedPanelMenu = null;
        }
        final PostPlay postPlay = this.mScreen.getPostPlay();
        final String playableId = this.getCurrentAsset().getPlayableId();
        VideoType videoType;
        if (this.getCurrentAsset().isEpisode()) {
            videoType = VideoType.EPISODE;
        }
        else {
            videoType = VideoType.MOVIE;
        }
        postPlay.fetchPostPlayVideosIfNeeded(playableId, videoType);
        if (AndroidUtils.getAndroidVersion() > 18) {
            this.mScreen.getSurfaceView().setSecure(true);
        }
        this.setTargetSelection();
        this.errorManager = new ErrorManager(this.handler, this, this.mConfig, serviceManager.getClientLogging());
        this.registerReceiverWithAutoUnregister(this.mNetworkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.mPlayerSuspendNotification = new PlayerSuspendNotification(this, serviceManager);
        this.registerReceiverWithAutoUnregister(this.mPlayerSuspendIntentReceiver, PlayerSuspendNotification.getNotificationIntentFilter());
        if (AndroidUtils.getAndroidVersion() >= 16 && (PlayerTypeFactory.isJPlayerBase(PlayerTypeFactory.getCurrentType((Context)this)) || PlayerTypeFactory.isJPlayer(PlayerTypeFactory.getCurrentType((Context)this)))) {
            this.mSurface2 = new SecondSurface((TextureView)this.findViewById(2131427709));
        }
        this.mState.activityState = PlayerActivity$PlayerActivityState.ACTIVITY_SRVCMNGR_READY;
    }
    
    private static void verifyPlayToContinue(final PlayerActivity playerActivity, final Asset asset) {
        if (!asset.isAgeProtected() && asset.isPinVerified()) {
            Log.d("PlayerActivity", String.format("nf_pin PlayerActivity pinVerification skipped - ageProtected: %b, pinVerified:%b, pinProtected:%b", asset.isAgeProtected(), asset.isPinVerified(), asset.isPinProtected()));
            playerActivity.continueInitAfterPlayVerify();
            return;
        }
        PlayVerifier.verify(playerActivity, asset.isAgeProtected(), asset.isPinProtected(), new PlayVerifierVault(PlayVerifierVault$PlayInvokedFrom.PLAYER.getValue(), asset));
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
        if (this.mPlayerBackgrounded) {
            if (this.mPlayerSuspendNotification != null) {
                this.mPlayerSuspendNotification.cancelNotification();
            }
            this.mPlayerBackgrounded = false;
        }
        if (this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
            this.stopPlayback();
        }
        this.stopScreenUpdateTask();
        this.mState.activityState = PlayerActivity$PlayerActivityState.ACTIVITY_NOTREADY;
        this.setResult(-1);
        if (!this.isFinishing()) {
            Log.d("PlayerActivity", "cleanupAndExit calling finish");
            this.finish();
        }
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new PlayerActivity$7(this);
    }
    
    MdxTargetSelection createMdxTargetSelection(final Pair<String, String>[] array, final String s) {
        return new MdxTargetSelection(array, s, this.mConfig.getPlaybackConfiguration().isLocalPlaybackEnabled());
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
            this.mIsBufferingOnPause = false;
            this.keepScreenOn();
            if (this.mScreen != null) {
                this.mScreen.changeActionState(true);
                this.mScreen.setBufferingOverlayVisibility(false);
                Log.d("PlayerActivity", "Remove bif image if it was visible. Only for phones!!!");
                if (!this.mIsTablet) {
                    this.mScreen.stopBif();
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
        if (this.mState.activityState != PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerActivity", "doUnPause: Invalid state, exit...:" + this.mState.activityState.getName());
            this.cleanupAndExit();
        }
        else {
            Log.i("PlayerActivity", "doPlay: resume");
            if (!this.mPlayer.isPlaying()) {
                if (this.mScreen != null) {
                    this.mScreen.getBottomPanel().setMediaImage(false);
                }
                this.keepScreenOn();
                if (!this.mPlayerBackgrounded) {
                    this.mPlayer.unpause();
                    return;
                }
                this.mState.setLastActionTime(SystemClock.elapsedRealtime());
                this.mState.userInteraction();
                this.mPlayer.seekWithFuzzRange(-5000, 5000);
                this.mSubtitleManager.onSubtitleRemove();
                this.mPlayerBackgrounded = false;
                if (this.mPlayerSuspendNotification != null) {
                    this.mPlayerSuspendNotification.cancelNotification();
                }
            }
        }
    }
    
    public void doZoomIn() {
        if (this.mState.activityState != PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerActivity", "doZoomIn: Invalid state, exit...:" + this.mState.activityState.getName());
            this.cleanupAndExit();
        }
        else {
            Log.d("PlayerActivity", "doZoomOut: start");
            final PlayScreen mScreen = this.mScreen;
            if (mScreen != null) {
                mScreen.setZoom(true);
                mScreen.getBottomPanel().setZoomImage(false);
            }
        }
    }
    
    public void doZoomOut() {
        if (this.mState.activityState != PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerActivity", "doZoomIn: Invalid state, exit...:" + this.mState.activityState.getName());
            this.cleanupAndExit();
        }
        else {
            Log.i("PlayerActivity", "doZoomIn: start");
            final PlayScreen mScreen = this.mScreen;
            if (mScreen != null) {
                mScreen.setZoom(false);
                mScreen.getBottomPanel().setZoomImage(true);
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
    
    @Override
    public DataContext getDataContext() {
        if (Log.isLoggable() && this.mAsset == null) {
            Log.w("PlayerActivity", "trying to create data context when mAsset is null!");
        }
        return new DataContext(this.mAsset);
    }
    
    public NetflixDialogFrag$DialogCanceledListener getDialogCanceledListener() {
        return this.mDialogCancedledListener;
    }
    
    @Override
    public AbsEpisodeView$EpisodeRowListener getEpisodeRowListener() {
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
    
    public PlayContext getPlayContext() {
        return this.mAsset;
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
    
    protected PlayerWorkflowState getState() {
        return this.mState;
    }
    
    SubtitleManager getSubtitleManager() {
        return this.mSubtitleManager;
    }
    
    public SecondSurface getSurface2() {
        return this.mSurface2;
    }
    
    public String getTitleForScreen(final Asset asset) {
        if (asset.isEpisode()) {
            return this.getResources().getString(2131493243, new Object[] { asset.getParentTitle(), asset.getSeasonNumber(), asset.getEpisodeNumber(), asset.getTitle() });
        }
        return this.getResources().getString(2131493242, new Object[] { asset.getTitle() });
    }
    
    public ResourceHelper getUiResources() {
        return this.mResources;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.playback;
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
    
    public boolean isListening() {
        return this.mIsListening;
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
        PinVerifier.getInstance().registerPlayEvent(this.mAsset.isPinProtected());
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP");
        this.mAsset.setPlaybackBookmark(this.mPlayer.getCurrentPositionMs() / 1000);
        this.sendBroadcast(this.mAsset.toIntent(intent));
        Log.v("PlayerActivity", "Intent PLAYER_PLAY_STOP sent");
    }
    
    public void onAudioChange(final int n) {
        if (Log.isLoggable()) {
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
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "onAudioFocusChange " + string);
        }
    }
    
    public void onBandwidthChange(final int n) {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "bandwidth changed to [Kbps]: " + n);
        }
    }
    
    public void onBufferingUpdate(final int n) {
        if (Log.isLoggable()) {
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
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "onCreate started " + this.hashCode());
            Log.d("PlayerActivity", this.getIntent());
        }
        AndroidUtils.logDeviceDensity(this);
        this.keepScreenOn();
        this.getWindow().getAttributes().buttonBrightness = 0.0f;
        this.mState.reset();
        UserActionLogUtils.reportPlayActionStarted((Context)this, null, this.getUiScreen());
        this.mState.playStartInProgress.set(true);
        this.mSubtitleManager = new SafeSubtitleManager(this);
        ConsolidatedLoggingUtils.pauseReporting((Context)this);
        Log.d("PlayerActivity", "onCreate done");
    }
    
    public void onCreateOptionsMenu(final Menu mPostponedPanelMenu, final Menu menu) {
        super.onCreateOptionsMenu(mPostponedPanelMenu, menu);
        if (this.mScreen == null || this.mScreen.getTopPanel() == null) {
            Log.w("PlayerActivity", "onCreateOptionsMenu() was triggered before UI was initialized. Scheduling panel menu update to be called later.");
            this.mPostponedPanelMenu = mPostponedPanelMenu;
            return;
        }
        this.mScreen.getTopPanel().onCreateOptionsMenu(mPostponedPanelMenu);
    }
    
    @Override
    protected void onDestroy() {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "====> Destroying PlayActivity " + this.hashCode());
        }
        if (this.mPlayerBackgrounded) {
            this.cleanupAndExit();
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
        ConsolidatedLoggingUtils.resumeReporting((Context)this, false);
        super.onDestroy();
        Log.d("PlayerActivity", "====> Destroying PlayActivity done");
    }
    
    public Surface onGetTextureSurface() {
        final SecondSurface mSurface2 = this.mSurface2;
        if (mSurface2 != null) {
            return mSurface2.getSurface();
        }
        return null;
    }
    
    @Override
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        final boolean b = true;
        if (Log.isLoggable()) {
            Log.v("PlayerActivity", "onKeyDown: " + keyEvent);
        }
        this.mState.setLastActionTime(SystemClock.elapsedRealtime());
        this.mState.userInteraction();
        boolean onKeyDown;
        if (n == 4) {
            if (this.mScreen == null || !this.mScreen.inInterruptedOrPendingInterrupted()) {
                Log.d("PlayerActivity", "Back...");
                this.handleBackPressed();
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
    
    @Override
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        if (n == 82) {
            this.openOptionsMenu();
            return true;
        }
        return super.onKeyUp(n, keyEvent);
    }
    
    public void onMediaError(final Error error) {
        if (Log.isLoggable()) {
            Log.e("PlayerActivity", "Media Error " + error);
        }
        this.errorManager.addMediaError(error);
    }
    
    public void onNccpError(final NccpError nccpError) {
        if (Log.isLoggable()) {
            Log.e("PlayerActivity", "Nccp Error " + nccpError);
        }
        this.errorManager.addError(nccpError);
    }
    
    public void onNrdFatalError() {
        Log.w("PlayerActivity", "onNrdFatalError");
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.mHandler, new AlertDialogFactory$AlertDialogDescriptor("", this.getString(2131493146), null, new PlayerActivity$19(this))));
    }
    
    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        boolean b;
        if (!(b = this.mScreen.getTopPanel().onOptionsItemSelected(menuItem))) {
            b = super.onOptionsItemSelected(menuItem);
        }
        return b;
    }
    
    @Override
    protected void onPause() {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "onPause called..." + this.hashCode());
        }
        this.mIsBufferingOnPause = (this.isStalled() || this.isSeeking());
        if (this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
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
    
    @Override
    public void onPlayVerified(final boolean b, final PlayVerifierVault playVerifierVault) {
        Log.d("nf_pin", String.format("%s onPlayVerification vault: %s", PlayerActivity.class.getSimpleName(), playVerifierVault));
        if (b && PlayVerifierVault$PlayInvokedFrom.PLAYER.getValue().equals(playVerifierVault.getInvokeLocation())) {
            this.continueInitAfterPlayVerify();
            return;
        }
        Log.d("PlayerActivity", "Age/Pin verification failed cannot proceed - stop playback");
        this.cleanupAndExit();
    }
    
    public void onPlaying() {
        synchronized (this) {
            Log.d("PlayerActivity", "Playout (re)started");
            if (this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
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
        if (this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
            try {
                this.mState.videoPrepared = true;
                this.mWidth = this.mPlayer.getVideoWidth();
                this.mHeight = this.mPlayer.getVideoHeight();
                if (this.mWidth != 0 && this.mHeight != 0 && this.mScreen != null) {
                    if (Log.isLoggable()) {
                        Log.d("PlayerActivity", "====> width = " + this.mWidth + ", height" + this.mHeight);
                    }
                    this.setSurface(this.mWidth, this.mHeight, true);
                    this.initSurfaceFixedSize(this.mWidth, this.mHeight);
                    Log.d("PlayerActivity", "Play");
                    this.mPlayer.play();
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
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "onResume: back " + this.hashCode());
        }
        final PostPlay postPlaySafely = this.getPostPlaySafely();
        if (postPlaySafely != null) {
            postPlaySafely.onResume();
        }
    }
    
    public void onRetryRequested() {
        if (Log.isLoggable()) {
            Log.v("PlayerActivity", "onRetryRequested()");
        }
        final DialogFragment dialogFragment = this.getDialogFragment();
        if (dialogFragment instanceof ErrorWrapper$Callback) {
            if (Log.isLoggable()) {
                Log.v("PlayerActivity", "Calling onRetryRequested for fragment: " + dialogFragment);
            }
            ((ErrorWrapper$Callback)dialogFragment).onRetryRequested();
        }
        else if (Log.isLoggable()) {
            Log.v("PlayerActivity", "frag does not implement ErrorWrapper.Callback: " + dialogFragment);
        }
    }
    
    public boolean onSearchRequested() {
        return false;
    }
    
    public void onSeekComplete() {
        Log.d("PlayerActivity", "MP onSeekComplete");
        if (this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
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
                        this.mScreen.changeActionState(false);
                    }
                    if (this.mScreen != null) {
                        this.mScreen.getBottomPanel().setMediaImage(false);
                    }
                    if (this.mState.lowBandwidth) {
                        Log.d("PlayerActivity", "Enabled Toast");
                        Toast.makeText(this.getBaseContext(), 2131493119, 1).show();
                    }
                    this.mScreen.setBufferingOverlayVisibility(true);
                }
            }
        }
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        if (Log.isLoggable()) {
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
            else if (Log.isLoggable()) {
                Log.d("PlayerActivity", "Asset received: " + fromIntent.toString());
            }
            this.mAsset = fromIntent;
            Log.d("PlayerActivity", "onStart done");
            if (Log.isLoggable()) {
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
            final PlayerType currentType = PlayerTypeFactory.getCurrentType((Context)this);
            if (this.mState.activityState == PlayerActivity$PlayerActivityState.ACTIVITY_PLAYER_READY) {
                final int currentProgress = this.mPlayer.getCurrentProgress();
                final int duration = this.mPlayer.getDuration();
                if (Log.isLoggable()) {
                    Log.d("PlayerActivity", "========> Duration = " + duration);
                }
                this.mScreen.initProgress(duration);
                if (Log.isLoggable()) {
                    Log.d("PlayerActivity", "Position: " + currentProgress + ", duration: " + duration);
                }
                this.mScreen.setProgress(currentProgress, duration, true, true);
                this.mState.playStarted = true;
                this.mScreen.removeSplashScreen();
                this.startScreenUpdate();
                UserActionLogUtils.reportPlayActionEnded((Context)this, IClientLogging$CompletionReason.success, null, null, currentType);
                this.mState.playStartInProgress.set(false);
                final PostPlay postPlay = this.mScreen.getPostPlay();
                final String playableId = this.getCurrentAsset().getPlayableId();
                VideoType videoType;
                if (this.getCurrentAsset().isEpisode()) {
                    videoType = VideoType.EPISODE;
                }
                else {
                    videoType = VideoType.MOVIE;
                }
                postPlay.fetchPostPlayVideosIfNeeded(playableId, videoType);
            }
            else {
                Log.e("PlayerActivity", "onStarted not in correct state, destroyed:" + this.destroyed() + " ActivityState: " + this.mState.activityState.getName());
                UserActionLogUtils.reportPlayActionEnded((Context)this, IClientLogging$CompletionReason.failed, new UIError(RootCause.clientFailure, ActionOnUIError.handledSilently, null, null), null, currentType);
                this.mState.playStartInProgress.set(false);
                this.cleanupAndExit();
            }
        }
    }
    
    @Override
    protected void onStop() {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "PlayerActivity::onStop called " + this.hashCode());
        }
        super.onStop();
        final AudioManager audioManager = (AudioManager)this.getSystemService("audio");
        if (audioManager != null) {
            audioManager.abandonAudioFocus((AudioManager$OnAudioFocusChangeListener)this);
        }
        else {
            Log.e("PlayerActivity", "Audio manager not found. Unable to abandon audio focus!");
        }
        if (this.mPlayerBackgrounded || this.canPlayerBeBackgrounded()) {
            Log.d("PlayerActivity", "PlayerActivity::onStop done, player is backbrounded");
            return;
        }
        if (this.mState.playStartInProgress.getAndSet(false)) {
            Log.d("PlayerActivity", "Start play is in progress and user canceled playback");
            UserActionLogUtils.reportPlayActionEnded((Context)this, IClientLogging$CompletionReason.canceled, null, null, PlayerTypeFactory.getCurrentType((Context)this));
        }
        final String mMaxStreamsReachedDialogId = this.mMaxStreamsReachedDialogId;
        if (mMaxStreamsReachedDialogId != null) {
            Log.d("PlayerActivity", "Report max stream reach dialog ended");
            this.reportUiModelessViewSessionEnded(IClientLogging$ModalView.maxStreamsReached, mMaxStreamsReachedDialogId);
        }
        final String mErrorDialogId = this.mErrorDialogId;
        if (mErrorDialogId != null) {
            Log.d("PlayerActivity", "Report end of error dialog ended");
            this.reportUiModelessViewSessionEnded(IClientLogging$ModalView.errorDialog, mErrorDialogId);
        }
        this.cleanupAndExit();
        Log.d("PlayerActivity", "PlayerActivity::onStop done");
    }
    
    public void onSubtitleChange(final SubtitleScreen subtitleScreen) {
        if (this.destroyed()) {
            return;
        }
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Update subtitles " + subtitleScreen);
        }
        this.mSubtitleManager.onSubtitleChange(subtitleScreen);
    }
    
    public void onSubtitleFailed() {
        if (this.destroyed()) {
            return;
        }
        Log.d("PlayerActivity", "We failed to change subtitle");
        Toast.makeText(this.getBaseContext(), 2131493272, 1).show();
        if (this.language != null) {
            Log.d("PlayerActivity", "Try to restore previous subtitle");
            this.language.restorePreviousSubtitle();
            return;
        }
        Log.w("PlayerActivity", "Unable to restore previous subtitle, lang is null. This should NOT happen!");
    }
    
    public void onSurface2Visibility(final boolean b) {
        if (Log.isLoggable()) {
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
            this.runInUiThread(new PlayerActivity$14(this));
            return;
        }
        this.runInUiThread(new PlayerActivity$15(this));
    }
    
    public void onUpdatePts(final int n) {
        this.mScreen.getPostPlay().updatePlaybackPosition(n);
        PinVerifier.getInstance().registerPlayEvent(this.mAsset.isPinProtected());
    }
    
    protected void onUserLeaveHint() {
        Log.d("PlayerActivity", "onUserLeaveHint ");
    }
    
    public void onVideoSizeChanged(final int mWidth, final int mHeight) {
        if (mWidth == 0 || mHeight == 0) {
            Log.e("PlayerActivity", "invalid aspect ratio width(" + mWidth + ") or aspect ratio height(" + mHeight + ")");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "MP onVideoSizeChanged: aspect ratio width " + mWidth + ", aspect ratio height " + mHeight);
        }
        this.setSurface(this.mWidth = mWidth, this.mHeight = mHeight, false);
    }
    
    public void onWindowFocusChanged(final boolean b) {
        if (Log.isLoggable()) {
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
        this.runInUiThread(new PlayerActivity$12(this));
    }
    
    @Override
    public void performUpAction() {
        UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.actionBarBackButton, this.getUiScreen(), this.getDataContext());
        this.finish();
    }
    
    public void playNextVideo(final Playable playable, final PlayContext playContext, final boolean b) {
        final boolean b2 = false;
        if (this.destroyed()) {
            Log.d("PlayerActivity", "Activity already destroyed, ignore next!");
            return;
        }
        int n;
        if (this.mAsset != null && b && this.mState.noUserInteraction()) {
            n = this.mAsset.getPostPlayVideoPlayed() + 1;
        }
        else {
            n = 0;
        }
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Play next video, count " + n + ", from auto play " + b + ", no user interaction " + this.mState.noUserInteraction());
        }
        this.cleanupAndExit();
        boolean b3 = b2;
        if (!PlayerActivity.PIN_VERIFIED) {
            b3 = true;
        }
        final Asset forPostPlay = Asset.createForPostPlay(playable, playContext, n, b3);
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Asset to play next: " + forPostPlay);
        }
        if (StringUtils.isEmpty(forPostPlay.getPlayableId())) {
            Log.e("PlayerActivity", "PlayableId is null - skip playback");
            ErrorLoggingManager.logHandledException("PlayableId is null - skip playback");
            return;
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
            this.mState.setLastActionTime(SystemClock.elapsedRealtime());
            this.mState.userInteraction();
            this.repostOnEverySecondRunnable(0);
            this.startScreenUpdateTask();
        }
        if (this.mScreen != null) {
            this.mScreen.changeActionState(true);
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
        int nccpOrderNumber;
        if (audio != null) {
            if (Log.isLoggable()) {
                Log.d("PlayerActivity", "Changing initial audio to " + audio);
            }
            nccpOrderNumber = audio.getNccpOrderNumber();
        }
        else {
            Log.d("PlayerActivity", "No need to set initial audio source");
            nccpOrderNumber = -1;
        }
        final Subtitle subtitle = initialLanguage.getSubtitle();
        int nccpOrderNumber2;
        if (subtitle != null) {
            if (Log.isLoggable()) {
                Log.d("PlayerActivity", "Changing initial subtitle to " + subtitle);
            }
            nccpOrderNumber2 = subtitle.getNccpOrderNumber();
            b = true;
        }
        else {
            Log.d("PlayerActivity", "No need to set initial subtitle");
            nccpOrderNumber2 = -1;
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
    
    @Override
    protected boolean shouldAttachToolbar() {
        return false;
    }
    
    @Override
    protected boolean showAboutInMenu() {
        return false;
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
}
