// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;

public class PresharedAuthenticationData extends EntityAuthenticationData
{
    private static final String KEY_IDENTITY = "identity";
    private final String identity;
    
    PresharedAuthenticationData(final JSONObject jsonObject) {
        super(EntityAuthenticationScheme.PSK);
        try {
            this.identity = jsonObject.getString("identity");
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "psk authdata " + jsonObject.toString(), ex);
        }
    }
    
    public PresharedAuthenticationData(final String identity) {
        super(EntityAuthenticationScheme.PSK);
        this.identity = identity;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PresharedAuthenticationData)) {
            return false;
        }
        final PresharedAuthenticationData presharedAuthenticationData = (PresharedAuthenticationData)o;
        return super.equals(o) && this.identity.equals(presharedAuthenticationData.identity);
    }
    
    @Override
    public JSONObject getAuthData() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("identity", this.identity);
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
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.identity.hashCode();
    }
}
