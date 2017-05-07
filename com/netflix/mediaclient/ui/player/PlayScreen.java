// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.SurfaceHolder$Callback;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.os.Build;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.view.SurfaceHolder;
import android.widget.ViewFlipper;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.ui.Screen;

public class PlayScreen implements Screen
{
    protected static final String TAG = "screen";
    protected Listeners listeners;
    protected RelativeLayout mBackground;
    protected ImageView mBif;
    protected BottomPanel mBottomPanel;
    protected View mBufferingOverlay;
    protected PlayerActivity mController;
    protected TextView mDebugData;
    protected ViewFlipper mFlipper;
    protected SurfaceHolder mHolder;
    protected boolean mNavigationBarSetVisibleInProgress;
    private PlayerUiState mPendingState;
    private String mPlaybackControlOverlayId;
    private PostPlay mPostPlayManager;
    private PlayerUiState mState;
    protected final TappableSurfaceView mSurface;
    protected TopPanel mTopPanel;
    private boolean mZoomEnabled;
    private final Runnable removeSoundBar;
    
    PlayScreen(final PlayerActivity mController, final Listeners listeners, final PostPlayFactory.PostPlayType postPlayType) {
        this.mState = PlayerUiState.Loading;
        this.mNavigationBarSetVisibleInProgress = false;
        this.mZoomEnabled = true;
        this.removeSoundBar = new Runnable() {
            @Override
            public void run() {
                if (PlayScreen.this.mState == PlayerUiState.Playing) {
                    Log.d("screen", "AUDIO:: sound bar hide");
                    final TopPanel mTopPanel = PlayScreen.this.mTopPanel;
                    if (mTopPanel != null) {
                        mTopPanel.hideSoundSection();
                    }
                    return;
                }
                Log.d("screen", "AUDIO:: not in loaded state anymore! Ignore.");
            }
        };
        if (mController == null || listeners == null) {
            throw new IllegalArgumentException("Argument can not be null!");
        }
        this.mController = mController;
        this.listeners = listeners;
        this.mTopPanel = new TopPanel(mController, listeners);
        this.mBottomPanel = new BottomPanel(mController, listeners);
        this.mSurface = (TappableSurfaceView)mController.findViewById(2131165534);
        if (this.mSurface != null) {
            this.mSurface.addTapListener(listeners.tapListener);
            this.mHolder = this.mSurface.getHolder();
            this.mSurface.setSurfaceMeasureListener(listeners.surfaceMeasureListener);
        }
        if (this.mHolder != null) {
            this.mHolder.addCallback(listeners.surfaceListener);
        }
        this.mFlipper = (ViewFlipper)mController.findViewById(2131165537);
        this.mBackground = (RelativeLayout)mController.findViewById(2131165462);
        this.mBufferingOverlay = mController.findViewById(2131165565);
        this.mBif = (ImageView)mController.findViewById(2131165535);
        this.mPostPlayManager = PostPlayFactory.create(mController, postPlayType);
        this.moveToState(PlayerUiState.Loading);
    }
    
    static PlayScreen createInstance(final PlayerActivity playerActivity, final Listeners listeners, final PostPlayFactory.PostPlayType postPlayType) {
        final int androidVersion = AndroidUtils.getAndroidVersion();
        if (androidVersion >= 16) {
            Log.d("screen", "PlayScreen for JB (Android 4.1+");
            return new PlayScreenJB(playerActivity, listeners, postPlayType);
        }
        if (Build.MANUFACTURER.equals("Amazon") && (Build.MODEL.equals("KFOT") || Build.MODEL.equals("KFTT") || Build.MODEL.equals("KFJWA") || Build.MODEL.equals("KFJWI"))) {
            Log.d("screen", "PlayScreen for Amazon Kindle HD");
            return new PlayScreenKindleHD(playerActivity, listeners, postPlayType);
        }
        if (androidVersion >= 14) {
            Log.d("screen", "PlayScreen for ICS (Android 4+");
            return new PlayScreenICS(playerActivity, listeners, postPlayType);
        }
        if (Build.MODEL.equals("Kindle Fire") && Build.MANUFACTURER.equals("Amazon")) {
            Log.d("screen", "PlayScreen for Amazon Kindle Fire");
            return new PlayScreenKindleFire(playerActivity, listeners, postPlayType);
        }
        Log.d("screen", "PlayScreen for Froyo/Gingerbread (Android 2.2-2.3) - default");
        return new PlayScreen(playerActivity, listeners, postPlayType);
    }
    
    private boolean isZoomEnabled() {
        if (Log.isLoggable("screen", 3)) {
            Log.d("screen", "mZoomEnabled = " + this.mZoomEnabled + ", surface.canVideoBeZoomed()=" + this.mSurface.canVideoBeZoomed());
        }
        return this.mZoomEnabled && this.mSurface.canVideoBeZoomed();
    }
    
    private void moveToInterrupted() {
        this.mController.removeVisibleDialog();
        if (this.mController.isDialogFragmentVisible()) {
            this.mController.removeDialogFrag();
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
        final int color = this.mController.getResources().getColor(2131296350);
        if (this.mBackground != null) {
            this.mBackground.setBackgroundColor(color);
        }
        if (this.mSurface != null) {
            this.mSurface.setBackgroundColor(color);
        }
        if (this.mPlaybackControlOverlayId != null) {
            if (!this.mController.getErrorManager().isErrorReported()) {
                this.mController.reportUiModelessViewSessionEnded(IClientLogging.ModalView.playbackControls, this.mPlaybackControlOverlayId);
            }
            this.mPlaybackControlOverlayId = null;
        }
        this.playerOverlayVisibility(false);
    }
    
    private void moveToLoadedTapped() {
        Log.d("screen", "STATE_LOADED_TAPPED");
        this.mBottomPanel.enableButtons(!this.mController.isStalled());
        final int color = this.mController.getResources().getColor(2131296350);
        if (this.mBackground != null) {
            this.mBackground.setBackgroundColor(color);
        }
        if (this.mSurface != null) {
            this.mSurface.setBackgroundColor(color);
        }
        if (!this.mController.getErrorManager().isErrorReported()) {
            this.mPlaybackControlOverlayId = this.mController.reportUiModelessViewSessionStart(IClientLogging.ModalView.playbackControls);
        }
        this.playerOverlayVisibility(true);
    }
    
    private void moveToLoading() {
        Log.d("screen", "STATE_LOADING, default");
    }
    
    private void moveToPostPlay() {
        this.mController.removeVisibleDialog();
        if (this.mController.isDialogFragmentVisible()) {
            this.mController.removeDialogFrag();
        }
        this.clearPanel();
        Log.d("screen", "POST_PLAY");
        this.mNavigationBarSetVisibleInProgress = true;
        this.mPostPlayManager.transitionToPostPlay();
        this.showNavigationBar();
    }
    
    static int resolveContentView(final PostPlayFactory.PostPlayType postPlayType) {
        if (postPlayType == PostPlayFactory.PostPlayType.EpisodesForPhone) {
            Log.d("screen", "playout_phone_episode");
            return 2130903149;
        }
        if (postPlayType == PostPlayFactory.PostPlayType.EpisodesForTablet) {
            Log.d("screen", "playout_tablet_episode");
            return 2130903154;
        }
        if (postPlayType == PostPlayFactory.PostPlayType.RecommendationForTablet) {
            Log.d("screen", "playout_tablet_movie");
            return 2130903155;
        }
        Log.d("screen", "playout_phone_movie");
        return 2130903150;
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
    
    public void changeActionState(final boolean b, final boolean b2) {
        if (this.mTopPanel != null) {
            this.mTopPanel.changeActionState(b);
        }
        if (this.mBottomPanel != null) {
            if (!b2) {
                this.mBottomPanel.changeActionState(b);
                return;
            }
            this.mBottomPanel.enableButtons(b);
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
        return this.mController;
    }
    
    public String getCurrentTitle() {
        if (this.mTopPanel == null) {
            return null;
        }
        return this.mTopPanel.getCurrentTitle();
    }
    
    SurfaceHolder getHolder() {
        return this.mHolder;
    }
    
    MdxTargetSelection getMdxTargetSelector() {
        return this.mBottomPanel.getMdxTargetSelector();
    }
    
    ImageView getMedia() {
        return (ImageView)this.mBottomPanel.getMedia();
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
    
    ImageView getZoom() {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null) {
            return (ImageView)mBottomPanel.getZoom();
        }
        return null;
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
            if (Log.isLoggable("screen", 3)) {
                Log.d("screen", "canIgnoreSurfaceDestroyed, state: " + this.mState + ", pending state: " + this.mPendingState);
            }
            return this.mState == PlayerUiState.PostPlay || this.mPendingState == PlayerUiState.PostPlay;
        }
    }
    
    void initAudioProgress(final int n) {
        if (Log.isLoggable("screen", 3)) {
            Log.d("screen", "InitAudioProgress: pos " + n);
        }
        this.mTopPanel.initAudioProgress(n);
    }
    
    void initProgress(final int n) {
        this.mBottomPanel.initProgress(n);
    }
    
    void moveCurrentTimeWithTimeline(final boolean b, final boolean b2) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null && mBottomPanel.getCurrentTime() != null) {
            mBottomPanel.getCurrentTime().move(b, b2);
        }
    }
    
    protected void moveToState(final PlayerUiState playerUiState) {
        while (true) {
            while (true) {
                Label_0073: {
                    synchronized (this) {
                        if (this.mState == playerUiState) {
                            Log.d("screen", "Already in this state, do nothing: " + playerUiState);
                        }
                        else {
                            if ((this.mPendingState = playerUiState) != PlayerUiState.Loading) {
                                break Label_0073;
                            }
                            this.moveToLoading();
                            this.mState = playerUiState;
                            this.mPendingState = null;
                        }
                        return;
                    }
                }
                if (playerUiState == PlayerUiState.Playing) {
                    this.moveToLoaded();
                    continue;
                }
                if (playerUiState == PlayerUiState.PlayingWithTrickPlayOverlay) {
                    this.moveToLoadedTapped();
                    continue;
                }
                if (playerUiState == PlayerUiState.PostPlay) {
                    this.moveToPostPlay();
                    continue;
                }
                if (playerUiState == PlayerUiState.Interrupter) {
                    this.moveToInterrupted();
                    continue;
                }
                Log.e("screen", "Invalid state set, ignoring");
                continue;
            }
        }
    }
    
    void onTap(final boolean b) {
        if (Log.isLoggable("screen", 3)) {
            Log.d("screen", "PlayScreen tap received. Event driven: " + b);
        }
        if (!b) {
            Log.d("screen", "Hack to make player overlay visible on ICS+ devices. It is only called when event is null");
            if (this.mNavigationBarSetVisibleInProgress) {
                Log.d("screen", "Navigation bar visibility was already triggered. Ignore.");
                return;
            }
            Log.d("screen", "Navigation bar is now visible. Make player overlay visible.");
            this.mNavigationBarSetVisibleInProgress = true;
        }
        else {
            Log.d("screen", "Event is received. We are either not on ICS+ phone or this is tap to hide overlay.");
            this.mNavigationBarSetVisibleInProgress = false;
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
        if (this.mPostPlayManager.wasPostPlayDismissed()) {
            Log.d("screen", "PostPlay was dismissed before, stay in it!");
            return;
        }
        Log.d("screen", "Move to PlayingWithTrickPlayOverlay from post play");
        this.moveToState(PlayerUiState.Playing);
        this.mPostPlayManager.transitionFromPostPlay();
        this.hideNavigationBar();
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
    
    public void resetToLoadingState() {
        this.moveToState(PlayerUiState.Loading);
        this.showSplashScreen();
    }
    
    int setAudioProgress(final int audioProgress) {
        if (Log.isLoggable("screen", 3)) {
            Log.d("screen", "SetAudioProgress: pos " + audioProgress);
        }
        return this.mTopPanel.setAudioProgress(audioProgress);
    }
    
    void setBufferingOverlayVisibility(final boolean b) {
        final View mBufferingOverlay = this.mBufferingOverlay;
        if (mBufferingOverlay == null) {
            Log.w("screen", "bufferingOverlay is NULL!");
        }
        else {
            if (Log.isLoggable("screen", 3)) {
                Log.d("screen", "Subtitles ARE visible");
            }
            if (b) {
                Log.d("screen", "Display buffering overlay");
                mBufferingOverlay.setVisibility(0);
                if (false) {
                    Log.d("screen", "Remove subtitles");
                    this.mController.getSubtitleManager().setSubtitleVisibility(false);
                }
            }
            else {
                Log.d("screen", "Remove buffering overlay");
                mBufferingOverlay.setVisibility(8);
                if (false) {
                    Log.d("screen", "Add subtitles");
                    this.mController.getSubtitleManager().setSubtitleVisibility(true);
                }
            }
        }
    }
    
    void setDebugData(final String s) {
    }
    
    void setDebugDataVisibility(final boolean b) {
    }
    
    public void setLanguage(final Language language) {
        final TopPanel mTopPanel = this.mTopPanel;
        if (mTopPanel != null) {
            mTopPanel.setLanguage(language);
        }
    }
    
    void setLastTimeState(final boolean lastTimeState) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null && mBottomPanel.getLastTime() != null) {
            mBottomPanel.getLastTime().setLastTimeState(lastTimeState);
        }
    }
    
    int setProgress(final int n, final int n2, final boolean b) {
        return this.setProgress(n, n2, b, false);
    }
    
    int setProgress(final int n, final int n2, final boolean b, final boolean b2) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null) {
            return mBottomPanel.setProgress(n, n2, b, b2);
        }
        return 0;
    }
    
    void setSoundBarVisibility(final boolean b) {
        if (this.mState == PlayerUiState.Playing) {
            Log.d("screen", "AUDIO:: sound bar is NOT visible");
            if (b) {
                Log.d("screen", "AUDIO:: sound bar make it visible");
                this.mTopPanel.showSoundSection();
                this.mSurface.removeCallbacks(this.removeSoundBar);
                return;
            }
            Log.d("screen", "AUDIO:: sound bar hide with 0.5 sec delay");
            this.mSurface.postDelayed(this.removeSoundBar, 500L);
        }
        else {
            if (this.mState == PlayerUiState.PlayingWithTrickPlayOverlay) {
                Log.d("screen", "AUDIO:: state loaded tapped. Sound bar is already visible");
                return;
            }
            Log.d("screen", "AUDIO:: SPLASH screen, ignore all");
        }
    }
    
    void setTitles(final String s, final String s2) {
        final TopPanel mTopPanel = this.mTopPanel;
        if (mTopPanel != null) {
            mTopPanel.setTitles(s, s2);
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
        this.mBottomPanel.getCurrentTime().setBifDownloaded(byteBuffer != null);
        if (byteBuffer == null || this.mBif == null) {
            Log.d("screen", "bif data is null");
            return;
        }
        if (this.mBif.getVisibility() != 0) {
            this.mBif.setVisibility(0);
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
    
    void snapToPosition(final int n, final int n2) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null) {
            mBottomPanel.snapToPosition(n, n2);
        }
    }
    
    public void startBif(final ByteBuffer byteBuffer) {
        this.showBif(byteBuffer);
    }
    
    void startCurrentTime(final ByteBuffer byteBuffer) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null && mBottomPanel.getCurrentTime() != null) {
            mBottomPanel.getCurrentTime().start(byteBuffer);
        }
    }
    
    public void stopBif() {
        if (this.mBif != null) {
            this.mBif.setVisibility(8);
        }
    }
    
    void stopCurrentTime(final boolean b) {
        final BottomPanel mBottomPanel = this.mBottomPanel;
        if (mBottomPanel != null && mBottomPanel.getCurrentTime() != null) {
            mBottomPanel.getCurrentTime().stop(b);
        }
    }
    
    public static class Listeners
    {
        public SeekBar$OnSeekBarChangeListener audioPositionListener;
        public View$OnClickListener episodeSelectorListener;
        public View$OnClickListener playPauseListener;
        public View$OnClickListener skipBackListener;
        public SurfaceHolder$Callback surfaceListener;
        public TappableSurfaceView.SurfaceMeasureListener surfaceMeasureListener;
        public TappableSurfaceView.TapListener tapListener;
        public SeekBar$OnSeekBarChangeListener videoPositionListener;
        public View$OnClickListener zoomListener;
    }
}
