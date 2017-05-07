// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.device;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public abstract class BaseDeviceEvent extends JsonBaseNccpEvent
{
    public BaseDeviceEvent(final String s) {
        super(s);
    }
    
    public BaseDeviceEvent(final String s, final JSONObject jsonObject) throws JSONException {
        super(s, jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.device";
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
    }
}
