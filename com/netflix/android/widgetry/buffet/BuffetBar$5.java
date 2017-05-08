// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.view.View;

class BuffetBar$5 implements BuffetBar$BuffetLayout$OnAttachStateChangeListener
{
    final /* synthetic */ BuffetBar this$0;
    
    BuffetBar$5(final BuffetBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onViewAttachedToWindow(final View view) {
    }
    
    @Override
    public void onViewDetachedFromWindow(final View view) {
        if (this.this$0.isShownOrQueued()) {
            BuffetBar.sHandler.post((Runnable)new BuffetBar$5$1(this));
        }
    }
}
