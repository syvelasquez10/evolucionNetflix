// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import java.util.Iterator;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;
import junit.framework.Assert;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Context;
import com.netflix.mediaclient.service.logging.IPdsLogging;
import java.util.Map;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;

class PdsDownloadSessionManager$1 implements PdsDownloadSessionManager$ManifestCallback
{
    final /* synthetic */ PdsDownloadSessionManager this$0;
    final /* synthetic */ int val$percentageDownloaded;
    
    PdsDownloadSessionManager$1(final PdsDownloadSessionManager this$0, final int val$percentageDownloaded) {
        this.this$0 = this$0;
        this.val$percentageDownloaded = val$percentageDownloaded;
    }
    
    @Override
    public void onManifestFetched(final PdsDownloadSession pdsDownloadSession) {
        this.this$0.handleProgressMessage(pdsDownloadSession, this.val$percentageDownloaded);
    }
}
