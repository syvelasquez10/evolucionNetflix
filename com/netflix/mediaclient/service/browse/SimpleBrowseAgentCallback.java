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
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;

public abstract class SimpleBrowseAgentCallback implements BrowseAgentCallback
{
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final Status status) {
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final Status status) {
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final Status status) {
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final Status status) {
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
    }
    
    @Override
    public void onPostPlayVideosFetched(final PostPlayVideosProvider postPlayVideosProvider, final Status status) {
    }
    
    @Override
    public void onQueueAdd(final Status status) {
    }
    
    @Override
    public void onQueueRemove(final Status status) {
    }
    
    @Override
    public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
    }
    
    @Override
    public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final Status status) {
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
    }
    
    @Override
    public void onSimilarVideosFetched(final SearchVideoListProvider searchVideoListProvider, final Status status) {
    }
    
    @Override
    public void onSocialNotificationWasThanked(final Status status) {
    }
    
    @Override
    public void onSocialNotificationsListFetched(final SocialNotificationsList list, final Status status) {
    }
    
    @Override
    public void onSocialNotificationsMarkedAsRead(final Status status) {
    }
    
    @Override
    public void onVideoHide(final Status status) {
    }
    
    @Override
    public void onVideoRatingSet(final UserRating userRating, final Status status) {
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
    }
}
