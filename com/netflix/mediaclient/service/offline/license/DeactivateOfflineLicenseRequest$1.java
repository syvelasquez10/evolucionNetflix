// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.SimpleBladeRunnerWebCallback;

class DeactivateOfflineLicenseRequest$1 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ DeactivateOfflineLicenseRequest this$0;
    
    DeactivateOfflineLicenseRequest$1(final DeactivateOfflineLicenseRequest this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onLicenseDeactivated(final Status status, final String s) {
        Log.i("nf_offlineLicenseMgr", "DeactivateOfflineLicenseRequest onLicenseDeactivated playableId=" + this.this$0.mPlayableId);
        this.this$0.mWorkHandler.post((Runnable)new DeactivateOfflineLicenseRequest$1$1(this, s, status));
    }
}
