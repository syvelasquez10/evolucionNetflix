// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.MobileNetworkConnectionSessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.apm.model.MobileNetworkConnectionSessionEndedEvent;

public class MobileNetworkConnectionSession extends NetworkConnectionSession
{
    public static final String NAME = "mobileNetworkConnection";
    
    @Override
    public MobileNetworkConnectionSessionEndedEvent createEndedEvent() {
        final MobileNetworkConnectionSessionEndedEvent mobileNetworkConnectionSessionEndedEvent = new MobileNetworkConnectionSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted);
        mobileNetworkConnectionSessionEndedEvent.setCategory(this.getCategory());
        return mobileNetworkConnectionSessionEndedEvent;
    }
    
    @Override
    public MobileNetworkConnectionSessionStartedEvent createStartEvent() {
        final MobileNetworkConnectionSessionStartedEvent mobileNetworkConnectionSessionStartedEvent = new MobileNetworkConnectionSessionStartedEvent();
        mobileNetworkConnectionSessionStartedEvent.setCategory(this.getCategory());
        mobileNetworkConnectionSessionStartedEvent.setSessionId(this.mId);
        return mobileNetworkConnectionSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "mobileNetworkConnection";
    }
}
