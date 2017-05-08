// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.manifest;

import java.util.Collections;
import java.util.Iterator;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$ManifestRequestFlavor;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.Triple;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Looper;
import android.os.HandlerThread;
import java.util.Map;
import android.os.Handler;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import java.util.List;
import com.netflix.mediaclient.Log;

class NfManifestCache$2 implements Runnable
{
    final /* synthetic */ NfManifestCache this$0;
    final /* synthetic */ NfManifestCachePlaybackInterface$ManifestCacheCallback val$cb;
    final /* synthetic */ Long val$movieId;
    
    NfManifestCache$2(final NfManifestCache this$0, final Long val$movieId, final NfManifestCachePlaybackInterface$ManifestCacheCallback val$cb) {
        this.this$0 = this$0;
        this.val$movieId = val$movieId;
        this.val$cb = val$cb;
    }
    
    @Override
    public void run() {
        this.this$0.purgeManifestCache();
        final NfManifest nfManifest = this.this$0.mManifestMap.get(this.val$movieId);
        if (nfManifest != null) {
            this.this$0.notifyFetchManifestResult(this.val$movieId, nfManifest, this.val$cb);
            if (Log.isLoggable()) {
                Log.d(NfManifestCache.TAG, "manifest available for " + this.val$movieId);
            }
            return;
        }
        this.this$0.fetchManifest(this.val$movieId, this.val$cb);
    }
}
