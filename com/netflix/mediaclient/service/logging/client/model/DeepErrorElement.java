// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;

public class DeepErrorElement
{
    public static final String DEBUG = "debug";
    public static final String ERROR_CODE = "errorCode";
    public static final String FATAL = "fatal";
    @SerializedName("debug")
    @Since(1.0)
    private DeepErrorElement$Debug debug;
    @SerializedName("errorCode")
    @Since(1.0)
    private String errorCode;
    @SerializedName("fatal")
    @Since(1.0)
    private boolean fatal;
    
    public static DeepErrorElement createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        deepErrorElement.debug = DeepErrorElement$Debug.createInstance(JsonUtils.getJSONObject(jsonObject, "debug", null));
        deepErrorElement.errorCode = JsonUtils.getString(jsonObject, "errorCode", null);
        deepErrorElement.fatal = JsonUtils.getBoolean(jsonObject, "fatal", false);
        return deepErrorElement;
    }
    
    public DeepErrorElement$Debug getDebug() {
        return this.debug;
    }
    
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public boolean isFatal() {
        return this.fatal;
    }
    
    public void setDebug(final DeepErrorElement$Debug debug) {
        this.debug = debug;
    }
    
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }
    
    public void setFatal(final boolean fatal) {
        this.fatal = fatal;
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        if (this.debug != null) {
            jsonObject.put("debug", (Object)this.debug.toJSONObject());
        }
        if (this.errorCode != null) {
            jsonObject.put("errorCode", (Object)this.errorCode);
        }
        jsonObject.put("fatal", this.fatal);
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "DeepErrorElement [errorCode=" + this.errorCode + ", fatal=" + this.fatal + ", debug=" + this.debug + "]";
    }
}
