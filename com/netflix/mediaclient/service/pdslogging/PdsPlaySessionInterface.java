// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import org.json.JSONObject;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;

public interface PdsPlaySessionInterface
{
    void notifyPlayProgress(final long p0);
    
    void onManifest(final OfflinePlaybackInterface$OfflineManifest p0);
    
    void pause();
    
    void play();
    
    void seekTo(final long p0);
    
    void stop(final JSONObject p0, final String p1, final String p2);
}
