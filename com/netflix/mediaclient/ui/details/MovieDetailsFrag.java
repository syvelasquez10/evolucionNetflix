// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableObject;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;

public class MovieDetailsFrag extends DetailsFrag<MovieDetails>
{
    protected static final String EXTRA_VIDEO_ID = "video_id";
    private static final String TAG = "MovieDetailsFrag";
    protected RecyclerViewHeaderAdapter adapter;
    private CopyrightView copyrightView;
    private boolean isLoading;
    protected int numColumns;
    protected RecyclerView recyclerView;
    private long requestId;
    private int targetScrollYOffset;
    protected String videoId;
    
    public MovieDetailsFrag() {
        this.isLoading = true;
    }
    
    private void addCopyrightAsFooter() {
        if (this.adapter == null) {
            return;
        }
        (this.copyrightView = CopyrightView.create(null, (Context)this.getActivity())).setGravityAsCenter();
        this.adapter.addFooterView(this.copyrightView.getView());
    }
    
    public static MovieDetailsFrag create(final String s) {
        final MovieDetailsFrag movieDetailsFrag = new MovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        movieDetailsFrag.setArguments(arguments);
        return movieDetailsFrag;
    }
    
    private void scrollTo(final int n) {
        if (this.primaryView != null) {
            Log.v("MovieDetailsFrag", "Posting scroll to mdp frag, offset: " + n);
            this.primaryView.post((Runnable)new MovieDetailsFrag$5(this, n));
        }
    }
    
    protected void fetchMovieData() {
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
    
    protected void findViews(final View view) {
        this.recyclerView = (RecyclerView)view.findViewById(16908298);
    }
    
    @Override
    protected VideoDetailsViewGroup$DetailsStringProvider getDetailsStringProvider(final MovieDetails movieDetails) {
        return new MovieDetailsFrag$4(this, movieDetails);
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
        this.detailsViewGroup.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new MovieDetailsFrag$1(this));
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
        SimilarItemsGridViewAdapter.refreshImagesIfNecessary(this.recyclerView);
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
    
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.recyclerView != null) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final DetailsPageParallaxScrollListener onScrollListener = new DetailsPageParallaxScrollListener(null, this.recyclerView, new View[] { this.detailsViewGroup.getHeroImage() }, null, this.recyclerView.getResources().getColor(2131558577), 0, null);
                this.recyclerView.setOnScrollListener(onScrollListener);
                return onScrollListener;
            }
        }
        return null;
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
        this.adapter = new SimilarItemsGridViewAdapter(true, this.numColumns, new MovieDetailsFrag$2(this));
        this.addCopyrightAsFooter();
        this.adapter.addHeaderView((View)this.detailsViewGroup);
        this.recyclerView.setAdapter(this.adapter);
    }
    
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131296442), this.numColumns));
    }
    
    protected void setupRecyclerViewLayoutManager() {
        final GridLayoutManager layoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns);
        layoutManager.setSpanSizeLookup(new MovieDetailsFrag$3(this));
        this.recyclerView.setLayoutManager(layoutManager);
    }
    
    @Override
    protected void showDetailsView(final MovieDetails movieDetails) {
        super.showDetailsView(movieDetails);
        this.adapter.setTrackable(new TrackableObject(movieDetails.getSimilarsRequestId(), movieDetails.getSimilarsTrackId(), movieDetails.getSimilarsListPos()));
        if (movieDetails.getSimilars().size() != 0) {
            this.adapter.setItems(movieDetails.getSimilars());
        }
        else if (this.getVideoDetailsViewGroup() != null) {
            this.getVideoDetailsViewGroup().hideRelatedTitle();
        }
        if (this.recyclerView != null) {
            this.recyclerView.setVisibility(0);
        }
        if (this.copyrightView != null && StringUtils.isNotEmpty(movieDetails.getCopyright())) {
            this.copyrightView.update(movieDetails);
            this.copyrightView.getView().setVisibility(0);
        }
        this.scrollTo(this.targetScrollYOffset);
        this.targetScrollYOffset = 0;
    }
}
