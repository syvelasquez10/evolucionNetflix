// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;

class RefreshOfflineLicenseRequest$1$1 implements Runnable
{
    final /* synthetic */ RefreshOfflineLicenseRequest$1 this$1;
    final /* synthetic */ OfflineLicenseResponse val$license;
    final /* synthetic */ Status val$res;
    
    RefreshOfflineLicenseRequest$1$1(final RefreshOfflineLicenseRequest$1 this$1, final OfflineLicenseResponse val$license, final Status val$res) {
        this.this$1 = this$1;
        this.val$license = val$license;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.handleLicenseResponse(this.val$license, this.val$res);
    }
}
