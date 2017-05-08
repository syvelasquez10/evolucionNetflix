// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.tokens.UserIdToken;

public class MslUserIdTokenException extends MslException
{
    private static final long serialVersionUID = 8796880393236563071L;
    
    public MslUserIdTokenException(final MslError mslError, final UserIdToken userIdToken) {
        super(mslError);
        this.setUserIdToken(userIdToken);
    }
    
    public MslUserIdTokenException(final MslError mslError, final UserIdToken userIdToken, final String s) {
        super(mslError, s);
        this.setUserIdToken(userIdToken);
    }
    
    public MslUserIdTokenException(final MslError mslError, final UserIdToken userIdToken, final String s, final Throwable t) {
        super(mslError, s, t);
        this.setUserIdToken(userIdToken);
    }
    
    @Override
    public MslUserIdTokenException setEntityAuthenticationData(final EntityAuthenticationData entityAuthenticationData) {
        super.setEntityAuthenticationData(entityAuthenticationData);
        return this;
    }
    
    @Override
    public MslUserIdTokenException setMasterToken(final MasterToken masterToken) {
        super.setMasterToken(masterToken);
        return this;
    }
    
    @Override
    public MslUserIdTokenException setMessageId(final long messageId) {
        super.setMessageId(messageId);
        return this;
    }
}
