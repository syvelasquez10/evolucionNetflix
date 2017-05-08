// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class NccpActionId extends NccpError
{
    private int actionId;
    private String bcp47;
    private int code;
    private String message;
    private int reasonCode;
    
    public NccpActionId(final int actionId, final String bcp47, final String message, final String origin, final String result, final int reasonCode, final String transaction) {
        this.actionId = actionId;
        this.message = message;
        this.origin = origin;
        this.bcp47 = bcp47;
        this.result = result;
        this.reasonCode = reasonCode;
        this.transaction = transaction;
        this.type = "Nccp";
    }
    
    public NccpActionId(final JSONObject jsonObject) {
        super(jsonObject);
    }
    
    public int getActionId() {
        return this.actionId;
    }
    
    public String getBcp47() {
        return this.bcp47;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public int getReasonCode() {
        return this.reasonCode;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.actionId = BaseNccpEvent.getInt(jsonObject, "actionId", 0);
        this.reasonCode = BaseNccpEvent.getInt(jsonObject, "reasonCode", 0);
        this.code = BaseNccpEvent.getInt(jsonObject, "code", 0);
        this.message = BaseNccpEvent.getString(jsonObject, "message", null);
        this.bcp47 = BaseNccpEvent.getString(jsonObject, "bcp47", null);
        this.origin = BaseNccpEvent.getString(jsonObject, "origin", null);
        this.result = BaseNccpEvent.getString(jsonObject, "result", null);
        this.transaction = BaseNccpEvent.getString(jsonObject, "transaction", null);
        this.type = BaseNccpEvent.getString(jsonObject, "type", null);
    }
    
    @Override
    public String toString() {
        return "NccpActionId{actionId=" + this.actionId + ", bcp47='" + this.bcp47 + '\'' + ", message='" + this.message + '\'' + ", reasonCode=" + this.reasonCode + ", code=" + this.code + "} " + super.toString();
    }
}
