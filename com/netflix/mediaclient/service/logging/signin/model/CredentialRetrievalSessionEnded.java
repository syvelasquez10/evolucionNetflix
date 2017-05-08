// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.signin.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class CredentialRetrievalSessionEnded extends SessionEndedEvent
{
    public static final String CREDENTIAL_SERVICE = "credentialService";
    public static final String ERROR = "error";
    public static final String REASON = "reason";
    private static final String SESSION_NAME = "credentialRetrieval";
    private IClientLogging$CompletionReason mCompletionReason;
    private SignInLogging$CredentialService mCredentialService;
    private Error mError;
    
    public CredentialRetrievalSessionEnded(final IClientLogging$CompletionReason mCompletionReason, final Error mError, final SignInLogging$CredentialService mCredentialService) {
        super("credentialRetrieval");
        if (mCompletionReason == null) {
            throw new IllegalStateException("Completion reason is missing");
        }
        if (mCredentialService == null) {
            throw new IllegalStateException("Credential service is missing");
        }
        this.mCompletionReason = mCompletionReason;
        this.mError = mError;
        this.mCredentialService = mCredentialService;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("reason", (Object)this.mCompletionReason.name());
        data.put("credentialService", (Object)this.mCredentialService);
        if (this.mError != null) {
            data.put("error", (Object)this.mError.toJSONObject());
        }
        return data;
    }
}
