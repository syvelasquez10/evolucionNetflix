// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend.model;

import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class ResumingSessionEndedEvent extends SessionEndedEvent
{
    private static final String SESSION_NAME = "resuming";
    
    public ResumingSessionEndedEvent(final long duration) {
        super("resuming");
        this.duration = duration;
    }
}
