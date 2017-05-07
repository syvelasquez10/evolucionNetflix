// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.UserRating;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideoListProvider;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideosProvider;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
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
    
    private Status wrapStatus(final Status status) {
        return status;
    }
    
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
        this.handleResultTiming("onBBVideosFetched");
        this.callback.onBBVideosFetched(list, this.wrapStatus(status));
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
        this.handleResultTiming("onCWVideosFetched");
        this.callback.onCWVideosFetched(list, this.wrapStatus(status));
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        this.handleResultTiming("onEpisodeDetailsFetched");
        this.callback.onEpisodeDetailsFetched(episodeDetails, this.wrapStatus(status));
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        this.handleResultTiming("onEpisodesFetched");
        this.callback.onEpisodesFetched(list, this.wrapStatus(status));
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final Status status) {
        this.handleResultTiming("onGenreListsFetched");
        this.callback.onGenreListsFetched(list, this.wrapStatus(status));
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final Status status) {
        this.handleResultTiming("onGenreLoLoMoPrefetched");
        this.callback.onGenreLoLoMoPrefetched(this.wrapStatus(status));
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final Status status) {
        this.handleResultTiming("onGenresFetched");
        this.callback.onGenresFetched(list, this.wrapStatus(status));
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        this.handleResultTiming("onKidsCharacterDetailsFetched");
        this.callback.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, this.wrapStatus(status));
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        this.handleResultTiming("onLoLoMoPrefetched");
        this.callback.onLoLoMoPrefetched(this.wrapStatus(status));
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final Status status) {
        this.handleResultTiming("onLoLoMoSummaryFetched");
        this.callback.onLoLoMoSummaryFetched(loLoMo, this.wrapStatus(status));
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        this.handleResultTiming("onLoMosFetched");
        this.callback.onLoMosFetched(list, this.wrapStatus(status));
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        this.handleResultTiming("onMovieDetailsFetched");
        this.callback.onMovieDetailsFetched(movieDetails, this.wrapStatus(status));
    }
    
    @Override
    public void onPostPlayVideosFetched(final PostPlayVideosProvider postPlayVideosProvider, final Status status) {
        this.handleResultTiming("onPostPlayVideosFetched");
        this.callback.onPostPlayVideosFetched(postPlayVideosProvider, this.wrapStatus(status));
    }
    
    @Override
    public void onQueueAdd(final Status status) {
        this.handleResultTiming("onQueueAdd");
        this.callback.onQueueAdd(this.wrapStatus(status));
    }
    
    @Override
    public void onQueueRemove(final Status status) {
        this.handleResultTiming("onQueueRemove");
        this.callback.onQueueRemove(this.wrapStatus(status));
    }
    
    @Override
    public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
        this.handleResultTiming("onSearchResultsFetched");
        this.callback.onSearchResultsFetched(searchResults, this.wrapStatus(status));
    }
    
    @Override
    public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final Status status) {
        this.handleResultTiming("onSeasonDetailsFetched");
        this.callback.onSeasonDetailsFetched(seasonDetails, this.wrapStatus(status));
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
        this.handleResultTiming("onSeasonsFetched");
        this.callback.onSeasonsFetched(list, this.wrapStatus(status));
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
        this.handleResultTiming("onShowDetailsAndSeasonsFetched");
        this.callback.onShowDetailsAndSeasonsFetched(showDetails, list, this.wrapStatus(status));
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        this.handleResultTiming("onShowDetailsFetched");
        this.callback.onShowDetailsFetched(showDetails, this.wrapStatus(status));
    }
    
    @Override
    public void onSimilarVideosFetched(final SearchVideoListProvider searchVideoListProvider, final Status status) {
        this.handleResultTiming("onSimilarVideosFetched");
        this.callback.onSimilarVideosFetched(searchVideoListProvider, this.wrapStatus(status));
    }
    
    @Override
    public void onSocialNotificationWasThanked(final Status status) {
        this.handleResultTiming("onSocialNotificationWasThanked");
        this.callback.onSocialNotificationWasThanked(this.wrapStatus(status));
    }
    
    @Override
    public void onSocialNotificationsListFetched(final SocialNotificationsList list, final Status status) {
        this.handleResultTiming("onSocialNotificationsListFetched");
        this.callback.onSocialNotificationsListFetched(list, this.wrapStatus(status));
    }
    
    @Override
    public void onSocialNotificationsMarkedAsRead(final Status status) {
        this.handleResultTiming("onSocialNotificationsMarkedAsRead");
        this.callback.onSocialNotificationsMarkedAsRead(this.wrapStatus(status));
    }
    
    @Override
    public void onVideoHide(final Status status) {
        this.handleResultTiming("onVideoHide");
        this.callback.onVideoHide(this.wrapStatus(status));
    }
    
    @Override
    public void onVideoRatingSet(final UserRating userRating, final Status status) {
        this.handleResultTiming("onVideoRatingSet");
        this.callback.onVideoRatingSet(userRating, this.wrapStatus(status));
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        this.handleResultTiming("onVideosFetched");
        this.callback.onVideosFetched(list, this.wrapStatus(status));
    }
}
