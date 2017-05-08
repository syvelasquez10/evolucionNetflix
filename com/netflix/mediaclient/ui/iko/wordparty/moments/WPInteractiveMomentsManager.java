// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;
import java.util.Comparator;
import java.util.Collections;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.netflix.mediaclient.ui.iko.wordparty.WPConstants;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPImage;
import android.animation.Animator;
import java.util.Iterator;
import org.json.JSONException;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import org.json.JSONObject;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.IkoLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.animation.TimeInterpolator;
import android.animation.Animator$AnimatorListener;
import android.animation.PropertyValuesHolder;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View$OnClickListener;
import android.view.ViewPropertyAnimator;
import android.app.Activity;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.iko.SoundPoolManager;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.graphics.Bitmap;
import com.netflix.mediaclient.ui.player.PlayPauseListener;
import android.view.View$OnTouchListener;
import android.widget.ProgressBar;
import android.view.animation.Interpolator;
import java.util.List;
import android.widget.RelativeLayout;
import android.os.Handler;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPMoment;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import android.view.View;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager;

public class WPInteractiveMomentsManager extends BaseInteractiveMomentsManager
{
    private static final String TAG = "WPInteractiveMomentsManager";
    private boolean activeExit;
    private View bottomPanel;
    private int bottomPanelHeight;
    private boolean bottomPanelVisible;
    private boolean cachingResourcesComplete;
    private IconFontTextView closeButton;
    private WPInteractiveMomentsModel$WPMoment currentInteractiveMoment;
    private WPInteractiveMomentsModel data;
    private int deviceWidth;
    private Handler handler;
    private boolean hasInteractiveMoments;
    private RelativeLayout ikoContainer;
    private List<WPInteractiveMomentsModel$WPMoment> interactiveMoments;
    private Interpolator interpolator;
    private boolean isPugAutoOptIn;
    private boolean isSeeking;
    private boolean isShowingInteractiveMoment;
    private int itemSelectSoundId;
    private int itemWiggleSoundId;
    private ProgressBar loadingProgressBar;
    private boolean momentBackgrounded;
    private int momentEntryProgress;
    private WPMomentScreen momentScreen;
    private boolean momentStarted;
    private int momentThemeColor;
    private int momentTransitionInSoundId;
    private int momentTransitionOutSoundId;
    private List<Integer> momentsDisplayTimeList;
    private View$OnTouchListener onTapHijackListener;
    private int panelShuffleSoundId;
    private int pausePosition;
    PlayPauseListener playPauseListener;
    private boolean playbackPaused;
    private boolean playerBackgrounded;
    private ProgressBar progressBar;
    private Bitmap pugBitmap;
    private FrameLayout pugContainer;
    private ImageView pugImageView;
    private int pugNotificationSoundId;
    private AnimatorSet pugProgressAnimatorSet;
    private float pugRevealScale;
    private View pugRevealView;
    private ObjectAnimator pugScaleAnimator;
    private boolean pugSelected;
    private Bitmap pugSelectedBitmap;
    private int pugSelectedSoundId;
    private Interpolator quitOutInterpolator;
    private ObjectAnimator revealAnimator;
    private SoundPoolManager soundPoolManager;
    private int tutorialBoingSoundId;
    private int victorySoundId;
    private RelativeLayout wpContainer;
    
    public WPInteractiveMomentsManager() {
        this.momentsDisplayTimeList = new ArrayList<Integer>();
        this.handler = new Handler();
        this.isPugAutoOptIn = true;
        this.playPauseListener = new WPInteractiveMomentsManager$1(this);
        this.onTapHijackListener = (View$OnTouchListener)new WPInteractiveMomentsManager$12(this);
    }
    
    private void adjustPugLayout(final boolean b) {
        if (this.isShowingInteractiveMoment) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "adjustPugLayout: bottomPanelVisisble - " + b);
            }
            final ViewPropertyAnimator animate = this.pugContainer.animate();
            if (b) {
                if (this.pugContainer.getTranslationY() != -this.bottomPanelHeight) {
                    if (!ViewUtils.isNavigationBarBelowContent(this.fragment.getNetflixActivity())) {
                        animate.translationX((float)(-ViewUtils.getNavigationBarHeight(this.getContext(), false)));
                    }
                    animate.translationY((float)(-this.bottomPanelHeight)).start();
                }
            }
            else if (this.pugContainer.getTranslationY() != 0.0f) {
                animate.translationY(0.0f).translationX(0.0f).start();
            }
        }
    }
    
    private void animateReveal(boolean b) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "animateReveal: reveal = " + b);
        }
        if (this.isActivityInvalid()) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "Fragment is either null or activity is invalid ");
            }
            return;
        }
        if (!this.isPugAutoOptIn) {
            if (b && this.pugSelected) {
                b = true;
            }
            else {
                b = false;
            }
        }
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "animateReveal: autoOptIn = " + this.isPugAutoOptIn + " PugSelected = " + this.pugSelected);
        }
        this.playPauseVideo(b);
        ViewUtils.setVisibleOrGone((View)this.closeButton, b);
        if (b) {
            ViewUtils.setVisibleOrGone((View)this.progressBar, false);
            ViewUtils.setVisibleOrGone((View)this.pugImageView, false);
            this.wpContainer.setOnTouchListener(this.onTapHijackListener);
        }
        else {
            ViewUtils.setVisibleOrGone(this.pugRevealView, true);
            this.momentScreen.hideScreen();
            this.wpContainer.setOnTouchListener((View$OnTouchListener)null);
            if (this.pugProgressAnimatorSet != null) {
                this.pugProgressAnimatorSet.removeAllListeners();
                this.pugProgressAnimatorSet.cancel();
            }
            if (this.momentStarted) {
                this.playSoundPoolId(this.momentTransitionOutSoundId, 1.0f);
            }
        }
        this.startRevealScaleAnimation(b);
    }
    
    private void cleanUpResources() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "cleanUpResources: release bitmaps and audio resources");
        }
        this.releaseBitmaps(this.pugBitmap, this.pugSelectedBitmap);
        this.releaseResources();
        this.releaseSoundPool();
    }
    
    private void clearPanel(final int n) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "clearPanel: delay - " + n);
        }
        if (this.isActivityInvalid()) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "Fragment is either null or activity is invalid ");
            }
            return;
        }
        this.handler.postDelayed((Runnable)new WPInteractiveMomentsManager$8(this), (long)n);
    }
    
    private void findAndConfigureViews(final View view) {
        if (view == null) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "findAndConfigureViews: playerview is null");
            }
            return;
        }
        this.bottomPanel = view.findViewById(2131690089);
        this.loadingProgressBar = (ProgressBar)view.findViewById(2131690345);
        this.pugContainer = (FrameLayout)view.findViewById(2131690326);
        this.wpContainer = (RelativeLayout)view.findViewById(2131690325);
        this.pugImageView = (ImageView)view.findViewById(2131690329);
        this.pugRevealView = view.findViewById(2131690327);
        this.progressBar = (ProgressBar)view.findViewById(2131690328);
        (this.closeButton = (IconFontTextView)view.findViewById(2131690054)).setOnClickListener((View$OnClickListener)new WPInteractiveMomentsManager$10(this));
        this.progressBar.setOnClickListener((View$OnClickListener)new WPInteractiveMomentsManager$11(this));
        this.wpContainer.setOnTouchListener((View$OnTouchListener)null);
        this.momentScreen = new WPMomentScreen(this);
    }
    
    private void handlePlaybackPaused(final boolean playbackPaused, final int pausePosition) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "handlePlaybackPaused: pause = " + playbackPaused + " position = " + pausePosition);
        }
        this.playbackPaused = playbackPaused;
        if (this.isShowingInteractiveMoment && this.pugProgressAnimatorSet != null) {
            if (playbackPaused) {
                this.pausePosition = pausePosition;
                if (this.pugProgressAnimatorSet.isRunning()) {
                    this.pugProgressAnimatorSet.pause();
                }
            }
            else {
                if (pausePosition < this.pausePosition - 500 || pausePosition > this.pausePosition + 500) {
                    this.hide();
                }
                if (this.pugProgressAnimatorSet.isPaused()) {
                    this.pugProgressAnimatorSet.resume();
                }
            }
        }
    }
    
    private void handlePugClicked() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "handlePugClicked: true");
        }
        this.pugSelected = true;
        this.reportCommandEvent(UIViewLogging$UIViewCommandName.select);
        this.playSoundPoolId(this.pugSelectedSoundId, 1.0f);
        if (this.pugSelectedBitmap != null) {
            this.pugImageView.setImageBitmap(this.pugSelectedBitmap);
        }
        else {
            this.pugImageView.setImageResource(2130837783);
        }
        this.pugPulsateAnimation(false);
    }
    
    private void hideNavBar() {
        if (this.isActivityInvalid()) {
            return;
        }
        this.fragment.startScreenUpdateTask();
        this.fragment.clearPanel();
        this.fragment.hideNavigationBar();
    }
    
    private void hidePug() {
        ViewUtils.setVisibleOrGone(this.pugRevealView, true);
        if (this.pugProgressAnimatorSet != null) {
            this.pugProgressAnimatorSet.removeAllListeners();
            this.pugProgressAnimatorSet.cancel();
        }
        this.pugPulsateAnimation(false);
        AnimationUtils.startViewAppearanceAnimation((View)this.ikoContainer, false);
        AnimationUtils.startViewAppearanceAnimation((View)this.progressBar, true);
        AnimationUtils.startViewAppearanceAnimation((View)this.pugImageView, true);
        this.isShowingInteractiveMoment = false;
        this.pugSelected = false;
        this.currentInteractiveMoment = null;
    }
    
    private boolean isBottomPanelVisible() {
        boolean b = false;
        if (this.bottomPanel != null) {
            b = b;
            if (this.bottomPanel.getVisibility() == 0) {
                b = true;
            }
        }
        return b;
    }
    
    private void loadMomentScreenResources() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "loadMomentScreenResources: is caching resources complete = " + this.cachingResourcesComplete);
        }
        if (!this.cachingResourcesComplete) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "loadMomentScreenResources: resources not in cache yet. Ignore loading resources.");
            }
            return;
        }
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "loadMomentScreenResources: start background thread to load resources");
        }
        AsyncTaskCompat.execute(new WPInteractiveMomentsManager$9(this));
    }
    
    private void loadPugResourcesBg() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "loadPugResourcesBg: request to load pug resources in background thread");
        }
        AsyncTaskCompat.execute(new WPInteractiveMomentsManager$3(this));
    }
    
    private void loadSoundPoolResources() {
        ThreadUtils.assertNotOnMain();
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "loadSoundPoolResources: loading sound resources for moment");
        }
        if (this.data == null) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "loadSoundPoolResources: data is null");
            }
            return;
        }
        this.itemWiggleSoundId = this.loadSoundPoolVo(this.data.getItemWiggleAudio());
        this.pugNotificationSoundId = this.loadSoundPoolVo(this.data.getPugNotificationAudio());
        this.momentTransitionInSoundId = this.loadSoundPoolVo(this.data.getMomentTransitionInAudio());
        this.momentTransitionOutSoundId = this.loadSoundPoolVo(this.data.getMomentTransitionOutAudio());
        this.victorySoundId = this.loadSoundPoolVo(this.data.getVictoryAudio());
        this.pugSelectedSoundId = this.loadSoundPoolVo(this.data.getPugSelectedAudio());
        this.itemSelectSoundId = this.loadSoundPoolVo(this.data.getItemSelectAudio());
        this.tutorialBoingSoundId = this.loadSoundPoolVo(this.data.getTutorialBoingAudio());
        this.panelShuffleSoundId = this.loadSoundPoolVo(this.data.getPanelShuffleAudio());
    }
    
    private void loadSoundPoolResourcesBg() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "loadSoundPoolResourcesBg: start loading in background thread");
        }
        AsyncTaskCompat.execute(new WPInteractiveMomentsManager$4(this));
    }
    
    private int loadSoundPoolVo(final WPInteractiveMomentsModel$WPAudio wpInteractiveMomentsModel$WPAudio) {
        ThreadUtils.assertNotOnMain();
        if (this.soundPoolManager == null || wpInteractiveMomentsModel$WPAudio == null || StringUtils.isEmpty(wpInteractiveMomentsModel$WPAudio.getUrl())) {
            Log.d("WPInteractiveMomentsManager", "loadSoundPoolVo() failed. SoundPoolManager is null.");
            return -1;
        }
        if (wpInteractiveMomentsModel$WPAudio == null || StringUtils.isEmpty(wpInteractiveMomentsModel$WPAudio.getUrl())) {
            Log.d("WPInteractiveMomentsManager", "loadSoundPoolVo() failed. WPAudio or it;s url is null.");
            return -1;
        }
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "loadSoundPoolVo: url - " + wpInteractiveMomentsModel$WPAudio.getUrl());
        }
        return this.soundPoolManager.loadSoundPoolVo(this.getLocalCachedFileMetadata(wpInteractiveMomentsModel$WPAudio.getUrl()));
    }
    
    private void playPauseVideo(final boolean pauseOnPlayerBackgrounded) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "playPauseVideo: pause - " + pauseOnPlayerBackgrounded + " bottomPanelVisible = " + this.bottomPanelVisible);
        }
        if (this.isActivityInvalid()) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "Fragment is either null or activity is invalid ");
            }
            return;
        }
        this.fragment.setPauseOnPlayerBackgrounded(pauseOnPlayerBackgrounded);
        this.fragment.setSubtitleVisiblity(!pauseOnPlayerBackgrounded);
        if (pauseOnPlayerBackgrounded) {
            this.fragment.doPause();
            if (this.fragment.getPlayer() != null) {
                this.momentEntryProgress = this.fragment.getPlayer().getCurrentProgress();
            }
            else {
                this.momentEntryProgress = this.currentInteractiveMoment.getPugEndTimeMS();
            }
            this.showNavBar();
            return;
        }
        if (this.momentStarted) {
            if (this.activeExit && this.momentScreen.isLearnMoment()) {
                this.momentEntryProgress = this.currentInteractiveMoment.getVideoReturnOffsetMS();
                this.fragment.doSeek(this.momentEntryProgress);
            }
            else if (this.playerBackgrounded) {
                this.fragment.doSeek(this.momentEntryProgress);
                this.playerBackgrounded = false;
            }
            else {
                this.fragment.doUnpause();
            }
        }
        this.hideNavBar();
        this.activeExit = false;
    }
    
    private void playPugIntro(final int n) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "playPugIntro: duration - " + n);
        }
        this.pugIntroStartValues();
        AnimationUtils.startViewAppearanceAnimation((View)this.ikoContainer, true);
        final ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder((Object)this.pugContainer, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(View.SCALE_X, new float[] { 0.0f, 1.0f }), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[] { 0.0f, 1.0f }) });
        ofPropertyValuesHolder.setDuration(1000L);
        ofPropertyValuesHolder.addListener((Animator$AnimatorListener)new WPInteractiveMomentsManager$5(this, n));
        ofPropertyValuesHolder.setInterpolator((TimeInterpolator)this.quitOutInterpolator);
        ofPropertyValuesHolder.start();
        this.playSoundPoolId(this.pugNotificationSoundId, 1.0f);
    }
    
    private void playSoundPoolId(final int n, final float n2) {
        if (this.soundPoolManager != null) {
            this.soundPoolManager.playSoundPoolId(n, n2);
        }
    }
    
    private boolean preparePugView(final WPInteractiveMomentsModel$WPMoment p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       15
        //     4: ldc             "WPInteractiveMomentsManager"
        //     6: ldc_w           "WPMoment object is null. Ignoring request to preparePugView()"
        //     9: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    12: pop            
        //    13: iconst_0       
        //    14: ireturn        
        //    15: aload_0        
        //    16: getfield        com/netflix/mediaclient/ui/iko/wordparty/moments/WPInteractiveMomentsManager.progressBar:Landroid/widget/ProgressBar;
        //    19: iconst_1       
        //    20: invokestatic    com/netflix/mediaclient/util/ViewUtils.setVisibleOrGone:(Landroid/view/View;Z)V
        //    23: aload_0        
        //    24: getfield        com/netflix/mediaclient/ui/iko/wordparty/moments/WPInteractiveMomentsManager.pugImageView:Landroid/widget/ImageView;
        //    27: iconst_1       
        //    28: invokestatic    com/netflix/mediaclient/util/ViewUtils.setVisibleOrGone:(Landroid/view/View;Z)V
        //    31: aload_1        
        //    32: invokevirtual   com/netflix/mediaclient/ui/iko/wordparty/model/WPInteractiveMomentsModel$WPMoment.getBackgroundColor:()Lcom/netflix/mediaclient/ui/iko/wordparty/model/WPInteractiveMomentsModel$WPColor;
        //    35: ifnull          182
        //    38: aload_1        
        //    39: invokevirtual   com/netflix/mediaclient/ui/iko/wordparty/model/WPInteractiveMomentsModel$WPMoment.getBackgroundColor:()Lcom/netflix/mediaclient/ui/iko/wordparty/model/WPInteractiveMomentsModel$WPColor;
        //    42: invokevirtual   com/netflix/mediaclient/ui/iko/wordparty/model/WPInteractiveMomentsModel$WPColor.getColorValue:()Ljava/lang/String;
        //    45: astore          4
        //    47: aload           4
        //    49: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //    52: ifeq            92
        //    55: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    58: ifeq            13
        //    61: ldc             "WPInteractiveMomentsManager"
        //    63: new             Ljava/lang/StringBuilder;
        //    66: dup            
        //    67: invokespecial   java/lang/StringBuilder.<init>:()V
        //    70: ldc_w           "Empty or null bgColorValue for the given moment - "
        //    73: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    76: aload_1        
        //    77: invokevirtual   com/netflix/mediaclient/ui/iko/wordparty/model/WPInteractiveMomentsModel$WPMoment.getSceneType:()Ljava/lang/String;
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    83: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    86: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    89: pop            
        //    90: iconst_0       
        //    91: ireturn        
        //    92: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    95: ifeq            125
        //    98: ldc             "WPInteractiveMomentsManager"
        //   100: new             Ljava/lang/StringBuilder;
        //   103: dup            
        //   104: invokespecial   java/lang/StringBuilder.<init>:()V
        //   107: ldc_w           "BgColor value is "
        //   110: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   113: aload           4
        //   115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   121: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   124: pop            
        //   125: aload           4
        //   127: invokestatic    android/graphics/Color.parseColor:(Ljava/lang/String;)I
        //   130: istore_2       
        //   131: aload_0        
        //   132: iload_2        
        //   133: putfield        com/netflix/mediaclient/ui/iko/wordparty/moments/WPInteractiveMomentsManager.momentThemeColor:I
        //   136: iload_2        
        //   137: istore_3       
        //   138: aload_0        
        //   139: getfield        com/netflix/mediaclient/ui/iko/wordparty/moments/WPInteractiveMomentsManager.progressBar:Landroid/widget/ProgressBar;
        //   142: invokevirtual   android/widget/ProgressBar.getProgressDrawable:()Landroid/graphics/drawable/Drawable;
        //   145: ldc_w           -1275068417
        //   148: getstatic       android/graphics/PorterDuff$Mode.SRC_IN:Landroid/graphics/PorterDuff$Mode;
        //   151: invokevirtual   android/graphics/drawable/Drawable.setColorFilter:(ILandroid/graphics/PorterDuff$Mode;)V
        //   154: aload_0        
        //   155: getfield        com/netflix/mediaclient/ui/iko/wordparty/moments/WPInteractiveMomentsManager.progressBar:Landroid/widget/ProgressBar;
        //   158: invokevirtual   android/widget/ProgressBar.getBackground:()Landroid/graphics/drawable/Drawable;
        //   161: iload_3        
        //   162: getstatic       android/graphics/PorterDuff$Mode.SRC_IN:Landroid/graphics/PorterDuff$Mode;
        //   165: invokevirtual   android/graphics/drawable/Drawable.setColorFilter:(ILandroid/graphics/PorterDuff$Mode;)V
        //   168: aload_0        
        //   169: getfield        com/netflix/mediaclient/ui/iko/wordparty/moments/WPInteractiveMomentsManager.pugRevealView:Landroid/view/View;
        //   172: invokevirtual   android/view/View.getBackground:()Landroid/graphics/drawable/Drawable;
        //   175: iload_3        
        //   176: getstatic       android/graphics/PorterDuff$Mode.SRC_IN:Landroid/graphics/PorterDuff$Mode;
        //   179: invokevirtual   android/graphics/drawable/Drawable.setColorFilter:(ILandroid/graphics/PorterDuff$Mode;)V
        //   182: aload_0        
        //   183: getfield        com/netflix/mediaclient/ui/iko/wordparty/moments/WPInteractiveMomentsManager.pugBitmap:Landroid/graphics/Bitmap;
        //   186: ifnonnull       263
        //   189: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   192: ifeq            204
        //   195: ldc             "WPInteractiveMomentsManager"
        //   197: ldc_w           "preparePugView: pugBitmap is null"
        //   200: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   203: pop            
        //   204: aload_0        
        //   205: getfield        com/netflix/mediaclient/ui/iko/wordparty/moments/WPInteractiveMomentsManager.pugImageView:Landroid/widget/ImageView;
        //   208: ldc_w           2130837782
        //   211: invokevirtual   android/widget/ImageView.setImageResource:(I)V
        //   214: iconst_1       
        //   215: ireturn        
        //   216: astore_1       
        //   217: ldc_w           -16711936
        //   220: istore_2       
        //   221: iload_2        
        //   222: istore_3       
        //   223: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   226: ifeq            138
        //   229: ldc             "WPInteractiveMomentsManager"
        //   231: new             Ljava/lang/StringBuilder;
        //   234: dup            
        //   235: invokespecial   java/lang/StringBuilder.<init>:()V
        //   238: ldc_w           "Unable to parse color - "
        //   241: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: aload_1        
        //   245: invokevirtual   java/lang/IllegalArgumentException.getMessage:()Ljava/lang/String;
        //   248: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   251: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   254: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   257: pop            
        //   258: iload_2        
        //   259: istore_3       
        //   260: goto            138
        //   263: aload_0        
        //   264: getfield        com/netflix/mediaclient/ui/iko/wordparty/moments/WPInteractiveMomentsManager.pugImageView:Landroid/widget/ImageView;
        //   267: aload_0        
        //   268: getfield        com/netflix/mediaclient/ui/iko/wordparty/moments/WPInteractiveMomentsManager.pugBitmap:Landroid/graphics/Bitmap;
        //   271: invokevirtual   android/widget/ImageView.setImageBitmap:(Landroid/graphics/Bitmap;)V
        //   274: goto            214
        //   277: astore_1       
        //   278: goto            221
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  92     125    216    221    Ljava/lang/IllegalArgumentException;
        //  125    131    216    221    Ljava/lang/IllegalArgumentException;
        //  131    136    277    281    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0138:
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
    
    private void pugIntroStartValues() {
        this.progressBar.setProgress(0);
        this.pugRevealView.setScaleX(1.0f);
        this.pugRevealView.setScaleY(1.0f);
        ViewUtils.setVisibleOrGone((View)this.pugContainer, true);
        ViewUtils.setVisibleOrGone((View)this.progressBar, true);
    }
    
    private void pugPulsateAnimation(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "pugPulsateAnimation: start - " + b);
        }
        if (!b) {
            this.progressBar.setScaleX(1.0f);
            this.progressBar.setScaleY(1.0f);
            if (this.pugScaleAnimator != null) {
                this.pugScaleAnimator.cancel();
            }
            return;
        }
        final PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[] { 1.0f, 1.1f });
        final PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[] { 1.0f, 1.1f });
        if (this.pugScaleAnimator == null) {
            (this.pugScaleAnimator = ObjectAnimator.ofPropertyValuesHolder((Object)this.progressBar, new PropertyValuesHolder[] { ofFloat, ofFloat2 })).setRepeatMode(2);
            this.pugScaleAnimator.setRepeatCount(-1);
            this.pugScaleAnimator.setDuration(1332L);
        }
        else {
            this.pugScaleAnimator.cancel();
        }
        this.pugScaleAnimator.setInterpolator((TimeInterpolator)this.quitOutInterpolator);
        this.pugScaleAnimator.start();
    }
    
    private void reportMomentStarted() {
        final Context context = this.getContext();
        if (context != null) {
            IkoLogUtils.reportIkoMomentStarted(context, null, IClientLogging$ModalView.ikoMoment);
        }
    }
    
    private void reportPugImpressionStarted(final int n) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("notificationId", (Object)this.currentInteractiveMoment.getPugNotificationId());
            jsonObject.put("expectedVideoOffset", this.currentInteractiveMoment.getPugNotificationExpectedVideoOffset());
            if (this.getContext() != null) {
                UIViewLogUtils.reportIkoNotificationImpressionStarted(this.getContext(), jsonObject);
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    private void revealCompleted(final boolean momentStarted) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "revealCompleted: reveal = " + momentStarted);
        }
        if (momentStarted) {
            final int statusBarHeight = ViewUtils.getStatusBarHeight(this.getContext());
            int navigationBarHeight;
            if (!this.isNavigationBarBelowContent()) {
                navigationBarHeight = ViewUtils.getNavigationBarHeight(this.getContext(), false);
            }
            else {
                navigationBarHeight = 0;
            }
            this.closeButton.setPadding(0, statusBarHeight, navigationBarHeight, 0);
            if (!this.momentScreen.prepareAndStart()) {
                this.showHideLoadingProgress(true);
            }
        }
        else {
            AnimationUtils.startViewAppearanceAnimation((View)this.ikoContainer, false);
            AnimationUtils.startViewAppearanceAnimation((View)this.progressBar, true);
            AnimationUtils.startViewAppearanceAnimation((View)this.pugImageView, true);
            this.currentInteractiveMoment = null;
        }
        this.momentStarted = momentStarted;
    }
    
    private void showNavBar() {
        if (this.isActivityInvalid()) {
            return;
        }
        this.fragment.showNavigationBar();
        this.clearPanel(300);
    }
    
    private void showPug(final WPInteractiveMomentsModel$WPMoment currentInteractiveMoment, final int n) {
        if (currentInteractiveMoment == null) {
            Log.d("WPInteractiveMomentsManager", "Request to showPug unlocking animation on an null collection item.");
        }
        else {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "showPug: item = " + currentInteractiveMoment.getSceneType() + " progress = " + n);
            }
            if (!this.isManagerReady()) {
                Log.d("WPInteractiveMomentsManager", "Resources not available to showPug moment. Skipping for now...");
            }
            this.adjustPugLayout(this.isBottomPanelVisible());
            if (this.currentInteractiveMoment != null && this.currentInteractiveMoment != currentInteractiveMoment) {
                this.hide();
            }
            if (this.momentBackgrounded) {
                if (Log.isLoggable()) {
                    Log.d("WPInteractiveMomentsManager", "showPug: momentBackgrounded = true");
                }
            }
            else {
                if (this.isShowingInteractiveMoment) {
                    Log.d("WPInteractiveMomentsManager", "Already showing interactive moment. Ignore request.");
                    return;
                }
                final int n2 = currentInteractiveMoment.getSceneTriggerStartMS() - n - 666;
                if (Log.isLoggable()) {
                    Log.d("WPInteractiveMomentsManager", "showPug: duration - " + n2);
                }
                if (n2 <= 2000) {
                    this.hide();
                    return;
                }
                final boolean preparePugView = this.preparePugView(currentInteractiveMoment);
                if (Log.isLoggable()) {
                    Log.d("WPInteractiveMomentsManager", "showPug: ready - " + preparePugView);
                }
                if (preparePugView) {
                    this.isShowingInteractiveMoment = true;
                    this.isPugAutoOptIn = currentInteractiveMoment.isPugAutoOptIn();
                    this.currentInteractiveMoment = currentInteractiveMoment;
                    this.momentScreen.setInteractiveMomentAndFindViewsForMoment(this.currentInteractiveMoment, this.fragment.getView());
                    this.wpContainer.setOnTouchListener((View$OnTouchListener)null);
                    this.showNavBar();
                    this.playPugIntro(n2);
                    this.loadMomentScreenResources();
                    this.reportPugImpressionStarted(n);
                    this.reportMomentStarted();
                    return;
                }
                this.hide();
            }
        }
    }
    
    private void startCachingResources() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "startCachingResources for interactive moments");
        }
        if (this.data == null) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "loadSoundPoolResources: data is null");
            }
        }
        else {
            final List<String> preCacheableResourcesList = this.data.getPreCacheableResourcesList();
            if (preCacheableResourcesList != null && preCacheableResourcesList.size() > 0) {
                this.cachingResourcesComplete = false;
                this.resetCounters(preCacheableResourcesList.size());
                final Iterator<String> iterator = preCacheableResourcesList.iterator();
                while (iterator.hasNext()) {
                    this.cacheResources(iterator.next());
                }
            }
        }
    }
    
    private void startPugIntroAnimation(final int n) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "startPugIntroAnimation: duration - " + n);
        }
        this.pugPulsateAnimation(true);
        final ProgressBar progressBar = this.progressBar;
        int n2;
        if (n <= 5000) {
            n2 = 500;
        }
        else {
            n2 = 0;
        }
        (this.pugProgressAnimatorSet = new AnimatorSet()).play((Animator)ObjectAnimator.ofInt((Object)progressBar, "progress", new int[] { n2, 500 }));
        this.pugProgressAnimatorSet.setDuration((long)n);
        this.pugProgressAnimatorSet.addListener((Animator$AnimatorListener)new WPInteractiveMomentsManager$6(this));
        this.pugProgressAnimatorSet.start();
        if (this.playbackPaused) {
            this.pugProgressAnimatorSet.pause();
        }
    }
    
    private void startRevealScaleAnimation(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "startRevealScaleAnimation reveal - " + b);
        }
        float pugRevealScale;
        if (b) {
            pugRevealScale = this.pugRevealScale;
        }
        else {
            pugRevealScale = 1.0f;
        }
        final PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[] { pugRevealScale });
        final PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[] { pugRevealScale });
        if (this.revealAnimator != null && this.revealAnimator.isStarted()) {
            this.revealAnimator.removeAllListeners();
            this.revealAnimator.cancel();
        }
        (this.revealAnimator = ObjectAnimator.ofPropertyValuesHolder((Object)this.pugRevealView, new PropertyValuesHolder[] { ofFloat, ofFloat2 })).addListener((Animator$AnimatorListener)new WPInteractiveMomentsManager$7(this, b));
        this.revealAnimator.setDuration(666L);
        this.revealAnimator.setInterpolator((TimeInterpolator)this.quitOutInterpolator);
        this.revealAnimator.start();
    }
    
    @Override
    protected void cachingResourcesComplete() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "cachingResourcesComplete: callback received for caching resources completion");
        }
        this.loadSoundPoolResourcesBg();
        this.loadPugResourcesBg();
        this.cachingResourcesComplete = true;
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "cachingResourcesComplete: showingMoment = " + this.isShowingInteractiveMoment);
        }
        if (this.isShowingInteractiveMoment) {
            this.loadMomentScreenResources();
        }
    }
    
    public Bitmap getBitmapFromCache(final WPInteractiveMomentsModel$WPImage wpInteractiveMomentsModel$WPImage, final boolean b) {
        ThreadUtils.assertNotOnMain();
        if (wpInteractiveMomentsModel$WPImage == null || StringUtils.isEmpty(wpInteractiveMomentsModel$WPImage.getUrl())) {
            Log.d("WPInteractiveMomentsManager", "getBitmapFromCache() failed. WPImage or its url is null.");
            return null;
        }
        return this.fetchImageFromCache(wpInteractiveMomentsModel$WPImage.getUrl(), b);
    }
    
    @Override
    public void hide() {
        if (!this.isShowingInteractiveMoment) {
            return;
        }
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "hide: request to close moment");
        }
        this.isShowingInteractiveMoment = false;
        this.showHideLoadingProgress(this.pugSelected = false);
        this.releaseResources();
        this.animateReveal(false);
    }
    
    @Override
    public void init(final PlayerFragment fragment) {
        if (fragment == null) {
            Log.e("WPInteractiveMomentsManager", "Player fragment is null. This should not happen");
            return;
        }
        this.fragment = fragment;
        final View view = fragment.getView();
        this.ikoContainer = (RelativeLayout)view.findViewById(2131689839);
        if (this.ikoContainer == null) {
            Log.d("WPInteractiveMomentsManager", "No interactive moments view container. Exiting the decorator.");
            return;
        }
        if (this.ikoContainer.getChildCount() > 0) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "init: word party container already present, removing all views.");
            }
            this.ikoContainer.removeAllViews();
        }
        this.hasInteractiveMoments = false;
        LayoutInflater.from((Context)fragment.getNetflixActivity()).inflate(2130903301, (ViewGroup)this.ikoContainer, true);
        this.findAndConfigureViews(view);
        if (ViewUtils.isNavigationBarBelowContent(fragment.getActivity())) {
            this.bottomPanelHeight += ViewUtils.getNavigationBarHeight((Context)fragment.getActivity(), false);
        }
        final int dimensionPixelSize = fragment.getResources().getDimensionPixelSize(2131361942);
        this.deviceWidth = DeviceUtils.getScreenWidthInPixels(this.getContext());
        this.pugRevealScale = this.deviceWidth / dimensionPixelSize * 2.5f;
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "Pug reveal view scale set to " + this.pugRevealScale);
        }
        this.svcManager = ServiceManager.getServiceManager(fragment.getNetflixActivity());
        this.soundPoolManager = new SoundPoolManager();
        this.quitOutInterpolator = WPConstants.getQuintOutInterpolator();
        this.interpolator = (Interpolator)new AccelerateDecelerateInterpolator();
        fragment.addPlayPauseListener(this.playPauseListener);
        this.setBitmapFactoryOptions(this.getContext());
    }
    
    @Override
    public boolean isManagerReady() {
        return this.areResourcesCached();
    }
    
    public boolean isNavigationBarBelowContent() {
        boolean navigationBarBelowContent = true;
        if (this.fragment != null) {
            navigationBarBelowContent = navigationBarBelowContent;
            if (this.fragment.getNetflixActivity() != null) {
                navigationBarBelowContent = ViewUtils.isNavigationBarBelowContent(this.fragment.getNetflixActivity());
            }
        }
        return navigationBarBelowContent;
    }
    
    @Override
    public void onDestroy() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "onDestroy() called for fragment");
        }
        if (this.fragment != null) {
            this.fragment.removePlayPauseListener(this.playPauseListener);
        }
        if (this.momentScreen != null) {
            this.momentScreen.onDestroy();
        }
        this.cleanUpResources();
        if (this.getContext() != null) {
            IkoLogUtils.reportIkoModeEnded(this.getContext(), IClientLogging$CompletionReason.success, null);
        }
    }
    
    @Override
    public void onMomentsFetched(final InteractivePlaybackMoments interactivePlaybackMoments) {
        if (interactivePlaybackMoments != null) {
            final InteractiveMomentsModel data = interactivePlaybackMoments.getData();
            if (data != null && !"wordparty".equalsIgnoreCase(data.getType())) {
                Log.d("WPInteractiveMomentsManager", "Interactive data is null or of wrong type.");
            }
            else {
                if (data == null || !(data instanceof WPInteractiveMomentsModel)) {
                    Log.d("WPInteractiveMomentsManager", "InteractiveData is null or not instance of WPInteractiveMomentsModel.");
                    return;
                }
                if (this.getContext() != null) {
                    IkoLogUtils.reportIkoModeStarted(this.getContext(), null, IClientLogging$ModalView.ikoMode);
                }
                this.data = (WPInteractiveMomentsModel)data;
                this.interactiveMoments = this.data.getMoments();
                if (this.interactiveMoments != null && !this.interactiveMoments.isEmpty()) {
                    this.hasInteractiveMoments = true;
                    Collections.sort(this.interactiveMoments, new WPInteractiveMomentsManager$2(this));
                    for (final WPInteractiveMomentsModel$WPMoment wpInteractiveMomentsModel$WPMoment : this.interactiveMoments) {
                        if (wpInteractiveMomentsModel$WPMoment != null) {
                            this.momentsDisplayTimeList.add(wpInteractiveMomentsModel$WPMoment.getPugStartTimeMS());
                            this.momentsDisplayTimeList.add(wpInteractiveMomentsModel$WPMoment.getSceneTriggerEndMS());
                        }
                    }
                    this.startCachingResources();
                }
            }
        }
    }
    
    @Override
    public void onPause() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "onPause() called for fragment");
        }
        if (this.urlToMediaPlayerMap != null && !this.urlToMediaPlayerMap.isEmpty()) {
            for (final MediaPlayerWrapper mediaPlayerWrapper : this.urlToMediaPlayerMap.values()) {
                if (mediaPlayerWrapper != null) {
                    mediaPlayerWrapper.pausePlayback();
                }
            }
        }
        if (this.soundPoolManager != null) {
            this.soundPoolManager.autoPause();
        }
        if (this.momentStarted && this.momentScreen != null) {
            this.momentScreen.onPause();
        }
        if (this.isShowingInteractiveMoment) {
            this.reportMomentEnded(IClientLogging$CompletionReason.canceled);
        }
    }
    
    @Override
    public void onResume() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "onResume() called for fragment");
        }
        if (this.isShowingInteractiveMoment) {
            this.reportMomentStarted();
        }
        if (this.urlToMediaPlayerMap != null && !this.urlToMediaPlayerMap.isEmpty()) {
            for (final MediaPlayerWrapper mediaPlayerWrapper : this.urlToMediaPlayerMap.values()) {
                if (mediaPlayerWrapper != null) {
                    mediaPlayerWrapper.resumePlayback();
                }
            }
        }
        if (this.soundPoolManager != null) {
            this.soundPoolManager.autoResume();
        }
        if (this.momentStarted && this.momentScreen != null) {
            this.momentScreen.onResume();
        }
    }
    
    @Override
    public void onStart() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "onStart() called for fragment");
        }
        this.momentBackgrounded = false;
        if (this.soundPoolManager == null) {
            this.soundPoolManager = new SoundPoolManager();
            this.loadSoundPoolResourcesBg();
        }
        if (this.momentScreen != null) {
            this.momentScreen.onStart();
        }
    }
    
    @Override
    public void onStop() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "onStop() called for fragment");
        }
        this.momentBackgrounded = true;
        this.playerBackgrounded = true;
        if (this.momentStarted && this.momentScreen != null) {
            this.momentScreen.onStop();
        }
        if (this.isShowingInteractiveMoment && !this.momentStarted) {
            this.hidePug();
        }
        this.releaseResources();
        this.releaseSoundPool();
    }
    
    @Override
    public void onVideoDetailsFetched(final VideoDetails videoDetails) {
    }
    
    public void playAudio(final String s, final float n, final boolean b, final BaseInteractiveMomentsManager$PlaybackCompleteListener baseInteractiveMomentsManager$PlaybackCompleteListener) {
        if (this.momentBackgrounded) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "playAudio: moment in background. Not playing audio url - " + s);
            }
            return;
        }
        this.playMediaPlayerAudio(s, n, b, baseInteractiveMomentsManager$PlaybackCompleteListener);
    }
    
    public void playBgAudio() {
        if (this.currentInteractiveMoment == null) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "playBgAudio: cannot play audio - currentInteractiveMoment is null");
            }
        }
        else {
            final WPInteractiveMomentsModel$WPAudio backgroundAudio = this.currentInteractiveMoment.getBackgroundAudio();
            if (backgroundAudio == null) {
                if (Log.isLoggable()) {
                    Log.d("WPInteractiveMomentsManager", "playBgAudio: moment getBackgroundAudio returned null");
                }
            }
            else {
                final String url = backgroundAudio.getUrl();
                if (!StringUtils.isEmpty(url)) {
                    this.playAudio(url, backgroundAudio.getVolume(), true, null);
                    return;
                }
                if (Log.isLoggable()) {
                    Log.d("WPInteractiveMomentsManager", "playBgAudio: moment background audio url is empty or null");
                }
            }
        }
    }
    
    public void playBoingSound() {
        this.playSoundPoolId(this.tutorialBoingSoundId, 1.0f);
    }
    
    public void playItemSelectSound() {
        this.playSoundPoolId(this.itemSelectSoundId, 1.0f);
    }
    
    public void playPanelShuffleSound() {
        this.playSoundPoolId(this.panelShuffleSoundId, 1.0f);
    }
    
    public void playVictorySound() {
        this.playSoundPoolId(this.victorySoundId, 1.0f);
    }
    
    public void playWiggleSound() {
        this.playSoundPoolId(this.itemWiggleSoundId, 1.0f);
    }
    
    @Override
    public void playerOverlayVisibility(final boolean bottomPanelVisible) {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "playerOverlayVisibility: show - " + bottomPanelVisible);
        }
        this.bottomPanelVisible = bottomPanelVisible;
        if (bottomPanelVisible && this.isShowingInteractiveMoment) {
            this.fragment.startScreenUpdateTask();
        }
        this.adjustPugLayout(bottomPanelVisible);
    }
    
    public void releaseResources() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "releaseResources: all MediaPlayerWrapper instances");
        }
        if (this.urlToMediaPlayerMap != null) {
            for (final MediaPlayerWrapper mediaPlayerWrapper : this.urlToMediaPlayerMap.values()) {
                if (mediaPlayerWrapper != null) {
                    mediaPlayerWrapper.releaseMediaPlayer();
                }
            }
        }
    }
    
    public void releaseSoundPool() {
        if (Log.isLoggable()) {
            Log.d("WPInteractiveMomentsManager", "releaseSoundPool: SoundPool resources");
        }
        if (this.soundPoolManager != null) {
            this.soundPoolManager.release();
            this.soundPoolManager = null;
        }
    }
    
    public void reportCommandEvent(final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName) {
        if (this.fragment == null || !this.fragment.isActivityValid()) {
            if (Log.isLoggable()) {
                Log.d("WPInteractiveMomentsManager", "reportCommanEvent: Fragment is either null or invalid.");
            }
            return;
        }
        UIViewLogUtils.reportUIViewCommand(this.getContext(), uiViewLogging$UIViewCommandName, this.fragment.getNetflixActivity().getUiScreen(), this.fragment.getNetflixActivity().getDataContext());
    }
    
    public void reportIkoNotificationImpressionEnded() {
        final Context context = this.getContext();
        if (context != null) {
            UIViewLogUtils.reportIkoNotificationImpressionEnded(context);
        }
    }
    
    public void reportMomentEnded(final IClientLogging$CompletionReason clientLogging$CompletionReason) {
        String currentStateNameForLogging;
        if (this.momentScreen != null) {
            currentStateNameForLogging = this.momentScreen.getCurrentStateNameForLogging();
        }
        else {
            currentStateNameForLogging = null;
        }
        final Context context = this.getContext();
        if (context != null) {
            int momentExpectedVideoOffset = -1;
            String momentId;
            String sceneType;
            if (this.currentInteractiveMoment != null) {
                momentId = this.currentInteractiveMoment.getMomentId();
                sceneType = this.currentInteractiveMoment.getSceneType();
                momentExpectedVideoOffset = this.currentInteractiveMoment.getMomentExpectedVideoOffset();
            }
            else {
                sceneType = null;
                momentId = null;
            }
            IkoLogUtils.reportIkoMomentEnded(context, clientLogging$CompletionReason, null, momentId, sceneType, momentExpectedVideoOffset, currentStateNameForLogging);
        }
    }
    
    public void setActiveExit(final boolean activeExit) {
        this.activeExit = activeExit;
    }
    
    @Override
    public void setTimelineProgress(final int n, final boolean isSeeking) {
        if (isSeeking && this.hasInteractiveMoments) {
            final int binarySearch = Collections.binarySearch(this.momentsDisplayTimeList, n);
            if (binarySearch >= 0) {
                this.showPug(this.interactiveMoments.get(binarySearch / 2), n);
            }
            else {
                final int n2 = binarySearch * -1 - 1;
                if (n2 % 2 != 0) {
                    this.showPug(this.interactiveMoments.get(n2 / 2), n);
                }
                else {
                    this.hide();
                }
            }
        }
        else if (this.isSeeking != isSeeking) {
            this.hidePug();
        }
        this.isSeeking = isSeeking;
    }
    
    public void showHideLoadingProgress(final boolean b) {
        ViewUtils.setVisibleOrGone((View)this.loadingProgressBar, b);
    }
}
