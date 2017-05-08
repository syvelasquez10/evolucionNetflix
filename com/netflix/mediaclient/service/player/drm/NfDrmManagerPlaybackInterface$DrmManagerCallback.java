// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

public interface NfDrmManagerPlaybackInterface$DrmManagerCallback
{
    void onDrmError();
    
    void onDrmSessionAvailable(final NfDrmSession p0);
}
