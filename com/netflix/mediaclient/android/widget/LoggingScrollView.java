// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.ScrollView;

public class LoggingScrollView extends ScrollView
{
    private long lastScrollUpdate;
    private LoggingScrollView$OnScrollStopListener stopScrollListener;
    
    public LoggingScrollView(final Context context) {
        super(context);
        this.lastScrollUpdate = -1L;
    }
    
    public LoggingScrollView(final Context context, final AttributeSet set) {
        super(context, set);
        this.lastScrollUpdate = -1L;
    }
    
    public LoggingScrollView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.lastScrollUpdate = -1L;
    }
    
    private void onScrollEnd() {
        if (this.stopScrollListener != null) {
            this.stopScrollListener.log();
        }
    }
    
    private void onScrollStart() {
    }
    
    protected void onScrollChanged(final int n, final int n2, final int n3, final int n4) {
        super.onScrollChanged(n, n2, n3, n4);
        if (this.lastScrollUpdate == -1L) {
            this.onScrollStart();
            this.postDelayed((Runnable)new LoggingScrollView$ScrollStateHandler(this, null), 100L);
        }
        this.lastScrollUpdate = System.currentTimeMillis();
    }
    
    public void setOnScrollStopListener(final LoggingScrollView$OnScrollStopListener stopScrollListener) {
        this.stopScrollListener = stopScrollListener;
    }
}
