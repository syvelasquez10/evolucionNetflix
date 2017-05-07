// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import java.util.List;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.model.SearchVideoList;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.os.Bundle;
import android.util.Pair;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import android.widget.ScrollView;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import java.io.Serializable;
import android.widget.AbsListView;
import android.widget.AbsListView$OnScrollListener;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.ListAdapter;
import android.widget.GridView;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.android.widget.StaticGridView;
import com.netflix.mediaclient.android.widget.LoggingScrollView;
import com.netflix.mediaclient.ui.common.SearchSimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SearchQueryDetailsActivity extends NetflixActivity
{
    private static final String EXTRA_ID = "extra_id";
    private static final String EXTRA_ORIGINAL_SEARCH_TERM = "extra_original_query";
    private static final String EXTRA_REFERNCE_ID = "extra_reference_id";
    private static final String EXTRA_TITLE = "extra_title";
    private static final String EXTRA_TYPE = "extra_type";
    private static final int NUM_SIMS_TO_FETCH = 40;
    private static final String TAG = "SearchQueryDetailsActivity";
    private SearchSimilarItemsGridViewAdapter adapter;
    private LoggingScrollView content;
    private StaticGridView gridView;
    private String id;
    private boolean isLoading;
    private final ErrorWrapper.Callback leCallback;
    private LoadingAndErrorWrapper leWrapper;
    private View loadingWrapper;
    private ServiceManager manager;
    private IClientLogging.ModalView nonModalView;
    private String originalSearchTerm;
    private String referenceId;
    private long requestId;
    private String title;
    private TextView titleView;
    private View topSpacer;
    private SearchQueryDetailsType type;
    
    public SearchQueryDetailsActivity() {
        this.isLoading = true;
        this.leCallback = new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                SearchQueryDetailsActivity.this.reloadData();
            }
        };
    }
    
    private void fetchData() {
        if (this.manager == null || !this.manager.isReady()) {
            Log.w("SearchQueryDetailsActivity", "Manager is null/notReady - can't load data");
            return;
        }
        this.isLoading = true;
        this.requestId = System.nanoTime();
        if (this.type == SearchQueryDetailsType.PERSON) {
            Log.v("SearchQueryDetailsActivity", "Fetching data for person, Id: " + this.id);
            this.manager.getBrowse().fetchSimilarVideosForPerson(this.id, 40, new OnSimsFetchedCallback(this.requestId), this.originalSearchTerm);
            return;
        }
        if (this.type == SearchQueryDetailsType.SEARCH_SUGGESTION) {
            Log.v("SearchQueryDetailsActivity", "Fetching data for suggestion, Id: " + this.id);
            this.manager.getBrowse().fetchSimilarVideosForQuerySuggestion(this.id, 40, new OnSimsFetchedCallback(this.requestId), this.originalSearchTerm);
            return;
        }
        throw new IllegalStateException("Bad state");
    }
    
    private void findViews() {
        this.titleView = (TextView)this.findViewById(2131165630);
        this.gridView = (StaticGridView)this.findViewById(2131165638);
        this.loadingWrapper = this.findViewById(2131165617);
        this.content = (LoggingScrollView)this.findViewById(2131165618);
        this.topSpacer = this.findViewById(2131165637);
    }
    
    private String getTitleForType(final SearchQueryDetailsType searchQueryDetailsType, final String s) {
        if (searchQueryDetailsType == SearchQueryDetailsType.PERSON) {
            return s;
        }
        if (searchQueryDetailsType == SearchQueryDetailsType.SEARCH_SUGGESTION) {
            return this.getString(2131493291, new Object[] { s });
        }
        throw new IllegalStateException("Bad type");
    }
    
    private void reloadData() {
        this.showLoadingView();
        this.fetchData();
    }
    
    private void setupGridView() {
        this.gridView.setLayoutAnimation(AnimationUtils.createGridLayoutAnimator((Context)this));
        this.gridView.setFocusable(false);
        this.adapter = new SearchSimilarItemsGridViewAdapter(this, this.gridView, false);
        this.gridView.setAdapter((ListAdapter)this.adapter);
        this.setupScrollViewLogging();
    }
    
    private void setupGridViewObserver() {
        this.gridView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                SearchQueryDetailsActivity.this.fireImpressionEvents();
                if (SearchQueryDetailsActivity.this.gridView.getCount() > 0) {
                    ViewUtils.removeGlobalLayoutListener((View)SearchQueryDetailsActivity.this.gridView, (ViewTreeObserver$OnGlobalLayoutListener)this);
                }
            }
        });
    }
    
    private void setupLoading() {
        this.leWrapper = new LoadingAndErrorWrapper(this.loadingWrapper, this.leCallback);
    }
    
    private void setupMetaData() {
        final Intent intent = this.getIntent();
        this.type = (SearchQueryDetailsType)intent.getSerializableExtra("extra_type");
        this.id = intent.getStringExtra("extra_id");
        this.title = intent.getStringExtra("extra_title");
        this.originalSearchTerm = intent.getStringExtra("extra_original_query");
        this.referenceId = intent.getStringExtra("extra_reference_id");
        final String stringExtra = intent.getStringExtra("view");
        if (StringUtils.isNotEmpty(stringExtra)) {
            this.nonModalView = IClientLogging.ModalView.valueOf(stringExtra);
        }
    }
    
    private void setupScrollViewLogging() {
        this.gridView.setOnScrollListener((AbsListView$OnScrollListener)new AbsListView$OnScrollListener() {
            public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
            }
            
            public void onScrollStateChanged(final AbsListView absListView, final int n) {
                if (n == 0 && SearchQueryDetailsActivity.this.gridView.getCount() > 0) {
                    SearchQueryDetailsActivity.this.fireImpressionEvents();
                }
            }
        });
        if (this.content != null) {
            this.content.setOnScrollStopListener((LoggingScrollView.OnScrollStopListener)new LoggingScrollView.OnScrollStopListener() {
                @Override
                public void log() {
                    SearchQueryDetailsActivity.this.fireImpressionEvents();
                }
            });
        }
    }
    
    private void setupViews() {
        final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(2131361896);
        this.content.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        this.content.setVisibility(4);
        this.topSpacer.setVisibility(0);
        this.titleView.setText((CharSequence)this.getTitleForType(this.type, this.title));
        this.titleView.setVisibility(0);
    }
    
    public static void show(final Activity activity, final SearchQueryDetailsType searchQueryDetailsType, final String s, final String s2, final String s3, final String s4, final IClientLogging.ModalView modalView) {
        activity.startActivity(new Intent((Context)activity, (Class)SearchQueryDetailsActivity.class).putExtra("extra_type", (Serializable)searchQueryDetailsType).putExtra("extra_id", s).putExtra("extra_reference_id", s4).putExtra("view", modalView.name()).putExtra("extra_original_query", s3).putExtra("extra_title", s2));
    }
    
    private void showContentView() {
        this.leWrapper.hide(true);
        AnimationUtils.showView((View)this.content, true);
    }
    
    private void showErrorView() {
        this.leWrapper.showErrorView(true);
        AnimationUtils.hideView((View)this.content, true);
    }
    
    private void showLoadingView() {
        this.leWrapper.showLoadingView(true);
        AnimationUtils.hideView((View)this.content, true);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                SearchQueryDetailsActivity.this.manager = serviceManager;
                SearchQueryDetailsActivity.this.fetchData();
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
                SearchQueryDetailsActivity.this.manager = null;
            }
        };
    }
    
    void fireImpressionEvents() {
        boolean b = true;
        boolean b2;
        if (this.content == null) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (this.content.getChildCount() != 0) {
            b = false;
        }
        if (!(b2 & b)) {
            final Pair<Integer, Integer> visiblePositions = ViewUtils.getVisiblePositions(this.gridView, this.content);
            if (visiblePositions != null) {
                SearchLogUtils.reportSearchImpression(1L, this.gridView.getContext(), IClientLogging.ModalView.titleResults, this.referenceId, null, (int)visiblePositions.first, (int)visiblePositions.second, this.nonModalView);
            }
        }
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.searchResults;
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setupMetaData();
        this.setContentView(2130903168);
        this.findViews();
        this.setupLoading();
        this.setupViews();
        this.setupGridView();
        this.setupGridViewObserver();
    }
    
    private class OnSimsFetchedCallback extends LoggingManagerCallback
    {
        private final long requestId;
        
        public OnSimsFetchedCallback(final long requestId) {
            super("SearchQueryDetailsActivity");
            this.requestId = requestId;
        }
        
        @Override
        public void onSimilarVideosFetched(final SearchVideoList list, final Status status) {
            super.onSimilarVideosFetched(list, status);
            if (this.requestId != SearchQueryDetailsActivity.this.requestId || AndroidUtils.isActivityFinishedOrDestroyed(SearchQueryDetailsActivity.this)) {
                Log.v("SearchQueryDetailsActivity", "Ignoring stale callback");
                return;
            }
            SearchQueryDetailsActivity.this.isLoading = false;
            if (status.isError()) {
                Log.w("SearchQueryDetailsActivity", "Invalid status code");
                SearchQueryDetailsActivity.this.showErrorView();
                return;
            }
            if (list == null || list.size() < 1) {
                Log.v("SearchQueryDetailsActivity", "No details in response");
                SearchQueryDetailsActivity.this.showErrorView();
                return;
            }
            SearchQueryDetailsActivity.this.referenceId = list.getVideosListTrackable().getReferenceId();
            SearchQueryDetailsActivity.this.adapter.setData(list, PlayContext.EMPTY_CONTEXT);
            SearchQueryDetailsActivity.this.showContentView();
        }
    }
    
    public enum SearchQueryDetailsType
    {
        PERSON, 
        SEARCH_SUGGESTION;
    }
}
