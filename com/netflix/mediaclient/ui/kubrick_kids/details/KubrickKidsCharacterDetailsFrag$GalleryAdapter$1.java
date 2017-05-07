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
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import android.annotation.SuppressLint;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class KubrickKidsCharacterDetailsFrag$GalleryAdapter$1 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ KubrickKidsCharacterDetailsFrag val$this$0;
    
    KubrickKidsCharacterDetailsFrag$GalleryAdapter$1(final KubrickKidsCharacterDetailsFrag val$this$0) {
        this.val$this$0 = val$this$0;
    }
    
    private int getImageHeight() {
        return (int)((KubrickUtils.getDetailsPageContentWidth((Context)this.val$this$0.getActivity()) - this.val$this$0.getActivity().getResources().getDimensionPixelOffset(2131296452) * (this.val$this$0.numColumns + 1.0f)) / this.val$this$0.numColumns * 0.5625f);
    }
    
    @Override
    public View createItemView() {
        final VideoView videoView = new VideoView(this.val$this$0.recyclerView.getContext());
        videoView.setAdjustViewBounds(true);
        videoView.setScaleType(ImageView$ScaleType.FIT_XY);
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.getImageHeight()));
        videoView.setIsHorizontal(true);
        return (View)videoView;
    }
}
