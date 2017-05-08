// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
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
import java.util.Collection;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.details.DPPrefetchABTestUtils;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class GalleryGenresLoMoFrag$GenresVideoFetchCallback extends LoggingManagerCallback
{
    private final int numItems;
    private final int start;
    final /* synthetic */ GalleryGenresLoMoFrag this$0;
    
    public GalleryGenresLoMoFrag$GenresVideoFetchCallback(final GalleryGenresLoMoFrag this$0, final String s, final int start, final int n) {
        this.this$0 = this$0;
        super(s);
        this.numItems = n - start + 1;
        this.start = start;
    }
    
    protected void handlePrefetchDPData(final List<Video> list) {
        DPPrefetchABTestUtils.prefetchDPForSimilars(this.this$0.getServiceManager(), list);
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        super.onVideosFetched(list, status);
        this.this$0.hasMoreData = true;
        if (status.isError()) {
            Log.w("GalleryGenresLoMoFrag", "Invalid status code");
            this.this$0.showErrorView();
        }
        else {
            if (Log.isLoggable()) {
                Log.v("GalleryGenresLoMoFrag", "Got " + list.size() + " items, expected " + this.numItems);
            }
            if (list == null || list.size() <= 0) {
                this.this$0.hasMoreData = false;
                this.this$0.leWrapper.showErrorView(2131296701, false, true);
                return;
            }
            if (list.size() < this.numItems) {
                this.this$0.hasMoreData = false;
            }
            this.this$0.adapter.updateItems(list, this.start);
            this.this$0.startIndex += list.size();
            this.this$0.hideLoadingAndErrorViews();
            if (this.this$0.startIndex == 0) {
                this.handlePrefetchDPData(list);
            }
        }
    }
}
