// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend;

import com.netflix.mediaclient.service.logging.suspend.model.UnfocusedSessionEndedEvent;
import com.netflix.mediaclient.service.logging.suspend.model.UnfocusedSessionStartedEvent;

public class UnfocusedSession extends BaseSuspendSession
{
    public static final long DELTA = 500L;
    public static final String NAME = "unfocused";
    private UnfocusedSessionStartedEvent mUnfocusedSessionStartedEvent;
    
    public UnfocusedSessionEndedEvent createEndedEvent() {
        final long n = System.currentTimeMillis() - this.mStarted;
        if (n <= 500L) {
            return null;
        }
        final UnfocusedSessionEndedEvent unfocusedSessionEndedEvent = new UnfocusedSessionEndedEvent(n);
        unfocusedSessionEndedEvent.setCategory(this.getCategory());
        unfocusedSessionEndedEvent.setSessionId(this.mId);
        return unfocusedSessionEndedEvent;
    }
    
    public void createStartEvent() {
        final UnfocusedSessionStartedEvent mUnfocusedSessionStartedEvent = new UnfocusedSessionStartedEvent();
        mUnfocusedSessionStartedEvent.setCategory(this.getCategory());
        mUnfocusedSessionStartedEvent.setSessionId(this.mId);
        this.mUnfocusedSessionStartedEvent = mUnfocusedSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "unfocused";
    }
    
    public UnfocusedSessionStartedEvent getUnfocusedSessionStartedEvent() {
        return this.mUnfocusedSessionStartedEvent;
    }
}
