// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.details.DetailsActivity$Action;
import java.util.List;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class NetflixComVideoDetailsHandler$1 extends SimpleManagerCallback
{
    final /* synthetic */ NetflixComVideoDetailsHandler this$0;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ String val$trackId;
    final /* synthetic */ String val$videoId;
    
    NetflixComVideoDetailsHandler$1(final NetflixComVideoDetailsHandler this$0, final String val$videoId, final NetflixActivity val$activity, final String val$trackId) {
        this.this$0 = this$0;
        this.val$videoId = val$videoId;
        this.val$activity = val$activity;
        this.val$trackId = val$trackId;
    }
    
    @Override
    public void onFalkorVideoFetched(final FalkorVideo falkorVideo, final Status status) {
        if (status.isSucces() && falkorVideo != null) {
            if (falkorVideo.getType() == VideoType.SEASON || falkorVideo.getType() == VideoType.EPISODE) {
                final String topLevelId = falkorVideo.getTopLevelId();
                if (!TextUtils.isEmpty((CharSequence)topLevelId) && !topLevelId.equals(this.val$videoId)) {
                    this.this$0.fetchFalkorVideo(topLevelId, this.val$activity, this.val$trackId);
                    return;
                }
                ErrorLoggingManager.logHandledException(new Throwable("Ancestor is null for: " + this.val$videoId));
            }
            else {
                if (this.val$activity != null && this.val$activity.getServiceManager() != null) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("trkid").append('=').append(this.val$trackId);
                    sb.append('&');
                    sb.append("movieid").append('=').append(this.val$videoId);
                    UIViewLogUtils.reportUIViewCommandStarted((Context)this.val$activity, UIViewLogging$UIViewCommandName.deepLink, IClientLogging$ModalView.movieDetails, (DataContext)null, sb.toString());
                    UIViewLogUtils.reportUIViewCommandEnded((Context)this.val$activity);
                }
                DetailsActivityLauncher.show(this.val$activity, (Video)falkorVideo, NflxProtocolUtils.getPlayContext(this.val$trackId), this.this$0.getAction(), this.this$0.getActionToken(), "DeepLink");
            }
        }
        else {
            ErrorLoggingManager.logHandledException(new Throwable("SPY-7518 - got error trying to fetch video summary for: " + this.val$videoId));
        }
        NflxProtocolUtils.reportDelayedResponseHandled((Activity)this.val$activity);
    }
}
