// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.io.Serializable;

class AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1 implements Serializable
{
    private static final long serialVersionUID = -2488473066578201069L;
    private final String accessToken;
    private final String appId;
    
    private AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1(final String accessToken, final String appId) {
        this.accessToken = accessToken;
        this.appId = appId;
    }
    
    private Object readResolve() {
        return new AppEventsLogger$AccessTokenAppIdPair(this.accessToken, this.appId);
    }
}
