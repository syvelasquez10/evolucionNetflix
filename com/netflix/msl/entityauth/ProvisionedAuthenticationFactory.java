// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.msl.crypto.NullCryptoContext;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;

public class ProvisionedAuthenticationFactory extends EntityAuthenticationFactory
{
    final ProvisionedAuthenticationFactory$IdentityProvisioningService service;
    
    public ProvisionedAuthenticationFactory(final ProvisionedAuthenticationFactory$IdentityProvisioningService service) {
        super(EntityAuthenticationScheme.PROVISIONED);
        this.service = service;
    }
    
    @Override
    public EntityAuthenticationData createData(final MslContext mslContext, final JSONObject jsonObject) {
        return new ProvisionedAuthenticationData(jsonObject);
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final EntityAuthenticationData entityAuthenticationData) {
        if (!(entityAuthenticationData instanceof ProvisionedAuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + entityAuthenticationData.getClass().getName() + ".");
        }
        ((ProvisionedAuthenticationData)entityAuthenticationData).setIdentity(this.service.nextIdentity());
        return new NullCryptoContext();
    }
}
