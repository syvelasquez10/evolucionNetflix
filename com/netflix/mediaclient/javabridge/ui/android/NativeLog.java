// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import org.json.JSONException;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.Log$ResetSessionIdCallback;
import com.netflix.mediaclient.javabridge.ui.Log$AppIdSetListener;
import com.netflix.mediaclient.javabridge.ui.Log$AppIdChangedListener;
import com.netflix.mediaclient.javabridge.ui.Log;

public final class NativeLog extends NativeNrdObject implements Log
{
    public static final String CMD_RESULT_EVENT_resetAppID = "appIDChanged";
    public static final String CMD_RESULT_EVENT_resetSessionID = "sessionIDChanged";
    public static final String METHOD_flush = "flush";
    public static final String METHOD_log = "log";
    public static final String METHOD_resetAppID = "resetAppID";
    public static final String METHOD_resetSessionID = "resetSessionID";
    public static final String PROPERTY_APP_ID = "appid";
    public static final String PROPERTY_SESSION_ID = "sessionid";
    public static final String PROPERTY_XID = "xid";
    private String mAppId;
    private Log$AppIdChangedListener mAppIdListener;
    private Log$AppIdSetListener mAppIdSetListener;
    private Log$ResetSessionIdCallback mSessionCallback;
    private String mSessionId;
    private String mXid;
    
    public NativeLog(final Bridge bridge) {
        super(bridge);
    }
    
    private int handleEvent(final JSONObject jsonObject) {
        final JSONObject jsonObject2 = this.getJSONObject(jsonObject, "data", null);
        final String string = this.getString(jsonObject, "name", null);
        if (jsonObject2 != null) {
            com.netflix.mediaclient.Log.w("nf_object", "handleEvent data !null");
            if ("appIDChanged".equals(string)) {
                return this.handleResetAppId(jsonObject2);
            }
            if ("sessionIDChanged".equals(string)) {
                return this.handleResetSessionId(jsonObject2);
            }
        }
        com.netflix.mediaclient.Log.w("nf_object", "Nobody to handle!");
        return 1;
    }
    
    private int handlePropertyUpdate(JSONObject jsonObject) {
        jsonObject = this.getJSONObject(jsonObject, "properties", null);
        if (jsonObject == null) {
            com.netflix.mediaclient.Log.w("nf_object", "Log.handlePropertyUpdate:: properties does not exist");
            return 0;
        }
        if (jsonObject.has("appid")) {
            this.mAppId = this.getString(jsonObject, "appid", null);
            if (this.mAppIdSetListener != null) {
                if (com.netflix.mediaclient.Log.isLoggable("nf_object", 3)) {
                    com.netflix.mediaclient.Log.d("nf_object", "App ID listener existed, alerted to first app id received " + this.mAppId + " and it is removed.");
                }
                this.mAppIdSetListener.onSet(this.mAppId);
                this.mAppIdSetListener = null;
            }
        }
        if (jsonObject.has("xid")) {
            this.mXid = this.getString(jsonObject, "xid", null);
        }
        if (jsonObject.has("sessionid")) {
            this.mSessionId = this.getString(jsonObject, "sessionid", null);
        }
        return -1;
    }
    
    private int handleResetAppId(final JSONObject jsonObject) {
        final String mSessionId = this.mSessionId;
        final String mSessionId2 = this.mSessionId;
        final String string = this.getString(jsonObject, "sessionid", null);
        final String string2 = this.getString(jsonObject, "appid", null);
        if (com.netflix.mediaclient.Log.isLoggable("nf_object", 3)) {
            com.netflix.mediaclient.Log.d("nf_object", "Log.handleResetAppId:: Old app id: " + mSessionId + ", new app id " + string2);
            com.netflix.mediaclient.Log.d("nf_object", "Log.handleResetAppId:: Old session id: " + mSessionId2 + ", new session id " + string);
        }
        this.mAppId = string2;
        this.mSessionId = string;
        if (this.mAppIdSetListener != null) {
            if (com.netflix.mediaclient.Log.isLoggable("nf_object", 3)) {
                com.netflix.mediaclient.Log.d("nf_object", "App ID listener existed, alerted to first app id received " + this.mAppId + " and it is removed.");
            }
            this.mAppIdSetListener.onSet(this.mAppId);
            this.mAppIdSetListener = null;
        }
        if (this.mAppIdListener != null) {
            com.netflix.mediaclient.Log.w("nf_object", "Log.handleResetAppId:: app id is changed and callback exist, report");
            this.mAppIdListener.changed(string2, string);
        }
        else {
            com.netflix.mediaclient.Log.w("nf_object", "Log.handleResetAppId:: app id is changed but callback does NOT exist");
        }
        return 1;
    }
    
    private int handleResetSessionId(final JSONObject jsonObject) {
        final String mSessionId = this.mSessionId;
        final String string = this.getString(jsonObject, "sessionid", null);
        if (com.netflix.mediaclient.Log.isLoggable("nf_object", 3)) {
            com.netflix.mediaclient.Log.d("nf_object", "Log.handleResetSessionId:: Old session id: " + mSessionId + ", new session id " + string);
        }
        this.mSessionId = string;
        if (this.mSessionCallback != null) {
            com.netflix.mediaclient.Log.w("nf_object", "Log.handleResetSessionId:: session id is changed and callback exist, report");
            this.mSessionCallback.sessionCreated(string);
            this.mSessionCallback = null;
        }
        else {
            com.netflix.mediaclient.Log.w("nf_object", "Log.handleResetSessionId:: session id is changed but callback does NOT exist");
        }
        return 1;
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
        synchronized (this) {
            try {
                final String string = this.getString(jsonObject, "type", null);
                if (com.netflix.mediaclient.Log.isLoggable("nf_object", 3)) {
                    com.netflix.mediaclient.Log.d("nf_object", "processUpdate: handle type " + string);
                }
                int n;
                if ("PropertyUpdate".equalsIgnoreCase(string)) {
                    if (jsonObject != null && com.netflix.mediaclient.Log.isLoggable("nf_object", 3)) {
                        com.netflix.mediaclient.Log.d("nf_object", "processUpdate: handle prop update " + jsonObject.toString());
                    }
                    n = this.handlePropertyUpdate(jsonObject);
                }
                else {
                    com.netflix.mediaclient.Log.d("nf_object", "processUpdate: handle event");
                    n = this.handleEvent(jsonObject);
                }
                return n;
            }
            catch (Exception ex) {
                com.netflix.mediaclient.Log.e("nf_object", "Failed with JSON", ex);
                return 0;
            }
        }
    }
    
    @Override
    public void removeEventListener(final String s, final EventListener eventListener) {
    }
    
    @Override
    public void resetAppID() {
        this.bridge.getNrdProxy().invokeMethod("log", "resetAppID", null);
    }
    
    @Override
    public void resetSessionID(final Log$ResetSessionIdCallback mSessionCallback) {
        this.mSessionCallback = mSessionCallback;
        this.bridge.getNrdProxy().invokeMethod("log", "resetSessionID", null);
    }
    
    @Override
    public void setAppIdChangedListener(final Log$AppIdChangedListener mAppIdListener) {
        this.mAppIdListener = mAppIdListener;
    }
    
    @Override
    public void setAppIdSetListener(final Log$AppIdSetListener mAppIdSetListener) {
        if (mAppIdSetListener == null) {
            this.mAppIdSetListener = null;
            return;
        }
        if (this.mAppId != null) {
            mAppIdSetListener.onSet(this.mAppId);
            this.mAppIdSetListener = null;
            return;
        }
        this.mAppIdSetListener = mAppIdSetListener;
    }
}
