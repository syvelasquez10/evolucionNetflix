// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.android.widget.StaticGridView;

class SearchResultsFrag$InstanceState$2 implements Runnable
{
    final /* synthetic */ SearchResultsFrag$InstanceState this$1;
    final /* synthetic */ StaticGridView val$gridView;
    final /* synthetic */ int val$selectedPosition;
    
    SearchResultsFrag$InstanceState$2(final SearchResultsFrag$InstanceState this$1, final StaticGridView val$gridView, final int val$selectedPosition) {
        this.this$1 = this$1;
        this.val$gridView = val$gridView;
        this.val$selectedPosition = val$selectedPosition;
    }
    
    @Override
    public void run() {
        this.val$gridView.performItemClick(this.val$gridView.getChildAt(this.val$selectedPosition), this.val$selectedPosition, this.val$gridView.getAdapter().getItemId(this.val$selectedPosition));
    }
}
