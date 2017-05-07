// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend.model;

import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class BackgroundSessionStartedEvent extends SessionStartedEvent
{
    private static final String SESSION_NAME = "background";
    
    public BackgroundSessionStartedEvent() {
        super("background");
    }
}
