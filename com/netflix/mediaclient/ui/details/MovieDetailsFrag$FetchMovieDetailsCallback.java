// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import java.util.Collection;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableObject;
import com.netflix.mediaclient.ui.offline.TutorialHelper;
import com.netflix.android.tooltips.Tooltip;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.transition.Transition$TransitionListener;
import android.transition.Transition;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.ui.mdx.MementoMovieDetailsActivity;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.DataUtil;
import android.os.Bundle;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.offline.TutorialHelper$Tutorialable;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class MovieDetailsFrag$FetchMovieDetailsCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ MovieDetailsFrag this$0;
    
    public MovieDetailsFrag$FetchMovieDetailsCallback(final MovieDetailsFrag this$0, final long requestId) {
        this.this$0 = this$0;
        super("MovieDetailsFrag");
        this.requestId = requestId;
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        super.onMovieDetailsFetched(movieDetails, status);
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getNetflixActivity())) {
            Log.v("MovieDetailsFrag", "Activity state is invalid");
            return;
        }
        if (this.requestId != this.this$0.requestId || this.this$0.isDestroyed()) {
            Log.v("MovieDetailsFrag", "Ignoring stale callback");
            return;
        }
        this.this$0.isLoading = false;
        if (status.isError()) {
            Log.w("MovieDetailsFrag", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        if (movieDetails == null) {
            Log.v("MovieDetailsFrag", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        if (Log.isLoggable()) {
            Log.v("MovieDetailsFrag", "evidence glyph: " + movieDetails.getEvidenceGlyph() + ", evidence text: " + movieDetails.getEvidenceText());
        }
        this.this$0.showDetailsView(movieDetails);
        DPPrefetchABTestUtils.reportDPMetadataFetchedEvent(status);
    }
}
