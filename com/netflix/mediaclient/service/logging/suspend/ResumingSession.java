// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend;

import com.netflix.mediaclient.service.logging.suspend.model.ResumingSessionEndedEvent;

public class ResumingSession extends BaseSuspendSession
{
    public static final String NAME = "resuming";
    
    public ResumingSessionEndedEvent createEndedEvent() {
        final ResumingSessionEndedEvent resumingSessionEndedEvent = new ResumingSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        resumingSessionEndedEvent.setCategory(this.getCategory());
        resumingSessionEndedEvent.setSessionId(this.mId);
        return resumingSessionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "resuming";
    }
}
