// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client;

import com.netflix.mediaclient.service.logging.client.model.SessionKey;

public interface LoggingSession
{
    SessionKey getKey();
    
    String getName();
}
