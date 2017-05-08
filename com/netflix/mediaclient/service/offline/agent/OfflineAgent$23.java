// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;

class OfflineAgent$23 implements Runnable
{
    final /* synthetic */ OfflineAgent this$0;
    final /* synthetic */ OfflinePlaybackInterface$ManifestCallback val$callback;
    final /* synthetic */ long val$movieId;
    final /* synthetic */ OfflinePlaybackInterface$OfflineManifest val$offlineManifest;
    final /* synthetic */ Status val$status;
    
    OfflineAgent$23(final OfflineAgent this$0, final OfflinePlaybackInterface$OfflineManifest val$offlineManifest, final OfflinePlaybackInterface$ManifestCallback val$callback, final long val$movieId, final Status val$status) {
        this.this$0 = this$0;
        this.val$offlineManifest = val$offlineManifest;
        this.val$callback = val$callback;
        this.val$movieId = val$movieId;
        this.val$status = val$status;
    }
    
    @Override
    public void run() {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "mainThread offlineManifest=" + this.val$offlineManifest);
        }
        this.val$callback.onManifestResponse(this.val$movieId, this.val$offlineManifest, this.val$status);
    }
}
