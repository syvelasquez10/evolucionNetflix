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

public abstract class SimpleManagerCallback implements ManagerCallback
{
    @Override
    public void onAvailableAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
    }
    
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
    }
    
    @Override
    public void onConnectWithFacebookComplete(final Status status) {
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
    public void onLoginComplete(final Status status) {
    }
    
    @Override
    public void onLogoutComplete(final Status status) {
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
    }
    
    @Override
    public void onPinVerified(final boolean b, final Status status) {
    }
    
    @Override
    public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final Status status) {
    }
    
    @Override
    public void onProfileListUpdateStatus(final Status status) {
    }
    
    @Override
    public void onQueueAdd(final Status status) {
    }
    
    @Override
    public void onQueueRemove(final Status status) {
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
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
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
    }
    
    @Override
    public void onSimilarVideosFetched(final SearchVideoList list, final Status status) {
    }
    
    @Override
    public void onVideoHide(final Status status) {
    }
    
    @Override
    public void onVideoRatingSet(final Status status) {
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
    }
}
