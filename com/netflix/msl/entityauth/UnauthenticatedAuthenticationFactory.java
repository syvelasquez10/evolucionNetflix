// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.msl.crypto.NullCryptoContext;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.util.AuthenticationUtils;

public class UnauthenticatedAuthenticationFactory extends EntityAuthenticationFactory
{
    final AuthenticationUtils authutils;
    
    public UnauthenticatedAuthenticationFactory(final AuthenticationUtils authutils) {
        super(EntityAuthenticationScheme.NONE);
        this.authutils = authutils;
    }
    
    @Override
    public EntityAuthenticationData createData(final MslContext mslContext, final JSONObject jsonObject) {
        return new UnauthenticatedAuthenticationData(jsonObject);
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final EntityAuthenticationData entityAuthenticationData) {
        if (!(entityAuthenticationData instanceof UnauthenticatedAuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + entityAuthenticationData.getClass().getName() + ".");
        }
        final UnauthenticatedAuthenticationData unauthenticatedAuthenticationData = (UnauthenticatedAuthenticationData)entityAuthenticationData;
        final String identity = unauthenticatedAuthenticationData.getIdentity();
        if (this.authutils.isEntityRevoked(identity)) {
            throw new MslEntityAuthException(MslError.ENTITY_REVOKED, "none " + identity).setEntityAuthenticationData(unauthenticatedAuthenticationData);
        }
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslEntityAuthException(MslError.INCORRECT_ENTITYAUTH_DATA, "Authentication Scheme for Device Type Not Supported " + identity + ":" + this.getScheme()).setEntityAuthenticationData(unauthenticatedAuthenticationData);
        }
        return new NullCryptoContext();
    }
}
