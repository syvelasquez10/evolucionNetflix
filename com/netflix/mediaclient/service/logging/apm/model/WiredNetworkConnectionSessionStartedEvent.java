// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class WiredNetworkConnectionSessionStartedEvent extends SessionStartedEvent
{
    private static final String NETWORK_CONNECTION_SESSION_NAME = "wiredNetworkConnection";
    
    public WiredNetworkConnectionSessionStartedEvent() {
        super("wiredNetworkConnection");
    }
    
    public WiredNetworkConnectionSessionStartedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
}
