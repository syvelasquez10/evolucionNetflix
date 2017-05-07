// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import android.app.Activity;
import android.net.Uri;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class NetflixComVideoDetailsHandler$1 extends SimpleManagerCallback
{
    final /* synthetic */ NetflixComVideoDetailsHandler this$0;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ String val$trackId;
    final /* synthetic */ String val$videoId;
    
    NetflixComVideoDetailsHandler$1(final NetflixComVideoDetailsHandler this$0, final NetflixActivity val$activity, final String val$videoId, final String val$trackId) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
        this.val$videoId = val$videoId;
        this.val$trackId = val$trackId;
    }
    
    @Override
    public void onVideoSummaryFetched(final Video$Summary video$Summary, final Status status) {
        if (status.isSucces() && video$Summary != null) {
            if (video$Summary.getType() != VideoType.MOVIE && video$Summary.getType() != VideoType.SHOW) {
                NetflixComUtils.launchBrowser(this.val$activity, Uri.parse(NetflixComVideoDetailsHandler.HANDLER_DETAILS_URL + this.val$videoId));
            }
            else {
                if (this.val$activity != null && this.val$activity.getServiceManager() != null) {
                    final IClientLogging clientLogging = this.val$activity.getServiceManager().getClientLogging();
                    if (clientLogging != null && clientLogging.getCustomerEventLogging() != null) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("trkid").append('=').append(this.val$trackId);
                        sb.append('&');
                        sb.append("movieid").append('=').append(this.val$videoId);
                        clientLogging.getCustomerEventLogging().reportMdpFromDeepLinking(sb.toString());
                    }
                }
                DetailsActivityLauncher.show(this.val$activity, video$Summary.getType(), this.val$videoId, video$Summary.getTitle(), NflxProtocolUtils.getPlayContext(this.val$trackId), this.this$0.getAction(), this.this$0.getActionToken(), "DeepLink");
            }
        }
        else {
            ErrorLoggingManager.logHandledException(new Throwable("SPY-7518 - got error trying to fetch video summary for: " + this.val$videoId));
        }
        NflxProtocolUtils.reportDelayedResponseHandled(this.val$activity);
    }
}
