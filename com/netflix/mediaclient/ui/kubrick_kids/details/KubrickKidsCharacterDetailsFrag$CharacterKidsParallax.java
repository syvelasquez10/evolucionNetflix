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
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.api.Api16Util;
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
                return view.getResources().getDrawable(2130837826);
            }
            case 2131558504: {
                return view.getResources().getDrawable(2130837830);
            }
            case 2131558497: {
                return view.getResources().getDrawable(2130837825);
            }
            case 2131558499: {
                return view.getResources().getDrawable(2130837827);
            }
            case 2131558501: {
                return view.getResources().getDrawable(2130837829);
            }
            case 2131558500: {
                return view.getResources().getDrawable(2130837828);
            }
        }
    }
    
    @Override
    protected void setInitialToolbarColor() {
        super.setInitialToolbarColor();
        final View viewById = this.recyclerView.getRootView().findViewById(2131624049);
        if (viewById != null) {
            Api16Util.setBackgroundDrawableCompat(viewById, this.getBackroundResource(viewById));
        }
    }
}