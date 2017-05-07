// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.Log;

public final class NativeLog extends NativeNrdObject implements Log
{
    public static final String METHOD_flush = "flush";
    public static final String METHOD_log = "log";
    public static final String METHOD_resetSessionID = "resetSessionID";
    private String mAppId;
    private ResetSessionIdCallback mSessionCallback;
    private String mSessionId;
    private String mXid;
    
    public NativeLog(final Bridge bridge) {
        super(bridge);
    }
    
    private int handleEvent(final JSONObject jsonObject) throws Exception {
        return 0;
    }
    
    private int handlePropertyUpdate(final JSONObject jsonObject) throws JSONException {
        final JSONObject jsonObject2 = this.getJSONObject(jsonObject, "properties", null);
        if (jsonObject2 == null) {
            com.netflix.mediaclient.Log.w("nf_object", "Log.handlePropertyUpdate:: properties does not exist");
            return 0;
        }
        if (jsonObject2.has("appid")) {
            this.mAppId = this.getString(jsonObject2, "appid", null);
        }
        if (jsonObject2.has("xid")) {
            this.mXid = this.getString(jsonObject2, "xid", null);
        }
        if (jsonObject2.has("sessionid")) {
            final String mSessionId = this.mSessionId;
            final String string = this.getString(jsonObject2, "sessionid", null);
            if (com.netflix.mediaclient.Log.isLoggable("nf_object", 3)) {
                com.netflix.mediaclient.Log.d("nf_object", "Log.handlePropertyUpdate:: Old session id: " + mSessionId + ", new session id " + string);
            }
            this.mSessionId = string;
            final boolean b = false;
            boolean b2;
            if (mSessionId == null && string != null) {
                b2 = true;
            }
            else {
                b2 = b;
                if (mSessionId != null) {
                    b2 = b;
                    if (!mSessionId.equals(string)) {
                        b2 = true;
                    }
                }
            }
            if (b2 && this.mSessionCallback != null) {
                com.netflix.mediaclient.Log.w("nf_object", "Log.handlePropertyUpdate:: session id is changed and callback exist, report");
                this.mSessionCallback.sessionCreated(string);
                this.mSessionCallback = null;
            }
            else if (b2 && this.mSessionCallback == null) {
                com.netflix.mediaclient.Log.w("nf_object", "Log.handlePropertyUpdate:: session id is changed but callback does NOT exist");
            }
            else {
                com.netflix.mediaclient.Log.w("nf_object", "Log.handlePropertyUpdate:: session id is not changed");
            }
        }
        return -1;
    }
    
    @Override
    public void addEventListener(final String s, final EventListener eventListener) {
    }
    
    @Override
    public void flush() {
        this.bridge.getNrdProxy().invokeMethod("log", "flush", null);
    }
    
    @Override
    public String getAppId() {
        return this.mAppId;
    }
    
    @Override
    public String getName() {
        return "log";
    }
    
    @Override
    public String getPath() {
        return "nrdp.log";
    }
    
    @Override
    public String getSessionId() {
        return this.mSessionId;
    }
    
    @Override
    public String getXid() {
        return this.mXid;
    }
    
    @Override
    public void log(final LogArguments logArguments) {
        try {
            this.bridge.getNrdProxy().invokeMethod("log", "log", logArguments.toJson().toString());
        }
        catch (JSONException ex) {
            com.netflix.mediaclient.Log.e("nf_object", "Failed with JSON", (Throwable)ex);
        }
    }
    
    @Override
    public int processUpdate(final JSONObject jsonObject) {
        try {
            final String string = this.getString(jsonObject, "type", null);
            if (com.netflix.mediaclient.Log.isLoggable("nf_object", 3)) {
                com.netflix.mediaclient.Log.d("nf_object", "processUpdate: handle type " + string);
            }
            if ("PropertyUpdate".equalsIgnoreCase(string)) {
                if (jsonObject != null && com.netflix.mediaclient.Log.isLoggable("nf_object", 3)) {
                    com.netflix.mediaclient.Log.d("nf_object", "processUpdate: handle prop update " + jsonObject.toString());
                }
                return this.handlePropertyUpdate(jsonObject);
            }
            com.netflix.mediaclient.Log.d("nf_object", "processUpdate: handle event");
            return this.handleEvent(jsonObject);
        }
        catch (Exception ex) {
            com.netflix.mediaclient.Log.e("nf_object", "Failed with JSON", ex);
            return 0;
        }
    }
    
    @Override
    public void removeEventListener(final String s, final EventListener eventListener) {
    }
    
    @Override
    public void resetSessionID(final ResetSessionIdCallback mSessionCallback) {
        this.mSessionCallback = mSessionCallback;
        this.bridge.getNrdProxy().invokeMethod("log", "resetSessionID", null);
    }
}
