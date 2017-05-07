// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.ProfileType;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.servicemgr.LoMo;

public interface BrowseWebClient
{
    void addToQueue(final String p0, final int p1, final int p2, final int p3, final boolean p4, final BrowseAgentCallback p5);
    
    void fetchCWVideos(final int p0, final int p1, final BrowseAgentCallback p2);
    
    void fetchEpisodeDetails(final String p0, final BrowseAgentCallback p1);
    
    void fetchEpisodes(final String p0, final int p1, final int p2, final BrowseAgentCallback p3);
    
    void fetchGenreLists(final BrowseAgentCallback p0);
    
    void fetchGenreVideos(final LoMo p0, final int p1, final int p2, final BrowseAgentCallback p3);
    
    void fetchGenres(final String p0, final int p1, final int p2, final BrowseAgentCallback p3);
    
    void fetchIQVideos(final int p0, final int p1, final BrowseAgentCallback p2);
    
    void fetchKidsCharacterDetails(final String p0, final BrowseAgentCallback p1);
    
    void fetchLoLoMoSummary(final String p0, final BrowseAgentCallback p1);
    
    void fetchLoMos(final String p0, final int p1, final int p2, final BrowseAgentCallback p3);
    
    void fetchMovieDetails(final String p0, final BrowseAgentCallback p1);
    
    void fetchPostPlayVideos(final String p0, final BrowseAgentCallback p1);
    
    void fetchSeasonDetails(final String p0, final BrowseAgentCallback p1);
    
    void fetchSeasons(final String p0, final int p1, final int p2, final BrowseAgentCallback p3);
    
    void fetchShowDetails(final String p0, final String p1, final BrowseAgentCallback p2);
    
    void fetchSimilarVideosForPerson(final String p0, final int p1, final BrowseAgentCallback p2);
    
    void fetchSimilarVideosForQuerySuggestion(final String p0, final int p1, final BrowseAgentCallback p2);
    
    void fetchVideos(final LoMo p0, final int p1, final int p2, final BrowseAgentCallback p3);
    
    void hideVideo(final String p0, final BrowseAgentCallback p1);
    
    boolean isSynchronous();
    
    void logBillboardActivity(final Video p0, final BrowseAgent.BillboardActivityType p1);
    
    void prefetchGenreLoLoMo(final String p0, final int p1, final int p2, final int p3, final int p4, final BrowseAgentCallback p5);
    
    void prefetchLoLoMo(final String p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final boolean p7, final BrowseAgentCallback p8);
    
    void refreshCWList(final int p0, final int p1, final BrowseAgentCallback p2);
    
    void refreshIQList(final int p0, final int p1, final BrowseAgentCallback p2);
    
    void refreshKidsCharacterDetils(final String p0, final BrowseAgentCallback p1);
    
    void removeFromQueue(final String p0, final int p1, final int p2, final boolean p3, final BrowseAgentCallback p4);
    
    void searchNetflix(final String p0, final ProfileType p1, final BrowseAgentCallback p2);
    
    void setVideoRating(final String p0, final int p1, final int p2, final BrowseAgentCallback p3);
}
