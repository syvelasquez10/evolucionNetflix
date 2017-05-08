// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.model.leafs.advisory.ExpiringContentAdvisory$ContentAction;
import java.util.List;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.falkor.ModelProxy;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.mediaclient.ui.player.PostPlayRequestContext;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import java.io.File;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;

public interface IBrowseManager
{
    boolean addToQueue(final String p0, final VideoType p1, final int p2, final String p3, final ManagerCallback p4);
    
    void dumpCacheToDisk(final File p0);
    
    void dumpHomeLoLoMosAndVideos(final String p0, final String p1);
    
    void endBrowsePlaySession(final long p0, final int p1, final int p2, final int p3);
    
    boolean fetchActorDetailsAndRelatedForTitle(final String p0, final ManagerCallback p1);
    
    void fetchAdvisories(final String p0, final ManagerCallback p1);
    
    boolean fetchCWVideos(final int p0, final int p1, final ManagerCallback p2);
    
    boolean fetchEpisodeDetails(final String p0, final String p1, final ManagerCallback p2);
    
    boolean fetchEpisodes(final String p0, final VideoType p1, final int p2, final int p3, final ManagerCallback p4);
    
    boolean fetchFalkorVideo(final String p0, final ManagerCallback p1);
    
    boolean fetchGenreLists(final ManagerCallback p0);
    
    boolean fetchGenreVideos(final LoMo p0, final int p1, final int p2, final boolean p3, final ManagerCallback p4);
    
    boolean fetchGenres(final String p0, final int p1, final int p2, final ManagerCallback p3);
    
    boolean fetchIQVideos(final LoMo p0, final int p1, final int p2, final boolean p3, final ManagerCallback p4);
    
    boolean fetchInteractiveVideoMoments(final VideoType p0, final String p1, final String p2, final int p3, final int p4, final ManagerCallback p5);
    
    boolean fetchKidsCharacterDetails(final String p0, final ManagerCallback p1);
    
    boolean fetchLoLoMoSummary(final String p0, final ManagerCallback p1);
    
    boolean fetchLoMos(final int p0, final int p1, final ManagerCallback p2);
    
    boolean fetchMovieDetails(final String p0, final String p1, final ManagerCallback p2);
    
    boolean fetchNotificationsList(final int p0, final int p1, final ManagerCallback p2);
    
    boolean fetchPersonDetail(final String p0, final ManagerCallback p1, final String p2);
    
    boolean fetchPersonRelated(final String p0, final ManagerCallback p1);
    
    boolean fetchPostPlayVideos(final String p0, final VideoType p1, final PostPlayRequestContext p2, final ManagerCallback p3);
    
    boolean fetchScenePosition(final VideoType p0, final String p1, final String p2, final ManagerCallback p3);
    
    boolean fetchSeasonDetails(final String p0, final ManagerCallback p1);
    
    boolean fetchSeasons(final String p0, final int p1, final int p2, final ManagerCallback p3);
    
    boolean fetchShowDetails(final String p0, final String p1, final boolean p2, final ManagerCallback p3);
    
    boolean fetchShowDetailsAndSeasons(final String p0, final String p1, final boolean p2, final boolean p3, final ManagerCallback p4);
    
    boolean fetchSimilarVideosForPerson(final String p0, final int p1, final ManagerCallback p2, final String p3);
    
    boolean fetchSimilarVideosForQuerySuggestion(final String p0, final int p1, final ManagerCallback p2, final String p3);
    
    void fetchTask(final CachedModelProxy$CmpTaskDetails p0, final ManagerCallback p1);
    
    boolean fetchVideoSummary(final String p0, final ManagerCallback p1);
    
    boolean fetchVideos(final LoMo p0, final int p1, final int p2, final boolean p3, final boolean p4, final boolean p5, final ManagerCallback p6);
    
    boolean flushCaches();
    
    String getLolomoId();
    
    ModelProxy<?> getModelProxy();
    
    void invalidateCachedEpisodes(final String p0, final VideoType p1);
    
    void logBillboardActivity(final Video p0, final BillboardInteractionType p1, final Map<String, String> p2);
    
    boolean logPostPlayImpression(final String p0, final VideoType p1, final String p2, final ManagerCallback p3);
    
    void markNotificationAsRead(final IrisNotificationSummary p0);
    
    void markNotificationsAsRead(final List<IrisNotificationSummary> p0);
    
    boolean prefetchGenreLoLoMo(final String p0, final int p1, final int p2, final int p3, final int p4, final boolean p5, final boolean p6, final ManagerCallback p7);
    
    boolean prefetchLoLoMo(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final boolean p6, final boolean p7, final boolean p8, final ManagerCallback p9);
    
    boolean prefetchVideoListDetails(final List<? extends Video> p0, final ManagerCallback p1);
    
    void refreshCw(final boolean p0);
    
    void refreshIq();
    
    void refreshIrisNotifications(final boolean p0);
    
    void refreshLolomo();
    
    boolean removeFromQueue(final String p0, final VideoType p1, final String p2, final ManagerCallback p3);
    
    boolean runPrefetchLolomoJob(final boolean p0);
    
    boolean searchNetflix(final String p0, final ManagerCallback p1);
    
    boolean setVideoRating(final String p0, final VideoType p1, final int p2, final int p3, final ManagerCallback p4);
    
    void updateExpiredContentAdvisoryStatus(final String p0, final ExpiringContentAdvisory$ContentAction p1);
}
