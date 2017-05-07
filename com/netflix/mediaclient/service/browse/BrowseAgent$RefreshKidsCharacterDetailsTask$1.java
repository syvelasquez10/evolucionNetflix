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
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Random;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;

class BrowseAgent$RefreshKidsCharacterDetailsTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$RefreshKidsCharacterDetailsTask this$1;
    
    BrowseAgent$RefreshKidsCharacterDetailsTask$1(final BrowseAgent$RefreshKidsCharacterDetailsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetailsFromCache = this.this$1.this$0.mCache.getKidsCharacterDetailsFromCache(this.this$1.mCharacterId);
        if (kidsCharacterDetailsFromCache == null) {
            Log.w("nf_service_browseagent", "Cached character details are null, can't refresh - charId: " + this.this$1.mCharacterId);
            return;
        }
        final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetails2 = (com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails)kidsCharacterDetails;
        final Boolean access$600 = this.this$1.this$0.isKidsCharacterDetailsNew(kidsCharacterDetailsFromCache, kidsCharacterDetails);
        if (b && kidsCharacterDetailsFromCache != null) {
            kidsCharacterDetailsFromCache.updateWatchNext(kidsCharacterDetails2);
        }
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$RefreshKidsCharacterDetailsTask$1$1(this, kidsCharacterDetailsFromCache, access$600, status));
    }
}
