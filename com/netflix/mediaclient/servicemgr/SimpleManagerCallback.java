// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.List;

public abstract class SimpleManagerCallback implements ManagerCallback
{
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final int n) {
    }
    
    @Override
    public void onConnectWithFacebookComplete(final int n, final String s) {
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final int n) {
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final int n) {
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final int n) {
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final int n) {
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final int n) {
    }
    
    @Override
    public void onLoLoMoPrefetched(final int n) {
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final int n) {
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final int n) {
    }
    
    @Override
    public void onLoginComplete(final int n, final String s) {
    }
    
    @Override
    public void onLogoutComplete(final int n) {
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final int n) {
    }
    
    @Override
    public void onQueueAdd(final int n) {
    }
    
    @Override
    public void onQueueRemove(final int n) {
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final int n) {
    }
    
    @Override
    public void onSearchResultsFetched(final SearchResults searchResults, final int n) {
    }
    
    @Override
    public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final int n) {
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> list, final int n) {
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final int n) {
    }
    
    @Override
    public void onSimilarVideosFetched(final VideoList list, final int n) {
    }
    
    @Override
    public void onVideoHide(final int n) {
    }
    
    @Override
    public void onVideoRatingSet(final int n) {
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final int n) {
    }
}
