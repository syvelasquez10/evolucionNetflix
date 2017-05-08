// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.content.Context;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.Log;

class SignupActivity$10 implements Runnable
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$10(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("SignupActivity", "Handling error during signup");
        this.this$0.clearCookies();
        this.this$0.startNextActivity(LoginActivity.createStartIntent((Context)this.this$0));
        this.this$0.finish();
    }
}
