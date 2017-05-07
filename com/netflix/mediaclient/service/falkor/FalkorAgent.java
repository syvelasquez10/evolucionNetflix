// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.service.browse.BrowseAgent$BillboardActivityType;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.falkor.ServiceProvider;
import com.netflix.mediaclient.service.ServiceAgent;

public class FalkorAgent extends ServiceAgent implements ServiceProvider, ServiceAgent$BrowseAgentInterface
{
    private static final String TAG = "FalkorAgent";
    private Root cache;
    private CachedModelProxy<Root> cmp;
    
    public void addToQueue(final String s, final int n, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doInit() {
        this.cache = new Root();
        this.cmp = new CachedModelProxy<Root>(this, this.cache, (FalcorVolleyWebClient)this.getResourceFetcher().getApiNextWebClient());
        this.cache.setProxy(this.cmp);
        this.initCompleted(CommonStatus.OK);
    }
    
    public void dumpCacheToDisk() {
    }
    
    public void dumpHomeLoLoMosAndVideos(final String s, final String s2) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    @Override
    public List<Billboard> fetchBillboardsFromCache(final int n) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        return null;
    }
    
    @Override
    public List<CWVideo> fetchCWFromCache(final int n) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        return null;
    }
    
    public void fetchCWVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchCWVideos(n, n2, browseAgentCallback);
    }
    
    @Override
    public void fetchEpisodeDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void fetchEpisodes(final String s, final VideoType videoType, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchEpisodes(s, videoType, n, n2, browseAgentCallback);
    }
    
    public void fetchGenreList(final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchGenreList(browseAgentCallback);
    }
    
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchVideos(loMo, n, n2, browseAgentCallback);
    }
    
    public void fetchGenres(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchGenres(s, n, n2, browseAgentCallback);
    }
    
    @Override
    public List<Video> fetchIQFromCache(final int n) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        return null;
    }
    
    public void fetchIQVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchIQVideos(loMo, n, n2, browseAgentCallback);
    }
    
    public void fetchKidsCharacterDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void fetchLoLoMoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void fetchLoMos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchLoMos(n, n2, browseAgentCallback);
    }
    
    @Override
    public void fetchMovieDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchMovieDetails(s, browseAgentCallback);
    }
    
    @Override
    public void fetchPostPlayVideos(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    @Override
    public List<Video> fetchRecommendedListFromCache(final int n) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        return null;
    }
    
    @Override
    public void fetchSeasonDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void fetchSeasons(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSeasons(s, n, n2, browseAgentCallback);
    }
    
    @Override
    public void fetchShowDetails(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchShowDetails(s, s2, false, browseAgentCallback);
    }
    
    public void fetchSimilarVideosForPerson(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSimilarVideos(Falkor$SimilarRequestType.PEOPLE, s, n, s2, browseAgentCallback);
    }
    
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchSimilarVideos(Falkor$SimilarRequestType.QUERY_SUGGESTION, s, n, s2, browseAgentCallback);
    }
    
    public void fetchSocialNotificationsList(final int n, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.fetchVideos(loMo, n, n2, browseAgentCallback);
    }
    
    public void flushCaches() {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.flushCaches();
    }
    
    @Override
    public NetflixService getService() {
        return super.getService();
    }
    
    public void hideVideo(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void logBillboardActivity(final Video video, final BrowseAgent$BillboardActivityType browseAgent$BillboardActivityType) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void markSocialNotificationsAsRead(final List<SocialNotificationSummary> list) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.prefetchGenreLoLoMo(s, n, n2, n3, n4, browseAgentCallback);
    }
    
    public void prefetchLoLoMo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.prefetchLoLoMo(n, n2, n3, n4, n5, n6, b, b2, browseAgentCallback);
    }
    
    public void refreshCW() {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void refreshEpisodesData(final Asset asset) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void refreshIQ() {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void refreshSocialNotifications(final boolean b, final boolean b2, final MessageData messageData) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void removeFromQueue(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void searchNetflix(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.searchNetflix(s, browseAgentCallback);
    }
    
    public void sendThanksToSocialNotification(final SocialNotificationSummary socialNotificationSummary, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
    
    public void setVideoRating(final String s, final VideoType videoType, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
        this.cmp.setVideoRating(s, videoType, n, n2, browseAgentCallback);
    }
    
    public void updateCachedVideoPosition(final Asset asset) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", LogUtils.getCurrMethodName());
        }
    }
}
