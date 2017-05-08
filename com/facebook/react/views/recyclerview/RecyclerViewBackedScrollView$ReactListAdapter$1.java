// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.recyclerview;

import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView$ViewHolder;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.RecyclerView$Adapter;
import android.view.View;
import android.view.View$OnLayoutChangeListener;

class RecyclerViewBackedScrollView$ReactListAdapter$1 implements View$OnLayoutChangeListener
{
    final /* synthetic */ RecyclerViewBackedScrollView$ReactListAdapter this$0;
    
    RecyclerViewBackedScrollView$ReactListAdapter$1(final RecyclerViewBackedScrollView$ReactListAdapter this$0) {
        this.this$0 = this$0;
    }
    
    public void onLayoutChange(View view, int n, int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        n = n8 - n6;
        n2 = n4 - n2;
        if (n != n2) {
            this.this$0.updateTotalChildrenHeight(n2 - n);
            this.this$0.mScrollOffsetTracker.onHeightChange(this.this$0.mViews.indexOf(view), n, n2);
            if (view.getParent() != null && view.getParent().getParent() != null) {
                view = (View)view.getParent();
                view.forceLayout();
                ((View)view.getParent()).forceLayout();
            }
        }
    }
}
