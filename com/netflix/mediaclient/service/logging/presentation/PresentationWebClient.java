// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.presentation;

public interface PresentationWebClient
{
    boolean isSynchronous();
    
    void sendPresentationEvents(final String p0, final PresentationRequest p1, final PresentationWebCallback p2);
}
