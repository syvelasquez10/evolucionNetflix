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
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.browse.cache.BrowseCache;

abstract class BrowseAgent$CachedFetchTask<T> extends BrowseAgent$FetchTask<T>
{
    private static final String TAG = "CachedFetchTask";
    private final String cacheKey;
    private T cacheVal;
    private final BrowseCache primaryCache;
    private final BrowseCache secondaryCache;
    final /* synthetic */ BrowseAgent this$0;
    
    public BrowseAgent$CachedFetchTask(final BrowseAgent browseAgent, final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this(browseAgent, s, n, n2, browseAgentCallback, true);
    }
    
    public BrowseAgent$CachedFetchTask(final BrowseAgent this$0, final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
        this.this$0 = this$0;
        super(s, n, n2, browseAgentCallback);
        Object primaryCache;
        if (b) {
            primaryCache = this$0.mCache.getSoftCache();
        }
        else {
            primaryCache = this$0.mCache.getHardCache();
        }
        this.primaryCache = (BrowseCache)primaryCache;
        Object secondaryCache;
        if (b) {
            secondaryCache = this$0.mCache.getHardCache();
        }
        else {
            secondaryCache = this$0.mCache.getSoftCache();
        }
        this.secondaryCache = (BrowseCache)secondaryCache;
        this.cacheKey = BrowseWebClientCache.buildBrowseCacheKey(getCacheKeyFromClassName(this.getClass()), s, String.valueOf(n), String.valueOf(n2));
    }
    
    protected String getCacheKey() {
        return this.cacheKey;
    }
    
    protected T getCachedValue() {
        this.cacheVal = (T)this.primaryCache.get(this.cacheKey);
        if (this.cacheVal == null) {
            this.cacheVal = (T)this.secondaryCache.get(this.cacheKey);
        }
        return this.cacheVal;
    }
    
    protected void updateCacheIfNecessary(final T t, final Status status) {
        if (this.cacheVal == null && status.isSucces()) {
            if (Log.isLoggable("CachedFetchTask", 2)) {
                Log.v("CachedFetchTask", "+ Putting in cache: " + this.cacheKey + ", hash: " + t.hashCode());
            }
            this.primaryCache.put(this.cacheKey, t);
        }
    }
}
