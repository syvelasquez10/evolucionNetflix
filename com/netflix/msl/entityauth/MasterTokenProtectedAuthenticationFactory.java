// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.util.AuthenticationUtils;

public class MasterTokenProtectedAuthenticationFactory extends EntityAuthenticationFactory
{
    final AuthenticationUtils authutils;
    
    public MasterTokenProtectedAuthenticationFactory(final AuthenticationUtils authutils) {
        super(EntityAuthenticationScheme.MT_PROTECTED);
        this.authutils = authutils;
    }
    
    @Override
    public EntityAuthenticationData createData(final MslContext mslContext, final JSONObject jsonObject) {
        return new MasterTokenProtectedAuthenticationData(mslContext, jsonObject);
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final EntityAuthenticationData entityAuthenticationData) {
        if (!(entityAuthenticationData instanceof MasterTokenProtectedAuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + entityAuthenticationData.getClass().getName() + ".");
        }
        final MasterTokenProtectedAuthenticationData entityAuthenticationData2 = (MasterTokenProtectedAuthenticationData)entityAuthenticationData;
        final String identity = entityAuthenticationData2.getIdentity();
        if (this.authutils.isEntityRevoked(identity)) {
            throw new MslEntityAuthException(MslError.ENTITY_REVOKED, "none " + identity).setEntityAuthenticationData(entityAuthenticationData2);
        }
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslEntityAuthException(MslError.INCORRECT_ENTITYAUTH_DATA, "Authentication Scheme for Device Type Not Supported " + identity + ":" + this.getScheme()).setEntityAuthenticationData(entityAuthenticationData2);
        }
        final EntityAuthenticationData encapsulatedAuthdata = entityAuthenticationData2.getEncapsulatedAuthdata();
        final EntityAuthenticationScheme scheme = encapsulatedAuthdata.getScheme();
        final EntityAuthenticationFactory entityAuthenticationFactory = mslContext.getEntityAuthenticationFactory(scheme);
        if (entityAuthenticationFactory == null) {
            throw new MslEntityAuthException(MslError.ENTITYAUTH_FACTORY_NOT_FOUND, scheme.name()).setEntityAuthenticationData(entityAuthenticationData2);
        }
        return entityAuthenticationFactory.getCryptoContext(mslContext, encapsulatedAuthdata);
    }
}
