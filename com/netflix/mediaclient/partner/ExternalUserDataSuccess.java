// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import android.content.ComponentName;
import org.json.JSONObject;

public class ExternalUserDataSuccess extends BaseResponse
{
    protected static final String RESULT_data = "data";
    protected JSONObject data;
    protected String userId;
    
    public ExternalUserDataSuccess(final String s, final String s2, final String userId, final JSONObject data, final ComponentName componentName) {
        super(s, s2, componentName);
        this.userId = userId;
        this.data = data;
    }
    
    public JSONObject getData() {
        return this.data;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    @Override
    public JSONObject toJson() {
        final JSONObject json = this.getJson();
        json.put("status", 0);
        json.put("data", (Object)this.data);
        json.put("userid", (Object)this.userId);
        return json;
    }
    
    @Override
    public String toString() {
        return "ExternalUserDataSuccess [userId=" + this.userId + ", data=" + this.data + ", service=" + this.service + ", id=" + this.id + "]";
    }
}
