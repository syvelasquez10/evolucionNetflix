// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class DiscreteEvent extends Event
{
    public DiscreteEvent() {
        this.type = EventType.event;
    }
    
    public DiscreteEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
}
