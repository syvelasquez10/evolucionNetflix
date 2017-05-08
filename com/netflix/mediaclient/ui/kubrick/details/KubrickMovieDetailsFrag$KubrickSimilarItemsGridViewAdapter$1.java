// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import com.netflix.mediaclient.util.DeviceUtils;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import java.util.Stack;
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
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter$1 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ int val$numColumns;
    final /* synthetic */ KubrickMovieDetailsFrag val$this$0;
    
    KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter$1(final KubrickMovieDetailsFrag val$this$0, final int val$numColumns) {
        this.val$this$0 = val$this$0;
        this.val$numColumns = val$numColumns;
    }
    
    private int getImageHeight() {
        return (int)((KubrickUtils.getDetailsPageContentWidth((Context)this.val$this$0.getActivity()) - this.val$this$0.getActivity().getResources().getDimensionPixelOffset(2131296577) * (this.val$numColumns + 1.0f)) / this.val$numColumns * 0.5625f);
    }
    
    @Override
    public View createItemView() {
        final VideoView videoView = new VideoView(this.val$this$0.recyclerView.getContext());
        videoView.setAdjustViewBounds(true);
        videoView.setClickListener(new KubrickMovieDetailsFrag$KubrickRelatedVideoDetailsClickListener(this.val$this$0, this.val$this$0.getNetflixActivity(), videoView));
        videoView.setScaleType(ImageView$ScaleType.FIT_XY);
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.getImageHeight()));
        videoView.setIsHorizontal(true);
        return (View)videoView;
    }
}
