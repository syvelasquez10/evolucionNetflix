// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.List;

public interface ManagerCallback
{
    void onCWVideosFetched(final List<CWVideo> p0, final int p1);
    
    void onConnectWithFacebookComplete(final int p0, final String p1);
    
    void onEpisodeDetailsFetched(final EpisodeDetails p0, final int p1);
    
    void onEpisodesFetched(final List<EpisodeDetails> p0, final int p1);
    
    void onGenreListsFetched(final List<GenreList> p0, final int p1);
    
    void onGenreLoLoMoPrefetched(final int p0);
    
    void onGenresFetched(final List<Genre> p0, final int p1);
    
    void onLoLoMoPrefetched(final int p0);
    
    void onLoLoMoSummaryFetched(final LoLoMo p0, final int p1);
    
    void onLoMosFetched(final List<LoMo> p0, final int p1);
    
    void onLoginComplete(final int p0, final String p1);
    
    void onLogoutComplete(final int p0);
    
    void onMovieDetailsFetched(final MovieDetails p0, final int p1);
    
    void onPostPlayVideosFetched(final List<PostPlayVideo> p0, final int p1);
    
    void onQueueAdd(final int p0);
    
    void onQueueRemove(final int p0);
    
    void onResourceFetched(final String p0, final String p1, final int p2);
    
    void onSearchResultsFetched(final SearchResults p0, final int p1);
    
    void onSeasonDetailsFetched(final SeasonDetails p0, final int p1);
    
    void onSeasonsFetched(final List<SeasonDetails> p0, final int p1);
    
    void onShowDetailsFetched(final ShowDetails p0, final int p1);
    
    void onSimilarVideosFetched(final VideoList p0, final int p1);
    
    void onVideoHide(final int p0);
    
    void onVideoRatingSet(final int p0);
    
    void onVideosFetched(final List<Video> p0, final int p1);
}
