// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.manifest;

public interface NfManifestCachePlaybackInterface
{
    void abort(final Long p0);
    
    void getManifestAsync(final Long p0, final NfManifestCachePlaybackInterface$ManifestCacheCallback p1);
}
