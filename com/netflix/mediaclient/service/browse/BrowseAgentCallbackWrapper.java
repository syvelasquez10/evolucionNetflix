// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.mediaclient.servicemgr.interface_.UserRating;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideoListProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideosProvider;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.LoLoMo;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.InteractiveMoments;
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.servicemgr.interface_.ExpiringContentAction;
import com.netflix.mediaclient.servicemgr.interface_.IExpiringContentWarning;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
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
        if (Log.isLoggable()) {
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
    public void onBrowsePlaySessionEnd(final boolean b, final Status status) {
        this.handleResultTiming("onBrowsePlaySessionEnd");
        this.callback.onBrowsePlaySessionEnd(b, this.wrapStatus(status));
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
    public void onExpiringContentWarning(final IExpiringContentWarning expiringContentWarning, final Status status, final ExpiringContentAction expiringContentAction) {
        this.handleResultTiming("onExpiringContentWarning");
        this.callback.onExpiringContentWarning(expiringContentWarning, this.wrapStatus(status), expiringContentAction);
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
    public void onInteractiveMomentsFetched(final InteractiveMoments interactiveMoments, final Status status) {
        this.handleResultTiming("onInteractiveMomentsFetched");
        this.callback.onInteractiveMomentsFetched(interactiveMoments, this.wrapStatus(status));
    }
    
    @Override
    public void onIrisNotificationsMarkedAsRead(final Status status) {
        this.handleResultTiming("onIrisNotificationsMarkedAsRead");
        this.callback.onIrisNotificationsMarkedAsRead(this.wrapStatus(status));
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
    public void onNotificationsListFetched(final IrisNotificationsList list, final Status status) {
        this.handleResultTiming("onIrisNotificationsListFetched");
        this.callback.onNotificationsListFetched(list, this.wrapStatus(status));
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
    public void onScenePositionFetched(final int n, final Status status) {
        this.handleResultTiming("onScenePositionFetched");
        this.callback.onScenePositionFetched(n, this.wrapStatus(status));
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
    public void onVideoSummaryFetched(final Video$Summary video$Summary, final Status status) {
        this.handleResultTiming("onVideoSummaryFetched");
        this.callback.onVideoSummaryFetched(video$Summary, this.wrapStatus(status));
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        this.handleResultTiming("onVideosFetched");
        this.callback.onVideosFetched(list, this.wrapStatus(status));
    }
}
