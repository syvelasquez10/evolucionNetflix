// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public final class AppSessionEndedEvent extends SessionEndedEvent
{
    private static final String APP_SESSION_NAME = "appSession";
    
    public AppSessionEndedEvent(final long n) {
        super("appSession", new DeviceUniqueId(), n);
    }
    
    public AppSessionEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
    }
}
