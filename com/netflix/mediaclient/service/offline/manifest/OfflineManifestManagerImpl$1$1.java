// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import java.io.File;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import java.util.List;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.player.exoplayback.logblob.OfflineErrorLogblob;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.pdslogging.DownloadContext;
import java.util.HashMap;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.service.pdslogging.PdsDownloadInterface;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;

class OfflineManifestManagerImpl$1$1 implements Runnable
{
    final /* synthetic */ OfflineManifestManagerImpl$1 this$1;
    final /* synthetic */ JSONObject val$manifests;
    final /* synthetic */ Status val$status;
    
    OfflineManifestManagerImpl$1$1(final OfflineManifestManagerImpl$1 this$1, final Status val$status, final JSONObject val$manifests) {
        this.this$1 = this$1;
        this.val$status = val$status;
        this.val$manifests = val$manifests;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.handleManifestResponse(this.val$status, this.this$1.val$playableId, this.this$1.val$playableDirPath, true, this.val$manifests, this.this$1.val$oxid, this.this$1.val$dxid, this.this$1.val$dc);
    }
}
