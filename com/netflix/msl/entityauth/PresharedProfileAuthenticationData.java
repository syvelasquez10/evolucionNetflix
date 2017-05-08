// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;

public class PresharedProfileAuthenticationData extends EntityAuthenticationData
{
    private static final String CONCAT_CHAR = "-";
    private static final String KEY_PROFILE = "profile";
    private static final String KEY_PSKID = "pskid";
    private final String profile;
    private final String pskid;
    
    public PresharedProfileAuthenticationData(final JSONObject jsonObject) {
        super(EntityAuthenticationScheme.PSK_PROFILE);
        try {
            this.pskid = jsonObject.getString("pskid");
            this.profile = jsonObject.getString("profile");
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "psk profile authdata " + jsonObject.toString(), ex);
        }
    }
    
    public PresharedProfileAuthenticationData(final String pskid, final String profile) {
        super(EntityAuthenticationScheme.PSK_PROFILE);
        this.pskid = pskid;
        this.profile = profile;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PresharedProfileAuthenticationData)) {
            return false;
        }
        final PresharedProfileAuthenticationData presharedProfileAuthenticationData = (PresharedProfileAuthenticationData)o;
        return super.equals(o) && this.pskid.equals(presharedProfileAuthenticationData.pskid) && this.profile.equals(presharedProfileAuthenticationData.profile);
    }
    
    @Override
    public JSONObject getAuthData() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("pskid", this.pskid);
            jsonObject.put("profile", this.profile);
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "psk profile authdata", ex);
        }
    }
    
    @Override
    public String getIdentity() {
        return this.pskid + "-" + this.profile;
    }
    
    public String getPresharedKeysId() {
        return this.pskid;
    }
    
    public String getProfile() {
        return this.profile;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.pskid.hashCode() ^ this.profile.hashCode();
    }
}
