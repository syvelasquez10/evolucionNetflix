// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.model.branches.FalkorVideo;
import java.util.HashMap;
import com.netflix.model.branches.FalkorActorStill;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import java.util.Map;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class RoleDetailsFrag extends NetflixFrag implements LoadingStatus, ErrorWrapper$Callback, ManagerStatusListener, DetailsActivity$Reloader, ILayoutManager
{
    private static final String EXTRA_ACTOR_ID = "actor_id";
    public static final int FOOTER_HEIGHT = 200;
    private static final long LONG_VIEW_ANIMATION_DURATION_MS = 200L;
    public static final String RDP_ACTION_CLOSE = "com.netflix.mediaclient.intent.action.RDP_CLOSE";
    private static final String TAG = "RoleDetailsFrag";
    private RoleDetailsFrag$ActorDetailsView actorDetailsView;
    private String actorId;
    private RecyclerViewHeaderAdapter adapter;
    private Map<String, AddToListData$StateListener> addToListeners;
    private View caret;
    private long detailsRequestId;
    protected final ErrorWrapper$Callback errorCallback;
    private GridLayoutManager gridLayoutManager;
    private int imageTint;
    private boolean isLoading;
    private Parcelable layoutManagerSavedState;
    private LoadingAndErrorWrapper leWrapper;
    private int numColumns;
    private DetailsPageParallaxScrollListener parallaxScroller;
    private RecyclerView recyclerView;
    private long relatedRequestId;
    private FalkorActorStill still;
    private int stillImageHeight;
    String videoId;
    
    public RoleDetailsFrag() {
        this.addToListeners = new HashMap<String, AddToListData$StateListener>();
        this.errorCallback = new RoleDetailsFrag$1(this);
        this.numColumns = 1;
    }
    
    private void addMyListener(final FalkorVideo falkorVideo, final AddToListData$StateListener addToListData$StateListener) {
        this.addToListeners.put(falkorVideo.getId(), addToListData$StateListener);
    }
    
    public static RoleDetailsFrag create(final String s) {
        final RoleDetailsFrag roleDetailsFrag = new RoleDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("actor_id", s);
        roleDetailsFrag.setArguments(arguments);
        return roleDetailsFrag;
    }
    
    private void fetchData() {
        if (this.getServiceManager() == null) {
            Log.w("RoleDetailsFrag", "Manager is null - can't reload data");
            return;
        }
        this.isLoading = true;
        this.detailsRequestId = System.nanoTime();
        this.relatedRequestId = System.nanoTime();
        this.leWrapper.showLoadingView(true);
        this.adapter.clearData();
        if (this.actorDetailsView != null) {
            this.actorDetailsView.onNetFlixLabel.setVisibility(4);
        }
        Log.v("RoleDetailsFrag", "Fetching data for actor ID: " + this.actorId);
        ((NetflixActivity)this.getActivity()).getServiceManager().getBrowse().fetchPersonDetail(this.actorId, new RoleDetailsFrag$FetchActorDetailsAndRelatedCallback(this, this.detailsRequestId), this.videoId);
        ((NetflixActivity)this.getActivity()).getServiceManager().getBrowse().fetchPersonRelated(this.actorId, new RoleDetailsFrag$FetchActorDetailsAndRelatedCallback(this, this.relatedRequestId));
    }
    
    private void removeMyListeners() {
        final ServiceManager serviceManager = this.getNetflixActivity().getServiceManager();
        if (serviceManager != null) {
            for (final String s : this.addToListeners.keySet()) {
                final AddToListData$StateListener addToListData$StateListener = this.addToListeners.get(s);
                if (addToListData$StateListener != null) {
                    serviceManager.unregisterAddToMyListListener(s, addToListData$StateListener);
                }
            }
        }
    }
    
    private void rotateCaretTo(final int n) {
        if (this.caret != null) {
            this.caret.animate().rotation((float)n).setDuration(200L).start();
        }
    }
    
    private void setupRecyclerViewHeader() {
        if (this.adapter == null || this.getActivity() == null || this.isDetached()) {
            return;
        }
        this.actorDetailsView = new RoleDetailsFrag$ActorDetailsView(this, (Context)this.getActivity());
        this.adapter.addHeaderView((View)this.actorDetailsView);
        final View view = new View((Context)this.getActivity());
        view.setLayoutParams(new ViewGroup$LayoutParams(-1, 200));
        this.adapter.addHeaderView((View)this.actorDetailsView);
        this.adapter.addFooterView(view);
    }
    
    private void setupRecyclerViewItemDecoration() {
        if (this.numColumns > 1) {
            this.recyclerView.addItemDecoration(new ItemDecorationEdgePadding(this.getActivity().getResources().getDimensionPixelOffset(2131361871), this.numColumns, 2));
            return;
        }
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131361869), this.numColumns));
    }
    
    protected void findViews(final View view) {
        this.recyclerView = (RecyclerView)view.findViewById(16908298);
    }
    
    @Override
    public RecyclerView$LayoutManager getLayoutManager() {
        if (this.recyclerView == null) {
            return null;
        }
        return this.recyclerView.getLayoutManager();
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        this.reload();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        if (this.getArguments() != null) {
            this.actorId = this.getArguments().getString("actor_id");
        }
        super.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("RoleDetailsFrag", "Creating new frag view...");
        final ViewGroup viewGroup2 = (ViewGroup)layoutInflater.inflate(2130903277, (ViewGroup)null, false);
        this.findViews((View)viewGroup2);
        this.leWrapper = new LoadingAndErrorWrapper((View)viewGroup2, this.errorCallback);
        this.setupRecyclerView();
        this.actorDetailsView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new RoleDetailsFrag$2(this));
        return (View)viewGroup2;
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        this.removeMyListeners();
    }
    
    public void onHiddenChanged(final boolean b) {
        super.onHiddenChanged(b);
        if (b) {
            this.rotateCaretTo(0);
        }
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.reload();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
    }
    
    @Override
    public void onRetryRequested() {
    }
    
    @Override
    public void reload() {
        this.fetchData();
    }
    
    public void resetScroll() {
        if (this.recyclerView != null) {
            if (this.parallaxScroller != null && this.parallaxScroller.getScrollDeltaY() != 0) {
                this.recyclerView.smoothScrollToPosition(0);
            }
            ((RecyclerViewHeaderAdapter)this.recyclerView.getAdapter()).clearItemChecked();
        }
    }
    
    public void setActorId(final String actorId) {
        if (this.caret != null) {
            this.caret.postDelayed((Runnable)new RoleDetailsFrag$3(this), 500L);
        }
        this.actorId = actorId;
    }
    
    public void setImageTint(final int imageTint) {
        this.imageTint = imageTint;
    }
    
    @Override
    public void setLayoutManagerSavedState(final Parcelable layoutManagerSavedState) {
        this.layoutManagerSavedState = layoutManagerSavedState;
    }
    
    @Override
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback loadingStatus$LoadingStatusCallback) {
    }
    
    public void setStillImageHeight(final int stillImageHeight) {
        this.stillImageHeight = stillImageHeight;
    }
    
    public void setVideoId(final String videoId) {
        this.videoId = videoId;
    }
    
    protected void setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.recyclerView != null && this.getNetflixActivity().getNetflixActionBar() != null) {
            Log.v("RoleDetailsFrag", "Attaching parallax scroll listener to recyclerView");
            this.parallaxScroller = DetailsPageParallaxScrollListener.createParallaxOnly(null, this.recyclerView, new View[] { this.actorDetailsView.actorStillImg }, null, null);
            this.recyclerView.setOnScrollListener(this.parallaxScroller);
        }
    }
    
    protected void setupRecyclerView() {
        if (this.recyclerView == null) {
            return;
        }
        this.recyclerView.setBackgroundResource(2131624094);
        this.recyclerView.setFocusable(false);
        this.recyclerView.setVisibility(4);
        this.setupRecyclerViewLayoutManager();
        this.setupRecyclerViewAdapter();
        this.setupRecyclerViewItemDecoration();
        this.setupRecyclerViewHeader();
        this.recyclerView.setAdapter(this.adapter);
    }
    
    protected void setupRecyclerViewAdapter() {
        this.adapter = new SimilarItemsGridViewAdapter(true, 1, new RoleDetailsFrag$4(this));
    }
    
    protected void setupRecyclerViewLayoutManager() {
        if (DeviceUtils.isLandscape((Context)this.getActivity()) || DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            this.numColumns = 2;
        }
        (this.gridLayoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns)).setSpanSizeLookup(new RoleDetailsFrag$5(this, this.numColumns));
        this.recyclerView.setLayoutManager(this.gridLayoutManager);
    }
}
