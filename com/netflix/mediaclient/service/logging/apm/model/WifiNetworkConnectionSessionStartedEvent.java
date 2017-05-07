// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class WifiNetworkConnectionSessionStartedEvent extends SessionStartedEvent
{
    private static final String NETWORK_CONNECTION_SESSION_NAME = "wifiNetworkConnection";
    
    public WifiNetworkConnectionSessionStartedEvent() {
        super("wifiNetworkConnection");
    }
    
    public WifiNetworkConnectionSessionStartedEvent(final JSONObject jsonObject) {
        super(jsonObject);
    }
}
