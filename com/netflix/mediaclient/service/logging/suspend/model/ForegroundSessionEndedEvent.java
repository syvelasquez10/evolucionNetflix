// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend.model;

import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class ForegroundSessionEndedEvent extends SessionEndedEvent
{
    private static final String SESSION_NAME = "foreground";
    
    public ForegroundSessionEndedEvent(final long duration) {
        super("foreground");
        this.duration = duration;
    }
}
