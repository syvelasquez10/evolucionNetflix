// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ObjectAnimator;
import android.text.Html;
import com.netflix.mediaclient.util.ViewUtils;
import android.graphics.Paint;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import android.view.animation.LinearInterpolator;
import android.graphics.BitmapFactory$Options;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.OnAnimationEndListener;
import android.view.animation.Interpolator;
import android.view.View$OnClickListener;
import android.animation.Animator$AnimatorListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;

public class KongBattleIntroScreen extends KongBaseScreen
{
    private static final String TAG = "KongBattleIntroScreen";
    private int autoPlayInterval;
    private int backButtonWidth;
    private ImageView battleCard;
    private View battleCardComposite;
    private float battleCardHeight;
    private float battleCardWidth;
    private TextView battleCountdownTimer;
    private float battleEpisodeSpacing;
    private PressAnimationFrameLayout battleIntroCompoundView;
    private ViewGroup battleIntroContainer;
    private View battleIntroPlaceholderView;
    Animator$AnimatorListener battleIntroScaleDownListener;
    private String battleOptInHeaderString;
    private String battleOptInVOUrl;
    private float battleOptInVOVolume;
    private TextView battleTitle;
    private String battleTitleString;
    private int battleWhatsNextSoundId;
    Runnable countdownTimerRunnable;
    private String dropShadowImageUrl;
    private int finishTimeCounterSeconds;
    View$OnClickListener gearSelectionClickListener;
    private boolean hasWatchedAllBattlesForEpisode;
    private Interpolator interpolator;
    private boolean isBattleResultWin;
    private boolean isNextEpisodeTimerFocused;
    private String itemSelectionSoundUrl;
    private float itemSelectionSoundVolume;
    private ImageView kongAvatar;
    private String kongAvatarImageUrl;
    private ImageView leftGate;
    private String leftGateImageUrl;
    private float leftGateWidth;
    OnAnimationEndListener mBattleIntroAnimationEndListener;
    private AdvancedImageView nextEpisode;
    View$OnClickListener nextEpisodeClickListener;
    private ViewGroup nextEpisodeContainer;
    private TextView nextEpisodeCountdownTimer;
    private String nextEpisodeImageUrl;
    private TextView nextEpisodeTitle;
    private String nextEpisodeTitleString;
    private int nextEpisodeTrackId;
    private int nextEpisodeVideoId;
    private ImageView opponentAvatar;
    private String opponentAvatarImageUrl;
    private BitmapFactory$Options options;
    private int prepareBattleSoundId;
    private String redFlareImageUrl;
    private String resultDataBattleCardImageUrl;
    private ImageView rightGate;
    private String rightGateImageUrl;
    private float rightGateWidth;
    private float scaleDownHeight;
    private float scaleDownWidth;
    private String timerString;
    private TextView timerView;
    private ImageView versusHex;
    private String versusHexImageUrl;
    private ImageView versusHexSword;
    private String versusHexSwordImageUrl;
    
    KongBattleIntroScreen(final KongInteractivePostPlayManager kongInteractivePostPlayManager) {
        super(kongInteractivePostPlayManager);
        this.interpolator = (Interpolator)new LinearInterpolator();
        this.options = new BitmapFactory$Options();
        this.countdownTimerRunnable = new KongBattleIntroScreen$2(this);
        this.mBattleIntroAnimationEndListener = new KongBattleIntroScreen$3(this);
        this.battleIntroScaleDownListener = (Animator$AnimatorListener)new KongBattleIntroScreen$4(this);
        this.gearSelectionClickListener = (View$OnClickListener)new KongBattleIntroScreen$5(this);
        this.nextEpisodeClickListener = (View$OnClickListener)new KongBattleIntroScreen$6(this);
    }
    
    void animationEndUIState() {
        this.leftGate.setTranslationX(0.0f);
        this.rightGate.setTranslationX(0.0f);
        this.opponentAvatar.setTranslationX(0.0f);
        this.versusHex.setAlpha(1.0f);
        this.versusHexSword.setAlpha(1.0f);
        this.postPlayManager.setTitleText(this.battleOptInHeaderString);
        this.battleIntroCompoundView.setScaleX(this.scaleDownWidth);
        this.battleIntroCompoundView.setScaleY(this.scaleDownHeight);
        this.battleIntroContainer.setTranslationX(-this.battleEpisodeSpacing);
        if (this.nextEpisodeVideoId > 0) {
            this.nextEpisodeContainer.setVisibility(0);
            this.nextEpisodeContainer.setScaleX(1.0f);
            this.nextEpisodeContainer.setScaleY(1.0f);
            this.nextEpisodeContainer.setTranslationX(this.battleEpisodeSpacing);
        }
    }
    
    public TextView getBattleCountdownTimer() {
        return this.battleCountdownTimer;
    }
    
    public int getBattleCountdownTimerVisibility() {
        if (this.battleCountdownTimer != null) {
            return this.battleCountdownTimer.getVisibility();
        }
        return 8;
    }
    
    PressAnimationFrameLayout getBattleIntroCompoundView() {
        return this.battleIntroCompoundView;
    }
    
    public ViewGroup getBattleIntroContainer() {
        return this.battleIntroContainer;
    }
    
    public View getBattleIntroPlaceholderView() {
        return this.battleIntroPlaceholderView;
    }
    
    public TextView getBattleTitle() {
        return this.battleTitle;
    }
    
    public ViewGroup getGear1Group() {
        return this.postPlayManager.getGear1Group();
    }
    
    public ViewGroup getGear2Group() {
        return this.postPlayManager.getGear2Group();
    }
    
    public ImageView getKongAvatar() {
        return this.kongAvatar;
    }
    
    public ImageView getLeftGate() {
        return this.leftGate;
    }
    
    public AdvancedImageView getNextEpisode() {
        return this.nextEpisode;
    }
    
    public ViewGroup getNextEpisodeContainer() {
        return this.nextEpisodeContainer;
    }
    
    public TextView getNextEpisodeCountdownTimer() {
        return this.nextEpisodeCountdownTimer;
    }
    
    public int getNextEpisodeCountdownTimerVisibility() {
        if (this.nextEpisodeCountdownTimer != null) {
            return this.nextEpisodeCountdownTimer.getVisibility();
        }
        return 8;
    }
    
    public TextView getNextEpisodeTitle() {
        return this.nextEpisodeTitle;
    }
    
    int getNextEpisodeVideoId() {
        return this.nextEpisodeVideoId;
    }
    
    AdvancedImageView getNextEpisodeView() {
        return this.nextEpisode;
    }
    
    public ImageView getOpponentAvatar() {
        return this.opponentAvatar;
    }
    
    public ImageView getRightGate() {
        return this.rightGate;
    }
    
    public ImageView getVersusHex() {
        return this.versusHex;
    }
    
    public ImageView getVersusHexSword() {
        return this.versusHexSword;
    }
    
    public boolean hasTimerExpired() {
        return this.finishTimeCounterSeconds <= 0;
    }
    
    @Override
    void hide() {
    }
    
    @Override
    void initViews(final View view) {
        if (view == null) {
            if (Log.isLoggable()) {
                Log.d("KongBattleIntroScreen", "Container is null. Cannot inflate views for screen.");
            }
        }
        else {
            this.battleIntroContainer = (ViewGroup)view.findViewById(2131755662);
            this.nextEpisodeContainer = (ViewGroup)view.findViewById(2131755658);
            final NetflixActivity activity = this.postPlayManager.getActivity();
            this.battleCardWidth = activity.getResources().getDimensionPixelSize(2131427461);
            this.battleCardHeight = this.battleCardWidth / DeviceUtils.getScreenAspectRatio((Context)activity);
            final float n = DeviceUtils.getScreenWidthInPixels((Context)activity);
            final float n2 = DeviceUtils.getScreenHeightInPixels((Context)activity);
            final float n3 = DeviceUtils.getRealScreenHeightInPixels(activity);
            this.scaleDownWidth = this.battleCardWidth / n;
            this.scaleDownHeight = this.battleCardHeight / (n3 * 1.0f);
            if (this.nextEpisodeVideoId > 0) {
                this.backButtonWidth = activity.getResources().getDimensionPixelSize(2131427474);
                this.battleEpisodeSpacing = activity.getResources().getDimensionPixelSize(2131427460);
                final float n4 = this.battleCardWidth / 2.0f;
                int n5;
                if (n / 2.0f <= this.backButtonWidth + n4 + this.battleEpisodeSpacing) {
                    n5 = 1;
                }
                else {
                    n5 = 0;
                }
                if (n5 != 0) {
                    this.battleEpisodeSpacing = (this.battleEpisodeSpacing - n4) / 2.0f + n4;
                }
            }
            if (this.battleIntroContainer != null) {
                this.battleTitle = (TextView)this.battleIntroContainer.findViewById(2131755665);
                this.battleCountdownTimer = (TextView)this.battleIntroContainer.findViewById(2131755666);
                this.battleIntroCompoundView = (PressAnimationFrameLayout)this.battleIntroContainer.findViewById(2131755663);
                this.battleIntroPlaceholderView = this.battleIntroContainer.findViewById(2131755664);
                this.battleIntroPlaceholderView.getLayoutParams().height = (int)this.battleCardHeight;
                if (this.battleIntroCompoundView != null) {
                    this.leftGate = (ImageView)this.battleIntroCompoundView.findViewById(2131755434);
                    this.rightGate = (ImageView)this.battleIntroCompoundView.findViewById(2131755433);
                    this.versusHex = (ImageView)this.battleIntroCompoundView.findViewById(2131755437);
                    this.versusHexSword = (ImageView)this.battleIntroCompoundView.findViewById(2131755438);
                    this.kongAvatar = (ImageView)this.battleIntroCompoundView.findViewById(2131755436);
                    this.opponentAvatar = (ImageView)this.battleIntroCompoundView.findViewById(2131755435);
                    this.leftGateWidth = DeviceUtils.getScreenWidthInPixels((Context)activity);
                    this.rightGateWidth = this.leftGateWidth;
                    this.battleCard = (ImageView)this.battleIntroCompoundView.findViewById(2131755439);
                    this.battleCardComposite = this.battleIntroCompoundView.findViewById(2131755431);
                    this.battleIntroCompoundView.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.battleIntroCompoundView.getPressedStateHandler(), this.gearSelectionClickListener));
                    this.battleCountdownTimer.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.battleIntroCompoundView.getPressedStateHandler(), this.gearSelectionClickListener));
                }
            }
            if (this.nextEpisodeContainer != null) {
                this.nextEpisode = (AdvancedImageView)this.nextEpisodeContainer.findViewById(2131755659);
                this.nextEpisode.getLayoutParams().height = (int)this.battleCardHeight;
                this.nextEpisode.setScaleType(ImageView$ScaleType.CENTER_CROP);
                this.nextEpisodeTitle = (TextView)this.nextEpisodeContainer.findViewById(2131755660);
                this.nextEpisodeCountdownTimer = (TextView)this.nextEpisodeContainer.findViewById(2131755661);
                this.nextEpisode.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.nextEpisode.getPressedStateHandler(), this.nextEpisodeClickListener));
                this.nextEpisodeCountdownTimer.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.nextEpisode.getPressedStateHandler(), this.nextEpisodeClickListener));
            }
        }
    }
    
    @Override
    void loadPostPlayData(final KongInteractivePostPlayModel kongInteractivePostPlayModel) {
        if (kongInteractivePostPlayModel == null) {
            if (Log.isLoggable()) {
                Log.d("KongBattleIntroScreen", "Interactive data is null. Cannot load post play info.");
            }
        }
        else {
            this.autoPlayInterval = kongInteractivePostPlayModel.getAutoPlayInterval();
            this.leftGateImageUrl = kongInteractivePostPlayModel.getLeftGateImageUrl();
            this.redFlareImageUrl = kongInteractivePostPlayModel.getRedFlareImageUrl();
            this.rightGateImageUrl = kongInteractivePostPlayModel.getRightGateImageUrl();
            this.versusHexImageUrl = kongInteractivePostPlayModel.getVersusHexImageUrl();
            this.versusHexSwordImageUrl = kongInteractivePostPlayModel.getVersusSwordsImageUrl();
            this.kongAvatarImageUrl = kongInteractivePostPlayModel.getAvatarImageUrl();
            this.opponentAvatarImageUrl = kongInteractivePostPlayModel.getOpponentImageUrl();
            this.dropShadowImageUrl = kongInteractivePostPlayModel.getCardDropshadowImageUrl();
            this.nextEpisodeImageUrl = kongInteractivePostPlayModel.getNextEpisodeImageUrl();
            this.nextEpisodeVideoId = kongInteractivePostPlayModel.getNextEpisodeVideoId();
            this.nextEpisodeTrackId = kongInteractivePostPlayModel.getNextEpisodeTrackId();
            this.nextEpisodeTitleString = kongInteractivePostPlayModel.getNextEpisodeString();
            this.timerString = kongInteractivePostPlayModel.getAutoPlayString();
            this.battleOptInHeaderString = kongInteractivePostPlayModel.getBattleOptInHeaderString();
            this.isNextEpisodeTimerFocused = kongInteractivePostPlayModel.isNextEpisodeFocused();
            this.battleTitleString = kongInteractivePostPlayModel.getBattleTitleString();
            final KongInteractivePostPlayModel$KongSound battleOptInVOSound = kongInteractivePostPlayModel.getBattleOptInVOSound();
            if (battleOptInVOSound != null) {
                this.battleOptInVOUrl = battleOptInVOSound.getUrl();
                this.battleOptInVOVolume = battleOptInVOSound.getVolume();
            }
            final KongInteractivePostPlayModel$KongSound itemSelectionSound = kongInteractivePostPlayModel.getItemSelectionSound();
            if (itemSelectionSound != null) {
                this.itemSelectionSoundUrl = itemSelectionSound.getUrl();
                this.itemSelectionSoundVolume = itemSelectionSound.getVolume();
            }
            if (this.postPlayManager.getPostPlayState() == KongInteractivePostPlayManager$POST_PLAY_STATE.RESULT) {
                this.battleTitleString = kongInteractivePostPlayModel.getBattleAgainString();
                this.hasWatchedAllBattlesForEpisode = kongInteractivePostPlayModel.hasWatchedAllBattleVideosForEpisode();
                this.resultDataBattleCardImageUrl = kongInteractivePostPlayModel.getResultBattleCardImageUrl();
                this.isBattleResultWin = "Win".equalsIgnoreCase(kongInteractivePostPlayModel.getResultType());
            }
        }
    }
    
    @Override
    void loadResources() {
        ThreadUtils.assertNotOnMain();
        if (!this.postPlayManager.isHighPerfDevice()) {
            this.options.inSampleSize = 2;
        }
        this.postPlayManager.loadImageBitmapFromCache(this.rightGate, this.rightGateImageUrl, this.options);
        this.postPlayManager.loadImageBitmapFromCache(this.leftGate, this.leftGateImageUrl, this.options);
        this.postPlayManager.loadImageBitmapFromCache(this.nextEpisode, this.nextEpisodeImageUrl, this.options);
        if (StringUtils.isNotEmpty(this.resultDataBattleCardImageUrl)) {
            this.postPlayManager.loadImageBitmapFromCache(this.battleCard, this.resultDataBattleCardImageUrl, this.options);
        }
        this.postPlayManager.loadImageBitmapFromCache(this.opponentAvatar, this.opponentAvatarImageUrl, this.options);
        this.postPlayManager.loadImageBitmapFromCache(this.kongAvatar, this.kongAvatarImageUrl, this.options);
        this.postPlayManager.loadImageBitmapFromCache(this.versusHex, this.versusHexImageUrl);
        this.postPlayManager.loadImageBitmapFromCache(this.versusHexSword, this.versusHexSwordImageUrl);
        this.loadSoundPoolResources();
    }
    
    void loadSoundPoolResources() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager == null) {
            Log.e("KongBattleIntroScreen", "Sound pool manager is null. Cannot load VO resources.");
            return;
        }
        this.battleWhatsNextSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.battleOptInVOUrl));
        this.prepareBattleSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.itemSelectionSoundUrl));
    }
    
    @Override
    void onResourcesLoaded() {
        this.versusHex.setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.versusHexSword.setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.rightGate.setLayerType(2, (Paint)null);
        this.leftGate.setLayerType(2, (Paint)null);
    }
    
    void playBattleSound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.battleWhatsNextSoundId, this.battleOptInVOVolume);
        }
    }
    
    void playPrepareBattleSound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.prepareBattleSoundId, this.itemSelectionSoundVolume);
        }
    }
    
    @Override
    public void releaseBitmapResources() {
        ViewUtils.resetImageDrawable(this.rightGate);
        ViewUtils.resetImageDrawable(this.leftGate);
        ViewUtils.resetImageDrawable(this.nextEpisode);
        ViewUtils.resetImageDrawable(this.opponentAvatar);
        ViewUtils.resetImageDrawable(this.kongAvatar);
        ViewUtils.resetImageDrawable(this.versusHex);
        ViewUtils.resetImageDrawable(this.versusHexSword);
    }
    
    void setCountdownTimerText(final TextView timerView) {
        if (timerView == null) {
            Log.d("KongBattleIntroScreen", "countDownTimerView is null. Returning from setting countdown timer.");
            return;
        }
        this.timerView = timerView;
        int finishTimeCounterSeconds;
        if (this.finishTimeCounterSeconds > 0) {
            finishTimeCounterSeconds = this.finishTimeCounterSeconds;
        }
        else {
            finishTimeCounterSeconds = 0;
        }
        this.timerView.setText((CharSequence)Html.fromHtml(String.format(this.timerString, String.valueOf(finishTimeCounterSeconds))));
        if (this.finishTimeCounterSeconds-- < 0) {
            this.timerView.performClick();
            ViewUtils.setVisibleOrInvisible((View)this.timerView, false);
            return;
        }
        this.getHandler().postDelayed(this.countdownTimerRunnable, 1000L);
    }
    
    @Override
    void start() {
        this.startAnimation();
    }
    
    boolean startAnimation() {
        this.postPlayManager.cancelCurrentAnimation();
        boolean b = false;
        if (this.battleIntroContainer.getVisibility() == 0) {
            b = true;
        }
        if (this.postPlayManager.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.BATTLE) {
            this.playPrepareBattleSound();
        }
        this.finishTimeCounterSeconds = this.autoPlayInterval;
        ViewUtils.setVisibleOrInvisible((View)this.battleCard, false);
        ViewUtils.setVisibleOrInvisible(this.battleCardComposite, true);
        ViewUtils.setVisibleOrGone((View)this.battleIntroContainer, true);
        ViewUtils.setVisibleOrGoneAnimation((View)this.postPlayManager.getBattleResultAvatar(), false, true);
        ViewUtils.setVisibleOrGoneAnimation((View)this.postPlayManager.getUnlockingGearContainer(), false, true);
        ViewUtils.setVisibleOrGone((View)this.nextEpisodeContainer, false);
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getPowerUpContainer(), false);
        ViewUtils.setVisibleOrGone((View)this.getGear1Group(), false);
        ViewUtils.setVisibleOrGone((View)this.getGear2Group(), false);
        ViewUtils.setVisibleOrGone((View)this.nextEpisodeTitle, false);
        ViewUtils.setVisibleOrGone((View)this.nextEpisodeCountdownTimer, false);
        ViewUtils.setVisibleOrGone((View)this.battleTitle, false);
        ViewUtils.setVisibleOrGone((View)this.battleCountdownTimer, false);
        this.battleIntroCompoundView.setClickable(false);
        this.battleIntroCompoundView.setClickable(false);
        this.nextEpisode.setClickable(false);
        this.getGear1Group().setClickable(false);
        this.getGear2Group().setClickable(false);
        this.battleIntroCompoundView.setScaleX(1.0f);
        this.battleIntroCompoundView.setScaleY(1.0f);
        this.battleIntroContainer.setTranslationX(0.0f);
        this.leftGate.setTranslationX(-this.leftGateWidth);
        this.rightGate.setTranslationX(this.rightGateWidth);
        if (!b) {
            this.kongAvatar.setTranslationX(-this.leftGateWidth);
        }
        this.opponentAvatar.setTranslationX(this.rightGateWidth);
        this.versusHex.setAlpha(0.0f);
        this.versusHexSword.setAlpha(0.0f);
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this.leftGate, View.TRANSLATION_X, new float[] { 0.0f });
        ofFloat.setInterpolator((TimeInterpolator)this.interpolator);
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object)this.rightGate, View.TRANSLATION_X, new float[] { 0.0f });
        ofFloat2.setInterpolator((TimeInterpolator)this.interpolator);
        final ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat((Object)this.kongAvatar, View.TRANSLATION_X, new float[] { 0.0f });
        ofFloat3.setInterpolator((TimeInterpolator)this.interpolator);
        final ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat((Object)this.opponentAvatar, View.TRANSLATION_X, new float[] { 0.0f });
        ofFloat4.setInterpolator((TimeInterpolator)this.interpolator);
        final ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat((Object)this.versusHex, View.ALPHA, new float[] { 1.0f });
        final ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat((Object)this.versusHexSword, View.ALPHA, new float[] { 1.0f });
        final AnimatorSet set = new AnimatorSet();
        set.playTogether(new Animator[] { ofFloat, ofFloat2, ofFloat3, ofFloat4 });
        set.setDuration(666L);
        final AnimatorSet currentAnimation = new AnimatorSet();
        currentAnimation.play((Animator)set).before((Animator)ofFloat5).before((Animator)ofFloat6);
        if (this.postPlayManager.shouldHideAnimation() || (this.postPlayManager.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.RESULT && !this.hasWatchedAllBattlesForEpisode)) {
            final ObjectAnimator setDuration = ObjectAnimator.ofFloat((Object)this.battleIntroCompoundView, View.ALPHA, new float[] { 0.0f }).setDuration(1000L);
            setDuration.setStartDelay(1000L);
            setDuration.addListener((Animator$AnimatorListener)new KongBattleIntroScreen$1(this));
            final ObjectAnimator setDuration2 = ObjectAnimator.ofFloat((Object)this.battleIntroCompoundView, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
            final ObjectAnimator setDuration3 = ObjectAnimator.ofFloat((Object)this.nextEpisodeContainer, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
            currentAnimation.play((Animator)ofFloat5).with((Animator)ofFloat6).before((Animator)setDuration);
            currentAnimation.play((Animator)setDuration2).with((Animator)setDuration3).after((Animator)setDuration);
        }
        else {
            final AnimatorSet set2 = new AnimatorSet();
            set2.playTogether(new Animator[] { ObjectAnimator.ofFloat((Object)this.battleIntroCompoundView, View.SCALE_Y, new float[] { this.scaleDownHeight }).setDuration(666L), ObjectAnimator.ofFloat((Object)this.battleIntroCompoundView, View.SCALE_X, new float[] { this.scaleDownWidth }).setDuration(666L) });
            set2.setStartDelay(1000L);
            set2.addListener(this.battleIntroScaleDownListener);
            final ObjectAnimator setDuration4 = ObjectAnimator.ofFloat((Object)this.battleIntroContainer, View.TRANSLATION_X, new float[] { -this.battleEpisodeSpacing }).setDuration(666L);
            setDuration4.setStartDelay(333L);
            this.nextEpisodeContainer.setTranslationX(0.0f);
            final ObjectAnimator setDuration5 = ObjectAnimator.ofFloat((Object)this.nextEpisodeContainer, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
            final ObjectAnimator setDuration6 = ObjectAnimator.ofFloat((Object)this.nextEpisodeContainer, View.TRANSLATION_X, new float[] { this.battleEpisodeSpacing }).setDuration(666L);
            currentAnimation.play((Animator)ofFloat5).with((Animator)ofFloat6).before((Animator)set2);
            if (this.nextEpisodeVideoId > 0) {
                currentAnimation.play((Animator)setDuration4).with((Animator)setDuration6).with((Animator)setDuration5).after((Animator)set2);
            }
        }
        currentAnimation.addListener((Animator$AnimatorListener)this.mBattleIntroAnimationEndListener);
        this.postPlayManager.setCurrentAnimation((Animator)currentAnimation);
        currentAnimation.start();
        return true;
    }
    
    void startTimer() {
        if (this.autoPlayInterval < 0) {
            return;
        }
        if (this.nextEpisodeVideoId > 0 && ((this.postPlayManager.postPlayState != KongInteractivePostPlayManager$POST_PLAY_STATE.RESULT && this.isNextEpisodeTimerFocused) || this.hasWatchedAllBattlesForEpisode)) {
            ViewUtils.setVisibleOrGone((View)this.battleCountdownTimer, false);
            ViewUtils.setVisibleOrGone((View)this.nextEpisodeCountdownTimer, true);
            this.setCountdownTimerText(this.nextEpisodeCountdownTimer);
            return;
        }
        ViewUtils.setVisibleOrGone((View)this.battleCountdownTimer, true);
        ViewUtils.setVisibleOrGone((View)this.nextEpisodeCountdownTimer, false);
        this.setCountdownTimerText(this.battleCountdownTimer);
    }
}
