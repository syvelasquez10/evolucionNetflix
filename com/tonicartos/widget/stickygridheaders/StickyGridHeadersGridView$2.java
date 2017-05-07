// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.view.View;

class StickyGridHeadersGridView$2 implements Runnable
{
    final /* synthetic */ StickyGridHeadersGridView this$0;
    final /* synthetic */ View val$headerHolder;
    
    StickyGridHeadersGridView$2(final StickyGridHeadersGridView this$0, final View val$headerHolder) {
        this.this$0 = this$0;
        this.val$headerHolder = val$headerHolder;
    }
    
    @Override
    public void run() {
        this.this$0.invalidate(0, this.val$headerHolder.getTop(), this.this$0.getWidth(), this.val$headerHolder.getTop() + this.val$headerHolder.getHeight());
    }
}
