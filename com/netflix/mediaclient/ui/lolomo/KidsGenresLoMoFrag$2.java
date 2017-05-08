// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class KidsGenresLoMoFrag$2 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ KidsGenresLoMoFrag this$0;
    
    KidsGenresLoMoFrag$2(final KidsGenresLoMoFrag this$0) {
        this.this$0 = this$0;
    }
    
    private int getImageWidth() {
        return (this.this$0.recyclerView.getWidth() - (this.this$0.getActivity().getResources().getDimensionPixelSize(2131362195) * 2 + this.this$0.getActivity().getResources().getDimensionPixelOffset(2131362193) * this.this$0.numColumns)) / this.this$0.numColumns;
    }
    
    @Override
    public View createItemView() {
        boolean isHorizontal = true;
        final VideoView videoView = new VideoView(this.this$0.recyclerView.getContext());
        videoView.setScaleType(ImageView$ScaleType.FIT_CENTER);
        videoView.setAdjustViewBounds(true);
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(this.getImageWidth(), -1));
        if (BrowseExperience.useLolomoBoxArt()) {
            isHorizontal = false;
        }
        videoView.setIsHorizontal(isHorizontal);
        return (View)videoView;
    }
}
