// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.Log;

class SignupActivity$NFAndroidJS$2 implements Runnable
{
    final /* synthetic */ SignupActivity$NFAndroidJS this$1;
    
    SignupActivity$NFAndroidJS$2(final SignupActivity$NFAndroidJS this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        Log.d("SignupActivity", "Disabling webview visibility");
        this.this$1.this$0.webViewVisibility(false);
    }
}
