// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public class MslKeyExchangeException extends MslException
{
    private static final long serialVersionUID = -1272784987270064773L;
    
    public MslKeyExchangeException(final MslError mslError) {
        super(mslError);
    }
    
    public MslKeyExchangeException(final MslError mslError, final String s) {
        super(mslError, s);
    }
    
    public MslKeyExchangeException(final MslError mslError, final String s, final Throwable t) {
        super(mslError, s, t);
    }
    
    public MslKeyExchangeException(final MslError mslError, final Throwable t) {
        super(mslError, t);
    }
    
    @Override
    public MslKeyExchangeException setEntityAuthenticationData(final EntityAuthenticationData entityAuthenticationData) {
        super.setEntityAuthenticationData(entityAuthenticationData);
        return this;
    }
    
    @Override
    public MslKeyExchangeException setMasterToken(final MasterToken masterToken) {
        super.setMasterToken(masterToken);
        return this;
    }
    
    @Override
    public MslKeyExchangeException setUserAuthenticationData(final UserAuthenticationData userAuthenticationData) {
        super.setUserAuthenticationData(userAuthenticationData);
        return this;
    }
    
    @Override
    public MslKeyExchangeException setUserIdToken(final UserIdToken userIdToken) {
        super.setUserIdToken(userIdToken);
        return this;
    }
}
