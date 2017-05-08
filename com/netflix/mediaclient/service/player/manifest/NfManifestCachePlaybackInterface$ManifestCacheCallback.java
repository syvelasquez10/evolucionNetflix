// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.manifest;

public interface NfManifestCachePlaybackInterface$ManifestCacheCallback
{
    void onManifestAvailable(final NfManifest p0);
    
    void onManifestError(final Long p0);
}
