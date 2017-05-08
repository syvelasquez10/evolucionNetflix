// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import java.util.Collection;
import android.animation.AnimatorSet;
import com.netflix.mediaclient.util.DeviceUtils;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import java.util.Iterator;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.TextView;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;
import java.util.List;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongCollectionItems;
import android.widget.ImageView;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class KongPowerUpScreen extends KongBaseScreen
{
    private static final String TAG = "KongPowerUpScreen";
    private String battleOptInVOUrl;
    private float battleOptInVOVolume;
    private int battleTitleSoundId;
    private String battleTitleVOUrl;
    private float battleTitleVOVolume;
    private NetflixActivity context;
    private ImageView emptyBadgeImage;
    private KongInteractivePostPlayModel$KongCollectionItems gear1Item;
    private String gear1ItemSoundUrl;
    private float gear1ItemSoundVolume;
    private int gear1SoundId;
    private ArrayList<ImageView> gearItemsImageViewList;
    private List<KongInteractivePostPlayModel$KongCollectionItems> gearItemsList;
    private String kongAvatarImageUrl;
    private int mBattleWhatsNextSoundId;
    private int nextEpisodeVideoId;
    private ImageView powerUpAvatarImage;
    private ViewGroup powerUpContainer;
    private boolean powerUpContainerPopulated;
    private FrameLayout$LayoutParams powerUpParams;
    private String powerUpTitle;
    private TextView powerUpTitleView;
    private ViewGroup powerUpViewsContainer;
    private ImageView smAvatarImage;
    private String smAvatarImageUrl;
    private String unlockEmptyBadgeUrl;
    private String unlockVOUrl;
    private float unlockVOVolume;
    private int unlockedSoundId;
    private ImageView whiteFlareImage;
    private String whiteFlareImageUrl;
    
    KongPowerUpScreen(final KongInteractivePostPlayManager kongInteractivePostPlayManager) {
        super(kongInteractivePostPlayManager);
    }
    
    @Override
    void hide() {
        ViewUtils.setVisibleOrGone((View)this.powerUpContainer, false);
    }
    
    @Override
    void initViews(final View view) {
        this.powerUpViewsContainer = (ViewGroup)view;
        this.context = this.postPlayManager.getActivity();
        this.powerUpContainer = (ViewGroup)this.powerUpViewsContainer.findViewById(2131624472);
        this.powerUpParams = new FrameLayout$LayoutParams(-2, this.context.getResources().getDimensionPixelSize(2131296358), 17);
        this.powerUpTitleView = (TextView)this.powerUpViewsContainer.findViewById(2131624471);
        (this.smAvatarImage = new ImageView((Context)this.postPlayManager.getActivity())).setLayoutParams((ViewGroup$LayoutParams)this.powerUpParams);
        (this.whiteFlareImage = new ImageView((Context)this.context)).setLayoutParams((ViewGroup$LayoutParams)this.powerUpParams);
        (this.emptyBadgeImage = new ImageView((Context)this.context)).setLayoutParams((ViewGroup$LayoutParams)this.powerUpParams);
        if (this.gearItemsList != null) {
            this.gearItemsImageViewList = new ArrayList<ImageView>();
            for (final KongInteractivePostPlayModel$KongCollectionItems kongInteractivePostPlayModel$KongCollectionItems : this.gearItemsList) {
                final ImageView imageView = new ImageView((Context)this.context);
                imageView.setLayoutParams((ViewGroup$LayoutParams)this.powerUpParams);
                this.gearItemsImageViewList.add(imageView);
            }
        }
        (this.powerUpAvatarImage = new ImageView((Context)this.context)).setLayoutParams((ViewGroup$LayoutParams)this.powerUpParams);
    }
    
    @Override
    void loadPostPlayData(final KongInteractivePostPlayModel kongInteractivePostPlayModel) {
        this.nextEpisodeVideoId = kongInteractivePostPlayModel.getNextEpisodeVideoId();
        this.smAvatarImageUrl = kongInteractivePostPlayModel.getSmAvatarImageUrl();
        this.whiteFlareImageUrl = kongInteractivePostPlayModel.getWhiteFlareImageUrl();
        this.unlockEmptyBadgeUrl = kongInteractivePostPlayModel.getUnlockEmptyBadgeImageUrl();
        this.kongAvatarImageUrl = kongInteractivePostPlayModel.getAvatarImageUrl();
        this.gearItemsList = kongInteractivePostPlayModel.getCollectionItems();
        if (this.gearItemsList != null && this.gearItemsList.size() >= 2) {
            this.gear1Item = this.gearItemsList.get(0);
            this.powerUpTitle = this.gear1Item.getItemNameString();
            final KongInteractivePostPlayModel$KongSound itemSound = this.gear1Item.getItemSound();
            if (itemSound != null) {
                this.gear1ItemSoundUrl = itemSound.getUrl();
                this.gear1ItemSoundVolume = itemSound.getVolume();
            }
        }
        final KongInteractivePostPlayModel$KongSound battleTitleSound = kongInteractivePostPlayModel.getBattleTitleSound();
        if (battleTitleSound != null) {
            this.battleTitleVOUrl = battleTitleSound.getUrl();
            this.battleTitleVOVolume = battleTitleSound.getVolume();
        }
        final KongInteractivePostPlayModel$KongSound unlockVOSound = kongInteractivePostPlayModel.getUnlockVOSound();
        if (unlockVOSound != null) {
            this.unlockVOUrl = unlockVOSound.getUrl();
            this.unlockVOVolume = unlockVOSound.getVolume();
        }
    }
    
    @Override
    void loadResources() {
        ThreadUtils.assertNotOnMain();
        this.postPlayManager.loadImageBitmapFromCache(this.smAvatarImage, this.smAvatarImageUrl);
        if (this.gearItemsList != null) {
            for (int i = 0; i < this.gearItemsList.size(); ++i) {
                this.postPlayManager.loadImageBitmapFromCache(this.gearItemsImageViewList.get(i), this.gearItemsList.get(i).getBadgeUrl());
            }
        }
        this.postPlayManager.loadImageBitmapFromCache(this.whiteFlareImage, this.whiteFlareImageUrl);
        this.postPlayManager.loadImageBitmapFromCache(this.emptyBadgeImage, this.unlockEmptyBadgeUrl);
        this.postPlayManager.loadImageBitmapFromCache(this.powerUpAvatarImage, this.kongAvatarImageUrl);
        this.loadSoundPoolResources();
    }
    
    void loadSoundPoolResources() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager == null) {
            Log.e("KongPowerUpScreen", "Sound pool manager is null. Cannot load VO resources.");
            return;
        }
        this.gear1SoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.gear1ItemSoundUrl));
        this.battleTitleSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.battleTitleVOUrl));
        this.unlockedSoundId = soundPoolManager.loadSoundPoolVo(this.postPlayManager.getResourceToLocalCacheFileMap(this.unlockVOUrl));
    }
    
    @Override
    void onResourcesLoaded() {
        this.powerUpTitleView.setText((CharSequence)this.powerUpTitle);
        if (!this.powerUpContainerPopulated) {
            this.powerUpContainer.addView((View)this.smAvatarImage);
        }
        if (this.gearItemsList != null) {
            for (int i = 0; i < this.gearItemsList.size(); ++i) {
                final ImageView imageView = this.gearItemsImageViewList.get(i);
                imageView.setAlpha(0.0f);
                if (!this.powerUpContainerPopulated && i > 0) {
                    this.powerUpContainer.addView((View)imageView);
                }
            }
        }
        if (!this.powerUpContainerPopulated) {
            this.powerUpContainer.addView((View)this.whiteFlareImage);
        }
        if (!this.powerUpContainerPopulated) {
            this.powerUpContainer.addView((View)this.emptyBadgeImage);
        }
        if (!this.powerUpContainerPopulated) {
            this.powerUpContainer.addView((View)this.powerUpAvatarImage);
        }
        this.powerUpContainerPopulated = true;
    }
    
    void playBattleTitleSound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.battleTitleSoundId, this.battleTitleVOVolume);
        }
    }
    
    void playGear1Sound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.gear1SoundId, this.gear1ItemSoundVolume);
        }
    }
    
    void playUnlockedSound() {
        final KongSoundPoolManager soundPoolManager = this.postPlayManager.getSoundPoolManager();
        if (soundPoolManager != null) {
            soundPoolManager.playSoundPoolId(this.unlockedSoundId, this.unlockVOVolume);
        }
    }
    
    @Override
    public void releaseBitmapResources() {
        ViewUtils.resetImageDrawable(this.smAvatarImage);
        if (this.gearItemsList != null) {
            for (int i = 0; i < this.gearItemsList.size(); ++i) {
                ViewUtils.resetImageDrawable(this.gearItemsImageViewList.get(i));
            }
        }
        ViewUtils.resetImageDrawable(this.whiteFlareImage);
        ViewUtils.resetImageDrawable(this.emptyBadgeImage);
        ViewUtils.resetImageDrawable(this.powerUpAvatarImage);
    }
    
    @Override
    void start() {
        this.playUnlockedSound();
        this.handler.postDelayed((Runnable)new KongPowerUpScreen$1(this), 1500L);
    }
    
    boolean startAnimation() {
        if (this.postPlayManager == null) {
            Log.e("KongPowerUpScreen", "Post play manager is null. Cannot start unlock animation.");
            return false;
        }
        this.postPlayManager.cancelCurrentAnimation();
        this.smAvatarImage.setAlpha(0.0f);
        this.whiteFlareImage.setAlpha(0.0f);
        this.emptyBadgeImage.setAlpha(0.0f);
        this.powerUpAvatarImage.setAlpha(0.0f);
        this.powerUpTitleView.setAlpha(0.0f);
        ViewUtils.setVisibleOrGone((View)this.powerUpTitleView, true);
        final ArrayList<ObjectAnimator> list = new ArrayList<ObjectAnimator>();
        if (this.nextEpisodeVideoId <= 0) {
            ViewUtils.setVisibleOrGone((View)this.smAvatarImage, false);
        }
        final ObjectAnimator setDuration = ObjectAnimator.ofFloat((Object)this.smAvatarImage, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
        final ObjectAnimator setDuration2 = ObjectAnimator.ofFloat((Object)this.powerUpTitleView, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
        final ObjectAnimator setDuration3 = ObjectAnimator.ofFloat((Object)this.smAvatarImage, View.ALPHA, new float[] { 0.0f }).setDuration(1000L);
        final ObjectAnimator setDuration4 = ObjectAnimator.ofFloat((Object)this.powerUpAvatarImage, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
        final ObjectAnimator setDuration5 = ObjectAnimator.ofFloat((Object)this.powerUpAvatarImage, View.ALPHA, new float[] { 0.0f }).setDuration(1000L);
        final int dimensionPixelSize = this.getActivity().getResources().getDimensionPixelSize(2131296376);
        for (int size = this.gearItemsImageViewList.size(), i = 1; i < size; ++i) {
            final ImageView imageView = this.gearItemsImageViewList.get(size - i);
            final int n = i / 2;
            int n2;
            if (i % 2 == 0) {
                n2 = -1;
            }
            else {
                n2 = 1;
            }
            if ((size - 1) % 2 == 0 || i > 1) {
                imageView.setTranslationX((float)(n2 * (dimensionPixelSize * (n + 1))));
                imageView.setAlpha(0.0f);
            }
            list.add(ObjectAnimator.ofFloat((Object)imageView, View.TRANSLATION_X, new float[] { 0.0f }).setDuration(1000L));
        }
        ViewUtils.setVisibleOrGone((View)this.powerUpViewsContainer, true);
        ViewUtils.setVisibleOrGone((View)this.powerUpContainer, true);
        final float n3 = DeviceUtils.getScreenHeightInPixels((Context)this.getActivity()) / this.getActivity().getResources().getDimension(2131296358) * 0.9f;
        final ObjectAnimator setDuration6 = ObjectAnimator.ofFloat((Object)this.whiteFlareImage, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
        this.powerUpAvatarImage.setScaleX(0.8f);
        this.powerUpAvatarImage.setScaleY(0.8f);
        final ObjectAnimator setDuration7 = ObjectAnimator.ofFloat((Object)this.powerUpAvatarImage, View.SCALE_X, new float[] { n3 }).setDuration(666L);
        final ObjectAnimator setDuration8 = ObjectAnimator.ofFloat((Object)this.powerUpAvatarImage, View.SCALE_Y, new float[] { n3 }).setDuration(666L);
        final ObjectAnimator setDuration9 = ObjectAnimator.ofFloat((Object)this.emptyBadgeImage, View.ALPHA, new float[] { 1.0f }).setDuration(1000L);
        final ObjectAnimator setDuration10 = ObjectAnimator.ofFloat((Object)this.emptyBadgeImage, View.ALPHA, new float[] { 0.0f }).setDuration(1000L);
        this.playGear1Sound();
        final AnimatorSet set = new AnimatorSet();
        set.playTogether((Collection)list);
        set.addListener((Animator$AnimatorListener)new KongPowerUpScreen$2(this));
        final AnimatorSet set2 = new AnimatorSet();
        set2.play((Animator)setDuration6);
        set2.addListener((Animator$AnimatorListener)new KongPowerUpScreen$3(this));
        final AnimatorSet currentAnimation = new AnimatorSet();
        if (this.nextEpisodeVideoId > 0) {
            currentAnimation.play((Animator)setDuration).with((Animator)setDuration2).before((Animator)set);
            currentAnimation.play((Animator)set).with((Animator)setDuration3);
        }
        else {
            currentAnimation.play((Animator)set).with((Animator)setDuration2).with((Animator)setDuration3);
        }
        currentAnimation.play((Animator)set2).with((Animator)setDuration9).after((Animator)set);
        currentAnimation.play((Animator)setDuration4).after((Animator)set2);
        currentAnimation.play((Animator)setDuration7).with((Animator)setDuration8).with((Animator)setDuration10).after((Animator)setDuration4);
        currentAnimation.play((Animator)setDuration5).after((Animator)setDuration7);
        currentAnimation.addListener((Animator$AnimatorListener)new KongPowerUpScreen$4(this));
        this.postPlayManager.setCurrentAnimation((Animator)currentAnimation);
        currentAnimation.start();
        return true;
    }
}
