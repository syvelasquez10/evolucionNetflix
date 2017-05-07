// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.ComponentName;

public class SsoFailure extends BaseResponse
{
    protected String errcode;
    protected String msg;
    
    public SsoFailure(final String s, final String s2, final String errcode, final String msg, final ComponentName componentName) {
        super(s, s2, componentName);
        this.errcode = errcode;
        this.msg = msg;
    }
    
    public String getErrcode() {
        return this.errcode;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    @Override
    public JSONObject toJson() throws JSONException {
        final JSONObject json = this.getJson();
        json.put("status", 1);
        json.put("errcode", (Object)this.errcode);
        json.put("msg", (Object)this.msg);
        return json;
    }
    
    @Override
    public String toString() {
        return "SsoFailure [errcode=" + this.errcode + ", msg=" + this.msg + ", service=" + this.service + ", idx" + this.id + "]";
    }
}
