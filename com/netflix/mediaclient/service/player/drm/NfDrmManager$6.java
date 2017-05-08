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
import com.netflix.mediaclient.Log;
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

class NfDrmManager$6 implements Runnable
{
    final /* synthetic */ NfDrmManager this$0;
    final /* synthetic */ byte[] val$sessionId;
    
    NfDrmManager$6(final NfDrmManager this$0, final byte[] val$sessionId) {
        this.this$0 = this$0;
        this.val$sessionId = val$sessionId;
    }
    
    @Override
    public void run() {
        final NfDrmSession drmSessionWithSessionId = this.this$0.getDrmSessionWithSessionId(this.val$sessionId);
        if (drmSessionWithSessionId != null) {
            this.this$0.removeSession(drmSessionWithSessionId.mMovieId);
        }
    }
}
