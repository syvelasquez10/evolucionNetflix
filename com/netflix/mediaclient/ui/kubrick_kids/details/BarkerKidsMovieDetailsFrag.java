// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.ui.kubrick.details.BarkerVideoDetailsViewGroup;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.kubrick.details.BarkerMovieDetailsFrag;

public class BarkerKidsMovieDetailsFrag extends BarkerMovieDetailsFrag
{
    public static BarkerKidsMovieDetailsFrag create(final String s) {
        final BarkerKidsMovieDetailsFrag barkerKidsMovieDetailsFrag = new BarkerKidsMovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        barkerKidsMovieDetailsFrag.setArguments(arguments);
        return barkerKidsMovieDetailsFrag;
    }
    
    @Override
    protected int getBackgroundResource() {
        return 2131624152;
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903152;
    }
    
    @Override
    protected int getNumColumns() {
        return this.getActivity().getResources().getInteger(2131492881);
    }
    
    @Override
    protected int getRecyclerViewShadowWidth() {
        return KidsUtils.getDetailsPageContentWidth((Context)this.getActivity()) + (int)this.getResources().getDimension(2131362185) * 2;
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new BarkerKidsMovieDetailsFrag$KubrickKidsMovieDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
        ((BarkerVideoDetailsViewGroup)this.detailsViewGroup).hideDataSelector();
    }
    
    @Override
    protected void setupDetailsPageParallaxScrollListener() {
        this.parallaxScroller = new KidsParallax(null, this.recyclerView, new View[] { this.detailsViewGroup.getHeroImage(), ((BarkerVideoDetailsViewGroup)this.detailsViewGroup).getHeroImage2() }, null, null);
        this.recyclerView.setOnScrollListener(this.parallaxScroller);
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
        this.adapter.setViewCreator(new BarkerKidsMovieDetailsFrag$1(this));
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationEdgePadding(this.getActivity().getResources().getDimensionPixelOffset(2131362195), this.numColumns, 3));
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        super.setupRecyclerViewLayoutManager();
        this.recyclerView.getLayoutParams().width = KidsUtils.getDetailsPageContentWidth((Context)this.getActivity());
    }
}
