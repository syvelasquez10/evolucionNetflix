// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import com.netflix.mediaclient.service.player.bladerunnerclient.SimpleBladeRunnerWebCallback;

class OfflineLicenseRequest$1 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ OfflineLicenseRequest this$0;
    
    OfflineLicenseRequest$1(final OfflineLicenseRequest this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onOfflineLicenseFetched(final OfflineLicenseResponse offlineLicenseResponse, final Status status) {
        Log.i("nf_offlineLicenseMgr", "sendLicenseRequest onLicenseFetched playableId=" + this.this$0.mPlayableId);
        this.this$0.mWorkHandler.post((Runnable)new OfflineLicenseRequest$1$1(this, offlineLicenseResponse, status));
    }
}
