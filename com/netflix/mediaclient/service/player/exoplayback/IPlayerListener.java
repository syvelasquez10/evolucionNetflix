// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

public interface IPlayerListener
{
    void playerBuffering();
    
    void playerError(final ExoPlaybackError p0);
    
    void playerPaused();
    
    void playerPrepared();
    
    void playerStarted();
    
    void playerStopped();
}
