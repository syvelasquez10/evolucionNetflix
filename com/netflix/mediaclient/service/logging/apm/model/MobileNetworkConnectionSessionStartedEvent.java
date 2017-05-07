// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class MobileNetworkConnectionSessionStartedEvent extends SessionStartedEvent
{
    private static final String NETWORK_CONNECTION_SESSION_NAME = "mobileNetworkConnection";
    
    public MobileNetworkConnectionSessionStartedEvent() {
        super("mobileNetworkConnection");
    }
    
    public MobileNetworkConnectionSessionStartedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
}
