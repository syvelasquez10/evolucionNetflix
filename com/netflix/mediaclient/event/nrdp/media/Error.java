// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import org.json.JSONArray;

public class Error extends BaseMediaEvent
{
    private static final String ATTR_ERROR = "error";
    private static final String ATTR_ERROR_CODE = "errorcode";
    private static final String ATTR_STACK = "stack";
    public static final String TYPE = "error";
    private int error;
    private JSONArray mArray;
    
    public Error(final JSONObject jsonObject) {
        super("error", jsonObject);
    }
    
    public boolean checkForAuthFailureRegistrationRequired() {
        boolean b = false;
        if (this.mArray == null) {
            return b;
        }
        final int n = 0;
        b = b;
        if (n >= this.mArray.length()) {
            return b;
        }
        try {
            if (this.mArray.getJSONObject(n).getString("errorcode").equals("NFErr_NCCP_RegistrationRequired")) {
                b = true;
                return b;
            }
            goto Label_0065;
        }
        catch (JSONException ex) {
            Log.e("nf_event", "checkForAuthFailureRegistrationRequired: JSONException:", (Throwable)ex);
        }
        catch (Throwable t) {
            Log.e("nf_event", "checkForAuthFailureRegistrationRequired:", t);
            goto Label_0065;
        }
    }
    
    public boolean checkForOpenDeviceFailureInStack() {
        boolean b = false;
        if (this.mArray == null) {
            return b;
        }
        final int n = 0;
        b = b;
        if (n >= this.mArray.length()) {
            return b;
        }
        try {
            if (this.mArray.getJSONObject(n).getString("errorcode").equals("NFErr_MC_OpenDeviceFailure")) {
                b = true;
                return b;
            }
            goto Label_0065;
        }
        catch (JSONException ex) {
            Log.e("nf_event", "checkForOpenDeviceFailureInStack: JSONException:", (Throwable)ex);
        }
        catch (Throwable t) {
            Log.e("nf_event", "checkForOpenDeviceFailureInStack:", t);
            goto Label_0065;
        }
    }
    
    public int getError() {
        return this.error;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.error = BaseNccpEvent.getInt(jsonObject, "error", 0);
        this.mArray = BaseNccpEvent.getJsonArray(jsonObject, "stack");
    }
}
