// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import java.util.Arrays;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public final class DeviceAccount
{
    private String accountKey;
    private String[] languages;
    private long lastAccessTime;
    private String netflixId;
    private boolean registered;
    private String secureId;
    
    public DeviceAccount(final JSONObject jsonObject) {
        int i = 0;
        this.accountKey = JsonUtils.getString(jsonObject, "accountKey", (String)null);
        this.registered = JsonUtils.getBoolean(jsonObject, "registered", false);
        this.lastAccessTime = JsonUtils.getLong(jsonObject, "lastAccessTime", 0L);
        if (jsonObject.has("tokens")) {
            final JSONObject jsonObject2 = jsonObject.getJSONObject("tokens");
            if (jsonObject2 != null) {
                this.netflixId = JsonUtils.getString(jsonObject2, "NetflixId", "");
                this.secureId = JsonUtils.getString(jsonObject2, "SecureNetflixId", "");
            }
        }
        if (jsonObject.has("UILanguages")) {
            final JSONArray jsonArray = jsonObject.getJSONArray("UILanguages");
            if (jsonArray != null) {
                this.languages = new String[jsonArray.length()];
                while (i < jsonArray.length()) {
                    this.languages[i] = jsonArray.getString(i);
                    ++i;
                }
            }
        }
    }
    
    public String getAccountKey() {
        return this.accountKey;
    }
    
    public String[] getLanguages() {
        return this.languages;
    }
    
    public long getLastAccessTime() {
        return this.lastAccessTime;
    }
    
    public String getNetflixId() {
        return this.netflixId;
    }
    
    public String getSecureId() {
        return this.secureId;
    }
    
    public boolean isRegistered() {
        return this.registered;
    }
    
    public void setLanguages(final String[] languages) {
        this.languages = languages;
    }
    
    public void setLastAccessTime(final long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }
    
    public void setNetflixId(final String netflixId) {
        this.netflixId = netflixId;
    }
    
    public void setRegistered(final boolean registered) {
        this.registered = registered;
    }
    
    public void setSecureId(final String secureId) {
        this.secureId = secureId;
    }
    
    @Override
    public String toString() {
        return "DeviceAccount [accountKey=" + this.accountKey + ", netflixId=" + this.netflixId + ", secureId=" + this.secureId + ", lastAccessTime=" + this.lastAccessTime + ", languages=" + Arrays.toString(this.languages) + ", registered=" + this.registered + "]";
    }
}
