// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class MementoFrag$2 implements ErrorWrapper$Callback
{
    final /* synthetic */ MementoFrag this$0;
    
    MementoFrag$2(final MementoFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        this.this$0.onRetryRequested();
    }
}
