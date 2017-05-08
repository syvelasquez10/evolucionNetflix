// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.Log;

class SignupActivity$3 implements Runnable
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$3(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("SignupActivity", "Disabling webview visibility");
        this.this$0.webViewVisibility(false);
    }
}
