// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.manifest.NfManifest;

public interface OfflineManifestCallback
{
    void onOfflineManifestResponse(final NfManifest p0, final Status p1);
}
