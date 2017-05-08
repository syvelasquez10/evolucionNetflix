// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;

class UserAgent$8 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent this$0;
    
    UserAgent$8(final UserAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAutologinCompleted(final ActivationTokens activationTokens, final Status status) {
        if (status.isSucces() && activationTokens != null) {
            Log.d("nf_service_useragent", "Autlogin success, go token activate");
            activationTokens.autoLoginSource = true;
            this.this$0.tokenActivate(activationTokens, null);
            return;
        }
        SignInLogUtils.reportSignInRequestSessionEnded(this.this$0.getContext(), SignInLogging$SignInType.autologin, IClientLogging$CompletionReason.failed, status.getError());
        Log.e("nf_service_useragent", "Autologin failed " + status);
    }
}
