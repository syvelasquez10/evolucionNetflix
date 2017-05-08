// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;

class OfflineAgent$DeleteAndTryAgainRequest
{
    public final PlayContext mPlayContext;
    public final String mPlayableId;
    public final VideoType mVideoType;
    final /* synthetic */ OfflineAgent this$0;
    
    public OfflineAgent$DeleteAndTryAgainRequest(final OfflineAgent this$0, final String mPlayableId, final PlayContext mPlayContext, final VideoType mVideoType) {
        this.this$0 = this$0;
        this.mPlayableId = mPlayableId;
        this.mPlayContext = mPlayContext;
        this.mVideoType = mVideoType;
    }
}
