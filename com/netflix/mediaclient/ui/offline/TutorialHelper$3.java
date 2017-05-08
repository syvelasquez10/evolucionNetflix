// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView$OnScrollListener;

class TutorialHelper$3 extends RecyclerView$OnScrollListener
{
    final /* synthetic */ TutorialHelper this$0;
    final /* synthetic */ RecyclerView val$recyclerView;
    
    TutorialHelper$3(final TutorialHelper this$0, final RecyclerView val$recyclerView) {
        this.this$0 = this$0;
        this.val$recyclerView = val$recyclerView;
    }
    
    @Override
    public void onScrollStateChanged(final RecyclerView recyclerView, final int n) {
        if (n == 0) {
            this.val$recyclerView.removeOnScrollListener(this);
            this.this$0.tooltip.show();
        }
    }
}
