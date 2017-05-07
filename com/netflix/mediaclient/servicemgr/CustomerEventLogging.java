// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface CustomerEventLogging
{
    void logMdxPlaybackStart(final String p0, final String p1, final String p2, final int p3);
    
    void logMdxTarget(final String p0, final String p1, final String p2, final String p3);
    
    void logMdxTargetSelection(final String p0);
    
    void reportApplicationLaunchedFromDeepLinking(final String p0, final String p1);
    
    void reportMdpFromDeepLinking();
    
    void reportNotificationOptIn(final boolean p0, final String p1);
}
