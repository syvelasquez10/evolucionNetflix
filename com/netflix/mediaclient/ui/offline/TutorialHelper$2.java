// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.android.tooltips.Tooltip;
import android.support.v7.widget.RecyclerView;
import com.netflix.android.tooltips.Tooltip$Callback;

class TutorialHelper$2 implements Tooltip$Callback
{
    final /* synthetic */ TutorialHelper this$0;
    final /* synthetic */ RecyclerView val$recyclerView;
    
    TutorialHelper$2(final TutorialHelper this$0, final RecyclerView val$recyclerView) {
        this.this$0 = this$0;
        this.val$recyclerView = val$recyclerView;
    }
    
    @Override
    public void onDismissed(final Tooltip tooltip) {
        this.val$recyclerView.smoothScrollToPosition(0);
    }
    
    @Override
    public void onShown(final Tooltip tooltip) {
    }
}
