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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;

class BrowseAgent$FetchEpisodesTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchEpisodesTask$1 this$2;
    final /* synthetic */ List val$requestedEpisodes;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchEpisodesTask$1$1(final BrowseAgent$FetchEpisodesTask$1 this$2, final Status val$res, final List val$requestedEpisodes) {
        this.this$2 = this$2;
        this.val$res = val$res;
        this.val$requestedEpisodes = val$requestedEpisodes;
    }
    
    @Override
    public void run() {
        if (this.val$res.isSucces() && this.val$requestedEpisodes != null) {
            this.this$2.this$1.this$0.mCache.putInWeakEpisodesCache(this.val$requestedEpisodes);
            this.this$2.this$1.this$0.updateEpisodesWithLatestShowInformation(this.val$requestedEpisodes);
        }
        this.this$2.this$1.getCallback().onEpisodesFetched(this.val$requestedEpisodes, this.val$res);
    }
}
