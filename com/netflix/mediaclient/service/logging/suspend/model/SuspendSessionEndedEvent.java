// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend.model;

import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class SuspendSessionEndedEvent extends SessionEndedEvent
{
    private static final String SESSION_NAME = "suspend";
    
    public SuspendSessionEndedEvent(final long duration) {
        super("suspend");
        this.duration = duration;
    }
}
