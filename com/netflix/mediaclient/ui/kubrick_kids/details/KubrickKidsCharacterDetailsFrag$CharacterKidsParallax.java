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
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.ShowDetailsFrag$ShowDetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.content.Context;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import android.view.View;

class KubrickKidsCharacterDetailsFrag$CharacterKidsParallax extends KidsParallax
{
    final /* synthetic */ KubrickKidsCharacterDetailsFrag this$0;
    
    public KubrickKidsCharacterDetailsFrag$CharacterKidsParallax(final KubrickKidsCharacterDetailsFrag this$0, final KubrickKidsShowDetailsFrag kubrickKidsShowDetailsFrag) {
        this.this$0 = this$0;
        final SeasonsSpinner access$200 = this$0.spinner;
        final RecyclerView access$201 = this$0.getRecyclerView();
        final ImageView backgroundImage = this$0.detailsViewGroup.getBackgroundImage();
        Object access$202;
        if (this$0.shouldRenderAsSDP) {
            access$202 = this$0.spinnerViewGroup;
        }
        else {
            access$202 = null;
        }
        Object footerViewGroup;
        if (this$0.shouldRenderAsSDP) {
            footerViewGroup = this$0.detailsViewGroup.getFooterViewGroup();
        }
        else {
            footerViewGroup = null;
        }
        super(kubrickKidsShowDetailsFrag, access$200, access$201, new View[] { backgroundImage }, (View)access$202, (View)footerViewGroup);
        if (!this$0.shouldRenderAsSDP) {
            this$0.detailsViewGroup.getFooterViewGroup().setVisibility(8);
        }
    }
    
    private Drawable getBackroundResource(final View view) {
        switch (this.this$0.kidsColor) {
            default: {
                return view.getResources().getDrawable(2130837770);
            }
            case 2131230894: {
                return view.getResources().getDrawable(2130837774);
            }
            case 2131230898: {
                return view.getResources().getDrawable(2130837770);
            }
            case 2131230895: {
                return view.getResources().getDrawable(2130837771);
            }
            case 2131230897: {
                return view.getResources().getDrawable(2130837773);
            }
            case 2131230893: {
                return view.getResources().getDrawable(2130837772);
            }
        }
    }
    
    @Override
    protected void setInitialToolbarColor() {
        super.setInitialToolbarColor();
        final View viewById = this.recyclerView.getRootView().findViewById(2131427430);
        if (viewById != null) {
            viewById.setBackground(this.getBackroundResource(viewById));
        }
    }
}
