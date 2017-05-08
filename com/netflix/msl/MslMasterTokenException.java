// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.MasterToken;

public class MslMasterTokenException extends MslException
{
    private static final long serialVersionUID = -3151662441952286016L;
    
    public MslMasterTokenException(final MslError mslError, final MasterToken masterToken) {
        super(mslError);
        this.setMasterToken(masterToken);
    }
    
    public MslMasterTokenException(final MslError mslError, final MasterToken masterToken, final Throwable t) {
        super(mslError, t);
        this.setMasterToken(masterToken);
    }
    
    @Override
    public MslMasterTokenException setMessageId(final long messageId) {
        super.setMessageId(messageId);
        return this;
    }
    
    @Override
    public MslMasterTokenException setUserAuthenticationData(final UserAuthenticationData userAuthenticationData) {
        super.setUserAuthenticationData(userAuthenticationData);
        return this;
    }
    
    @Override
    public MslMasterTokenException setUserIdToken(final UserIdToken userIdToken) {
        super.setUserIdToken(userIdToken);
        return this;
    }
}
