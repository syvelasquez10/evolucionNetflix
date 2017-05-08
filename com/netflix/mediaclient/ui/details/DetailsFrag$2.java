// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class DetailsFrag$2 implements ErrorWrapper$Callback
{
    final /* synthetic */ DetailsFrag this$0;
    
    DetailsFrag$2(final DetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        ((ErrorWrapper$Callback)this.this$0.getActivity()).onRetryRequested();
    }
}
