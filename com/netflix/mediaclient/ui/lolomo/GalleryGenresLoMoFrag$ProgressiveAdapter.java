// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.view.View;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
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
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;

class GalleryGenresLoMoFrag$ProgressiveAdapter extends SimilarItemsGridViewAdapter
{
    private final int BATCH;
    final /* synthetic */ GalleryGenresLoMoFrag this$0;
    
    public GalleryGenresLoMoFrag$ProgressiveAdapter(final GalleryGenresLoMoFrag this$0, final boolean b, final int n, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        this.this$0 = this$0;
        super(b, n, recyclerViewHeaderAdapter$IViewCreator);
        this.BATCH = 40;
    }
    
    public void fetchData() {
        final ServiceManager serviceManager = this.this$0.getServiceManager();
        if (serviceManager != null) {
            final int n = this.this$0.startIndex + 39;
            if (Log.isLoggable()) {
                Log.v("GalleryGenresLoMoFrag", "Fetching genre videos: start: " + this.this$0.startIndex + ", end: " + n);
            }
            serviceManager.getBrowse().fetchGenreVideos(new GalleryGenresLoMoFrag$FlatGenre(this.this$0, null), this.this$0.startIndex, n, false, new GalleryGenresLoMoFrag$GenresVideoFetchCallback(this.this$0, "GalleryGenresLoMoFrag", this.this$0.startIndex, n));
        }
    }
    
    @Override
    protected void onPostItemViewBind(final int n) {
        super.onPostItemViewBind(n);
        if (this.this$0.hasMoreData && n == this.getItemCount() - 20) {
            if (Log.isLoggable()) {
                Log.v("GalleryGenresLoMoFrag", "Fetching more genres videos, at position: " + n);
            }
            this.fetchData();
        }
    }
}
