// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.StaticGridView;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class SearchResultsFrag$3 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ SearchResultsFrag this$0;
    final /* synthetic */ StaticGridView val$gridView;
    
    SearchResultsFrag$3(final SearchResultsFrag this$0, final StaticGridView val$gridView) {
        this.this$0 = this$0;
        this.val$gridView = val$gridView;
    }
    
    public void onGlobalLayout() {
        this.this$0.fireImpressionEvents();
        ViewUtils.removeGlobalLayoutListener((View)this.val$gridView, (ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
