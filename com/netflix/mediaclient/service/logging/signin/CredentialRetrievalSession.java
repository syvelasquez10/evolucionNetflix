// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.signin;

import com.netflix.mediaclient.service.logging.signin.model.CredentialRetrievalSessionEnded;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;

public class CredentialRetrievalSession extends BaseSignInSession
{
    public static final String NAME = "credentialRetrieval";
    private SignInLogging$CredentialService mCredentialService;
    
    public CredentialRetrievalSession(final SignInLogging$CredentialService mCredentialService) {
        if (mCredentialService == null) {
            throw new IllegalStateException("Missing credential service");
        }
        this.mCredentialService = mCredentialService;
    }
    
    public CredentialRetrievalSessionEnded createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        final CredentialRetrievalSessionEnded credentialRetrievalSessionEnded = new CredentialRetrievalSessionEnded(clientLogging$CompletionReason, error, this.mCredentialService);
        credentialRetrievalSessionEnded.setCategory(this.getCategory());
        return credentialRetrievalSessionEnded;
    }
    
    @Override
    public String getName() {
        return "credentialRetrieval";
    }
}
