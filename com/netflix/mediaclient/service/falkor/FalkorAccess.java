// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.servicemgr.model.SearchVideoList;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.INetflixServiceCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.service.browse.BrowseAgentCallbackWrapper;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.servicemgr.IBrowseInterface;

public class FalkorAccess implements IBrowseInterface
{
    private static final String TAG = "NetflixServiceBrowse";
    private final FalkorAgent mBrowseAgent;
    private final NetflixService.ClientCallbacks mClientCallbacks;
    
    public FalkorAccess(final FalkorAgent mBrowseAgent, final NetflixService.ClientCallbacks mClientCallbacks) {
        this.mBrowseAgent = mBrowseAgent;
        this.mClientCallbacks = mClientCallbacks;
    }
    
    private BrowseAgentCallback wrapCallback(final BrowseAgentCallback browseAgentCallback) {
        return new BrowseAgentCallbackWrapper(browseAgentCallback);
    }
    
    @Override
    public void addToQueue(final String s, final int n, final int n2, final int n3) {
        this.mBrowseAgent.addToQueue(s, n, this.wrapCallback(new BrowseAgentClientCallback(n2, n3)));
    }
    
    @Override
    public void dumpHomeLoLoMosAndVideos(final String s, final String s2) {
        this.mBrowseAgent.dumpHomeLoLoMosAndVideos(s, s2);
    }
    
    @Override
    public void dumpHomeLoLoMosAndVideosToLog() {
        this.mBrowseAgent.dumpHomeLoLoMosAndVideosToLog();
    }
    
    @Override
    public void dumpHomeLoMos() {
        this.mBrowseAgent.dumpHomeLoMos();
    }
    
    @Override
    public void fetchCWVideos(final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchCWVideos(n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    @Override
    public void fetchEpisodeDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchEpisodeDetails(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void fetchEpisodes(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchEpisodes(s, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    @Override
    public void fetchGenreLists(final int n, final int n2) {
        this.mBrowseAgent.fetchGenreList(this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchGenreVideos(loMo, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    @Override
    public void fetchGenres(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchGenres(s, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    @Override
    public void fetchIQVideos(final LoMo loMo, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchIQVideos(loMo, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    @Override
    public void fetchKidsCharacterDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchKidsCharacterDetails(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void fetchLoLoMoSummary(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchLoLoMoSummary(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void fetchLoMos(final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchLoMos(n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    @Override
    public void fetchMovieDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchMovieDetails(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void fetchPostPlayVideos(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchPostPlayVideos(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void fetchSeasonDetails(final String s, final int n, final int n2) {
        this.mBrowseAgent.fetchSeasonDetails(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void fetchSeasons(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchSeasons(s, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    @Override
    public void fetchShowDetails(final String s, final String s2, final int n, final int n2) {
        this.mBrowseAgent.fetchShowDetails(s, s2, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void fetchSimilarVideosForPerson(final String s, final int n, final int n2, final int n3) {
        this.mBrowseAgent.fetchSimilarVideosForPerson(s, n, this.wrapCallback(new BrowseAgentClientCallback(n2, n3)));
    }
    
    @Override
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final int n2, final int n3, final String s2) {
        this.mBrowseAgent.fetchSimilarVideosForQuerySuggestion(s, n, this.wrapCallback(new BrowseAgentClientCallback(n2, n3)), s2);
    }
    
    @Override
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.fetchVideos(loMo, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    @Override
    public void flushCaches() {
        this.mBrowseAgent.flushCaches();
    }
    
    @Override
    public void hideVideo(final String s, final int n, final int n2) {
        this.mBrowseAgent.hideVideo(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void logBillboardActivity(final Video video, final BrowseAgent.BillboardActivityType billboardActivityType) {
        this.mBrowseAgent.logBillboardActivity(video, billboardActivityType);
    }
    
    @Override
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final int n5, final int n6) {
        this.mBrowseAgent.prefetchGenreLoLoMo(s, n, n2, n3, n4, b, this.wrapCallback(new BrowseAgentClientCallback(n5, n6)));
    }
    
    @Override
    public void prefetchLoLoMo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final int n7, final int n8) {
        this.mBrowseAgent.prefetchLoLoMo(n, n2, n3, n4, n5, n6, b, b2, this.wrapCallback(new BrowseAgentClientCallback(n7, n8)));
    }
    
    @Override
    public void refreshCW() {
        this.mBrowseAgent.refreshCW();
    }
    
    @Override
    public void refreshIQ() {
        this.mBrowseAgent.refreshIQ();
    }
    
    @Override
    public void removeFromQueue(final String s, final int n, final int n2) {
        this.mBrowseAgent.removeFromQueue(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void searchNetflix(final String s, final int n, final int n2) {
        this.mBrowseAgent.searchNetflix(s, this.wrapCallback(new BrowseAgentClientCallback(n, n2)));
    }
    
    @Override
    public void setVideoRating(final String s, final int n, final int n2, final int n3, final int n4) {
        this.mBrowseAgent.setVideoRating(s, n, n2, this.wrapCallback(new BrowseAgentClientCallback(n3, n4)));
    }
    
    private class BrowseAgentClientCallback implements BrowseAgentCallback
    {
        private final int clientId;
        private final int requestId;
        
        BrowseAgentClientCallback(final int clientId, final int requestId) {
            this.clientId = clientId;
            this.requestId = requestId;
        }
        
        @Override
        public void onBBVideosFetched(final List<Billboard> list, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for oBBVideosFetched");
                return;
            }
            netflixServiceCallback.onBBVideosFetched(this.requestId, list, status);
        }
        
        @Override
        public void onCWListRefresh(final Status status) {
            throw new IllegalStateException("not implemented");
        }
        
        @Override
        public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onCWVideosFetched");
                return;
            }
            netflixServiceCallback.onCWVideosFetched(this.requestId, list, status);
        }
        
        @Override
        public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onEpisodeDetailsFetched");
                return;
            }
            netflixServiceCallback.onEpisodeDetailsFetched(this.requestId, episodeDetails, status);
        }
        
        @Override
        public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onEpisodesFetched");
                return;
            }
            netflixServiceCallback.onEpisodesFetched(this.requestId, list, status);
        }
        
        @Override
        public void onGenreListsFetched(final List<GenreList> list, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onGenreListsFetched");
                return;
            }
            netflixServiceCallback.onGenreListsFetched(this.requestId, list, status);
        }
        
        @Override
        public void onGenreLoLoMoPrefetched(final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for client onGenreLoLoMoPrefetched");
                return;
            }
            netflixServiceCallback.onGenreLoLoMoPrefetched(this.requestId, status);
        }
        
        @Override
        public void onGenresFetched(final List<Genre> list, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onGenresFetched");
                return;
            }
            netflixServiceCallback.onGenresFetched(this.requestId, list, status);
        }
        
        @Override
        public void onIQListRefresh(final Status status) {
            throw new IllegalStateException("not implemented");
        }
        
        @Override
        public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onKidsCharacterDetailsFetched");
                return;
            }
            netflixServiceCallback.onKidsCharacterDetailsFetched(this.requestId, kidsCharacterDetails, b, status);
        }
        
        @Override
        public void onLoLoMoPrefetched(final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for client onLoLoMoPrefetched");
                return;
            }
            netflixServiceCallback.onLoLoMoPrefetched(this.requestId, status);
        }
        
        @Override
        public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onLoLoMoSummaryFetched");
                return;
            }
            netflixServiceCallback.onLoLoMoSummaryFetched(this.requestId, loLoMo, status);
        }
        
        @Override
        public void onLoMosFetched(final List<LoMo> list, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onLoMosFetched");
                return;
            }
            netflixServiceCallback.onLoMosFetched(this.requestId, list, status);
        }
        
        @Override
        public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onMovieDetailsFetched");
                return;
            }
            netflixServiceCallback.onMovieDetailsFetched(this.requestId, movieDetails, status);
        }
        
        @Override
        public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onPostPlayVideosFetched");
                return;
            }
            netflixServiceCallback.onPostPlayVideosFetched(this.requestId, list, status);
        }
        
        @Override
        public void onQueueAdd(final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onQueueAdd");
                return;
            }
            netflixServiceCallback.onQueueAdd(this.requestId, status);
        }
        
        @Override
        public void onQueueRemove(final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onQueueRemove");
                return;
            }
            netflixServiceCallback.onQueueRemove(this.requestId, status);
        }
        
        @Override
        public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onSearchResultsFetched");
                return;
            }
            netflixServiceCallback.onSearchResultsFetched(this.requestId, searchResults, status);
        }
        
        @Override
        public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onSeasonDetailsFetched");
                return;
            }
            netflixServiceCallback.onSeasonDetailsFetched(this.requestId, seasonDetails, status);
        }
        
        @Override
        public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onSeasonsFetched");
                return;
            }
            netflixServiceCallback.onSeasonsFetched(this.requestId, list, status);
        }
        
        @Override
        public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onShowDetailsFetched");
                return;
            }
            netflixServiceCallback.onShowDetailsFetched(this.requestId, showDetails, status);
        }
        
        @Override
        public void onSimilarVideosFetched(final SearchVideoList list, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onSimilarVideosFetched");
                return;
            }
            netflixServiceCallback.onSimilarVideosFetched(this.requestId, list, status);
        }
        
        @Override
        public void onVideoHide(final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onVideoHide");
                return;
            }
            netflixServiceCallback.onVideoHide(this.requestId, status);
        }
        
        @Override
        public void onVideoRatingSet(final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onVideoRatingSet");
                return;
            }
            netflixServiceCallback.onVideoRatingSet(this.requestId, status);
        }
        
        @Override
        public void onVideosFetched(final List<Video> list, final Status status) {
            final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)FalkorAccess.this.mClientCallbacks.get(this.clientId);
            if (netflixServiceCallback == null) {
                Log.w("NetflixServiceBrowse", "No client callback found for onVideosFetched");
                return;
            }
            netflixServiceCallback.onVideosFetched(this.requestId, list, status);
        }
    }
}
