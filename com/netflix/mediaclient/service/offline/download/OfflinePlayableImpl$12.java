// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManagerCallback;

final class OfflinePlayableImpl$12 implements OfflineLicenseManagerCallback
{
    final /* synthetic */ OfflinePlayable$PlayableDeleteCompleteCallBack val$listener;
    final /* synthetic */ OfflinePlayableListener val$offlinePlayableListener;
    final /* synthetic */ OfflinePlayablePersistentData val$playablePersistentData;
    
    OfflinePlayableImpl$12(final OfflinePlayable$PlayableDeleteCompleteCallBack val$listener, final OfflinePlayablePersistentData val$playablePersistentData, final OfflinePlayableListener val$offlinePlayableListener) {
        this.val$listener = val$listener;
        this.val$playablePersistentData = val$playablePersistentData;
        this.val$offlinePlayableListener = val$offlinePlayableListener;
    }
    
    @Override
    public void onOfflineLicenseRequestDone(final String s, final OfflineLicenseResponse offlineLicenseResponse, final Status status) {
        boolean b;
        if (this.val$listener != null) {
            b = true;
        }
        else {
            b = false;
        }
        if (status.isSucces() || (b && !status.getStatusCode().equals(StatusCode.NETWORK_ERROR))) {
            this.val$playablePersistentData.setDownloadStateDeleteComplete();
        }
        if (this.val$listener != null) {
            this.val$listener.onDeleteCompleted(this.val$playablePersistentData);
        }
        if (this.val$offlinePlayableListener != null && status.isSucces()) {
            this.val$offlinePlayableListener.onLicenseDeleteSuccessfully(this.val$playablePersistentData);
        }
    }
}
