// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client;

public interface ClientLoggingWebClient
{
    boolean isSynchronous();
    
    void sendLoggingEvents(final String p0);
    
    void sendLoggingEvents(final String p0, final String p1, final ClientLoggingWebCallback p2);
}
