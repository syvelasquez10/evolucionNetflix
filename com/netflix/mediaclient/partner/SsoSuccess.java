// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import android.content.ComponentName;
import org.json.JSONObject;

public class SsoSuccess extends BaseResponse
{
    protected JSONObject token;
    protected String userId;
    
    public SsoSuccess(final String s, final String s2, final String userId, final JSONObject token, final ComponentName componentName) {
        super(s, s2, componentName);
        this.token = token;
        this.userId = userId;
    }
    
    public JSONObject getToken() {
        return this.token;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    @Override
    public JSONObject toJson() {
        final JSONObject json = this.getJson();
        json.put("status", 0);
        json.put("userid", (Object)BaseResponse.noNull(this.userId));
        json.put("token", (Object)this.token);
        return json;
    }
    
    @Override
    public String toString() {
        return "SsoSuccess [service=" + this.service + ", userId=" + this.userId + ", token=" + this.token + ", idx" + this.id + "]";
    }
}
