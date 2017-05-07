// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import com.netflix.mediaclient.util.JsonUtils;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

public class DeepErrorElement$Debug
{
    public static final String MESSAGE = "message";
    public static final String STACKTRACE = "stackTrace";
    @SerializedName("message")
    @Since(1.0)
    private JSONObject message;
    @SerializedName("stackTrace")
    @Since(1.0)
    private String stackTrace;
    
    public static DeepErrorElement$Debug createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final DeepErrorElement$Debug deepErrorElement$Debug = new DeepErrorElement$Debug();
        deepErrorElement$Debug.stackTrace = JsonUtils.getString(jsonObject, "stackTrace", null);
        deepErrorElement$Debug.message = JsonUtils.getJSONObject(jsonObject, "message", null);
        return deepErrorElement$Debug;
    }
    
    public JSONObject getMessage() {
        return this.message;
    }
    
    public String getStackTrace() {
        return this.stackTrace;
    }
    
    public void setMessage(final JSONObject message) {
        this.message = message;
    }
    
    public void setStackTrace(final String stackTrace) {
        this.stackTrace = stackTrace;
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        if (this.stackTrace != null) {
            jsonObject.put("stackTrace", (Object)this.stackTrace);
        }
        if (this.message != null) {
            jsonObject.put("message", (Object)this.message);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "Debug [stackTrace=" + this.stackTrace + ", message=" + this.message + "]";
    }
}
