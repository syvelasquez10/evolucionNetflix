// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.servicemgr.VideoList;
import com.netflix.mediaclient.servicemgr.ShowDetails;
import com.netflix.mediaclient.servicemgr.SeasonDetails;
import com.netflix.mediaclient.servicemgr.SearchResults;
import com.netflix.mediaclient.servicemgr.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.MovieDetails;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.servicemgr.LoLoMo;
import com.netflix.mediaclient.servicemgr.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.Genre;
import com.netflix.mediaclient.servicemgr.GenreList;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.CWVideo;
import java.util.List;
import com.netflix.mediaclient.Log;
import java.util.concurrent.TimeUnit;

public class BrowseAgentCallbackWrapper implements BrowseAgentCallback
{
    private static final String TAG = "TimingBrowseAgentCallback";
    private final BrowseAgentCallback callback;
    private final long start;
    
    public BrowseAgentCallbackWrapper(final BrowseAgentCallback callback) {
        this.callback = callback;
        this.start = System.nanoTime();
    }
    
    private void handleResultTiming(final String s) {
        final long convert = TimeUnit.MILLISECONDS.convert(System.nanoTime() - this.start, TimeUnit.NANOSECONDS);
        if (Log.isLoggable("TimingBrowseAgentCallback", 2)) {
            Log.v("TimingBrowseAgentCallback", String.format("%s took %d ms", s, convert));
        }
    }
    
    private int wrapStatus(final int n) {
        return n;
    }
    
    @Override
    public void onCWListRefresh(final int n) {
        this.handleResultTiming("onCWListRefresh");
        this.callback.onCWListRefresh(this.wrapStatus(n));
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final int n) {
        this.handleResultTiming("onCWVideosFetched");
        this.callback.onCWVideosFetched(list, this.wrapStatus(n));
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final int n) {
        this.handleResultTiming("onEpisodeDetailsFetched");
        this.callback.onEpisodeDetailsFetched(episodeDetails, this.wrapStatus(n));
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final int n) {
        this.handleResultTiming("onEpisodesFetched");
        this.callback.onEpisodesFetched(list, this.wrapStatus(n));
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final int n) {
        this.handleResultTiming("onGenreListsFetched");
        this.callback.onGenreListsFetched(list, this.wrapStatus(n));
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final int n) {
        this.handleResultTiming("onGenreLoLoMoPrefetched");
        this.callback.onGenreLoLoMoPrefetched(this.wrapStatus(n));
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final int n) {
        this.handleResultTiming("onGenresFetched");
        this.callback.onGenresFetched(list, this.wrapStatus(n));
    }
    
    @Override
    public void onIQListRefresh(final int n) {
        this.handleResultTiming("onIQListRefresh");
        this.callback.onIQListRefresh(this.wrapStatus(n));
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final int n) {
        this.handleResultTiming("onKidsCharacterDetailsFetched");
        this.callback.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, this.wrapStatus(n));
    }
    
    @Override
    public void onLoLoMoPrefetched(final int n) {
        this.handleResultTiming("onLoLoMoPrefetched");
        this.callback.onLoLoMoPrefetched(this.wrapStatus(n));
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final int n) {
        this.handleResultTiming("onLoLoMoSummaryFetched");
        this.callback.onLoLoMoSummaryFetched(loLoMo, this.wrapStatus(n));
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final int n) {
        this.handleResultTiming("onLoMosFetched");
        this.callback.onLoMosFetched(list, this.wrapStatus(n));
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final int n) {
        this.handleResultTiming("onMovieDetailsFetched");
        this.callback.onMovieDetailsFetched(movieDetails, this.wrapStatus(n));
    }
    
    @Override
    public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final int n) {
        this.handleResultTiming("onPostPlayVideosFetched");
        this.callback.onPostPlayVideosFetched(list, this.wrapStatus(n));
    }
    
    @Override
    public void onQueueAdd(final int n) {
        this.handleResultTiming("onQueueAdd");
        this.callback.onQueueAdd(this.wrapStatus(n));
    }
    
    @Override
    public void onQueueRemove(final int n) {
        this.handleResultTiming("onQueueRemove");
        this.callback.onQueueRemove(this.wrapStatus(n));
    }
    
    @Override
    public void onSearchResultsFetched(final SearchResults searchResults, final int n) {
        this.handleResultTiming("onSearchResultsFetched");
        this.callback.onSearchResultsFetched(searchResults, this.wrapStatus(n));
    }
    
    @Override
    public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final int n) {
        this.handleResultTiming("onSeasonDetailsFetched");
        this.callback.onSeasonDetailsFetched(seasonDetails, this.wrapStatus(n));
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> list, final int n) {
        this.handleResultTiming("onSeasonsFetched");
        this.callback.onSeasonsFetched(list, this.wrapStatus(n));
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final int n) {
        this.handleResultTiming("onShowDetailsFetched");
        this.callback.onShowDetailsFetched(showDetails, this.wrapStatus(n));
    }
    
    @Override
    public void onSimilarVideosFetched(final VideoList list, final int n) {
        this.handleResultTiming("onSimilarVideosFetched");
        this.callback.onSimilarVideosFetched(list, this.wrapStatus(n));
    }
    
    @Override
    public void onVideoHide(final int n) {
        this.handleResultTiming("onVideoHide");
        this.callback.onVideoHide(this.wrapStatus(n));
    }
    
    @Override
    public void onVideoRatingSet(final int n) {
        this.handleResultTiming("onVideoRatingSet");
        this.callback.onVideoRatingSet(this.wrapStatus(n));
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final int n) {
        this.handleResultTiming("onVideosFetched");
        this.callback.onVideosFetched(list, this.wrapStatus(n));
    }
}
