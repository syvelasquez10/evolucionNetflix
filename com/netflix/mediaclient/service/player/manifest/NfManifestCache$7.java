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
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Looper;
import android.os.HandlerThread;
import java.util.Map;
import android.os.Handler;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;
import com.netflix.mediaclient.service.player.bladerunnerclient.SimpleBladeRunnerWebCallback;

class NfManifestCache$7 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ NfManifestCache this$0;
    final /* synthetic */ NfManifestCachePlaybackInterface$ManifestCacheCallback val$cb;
    final /* synthetic */ Long val$movieId;
    
    NfManifestCache$7(final NfManifestCache this$0, final Long val$movieId, final NfManifestCachePlaybackInterface$ManifestCacheCallback val$cb) {
        this.this$0 = this$0;
        this.val$movieId = val$movieId;
        this.val$cb = val$cb;
    }
    
    @Override
    public void onManifestsFetched(final JSONObject jsonObject, final Status status) {
        if (Log.isLoggable()) {
            Log.d(NfManifestCache.TAG, "fetchManifests movie " + this.val$movieId + " result " + status);
        }
        if (!status.isSucces() || jsonObject == null) {
            Log.d(NfManifestCache.TAG, "fetchManifests failed");
            this.this$0.notifyFetchManifestResult(this.val$movieId, null, this.val$cb);
            return;
        }
        if (Log.isLoggable()) {
            Log.d(NfManifestCache.TAG, "fetchManifests return " + jsonObject);
        }
        this.this$0.mWorkHandler.post((Runnable)new NfManifestCache$7$1(this, jsonObject));
    }
}
