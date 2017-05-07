// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;

public class KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter extends SimilarItemsGridViewAdapter
{
    final /* synthetic */ KubrickMovieDetailsFrag this$0;
    
    public KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter(final KubrickMovieDetailsFrag this$0, final boolean b, final int n) {
        this.this$0 = this$0;
        super(b, n, new KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter$1(this$0, n));
    }
}
