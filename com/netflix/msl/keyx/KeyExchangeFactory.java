// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;

public abstract class KeyExchangeFactory
{
    private final KeyExchangeScheme scheme;
    
    protected KeyExchangeFactory(final KeyExchangeScheme scheme) {
        this.scheme = scheme;
    }
    
    protected abstract KeyRequestData createRequestData(final MslContext p0, final JSONObject p1);
    
    protected abstract KeyResponseData createResponseData(final MslContext p0, final MasterToken p1, final JSONObject p2);
    
    public abstract KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext p0, final KeyRequestData p1, final EntityAuthenticationData p2);
    
    public abstract KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext p0, final KeyRequestData p1, final MasterToken p2);
    
    public abstract ICryptoContext getCryptoContext(final MslContext p0, final KeyRequestData p1, final KeyResponseData p2, final MasterToken p3);
    
    public KeyExchangeScheme getScheme() {
        return this.scheme;
    }
}
