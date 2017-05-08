// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.BounceInterpolator;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.util.OnAnimationEndListener;
import java.util.List;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongCollectionItems;

public class KongUnlockScreen extends KongBaseScreen
{
    private static final String TAG = "KongUnlockScreen";
    private int battleTitleSoundId;
    private String battleTitleVOUrl;
    private float battleTitleVOVolume;
    private KongInteractivePostPlayModel$KongCollectionItems gear1Item;
    private String gear1ItemSoundUrl;
    private float gear1ItemSoundVolume;
    private int gear1SoundId;
    private KongInteractivePostPlayModel$KongCollectionItems gear2Item;
    private String gear2ItemSoundUrl;
    private float gear2ItemSoundVolume;
    private int gear2SoundId;
    private List<KongInteractivePostPlayModel$KongCollectionItems> gearItemsList;
    private boolean isFirstGearUnlockingComplete;
    private String sunburstImageUrl;
    private String unlockVOUrl;
    private float unlockVOVolume;
    private OnAnimationEndListener unlockedAnimationEndListener;
    private int unlockedSoundId;
    private ImageView unlockingGear;
    private ImageView unlockingGearBackground;
    private ViewGroup unlockingGearContainer;
    private TextView unlockingGearTimer;
    private TextView unlockingGearTitle;
    private int zapSfxSoundId;
    private String zapSoundSfxUrl;
    private float zapSoundSfxVolume;
    
    KongUnlockScreen(final KongInteractivePostPlayManager kongInteractivePostPlayManager) {
        super(kongInteractivePostPlayManager);
        this.unlockedAnimationEndListener = new KongUnlockScreen$2(this);
    }
    
    private void playBattleTitleSound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.battleTitleSoundId, this.battleTitleVOVolume);
        }
    }
    
    private void playGear1Sound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.gear1SoundId, this.gear1ItemSoundVolume);
        }
    }
    
    private void playGear2Sound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.gear2SoundId, this.gear2ItemSoundVolume);
        }
    }
    
    private void playUnlockedSound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.unlockedSoundId, this.unlockVOVolume);
        }
    }
    
    private void playZapSound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.zapSfxSoundId, this.zapSoundSfxVolume);
        }
    }
    
    @Override
    void hide() {
        ViewUtils.setVisibleOrGone((View)this.unlockingGearContainer, false);
    }
    
    @Override
    void initViews(final View view) {
        this.unlockingGearContainer = (ViewGroup)view;
        if (view != null) {
            this.unlockingGear = (ImageView)view.findViewById(2131755117);
            this.unlockingGearBackground = (ImageView)view.findViewById(2131755437);
            this.unlockingGearTitle = (TextView)view.findViewById(2131755121);
            ViewUtils.setVisibleOrGone((View)(this.unlockingGearTimer = (TextView)view.findViewById(2131755438)), false);
        }
    }
    
    @Override
    void loadPostPlayData(final KongInteractivePostPlayModel kongInteractivePostPlayModel) {
        this.sunburstImageUrl = kongInteractivePostPlayModel.getSunburstImageUrl();
        this.gearItemsList = kongInteractivePostPlayModel.getCollectionItems();
        if (this.gearItemsList != null && this.gearItemsList.size() >= 2) {
            this.gear1Item = this.gearItemsList.get(0);
            this.gear2Item = this.gearItemsList.get(1);
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
            final KongInteractivePostPlayModel$KongSound unlockVOSound = kongInteractivePostPlayModel.getUnlockVOSound();
            if (unlockVOSound != null) {
                this.unlockVOUrl = unlockVOSound.getUrl();
                this.unlockVOVolume = unlockVOSound.getVolume();
            }
            final KongInteractivePostPlayModel$KongSound battleTitleSound = kongInteractivePostPlayModel.getBattleTitleSound();
            if (battleTitleSound != null) {
                this.battleTitleVOUrl = battleTitleSound.getUrl();
                this.battleTitleVOVolume = battleTitleSound.getVolume();
            }
            final KongInteractivePostPlayModel$KongSound itemUnlockSfxSound = kongInteractivePostPlayModel.getItemUnlockSfxSound();
            if (itemUnlockSfxSound != null) {
                this.zapSoundSfxUrl = itemUnlockSfxSound.getUrl();
                this.zapSoundSfxVolume = itemUnlockSfxSound.getVolume();
            }
        }
    }
    
    @Override
    void loadResources() {
        ThreadUtils.assertNotOnMain();
        this.postPlayManager.loadImageBitmapFromCache(this.unlockingGearBackground, this.sunburstImageUrl);
        this.loadSoundPoolResources();
    }
    
    void loadSoundPoolResources() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager == null) {
            Log.e("KongUnlockScreen", "Sound pool manager is null. Cannot load VO resources.");
            return;
        }
        this.gear1SoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.gear1ItemSoundUrl));
        this.gear2SoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.gear2ItemSoundUrl));
        this.unlockedSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.unlockVOUrl));
        this.zapSfxSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.zapSoundSfxUrl));
        this.battleTitleSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.battleTitleVOUrl));
    }
    
    @Override
    void onResourcesLoaded() {
    }
    
    @Override
    public void releaseBitmapResources() {
        ViewUtils.resetImageDrawable(this.unlockingGear);
        ViewUtils.resetImageDrawable(this.unlockingGearBackground);
    }
    
    @Override
    void start() {
        this.isFirstGearUnlockingComplete = false;
        this.playUnlockedSound();
        this.getHandler().postDelayed((Runnable)new KongUnlockScreen$1(this), 1500L);
    }
    
    boolean startAnimation(final KongInteractivePostPlayModel$KongCollectionItems kongInteractivePostPlayModel$KongCollectionItems) {
        if (this.postPlayManager == null) {
            Log.e("KongUnlockScreen", "Post play manager is null. Cannot start unlock animation.");
            return false;
        }
        if (kongInteractivePostPlayModel$KongCollectionItems == null) {
            Log.e("KongUnlockScreen", "Request to show unlocking animation on null collection item.");
            return false;
        }
        this.postPlayManager.cancelCurrentAnimation();
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getBattleIntroContainer(), false);
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getNextEpisodeContainer(), false);
        ViewUtils.setVisibleOrGone((View)this.postPlayManager.getBattleResultAvatar(), false);
        ViewUtils.setVisibleOrGoneAnimation((View)this.unlockingGearContainer, true, true);
        this.postPlayManager.getBattleIntroCompoundView().setClickable(false);
        this.postPlayManager.getNextEpisodeView().setClickable(false);
        this.postPlayManager.getGear1Group().setClickable(false);
        this.postPlayManager.getGear2Group().setClickable(false);
        this.unlockingGear.setAlpha(0.0f);
        this.unlockingGearBackground.setAlpha(0.0f);
        this.unlockingGear.setScaleX(0.8f);
        this.unlockingGear.setScaleY(0.8f);
        if (kongInteractivePostPlayModel$KongCollectionItems != null) {
            this.unlockingGear.setImageBitmap(kongInteractivePostPlayModel$KongCollectionItems.getBadgeBitmap());
            this.unlockingGearTitle.setText((CharSequence)kongInteractivePostPlayModel$KongCollectionItems.getItemNameString());
        }
        this.unlockingGearTitle.setAlpha(0.0f);
        final ObjectAnimator setDuration = ObjectAnimator.ofFloat((Object)this.unlockingGearTitle, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
        final AnimatorSet set = new AnimatorSet();
        set.play((Animator)ObjectAnimator.ofFloat((Object)this.unlockingGear, View.SCALE_X, new float[] { 1.0f })).with((Animator)ObjectAnimator.ofFloat((Object)this.unlockingGear, View.SCALE_Y, new float[] { 1.0f }));
        set.setInterpolator((TimeInterpolator)new BounceInterpolator());
        set.setDuration(500L);
        final ObjectAnimator setDuration2 = ObjectAnimator.ofFloat((Object)this.unlockingGearBackground, View.ALPHA, new float[] { 1.0f }).setDuration(100L);
        final ObjectAnimator setDuration3 = ObjectAnimator.ofFloat((Object)this.unlockingGearBackground, View.ROTATION, new float[] { 0.0f, 90.0f }).setDuration(2000L);
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this.unlockingGear, View.ALPHA, new float[] { 1.0f });
        final ObjectAnimator setDuration4 = ObjectAnimator.ofFloat((Object)this.unlockingGearTitle, View.ALPHA, new float[] { 0.0f }).setDuration(500L);
        final ObjectAnimator setDuration5 = ObjectAnimator.ofFloat((Object)this.unlockingGear, View.SCALE_X, new float[] { 0.8f }).setDuration(1000L);
        final ObjectAnimator setDuration6 = ObjectAnimator.ofFloat((Object)this.unlockingGear, View.SCALE_Y, new float[] { 0.8f }).setDuration(1000L);
        final ObjectAnimator setDuration7 = ObjectAnimator.ofFloat((Object)this.unlockingGear, View.ALPHA, new float[] { 0.0f }).setDuration(1000L);
        final ObjectAnimator setDuration8 = ObjectAnimator.ofFloat((Object)this.unlockingGearBackground, View.ALPHA, new float[] { 0.0f }).setDuration(1000L);
        if (this.isFirstGearUnlockingComplete) {
            this.playGear2Sound();
        }
        else {
            this.playGear1Sound();
        }
        final AnimatorSet currentAnimation = new AnimatorSet();
        currentAnimation.play((Animator)set).with((Animator)ofFloat).with((Animator)setDuration).with((Animator)setDuration2);
        currentAnimation.play((Animator)setDuration5).with((Animator)setDuration6).with((Animator)setDuration8).with((Animator)setDuration7).with((Animator)setDuration4).after((Animator)setDuration);
        currentAnimation.addListener((Animator$AnimatorListener)this.unlockedAnimationEndListener);
        currentAnimation.playTogether(new Animator[] { setDuration3 });
        this.postPlayManager.setCurrentAnimation((Animator)currentAnimation);
        currentAnimation.start();
        return true;
    }
}
