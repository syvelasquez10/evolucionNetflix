// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.kubrick.details.KubrickMovieDetailsFrag;

public class KubrickKidsMovieDetailsFrag extends KubrickMovieDetailsFrag
{
    public static KubrickKidsMovieDetailsFrag create(final String s) {
        final KubrickKidsMovieDetailsFrag kubrickKidsMovieDetailsFrag = new KubrickKidsMovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        kubrickKidsMovieDetailsFrag.setArguments(arguments);
        return kubrickKidsMovieDetailsFrag;
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903115;
    }
    
    @Override
    protected int getRecyclerViewShadowWidth() {
        return KidsUtils.getDetailsPageContentWidth((Context)this.getActivity()) + (int)this.getResources().getDimension(2131296517) * 2;
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new KubrickKidsMovieDetailsFrag$KubrickKidsMovieDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
        ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).hideDataSelector();
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        final KidsParallax onScrollListener = new KidsParallax(null, null, this.recyclerView, new View[] { this.detailsViewGroup.getHeroImage(), ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).getHeroImage2() }, null, null);
        this.recyclerView.setOnScrollListener(onScrollListener);
        return onScrollListener;
    }
    
    @Override
    protected void setupRecyclerView() {
        super.setupRecyclerView();
        if (this.recyclerView != null) {
            this.recyclerView.setBackgroundColor(-1);
        }
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        super.setupRecyclerViewAdapter();
        this.adapter.setViewCreator(new KubrickKidsMovieDetailsFrag$1(this));
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationEdgePadding(this.getActivity().getResources().getDimensionPixelOffset(2131296531), this.numColumns, 3));
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        super.setupRecyclerViewLayoutManager();
        this.recyclerView.getLayoutParams().width = KidsUtils.getDetailsPageContentWidth((Context)this.getActivity());
        this.recyclerView.setAlpha(0.0f);
    }
    
    public void showViews() {
        if (new MaturityValidator(this, this.getNetflixActivity(), this.mVideoDetails).isRestricted()) {
            this.leWrapper.hide(false);
            return;
        }
        super.showViews();
    }
}
