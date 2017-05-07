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
import com.netflix.mediaclient.servicemgr.Genre;
import com.netflix.mediaclient.servicemgr.GenreList;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.CWVideo;
import java.util.List;

public abstract class SimpleBrowseAgentCallback implements BrowseAgentCallback
{
    @Override
    public void onCWListRefresh(final int n) {
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final int n) {
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
    public void onIQListRefresh(final int n) {
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
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final int n) {
    }
    
    @Override
    public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final int n) {
    }
    
    @Override
    public void onQueueAdd(final int n) {
    }
    
    @Override
    public void onQueueRemove(final int n) {
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
