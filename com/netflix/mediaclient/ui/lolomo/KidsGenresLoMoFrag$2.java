// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
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
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
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
    
    private int getImageHeightHoriz() {
        return (int)((this.this$0.recyclerView.getWidth() - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131296507) * (this.this$0.numColumns + 1.0f)) / this.this$0.numColumns * 0.5625f);
    }
    
    private int getImageHeightVert() {
        return (int)((this.this$0.recyclerView.getWidth() - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131296507) * (this.this$0.numColumns + 1.0f)) / this.this$0.numColumns * 1.333f);
    }
    
    @Override
    public View createItemView() {
        final VideoView videoView = new VideoView(this.this$0.recyclerView.getContext());
        videoView.setAdjustViewBounds(true);
        videoView.setScaleType(ImageView$ScaleType.FIT_XY);
        int n;
        if (BrowseExperience.useLolomoVerticalArt()) {
            n = this.getImageHeightVert();
        }
        else {
            n = this.getImageHeightHoriz();
        }
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, n));
        videoView.setIsHorizontal(!BrowseExperience.useLolomoVerticalArt());
        return (View)videoView;
    }
}
