// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import java.util.Iterator;
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
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import com.netflix.mediaclient.servicemgr.interface_.offline.SimpleOfflineAgentListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.offline.agent.OfflinePdsData;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface$OfflinePdsDataCallback;

class PdsDownloadSessionManager$3 implements OfflineAgentInterface$OfflinePdsDataCallback
{
    final /* synthetic */ PdsDownloadSessionManager this$0;
    final /* synthetic */ PdsDownloadSessionManager$ManifestCallback val$cb;
    
    PdsDownloadSessionManager$3(final PdsDownloadSessionManager this$0, final PdsDownloadSessionManager$ManifestCallback val$cb) {
        this.this$0 = this$0;
        this.val$cb = val$cb;
    }
    
    @Override
    public void onOfflinePdsData(final String s, final OfflinePdsData offlinePdsData, final Status status) {
        final PdsDownloadSession access$200 = this.this$0.getDownloadSession(s);
        if (access$200 != null) {
            access$200.setManifestFetchInProgress(false);
            if (access$200 != null && offlinePdsData != null && offlinePdsData.getLinks() != null) {
                Log.d(PdsDownloadSessionManager.TAG, "got manifestFromCache :%s - setting links and sendng pds resume", s);
                access$200.setLinks(offlinePdsData.getLinks());
                if (this.val$cb != null) {
                    this.val$cb.onManifestFetched(access$200);
                }
            }
            return;
        }
        if (offlinePdsData != null) {
            this.this$0.createDownloadSession(s, offlinePdsData.getOxId(), offlinePdsData.getDxId(), offlinePdsData.getDownloadContext(), offlinePdsData.getLinks());
            return;
        }
        Log.e(PdsDownloadSessionManager.TAG, "error receiving manifest from cache movieId=%s status=%s", s, status.toString());
    }
}
