// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.registration;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public abstract class BaseRegistrationEvent extends JsonBaseNccpEvent
{
    public BaseRegistrationEvent(final String s) {
        super(s);
    }
    
    public BaseRegistrationEvent(final String s, final JSONObject jsonObject) throws JSONException {
        super(s, jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.registration";
    }
}
