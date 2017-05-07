// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.content.Intent;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
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
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;

class BrowseAgent$FetchBillboardVideosTask extends BrowseAgent$CachedFetchTask<List<Billboard>>
{
    private final LoMo lomo;
    final /* synthetic */ BrowseAgent this$0;
    private final BrowseAgentCallback webClientCallback;
    
    public BrowseAgent$FetchBillboardVideosTask(final BrowseAgent this$0, final LoMo lomo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
        this.this$0 = this$0;
        super(this$0, "billboard", n, n2, browseAgentCallback, b);
        this.webClientCallback = new BrowseAgent$FetchBillboardVideosTask$1(this);
        this.lomo = lomo;
    }
    
    @Override
    public void run() {
        final List<Billboard> list = this.getCachedValue();
        if (list == null) {
            this.this$0.mBrowseWebClient.fetchBBVideos(this.lomo, this.getFromIndex(), this.getToIndex(), this.webClientCallback);
            return;
        }
        this.webClientCallback.onBBVideosFetched(list, CommonStatus.OK);
    }
}
