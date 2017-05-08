// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import com.netflix.msl.userauth.UserAuthenticationScheme;
import com.netflix.msl.tokens.MslUser;
import com.netflix.msl.keyx.KeyExchangeScheme;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;

public interface AuthenticationUtils
{
    boolean isEntityRevoked(final String p0);
    
    boolean isSchemePermitted(final String p0, final EntityAuthenticationScheme p1);
    
    boolean isSchemePermitted(final String p0, final KeyExchangeScheme p1);
    
    boolean isSchemePermitted(final String p0, final MslUser p1, final UserAuthenticationScheme p2);
    
    boolean isSchemePermitted(final String p0, final UserAuthenticationScheme p1);
}
