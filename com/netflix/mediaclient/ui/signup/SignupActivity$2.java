// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.Log;

class SignupActivity$2 implements Runnable
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$2(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("SignupActivity", "Disabling webview visibility");
        this.this$0.webViewVisibility(false);
    }
}
