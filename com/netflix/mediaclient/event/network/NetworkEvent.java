// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.network;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class NetworkEvent extends JsonBaseNccpEvent
{
    private int errorCode;
    private int errorGroup;
    private int result;
    private String type;
    private String url;
    
    public NetworkEvent(final JSONObject jsonObject) throws JSONException {
        super("INetwork", jsonObject);
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public int getErrorGroup() {
        return this.errorGroup;
    }
    
    @Override
    public String getObject() {
        return "nrdp.network";
    }
    
    public int getResult() {
        return this.result;
    }
    
    @Override
    public String getType() {
        return this.type;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.type = BaseNccpEvent.getString(jsonObject, "type", null);
        this.result = BaseNccpEvent.getInt(jsonObject, "result", 0);
        this.errorCode = BaseNccpEvent.getInt(jsonObject, "errorcode", 0);
        this.errorGroup = BaseNccpEvent.getInt(jsonObject, "errorgroup", 0);
        this.url = BaseNccpEvent.getString(jsonObject, "url", null);
    }
}
