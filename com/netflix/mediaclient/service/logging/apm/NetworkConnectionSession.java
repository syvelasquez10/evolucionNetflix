// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.client.model.Event;

public abstract class NetworkConnectionSession extends BaseApmSession
{
    public abstract Event createEndedEvent();
    
    public abstract Event createStartEvent();
}
