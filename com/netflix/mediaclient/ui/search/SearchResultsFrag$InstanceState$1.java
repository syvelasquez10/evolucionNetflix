// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.view.View;
import com.netflix.mediaclient.android.widget.FlowLayout;

class SearchResultsFrag$InstanceState$1 implements Runnable
{
    final /* synthetic */ SearchResultsFrag$InstanceState this$1;
    final /* synthetic */ FlowLayout val$flowLayout;
    final /* synthetic */ int val$selectedPosition;
    
    SearchResultsFrag$InstanceState$1(final SearchResultsFrag$InstanceState this$1, final FlowLayout val$flowLayout, final int val$selectedPosition) {
        this.this$1 = this$1;
        this.val$flowLayout = val$flowLayout;
        this.val$selectedPosition = val$selectedPosition;
    }
    
    @Override
    public void run() {
        final View child = this.val$flowLayout.getChildAt(this.val$selectedPosition);
        if (child != null) {
            child.performClick();
        }
    }
}
