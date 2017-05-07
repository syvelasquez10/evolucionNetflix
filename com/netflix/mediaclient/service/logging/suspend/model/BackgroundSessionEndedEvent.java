// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend.model;

import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class BackgroundSessionEndedEvent extends SessionEndedEvent
{
    private static final String SESSION_NAME = "background";
    
    public BackgroundSessionEndedEvent(final long duration) {
        super("background");
        this.duration = duration;
    }
}
