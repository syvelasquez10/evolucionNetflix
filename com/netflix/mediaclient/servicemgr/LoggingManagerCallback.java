// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.SearchVideoList;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;

public class LoggingManagerCallback implements ManagerCallback
{
    protected final String tag;
    
    public LoggingManagerCallback(final String tag) {
        this.tag = tag;
    }
    
    @Override
    public void onAvailableAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onAvailableAvatarsListFetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
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
        if (Log.isLoggable(this.tag, 2)) {
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
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onConnectWithFacebookComplete, status: %d, errorMsg: %s", status.getStatusCode().getValue(), status.getMessage()));
        }
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
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
        if (Log.isLoggable(this.tag, 2)) {
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
    public void onGenreListsFetched(final List<GenreList> list, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
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
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onGenreLoLoMoPrefetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
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
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
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
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onLoLoMoPrefetched, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onLoLoMoSummaryFetched, num: %d, status: %d", loLoMo.getNumLoMos(), status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
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
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onLoginComplete, status: %d, errorMsg: %s", status.getStatusCode().getValue(), status.getMessage()));
        }
    }
    
    @Override
    public void onLogoutComplete(final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onLogoutComplete, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
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
    public void onPinVerified(final boolean b, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onPinVerified, status: %d, isPinValid: %s", status.getStatusCode().getValue(), b));
        }
    }
    
    @Override
    public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onPostPlayVideosFetched, num: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onProfileListUpdateStatus(final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onProfileListUpdateStatus, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onQueueAdd(final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onQueueAdd, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onQueueRemove(final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onQueueRemove, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onResourceFetched, remoteUrl: %s, status: %d", s, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
        int numSections = -1;
        if (Log.isLoggable(this.tag, 2)) {
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
        if (Log.isLoggable(this.tag, 2)) {
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
        if (Log.isLoggable(this.tag, 2)) {
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
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
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
    public void onSimilarVideosFetched(final SearchVideoList list, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            final String tag = this.tag;
            int size;
            if (list == null) {
                size = -1;
            }
            else {
                size = list.size();
            }
            Log.v(tag, String.format("onSimilarVideosFetched, num videos: %d, status: %d", size, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onVideoHide(final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onVideoUnshared, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onVideoRatingSet(final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
            Log.v(this.tag, String.format("onVideoRatingSet, status: %d", status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        if (Log.isLoggable(this.tag, 2)) {
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
