// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.app.Activity;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class EpisodesFrag$6 implements ErrorWrapper$Callback
{
    final /* synthetic */ EpisodesFrag this$0;
    
    EpisodesFrag$6(final EpisodesFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        final Activity activity = this.this$0.getActivity();
        if (activity instanceof ErrorWrapper$Callback) {
            ((ErrorWrapper$Callback)activity).onRetryRequested();
        }
    }
}
