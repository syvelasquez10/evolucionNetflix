// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.userauth;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslUserAuthException;
import com.netflix.msl.NetflixMslError;
import com.netflix.android.org.json.JSONObject;

public final class NetflixIdAuthenticationData extends UserAuthenticationData
{
    private static final String KEY_NETFLIXID = "netflixid";
    private static final String KEY_SECURENETFLIXID = "securenetflixid";
    private final String netflixId;
    private final String secureNetflixId;
    
    public NetflixIdAuthenticationData(final JSONObject jsonObject) {
        this(jsonObject, null, null);
    }
    
    public NetflixIdAuthenticationData(final JSONObject jsonObject, final String s, final String s2) {
        super(NetflixUserAuthenticationScheme.NETFLIXID);
        try {
            this.netflixId = jsonObject.optString("netflixid", s);
            this.secureNetflixId = jsonObject.optString("securenetflixid", s2);
            if (this.netflixId == null || this.netflixId.isEmpty()) {
                throw new MslUserAuthException(NetflixMslError.NETFLIXID_COOKIES_BLANK, "NetflixId authdata missing in payload and no fallback provided " + jsonObject.toString()).setUserAuthenticationData(this);
            }
        }
        catch (Exception ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "NetflixId authdata " + jsonObject.toString(), ex);
        }
    }
    
    public NetflixIdAuthenticationData(final String netflixId, final String secureNetflixId) {
        super(NetflixUserAuthenticationScheme.NETFLIXID);
        this.netflixId = netflixId;
        this.secureNetflixId = secureNetflixId;
    }
    
    protected boolean canEqual(final Object o) {
        return o instanceof NetflixIdAuthenticationData;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NetflixIdAuthenticationData)) {
            return false;
        }
        final NetflixIdAuthenticationData netflixIdAuthenticationData = (NetflixIdAuthenticationData)o;
        if (!netflixIdAuthenticationData.canEqual(this)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final String netflixId = this.getNetflixId();
        final String netflixId2 = netflixIdAuthenticationData.getNetflixId();
        Label_0069: {
            if (netflixId == null) {
                if (netflixId2 == null) {
                    break Label_0069;
                }
            }
            else if (netflixId.equals(netflixId2)) {
                break Label_0069;
            }
            return false;
        }
        final String secureNetflixId = this.getSecureNetflixId();
        final String secureNetflixId2 = netflixIdAuthenticationData.getSecureNetflixId();
        if (secureNetflixId == null) {
            if (secureNetflixId2 == null) {
                return true;
            }
        }
        else if (secureNetflixId.equals(secureNetflixId2)) {
            return true;
        }
        return false;
    }
    
    @Override
    public JSONObject getAuthData() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("netflixid", this.netflixId);
            if (this.secureNetflixId != null) {
                jsonObject.put("securenetflixid", this.secureNetflixId);
            }
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "NetflidId authdata", ex);
        }
    }
    
    public String getNetflixId() {
        return this.netflixId;
    }
    
    public String getSecureNetflixId() {
        return this.secureNetflixId;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 43;
        final int hashCode2 = super.hashCode();
        final String netflixId = this.getNetflixId();
        int hashCode3;
        if (netflixId == null) {
            hashCode3 = 43;
        }
        else {
            hashCode3 = netflixId.hashCode();
        }
        final String secureNetflixId = this.getSecureNetflixId();
        if (secureNetflixId != null) {
            hashCode = secureNetflixId.hashCode();
        }
        return (hashCode3 + (hashCode2 + 59) * 59) * 59 + hashCode;
    }
    
    @Override
    public String toString() {
        return "NetflixIdAuthenticationData(netflixId=" + this.getNetflixId() + ", secureNetflixId=" + this.getSecureNetflixId() + ")";
    }
}
