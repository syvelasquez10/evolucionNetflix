// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import com.netflix.mediaclient.util.JsonUtils;
import com.netflix.mediaclient.Log;
import org.json.JSONException;
import org.json.JSONObject;

public final class ActivationTokens
{
    private static final String TAG = "nf_reg";
    private JSONObject json;
    public String netflixId;
    public String secureNetflixId;
    
    public ActivationTokens(final String s) {
        this.parseTokens(s);
    }
    
    public ActivationTokens(final String netflixId, final String secureNetflixId) {
        this.netflixId = netflixId;
        this.secureNetflixId = secureNetflixId;
        this.createJson();
    }
    
    public ActivationTokens(final JSONObject jsonObject) {
        this.parseTokens(jsonObject);
    }
    
    private void createJson() {
        if (this.netflixId != null && this.secureNetflixId != null) {
            (this.json = new JSONObject()).put("NetflixId", (Object)this.netflixId);
            this.json.put("SecureNetflixId", (Object)this.secureNetflixId);
            return;
        }
        throw new JSONException("Tokens cannot be null");
    }
    
    private void parseTokens(final String s) {
        if (s == null) {
            Log.e("nf_reg", "Tokens are null");
            return;
        }
        if ("undefined".equalsIgnoreCase(s)) {
            Log.d("nf_reg", "Tokens undefined");
            return;
        }
        this.parseTokens(this.json = new JSONObject(s));
    }
    
    private void parseTokens(final JSONObject json) {
        this.json = json;
        if (json == null) {
            Log.e("nf_reg", "Tokens are null");
        }
        else {
            this.netflixId = JsonUtils.getString(json, "NetflixId", null);
            this.secureNetflixId = JsonUtils.getString(json, "SecureNetflixId", null);
            if (Log.isLoggable("nf_reg", 3)) {
                Log.d("nf_reg", "NetflixID: " + this.netflixId + ", SecureNetlixId: " + this.secureNetflixId);
            }
            if (this.netflixId == null || this.secureNetflixId == null) {
                throw new JSONException("Tokens cannot be null");
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof ActivationTokens)) {
                return false;
            }
            final ActivationTokens activationTokens = (ActivationTokens)o;
            if (this.netflixId == null) {
                if (activationTokens.netflixId != null) {
                    return false;
                }
            }
            else if (!this.netflixId.equals(activationTokens.netflixId)) {
                return false;
            }
            if (this.secureNetflixId == null) {
                if (activationTokens.secureNetflixId != null) {
                    return false;
                }
            }
            else if (!this.secureNetflixId.equals(activationTokens.secureNetflixId)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.netflixId == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.netflixId.hashCode();
        }
        if (this.secureNetflixId != null) {
            hashCode = this.secureNetflixId.hashCode();
        }
        return (hashCode2 + 31) * 31 + hashCode;
    }
    
    public JSONObject toJSON() {
        return this.json;
    }
    
    @Override
    public String toString() {
        return this.json.toString();
    }
}
