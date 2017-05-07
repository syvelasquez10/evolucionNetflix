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
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;

class BrowseAgent$FetchShowDetailsTask extends BrowseAgent$CachedFetchTask<ShowDetails>
{
    private final String mCurrentEpisodeId;
    private final boolean mIncludeKubrick;
    private final boolean mIncludeSeasons;
    final /* synthetic */ BrowseAgent this$0;
    private final BrowseAgentCallback webClientCallback;
    
    public BrowseAgent$FetchShowDetailsTask(final BrowseAgent this$0, final String s, final String mCurrentEpisodeId, final boolean mIncludeSeasons, final boolean mIncludeKubrick, final BrowseAgentCallback browseAgentCallback) {
        this.this$0 = this$0;
        super(this$0, s, 0, 0, browseAgentCallback);
        this.webClientCallback = new BrowseAgent$FetchShowDetailsTask$1(this);
        this.mCurrentEpisodeId = mCurrentEpisodeId;
        this.mIncludeSeasons = mIncludeSeasons;
        this.mIncludeKubrick = mIncludeKubrick;
    }
    
    @Override
    public void run() {
        final ShowDetails showDetails = this.getCachedValue();
        if (showDetails != null && !StringUtils.isNotEmpty(this.mCurrentEpisodeId)) {
            this.webClientCallback.onShowDetailsFetched(showDetails, CommonStatus.OK);
            return;
        }
        if (this.mIncludeSeasons) {
            this.this$0.mBrowseWebClient.fetchShowDetailsAndSeasons(this.getCategory(), this.mCurrentEpisodeId, this.mIncludeKubrick, this.webClientCallback);
            return;
        }
        this.this$0.mBrowseWebClient.fetchShowDetails(this.getCategory(), this.mCurrentEpisodeId, this.mIncludeKubrick, this.webClientCallback);
    }
}
