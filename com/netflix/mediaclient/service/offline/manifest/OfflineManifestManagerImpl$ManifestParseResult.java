// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.manifest.NfManifest;

class OfflineManifestManagerImpl$ManifestParseResult
{
    public NfManifest mNfManifest;
    public Status mStatus;
    final /* synthetic */ OfflineManifestManagerImpl this$0;
    
    OfflineManifestManagerImpl$ManifestParseResult(final OfflineManifestManagerImpl this$0) {
        this.this$0 = this$0;
        this.mStatus = CommonStatus.OK;
        this.mNfManifest = null;
    }
}
