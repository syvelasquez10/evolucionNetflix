// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

public interface OfflinePlaybackInterface
{
    void abortManifestRequest(final long p0);
    
    void notifyPause(final long p0);
    
    void notifyPlay(final long p0);
    
    void notifyPlayError(final long p0);
    
    void notifyPlayProgress(final long p0, final long p1);
    
    void notifyStop(final long p0);
    
    void requestOfflineManifest(final long p0, final OfflinePlaybackInterface$ManifestCallback p1);
}
