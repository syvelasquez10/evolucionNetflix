// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AbsListView$RecyclerListener;

class LoLoMoFrag$2 implements AbsListView$RecyclerListener
{
    final /* synthetic */ LoLoMoFrag this$0;
    
    LoLoMoFrag$2(final LoLoMoFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onMovedToScrapHeap(final View view) {
        final BaseLoLoMoAdapter$RowHolder baseLoLoMoAdapter$RowHolder = (BaseLoLoMoAdapter$RowHolder)view.getTag();
        if (baseLoLoMoAdapter$RowHolder == null) {
            if (Log.isLoggable("LoLoMoFrag", 3)) {
                Log.d("LoLoMoFrag", "View tag is null - can't notify holder of move to scrap, view: " + view.getClass().getSimpleName());
            }
            return;
        }
        Log.v("LoLoMoFrag", "View moved to scrap heap - notifying holder");
        baseLoLoMoAdapter$RowHolder.onViewMovedToScrapHeap();
    }
}
