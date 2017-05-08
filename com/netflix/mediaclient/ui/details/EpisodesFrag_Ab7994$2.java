// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import android.os.Bundle;
import com.netflix.mediaclient.util.TrailerUtils;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.view.ViewGroup;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableObject;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class EpisodesFrag_Ab7994$2 implements RecyclerViewHeaderAdapter$IViewCreator
{
    private int height;
    int padding;
    final /* synthetic */ EpisodesFrag_Ab7994 this$0;
    private int width;
    
    EpisodesFrag_Ab7994$2(final EpisodesFrag_Ab7994 this$0) {
        this.this$0 = this$0;
        this.padding = this.this$0.getActivity().getResources().getDimensionPixelSize(2131427641);
        this.calculateViewDimensions();
    }
    
    private void calculateViewDimensions() {
        this.width = (DeviceUtils.getScreenWidthInPixels((Context)this.this$0.getActivity()) - this.this$0.recyclerView.getPaddingLeft() - this.this$0.recyclerView.getPaddingRight() - (this.this$0.numColumns + 1) * this.this$0.getActivity().getResources().getDimensionPixelOffset(2131427641)) / this.this$0.numColumns;
        this.height = (int)(this.width * 1.43f);
    }
    
    @Override
    public View createItemView() {
        final VideoView videoView = new VideoView(this.this$0.recyclerView.getContext());
        videoView.setAdjustViewBounds(true);
        videoView.setScaleType(ImageView$ScaleType.FIT_XY);
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(this.width, this.height));
        return (View)videoView;
    }
}
