// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

class OfflineAgentListenerHelper$6$1 implements Runnable
{
    final /* synthetic */ OfflineAgentListenerHelper$6 this$1;
    
    OfflineAgentListenerHelper$6$1(final OfflineAgentListenerHelper$6 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.val$offlineAgent.requestOfflineViewing(this.this$1.val$tryAgainRequest.mPlayableId, this.this$1.val$tryAgainRequest.mVideoType, this.this$1.val$tryAgainRequest.mPlayContext);
    }
}
