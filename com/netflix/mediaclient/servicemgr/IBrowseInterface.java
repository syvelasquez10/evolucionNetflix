// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;

public interface IBrowseInterface
{
    void addToQueue(final String p0, final VideoType p1, final int p2, final boolean p3, final String p4, final int p5, final int p6);
    
    void dumpCacheToDisk();
    
    void fetchCWVideos(final int p0, final int p1, final int p2, final int p3);
    
    void fetchEpisodeDetails(final String p0, final int p1, final int p2);
    
    void fetchEpisodes(final String p0, final VideoType p1, final int p2, final int p3, final int p4, final int p5);
    
    void fetchGenreLists(final int p0, final int p1);
    
    void fetchGenreVideos(final LoMo p0, final int p1, final int p2, final boolean p3, final int p4, final int p5);
    
    void fetchGenres(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchIQVideos(final LoMo p0, final int p1, final int p2, final boolean p3, final int p4, final int p5);
    
    void fetchKidsCharacterDetails(final String p0, final int p1, final int p2);
    
    void fetchLoLoMoSummary(final String p0, final int p1, final int p2);
    
    void fetchLoMos(final int p0, final int p1, final int p2, final int p3);
    
    void fetchMovieDetails(final String p0, final int p1, final int p2);
    
    void fetchNotifications(final int p0, final int p1, final int p2, final int p3);
    
    void fetchPostPlayVideos(final String p0, final VideoType p1, final int p2, final int p3);
    
    void fetchPreAppData(final int p0, final int p1);
    
    void fetchSeasonDetails(final String p0, final int p1, final int p2);
    
    void fetchSeasons(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchShowDetails(final String p0, final String p1, final boolean p2, final int p3, final int p4);
    
    void fetchShowDetailsAndSeasons(final String p0, final String p1, final boolean p2, final int p3, final int p4);
    
    void fetchSimilarVideosForPerson(final String p0, final int p1, final int p2, final int p3, final String p4);
    
    void fetchSimilarVideosForQuerySuggestion(final String p0, final int p1, final int p2, final int p3, final String p4);
    
    void fetchVideoSummary(final String p0, final int p1, final int p2);
    
    void fetchVideos(final LoMo p0, final int p1, final int p2, final boolean p3, final boolean p4, final boolean p5, final int p6, final int p7);
    
    void flushCaches();
    
    void forceFetchFromLocalCache(final boolean p0);
    
    ModelProxy<?> getModelProxy();
    
    void invalidateCachedEpisodes(final String p0, final VideoType p1);
    
    void logBillboardActivity(final Video p0, final BillboardInteractionType p1);
    
    void markNotificationAsRead(final SocialNotificationSummary p0);
    
    void prefetchGenreLoLoMo(final String p0, final int p1, final int p2, final int p3, final int p4, final boolean p5, final boolean p6, final int p7, final int p8);
    
    void prefetchLoLoMo(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final boolean p6, final boolean p7, final boolean p8, final int p9, final int p10);
    
    void refreshAll();
    
    void refreshCw();
    
    void refreshEpisodeData(final Asset p0);
    
    void refreshIq();
    
    void refreshSocialNotifications(final boolean p0, final boolean p1, final MessageData p2);
    
    void removeFromQueue(final String p0, final VideoType p1, final String p2, final int p3, final int p4);
    
    void searchNetflix(final String p0, final int p1, final int p2);
    
    void sendThanksToSocialNotification(final SocialNotificationSummary p0, final int p1, final int p2);
    
    void sendThanksToSocialNotificationFromService(final SocialNotificationSummary p0, final NetflixService p1, final boolean p2);
    
    void setVideoRating(final String p0, final VideoType p1, final int p2, final int p3, final int p4, final int p5);
    
    void updateCachedVideoPosition(final Asset p0);
}
