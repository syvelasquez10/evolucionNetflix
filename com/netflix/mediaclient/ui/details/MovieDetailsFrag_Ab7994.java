// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.content.Context;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableObject;
import com.netflix.mediaclient.util.TrailerUtils;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.os.Bundle;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.GridLayoutManager;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;

public class MovieDetailsFrag_Ab7994 extends MovieDetailsFrag
{
    private MovieDetails details;
    private GridLayoutManager gridLayoutManagerForTrailers;
    private ItemDecorationUniformPadding mItemDecorationForSims;
    private RecyclerViewHeaderAdapter$IViewCreator viewCreatorTrailers;
    
    public static MovieDetailsFrag_Ab7994 create(final String s) {
        final MovieDetailsFrag_Ab7994 movieDetailsFrag_Ab7994 = new MovieDetailsFrag_Ab7994();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        movieDetailsFrag_Ab7994.setArguments(arguments);
        return movieDetailsFrag_Ab7994;
    }
    
    private void setupRecyclerViewItemDecorationForSims() {
        if (this.mItemDecorationForSims == null) {
            this.mItemDecorationForSims = new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131427641), this.numColumns);
        }
        this.recyclerView.removeItemDecoration((RecyclerView$ItemDecoration)this.mItemDecorationForSims);
        this.recyclerView.addItemDecoration((RecyclerView$ItemDecoration)this.mItemDecorationForSims);
    }
    
    @Override
    protected void restoreLayoutManagerState() {
        if (!TrailerUtils.shouldShowTrailers(this.details)) {
            super.restoreLayoutManagerState();
        }
    }
    
    @Override
    protected void setTrackId(final MovieDetails trackId) {
        if (!TrailerUtils.shouldShowTrailers(trackId)) {
            super.setTrackId(trackId);
            return;
        }
        this.adapter.setTrackable(new TrackableObject(trackId.getTrailersRequestId(), trackId.getTrailersTrackId(), trackId.getTrailersListPos()));
    }
    
    @Override
    protected void setupRecyclerView() {
        super.setupRecyclerView();
        this.setupRecyclerViewAdapterForTrailers();
    }
    
    protected void setupRecyclerViewAdapterForTrailers() {
        if (this.viewCreatorTrailers == null) {
            this.viewCreatorTrailers = TrailerUtils.generateAdapterForTrailers((View)this.recyclerView);
        }
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
    }
    
    protected void setupRecyclerViewLayoutManagerForTrailers() {
        if (this.gridLayoutManagerForTrailers == null) {
            this.gridLayoutManagerForTrailers = TrailerUtils.generateGridViewLayoutManagerForTrailers((Context)this.getActivity(), (RecyclerViewHeaderAdapter)this.recyclerView.getAdapter());
        }
        this.recyclerView.setLayoutManager(this.gridLayoutManagerForTrailers);
    }
    
    @Override
    protected void showDetailsView(final MovieDetails details) {
        super.showDetailsView(this.details = details);
        if (TrailerUtils.shouldShowTrailers(details)) {
            this.setupRecyclerViewLayoutManagerForTrailers();
            this.setupRecyclerViewAdapterForTrailers();
            if (this.adapter instanceof SimilarItemsGridViewAdapter) {
                ((SimilarItemsGridViewAdapter)this.adapter).setClipToCompleteRows(false);
            }
            this.adapter.setItems(details.getTrailers(), 1, this.viewCreatorTrailers);
            return;
        }
        this.setupRecyclerViewItemDecorationForSims();
    }
    
    @Override
    protected void showSimsItems(final MovieDetails movieDetails) {
        if (!TrailerUtils.shouldShowTrailers(movieDetails)) {
            super.showSimsItems(movieDetails);
        }
    }
}
