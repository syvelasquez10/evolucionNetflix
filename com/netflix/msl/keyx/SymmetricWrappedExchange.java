// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.util.MslContext$ReauthCode;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.netflix.msl.MslInternalException;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.entityauth.EntityAuthenticationFactory;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import com.netflix.msl.entityauth.PresharedAuthenticationData;
import com.netflix.msl.crypto.SessionCryptoContext;
import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.MslError;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.util.AuthenticationUtils;

public class SymmetricWrappedExchange extends KeyExchangeFactory
{
    private final AuthenticationUtils authutils;
    
    public SymmetricWrappedExchange(final AuthenticationUtils authutils) {
        super(KeyExchangeScheme.SYMMETRIC_WRAPPED);
        this.authutils = authutils;
    }
    
    private static ICryptoContext createCryptoContext(final MslContext mslContext, final SymmetricWrappedExchange$KeyId symmetricWrappedExchange$KeyId, final MasterToken masterToken, final String s) {
        switch (SymmetricWrappedExchange$1.$SwitchMap$com$netflix$msl$keyx$SymmetricWrappedExchange$KeyId[symmetricWrappedExchange$KeyId.ordinal()]) {
            default: {
                throw new MslKeyExchangeException(MslError.UNSUPPORTED_KEYX_KEY_ID, symmetricWrappedExchange$KeyId.name());
            }
            case 1: {
                if (masterToken == null) {
                    throw new MslKeyExchangeException(MslError.KEYX_MASTER_TOKEN_MISSING, symmetricWrappedExchange$KeyId.name());
                }
                final ICryptoContext cryptoContext = mslContext.getMslStore().getCryptoContext(masterToken);
                if (cryptoContext != null) {
                    return cryptoContext;
                }
                if (!masterToken.isDecrypted()) {
                    throw new MslMasterTokenException(MslError.MASTERTOKEN_UNTRUSTED, masterToken);
                }
                return new SessionCryptoContext(mslContext, masterToken);
            }
            case 2: {
                final PresharedAuthenticationData presharedAuthenticationData = new PresharedAuthenticationData(s);
                final EntityAuthenticationFactory entityAuthenticationFactory = mslContext.getEntityAuthenticationFactory(EntityAuthenticationScheme.PSK);
                if (entityAuthenticationFactory == null) {
                    throw new MslKeyExchangeException(MslError.UNSUPPORTED_KEYX_KEY_ID, symmetricWrappedExchange$KeyId.name());
                }
                return entityAuthenticationFactory.getCryptoContext(mslContext, presharedAuthenticationData);
            }
        }
    }
    
    @Override
    protected KeyRequestData createRequestData(final MslContext mslContext, final JSONObject jsonObject) {
        return new SymmetricWrappedExchange$RequestData(jsonObject);
    }
    
    @Override
    protected KeyResponseData createResponseData(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        return new SymmetricWrappedExchange$ResponseData(masterToken, jsonObject);
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, final EntityAuthenticationData entityAuthenticationData) {
        if (!(keyRequestData instanceof SymmetricWrappedExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final SymmetricWrappedExchange$RequestData symmetricWrappedExchange$RequestData = (SymmetricWrappedExchange$RequestData)keyRequestData;
        final String identity = entityAuthenticationData.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication Scheme for Device Type Not Supported " + identity + ":" + this.getScheme());
        }
        final byte[] array = new byte[16];
        final byte[] array2 = new byte[32];
        mslContext.getRandom().nextBytes(array);
        mslContext.getRandom().nextBytes(array2);
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final SecretKeySpec secretKeySpec2 = new SecretKeySpec(array2, "HmacSHA256");
        final SymmetricWrappedExchange$KeyId keyId = symmetricWrappedExchange$RequestData.getKeyId();
        byte[] wrap;
        byte[] wrap2;
        MasterToken masterToken;
        try {
            final ICryptoContext cryptoContext = createCryptoContext(mslContext, keyId, null, identity);
            wrap = cryptoContext.wrap(array);
            wrap2 = cryptoContext.wrap(array2);
            masterToken = mslContext.getTokenFactory().createMasterToken(mslContext, entityAuthenticationData, secretKeySpec, secretKeySpec2, null);
            final MslContext mslContext2 = mslContext;
            final MasterToken masterToken2 = masterToken;
            final SessionCryptoContext sessionCryptoContext = new SessionCryptoContext(mslContext2, masterToken2);
            final MasterToken masterToken3 = masterToken;
            final SymmetricWrappedExchange$KeyId symmetricWrappedExchange$KeyId = keyId;
            final byte[] array3 = wrap;
            final byte[] array4 = wrap2;
            final SymmetricWrappedExchange$ResponseData symmetricWrappedExchange$ResponseData = new SymmetricWrappedExchange$ResponseData(masterToken3, symmetricWrappedExchange$KeyId, array3, array4);
            final SessionCryptoContext sessionCryptoContext2 = sessionCryptoContext;
            return new KeyExchangeFactory$KeyExchangeData(symmetricWrappedExchange$ResponseData, sessionCryptoContext2);
        }
        catch (MslMasterTokenException ex) {
            throw new MslInternalException("Master token exception thrown when the master token is null.", ex);
        }
        try {
            final MslContext mslContext2 = mslContext;
            final MasterToken masterToken2 = masterToken;
            final SessionCryptoContext sessionCryptoContext = new SessionCryptoContext(mslContext2, masterToken2);
            final MasterToken masterToken3 = masterToken;
            final SymmetricWrappedExchange$KeyId symmetricWrappedExchange$KeyId = keyId;
            final byte[] array3 = wrap;
            final byte[] array4 = wrap2;
            final SymmetricWrappedExchange$ResponseData symmetricWrappedExchange$ResponseData = new SymmetricWrappedExchange$ResponseData(masterToken3, symmetricWrappedExchange$KeyId, array3, array4);
            final SessionCryptoContext sessionCryptoContext2 = sessionCryptoContext;
            return new KeyExchangeFactory$KeyExchangeData(symmetricWrappedExchange$ResponseData, sessionCryptoContext2);
        }
        catch (MslMasterTokenException ex2) {
            throw new MslInternalException("Master token constructed by token factory is not trusted.", ex2);
        }
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, MasterToken renewMasterToken) {
        if (!(keyRequestData instanceof SymmetricWrappedExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final SymmetricWrappedExchange$RequestData symmetricWrappedExchange$RequestData = (SymmetricWrappedExchange$RequestData)keyRequestData;
        final String identity = renewMasterToken.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication Scheme for Device Type Not Supported " + identity + ":" + this.getScheme()).setMasterToken(renewMasterToken);
        }
        if (!renewMasterToken.isVerified()) {
            throw new MslMasterTokenException(MslError.MASTERTOKEN_UNTRUSTED, renewMasterToken).setMasterToken(renewMasterToken);
        }
        final byte[] array = new byte[16];
        final byte[] array2 = new byte[32];
        mslContext.getRandom().nextBytes(array);
        mslContext.getRandom().nextBytes(array2);
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final SecretKeySpec secretKeySpec2 = new SecretKeySpec(array2, "HmacSHA256");
        final SymmetricWrappedExchange$KeyId keyId = symmetricWrappedExchange$RequestData.getKeyId();
        final ICryptoContext cryptoContext = createCryptoContext(mslContext, keyId, renewMasterToken, renewMasterToken.getIdentity());
        final byte[] wrap = cryptoContext.wrap(array);
        final byte[] wrap2 = cryptoContext.wrap(array2);
        renewMasterToken = mslContext.getTokenFactory().renewMasterToken(mslContext, renewMasterToken, secretKeySpec, secretKeySpec2, null);
        return new KeyExchangeFactory$KeyExchangeData(new SymmetricWrappedExchange$ResponseData(renewMasterToken, keyId, wrap, wrap2), new SessionCryptoContext(mslContext, renewMasterToken));
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final KeyRequestData keyRequestData, final KeyResponseData keyResponseData, final MasterToken masterToken) {
        if (!(keyRequestData instanceof SymmetricWrappedExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final SymmetricWrappedExchange$RequestData symmetricWrappedExchange$RequestData = (SymmetricWrappedExchange$RequestData)keyRequestData;
        if (!(keyResponseData instanceof SymmetricWrappedExchange$ResponseData)) {
            throw new MslInternalException("Key response data " + keyResponseData.getClass().getName() + " was not created by this factory.");
        }
        final SymmetricWrappedExchange$ResponseData symmetricWrappedExchange$ResponseData = (SymmetricWrappedExchange$ResponseData)keyResponseData;
        final SymmetricWrappedExchange$KeyId keyId = symmetricWrappedExchange$RequestData.getKeyId();
        final SymmetricWrappedExchange$KeyId keyId2 = symmetricWrappedExchange$ResponseData.getKeyId();
        if (!keyId.equals(keyId2)) {
            throw new MslKeyExchangeException(MslError.KEYX_RESPONSE_REQUEST_MISMATCH, "request " + keyId + "; response " + keyId2).setMasterToken(masterToken);
        }
        final String identity = mslContext.getEntityAuthenticationData(null).getIdentity();
        final ICryptoContext cryptoContext = createCryptoContext(mslContext, keyId2, masterToken, identity);
        return new SessionCryptoContext(mslContext, symmetricWrappedExchange$ResponseData.getMasterToken(), identity, new SecretKeySpec(cryptoContext.unwrap(symmetricWrappedExchange$ResponseData.getEncryptionKey()), "AES"), new SecretKeySpec(cryptoContext.unwrap(symmetricWrappedExchange$ResponseData.getHmacKey()), "HmacSHA256"));
    }
}
