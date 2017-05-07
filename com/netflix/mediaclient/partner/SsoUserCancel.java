// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import org.json.JSONObject;
import android.content.ComponentName;

public class SsoUserCancel extends BaseResponse
{
    public SsoUserCancel(final String s, final String s2, final ComponentName componentName) {
        super(s, s2, componentName);
    }
    
    @Override
    public JSONObject toJson() {
        final JSONObject json = this.getJson();
        json.put("status", 3);
        return json;
    }
    
    @Override
    public String toString() {
        return "SsoUserCancel [service=" + this.service + ", id=" + this.id + "]";
    }
}
