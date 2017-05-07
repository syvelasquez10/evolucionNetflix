// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import android.support.v4.util.Pair;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideoListProvider;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.model.UserRating;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideosProvider;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class FalkorValidationActivity$ObjectNotifierCallback extends LoggingManagerCallback
{
    public List<Billboard> bbVideos;
    public List<CWVideo> cwVideos;
    public VideoDetails details;
    public EpisodeDetails episodeDetails;
    public List<EpisodeDetails> episodes;
    public List<Genre> genres;
    public KidsCharacterDetails kidsCharacterDetails;
    public List<GenreList> listofGenres;
    public List<LoMo> lomos;
    private final Object objectToNotify;
    public PostPlayVideosProvider postPlayVideos;
    public UserRating rating;
    public ISearchResults searchResults;
    public SearchVideoListProvider searchVideoList;
    public List<SeasonDetails> seasons;
    public Pair<ShowDetails, List<SeasonDetails>> showAndSeasons;
    public Status status;
    public LoLoMo summary;
    public List<Video> videos;
    
    public FalkorValidationActivity$ObjectNotifierCallback(final Object objectToNotify) {
        super("FalkorValidationActivity");
        this.objectToNotify = objectToNotify;
    }
    
    private void notifyCaller() {
        synchronized (this.objectToNotify) {
            this.objectToNotify.notify();
        }
    }
    
    @Override
    public void onBBVideosFetched(final List<Billboard> bbVideos, final Status status) {
        super.onBBVideosFetched(bbVideos, status);
        this.bbVideos = bbVideos;
        this.notifyCaller();
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> cwVideos, final Status status) {
        super.onCWVideosFetched(cwVideos, status);
        this.cwVideos = cwVideos;
        this.notifyCaller();
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        super.onEpisodeDetailsFetched(episodeDetails, status);
        this.episodeDetails = episodeDetails;
        this.notifyCaller();
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> episodes, final Status status) {
        super.onEpisodesFetched(episodes, status);
        this.episodes = episodes;
        this.notifyCaller();
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> listofGenres, final Status status) {
        super.onGenreListsFetched(listofGenres, status);
        this.listofGenres = listofGenres;
        this.notifyCaller();
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final Status status) {
        super.onLoLoMoPrefetched(status);
        this.notifyCaller();
    }
    
    @Override
    public void onGenresFetched(final List<Genre> genres, final Status status) {
        super.onGenresFetched(genres, status);
        this.genres = genres;
        this.notifyCaller();
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        super.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, status);
        this.kidsCharacterDetails = kidsCharacterDetails;
        this.notifyCaller();
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        super.onLoLoMoPrefetched(status);
        this.notifyCaller();
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo summary, final Status status) {
        super.onLoLoMoSummaryFetched(summary, status);
        this.summary = summary;
        this.notifyCaller();
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> lomos, final Status status) {
        super.onLoMosFetched(lomos, status);
        this.lomos = lomos;
        this.notifyCaller();
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails details, final Status status) {
        super.onMovieDetailsFetched(details, status);
        this.details = details;
        this.notifyCaller();
    }
    
    @Override
    public void onPostPlayVideosFetched(final PostPlayVideosProvider postPlayVideos, final Status status) {
        super.onPostPlayVideosFetched(postPlayVideos, status);
        this.postPlayVideos = postPlayVideos;
        this.notifyCaller();
    }
    
    @Override
    public void onQueueAdd(final Status status) {
        super.onQueueAdd(status);
        this.status = status;
        this.notifyCaller();
    }
    
    @Override
    public void onQueueRemove(final Status status) {
        super.onQueueRemove(status);
        this.status = status;
        this.notifyCaller();
    }
    
    @Override
    public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
        super.onSearchResultsFetched(searchResults, status);
        this.searchResults = searchResults;
        this.notifyCaller();
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> seasons, final Status status) {
        super.onSeasonsFetched(seasons, status);
        this.seasons = seasons;
        this.notifyCaller();
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
        super.onShowDetailsAndSeasonsFetched(showDetails, list, status);
        this.showAndSeasons = new Pair<ShowDetails, List<SeasonDetails>>(showDetails, list);
        this.notifyCaller();
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails details, final Status status) {
        super.onShowDetailsFetched(details, status);
        this.details = details;
        this.notifyCaller();
    }
    
    @Override
    public void onSimilarVideosFetched(final SearchVideoListProvider searchVideoList, final Status status) {
        super.onSimilarVideosFetched(searchVideoList, status);
        this.searchVideoList = searchVideoList;
        this.notifyCaller();
    }
    
    @Override
    public void onVideoRatingSet(final UserRating rating, final Status status) {
        super.onVideoRatingSet(rating, status);
        this.rating = rating;
        this.notifyCaller();
    }
    
    @Override
    public void onVideosFetched(final List<Video> videos, final Status status) {
        super.onVideosFetched(videos, status);
        this.videos = videos;
        this.notifyCaller();
    }
}
