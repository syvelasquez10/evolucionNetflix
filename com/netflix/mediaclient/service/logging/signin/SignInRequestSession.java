// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.signin;

import com.netflix.mediaclient.service.logging.signin.model.SignInRequestSessionEnded;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialSaved;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;

public class SignInRequestSession extends BaseSignInSession
{
    public static final String NAME = "signInRequest";
    private SignInLogging$SignInType mSignInType;
    
    public SignInRequestSession(final SignInLogging$SignInType mSignInType) {
        if (mSignInType == null) {
            throw new IllegalStateException("SignIn type is missing");
        }
        this.mSignInType = mSignInType;
    }
    
    public SignInRequestSessionEnded createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error, final SignInLogging$CredentialSaved signInLogging$CredentialSaved) {
        final SignInRequestSessionEnded signInRequestSessionEnded = new SignInRequestSessionEnded(this.mSignInType, clientLogging$CompletionReason, error, signInLogging$CredentialSaved);
        signInRequestSessionEnded.setCategory(this.getCategory());
        return signInRequestSessionEnded;
    }
    
    @Override
    public String getName() {
        return "signInRequest";
    }
}
