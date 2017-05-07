// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.servicemgr.ProfileType;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.BasicLoMo;
import com.netflix.mediaclient.servicemgr.LoMoUtils;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseWebClient;

public final class BrowseVolleyWebClient implements BrowseWebClient
{
    private static final int GENRE_LIST_MAX = 30;
    private static final int MOVIE_DETAILS_MAX_SIMILARS = 50;
    public static final int SEARCH_RESULTS_MAX = 10;
    private final List<String> cwKeysCache;
    private final HardCache hardCache;
    private final List<String> iqKeysCache;
    private final NetflixService service;
    private final SoftCache softCache;
    private final SoftCache weakSeasonsCache;
    private final FalcorVolleyWebClient webclient;
    
    public BrowseVolleyWebClient(final HardCache hardCache, final SoftCache softCache, final SoftCache weakSeasonsCache, final List<String> cwKeysCache, final List<String> iqKeysCache, final NetflixService service, final FalcorVolleyWebClient webclient) {
        this.service = service;
        this.webclient = webclient;
        this.hardCache = hardCache;
        this.softCache = softCache;
        this.weakSeasonsCache = weakSeasonsCache;
        this.cwKeysCache = cwKeysCache;
        this.iqKeysCache = iqKeysCache;
    }
    
    private void fetchVideosAndInjectSocialData(final String s, final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.fetchVideosInternal(s, loMo, n, n2, new SimpleBrowseAgentCallback() {
            @Override
            public void onVideosFetched(final List<Video> list, final int n) {
                if (n == 0 && n == 0) {
                    PrefetchHomeLoLoMoRequest.injectSocialData(loMo, list);
                }
                browseAgentCallback.onVideosFetched(list, n);
            }
        });
    }
    
    private void fetchVideosInternal(final String s, final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchVideosRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, loMo, n, n2, browseAgentCallback));
    }
    
    @Override
    public void addToQueue(final String s, final int n, final int n2, final int n3, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (!b) {
            this.webclient.sendRequest(new AddToQueueRequestNoLolomo(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, s, n3, browseAgentCallback));
            return;
        }
        final AddToQueueRequest addToQueueRequest = new AddToQueueRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, this.iqKeysCache, s, n, n2, n3, browseAgentCallback);
        if (addToQueueRequest.canProceed()) {
            this.webclient.sendRequest(addToQueueRequest);
            return;
        }
        addToQueueRequest.onFailure(-67);
    }
    
    @Override
    public void fetchCWVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchCWVideosRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, this.weakSeasonsCache, n, n2, 50, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchEpisodeDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchEpisodeDetailsRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchEpisodes(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchEpisodesRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, n, n2, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchGenreLists(final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchGenreListsRequest(this.service.getApplicationContext(), this.service.getConfiguration(), 0, 30, browseAgentCallback));
    }
    
    @Override
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchVideosRequest(this.service.getApplicationContext(), this.service.getConfiguration(), "glists", loMo, n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchGenres(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchGenresRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, s, n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchIQVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchIQVideosRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchLoLoMoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchLoLoMoSummaryRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, s, browseAgentCallback));
    }
    
    @Override
    public void fetchLoMos(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchLoMosRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchMovieDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchMovieDetailsRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, 0, 50, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchSeasonDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchSeasonDetailsRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, browseAgentCallback));
    }
    
    @Override
    public void fetchSeasons(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchSeasonsRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchShowDetails(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchShowDetailsRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, s2, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchSimilarVideosForPerson(final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchSimilarVideosRequest.FetchSimilarVideosForPersonRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, n, browseAgentCallback));
    }
    
    @Override
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchSimilarVideosRequest.FetchSimilarVideosForQuerySuggestionRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, n, browseAgentCallback));
    }
    
    @Override
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (LoMoUtils.shouldInjectSocialData(loMo, this.service.isCurrentProfileFacebookConnected())) {
            this.fetchVideosAndInjectSocialData("lists", loMo, n, n2, browseAgentCallback);
            return;
        }
        this.fetchVideosInternal("lists", loMo, n, n2, browseAgentCallback);
    }
    
    @Override
    public void hideVideo(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new HideVideoRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, s, browseAgentCallback));
    }
    
    @Override
    public final boolean isSynchronous() {
        return this.webclient.isSynchronous();
    }
    
    @Override
    public void logBillboardActivity(final Video video, final BrowseAgent.BillboardActivityType billboardActivityType) {
        this.webclient.sendRequest(new LogBillboardActivityRequest(this.service.getApplicationContext(), this.service.getConfiguration(), video, billboardActivityType));
    }
    
    @Override
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new PrefetchGenreLoLoMoRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, s, n, n2, n3, n4, browseAgentCallback));
    }
    
    @Override
    public void prefetchLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new PrefetchHomeLoLoMoRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, this.weakSeasonsCache, n, n2, n3, n4, n5, n6, 50, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void refreshCWList(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        final RefreshCWRequest refreshCWRequest = new RefreshCWRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, this.weakSeasonsCache, this.cwKeysCache, n, n2, 50, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback);
        if (refreshCWRequest.canProceed()) {
            this.webclient.sendRequest(refreshCWRequest);
            return;
        }
        refreshCWRequest.onFailure(-68);
    }
    
    @Override
    public void refreshIQList(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        throw new IllegalStateException(" refreshIQList not implemented");
    }
    
    @Override
    public void removeFromQueue(final String s, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (!b) {
            this.webclient.sendRequest(new RemoveFromQueueRequestNoLolomo(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, s, browseAgentCallback));
            return;
        }
        final RemoveFromQueueRequest removeFromQueueRequest = new RemoveFromQueueRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, this.iqKeysCache, s, n, n2, browseAgentCallback);
        if (removeFromQueueRequest.canProceed()) {
            this.webclient.sendRequest(removeFromQueueRequest);
            return;
        }
        removeFromQueueRequest.onFailure(-67);
    }
    
    @Override
    public void searchNetflix(final String s, final ProfileType profileType, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchSearchRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, 0, 10, profileType, browseAgentCallback));
    }
    
    @Override
    public void setVideoRating(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new SetVideoRatingRequest(this.service.getApplicationContext(), this.service.getConfiguration(), this.hardCache, this.softCache, s, n, n2, browseAgentCallback));
    }
}
