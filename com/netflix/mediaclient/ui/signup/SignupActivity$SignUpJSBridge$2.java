// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.Log;

class SignupActivity$SignUpJSBridge$2 implements Runnable
{
    final /* synthetic */ SignupActivity$SignUpJSBridge this$1;
    
    SignupActivity$SignUpJSBridge$2(final SignupActivity$SignUpJSBridge this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        Log.d("SignupActivity", "Disabling webview visibility");
        this.this$1.this$0.webViewVisibility(false);
    }
}
