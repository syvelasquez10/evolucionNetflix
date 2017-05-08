// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.SimpleBladeRunnerWebCallback;

class OfflineLicenseManagerImpl$1 extends SimpleBladeRunnerWebCallback
{
    final /* synthetic */ OfflineLicenseManagerImpl this$0;
    final /* synthetic */ OfflineLicenseManager$DownloadCompleteAndActivateCallback val$callback;
    final /* synthetic */ String val$playableId;
    
    OfflineLicenseManagerImpl$1(final OfflineLicenseManagerImpl this$0, final OfflineLicenseManager$DownloadCompleteAndActivateCallback val$callback, final String val$playableId) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        this.val$playableId = val$playableId;
    }
    
    @Override
    public void onDownloadComplete(final Status status, final String s) {
        this.val$callback.onDownloadCompleteAndActivateDone(this.val$playableId, s, status);
    }
}
