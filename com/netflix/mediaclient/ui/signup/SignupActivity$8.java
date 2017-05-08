// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class SignupActivity$8 extends SimpleManagerCallback
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$8(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onLoginComplete(final Status status) {
        this.this$0.runOnUiThread((Runnable)new SignupActivity$8$1(this, status));
    }
}
