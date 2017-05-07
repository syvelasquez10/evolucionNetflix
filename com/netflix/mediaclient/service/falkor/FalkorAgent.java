// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.NetflixApplication;
import java.util.Iterator;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.interface_.search.SocialNotificationsList;
import android.content.BroadcastReceiver;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.falkor.ServiceProvider;
import com.netflix.mediaclient.service.ServiceAgent;

public class FalkorAgent extends ServiceAgent implements ServiceProvider, ServiceAgent$BrowseAgentInterface
{
    private static final int REFRESH_NOTIFICATIONS_INTERVAL_MS = 3600000;
    private static final String TAG = "FalkorAgent";
    private static final Boolean USE_CACHE_AND_REMOTE;
    private static final Boolean USE_CACHE_ONLY;
    private static AtomicBoolean isCurrentProfileActive;
    private Root cache;
    private CachedModelProxy<Root> cmp;
    public final BroadcastReceiver playReceiver;
    private final Runnable refreshNotificationsRunnable;
    public final BroadcastReceiver userAgentIntentReceiver;
    
    static {
        boolean b = true;
        USE_CACHE_ONLY = true;
        if (FalkorAgent.USE_CACHE_ONLY) {
            b = false;
        }
        USE_CACHE_AND_REMOTE = b;
        FalkorAgent.isCurrentProfileActive = new AtomicBoolean();
    }
    
    public FalkorAgent() {
        this.playReceiver = new FalkorAgent$1(this);
        this.userAgentIntentReceiver = new FalkorAgent$2(this);
        this.refreshNotificationsRunnable = new FalkorAgent$7(this);
    }
    
    private static boolean canDoDataFetches() {
        if (!FalkorAgent.isCurrentProfileActive.get()) {
            Log.d("FalkorAgent", "wrong state - canDoDataFetches false - skipping browse request");
        }
        return FalkorAgent.isCurrentProfileActive.get();
    }
    
    private SocialNotificationSummary getFirstUnreadNotification(final SocialNotificationsList list) {
        final List<SocialNotificationSummary> socialNotifications = list.getSocialNotifications();
        if (socialNotifications == null) {
            return null;
        }
        for (final SocialNotificationSummary socialNotificationSummary : socialNotifications) {
            if (!socialNotificationSummary.getWasRead()) {
                return socialNotificationSummary;
            }
        }
        return null;
    }
    
    private void handleProfileActive() {
        Log.i("FalkorAgent", "Flushing all caches because new profile activated...");
        this.flushCaches();
        FalkorAgent.isCurrentProfileActive.set(true);
    }
    
    private void handleProfileDeactive() {
        FalkorAgent.isCurrentProfileActive.set(false);
    }
    
    private void rescheduleNotificationsRefresh() {
        this.getMainHandler().removeCallbacks(this.refreshNotificationsRunnable);
        this.getMainHandler().postDelayed(this.refreshNotificationsRunnable, 3600000L);
    }
    
    private boolean shouldBeNotificationSentToStatusBar(final SocialNotificationSummary socialNotificationSummary) {
        return socialNotificationSummary != null && !NetflixApplication.isActivityVisible() && SocialUtils.isNotificationsFeatureSupported(this.getService().getCurrentProfile(), this.getContext()) && this.getService().getPushNotification().isOptIn();
    }
    
    public void addToQueue(final String s, final VideoType videoType, final int n, final boolean b, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.addToQueue(s, videoType, n, b, s2, browseAgentCallback);
    }
    
    @Override
    public void destroy() {
        IntentUtils.unregisterSafelyLocalBroadcastReceiver(this.getContext(), this.userAgentIntentReceiver);
        IntentUtils.unregisterSafelyLocalBroadcastReceiver(this.getContext(), this.playReceiver);
        super.destroy();
    }
    
    public void doInit() {
        this.cache = new Root();
        this.cmp = new CachedModelProxy<Root>(this, this.cache, (FalkorVolleyWebClient)this.getResourceFetcher().getApiNextWebClient());
        this.cache.setProxy(this.cmp);
        IntentUtils.registerSafelyLocalBroadcastReceiver(this.getContext(), this.userAgentIntentReceiver, UserAgentBroadcastIntents.getNotificationIntentFilter());
        IntentUtils.registerSafelyBroadcastReceiver(this.getContext(), this.playReceiver, null, "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START", "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP");
        this.initCompleted(CommonStatus.OK);
    }
    
    public void dumpCacheToDisk() {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.dumpCacheToDisk();
    }
    
    @Override
    public void fetchBillboardsFromCache(final int n, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchBBVideos(0, n - 1, FalkorAgent.USE_CACHE_ONLY, browseAgentCallback);
    }
    
    @Override
    public void fetchCWFromCache(final int n, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchCWVideos(0, n - 1, FalkorAgent.USE_CACHE_ONLY, browseAgentCallback);
    }
    
    public void fetchCWVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchCWVideos(n, n2, FalkorAgent.USE_CACHE_AND_REMOTE, browseAgentCallback);
    }
    
    @Override
    public void fetchEpisodeDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchEpisodeDetails(s, browseAgentCallback);
    }
    
    public void fetchEpisodes(final String s, final VideoType videoType, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchEpisodes(s, videoType, n, n2, browseAgentCallback);
    }
    
    public void fetchEpisodesForSeason(final Asset asset) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        final String playableId = asset.getPlayableId();
        final boolean episode = asset.isEpisode();
        final String parentId = asset.getParentId();
        if (StringUtils.isEmpty(playableId) || (episode && StringUtils.isEmpty(parentId))) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", String.format("fetchEpisodesForSeason - parentId %s or videoId %s null - skip!", parentId, playableId));
            }
        }
        else {
            if (episode) {
                this.fetchShowDetails(parentId, null, false, new FalkorAgent$4(this));
                return;
            }
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", String.format("fetchEpisodesForSeason - parentId %s or videoId %s is Movie - skip!", parentId, playableId));
            }
        }
    }
    
    public void fetchGenreList(final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchGenreList(browseAgentCallback);
    }
    
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchVideos(loMo, n, n2, FalkorAgent.USE_CACHE_AND_REMOTE, b, false, browseAgentCallback);
    }
    
    public void fetchGenres(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchGenres(s, n, n2, browseAgentCallback);
    }
    
    @Override
    public void fetchIQFromCache(final int n, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchIQVideos(0, n - 1, FalkorAgent.USE_CACHE_ONLY, false, browseAgentCallback);
    }
    
    public void fetchIQVideos(final LoMo loMo, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchIQVideos(loMo, n, n2, FalkorAgent.USE_CACHE_AND_REMOTE, b, browseAgentCallback);
    }
    
    public void fetchKidsCharacterDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchKidsCharacterDetails(s, browseAgentCallback);
    }
    
    public void fetchLoLoMoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchGenreLoLoMoSummary(s, browseAgentCallback);
    }
    
    public void fetchLoMos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchLoMos(n, n2, browseAgentCallback);
    }
    
    @Override
    public void fetchMovieDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchMovieDetails(s, browseAgentCallback);
    }
    
    public void fetchNotificationsList(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchNotifications(n, n2, false, browseAgentCallback);
    }
    
    @Override
    public void fetchPostPlayVideos(final String s, final VideoType videoType, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchPostPlayVideos(s, videoType, browseAgentCallback);
    }
    
    public void fetchPreAppData(final int n, int n2) {
        final FalkorAgent$8 falkorAgent$8 = new FalkorAgent$8(this);
        --n2;
        this.prefetchLoLoMo(0, n - 1, 0, n2, 0, n2, false, false, false, falkorAgent$8);
    }
    
    @Override
    public void fetchRecommendedListFromCache(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchLoMos(0, 10, new FalkorAgent$9(this, n, n2, browseAgentCallback));
    }
    
    public void fetchSeasonDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSeasonDetails(s, browseAgentCallback);
    }
    
    public void fetchSeasons(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSeasons(s, n, n2, browseAgentCallback);
    }
    
    public void fetchShowDetails(final String s, final String s2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchShowDetails(s, s2, false, b, browseAgentCallback);
    }
    
    public void fetchShowDetailsAndSeasons(final String s, final String s2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchShowDetails(s, s2, true, b, browseAgentCallback);
    }
    
    public void fetchSimilarVideosForPerson(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSimilarVideos(Falkor$SimilarRequestType.PEOPLE, s, n, s2, browseAgentCallback);
    }
    
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSimilarVideos(Falkor$SimilarRequestType.QUERY_SUGGESTION, s, n, s2, browseAgentCallback);
    }
    
    @Override
    public void fetchVideoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchVideoSummary(s, browseAgentCallback);
    }
    
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final boolean b, final boolean b2, final boolean b3, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchVideos(loMo, n, n2, b, b2, b3, browseAgentCallback);
    }
    
    public void flushCaches() {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.flushCaches();
    }
    
    public void forceFetchFromLocalCache(final boolean b) {
        this.cmp.forceFetchFromLocalCache(b);
    }
    
    public ModelProxy<?> getModelProxy() {
        return this.cmp;
    }
    
    @Override
    public NetflixService getService() {
        return super.getService();
    }
    
    public void invalidateCachedEpisodes(final String s, final VideoType videoType) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.invalidateEpisodes(s, videoType);
    }
    
    public void logBillboardActivity(final Video video, final BillboardInteractionType billboardInteractionType) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.logBillboardActivity(video, billboardInteractionType);
    }
    
    public void markNotificationAsRead(final SocialNotificationSummary socialNotificationSummary) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.markSocialNotificationAsRead(socialNotificationSummary, new FalkorAgent$5(this));
    }
    
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.prefetchGenreLoLoMo(s, n, n2, n3, n4, b, browseAgentCallback);
    }
    
    public void prefetchLoLoMo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final boolean b3, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.prefetchLoLoMo(n, n2, n3, n4, n5, n6, b, b2, b3, new FalkorAgent$3(this, browseAgentCallback));
    }
    
    public void refreshAll() {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.flushCaches();
        ServiceManager.sendHomeRefreshBrodcast((Context)this.getService());
    }
    
    public void refreshCw() {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        if (BrowseExperience.isKubrickKids()) {
            if (Log.isLoggable()) {
                Log.v("FalkorAgent", "Showing Kubrick Kids experience - redirecting refresh CW call to refresh popular titles...");
            }
            this.cmp.refreshPopularTitlesLomo();
            return;
        }
        if (this.cmp.doesCwExist()) {
            this.cmp.refreshCw();
            return;
        }
        this.refreshAll();
    }
    
    public void refreshIq() {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.refreshIq();
    }
    
    public void refreshSocialNotifications(final boolean b, final boolean b2, final MessageData messageData) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchNotifications(0, 19, b, new FalkorAgent$6(this, b2, messageData));
        if (this.getService() != null && this.getService().getCurrentProfile() != null && this.getService().getCurrentProfile().isSocialConnected()) {
            this.rescheduleNotificationsRefresh();
        }
    }
    
    public void removeFromQueue(final String s, final VideoType videoType, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.removeFromQueue(s, videoType, s2, browseAgentCallback);
    }
    
    public void searchNetflix(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.searchNetflix(s, browseAgentCallback);
    }
    
    public void sendThanksToSocialNotification(final SocialNotificationSummary socialNotificationSummary, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.sendThanksToSocialNotification(socialNotificationSummary, browseAgentCallback);
    }
    
    public void setVideoRating(final String s, final VideoType videoType, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.setVideoRating(s, videoType, n, n2, browseAgentCallback);
    }
    
    public void updateCachedVideoPosition(final Asset asset) {
        if (Log.isLoggable()) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.updateBookmarkPosition(asset);
    }
}
