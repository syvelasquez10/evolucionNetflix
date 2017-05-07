// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import android.widget.ListAdapter;
import android.widget.GridView;
import android.view.ViewStub;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.StaticGridView;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
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
        this.this$0.showDetailsView(movieDetails);
    }
}
