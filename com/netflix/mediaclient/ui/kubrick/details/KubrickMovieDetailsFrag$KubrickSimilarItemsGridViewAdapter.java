// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;

class KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter extends SimilarItemsGridViewAdapter
{
    final /* synthetic */ KubrickMovieDetailsFrag this$0;
    
    public KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter(final KubrickMovieDetailsFrag this$0, final RecyclerView recyclerView, final boolean b, final int n) {
        this.this$0 = this$0;
        super(recyclerView, b, n, new KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter$1(this$0, recyclerView, n));
    }
}
