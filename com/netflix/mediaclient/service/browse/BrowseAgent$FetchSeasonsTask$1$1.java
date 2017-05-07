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
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.CWVideo;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.content.IntentFilter;
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
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;

class BrowseAgent$FetchSeasonsTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchSeasonsTask$1 this$2;
    final /* synthetic */ List val$requestedSeasons;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchSeasonsTask$1$1(final BrowseAgent$FetchSeasonsTask$1 this$2, final Status val$res, final List val$requestedSeasons) {
        this.this$2 = this$2;
        this.val$res = val$res;
        this.val$requestedSeasons = val$requestedSeasons;
    }
    
    @Override
    public void run() {
        if (this.val$res.isSucces() && this.val$requestedSeasons != null) {
            this.this$2.this$1.this$0.mCache.putInWeakSeasonsCache(this.val$requestedSeasons);
        }
        this.this$2.this$1.getCallback().onSeasonsFetched(this.val$requestedSeasons, this.val$res);
    }
}
