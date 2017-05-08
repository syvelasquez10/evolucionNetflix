// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.app.Activity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class EpisodesFrag$7 implements ErrorWrapper$Callback
{
    final /* synthetic */ EpisodesFrag this$0;
    
    EpisodesFrag$7(final EpisodesFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        final Activity activity = this.this$0.getActivity();
        if (activity instanceof ErrorWrapper$Callback) {
            ((ErrorWrapper$Callback)activity).onRetryRequested();
            return;
        }
        Log.w("EpisodesFrag", "onRetryRequested but activity doesn't implement Errors callback: " + this.this$0.getActivity());
    }
}
