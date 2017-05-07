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
import com.netflix.mediaclient.service.webclient.model.CWVideo;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.content.IntentFilter;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.Random;
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
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$FetchShowDetailsTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchShowDetailsTask this$1;
    
    BrowseAgent$FetchShowDetailsTask$1(final BrowseAgent$FetchShowDetailsTask this$1) {
        this.this$1 = this$1;
    }
    
    private void updateCache(final Status status, final ShowDetails showDetails) {
        if (status.isSucces()) {
            if (!StringUtils.isEmpty(this.this$1.mCurrentEpisodeId)) {
                this.this$1.this$0.updateSeasonWithSdp(showDetails);
                return;
            }
            this.this$1.updateCacheIfNecessary(showDetails, status);
        }
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
        this.updateCache(status, showDetails);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchShowDetailsTask$1$2(this, showDetails, list, status));
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        this.updateCache(status, showDetails);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchShowDetailsTask$1$1(this, showDetails, status));
    }
}
