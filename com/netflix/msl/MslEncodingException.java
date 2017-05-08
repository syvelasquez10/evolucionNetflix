// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public class MslEncodingException extends MslException
{
    private static final long serialVersionUID = -2295976834635986944L;
    
    public MslEncodingException(final MslError mslError) {
        super(mslError);
    }
    
    public MslEncodingException(final MslError mslError, final String s) {
        super(mslError, s);
    }
    
    public MslEncodingException(final MslError mslError, final String s, final Throwable t) {
        super(mslError, s, t);
    }
    
    public MslEncodingException(final MslError mslError, final Throwable t) {
        super(mslError, t);
    }
    
    @Override
    public MslEncodingException setEntityAuthenticationData(final EntityAuthenticationData entityAuthenticationData) {
        super.setEntityAuthenticationData(entityAuthenticationData);
        return this;
    }
    
    @Override
    public MslEncodingException setMasterToken(final MasterToken masterToken) {
        super.setMasterToken(masterToken);
        return this;
    }
    
    @Override
    public MslEncodingException setMessageId(final long messageId) {
        super.setMessageId(messageId);
        return this;
    }
    
    @Override
    public MslEncodingException setUserAuthenticationData(final UserAuthenticationData userAuthenticationData) {
        super.setUserAuthenticationData(userAuthenticationData);
        return this;
    }
    
    @Override
    public MslEncodingException setUserIdToken(final UserIdToken userIdToken) {
        super.setUserIdToken(userIdToken);
        return this;
    }
}
