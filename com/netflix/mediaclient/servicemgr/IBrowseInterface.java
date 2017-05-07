// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMo;

public interface IBrowseInterface
{
    void addToQueue(final String p0, final int p1, final String p2, final int p3, final int p4);
    
    void dumpHomeLoLoMosAndVideos(final String p0, final String p1);
    
    void dumpHomeLoLoMosAndVideosToLog();
    
    void dumpHomeLoMos();
    
    void fetchCWVideos(final int p0, final int p1, final int p2, final int p3);
    
    void fetchEpisodeDetails(final String p0, final int p1, final int p2);
    
    void fetchEpisodes(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchGenreLists(final int p0, final int p1);
    
    void fetchGenreVideos(final LoMo p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchGenres(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchIQVideos(final LoMo p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchKidsCharacterDetails(final String p0, final int p1, final int p2);
    
    void fetchLoLoMoSummary(final String p0, final int p1, final int p2);
    
    void fetchLoMos(final int p0, final int p1, final int p2, final int p3);
    
    void fetchMovieDetails(final String p0, final int p1, final int p2);
    
    void fetchPostPlayVideos(final String p0, final int p1, final int p2);
    
    void fetchSeasonDetails(final String p0, final int p1, final int p2);
    
    void fetchSeasons(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    void fetchShowDetails(final String p0, final String p1, final int p2, final int p3);
    
    void fetchSimilarVideosForPerson(final String p0, final int p1, final int p2, final int p3, final String p4);
    
    void fetchSimilarVideosForQuerySuggestion(final String p0, final int p1, final int p2, final int p3, final String p4);
    
    void fetchVideos(final LoMo p0, final int p1, final int p2, final int p3, final int p4);
    
    void flushCaches();
    
    void hideVideo(final String p0, final int p1, final int p2);
    
    void logBillboardActivity(final Video p0, final BrowseAgent.BillboardActivityType p1);
    
    void prefetchGenreLoLoMo(final String p0, final int p1, final int p2, final int p3, final int p4, final boolean p5, final int p6, final int p7);
    
    void prefetchLoLoMo(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final boolean p6, final boolean p7, final int p8, final int p9);
    
    void refreshCW();
    
    void refreshIQ();
    
    void removeFromQueue(final String p0, final String p1, final int p2, final int p3);
    
    void searchNetflix(final String p0, final int p1, final int p2);
    
    void setVideoRating(final String p0, final int p1, final int p2, final int p3, final int p4);
}
