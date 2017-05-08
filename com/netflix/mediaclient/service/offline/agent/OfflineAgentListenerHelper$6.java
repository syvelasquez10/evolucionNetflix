// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixPowerManager$PartialWakeLockReason;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.service.NetflixPowerManager;
import java.util.Iterator;
import com.netflix.mediaclient.android.app.Status;
import android.os.Handler;

class OfflineAgentListenerHelper$6 implements Runnable
{
    final /* synthetic */ OfflineAgentListenerHelper this$0;
    final /* synthetic */ Handler val$mainHandler;
    final /* synthetic */ OfflineAgentInterface val$offlineAgent;
    final /* synthetic */ String val$playableId;
    final /* synthetic */ Status val$status;
    final /* synthetic */ OfflineAgent$DeleteAndTryAgainRequest val$tryAgainRequest;
    
    OfflineAgentListenerHelper$6(final OfflineAgentListenerHelper this$0, final String val$playableId, final Status val$status, final OfflineAgent$DeleteAndTryAgainRequest val$tryAgainRequest, final Handler val$mainHandler, final OfflineAgentInterface val$offlineAgent) {
        this.this$0 = this$0;
        this.val$playableId = val$playableId;
        this.val$status = val$status;
        this.val$tryAgainRequest = val$tryAgainRequest;
        this.val$mainHandler = val$mainHandler;
        this.val$offlineAgent = val$offlineAgent;
    }
    
    @Override
    public void run() {
        this.this$0.releasePartialWakeLock();
        this.this$0.removeDeadListeners();
        final Iterator<OfflineAgentListener> iterator = this.this$0.mOfflineAgentListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onOfflinePlayableDeleted(this.val$playableId, this.val$status);
        }
        if (this.val$status.isSucces() && this.val$tryAgainRequest != null) {
            this.val$mainHandler.post((Runnable)new OfflineAgentListenerHelper$6$1(this));
        }
    }
}
