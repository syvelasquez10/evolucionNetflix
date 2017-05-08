// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.widget.TextView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Context;
import android.content.ServiceConnection;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.mediaclient.servicemgr.interface_.UserRating;
import com.netflix.model.survey.Survey;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideoListProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideosProvider;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.model.leafs.OnRampEligibility;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.LoLoMo;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.servicemgr.interface_.details.InteractiveMoments;
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import com.netflix.model.leafs.advisory.Advisory;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.model.branches.MementoVideoSwatch;
import com.netflix.model.branches.FalkorPerson;
import java.util.List;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.app.Status;

class ServiceManager$ServiceListener implements INetflixServiceCallback
{
    final /* synthetic */ ServiceManager this$0;
    
    private ServiceManager$ServiceListener(final ServiceManager this$0) {
        this.this$0 = this$0;
    }
    
    private void logShowDetailsInfo(final Status status, final int n, final ShowDetails showDetails) {
        Log.d("ServiceManager", "onShowDetailsFetched requestId=" + n + " errorCode=" + status.getStatusCode());
        Log.d("ServiceManager", "onShowDetailsFetched requestedSdp=" + showDetails);
        if (showDetails != null && StringUtils.isNotEmpty(showDetails.getId()) && showDetails.getSimilars() != null) {
            Log.d("ServiceManager", "onShowDetailsFetched sims size=" + showDetails.getSimilars().size());
            Log.d("ServiceManager", "onShowDetailsFetched sims track id=" + showDetails.getSimilarsTrackId());
        }
    }
    
    private void updateStatusRequestId(final Status status, final int requestId) {
        if (status instanceof NetflixStatus) {
            ((NetflixStatus)status).setRequestId(requestId);
        }
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        final int n = hashCode = super.hashCode();
        if (n < 0) {
            hashCode = -n;
        }
        return hashCode;
    }
    
    @Override
    public void onActorDetailsAndRelatedFetched(final int n, final List<FalkorPerson> list, final List<MementoVideoSwatch> list2, final Status status, final List<FalkorActorStill> list3) {
        this.updateStatusRequestId(status, n);
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onShowDetailsAndSeasonsFetched requestId " + n);
            return;
        }
        access$400.onActorDetailsAndRelatedFetched(list, list2, status, list3);
    }
    
    @Override
    public void onAdvisoriesFetched(final int n, final List<Advisory> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onAdvisoriesFetched requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onAdvisoriesFetched requestId " + n);
            return;
        }
        access$400.onAdvisoriesFetched(list, status);
    }
    
    @Override
    public void onAutoLoginTokenCreated(final int n, final String s, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onAutoLoginTokenCreated requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onAutoLoginTokenCreated requestId " + n);
            return;
        }
        access$400.onAutoLoginTokenCreated(s, status);
    }
    
    @Override
    public void onAvailableAvatarsListFetched(final int n, final List<AvatarInfo> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onAvailableAvatarsListFetched requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onAvailableAvatarsListFetched requestId " + n);
            return;
        }
        access$400.onAvailableAvatarsListFetched(list, status);
    }
    
    @Override
    public void onBBVideosFetched(final int n, final List<Billboard> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onBBVideosFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onBBVideosFetched requestedVideos=" + list);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onBBVideosFetched requestId " + n);
            return;
        }
        access$400.onBBVideosFetched(list, status);
    }
    
    @Override
    public void onCWVideosFetched(final int n, final List<CWVideo> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onCWVideosFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onCWVideosFetched requestedVideos=" + list);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onCWVideosFetched requestId " + n);
            return;
        }
        access$400.onCWVideosFetched(list, status);
    }
    
    @Override
    public void onDiscoveryVideosFetched(final int n, final List<Discovery> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onDiscoveryVideosFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onDiscoveryVideosFetched requestedVideos=" + list);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onDiscoveryVideosFetched requestId " + n);
            return;
        }
        access$400.onDiscoveryVideosFetched(list, status);
    }
    
    @Override
    public void onEpisodeDetailsFetched(final int n, final EpisodeDetails episodeDetails, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onEpisodeDetailsFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onEpisodeDetailsFetched requestedEdp=" + episodeDetails);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onEpisodeDetailsFetched requestId " + n);
            return;
        }
        access$400.onEpisodeDetailsFetched(episodeDetails, status);
    }
    
    @Override
    public void onEpisodesFetched(final int n, final List<EpisodeDetails> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onEpisodesFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onEpisodesFetched requestedEpisodes=" + list);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onEpisodesFetched requestId " + n);
            return;
        }
        access$400.onEpisodesFetched(list, status);
    }
    
    @Override
    public void onGenreListsFetched(final int n, final List<GenreList> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onGenreListsFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onGenreListsFetched requestedGenreLists=" + list);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onGenreListsFetched requestId " + n);
            return;
        }
        access$400.onGenreListsFetched(list, status);
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final int n, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onGenreLoLoMoPrefetched requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            if (Log.isLoggable()) {
                Log.d("ServiceManager", "No callback for onGenreLoLoMoPrefetched requestId " + n);
            }
            return;
        }
        access$400.onGenreLoLoMoPrefetched(status);
    }
    
    @Override
    public void onGenresFetched(final int n, final List<Genre> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onGenresFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onGenresFetched requestedGenres=" + list);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onGenresFetched requestId " + n);
            return;
        }
        access$400.onGenresFetched(list, status);
    }
    
    @Override
    public void onInteractiveMomentsFetched(final int n, final InteractiveMoments interactiveMoments, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onInteractiveMomentsFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onInteractiveMomentsFetched requestedMoments=" + interactiveMoments);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onInteractiveMomentsFetched requestId " + n);
            return;
        }
        access$400.onInteractiveMomentsFetched(interactiveMoments, status);
    }
    
    @Override
    public void onIrisNotificationsListFetched(final int n, final IrisNotificationsList list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onIrisNotificationsListFetched requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onIrisNotificationsListFetched requestId " + n);
            return;
        }
        access$400.onNotificationsListFetched(list, status);
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final int n, final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onKidsCharacterDetailsFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onKidsCharacterDetailsFetched kidsCharacterDetails=" + kidsCharacterDetails);
            if (kidsCharacterDetails != null) {
                Log.d("ServiceManager", "onKidsCharacterDetailsFetched gallery size=" + kidsCharacterDetails.getGallery().size());
                Log.d("ServiceManager", "onKidsCharacterDetailsFetched gallery track id=" + kidsCharacterDetails.getGalleryTrackId());
            }
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onKidsCharacterDetailsFetched requestId " + n);
            return;
        }
        access$400.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, status);
    }
    
    @Override
    public void onLoLoMoPrefetched(final int n, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onLoLoMoPrefetched requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            if (Log.isLoggable()) {
                Log.d("ServiceManager", "No callback for onLoLoMoPrefetched requestId " + n);
            }
            return;
        }
        access$400.onLoLoMoPrefetched(status);
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final int n, final LoLoMo loLoMo, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onLoLoMoSummaryFetched requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onLoLoMoSummaryFetched requestId " + n);
            return;
        }
        access$400.onLoLoMoSummaryFetched(loLoMo, status);
    }
    
    @Override
    public void onLoMosFetched(final int n, final List<LoMo> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onLoMosFetched requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
        }
        if (Log.isLoggable()) {
            Log.v("ServiceManager", "onLoMosFetched requestedLoMos=" + list);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            if (Log.isLoggable()) {
                Log.d("ServiceManager", "No callback for onLoMosFetched requestId " + n);
            }
            return;
        }
        access$400.onLoMosFetched(list, status);
    }
    
    @Override
    public void onLoginComplete(final int n, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onLoginComplete requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onLoginComplete requestId " + n);
            return;
        }
        access$400.onLoginComplete(status);
    }
    
    @Override
    public void onLogoutComplete(final int n, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onLogoutComplete requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onLogoutComplete requestId " + n);
            return;
        }
        access$400.onLogoutComplete(status);
    }
    
    @Override
    public void onMovieDetailsFetched(final int n, final MovieDetails movieDetails, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onMovieDetailsFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onMovieDetailsFetched requestedMdp=" + movieDetails);
            if (movieDetails != null) {
                Log.d("ServiceManager", "onMovieDetailsFetched sims size=" + movieDetails.getSimilars().size());
                Log.d("ServiceManager", "onMovieDetailsFetched sims track id=" + movieDetails.getSimilarsTrackId());
            }
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onMovieDetailsFetched requestId " + n);
            return;
        }
        access$400.onMovieDetailsFetched(movieDetails, status);
    }
    
    @Override
    public void onOnRampEligibilityAction(final int n, final OnRampEligibility onRampEligibility, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "doOnRampEligibilityAction requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for doOnRampEligibilityAction requestId " + n);
            return;
        }
        access$400.onOnRampEligibilityAction(onRampEligibility, status);
    }
    
    @Override
    public void onPersonDetailFetched(final int n, final FalkorPerson falkorPerson, final FalkorActorStill falkorActorStill, final Status status) {
        this.updateStatusRequestId(status, n);
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onPersonDetailFetched requestId " + n);
            return;
        }
        access$400.onPersonDetailFetched(falkorPerson, falkorActorStill, status);
    }
    
    @Override
    public void onPersonRelatedFetched(final int n, final FalkorPerson falkorPerson, final List<Video> list, final Status status) {
        this.updateStatusRequestId(status, n);
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onPersonDetailFetched requestId " + n);
            return;
        }
        access$400.onPersonRelatedFetched(falkorPerson, list, status);
    }
    
    @Override
    public void onPostPlayImpressionLogged(final int n, final boolean b, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onPostPlayImpressionLogged requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onPostPlayImpressionLogged success=" + b);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onPostPlayImpressionLogged requestId " + n);
            return;
        }
        access$400.onPostPlayImpressionLogged(b, status);
    }
    
    @Override
    public void onPostPlayVideosFetched(final int n, final PostPlayVideosProvider postPlayVideosProvider, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onPostPlayVideosFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onPostPlayVideosFetched requestedVideos=" + postPlayVideosProvider);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onPostPlayVideoFetched requestId " + n);
            return;
        }
        access$400.onPostPlayVideosFetched(postPlayVideosProvider, status);
    }
    
    @Override
    public void onProfileListUpdateStatus(final int n, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onProfileListUpdateStatus requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onProfileListUpdateStatus requestId " + n);
            return;
        }
        access$400.onProfileListUpdateStatus(status);
    }
    
    @Override
    public void onQueueAdd(final int n, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onQueueAdd requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onQueueAdd requestId " + n);
            return;
        }
        access$400.onQueueAdd(status);
    }
    
    @Override
    public void onQueueRemove(final int n, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onQueueRemove requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onQueueRemove requestId " + n);
            return;
        }
        access$400.onQueueRemove(status);
    }
    
    @Override
    public void onResourceCached(final int n, final String s, final String s2, final long n2, final long n3, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.v("ServiceManager", "onResourceCached requestId=" + n + " requestedUrl=" + s + " size=" + n3 + " res.getStatusCode()=" + status.getStatusCode());
        }
        final ManagerCallback demuxCallback = this.this$0.mCallbackMuxer.demuxCallback(n);
        if (demuxCallback == null) {
            Log.d("ServiceManager", "No callback for onResourceCached requestId " + n);
            return;
        }
        demuxCallback.onResourceCached(s, s2, n2, n3, status);
    }
    
    @Override
    public void onResourceFetched(final int n, final String s, final String s2, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.v("ServiceManager", "onResourceFetched requestId=" + n + " requestedUrl=" + s + " localUrl=" + s2 + " res.getStatusCode()=" + status.getStatusCode());
        }
        final ManagerCallback demuxCallback = this.this$0.mCallbackMuxer.demuxCallback(n);
        if (demuxCallback == null) {
            Log.d("ServiceManager", "No callback for onResourceFetched requestId " + n);
            return;
        }
        demuxCallback.onResourceFetched(s, s2, status);
    }
    
    @Override
    public void onResourceRawFetched(final int n, final String s, final byte[] array, final Status status) {
        if (Log.isLoggable() && array != null) {
            Log.v("ServiceManager", "onResourceRawFetched requestId=" + n + " requestedUrl=" + s + " byte size=" + array.length + " res.getStatusCode()=" + status.getStatusCode());
        }
        final ManagerCallback demuxCallback = this.this$0.mCallbackMuxer.demuxCallback(n);
        if (demuxCallback == null) {
            Log.d("ServiceManager", "No callback for onResourceRawFetched requestId " + n);
            return;
        }
        demuxCallback.onResourceRawFetched(s, array, status);
    }
    
    @Override
    public void onScenePositionFetched(final int n, final int n2, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onScenePositionFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onScenePositionFetched position=" + n2);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onScenePositionFetched requestId " + n);
            return;
        }
        access$400.onScenePositionFetched(n2, status);
    }
    
    @Override
    public void onSearchResultsFetched(final int n, final ISearchResults searchResults, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onSearchResultsFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onSearchResultsFetched results=" + searchResults);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onSearchResultsFetched requestId " + n);
            return;
        }
        access$400.onSearchResultsFetched(searchResults, status);
    }
    
    @Override
    public void onSeasonDetailsFetched(final int n, final SeasonDetails seasonDetails, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onSeasonDetailsFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            final StringBuilder append = new StringBuilder().append("onSeasonDetailsFetched seasonDetailsId=");
            String id;
            if (seasonDetails == null) {
                id = "n/a";
            }
            else {
                id = seasonDetails.getId();
            }
            Log.d("ServiceManager", append.append(id).toString());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onSeasonDetailsFetched requestId " + n);
            return;
        }
        access$400.onSeasonDetailsFetched(seasonDetails, status);
    }
    
    @Override
    public void onSeasonsFetched(final int n, final List<SeasonDetails> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onSeasonsFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onSeasonsFetched requestedSeasons=" + list);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onSeasonsFetched requestId " + n);
            return;
        }
        access$400.onSeasonsFetched(list, status);
    }
    
    @Override
    public void onServiceReady(final int n, final Status status) {
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onServiceReady clientId=" + n + " res.getStatusCode()=" + status.getStatusCode());
        }
        this.this$0.mClientId = n;
        final ManagerStatusListener access$200 = this.this$0.mCallback;
        if (access$200 != null) {
            if (!status.isSucces()) {
                access$200.onManagerUnavailable(this.this$0, status);
                return;
            }
            this.this$0.mReady = true;
            access$200.onManagerReady(this.this$0, status);
        }
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final int n, final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            this.logShowDetailsInfo(status, n, showDetails);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onShowDetailsAndSeasonsFetched requestId " + n);
            return;
        }
        access$400.onShowDetailsAndSeasonsFetched(showDetails, list, status);
    }
    
    @Override
    public void onShowDetailsFetched(final int n, final ShowDetails showDetails, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            this.logShowDetailsInfo(status, n, showDetails);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onShowDetailsFetched requestId " + n);
            return;
        }
        access$400.onShowDetailsFetched(showDetails, status);
    }
    
    @Override
    public void onSimilarVideosFetched(final int n, final SearchVideoListProvider searchVideoListProvider, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onSimilarVideosFetched requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onSimilarVideosFetched requestId " + n);
            return;
        }
        access$400.onSimilarVideosFetched(searchVideoListProvider, status);
    }
    
    @Override
    public void onSurveyFetched(final int n, final Survey survey, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onSurveyFetched requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onSurveyFetched requestId " + n);
            return;
        }
        access$400.onSurveyFetched(survey, status);
    }
    
    @Override
    public void onVerified(final int n, final boolean b, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onVerified requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onVerified requestId " + n);
            return;
        }
        access$400.onVerified(b, status);
    }
    
    @Override
    public void onVideoHide(final int n, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onVideoHide requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onVideoHide requestId " + n);
            return;
        }
        access$400.onVideoHide(status);
    }
    
    @Override
    public void onVideoRatingSet(final int n, final UserRating userRating, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onVideoRatingSet requestId=" + n + " errorCode=" + status.getStatusCode());
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onVideoRatingSet requestId " + n);
            return;
        }
        access$400.onVideoRatingSet(userRating, status);
    }
    
    @Override
    public void onVideoSummaryFetched(final int n, final Video$Summary video$Summary, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onVideoSummaryFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onVideoSummaryFetched requestedMdp=" + video$Summary);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            Log.d("ServiceManager", "No callback for onVideoSummaryFetched requestId " + n);
            return;
        }
        access$400.onVideoSummaryFetched(video$Summary, status);
    }
    
    @Override
    public void onVideosFetched(final int n, final List<Video> list, final Status status) {
        this.updateStatusRequestId(status, n);
        if (Log.isLoggable()) {
            Log.d("ServiceManager", "onVideosFetched requestId=" + n + " errorCode=" + status.getStatusCode());
            Log.d("ServiceManager", "onVideosFetched requestedVideos=" + list);
        }
        final ManagerCallback access$400 = this.this$0.getManagerCallback(n);
        if (access$400 == null) {
            if (Log.isLoggable()) {
                Log.d("ServiceManager", "No callback for onVideosFetched requestId " + n);
            }
            return;
        }
        access$400.onVideosFetched(list, status);
    }
}
