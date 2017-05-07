// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend;

import com.netflix.mediaclient.service.logging.suspend.model.BackgroundingSessionEndedEvent;

public class BackgroundingSession extends BaseSuspendSession
{
    public static final String NAME = "backgrounding";
    
    public BackgroundingSessionEndedEvent createEndedEvent() {
        final BackgroundingSessionEndedEvent backgroundingSessionEndedEvent = new BackgroundingSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        backgroundingSessionEndedEvent.setCategory(this.getCategory());
        backgroundingSessionEndedEvent.setSessionId(this.mId);
        return backgroundingSessionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "backgrounding";
    }
}
