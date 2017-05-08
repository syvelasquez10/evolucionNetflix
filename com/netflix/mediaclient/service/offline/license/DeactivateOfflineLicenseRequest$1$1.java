// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import android.os.Handler;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.android.app.Status;

class DeactivateOfflineLicenseRequest$1$1 implements Runnable
{
    final /* synthetic */ DeactivateOfflineLicenseRequest$1 this$1;
    final /* synthetic */ String val$licenseRelease;
    final /* synthetic */ Status val$res;
    
    DeactivateOfflineLicenseRequest$1$1(final DeactivateOfflineLicenseRequest$1 this$1, final String val$licenseRelease, final Status val$res) {
        this.this$1 = this$1;
        this.val$licenseRelease = val$licenseRelease;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.handleDeactivateResponse(this.val$licenseRelease, this.val$res);
    }
}
