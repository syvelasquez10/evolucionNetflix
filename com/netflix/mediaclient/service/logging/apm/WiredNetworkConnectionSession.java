// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.WiredNetworkConnectionSessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.apm.model.WiredNetworkConnectionSessionEndedEvent;

public class WiredNetworkConnectionSession extends NetworkConnectionSession
{
    public static final String NAME = "wiredNetworkConnection";
    
    @Override
    public WiredNetworkConnectionSessionEndedEvent createEndedEvent() {
        final WiredNetworkConnectionSessionEndedEvent wiredNetworkConnectionSessionEndedEvent = new WiredNetworkConnectionSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted);
        wiredNetworkConnectionSessionEndedEvent.setCategory(this.getCategory());
        return wiredNetworkConnectionSessionEndedEvent;
    }
    
    @Override
    public WiredNetworkConnectionSessionStartedEvent createStartEvent() {
        final WiredNetworkConnectionSessionStartedEvent wiredNetworkConnectionSessionStartedEvent = new WiredNetworkConnectionSessionStartedEvent();
        wiredNetworkConnectionSessionStartedEvent.setCategory(this.getCategory());
        wiredNetworkConnectionSessionStartedEvent.setSessionId(this.mId);
        return wiredNetworkConnectionSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "wiredNetworkConnection";
    }
}
