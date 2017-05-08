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
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.Triple;
import java.util.List;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.player.manifest.NfManifestCachePlaybackInterface;
import java.util.Map;
import android.content.Context;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.service.player.manifest.NfManifestCachePlaybackInterface$ManifestCacheCallback;
import android.media.MediaDrm$OnEventListener;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.mediaclient.Log;
import android.media.MediaDrm$ProvisionRequest;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class NfDrmManager$1 extends Handler
{
    final /* synthetic */ NfDrmManager this$0;
    
    NfDrmManager$1(final NfDrmManager this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
    }
    
    private Long getSessionId(final int n, final int n2) {
        return n << 32 | (n2 & -1L);
    }
    
    public void handleMessage(final Message message) {
        final Long sessionId = this.getSessionId(message.arg1, message.arg2);
        switch (message.what) {
            case 1: {
                if (message.obj != null && message.obj instanceof MediaDrm$ProvisionRequest) {
                    final MediaDrm$ProvisionRequest mediaDrm$ProvisionRequest = (MediaDrm$ProvisionRequest)message.obj;
                    return;
                }
                break;
            }
            case 2:
            case 3: {
                if (message.obj != null && message.obj instanceof LicenseContext) {
                    final LicenseContext licenseContext = (LicenseContext)message.obj;
                    if (Log.isLoggable()) {
                        Log.d("NfPlayerDrmManager", "about to fetchLicense for session " + sessionId + ", challenge [" + licenseContext.getBase64Challenge().length() + "], " + licenseContext.getmLicenseType());
                    }
                    this.this$0.mBladeRunnerClient.fetchLicense(licenseContext, new NfDrmManager$1$1(this, sessionId));
                    return;
                }
                break;
            }
        }
    }
}
