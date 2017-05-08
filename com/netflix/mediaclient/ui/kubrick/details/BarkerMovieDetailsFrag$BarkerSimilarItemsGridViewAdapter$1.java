// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.servicemgr.interface_.details.Similarable;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.support.v7.widget.RecyclerView;
import java.util.Stack;
import com.netflix.mediaclient.util.ItemDecorationBarkerGrid;
import com.netflix.mediaclient.ui.details.CopyrightView;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class BarkerMovieDetailsFrag$BarkerSimilarItemsGridViewAdapter$1 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ BarkerMovieDetailsFrag val$this$0;
    
    BarkerMovieDetailsFrag$BarkerSimilarItemsGridViewAdapter$1(final BarkerMovieDetailsFrag val$this$0) {
        this.val$this$0 = val$this$0;
    }
    
    private int getImageHeight() {
        return (int)(this.getImageWidth() * 1.43f);
    }
    
    private int getImageWidth() {
        return (BarkerUtils.getDetailsPageContentWidth((Context)this.val$this$0.getActivity()) - this.val$this$0.innerGridDecoration.getAllSpaceWidth()) / this.val$this$0.getNumColumns();
    }
    
    @Override
    public View createItemView() {
        final VideoView videoView = new VideoView(this.val$this$0.recyclerView.getContext());
        videoView.setAdjustViewBounds(true);
        videoView.setClickListener(new BarkerMovieDetailsFrag$BarkerRelatedVideoDetailsClickListener(this.val$this$0, this.val$this$0.getNetflixActivity(), videoView));
        videoView.setScaleType(ImageView$ScaleType.FIT_XY);
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(this.getImageWidth(), this.getImageHeight()));
        videoView.setIsHorizontal(false);
        return (View)videoView;
    }
}
