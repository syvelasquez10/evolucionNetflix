// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.service.offline.agent.OfflinePdsData;
import com.netflix.mediaclient.service.pdslogging.DownloadContext;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface$OfflinePdsDataCallback;
import com.netflix.mediaclient.service.offline.manifest.OfflineManifestCallback;

class OfflinePlayableImpl$9 implements OfflineManifestCallback
{
    final /* synthetic */ OfflinePlayableImpl this$0;
    final /* synthetic */ OfflineAgentInterface$OfflinePdsDataCallback val$callback;
    
    OfflinePlayableImpl$9(final OfflinePlayableImpl this$0, final OfflineAgentInterface$OfflinePdsDataCallback val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onOfflineManifestResponse(final NfManifest nfManifest, final Status status) {
        OfflinePdsData offlinePdsData = null;
        if (status.isSucces()) {
            offlinePdsData = new OfflinePdsData(nfManifest.getLinks(), this.this$0.getOxId(), this.this$0.getDxId(), DownloadContext.createDownloadContext(this.this$0.getOfflineViewablePersistentData()));
        }
        this.val$callback.onOfflinePdsData(this.this$0.getPlayableId(), offlinePdsData, status);
    }
}
