// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import android.content.ComponentName;
import org.json.JSONObject;

public class ExternalUserConfirmationSuccess extends BaseResponse
{
    protected static final String RESULT_confirmed = "confirmed";
    protected boolean confirmed;
    protected JSONObject token;
    protected String userId;
    
    public ExternalUserConfirmationSuccess(final String s, final String s2, final String userId, final boolean confirmed, final JSONObject token, final ComponentName componentName) {
        super(s, s2, componentName);
        this.userId = userId;
        this.confirmed = confirmed;
        this.token = token;
    }
    
    public JSONObject getToken() {
        return this.token;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public boolean isConfirmed() {
        return this.confirmed;
    }
    
    @Override
    public JSONObject toJson() {
        final JSONObject json = this.getJson();
        json.put("status", 0);
        json.put("confirmed", this.confirmed);
        json.put("userid", (Object)this.userId);
        json.put("token", (Object)this.token);
        return json;
    }
    
    @Override
    public String toString() {
        return "ExternalUserConfirmationSuccess [userId=" + this.userId + ", confirmed=" + this.confirmed + ", token=" + this.token + ", service=" + this.service + ", id=" + this.id + "]";
    }
}
