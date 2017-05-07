// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class SessionStartedEvent extends SessionEvent
{
    private static final String EXT = ".started";
    
    public SessionStartedEvent(final String s) {
        super(s);
        this.type = EventType.sessionStarted;
        this.name = s + ".started";
        this.sessionId = new DeviceUniqueId();
    }
    
    public SessionStartedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
}
