// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.util.LanguageChoice;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import java.io.Serializable;
import android.media.AudioManager;
import android.widget.Toast;
import android.app.DialogFragment;
import android.view.MenuItem;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.app.Status;
import android.view.Surface;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.res.Configuration;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier;
import android.content.Intent;
import com.netflix.mediaclient.service.net.LogMobileType;
import android.view.Window;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.util.Pair;
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
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManager;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManagerFactory;
import android.os.Debug;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.app.Activity;
import android.os.SystemClock;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.media.PlayerType;
import android.content.Context;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import android.view.View;
import android.view.KeyEvent;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Parcelable;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$TapListener;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$SurfaceMeasureListener;
import android.view.SurfaceHolder$Callback;
import com.netflix.mediaclient.media.JPlayer.SecondSurface;
import com.netflix.mediaclient.service.player.subtitles.SafeSubtitleManager;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.ViewGroup;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import android.content.BroadcastReceiver;
import android.os.Handler;
import android.os.Bundle;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.media.Language;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class PlayerFragment extends NetflixFrag implements AudioManager$OnAudioFocusChangeListener, NetflixDialogFrag$DialogCanceledListenerProvider, ErrorWrapper$Callback, JPlayer$JplayerListener, IPlayer$PlayerListener, PlayContextProvider
{
    public static final String ASSET_EXTRA = "AssetExtra";
    private static final int DELAY_POST = 1000;
    public static final String DETAILS_PLAY_CONTEXT_EXTRA = "VideoDetailsPlaycontextExtra";
    public static final String DETAILS_VIDEO_ID_EXTRA = "VideoDetailsIdExtra";
    public static final String DETAILS_VIDEO_TYPE_EXTRA = "VideoDetailsTypeExtra";
    public static final int INACTIVITY_TIMEOUT = 5000;
    private static final int INVALID_TRACK_INDEX = -1;
    private static final String MEDIA_ERROR_CODE = "Media Error";
    private static final int PAUSE_LOCK_SCREEN_TIMEOUT = 120000;
    private static final long PAUSE_TIMEOUT = 900000L;
    private static final long PLAYREADY_CERT_REVOKED = 113L;
    private static final int SKIP_DELTA_MS = 30000;
    private static final String TAG = "PlayerFragment";
    public static final String TRACK_ID_PREFIX_TAG = "TRACK_ID: ";
    private static final int VOLUME_TIMEOUT = 500;
    private final Runnable allowScreenLockTimeout;
    private final SeekBar$OnSeekBarChangeListener audioPositionListener;
    private final View$OnClickListener episodesListener;
    Runnable exitButtonHandler;
    private Language language;
    private int mActionId12Count;
    private Asset mAsset;
    private ServiceAgent$ConfigurationAgentInterface mConfig;
    private final NetflixDialogFrag$DialogCanceledListener mDialogCancedledListener;
    private final AbsEpisodeView$EpisodeRowListener mEpisodeRowListener;
    private Bundle mExternalBundle;
    protected Handler mHandler;
    private int mHeight;
    private boolean mIsAssetReady;
    private boolean mIsBufferingOnPause;
    private boolean mIsListening;
    private boolean mIsSurfaceReady;
    protected boolean mIsTablet;
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
    private ViewGroup mRootLayout;
    private PlayScreen mScreen;
    private ServiceManager mServiceManager;
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
    
    public PlayerFragment() {
        this.mWidth = 0;
        this.mHeight = 0;
        this.mHandler = new Handler();
        this.mState = new PlayerWorkflowState();
        this.mReloadOnAudioTrackChange = false;
        this.mIsZoomedOut = true;
        this.mActionId12Count = 0;
        this.mRestartPlayback = false;
        this.allowScreenLockTimeout = new PlayerFragment$1(this);
        this.playPauseListener = (View$OnClickListener)new PlayerFragment$2(this);
        this.episodesListener = (View$OnClickListener)new PlayerFragment$3(this);
        this.zoomListener = (View$OnClickListener)new PlayerFragment$4(this);
        this.skipBackListener = (View$OnClickListener)new PlayerFragment$5(this);
        this.audioPositionListener = (SeekBar$OnSeekBarChangeListener)new PlayerFragment$6(this);
        this.tapListener = new PlayerFragment$7(this);
        this.onEverySecond = new PlayerFragment$8(this);
        this.surfaceListener = (SurfaceHolder$Callback)new PlayerFragment$9(this);
        this.mPlayerSuspendIntentReceiver = new PlayerFragment$10(this);
        this.surfaceMeasureListener = new PlayerFragment$12(this);
        this.pauseTimeout = new PlayerFragment$15(this);
        this.exitButtonHandler = new PlayerFragment$16(this);
        this.mNetworkChangeReceiver = new PlayerFragment$17(this);
        this.mEpisodeRowListener = new PlayerFragment$19(this);
        this.mDialogCancedledListener = new PlayerFragment$20(this);
    }
    
    private boolean addError(final NccpError nccpError) {
        final ErrorDescriptor handler = PlayerErrorDialogDescriptorFactory.getHandler(this, nccpError);
        if (handler == null) {
            Log.w("PlayerFragment", "We decided to ignore " + nccpError);
            return false;
        }
        final AlertDialogFactory$AlertDialogDescriptor data = handler.getData();
        if (data == null) {
            Log.d("PlayerFragment", "Metadata does not exist, error should not be reported to an user");
            return false;
        }
        this.reportStartPlayEnded(this, RootCause.clientFailure, ActionOnUIError.displayedError, data.getMessage(), null, nccpError);
        if (this.mServiceManager.getErrorHandler() != null) {
            this.mServiceManager.getErrorHandler().addError(new PlaybackErrorDescriptor(data));
        }
        return true;
    }
    
    private boolean addMediaError(final Error error) {
        if (this.handlePlayReadyRevocation(error)) {
            this.reportStartPlayEnded(this, RootCause.clientFailure, ActionOnUIError.handledSilently, null, null, error);
            Log.d("PlayerFragment", "Playready certificate revocated, do not display error!");
            this.finish();
            return true;
        }
        final ErrorDescriptor handler = PlayerErrorDialogDescriptorFactory.getHandler(this, error);
        if (handler == null) {
            Log.w("PlayerFragment", "We decided to ignore " + error);
            return false;
        }
        final AlertDialogFactory$AlertDialogDescriptor data = handler.getData();
        if (data == null) {
            Log.d("PlayerFragment", "Metadata does not exist, error should not be reported to an user");
            return false;
        }
        this.reportStartPlayEnded(this, RootCause.clientFailure, ActionOnUIError.displayedError, data.getMessage(), null, error);
        if (this.mServiceManager.getErrorHandler() != null) {
            this.mServiceManager.getErrorHandler().addError(new PlaybackErrorDescriptor(data));
        }
        return true;
    }
    
    private boolean canPlayerBeBackgrounded() {
        return this.isActivityValid() && this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY && this.mState.isPlayStarted() && !this.isStalled() && !this.isSeeking() && !this.mIsBufferingOnPause && this.mScreen != null && this.mScreen.getState() != PlayerUiState.PostPlay && PlayerTypeFactory.isJPlayer2(PlayerTypeFactory.getCurrentType(this.getActivity().getBaseContext())) && this.mConfig != null && !this.mConfig.isDeviceLowMem() && this.mConfig.getPlaybackConfiguration().isSuspendPlaybackEnabled();
    }
    
    private void clearPanel() {
        if (this.mScreen.getState() == PlayerUiState.PostPlay) {
            Log.d("PlayerFragment", "When in post play do NOT clear panel.");
            return;
        }
        this.mState.setLastActionTime(0L);
        this.mScreen.clearPanel();
    }
    
    private void completeInitIfReady() {
        if (!this.mIsSurfaceReady) {
            Log.d("PlayerFragment", "Surface not ready - cannot complete init");
            return;
        }
        if (!this.mIsAssetReady) {
            Log.d("PlayerFragment", "Asset not ready - cannot complete init");
            return;
        }
        if (this.mAsset == null) {
            Log.d("PlayerFragment", "Asset is null - cannot complete init");
            return;
        }
        verifyPlayToContinue(this, this.mAsset);
    }
    
    private void continueInitAfterPlayVerify() {
        Log.v("PlayerFragment", "Completing init process...");
        this.mScreen.setTitle(this.getTitleForScreen(this.mAsset));
        if (!this.mState.videoLoaded && this.loadVideo()) {
            this.mState.playerState = PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY;
        }
    }
    
    private PlayScreen$Listeners createListeners() {
        final PlayScreen$Listeners playScreen$Listeners = new PlayScreen$Listeners();
        playScreen$Listeners.videoPositionListener = (SeekBar$OnSeekBarChangeListener)new PlayerFragment$VideoPositionListener(this);
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
    
    public static PlayerFragment createPlayerFragment(final Asset asset) {
        final PlayerFragment playerFragment = new PlayerFragment();
        playerFragment.setArguments(getBundle(asset));
        return playerFragment;
    }
    
    public static PlayerFragment createPlayerFragment(final String s, final String s2, final Parcelable parcelable) {
        final PlayerFragment playerFragment = new PlayerFragment();
        playerFragment.setArguments(getBundle(s, s2, parcelable));
        return playerFragment;
    }
    
    private void doPause(final boolean b) {
        this.doPause(b, false);
    }
    
    private void doPause(final boolean b, final boolean b2) {
        if (this.mState.playerState != PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerFragment", "doPause: Invalid state, exit...:" + this.mState.playerState.getName());
            this.cleanupAndExit();
        }
        else {
            if (this.isVolumeChangeInProgress()) {
                Log.i("PlayerFragment", "doPause: volume up or down is pressed, do not pause...");
                return;
            }
            Log.i("PlayerFragment", "doPause: paused");
            if (b || (this.mPlayer.isBufferingCompleted() && this.mPlayer.isPlaying())) {
                this.mSubtitleManager.clearPendingUpdates();
                if (!b2 && this.mScreen != null) {
                    this.mScreen.getBottomPanel().setMediaImage(true);
                }
                this.mPlayer.pause();
                Log.d("PlayerFragment", "Pause, release awake clock after 2 minutes.");
                this.mHandler.postDelayed(this.allowScreenLockTimeout, 120000L);
                this.mHandler.postDelayed(this.pauseTimeout, 900000L);
            }
        }
    }
    
    private void doSeek(final int n, final boolean b) {
        if (this.mState.playerState != PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerFragment", "doSeek: Invalid state, exit...:" + this.mState.playerState.getName());
            this.cleanupAndExit();
        }
        else if (this.mScreen != null) {
            Log.w("PlayerFragment", "Playout seek...");
            if (this.mScreen != null) {
                this.mScreen.changeActionState(false);
            }
            this.mState.seekToInProgress = true;
            this.onSeek();
            if (Log.isLoggable()) {
                Log.d("PlayerFragment", "==> seekTo: " + n);
            }
            if (this.mScreen != null) {
                this.mScreen.getBottomPanel().setMediaImage(false);
            }
            this.mPlayer.seekTo(n, b);
            this.mSubtitleManager.onSubtitleRemove();
        }
    }
    
    public static Bundle getBundle(final Asset asset) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable("AssetExtra", (Parcelable)asset);
        return bundle;
    }
    
    public static Bundle getBundle(final String s, final String s2, final Parcelable parcelable) {
        final Bundle bundle = new Bundle();
        bundle.putString("VideoDetailsIdExtra", s);
        bundle.putString("VideoDetailsTypeExtra", s2);
        bundle.putParcelable("VideoDetailsPlaycontextExtra", parcelable);
        return bundle;
    }
    
    private static JSONObject getJSonSafely(final MediaEvent mediaEvent) {
        try {
            return mediaEvent.getData();
        }
        catch (JSONException ex) {
            return null;
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
                Log.v("PlayerFragment", "A button pressed");
                this.showControlScreenOverlay(false);
                this.playPauseListener.onClick((View)null);
                return true;
            }
        }
        else if (n == 21 || n == 102) {
            if (this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
                this.showControlScreenOverlay(false);
                this.skipBack();
                return true;
            }
        }
        else if ((n == 22 || n == 103) && this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            this.showControlScreenOverlay(false);
            this.skipForward();
            return true;
        }
        return false;
    }
    
    private boolean handlePlayReadyRevocation(final MediaEvent mediaEvent) {
        if (!(mediaEvent instanceof NccpActionId)) {
            Log.d("PlayerFragment", "Not action ID error");
            return false;
        }
        if (((NccpActionId)mediaEvent).getReasonCode() == 113L) {
            Log.e("PlayerFragment", "Playready certificate revocated, we do not have backup player, report an error!");
            return false;
        }
        Log.e("PlayerFragment", "Regular error, handle as popup");
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
        if (this.isActivityValid()) {
            this.getWindow().addFlags(128);
        }
        Log.d("PlayerFragment", "KEEP_SCREEN: ON");
        this.mHandler.removeCallbacks(this.pauseTimeout);
        this.mHandler.removeCallbacks(this.allowScreenLockTimeout);
    }
    
    private boolean loadVideo() {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "Do load Video " + this.hashCode());
        }
        if (!this.isActivityValid()) {
            return false;
        }
        if (!ConnectivityUtils.isConnectedOrConnecting((Context)this.getActivity())) {
            Log.d("PlayerFragment", "Raising no connectivity warning");
            this.noConnectivityWarning();
            return false;
        }
        if (!this.handleConnectivityCheck()) {
            Log.d("PlayerFragment", "Network check fails");
            return false;
        }
        final Asset mAsset = this.mAsset;
        if (mAsset == null) {
            Log.e("PlayerFragment", "asset is null, this should not happen!");
            return false;
        }
        while (true) {
            this.mState.videoLoaded = true;
            while (true) {
                Label_0411: {
                    try {
                        final PlayerType currentType = PlayerTypeFactory.getCurrentType((Context)this.getActivity());
                        if (currentType == PlayerType.device10 || currentType == PlayerType.device11) {
                            if (Log.isLoggable()) {
                                Log.d("PlayerFragment", "Set JPlayerListener: " + this);
                            }
                            this.mPlayer.setJPlayerListener(this);
                        }
                        int playbackBookmark = mAsset.getPlaybackBookmark();
                        if (Log.isLoggable()) {
                            Log.d("PlayerFragment", this.hashCode() + " Do Play from position " + playbackBookmark);
                            Log.d("PlayerFragment", this.hashCode() + " Do Play asset " + mAsset.toString());
                        }
                        if (playbackBookmark < 0) {
                            Log.d("PlayerFragment", this.hashCode() + " Invalid bookmark, reset to 0");
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
                        Log.e("PlayerFragment", "Exception in video preparation", t);
                        this.addError(new NccpActionId(3, "", this.getString(2131165443), "handleActionId", "ACTION_ID", 0, null));
                        return false;
                    }
                }
                continue;
            }
        }
    }
    
    private void noConnectivityWarning() {
        this.getNetflixActivity().displayDialog(AlertDialogFactory.createDialog((Context)this.getActivity(), this.mHandler, new AlertDialogFactory$AlertDialogDescriptor(null, this.getString(2131165551), this.getString(2131165485), this.exitButtonHandler)));
    }
    
    private void nonWifiPlayWarning() {
        ThreadUtils.assertOnMain();
        this.getNetflixActivity().displayDialog(AlertDialogFactory.createDialog((Context)this.getActivity(), this.mHandler, new AlertDialogFactory$AlertDialogDescriptor(null, this.getString(2131165482), this.getString(2131165485), this.exitButtonHandler)));
    }
    
    private void onSeek() {
        ThreadUtils.assertOnMain();
        Log.d("PlayerFragment", "onSeek");
        this.showLoading();
    }
    
    private void releaseLockOnScreen() {
        if (this.isActivityValid()) {
            this.getWindow().clearFlags(128);
        }
        Log.d("PlayerFragment", "KEEP_SCREEN: OFF");
    }
    
    private void removeDialogFragmentIfShown() {
        if (this.getNetflixActivity().isDialogFragmentVisible()) {
            this.getNetflixActivity().removeDialogFrag();
        }
    }
    
    private void reportStartPlayEnded(final PlayerFragment playerFragment, final RootCause rootCause, final ActionOnUIError actionOnUIError, final String s, final Integer n, final MediaEvent mediaEvent) {
        if (playerFragment.getState().getPlayStartInProgress().getAndSet(false)) {
            final UIError uiError = StatusUtils.createUiError("Media Error", actionOnUIError, s, true, rootCause, ConsolidatedLoggingUtils.createDebug(null, getJSonSafely(mediaEvent)));
            if (this.mServiceManager.getConfiguration() != null) {
                UserActionLogUtils.reportPlayActionEnded((Context)playerFragment.getNetflixActivity(), IClientLogging$CompletionReason.failed, uiError, n, this.mServiceManager.getConfiguration().getCurrentPlayerType());
            }
        }
    }
    
    private void repostOnEverySecondRunnable(final int n) {
        this.mHandler.removeCallbacks(this.onEverySecond);
        this.mHandler.postDelayed(this.onEverySecond, (long)n);
    }
    
    private boolean requestDetailsIfNeeded(final ServiceManager serviceManager) {
        final Bundle arguments = this.getArguments();
        final String string = arguments.getString("VideoDetailsIdExtra");
        if (!StringUtils.isNotEmpty(string)) {
            this.mIsAssetReady = true;
            Log.d("PlayerFragment", "Regular playback");
            return false;
        }
        Log.d("PlayerFragment", "Intent has EXTRA_GET_DETAILS_VIDEO_ID - fetching details...");
        final VideoType create = VideoType.create(arguments.getString("VideoDetailsTypeExtra"));
        final PlayContext playContext = (PlayContext)arguments.getParcelable("VideoDetailsPlaycontextExtra");
        if (create == VideoType.MOVIE) {
            serviceManager.getBrowse().fetchMovieDetails(string, new PlayerFragment$FetchVideoDetailsForPlaybackCallback(this, playContext));
            return true;
        }
        if (create == VideoType.SHOW) {
            serviceManager.getBrowse().fetchShowDetails(string, null, BrowseExperience.shouldLoadKubrickLeavesInDetails(), new PlayerFragment$FetchVideoDetailsForPlaybackCallback(this, playContext));
            return true;
        }
        throw new IllegalStateException("Invalid billboard video type: " + create);
    }
    
    private void setFragmentContentView(final int n) {
        this.mRootLayout.removeAllViews();
        this.mRootLayout.addView(this.getActivity().getLayoutInflater().inflate(n, (ViewGroup)null), (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -1));
    }
    
    private void setProgress() {
        if (this.mPlayer != null && !this.mState.draggingInProgress) {
            final int currentProgress = this.mPlayer.getCurrentProgress();
            final int duration = this.mPlayer.getDuration();
            if (this.mPlayer.canUpdatePosition(currentProgress)) {
                if (Log.isLoggable()) {
                    Log.d("PlayerFragment", "PA#setProgress:: Position: " + currentProgress + ", duration: " + duration);
                }
                this.mScreen.setProgress(currentProgress, duration, true);
                return;
            }
            if (Log.isLoggable()) {
                Log.w("PlayerFragment", "PA#setProgress:: Draging in progress? " + currentProgress + ", duration: " + duration);
            }
        }
    }
    
    private void setSurface(final int videoWidth, final int videoHeight) {
        if (this.mScreen.getSurfaceView() == null) {
            return;
        }
        this.mScreen.getSurfaceView().setVideoWidth(videoWidth);
        this.mScreen.getSurfaceView().setVideoHeight(videoHeight);
    }
    
    private void showControlScreenOverlay(final boolean b) {
        final PlayScreen mScreen = this.mScreen;
        if (mScreen != null) {
            mScreen.onTap(b);
            return;
        }
        Log.w("PlayerFragment", "Screen is null!");
    }
    
    private void showLoading() {
        this.mState.stalled = true;
        if (this.mScreen != null) {
            this.mScreen.setBufferingOverlayVisibility(true);
        }
    }
    
    private void skip(int n) {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "Skip back " + n + " ms");
        }
        this.mState.setLastActionTime(SystemClock.elapsedRealtime());
        this.mState.userInteraction();
        if ((n += this.mPlayer.getCurrentProgress()) < 0) {
            Log.d("PlayerFragment", "Go back to start, instead of trying to go minus!");
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
        this.getNetflixActivity().displayDialog(AlertDialogFactory.createDialog((Context)this.getActivity(), this.mHandler, new AlertDialogFactory$AlertDialogDescriptor(null, this.getString(2131165541), this.getString(2131165485), this.exitButtonHandler)));
    }
    
    private void startScreenUpdate() {
        if (this.mScreen != null && this.isActivityValid()) {
            this.mState.setLastActionTime(SystemClock.elapsedRealtime());
            this.startScreenUpdateTask();
        }
    }
    
    private void stopPlayback() {
        Log.d("PlayerFragment", "stopPlayback");
        if (this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_SRVCMNGR_READY || this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            this.mPlayer.close();
            this.mIsListening = false;
            this.mPlayer.removePlayerListener(this);
            this.mState.playerState = PlayerFragment$PlayerFragmentState.ACTIVITY_NOTREADY;
            this.mScreen.getBottomPanel().enableButtons(false);
            if (this.mState.playStarted && this.mAsset != null) {
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
                Log.d("PlayerFragment", "Right on target, no need to ajust seekbar position " + n + " [ms]");
            }
        }
        else if (Log.isLoggable()) {
            Log.d("PlayerFragment", "Progres : " + n + " [ms] vs. bif position " + n2 + " [ms]");
            return n2;
        }
        return n2;
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
                    Log.e("PlayerFragment", "Got exception inside toLongSafe: " + t);
                    return 0L;
                }
            }
        }
        return 0L;
    }
    
    private void tryFinishActivity() {
        final Activity activity = this.getActivity();
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }
    
    private void updateMetadataIfNeeded() {
        if (PreferenceUtils.getBooleanPref(this.getActivity().getBaseContext(), "ui.playeroverlay", false)) {
            this.mScreen.setDebugDataVisibility(true);
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            String string = "N/A";
            while (true) {
                Label_0499: {
                    if (this.mPlayer == null) {
                        break Label_0499;
                    }
                    final PlayoutMetadata playoutMetadata = this.mPlayer.getPlayoutMetadata();
                    if (playoutMetadata == null) {
                        break Label_0499;
                    }
                    final int n = playoutMetadata.position / 60000;
                    final int n2 = playoutMetadata.duration / 60000;
                    sb.append(playoutMetadata.instantBitRate).append("/");
                    sb.append(playoutMetadata.targetBitRate).append("/");
                    if (playoutMetadata.isSuperHD) {
                        sb.append(this.getString(2131165554));
                    }
                    else if (playoutMetadata.isHD) {
                        sb.append(this.getString(2131165449));
                    }
                    else {
                        sb.append(this.getString(2131165528));
                    }
                    sb2.append(playoutMetadata.language).append("/");
                    sb2.append(playoutMetadata.getAudioChannel()).append("/");
                    sb2.append(playoutMetadata.getAudioTrackType());
                    final SubtitleConfiguration subtitleConfiguration = this.mPlayer.getSubtitleConfiguration();
                    if (subtitleConfiguration != null) {
                        string = subtitleConfiguration.getString((Context)this.getActivity());
                    }
                    Log.d("PlayerFragment", "Subtitle config: " + string);
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
                    final String string2 = this.getString(2131165419, new Object[] { "Release", AndroidManifestUtils.getVersionCode(this.getActivity().getBaseContext()), (int)(Debug.getNativeHeapAllocatedSize() / 1048576L), "UI Version", n, n2, sb.toString(), sb2.toString(), PlayerTypeFactory.getCurrentType(this.getActivity().getBaseContext()).getDescription(), s, s2, SubtitleManagerFactory.getSubtitleManagerLabel(this.mSubtitleManager), DrmManagerRegistry.getDrmInfo() });
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
        final PostPlayFactory$PostPlayType postPlayType = PostPlay.getPostPlayType(this.getNetflixActivity(), this.mAsset);
        this.setFragmentContentView(PlayScreen.resolveContentView(postPlayType));
        this.getNetflixActivity().setSupportActionBar((Toolbar)this.mRootLayout.findViewById(2131624408));
        final ServiceManager mServiceManager = this.mServiceManager;
        this.mPlayer = mServiceManager.getPlayer();
        this.mConfig = mServiceManager.getConfiguration();
        if (this.mPlayer == null || this.mConfig == null) {
            Log.d("PlayerFragment", "Unable to receive handle to player object, finishing activity ");
            this.tryFinishActivity();
            return;
        }
        mServiceManager.cancelAllImageLoaderRequests();
        this.mPlayer.addPlayerListener(this);
        this.mIsListening = true;
        this.mScreen = PlayScreen.createInstance(this, this.createListeners(), postPlayType);
        Log.d("PlayerFragment", "Checking episode visibility");
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
        this.getNetflixActivity().registerReceiverWithAutoUnregister(this.mNetworkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.mPlayerSuspendNotification = new PlayerSuspendNotification(this.getNetflixActivity(), mServiceManager);
        this.getNetflixActivity().registerReceiverWithAutoUnregister(this.mPlayerSuspendIntentReceiver, PlayerSuspendNotification.getNotificationIntentFilter());
        if (AndroidUtils.getAndroidVersion() >= 16 && (PlayerTypeFactory.isJPlayerBase(PlayerTypeFactory.getCurrentType((Context)this.getActivity())) || PlayerTypeFactory.isJPlayer(PlayerTypeFactory.getCurrentType((Context)this.getActivity())))) {
            this.mSurface2 = new SecondSurface((TextureView)this.mRootLayout.findViewById(2131624404));
        }
        this.mState.playerState = PlayerFragment$PlayerFragmentState.ACTIVITY_SRVCMNGR_READY;
    }
    
    private static void verifyPlayToContinue(final PlayerFragment playerFragment, final Asset asset) {
        if (!asset.isAgeProtected() && asset.isPinVerified()) {
            Log.d("PlayerFragment", String.format("nf_pin PlayerActivity pinVerification skipped - ageProtected: %b, pinVerified:%b, pinProtected:%b", asset.isAgeProtected(), asset.isPinVerified(), asset.isPinProtected()));
            playerFragment.continueInitAfterPlayVerify();
            return;
        }
        PlayVerifier.verify(playerFragment.getNetflixActivity(), asset.isAgeProtected(), asset.isPinProtected(), new PlayVerifierVault(PlayVerifierVault$PlayInvokedFrom.PLAYER.getValue(), asset));
    }
    
    public void changeLanguage(final Language language, final boolean b) {
        ThreadUtils.assertOnMain();
        if (language != null) {
            this.setLanguage(language);
            this.mPlayer.selectTracks(language.getSelectedAudio(), language.getSelectedSubtitle());
            if (language.getSelectedSubtitle() == null) {
                Log.d("PlayerFragment", "Disable subtitles, none is selected");
                this.mSubtitleManager.clear();
            }
            language.commit();
            if (b) {
                Log.d("PlayerFragment", "Starting playback by seek with forceRebuffer to current position");
                this.mReloadOnAudioTrackChange = true;
                this.onStalled();
            }
        }
        Log.d("PlayerFragment", "Language change should be completed");
    }
    
    protected void cleanupAndExit() {
        if (this.mPlayerBackgrounded) {
            if (this.mPlayerSuspendNotification != null) {
                this.mPlayerSuspendNotification.cancelNotification();
            }
            this.mPlayerBackgrounded = false;
        }
        if (this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            this.stopPlayback();
        }
        this.stopScreenUpdateTask();
        this.mState.playerState = PlayerFragment$PlayerFragmentState.ACTIVITY_NOTREADY;
        Log.d("PlayerFragment", "cleanupAndExit calling finish");
        this.tryFinishActivity();
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
            Log.d("PlayerFragment", "Dismissing buffering progress bar...");
            this.mState.seekToInProgress = false;
            this.mState.audioSeekToInProgress = false;
            this.mState.stalled = false;
            this.mIsBufferingOnPause = false;
            this.keepScreenOn();
            if (this.mScreen != null) {
                this.mScreen.changeActionState(true);
                this.mScreen.setBufferingOverlayVisibility(false);
                Log.d("PlayerFragment", "Remove bif image if it was visible. Only for phones!!!");
                if (!this.mIsTablet) {
                    this.mScreen.stopBif();
                }
            }
            this.startScreenUpdate();
        }
        this.mScreen.getBottomPanel().setSeekbarTrackingEnabled(true);
        if (this.isActivityValid() && !this.getActivity().hasWindowFocus()) {
            Log.d("PlayerFragment", "App is not in focus, pause");
            this.doPause();
        }
    }
    
    public void doSeek(final int n) {
        this.doSeek(n, false);
    }
    
    public void doUnpause() {
        if (this.mState.playerState != PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerFragment", "doUnPause: Invalid state, exit...:" + this.mState.playerState.getName());
            this.cleanupAndExit();
        }
        else {
            Log.i("PlayerFragment", "doPlay: resume");
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
        if (this.mState.playerState != PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerFragment", "doZoomIn: Invalid state, exit...:" + this.mState.playerState.getName());
            this.cleanupAndExit();
        }
        else {
            Log.d("PlayerFragment", "doZoomOut: start");
            final PlayScreen mScreen = this.mScreen;
            if (mScreen != null) {
                mScreen.setZoom(true);
                mScreen.getBottomPanel().setZoomImage(false);
            }
        }
    }
    
    public void doZoomOut() {
        if (this.mState.playerState != PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            Log.i("PlayerFragment", "doZoomIn: Invalid state, exit...:" + this.mState.playerState.getName());
            this.cleanupAndExit();
        }
        else {
            Log.i("PlayerFragment", "doZoomIn: start");
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
    
    public void finish() {
        if (Log.isLoggable()) {
            Log.i("PlayerFragment", "Finishing the activity: " + this.getActivity());
        }
        if (this.isActivityValid()) {
            this.getActivity().finish();
        }
        else if (Log.isLoggable()) {
            Log.w("PlayerFragment", "Got wrong activity state so we will not finish the following activity: " + this.getActivity());
        }
    }
    
    public Asset getCurrentAsset() {
        return this.mAsset;
    }
    
    public NetflixDialogFrag$DialogCanceledListener getDialogCanceledListener() {
        return this.mDialogCancedledListener;
    }
    
    public AbsEpisodeView$EpisodeRowListener getEpisodeRowListener() {
        return this.mEpisodeRowListener;
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
    
    public ServiceManager getServiceManager() {
        return this.getNetflixActivity().getServiceManager();
    }
    
    public PlayerWorkflowState getState() {
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
            return this.getResources().getString(2131165639, new Object[] { asset.getParentTitle(), asset.getSeasonNumber(), asset.getEpisodeNumber(), asset.getTitle() });
        }
        return this.getResources().getString(2131165640, new Object[] { asset.getTitle() });
    }
    
    public ResourceHelper getUiResources() {
        return this.mResources;
    }
    
    public Window getWindow() {
        return this.getActivity().getWindow();
    }
    
    public boolean handleBackPressed() {
        if (this.mScreen != null && this.mScreen.inInterruptedOrPendingInterrupted() && this.mScreen.getPostPlay() != null) {
            this.mScreen.getPostPlay().moveFromInterruptedToPlaying();
            return true;
        }
        return false;
    }
    
    boolean handleConnectivityCheck() {
        Log.i("PlayerFragment", "Check connection");
        final LogMobileType connectionType = ConnectivityUtils.getConnectionType((Context)this.getNetflixActivity());
        if (connectionType == null) {
            Log.i("PlayerFragment", "No internet connection. Since this is expected state on Verizons' phones, skip");
            return true;
        }
        if (connectionType == LogMobileType._2G) {
            Log.i("PlayerFragment", "2G only, alert");
            this.slowNetworkWarning();
            return false;
        }
        if (connectionType == LogMobileType.WIFI) {
            Log.i("PlayerFragment", "WiFi connection, do playback");
            return true;
        }
        final boolean booleanPref = PreferenceUtils.getBooleanPref((Context)this.getActivity(), "nf_play_no_wifi_warning", false);
        Log.i("PlayerFragment", "3G Preference setting: " + booleanPref);
        if (booleanPref) {
            Log.w("PlayerFragment", "We should warn user if he is on NON WIFI network!");
            this.nonWifiPlayWarning();
            return false;
        }
        Log.d("PlayerFragment", "Warning is not required, proceed with playback");
        return true;
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
    
    public boolean isTablet() {
        return this.mIsTablet;
    }
    
    public void notifyOthersOfPlayStart() {
        this.getActivity().sendBroadcast(this.mAsset.toIntent(new Intent("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START")));
        Log.v("PlayerFragment", "Intent PLAYER_PLAY_START sent");
    }
    
    public void notifyOthersOfPlayStop() {
        PinVerifier.getInstance().registerPlayEvent(this.mAsset.isPinProtected());
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP");
        this.mAsset.setPlaybackBookmark(this.mPlayer.getCurrentPositionMs() / 1000);
        this.getActivity().sendBroadcast(this.mAsset.toIntent(intent));
        Log.v("PlayerFragment", "Intent PLAYER_PLAY_STOP sent");
    }
    
    public void onAudioChange(final int n) {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "onAudioChange" + n);
        }
        if (!this.isActivityValid()) {
            Log.w("PlayerFragment", "Activity isn't already in a valid state - no need to update the audio");
        }
        else if (this.mReloadOnAudioTrackChange) {
            Log.d("PlayerFragment", "Starting playback by seek with forceRebuffer to current position");
            this.mReloadOnAudioTrackChange = false;
            this.doSeek(this.mPlayer.getCurrentPositionMs(), true);
        }
    }
    
    public void onAudioFocusChange(final int n) {
        String string = null;
        switch (n) {
            default: {
                string = "unknown audio focus: " + n;
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
            Log.d("PlayerFragment", "onAudioFocusChange " + string);
        }
    }
    
    public void onBandwidthChange(final int n) {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "bandwidth changed to [Kbps]: " + n);
        }
    }
    
    public void onBufferingUpdate(final int n) {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "MP onBufferingUpdate " + n + "%");
        }
    }
    
    public void onCompletion() {
        Log.d("PlayerFragment", "onCompletion, check if we are in postplay");
        this.stopScreenUpdateTask();
        if (this.mScreen != null && this.mScreen.canExitPlaybackEndOfPlay()) {
            this.cleanupAndExit();
            return;
        }
        Log.d("PlayerFragment", "In PostPlay, allow screen to lock after timeout...");
        this.mHandler.postDelayed(this.allowScreenLockTimeout, 120000L);
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.hardKeyboardHidden == 1) {
            Log.d("PlayerFragment", "keyboard out");
        }
        else if (configuration.hardKeyboardHidden == 2) {
            Log.d("PlayerFragment", "keyboard in");
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setHasOptionsMenu(true);
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "onCreate started " + this.hashCode());
        }
        this.keepScreenOn();
        AndroidUtils.logDeviceDensity(this.getActivity());
        this.getWindow().getAttributes().buttonBrightness = 0.0f;
        this.mState.reset();
        UserActionLogUtils.reportPlayActionStarted((Context)this.getActivity(), null, IClientLogging$ModalView.playback);
        this.mState.playStartInProgress.set(true);
        this.mSubtitleManager = new SafeSubtitleManager(this);
        ConsolidatedLoggingUtils.pauseReporting((Context)this.getActivity());
        Log.d("PlayerFragment", "onCreate done");
    }
    
    public void onCreateOptionsMenu(final Menu mPostponedPanelMenu, final MenuInflater menuInflater) {
        super.onCreateOptionsMenu(mPostponedPanelMenu, menuInflater);
        if (this.mScreen == null || this.mScreen.getTopPanel() == null || this.getView() == null) {
            Log.w("PlayerFragment", "onCreateOptionsMenu() was triggered before UI was initialized. Scheduling panel menu update to be called later.");
            this.mPostponedPanelMenu = mPostponedPanelMenu;
            return;
        }
        this.mScreen.getTopPanel().onCreateOptionsMenu(mPostponedPanelMenu);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return (View)(this.mRootLayout = (ViewGroup)new LinearLayout((Context)this.getActivity()));
    }
    
    @Override
    public void onDestroy() {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "====> Destroying PlayerFragment " + this.hashCode());
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
        if (this.mSubtitleManager != null) {
            this.mSubtitleManager.clear();
        }
        if (this.mSurface2 != null) {
            this.mSurface2 = null;
        }
        ConsolidatedLoggingUtils.resumeReporting((Context)this.getActivity(), false);
        super.onDestroy();
        Log.d("PlayerFragment", "====> Destroying PlayerFragment done");
    }
    
    public Surface onGetTextureSurface() {
        final SecondSurface mSurface2 = this.mSurface2;
        if (mSurface2 != null) {
            return mSurface2.getSurface();
        }
        return null;
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (Log.isLoggable()) {
            Log.v("PlayerFragment", "onKeyDown: " + keyEvent);
        }
        this.mState.setLastActionTime(SystemClock.elapsedRealtime());
        this.mState.userInteraction();
        if (n == 4) {
            if (this.mScreen == null || !this.mScreen.inInterruptedOrPendingInterrupted()) {
                Log.d("PlayerFragment", "Back...");
                this.handleBackPressed();
                this.cleanupAndExit();
                return true;
            }
            Log.d("PlayerFragment", "Back used to dismiss interrupter overlay, send it back to framework");
        }
        else {
            if (n == 84) {
                return true;
            }
            if (n == 82) {
                keyEvent.startTracking();
                return true;
            }
            if (this.handleControlButtonPress(n, keyEvent)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void onManagerReady(final ServiceManager mServiceManager, final Status status) {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "ServiceManager ready: " + status.getStatusCode());
        }
        ThreadUtils.assertOnMain();
        this.mServiceManager = mServiceManager;
        this.mIsTablet = this.getNetflixActivity().isTablet();
        this.mResources = ResourceHelper.newInstance(this.mIsTablet);
        if (!this.requestDetailsIfNeeded(mServiceManager)) {
            this.updateUI();
        }
        else if (this.isActivityValid()) {
            int fragmentContentView;
            if (DeviceUtils.isTabletByContext(this.getActivity().getBaseContext())) {
                fragmentContentView = 2130903194;
            }
            else {
                fragmentContentView = 2130903190;
            }
            this.setFragmentContentView(fragmentContentView);
        }
        if (mServiceManager.getErrorHandler() != null) {
            mServiceManager.getErrorHandler().clear();
        }
        if (this.getActivity() != null) {
            NflxProtocolUtils.reportUserOpenedNotification(mServiceManager, this.getActivity().getIntent());
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.e("PlayerFragment", "NetflixService is NOT available!");
        this.mServiceManager = null;
        this.cleanupAndExit();
    }
    
    public void onMediaError(final Error error) {
        if (Log.isLoggable()) {
            Log.e("PlayerFragment", "Media Error " + error);
        }
        this.addMediaError(error);
    }
    
    public void onNccpError(final NccpError nccpError) {
        if (Log.isLoggable()) {
            Log.e("PlayerFragment", "Nccp Error " + nccpError);
        }
        this.addError(nccpError);
    }
    
    public void onNrdFatalError() {
        Log.w("PlayerFragment", "onNrdFatalError");
        if (!this.isActivityValid()) {
            Log.w("PlayerFragment", "Activity isn't already in a valid state - no need to show the error dialog");
            return;
        }
        this.getNetflixActivity().displayDialog(AlertDialogFactory.createDialog((Context)this.getActivity(), this.mHandler, new AlertDialogFactory$AlertDialogDescriptor("", this.getString(2131165249), null, new PlayerFragment$18(this))));
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        return this.mScreen.getTopPanel().onOptionsItemSelected(menuItem) || super.onOptionsItemSelected(menuItem);
    }
    
    public void onPause() {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "onPause called..." + this.hashCode());
        }
        this.mIsBufferingOnPause = (this.isStalled() || this.isSeeking());
        if (this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            Log.d("PlayerFragment", "Screen is on, just pause");
            this.doPause();
        }
        final PostPlay postPlaySafely = this.getPostPlaySafely();
        if (postPlaySafely != null) {
            postPlaySafely.onPause();
        }
        Log.d("PlayerFragment", "onPause called done");
        super.onPause();
    }
    
    public void onPlayVerified(final boolean b, final PlayVerifierVault playVerifierVault) {
        Log.d("nf_pin", String.format("%s onPlayVerification vault: %s", PlayerFragment.class.getSimpleName(), playVerifierVault));
        if (b && PlayVerifierVault$PlayInvokedFrom.PLAYER.getValue().equals(playVerifierVault.getInvokeLocation())) {
            this.continueInitAfterPlayVerify();
            return;
        }
        Log.d("PlayerFragment", "Age/Pin verification failed cannot proceed - stop playback");
        this.cleanupAndExit();
    }
    
    public void onPlaying() {
        synchronized (this) {
            Log.d("PlayerFragment", "Playout (re)started");
            if (this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
                this.doPlaying();
            }
            else {
                Log.e("PlayerFragment", "onPlaying not in correct state, ActivityState: " + this.mState.playerState.getName());
                this.cleanupAndExit();
            }
        }
    }
    
    public void onPrepared() {
        Log.d("PlayerFragment", "onPrepared called");
        ThreadUtils.assertOnMain();
        if (this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            try {
                this.mState.videoPrepared = true;
                this.mWidth = this.mPlayer.getVideoWidth();
                this.mHeight = this.mPlayer.getVideoHeight();
                if (this.mWidth != 0 && this.mHeight != 0 && this.mScreen != null) {
                    if (Log.isLoggable()) {
                        Log.d("PlayerFragment", "====> width = " + this.mWidth + ", height" + this.mHeight);
                    }
                    this.setSurface(this.mWidth, this.mHeight);
                    this.initSurfaceFixedSize(this.mWidth, this.mHeight);
                    Log.d("PlayerFragment", "Play");
                    this.mPlayer.play();
                    this.mScreen.setZoomEnabledByPlayertype(this.isZoomEnabledByPlayerType());
                }
                this.selectInitialTracks();
                return;
            }
            catch (Exception ex) {
                Log.e("PlayerFragment", "Failed to start player", ex);
                this.cleanupAndExit();
                return;
            }
        }
        Log.e("PlayerFragment", "onPrepared not in correct state, ActivityState: " + this.mState.playerState.getName());
        this.cleanupAndExit();
    }
    
    public void onResume() {
        super.onResume();
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "onResume: back " + this.hashCode());
        }
        final PostPlay postPlaySafely = this.getPostPlaySafely();
        if (postPlaySafely != null) {
            postPlaySafely.onResume();
        }
    }
    
    public void onRetryRequested() {
        if (Log.isLoggable()) {
            Log.v("PlayerFragment", "onRetryRequested()");
        }
        final DialogFragment dialogFragment = this.getNetflixActivity().getDialogFragment();
        if (dialogFragment instanceof ErrorWrapper$Callback) {
            if (Log.isLoggable()) {
                Log.v("PlayerFragment", "Calling onRetryRequested for fragment: " + dialogFragment);
            }
            ((ErrorWrapper$Callback)dialogFragment).onRetryRequested();
        }
        else if (Log.isLoggable()) {
            Log.v("PlayerFragment", "frag does not implement ErrorWrapper.Callback: " + dialogFragment);
        }
    }
    
    public void onSeekComplete() {
        Log.d("PlayerFragment", "MP onSeekComplete");
        if (this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
            this.mState.draggingInProgress = false;
            this.startScreenUpdateTask();
            this.doPlaying();
            return;
        }
        Log.e("PlayerFragment", "onSeekComplete not in correct state, ActivityState: " + this.mState.playerState.getName());
        this.cleanupAndExit();
    }
    
    public void onStalled() {
        synchronized (this) {
            Log.w("PlayerFragment", "Playout stalled");
            ThreadUtils.assertOnMain();
            if (this.mScreen != null && this.isActivityValid()) {
                Log.d("PlayerFragment", "Playout stalled, clear pending updates");
                this.mSubtitleManager.clearPendingUpdates();
                if (this.mState.seekToInProgress || this.mState.audioSeekToInProgress) {
                    Log.d("PlayerFragment", "Seek in progress...");
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
                        Log.d("PlayerFragment", "Enabled Toast");
                        Toast.makeText((Context)this.getActivity(), 2131165457, 1).show();
                    }
                    this.mScreen.setBufferingOverlayVisibility(true);
                }
            }
        }
    }
    
    public void onStart() {
        super.onStart();
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "onStart " + this.hashCode());
        }
        final AudioManager audioManager = (AudioManager)this.getActivity().getSystemService("audio");
        if (audioManager != null) {
            audioManager.requestAudioFocus((AudioManager$OnAudioFocusChangeListener)this, 3, 1);
        }
        else {
            Log.e("PlayerFragment", "Audio manager not found. Unable to ask for audio focus!");
        }
        Bundle bundle;
        if (this.mExternalBundle != null) {
            bundle = this.mExternalBundle;
        }
        else {
            bundle = this.getArguments();
        }
        if (bundle == null) {
            Log.e("PlayerFragment", "This should NEVER happen, bundle is null!");
            this.tryFinishActivity();
        }
        else {
            this.mAsset = (Asset)bundle.getParcelable("AssetExtra");
            if (Log.isLoggable()) {
                Log.d("PlayerFragment", "Asset received: " + this.mAsset);
            }
            Log.d("PlayerFragment", "onStart done");
            if (Log.isLoggable()) {
                final StringBuilder append = new StringBuilder().append("TRACK_ID: ");
                Serializable value;
                if (this.mAsset == null) {
                    value = "n/a";
                }
                else {
                    value = this.mAsset.getTrackId();
                }
                Log.v("PlayerFragment", append.append(value).toString());
            }
        }
    }
    
    public void onStarted() {
        synchronized (this) {
            Log.d("PlayerFragment", "Playout started");
            ThreadUtils.assertOnMain();
            final PlayerType currentType = PlayerTypeFactory.getCurrentType((Context)this.getNetflixActivity());
            if (this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
                final int currentProgress = this.mPlayer.getCurrentProgress();
                final int duration = this.mPlayer.getDuration();
                if (Log.isLoggable()) {
                    Log.d("PlayerFragment", "========> Duration = " + duration);
                }
                this.mScreen.initProgress(duration);
                if (Log.isLoggable()) {
                    Log.d("PlayerFragment", "Position: " + currentProgress + ", duration: " + duration);
                }
                this.mScreen.setProgress(currentProgress, duration, true, true);
                this.mState.playStarted = true;
                this.mScreen.removeSplashScreen();
                this.startScreenUpdate();
                UserActionLogUtils.reportPlayActionEnded((Context)this.getActivity(), IClientLogging$CompletionReason.success, null, null, currentType);
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
                if (Log.isLoggable()) {
                    Log.e("PlayerFragment", "onStarted not in correct state, ActivityState: " + this.mState.playerState.getName());
                }
                UserActionLogUtils.reportPlayActionEnded((Context)this.getActivity(), IClientLogging$CompletionReason.failed, new UIError(RootCause.clientFailure, ActionOnUIError.handledSilently, null, null), null, currentType);
                this.mState.playStartInProgress.set(false);
                this.cleanupAndExit();
            }
        }
    }
    
    public void onStop() {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "PlayerActivity::onStop called " + this.hashCode());
        }
        super.onStop();
        final AudioManager audioManager = (AudioManager)this.getActivity().getSystemService("audio");
        if (audioManager != null) {
            audioManager.abandonAudioFocus((AudioManager$OnAudioFocusChangeListener)this);
        }
        else {
            Log.e("PlayerFragment", "Audio manager not found. Unable to abandon audio focus!");
        }
        if (this.mPlayerBackgrounded || this.canPlayerBeBackgrounded()) {
            Log.d("PlayerFragment", "PlayerActivity::onStop done, player is backbrounded");
            return;
        }
        if (this.mState.playStartInProgress.getAndSet(false)) {
            Log.d("PlayerFragment", "Start play is in progress and user canceled playback");
            UserActionLogUtils.reportPlayActionEnded((Context)this.getActivity(), IClientLogging$CompletionReason.canceled, null, null, PlayerTypeFactory.getCurrentType((Context)this.getNetflixActivity()));
        }
        final String mMaxStreamsReachedDialogId = this.mMaxStreamsReachedDialogId;
        if (mMaxStreamsReachedDialogId != null) {
            Log.d("PlayerFragment", "Report max stream reach dialog ended");
            this.getNetflixActivity().reportUiModelessViewSessionEnded(IClientLogging$ModalView.maxStreamsReached, mMaxStreamsReachedDialogId);
        }
        this.cleanupAndExit();
        Log.d("PlayerFragment", "PlayerActivity::onStop done");
    }
    
    public void onSubtitleChange(final SubtitleScreen subtitleScreen) {
        if (!this.isActivityValid()) {
            Log.w("PlayerFragment", "Activity isn't already in a valid state - no need to update the subtitles");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "Update subtitles " + subtitleScreen);
        }
        this.mSubtitleManager.onSubtitleChange(subtitleScreen);
    }
    
    public void onSubtitleFailed() {
        if (!this.isActivityValid()) {
            return;
        }
        Log.d("PlayerFragment", "We failed to change subtitle");
        Toast.makeText(this.getActivity().getBaseContext(), 2131165444, 1).show();
        if (this.language != null) {
            Log.d("PlayerFragment", "Try to restore previous subtitle");
            this.language.restorePreviousSubtitle();
            return;
        }
        Log.w("PlayerFragment", "Unable to restore previous subtitle, lang is null. This should NOT happen!");
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
            Log.d("PlayerFragment", append.append(s).toString());
        }
        if (b) {
            this.runOnUiThread(new PlayerFragment$13(this));
            return;
        }
        this.runOnUiThread(new PlayerFragment$14(this));
    }
    
    public void onUpdatePts(final int n) {
        if (!this.isActivityValid()) {
            Log.i("PlayerFragment", "Activity is already not in valid state - skpping onUpdatePts()");
        }
        else {
            this.mScreen.getPostPlay().updatePlaybackPosition(n);
            if (this.mAsset != null) {
                PinVerifier.getInstance().registerPlayEvent(this.mAsset.isPinProtected());
            }
        }
    }
    
    public void onVideoSizeChanged(final int mWidth, final int mHeight) {
        if (!this.isActivityValid()) {
            Log.w("PlayerFragment", "Activity isn't already in a valid state - no need to update video size");
            return;
        }
        if (mWidth == 0 || mHeight == 0) {
            Log.e("PlayerFragment", "invalid aspect ratio width(" + mWidth + ") or aspect ratio height(" + mHeight + ")");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "MP onVideoSizeChanged: aspect ratio width " + mWidth + ", aspect ratio height " + mHeight);
        }
        this.setSurface(this.mWidth = mWidth, this.mHeight = mHeight);
    }
    
    public void onWindowFocusChanged(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "====> In focus: " + b);
        }
        if (b || !this.isActivityValid()) {
            return;
        }
        final PlayScreen mScreen = this.mScreen;
        if (mScreen == null || PlayerUiState.Loading == mScreen.getState()) {
            Log.d("PlayerFragment", "UI is not in focus on splash screen. Do NOT pause, ignore.");
            return;
        }
        Log.d("PlayerFragment", "Alert from some other activity is in front of us. Pause.");
        this.runOnUiThread(new PlayerFragment$11(this));
    }
    
    public void performUpAction() {
        final NetflixActivity netflixActivity = this.getNetflixActivity();
        UIViewLogUtils.reportUIViewCommand((Context)netflixActivity, UIViewLogging$UIViewCommandName.actionBarBackButton, netflixActivity.getUiScreen(), netflixActivity.getDataContext());
        this.finish();
    }
    
    public void playNextVideo(final Playable playable, final PlayContext playContext, final boolean b) {
        final boolean b2 = false;
        if (!this.isActivityValid()) {
            Log.d("PlayerFragment", "Activity already destroyed, ignore next!");
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
            Log.d("PlayerFragment", "Play next video, count " + n + ", from auto play " + b + ", no user interaction " + this.mState.noUserInteraction());
        }
        this.cleanupAndExit();
        boolean b3 = b2;
        if (!PlayerActivity.PIN_VERIFIED) {
            b3 = true;
        }
        final Asset forPostPlay = Asset.createForPostPlay(playable, playContext, n, b3);
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "Asset to play next: " + forPostPlay);
        }
        if (StringUtils.isEmpty(forPostPlay.getPlayableId())) {
            Log.e("PlayerFragment", "PlayableId is null - skip playback");
            ErrorLoggingManager.logHandledException("PlayableId is null - skip playback");
            return;
        }
        this.startActivity(PlayerActivity.toIntent(this.getNetflixActivity(), forPostPlay));
    }
    
    public void playbackClosed() {
        Log.d("PlayerFragment", "playbackClosed");
        if (this.mRestartPlayback) {
            Log.d("PlayerFragment", "Reloading Video to start playback");
            this.loadVideo();
            this.mRestartPlayback = false;
        }
    }
    
    public void resetCurrentPlayback() {
        if (this.mPlayer != null) {
            this.mPlayer.pause();
            this.mPlayer.close();
        }
        this.mState.videoLoaded = false;
        this.showLoading();
    }
    
    public void restartPlayback(final NccpError nccpError) {
        Log.e("PlayerFragment", "Restarting playback");
        if (!this.isActivityValid()) {
            Log.w("PlayerFragment", "Activity isn't already in a valid state - no need to restart the playback");
            return;
        }
        ++this.mActionId12Count;
        if (this.mActionId12Count > 1) {
            this.addError(nccpError);
            return;
        }
        this.mRestartPlayback = true;
        this.mPlayer.close();
    }
    
    public void restorePlaybackAfterSnap() {
        Log.d("PlayerFragment", "restorePlaybackAfterSnap.");
        this.keepScreenOn();
        if (this.mScreen != null && this.isActivityValid()) {
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
    
    public void runOnUiThread(final Runnable runnable) {
        this.mRootLayout.post(runnable);
    }
    
    public void selectInitialTracks() {
        final Subtitle[] subtitleTrackList = this.mPlayer.getSubtitleTrackList();
        final AudioSource[] audioTrackList = this.mPlayer.getAudioTrackList();
        final AudioSubtitleDefaultOrderInfo[] audioSubtitleDefaultOrderInfo = this.mPlayer.getAudioSubtitleDefaultOrderInfo();
        Log.d("PlayerFragment", "Create localization manager");
        final LocalizationManager localizationManager = new LocalizationManager(subtitleTrackList, audioTrackList, audioSubtitleDefaultOrderInfo, null);
        boolean b = false;
        final LanguageChoice initialLanguage = localizationManager.findInitialLanguage();
        final AudioSource audio = initialLanguage.getAudio();
        int nccpOrderNumber;
        if (audio != null) {
            if (Log.isLoggable()) {
                Log.d("PlayerFragment", "Changing initial audio to " + audio);
            }
            nccpOrderNumber = audio.getNccpOrderNumber();
        }
        else {
            Log.d("PlayerFragment", "No need to set initial audio source");
            nccpOrderNumber = -1;
        }
        final Subtitle subtitle = initialLanguage.getSubtitle();
        int nccpOrderNumber2;
        if (subtitle != null) {
            if (Log.isLoggable()) {
                Log.d("PlayerFragment", "Changing initial subtitle to " + subtitle);
            }
            nccpOrderNumber2 = subtitle.getNccpOrderNumber();
            b = true;
        }
        else {
            Log.d("PlayerFragment", "No need to set initial subtitle");
            nccpOrderNumber2 = -1;
        }
        this.mPlayer.selectTracks(audio, subtitle);
        this.setLanguage(new Language(audioTrackList, nccpOrderNumber, subtitleTrackList, nccpOrderNumber2, b));
    }
    
    public void setExternalBundle(final Bundle mExternalBundle) {
        this.mExternalBundle = mExternalBundle;
    }
    
    public void setLanguage(final Language language) {
        if (language == null) {
            Log.w("PlayerFragment", "Language is null!");
            return;
        }
        Log.d("PlayerFragment", "Sets language");
        this.language = language;
    }
    
    public void setMaxStreamsReachedDialogId(final String mMaxStreamsReachedDialogId) {
        this.mMaxStreamsReachedDialogId = mMaxStreamsReachedDialogId;
    }
    
    public void setTargetSelection() {
        if (this.mScreen == null || !this.isActivityValid()) {
            Log.i("PlayerFragment", "Skipping setTargetSelection()");
            return;
        }
        final ServiceManager mServiceManager = this.mServiceManager;
        if (mServiceManager == null || !mServiceManager.isReady() || mServiceManager.getMdx() == null) {
            this.mScreen.getTopPanel().setMdxTargetSelector(null);
            return;
        }
        final Pair<String, String>[] targetList = mServiceManager.getMdx().getTargetList();
        if (targetList == null || targetList.length < 1) {
            this.mScreen.getTopPanel().setMdxTargetSelector(null);
            return;
        }
        this.mScreen.getTopPanel().setMdxTargetSelector(this.createMdxTargetSelection(targetList, mServiceManager.getMdx().getCurrentTarget()));
    }
    
    void startScreenUpdateTask() {
        this.repostOnEverySecondRunnable(1000);
    }
    
    void stopScreenUpdateTask() {
        this.mHandler.removeCallbacks(this.onEverySecond);
        Log.d("PlayerFragment", "===>> Screen update thread canceled");
    }
}
