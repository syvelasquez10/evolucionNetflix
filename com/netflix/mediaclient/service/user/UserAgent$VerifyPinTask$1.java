// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.android.app.Status;

class UserAgent$VerifyPinTask$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$VerifyPinTask this$1;
    
    UserAgent$VerifyPinTask$1(final UserAgent$VerifyPinTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onVerified(final boolean b, final Status status) {
        this.this$1.this$0.getMainHandler().post((Runnable)new UserAgent$VerifyPinTask$1$1(this, b, status));
    }
}
