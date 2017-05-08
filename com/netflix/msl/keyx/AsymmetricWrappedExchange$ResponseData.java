// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import java.util.Arrays;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.MslError;
import com.netflix.msl.util.Base64;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.tokens.MasterToken;

public class AsymmetricWrappedExchange$ResponseData extends KeyResponseData
{
    private static final String KEY_ENCRYPTION_KEY = "encryptionkey";
    private static final String KEY_HMAC_KEY = "hmackey";
    private static final String KEY_KEY_PAIR_ID = "keypairid";
    private final byte[] encryptionKey;
    private final byte[] hmacKey;
    private final String keyPairId;
    
    public AsymmetricWrappedExchange$ResponseData(final MasterToken masterToken, final JSONObject jsonObject) {
        super(masterToken, KeyExchangeScheme.ASYMMETRIC_WRAPPED);
        try {
            this.keyPairId = jsonObject.getString("keypairid");
            try {
                this.encryptionKey = Base64.decode(jsonObject.getString("encryptionkey"));
                final AsymmetricWrappedExchange$ResponseData asymmetricWrappedExchange$ResponseData = this;
                final JSONObject jsonObject2 = jsonObject;
                final String s = "hmackey";
                final String s2 = jsonObject2.getString(s);
                final byte[] array = Base64.decode(s2);
                asymmetricWrappedExchange$ResponseData.hmacKey = array;
                return;
            }
            catch (IllegalArgumentException ex) {
                throw new MslKeyExchangeException(MslError.KEYX_INVALID_ENCRYPTION_KEY, "keydata " + jsonObject.toString(), ex);
            }
        }
        catch (JSONException ex2) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "keydata " + jsonObject.toString(), ex2);
        }
        try {
            final AsymmetricWrappedExchange$ResponseData asymmetricWrappedExchange$ResponseData = this;
            final JSONObject jsonObject2 = jsonObject;
            final String s = "hmackey";
            final String s2 = jsonObject2.getString(s);
            final byte[] array = Base64.decode(s2);
            asymmetricWrappedExchange$ResponseData.hmacKey = array;
        }
        catch (IllegalArgumentException ex3) {
            throw new MslKeyExchangeException(MslError.KEYX_INVALID_HMAC_KEY, "keydata " + jsonObject.toString(), ex3);
        }
    }
    
    public AsymmetricWrappedExchange$ResponseData(final MasterToken masterToken, final String keyPairId, final byte[] encryptionKey, final byte[] hmacKey) {
        super(masterToken, KeyExchangeScheme.ASYMMETRIC_WRAPPED);
        this.keyPairId = keyPairId;
        this.encryptionKey = encryptionKey;
        this.hmacKey = hmacKey;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AsymmetricWrappedExchange$ResponseData)) {
            return false;
        }
        final AsymmetricWrappedExchange$ResponseData asymmetricWrappedExchange$ResponseData = (AsymmetricWrappedExchange$ResponseData)o;
        return super.equals(o) && this.keyPairId.equals(asymmetricWrappedExchange$ResponseData.keyPairId) && Arrays.equals(this.encryptionKey, asymmetricWrappedExchange$ResponseData.encryptionKey) && Arrays.equals(this.hmacKey, asymmetricWrappedExchange$ResponseData.hmacKey);
    }
    
    public byte[] getEncryptionKey() {
        return this.encryptionKey;
    }
    
    public byte[] getHmacKey() {
        return this.hmacKey;
    }
    
    public String getKeyPairId() {
        return this.keyPairId;
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("keypairid", this.keyPairId);
        jsonObject.put("encryptionkey", Base64.encode(this.encryptionKey));
        jsonObject.put("hmackey", Base64.encode(this.hmacKey));
        return jsonObject;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.keyPairId.hashCode() ^ Arrays.hashCode(this.encryptionKey) ^ Arrays.hashCode(this.hmacKey);
    }
}
