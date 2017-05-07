// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.service.pushnotification.MessageData;
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
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.Random;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.model.leafs.social.SocialNotificationsList;

class BrowseAgent$FetchSocialNotificationsTask extends BrowseAgent$CachedFetchTask<SocialNotificationsList>
{
    private final int fromIndex;
    BrowseAgentCallback mCallback;
    private final boolean skipCache;
    final /* synthetic */ BrowseAgent this$0;
    private final BrowseAgentCallback webClientCallback;
    
    public BrowseAgent$FetchSocialNotificationsTask(final BrowseAgent this$0, final int fromIndex, final boolean skipCache, final BrowseAgentCallback mCallback) {
        this.this$0 = this$0;
        super(this$0, "socialNotificationsList", fromIndex, fromIndex + 20 - 1, mCallback);
        this.webClientCallback = new BrowseAgent$FetchSocialNotificationsTask$1(this);
        this.mCallback = mCallback;
        this.fromIndex = fromIndex;
        this.skipCache = skipCache;
    }
    
    @Override
    public void run() {
        final SocialNotificationsList list = this.getCachedValue();
        if (this.skipCache || list == null) {
            this.this$0.mBrowseWebClient.fetchSocialNotifications(this.fromIndex, this.webClientCallback);
            return;
        }
        this.webClientCallback.onSocialNotificationsListFetched(list, CommonStatus.OK);
    }
}
