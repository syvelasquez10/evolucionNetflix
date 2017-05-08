// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableObject;
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
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.ui.mdx.MementoMovieDetailsActivity;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DataUtil;
import android.os.Bundle;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;

public class MovieDetailsFrag extends DetailsFrag<MovieDetails> implements ILayoutManager
{
    private static final String EXTRA_LAYOUT_MANAGER_STATE = "layout_manager_state";
    protected static final String EXTRA_VIDEO_ID = "video_id";
    private static final String TAG = "MovieDetailsFrag";
    protected RecyclerViewHeaderAdapter adapter;
    private CopyrightView copyrightView;
    private GridLayoutManager gridLayoutManager;
    protected boolean isLoading;
    private Parcelable layoutManagerSavedState;
    protected int numColumns;
    protected DetailsPageParallaxScrollListener parallaxScroller;
    protected RecyclerView recyclerView;
    protected long requestId;
    private View rootContainer;
    protected String videoId;
    
    public MovieDetailsFrag() {
        this.isLoading = true;
    }
    
    private void addCopyrightAsFooter() {
        if (this.adapter != null) {
            this.copyrightView = CopyrightView.create(null, (Context)this.getActivity());
            if (this.copyrightView != null) {
                this.copyrightView.setGravityAsCenter();
                this.adapter.addFooterView(this.copyrightView.getView());
            }
        }
    }
    
    public static MovieDetailsFrag create(final String s) {
        final MovieDetailsFrag movieDetailsFrag = new MovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        movieDetailsFrag.setArguments(arguments);
        return movieDetailsFrag;
    }
    
    private void restoreLayoutManagerState() {
        if (DataUtil.areAnyNull("MovieDetailsFrag", "can't restore layout manager", this.layoutManagerSavedState, this.recyclerView, this.parallaxScroller)) {
            return;
        }
        if (Log.isLoggable()) {
            Log.v("MovieDetailsFrag", "Restoring layout manager state: " + this.layoutManagerSavedState);
        }
        this.recyclerView.getLayoutManager().onRestoreInstanceState(this.layoutManagerSavedState);
        this.layoutManagerSavedState = null;
        Log.v("MovieDetailsFrag", "Posting message to reset parallax views");
        this.recyclerView.post((Runnable)new MovieDetailsFrag$7(this));
    }
    
    private void setBackgroundResource(final int backgroundResource) {
        if (this.rootContainer != null) {
            this.rootContainer.setBackgroundResource(backgroundResource);
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
        serviceManager.getBrowse().fetchMovieDetails(this.videoId, null, new MovieDetailsFrag$FetchMovieDetailsCallback(this, this.requestId));
    }
    
    protected void findViews(final View view) {
        this.recyclerView = (RecyclerView)this.primaryView;
        this.rootContainer = view.findViewById(2131689869);
    }
    
    @Override
    protected VideoDetailsViewGroup$DetailsStringProvider getDetailsStringProvider(final MovieDetails movieDetails) {
        return new MovieDetailsFrag$6(this, movieDetails);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903257;
    }
    
    @Override
    public RecyclerView$LayoutManager getLayoutManager() {
        if (this.recyclerView == null) {
            return null;
        }
        return this.recyclerView.getLayoutManager();
    }
    
    protected int getNumColumns() {
        if (this.getActivity() instanceof MementoMovieDetailsActivity) {
            return 4;
        }
        return LomoConfig.computeStandardNumVideosPerPage(this.getNetflixActivity(), false);
    }
    
    @Override
    protected int getPrimaryViewId() {
        return 16908298;
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
        this.numColumns = this.getNumColumns();
        this.initDetailsViewGroup(onCreateView);
        if (bundle != null) {
            final Parcelable parcelable = bundle.getParcelable("layout_manager_state");
            if (parcelable != null) {
                this.setLayoutManagerSavedState(parcelable);
            }
        }
        this.setupRecyclerView();
        this.detailsViewGroup.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new MovieDetailsFrag$2(this));
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
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.gridLayoutManager != null) {
            bundle.putParcelable("layout_manager_state", this.gridLayoutManager.onSaveInstanceState());
        }
    }
    
    @Override
    protected void reloadData() {
        this.fetchMovieData();
    }
    
    public void scrollTop() {
        this.recyclerView.post((Runnable)new MovieDetailsFrag$1(this));
    }
    
    public void setExitTransition(final Transition exitTransition) {
        super.setExitTransition(exitTransition);
        this.setBackgroundResource(2131624168);
        if (exitTransition != null) {
            exitTransition.addListener((Transition$TransitionListener)new MovieDetailsFrag$3(this));
        }
    }
    
    @Override
    public void setLayoutManagerSavedState(final Parcelable layoutManagerSavedState) {
        this.layoutManagerSavedState = layoutManagerSavedState;
    }
    
    public void setVideoId(final String videoId) {
        this.videoId = videoId;
    }
    
    protected void setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.recyclerView != null) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                Log.v("MovieDetailsFrag", "Attaching parallax scroll listener to recyclerView");
                this.parallaxScroller = DetailsPageParallaxScrollListener.createDefault(null, this.recyclerView, new View[] { this.detailsViewGroup.getHeroImage() }, null, null);
                this.recyclerView.setOnScrollListener(this.parallaxScroller);
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
        this.adapter = new SimilarItemsGridViewAdapter(true, this.numColumns, new MovieDetailsFrag$4(this));
        this.addCopyrightAsFooter();
        this.adapter.addHeaderView((View)this.detailsViewGroup);
        this.recyclerView.setAdapter(this.adapter);
    }
    
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131362066), this.numColumns));
    }
    
    protected void setupRecyclerViewLayoutManager() {
        (this.gridLayoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns)).setSpanSizeLookup(new MovieDetailsFrag$5(this));
        this.recyclerView.setLayoutManager(this.gridLayoutManager);
    }
    
    @Override
    protected void showDetailsView(final MovieDetails movieDetails) {
        super.showDetailsView(movieDetails);
        this.adapter.setTrackable(new TrackableObject(movieDetails.getSimilarsRequestId(), movieDetails.getSimilarsTrackId(), movieDetails.getSimilarsListPos()));
        this.showSimsItems(movieDetails);
        if (this.recyclerView != null) {
            this.recyclerView.setVisibility(0);
        }
        if (this.copyrightView != null && StringUtils.isNotEmpty(movieDetails.getCopyright())) {
            this.copyrightView.update(movieDetails);
            this.copyrightView.getView().setVisibility(0);
        }
        this.restoreLayoutManagerState();
        this.onLoaded(CommonStatus.OK);
    }
    
    protected void showSimsItems(final MovieDetails movieDetails) {
        if (movieDetails.getSimilars().size() != 0) {
            this.adapter.setItems(movieDetails.getSimilars());
        }
        else if (this.getVideoDetailsViewGroup() != null) {
            this.getVideoDetailsViewGroup().hideRelatedTitle();
        }
    }
}
