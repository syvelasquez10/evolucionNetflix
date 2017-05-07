// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.android.app.Status;

class UserAgent$VerifyPinTask$1$1 implements Runnable
{
    final /* synthetic */ UserAgent$VerifyPinTask$1 this$2;
    final /* synthetic */ boolean val$isPinValid;
    final /* synthetic */ Status val$res;
    
    UserAgent$VerifyPinTask$1$1(final UserAgent$VerifyPinTask$1 this$2, final boolean val$isPinValid, final Status val$res) {
        this.this$2 = this$2;
        this.val$isPinValid = val$isPinValid;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onVerified(this.val$isPinValid, this.val$res);
    }
}
