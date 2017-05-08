// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

import com.netflix.mediaclient.service.player.SessionParams;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import java.util.Arrays;
import java.util.Iterator;
import com.netflix.mediaclient.util.MediaDrmUtils;
import android.os.Looper;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.Triple;
import java.util.List;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.player.manifest.NfManifestCachePlaybackInterface;
import android.os.Handler;
import java.util.Map;
import android.content.Context;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.service.player.manifest.NfManifestCachePlaybackInterface$ManifestCacheCallback;
import android.media.MediaDrm$OnEventListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;
import com.netflix.mediaclient.service.player.bladerunnerclient.SimpleBladeRunnerWebCallback;

class NfDrmManager$1$1 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ NfDrmManager$1 this$1;
    final /* synthetic */ Long val$sessionId;
    
    NfDrmManager$1$1(final NfDrmManager$1 this$1, final Long val$sessionId) {
        this.this$1 = this$1;
        this.val$sessionId = val$sessionId;
    }
    
    @Override
    public void onLicenseFetched(final JSONObject jsonObject, final Status status) {
        if (Log.isLoggable()) {
            Log.d("NfPlayerDrmManager", "fetchLicense movie " + this.val$sessionId + " result " + status);
        }
        if (!status.isSucces() || jsonObject == null) {
            Log.d("NfPlayerDrmManager", "fetchLicense failed");
            return;
        }
        this.this$1.this$0.mWorkHandler.post((Runnable)new NfDrmManager$1$1$1(this, jsonObject));
    }
}
