// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.home.AccountHandler;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class AgeDialog$AgeDialogOnVerify$1$2 extends SimpleManagerCallback
{
    final /* synthetic */ AgeDialog$AgeDialogOnVerify$1 this$2;
    final /* synthetic */ AccountHandler val$handler;
    final /* synthetic */ Runnable val$timeout;
    
    AgeDialog$AgeDialogOnVerify$1$2(final AgeDialog$AgeDialogOnVerify$1 this$2, final Runnable val$timeout, final AccountHandler val$handler) {
        this.this$2 = this$2;
        this.val$timeout = val$timeout;
        this.val$handler = val$handler;
    }
    
    @Override
    public void onAutoLoginTokenCreated(final String s, final Status status) {
        this.this$2.val$context.getHandler().removeCallbacks(this.val$timeout);
        this.val$handler.handle(s, status, "https://www.netflix.com/verifyage");
    }
}
