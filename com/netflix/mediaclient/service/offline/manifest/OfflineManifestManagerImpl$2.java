// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.LogUtils;
import java.io.File;
import com.netflix.mediaclient.android.app.BaseStatus;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import java.util.List;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.offline.log.OfflineErrorLogblob;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.pdslogging.DownloadContext;
import org.json.JSONObject;
import java.util.HashMap;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.service.pdslogging.PdsDownloadInterface;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;

class OfflineManifestManagerImpl$2 implements OfflineManifestCallback
{
    final /* synthetic */ OfflineManifestManagerImpl this$0;
    final /* synthetic */ DownloadVideoQuality val$downloadVideoQuality;
    final /* synthetic */ String val$dxid;
    final /* synthetic */ OfflineManifestCallback val$offlineManifestCallback;
    final /* synthetic */ String val$oxid;
    final /* synthetic */ String val$playableDirPath;
    final /* synthetic */ String val$playableId;
    
    OfflineManifestManagerImpl$2(final OfflineManifestManagerImpl this$0, final String val$playableId, final OfflineManifestCallback val$offlineManifestCallback, final String val$oxid, final String val$dxid, final DownloadVideoQuality val$downloadVideoQuality, final String val$playableDirPath) {
        this.this$0 = this$0;
        this.val$playableId = val$playableId;
        this.val$offlineManifestCallback = val$offlineManifestCallback;
        this.val$oxid = val$oxid;
        this.val$dxid = val$dxid;
        this.val$downloadVideoQuality = val$downloadVideoQuality;
        this.val$playableDirPath = val$playableDirPath;
    }
    
    @Override
    public void onOfflineManifestResponse(final NfManifest nfManifest, final Status status) {
        this.this$0.mOfflineManifestCache.remove(this.val$playableId);
        this.this$0.mOfflineManifestRequestMap.put(this.val$playableId, this.val$offlineManifestCallback);
        if (status.isSucces() && nfManifest != null) {
            this.this$0.mBladeRunnerClient.refreshOfflineManifest(this.val$playableId, this.val$oxid, this.val$dxid, this.val$downloadVideoQuality, nfManifest, new OfflineManifestManagerImpl$2$1(this));
            return;
        }
        this.this$0.mWorkHandler.post((Runnable)new OfflineManifestManagerImpl$2$2(this, status));
    }
}
