// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
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
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new KubrickKidsMovieDetailsFrag$KubrickKidsVideoDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
        ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).hideDataSelector();
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        final DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener = super.setupDetailsPageParallaxScrollListener();
        final View viewById = this.getActivity().findViewById(2131165294);
        if (setupDetailsPageParallaxScrollListener != null) {
            setupDetailsPageParallaxScrollListener.setInitialBottomColor(this.recyclerView.getResources().getColor(2131296365));
            setupDetailsPageParallaxScrollListener.setInitialTopColor(this.recyclerView.getResources().getColor(2131296364));
            setupDetailsPageParallaxScrollListener.setOnScrollStateChangedListener(new KubrickKidsMovieDetailsFrag$1(this, viewById));
        }
        return setupDetailsPageParallaxScrollListener;
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
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131362011), this.numColumns));
    }
}
