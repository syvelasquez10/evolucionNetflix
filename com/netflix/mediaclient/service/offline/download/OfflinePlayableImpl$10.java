// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.util.log.OfflineLogUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.WatchState;
import java.util.concurrent.TimeUnit;
import android.telephony.TelephonyManager;
import android.net.wifi.WifiManager;
import com.netflix.mediaclient.service.offline.manifest.OfflineManifestCallback;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
import com.netflix.mediaclient.service.offline.manifest.OfflinePlayableManifestImpl;
import com.netflix.mediaclient.service.pdslogging.DownloadContext;
import com.netflix.mediaclient.service.offline.log.OfflineErrorLogblob;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManager$DownloadCompleteAndActivateCallback;
import com.netflix.mediaclient.service.offline.agent.PlayabilityEnforcer;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import java.util.Iterator;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$OfflineRefreshInvoke;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import java.util.ArrayList;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.offline.manifest.OfflineManifestManager;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManager;
import java.io.File;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import java.util.List;
import android.os.HandlerThread;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface$PlayableRefreshLicenseCallBack;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManagerCallback;

class OfflinePlayableImpl$10 implements OfflineLicenseManagerCallback
{
    final /* synthetic */ OfflinePlayableImpl this$0;
    final /* synthetic */ OfflineAgentInterface$PlayableRefreshLicenseCallBack val$callback;
    final /* synthetic */ NfManifest val$offlineManifest;
    
    OfflinePlayableImpl$10(final OfflinePlayableImpl this$0, final NfManifest val$offlineManifest, final OfflineAgentInterface$PlayableRefreshLicenseCallBack val$callback) {
        this.this$0 = this$0;
        this.val$offlineManifest = val$offlineManifest;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onOfflineLicenseRequestDone(final String s, final OfflineLicenseResponse offlineLicenseResponse, final Status status) {
        if (status.getStatusCode() == StatusCode.OFFLINE_LICENSE_FETCH_NEW) {
            this.this$0.fetchFreshLicenseOnRefreshFailure(this.val$offlineManifest, this.val$callback);
            return;
        }
        this.this$0.handleRefreshLicenseResponse(offlineLicenseResponse, status, this.val$callback);
    }
}
