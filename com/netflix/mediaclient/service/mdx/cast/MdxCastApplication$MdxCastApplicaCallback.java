// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

public interface MdxCastApplication$MdxCastApplicaCallback
{
    void onApplicationStopped(final String p0);
    
    void onFailToConnect(final String p0);
    
    void onFailToLaunch(final String p0);
    
    void onFailToSendMessage(final String p0);
    
    void onLaunched();
    
    void onMessageReceived(final String p0);
    
    void onMessageSent();
}
