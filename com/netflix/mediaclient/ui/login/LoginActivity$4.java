// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class LoginActivity$4 extends SimpleManagerCallback
{
    final /* synthetic */ LoginActivity this$0;
    
    LoginActivity$4(final LoginActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onLoginComplete(final Status status) {
        this.this$0.runOnUiThread((Runnable)new LoginActivity$4$1(this, status));
    }
}