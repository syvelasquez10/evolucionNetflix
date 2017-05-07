// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.ads;

public interface AdvertiserIdLoggingWebClient
{
    boolean isSynchronous();
    
    void sendLoggingEvent(final String p0, final AdvertiserIdLoggingCallback p1);
}
