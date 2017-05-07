// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.android.app.Status;

class UserAgent$CreateAutoLoginTokenTask$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$CreateAutoLoginTokenTask this$1;
    
    UserAgent$CreateAutoLoginTokenTask$1(final UserAgent$CreateAutoLoginTokenTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onAutoLoginTokenCreated(final String s, final Status status) {
        this.this$1.this$0.getMainHandler().post((Runnable)new UserAgent$CreateAutoLoginTokenTask$1$1(this, s, status));
    }
}
