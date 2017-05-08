// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.util.Base64;
import com.netflix.msl.util.MslContext$ReauthCode;
import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.crypto.SessionCryptoContext;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.spec.DHPublicKeySpec;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import javax.crypto.KeyAgreement;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.DHParameterSpec;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import javax.crypto.Mac;
import java.security.InvalidKeyException;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslError;
import java.security.NoSuchAlgorithmException;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.crypto.CryptoCache;
import java.security.Key;
import com.netflix.msl.util.AuthenticationUtils;

public abstract class AbstractAuthenticatedDiffieHellmanExchange extends KeyExchangeFactory
{
    private static final String HMAC_SHA256_ALGO = "HmacSHA256";
    private static final String HMAC_SHA384_ALGO = "HmacSHA384";
    private static final String SHA384_ALGO = "SHA-384";
    private final AuthenticationUtils authutils;
    private final DiffieHellmanParameters params;
    private final DerivationKeyRepository repository;
    
    public AbstractAuthenticatedDiffieHellmanExchange(final DerivationKeyRepository repository, final DiffieHellmanParameters params, final AuthenticationUtils authutils) {
        super(NetflixKeyExchangeScheme.AUTHENTICATED_DH);
        this.repository = repository;
        this.params = params;
        this.authutils = authutils;
    }
    
    private static byte[] computeMac(final Key key, final byte[] array, final String s) {
        try {
            final Mac mac = CryptoCache.getMac(s);
            mac.init(key);
            return mac.doFinal(array);
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException(s + " algorithm not found.", ex);
        }
        catch (InvalidKeyException ex2) {
            throw new MslCryptoException(MslError.INVALID_HMAC_KEY, ex2);
        }
    }
    
    public static byte[] concat(final byte[] array, final byte[] array2) {
        final byte[] copy = Arrays.copyOf(array, array.length + array2.length);
        System.arraycopy(array2, 0, copy, array.length, array2.length);
        return copy;
    }
    
    static byte[] correctNullBytes(final byte[] array) {
        int n = 0;
        int n2 = 0;
        while (n < array.length && array[n] == 0) {
            ++n2;
            ++n;
        }
        if (n2 == 1) {
            return array;
        }
        final int n3 = array.length - n2;
        final byte[] array2 = new byte[n3 + 1];
        array2[0] = 0;
        System.arraycopy(array, n2, array2, 1, n3);
        return array2;
    }
    
    protected static AbstractAuthenticatedDiffieHellmanExchange$SessionKeys deriveSessionKeys(final PublicKey publicKey, final PrivateKey privateKey, final DHParameterSpec dhParameterSpec, byte[] array) {
        try {
            final KeyAgreement keyAgreement = CryptoCache.getKeyAgreement("DiffieHellman");
            keyAgreement.init(privateKey, dhParameterSpec);
            keyAgreement.doPhase(publicKey, true);
            final byte[] computeMac = computeMac(new SecretKeySpec(array, "HmacSHA384"), correctNullBytes(keyAgreement.generateSecret()), "HmacSHA384");
            final byte[] array2 = new byte[16];
            System.arraycopy(computeMac, 0, array2, 0, array2.length);
            final byte[] array3 = new byte[32];
            System.arraycopy(computeMac, array2.length, array3, 0, array3.length);
            final byte[] computeMac2 = computeMac(new SecretKeySpec(computeMac(new SecretKeySpec(new byte[] { 2, 118, 23, -104, 79, 98, 39, 83, -102, 99, 11, -119, 124, 1, 125, 105 }, "HmacSHA256"), concat(array2, array3), "HmacSHA256"), "HmacSHA256"), new byte[] { -128, -97, -126, -89, -83, -33, 84, -115, 62, -87, -35, 6, 127, -7, -69, -111 }, "HmacSHA256");
            array = new byte[16];
            System.arraycopy(computeMac2, 0, array, 0, array.length);
            return new AbstractAuthenticatedDiffieHellmanExchange$SessionKeys(new SecretKeySpec(array2, "AES"), new SecretKeySpec(array3, "HmacSHA256"), new SecretKeySpec(array, "AES"));
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException("DiffieHellman algorithm not found.", ex);
        }
        catch (InvalidKeyException ex2) {
            throw new MslInternalException("Diffie-Hellman private key or generated public key rejected by Diffie-Hellman key agreement.", ex2);
        }
        catch (InvalidAlgorithmParameterException ex3) {
            throw new MslInternalException("Diffie-Hellman algorithm parameters rejected by Diffie-Hellman key agreement.", ex3);
        }
    }
    
    private KeyPair generatePrivatePublicKeyPair(final DHParameterSpec dhParameterSpec) {
        try {
            final KeyPairGenerator keyPairGenerator = CryptoCache.getKeyPairGenerator("DH");
            keyPairGenerator.initialize(dhParameterSpec);
            return keyPairGenerator.generateKeyPair();
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException("DiffieHellman algorithm not found.", ex);
        }
        catch (InvalidAlgorithmParameterException ex2) {
            throw new MslInternalException("Diffie-Hellman algorithm parameters rejected by Diffie-Hellman key agreement.", ex2);
        }
    }
    
    private PublicKey reconstituteRequestPublicKey(final AuthenticatedDiffieHellmanRequestData authenticatedDiffieHellmanRequestData, final DHParameterSpec dhParameterSpec) {
        try {
            return CryptoCache.getKeyFactory("DiffieHellman").generatePublic(new DHPublicKeySpec(authenticatedDiffieHellmanRequestData.getPublicKey(), dhParameterSpec.getP(), dhParameterSpec.getG()));
        }
        catch (NoSuchAlgorithmException ex) {
            throw new MslInternalException("DiffieHellman algorithm not found.", ex);
        }
        catch (InvalidKeySpecException ex2) {
            throw new MslInternalException("Diffie-Hellman public key specification rejected by Diffie-Hellman key factory.", ex2);
        }
    }
    
    @Override
    protected KeyRequestData createRequestData(final MslContext mslContext, final JSONObject jsonObject) {
        return new AuthenticatedDiffieHellmanRequestData(jsonObject);
    }
    
    @Override
    protected KeyResponseData createResponseData(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        return new AuthenticatedDiffieHellmanResponseData(masterToken, jsonObject);
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, final EntityAuthenticationData entityAuthenticationData) {
        if (!(keyRequestData instanceof AuthenticatedDiffieHellmanRequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final AuthenticatedDiffieHellmanRequestData authenticatedDiffieHellmanRequestData = (AuthenticatedDiffieHellmanRequestData)keyRequestData;
        final String identity = entityAuthenticationData.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication Scheme for Device Type Not Supported " + identity + ":" + this.getScheme());
        }
        final AbstractAuthenticatedDiffieHellmanExchange$Mechanism mechanism = authenticatedDiffieHellmanRequestData.getMechanism();
        final byte[] wrapdata = authenticatedDiffieHellmanRequestData.getWrapdata();
        final DHParameterSpec parameterSpec = this.params.getParameterSpec(authenticatedDiffieHellmanRequestData.getParametersId());
        final PublicKey reconstituteRequestPublicKey = this.reconstituteRequestPublicKey(authenticatedDiffieHellmanRequestData, parameterSpec);
        final KeyPair generatePrivatePublicKeyPair = this.generatePrivatePublicKeyPair(parameterSpec);
        final DHPublicKey dhPublicKey = (DHPublicKey)generatePrivatePublicKeyPair.getPublic();
        final AbstractAuthenticatedDiffieHellmanExchange$SessionKeys deriveSessionKeys = deriveSessionKeys(reconstituteRequestPublicKey, generatePrivatePublicKeyPair.getPrivate(), parameterSpec, this.getHashWrapKeyData(mslContext, mechanism, wrapdata, identity));
        final byte[] wrap = mslContext.getMslCryptoContext().wrap(deriveSessionKeys.derivationKey.getEncoded());
        final MasterToken masterToken = mslContext.getTokenFactory().createMasterToken(mslContext, entityAuthenticationData, deriveSessionKeys.encryptionKey, deriveSessionKeys.hmacKey, null);
        return new KeyExchangeFactory$KeyExchangeData(new AuthenticatedDiffieHellmanResponseData(masterToken, wrap, authenticatedDiffieHellmanRequestData.getParametersId(), dhPublicKey.getY()), new SessionCryptoContext(mslContext, masterToken));
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, MasterToken renewMasterToken) {
        if (!(keyRequestData instanceof AuthenticatedDiffieHellmanRequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final AuthenticatedDiffieHellmanRequestData authenticatedDiffieHellmanRequestData = (AuthenticatedDiffieHellmanRequestData)keyRequestData;
        if (!renewMasterToken.isVerified()) {
            throw new MslMasterTokenException(MslError.MASTERTOKEN_UNTRUSTED, renewMasterToken);
        }
        final String identity = renewMasterToken.getIdentity();
        if (!this.authutils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication Scheme for Device Type Not Supported " + identity + ":" + this.getScheme());
        }
        final AbstractAuthenticatedDiffieHellmanExchange$Mechanism mechanism = authenticatedDiffieHellmanRequestData.getMechanism();
        final byte[] wrapdata = authenticatedDiffieHellmanRequestData.getWrapdata();
        final DHParameterSpec parameterSpec = this.params.getParameterSpec(authenticatedDiffieHellmanRequestData.getParametersId());
        final PublicKey reconstituteRequestPublicKey = this.reconstituteRequestPublicKey(authenticatedDiffieHellmanRequestData, parameterSpec);
        final KeyPair generatePrivatePublicKeyPair = this.generatePrivatePublicKeyPair(parameterSpec);
        final DHPublicKey dhPublicKey = (DHPublicKey)generatePrivatePublicKeyPair.getPublic();
        final AbstractAuthenticatedDiffieHellmanExchange$SessionKeys deriveSessionKeys = deriveSessionKeys(reconstituteRequestPublicKey, generatePrivatePublicKeyPair.getPrivate(), parameterSpec, this.getHashWrapKeyData(mslContext, mechanism, wrapdata, identity));
        final byte[] wrap = mslContext.getMslCryptoContext().wrap(deriveSessionKeys.derivationKey.getEncoded());
        renewMasterToken = mslContext.getTokenFactory().renewMasterToken(mslContext, renewMasterToken, deriveSessionKeys.encryptionKey, deriveSessionKeys.hmacKey, null);
        return new KeyExchangeFactory$KeyExchangeData(new AuthenticatedDiffieHellmanResponseData(renewMasterToken, wrap, authenticatedDiffieHellmanRequestData.getParametersId(), dhPublicKey.getY()), new SessionCryptoContext(mslContext, renewMasterToken));
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final KeyRequestData keyRequestData, final KeyResponseData keyResponseData, final MasterToken masterToken) {
        if (!(keyRequestData instanceof AuthenticatedDiffieHellmanRequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory.");
        }
        final AuthenticatedDiffieHellmanRequestData authenticatedDiffieHellmanRequestData = (AuthenticatedDiffieHellmanRequestData)keyRequestData;
        if (!(keyResponseData instanceof AuthenticatedDiffieHellmanResponseData)) {
            throw new MslInternalException("Key response data " + keyResponseData.getClass().getName() + " was not created by this factory.");
        }
        final AuthenticatedDiffieHellmanResponseData authenticatedDiffieHellmanResponseData = (AuthenticatedDiffieHellmanResponseData)keyResponseData;
        final String parametersId = authenticatedDiffieHellmanRequestData.getParametersId();
        final String parametersId2 = authenticatedDiffieHellmanResponseData.getParametersId();
        if (!parametersId.equals(parametersId2)) {
            throw new MslKeyExchangeException(MslError.KEYX_RESPONSE_REQUEST_MISMATCH, "request " + parametersId + "; response " + parametersId2).setMasterToken(masterToken);
        }
        final DHPrivateKey privateKey = authenticatedDiffieHellmanRequestData.getPrivateKey();
        if (privateKey == null) {
            throw new MslKeyExchangeException(MslError.KEYX_PRIVATE_KEY_MISSING, "request Diffie-Hellman private key").setMasterToken(masterToken);
        }
        final DHParameterSpec params = privateKey.getParams();
        while (true) {
            AbstractAuthenticatedDiffieHellmanExchange$Mechanism mechanism = null;
            String identity = null;
            Label_0503: {
                Label_0489: {
                    PublicKey generatePublic;
                    byte[] wrapdata;
                    try {
                        generatePublic = CryptoCache.getKeyFactory("DiffieHellman").generatePublic(new DHPublicKeySpec(authenticatedDiffieHellmanResponseData.getPublicKey(), params.getP(), params.getG()));
                        mechanism = authenticatedDiffieHellmanRequestData.getMechanism();
                        wrapdata = authenticatedDiffieHellmanRequestData.getWrapdata();
                        identity = mslContext.getEntityAuthenticationData(null).getIdentity();
                        switch (AbstractAuthenticatedDiffieHellmanExchange$1.$SwitchMap$com$netflix$msl$keyx$AbstractAuthenticatedDiffieHellmanExchange$Mechanism[mechanism.ordinal()]) {
                            default: {
                                throw new MslInternalException("Key request data mechanism " + mechanism + " is not supported but it should be.");
                            }
                            case 1: {
                                break;
                            }
                            case 2: {
                                break Label_0489;
                            }
                            case 3: {
                                break Label_0503;
                            }
                        }
                    }
                    catch (NoSuchAlgorithmException ex) {
                        throw new MslInternalException("DiffieHellman algorithm not found.", ex);
                    }
                    catch (InvalidKeySpecException ex2) {
                        throw new MslKeyExchangeException(MslError.KEYX_INVALID_PUBLIC_KEY, "Diffie-Hellman public key specification rejected by Diffie-Hellman key factory.", ex2);
                    }
                    final SecretKey derivationKey = this.repository.getDerivationKey(wrapdata);
                    if (derivationKey == null) {
                        throw new MslKeyExchangeException(MslError.KEYX_DERIVATION_KEY_MISSING, Base64.encode(wrapdata));
                    }
                    try {
                        final byte[] array = CryptoCache.getMessageDigest("SHA-384").digest(derivationKey.getEncoded());
                        final AbstractAuthenticatedDiffieHellmanExchange$SessionKeys deriveSessionKeys = deriveSessionKeys(generatePublic, privateKey, params, array);
                        this.repository.addDerivationKey(authenticatedDiffieHellmanResponseData.getWrapdata(), deriveSessionKeys.derivationKey);
                        if (wrapdata != null) {
                            this.repository.removeDerivationKey(wrapdata);
                        }
                        return new SessionCryptoContext(mslContext, authenticatedDiffieHellmanResponseData.getMasterToken(), identity, deriveSessionKeys.encryptionKey, deriveSessionKeys.hmacKey);
                    }
                    catch (NoSuchAlgorithmException ex3) {
                        throw new MslInternalException("SHA-384 algorithm not found.", ex3);
                    }
                }
                final byte[] array = this.getHashWrapKeyData(mslContext, mechanism, null, identity);
                continue;
            }
            final byte[] array = this.getHashWrapKeyData(mslContext, mechanism, null, identity);
            continue;
        }
    }
    
    protected abstract byte[] getHashWrapKeyData(final MslContext p0, final AbstractAuthenticatedDiffieHellmanExchange$Mechanism p1, final byte[] p2, final String p3);
}
