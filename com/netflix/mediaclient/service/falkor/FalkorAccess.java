// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import android.content.Intent;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.browse.BrowseAgentCallbackWrapper;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.NetflixService$ClientCallbacks;
import com.netflix.mediaclient.servicemgr.IBrowseInterface;

public class FalkorAccess implements IBrowseInterface
{
    private static final String TAG = "FalkorAccess";
    private final FalkorAgent mBrowseAgent;
    private final NetflixService$ClientCallbacks mClientCallbacks;
    
    public FalkorAccess(final FalkorAgent mBrowseAgent, final NetflixService$ClientCallbacks mClientCallbacks) {
        this.mBrowseAgent = mBrowseAgent;
        this.mClientCallbacks = mClientCallbacks;
    }
    
    private BrowseAgentCallback wrapCallback(final BrowseAgentCallback browseAgentCallback) {
        return new BrowseAgentCallbackWrapper(browseAgentCallback);
    }
    
    @Override
    public void addToQueue(final String s, final VideoType videoType, final int n, final boolean b, final String s2, final int n2, final int n3) {
        this.mBrowseAgent.addToQueue(s, videoType, n, b, s2, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n2, n3)));
    }
    
    @Override
    public void dumpCacheToDisk() {
        this.mBrowseAgent.dumpCacheToDisk();
    }
    
    @Override
    public void fetchCWVideos(final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchCWVideos(n, n2, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n3, n4)));
    }
    
    @Override
    public void fetchEpisodeDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchEpisodeDetails(s, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void fetchEpisodes(final String s, final VideoType videoType, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchEpisodes(s, videoType, n, n2, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n3, n4)));
    }
    
    @Override
    public void fetchGenreLists(final int n, final int n2) {
        this.mBrowseAgent.fetchGenreList(this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final boolean b, final int n3, final int n4) {
        this.mBrowseAgent.fetchGenreVideos(loMo, n, n2, b, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n3, n4)));
    }
    
    @Override
    public void fetchGenres(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchGenres(s, n, n2, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n3, n4)));
    }
    
    @Override
    public void fetchIQVideos(final LoMo loMo, final int n, final int n2, final boolean b, final int n3, final int n4) {
        this.mBrowseAgent.fetchIQVideos(loMo, n, n2, b, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n3, n4)));
    }
    
    @Override
    public void fetchKidsCharacterDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchKidsCharacterDetails(s, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void fetchLoLoMoSummary(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchLoLoMoSummary(s, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void fetchLoMos(final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchLoMos(n, n2, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n3, n4)));
    }
    
    @Override
    public void fetchMovieDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchMovieDetails(s, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void fetchNotifications(final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchNotificationsList(n, n2, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n3, n4)));
    }
    
    @Override
    public void fetchPostPlayVideos(final String s, final VideoType videoType, final int n, final int n2) {
        this.mBrowseAgent.fetchPostPlayVideos(s, videoType, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void fetchPreAppData(final int n, final int n2) {
        this.mBrowseAgent.fetchPreAppData(n, n2);
    }
    
    @Override
    public void fetchSeasonDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchSeasonDetails(s, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void fetchSeasons(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchSeasons(s, n, n2, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n3, n4)));
    }
    
    @Override
    public void fetchShowDetails(final String s, final String s2, final boolean b, final int n, final int n2) {
        this.mBrowseAgent.fetchShowDetails(s, s2, b, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void fetchShowDetailsAndSeasons(final String s, final String s2, final boolean b, final int n, final int n2) {
        this.mBrowseAgent.fetchShowDetailsAndSeasons(s, s2, b, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void fetchSimilarVideosForPerson(final String s, final int n, final int n2, final int n3, final String s2) {
        this.mBrowseAgent.fetchSimilarVideosForPerson(s, n, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n2, n3)), s2);
    }
    
    @Override
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final int n2, final int n3, final String s2) {
        this.mBrowseAgent.fetchSimilarVideosForQuerySuggestion(s, n, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n2, n3)), s2);
    }
    
    @Override
    public void fetchVideoSummary(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchVideoSummary(s, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final boolean b, final boolean b2, final boolean b3, final int n3, final int n4) {
        this.mBrowseAgent.fetchVideos(loMo, n, n2, b, b2, b3, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n3, n4)));
    }
    
    @Override
    public void flushCaches() {
        this.mBrowseAgent.flushCaches();
    }
    
    @Override
    public void forceFetchFromLocalCache(final boolean b) {
        this.mBrowseAgent.forceFetchFromLocalCache(b);
    }
    
    @Override
    public ModelProxy<?> getModelProxy() {
        return this.mBrowseAgent.getModelProxy();
    }
    
    @Override
    public void logBillboardActivity(final Video video, final BillboardInteractionType billboardInteractionType) {
        this.mBrowseAgent.logBillboardActivity(video, billboardInteractionType);
    }
    
    @Override
    public void markNotificationAsRead(final SocialNotificationSummary socialNotificationSummary) {
        this.mBrowseAgent.markNotificationAsRead(socialNotificationSummary);
    }
    
    @Override
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2, final int n5, final int n6) {
        this.mBrowseAgent.prefetchGenreLoLoMo(s, n, n2, n3, n4, b, b2, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n5, n6)));
    }
    
    @Override
    public void prefetchLoLoMo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final boolean b3, final int n7, final int n8) {
        this.mBrowseAgent.prefetchLoLoMo(n, n2, n3, n4, n5, n6, b, b2, b3, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n7, n8)));
    }
    
    @Override
    public void refreshAll() {
        this.mBrowseAgent.refreshAll();
    }
    
    @Override
    public void refreshCw() {
        this.mBrowseAgent.refreshCw();
    }
    
    @Override
    public void refreshEpisodeData(final Asset asset) {
        this.mBrowseAgent.fetchEpisodesForSeason(asset);
    }
    
    @Override
    public void refreshIq() {
        this.mBrowseAgent.refreshIq();
    }
    
    @Override
    public void refreshSocialNotifications(final boolean b, final boolean b2, final MessageData messageData) {
        this.mBrowseAgent.refreshSocialNotifications(b, b2, messageData);
    }
    
    @Override
    public void removeFromQueue(final String s, final VideoType videoType, final String s2, final int n, final int n2) {
        this.mBrowseAgent.removeFromQueue(s, videoType, s2, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void searchNetflix(final String s, final int n, final int n2) {
        this.mBrowseAgent.searchNetflix(s, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void sendThanksToSocialNotification(final SocialNotificationSummary socialNotificationSummary, final int n, final int n2) {
        this.mBrowseAgent.sendThanksToSocialNotification(socialNotificationSummary, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n, n2)));
    }
    
    @Override
    public void sendThanksToSocialNotificationFromService(final SocialNotificationSummary socialNotificationSummary, final NetflixService netflixService, final boolean b) {
        if (b) {
            netflixService.getApplicationContext().sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
        this.mBrowseAgent.sendThanksToSocialNotification(socialNotificationSummary, new FalkorAccess$1SentThanksCallback(this, netflixService));
    }
    
    @Override
    public void setVideoRating(final String s, final VideoType videoType, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.setVideoRating(s, videoType, n, n2, this.wrapCallback(new FalkorAccess$BrowseAgentClientCallback(this, n3, n4)));
    }
    
    @Override
    public void updateCachedVideoPosition(final Asset asset) {
        this.mBrowseAgent.updateCachedVideoPosition(asset);
    }
}
