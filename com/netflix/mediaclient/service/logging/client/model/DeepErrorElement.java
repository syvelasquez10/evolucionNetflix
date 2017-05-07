// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import org.json.JSONException;
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
    private Debug debug;
    @SerializedName("errorCode")
    @Since(1.0)
    private String errorCode;
    @SerializedName("fatal")
    @Since(1.0)
    private boolean fatal;
    
    public static DeepErrorElement createInstance(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        deepErrorElement.debug = Debug.createInstance(JsonUtils.getJSONObject(jsonObject, "debug", null));
        deepErrorElement.errorCode = JsonUtils.getString(jsonObject, "errorCode", null);
        deepErrorElement.fatal = JsonUtils.getBoolean(jsonObject, "fatal", false);
        return deepErrorElement;
    }
    
    public Debug getDebug() {
        return this.debug;
    }
    
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public boolean isFatal() {
        return this.fatal;
    }
    
    public void setDebug(final Debug debug) {
        this.debug = debug;
    }
    
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }
    
    public void setFatal(final boolean fatal) {
        this.fatal = fatal;
    }
    
    public JSONObject toJSONObject() throws JSONException {
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
    
    public static class Debug
    {
        public static final String MESSAGE = "message";
        public static final String STACKTRACE = "stackTrace";
        @SerializedName("message")
        @Since(1.0)
        private JSONObject message;
        @SerializedName("stackTrace")
        @Since(1.0)
        private String stackTrace;
        
        public static Debug createInstance(final JSONObject jsonObject) throws JSONException {
            if (jsonObject == null) {
                return null;
            }
            final Debug debug = new Debug();
            debug.stackTrace = JsonUtils.getString(jsonObject, "stackTrace", null);
            debug.message = JsonUtils.getJSONObject(jsonObject, "message", null);
            return debug;
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
        
        public JSONObject toJSONObject() throws JSONException {
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
}
