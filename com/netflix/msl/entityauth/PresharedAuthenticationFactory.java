// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.msl.crypto.SymmetricCryptoContext;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.util.AuthenticationUtils;

public class PresharedAuthenticationFactory extends EntityAuthenticationFactory
{
    private final AuthenticationUtils authutils;
    private final PresharedKeyStore store;
    
    public PresharedAuthenticationFactory(final PresharedKeyStore store, final AuthenticationUtils authutils) {
        super(EntityAuthenticationScheme.PSK);
        this.store = store;
        this.authutils = authutils;
    }
    
    @Override
    public EntityAuthenticationData createData(final MslContext mslContext, final JSONObject jsonObject) {
        return new PresharedAuthenticationData(jsonObject);
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final EntityAuthenticationData entityAuthenticationData) {
        if (!(entityAuthenticationData instanceof PresharedAuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + entityAuthenticationData.getClass().getName() + ".");
        }
        final PresharedAuthenticationData entityAuthenticationData2 = (PresharedAuthenticationData)entityAuthenticationData;
        final String identity = entityAuthenticationData2.getIdentity();
        if (this.authutils.isEntityRevoked(identity)) {
            throw new MslEntityAuthException(MslError.ENTITY_REVOKED, "psk " + identity).setEntityAuthenticationData(entityAuthenticationData2);
        }
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslEntityAuthException(MslError.INCORRECT_ENTITYAUTH_DATA, "Authentication Scheme for Device Type Not Supported " + identity + ":" + this.getScheme()).setEntityAuthenticationData(entityAuthenticationData2);
        }
        final PresharedKeyStore$KeySet keys = this.store.getKeys(identity);
        if (keys == null) {
            throw new MslEntityAuthException(MslError.ENTITY_NOT_FOUND, "psk " + identity).setEntityAuthenticationData(entityAuthenticationData2);
        }
        return new SymmetricCryptoContext(mslContext, identity, keys.encryptionKey, keys.hmacKey, keys.wrappingKey);
    }
}
