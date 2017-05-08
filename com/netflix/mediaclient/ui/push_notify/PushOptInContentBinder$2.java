// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.push_notify;

import android.view.View;
import android.view.View$OnClickListener;

class PushOptInContentBinder$2 implements View$OnClickListener
{
    final /* synthetic */ PushOptInContentBinder this$0;
    final /* synthetic */ OptInResponseHandler val$callback;
    
    PushOptInContentBinder$2(final PushOptInContentBinder this$0, final OptInResponseHandler val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
    }
    
    public void onClick(final View view) {
        this.val$callback.onSkip();
    }
}
