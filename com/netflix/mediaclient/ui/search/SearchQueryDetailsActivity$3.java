// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.android.widget.LoggingScrollView$OnScrollStopListener;

class SearchQueryDetailsActivity$3 implements LoggingScrollView$OnScrollStopListener
{
    final /* synthetic */ SearchQueryDetailsActivity this$0;
    
    SearchQueryDetailsActivity$3(final SearchQueryDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void log() {
        this.this$0.fireImpressionEvents();
    }
}
