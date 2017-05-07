// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.servicemgr.VideoList;
import com.netflix.mediaclient.servicemgr.ShowDetails;
import com.netflix.mediaclient.servicemgr.SeasonDetails;
import com.netflix.mediaclient.servicemgr.SearchResults;
import com.netflix.mediaclient.servicemgr.MovieDetails;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.servicemgr.LoLoMo;
import com.netflix.mediaclient.servicemgr.Genre;
import com.netflix.mediaclient.servicemgr.GenreList;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.CWVideo;
import java.util.List;

public interface BrowseAgentCallback
{
    void onCWListRefresh(final int p0);
    
    void onCWVideosFetched(final List<CWVideo> p0, final int p1);
    
    void onEpisodeDetailsFetched(final EpisodeDetails p0, final int p1);
    
    void onEpisodesFetched(final List<EpisodeDetails> p0, final int p1);
    
    void onGenreListsFetched(final List<GenreList> p0, final int p1);
    
    void onGenreLoLoMoPrefetched(final int p0);
    
    void onGenresFetched(final List<Genre> p0, final int p1);
    
    void onIQListRefresh(final int p0);
    
    void onLoLoMoPrefetched(final int p0);
    
    void onLoLoMoSummaryFetched(final LoLoMo p0, final int p1);
    
    void onLoMosFetched(final List<LoMo> p0, final int p1);
    
    void onMovieDetailsFetched(final MovieDetails p0, final int p1);
    
    void onQueueAdd(final int p0);
    
    void onQueueRemove(final int p0);
    
    void onSearchResultsFetched(final SearchResults p0, final int p1);
    
    void onSeasonDetailsFetched(final SeasonDetails p0, final int p1);
    
    void onSeasonsFetched(final List<SeasonDetails> p0, final int p1);
    
    void onShowDetailsFetched(final ShowDetails p0, final int p1);
    
    void onSimilarVideosFetched(final VideoList p0, final int p1);
    
    void onVideoHide(final int p0);
    
    void onVideoRatingSet(final int p0);
    
    void onVideosFetched(final List<Video> p0, final int p1);
}
