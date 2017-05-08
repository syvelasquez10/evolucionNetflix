// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.ClientActionFromLase;
import java.util.Map;
import com.netflix.mediaclient.service.player.bladerunnerclient.SimpleBladeRunnerWebCallback;

class OfflineLicenseManagerImpl$1 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ OfflineLicenseManagerImpl this$0;
    final /* synthetic */ OfflineLicenseManager$LicenseSyncResponseCallback val$callback;
    
    OfflineLicenseManagerImpl$1(final OfflineLicenseManagerImpl this$0, final OfflineLicenseManager$LicenseSyncResponseCallback val$callback) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onSyncLicenseDone(final Map<String, ClientActionFromLase> map, final Status status) {
        Log.i("nf_offlineLicenseMgr", "onSyncLicenseDone res=%s", status);
        this.val$callback.onLicenseSyncDone(map, status);
    }
}
