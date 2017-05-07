// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.SharedContextSessionStartedEvent;
import com.netflix.mediaclient.service.logging.apm.model.SharedContextSessionEndedEvent;

public class SharedContextSession extends BaseApmSession
{
    public static final String NAME = "sharedContext";
    private String mUuid;
    
    public SharedContextSession(final String mUuid) {
        this.mUuid = mUuid;
    }
    
    public SharedContextSessionEndedEvent createEndedEvent() {
        final SharedContextSessionEndedEvent sharedContextSessionEndedEvent = new SharedContextSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mUuid);
        sharedContextSessionEndedEvent.setCategory(this.getCategory());
        return sharedContextSessionEndedEvent;
    }
    
    public SharedContextSessionStartedEvent createStartEvent() {
        final SharedContextSessionStartedEvent sharedContextSessionStartedEvent = new SharedContextSessionStartedEvent(this.mUuid);
        sharedContextSessionStartedEvent.setCategory(this.getCategory());
        sharedContextSessionStartedEvent.setSessionId(this.mId);
        return sharedContextSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "sharedContext";
    }
}
