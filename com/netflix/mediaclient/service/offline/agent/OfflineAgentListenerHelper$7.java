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
import com.netflix.mediaclient.service.NetflixPowerManager;
import java.util.Iterator;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;

class OfflineAgentListenerHelper$7 implements Runnable
{
    final /* synthetic */ OfflineAgentListenerHelper this$0;
    final /* synthetic */ List val$playableIdList;
    final /* synthetic */ Status val$status;
    
    OfflineAgentListenerHelper$7(final OfflineAgentListenerHelper this$0, final List val$playableIdList, final Status val$status) {
        this.this$0 = this$0;
        this.val$playableIdList = val$playableIdList;
        this.val$status = val$status;
    }
    
    @Override
    public void run() {
        this.this$0.releasePartialWakeLock();
        this.this$0.removeDeadListeners();
        final Iterator<OfflineAgentListener> iterator = this.this$0.mOfflineAgentListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onOfflinePlayablesDeleted(this.val$playableIdList, this.val$status);
        }
    }
}
