// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class WiredNetworkConnectionSessionEndedEvent extends SessionEndedEvent
{
    private static final String NETWORK_CONNECTION_SESSION_NAME = "wiredNetworkConnection";
    
    public WiredNetworkConnectionSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n) {
        super("wiredNetworkConnection", deviceUniqueId, n);
    }
    
    public WiredNetworkConnectionSessionEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
    }
}
