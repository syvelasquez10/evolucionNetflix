// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View$OnClickListener;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.view.View;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;

public class KubrickMovieDetailsFrag extends MovieDetailsFrag
{
    private static final int ANIMATE_IN_DURATION_MS = 500;
    private View rootContainer;
    
    private void adjustBackroundForNestedActivities(final View view) {
        if (view != null && this.getActivity().getIntent().getBooleanExtra("extra_same_activity_type", false)) {
            Api16Util.setBackgroundDrawableCompat(view, null);
        }
    }
    
    public static KubrickMovieDetailsFrag create(final String s) {
        final KubrickMovieDetailsFrag kubrickMovieDetailsFrag = new KubrickMovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        kubrickMovieDetailsFrag.setArguments(arguments);
        return kubrickMovieDetailsFrag;
    }
    
    private void setupDismissClick() {
        if (this.rootContainer != null) {
            this.rootContainer.setOnClickListener((View$OnClickListener)new KubrickMovieDetailsFrag$1(this));
        }
    }
    
    protected void animateIn() {
        if (this.recyclerView == null || this.rootContainer == null) {
            return;
        }
        this.recyclerView.animate().alpha(1.0f).setDuration(500L);
        this.getNetflixActivity().getNetflixActionBar().setAlphaWithAnimation(1.0f, 500);
    }
    
    @Override
    protected void findViews(final View view) {
        super.findViews(view);
        this.rootContainer = view.findViewById(2131427613);
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new KubrickVideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
        ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).hideDataSelector();
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.adjustBackroundForNestedActivities(onCreateView);
        return onCreateView;
    }
    
    @Override
    protected int retrieveNumColumns() {
        return this.getActivity().getResources().getInteger(2131361802);
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        (this.adapter = new KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter(this, this.recyclerView, true, this.numColumns)).addHeaderView((View)this.detailsViewGroup);
        this.recyclerView.setAdapter(this.adapter);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131296452), this.numColumns));
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        super.setupRecyclerViewLayoutManager();
        this.recyclerView.getLayoutParams().width = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
        this.recyclerView.setAlpha(0.0f);
    }
    
    @Override
    protected void showDetailsView(final MovieDetails movieDetails) {
        super.showDetailsView(movieDetails);
        this.animateIn();
        this.setupDismissClick();
        this.updateBookmark(movieDetails);
    }
    
    protected void updateBookmark(final MovieDetails movieDetails) {
        final KubrickVideoDetailsViewGroup kubrickVideoDetailsViewGroup = (KubrickVideoDetailsViewGroup)this.detailsViewGroup;
        if (movieDetails.getPlayable().getPlayableBookmarkPosition() > 0) {
            kubrickVideoDetailsViewGroup.setBookmarkVisibility(0);
            kubrickVideoDetailsViewGroup.updateBookmark(movieDetails.getPlayable());
            return;
        }
        kubrickVideoDetailsViewGroup.setBookmarkVisibility(8);
    }
}
