// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import android.os.Handler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixPowerManager$PartialWakeLockReason;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.service.NetflixPowerManager;
import java.util.Iterator;
import com.netflix.mediaclient.android.app.Status;

class OfflineAgentListenerHelper$1 implements Runnable
{
    final /* synthetic */ OfflineAgentListenerHelper this$0;
    final /* synthetic */ String val$playableId;
    final /* synthetic */ Status val$status;
    
    OfflineAgentListenerHelper$1(final OfflineAgentListenerHelper this$0, final String val$playableId, final Status val$status) {
        this.this$0 = this$0;
        this.val$playableId = val$playableId;
        this.val$status = val$status;
    }
    
    @Override
    public void run() {
        this.this$0.removeDeadListeners();
        final Iterator<OfflineAgentListener> iterator = this.this$0.mOfflineAgentListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onCreateRequestResponse(this.val$playableId, this.val$status);
        }
    }
}
