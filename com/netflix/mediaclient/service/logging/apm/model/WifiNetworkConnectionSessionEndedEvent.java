// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class WifiNetworkConnectionSessionEndedEvent extends SessionEndedEvent
{
    private static final String NETWORK_CONNECTION_SESSION_NAME = "wifiNetworkConnection";
    
    public WifiNetworkConnectionSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n) {
        super("wifiNetworkConnection", deviceUniqueId, n);
    }
    
    public WifiNetworkConnectionSessionEndedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
}
