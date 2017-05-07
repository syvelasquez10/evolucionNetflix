// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.servicemgr.model.user.ProfileType;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgent$BillboardActivityType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.servicemgr.model.LoMoUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.browse.BrowseWebClient;

public final class BrowseVolleyWebClient implements BrowseWebClient
{
    private static final int GENRE_LIST_MAX = 30;
    public static final int MAX_SEARCH_RESULTS_PER_SECTION_INDEX = 19;
    private static final int MAX_SIMILAR_VIDEO_INDEX = 11;
    private final BrowseWebClientCache browseCache;
    private final NetflixService service;
    private final FalkorVolleyWebClient webclient;
    
    public BrowseVolleyWebClient(final BrowseWebClientCache browseCache, final NetflixService service, final FalkorVolleyWebClient webclient) {
        this.service = service;
        this.webclient = webclient;
        this.browseCache = browseCache;
    }
    
    private void fetchVideosAndInjectSocialData(final LoMo loMo, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.fetchVideosInternal(loMo, n, n2, b, new BrowseVolleyWebClient$1(this, n, loMo, browseAgentCallback));
    }
    
    private void fetchVideosInternal(final LoMo loMo, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchVideosRequest(this.service.getApplicationContext(), "lists", loMo, n, n2, b, browseAgentCallback));
    }
    
    @Override
    public void addToQueue(final String s, final int n, final int n2, final int n3, final boolean b, final boolean b2, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (!b) {
            this.webclient.sendRequest(new AddToQueueRequestNoLolomo(this.service.getApplicationContext(), this.browseCache, s, n3, s2, browseAgentCallback));
            return;
        }
        final AddToQueueRequest addToQueueRequest = new AddToQueueRequest(this.service.getApplicationContext(), this.browseCache, s, n, n2, n3, b2, s2, browseAgentCallback);
        if (addToQueueRequest.canProceed()) {
            this.webclient.sendRequest(addToQueueRequest);
            return;
        }
        addToQueueRequest.onFailure(new NetflixStatus(StatusCode.BROWSE_IQ_WRONG_STATE));
    }
    
    @Override
    public void fetchBBVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchBBVideosRequest(this.service.getApplicationContext(), this.browseCache, loMo, n, n2, 11, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchCWVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchCWVideosRequest(this.service.getApplicationContext(), this.browseCache, n, n2, 11, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchEpisodeDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchEpisodeDetailsRequest(this.service.getApplicationContext(), s, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchEpisodes(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchEpisodesRequest(this.service.getApplicationContext(), s, n, n2, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchGenreLists(final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchGenreListsRequest(this.service.getApplicationContext(), 0, 30, browseAgentCallback));
    }
    
    @Override
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        final FalkorVolleyWebClient webclient = this.webclient;
        final Context applicationContext = this.service.getApplicationContext();
        String s;
        if (LoMoType.FLAT_GENRE.equals(loMo.getType())) {
            s = "flatGenre";
        }
        else {
            s = "lists";
        }
        webclient.sendRequest(new FetchVideosRequest(applicationContext, s, loMo, n, n2, b, browseAgentCallback));
    }
    
    @Override
    public void fetchGenres(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchGenresRequest(this.service.getApplicationContext(), this.browseCache, s, n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchIQVideos(final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchIQVideosRequest(this.service.getApplicationContext(), this.browseCache, n, n2, b, browseAgentCallback));
    }
    
    @Override
    public void fetchKidsCharacterDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchKidsCharacterDetailsRequest(this.service.getApplicationContext(), s, browseAgentCallback));
    }
    
    @Override
    public void fetchLoLoMoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchLoLoMoSummaryRequest(this.service.getApplicationContext(), this.browseCache, s, browseAgentCallback));
    }
    
    @Override
    public void fetchLoMos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchLoMosRequest(this.service.getApplicationContext(), this.browseCache, n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchMovieDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchMovieDetailsRequest(this.service.getApplicationContext(), this.browseCache, s, 11, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchPostPlayVideos(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchPostPlayVideosRequest(this.service.getApplicationContext(), s, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchSeasonDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchSeasonDetailsRequest(this.service.getApplicationContext(), s, browseAgentCallback));
    }
    
    @Override
    public void fetchSeasons(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchSeasonsRequest(this.service.getApplicationContext(), s, n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchShowDetails(final String s, final String s2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchShowDetailsRequest(this.service.getApplicationContext(), this.browseCache, s, s2, 11, false, b, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchShowDetailsAndSeasons(final String s, final String s2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchShowDetailsRequest(this.service.getApplicationContext(), this.browseCache, s, s2, 11, true, b, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void fetchSimilarVideosForPerson(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        this.webclient.sendRequest(new FetchSimilarVideosRequest$FetchSimilarVideosForPersonRequest(this.service.getApplicationContext(), s, n, browseAgentCallback, s2));
    }
    
    @Override
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        this.webclient.sendRequest(new FetchSimilarVideosRequest$FetchSimilarVideosForQuerySuggestionRequest(this.service.getApplicationContext(), s, n, browseAgentCallback, s2));
    }
    
    @Override
    public void fetchSocialNotifications(final int n, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchSocialNotificationsRequest(this.service.getApplicationContext(), this.browseCache, n, browseAgentCallback));
    }
    
    @Override
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (LoMoUtils.shouldInjectSocialData(loMo, this.service.isCurrentProfileFacebookConnected(), b)) {
            this.fetchVideosAndInjectSocialData(loMo, n, n2, b, browseAgentCallback);
            return;
        }
        this.fetchVideosInternal(loMo, n, n2, b, browseAgentCallback);
    }
    
    @Override
    public void hideVideo(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new HideVideoRequest(this.service.getApplicationContext(), this.browseCache, s, browseAgentCallback));
    }
    
    @Override
    public final boolean isSynchronous() {
        return this.webclient.isSynchronous();
    }
    
    @Override
    public void logBillboardActivity(final Video video, final BrowseAgent$BillboardActivityType browseAgent$BillboardActivityType) {
        this.webclient.sendRequest(new LogBillboardActivityRequest(this.service.getApplicationContext(), video, browseAgent$BillboardActivityType));
    }
    
    @Override
    public void markSocialNotificationsAsRead(final List<SocialNotificationSummary> list, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new MarkNotificationsAsReadRequest(this.service.getApplicationContext(), list, browseAgentCallback));
    }
    
    @Override
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new PrefetchGenreLoLoMoRequest(this.service.getApplicationContext(), KidsUtils.isKoPExperience(this.service.getConfiguration(), this.service.getCurrentProfile()), this.browseCache, s, n, n2, n3, n4, b, browseAgentCallback));
    }
    
    @Override
    public void prefetchLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new PrefetchHomeLoLoMoRequest(this.service.getApplicationContext(), this.browseCache, n, n2, n3, n4, 11, b, b2, this.service.isCurrentProfileFacebookConnected(), browseAgentCallback));
    }
    
    @Override
    public void refreshCWList(final int n, final int n2) {
        final RefreshCWRequest refreshCWRequest = new RefreshCWRequest(this.service.getApplicationContext(), this.browseCache, n, n2, 11, this.service.isCurrentProfileFacebookConnected());
        if (refreshCWRequest.canProceed()) {
            this.webclient.sendRequest(refreshCWRequest);
            return;
        }
        refreshCWRequest.onFailure(new NetflixStatus(StatusCode.BROWSE_CW_WRONG_STATE));
    }
    
    @Override
    public void refreshIQList(final int n, final int n2) {
        final RefreshIQRequest refreshIQRequest = new RefreshIQRequest(this.service.getApplicationContext(), this.browseCache, n, n2);
        if (refreshIQRequest.canProceed()) {
            this.webclient.sendRequest(refreshIQRequest);
            return;
        }
        refreshIQRequest.onFailure(new NetflixStatus(StatusCode.BROWSE_IQ_WRONG_STATE));
    }
    
    @Override
    public void refreshKidsCharacterDetils(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new RefreshKidsCharacterDetails(this.service.getApplicationContext(), s, browseAgentCallback));
    }
    
    @Override
    public void removeFromQueue(final String s, final int n, final int n2, final boolean b, final String s2, final BrowseAgentCallback browseAgentCallback) {
        if (!b) {
            this.webclient.sendRequest(new RemoveFromQueueRequestNoLolomo(this.service.getApplicationContext(), this.browseCache, s, s2, browseAgentCallback));
            return;
        }
        final RemoveFromQueueRequest removeFromQueueRequest = new RemoveFromQueueRequest(this.service.getApplicationContext(), this.browseCache, s, n, n2, s2, browseAgentCallback);
        if (removeFromQueueRequest.canProceed()) {
            this.webclient.sendRequest(removeFromQueueRequest);
            return;
        }
        removeFromQueueRequest.onFailure(new NetflixStatus(StatusCode.BROWSE_IQ_WRONG_STATE));
    }
    
    @Override
    public void searchNetflix(final String s, final ProfileType profileType, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new FetchSearchRequest(this.service.getApplicationContext(), s, profileType, browseAgentCallback));
    }
    
    @Override
    public void sendThanksToSocialNotification(final SocialNotificationSummary socialNotificationSummary, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new SendThanksToSocialNotificationRequest(this.service.getApplicationContext(), socialNotificationSummary, browseAgentCallback));
    }
    
    @Override
    public void setVideoRating(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.webclient.sendRequest(new SetVideoRatingRequest(this.service.getApplicationContext(), this.browseCache, s, n, n2, browseAgentCallback));
    }
}
