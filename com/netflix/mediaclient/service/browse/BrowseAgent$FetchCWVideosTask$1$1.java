// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.content.Intent;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;

class BrowseAgent$FetchCWVideosTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchCWVideosTask$1 this$2;
    final /* synthetic */ List val$requestedVideos;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchCWVideosTask$1$1(final BrowseAgent$FetchCWVideosTask$1 this$2, final List val$requestedVideos, final Status val$res) {
        this.this$2 = this$2;
        this.val$requestedVideos = val$requestedVideos;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onCWVideosFetched(this.val$requestedVideos, this.val$res);
        if (StatusCode.SERVER_ERROR_MAP_CACHE_MISS == this.val$res.getStatusCode()) {
            Log.e("nf_service_browseagent", "Map cache miss for CW - refresh");
            BrowseAgent.sendHomeRefreshBrodcast(this.this$2.this$1.this$0.getContext());
        }
    }
}
