// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMo;

public interface IBrowseManager
{
    boolean addToQueue(final String p0, final int p1, final ManagerCallback p2);
    
    boolean dumpHomeLoLoMosAndVideos(final String p0, final String p1);
    
    boolean dumpHomeLoLoMosAndVideosToLog();
    
    boolean dumpHomeLoMos();
    
    boolean fetchCWVideos(final int p0, final int p1, final ManagerCallback p2);
    
    boolean fetchEpisodeDetails(final String p0, final ManagerCallback p1);
    
    boolean fetchEpisodes(final String p0, final int p1, final int p2, final ManagerCallback p3);
    
    boolean fetchGenreLists(final ManagerCallback p0);
    
    boolean fetchGenreVideos(final LoMo p0, final int p1, final int p2, final ManagerCallback p3);
    
    boolean fetchGenres(final String p0, final int p1, final int p2, final ManagerCallback p3);
    
    boolean fetchIQVideos(final LoMo p0, final int p1, final int p2, final ManagerCallback p3);
    
    boolean fetchKidsCharacterDetails(final String p0, final ManagerCallback p1);
    
    boolean fetchLoLoMoSummary(final String p0, final ManagerCallback p1);
    
    boolean fetchLoMos(final int p0, final int p1, final ManagerCallback p2);
    
    boolean fetchMovieDetails(final String p0, final ManagerCallback p1);
    
    boolean fetchPostPlayVideos(final String p0, final ManagerCallback p1);
    
    boolean fetchSeasonDetails(final String p0, final ManagerCallback p1);
    
    boolean fetchSeasons(final String p0, final int p1, final int p2, final ManagerCallback p3);
    
    boolean fetchShowDetails(final String p0, final String p1, final ManagerCallback p2);
    
    boolean fetchSimilarVideosForPerson(final String p0, final int p1, final ManagerCallback p2);
    
    boolean fetchSimilarVideosForQuerySuggestion(final String p0, final int p1, final ManagerCallback p2, final String p3);
    
    boolean fetchVideos(final LoMo p0, final int p1, final int p2, final ManagerCallback p3);
    
    boolean flushCaches();
    
    boolean hideVideo(final String p0, final ManagerCallback p1);
    
    void logBillboardActivity(final Video p0, final BrowseAgent.BillboardActivityType p1);
    
    boolean prefetchGenreLoLoMo(final String p0, final int p1, final int p2, final int p3, final int p4, final boolean p5, final ManagerCallback p6);
    
    boolean prefetchLoLoMo(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final boolean p6, final boolean p7, final ManagerCallback p8);
    
    boolean removeFromQueue(final String p0, final ManagerCallback p1);
    
    boolean searchNetflix(final String p0, final ManagerCallback p1);
    
    boolean setVideoRating(final String p0, final int p1, final int p2, final ManagerCallback p3);
}
