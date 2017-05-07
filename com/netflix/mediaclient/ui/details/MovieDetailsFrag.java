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
import com.netflix.mediaclient.android.app.Status;
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
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;

public class MovieDetailsFrag extends DetailsFrag<MovieDetails>
{
    protected static final String EXTRA_VIDEO_ID = "video_id";
    private static final String TAG = "MovieDetailsFrag";
    protected RecyclerViewHeaderAdapter adapter;
    private boolean isLoading;
    protected int numColumns;
    protected RecyclerView recyclerView;
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
            this.primaryView.post((Runnable)new MovieDetailsFrag$4(this, n));
        }
    }
    
    private void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131361878), this.numColumns));
    }
    
    private void setupRecyclerViewLayoutManager() {
        final GridLayoutManager layoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns);
        layoutManager.setSpanSizeLookup(new MovieDetailsFrag$2(this));
        this.recyclerView.setLayoutManager(layoutManager);
    }
    
    protected void findViews(final View view) {
        this.recyclerView = (RecyclerView)view.findViewById(16908298);
    }
    
    @Override
    protected VideoDetailsViewGroup$DetailsStringProvider getDetailsStringProvider(final MovieDetails movieDetails) {
        return new MovieDetailsFrag$3(this, movieDetails);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903177;
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
        (this.detailsViewGroup = new VideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
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
        this.numColumns = this.retrieveNumColumns();
        this.initDetailsViewGroup(onCreateView);
        this.setupRecyclerView();
        this.setupDetailsPageParallaxScrollListener();
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
        ((SimilarItemsGridViewAdapter)this.adapter).refreshImagesIfNecessary();
    }
    
    @Override
    protected void reloadData() {
        this.fetchMovieData();
    }
    
    protected int retrieveNumColumns() {
        return LomoConfig.computeStandardNumVideosPerPage(this.getNetflixActivity(), false);
    }
    
    public void setScrollYOffset(final int targetScrollYOffset) {
        this.targetScrollYOffset = targetScrollYOffset;
    }
    
    protected void setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.recyclerView != null) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final Drawable drawable = this.getActivity().getResources().getDrawable(2130837560);
                drawable.setAlpha(0);
                this.recyclerView.setOnScrollListener(new DetailsPageParallaxScrollListener(null, this.recyclerView, (View)this.detailsViewGroup.getHeroImage(), null, drawable));
            }
        }
    }
    
    protected void setupRecyclerView() {
        if (this.recyclerView == null) {
            return;
        }
        this.recyclerView.setFocusable(false);
        this.recyclerView.setVisibility(4);
        this.setupRecyclerViewItemDecoration();
        this.setupRecyclerViewLayoutManager();
        this.setupRecyclerViewAdapter();
    }
    
    protected void setupRecyclerViewAdapter() {
        (this.adapter = new SimilarItemsGridViewAdapter(this.recyclerView, true, this.numColumns, new MovieDetailsFrag$1(this))).addHeaderView((View)this.detailsViewGroup);
        this.recyclerView.setAdapter(this.adapter);
    }
    
    @Override
    protected void showDetailsView(final MovieDetails movieDetails) {
        super.showDetailsView(movieDetails);
        if (this.similarShowsTitle != null) {
            this.similarShowsTitle.setText((CharSequence)this.getString(2131493152, new Object[] { movieDetails.getTitle() }));
            this.similarShowsTitle.setVisibility(0);
        }
        this.adapter.setTrackable(new TrackableObject(movieDetails.getSimilarsRequestId(), movieDetails.getSimilarsTrackId(), movieDetails.getSimilarsListPos()));
        this.adapter.setItems(movieDetails.getSimilars());
        if (this.recyclerView != null) {
            this.recyclerView.setVisibility(0);
        }
        this.scrollTo(this.targetScrollYOffset);
        this.targetScrollYOffset = 0;
    }
}
