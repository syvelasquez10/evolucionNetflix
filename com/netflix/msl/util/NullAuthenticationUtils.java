// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import com.netflix.msl.userauth.UserAuthenticationScheme;
import com.netflix.msl.tokens.MslUser;
import com.netflix.msl.keyx.KeyExchangeScheme;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;

public class NullAuthenticationUtils implements AuthenticationUtils
{
    @Override
    public boolean isEntityRevoked(final String s) {
        return false;
    }
    
    @Override
    public boolean isSchemePermitted(final String s, final EntityAuthenticationScheme entityAuthenticationScheme) {
        return true;
    }
    
    @Override
    public boolean isSchemePermitted(final String s, final KeyExchangeScheme keyExchangeScheme) {
        return true;
    }
    
    @Override
    public boolean isSchemePermitted(final String s, final MslUser mslUser, final UserAuthenticationScheme userAuthenticationScheme) {
        return true;
    }
    
    @Override
    public boolean isSchemePermitted(final String s, final UserAuthenticationScheme userAuthenticationScheme) {
        return true;
    }
}
