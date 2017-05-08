// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.signin;

import com.netflix.mediaclient.service.logging.signin.model.CredentialStoreSessionEnded;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;

public class CredentialStoreSession extends BaseSignInSession
{
    public static final String NAME = "credentialStore";
    private SignInLogging$CredentialService mCredentialService;
    
    public CredentialStoreSession(final SignInLogging$CredentialService mCredentialService) {
        if (mCredentialService == null) {
            throw new IllegalStateException("Missing credential service");
        }
        this.mCredentialService = mCredentialService;
    }
    
    public CredentialStoreSessionEnded createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        final CredentialStoreSessionEnded credentialStoreSessionEnded = new CredentialStoreSessionEnded(clientLogging$CompletionReason, error, this.mCredentialService);
        credentialStoreSessionEnded.setCategory(this.getCategory());
        return credentialStoreSessionEnded;
    }
    
    @Override
    public String getName() {
        return "credentialStore";
    }
}
