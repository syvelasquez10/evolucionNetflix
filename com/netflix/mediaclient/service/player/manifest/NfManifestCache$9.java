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

class NfManifestCache$9 implements Runnable
{
    final /* synthetic */ NfManifestCache this$0;
    final /* synthetic */ NfManifestCachePlaybackInterface$ManifestCacheCallback val$cb;
    final /* synthetic */ NfManifest val$manifest;
    final /* synthetic */ Long val$movieId;
    
    NfManifestCache$9(final NfManifestCache this$0, final Long val$movieId, final NfManifest val$manifest, final NfManifestCachePlaybackInterface$ManifestCacheCallback val$cb) {
        this.this$0 = this$0;
        this.val$movieId = val$movieId;
        this.val$manifest = val$manifest;
        this.val$cb = val$cb;
    }
    
    @Override
    public void run() {
        if (this.this$0.mAbortedMovies.contains(this.val$movieId)) {
            if (Log.isLoggable()) {
                Log.d(NfManifestCache.TAG, "callback js aborted for movie " + this.val$movieId);
            }
            this.this$0.mAbortedMovies.remove(this.val$movieId);
            return;
        }
        if (this.val$manifest == null) {
            if (Log.isLoggable()) {
                Log.d(NfManifestCache.TAG, "manifest error for movie " + this.val$movieId);
            }
            this.val$cb.onManifestError(this.val$movieId);
            return;
        }
        this.val$cb.onManifestAvailable(this.val$manifest);
    }
}
