// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

import com.netflix.mediaclient.service.player.SessionParams;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import java.util.Arrays;
import com.netflix.mediaclient.util.MediaDrmUtils;
import com.netflix.mediaclient.Log;
import android.os.Looper;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.Triple;
import java.util.List;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.player.manifest.NfManifestCachePlaybackInterface;
import android.os.Handler;
import android.content.Context;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.service.player.manifest.NfManifestCachePlaybackInterface$ManifestCacheCallback;
import android.media.MediaDrm$OnEventListener;
import java.util.Iterator;
import java.util.Map;

class NfDrmManager$3 implements Runnable
{
    final /* synthetic */ NfDrmManager this$0;
    final /* synthetic */ boolean val$keepInUseSession;
    
    NfDrmManager$3(final NfDrmManager this$0, final boolean val$keepInUseSession) {
        this.this$0 = this$0;
        this.val$keepInUseSession = val$keepInUseSession;
    }
    
    @Override
    public void run() {
        if (!this.this$0.mDrmSessionMap.isEmpty()) {
            final Iterator<Map.Entry<K, NfDrmSession>> iterator = this.this$0.mDrmSessionMap.entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<K, NfDrmSession> entry = iterator.next();
                if (!this.val$keepInUseSession || !entry.getValue().getInUse()) {
                    entry.getValue().close();
                    iterator.remove();
                }
            }
        }
        this.this$0.mWaitToPrepareList.clear();
    }
}
