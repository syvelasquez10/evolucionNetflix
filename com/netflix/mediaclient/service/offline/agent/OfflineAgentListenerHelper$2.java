// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.android.app.Status;
import android.os.Handler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixPowerManager$PartialWakeLockReason;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.service.NetflixPowerManager;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;

class OfflineAgentListenerHelper$2 implements Runnable
{
    final /* synthetic */ OfflineAgentListenerHelper this$0;
    final /* synthetic */ OfflinePlayableViewData val$offlinePlayableViewData;
    final /* synthetic */ int val$percentageDownloaded;
    
    OfflineAgentListenerHelper$2(final OfflineAgentListenerHelper this$0, final OfflinePlayableViewData val$offlinePlayableViewData, final int val$percentageDownloaded) {
        this.this$0 = this$0;
        this.val$offlinePlayableViewData = val$offlinePlayableViewData;
        this.val$percentageDownloaded = val$percentageDownloaded;
    }
    
    @Override
    public void run() {
        this.this$0.takePartialWakeLock();
        this.this$0.removeDeadListeners();
        final Iterator<OfflineAgentListener> iterator = this.this$0.mOfflineAgentListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onOfflinePlayableProgress(this.val$offlinePlayableViewData, this.val$percentageDownloaded);
        }
    }
}
