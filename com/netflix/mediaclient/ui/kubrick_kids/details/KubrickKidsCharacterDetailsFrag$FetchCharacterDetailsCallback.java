// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.ShowDetailsFrag$ShowDetailsStringProvider;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.content.Context;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class KubrickKidsCharacterDetailsFrag$FetchCharacterDetailsCallback extends LoggingManagerCallback
{
    final /* synthetic */ KubrickKidsCharacterDetailsFrag this$0;
    
    public KubrickKidsCharacterDetailsFrag$FetchCharacterDetailsCallback(final KubrickKidsCharacterDetailsFrag this$0) {
        this.this$0 = this$0;
        super("KidsCharacterDetailsFrag");
    }
    
    Video getRecommendedMovie() {
        for (final Video video : this.this$0.kidsCharacterDetails.getGallery()) {
            if (video.getType() == VideoType.MOVIE) {
                return video;
            }
        }
        return null;
    }
    
    Video getRecommendedShow() {
        for (final Video video : this.this$0.kidsCharacterDetails.getGallery()) {
            if (video.getType() == VideoType.SHOW) {
                return video;
            }
        }
        return null;
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        super.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, status);
        if (status.isError()) {
            Log.w("KidsCharacterDetailsFrag", "Invalid status code");
            this.this$0.showErrorView();
        }
        else {
            this.this$0.kidsCharacterDetails = kidsCharacterDetails;
            if (kidsCharacterDetails == null || kidsCharacterDetails.getGallery().size() == 0) {
                Log.v("KidsCharacterDetailsFrag", "No details in response");
                this.this$0.showErrorView();
                return;
            }
            if (kidsCharacterDetails.getGallery().size() > 1) {
                final Video recommendedShow = this.getRecommendedShow();
                if (recommendedShow != null) {
                    this.this$0.showId = recommendedShow.getId();
                    this.this$0.shouldRenderAsSDP = false;
                    this.this$0.fetchShowDetailsAndSeasons();
                    return;
                }
                final Video recommendedMovie = this.getRecommendedMovie();
                if (recommendedMovie != null) {
                    DetailsActivityLauncher.show(this.this$0.getNetflixActivity(), recommendedMovie, PlayContext.EMPTY_CONTEXT, "CharactorClickListener");
                }
            }
            else {
                final Video video = kidsCharacterDetails.getGallery().get(0);
                if (video.getType() == VideoType.SHOW) {
                    this.this$0.showId = video.getId();
                    this.this$0.shouldRenderAsSDP = true;
                    this.this$0.fetchShowDetailsAndSeasons();
                    return;
                }
                if (video.getType() == VideoType.MOVIE) {
                    DetailsActivityLauncher.show(this.this$0.getNetflixActivity(), video, PlayContext.EMPTY_CONTEXT, "CharactorClickListener");
                }
            }
        }
    }
}
