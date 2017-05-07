// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.launch;

import com.netflix.mediaclient.android.app.Status;
import com.google.android.gms.auth.api.credentials.Credential;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class LaunchActivity$6 extends SimpleManagerCallback
{
    final /* synthetic */ LaunchActivity this$0;
    final /* synthetic */ Credential val$credential;
    
    LaunchActivity$6(final LaunchActivity this$0, final Credential val$credential) {
        this.this$0 = this$0;
        this.val$credential = val$credential;
    }
    
    @Override
    public void onLoginComplete(final Status status) {
        this.this$0.runOnUiThread((Runnable)new LaunchActivity$6$1(this, status));
    }
}
