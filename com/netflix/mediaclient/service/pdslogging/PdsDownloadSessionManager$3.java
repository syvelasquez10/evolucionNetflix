// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import java.util.Iterator;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.util.StringUtils;
import junit.framework.Assert;
import org.json.JSONObject;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Context;
import com.netflix.mediaclient.service.logging.IPdsLogging;
import java.util.Map;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;

class PdsDownloadSessionManager$3 implements OfflinePlaybackInterface$ManifestCallback
{
    final /* synthetic */ PdsDownloadSessionManager this$0;
    final /* synthetic */ PdsDownloadSessionManager$ManifestCallback val$cb;
    
    PdsDownloadSessionManager$3(final PdsDownloadSessionManager this$0, final PdsDownloadSessionManager$ManifestCallback val$cb) {
        this.this$0 = this$0;
        this.val$cb = val$cb;
    }
    
    @Override
    public void onManifestResponse(final long n, final OfflinePlaybackInterface$OfflineManifest offlinePlaybackInterface$OfflineManifest, final Status status) {
        final PdsDownloadSession access$200 = this.this$0.getDownloadSession(String.valueOf(n));
        if (access$200 != null) {
            access$200.setManifestFetchInProgress(false);
            if (access$200 != null && offlinePlaybackInterface$OfflineManifest != null && offlinePlaybackInterface$OfflineManifest.getLinks() != null) {
                Log.d(PdsDownloadSessionManager.TAG, "got manifestFromCache :%d - setting links and sendng pds resume", n);
                access$200.setLinks(offlinePlaybackInterface$OfflineManifest.getLinks());
                if (this.val$cb != null) {
                    this.val$cb.onManifestFetched(access$200);
                }
            }
            return;
        }
        this.this$0.createDownloadSession(String.valueOf(n), offlinePlaybackInterface$OfflineManifest.getOxId(), offlinePlaybackInterface$OfflineManifest.getDxId(), offlinePlaybackInterface$OfflineManifest.getDownloadContext(), offlinePlaybackInterface$OfflineManifest.getLinks());
    }
}
