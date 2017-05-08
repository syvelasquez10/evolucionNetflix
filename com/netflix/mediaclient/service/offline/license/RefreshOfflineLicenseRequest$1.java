// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import com.netflix.mediaclient.service.player.bladerunnerclient.SimpleBladeRunnerWebCallback;

class RefreshOfflineLicenseRequest$1 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ RefreshOfflineLicenseRequest this$0;
    
    RefreshOfflineLicenseRequest$1(final RefreshOfflineLicenseRequest this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onOfflineLicenseFetched(final OfflineLicenseResponse offlineLicenseResponse, final Status status) {
        Log.i("nf_offlineLicenseMgr", "RefreshOfflineLicenseRequest onLicenseFetched playableId=" + this.this$0.mPlayableId);
        this.this$0.mWorkHandler.post((Runnable)new RefreshOfflineLicenseRequest$1$1(this, offlineLicenseResponse, status));
    }
}
