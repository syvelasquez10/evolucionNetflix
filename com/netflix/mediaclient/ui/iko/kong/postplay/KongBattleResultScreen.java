// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.animation.LinearInterpolator;
import android.animation.TimeInterpolator;
import android.widget.ImageView;

public class KongBattleResultScreen extends KongBaseScreen
{
    private static final String TAG = "KongBattleResultScreen";
    private String battleAgainSoundUrl;
    private float battleAgainSoundVolume;
    private ImageView battleResultAvatar;
    private int battleResultAvatarSoundId;
    private String battleResultAvatarSoundUrl;
    private float battleResultAvatarSoundVolume;
    private int battleResultBattleAgainSoundId;
    private String battleResultDataSoundUrl;
    private float battleResultDataSoundVolume;
    private int battleResultSoundId;
    private boolean hasWatchedAllBattlesForEpisode;
    private TimeInterpolator interpolator;
    private int leftGateWidth;
    private String resultAvatarImageUrl;
    
    KongBattleResultScreen(final KongInteractivePostPlayManager kongInteractivePostPlayManager) {
        super(kongInteractivePostPlayManager);
        this.interpolator = (TimeInterpolator)new LinearInterpolator();
    }
    
    @Override
    void hide() {
        ViewUtils.setVisibleOrGone((View)this.battleResultAvatar, false);
    }
    
    @Override
    void initViews(final View view) {
        this.leftGateWidth = DeviceUtils.getScreenWidthInPixels((Context)this.postPlayManager.getActivity()) / 2;
        this.battleResultAvatar = (ImageView)view.findViewById(2131755669);
    }
    
    @Override
    void loadPostPlayData(final KongInteractivePostPlayModel kongInteractivePostPlayModel) {
        this.resultAvatarImageUrl = kongInteractivePostPlayModel.getResultAvatarImageUrl();
        final KongInteractivePostPlayModel$KongSound resultDataSound = kongInteractivePostPlayModel.getResultDataSound();
        if (resultDataSound != null) {
            this.battleResultDataSoundUrl = resultDataSound.getUrl();
            this.battleResultDataSoundVolume = resultDataSound.getVolume();
        }
        final KongInteractivePostPlayModel$KongSound resultBattleSound = kongInteractivePostPlayModel.getResultBattleSound();
        if (resultBattleSound != null) {
            this.battleAgainSoundUrl = resultBattleSound.getUrl();
            this.battleAgainSoundVolume = resultBattleSound.getVolume();
        }
        final KongInteractivePostPlayModel$KongSound resultAvatarSound = kongInteractivePostPlayModel.getResultAvatarSound();
        if (resultAvatarSound != null) {
            this.battleResultAvatarSoundUrl = resultAvatarSound.getUrl();
            this.battleResultAvatarSoundVolume = resultAvatarSound.getVolume();
        }
        if (this.postPlayManager.getPostPlayState() == KongInteractivePostPlayManager$POST_PLAY_STATE.RESULT) {
            this.hasWatchedAllBattlesForEpisode = kongInteractivePostPlayModel.hasWatchedAllBattleVideosForEpisode();
        }
    }
    
    @Override
    void loadResources() {
        ThreadUtils.assertNotOnMain();
        this.postPlayManager.loadImageBitmapFromCache(this.battleResultAvatar, this.resultAvatarImageUrl);
        this.loadSoundPoolResources();
    }
    
    void loadSoundPoolResources() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager == null) {
            Log.e("KongBattleResultScreen", "Sound pool manager is null. Cannot load VO resources.");
            return;
        }
        this.battleResultSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.battleResultDataSoundUrl));
        this.battleResultBattleAgainSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.battleAgainSoundUrl));
        this.battleResultAvatarSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.battleResultAvatarSoundUrl));
    }
    
    @Override
    void onResourcesLoaded() {
    }
    
    void playAvatarSound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.battleResultAvatarSoundId, this.battleResultAvatarSoundVolume);
        }
    }
    
    void playBattleAgainSound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.battleResultBattleAgainSoundId, this.battleAgainSoundVolume);
        }
    }
    
    void playBattleResultSound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.battleResultSoundId, this.battleResultDataSoundVolume);
        }
    }
    
    @Override
    public void releaseBitmapResources() {
        ViewUtils.resetImageDrawable(this.battleResultAvatar);
    }
    
    @Override
    void start() {
        this.startAnimation();
    }
    
    boolean startAnimation() {
        this.postPlayManager.cancelCurrentAnimation();
        this.playAvatarSound();
        this.playBattleResultSound();
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getBattleIntroContainer(), false);
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getNextEpisodeContainer(), false);
        ViewUtils.setVisibleOrGoneAnimation((View)this.battleResultAvatar, true, true);
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getUnlockingGearContainer(), false);
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getGear1Group(), false);
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getGear2Group(), false);
        this.postPlayManager.getBattleIntroCompoundView().setClickable(false);
        this.postPlayManager.getNextEpisodeView().setClickable(false);
        this.postPlayManager.getGear1Group().setClickable(false);
        this.postPlayManager.getGear2Group().setClickable(false);
        ObjectAnimator currentAnimation;
        if (this.postPlayManager.shouldHideAnimation()) {
            this.battleResultAvatar.setAlpha(0.0f);
            currentAnimation = ObjectAnimator.ofFloat((Object)this.battleResultAvatar, View.ALPHA, new float[] { 1.0f });
        }
        else {
            this.battleResultAvatar.setTranslationX((float)(-this.leftGateWidth));
            currentAnimation = ObjectAnimator.ofFloat((Object)this.battleResultAvatar, View.TRANSLATION_X, new float[] { 0.0f });
        }
        currentAnimation.setDuration(666L);
        currentAnimation.setInterpolator(this.interpolator);
        currentAnimation.addListener((Animator$AnimatorListener)new KongBattleResultScreen$1(this));
        this.postPlayManager.setCurrentAnimation((Animator)currentAnimation);
        currentAnimation.start();
        return true;
    }
}
