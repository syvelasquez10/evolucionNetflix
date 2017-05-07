// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.Log;
import java.util.List;

public class LoggingManagerCallback implements ManagerCallback
{
    protected final String tag;
    
    public LoggingManagerCallback(final String tag) {
        this.tag = tag;
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final int n) {
        final String tag = this.tag;
        int size;
        if (list == null) {
            size = -1;
        }
        else {
            size = list.size();
        }
        Log.v(tag, String.format("onCWVideosFetched, num: %d, status: %d", size, n));
    }
    
    @Override
    public void onConnectWithFacebookComplete(final int n, final String s) {
        Log.v(this.tag, String.format("onConnectWithFacebookComplete, status: %d, errorMsg: %s", n, s));
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final int n) {
        final String tag = this.tag;
        String title;
        if (episodeDetails == null) {
            title = "null";
        }
        else {
            title = episodeDetails.getTitle();
        }
        Log.v(tag, String.format("onEpisodeDetailsFetched, title: %s, status: %d", title, n));
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final int n) {
        final String tag = this.tag;
        int size;
        if (list == null) {
            size = -1;
        }
        else {
            size = list.size();
        }
        Log.v(tag, String.format("onEpisodesFetched, num: %d, status: %d", size, n));
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final int n) {
        final String tag = this.tag;
        int size;
        if (list == null) {
            size = -1;
        }
        else {
            size = list.size();
        }
        Log.v(tag, String.format("onGenreListsFetched, num: %d, status: %d", size, n));
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final int n) {
        Log.v(this.tag, String.format("onGenreLoLoMoPrefetched, status: %d", n));
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final int n) {
        final String tag = this.tag;
        int size;
        if (list == null) {
            size = -1;
        }
        else {
            size = list.size();
        }
        Log.v(tag, String.format("onGenresFetched, num: %d, status: %d", size, n));
    }
    
    @Override
    public void onLoLoMoPrefetched(final int n) {
        Log.v(this.tag, String.format("onLoLoMoPrefetched, status: %d", n));
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final int n) {
        Log.v(this.tag, String.format("onLoLoMoSummaryFetched, num: %d, status: %d", loLoMo.getNumLoMos(), n));
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final int n) {
        final String tag = this.tag;
        int size;
        if (list == null) {
            size = -1;
        }
        else {
            size = list.size();
        }
        Log.v(tag, String.format("onLoMosFetched, num: %d, status: %d", size, n));
    }
    
    @Override
    public void onLoginComplete(final int n, final String s) {
        Log.v(this.tag, String.format("onLoginComplete, status: %d, errorMsg: %s", n, s));
    }
    
    @Override
    public void onLogoutComplete(final int n) {
        Log.v(this.tag, String.format("onLogoutComplete, status: %d", n));
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final int n) {
        final String tag = this.tag;
        String title;
        if (movieDetails == null) {
            title = "null";
        }
        else {
            title = movieDetails.getTitle();
        }
        Log.v(tag, String.format("onMovieDetailsFetched, title: %s, status: %d", title, n));
    }
    
    @Override
    public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final int n) {
        final String tag = this.tag;
        int size;
        if (list == null) {
            size = -1;
        }
        else {
            size = list.size();
        }
        Log.v(tag, String.format("onPostPlayVideosFetched, num: %d, status: %d", size, n));
    }
    
    @Override
    public void onQueueAdd(final int n) {
        Log.v(this.tag, String.format("onQueueAdd, status: %d", n));
    }
    
    @Override
    public void onQueueRemove(final int n) {
        Log.v(this.tag, String.format("onQueueRemove, status: %d", n));
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final int n) {
        Log.v(this.tag, String.format("onResourceFetched, remoteUrl: %s, status: %d", s, n));
    }
    
    @Override
    public void onSearchResultsFetched(final SearchResults searchResults, final int n) {
        int numSections = -1;
        final String tag = this.tag;
        int numResults;
        if (searchResults == null) {
            numResults = -1;
        }
        else {
            numResults = searchResults.getNumResults();
        }
        if (searchResults != null) {
            numSections = searchResults.getNumSections();
        }
        Log.v(tag, String.format("onSearchResultsFetched, total results: %d, number of sections: %d, status: %d", numResults, numSections, n));
    }
    
    @Override
    public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final int n) {
        final String tag = this.tag;
        String title;
        if (seasonDetails == null) {
            title = "null";
        }
        else {
            title = seasonDetails.getTitle();
        }
        Log.v(tag, String.format("onSeasonDetailsFetched, title: %s, status: %d", title, n));
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> list, final int n) {
        final String tag = this.tag;
        int size;
        if (list == null) {
            size = -1;
        }
        else {
            size = list.size();
        }
        Log.v(tag, String.format("onSeasonsFetched, num: %d, status: %d", size, n));
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final int n) {
        final String tag = this.tag;
        String title;
        if (showDetails == null) {
            title = "null";
        }
        else {
            title = showDetails.getTitle();
        }
        Log.v(tag, String.format("onShowDetailsFetched, title: %s, status: %d", title, n));
    }
    
    @Override
    public void onSimilarVideosFetched(final VideoList list, final int n) {
        final String tag = this.tag;
        int size;
        if (list == null) {
            size = -1;
        }
        else {
            size = list.size();
        }
        Log.v(tag, String.format("onSimilarVideosFetched, num videos: %d, status: %d", size, n));
    }
    
    @Override
    public void onVideoHide(final int n) {
        Log.v(this.tag, String.format("onVideoUnshared, status: %d", n));
    }
    
    @Override
    public void onVideoRatingSet(final int n) {
        Log.v(this.tag, String.format("onVideoRatingSet, status: %d", n));
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final int n) {
        final String tag = this.tag;
        int size;
        if (list == null) {
            size = -1;
        }
        else {
            size = list.size();
        }
        Log.v(tag, String.format("onVideosFetched, num: %d, status: %d", size, n));
    }
}
