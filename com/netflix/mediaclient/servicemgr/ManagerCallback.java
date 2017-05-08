// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

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
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import com.netflix.mediaclient.servicemgr.interface_.ExpiringContentAction;
import com.netflix.mediaclient.servicemgr.interface_.IExpiringContentWarning;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;

public interface ManagerCallback
{
    void onAutoLoginTokenCreated(final String p0, final Status p1);
    
    void onAvailableAvatarsListFetched(final List<AvatarInfo> p0, final Status p1);
    
    void onBBVideosFetched(final List<Billboard> p0, final Status p1);
    
    void onCWVideosFetched(final List<CWVideo> p0, final Status p1);
    
    void onConnectWithFacebookComplete(final Status p0);
    
    void onEpisodeDetailsFetched(final EpisodeDetails p0, final Status p1);
    
    void onEpisodesFetched(final List<EpisodeDetails> p0, final Status p1);
    
    void onExpiringContentWarning(final IExpiringContentWarning p0, final Status p1, final ExpiringContentAction p2);
    
    void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> p0, final Status p1);
    
    void onGenreListsFetched(final List<GenreList> p0, final Status p1);
    
    void onGenreLoLoMoPrefetched(final Status p0);
    
    void onGenresFetched(final List<Genre> p0, final Status p1);
    
    void onInteractiveMomentsFetched(final InteractiveMoments p0, final Status p1);
    
    void onKidsCharacterDetailsFetched(final KidsCharacterDetails p0, final Boolean p1, final Status p2);
    
    void onLoLoMoPrefetched(final Status p0);
    
    void onLoLoMoSummaryFetched(final LoLoMo p0, final Status p1);
    
    void onLoMosFetched(final List<LoMo> p0, final Status p1);
    
    void onLoginComplete(final Status p0);
    
    void onLogoutComplete(final Status p0);
    
    void onMovieDetailsFetched(final MovieDetails p0, final Status p1);
    
    void onNotificationsListFetched(final IrisNotificationsList p0, final Status p1);
    
    void onPostPlayVideosFetched(final PostPlayVideosProvider p0, final Status p1);
    
    void onProfileListUpdateStatus(final Status p0);
    
    void onQueueAdd(final Status p0);
    
    void onQueueRemove(final Status p0);
    
    void onResourceCached(final String p0, final String p1, final long p2, final long p3, final Status p4);
    
    void onResourceFetched(final String p0, final String p1, final Status p2);
    
    void onResourceRawFetched(final String p0, final byte[] p1, final Status p2);
    
    void onScenePositionFetched(final int p0, final Status p1);
    
    void onSearchResultsFetched(final ISearchResults p0, final Status p1);
    
    void onSeasonDetailsFetched(final SeasonDetails p0, final Status p1);
    
    void onSeasonsFetched(final List<SeasonDetails> p0, final Status p1);
    
    void onShowDetailsAndSeasonsFetched(final ShowDetails p0, final List<SeasonDetails> p1, final Status p2);
    
    void onShowDetailsFetched(final ShowDetails p0, final Status p1);
    
    void onSimilarVideosFetched(final SearchVideoListProvider p0, final Status p1);
    
    void onSocialNotificationWasThanked(final Status p0);
    
    void onVerified(final boolean p0, final Status p1);
    
    void onVideoHide(final Status p0);
    
    void onVideoRatingSet(final UserRating p0, final Status p1);
    
    void onVideoSummaryFetched(final Video$Summary p0, final Status p1);
    
    void onVideosFetched(final List<Video> p0, final Status p1);
}
