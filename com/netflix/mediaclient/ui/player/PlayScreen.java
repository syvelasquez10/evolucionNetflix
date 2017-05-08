// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import java.util.Date;
import junit.framework.Assert;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.ExpiringContentAction;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.IExpiringContentWarning;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManager;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.media.Language;
import android.content.res.Configuration;
import android.app.Activity;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.media.Watermark$Anchor;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.SubtitleUtils;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AutoResizeTextView;
import com.netflix.mediaclient.media.Watermark;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import android.support.v7.widget.Toolbar;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.os.Build;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.view.SurfaceHolder;
import android.os.Handler;
import android.widget.ViewFlipper;
import android.widget.TextView;
import android.view.View;
import android.animation.Animator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PlayScreen implements Screen
{
    protected static final String TAG = "screen";
    private final Runnable contentAdvisoryNoticeCompletionRunnable;
    private final Runnable contentAdvisoryStartNoticeRunnable;
    private int currentTimelineProgress;
    protected PlayScreen$Listeners listeners;
    protected RelativeLayout mBackground;
    protected ImageView mBif;
    private Animator mBifAnim;
    protected BottomPanel mBottomPanel;
    protected View mBufferingOverlay;
    private ContentAdvisoryController mContentAdvisory;
    protected PlayerFragment mController;
    protected TextView mDebugData;
    private PlayScreenDecorator mDecorator;
    private PlayScreen$ExpiringContent mExpiringContent;
    protected ViewFlipper mFlipper;
    private final Handler mHandler;
    protected SurfaceHolder mHolder;
    private boolean mIsAdvisoryDisabled;
    protected View mLoadingOverlay;
    protected boolean mNavigationBarSetVisibleInProgress;
    private PlayerUiState mPendingState;
    private String mPlaybackControlOverlayId;
    private PostPlay mPostPlayManager;
    private View mQuickActions;
    private PlayerUiState mState;
    protected final TappableSurfaceView mSurface;
    protected View mTabletBifsLayout;
    protected TopPanel mTopPanel;
    protected RelativeLayout mWatermarkDisplayArea;
    private boolean mZoomEnabled;
    
    PlayScreen(final PlayerFragment mController, final PlayScreen$Listeners listeners, final PostPlayFactory$PostPlayType postPlayFactory$PostPlayType) {
        this.mState = PlayerUiState.Loading;
        this.mNavigationBarSetVisibleInProgress = false;
        this.mZoomEnabled = true;
        this.mHandler = new Handler();
        this.mContentAdvisory = null;
        this.contentAdvisoryStartNoticeRunnable = new PlayScreen$1(this);
        this.contentAdvisoryNoticeCompletionRunnable = new PlayScreen$2(this);
        if (mController == null || listeners == null) {
            throw new IllegalArgumentException("Argument can not be null!");
        }
        this.mController = mController;
        this.listeners = listeners;
        this.mTopPanel = new TopPanel(mController, listeners);
        this.mBottomPanel = new BottomPanel(mController, listeners);
        final View view = mController.getView();
        this.mSurface = (TappableSurfaceView)view.findViewById(2131690087);
        if (this.mSurface != null) {
            this.mSurface.addTapListener(listeners.tapListener);
            this.mHolder = this.mSurface.getHolder();
            this.mSurface.setSurfaceMeasureListener(listeners.surfaceMeasureListener);
        }
        if (this.mHolder != null) {
            this.mHolder.addCallback(listeners.surfaceListener);
        }
        this.mFlipper = (ViewFlipper)view.findViewById(2131689832);
        this.mBackground = (RelativeLayout)view.findViewById(2131689831);
        this.mWatermarkDisplayArea = (RelativeLayout)view.findViewById(2131690090);
        this.mBufferingOverlay = view.findViewById(2131690109);
        this.mLoadingOverlay = view.findViewById(2131690091);
        int n;
        if (mController.getNetflixActivity().isTablet()) {
            n = 2131690106;
        }
        else {
            n = 2131690089;
        }
        this.mBif = (ImageView)view.findViewById(n);
        this.mTabletBifsLayout = view.findViewById(2131690105);
        this.mQuickActions = view.findViewById(2131690098);
        this.mPostPlayManager = PostPlayFactory.create(mController, postPlayFactory$PostPlayType);
        this.moveToState(PlayerUiState.Loading);
        final Asset currentAsset = mController.getCurrentAsset();
        if (currentAsset == null) {
            Log.w("screen", "PlayerFragment getCurrentAsset() is null. Content advisory notice is disabled.");
        }
        else if (!(this.mIsAdvisoryDisabled = (currentAsset.isAdvisoryDisabled() || StringUtils.isEmpty(currentAsset.getAdvisoryRating()) || StringUtils.isEmpty(currentAsset.getAdvisoryDescription())))) {
            (this.mContentAdvisory = ContentAdvisoryController.createInstance(mController)).setDisplayDuration(currentAsset.getAdvisoryDisplayDuration() * 1000);
            this.mContentAdvisory.populateViews(currentAsset.getAdvisoryRating(), mController.getCurrentAsset().getAdvisoryDescription());
        }
        this.setupABTest();
    }
    
    static PlayScreen createInstance(final PlayerFragment playerFragment, final PlayScreen$Listeners playScreen$Listeners, final PostPlayFactory$PostPlayType postPlayFactory$PostPlayType) {
        final PlayScreenJB playScreenJB = null;
        final int androidVersion = AndroidUtils.getAndroidVersion();
        PlayScreen playScreen;
        if (androidVersion >= 16) {
            Log.d("screen", "PlayScreen for JB (Android 4.1+");
            playScreen = new PlayScreenJB(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
        }
        else if (Build.MANUFACTURER.equals("Amazon") && (Build.MODEL.equals("KFOT") || Build.MODEL.equals("KFTT") || Build.MODEL.equals("KFJWA") || Build.MODEL.equals("KFJWI"))) {
            Log.d("screen", "PlayScreen for Amazon Kindle HD");
            playScreen = new PlayScreenKindleHD(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
        }
        else if (androidVersion >= 14) {
            Log.d("screen", "PlayScreen for ICS (Android 4+");
            playScreen = new PlayScreenICS(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
        }
        else {
            playScreen = playScreenJB;
            if (Build.MODEL.equals("Kindle Fire")) {
                playScreen = playScreenJB;
                if (Build.MANUFACTURER.equals("Amazon")) {
                    Log.d("screen", "PlayScreen for Amazon Kindle Fire");
                    playScreen = new PlayScreenKindleFire(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
                }
            }
        }
        PlayScreen playScreen2;
        if ((playScreen2 = playScreen) == null) {
            Log.d("screen", "PlayScreen for Froyo/Gingerbread (Android 2.2-2.3) - default");
            playScreen2 = new PlayScreen(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
        }
        playScreen2.setDecorator(new InteractiveMomentsDecorator(playScreen2));
        if (playerFragment.isCoppolaPlayback()) {
            playScreen2.setDecorator(new CoppolaLoadingDecorator(new CoppolaControlsDecorator(playScreen2.getDecorator())));
        }
        return playScreen2;
    }
    
    private static boolean isBrowseValid(final NetflixFrag netflixFrag) {
        return netflixFrag != null && netflixFrag.getServiceManager() != null && netflixFrag.getServiceManager().getBrowse() != null;
    }
    
    private boolean isCoppolaPortrait() {
        return this.mController.isCoppolaPlayback() && this.mController.isInPortrait();
    }
    
    private boolean isZoomEnabled() {
        if (Log.isLoggable()) {
            Log.d("screen", "mZoomEnabled = " + this.mZoomEnabled + ", surface.canVideoBeZoomed()=" + this.mSurface.canVideoBeZoomed());
        }
        return this.mZoomEnabled && this.mSurface.canVideoBeZoomed();
    }
    
    private void moveToInterrupted() {
        this.mController.getNetflixActivity().removeVisibleDialog();
        if (this.mController.getNetflixActivity().isDialogFragmentVisible()) {
            this.mController.getNetflixActivity().removeDialogFrag();
        }
        this.clearPanel();
        this.mNavigationBarSetVisibleInProgress = true;
        this.showNavigationBar();
        this.mController.getSubtitleManager().setSubtitleVisibility(false);
        Log.d("screen", "Interrupted");
    }
    
    private void moveToLoaded() {
        Log.d("screen", "STATE_LOADED");
        this.mBottomPanel.enableButtons(!this.mController.isStalled());
        final int color = this.mController.getResources().getColor(2131624150);
        if (this.mBackground != null) {
            this.mBackground.setBackgroundColor(color);
        }
        if (this.mSurface != null) {
            this.mSurface.setBackgroundColor(color);
        }
        if (this.mPlaybackControlOverlayId != null) {
            this.mController.getNetflixActivity().reportUiModelessViewSessionEnded(IClientLogging$ModalView.playbackControls, this.mPlaybackControlOverlayId);
            this.mPlaybackControlOverlayId = null;
        }
        this.playerOverlayVisibility(false);
    }
    
    private void moveToLoadedTapped() {
        Log.d("screen", "STATE_LOADED_TAPPED");
        this.mBottomPanel.enableButtons(!this.mController.isStalled());
        final int color = this.mController.getResources().getColor(2131624150);
        if (this.mBackground != null) {
            this.mBackground.setBackgroundColor(color);
        }
        if (this.mSurface != null) {
            this.mSurface.setBackgroundColor(color);
        }
        this.mPlaybackControlOverlayId = this.mController.getNetflixActivity().reportUiModelessViewSessionStart(IClientLogging$ModalView.playbackControls);
        this.playerOverlayVisibility(true);
    }
    
    private void moveToLoading() {
        Log.d("screen", "STATE_LOADING, default");
    }
    
    static int resolveContentView(final PostPlayFactory$PostPlayType postPlayFactory$PostPlayType) {
        if (postPlayFactory$PostPlayType == PostPlayFactory$PostPlayType.EpisodesForPhone) {
            Log.d("screen", "playout_phone_episode");
            return 2130903226;
        }
        if (postPlayFactory$PostPlayType == PostPlayFactory$PostPlayType.EpisodesForTablet) {
            Log.d("screen", "playout_tablet_episode");
            return 2130903230;
        }
        if (postPlayFactory$PostPlayType == PostPlayFactory$PostPlayType.RecommendationForTablet) {
            Log.d("screen", "playout_tablet_movie");
            return 2130903231;
        }
        Log.d("screen", "playout_phone_movie");
        return 2130903227;
    }
    
    private void setToolbarVisibility(final boolean b) {
        boolean b2 = false;
        if (this.mController.getNetflixActivity().getNetflixActionBar() != null) {
            final Toolbar toolbar = this.mController.getNetflixActivity().getNetflixActionBar().getToolbar();
            if (b && !toolbar.isShown()) {
                toolbar.setVisibility(0);
            }
            if (!this.isCoppolaPortrait() || this.getPlayerFragment().getPlayer().isPlaying() || b) {
                if (toolbar.getAlpha() > 0.0f) {
                    b2 = true;
                }
                if (b2 != b) {
                    AnimationUtils.startViewAppearanceAnimation((View)toolbar, b);
                }
            }
        }
    }
    
    private void setupABTest() {
        if (this.mController != null && this.mController.getServiceManager() != null) {
            final ABTestConfig abTestConfiguration_6634 = this.mController.getServiceManager().getConfiguration().getABTestConfiguration_6634();
            if (abTestConfiguration_6634 != null && abTestConfiguration_6634.getCell() == ABTestConfig$Cell.CELL_ONE) {
                this.mExpiringContent = new PlayScreen$ExpiringContent(this);
            }
        }
    }
    
    void addWatermark(final Watermark watermark) {
        if (this.mWatermarkDisplayArea != null && watermark != null && StringUtils.isNotEmpty(watermark.getIdentifier())) {
            if (Log.isLoggable()) {
                Log.d("screen", "Display watermark " + watermark);
            }
            final AutoResizeTextView autoResizeTextView = new AutoResizeTextView((Context)this.mController.getActivity());
            autoResizeTextView.setGravity(119);
            final int dipToPixels = AndroidUtils.dipToPixels((Context)this.mController.getActivity(), 5);
            autoResizeTextView.setPadding(dipToPixels, dipToPixels, dipToPixels, dipToPixels);
            autoResizeTextView.setText((CharSequence)this.mController.getActivity().getString(2131231221, new Object[] { watermark.getIdentifier() }));
            float n;
            if (this.mController.getNetflixActivity().isTablet()) {
                n = this.mController.getResources().getDimension(2131362247);
            }
            else {
                n = this.mController.getResources().getDimension(2131362245);
            }
            SubtitleUtils.applyStyle(autoResizeTextView, watermark.getStyle((Context)this.mController.getActivity()), n);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
            relativeLayout$LayoutParams.addRule(14);
            if (Watermark$Anchor.top_center == watermark.getAnchor()) {
                relativeLayout$LayoutParams.addRule(10);
            }
            else {
                relativeLayout$LayoutParams.addRule(12);
            }
            this.mWatermarkDisplayArea.addView((View)autoResizeTextView, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        }
    }
    
    public boolean canExitPlaybackEndOfPlay() {
        while (true) {
            boolean b = false;
            Label_0083: {
                synchronized (this) {
                    if (this.mState == PlayerUiState.PostPlay) {
                        Log.d("screen", "We are in post play state, do not exit player activity");
                        this.mPostPlayManager.endOfPlay();
                    }
                    else {
                        if (!this.mPostPlayManager.wasPostPlayDismissed()) {
                            break Label_0083;
                        }
                        Log.d("screen", "Postplay was dismissed, force postplay");
                        this.moveToState(PlayerUiState.PostPlay);
                        this.mPostPlayManager.endOfPlay();
                        this.mPostPlayManager.setBackgroundImageVisible(true);
                    }
                    return b;
                }
            }
            Log.d("screen", "Not in postplay, exit activity");
            b = true;
            return b;
        }
    }
    
    public void changeActionState(final boolean b) {
        this.changeActionState(b, true);
    }
    
    public void changeActionState(final boolean b, final boolean b2) {
        if (!this.mController.isActivityValid()) {
            Log.i("screen", "changeActionState() was called when activity is already not in valid state - skipping...");
        }
        else {
            if (this.mTopPanel != null) {
                this.mTopPanel.changeActionState(b);
            }
            if (this.mBottomPanel != null) {
                this.mBottomPanel.changeActionState(b, b2);
            }
        }
    }
    
    void clearPanel() {
        this.mNavigationBarSetVisibleInProgress = false;
        if (this.mState == PlayerUiState.Loading) {
            return;
        }
        this.moveToState(PlayerUiState.Playing);
    }
    
    void destroy() {
        synchronized (this) {
            if (this.listeners != null) {
                if (this.mSurface != null) {
                    this.mSurface.removeTapListener(this.listeners.tapListener);
                }
                if (this.mHolder != null) {
                    this.mHolder.removeCallback(this.listeners.surfaceListener);
                }
            }
            if (this.mTopPanel != null) {
                this.mTopPanel.destroy();
            }
            if (this.mBottomPanel != null) {
                this.mBottomPanel.destroy();
            }
            if (this.mPostPlayManager != null) {
                this.mPostPlayManager.destroy();
            }
            if (this.mDecorator != null) {
                this.mDecorator.onDestroy();
            }
        }
    }
    
    public void enableButtons(final boolean b) {
        if (!this.isCoppolaPortrait()) {
            this.mBottomPanel.enableButtons(b);
        }
    }
    
    public void finishDragging() {
        this.setTopPanelVisibility(true);
        if (this.mDecorator != null) {
            this.mDecorator.setDraggingState(false);
        }
        if (this.isCoppolaPortrait()) {
            this.setToolbarVisibility(true);
            return;
        }
        this.mBottomPanel.finishDragging();
    }
    
    @Override
    public Activity getController() {
        return this.mController.getNetflixActivity();
    }
    
    public int getCurrentTimelineProgress() {
        return this.currentTimelineProgress;
    }
    
    public PlayScreenDecorator getDecorator() {
        return this.mDecorator;
    }
    
    SurfaceHolder getHolder() {
        return this.mHolder;
    }
    
    public PlayScreen$Listeners getListeners() {
        return this.listeners;
    }
    
    PlayerFragment getPlayerFragment() {
        return this.mController;
    }
    
    PostPlay getPostPlay() {
        return this.mPostPlayManager;
    }
    
    public PlayerUiState getState() {
        return this.mState;
    }
    
    public final TappableSurfaceView getSurfaceView() {
        return this.mSurface;
    }
    
    public String getTimeStringForMs() {
        return this.mBottomPanel.getFormatter().getStringForMs(this.mBottomPanel.getCurrentProgress());
    }
    
    public int getTimeXAndUpdateHandler(final View view) {
        return this.mBottomPanel.getTimeXAndUpdateHandler(view);
    }
    
    public TopPanel getTopPanel() {
        return this.mTopPanel;
    }
    
    void hideContentAdvisory() {
        if (this.mContentAdvisory == null) {
            return;
        }
        this.mContentAdvisory.hide(false);
        this.mHandler.removeCallbacks(this.contentAdvisoryStartNoticeRunnable);
        this.mHandler.removeCallbacks(this.contentAdvisoryNoticeCompletionRunnable);
    }
    
    void hideNavigationBar() {
        Log.d("screen", "hide nav noop");
    }
    
    protected void hideQuickActions() {
        if (this.mQuickActions != null) {
            this.mQuickActions.setVisibility(8);
        }
    }
    
    public void hideTopBottomPanel() {
        if (this.mBottomPanel != null) {
            this.mBottomPanel.hide();
        }
        if (this.mTopPanel != null) {
            this.mTopPanel.hide();
        }
    }
    
    boolean inInterruptedOrPendingInterrupted() {
        synchronized (this) {
            return this.mState == PlayerUiState.Interrupter || this.mPendingState == PlayerUiState.Interrupter;
        }
    }
    
    boolean inPostPlayOrPendingPostplay() {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("screen", "inPostPlayOrPendingPostplay, state: " + this.mState + ", pending state: " + this.mPendingState);
            }
            return this.mState == PlayerUiState.PostPlay || this.mPendingState == PlayerUiState.PostPlay;
        }
    }
    
    void initProgress(final int timelineMaxProgress) {
        this.mBottomPanel.initProgress(timelineMaxProgress);
        if (this.mDecorator != null) {
            this.mDecorator.setTimelineMaxProgress(timelineMaxProgress);
        }
    }
    
    boolean isAdvisoryDisabled() {
        return this.mIsAdvisoryDisabled;
    }
    
    public void moveToPostPlay() {
        this.mController.getNetflixActivity().removeVisibleDialog();
        if (this.mController.getNetflixActivity().isDialogFragmentVisible()) {
            this.mController.getNetflixActivity().removeDialogFrag();
        }
        this.clearPanel();
        Log.d("screen", "POST_PLAY");
        this.mNavigationBarSetVisibleInProgress = true;
        this.showNavigationBar();
        this.mPostPlayManager.transitionToPostPlay();
    }
    
    public void moveToState(final PlayerUiState playerUiState) {
        while (true) {
            Label_0079: {
                synchronized (this) {
                    if (this.mController == null || !this.mController.isActivityValid()) {
                        Log.w("screen", "moveToState() mController is already in finishing state, do nothing");
                    }
                    else {
                        if (this.mState != playerUiState) {
                            break Label_0079;
                        }
                        if (Log.isLoggable()) {
                            Log.d("screen", "moveToState() Already in this state, do nothing: " + playerUiState);
                        }
                    }
                    return;
                }
            }
            final PlayerUiState playerUiState2;
            if ((this.mPendingState = playerUiState2) == PlayerUiState.Loading) {
                this.moveToLoading();
            }
            else if (playerUiState2 == PlayerUiState.Playing) {
                this.moveToLoaded();
            }
            else if (playerUiState2 == PlayerUiState.PlayingWithTrickPlayOverlay) {
                this.moveToLoadedTapped();
            }
            else if (playerUiState2 == PlayerUiState.PostPlay) {
                this.moveToPostPlay();
            }
            else if (playerUiState2 == PlayerUiState.Interrupter) {
                this.moveToInterrupted();
            }
            else {
                Log.e("screen", "Invalid state set, ignoring");
            }
            this.mState = playerUiState2;
            this.mPendingState = null;
            if (Log.isLoggable()) {
                Log.i("screen", "moveToState() finished moving to state: " + this.mState);
            }
        }
    }
    
    public void onAssetUpdated(final Asset asset) {
        if (this.mDecorator != null) {
            this.mDecorator.onAssetUpdated(asset);
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (this.mDecorator != null) {
            this.mDecorator.onConfigurationChanged(configuration);
        }
        if (this.mPostPlayManager != null) {
            this.mPostPlayManager.onConfigurationChanged(configuration);
        }
    }
    
    public void onLanguageUpdated(final Language language) {
        if (this.mDecorator != null) {
            this.mDecorator.onLanguageUpdated(language);
        }
    }
    
    void onPause() {
        if (this.mDecorator != null) {
            this.mDecorator.onPause();
        }
    }
    
    void onResume() {
        if (this.mDecorator != null) {
            this.mDecorator.onResume();
        }
    }
    
    void onStart() {
        if (this.mDecorator != null) {
            this.mDecorator.onStart();
        }
    }
    
    void onStop() {
        if (this.mDecorator != null) {
            this.mDecorator.onStop();
        }
    }
    
    void onTap(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("screen", "PlayScreen tap received. Event driven: " + b);
        }
        Label_0076: {
            if (b) {
                Log.d("screen", "Event is received. We are either not on ICS+ phone or this is tap to hide overlay.");
                this.mNavigationBarSetVisibleInProgress = false;
                break Label_0076;
            }
            Log.d("screen", "Hack to make player overlay visible on ICS+ devices. It is only called when event is null");
            if (!this.mNavigationBarSetVisibleInProgress) {
                Log.d("screen", "Navigation bar is now visible. Make player overlay visible.");
                this.mNavigationBarSetVisibleInProgress = true;
                break Label_0076;
            }
            Log.d("screen", "Navigation bar visibility was already triggered. Ignore.");
            return;
        }
        if (this.mState == PlayerUiState.Loading) {
            Log.d("screen", "Loading, noop");
            return;
        }
        if (this.mState == PlayerUiState.Playing) {
            Log.d("screen", "Move to PlayingWithTrickPlayOverlay");
            this.moveToState(PlayerUiState.PlayingWithTrickPlayOverlay);
            return;
        }
        if (this.mState == PlayerUiState.PlayingWithTrickPlayOverlay) {
            Log.d("screen", "Move to Playing");
            this.moveToState(PlayerUiState.Playing);
            return;
        }
        if (this.mState != PlayerUiState.PostPlay) {
            Log.e("screen", "This should not be possible, ignoring");
            return;
        }
        if (!this.mPostPlayManager.wasPostPlayDismissed()) {
            Log.d("screen", "Move to PlayingWithTrickPlayOverlay from post play");
            this.moveToState(PlayerUiState.Playing);
            this.mPostPlayManager.transitionFromPostPlay();
            this.hideNavigationBar();
            return;
        }
        Log.d("screen", "PostPlay was dismissed before, stay in it!");
        if (!this.getPostPlay().isAutoPlayEnabled()) {
            this.mPostPlayManager.transitionFromPostPlay();
        }
    }
    
    public void onVideoDetailsFetched(final VideoDetails videoDetails) {
        if (this.mDecorator != null) {
            this.mDecorator.onVideoDetailsFetched(videoDetails);
        }
    }
    
    public void playExtraHandlerAnimation(final int n, final Runnable runnable) {
        if (this.isCoppolaPortrait()) {
            this.mDecorator.playExtraHandlerAnimation(n, runnable);
            return;
        }
        this.mBottomPanel.playExtraHandlerAnimation(n, runnable);
    }
    
    protected void playerOverlayVisibility(final boolean toolbarVisibility) {
        boolean b;
        if (DeviceUtils.isTabletByContext((Context)this.getController()) && (AndroidUtils.getAndroidVersion() < 14 || AndroidUtils.getAndroidVersion() >= 16)) {
            b = true;
        }
        else {
            b = false;
        }
        if (!toolbarVisibility || (toolbarVisibility && this.mController.isInPortrait())) {
            if (b) {
                this.mController.getWindow().setFlags(1024, 1024);
            }
            if (this.mBottomPanel != null) {
                this.mBottomPanel.hide();
            }
            if (this.mTopPanel != null) {
                this.mTopPanel.hide();
            }
            if (this.mExpiringContent != null) {
                this.mExpiringContent.hideWarning();
            }
        }
        else {
            if (b) {
                this.mController.getWindow().clearFlags(1024);
            }
            if (this.mBottomPanel != null) {
                this.mBottomPanel.setZoomEnabled(this.isZoomEnabled());
                this.mBottomPanel.show();
            }
            if (this.mTopPanel != null) {
                this.mTopPanel.show();
            }
            if (this.mExpiringContent != null) {
                this.mExpiringContent.tryShowWarning();
            }
        }
        final SubtitleManager subtitleManager = this.mController.getSubtitleManager();
        if (subtitleManager != null && (!toolbarVisibility || !this.mController.isInPortrait())) {
            subtitleManager.onPlayerOverlayVisibiltyChange(toolbarVisibility);
        }
        if (this.mDecorator != null) {
            this.mDecorator.playerOverlayVisibility(toolbarVisibility);
        }
        if (this.mController.isInPortrait()) {
            this.setToolbarVisibility(toolbarVisibility);
        }
    }
    
    void removeSplashScreen() {
        if (this.mState == PlayerUiState.Loading) {
            Log.d("screen", "=========================>");
            if (this.mFlipper != null) {
                this.mFlipper.showNext();
            }
            this.moveToState(PlayerUiState.PlayingWithTrickPlayOverlay);
        }
        else if (this.mState == PlayerUiState.PostPlay && this.mController.isCoppolaPlayback()) {
            this.moveToState(PlayerUiState.Playing);
        }
    }
    
    void setBufferingOverlayVisibility(final boolean b) {
        final View mBufferingOverlay = this.mBufferingOverlay;
        if (mBufferingOverlay == null) {
            Log.w("screen", "bufferingOverlay is NULL!");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("screen", "Subtitles ARE visible");
        }
        if (b) {
            Log.d("screen", "Display buffering overlay");
            mBufferingOverlay.setVisibility(0);
            return;
        }
        Log.d("screen", "Remove buffering overlay");
        mBufferingOverlay.setVisibility(8);
    }
    
    void setDebugData(final String s) {
    }
    
    void setDebugDataVisibility(final boolean b) {
    }
    
    public void setDecorator(final PlayScreenDecorator mDecorator) {
        this.mDecorator = mDecorator;
    }
    
    public void setMediaImage(final boolean mediaImage) {
        if (this.mDecorator != null) {
            this.mDecorator.updatePlaybackStatus(mediaImage);
        }
        if (!Coppola1Utils.isCoppolaContext((Context)this.getController())) {
            this.mBottomPanel.setMediaImage(mediaImage);
        }
    }
    
    public void setPlayPauseVisibility(final int playPauseVisibility) {
        this.mBottomPanel.setPlayPauseVisibility(playPauseVisibility);
    }
    
    int setProgress(final int n, final int n2, final boolean b) {
        return this.setProgress(n, n2, b, true);
    }
    
    int setProgress(final int currentTimelineProgress, final int n, final boolean b, final boolean b2) {
        this.currentTimelineProgress = currentTimelineProgress;
        if (this.mDecorator != null) {
            this.mDecorator.setTimelineProgress(currentTimelineProgress, b);
        }
        this.mBottomPanel.setProgress(currentTimelineProgress, n, b, b2);
        return currentTimelineProgress;
    }
    
    public void setSeekbarTrackingEnabled(final boolean seekbarTrackingEnabled) {
        this.mBottomPanel.setSeekbarTrackingEnabled(seekbarTrackingEnabled);
    }
    
    void setTitle(final String title) {
        final TopPanel mTopPanel = this.mTopPanel;
        if (mTopPanel != null) {
            mTopPanel.setTitle(title);
        }
    }
    
    public void setTopPanelVisibility(final boolean b) {
        if (this.isCoppolaPortrait() || this.mTopPanel == null) {
            return;
        }
        if (!b) {
            this.mTopPanel.hide();
            return;
        }
        if (this.mState != PlayerUiState.PlayingWithTrickPlayOverlay) {
            Log.d("screen", "Player UI is NOT visible. Do not make top panel visible");
            return;
        }
        this.mTopPanel.show();
    }
    
    public void setZoom(final boolean b) {
        if (this.mSurface != null) {
            if (!b) {
                this.mSurface.setMode(0);
                return;
            }
            this.mSurface.setMode(1);
        }
    }
    
    public void setZoomEnabledByPlayertype(final boolean mZoomEnabled) {
        this.mZoomEnabled = mZoomEnabled;
    }
    
    public void setZoomImage(final boolean zoomImage) {
        if (!this.mController.isCoppolaPlayback()) {
            this.mBottomPanel.setZoomImage(zoomImage);
        }
    }
    
    public void setZoomVisibility(final int zoomVisibility) {
        this.mBottomPanel.setZoomVisibility(zoomVisibility);
    }
    
    protected boolean shouldPlaybackRelatedOptionBePossible() {
        return !this.mController.isStalled() && this.mState != PlayerUiState.Loading;
    }
    
    public void showBif(final ByteBuffer byteBuffer) {
        if (this.mController == null || !this.mController.isActivityValid()) {
            return;
        }
        this.mBottomPanel.getCurrentTime().setBifDownloaded(byteBuffer != null);
        if (byteBuffer == null || this.mBif == null) {
            Log.d("screen", "bif data is null");
            return;
        }
        if (this.mController.isTablet()) {
            final int dipToPixels = AndroidUtils.dipToPixels((Context)this.mController.getActivity(), 40);
            final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.mTabletBifsLayout.getLayoutParams();
            layoutParams.setMargins(this.mBottomPanel.getTimeXAndUpdateHandler(this.mTabletBifsLayout), 0, 0, dipToPixels);
            this.mTabletBifsLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        }
        final Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        if (decodeByteArray != null) {
            this.mBif.setImageBitmap(decodeByteArray);
            return;
        }
        Log.d("screen", "bitmap is null");
    }
    
    public void showBottomPanel() {
        if (!this.isCoppolaPortrait()) {
            this.mBottomPanel.show();
        }
    }
    
    void showContentAdvisory() {
        if (this.mContentAdvisory == null || this.isAdvisoryDisabled()) {
            return;
        }
        this.mHandler.removeCallbacks(this.contentAdvisoryNoticeCompletionRunnable);
        this.mHandler.postDelayed(this.contentAdvisoryStartNoticeRunnable, 500L);
    }
    
    void showNavigationBar() {
        Log.d("screen", "show nav noop");
    }
    
    void showSplashScreen() {
        if (this.mState != PlayerUiState.Loading) {
            Log.d("screen", "=========================> display spash screen");
            if (this.mFlipper != null) {
                this.mFlipper.showPrevious();
            }
        }
    }
    
    public void startBif(final ByteBuffer byteBuffer) {
        if (this.mController == null || !this.mController.isActivityValid()) {
            return;
        }
        if (this.mController.isTablet()) {
            this.mBifAnim = AnimationUtils.startViewAppearanceAnimation(this.mTabletBifsLayout, true);
        }
        else {
            AnimationUtils.startViewAppearanceAnimation((View)this.mBif, true);
        }
        this.showBif(byteBuffer);
    }
    
    void startCurrentTime(final ByteBuffer byteBuffer) {
        if (this.mController != null && this.mController.isActivityValid()) {
            final BottomPanel mBottomPanel = this.mBottomPanel;
            if (mBottomPanel != null && mBottomPanel.getCurrentTime() != null) {
                mBottomPanel.getCurrentTime().start(byteBuffer);
            }
        }
    }
    
    public void startDragging() {
        this.changeActionState(false, false);
        this.setTopPanelVisibility(false);
        if (this.mDecorator != null) {
            this.mDecorator.setDraggingState(true);
        }
        if (this.isCoppolaPortrait()) {
            this.setToolbarVisibility(false);
            return;
        }
        this.mBottomPanel.startDragging();
    }
    
    public void stopBif() {
        if (this.mController == null || !this.mController.isActivityValid()) {
            return;
        }
        if (!this.mController.isTablet()) {
            AnimationUtils.startViewAppearanceAnimation((View)this.mBif, false);
            return;
        }
        if (this.mBifAnim != null && this.mBifAnim.isRunning()) {
            this.mBifAnim.cancel();
            this.mTabletBifsLayout.setAlpha(0.0f);
            return;
        }
        AnimationUtils.startViewAppearanceAnimation(this.mTabletBifsLayout, false);
    }
    
    void stopCurrentTime(final boolean b) {
        if (this.mController != null && this.mController.isActivityValid()) {
            final BottomPanel mBottomPanel = this.mBottomPanel;
            if (mBottomPanel != null && mBottomPanel.getCurrentTime() != null) {
                mBottomPanel.getCurrentTime().stop(b);
            }
        }
    }
}
