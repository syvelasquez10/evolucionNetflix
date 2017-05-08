// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import com.netflix.mediaclient.service.webclient.model.leafs.UmaCta;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.home.AccountHandler;

class UserMessageAreaView$9 implements Runnable
{
    final /* synthetic */ UserMessageAreaView this$0;
    final /* synthetic */ AccountHandler val$handler;
    final /* synthetic */ Status val$timeoutStatus;
    final /* synthetic */ UmaCta val$umaCta;
    
    UserMessageAreaView$9(final UserMessageAreaView this$0, final AccountHandler val$handler, final Status val$timeoutStatus, final UmaCta val$umaCta) {
        this.this$0 = this$0;
        this.val$handler = val$handler;
        this.val$timeoutStatus = val$timeoutStatus;
        this.val$umaCta = val$umaCta;
    }
    
    @Override
    public void run() {
        this.val$handler.handle(null, this.val$timeoutStatus, this.val$umaCta.action());
    }
}
