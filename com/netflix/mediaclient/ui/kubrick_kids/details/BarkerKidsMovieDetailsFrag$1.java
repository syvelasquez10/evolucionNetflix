// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.ui.kubrick.details.BarkerVideoDetailsViewGroup;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.ui.kubrick.details.BarkerMovieDetailsFrag;
import com.netflix.mediaclient.ui.kubrick.details.BarkerMovieDetailsFrag$BarkerRelatedVideoDetailsClickListener;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class BarkerKidsMovieDetailsFrag$1 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ BarkerKidsMovieDetailsFrag this$0;
    
    BarkerKidsMovieDetailsFrag$1(final BarkerKidsMovieDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    private int getImageHeight() {
        return (int)((KidsUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity()) - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131362171) * (this.this$0.numColumns + 1.0f)) / this.this$0.numColumns * 1.43f);
    }
    
    @Override
    public View createItemView() {
        final VideoView videoView = new VideoView(this.this$0.recyclerView.getContext());
        videoView.setAdjustViewBounds(true);
        videoView.setClickListener(new BarkerMovieDetailsFrag$BarkerRelatedVideoDetailsClickListener(this.this$0, this.this$0.getNetflixActivity(), videoView));
        videoView.setScaleType(ImageView$ScaleType.FIT_XY);
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.getImageHeight()));
        return (View)videoView;
    }
}
