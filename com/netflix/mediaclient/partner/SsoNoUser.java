// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.ComponentName;

public class SsoNoUser extends BaseResponse
{
    public SsoNoUser(final String s, final String s2, final ComponentName componentName) {
        super(s, s2, componentName);
    }
    
    @Override
    public JSONObject toJson() throws JSONException {
        final JSONObject json = this.getJson();
        json.put("status", 2);
        return json;
    }
    
    @Override
    public String toString() {
        return "SsoNoUser [service=" + this.service + ", id=" + this.id + "]";
    }
}
