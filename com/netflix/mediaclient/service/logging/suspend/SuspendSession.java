// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend;

import com.netflix.mediaclient.service.logging.suspend.model.SuspendSessionStartedEvent;
import com.netflix.mediaclient.service.logging.suspend.model.SuspendSessionEndedEvent;

public class SuspendSession extends BaseSuspendSession
{
    public static final String NAME = "suspend";
    
    public SuspendSessionEndedEvent createEndedEvent() {
        final SuspendSessionEndedEvent suspendSessionEndedEvent = new SuspendSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        suspendSessionEndedEvent.setCategory(this.getCategory());
        suspendSessionEndedEvent.setSessionId(this.mId);
        return suspendSessionEndedEvent;
    }
    
    public SuspendSessionStartedEvent createStartEvent() {
        final SuspendSessionStartedEvent suspendSessionStartedEvent = new SuspendSessionStartedEvent();
        suspendSessionStartedEvent.setCategory(this.getCategory());
        suspendSessionStartedEvent.setSessionId(this.mId);
        return suspendSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "suspend";
    }
}
