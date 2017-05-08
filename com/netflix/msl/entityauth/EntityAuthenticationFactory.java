// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;

public abstract class EntityAuthenticationFactory
{
    private final EntityAuthenticationScheme scheme;
    
    protected EntityAuthenticationFactory(final EntityAuthenticationScheme scheme) {
        this.scheme = scheme;
    }
    
    public abstract EntityAuthenticationData createData(final MslContext p0, final JSONObject p1);
    
    public abstract ICryptoContext getCryptoContext(final MslContext p0, final EntityAuthenticationData p1);
    
    public EntityAuthenticationScheme getScheme() {
        return this.scheme;
    }
}
