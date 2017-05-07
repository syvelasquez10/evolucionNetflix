// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

public interface MdxSessionWatchDog$SessionWatchDogInterface
{
    long onGetTimeOfMostRecentIncomingMessage();
    
    void onSessionWatchDogExpired();
}
