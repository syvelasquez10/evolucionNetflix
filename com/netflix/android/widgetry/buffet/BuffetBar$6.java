// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.view.View;

class BuffetBar$6 implements BuffetBar$BuffetLayout$OnLayoutChangeListener
{
    final /* synthetic */ BuffetBar this$0;
    final /* synthetic */ boolean val$animate;
    
    BuffetBar$6(final BuffetBar this$0, final boolean val$animate) {
        this.this$0 = this$0;
        this.val$animate = val$animate;
    }
    
    @Override
    public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4) {
        this.this$0.mView.setOnLayoutChangeListener(null);
        if (this.val$animate && this.this$0.shouldAnimate()) {
            this.this$0.animateViewIn();
            return;
        }
        this.this$0.onViewShown();
    }
}
