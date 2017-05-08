// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend.model;

import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class UnfocusedSessionEndedEvent extends SessionEndedEvent
{
    private static final String SESSION_NAME = "unfocused";
    private static final String TAG = "nf_log";
    
    public UnfocusedSessionEndedEvent(final long duration) {
        super("unfocused");
        this.duration = duration;
    }
}
