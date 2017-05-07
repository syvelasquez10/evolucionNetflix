// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.android.app.Status;

class UserAgent$ConnectWithFacebookTask$1$1 implements Runnable
{
    final /* synthetic */ UserAgent$ConnectWithFacebookTask$1 this$2;
    final /* synthetic */ Status val$res;
    
    UserAgent$ConnectWithFacebookTask$1$1(final UserAgent$ConnectWithFacebookTask$1 this$2, final Status val$res) {
        this.this$2 = this$2;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onConnectWithFacebook(this.val$res);
    }
}
