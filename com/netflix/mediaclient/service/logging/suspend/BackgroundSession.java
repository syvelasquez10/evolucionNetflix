// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend;

import com.netflix.mediaclient.service.logging.suspend.model.BackgroundSessionStartedEvent;
import com.netflix.mediaclient.service.logging.suspend.model.BackgroundSessionEndedEvent;

public class BackgroundSession extends BaseSuspendSession
{
    public static final String NAME = "background";
    
    public BackgroundSessionEndedEvent createEndedEvent() {
        final BackgroundSessionEndedEvent backgroundSessionEndedEvent = new BackgroundSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        backgroundSessionEndedEvent.setCategory(this.getCategory());
        backgroundSessionEndedEvent.setSessionId(this.mId);
        return backgroundSessionEndedEvent;
    }
    
    public BackgroundSessionStartedEvent createStartEvent() {
        final BackgroundSessionStartedEvent backgroundSessionStartedEvent = new BackgroundSessionStartedEvent();
        backgroundSessionStartedEvent.setCategory(this.getCategory());
        backgroundSessionStartedEvent.setSessionId(this.mId);
        return backgroundSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "background";
    }
}
