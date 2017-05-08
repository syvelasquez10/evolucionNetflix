// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.model.leafs.Video$Summary;
import com.netflix.mediaclient.servicemgr.interface_.UserRating;
import com.netflix.model.survey.Survey;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideoListProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideosProvider;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.LoLoMo;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.InteractiveMoments;
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import com.netflix.model.leafs.advisory.Advisory;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.branches.MementoVideoSwatch;
import com.netflix.model.branches.FalkorPerson;
import java.util.List;

public class LoggingManagerCallback implements ManagerCallback
{
    protected final String tag;
    
    public LoggingManagerCallback(final String tag) {
        this.tag = tag;
    }
    
    @Override
    public void onActorDetailsAndRelatedFetched(final List<FalkorPerson> list, final List<MementoVideoSwatch> list2, final Status status, final List<FalkorActorStill> list3) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            Serializable value;
            if (list == null) {
                value = "null";
            }
            else {
                value = list.size();
            }
            Log.v(tag, String.format("onActorDetailsAndRelatedFetched, name: %s, status: %d", value, status.getStatusCode().getValue()));
            final String tag2 = this.tag;
            Serializable value2;
            if (list2 == null) {
                value2 = "null";
            }
            else {
                value2 = list2.size();
            }
            Log.v(tag2, String.format("onActorDetailsAndRelatedFetched, related: %s items", value2));
        }
    }
    
    @Override
    public void onAdvisoriesFetched(final List<Advisory> list, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onAdvisoriesFetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onAutoLoginTokenCreated(final String s, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onAutoLoginTokenCreated, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onAvailableAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onAvailableAvatarsListFetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onBBVideosFetched, num: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onCWVideosFetched, num: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onConnectWithFacebookComplete(final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onConnectWithFacebookComplete, status: %d, errorMsg: %s", status.getStatusCode().getValue(), status.getMessage()));
        }
    }
    
    @Override
    public void onDiscoveryVideosFetched(final List<Discovery> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onDiscoveryVideosFetched, num: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            String title;
            if (episodeDetails == null) {
                title = "null";
            }
            else {
                title = episodeDetails.getTitle();
            }
            Log.v(tag, String.format("onEpisodeDetailsFetched, title: %s, status: %d", title, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onEpisodesFetched, num: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> list, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onFriendsForRecommendationsListFetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onGenreListsFetched, num: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onGenreLoLoMoPrefetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onGenresFetched, num: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onInteractiveMomentsFetched(final InteractiveMoments interactiveMoments, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onInteractiveMomentsFetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            String title;
            if (kidsCharacterDetails == null) {
                title = "null";
            }
            else {
                title = kidsCharacterDetails.getTitle();
            }
            Log.v(tag, String.format("onKidsCharacterDetailsFetched, title: %s, status: %d", title, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onLoLoMoPrefetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int numLoMos;
            if (loLoMo == null) {
                numLoMos = -1;
            }
            else {
                numLoMos = loLoMo.getNumLoMos();
            }
            Log.v(tag, String.format("onLoLoMoSummaryFetched, num: %d, status: %d", numLoMos, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onLoMosFetched, num: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onLoginComplete(final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onLoginComplete, status: %d, errorMsg: %s", status.getStatusCode().getValue(), status.getMessage()));
        }
    }
    
    @Override
    public void onLogoutComplete(final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onLogoutComplete, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            String title;
            if (movieDetails == null) {
                title = "null";
            }
            else {
                title = movieDetails.getTitle();
            }
            Log.v(tag, String.format("onMovieDetailsFetched, title: %s, status: %d", title, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onNotificationsListFetched(final IrisNotificationsList list, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onIrisNotificationsListFetched, status: %d", status.getStatusCode().getValue()));
            final String tag = this.tag;
            final StringBuilder append = new StringBuilder().append("Notifications count: ");
            Serializable value;
            if (list == null) {
                value = "null";
            }
            else if (list.getSocialNotifications() == null) {
                value = "null";
            }
            else {
                value = list.getSocialNotifications().size();
            }
            Log.v(tag, append.append(value).toString());
        }
    }
    
    @Override
    public void onPersonDetailFetched(final FalkorPerson falkorPerson, final FalkorActorStill falkorActorStill, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            String name;
            if (falkorPerson == null) {
                name = "null";
            }
            else {
                name = falkorPerson.getName();
            }
            Log.v(tag, String.format("onPersonDetailFetched, name: %s, status: %d", name, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onPersonRelatedFetched(final FalkorPerson falkorPerson, final List<Video> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            String name;
            if (falkorPerson == null) {
                name = "null";
            }
            else {
                name = falkorPerson.getName();
            }
            Log.v(tag, String.format("onPersonRelatedFetched, name: %s, status: %d", name, status.getStatusCode().getValue()));
            final String tag2 = this.tag;
            Serializable value;
            if (list == null) {
                value = "null";
            }
            else {
                value = list.size();
            }
            Log.v(tag2, String.format("onPersonRelatedFetched, related: %s items", value));
        }
    }
    
    @Override
    public void onPostPlayImpressionLogged(final boolean b, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onPostPlayImpressionLogged, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onPostPlayVideosFetched(final PostPlayVideosProvider postPlayVideosProvider, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onPostPlayVideosFetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onProfileListUpdateStatus(final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onProfileListUpdateStatus, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onQueueAdd(final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onQueueAdd, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onQueueRemove(final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onQueueRemove, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onResourceCached(final String s, final String s2, final long n, final long n2, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onResourceCached, remoteUrl: %s, localUrl: %s, byteOffset: %l, byteSize: %l, , status: %d", s, s2, n, n2, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onResourceFetched, remoteUrl: %s, status: %d", s, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onResourceRawFetched(final String s, final byte[] array, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onResourceRawFetched, remoteUrl: %s, status: %d", s, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onScenePositionFetched(final int n, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onScenePositionFetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
        int numSections = -1;
        if (Log.isLoggable()) {
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
            Log.v(tag, String.format("onSearchResultsFetched, total results: %d, number of sections: %d, status: %d", numResults, numSections, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            String title;
            if (seasonDetails == null) {
                title = "null";
            }
            else {
                title = seasonDetails.getTitle();
            }
            Log.v(tag, String.format("onSeasonDetailsFetched, title: %s, status: %d", title, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onSeasonsFetched, num: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            String title;
            if (showDetails == null) {
                title = "null";
            }
            else {
                title = showDetails.getTitle();
            }
            Log.v(tag, String.format("onShowDetailsAndSeasonsFetched, title: %s, status: %d", title, status.getStatusCode().getValue()));
            final String tag2 = this.tag;
            Serializable value;
            if (list == null) {
                value = "null";
            }
            else {
                value = list.size();
            }
            Log.v(tag2, String.format("onShowDetailsAndSeasonsFetched, seasons: %s items", value));
        }
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            String title;
            if (showDetails == null) {
                title = "null";
            }
            else {
                title = showDetails.getTitle();
            }
            Log.v(tag, String.format("onShowDetailsFetched, title: %s, status: %d", title, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onSimilarVideosFetched(final SearchVideoListProvider searchVideoListProvider, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int size;
            if (searchVideoListProvider == null) {
                size = -1;
            }
            else {
                size = searchVideoListProvider.getVideosList().size();
            }
            Log.v(tag, String.format("onSimilarVideosFetched, num videos: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onSocialNotificationWasThanked(final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onSocialNotificationWasThanked, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onSurveyFetched(final Survey survey, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onSurveyFetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onVerified(final boolean b, final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onVerified, status: %d, isValid: %s", status.getStatusCode().getValue(), b));
        }
    }
    
    @Override
    public void onVideoHide(final Status status) {
        if (Log.isLoggable()) {
            Log.v(this.tag, String.format("onVideoUnshared, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onVideoRatingSet(final UserRating userRating, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            float userRating2;
            if (userRating == null) {
                userRating2 = -1.0f;
            }
            else {
                userRating2 = userRating.getUserRating();
            }
            Log.v(tag, String.format("onVideoRatingSet, rating: %f, status: %d", userRating2, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onVideoSummaryFetched(final Video$Summary video$Summary, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            String title;
            if (video$Summary == null) {
                title = "null";
            }
            else {
                title = video$Summary.getTitle();
            }
            Log.v(tag, String.format("onVideoSummaryFetched, title: %s, status: %d", title, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        if (Log.isLoggable()) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onVideosFetched, num: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
}
