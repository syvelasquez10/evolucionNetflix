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
import java.util.List;
import org.json.JSONObject;

class NfManifestCache$7$1 implements Runnable
{
    final /* synthetic */ NfManifestCache$7 this$1;
    final /* synthetic */ JSONObject val$manifestJson;
    
    NfManifestCache$7$1(final NfManifestCache$7 this$1, final JSONObject val$manifestJson) {
        this.this$1 = this$1;
        this.val$manifestJson = val$manifestJson;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.processManifestResponse(this.val$manifestJson);
        this.this$1.this$0.notifyFetchManifestResult(this.this$1.val$movieId, this.this$1.this$0.mManifestMap.get(this.this$1.val$movieId), this.this$1.val$cb);
        this.this$1.this$0.purgeManifestCache();
    }
}
