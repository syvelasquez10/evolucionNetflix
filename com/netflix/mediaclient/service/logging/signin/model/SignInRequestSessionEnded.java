// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.signin.model;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialSaved;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class SignInRequestSessionEnded extends SessionEndedEvent
{
    public static final String CREDENTIAL_SAVED = "credentialSaved";
    public static final String ERROR = "error";
    public static final String REASON = "reason";
    private static final String SESSION_NAME = "signInRequest";
    public static final String SIGN_IN_TYPE = "signInType";
    private IClientLogging$CompletionReason mCompletionReason;
    private SignInLogging$CredentialSaved mCredentialSavedState;
    private Error mError;
    private SignInLogging$SignInType mSignInType;
    
    public SignInRequestSessionEnded(final SignInLogging$SignInType mSignInType, final IClientLogging$CompletionReason mCompletionReason, final Error mError, final SignInLogging$CredentialSaved mCredentialSavedState) {
        super("signInRequest");
        if (mCompletionReason == null) {
            throw new IllegalStateException("Completion reason is missing");
        }
        if (mSignInType == null) {
            throw new IllegalStateException("SignIn type is missing");
        }
        this.mCompletionReason = mCompletionReason;
        this.mError = mError;
        this.mSignInType = mSignInType;
        this.mCredentialSavedState = mCredentialSavedState;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("signInType", (Object)this.mSignInType);
        data.put("reason", (Object)this.mCompletionReason.name());
        if (this.mError != null) {
            data.put("error", (Object)this.mError.toJSONObject());
        }
        if (this.mCredentialSavedState != null) {
            data.put("credentialSaved", (Object)this.mCredentialSavedState);
        }
        return data;
    }
}
