// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface$PlayableRefreshLicenseCallBack;

class OfflinePlayableImpl$5 extends OfflineAgentInterface$PlayableRefreshLicenseCallBack
{
    final /* synthetic */ OfflinePlayableImpl this$0;
    final /* synthetic */ OfflinePlayable$PlayableMaintenanceCallBack val$cb;
    
    OfflinePlayableImpl$5(final OfflinePlayableImpl this$0, final OfflinePlayable$PlayableMaintenanceCallBack val$cb) {
        this.this$0 = this$0;
        this.val$cb = val$cb;
    }
    
    @Override
    public void onLicenseRefreshDone(final Status status) {
        if (this.val$cb != null) {
            this.val$cb.onMaintenanceJobDone(this.this$0);
        }
    }
}
