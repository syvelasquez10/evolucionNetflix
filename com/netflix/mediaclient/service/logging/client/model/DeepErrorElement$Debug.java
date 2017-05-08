// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import android.util.Log;
import com.netflix.mediaclient.util.JsonUtils;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

public class DeepErrorElement$Debug
{
    public static final String MESSAGE = "message";
    public static final String STACKTRACE = "stackTrace";
    private static final String STR_MESSAGE = "srtMessage";
    private static final String ST_FILE_NAME = "stackTraceFileName";
    private static final String ST_LINE_NUMBER = "stackTraceLineNumber";
    private static final String ST_METHOD_NAME = "stackTraceMethodName";
    @SerializedName("message")
    @Since(1.0)
    private JSONObject message;
    @SerializedName("stackTrace")
    @Since(1.0)
    private String stackTrace;
    @SerializedName("stackTraceFileName")
    @Since(1.0)
    private String stackTraceFileName;
    @SerializedName("stackTraceLineNumber")
    @Since(1.0)
    private Integer stackTraceLineNumber;
    @SerializedName("stackTraceMethodName")
    @Since(1.0)
    private String stackTraceMethodName;
    @SerializedName("strMessage")
    @Since(1.0)
    private String strMessage;
    
    public static DeepErrorElement$Debug createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final DeepErrorElement$Debug deepErrorElement$Debug = new DeepErrorElement$Debug();
        deepErrorElement$Debug.stackTrace = JsonUtils.getString(jsonObject, "stackTrace", null);
        deepErrorElement$Debug.message = JsonUtils.getJSONObject(jsonObject, "message", null);
        deepErrorElement$Debug.strMessage = JsonUtils.getString(jsonObject, "srtMessage", null);
        deepErrorElement$Debug.stackTraceFileName = JsonUtils.getString(jsonObject, "stackTraceFileName", null);
        deepErrorElement$Debug.stackTraceLineNumber = JsonUtils.getIntegerObject(jsonObject, "stackTraceLineNumber", null);
        deepErrorElement$Debug.stackTraceMethodName = JsonUtils.getString(jsonObject, "stackTraceMethodName", null);
        return deepErrorElement$Debug;
    }
    
    public JSONObject getMessage() {
        return this.message;
    }
    
    public String getStackTrace() {
        return this.stackTrace;
    }
    
    public void setMessage(final String strMessage) {
        this.strMessage = strMessage;
    }
    
    public void setMessage(final JSONObject message) {
        this.message = message;
    }
    
    public void setStackTrace(final String stackTrace) {
        this.stackTrace = stackTrace;
    }
    
    public void setStackTrace(final Throwable t) {
        if (t != null) {
            this.setStackTrace(Log.getStackTraceString(t));
            if (t.getStackTrace().length > 0 && t.getStackTrace()[0] != null) {
                this.setStackTraceFileName(t.getStackTrace()[0].getFileName());
                this.setStackTraceLineNumber(t.getStackTrace()[0].getLineNumber());
                this.setStackTraceMethodName(t.getStackTrace()[0].getMethodName());
            }
        }
    }
    
    public void setStackTraceFileName(final String stackTraceFileName) {
        this.stackTraceFileName = stackTraceFileName;
    }
    
    public void setStackTraceLineNumber(final int n) {
        this.stackTraceLineNumber = n;
    }
    
    public void setStackTraceMethodName(final String stackTraceMethodName) {
        this.stackTraceMethodName = stackTraceMethodName;
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        if (this.stackTrace != null) {
            jsonObject.put("stackTrace", (Object)this.stackTrace);
        }
        if (this.message != null) {
            jsonObject.put("message", (Object)this.message);
        }
        if (this.strMessage != null) {
            jsonObject.put("srtMessage", (Object)this.strMessage);
        }
        if (this.stackTraceFileName != null) {
            jsonObject.put("stackTraceFileName", (Object)this.stackTraceFileName);
        }
        if (this.stackTraceLineNumber != null) {
            jsonObject.put("stackTraceLineNumber", (Object)this.stackTraceLineNumber);
        }
        if (this.stackTraceMethodName != null) {
            jsonObject.put("stackTraceMethodName", (Object)this.stackTraceMethodName);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "Debug [stackTrace=" + this.stackTrace + ", message=" + this.message + "]";
    }
}
