// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import com.netflix.mediaclient.Log;
import org.json.JSONException;
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
    
    public Error(final JSONObject jsonObject) throws JSONException {
        super("error", jsonObject);
    }
    
    public boolean checkForOpenDeviceFailureInStack() {
        if (this.mArray != null) {
            final int n = 0;
            if (n < this.mArray.length()) {
                try {
                    if (this.mArray.getJSONObject(n).getString("errorcode").equals("NFErr_MC_OpenDeviceFailure")) {
                        return true;
                    }
                    goto Label_0055;
                }
                catch (JSONException ex) {
                    Log.e("nf_event", "checkForOpenDeviceFailureInStack: JSONException:", (Throwable)ex);
                }
                catch (Throwable t) {
                    Log.e("nf_event", "checkForOpenDeviceFailureInStack:", t);
                    goto Label_0055;
                }
            }
        }
        return false;
    }
    
    public int getError() {
        return this.error;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.error = BaseNccpEvent.getInt(jsonObject, "error", 0);
        this.mArray = BaseNccpEvent.getJsonArray(jsonObject, "stack");
    }
}
