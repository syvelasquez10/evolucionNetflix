// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
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
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;

class KubrickKidsCharacterDetailsFrag$KubrickKidsCharacterDetailsViewGroup extends KubrickVideoDetailsViewGroup
{
    AdvancedImageView characterView;
    final /* synthetic */ KubrickKidsCharacterDetailsFrag this$0;
    
    public KubrickKidsCharacterDetailsFrag$KubrickKidsCharacterDetailsViewGroup(final KubrickKidsCharacterDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.setupViews();
    }
    
    private Drawable getBackgroundResource() {
        switch (this.this$0.kidsColor) {
            default: {
                return this.this$0.getActivity().getResources().getDrawable(2130837566);
            }
            case 2131230894: {
                return this.this$0.getActivity().getResources().getDrawable(2130837571);
            }
            case 2131230898: {
                return this.this$0.getActivity().getResources().getDrawable(2130837566);
            }
            case 2131230895: {
                return this.this$0.getActivity().getResources().getDrawable(2130837568);
            }
            case 2131230897: {
                return this.this$0.getActivity().getResources().getDrawable(2130837570);
            }
            case 2131230893: {
                return this.this$0.getActivity().getResources().getDrawable(2130837569);
            }
        }
    }
    
    private void setupViews() {
        final int detailsPageContentWidth = KubrickUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity());
        final int height = (int)(detailsPageContentWidth * 0.5625f);
        this.getBackgroundImage().getLayoutParams().width = detailsPageContentWidth;
        if (DeviceUtils.isLandscape(this.getContext())) {
            this.getBackgroundImage().getLayoutParams().height = (int)(height * 0.75f);
            final ViewGroup$LayoutParams layoutParams = this.characterView.getLayoutParams();
            final ViewGroup$LayoutParams layoutParams2 = this.characterView.getLayoutParams();
            final int n = (int)(height * 0.75f);
            layoutParams2.width = n;
            layoutParams.height = n;
        }
        else {
            this.getBackgroundImage().getLayoutParams().height = height;
            final ViewGroup$LayoutParams layoutParams3 = this.characterView.getLayoutParams();
            this.characterView.getLayoutParams().width = height;
            layoutParams3.height = height;
        }
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
        this.characterView = (AdvancedImageView)this.findViewById(2131427531);
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
        NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.horzDispImg, storyUrl, IClientLogging$AssetType.boxArt, String.format(this.getResources().getString(2131493182), this.this$0.kidsCharacterDetails.getTitle()), BrowseExperience.getImageLoaderConfig(), true);
        this.horzDispImg.setTag((Object)storyUrl);
    }
    
    public void updateCharacterDetails(final KidsCharacterDetails kidsCharacterDetails) {
        this.updateBoxart(kidsCharacterDetails);
        this.updateCharacterImage();
        this.updateTitle(kidsCharacterDetails);
    }
    
    protected void updateCharacterImage() {
        if (this.characterView != null) {
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.characterView, this.this$0.kidsCharacterDetails.getSquareUrl(), IClientLogging$AssetType.boxArt, String.format(this.getResources().getString(2131493182), this.this$0.kidsCharacterDetails.getTitle()), BrowseExperience.getImageLoaderConfig(), true);
        }
    }
}
