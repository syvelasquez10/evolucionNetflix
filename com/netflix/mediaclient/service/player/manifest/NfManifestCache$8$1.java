// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.manifest;

import java.util.Collections;
import java.util.Iterator;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$ManifestRequestFlavor;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.Triple;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Looper;
import android.os.HandlerThread;
import java.util.Map;
import android.os.Handler;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import org.json.JSONObject;

class NfManifestCache$8$1 implements Runnable
{
    final /* synthetic */ NfManifestCache$8 this$1;
    final /* synthetic */ JSONObject val$manifestsJson;
    final /* synthetic */ List val$requestedMovies;
    final /* synthetic */ Status val$res;
    
    NfManifestCache$8$1(final NfManifestCache$8 this$1, final Status val$res, final JSONObject val$manifestsJson, final List val$requestedMovies) {
        this.this$1 = this$1;
        this.val$res = val$res;
        this.val$manifestsJson = val$manifestsJson;
        this.val$requestedMovies = val$requestedMovies;
    }
    
    @Override
    public void run() {
        if (!this.val$res.isSucces() || this.val$manifestsJson == null) {
            this.this$1.this$0.processPrefetchFailure(this.val$requestedMovies);
            return;
        }
        this.this$1.this$0.processPrefetchResponse(this.val$requestedMovies, this.val$manifestsJson);
        this.this$1.this$0.purgeManifestCache();
    }
}
