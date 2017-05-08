// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.home.AccountHandler;

class EogLandingPage$1$1$1 implements Runnable
{
    final /* synthetic */ EogLandingPage$1$1 this$1;
    final /* synthetic */ AccountHandler val$handler;
    final /* synthetic */ Status val$timeoutStatus;
    
    EogLandingPage$1$1$1(final EogLandingPage$1$1 this$1, final AccountHandler val$handler, final Status val$timeoutStatus) {
        this.this$1 = this$1;
        this.val$handler = val$handler;
        this.val$timeoutStatus = val$timeoutStatus;
    }
    
    @Override
    public void run() {
        this.val$handler.handle(null, this.val$timeoutStatus);
    }
}
