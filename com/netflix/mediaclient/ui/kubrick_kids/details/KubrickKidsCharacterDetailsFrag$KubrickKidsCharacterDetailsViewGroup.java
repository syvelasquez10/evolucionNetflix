// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.ShowDetailsFrag$ShowDetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
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
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;

class KubrickKidsCharacterDetailsFrag$KubrickKidsCharacterDetailsViewGroup extends KubrickVideoDetailsViewGroup
{
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
                return this.this$0.getActivity().getResources().getDrawable(2130837566);
            }
            case 2131230892: {
                return this.this$0.getActivity().getResources().getDrawable(2130837571);
            }
            case 2131230896: {
                return this.this$0.getActivity().getResources().getDrawable(2130837566);
            }
            case 2131230893: {
                return this.this$0.getActivity().getResources().getDrawable(2130837568);
            }
            case 2131230895: {
                return this.this$0.getActivity().getResources().getDrawable(2130837570);
            }
            case 2131230891: {
                return this.this$0.getActivity().getResources().getDrawable(2130837569);
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
                imageResource = 2130837576;
                break;
            }
            case 2131230892: {
                imageResource = 2130837580;
                break;
            }
            case 2131230896: {
                imageResource = 2130837575;
                break;
            }
            case 2131230893: {
                imageResource = 2130837577;
                break;
            }
            case 2131230895: {
                imageResource = 2130837579;
                break;
            }
            case 2131230891: {
                imageResource = 2130837578;
                break;
            }
        }
        this.playView.setImageResource(imageResource);
    }
    
    private void setupViews() {
        final int detailsPageContentWidth = KubrickUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity());
        final int n = (int)(detailsPageContentWidth * 0.5625f);
        this.getBackgroundImage().getLayoutParams().width = detailsPageContentWidth;
        int height = n;
        if (DeviceUtils.getScreenAspectRatio(this.getContext()) > 1.6f) {
            float n2;
            if (DeviceUtils.isLandscape(this.getContext())) {
                n2 = n * 0.75f;
            }
            else {
                n2 = n * 1.1f;
            }
            height = (int)n2;
        }
        this.getBackgroundImage().getLayoutParams().height = height;
        final ViewGroup$LayoutParams layoutParams = this.characterView.getLayoutParams();
        this.characterView.getLayoutParams().width = height;
        layoutParams.height = height;
        this.getBackgroundImage().setBackground(this.getBackgroundResource());
        this.getHeroImage().getLayoutParams().width = detailsPageContentWidth / 2;
        this.getHeroImage().getLayoutParams().height = height / 2;
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
        this.characterView = (AdvancedImageView)this.findViewById(2131427530);
        this.playView = (ImageView)this.findViewById(2131427534);
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903099;
    }
    
    protected void updateBoxart(final KidsCharacterDetails kidsCharacterDetails) {
        if (kidsCharacterDetails == null) {
            return;
        }
        final String storyUrl = kidsCharacterDetails.getStoryUrl();
        NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.horzDispImg, storyUrl, IClientLogging$AssetType.boxArt, String.format(this.getResources().getString(2131493174), this.this$0.kidsCharacterDetails.getTitle()), BrowseExperience.getImageLoaderConfig(), true);
        this.horzDispImg.setTag((Object)storyUrl);
    }
    
    public void updateCharacterDetails(final KidsCharacterDetails kidsCharacterDetails) {
        this.updateBoxart(kidsCharacterDetails);
        this.updateCharacterImage();
        this.updateTitle(kidsCharacterDetails);
    }
    
    protected void updateCharacterImage() {
        if (this.characterView != null) {
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.characterView, this.this$0.kidsCharacterDetails.getSquareUrl(), IClientLogging$AssetType.boxArt, String.format(this.getResources().getString(2131493174), this.this$0.kidsCharacterDetails.getTitle()), BrowseExperience.getImageLoaderConfig(), true);
        }
    }
}
