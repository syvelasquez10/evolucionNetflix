// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.browse.BrowseAgentCallbackWrapper;
import com.netflix.mediaclient.service.NetflixService$ClientCallbacks;
import com.netflix.mediaclient.servicemgr.IBrowseInterface;
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
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.INetflixServiceCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;

class FalkorAccess$BrowseAgentClientCallback implements BrowseAgentCallback
{
    private final int clientId;
    private final int requestId;
    final /* synthetic */ FalkorAccess this$0;
    
    FalkorAccess$BrowseAgentClientCallback(final FalkorAccess this$0, final int clientId, final int requestId) {
        this.this$0 = this$0;
        this.clientId = clientId;
        this.requestId = requestId;
    }
    
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for oBBVideosFetched");
            return;
        }
        netflixServiceCallback.onBBVideosFetched(this.requestId, list, status);
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onCWVideosFetched");
            return;
        }
        netflixServiceCallback.onCWVideosFetched(this.requestId, list, status);
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onEpisodeDetailsFetched");
            return;
        }
        netflixServiceCallback.onEpisodeDetailsFetched(this.requestId, episodeDetails, status);
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onEpisodesFetched");
            return;
        }
        netflixServiceCallback.onEpisodesFetched(this.requestId, list, status);
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onGenreListsFetched");
            return;
        }
        netflixServiceCallback.onGenreListsFetched(this.requestId, list, status);
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for client onGenreLoLoMoPrefetched");
            return;
        }
        netflixServiceCallback.onGenreLoLoMoPrefetched(this.requestId, status);
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onGenresFetched");
            return;
        }
        netflixServiceCallback.onGenresFetched(this.requestId, list, status);
    }
    
    @Override
    public void onIrisNotificationsMarkedAsRead(final Status status) {
        if (Log.isLoggable()) {
            Log.i("FalkorAccess", "onIrisNotificationsMarkedAsRead: " + status);
        }
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onKidsCharacterDetailsFetched");
            return;
        }
        netflixServiceCallback.onKidsCharacterDetailsFetched(this.requestId, kidsCharacterDetails, b, status);
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for client onLoLoMoPrefetched");
            return;
        }
        netflixServiceCallback.onLoLoMoPrefetched(this.requestId, status);
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onLoLoMoSummaryFetched");
            return;
        }
        netflixServiceCallback.onLoLoMoSummaryFetched(this.requestId, loLoMo, status);
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onLoMosFetched");
            return;
        }
        netflixServiceCallback.onLoMosFetched(this.requestId, list, status);
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onMovieDetailsFetched");
            return;
        }
        netflixServiceCallback.onMovieDetailsFetched(this.requestId, movieDetails, status);
    }
    
    @Override
    public void onNotificationsListFetched(final IrisNotificationsList list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onIrisNotificationsListFetched");
            return;
        }
        netflixServiceCallback.onIrisNotificationsListFetched(this.requestId, list, status);
    }
    
    @Override
    public void onPostPlayVideosFetched(final PostPlayVideosProvider postPlayVideosProvider, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onPostPlayVideosFetched");
            return;
        }
        netflixServiceCallback.onPostPlayVideosFetched(this.requestId, postPlayVideosProvider, status);
    }
    
    @Override
    public void onQueueAdd(final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onQueueAdd");
            return;
        }
        netflixServiceCallback.onQueueAdd(this.requestId, status);
    }
    
    @Override
    public void onQueueRemove(final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onQueueRemove");
            return;
        }
        netflixServiceCallback.onQueueRemove(this.requestId, status);
    }
    
    @Override
    public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onSearchResultsFetched");
            return;
        }
        netflixServiceCallback.onSearchResultsFetched(this.requestId, searchResults, status);
    }
    
    @Override
    public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onSeasonDetailsFetched");
            return;
        }
        netflixServiceCallback.onSeasonDetailsFetched(this.requestId, seasonDetails, status);
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onSeasonsFetched");
            return;
        }
        netflixServiceCallback.onSeasonsFetched(this.requestId, list, status);
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onShowDetailsAndSeasonsFetched");
            return;
        }
        netflixServiceCallback.onShowDetailsAndSeasonsFetched(this.requestId, showDetails, list, status);
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onShowDetailsFetched");
            return;
        }
        netflixServiceCallback.onShowDetailsFetched(this.requestId, showDetails, status);
    }
    
    @Override
    public void onSimilarVideosFetched(final SearchVideoListProvider searchVideoListProvider, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onSimilarVideosFetched");
            return;
        }
        netflixServiceCallback.onSimilarVideosFetched(this.requestId, searchVideoListProvider, status);
    }
    
    @Override
    public void onVideoHide(final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onVideoHide");
            return;
        }
        netflixServiceCallback.onVideoHide(this.requestId, status);
    }
    
    @Override
    public void onVideoRatingSet(final UserRating userRating, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onVideoRatingSet");
            return;
        }
        netflixServiceCallback.onVideoRatingSet(this.requestId, userRating, status);
    }
    
    @Override
    public void onVideoSummaryFetched(final Video$Summary video$Summary, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onVideoSummaryFetched");
            return;
        }
        netflixServiceCallback.onVideoSummaryFetched(this.requestId, video$Summary, status);
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("FalkorAccess", "No client callback found for onVideosFetched");
            return;
        }
        netflixServiceCallback.onVideosFetched(this.requestId, list, status);
    }
}
