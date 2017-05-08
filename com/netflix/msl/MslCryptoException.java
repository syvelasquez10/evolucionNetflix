// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public class MslCryptoException extends MslException
{
    private static final long serialVersionUID = -7618578454440397528L;
    
    public MslCryptoException(final MslError mslError) {
        super(mslError);
    }
    
    public MslCryptoException(final MslError mslError, final String s) {
        super(mslError, s);
    }
    
    public MslCryptoException(final MslError mslError, final String s, final Throwable t) {
        super(mslError, s, t);
    }
    
    public MslCryptoException(final MslError mslError, final Throwable t) {
        super(mslError, t);
    }
    
    @Override
    public MslCryptoException setEntityAuthenticationData(final EntityAuthenticationData entityAuthenticationData) {
        super.setEntityAuthenticationData(entityAuthenticationData);
        return this;
    }
    
    @Override
    public MslCryptoException setMasterToken(final MasterToken masterToken) {
        super.setMasterToken(masterToken);
        return this;
    }
}
