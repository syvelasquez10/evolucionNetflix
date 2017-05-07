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
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;

public interface INetflixServiceCallback
{
    void onAvailableAvatarsListFetched(final int p0, final List<AvatarInfo> p1, final Status p2);
    
    void onBBVideosFetched(final int p0, final List<Billboard> p1, final Status p2);
    
    void onCWVideosFetched(final int p0, final List<CWVideo> p1, final Status p2);
    
    void onConnectWithFacebookComplete(final int p0, final Status p1);
    
    void onEpisodeDetailsFetched(final int p0, final EpisodeDetails p1, final Status p2);
    
    void onEpisodesFetched(final int p0, final List<EpisodeDetails> p1, final Status p2);
    
    void onGenreListsFetched(final int p0, final List<GenreList> p1, final Status p2);
    
    void onGenreLoLoMoPrefetched(final int p0, final Status p1);
    
    void onGenresFetched(final int p0, final List<Genre> p1, final Status p2);
    
    void onKidsCharacterDetailsFetched(final int p0, final KidsCharacterDetails p1, final Boolean p2, final Status p3);
    
    void onLoLoMoPrefetched(final int p0, final Status p1);
    
    void onLoLoMoSummaryFetched(final int p0, final LoLoMo p1, final Status p2);
    
    void onLoMosFetched(final int p0, final List<LoMo> p1, final Status p2);
    
    void onLoginComplete(final int p0, final Status p1);
    
    void onLogoutComplete(final int p0, final Status p1);
    
    void onMovieDetailsFetched(final int p0, final MovieDetails p1, final Status p2);
    
    void onPinVerified(final int p0, final boolean p1, final Status p2);
    
    void onPostPlayVideosFetched(final int p0, final List<PostPlayVideo> p1, final Status p2);
    
    void onProfileListUpdateStatus(final int p0, final Status p1);
    
    void onQueueAdd(final int p0, final Status p1);
    
    void onQueueRemove(final int p0, final Status p1);
    
    void onResourceFetched(final int p0, final String p1, final String p2, final Status p3);
    
    void onSearchResultsFetched(final int p0, final ISearchResults p1, final Status p2);
    
    void onSeasonDetailsFetched(final int p0, final SeasonDetails p1, final Status p2);
    
    void onSeasonsFetched(final int p0, final List<SeasonDetails> p1, final Status p2);
    
    void onServiceReady(final int p0, final Status p1);
    
    void onShowDetailsFetched(final int p0, final ShowDetails p1, final Status p2);
    
    void onSimilarVideosFetched(final int p0, final SearchVideoList p1, final Status p2);
    
    void onVideoHide(final int p0, final Status p1);
    
    void onVideoRatingSet(final int p0, final Status p1);
    
    void onVideosFetched(final int p0, final List<Video> p1, final Status p2);
}
