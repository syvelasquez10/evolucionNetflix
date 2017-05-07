// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.List;
import java.io.Serializable;

class Session$AuthorizationRequest$AuthRequestSerializationProxyV1 implements Serializable
{
    private static final long serialVersionUID = -8748347685113614927L;
    private final String applicationId;
    private final String defaultAudience;
    private final boolean isLegacy;
    private final SessionLoginBehavior loginBehavior;
    private final List<String> permissions;
    private final int requestCode;
    private final String validateSameFbidAsToken;
    
    private Session$AuthorizationRequest$AuthRequestSerializationProxyV1(final SessionLoginBehavior loginBehavior, final int requestCode, final List<String> permissions, final String defaultAudience, final boolean isLegacy, final String applicationId, final String validateSameFbidAsToken) {
        this.loginBehavior = loginBehavior;
        this.requestCode = requestCode;
        this.permissions = permissions;
        this.defaultAudience = defaultAudience;
        this.isLegacy = isLegacy;
        this.applicationId = applicationId;
        this.validateSameFbidAsToken = validateSameFbidAsToken;
    }
    
    private Object readResolve() {
        return new Session$AuthorizationRequest(this.loginBehavior, this.requestCode, this.permissions, this.defaultAudience, this.isLegacy, this.applicationId, this.validateSameFbidAsToken, null);
    }
}
