// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.List;

public interface INetflixServiceCallback
{
    void onCWVideosFetched(final int p0, final List<CWVideo> p1, final int p2);
    
    void onConnectWithFacebookComplete(final int p0, final int p1, final String p2);
    
    void onEpisodeDetailsFetched(final int p0, final EpisodeDetails p1, final int p2);
    
    void onEpisodesFetched(final int p0, final List<EpisodeDetails> p1, final int p2);
    
    void onGenreListsFetched(final int p0, final List<GenreList> p1, final int p2);
    
    void onGenreLoLoMoPrefetched(final int p0, final int p1);
    
    void onGenresFetched(final int p0, final List<Genre> p1, final int p2);
    
    void onLoLoMoPrefetched(final int p0, final int p1);
    
    void onLoLoMoSummaryFetched(final int p0, final LoLoMo p1, final int p2);
    
    void onLoMosFetched(final int p0, final List<LoMo> p1, final int p2);
    
    void onLoginComplete(final int p0, final int p1, final String p2);
    
    void onLogoutComplete(final int p0, final int p1);
    
    void onMovieDetailsFetched(final int p0, final MovieDetails p1, final int p2);
    
    void onQueueAdd(final int p0, final int p1);
    
    void onQueueRemove(final int p0, final int p1);
    
    void onResourceFetched(final int p0, final String p1, final String p2, final int p3);
    
    void onSearchResultsFetched(final int p0, final SearchResults p1, final int p2);
    
    void onSeasonDetailsFetched(final int p0, final SeasonDetails p1, final int p2);
    
    void onSeasonsFetched(final int p0, final List<SeasonDetails> p1, final int p2);
    
    void onServiceReady(final int p0, final int p1);
    
    void onShowDetailsFetched(final int p0, final ShowDetails p1, final int p2);
    
    void onSimilarVideosFetched(final int p0, final VideoList p1, final int p2);
    
    void onVideoHide(final int p0, final int p1);
    
    void onVideoRatingSet(final int p0, final int p1);
    
    void onVideosFetched(final int p0, final List<Video> p1, final int p2);
}
