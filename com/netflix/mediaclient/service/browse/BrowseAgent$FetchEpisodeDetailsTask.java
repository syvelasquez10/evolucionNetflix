// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.util.UiUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.service.webclient.model.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.CWVideo;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.content.IntentFilter;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.Iterator;
import java.util.Random;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import java.util.List;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import java.lang.ref.WeakReference;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;

class BrowseAgent$FetchEpisodeDetailsTask extends BrowseAgent$CachedFetchTask<EpisodeDetails>
{
    final /* synthetic */ BrowseAgent this$0;
    private final BrowseAgentCallback webClientCallback;
    
    public BrowseAgent$FetchEpisodeDetailsTask(final BrowseAgent this$0, final String s, final BrowseAgentCallback browseAgentCallback) {
        this.this$0 = this$0;
        super(this$0, s, 0, 0, browseAgentCallback);
        this.webClientCallback = new BrowseAgent$FetchEpisodeDetailsTask$1(this);
    }
    
    @Override
    public void run() {
        final EpisodeDetails episodeDetails = this.getCachedValue();
        if (episodeDetails != null) {
            this.this$0.updateEpisodeWithLatestShowInformation(episodeDetails);
            this.webClientCallback.onEpisodeDetailsFetched(episodeDetails, CommonStatus.OK);
            return;
        }
        final String buildEpisodeDetailsCacheKey = BrowseWebClientCache.buildEpisodeDetailsCacheKey(this.getCategory());
        if (Log.isLoggable("nf_service_browseagent", 3)) {
            Log.d("nf_bookmark", "looking for episode with key: " + buildEpisodeDetailsCacheKey);
        }
        final WeakReference weakReference = (WeakReference)this.this$0.mCache.getWeakEpisodesCache().get(buildEpisodeDetailsCacheKey);
        if (weakReference != null && weakReference.get() != null) {
            final EpisodeDetails episodeDetails2 = weakReference.get();
            this.this$0.updateEpisodeWithLatestShowInformation(episodeDetails2);
            this.webClientCallback.onEpisodeDetailsFetched(episodeDetails2, CommonStatus.OK);
            return;
        }
        this.this$0.mBrowseWebClient.fetchEpisodeDetails(this.getCategory(), this.webClientCallback);
    }
}
