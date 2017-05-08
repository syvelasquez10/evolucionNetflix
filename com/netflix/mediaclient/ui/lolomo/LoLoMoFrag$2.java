// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class LoLoMoFrag$2 implements ErrorWrapper$Callback
{
    final /* synthetic */ LoLoMoFrag this$0;
    
    LoLoMoFrag$2(final LoLoMoFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        this.this$0.refresh();
    }
}
