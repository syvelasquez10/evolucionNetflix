// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.WifiNetworkConnectionSessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.apm.model.WifiNetworkConnectionSessionEndedEvent;

public class WifiNetworkConnectionSession extends NetworkConnectionSession
{
    public static final String NAME = "wifiNetworkConnection";
    
    @Override
    public WifiNetworkConnectionSessionEndedEvent createEndedEvent() {
        final WifiNetworkConnectionSessionEndedEvent wifiNetworkConnectionSessionEndedEvent = new WifiNetworkConnectionSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted);
        wifiNetworkConnectionSessionEndedEvent.setCategory(this.getCategory());
        return wifiNetworkConnectionSessionEndedEvent;
    }
    
    @Override
    public WifiNetworkConnectionSessionStartedEvent createStartEvent() {
        final WifiNetworkConnectionSessionStartedEvent wifiNetworkConnectionSessionStartedEvent = new WifiNetworkConnectionSessionStartedEvent();
        wifiNetworkConnectionSessionStartedEvent.setCategory(this.getCategory());
        wifiNetworkConnectionSessionStartedEvent.setSessionId(this.mId);
        return wifiNetworkConnectionSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "wifiNetworkConnection";
    }
}
