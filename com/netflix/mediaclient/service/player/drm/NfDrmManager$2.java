// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

import com.netflix.mediaclient.service.player.SessionParams;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import java.util.Arrays;
import com.netflix.mediaclient.util.MediaDrmUtils;
import android.os.Looper;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.player.manifest.NfManifestCachePlaybackInterface;
import android.os.Handler;
import java.util.Map;
import android.content.Context;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import android.media.MediaDrm$OnEventListener;
import java.util.Iterator;
import com.netflix.mediaclient.service.player.manifest.NfManifestCachePlaybackInterface$ManifestCacheCallback;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.Triple;
import java.util.List;

class NfDrmManager$2 implements Runnable
{
    final /* synthetic */ NfDrmManager this$0;
    final /* synthetic */ List val$prefetchHints;
    
    NfDrmManager$2(final NfDrmManager this$0, final List val$prefetchHints) {
        this.this$0 = this$0;
        this.val$prefetchHints = val$prefetchHints;
    }
    
    @Override
    public void run() {
        for (final Triple triple : this.val$prefetchHints) {
            final Long n = (Long)triple.first;
            final Integer n2 = (Integer)triple.second;
            final PlayContext playContext = (PlayContext)triple.third;
            if (this.this$0.mDrmSessionMap.get(n) != null) {
                if (!Log.isLoggable()) {
                    continue;
                }
                Log.d("NfPlayerDrmManager", "movieId = " + n + ", priority = " + n2 + ", already cached");
            }
            else if (n2 >= ServiceManagerUtils.PREFETCH_PRIORITY_DEFAULT && this.this$0.mDeviceHasLowDrmResource) {
                if (!Log.isLoggable()) {
                    continue;
                }
                Log.d("NfPlayerDrmManager", "movieId = " + n + ", priority = " + n2 + ", skip");
            }
            else {
                if (Log.isLoggable()) {
                    Log.d("NfPlayerDrmManager", "movieId = " + n + ", priority = " + n2 + ", request manifest");
                }
                this.this$0.mWaitToPrepareList.add(new Triple<Long, Integer, PlayContext>(n, n2, playContext));
                this.this$0.mManifestCache.getManifestAsync(n, this.this$0);
            }
        }
    }
}
