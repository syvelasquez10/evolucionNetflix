// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import com.netflix.mediaclient.service.player.exoplayback.logblob.OfflineErrorLogblob;
import com.netflix.mediaclient.servicemgr.Logblob$Severity;
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

class PdsDownloadSessionManager$2 implements PdsDownloadSessionManager$ManifestCallback
{
    final /* synthetic */ PdsDownloadSessionManager this$0;
    
    PdsDownloadSessionManager$2(final PdsDownloadSessionManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManifestFetched(final PdsDownloadSession pdsDownloadSession) {
        this.this$0.handleDownloadComplete(pdsDownloadSession);
    }
}
