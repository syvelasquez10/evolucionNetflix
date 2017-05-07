// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Date;
import java.io.Serializable;

class Session$SerializationProxyV1 implements Serializable
{
    private static final long serialVersionUID = 7663436173185080063L;
    private final String applicationId;
    private final Date lastAttemptedTokenExtendDate;
    private final Session$AuthorizationRequest pendingRequest;
    private final boolean shouldAutoPublish;
    private final SessionState state;
    private final AccessToken tokenInfo;
    
    Session$SerializationProxyV1(final String applicationId, final SessionState state, final AccessToken tokenInfo, final Date lastAttemptedTokenExtendDate, final boolean shouldAutoPublish, final Session$AuthorizationRequest pendingRequest) {
        this.applicationId = applicationId;
        this.state = state;
        this.tokenInfo = tokenInfo;
        this.lastAttemptedTokenExtendDate = lastAttemptedTokenExtendDate;
        this.shouldAutoPublish = shouldAutoPublish;
        this.pendingRequest = pendingRequest;
    }
    
    private Object readResolve() {
        return new Session(this.applicationId, this.state, this.tokenInfo, this.lastAttemptedTokenExtendDate, this.shouldAutoPublish, this.pendingRequest, null);
    }
}
