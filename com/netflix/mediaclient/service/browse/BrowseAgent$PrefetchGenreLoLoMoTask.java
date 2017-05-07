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
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
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
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;

class BrowseAgent$PrefetchGenreLoLoMoTask extends BrowseAgent$FetchTask<Void>
{
    final int mFromLoMo;
    final boolean mIncludeKubrick;
    final int mToLoMo;
    final /* synthetic */ BrowseAgent this$0;
    private final BrowseAgentCallback webClientCallback;
    
    public BrowseAgent$PrefetchGenreLoLoMoTask(final BrowseAgent this$0, final String s, final int mFromLoMo, final int mToLoMo, final int n, final int n2, final boolean mIncludeKubrick, final BrowseAgentCallback browseAgentCallback) {
        this.this$0 = this$0;
        super(s, n, n2, browseAgentCallback);
        this.webClientCallback = new BrowseAgent$PrefetchGenreLoLoMoTask$1(this);
        this.mFromLoMo = mFromLoMo;
        this.mToLoMo = mToLoMo;
        this.mIncludeKubrick = mIncludeKubrick;
    }
    
    @Override
    public void run() {
        this.this$0.mBrowseWebClient.prefetchGenreLoLoMo(this.getCategory(), this.mFromLoMo, this.mToLoMo, this.getFromIndex(), this.getToIndex(), this.mIncludeKubrick, this.webClientCallback);
    }
}
