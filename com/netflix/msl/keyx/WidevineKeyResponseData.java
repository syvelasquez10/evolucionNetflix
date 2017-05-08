// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.tokens.MasterToken;

public class WidevineKeyResponseData extends KeyResponseData
{
    private static final String KEY_CDM_KEY_RESPONSE = "cdmkeyresponse";
    private static final String KEY_ENCRYPTION_KEY_ID = "encryptionkeyid";
    private static final String KEY_HMAC_KEY_ID = "hmackeyid";
    private final String encryptionKeyId;
    private final String hmacKeyId;
    private final String keyResponse;
    
    public WidevineKeyResponseData(final MasterToken masterToken, final JSONObject jsonObject) {
        super(masterToken, NetflixKeyExchangeScheme.WIDEVINE);
        try {
            this.keyResponse = jsonObject.getString("cdmkeyresponse");
            this.encryptionKeyId = jsonObject.getString("encryptionkeyid");
            this.hmacKeyId = jsonObject.getString("hmackeyid");
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "keydata " + jsonObject.toString(), ex);
        }
    }
    
    public WidevineKeyResponseData(final MasterToken masterToken, final String keyResponse, final String encryptionKeyId, final String hmacKeyId) {
        super(masterToken, NetflixKeyExchangeScheme.WIDEVINE);
        this.keyResponse = keyResponse;
        this.encryptionKeyId = encryptionKeyId;
        this.hmacKeyId = hmacKeyId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WidevineKeyResponseData)) {
            return false;
        }
        final WidevineKeyResponseData widevineKeyResponseData = (WidevineKeyResponseData)o;
        return super.equals(o) && this.keyResponse.equals(widevineKeyResponseData.keyResponse) && this.encryptionKeyId.equals(widevineKeyResponseData.encryptionKeyId) && this.hmacKeyId.equals(widevineKeyResponseData.hmacKeyId);
    }
    
    public String getEncryptionKeyId() {
        return this.encryptionKeyId;
    }
    
    public String getHmacKeyId() {
        return this.hmacKeyId;
    }
    
    public String getKeyResponse() {
        return this.keyResponse;
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("encryptionkeyid", this.encryptionKeyId);
        jsonObject.put("hmackeyid", this.hmacKeyId);
        jsonObject.put("cdmkeyresponse", this.keyResponse);
        return jsonObject;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.keyResponse.hashCode() ^ this.encryptionKeyId.hashCode() ^ this.hmacKeyId.hashCode();
    }
}
