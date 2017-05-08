// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public class MslEntityAuthException extends MslException
{
    private static final long serialVersionUID = 5335550727677217303L;
    
    public MslEntityAuthException(final MslError mslError) {
        super(mslError);
    }
    
    public MslEntityAuthException(final MslError mslError, final String s) {
        super(mslError, s);
    }
    
    public MslEntityAuthException(final MslError mslError, final String s, final Throwable t) {
        super(mslError, s, t);
    }
    
    public MslEntityAuthException(final MslError mslError, final Throwable t) {
        super(mslError, t);
    }
    
    @Override
    public MslEntityAuthException setEntityAuthenticationData(final EntityAuthenticationData entityAuthenticationData) {
        super.setEntityAuthenticationData(entityAuthenticationData);
        return this;
    }
    
    @Override
    public MslEntityAuthException setMasterToken(final MasterToken masterToken) {
        super.setMasterToken(masterToken);
        return this;
    }
}
