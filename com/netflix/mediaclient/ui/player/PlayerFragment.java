// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.LanguageChoice;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import java.io.Serializable;
import android.media.AudioManager;
import com.netflix.mediaclient.media.Watermark;
import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackError;
import android.view.MenuItem;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import android.view.Surface;
import android.widget.FrameLayout;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.support.v4.media.session.MediaSessionCompat$Callback;
import android.content.res.Configuration;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier;
import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import com.netflix.mediaclient.service.net.LogMobileType;
import android.view.Window;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.util.Pair;
import com.netflix.mediaclient.ui.verifyplay.PinAndAgeVerifier;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault$RequestedBy;
import android.annotation.SuppressLint;
import android.view.TextureView;
import android.content.IntentFilter;
import com.netflix.mediaclient.ui.details.DPPrefetchABTestUtils;
import android.support.v7.widget.Toolbar;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
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
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsFrag;
import com.netflix.mediaclient.ui.barker.details.BarkerHelper;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.log.OfflineLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.PlaybackBookmark;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.media.BookmarkStore;
import android.widget.Toast;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import android.view.View;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.common.PlayLocationType;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.android.widget.UpdateDialog$Builder;
import com.netflix.mediaclient.servicemgr.interface_.offline.WatchState;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Parcelable;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import com.netflix.mediaclient.ui.player.error.PlayerErrorDialogDescriptorFactory;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$TapListener;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$SurfaceMeasureListener;
import android.view.SurfaceHolder$Callback;
import java.util.ArrayList;
import com.netflix.mediaclient.media.JPlayer.SecondSurface;
import com.netflix.mediaclient.service.player.subtitles.SafeSubtitleManager;
import android.view.ViewGroup;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackType;
import android.content.BroadcastReceiver;
import android.os.Handler;
import android.os.Bundle;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.media.Language;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.ui.details.DetailsActivity$Reloader;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class PlayerFragment extends NetflixFrag implements AudioManager$OnAudioFocusChangeListener, NetflixDialogFrag$DialogCanceledListenerProvider, ErrorWrapper$Callback, JPlayer$JplayerListener, IPlayer$PlayerListener, PlayContextProvider, DetailsActivity$Reloader
{
    public static final String ASSET_EXTRA = "AssetExtra";
    public static final String BOOKMARK_SECONDS_FROM_START_PARAM = "BookmarkSecondsFromStart";
    private static final int BROWSE_PLAY_END_MS = 120000;
    private static final int DELAY_POST = 1000;
    public static final String DETAILS_PLAY_CONTEXT_EXTRA_BUNDLE = "VideoDetailsPlaycontextExtraBundle";
    public static final String DETAILS_VIDEO_ID_EXTRA = "VideoDetailsIdExtra";
    public static final String DETAILS_VIDEO_TYPE_EXTRA = "VideoDetailsTypeExtra";
    public static final int INACTIVITY_TIMEOUT = 5000;
    private static final int INVALID_TRACK_INDEX = -1;
    private static final String MEDIA_ERROR_CODE = "Media Error";
    private static final int PAUSE_LOCK_SCREEN_TIMEOUT = 120000;
    private static final long PAUSE_TIMEOUT = 900000L;
    private static final long PLAYREADY_CERT_REVOKED = 113L;
    public static final String SEAMLESS_MODE = "SeamlessMode";
    private static final int SKIP_DELTA_MS = 30000;
    private static final String TAG = "PlayerFragment";
    public static final String TRACK_ID_PREFIX_TAG = "TRACK_ID: ";
    private static final int VOLUME_TIMEOUT = 500;
    private boolean allowCoppolaAutoplay;
    private final Runnable allowScreenLockTimeout;
    private final SeekBar$OnSeekBarChangeListener audioPositionListener;
    private final View$OnClickListener episodesListener;
    Runnable exitButtonHandler;
    private Language language;
    boolean launched;
    private int mActionId12Count;
    private Asset mAsset;
    private ServiceAgent$ConfigurationAgentInterface mConfig;
    private final NetflixDialogFrag$DialogCanceledListener mDialogCancedledListener;
    private final AbsEpisodeView$EpisodeRowListener mEpisodeRowListener;
    private NetflixDialogFrag mEpisodesFrag;
    private Bundle mExternalBundle;
    protected Handler mHandler;
    private int mHeight;
    private boolean mIsAssetReady;
    private boolean mIsBufferingOnPause;
    private boolean mIsListening;
    private boolean mIsLoadingData;
    private boolean mIsSurfaceReady;
    protected boolean mIsTablet;
    private boolean mIsZoomedOut;
    private String mMaxStreamsReachedDialogId;
    private PlayerMediaSessionController mMediaSessionController;
    private final BroadcastReceiver mNetworkChangeReceiver;
    private final BroadcastReceiver mNoisyAudioStreamReceiver;
    private PlayerFragment$OnPlaybackStateListener mPlaybackStateListener;
    private IPlayer$PlaybackType mPlaybackType;
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
    private final PlayerWorkflowState mState;
    private SafeSubtitleManager mSubtitleManager;
    private SecondSurface mSurface2;
    private boolean mWasEndOfBrowsePlayreported;
    private int mWidth;
    private boolean onBufferingComplete;
    private final Runnable onEverySecond;
    private boolean pausePlaybackOnPlayerBackgrounded;
    private final Runnable pauseTimeout;
    private final View$OnClickListener playPauseListener;
    private ArrayList<PlayPauseListener> playPauseListenerList;
    boolean playWhenBufferingComplete;
    private int playedVideoCount;
    private boolean postPlayed;
    private Runnable reportBrowsePlayEndRunnable;
    private int secondsFromStart;
    private final View$OnClickListener skipBackListener;
    private Menu staticToolbarMenu;
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
        this.secondsFromStart = -1;
        this.mIsLoadingData = true;
        this.mPlaybackType = IPlayer$PlaybackType.StreamingPlayback;
        this.playWhenBufferingComplete = false;
        this.allowScreenLockTimeout = new PlayerFragment$2(this);
        this.playedVideoCount = 0;
        this.playPauseListener = (View$OnClickListener)new PlayerFragment$3(this);
        this.episodesListener = (View$OnClickListener)new PlayerFragment$4(this);
        this.zoomListener = (View$OnClickListener)new PlayerFragment$5(this);
        this.skipBackListener = (View$OnClickListener)new PlayerFragment$6(this);
        this.audioPositionListener = (SeekBar$OnSeekBarChangeListener)new PlayerFragment$7(this);
        this.tapListener = new PlayerFragment$8(this);
        this.onEverySecond = new PlayerFragment$9(this);
        this.surfaceListener = (SurfaceHolder$Callback)new PlayerFragment$10(this);
        this.mPlayerSuspendIntentReceiver = new PlayerFragment$11(this);
        this.surfaceMeasureListener = new PlayerFragment$13(this);
        this.pauseTimeout = new PlayerFragment$16(this);
        this.exitButtonHandler = new PlayerFragment$17(this);
        this.mNetworkChangeReceiver = new PlayerFragment$18(this);
        this.mNoisyAudioStreamReceiver = new PlayerFragment$19(this);
        this.mEpisodeRowListener = new PlayerFragment$21(this);
        this.mDialogCancedledListener = new PlayerFragment$22(this);
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
        this.reportStartPlayEnded(this, RootCause.clientFailure, ActionOnUIError.displayedError, data.getMessage(), null, nccpError, this.getPlayLocation());
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null && serviceManager.getErrorHandler() != null) {
            serviceManager.getErrorHandler().addError(handler);
        }
        return true;
    }
    
    private boolean addMediaError(final Error error) {
        if (this.handlePlayReadyRevocation(error)) {
            this.reportStartPlayEnded(this, RootCause.clientFailure, ActionOnUIError.handledSilently, null, null, error, this.getPlayLocation());
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
        this.reportStartPlayEnded(this, RootCause.clientFailure, ActionOnUIError.displayedError, data.getMessage(), null, error, this.getPlayLocation());
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null && serviceManager.getErrorHandler() != null) {
            serviceManager.getErrorHandler().addError(handler);
        }
        return true;
    }
    
    private static boolean availableInMyDownloads(final ServiceManager serviceManager, final String s) {
        final OfflinePlayableViewData offlinePlayableData = getOfflinePlayableData(serviceManager, s);
        return offlinePlayableData != null && offlinePlayableData.getDownloadState() == DownloadState.Complete;
    }
    
    private boolean canPlayerBeBackgrounded() {
        return this.isActivityValid() && (this.isOfflinePlayback() || (this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY && this.mState.isPlayStarted() && !this.isStalled() && !this.isSeeking() && !this.mIsBufferingOnPause && this.mPlayer.isBufferingCompleted() && this.mScreen != null && (this.mScreen.getState() != PlayerUiState.PostPlay || this.isInteractivePostPlay()) && PlayerTypeFactory.isJPlayer2(PlayerTypeFactory.getCurrentType(this.getActivity().getBaseContext())) && this.mConfig != null && !this.mConfig.isDeviceLowMem() && this.mConfig.getPlaybackConfiguration().isSuspendPlaybackEnabled()));
    }
    
    private void cancelBrowsePlayRunnable() {
        if (this.reportBrowsePlayEndRunnable != null) {
            this.mHandler.removeCallbacks(this.reportBrowsePlayEndRunnable);
        }
    }
    
    private void completeInitIfReady() {
        if (!this.mIsSurfaceReady) {
            Log.d("PlayerFragment", "Surface not ready - cannot complete init");
        }
        else {
            if (!this.mIsAssetReady) {
                Log.d("PlayerFragment", "Asset not ready - cannot complete init");
                return;
            }
            if (this.mAsset == null) {
                Log.d("PlayerFragment", "Asset is null - cannot complete init");
                return;
            }
            Log.v("PlayerFragment", "completeInitIfReady()");
            if (this.isCoppolaPlayback()) {
                this.setCoppolaSeekbarEnabled(false);
                if (!Coppola1Utils.isNewPlayerExperience((Context)this.getActivity())) {
                    return;
                }
                if (!Coppola1Utils.isAutoplay((Context)this.getActivity()) && this.isInPortrait() && !this.allowCoppolaAutoplay) {
                    Log.d("PlayerFragment", "Autoplayback is turned off - waiting for user/timer to launch it to perform launchPlayback() call");
                    if (PersistentConfig.getCoppola1ABTestCell((Context)this.getActivity()).ordinal() == ABTestConfig$Cell.CELL_THREE.ordinal()) {
                        if (this.launched) {
                            this.mPlayer.close();
                            this.onBufferingComplete = false;
                            if (this.mPlaybackStateListener != null) {
                                this.mPlaybackStateListener.onPlaybackStopped();
                            }
                            this.launched = false;
                        }
                        ((CoppolaLoadingDecorator)this.mScreen.getDecorator()).showLaunchButtons();
                        ((CoppolaLoadingDecorator)this.mScreen.getDecorator()).showProgressAndTextIndicator(false);
                    }
                    return;
                }
            }
            verifyPlayToContinue(this, this.mAsset);
        }
    }
    
    private void continueInitAfterPlayVerify() {
        final boolean b = false;
        Log.v("PlayerFragment", "Playback verified - completing init process...");
        final ServiceManager serviceManager = this.getServiceManager();
        if (this.mAsset != null) {
            this.mScreen.setTitle(this.getTitleForScreen(this.mAsset));
            this.mScreen.onAssetUpdated(this.mAsset);
        }
        final boolean b2 = serviceManager != null && serviceManager.isNonMemberPlayback();
        if (!this.isOfflinePlayback()) {
            final TopPanel topPanel = this.mScreen.getTopPanel();
            boolean episodeSelectorVisibility = b;
            if (this.mAsset != null) {
                episodeSelectorVisibility = b;
                if (this.mAsset.isEpisode()) {
                    episodeSelectorVisibility = b;
                    if (!b2) {
                        episodeSelectorVisibility = true;
                    }
                }
            }
            topPanel.setEpisodeSelectorVisibility(episodeSelectorVisibility);
        }
        this.reportCachePlayStartIfNeeded();
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
    
    public static PlayerFragment createPlayerFragment(final String s, final String s2, final PlayContext playContext, final int n) {
        final PlayerFragment playerFragment = new PlayerFragment();
        playerFragment.setArguments(getBundle(s, s2, playContext, n));
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
                    this.mScreen.setMediaImage(true);
                }
                this.mPlayer.pause();
                this.mMediaSessionController.setMediaSessionState(2);
                Log.d("PlayerFragment", "Pause, release awake clock after 2 minutes.");
                this.mHandler.postDelayed(this.allowScreenLockTimeout, 120000L);
                this.mHandler.postDelayed(this.pauseTimeout, 900000L);
                Log.i("PlayerFragment", "doPause() remove reporting");
                this.cancelBrowsePlayRunnable();
                this.notifyPlayPauseToListener(true);
                return;
            }
            if (!this.mPlayer.isBufferingCompleted() && this.mPlayer.isPlaying()) {
                Log.d("PlayerFragment", "Problems reaching Netflix service. Screen lock needs to be released.");
                this.mHandler.postDelayed(this.allowScreenLockTimeout, 120000L);
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
                this.mScreen.setMediaImage(false);
            }
            this.mPlayer.seekTo(n, b);
            this.notifyPlayPauseToListener(true);
            this.mSubtitleManager.onSubtitleRemove();
            Log.i("PlayerFragment", "doSeek() - scheduling \"browse_play\" end reporting");
            this.scheduleBrowsePlayRunnableIfNeeded(false);
        }
    }
    
    public static Bundle getBundle(final Asset asset) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable("AssetExtra", (Parcelable)asset);
        return bundle;
    }
    
    public static Bundle getBundle(final String s, final String s2, final PlayContext playContext) {
        return getBundle(s, s2, playContext, -1);
    }
    
    public static Bundle getBundle(final String s, final String s2, final PlayContext playContext, final int n) {
        final Bundle bundle = new Bundle();
        bundle.putString("VideoDetailsIdExtra", s);
        bundle.putString("VideoDetailsTypeExtra", s2);
        bundle.putBundle("VideoDetailsPlaycontextExtraBundle", playContext.playContextToBundle());
        if (n > -1) {
            bundle.putInt("BookmarkSecondsFromStart", n);
        }
        return bundle;
    }
    
    private String getCurrentProfileGuidOrNull() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null) {
            return serviceManager.getCurrentProfileGuidOrNull();
        }
        return null;
    }
    
    private static JSONObject getJSonSafely(final MediaEvent mediaEvent) {
        try {
            return mediaEvent.getData();
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    private UpdateDialog$Builder getOfflineErrorBuilderOrNullFromWatchState(final WatchState watchState) {
        int n = 2131296897;
        int n2 = 0;
        switch (PlayerFragment$23.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$WatchState[watchState.ordinal()]) {
            default: {
                n = -1;
                n2 = -1;
                break;
            }
            case 1: {
                n2 = 2131296920;
                break;
            }
            case 2: {
                if (ConnectivityUtils.isConnected((Context)this.getActivity())) {
                    n2 = 2131296879;
                    n = 2131296891;
                    break;
                }
                n = 2131296891;
                n2 = 2131296893;
                break;
            }
            case 3: {
                n = 2131296891;
                n2 = 2131296893;
                break;
            }
            case 4: {
                n2 = 2131296892;
                n = 2131296891;
                break;
            }
            case 5: {
                n2 = 2131296898;
                n = 2131296891;
                break;
            }
            case 6: {
                n2 = 2131296896;
                break;
            }
        }
        if (n2 != -1 && n != -1) {
            final String string = this.getString(n);
            final String string2 = this.getString(n2);
            if (this.isActivityValid()) {
                return AlertDialogFactory.createDialog((Context)this.getActivity(), this.mHandler, new AlertDialogFactory$AlertDialogDescriptor(string, string2, this.getString(2131296724), this.exitButtonHandler));
            }
        }
        return null;
    }
    
    private static OfflinePlayableViewData getOfflinePlayableData(final ServiceManager serviceManager, final String s) {
        if (serviceManager != null) {
            return serviceManager.getOfflinePlayableData(s);
        }
        return null;
    }
    
    private PlayLocationType getPlayLocation() {
        if (this.getPlayContext() != null) {
            return this.getPlayContext().getPlayLocation();
        }
        return PlayLocationType.UNKNOWN;
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
    
    private void handleVideoDetailsResponse(final PlayContext playContext, final VideoDetails videoDetails, final Status status, final IPlayer$PlaybackType player$PlaybackType) {
        this.mIsAssetReady = false;
        if (!this.isActivityValid()) {
            return;
        }
        if (status.isError() || videoDetails == null) {
            Log.w("PlayerFragment", "Error loading video details for video playback");
            Toast.makeText((Context)this.getActivity(), 2131296660, 1).show();
            return;
        }
        if (Log.isLoggable()) {
            Log.v("PlayerFragment", "Retrieved details: " + videoDetails.getTitle() + ", " + videoDetails);
        }
        this.mAsset = Asset.create(videoDetails.getPlayable(), playContext, false);
        if (player$PlaybackType == IPlayer$PlaybackType.OfflinePlayback) {
            final PlaybackBookmark bookmark = BookmarkStore.getInstance().getBookmark(this.getCurrentProfileGuidOrNull(), videoDetails.getPlayable().getPlayableId());
            final Playable playable = videoDetails.getPlayable();
            if (bookmark != null && playable != null) {
                this.mAsset.setPlaybackBookmark(TimeUtils.computePlayPos(bookmark.mBookmarkInSecond, playable.getEndtime(), playable.getRuntime()));
            }
        }
        this.mIsAssetReady = true;
        if (this.mExternalBundle == null) {
            this.updateUI(videoDetails);
        }
        else if (this.mScreen != null) {
            this.mScreen.onVideoDetailsFetched(videoDetails, player$PlaybackType);
        }
        this.mExternalBundle = null;
        this.completeInitIfReady();
    }
    
    private void initSurfaceFixedSize(final int n, final int n2) {
        if (this.mScreen.getHolder() == null) {
            return;
        }
        this.mScreen.getHolder().setFixedSize(n, n2);
    }
    
    private boolean isCoppolaWithOldPlayer() {
        return this.isCoppolaPlayback() && !Coppola1Utils.isNewPlayerExperience((Context)this.getActivity());
    }
    
    private boolean isInteractivePostPlay() {
        return this.mScreen != null && this.mScreen.getPostPlay() != null && this.mScreen.getPostPlay().isInPostPlay() && this.mScreen.getPostPlay().isInteractivePostPlay;
    }
    
    private boolean isOfflinePlayback() {
        return this.mPlaybackType == IPlayer$PlaybackType.OfflinePlayback;
    }
    
    private boolean isSeamless() {
        return this.getArguments().getBoolean("SeamlessMode", false);
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
        if (!ConnectivityUtils.isConnectedOrConnecting((Context)this.getActivity()) && !this.isOfflinePlayback()) {
            Log.d("PlayerFragment", "Raising no connectivity warning");
            this.noConnectivityWarning();
            return false;
        }
        if (!this.handleConnectivityCheck()) {
            Log.d("PlayerFragment", "Network check fails");
            return false;
        }
        if (this.mAsset == null) {
            Log.e("PlayerFragment", "asset is null, this should not happen!");
            return false;
        }
        while (true) {
            this.mState.videoLoaded = true;
            while (true) {
                Label_0467: {
                    try {
                        final PlayerType currentType = PlayerTypeFactory.getCurrentType((Context)this.getActivity());
                        if (currentType == PlayerType.device10 || currentType == PlayerType.device11) {
                            if (Log.isLoggable()) {
                                Log.d("PlayerFragment", "Set JPlayerListener: " + this);
                            }
                            this.mPlayer.setJPlayerListener(this);
                        }
                        int n;
                        if (this.secondsFromStart > -1) {
                            n = this.secondsFromStart;
                        }
                        else {
                            n = this.mAsset.getPlaybackBookmark();
                        }
                        this.secondsFromStart = -1;
                        if (Log.isLoggable()) {
                            Log.d("PlayerFragment", this.hashCode() + " Do Play from position " + n);
                            Log.d("PlayerFragment", this.hashCode() + " Do Play asset " + this.mAsset.toString());
                        }
                        if (n < 0) {
                            Log.d("PlayerFragment", this.hashCode() + " Invalid bookmark, reset to 0");
                            n = 0;
                            this.mPlayer.setSurface(this.mScreen.getHolder().getSurface());
                            this.mPlayer.setSurfaceHolder(this.mScreen.getHolder());
                            if (Coppola1Utils.isBrowsePlay(this.getActivity())) {
                                this.mAsset.setBrowsePlay(true);
                            }
                            Thread.sleep(30L);
                            this.mPlayer.open(this.toLongSafe(this.mAsset.getPlayableId()), this.mAsset, n * 1000);
                            this.notifyOthersOfPlayStart();
                            return true;
                        }
                        break Label_0467;
                    }
                    catch (Throwable t) {
                        Log.e("PlayerFragment", "Exception in video preparation", t);
                        this.addError(new NccpActionId(3, "", this.getString(2131296660), "handleActionId", "ACTION_ID", 0, null));
                        return false;
                    }
                }
                continue;
            }
        }
    }
    
    private void moveToMDXPLayback() {
        if (this.getCurrentAsset() != null && this.getPlayer() != null && this.isActivityValid()) {
            final Asset currentAsset = this.getCurrentAsset();
            currentAsset.setPlaybackBookmark(this.getPlayer().getCurrentPositionMs() / 1000);
            PlaybackLauncher.startPlaybackAfterPIN(this.getNetflixActivity(), currentAsset);
            this.getNetflixActivity().getServiceManager().getMdx().transferPlaybackFromLocal();
            Log.i("PlayerFragment", "MDX is currently connected so we need to skip local playback");
            return;
        }
        Log.w("PlayerFragment", "moveToMDXPLayback() was called in a bad state - skipping...");
    }
    
    private void noConnectivityWarning() {
        this.getNetflixActivity().displayDialog(AlertDialogFactory.createDialog((Context)this.getActivity(), this.mHandler, new AlertDialogFactory$AlertDialogDescriptor(null, this.getString(2131296817), this.getString(2131296724), this.exitButtonHandler)));
    }
    
    private void nonWifiPlayWarning(final boolean b) {
        ThreadUtils.assertOnMain();
        String s;
        if (b) {
            s = this.getString(2131296717);
        }
        else {
            s = this.getString(2131296718);
        }
        final String string = this.getString(2131296724);
        Runnable exitButtonHandler;
        if (b) {
            exitButtonHandler = null;
        }
        else {
            exitButtonHandler = this.exitButtonHandler;
        }
        this.getNetflixActivity().displayDialog(AlertDialogFactory.createDialog((Context)this.getActivity(), this.mHandler, new AlertDialogFactory$AlertDialogDescriptor(null, s, string, exitButtonHandler)));
    }
    
    private void notifyPlayPauseToListener(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "NotifyPlayPauseToListener() invoked with player paused = " + b + " Position = " + this.mPlayer.getCurrentProgress());
        }
        if (this.playPauseListenerList != null && !this.playPauseListenerList.isEmpty()) {
            for (final PlayPauseListener playPauseListener : this.playPauseListenerList) {
                if (playPauseListener != null) {
                    playPauseListener.onPlaybackPaused(b, this.mPlayer.getCurrentProgress());
                }
            }
        }
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
    
    private void reportCachePlayEndedIfNeeded(final IClientLogging$CompletionReason clientLogging$CompletionReason) {
        if (this.isOfflinePlayback() && this.mAsset != null && getOfflinePlayableData(this.getServiceManager(), this.mAsset.getPlayableId()) != null) {
            OfflineLogUtils.reportCachedPlayEnded((Context)this.getActivity(), null, clientLogging$CompletionReason, null);
        }
    }
    
    private void reportCachePlayStartIfNeeded() {
        if (this.isOfflinePlayback()) {
            final Asset mAsset = this.mAsset;
            if (mAsset != null) {
                final OfflinePlayableViewData offlinePlayableData = getOfflinePlayableData(this.getServiceManager(), mAsset.getPlayableId());
                if (offlinePlayableData != null) {
                    OfflineLogUtils.reportCachedPlayStart((Context)this.getActivity(), offlinePlayableData.getOxId(), offlinePlayableData.getPlayableId(), mAsset.getDuration(), mAsset.getLogicalStart(), mAsset.getEndtime());
                }
            }
        }
    }
    
    private void reportStartPlayEnded(final PlayerFragment playerFragment, final RootCause rootCause, final ActionOnUIError actionOnUIError, final String s, final Integer n, final MediaEvent mediaEvent, final PlayLocationType playLocationType) {
        if (playerFragment.getState().getPlayStartInProgress().getAndSet(false)) {
            final UIError uiError = StatusUtils.createUiError("Media Error", actionOnUIError, s, true, rootCause, ConsolidatedLoggingUtils.createDebug(null, getJSonSafely(mediaEvent)));
            final ServiceManager serviceManager = this.getServiceManager();
            if (serviceManager != null && serviceManager.getConfiguration() != null) {
                UserActionLogUtils.reportPlayActionEnded((Context)playerFragment.getNetflixActivity(), IClientLogging$CompletionReason.failed, uiError, n, serviceManager.getConfiguration().getCurrentPlayerType(), playLocationType);
            }
        }
    }
    
    private void repostOnEverySecondRunnable(final int n) {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "repostOnEverySecondRunnable: " + n);
        }
        this.mHandler.removeCallbacks(this.onEverySecond);
        this.mHandler.postDelayed(this.onEverySecond, (long)n);
    }
    
    private boolean requestDetailsIfNeededOffline(final String s, final Bundle bundle) {
        final PlayContext playContextFromBundle = PlayContextImp.createPlayContextFromBundle(bundle.getBundle("VideoDetailsPlaycontextExtraBundle"));
        final RealmVideoDetails offlineVideoDetails = RealmUtils.getOfflineVideoDetails(s);
        NetflixImmutableStatus netflixImmutableStatus;
        if (offlineVideoDetails != null) {
            netflixImmutableStatus = CommonStatus.OK;
        }
        else {
            netflixImmutableStatus = CommonStatus.INT_ERR_REALM_DETAILS_NULL;
        }
        this.handleVideoDetailsResponse(playContextFromBundle, offlineVideoDetails, netflixImmutableStatus, IPlayer$PlaybackType.OfflinePlayback);
        return true;
    }
    
    private boolean requestDetailsIfNeededStreaming(final String s, final Bundle bundle, final ServiceManager serviceManager) {
        if (!StringUtils.isNotEmpty(s)) {
            this.mIsAssetReady = true;
            Log.d("PlayerFragment", "Regular playback");
            return false;
        }
        Log.d("PlayerFragment", "Intent has EXTRA_GET_DETAILS_VIDEO_ID - fetching details...");
        final VideoType create = VideoType.create(bundle.getString("VideoDetailsTypeExtra"));
        final PlayContext playContextFromBundle = PlayContextImp.createPlayContextFromBundle(bundle.getBundle("VideoDetailsPlaycontextExtraBundle"));
        if (create == VideoType.MOVIE) {
            serviceManager.getBrowse().fetchMovieDetails(s, null, new PlayerFragment$FetchVideoDetailsForPlaybackCallback(this, playContextFromBundle));
            return true;
        }
        if (create == VideoType.SHOW) {
            serviceManager.getBrowse().fetchShowDetails(s, null, BrowseExperience.shouldLoadKubrickLeavesInDetails(), new PlayerFragment$FetchVideoDetailsForPlaybackCallback(this, playContextFromBundle));
            return true;
        }
        if (VideoType.EPISODE.equals(create)) {
            serviceManager.getBrowse().fetchEpisodeDetails(s, null, new PlayerFragment$FetchVideoDetailsForPlaybackCallback(this, playContextFromBundle));
            return true;
        }
        throw new IllegalStateException("Invalid billboard video type: " + create);
    }
    
    private void scheduleBrowsePlayRunnableIfNeeded(final boolean b) {
        if (b) {
            this.mWasEndOfBrowsePlayreported = false;
        }
        if (this.mAsset != null && this.mAsset.getBrowsePlay() && !this.mWasEndOfBrowsePlayreported) {
            if (this.reportBrowsePlayEndRunnable == null) {
                this.reportBrowsePlayEndRunnable = new PlayerFragment$EndOfBrowsePlayRunnable(this);
            }
            this.mHandler.postDelayed(this.reportBrowsePlayEndRunnable, 120000L);
        }
    }
    
    private void setCoppolaSeekbarEnabled(final boolean seekbarEnabled) {
        if (this.isCoppolaPlayback()) {
            if (Log.isLoggable()) {
                String s;
                if (seekbarEnabled) {
                    s = "Enabling coppola seekbar";
                }
                else {
                    s = "Disabling coppola seekbar";
                }
                Log.v("PlayerFragment", s);
            }
            if (this.mScreen != null) {
                final PlayScreenDecorator decorator = this.mScreen.getDecorator();
                if (decorator != null) {
                    decorator.setSeekbarEnabled(seekbarEnabled);
                }
            }
        }
    }
    
    private void setFragmentContentView(final int n) {
        Log.i("PlayerFragment", "setFragmentContentView");
        this.mRootLayout.removeAllViews();
        this.mRootLayout.addView(this.getActivity().getLayoutInflater().inflate(n, (ViewGroup)null), (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -1));
    }
    
    private void setProgress() {
        if (this.mPlayer == null || this.mState.draggingInProgress) {
            if (Log.isLoggable()) {
                Log.d("PlayerFragment", "setProgress aborted. isDragging -> " + this.mState.draggingInProgress);
            }
        }
        else {
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
                Log.w("PlayerFragment", "PA#setProgress:: Dragging in progress? " + currentProgress + ", duration: " + duration);
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
    
    private void showEpisodesFrag() {
        final NetflixActivity netflixActivity = this.getNetflixActivity();
        if (netflixActivity == null || AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivity)) {
            return;
        }
        if (this.mEpisodesFrag == null) {
            NetflixDialogFrag mEpisodesFrag;
            if (BarkerHelper.isInTest((Context)this.getActivity())) {
                mEpisodesFrag = BarkerShowDetailsFrag.create(false);
            }
            else {
                mEpisodesFrag = EpisodesFrag.createEpisodes(this.mAsset.getParentId(), this.mAsset.getPlayableId(), false);
            }
            (this.mEpisodesFrag = mEpisodesFrag).onManagerReady(this.getServiceManager(), CommonStatus.OK);
            this.mEpisodesFrag.setCancelable(true);
            this.mEpisodesFrag.setStyle(1, 2131493151);
        }
        if (this.mEpisodesFrag instanceof BarkerShowDetailsFrag) {
            ((BarkerShowDetailsFrag)this.mEpisodesFrag).addEpisodeArguments(this.mAsset.getParentId(), this.mAsset.getPlayableId());
        }
        this.notifyOthersOfPlayStop();
        netflixActivity.showDialog(this.mEpisodesFrag);
    }
    
    private void showLoading() {
        this.mState.stalled = true;
        if (this.mScreen != null && !this.isOfflinePlayback()) {
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
        this.getNetflixActivity().displayDialog(AlertDialogFactory.createDialog((Context)this.getActivity(), this.mHandler, new AlertDialogFactory$AlertDialogDescriptor(null, this.getString(2131296800), this.getString(2131296724), this.exitButtonHandler)));
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
            this.mScreen.enableButtons(false);
            if (this.mAsset != null) {
                this.notifyOthersOfPlayStop();
            }
        }
        this.mAsset = null;
        this.mIsAssetReady = false;
        this.mReloadOnAudioTrackChange = false;
        this.mMediaSessionController.stopMediaSession();
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
    
    private void updateAssetWithCurrentPlayPosition() {
        if (this.mAsset != null) {
            this.mAsset.setPlaybackBookmark(this.mPlayer.getCurrentPositionMs() / 1000);
        }
    }
    
    private void updateMetadataIfNeeded() {
        if (!PreferenceUtils.getBooleanPref(this.getActivity().getBaseContext(), "ui.playeroverlay", false)) {
            this.mScreen.setDebugDataVisibility(false);
        }
        else {
            this.mScreen.setDebugDataVisibility(true);
            int n = 0;
            int n2 = 0;
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            String string = "N/A";
            if (this.mPlayer != null) {
                final String value = String.valueOf(AndroidManifestUtils.getVersionCode(this.getActivity().getBaseContext()));
                final int n3 = (int)(Debug.getNativeHeapAllocatedSize() / 1048576L);
                final PlayoutMetadata playoutMetadata = this.mPlayer.getPlayoutMetadata();
                String debugData = null;
                Label_0205: {
                    if (!this.isOfflinePlayback()) {
                        String s = string;
                        while (true) {
                            Label_0620: {
                                if (playoutMetadata == null) {
                                    break Label_0620;
                                }
                                final int n4 = playoutMetadata.position / 60000;
                                final int n5 = playoutMetadata.duration / 60000;
                                sb.append(playoutMetadata.instantBitRate).append("/");
                                sb.append(playoutMetadata.targetBitRate).append("/");
                                if (playoutMetadata.isSuperHD) {
                                    sb.append(this.getString(2131296821));
                                }
                                else if (playoutMetadata.isHD) {
                                    sb.append(this.getString(2131296667));
                                }
                                else {
                                    sb.append(this.getString(2131296779));
                                }
                                sb2.append(playoutMetadata.language).append("/");
                                sb2.append(playoutMetadata.getAudioChannel()).append("/");
                                sb2.append(playoutMetadata.getAudioTrackType());
                                final SubtitleConfiguration subtitleConfiguration = this.mPlayer.getSubtitleConfiguration();
                                if (subtitleConfiguration != null) {
                                    string = subtitleConfiguration.getString((Context)this.getActivity());
                                }
                                Log.d("PlayerFragment", "Subtitle config: " + string);
                                final ISubtitleDef$SubtitleProfile subtitleProfileFromMetadata = this.mPlayer.getSubtitleProfileFromMetadata();
                                s = string;
                                n2 = n5;
                                n = n4;
                                if (subtitleProfileFromMetadata == null) {
                                    break Label_0620;
                                }
                                final String nccpCode = subtitleProfileFromMetadata.getNccpCode();
                                n = n4;
                                n2 = n5;
                                s = string;
                                final String s2 = nccpCode;
                                debugData = this.getString(2131296621, new Object[] { "Release", value, n3, "UI Version", n, n2, sb.toString(), sb2.toString(), PlayerTypeFactory.getCurrentType(this.getActivity().getBaseContext()).getDescription(), s, s2, SubtitleManagerFactory.getSubtitleManagerLabel(this.mSubtitleManager), DrmManagerRegistry.getDrmInfo() });
                                break Label_0205;
                            }
                            final String s2 = "N/A";
                            continue;
                        }
                    }
                    if (playoutMetadata != null) {
                        debugData = this.getString(2131296622, new Object[] { "Release", value, n3, playoutMetadata.instantBitRate, playoutMetadata.mVideoResolution.first, playoutMetadata.mVideoResolution.second, playoutMetadata.mVideoDecoderName, "Offline", DrmManagerRegistry.getDrmInfo() });
                    }
                    else {
                        debugData = "N/A";
                    }
                }
                if (this.mScreen != null) {
                    this.mScreen.setDebugData(debugData);
                }
            }
        }
    }
    
    @SuppressLint({ "NewApi" })
    private void updateUI(final VideoDetails videoDetails) {
        Log.v("PlayerFragment", "updateUI, details: " + videoDetails);
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null || !this.isActivityValid()) {
            Log.w("PlayerFragment", "Wrong activity state, finishing activity ");
            this.tryFinishActivity();
            return;
        }
        final PostPlayFactory$PostPlayType postPlayType = PostPlay.getPostPlayType(this.getNetflixActivity(), this.mAsset);
        this.setFragmentContentView(PlayScreen.resolveContentView(postPlayType));
        final Toolbar supportActionBar = (Toolbar)this.mRootLayout.findViewById(2131755701);
        this.staticToolbarMenu = supportActionBar.getMenu();
        this.getNetflixActivity().setSupportActionBar(supportActionBar);
        final OfflinePlayableViewData offlinePlayableData = getOfflinePlayableData(serviceManager, this.mAsset.getPlayableId());
        if (offlinePlayableData != null && serviceManager.willPlayOffline(offlinePlayableData.getPlayableId())) {
            this.mPlaybackType = IPlayer$PlaybackType.OfflinePlayback;
            final UpdateDialog$Builder offlineErrorBuilderOrNullFromWatchState = this.getOfflineErrorBuilderOrNullFromWatchState(offlinePlayableData.getWatchState());
            if (offlineErrorBuilderOrNullFromWatchState != null) {
                this.getNetflixActivity().displayDialog(offlineErrorBuilderOrNullFromWatchState);
                return;
            }
        }
        else {
            this.mPlaybackType = IPlayer$PlaybackType.StreamingPlayback;
        }
        this.mPlayer = serviceManager.getPlayer(this.mPlaybackType);
        this.mConfig = serviceManager.getConfiguration();
        if (this.mPlayer == null || this.mConfig == null) {
            Log.d("PlayerFragment", "Unable to receive handle to player object, finishing activity ");
            this.tryFinishActivity();
            return;
        }
        if (!Coppola1Utils.isCoppolaContext((Context)this.getNetflixActivity())) {
            serviceManager.cancelAllImageLoaderRequests();
        }
        if (DPPrefetchABTestUtils.isInTest((Context)this.getNetflixActivity())) {
            DPPrefetchABTestUtils.cancelPrefetchDPRequests(serviceManager);
        }
        this.mScreen = PlayScreen.createInstance(this, this.createListeners(), postPlayType);
        if (videoDetails != null) {
            this.mScreen.onVideoDetailsFetched(videoDetails, this.mPlaybackType);
        }
        if (this.isSeamless()) {
            this.mScreen.startSeamlessMode();
        }
        if (this.isSeamless()) {
            this.mScreen.startSeamlessMode();
        }
        if (this.isCoppolaWithOldPlayer()) {
            this.mIsLoadingData = false;
            return;
        }
        this.mPlayer.addPlayerListener(this);
        this.mIsListening = true;
        if (this.mPostponedPanelMenu != null && !this.isInPortrait()) {
            this.mPostponedPanelMenu.clear();
            this.mScreen.getTopPanel().onCreateOptionsMenu(this.mPostponedPanelMenu);
            this.getActivity().invalidateOptionsMenu();
        }
        if (this.getCurrentAsset() != null && this.mPlaybackType == IPlayer$PlaybackType.StreamingPlayback) {
            final PostPlay postPlay = this.mScreen.getPostPlay();
            final String playableId = this.getCurrentAsset().getPlayableId();
            VideoType videoType;
            if (this.getCurrentAsset().isEpisode()) {
                videoType = VideoType.EPISODE;
            }
            else {
                videoType = VideoType.MOVIE;
            }
            postPlay.fetchPostPlayVideosIfNeeded(playableId, videoType, PostPlayRequestContext.POST_PLAY);
        }
        if (AndroidUtils.getAndroidVersion() > 18) {
            this.mScreen.getSurfaceView().setSecure(true);
        }
        this.setTargetSelection();
        this.getNetflixActivity().registerReceiverWithAutoUnregister(this.mNetworkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.mPlayerSuspendNotification = new PlayerSuspendNotification(this.getNetflixActivity(), serviceManager);
        this.getNetflixActivity().registerReceiverWithAutoUnregister(this.mPlayerSuspendIntentReceiver, PlayerSuspendNotification.getNotificationIntentFilter());
        this.getNetflixActivity().registerReceiverWithAutoUnregister(this.mNoisyAudioStreamReceiver, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
        if (AndroidUtils.getAndroidVersion() >= 16 && (PlayerTypeFactory.isJPlayerBase(PlayerTypeFactory.getCurrentType((Context)this.getActivity())) || PlayerTypeFactory.isJPlayer(PlayerTypeFactory.getCurrentType((Context)this.getActivity())))) {
            this.mSurface2 = new SecondSurface((TextureView)this.mRootLayout.findViewById(2131755692));
        }
        this.mState.playerState = PlayerFragment$PlayerFragmentState.ACTIVITY_SRVCMNGR_READY;
    }
    
    private static void verifyPlayToContinue(final PlayerFragment playerFragment, final Asset asset) {
        if (!asset.isAgeProtected() && asset.isPinVerified()) {
            Log.d("PlayerFragment", String.format("nf_pin PlayerActivity pinVerification skipped - ageProtected: %b, pinVerified:%b, pinProtected:%b", asset.isAgeProtected(), asset.isPinVerified(), asset.isPinProtected()));
            playerFragment.continueInitAfterPlayVerify();
            return;
        }
        PinAndAgeVerifier.verifyAgeAndPinToPlay(playerFragment.getNetflixActivity(), asset.isAgeProtected(), new PlayVerifierVault(PlayVerifierVault$RequestedBy.PLAYER.getValue(), asset));
    }
    
    private boolean willstartPlaybackInAnotherActivity(final String s, final PlayContext playContext) {
        Log.d("PlayerFragment", "playableId: " + this.mAsset.getPlayableId() + " => " + s);
        final ServiceManager serviceManager = this.getServiceManager();
        final OfflinePlayableViewData offlinePlayableData = getOfflinePlayableData(serviceManager, s);
        if (offlinePlayableData != null && serviceManager.willPlayOffline(offlinePlayableData.getPlayableId()) && offlinePlayableData.getDownloadState() == DownloadState.Complete) {
            this.stopPlayback();
            this.tryFinishActivity();
            this.startActivity(PlayerActivity.createColdStartIntent(this.getNetflixActivity().getApplicationContext(), s, VideoType.EPISODE, playContext));
            return true;
        }
        return false;
    }
    
    public void addPlayPauseListener(final PlayPauseListener playPauseListener) {
        if (playPauseListener == null) {
            return;
        }
        if (this.playPauseListenerList == null) {
            this.playPauseListenerList = new ArrayList<PlayPauseListener>();
        }
        this.playPauseListenerList.add(playPauseListener);
    }
    
    public void changeLanguage(final Language language, final boolean b) {
        ThreadUtils.assertOnMain();
        if (language != null) {
            this.setLanguage(language);
            this.mPlayer.selectTracks(language.getSelectedAudio(), language.getSelectedSubtitle(), true);
            if (language.getSelectedSubtitle() == null) {
                Log.d("PlayerFragment", "Disable subtitles, none is selected");
                this.mSubtitleManager.clear();
            }
            language.commit();
            if (b && !this.isOfflinePlayback()) {
                Log.d("PlayerFragment", "Starting playback by seek with forceRebuffer to current position");
                this.mReloadOnAudioTrackChange = true;
                this.onStalled();
            }
        }
        Log.d("PlayerFragment", "Language change should be completed");
    }
    
    protected void cleanup() {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "cleanup playerState=" + this.mState.playerState);
        }
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
    }
    
    public void cleanupAndExit() {
        Log.d("PlayerFragment", "cleanupAndExit");
        this.reportCachePlayEndedIfNeeded(IClientLogging$CompletionReason.success);
        this.cleanup();
        Log.d("PlayerFragment", "cleanupAndExit calling finish");
        if (this.isActivityValid() && !this.getActivity().isChangingConfigurations()) {
            this.tryFinishActivity();
        }
    }
    
    public void clearPanel() {
        if (this.mScreen == null) {
            Log.d("PlayerFragment", "Screen was not yet initialized - no need to clear the panel.");
            return;
        }
        if (this.mScreen.getState() == PlayerUiState.PostPlay) {
            Log.d("PlayerFragment", "When in post play do NOT clear panel.");
            return;
        }
        this.mState.setLastActionTime(0L);
        this.mScreen.clearPanel();
    }
    
    MdxTargetSelection createMdxTargetSelection(final Pair<String, String>[] array, final String s) {
        return new MdxTargetSelection(array, s, this.mConfig.getPlaybackConfiguration().isLocalPlaybackEnabled());
    }
    
    public void doPause() {
        this.doPause(false);
    }
    
    public void doPlaying() {
        ThreadUtils.assertOnMain();
        this.mMediaSessionController.startMediaSession();
        ++this.playedVideoCount;
        if (Log.isLoggable()) {
            Log.v("PlayerFragment", "doPlaying(), playedVideoCount: " + this.playedVideoCount);
        }
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
        this.mScreen.setSeekbarTrackingEnabled(true);
        this.setCoppolaSeekbarEnabled(true);
        this.onBufferingComplete = true;
        this.mIsLoadingData = false;
        if (this.isActivityValid() && !this.getActivity().hasWindowFocus() && !this.isOfflinePlayback()) {
            Log.d("PlayerFragment", "App is not in focus, pause");
            this.doPause();
        }
        else {
            if (this.isCoppolaPlayback() && Coppola1Utils.showCountdownTimer((Context)this.getActivity()) && !this.allowCoppolaAutoplay && !this.playWhenBufferingComplete && this.playedVideoCount == 1 && this.isInPortrait()) {
                if (Log.isLoggable()) {
                    Log.d("PlayerFragment", "Not allowed to auto-play so pausing. allowCoppolaAutoplay: " + this.allowCoppolaAutoplay + ", playWhenBufferingComplete: " + this.playWhenBufferingComplete);
                }
                this.doPause();
                this.mPlayer.pause();
                return;
            }
            if (this.isOfflinePlayback() && this.mScreen != null) {
                this.mScreen.setMediaImage(false);
            }
        }
    }
    
    public void doSeek(final int n) {
        this.doSeek(n, false);
    }
    
    public void doUnpause() {
        if (!this.isCoppolaWithOldPlayer()) {
            if (this.mState.playerState != PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
                Log.i("PlayerFragment", "doUnpause: Invalid state, exit...:" + this.mState.playerState.getName());
                this.cleanupAndExit();
                return;
            }
            Log.i("PlayerFragment", "doUnpause: resume");
            if (!this.mPlayer.isPlaying()) {
                if (this.mScreen != null) {
                    this.mScreen.setMediaImage(false);
                }
                this.keepScreenOn();
                if (this.mPlayerBackgrounded) {
                    this.mState.setLastActionTime(SystemClock.elapsedRealtime());
                    this.mState.userInteraction();
                    this.mSubtitleManager.onSubtitleRemove();
                    this.mPlayerBackgrounded = false;
                    if (this.mPlayerSuspendNotification != null) {
                        this.mPlayerSuspendNotification.cancelNotification();
                    }
                    if (this.pausePlaybackOnPlayerBackgrounded) {
                        Log.d("PlayerFragment", "Pause playback is true, so not resuming playback from player backgrounded case.");
                        return;
                    }
                    this.mPlayer.seekWithFuzzRange(-5000, 5000);
                }
                else {
                    this.mPlayer.unpause();
                    this.mMediaSessionController.setMediaSessionState(3);
                    this.notifyPlayPauseToListener(false);
                }
                Log.i("PlayerFragment", "doUnpause() - scheduling \"browse_play\" end reporting");
                this.scheduleBrowsePlayRunnableIfNeeded(false);
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
                mScreen.setZoomImage(false);
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
                mScreen.setZoomImage(true);
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
    
    public View$OnClickListener getPlayPauseListener() {
        return this.playPauseListener;
    }
    
    public IPlayer getPlayer() {
        return this.mPlayer;
    }
    
    public PlayScreen getScreen() {
        return this.mScreen;
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
            String s = this.getResources().getString(2131296987, new Object[] { LocalizationUtils.forceLayoutDirectionIfNeeded(asset.getParentTitle()), asset.getSeasonAbbrSeqLabel(), asset.getEpisodeNumber(), LocalizationUtils.forceLayoutDirectionIfNeeded(asset.getTitle()) });
            if (asset.isNSRE()) {
                s = this.getResources().getString(2131296988, new Object[] { LocalizationUtils.forceLayoutDirectionIfNeeded(asset.getParentTitle()), LocalizationUtils.forceLayoutDirectionIfNeeded(asset.getTitle()) });
            }
            return s;
        }
        return this.getResources().getString(2131296989, new Object[] { asset.getTitle() });
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
        if (this.isOfflinePlayback()) {
            Log.i("PlayerFragment", "offline playback network is not needed.");
            return true;
        }
        final LogMobileType connectionType = ConnectivityUtils.getConnectionType((Context)this.getNetflixActivity());
        if (this.isCoppolaWithOldPlayer()) {
            return false;
        }
        if (this.isMDXTargetSelected() && Coppola1Utils.isAutoplay((Context)this.getActivity())) {
            this.moveToMDXPLayback();
            return false;
        }
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
        final boolean playbackInWifiOnly = BandwidthUtility.isPlaybackInWifiOnly((Context)this.getActivity());
        Log.i("PlayerFragment", "3G Preference setting: " + playbackInWifiOnly);
        if (playbackInWifiOnly) {
            Log.w("PlayerFragment", "We should warn user if he is on NON WIFI network!");
            final boolean booleanPref = PreferenceUtils.getBooleanPref((Context)this.getActivity(), "nf_play_user_no_wifi_warned_already", false);
            if (!this.isInPortrait() || !this.isCoppolaPlayback() || !booleanPref) {
                this.nonWifiPlayWarning(this.isCoppolaPlayback() && this.isInPortrait());
                PreferenceUtils.putBooleanPref((Context)this.getActivity(), "nf_play_user_no_wifi_warned_already", true);
            }
            else {
                Log.i("PlayerFragment", "User was already notified regarding disabled auto-playback");
            }
            return false;
        }
        Log.d("PlayerFragment", "Warning is not required, proceed with playback");
        return true;
    }
    
    public void handleMdxClick() {
        if (this.isMDXTargetSelected() && (!this.isCoppolaPlayback() || Coppola1Utils.isAutoplay((Context)this.getActivity()) || (this.mPlayer != null && this.mPlayer.isBufferingCompleted()))) {
            this.reportCachePlayEndedIfNeeded(IClientLogging$CompletionReason.canceled);
            if (this.mPlaybackStateListener != null) {
                this.mPlaybackStateListener.onPlaybackStopped();
            }
            this.moveToMDXPLayback();
        }
        else if (!this.isCoppolaPlayback() || Coppola1Utils.isAutoplay((Context)this.getActivity())) {
            if (this.mPlaybackStateListener != null) {
                this.mPlaybackStateListener.onPlaybackStarted();
            }
            this.allowCoppolaAutoplay = true;
            this.continueInitAfterPlayVerify();
        }
    }
    
    public void hideNavigationBar() {
        if (this.mScreen != null) {
            this.mScreen.hideNavigationBar();
        }
    }
    
    public boolean isCoppolaPlayback() {
        return Coppola1Utils.isCoppolaContextForPlayer((Context)this.getActivity());
    }
    
    public boolean isInPortrait() {
        return this.getActivity() != null && DeviceUtils.isPortrait((Context)this.getActivity());
    }
    
    public boolean isListening() {
        return this.mIsListening;
    }
    
    public boolean isLoadingData() {
        return this.mIsLoadingData;
    }
    
    public boolean isMDXTargetSelected() {
        return MdxUtils.isCurrentMdxTargetAvailable(this.getServiceManager());
    }
    
    public boolean isPaused() {
        return this.mPlayer != null && !this.mPlayer.isPlaying();
    }
    
    public boolean isPostPlayed() {
        return this.postPlayed;
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
    
    public void launchPlayback() {
        this.allowCoppolaAutoplay = true;
        Log.v("PlayerFragment", "launchPlayback()");
        if (!Coppola1Utils.isNewPlayerExperience((Context)this.getActivity()) || this.isMDXTargetSelected()) {
            PlaybackLauncher.startPlaybackAfterPIN(this.getNetflixActivity(), this.mAsset);
        }
        else {
            if (!Coppola1Utils.isAutoplay((Context)this.getActivity())) {
                UserActionLogUtils.reportPlayActionStarted((Context)this.getActivity(), null, IClientLogging$ModalView.playback);
                verifyPlayToContinue(this, this.mAsset);
                return;
            }
            if (Coppola1Utils.showCountdownTimer((Context)this.getActivity())) {
                if (this.onBufferingComplete) {
                    this.doUnpause();
                    return;
                }
                Log.d("PlayerFragment", "Buffering not complete - can't unpause.");
                this.playWhenBufferingComplete = true;
            }
        }
    }
    
    public void notifyOthersOfPlayStart() {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START");
        this.mAsset.toIntent(intent);
        intent.putExtra("playbackType", this.mPlaybackType.getValue());
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "Updating PlayerActivity's intent with: " + this.mAsset.getPlayableId());
        }
        if (this.isCoppolaPlayback()) {
            ((CoppolaDetailsActivity)this.getActivity()).updateIntent(this.mAsset);
        }
        else if (this.getActivity() instanceof PlayerActivity) {
            ((PlayerActivity)this.getActivity()).updateIntent(this.mAsset);
        }
        else {
            Log.e("PlayerFragment", "notifyOthersOfPlayStart() got unsupported activity type - skipping...");
        }
        this.getActivity().sendBroadcast(intent);
        Log.v("PlayerFragment", "Intent PLAYER_PLAY_START sent");
    }
    
    public void notifyOthersOfPlayStop() {
        if (this.mAsset != null) {
            PinVerifier.getInstance().registerPlayEvent(this.mAsset.isPinProtected());
            final Intent intent = this.mAsset.toIntent(new Intent("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP"));
            intent.putExtra("playbackType", this.mPlaybackType.getValue());
            this.getActivity().sendBroadcast(intent);
            BookmarkStore.getInstance().setBookmark(this.getCurrentProfileGuidOrNull(), PlaybackBookmark.fromAsset(this.mAsset));
            Log.v("PlayerFragment", "Intent PLAYER_PLAY_STOP sent");
        }
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
                if (this.mPlayer != null) {
                    this.mPlayer.setAudioDuck(false);
                }
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
                if (this.mPlayer != null) {
                    this.mPlayer.setAudioDuck(true);
                }
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
        if (this.isCoppolaPlayback() && this.isMDXTargetSelected()) {
            Log.i("PlayerFragment", "Stopping local playback since we already started the remote one.");
            this.resetCurrentPlayback();
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
        boolean zoom = true;
        super.onConfigurationChanged(configuration);
        if (configuration.hardKeyboardHidden == 1) {
            Log.d("PlayerFragment", "keyboard out");
        }
        else if (configuration.hardKeyboardHidden == 2) {
            Log.d("PlayerFragment", "keyboard in");
        }
        if (this.isCoppolaPlayback() && configuration.orientation == 2 && (!Coppola1Utils.isAutoplay((Context)this.getActivity()) || Coppola1Utils.showCountdownTimer((Context)this.getActivity())) && !this.mState.videoLoaded && this.mAsset != null) {
            Log.e("PlayerFragment", "Scheduling playback when user rotated to landscape.");
            this.launchPlayback();
        }
        if (this.mScreen != null) {
            this.mScreen.onConfigurationChanged(configuration);
            final PlayScreen mScreen = this.mScreen;
            if (configuration.orientation != 1) {
                zoom = false;
            }
            mScreen.setZoom(zoom);
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "onCreate started " + this.hashCode());
        }
        this.keepScreenOn();
        AndroidUtils.logDeviceDensity(this.getActivity());
        this.getWindow().getAttributes().buttonBrightness = 0.0f;
        this.mState.reset();
        if (!this.isCoppolaPlayback() || Coppola1Utils.isAutoplay((Context)this.getActivity()) || !this.isInPortrait()) {
            UserActionLogUtils.reportPlayActionStarted((Context)this.getActivity(), null, IClientLogging$ModalView.playback);
        }
        this.mState.playStartInProgress.set(true);
        this.mSubtitleManager = new SafeSubtitleManager(this);
        this.mMediaSessionController = new PlayerMediaSessionController((Context)this.getNetflixActivity(), new PlayerFragment$1(this));
        ConsolidatedLoggingUtils.pauseReporting((Context)this.getActivity());
        Log.d("PlayerFragment", "onCreate done");
    }
    
    public void onCreateOptionsMenu(final Menu mPostponedPanelMenu, final MenuInflater menuInflater) {
        super.onCreateOptionsMenu(mPostponedPanelMenu, menuInflater);
        if (this.staticToolbarMenu != mPostponedPanelMenu) {
            return;
        }
        if (this.mScreen == null || this.mScreen.getTopPanel() == null || this.getView() == null) {
            Log.w("PlayerFragment", "onCreateOptionsMenu() was triggered before UI was initialized. Scheduling panel menu update to be called later.");
            this.mPostponedPanelMenu = mPostponedPanelMenu;
            return;
        }
        this.mScreen.getTopPanel().onCreateOptionsMenu(mPostponedPanelMenu);
        this.mPostponedPanelMenu = null;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.i("PlayerFragment", "onCreateView");
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mRootLayout = (ViewGroup)new FrameLayout((Context)this.getActivity());
        this.setHasOptionsMenu(true);
        return (View)this.mRootLayout;
    }
    
    @Override
    public void onDestroy() {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "====> Destroying PlayerFragment " + this.hashCode());
        }
        if (this.mPlayerBackgrounded && !Coppola1Utils.shouldInjectPlayerFragment((Context)this.getActivity())) {
            this.cleanupAndExit();
        }
        this.getWindow().getAttributes().buttonBrightness = -1.0f;
        this.releaseLockOnScreen();
        this.cancelBrowsePlayRunnable();
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
        this.mMediaSessionController.destroy();
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
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        if (!this.isActivityValid()) {
            Log.w("PlayerFragment", "onManagerReady() was called when activity is already not in valid state - skipping...");
        }
        else {
            if (Log.isLoggable()) {
                Log.d("PlayerFragment", "ServiceManager ready: " + status.getStatusCode());
            }
            ThreadUtils.assertOnMain();
            this.mIsTablet = this.getNetflixActivity().isTablet();
            this.mResources = ResourceHelper.newInstance(this.mIsTablet);
            if (!this.requestDetailsIfNeeded(serviceManager)) {
                this.updateUI(null);
            }
            else if (this.isActivityValid() && !this.isOfflinePlayback()) {
                int fragmentContentView;
                if (DeviceUtils.isTabletByContext(this.getActivity().getBaseContext())) {
                    fragmentContentView = 2130903257;
                }
                else {
                    fragmentContentView = 2130903253;
                }
                this.setFragmentContentView(fragmentContentView);
            }
            if (serviceManager.getErrorHandler() != null) {
                serviceManager.getErrorHandler().clear();
            }
            if (this.getActivity() != null) {
                NflxProtocolUtils.reportUserOpenedNotification(serviceManager, this.getActivity().getIntent());
            }
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.e("PlayerFragment", "NetflixService is NOT available!");
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
        if (Coppola1Utils.isAutoplay((Context)this.getActivity()) && nccpError instanceof NccpActionId) {
            if (((NccpActionId)nccpError).getActionId() == 1) {
                Log.w("PlayerFragment", "SPY-8915: Ignoring NCCP error, action ID 1 for Coppola autoplay cases");
                ErrorLoggingManager.logHandledException("SPY-8915: Ignoring NCCP error, action ID 1 for Coppola autoplay cases");
            }
            return;
        }
        this.addError(nccpError);
    }
    
    public void onNrdFatalError() {
        Log.w("PlayerFragment", "onNrdFatalError");
        if (!this.isActivityValid()) {
            Log.w("PlayerFragment", "Activity isn't already in a valid state - no need to show the error dialog");
            return;
        }
        this.getNetflixActivity().displayDialog(AlertDialogFactory.createDialog((Context)this.getActivity(), this.mHandler, new AlertDialogFactory$AlertDialogDescriptor("", this.getString(2131296336), null, new PlayerFragment$20(this))));
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        return (this.mScreen != null && this.mScreen.getTopPanel().onOptionsItemSelected(menuItem)) || super.onOptionsItemSelected(menuItem);
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
        this.mMediaSessionController.stopMediaSession();
        final PostPlay postPlaySafely = this.getPostPlaySafely();
        if (postPlaySafely != null) {
            postPlaySafely.onPause();
        }
        if (this.mScreen != null) {
            this.mScreen.onPause();
        }
        Log.d("PlayerFragment", "onPause called done");
        super.onPause();
    }
    
    public void onPlayVerified(final boolean b, final PlayVerifierVault playVerifierVault) {
        Log.d("nf_pin", String.format("%s onPlayVerification vault: %s", PlayerFragment.class.getSimpleName(), playVerifierVault));
        if (b && PlayVerifierVault$RequestedBy.PLAYER.getValue().equals(playVerifierVault.getInvokeLocation())) {
            this.continueInitAfterPlayVerify();
            return;
        }
        Log.d("PlayerFragment", "Age/Pin verification failed cannot proceed - stop playback");
        this.cleanupAndExit();
    }
    
    public void onPlaybackError(final IPlayer$PlaybackError player$PlaybackError) {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "onPlaybackError, " + player$PlaybackError.getMessage());
        }
        final ErrorDescriptor handlerForPlaybackError = PlayerErrorDialogDescriptorFactory.getHandlerForPlaybackError(this, player$PlaybackError);
        if (handlerForPlaybackError != null && handlerForPlaybackError.getData() != null) {
            final ServiceManager serviceManager = this.getServiceManager();
            if (serviceManager != null && serviceManager.getErrorHandler() != null) {
                serviceManager.getErrorHandler().addError(handlerForPlaybackError);
            }
        }
    }
    
    public void onPlaying() {
        synchronized (this) {
            Log.d("PlayerFragment", "onPlaying()");
            if (this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
                this.doPlaying();
            }
            else {
                Log.e("PlayerFragment", "onPlaying not in correct state, ActivityState: " + this.mState.playerState.getName());
                this.cleanupAndExit();
            }
        }
    }
    
    public void onPrepared(final Watermark watermark) {
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
                if (watermark != null) {
                    if (Log.isLoggable()) {
                        Log.d("PlayerFragment", "Watermark found: " + watermark);
                    }
                    this.mScreen.addWatermark(watermark);
                }
                else {
                    Log.d("PlayerFragment", "Watermark not found...");
                }
                this.selectInitialTracks();
                return;
            }
            catch (Exception ex) {
                Log.e("PlayerFragment", ex, "Failed to start player", new Object[0]);
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
        if (this.mScreen != null) {
            this.mScreen.onResume();
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
            this.notifyPlayPauseToListener(false);
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
                        this.mScreen.setMediaImage(false);
                    }
                    if (this.mState.lowBandwidth) {
                        Log.d("PlayerFragment", "Enabled Toast");
                        Toast.makeText((Context)this.getActivity(), 2131296676, 1).show();
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
        final PostPlay postPlaySafely = this.getPostPlaySafely();
        if (postPlaySafely != null) {
            postPlaySafely.onStart();
        }
        if (this.mScreen != null) {
            this.mScreen.onStart();
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
            final Asset mAsset = (Asset)bundle.getParcelable("AssetExtra");
            if (Log.isLoggable()) {
                Log.d("PlayerFragment", "Asset received: " + mAsset);
            }
            if (mAsset != null) {
                this.mAsset = mAsset;
            }
            this.secondsFromStart = this.getArguments().getInt("BookmarkSecondsFromStart", -1);
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
            Log.d("PlayerFragment", "Playout started: " + this.getCurrentAsset());
            ThreadUtils.assertOnMain();
            final PlayerType currentType = PlayerTypeFactory.getCurrentType((Context)this.getNetflixActivity());
            if (this.getCurrentAsset() != null && this.mState.playerState == PlayerFragment$PlayerFragmentState.ACTIVITY_PLAYER_READY) {
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
                UserActionLogUtils.reportPlayActionEnded((Context)this.getActivity(), IClientLogging$CompletionReason.success, null, null, currentType, this.getPlayLocation());
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
                postPlay.fetchPostPlayVideosIfNeeded(playableId, videoType, PostPlayRequestContext.POST_PLAY);
                if (this.mPlaybackStateListener != null) {
                    this.mPlaybackStateListener.onPlaybackStarted();
                    if (PersistentConfig.getCoppola1ABTestCell((Context)this.getActivity()).ordinal() == ABTestConfig$Cell.CELL_THREE.ordinal()) {
                        this.launched = true;
                    }
                }
                this.mScreen.setZoom(this.isInPortrait());
                Log.i("PlayerFragment", "onStarted() - scheduling \"browse_play\" end reporting");
                this.scheduleBrowsePlayRunnableIfNeeded(true);
                this.mScreen.createAdvisories();
            }
            else {
                if (Log.isLoggable()) {
                    Log.e("PlayerFragment", "onStarted not in correct state, ActivityState: " + this.mState.playerState.getName() + "; asset: " + this.getCurrentAsset());
                }
                if (this.isActivityValid()) {
                    UserActionLogUtils.reportPlayActionEnded((Context)this.getActivity(), IClientLogging$CompletionReason.failed, new UIError(RootCause.clientFailure, ActionOnUIError.handledSilently, null, null), null, currentType, this.getPlayLocation());
                }
                this.mState.playStartInProgress.set(false);
                this.cleanupAndExit();
            }
        }
    }
    
    public void onStop() {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "PlayerActivity::onStop called " + this.hashCode());
        }
        final PostPlay postPlaySafely = this.getPostPlaySafely();
        if (postPlaySafely != null) {
            postPlaySafely.onStop();
        }
        if (this.mScreen != null) {
            this.mScreen.onStop();
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
            UserActionLogUtils.reportPlayActionEnded((Context)this.getActivity(), IClientLogging$CompletionReason.canceled, null, null, PlayerTypeFactory.getCurrentType((Context)this.getNetflixActivity()), this.getPlayLocation());
        }
        final String mMaxStreamsReachedDialogId = this.mMaxStreamsReachedDialogId;
        if (mMaxStreamsReachedDialogId != null) {
            Log.d("PlayerFragment", "Report max stream reach dialog ended");
            this.getNetflixActivity().reportUiModelessViewSessionEnded(IClientLogging$ModalView.maxStreamsReached, mMaxStreamsReachedDialogId);
        }
        if (!Coppola1Utils.shouldInjectPlayerFragment((Context)this.getActivity())) {
            this.cleanupAndExit();
        }
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
            this.runOnUiThread(new PlayerFragment$14(this));
            return;
        }
        this.runOnUiThread(new PlayerFragment$15(this));
    }
    
    public void onUpdatePts(final int n) {
        if (!this.isActivityValid()) {
            Log.i("PlayerFragment", "Activity is already not in valid state - skpping onUpdatePts()");
            return;
        }
        this.mScreen.getPostPlay().updatePlaybackPosition(n);
        if (this.mAsset != null) {
            PinVerifier.getInstance().registerPlayEvent(this.mAsset.isPinProtected());
        }
        this.updateAssetWithCurrentPlayPosition();
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
        this.runOnUiThread(new PlayerFragment$12(this));
    }
    
    public void performUpAction() {
        final NetflixActivity netflixActivity = this.getNetflixActivity();
        UIViewLogUtils.reportUIViewCommand((Context)netflixActivity, UIViewLogging$UIViewCommandName.actionBarBackButton, netflixActivity.getUiScreen(), netflixActivity.getDataContext());
        if (netflixActivity.isTaskRoot()) {
            this.startActivity(HomeActivity.createShowIntent(netflixActivity));
        }
    }
    
    public void playNextVideo(final Playable playable, final PlayContext playContext, final boolean b) {
        this.playNextVideo(playable, playContext, b, -1, false);
    }
    
    public void playNextVideo(final Playable playable, final PlayContext playContext, final boolean b, final int n, final boolean b2) {
        final boolean b3 = true;
        if (!this.isActivityValid()) {
            Log.d("PlayerFragment", "Activity already destroyed, ignore next!");
            return;
        }
        boolean b4;
        int postPlayVideoPlayed;
        if (this.mAsset == null) {
            if (Log.isLoggable()) {
                Log.w("PlayerFragment", "Current Asset is null for request to play next video.");
                b4 = false;
                postPlayVideoPlayed = 0;
            }
            else {
                b4 = false;
                postPlayVideoPlayed = 0;
            }
        }
        else {
            final boolean exemptFromInterrupterLimit = this.mAsset.isExemptFromInterrupterLimit();
            if (Log.isLoggable()) {
                Log.d("PlayerFragment", "playNextVideo: Current playable video exempt from interrupter limit = " + exemptFromInterrupterLimit);
            }
            if (b && this.mState.noUserInteraction()) {
                final int n2 = postPlayVideoPlayed = this.mAsset.getPostPlayVideoPlayed();
                if (!exemptFromInterrupterLimit) {
                    postPlayVideoPlayed = n2 + 1;
                }
            }
            else {
                postPlayVideoPlayed = 0;
            }
            if (Log.isLoggable()) {
                Log.d("PlayerFragment", "Play next video, count " + postPlayVideoPlayed + ", from auto play " + b + ", no user interaction " + this.mState.noUserInteraction());
            }
            if (playable == null) {
                if (Log.isLoggable()) {
                    Log.w("PlayerFragment", "Video is null for postplay next playable video.");
                    b4 = false;
                }
                else {
                    b4 = false;
                }
            }
            else {
                b4 = (playable.isPlayableEpisode() && this.mAsset.isEpisode() && StringUtils.safeEquals(this.mAsset.getParentId(), playable.getParentId()));
            }
        }
        this.cleanupAndExit();
        final Asset forPostPlay = Asset.createForPostPlay(playable, playContext, postPlayVideoPlayed, false);
        final boolean b5 = this.mScreen != null && this.mScreen.isAdvisoryDisabled();
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "Is post play episode from same show? " + b4 + ", Was advisory notice disabled? " + b5);
        }
        if (b4 && b5) {
            forPostPlay.setAdvisoryDisabled(true);
        }
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "Asset to play next: " + forPostPlay);
        }
        if (StringUtils.isEmpty(forPostPlay.getPlayableId())) {
            Log.e("PlayerFragment", "PlayableId is null - skip playback");
            ErrorLoggingManager.logHandledException("PlayableId is null - skip playback");
            return;
        }
        while (true) {
            Label_0462: {
                if (!Coppola1Utils.isCoppolaContext((Context)this.getActivity())) {
                    break Label_0462;
                }
                boolean b6 = b3;
                if (Coppola1Utils.isAutoplay((Context)this.getActivity())) {
                    if (!Coppola1Utils.showCountdownTimer((Context)this.getActivity())) {
                        break Label_0462;
                    }
                    b6 = b3;
                }
                PlaybackLauncher.playVideo(this.getNetflixActivity(), forPostPlay, b6, n, b2);
                return;
            }
            boolean b6 = false;
            continue;
        }
    }
    
    public void playbackClosed() {
        Log.d("PlayerFragment", "playbackClosed");
        if (this.mRestartPlayback) {
            Log.d("PlayerFragment", "Reloading Video to start playback");
            this.loadVideo();
            this.mRestartPlayback = false;
        }
    }
    
    public void reload() {
        if (this.isActivityValid() && this.isCoppolaPlayback() && !Coppola1Utils.isNewPlayerExperience((Context)this.getActivity()) && this.mAsset != null) {
            final String playableId = this.mAsset.getPlayableId();
            String s;
            if (this.mAsset.isEpisode()) {
                s = "episodes";
            }
            else {
                s = "movies";
            }
            this.setExternalBundle(getBundle(playableId, s, ((CoppolaDetailsActivity)this.getNetflixActivity()).getPlayContext()));
            this.requestDetailsIfNeeded(this.getServiceManager());
        }
    }
    
    public void removePlayPauseListener(final PlayPauseListener playPauseListener) {
        if (playPauseListener != null && this.playPauseListenerList != null && !this.playPauseListenerList.isEmpty()) {
            this.playPauseListenerList.remove(playPauseListener);
        }
    }
    
    public boolean requestDetailsIfNeeded(final ServiceManager serviceManager) {
        if (Log.isLoggable()) {
            Log.i("PlayerFragment", "requestDetailsIfNeeded");
        }
        Bundle bundle;
        if (this.mExternalBundle == null) {
            bundle = this.getArguments();
        }
        else {
            bundle = this.mExternalBundle;
        }
        final String string = bundle.getString("VideoDetailsIdExtra");
        final boolean availableInMyDownloads = availableInMyDownloads(serviceManager, string);
        if (Log.isLoggable()) {
            Log.i("PlayerFragment", "requestDetailsIfNeeded videoId=" + string + " hasBeenDownloaded=" + availableInMyDownloads);
        }
        if (availableInMyDownloads) {
            return this.requestDetailsIfNeededOffline(string, bundle);
        }
        return this.requestDetailsIfNeededStreaming(string, bundle, serviceManager);
    }
    
    public void requestLayout() {
        this.mRootLayout.requestLayout();
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
        final LanguageChoice initialLanguage = new LocalizationManager((Context)this.getActivity(), subtitleTrackList, audioTrackList, audioSubtitleDefaultOrderInfo, this.isOfflinePlayback()).findInitialLanguage();
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
        boolean b;
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
            b = false;
        }
        this.mPlayer.selectTracks(audio, subtitle, false);
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
        this.mScreen.onLanguageUpdated(language);
    }
    
    public void setMaxStreamsReachedDialogId(final String mMaxStreamsReachedDialogId) {
        this.mMaxStreamsReachedDialogId = mMaxStreamsReachedDialogId;
    }
    
    public void setOnStartedPlaybackListener(final PlayerFragment$OnPlaybackStateListener mPlaybackStateListener) {
        this.mPlaybackStateListener = mPlaybackStateListener;
    }
    
    public void setPauseOnPlayerBackgrounded(final boolean pausePlaybackOnPlayerBackgrounded) {
        this.pausePlaybackOnPlayerBackgrounded = pausePlaybackOnPlayerBackgrounded;
    }
    
    public void setPostPlayed(final boolean postPlayed) {
        this.postPlayed = postPlayed;
    }
    
    public void setSubtitleVisiblity(final boolean subtitleVisibility) {
        if (this.mSubtitleManager == null) {
            if (Log.isLoggable()) {
                Log.d("PlayerFragment", "setSubtitleVisiblity: subtitleManager is null");
            }
            return;
        }
        this.mSubtitleManager.setSubtitleVisibility(subtitleVisibility);
    }
    
    public void setTargetSelection() {
        if (this.mScreen == null || !this.isActivityValid()) {
            Log.i("PlayerFragment", "Skipping setTargetSelection()");
            return;
        }
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
    
    public void setUserInteraction() {
        if (this.mState != null) {
            this.mState.userInteraction();
            Log.d("PlayerFragment", "Set user interaction to true");
        }
    }
    
    public void showControlScreenOverlay(final boolean b) {
        final PlayScreen mScreen = this.mScreen;
        if (mScreen != null) {
            mScreen.onTap(b);
            return;
        }
        Log.w("PlayerFragment", "Screen is null!");
    }
    
    public void showNavigationBar() {
        if (this.mScreen != null) {
            this.mScreen.showNavigationBar();
        }
    }
    
    public void startScreenUpdateTask() {
        this.repostOnEverySecondRunnable(1000);
        Log.d("PlayerFragment", "===>> Screen update thread started");
    }
    
    public void stopScreenUpdateTask() {
        this.mHandler.removeCallbacks(this.onEverySecond);
        Log.d("PlayerFragment", "===>> Screen update thread canceled");
    }
    
    public boolean wasBufferingComplete() {
        return this.onBufferingComplete;
    }
}
