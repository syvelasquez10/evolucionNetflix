// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import android.app.Activity;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class NetflixComWatchHandler$1 extends SimpleManagerCallback
{
    final /* synthetic */ NetflixComWatchHandler this$0;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ String val$targetDialUuid;
    final /* synthetic */ String val$trackId;
    final /* synthetic */ String val$videoId;
    
    NetflixComWatchHandler$1(final NetflixComWatchHandler this$0, final NetflixActivity val$activity, final String val$videoId, final String val$targetDialUuid, final String val$trackId) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
        this.val$videoId = val$videoId;
        this.val$targetDialUuid = val$targetDialUuid;
        this.val$trackId = val$trackId;
    }
    
    @Override
    public void onVideoSummaryFetched(final Video$Summary video$Summary, final Status status) {
        if (status.isSucces() && video$Summary != null) {
            this.this$0.playVideo(this.val$activity, video$Summary.getType(), this.val$videoId, this.val$targetDialUuid, this.val$trackId);
            return;
        }
        ErrorLoggingManager.logHandledException(new Throwable("SPY-7518 - got error trying to fetch video summary for: " + this.val$videoId));
        NflxProtocolUtils.reportDelayedResponseHandled((Activity)this.val$activity);
    }
}
