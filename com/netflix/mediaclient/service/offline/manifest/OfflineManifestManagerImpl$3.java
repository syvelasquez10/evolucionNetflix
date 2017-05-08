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
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.Status;
import java.util.HashMap;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.service.pdslogging.PdsDownloadInterface;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;

class OfflineManifestManagerImpl$3 implements Runnable
{
    final /* synthetic */ OfflineManifestManagerImpl this$0;
    
    OfflineManifestManagerImpl$3(final OfflineManifestManagerImpl this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mOfflineManifestCache.clear();
    }
}
