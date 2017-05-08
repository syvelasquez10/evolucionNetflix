// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.home.AccountHandler;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class EogLandingPage$1$1$2 extends SimpleManagerCallback
{
    final /* synthetic */ EogLandingPage$1$1 this$1;
    final /* synthetic */ AccountHandler val$handler;
    final /* synthetic */ Runnable val$timeout;
    
    EogLandingPage$1$1$2(final EogLandingPage$1$1 this$1, final Runnable val$timeout, final AccountHandler val$handler) {
        this.this$1 = this$1;
        this.val$timeout = val$timeout;
        this.val$handler = val$handler;
    }
    
    @Override
    public void onAutoLoginTokenCreated(final String s, final Status status) {
        this.this$1.this$0.val$context.getHandler().removeCallbacks(this.val$timeout);
        this.val$handler.handle(s, status);
    }
}
