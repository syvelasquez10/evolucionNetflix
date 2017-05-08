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
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.IPdsLogging;
import java.util.Map;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class PdsDownloadSessionManager$4 extends BroadcastReceiver
{
    final /* synthetic */ PdsDownloadSessionManager this$0;
    
    PdsDownloadSessionManager$4(final PdsDownloadSessionManager this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        Log.v(PdsDownloadSessionManager.TAG, "Received intent %s", intent);
        if (intent == null) {
            return;
        }
        final String stringExtra = intent.getStringExtra(PdsDownloadSessionManager.EXTRA_PLAYABLE_ID);
        final String stringExtra2 = intent.getStringExtra(PdsDownloadSessionManager.EXTRA_ERROR_CODE);
        final String stringExtra3 = intent.getStringExtra(PdsDownloadSessionManager.EXTRA_ERROR_MESSAGE);
        final PdsDownloadSession access$200 = this.this$0.getDownloadSession(stringExtra);
        if (access$200 == null) {
            Log.e(PdsDownloadSessionManager.TAG, "playable: %s - no session, dropping intent %s", stringExtra, intent.getAction());
            return;
        }
        final String action = intent.getAction();
        if (PdsDownloadSessionManager.STOP_DOWNLOAD_LICENSE_ERROR.equals(action)) {
            access$200.sendStopDownloadOnLicenseError(stringExtra2, stringExtra3);
            return;
        }
        if (PdsDownloadSessionManager.STOP_DOWNLOAD_MANIFEST_EXPIRED.equals(action)) {
            access$200.sendStopDownloadOnExpiredManifest(stringExtra2, stringExtra3);
            return;
        }
        if (PdsDownloadSessionManager.STOP_DOWNLOAD_ERROR.equals(action)) {
            access$200.sendStopDownloadOnError(stringExtra2, stringExtra3);
            return;
        }
        Log.d(PdsDownloadSessionManager.TAG, "We do not support action :%s ", action);
    }
}
