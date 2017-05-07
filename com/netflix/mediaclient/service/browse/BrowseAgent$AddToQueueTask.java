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
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.Random;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;

class BrowseAgent$AddToQueueTask extends BrowseAgent$FetchTask<Void>
{
    private final boolean iqInCache;
    private final String messageToken;
    final /* synthetic */ BrowseAgent this$0;
    private final int trackId;
    private final BrowseAgentCallback webClientCallback;
    
    public BrowseAgent$AddToQueueTask(final BrowseAgent this$0, final String s, final int trackId, final String messageToken, final BrowseAgentCallback browseAgentCallback) {
        this.this$0 = this$0;
        super(s, BrowseAgent.mPrefetchFromVideo, BrowseAgent.mPrefetchToVideo, browseAgentCallback);
        this.webClientCallback = new BrowseAgent$AddToQueueTask$1(this);
        this.trackId = trackId;
        this.iqInCache = this$0.mCache.areIqIdsInCache();
        this.messageToken = messageToken;
    }
    
    @Override
    public void run() {
        this.this$0.mBrowseWebClient.addToQueue(this.getCategory(), this.getFromIndex(), this.getToIndex(), this.trackId, this.iqInCache, this.messageToken, this.webClientCallback);
    }
}
