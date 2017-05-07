// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import android.view.View;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
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
        if (Log.isLoggable("MovieDetailsFrag", 2)) {
            Log.v("MovieDetailsFrag", "evidence glyph: " + movieDetails.getEvidenceGlyph() + ", evidence text: " + movieDetails.getEvidenceText());
        }
        this.this$0.showDetailsView(movieDetails);
    }
}
