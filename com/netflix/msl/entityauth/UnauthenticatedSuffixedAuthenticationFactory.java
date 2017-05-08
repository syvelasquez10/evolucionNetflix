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

public class UnauthenticatedSuffixedAuthenticationFactory extends EntityAuthenticationFactory
{
    final AuthenticationUtils authutils;
    
    public UnauthenticatedSuffixedAuthenticationFactory(final AuthenticationUtils authutils) {
        super(EntityAuthenticationScheme.NONE_SUFFIXED);
        this.authutils = authutils;
    }
    
    @Override
    public EntityAuthenticationData createData(final MslContext mslContext, final JSONObject jsonObject) {
        return new UnauthenticatedSuffixedAuthenticationData(jsonObject);
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final EntityAuthenticationData entityAuthenticationData) {
        if (!(entityAuthenticationData instanceof UnauthenticatedSuffixedAuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + entityAuthenticationData.getClass().getName() + ".");
        }
        final UnauthenticatedSuffixedAuthenticationData unauthenticatedSuffixedAuthenticationData = (UnauthenticatedSuffixedAuthenticationData)entityAuthenticationData;
        final String root = unauthenticatedSuffixedAuthenticationData.getRoot();
        if (this.authutils.isEntityRevoked(root)) {
            throw new MslEntityAuthException(MslError.ENTITY_REVOKED, "none suffixed " + root).setEntityAuthenticationData(unauthenticatedSuffixedAuthenticationData);
        }
        if (!this.authutils.isSchemePermitted(root, this.getScheme())) {
            throw new MslEntityAuthException(MslError.INCORRECT_ENTITYAUTH_DATA, "Authentication Scheme for Device Type Not Supported " + root + ":" + this.getScheme()).setEntityAuthenticationData(unauthenticatedSuffixedAuthenticationData);
        }
        return new NullCryptoContext();
    }
}
