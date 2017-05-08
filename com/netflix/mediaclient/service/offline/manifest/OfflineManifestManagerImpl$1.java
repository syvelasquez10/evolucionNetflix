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
import com.netflix.mediaclient.service.pdslogging.DownloadContext;
import com.netflix.mediaclient.service.player.bladerunnerclient.SimpleBladeRunnerWebCallback;

class OfflineManifestManagerImpl$1 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ OfflineManifestManagerImpl this$0;
    final /* synthetic */ DownloadContext val$dc;
    final /* synthetic */ String val$dxid;
    final /* synthetic */ String val$oxid;
    final /* synthetic */ String val$playableDirPath;
    final /* synthetic */ String val$playableId;
    
    OfflineManifestManagerImpl$1(final OfflineManifestManagerImpl this$0, final String val$playableId, final String val$playableDirPath, final String val$oxid, final String val$dxid, final DownloadContext val$dc) {
        this.this$0 = this$0;
        this.val$playableId = val$playableId;
        this.val$playableDirPath = val$playableDirPath;
        this.val$oxid = val$oxid;
        this.val$dxid = val$dxid;
        this.val$dc = val$dc;
    }
    
    @Override
    public void onManifestsFetched(final JSONObject jsonObject, final Status status) {
        this.this$0.mWorkHandler.post((Runnable)new OfflineManifestManagerImpl$1$1(this, status, jsonObject));
    }
}
