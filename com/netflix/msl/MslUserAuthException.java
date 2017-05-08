// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.MasterToken;

public class MslUserAuthException extends MslException
{
    private static final long serialVersionUID = 3836512629362408424L;
    
    public MslUserAuthException(final MslError mslError) {
        super(mslError);
    }
    
    public MslUserAuthException(final MslError mslError, final String s) {
        super(mslError, s);
    }
    
    public MslUserAuthException(final MslError mslError, final String s, final Throwable t) {
        super(mslError, s, t);
    }
    
    public MslUserAuthException(final MslError mslError, final Throwable t) {
        super(mslError, t);
    }
    
    @Override
    public MslUserAuthException setMasterToken(final MasterToken masterToken) {
        super.setMasterToken(masterToken);
        return this;
    }
    
    @Override
    public MslUserAuthException setUserAuthenticationData(final UserAuthenticationData userAuthenticationData) {
        super.setUserAuthenticationData(userAuthenticationData);
        return this;
    }
}
