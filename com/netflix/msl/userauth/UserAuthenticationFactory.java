// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.userauth;

import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.tokens.MslUser;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.util.MslContext;

public abstract class UserAuthenticationFactory
{
    private final UserAuthenticationScheme scheme;
    
    protected UserAuthenticationFactory(final UserAuthenticationScheme scheme) {
        this.scheme = scheme;
    }
    
    public abstract MslUser authenticate(final MslContext p0, final String p1, final UserAuthenticationData p2, final UserIdToken p3);
    
    public abstract UserAuthenticationData createData(final MslContext p0, final MasterToken p1, final JSONObject p2);
    
    public UserAuthenticationScheme getScheme() {
        return this.scheme;
    }
}
