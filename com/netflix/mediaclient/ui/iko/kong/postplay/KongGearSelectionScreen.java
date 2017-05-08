// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.animation.LinearInterpolator;
import android.animation.TimeInterpolator;
import java.util.List;
import android.widget.TextView;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongCollectionItems;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.graphics.Bitmap;

public class KongGearSelectionScreen extends KongBaseScreen
{
    private static final String TAG = "KongGearSelectionScreen";
    private int autoPlayInterval;
    private KongBattleIntroScreen battleIntroScreen;
    private int battleLoseTrackId;
    private int battleLoseVideoId;
    private int battleWinTrackId;
    private int battleWinVideoId;
    private Bitmap bitmapGear1;
    private Bitmap bitmapGear2;
    private int finishTimeCounterSeconds;
    private ImageView gear1;
    private ImageView gear1Background;
    private String gear1BadgeUrl;
    private ViewGroup gear1Group;
    private KongInteractivePostPlayModel$KongCollectionItems gear1Item;
    private String gear1ItemNameString;
    private String gear1ItemSoundUrl;
    private float gear1ItemSoundVolume;
    private int gear1SoundId;
    private TextView gear1Timer;
    private TextView gear1Title;
    private ImageView gear2;
    private ImageView gear2Background;
    private String gear2BadgeUrl;
    private ViewGroup gear2Group;
    private KongInteractivePostPlayModel$KongCollectionItems gear2Item;
    private String gear2ItemNameString;
    private String gear2ItemSoundUrl;
    private float gear2ItemSoundVolume;
    private int gear2SoundId;
    private TextView gear2Timer;
    private TextView gear2Title;
    private List<KongInteractivePostPlayModel$KongCollectionItems> gearItemsList;
    private TimeInterpolator interpolator;
    private String itemSelectionHeaderString;
    private String itemSelectionSoundUrl;
    private float itemSelectionSoundVolume;
    private int leftGateWidth;
    private int prepareBattleSoundId;
    private int rightGateWidth;
    private String timerString;
    
    KongGearSelectionScreen(final KongInteractivePostPlayManager kongInteractivePostPlayManager) {
        super(kongInteractivePostPlayManager);
        this.interpolator = (TimeInterpolator)new LinearInterpolator();
    }
    
    void animationEndUIState() {
        this.battleIntroScreen.getBattleIntroContainer().setTranslationX(0.0f);
        this.battleIntroScreen.getBattleIntroCompoundView().setScaleX(1.0f);
        this.battleIntroScreen.getBattleIntroCompoundView().setScaleY(1.0f);
        this.battleIntroScreen.getLeftGate().setTranslationX((float)(-this.leftGateWidth));
        this.battleIntroScreen.getRightGate().setTranslationX((float)this.rightGateWidth);
        this.battleIntroScreen.getKongAvatar().setTranslationX(0.0f);
        this.battleIntroScreen.getOpponentAvatar().setTranslationX((float)this.rightGateWidth);
        this.battleIntroScreen.getVersusHex().setAlpha(0.0f);
        this.battleIntroScreen.getVersusHexSword().setAlpha(0.0f);
        this.gear1Group.setAlpha(1.0f);
        this.gear2Group.setAlpha(1.0f);
        this.gear1Title.setText((CharSequence)this.gear1ItemNameString);
        this.gear2Title.setText((CharSequence)this.gear2ItemNameString);
        ViewUtils.setVisibleOrInvisible((View)this.gear1Timer, false);
        ViewUtils.setVisibleOrInvisible((View)this.gear2Timer, false);
        ViewUtils.setVisibleOrGone((View)this.gear1Group, true);
        ViewUtils.setVisibleOrGone((View)this.gear2Group, true);
        this.gear1Group.setClickable(true);
        this.gear2Group.setClickable(true);
    }
    
    int getBattleLoseTrackId() {
        return this.battleLoseTrackId;
    }
    
    int getBattleLostVideoId() {
        return this.battleLoseVideoId;
    }
    
    int getBattleWinTrackId() {
        return this.battleWinTrackId;
    }
    
    int getBattleWinVideoId() {
        return this.battleWinVideoId;
    }
    
    @Override
    void hide() {
        ViewUtils.setVisibleOrGone((View)this.gear1Group, false);
        ViewUtils.setVisibleOrGone((View)this.gear2Group, false);
    }
    
    @Override
    void initViews(final View view) {
        this.gear1Group = (ViewGroup)view.findViewById(2131690099);
        this.gear2Group = (ViewGroup)view.findViewById(2131690100);
        this.leftGateWidth = DeviceUtils.getScreenWidthInPixels((Context)this.postPlayManager.getActivity());
        this.rightGateWidth = this.leftGateWidth;
        if (this.gear1Group != null) {
            this.gear1 = (ImageView)this.gear1Group.findViewById(2131689570);
            this.gear1Background = (ImageView)this.gear1Group.findViewById(2131689879);
            final PressAnimationFrameLayout pressAnimationFrameLayout = (PressAnimationFrameLayout)this.gear1Group.findViewById(2131689878);
            ViewUtils.setVisibleOrGone((View)this.gear1Background, false);
            this.gear1Title = (TextView)this.gear1Group.findViewById(2131689574);
            this.gear1Timer = (TextView)this.gear1Group.findViewById(2131689880);
            final KongGearSelectionScreen$BattleResultClickListener onClickListener = new KongGearSelectionScreen$BattleResultClickListener(this, 0);
            this.gear1Group.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(pressAnimationFrameLayout.getPressedStateHandler(), (View$OnClickListener)onClickListener));
            this.gear1Timer.setOnClickListener((View$OnClickListener)onClickListener);
        }
        if (this.gear2Group != null) {
            this.gear2 = (ImageView)this.gear2Group.findViewById(2131689570);
            this.gear2Background = (ImageView)this.gear2Group.findViewById(2131689879);
            final PressAnimationFrameLayout pressAnimationFrameLayout2 = (PressAnimationFrameLayout)this.gear2Group.findViewById(2131689878);
            ViewUtils.setVisibleOrGone((View)this.gear2Background, false);
            this.gear2Title = (TextView)this.gear2Group.findViewById(2131689574);
            this.gear2Timer = (TextView)this.gear2Group.findViewById(2131689880);
            final KongGearSelectionScreen$BattleResultClickListener onClickListener2 = new KongGearSelectionScreen$BattleResultClickListener(this, 1);
            this.gear2Group.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(pressAnimationFrameLayout2.getPressedStateHandler(), (View$OnClickListener)onClickListener2));
            this.gear2Timer.setOnClickListener((View$OnClickListener)onClickListener2);
        }
        this.battleIntroScreen = this.postPlayManager.getBattleIntroScreen();
    }
    
    @Override
    void loadPostPlayData(final KongInteractivePostPlayModel kongInteractivePostPlayModel) {
        this.autoPlayInterval = kongInteractivePostPlayModel.getAutoPlayInterval();
        this.timerString = kongInteractivePostPlayModel.getAutoPlayString();
        this.itemSelectionHeaderString = kongInteractivePostPlayModel.getItemSelectionHeaderString();
        this.gearItemsList = kongInteractivePostPlayModel.getCollectionItems();
        if (this.gearItemsList != null && this.gearItemsList.size() >= 2) {
            this.gear1Item = this.gearItemsList.get(0);
            this.gear2Item = this.gearItemsList.get(1);
            this.gear1ItemNameString = this.gear1Item.getItemNameString();
            this.gear2ItemNameString = this.gear2Item.getItemNameString();
            if ("Win".equalsIgnoreCase(this.gear1Item.getItemResult())) {
                this.battleLoseVideoId = this.gear2Item.getVideoId();
                this.battleWinVideoId = this.gear1Item.getVideoId();
                this.battleWinTrackId = this.gear1Item.getTrackId();
                this.battleLoseTrackId = this.gear2Item.getTrackId();
            }
            else {
                this.battleWinVideoId = this.gear2Item.getVideoId();
                this.battleLoseVideoId = this.gear1Item.getVideoId();
                this.battleWinTrackId = this.gear2Item.getTrackId();
                this.battleLoseTrackId = this.gear1Item.getTrackId();
            }
            this.gear1BadgeUrl = this.gear1Item.getBadgeUrl();
            this.gear2BadgeUrl = this.gear2Item.getBadgeUrl();
            final KongInteractivePostPlayModel$KongSound itemSound = this.gear1Item.getItemSound();
            if (itemSound != null) {
                this.gear1ItemSoundUrl = itemSound.getUrl();
                this.gear1ItemSoundVolume = itemSound.getVolume();
            }
            final KongInteractivePostPlayModel$KongSound itemSound2 = this.gear2Item.getItemSound();
            if (itemSound2 != null) {
                this.gear2ItemSoundUrl = itemSound2.getUrl();
                this.gear2ItemSoundVolume = itemSound2.getVolume();
            }
            final KongInteractivePostPlayModel$KongSound itemSelectionSound = kongInteractivePostPlayModel.getItemSelectionSound();
            if (itemSelectionSound != null) {
                this.itemSelectionSoundUrl = itemSelectionSound.getUrl();
                this.itemSelectionSoundVolume = itemSelectionSound.getVolume();
            }
        }
    }
    
    @Override
    void loadResources() {
        ThreadUtils.assertNotOnMain();
        this.postPlayManager.loadImageBitmapFromCache(this.gear1, this.gear1BadgeUrl);
        this.bitmapGear1 = this.postPlayManager.fetchImageFromCache(this.gear1BadgeUrl);
        this.bitmapGear2 = this.postPlayManager.fetchImageFromCache(this.gear2BadgeUrl);
        this.postPlayManager.loadImageBitmapFromCache(this.gear2, this.gear2BadgeUrl);
        this.loadSoundPoolResources();
    }
    
    void loadSoundPoolResources() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager == null) {
            Log.e("KongGearSelectionScreen", "Sound pool manager is null. Cannot load VO resources.");
            return;
        }
        this.gear1SoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.gear1ItemSoundUrl));
        this.gear2SoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.gear2ItemSoundUrl));
        this.prepareBattleSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.itemSelectionSoundUrl));
    }
    
    @Override
    void onResourcesLoaded() {
        if (this.gear1Item != null && this.bitmapGear1 != null) {
            this.gear1Item.setBadgeBitmap(this.bitmapGear1);
        }
        if (this.gear2Item != null && this.bitmapGear2 != null) {
            this.gear2Item.setBadgeBitmap(this.bitmapGear2);
        }
    }
    
    void playGear1Sound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.gear1SoundId, this.gear1ItemSoundVolume);
        }
    }
    
    void playGear2Sound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.gear2SoundId, this.gear2ItemSoundVolume);
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
        ViewUtils.resetImageDrawable(this.gear1);
        ViewUtils.resetImageDrawable(this.gear2);
        this.bitmapGear1 = null;
        this.bitmapGear2 = null;
        if (this.gearItemsList != null) {
            for (final KongInteractivePostPlayModel$KongCollectionItems kongInteractivePostPlayModel$KongCollectionItems : this.gearItemsList) {
                if (kongInteractivePostPlayModel$KongCollectionItems != null) {
                    kongInteractivePostPlayModel$KongCollectionItems.setBadgeBitmap(null);
                }
            }
        }
    }
    
    void setCountdownTimerText() {
        this.getHandler().post((Runnable)new KongGearSelectionScreen$6(this));
    }
    
    @Override
    void start() {
        this.playPrepareBattleSound();
        this.startAnimation();
    }
    
    boolean startAnimation() {
        if (this.postPlayManager == null) {
            Log.e("KongGearSelectionScreen", "Post play manager is null. Cannot start animation.");
            return false;
        }
        if (this.battleIntroScreen == null) {
            Log.e("KongGearSelectionScreen", "Battle intro screen is null. Cannot start animation.");
            return false;
        }
        this.postPlayManager.cancelCurrentAnimation();
        int n = 0;
        this.finishTimeCounterSeconds = this.autoPlayInterval;
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getBattleIntroContainer(), true);
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getUnlockingGearContainer(), false);
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getNextEpisodeContainer(), false);
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getBattleResultAvatar(), false);
        ViewUtils.setVisibleOrGone((View)this.gear1Group, false);
        ViewUtils.setVisibleOrGone((View)this.gear2Group, false);
        ViewUtils.setVisibleOrGone((View)this.battleIntroScreen.getNextEpisodeTitle(), false);
        ViewUtils.setVisibleOrGone((View)this.battleIntroScreen.getNextEpisodeCountdownTimer(), false);
        ViewUtils.setVisibleOrGone((View)this.battleIntroScreen.getBattleTitle(), false);
        ViewUtils.setVisibleOrGone((View)this.battleIntroScreen.getBattleCountdownTimer(), false);
        this.postPlayManager.getBattleIntroCompoundView().setClickable(false);
        this.postPlayManager.getNextEpisodeView().setClickable(false);
        this.gear1Group.setClickable(false);
        this.gear2Group.setClickable(false);
        final AnimatorSet currentAnimation = new AnimatorSet();
        final AnimatorSet set = new AnimatorSet();
        if (this.postPlayManager.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.BATTLE) {
            this.battleIntroScreen.getBattleIntroContainer().setTranslationX(0.0f);
            this.battleIntroScreen.getBattleIntroCompoundView().setScaleX(1.0f);
            this.battleIntroScreen.getBattleIntroCompoundView().setScaleY(1.0f);
            this.battleIntroScreen.getLeftGate().setTranslationX((float)(-this.leftGateWidth));
            this.battleIntroScreen.getRightGate().setTranslationX((float)this.rightGateWidth);
            this.battleIntroScreen.getKongAvatar().setTranslationX((float)(-this.leftGateWidth));
            this.battleIntroScreen.getOpponentAvatar().setTranslationX((float)this.rightGateWidth);
            this.battleIntroScreen.getVersusHex().setAlpha(0.0f);
            this.battleIntroScreen.getVersusHexSword().setAlpha(0.0f);
            final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getLeftGate(), View.TRANSLATION_X, new float[] { 0.0f });
            ofFloat.setInterpolator(this.interpolator);
            final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getRightGate(), View.TRANSLATION_X, new float[] { 0.0f });
            ofFloat2.setInterpolator(this.interpolator);
            final ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getKongAvatar(), View.TRANSLATION_X, new float[] { 0.0f });
            ofFloat3.setInterpolator(this.interpolator);
            final ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getOpponentAvatar(), View.TRANSLATION_X, new float[] { 0.0f });
            ofFloat4.setInterpolator(this.interpolator);
            final ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getVersusHex(), View.ALPHA, new float[] { 1.0f });
            ofFloat5.setStartDelay(666L);
            final ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getVersusHexSword(), View.ALPHA, new float[] { 1.0f });
            ofFloat6.setStartDelay(666L);
            set.playTogether(new Animator[] { ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5, ofFloat6 });
            set.setDuration(666L);
            n = 666;
        }
        else {
            set.playTogether(new Animator[] { ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getBattleIntroContainer(), View.TRANSLATION_X, new float[] { 0.0f }).setDuration(666L), ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getBattleIntroCompoundView(), View.SCALE_Y, new float[] { 1.0f }).setDuration(666L), ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getBattleIntroCompoundView(), View.SCALE_X, new float[] { 1.0f }).setDuration(666L) });
        }
        set.addListener((Animator$AnimatorListener)new KongGearSelectionScreen$1(this));
        final ObjectAnimator setDuration = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getVersusHex(), View.ALPHA, new float[] { 0.0f }).setDuration(333L);
        final ObjectAnimator setDuration2 = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getVersusHexSword(), View.ALPHA, new float[] { 0.0f }).setDuration(333L);
        setDuration.setStartDelay((long)n);
        final ObjectAnimator setDuration3 = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getLeftGate(), View.TRANSLATION_X, new float[] { -this.leftGateWidth }).setDuration(1000L);
        setDuration3.setInterpolator(this.interpolator);
        final ObjectAnimator setDuration4 = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getRightGate(), View.TRANSLATION_X, new float[] { this.rightGateWidth }).setDuration(1000L);
        setDuration4.setInterpolator(this.interpolator);
        final ObjectAnimator setDuration5 = ObjectAnimator.ofFloat((Object)this.battleIntroScreen.getOpponentAvatar(), View.TRANSLATION_X, new float[] { this.rightGateWidth }).setDuration(1000L);
        setDuration5.setInterpolator(this.interpolator);
        setDuration5.addListener((Animator$AnimatorListener)new KongGearSelectionScreen$2(this));
        final ObjectAnimator setDuration6 = ObjectAnimator.ofFloat((Object)this.gear1Group, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
        setDuration6.setInterpolator(this.interpolator);
        setDuration6.addListener((Animator$AnimatorListener)new KongGearSelectionScreen$3(this));
        final ObjectAnimator setDuration7 = ObjectAnimator.ofFloat((Object)this.gear2Group, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
        setDuration7.setInterpolator(this.interpolator);
        setDuration7.addListener((Animator$AnimatorListener)new KongGearSelectionScreen$4(this));
        final AnimatorSet set2 = new AnimatorSet();
        set2.playSequentially(new Animator[] { setDuration6, setDuration7 });
        set2.setStartDelay(1000L);
        currentAnimation.play((Animator)set).before((Animator)setDuration);
        currentAnimation.play((Animator)setDuration).with((Animator)setDuration2).with((Animator)setDuration3).with((Animator)setDuration4).with((Animator)setDuration5);
        currentAnimation.play((Animator)set2).after((Animator)setDuration5);
        currentAnimation.addListener((Animator$AnimatorListener)new KongGearSelectionScreen$5(this));
        this.postPlayManager.setCurrentAnimation((Animator)currentAnimation);
        if (this.postPlayManager.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.BATTLE) {
            currentAnimation.setStartDelay(1500L);
        }
        currentAnimation.start();
        return true;
    }
}
