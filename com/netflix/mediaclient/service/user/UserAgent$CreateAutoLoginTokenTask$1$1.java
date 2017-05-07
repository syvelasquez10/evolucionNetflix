// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.android.app.Status;

class UserAgent$CreateAutoLoginTokenTask$1$1 implements Runnable
{
    final /* synthetic */ UserAgent$CreateAutoLoginTokenTask$1 this$2;
    final /* synthetic */ Status val$res;
    final /* synthetic */ String val$token;
    
    UserAgent$CreateAutoLoginTokenTask$1$1(final UserAgent$CreateAutoLoginTokenTask$1 this$2, final String val$token, final Status val$res) {
        this.this$2 = this$2;
        this.val$token = val$token;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onAutoLoginTokenCreated(this.val$token, this.val$res);
    }
}
