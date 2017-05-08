// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

class SignupActivity$1 implements Runnable
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$1(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.reload(false);
    }
}
