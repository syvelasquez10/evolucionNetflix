// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import java.security.PrivateKey;
import java.security.PublicKey;
import com.netflix.msl.crypto.RsaCryptoContext;
import com.netflix.msl.crypto.RsaCryptoContext$Mode;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.util.AuthenticationUtils;

public class RsaAuthenticationFactory extends EntityAuthenticationFactory
{
    final AuthenticationUtils authutils;
    private final String keyPairId;
    private final RsaStore store;
    
    public RsaAuthenticationFactory(final RsaStore rsaStore, final AuthenticationUtils authenticationUtils) {
        this(null, rsaStore, authenticationUtils);
    }
    
    public RsaAuthenticationFactory(final String keyPairId, final RsaStore store, final AuthenticationUtils authutils) {
        super(EntityAuthenticationScheme.RSA);
        this.keyPairId = keyPairId;
        this.store = store;
        this.authutils = authutils;
    }
    
    @Override
    public EntityAuthenticationData createData(final MslContext mslContext, final JSONObject jsonObject) {
        return new RsaAuthenticationData(jsonObject);
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final EntityAuthenticationData entityAuthenticationData) {
        if (!(entityAuthenticationData instanceof RsaAuthenticationData)) {
            throw new MslInternalException("Incorrect authentication data type " + entityAuthenticationData.getClass().getName() + ".");
        }
        final RsaAuthenticationData rsaAuthenticationData = (RsaAuthenticationData)entityAuthenticationData;
        final String identity = rsaAuthenticationData.getIdentity();
        if (this.authutils.isEntityRevoked(identity)) {
            throw new MslEntityAuthException(MslError.ENTITY_REVOKED, "rsa " + identity).setEntityAuthenticationData(rsaAuthenticationData);
        }
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslEntityAuthException(MslError.INCORRECT_ENTITYAUTH_DATA, "Authentication Scheme for Device Type Not Supported " + identity + ":" + this.getScheme()).setEntityAuthenticationData(rsaAuthenticationData);
        }
        final String publicKeyId = rsaAuthenticationData.getPublicKeyId();
        final PublicKey publicKey = this.store.getPublicKey(publicKeyId);
        final PrivateKey privateKey = this.store.getPrivateKey(publicKeyId);
        if (publicKeyId.equals(this.keyPairId) && privateKey == null) {
            throw new MslEntityAuthException(MslError.RSA_PRIVATEKEY_NOT_FOUND, publicKeyId).setEntityAuthenticationData(rsaAuthenticationData);
        }
        if (!publicKeyId.equals(this.keyPairId) && publicKey == null) {
            throw new MslEntityAuthException(MslError.RSA_PUBLICKEY_NOT_FOUND, publicKeyId).setEntityAuthenticationData(rsaAuthenticationData);
        }
        return new RsaCryptoContext(mslContext, identity, privateKey, publicKey, RsaCryptoContext$Mode.SIGN_VERIFY);
    }
}
