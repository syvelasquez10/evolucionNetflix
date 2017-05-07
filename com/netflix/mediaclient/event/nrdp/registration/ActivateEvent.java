// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.registration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class ActivateEvent extends BaseRegistrationEvent
{
    private static final String ACTION_ID = "ACTION_ID";
    private static final String COMPLETE = "COMPLETE";
    public static final String NAME = "activateComplete";
    private static final String NETWORK_ERROR = "NETWORK_ERROR";
    public static final String TYPE = "activate";
    private int actionID;
    private boolean actionId;
    private String bcp47;
    private String cookies;
    private String message;
    private boolean networkError;
    private boolean ok;
    private String origin;
    private int reasonCode;
    
    public ActivateEvent(final JSONObject jsonObject) {
        super("activate", jsonObject);
    }
    
    public boolean failed() {
        return !this.ok;
    }
    
    public int getActionID() {
        return this.actionID;
    }
    
    public String getBcp47() {
        return this.bcp47;
    }
    
    public String getCookies() {
        return this.cookies;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getOrigin() {
        return this.origin;
    }
    
    public int getReasonCode() {
        return this.reasonCode;
    }
    
    public boolean isActionId() {
        return this.actionId;
    }
    
    public boolean isNetworkError() {
        return this.networkError;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        final String string = BaseNccpEvent.getString(jsonObject, "result", null);
        if (Log.isLoggable()) {
            Log.d("nf_event", "Device activated as " + this.cookies + ", status: " + string);
        }
        if ("COMPLETE".equalsIgnoreCase(string)) {
            Log.d("nf_event", "Activation was success");
            this.ok = true;
            this.actionId = false;
            this.networkError = false;
            this.cookies = BaseNccpEvent.getString(jsonObject, "cookies", null);
        }
        else {
            Log.d("nf_event", "Activation failed");
            this.ok = false;
            this.actionID = BaseNccpEvent.getInt(jsonObject, "actionID", 0);
            this.reasonCode = BaseNccpEvent.getInt(jsonObject, "reasonCode", 0);
            this.message = BaseNccpEvent.getString(jsonObject, "message", null);
            this.bcp47 = BaseNccpEvent.getString(jsonObject, "bcp47", null);
            this.origin = BaseNccpEvent.getString(jsonObject, "origin", null);
            if ("ACTION_ID".equalsIgnoreCase(string)) {
                this.actionId = true;
                this.networkError = false;
                return;
            }
            if ("NETWORK_ERROR".equalsIgnoreCase(string)) {
                this.actionId = false;
                this.networkError = true;
            }
        }
    }
}
