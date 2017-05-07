// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.View;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.view.View$OnClickListener;

class AbsEpisodeView$1 implements View$OnClickListener
{
    final /* synthetic */ AbsEpisodeView this$0;
    final /* synthetic */ ErrorWrapper$Callback val$callback;
    
    AbsEpisodeView$1(final AbsEpisodeView this$0, final ErrorWrapper$Callback val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
    }
    
    public void onClick(final View view) {
        this.val$callback.onRetryRequested();
    }
}
