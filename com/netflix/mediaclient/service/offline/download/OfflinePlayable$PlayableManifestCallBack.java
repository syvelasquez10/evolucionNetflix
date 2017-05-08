// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;

public interface OfflinePlayable$PlayableManifestCallBack
{
    void onPlayableManifestReady(final OfflinePlaybackInterface$OfflineManifest p0, final Status p1);
}
