// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.app.Status;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;

class KubrickKidsCharacterDetailsFrag$KubrickKidsCharacterDetailsViewGroup extends KubrickVideoDetailsViewGroup
{
    public static final float CHARACTER_IMAGE_SIZE_MULTIPLIER = 0.6f;
    AdvancedImageView characterView;
    ImageView playView;
    final /* synthetic */ KubrickKidsCharacterDetailsFrag this$0;
    
    public KubrickKidsCharacterDetailsFrag$KubrickKidsCharacterDetailsViewGroup(final KubrickKidsCharacterDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.setupViews();
        this.setupPlayButton();
    }
    
    private Drawable getBackgroundResource() {
        switch (this.this$0.kidsColor) {
            default: {
                return this.this$0.getActivity().getResources().getDrawable(2130837577);
            }
            case 2131558504: {
                return this.this$0.getActivity().getResources().getDrawable(2130837581);
            }
            case 2131558497: {
                return this.this$0.getActivity().getResources().getDrawable(2130837577);
            }
            case 2131558499: {
                return this.this$0.getActivity().getResources().getDrawable(2130837578);
            }
            case 2131558501: {
                return this.this$0.getActivity().getResources().getDrawable(2130837580);
            }
            case 2131558500: {
                return this.this$0.getActivity().getResources().getDrawable(2130837579);
            }
        }
    }
    
    private void setupPlayButton() {
        if (this.playView == null) {
            return;
        }
        int imageResource = 0;
        switch (this.this$0.kidsColor) {
            default: {
                imageResource = 2130837586;
                break;
            }
            case 2131558504: {
                imageResource = 2130837590;
                break;
            }
            case 2131558497: {
                imageResource = 2130837585;
                break;
            }
            case 2131558499: {
                imageResource = 2130837587;
                break;
            }
            case 2131558501: {
                imageResource = 2130837589;
                break;
            }
            case 2131558500: {
                imageResource = 2130837588;
                break;
            }
        }
        this.playView.setImageResource(imageResource);
    }
    
    private void setupViews() {
        final int detailsPageContentWidth = KidsUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity());
        this.getBackgroundImage().setBackground(this.getBackgroundResource());
        this.getBackgroundImage().getLayoutParams().width = detailsPageContentWidth;
        int height;
        if (DeviceUtils.isLandscape(this.getContext())) {
            height = (int)(DeviceUtils.getScreenHeightInPixels((Context)this.this$0.getActivity()) * 0.7);
        }
        else {
            height = (int)(detailsPageContentWidth * 0.5625f);
        }
        this.getBackgroundImage().getLayoutParams().height = height;
        final ViewGroup$LayoutParams layoutParams = this.characterView.getLayoutParams();
        this.characterView.getLayoutParams().width = height;
        layoutParams.height = height;
        this.getHeroImage().getLayoutParams().width = (int)(detailsPageContentWidth * 0.6f);
        this.getHeroImage().getLayoutParams().height = (int)(height * 0.6f);
    }
    
    private void updateTitle(final KidsCharacterDetails kidsCharacterDetails) {
        if (kidsCharacterDetails == null || this.title == null) {
            return;
        }
        this.title.setText((CharSequence)kidsCharacterDetails.getCharacterName());
        this.title.setVisibility(0);
        this.titleImg.setVisibility(8);
    }
    
    @Override
    protected void alignViews() {
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.characterView = (AdvancedImageView)this.findViewById(2131624204);
        this.playView = (ImageView)this.findViewById(2131624208);
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903114;
    }
    
    protected void updateBoxart(final KidsCharacterDetails kidsCharacterDetails) {
        if (kidsCharacterDetails == null) {
            return;
        }
        final String storyUrl = kidsCharacterDetails.getStoryUrl();
        NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.horzDispImg, storyUrl, IClientLogging$AssetType.boxArt, String.format(this.getResources().getString(2131165357), this.this$0.kidsCharacterDetails.getTitle()), BrowseExperience.getImageLoaderConfig(), true);
        this.horzDispImg.setTag((Object)storyUrl);
    }
    
    public void updateCharacterDetails(final KidsCharacterDetails kidsCharacterDetails) {
        this.updateBoxart(kidsCharacterDetails);
        this.updateCharacterImage();
        this.updateTitle(kidsCharacterDetails);
    }
    
    protected void updateCharacterImage() {
        if (this.characterView != null) {
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.characterView, this.this$0.kidsCharacterDetails.getSquareUrl(), IClientLogging$AssetType.boxArt, String.format(this.getResources().getString(2131165357), this.this$0.kidsCharacterDetails.getTitle()), BrowseExperience.getImageLoaderConfig(), true);
        }
    }
}
