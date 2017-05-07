// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.android.widget.LoggingScrollView$OnScrollStopListener;

class SearchResultsFrag$ScrollLoggingListener implements LoggingScrollView$OnScrollStopListener
{
    final /* synthetic */ SearchResultsFrag this$0;
    
    SearchResultsFrag$ScrollLoggingListener(final SearchResultsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void log() {
        this.this$0.fireImpressionEvents();
    }
}
