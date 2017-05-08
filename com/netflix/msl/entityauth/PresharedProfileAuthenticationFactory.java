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

public class PresharedProfileAuthenticationFactory extends EntityAuthenticationFactory
{
    private final AuthenticationUtils authutils;
    private final PresharedKeyStore store;
    
    public PresharedProfileAuthenticationFactory(final PresharedKeyStore store, final AuthenticationUtils authutils) {
        super(EntityAuthenticationScheme.PSK_PROFILE);
        this.store = store;
        this.authutils = authutils;
    }
    
    @Override
    public EntityAuthenticationData createData(final MslContext mslContext, final JSONObject jsonObject) {
        return new PresharedProfileAuthenticationData(jsonObject);
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final EntityAuthenticationData entityAuthenticationData) {
        if (!(entityAuthenticationData instanceof PresharedProfileAuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + entityAuthenticationData.getClass().getName() + ".");
        }
        final PresharedProfileAuthenticationData entityAuthenticationData2 = (PresharedProfileAuthenticationData)entityAuthenticationData;
        final String presharedKeysId = entityAuthenticationData2.getPresharedKeysId();
        if (this.authutils.isEntityRevoked(presharedKeysId)) {
            throw new MslEntityAuthException(MslError.ENTITY_REVOKED, "psk profile " + presharedKeysId).setEntityAuthenticationData(entityAuthenticationData2);
        }
        if (!this.authutils.isSchemePermitted(presharedKeysId, this.getScheme())) {
            throw new MslEntityAuthException(MslError.INCORRECT_ENTITYAUTH_DATA, "Authentication Scheme for Device Type Not Supported " + presharedKeysId + ":" + this.getScheme()).setEntityAuthenticationData(entityAuthenticationData2);
        }
        final PresharedKeyStore$KeySet keys = this.store.getKeys(presharedKeysId);
        if (keys == null) {
            throw new MslEntityAuthException(MslError.ENTITY_NOT_FOUND, "psk profile " + presharedKeysId).setEntityAuthenticationData(entityAuthenticationData2);
        }
        return new SymmetricCryptoContext(mslContext, entityAuthenticationData2.getIdentity(), keys.encryptionKey, keys.hmacKey, keys.wrappingKey);
    }
}
