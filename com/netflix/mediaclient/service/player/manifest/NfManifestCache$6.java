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
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Looper;
import android.os.HandlerThread;
import java.util.Map;
import android.os.Handler;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import java.util.List;

class NfManifestCache$6 implements Runnable
{
    final /* synthetic */ NfManifestCache this$0;
    
    NfManifestCache$6(final NfManifestCache this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mAbortedMovies.clear();
    }
}
