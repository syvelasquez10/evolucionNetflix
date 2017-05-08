// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.client.model.Error;

public interface SignInLogging
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_SIGNIN_START", "com.netflix.mediaclient.intent.action.LOG_SIGNIN_ENDED", "com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_RETRIEVAL_START", "com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_RETRIEVAL_ENDED", "com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_SAVE_START", "com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_SAVE_ENDED" };
    public static final String CREDENTIAL_RETRIEVAL_ENDED = "com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_RETRIEVAL_ENDED";
    public static final String CREDENTIAL_RETRIEVAL_START = "com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_RETRIEVAL_START";
    public static final String CREDENTIAL_SAVE_ENDED = "com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_SAVE_ENDED";
    public static final String CREDENTIAL_SAVE_START = "com.netflix.mediaclient.intent.action.LOG_SIGNIN_CREDENTIAL_SAVE_START";
    public static final String EXTRA_CRED_SAVED = "credSavedState";
    public static final String EXTRA_CRED_SERVICE = "credSavedService";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_REASON = "reason";
    public static final String EXTRA_TYPE = "type";
    public static final String SIGNIN_ENDED = "com.netflix.mediaclient.intent.action.LOG_SIGNIN_ENDED";
    public static final String SIGNIN_START = "com.netflix.mediaclient.intent.action.LOG_SIGNIN_START";
    
    void endAllActiveSessions();
    
    void endCredentialRetrieval(final IClientLogging$CompletionReason p0, final Error p1);
    
    void endCredentialStore(final IClientLogging$CompletionReason p0, final Error p1);
    
    void endSignInRequest(final SignInLogging$SignInType p0, final IClientLogging$CompletionReason p1, final Error p2, final SignInLogging$CredentialSaved p3);
    
    void startCredentialRetrieval(final SignInLogging$CredentialService p0);
    
    void startCredentialStore(final SignInLogging$CredentialService p0);
    
    void startSignInRequest(final SignInLogging$SignInType p0);
}
