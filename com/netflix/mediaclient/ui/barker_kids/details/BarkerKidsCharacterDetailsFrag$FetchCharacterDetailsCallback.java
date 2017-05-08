// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.details;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsFrag$HeroSlideshow;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class BarkerKidsCharacterDetailsFrag$FetchCharacterDetailsCallback extends LoggingManagerCallback
{
    final /* synthetic */ BarkerKidsCharacterDetailsFrag this$0;
    
    public BarkerKidsCharacterDetailsFrag$FetchCharacterDetailsCallback(final BarkerKidsCharacterDetailsFrag this$0) {
        this.this$0 = this$0;
        super("KidsCharacterDetailsFrag");
    }
    
    private void fetchMovieDetails() {
        final ServiceManager serviceManager = this.this$0.getServiceManager();
        if (serviceManager == null) {
            Log.w("KidsCharacterDetailsFrag", "Manager is null - can't get movie details");
            return;
        }
        if (this.this$0.getNetflixActivity() == null) {
            Log.w("KidsCharacterDetailsFrag", "Activity is null - can't get movie details");
            return;
        }
        if (this.this$0.getShowId() == null) {
            Log.w("KidsCharacterDetailsFrag", "show ID is null - can't get movie details");
            return;
        }
        this.this$0.isLoading = true;
        this.this$0.requestId = System.nanoTime();
        if (Log.isLoggable()) {
            Log.v("KidsCharacterDetailsFrag", "Fetching data for show ID: " + this.this$0.getShowId());
        }
        serviceManager.getBrowse().fetchMovieDetails(this.this$0.showId, null, new BarkerKidsCharacterDetailsFrag$FetchCharacterDetailsCallback$FetchMovieDataCallback(this, this.this$0.requestId));
    }
    
    Video getRecommendedMovie(final List<Video> list) {
        for (final Video video : list) {
            if (video.getType() == VideoType.MOVIE) {
                return video;
            }
        }
        return null;
    }
    
    Video getRecommendedShow(final List<Video> list) {
        for (final Video video : list) {
            if (video.getType() == VideoType.SHOW) {
                return video;
            }
        }
        return null;
    }
    
    Video getRecommendedVideo(final List<Video> list) {
        Video video;
        if ((video = this.getRecommendedShow(list)) == null) {
            video = this.getRecommendedMovie(list);
        }
        return video;
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        super.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, status);
        this.this$0.isLoading = false;
        if (status.isError()) {
            Log.w("KidsCharacterDetailsFrag", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        this.this$0.kidsCharacterDetails = kidsCharacterDetails;
        if (kidsCharacterDetails == null) {
            Log.v("KidsCharacterDetailsFrag", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        final List<Video> gallery = kidsCharacterDetails.getGallery();
        if (gallery.size() == 0) {
            Log.v("KidsCharacterDetailsFrag", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        this.this$0.setupDismissClick();
        if (gallery.size() > 1) {
            final Video recommendedVideo = this.getRecommendedVideo(gallery);
            if (recommendedVideo != null) {
                this.this$0.showId = recommendedVideo.getId();
                this.this$0.shouldRenderAsSDP = false;
                if (recommendedVideo.getType() == VideoType.SHOW) {
                    this.this$0.fetchShowDetailsAndSeasons();
                }
                else if (recommendedVideo.getType() == VideoType.MOVIE) {
                    this.fetchMovieDetails();
                }
            }
        }
        else {
            final Video video = gallery.get(0);
            if (video.getType() == VideoType.SHOW) {
                this.this$0.showId = video.getId();
                this.this$0.shouldRenderAsSDP = true;
                this.this$0.setupSeasonsSpinnerGroupLocal();
                this.this$0.fetchShowDetailsAndSeasons();
            }
            else if (video.getType() == VideoType.MOVIE) {
                DetailsActivityLauncher.show(this.this$0.getNetflixActivity(), video, PlayContext.EMPTY_CONTEXT, "CharactorClickListener");
            }
        }
        this.this$0.setupDetailsPageParallaxScrollListenerLocal();
    }
}
