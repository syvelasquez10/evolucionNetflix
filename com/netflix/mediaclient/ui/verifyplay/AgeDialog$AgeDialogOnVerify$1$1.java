// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.home.AccountHandler;

class AgeDialog$AgeDialogOnVerify$1$1 implements Runnable
{
    final /* synthetic */ AgeDialog$AgeDialogOnVerify$1 this$2;
    final /* synthetic */ AccountHandler val$handler;
    final /* synthetic */ Status val$timeoutStatus;
    
    AgeDialog$AgeDialogOnVerify$1$1(final AgeDialog$AgeDialogOnVerify$1 this$2, final AccountHandler val$handler, final Status val$timeoutStatus) {
        this.this$2 = this$2;
        this.val$handler = val$handler;
        this.val$timeoutStatus = val$timeoutStatus;
    }
    
    @Override
    public void run() {
        this.val$handler.handle(null, this.val$timeoutStatus);
    }
}
