// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import com.netflix.mediaclient.android.app.Status;
import android.widget.ListAdapter;
import android.widget.GridView;
import android.view.ViewStub;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.StaticGridView;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;

public class MovieDetailsFrag extends DetailsFrag<MovieDetails>
{
    private static final String EXTRA_VIDEO_ID = "video_id";
    private static final String TAG = "MovieDetailsFrag";
    private SimilarItemsGridViewAdapter adapter;
    private StaticGridView gridView;
    private boolean isLoading;
    private long requestId;
    private TextView similarShowsTitle;
    private int targetScrollYOffset;
    private String videoId;
    
    public MovieDetailsFrag() {
        this.isLoading = true;
    }
    
    public static MovieDetailsFrag create(final String s) {
        final MovieDetailsFrag movieDetailsFrag = new MovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        movieDetailsFrag.setArguments(arguments);
        return movieDetailsFrag;
    }
    
    private void fetchMovieData() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null || !serviceManager.isReady()) {
            Log.w("MovieDetailsFrag", "Manager is null/notReady - can't reload data");
            return;
        }
        this.isLoading = true;
        this.requestId = System.nanoTime();
        Log.v("MovieDetailsFrag", "Fetching data for movie ID: " + this.videoId);
        serviceManager.getBrowse().fetchMovieDetails(this.videoId, new MovieDetailsFrag$FetchMovieDetailsCallback(this, this.requestId));
    }
    
    private void scrollTo(final int n) {
        if (this.primaryView != null) {
            Log.v("MovieDetailsFrag", "Posting scroll to mdp frag, offset: " + n);
            this.primaryView.post((Runnable)new MovieDetailsFrag$2(this, n));
        }
    }
    
    @Override
    protected VideoDetailsViewGroup$DetailsStringProvider getDetailsStringProvider(final MovieDetails movieDetails) {
        return new MovieDetailsFrag$1(this, movieDetails);
    }
    
    public int getScrollYOffset() {
        if (this.primaryView == null) {
            return 0;
        }
        return this.primaryView.getScrollY();
    }
    
    @Override
    protected String getVideoId() {
        return this.videoId;
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        this.videoId = this.getArguments().getString("video_id");
        super.onCreate(bundle);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(2131361896);
        final View inflate = ((ViewStub)onCreateView.findViewById(2131165685)).inflate();
        inflate.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        this.similarShowsTitle = (TextView)inflate.findViewById(2131165645);
        (this.gridView = (StaticGridView)inflate.findViewById(2131165656)).setFocusable(false);
        this.adapter = new SimilarItemsGridViewAdapter(this.getActivity(), this.gridView, true);
        this.gridView.setAdapter((ListAdapter)this.adapter);
        return onCreateView;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        this.fetchMovieData();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.adapter.refreshImagesIfNecessary();
    }
    
    @Override
    protected void reloadData() {
        this.fetchMovieData();
    }
    
    public void setScrollYOffset(final int targetScrollYOffset) {
        this.targetScrollYOffset = targetScrollYOffset;
    }
    
    @Override
    protected void showDetailsView(final MovieDetails movieDetails) {
        super.showDetailsView(movieDetails);
        this.similarShowsTitle.setText((CharSequence)this.getString(2131493145, new Object[] { movieDetails.getTitle() }));
        this.similarShowsTitle.setVisibility(0);
        this.adapter.setData(movieDetails.getSimilars(), new TrackableObject(movieDetails.getSimilarsRequestId(), movieDetails.getSimilarsTrackId(), movieDetails.getSimilarsListPos()));
        this.scrollTo(this.targetScrollYOffset);
        this.targetScrollYOffset = 0;
    }
}
