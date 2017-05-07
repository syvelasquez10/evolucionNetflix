// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;

class UserAgent$6 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent this$0;
    
    UserAgent$6(final UserAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAutologinCompleted(final ActivationTokens activationTokens, final Status status) {
        if (status.isSucces() && activationTokens != null) {
            Log.d("nf_service_useragent", "Autlogin success, go token activate");
            this.this$0.tokenActivate(activationTokens, null);
            return;
        }
        Log.e("nf_service_useragent", "Autologin failed " + status);
    }
}
