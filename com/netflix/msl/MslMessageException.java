// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public class MslMessageException extends MslException
{
    private static final long serialVersionUID = 8022562525891870639L;
    
    public MslMessageException(final MslError mslError) {
        super(mslError);
    }
    
    public MslMessageException(final MslError mslError, final String s) {
        super(mslError, s);
    }
    
    public MslMessageException(final MslError mslError, final String s, final Throwable t) {
        super(mslError, s, t);
    }
    
    @Override
    public MslMessageException setEntityAuthenticationData(final EntityAuthenticationData entityAuthenticationData) {
        super.setEntityAuthenticationData(entityAuthenticationData);
        return this;
    }
    
    @Override
    public MslMessageException setMasterToken(final MasterToken masterToken) {
        super.setMasterToken(masterToken);
        return this;
    }
    
    @Override
    public MslMessageException setMessageId(final long messageId) {
        super.setMessageId(messageId);
        return this;
    }
    
    @Override
    public MslMessageException setUserIdToken(final UserIdToken userIdToken) {
        super.setUserIdToken(userIdToken);
        return this;
    }
}
