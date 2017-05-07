// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend.model;

import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class BackgroundingSessionEndedEvent extends SessionEndedEvent
{
    private static final String SESSION_NAME = "backgrounding";
    
    public BackgroundingSessionEndedEvent(final long duration) {
        super("backgrounding");
        this.duration = duration;
    }
}
