// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import android.content.ComponentName;

public abstract class BaseResponse implements Response
{
    public static final int RESULT_FAILURE = 1;
    public static final int RESULT_NOUSER = 2;
    public static final int RESULT_OK = 0;
    public static final int RESULT_USERCANCEL = 3;
    public static final String RESULT_errcode = "errcode";
    public static final String RESULT_idx = "idx";
    public static final String RESULT_msg = "msg";
    public static final String RESULT_service = "service";
    public static final String RESULT_status = "status";
    public static final String RESULT_token = "token";
    public static final String RESULT_userid = "userid";
    protected String id;
    protected ComponentName originator;
    protected String service;
    
    BaseResponse(final String s, final String s2) {
    }
    
    BaseResponse(final String service, final String id, final ComponentName originator) {
        if (service == null) {
            throw new IllegalArgumentException("Service is null");
        }
        if (id == null) {
            throw new IllegalArgumentException("Session id is null");
        }
        this.service = service;
        this.id = id;
        this.originator = originator;
    }
    
    public static String noNull(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        return s2;
    }
    
    public static JSONObject toJSon(String o) {
        if (o == null) {
            return null;
        }
        try {
            o = new JSONObject((String)o);
            return (JSONObject)o;
        }
        catch (Exception ex) {
            Log.e("", "Failed to convert " + (String)o + " to JSON!", ex);
            o = null;
            return (JSONObject)o;
        }
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    protected JSONObject getJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("idx", (Object)this.id);
        jsonObject.put("service", (Object)this.service);
        return jsonObject;
    }
    
    @Override
    public ComponentName getResponder() {
        return this.originator;
    }
    
    @Override
    public String getService() {
        return this.service;
    }
}
