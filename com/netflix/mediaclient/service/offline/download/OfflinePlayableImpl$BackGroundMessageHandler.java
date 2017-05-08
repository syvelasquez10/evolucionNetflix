// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.service.player.bladerunnerclient.volley.ClientActionFromLase;
import com.netflix.mediaclient.util.log.OfflineLogUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.WatchState;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface$OfflinePdsDataCallback;
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
import com.netflix.mediaclient.service.offline.agent.PlayabilityEnforcer;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManagerCallback;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import java.util.Iterator;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface$PlayableRefreshLicenseCallBack;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$OfflineRefreshInvoke;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
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
import com.netflix.mediaclient.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class OfflinePlayableImpl$BackGroundMessageHandler extends Handler
{
    final /* synthetic */ OfflinePlayableImpl this$0;
    
    OfflinePlayableImpl$BackGroundMessageHandler(final OfflinePlayableImpl this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        Log.i("nf_offlinePlayable", "handleMessage cmd=%d", message.what);
        final OfflinePlayableImpl$CdnUrlDownloaderResponse offlinePlayableImpl$CdnUrlDownloaderResponse = (OfflinePlayableImpl$CdnUrlDownloaderResponse)message.obj;
        switch (message.what) {
            default: {}
            case 1: {
                this.this$0.handleUrlDownloadDiskIOError();
            }
            case 2: {
                this.this$0.handleNetworkError(offlinePlayableImpl$CdnUrlDownloaderResponse.mCdnUrlDownloader, offlinePlayableImpl$CdnUrlDownloaderResponse.mStatus);
            }
            case 3: {
                this.this$0.handleCdnUrlExpiredOrMoved(offlinePlayableImpl$CdnUrlDownloaderResponse.mStatus);
            }
            case 5: {
                this.this$0.handleCdnUrlGeoCheckError(offlinePlayableImpl$CdnUrlDownloaderResponse.mStatus);
            }
            case 4: {
                this.this$0.handleCdnUrlDownloadSessionEnd(offlinePlayableImpl$CdnUrlDownloaderResponse.mCdnUrlDownloader);
            }
        }
    }
}
