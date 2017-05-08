// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

public interface IPdsLogging
{
    void flushEventsInLogging();
    
    void sendPdsEventViaLogging(final String p0);
}
