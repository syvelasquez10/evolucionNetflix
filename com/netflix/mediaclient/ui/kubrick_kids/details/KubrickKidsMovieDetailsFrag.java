// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;
import android.content.Context;
import android.view.View;
import android.os.Bundle;
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
        return 2130903108;
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
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationEdgePadding(this.getActivity().getResources().getDimensionPixelOffset(2131296476), this.numColumns, 3));
    }
}
