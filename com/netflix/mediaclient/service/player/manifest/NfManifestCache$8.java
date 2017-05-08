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
import org.json.JSONObject;
import java.util.List;
import com.netflix.mediaclient.service.player.bladerunnerclient.SimpleBladeRunnerWebCallback;

class NfManifestCache$8 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ NfManifestCache this$0;
    final /* synthetic */ List val$finalist;
    
    NfManifestCache$8(final NfManifestCache this$0, final List val$finalist) {
        this.this$0 = this$0;
        this.val$finalist = val$finalist;
    }
    
    @Override
    public void onManifestsFetched(final JSONObject jsonObject, final Status status) {
        this.this$0.mWorkHandler.post((Runnable)new NfManifestCache$8$1(this, status, jsonObject, this.val$finalist));
    }
}
