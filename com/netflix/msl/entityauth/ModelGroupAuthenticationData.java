// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;

public class ModelGroupAuthenticationData extends EntityAuthenticationData
{
    private static final String KEY_IDENTITY = "identity";
    private final String identity;
    
    public ModelGroupAuthenticationData(final JSONObject jsonObject) {
        super(NetflixEntityAuthenticationScheme.MGK);
        try {
            this.identity = jsonObject.getString("identity");
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "mgk authdata " + jsonObject.toString(), ex);
        }
    }
    
    public ModelGroupAuthenticationData(final String identity) {
        super(NetflixEntityAuthenticationScheme.MGK);
        this.identity = identity;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ModelGroupAuthenticationData)) {
            return false;
        }
        final ModelGroupAuthenticationData modelGroupAuthenticationData = (ModelGroupAuthenticationData)o;
        return super.equals(o) && this.identity.equals(modelGroupAuthenticationData.identity);
    }
    
    @Override
    public JSONObject getAuthData() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("identity", this.identity);
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "mgk authdata", ex);
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
