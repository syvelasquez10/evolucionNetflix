// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class SlidingMenuAdapter$2 implements ErrorWrapper$Callback
{
    final /* synthetic */ SlidingMenuAdapter this$0;
    
    SlidingMenuAdapter$2(final SlidingMenuAdapter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        this.this$0.refresh();
    }
}
