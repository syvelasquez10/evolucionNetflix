// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManager;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.os.Build;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.view.SurfaceHolder;
import android.widget.ViewFlipper;
import android.widget.TextView;
import android.view.View;
import android.animation.Animator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PlayScreen implements Screen
{
    protected static final String TAG = "screen";
    protected PlayScreen$Listeners listeners;
    protected RelativeLayout mBackground;
    protected ImageView mBif;
    private Animator mBifAnim;
    protected BottomPanel mBottomPanel;
    protected View mBufferingOverlay;
    protected PlayerFragment mController;
    protected TextView mDebugData;
    protected ViewFlipper mFlipper;
    protected SurfaceHolder mHolder;
    protected boolean mNavigationBarSetVisibleInProgress;
    private PlayerUiState mPendingState;
    private String mPlaybackControlOverlayId;
    private PostPlay mPostPlayManager;
    private PlayerUiState mState;
    protected final TappableSurfaceView mSurface;
    protected View mTabletBifsLayout;
    protected TopPanel mTopPanel;
    private boolean mZoomEnabled;
    
    PlayScreen(final PlayerFragment mController, final PlayScreen$Listeners listeners, final PostPlayFactory$PostPlayType postPlayFactory$PostPlayType) {
        this.mState = PlayerUiState.Loading;
        this.mNavigationBarSetVisibleInProgress = false;
        this.mZoomEnabled = true;
        if (mController == null || listeners == null) {
            throw new IllegalArgumentException("Argument can not be null!");
        }
        this.mController = mController;
        this.listeners = listeners;
        this.mTopPanel = new TopPanel(mController, listeners);
        this.mBottomPanel = new BottomPanel(mController, listeners);
        final View view = mController.getView();
        this.mSurface = (TappableSurfaceView)view.findViewById(2131624399);
        if (this.mSurface != null) {
            this.mSurface.addTapListener(listeners.tapListener);
            this.mHolder = this.mSurface.getHolder();
            this.mSurface.setSurfaceMeasureListener(listeners.surfaceMeasureListener);
        }
        if (this.mHolder != null) {
            this.mHolder.addCallback(listeners.surfaceListener);
        }
        this.mFlipper = (ViewFlipper)view.findViewById(2131624194);
        this.mBackground = (RelativeLayout)view.findViewById(2131624193);
        this.mBufferingOverlay = view.findViewById(2131624420);
        int n;
        if (mController.getNetflixActivity().isTablet()) {
            n = 2131624417;
        }
        else {
            n = 2131624400;
        }
        this.mBif = (ImageView)view.findViewById(n);
        this.mTabletBifsLayout = view.findViewById(2131624416);
        this.mPostPlayManager = PostPlayFactory.create(mController, postPlayFactory$PostPlayType);
        this.moveToState(PlayerUiState.Loading);
    }
    
    static PlayScreen createInstance(final PlayerFragment playerFragment, final PlayScreen$Listeners playScreen$Listeners, final PostPlayFactory$PostPlayType postPlayFactory$PostPlayType) {
        final int androidVersion = AndroidUtils.getAndroidVersion();
        if (androidVersion >= 16) {
            Log.d("screen", "PlayScreen for JB (Android 4.1+");
            return new PlayScreenJB(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
        }
        if (Build.MANUFACTURER.equals("Amazon") && (Build.MODEL.equals("KFOT") || Build.MODEL.equals("KFTT") || Build.MODEL.equals("KFJWA") || Build.MODEL.equals("KFJWI"))) {
            Log.d("screen", "PlayScreen for Amazon Kindle HD");
            return new PlayScreenKindleHD(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
        }
        if (androidVersion >= 14) {
            Log.d("screen", "PlayScreen for ICS (Android 4+");
            return new PlayScreenICS(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
        }
        if (Build.MODEL.equals("Kindle Fire") && Build.MANUFACTURER.equals("Amazon")) {
            Log.d("screen", "PlayScreen for Amazon Kindle Fire");
            return new PlayScreenKindleFire(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
        }
        Log.d("screen", "PlayScreen for Froyo/Gingerbread (Android 2.2-2.3) - default");
        return new PlayScreen(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
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
        final int color = this.mController.getResources().getColor(2131558590);
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
        final int color = this.mController.getResources().getColor(2131558590);
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
    
    private void moveToPostPlay() {
        this.mController.getNetflixActivity().removeVisibleDialog();
        if (this.mController.getNetflixActivity().isDialogFragmentVisible()) {
            this.mController.getNetflixActivity().removeDialogFrag();
        }
        this.clearPanel();
        Log.d("screen", "POST_PLAY");
        this.mNavigationBarSetVisibleInProgress = true;
        this.mPostPlayManager.transitionToPostPlay();
        this.showNavigationBar();
    }
    
    static int resolveContentView(final PostPlayFactory$PostPlayType postPlayFactory$PostPlayType) {
        if (postPlayFactory$PostPlayType == PostPlayFactory$PostPlayType.EpisodesForPhone) {
            Log.d("screen", "playout_phone_episode");
            return 2130903190;
        }
        if (postPlayFactory$PostPlayType == PostPlayFactory$PostPlayType.EpisodesForTablet) {
            Log.d("screen", "playout_tablet_episode");
            return 2130903194;
        }
        if (postPlayFactory$PostPlayType == PostPlayFactory$PostPlayType.RecommendationForTablet) {
            Log.d("screen", "playout_tablet_movie");
            return 2130903195;
        }
        Log.d("screen", "playout_phone_movie");
        return 2130903191;
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
        }
    }
    
    public BottomPanel getBottomPanel() {
        return this.mBottomPanel;
    }
    
    @Override
    public Activity getController() {
        return this.mController.getNetflixActivity();
    }
    
    SurfaceHolder getHolder() {
        return this.mHolder;
    }
    
    MdxTargetSelection getMdxTargetSelector() {
        return this.mTopPanel.getMdxTargetSelector();
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
    
    public TopPanel getTopPanel() {
        return this.mTopPanel;
    }
    
    void hideNavigationBar() {
        Log.d("screen", "hide nav noop");
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
    
    void initProgress(final int n) {
        this.mBottomPanel.initProgress(n);
    }
    
    protected void moveToState(final PlayerUiState playerUiState) {
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
    
    protected void playerOverlayVisibility(final boolean b) {
        if (b) {
            if (AndroidUtils.getAndroidVersion() < 14 || AndroidUtils.getAndroidVersion() >= 16) {
                this.mController.getWindow().clearFlags(1024);
            }
            if (this.mBottomPanel != null) {
                this.mBottomPanel.setZoomEnabled(this.isZoomEnabled());
                this.mBottomPanel.show();
            }
            if (this.mTopPanel != null) {
                this.mTopPanel.show();
            }
        }
        else {
            this.mController.getWindow().setFlags(1024, 1024);
            if (this.mBottomPanel != null) {
                this.mBottomPanel.hide();
            }
            if (this.mTopPanel != null) {
                this.mTopPanel.hide();
            }
        }
        final SubtitleManager subtitleManager = this.mController.getSubtitleManager();
        if (subtitleManager != null) {
            subtitleManager.onPlayerOverlayVisibiltyChange(b);
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
    
    int setProgress(final int n, final int n2, final boolean b) {
        return this.setProgress(n, n2, b, true);
    }
    
    int setProgress(final int n, final int n2, final boolean b, final boolean b2) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null) {
            return mBottomPanel.setProgress(n, n2, b, b2);
        }
        return 0;
    }
    
    void setTitle(final String title) {
        final TopPanel mTopPanel = this.mTopPanel;
        if (mTopPanel != null) {
            mTopPanel.setTitle(title);
        }
    }
    
    public void setTopPanelVisibility(final boolean b) {
        if (this.mTopPanel != null) {
            if (!b) {
                this.mTopPanel.hide();
                return;
            }
            if (this.mState == PlayerUiState.PlayingWithTrickPlayOverlay) {
                this.mTopPanel.show();
                return;
            }
            Log.d("screen", "Player UI is NOT visible. Do not make top panel visible");
        }
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
            final int dipToPixels = AndroidUtils.dipToPixels((Context)this.getController(), 40);
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
