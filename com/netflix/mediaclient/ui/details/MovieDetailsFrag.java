// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.widget.AbsListView$OnScrollListener;
import android.widget.AbsListView;
import com.netflix.mediaclient.ui.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.android.app.Status;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.GridView;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;

public class MovieDetailsFrag extends DetailsFrag<MovieDetails>
{
    protected static final String EXTRA_VIDEO_ID = "video_id";
    private static final String TAG = "MovieDetailsFrag";
    protected SimilarItemsGridViewAdapter adapter;
    protected GridView gridView;
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
    
    protected void findViews(final View view) {
        this.gridView = (StickyGridHeadersGridView)view.findViewById(16908298);
    }
    
    @Override
    protected VideoDetailsViewGroup$DetailsStringProvider getDetailsStringProvider(final MovieDetails movieDetails) {
        return new MovieDetailsFrag$1(this, movieDetails);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903200;
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
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new MovieDetailsFrag$MovieDetailVideoDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
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
        this.findViews(onCreateView);
        this.setupGridView();
        this.initDetailsViewGroup(onCreateView);
        this.adapter = new MovieDetailsFrag$SimGridAdapter(this, this.getNetflixActivity(), this.gridView);
        ((MovieDetailsFrag$SimGridAdapter)this.adapter).setDetailsHeaderView((ViewGroup)this.detailsViewGroup);
        this.gridView.setAdapter((ListAdapter)this.adapter);
        this.setupDetailsPageParallaxScrollListener();
        return onCreateView;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        serviceManager.registerAddToMyListListener(this.getVideoId(), new MovieDetailsFrag$MovieDetailaStateListener(this));
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
    
    protected void setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.gridView != null) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final Drawable drawable = this.getActivity().getResources().getDrawable(2130837560);
                drawable.setAlpha(0);
                this.gridView.setOnScrollListener((AbsListView$OnScrollListener)new DetailsPageParallaxScrollListener(null, (AbsListView)this.gridView, (View)this.detailsViewGroup.getHeroImage(), null, drawable));
            }
        }
    }
    
    protected void setupGridView() {
        if (this.gridView == null) {
            return;
        }
        this.gridView.setFocusable(false);
        this.gridView.setVisibility(4);
        ((StickyGridHeadersGridView)this.gridView).setAreHeadersSticky(false);
    }
    
    @Override
    protected void showDetailsView(final MovieDetails movieDetails) {
        super.showDetailsView(movieDetails);
        if (this.similarShowsTitle != null) {
            this.similarShowsTitle.setText((CharSequence)this.getString(2131493152, new Object[] { movieDetails.getTitle() }));
            this.similarShowsTitle.setVisibility(0);
        }
        this.adapter.setData(movieDetails.getSimilars(), new TrackableObject(movieDetails.getSimilarsRequestId(), movieDetails.getSimilarsTrackId(), movieDetails.getSimilarsListPos()));
        if (this.gridView != null) {
            this.gridView.setVisibility(0);
        }
        this.scrollTo(this.targetScrollYOffset);
        this.targetScrollYOffset = 0;
    }
}
