// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.support.v7.widget.RecyclerView$Adapter;
import android.content.Context;
import android.view.View;
import android.os.Bundle;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;

public class KubrickMovieDetailsFrag extends MovieDetailsFrag
{
    public static KubrickMovieDetailsFrag create(final String s) {
        final KubrickMovieDetailsFrag kubrickMovieDetailsFrag = new KubrickMovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        kubrickMovieDetailsFrag.setArguments(arguments);
        return kubrickMovieDetailsFrag;
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new KubrickVideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
        ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).hideDataSelector();
    }
    
    @Override
    protected int retrieveNumColumns() {
        return this.getActivity().getResources().getInteger(2131427338);
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        (this.adapter = new KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter(this, this.recyclerView, true, this.numColumns)).addHeaderView((View)this.detailsViewGroup);
        this.recyclerView.setAdapter(this.adapter);
    }
}
