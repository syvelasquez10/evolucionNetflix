// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.ScrollView;

class LoggingScrollView$ScrollStateHandler implements Runnable
{
    final /* synthetic */ LoggingScrollView this$0;
    
    private LoggingScrollView$ScrollStateHandler(final LoggingScrollView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (System.currentTimeMillis() - this.this$0.lastScrollUpdate > 100L) {
            this.this$0.lastScrollUpdate = -1L;
            this.this$0.onScrollEnd();
            return;
        }
        this.this$0.postDelayed((Runnable)this, 100L);
    }
}
