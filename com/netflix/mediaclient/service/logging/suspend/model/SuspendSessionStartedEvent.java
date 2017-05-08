// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend.model;

import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class SuspendSessionStartedEvent extends SessionStartedEvent
{
    private static final String SESSION_NAME = "suspend";
    
    public SuspendSessionStartedEvent() {
        super("suspend");
    }
}
