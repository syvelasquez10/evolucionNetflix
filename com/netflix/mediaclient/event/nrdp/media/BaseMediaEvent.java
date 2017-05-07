// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import org.json.JSONObject;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public abstract class BaseMediaEvent extends JsonBaseNccpEvent implements MediaEvent
{
    public BaseMediaEvent(final String s) {
        super(s);
    }
    
    public BaseMediaEvent(final String s, final JSONObject jsonObject) {
        super(s, jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.media";
    }
}
