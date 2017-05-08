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

public class SymmetricWrappedExchange$ResponseData extends KeyResponseData
{
    private static final String KEY_ENCRYPTION_KEY = "encryptionkey";
    private static final String KEY_HMAC_KEY = "hmackey";
    private static final String KEY_KEY_ID = "keyid";
    private final byte[] encryptionKey;
    private final byte[] hmacKey;
    private final SymmetricWrappedExchange$KeyId keyId;
    
    public SymmetricWrappedExchange$ResponseData(MasterToken string, final JSONObject jsonObject) {
        super(string, KeyExchangeScheme.SYMMETRIC_WRAPPED);
        try {
            string = (MasterToken)jsonObject.getString("keyid");
            try {
                this.keyId = SymmetricWrappedExchange$KeyId.valueOf((String)string);
                try {
                    this.encryptionKey = Base64.decode(jsonObject.getString("encryptionkey"));
                    final SymmetricWrappedExchange$ResponseData symmetricWrappedExchange$ResponseData = this;
                    final JSONObject jsonObject2 = jsonObject;
                    final String s = "hmackey";
                    final String s2 = jsonObject2.getString(s);
                    final byte[] array = Base64.decode(s2);
                    symmetricWrappedExchange$ResponseData.hmacKey = array;
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw new MslKeyExchangeException(MslError.KEYX_INVALID_ENCRYPTION_KEY, "keydata " + jsonObject.toString(), ex);
                }
            }
            catch (IllegalArgumentException ex2) {
                try {
                    throw new MslKeyExchangeException(MslError.UNIDENTIFIED_KEYX_KEY_ID, (String)string, ex2);
                }
                catch (JSONException ex3) {
                    throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "keydata " + jsonObject.toString(), ex3);
                }
            }
        }
        catch (JSONException ex5) {}
        try {
            final SymmetricWrappedExchange$ResponseData symmetricWrappedExchange$ResponseData = this;
            final JSONObject jsonObject2 = jsonObject;
            final String s = "hmackey";
            final String s2 = jsonObject2.getString(s);
            final byte[] array = Base64.decode(s2);
            symmetricWrappedExchange$ResponseData.hmacKey = array;
        }
        catch (IllegalArgumentException ex4) {
            throw new MslKeyExchangeException(MslError.KEYX_INVALID_HMAC_KEY, "keydata " + jsonObject.toString(), ex4);
        }
    }
    
    public SymmetricWrappedExchange$ResponseData(final MasterToken masterToken, final SymmetricWrappedExchange$KeyId keyId, final byte[] encryptionKey, final byte[] hmacKey) {
        super(masterToken, KeyExchangeScheme.SYMMETRIC_WRAPPED);
        this.keyId = keyId;
        this.encryptionKey = encryptionKey;
        this.hmacKey = hmacKey;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SymmetricWrappedExchange$ResponseData)) {
            return false;
        }
        final SymmetricWrappedExchange$ResponseData symmetricWrappedExchange$ResponseData = (SymmetricWrappedExchange$ResponseData)o;
        return super.equals(o) && this.keyId.equals(symmetricWrappedExchange$ResponseData.keyId) && Arrays.equals(this.encryptionKey, symmetricWrappedExchange$ResponseData.encryptionKey) && Arrays.equals(this.hmacKey, symmetricWrappedExchange$ResponseData.hmacKey);
    }
    
    public byte[] getEncryptionKey() {
        return this.encryptionKey;
    }
    
    public byte[] getHmacKey() {
        return this.hmacKey;
    }
    
    public SymmetricWrappedExchange$KeyId getKeyId() {
        return this.keyId;
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyid", this.keyId.name());
        jsonObject.put("encryptionkey", Base64.encode(this.encryptionKey));
        jsonObject.put("hmackey", Base64.encode(this.hmacKey));
        return jsonObject;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.keyId.hashCode() ^ Arrays.hashCode(this.encryptionKey) ^ Arrays.hashCode(this.hmacKey);
    }
}
