// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.content.Intent;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideo;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class PostPlayForMDX$FetchPostPlayForPlaybackCallback extends LoggingManagerCallback
{
    final /* synthetic */ PostPlayForMDX this$0;
    
    public PostPlayForMDX$FetchPostPlayForPlaybackCallback(final PostPlayForMDX this$0) {
        this.this$0 = this$0;
        super("nf_postplay");
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        super.onEpisodeDetailsFetched(episodeDetails, status);
        if (status.isSucces() && episodeDetails != null) {
            this.this$0.episodeDetails = episodeDetails;
            this.this$0.updateViews(episodeDetails);
            this.this$0.setMDXTargetName();
            this.this$0.transitionToPostPlay();
        }
    }
}
