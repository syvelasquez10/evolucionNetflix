// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;

class OfflineAgent$15 implements Runnable
{
    final /* synthetic */ OfflineAgent this$0;
    final /* synthetic */ OfflineAgentInterface$OfflinePdsDataCallback val$callback;
    final /* synthetic */ OfflinePdsData val$data;
    final /* synthetic */ String val$playableId;
    final /* synthetic */ StatusCode val$statusCode;
    
    OfflineAgent$15(final OfflineAgent this$0, final OfflineAgentInterface$OfflinePdsDataCallback val$callback, final String val$playableId, final OfflinePdsData val$data, final StatusCode val$statusCode) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        this.val$playableId = val$playableId;
        this.val$data = val$data;
        this.val$statusCode = val$statusCode;
    }
    
    @Override
    public void run() {
        this.val$callback.onOfflinePdsData(this.val$playableId, this.val$data, new NetflixStatus(this.val$statusCode));
    }
}
