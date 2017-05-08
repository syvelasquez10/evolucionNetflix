// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.view.View;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;

class GalleryGenresLoMoFrag$1 extends GridLayoutManager$SpanSizeLookup
{
    final /* synthetic */ GalleryGenresLoMoFrag this$0;
    
    GalleryGenresLoMoFrag$1(final GalleryGenresLoMoFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int getSpanSize(final int n) {
        final RecyclerViewHeaderAdapter recyclerViewHeaderAdapter = (RecyclerViewHeaderAdapter)this.this$0.recyclerView.getAdapter();
        if (recyclerViewHeaderAdapter.isPositionFooter(n) || recyclerViewHeaderAdapter.isPositionHeader(n)) {
            return this.this$0.numColumns;
        }
        if (n == 0) {
            return this.this$0.numColumns;
        }
        return 1;
    }
}
