// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import java.util.HashMap;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.annotations.SerializedName;

public class SignInConfigData
{
    private static final int MAX_EMAIL_LEN = 50;
    private static final int MAX_PWD_LEN = 50;
    private static final int MIN_EMAIL_LEN = 5;
    private static final int MIN_PWD_LEN = 4;
    private static final String TAG = "nf_config_signin";
    @SerializedName("fields")
    public SignInConfigData$Fields fields;
    @SerializedName("flwssn")
    public String flwssn;
    @SerializedName("mode")
    public String nextStep;
    
    public static SignInConfigData fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if (Log.isLoggable()) {
            Log.v("nf_config_signin", "Parsing signInConfigData from json: " + s);
        }
        return FalkorParseUtils.getGson().fromJson(s, SignInConfigData.class);
    }
    
    private int getMaxEmailLen() {
        if (this.fields == null || this.fields.email == null || this.fields.email.maxLength <= 0) {
            return 50;
        }
        return this.fields.email.maxLength;
    }
    
    private int getMaxPwdLen() {
        if (this.fields == null || this.fields.password == null || this.fields.password.maxLength <= 0) {
            return 50;
        }
        return this.fields.password.maxLength;
    }
    
    private int getMinEmailLen() {
        if (this.fields == null || this.fields.email == null || this.fields.email.minLength <= 0) {
            return 5;
        }
        return this.fields.email.minLength;
    }
    
    private int getMinPwdLen() {
        if (this.fields == null || this.fields.password == null || this.fields.password.minLength <= 0) {
            return 4;
        }
        return this.fields.password.minLength;
    }
    
    public HashMap<String, Object> getReactNativeMode() {
        if (this.fields == null || this.fields.backAction == null || this.fields.backAction.cached == null) {
            return null;
        }
        return this.fields.backAction.cached;
    }
    
    public boolean isEmailValid(final String s) {
        return !StringUtils.isEmpty(s) && s.length() >= this.getMinEmailLen();
    }
    
    public boolean isPasswordValid(final String s) {
        return !StringUtils.isEmpty(s) && s.length() >= this.getMinPwdLen();
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_config_signin", "SignInConfigData as json: " + json);
        }
        return json;
    }
}
