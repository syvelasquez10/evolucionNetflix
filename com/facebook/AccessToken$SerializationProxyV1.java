// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.List;
import java.util.Date;
import java.io.Serializable;

class AccessToken$SerializationProxyV1 implements Serializable
{
    private static final long serialVersionUID = -2488473066578201069L;
    private final Date expires;
    private final Date lastRefresh;
    private final List<String> permissions;
    private final AccessTokenSource source;
    private final String token;
    
    private AccessToken$SerializationProxyV1(final String token, final Date expires, final List<String> permissions, final AccessTokenSource source, final Date lastRefresh) {
        this.expires = expires;
        this.permissions = permissions;
        this.token = token;
        this.source = source;
        this.lastRefresh = lastRefresh;
    }
    
    private Object readResolve() {
        return new AccessToken(this.token, this.expires, this.permissions, null, this.source, this.lastRefresh);
    }
}
