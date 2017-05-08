// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.util.MslContext$ReauthCode;
import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.MslConstants;
import com.netflix.msl.crypto.JsonWebKey;
import com.netflix.msl.crypto.JsonWebKey$Algorithm;
import com.netflix.msl.crypto.JsonWebKey$Usage;
import com.netflix.msl.crypto.SessionCryptoContext;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.crypto.JsonWebEncryptionCryptoContext$CekCryptoContext;
import com.netflix.msl.crypto.JsonWebEncryptionCryptoContext;
import com.netflix.msl.crypto.JsonWebEncryptionCryptoContext$Format;
import com.netflix.msl.crypto.JsonWebEncryptionCryptoContext$Encryption;
import com.netflix.msl.crypto.JsonWebEncryptionCryptoContext$RsaOaepCryptoContext;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import com.netflix.msl.crypto.ICryptoContext;
import java.security.PublicKey;
import java.security.PrivateKey;
import com.netflix.msl.util.MslContext;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.netflix.msl.util.AuthenticationUtils;
import com.netflix.msl.crypto.JsonWebKey$KeyOp;
import java.util.Set;

public class AsymmetricWrappedExchange extends KeyExchangeFactory
{
    private static final Set<JsonWebKey$KeyOp> ENCRYPT_DECRYPT;
    private static final Set<JsonWebKey$KeyOp> SIGN_VERIFY;
    private final AuthenticationUtils authutils;
    
    static {
        ENCRYPT_DECRYPT = new HashSet<JsonWebKey$KeyOp>(Arrays.asList(JsonWebKey$KeyOp.encrypt, JsonWebKey$KeyOp.decrypt));
        SIGN_VERIFY = new HashSet<JsonWebKey$KeyOp>(Arrays.asList(JsonWebKey$KeyOp.sign, JsonWebKey$KeyOp.verify));
    }
    
    public AsymmetricWrappedExchange(final AuthenticationUtils authutils) {
        super(KeyExchangeScheme.ASYMMETRIC_WRAPPED);
        this.authutils = authutils;
    }
    
    private static ICryptoContext createCryptoContext(final MslContext mslContext, final String s, final AsymmetricWrappedExchange$RequestData$Mechanism asymmetricWrappedExchange$RequestData$Mechanism, final PrivateKey privateKey, final PublicKey publicKey) {
        switch (AsymmetricWrappedExchange$1.$SwitchMap$com$netflix$msl$keyx$AsymmetricWrappedExchange$RequestData$Mechanism[asymmetricWrappedExchange$RequestData$Mechanism.ordinal()]) {
            default: {
                throw new MslCryptoException(MslError.UNSUPPORTED_KEYX_MECHANISM, asymmetricWrappedExchange$RequestData$Mechanism.name());
            }
            case 2: {
                return new JsonWebEncryptionCryptoContext(mslContext, new JsonWebEncryptionCryptoContext$RsaOaepCryptoContext(privateKey, publicKey), JsonWebEncryptionCryptoContext$Encryption.A128GCM, JsonWebEncryptionCryptoContext$Format.JWE_CS);
            }
            case 3: {
                return new JsonWebEncryptionCryptoContext(mslContext, new JsonWebEncryptionCryptoContext$RsaOaepCryptoContext(privateKey, publicKey), JsonWebEncryptionCryptoContext$Encryption.A128GCM, JsonWebEncryptionCryptoContext$Format.JWE_JS);
            }
            case 1:
            case 4: {
                return new AsymmetricWrappedExchange$RsaWrappingCryptoContext(mslContext, s, privateKey, publicKey, AsymmetricWrappedExchange$RsaWrappingCryptoContext$Mode.WRAP_UNWRAP_OAEP);
            }
            case 5: {
                return new AsymmetricWrappedExchange$RsaWrappingCryptoContext(mslContext, s, privateKey, publicKey, AsymmetricWrappedExchange$RsaWrappingCryptoContext$Mode.WRAP_UNWRAP_PKCS1);
            }
        }
    }
    
    @Override
    protected KeyRequestData createRequestData(final MslContext mslContext, final JSONObject jsonObject) {
        return new AsymmetricWrappedExchange$RequestData(jsonObject);
    }
    
    @Override
    protected KeyResponseData createResponseData(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        return new AsymmetricWrappedExchange$ResponseData(masterToken, jsonObject);
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, final EntityAuthenticationData entityAuthenticationData) {
        if (!(keyRequestData instanceof AsymmetricWrappedExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final AsymmetricWrappedExchange$RequestData asymmetricWrappedExchange$RequestData = (AsymmetricWrappedExchange$RequestData)keyRequestData;
        final String identity = entityAuthenticationData.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication scheme for entity not permitted " + identity + ":" + this.getScheme()).setEntityAuthenticationData(entityAuthenticationData);
        }
        final byte[] array = new byte[16];
        final byte[] array2 = new byte[32];
        mslContext.getRandom().nextBytes(array);
        mslContext.getRandom().nextBytes(array2);
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final SecretKeySpec secretKeySpec2 = new SecretKeySpec(array2, "HmacSHA256");
        final String keyPairId = asymmetricWrappedExchange$RequestData.getKeyPairId();
        final AsymmetricWrappedExchange$RequestData$Mechanism mechanism = asymmetricWrappedExchange$RequestData.getMechanism();
        final ICryptoContext cryptoContext = createCryptoContext(mslContext, keyPairId, mechanism, null, asymmetricWrappedExchange$RequestData.getPublicKey());
        while (true) {
            switch (AsymmetricWrappedExchange$1.$SwitchMap$com$netflix$msl$keyx$AsymmetricWrappedExchange$RequestData$Mechanism[mechanism.ordinal()]) {
                default: {
                    final byte[] array3 = cryptoContext.wrap(array);
                    final byte[] array4 = cryptoContext.wrap(array2);
                }
                case 2:
                case 3: {
                    Label_0311: {
                        break Label_0311;
                        while (true) {
                            final MasterToken masterToken = mslContext.getTokenFactory().createMasterToken(mslContext, entityAuthenticationData, secretKeySpec, secretKeySpec2, null);
                            try {
                                byte[] array3 = null;
                                byte[] array4 = null;
                                return new KeyExchangeFactory$KeyExchangeData(new AsymmetricWrappedExchange$ResponseData(masterToken, asymmetricWrappedExchange$RequestData.getKeyPairId(), array3, array4), new SessionCryptoContext(mslContext, masterToken));
                                final JsonWebKey jsonWebKey = new JsonWebKey(JsonWebKey$Usage.enc, JsonWebKey$Algorithm.A128CBC, false, null, secretKeySpec);
                                final JsonWebKey jsonWebKey2 = new JsonWebKey(JsonWebKey$Usage.sig, JsonWebKey$Algorithm.HS256, false, null, secretKeySpec2);
                                array3 = cryptoContext.wrap(jsonWebKey.toJSONString().getBytes(MslConstants.DEFAULT_CHARSET));
                                array4 = cryptoContext.wrap(jsonWebKey2.toJSONString().getBytes(MslConstants.DEFAULT_CHARSET));
                                continue;
                                final JsonWebKey jsonWebKey3 = new JsonWebKey(AsymmetricWrappedExchange.ENCRYPT_DECRYPT, JsonWebKey$Algorithm.A128CBC, false, null, secretKeySpec);
                                final JsonWebKey jsonWebKey4 = new JsonWebKey(AsymmetricWrappedExchange.SIGN_VERIFY, JsonWebKey$Algorithm.HS256, false, null, secretKeySpec2);
                                array3 = cryptoContext.wrap(jsonWebKey3.toJSONString().getBytes(MslConstants.DEFAULT_CHARSET));
                                array4 = cryptoContext.wrap(jsonWebKey4.toJSONString().getBytes(MslConstants.DEFAULT_CHARSET));
                            }
                            catch (MslMasterTokenException ex) {
                                throw new MslInternalException("Master token constructed by token factory is not trusted.", ex);
                            }
                        }
                    }
                    break;
                }
                case 4:
                case 5: {
                    continue;
                }
            }
            break;
        }
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, MasterToken renewMasterToken) {
        if (!(keyRequestData instanceof AsymmetricWrappedExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final AsymmetricWrappedExchange$RequestData asymmetricWrappedExchange$RequestData = (AsymmetricWrappedExchange$RequestData)keyRequestData;
        if (!renewMasterToken.isVerified()) {
            throw new MslMasterTokenException(MslError.MASTERTOKEN_UNTRUSTED, renewMasterToken);
        }
        final String identity = renewMasterToken.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication scheme for entity not permitted " + identity + ":" + this.getScheme()).setMasterToken(renewMasterToken);
        }
        while (true) {
            final byte[] array = new byte[16];
            final byte[] array2 = new byte[32];
            mslContext.getRandom().nextBytes(array);
            mslContext.getRandom().nextBytes(array2);
            while (true) {
                SecretKeySpec secretKeySpec = null;
                SecretKeySpec secretKeySpec2 = null;
                ICryptoContext cryptoContext = null;
                Label_0426: {
                    try {
                        secretKeySpec = new SecretKeySpec(array, "AES");
                        secretKeySpec2 = new SecretKeySpec(array2, "HmacSHA256");
                        final String keyPairId = asymmetricWrappedExchange$RequestData.getKeyPairId();
                        final AsymmetricWrappedExchange$RequestData$Mechanism mechanism = asymmetricWrappedExchange$RequestData.getMechanism();
                        cryptoContext = createCryptoContext(mslContext, keyPairId, mechanism, null, asymmetricWrappedExchange$RequestData.getPublicKey());
                        switch (AsymmetricWrappedExchange$1.$SwitchMap$com$netflix$msl$keyx$AsymmetricWrappedExchange$RequestData$Mechanism[mechanism.ordinal()]) {
                            default: {
                                final byte[] array3 = cryptoContext.wrap(array);
                                final byte[] array4 = cryptoContext.wrap(array2);
                                renewMasterToken = mslContext.getTokenFactory().renewMasterToken(mslContext, renewMasterToken, secretKeySpec, secretKeySpec2, null);
                                return new KeyExchangeFactory$KeyExchangeData(new AsymmetricWrappedExchange$ResponseData(renewMasterToken, asymmetricWrappedExchange$RequestData.getKeyPairId(), array3, array4), new SessionCryptoContext(mslContext, renewMasterToken));
                            }
                            case 2:
                            case 3: {
                                break;
                            }
                            case 4:
                            case 5: {
                                break Label_0426;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw new MslCryptoException(MslError.SESSION_KEY_CREATION_FAILURE, ex).setMasterToken(renewMasterToken);
                    }
                    final JsonWebKey jsonWebKey = new JsonWebKey(JsonWebKey$Usage.enc, JsonWebKey$Algorithm.A128CBC, false, null, secretKeySpec);
                    final JsonWebKey jsonWebKey2 = new JsonWebKey(JsonWebKey$Usage.sig, JsonWebKey$Algorithm.HS256, false, null, secretKeySpec2);
                    final byte[] array3 = cryptoContext.wrap(jsonWebKey.toJSONString().getBytes(MslConstants.DEFAULT_CHARSET));
                    final byte[] array4 = cryptoContext.wrap(jsonWebKey2.toJSONString().getBytes(MslConstants.DEFAULT_CHARSET));
                    continue;
                }
                final JsonWebKey jsonWebKey3 = new JsonWebKey(AsymmetricWrappedExchange.ENCRYPT_DECRYPT, JsonWebKey$Algorithm.A128CBC, false, null, secretKeySpec);
                final JsonWebKey jsonWebKey4 = new JsonWebKey(AsymmetricWrappedExchange.SIGN_VERIFY, JsonWebKey$Algorithm.HS256, false, null, secretKeySpec2);
                final byte[] array3 = cryptoContext.wrap(jsonWebKey3.toJSONString().getBytes(MslConstants.DEFAULT_CHARSET));
                final byte[] array4 = cryptoContext.wrap(jsonWebKey4.toJSONString().getBytes(MslConstants.DEFAULT_CHARSET));
                continue;
            }
        }
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final KeyRequestData keyRequestData, final KeyResponseData keyResponseData, MasterToken identity) {
        if (!(keyRequestData instanceof AsymmetricWrappedExchange$RequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final AsymmetricWrappedExchange$RequestData asymmetricWrappedExchange$RequestData = (AsymmetricWrappedExchange$RequestData)keyRequestData;
        if (!(keyResponseData instanceof AsymmetricWrappedExchange$ResponseData)) {
            throw new MslInternalException("Key response data " + keyResponseData.getClass().getName() + " was not created by this factory.");
        }
        final AsymmetricWrappedExchange$ResponseData asymmetricWrappedExchange$ResponseData = (AsymmetricWrappedExchange$ResponseData)keyResponseData;
        final String keyPairId = asymmetricWrappedExchange$RequestData.getKeyPairId();
        final String keyPairId2 = asymmetricWrappedExchange$ResponseData.getKeyPairId();
        if (!keyPairId.equals(keyPairId2)) {
            throw new MslKeyExchangeException(MslError.KEYX_RESPONSE_REQUEST_MISMATCH, "request " + keyPairId + "; response " + keyPairId2);
        }
        final PrivateKey privateKey = asymmetricWrappedExchange$RequestData.getPrivateKey();
        if (privateKey == null) {
            throw new MslKeyExchangeException(MslError.KEYX_PRIVATE_KEY_MISSING, "request Asymmetric private key");
        }
        final AsymmetricWrappedExchange$RequestData$Mechanism mechanism = asymmetricWrappedExchange$RequestData.getMechanism();
        final ICryptoContext cryptoContext = createCryptoContext(mslContext, keyPairId, mechanism, privateKey, null);
        switch (AsymmetricWrappedExchange$1.$SwitchMap$com$netflix$msl$keyx$AsymmetricWrappedExchange$RequestData$Mechanism[mechanism.ordinal()]) {
            default:
            case 2:
            case 3:
            case 4:
            case 5: {
                Label_0318: {
                    break Label_0318;
                    final byte[] unwrap = cryptoContext.unwrap(asymmetricWrappedExchange$ResponseData.getEncryptionKey());
                    final byte[] unwrap2 = cryptoContext.unwrap(asymmetricWrappedExchange$ResponseData.getHmacKey());
                    try {
                        SecretKey secretKey = new SecretKeySpec(unwrap, "AES");
                        SecretKey secretKey2 = new SecretKeySpec(unwrap2, "HmacSHA256");
                        identity = (MasterToken)mslContext.getEntityAuthenticationData(null).getIdentity();
                        return new SessionCryptoContext(mslContext, asymmetricWrappedExchange$ResponseData.getMasterToken(), (String)identity, secretKey, secretKey2);
                        final byte[] unwrap3 = cryptoContext.unwrap(asymmetricWrappedExchange$ResponseData.getEncryptionKey());
                        final byte[] unwrap4 = cryptoContext.unwrap(asymmetricWrappedExchange$ResponseData.getHmacKey());
                        try {
                            final JSONObject jsonObject = new JSONObject(new String(unwrap3, MslConstants.DEFAULT_CHARSET));
                            final JSONObject jsonObject2 = new JSONObject(new String(unwrap4, MslConstants.DEFAULT_CHARSET));
                            secretKey = new JsonWebKey(jsonObject).getSecretKey();
                            secretKey2 = new JsonWebKey(jsonObject2).getSecretKey();
                        }
                        catch (JSONException ex) {
                            throw new MslCryptoException(MslError.SESSION_KEY_CREATION_FAILURE, ex).setMasterToken(identity);
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw new MslCryptoException(MslError.SESSION_KEY_CREATION_FAILURE, ex2).setMasterToken(identity);
                    }
                }
                break;
            }
        }
    }
}
