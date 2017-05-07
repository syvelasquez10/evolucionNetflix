// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class MobileNetworkConnectionSessionEndedEvent extends SessionEndedEvent
{
    private static final String NETWORK_CONNECTION_SESSION_NAME = "mobileNetworkConnection";
    
    public MobileNetworkConnectionSessionEndedEvent(final DeviceUniqueId deviceUniqueId, final long n) {
        super("mobileNetworkConnection", deviceUniqueId, n);
    }
    
    public MobileNetworkConnectionSessionEndedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
}
