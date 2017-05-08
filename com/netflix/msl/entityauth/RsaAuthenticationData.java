// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;

public class RsaAuthenticationData extends EntityAuthenticationData
{
    private static final String KEY_IDENTITY = "identity";
    private static final String KEY_PUBKEY_ID = "pubkeyid";
    private final String identity;
    private final String pubkeyid;
    
    RsaAuthenticationData(final JSONObject jsonObject) {
        super(EntityAuthenticationScheme.RSA);
        try {
            this.identity = jsonObject.getString("identity");
            this.pubkeyid = jsonObject.getString("pubkeyid");
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "RSA authdata " + jsonObject.toString(), ex);
        }
    }
    
    public RsaAuthenticationData(final String identity, final String pubkeyid) {
        super(EntityAuthenticationScheme.RSA);
        this.identity = identity;
        this.pubkeyid = pubkeyid;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RsaAuthenticationData)) {
            return false;
        }
        final RsaAuthenticationData rsaAuthenticationData = (RsaAuthenticationData)o;
        return super.equals(o) && this.identity.equals(rsaAuthenticationData.identity) && this.pubkeyid.equals(rsaAuthenticationData.pubkeyid);
    }
    
    @Override
    public JSONObject getAuthData() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("identity", this.identity);
            jsonObject.put("pubkeyid", this.pubkeyid);
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "psk authdata", ex);
        }
    }
    
    @Override
    public String getIdentity() {
        return this.identity;
    }
    
    public String getPublicKeyId() {
        return this.pubkeyid;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ (this.identity + "|" + this.pubkeyid).hashCode();
    }
}
