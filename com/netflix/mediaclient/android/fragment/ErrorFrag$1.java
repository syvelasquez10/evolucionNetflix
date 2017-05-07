// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class ErrorFrag$1 implements ErrorWrapper$Callback
{
    final /* synthetic */ ErrorFrag this$0;
    
    ErrorFrag$1(final ErrorFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        ((ErrorWrapper$Callback)this.this$0.getActivity()).onRetryRequested();
    }
}
