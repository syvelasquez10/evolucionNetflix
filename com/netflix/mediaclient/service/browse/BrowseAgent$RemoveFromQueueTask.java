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
import com.netflix.mediaclient.android.app.Status;
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
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;

class BrowseAgent$RemoveFromQueueTask extends BrowseAgent$FetchTask<Void>
{
    private final boolean iqInCache;
    private final String messageToken;
    final /* synthetic */ BrowseAgent this$0;
    private final BrowseAgentCallback webClientCallback;
    
    public BrowseAgent$RemoveFromQueueTask(final BrowseAgent this$0, final String s, final String messageToken, final BrowseAgentCallback browseAgentCallback) {
        this.this$0 = this$0;
        super(s, BrowseAgent.sPrefetchFromVideo, BrowseAgent.sPrefetchToVideo, browseAgentCallback);
        this.webClientCallback = new BrowseAgent$RemoveFromQueueTask$1(this);
        this.iqInCache = this$0.mCache.areIqIdsInCache();
        this.messageToken = messageToken;
    }
    
    @Override
    public void run() {
        this.this$0.mBrowseWebClient.removeFromQueue(this.getCategory(), this.getFromIndex(), this.getToIndex(), this.iqInCache, this.messageToken, this.webClientCallback);
    }
}
