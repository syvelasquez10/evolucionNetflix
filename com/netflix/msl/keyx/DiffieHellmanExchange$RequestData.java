// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.util.MslContext$ReauthCode;
import com.netflix.msl.tokens.TokenFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.InvalidKeySpecException;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.crypto.SessionCryptoContext;
import javax.crypto.interfaces.DHPublicKey;
import java.security.spec.KeySpec;
import javax.crypto.spec.DHPublicKeySpec;
import com.netflix.msl.MslMasterTokenException;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.util.MslContext;
import java.security.MessageDigest;
import javax.crypto.KeyAgreement;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import com.netflix.msl.MslInternalException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import com.netflix.msl.crypto.CryptoCache;
import javax.crypto.spec.DHParameterSpec;
import java.security.PrivateKey;
import java.security.PublicKey;
import com.netflix.msl.util.AuthenticationUtils;
import java.util.Arrays;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.msl.util.Base64;
import com.netflix.android.org.json.JSONObject;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPrivateKey;

public class DiffieHellmanExchange$RequestData extends KeyRequestData
{
    private final String parametersId;
    private final DHPrivateKey privateKey;
    private final BigInteger publicKey;
    
    public DiffieHellmanExchange$RequestData(final JSONObject jsonObject) {
        super(KeyExchangeScheme.DIFFIE_HELLMAN);
        try {
            this.parametersId = jsonObject.getString("parametersid");
            this.publicKey = new BigInteger(correctNullBytes(Base64.decode(jsonObject.getString("publickey"))));
            this.privateKey = null;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "keydata " + jsonObject.toString(), ex);
        }
        catch (NullPointerException ex2) {
            throw new MslKeyExchangeException(MslError.KEYX_INVALID_PUBLIC_KEY, "keydata " + jsonObject.toString(), ex2);
        }
        catch (NumberFormatException ex3) {
            throw new MslKeyExchangeException(MslError.KEYX_INVALID_PUBLIC_KEY, "keydata " + jsonObject.toString(), ex3);
        }
        catch (IllegalArgumentException ex4) {
            throw new MslKeyExchangeException(MslError.KEYX_INVALID_PUBLIC_KEY, "keydata " + jsonObject.toString(), ex4);
        }
    }
    
    public DiffieHellmanExchange$RequestData(final String parametersId, final BigInteger publicKey, final DHPrivateKey privateKey) {
        super(KeyExchangeScheme.DIFFIE_HELLMAN);
        this.parametersId = parametersId;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof DiffieHellmanExchange$RequestData)) {
                return false;
            }
            final DiffieHellmanExchange$RequestData diffieHellmanExchange$RequestData = (DiffieHellmanExchange$RequestData)o;
            boolean b;
            if (this.privateKey == diffieHellmanExchange$RequestData.privateKey || (this.privateKey != null && diffieHellmanExchange$RequestData.privateKey != null && Arrays.equals(this.privateKey.getEncoded(), diffieHellmanExchange$RequestData.privateKey.getEncoded()))) {
                b = true;
            }
            else {
                b = false;
            }
            if (!super.equals(o) || !this.parametersId.equals(diffieHellmanExchange$RequestData.parametersId) || !this.publicKey.equals(diffieHellmanExchange$RequestData.publicKey) || !b) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("parametersid", this.parametersId);
        jsonObject.put("publickey", Base64.encode(correctNullBytes(this.publicKey.toByteArray())));
        return jsonObject;
    }
    
    public String getParametersId() {
        return this.parametersId;
    }
    
    public DHPrivateKey getPrivateKey() {
        return this.privateKey;
    }
    
    public BigInteger getPublicKey() {
        return this.publicKey;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.privateKey != null) {
            hashCode = Arrays.hashCode(this.privateKey.getEncoded());
        }
        else {
            hashCode = 0;
        }
        return hashCode ^ (super.hashCode() ^ this.parametersId.hashCode() ^ this.publicKey.hashCode());
    }
}
