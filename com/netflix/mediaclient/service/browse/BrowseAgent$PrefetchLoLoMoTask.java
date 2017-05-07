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

class BrowseAgent$PrefetchLoLoMoTask extends BrowseAgent$FetchTask<Void>
{
    final boolean mIncludeBoxshots;
    final boolean mIncludeExtraCharacters;
    final int mToBBVideo;
    final int mToCWVideo;
    final int mToLoMo;
    final /* synthetic */ BrowseAgent this$0;
    private final BrowseAgentCallback webClientCallback;
    
    public BrowseAgent$PrefetchLoLoMoTask(final BrowseAgent this$0, final int mToLoMo, final int n, final int mToCWVideo, final int mToBBVideo, final boolean mIncludeExtraCharacters, final boolean mIncludeBoxshots, final BrowseAgentCallback browseAgentCallback) {
        this.this$0 = this$0;
        super("lolomo", 0, n, browseAgentCallback);
        this.webClientCallback = new BrowseAgent$PrefetchLoLoMoTask$1(this);
        this.mToLoMo = mToLoMo;
        this.mToCWVideo = mToCWVideo;
        this.mToBBVideo = mToBBVideo;
        this.mIncludeBoxshots = mIncludeBoxshots;
        this.mIncludeExtraCharacters = mIncludeExtraCharacters;
    }
    
    @Override
    public void run() {
        this.this$0.mBrowseWebClient.prefetchLoLoMo(this.getCategory(), this.mToLoMo, this.getToIndex(), this.mToCWVideo, this.mToBBVideo, this.mIncludeExtraCharacters, this.webClientCallback);
    }
}
