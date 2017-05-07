// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.AppSessionStartedEvent;
import com.netflix.mediaclient.service.logging.apm.model.AppSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;

public class ApplicationSession extends BaseApmSession
{
    public static final String NAME = "appSession";
    private DeviceUniqueId mId;
    
    public AppSessionEndedEvent createEndedEvent() {
        final AppSessionEndedEvent appSessionEndedEvent = new AppSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        appSessionEndedEvent.setCategory(this.getCategory());
        appSessionEndedEvent.setSessionId(this.mId);
        return appSessionEndedEvent;
    }
    
    public AppSessionStartedEvent createStartEvent(final boolean lastShutdownGraceful) {
        final AppSessionStartedEvent appSessionStartedEvent = new AppSessionStartedEvent();
        appSessionStartedEvent.setLastShutdownGraceful(lastShutdownGraceful);
        appSessionStartedEvent.setCategory(this.getCategory());
        appSessionStartedEvent.setSessionId(this.mId);
        return appSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "appSession";
    }
}
