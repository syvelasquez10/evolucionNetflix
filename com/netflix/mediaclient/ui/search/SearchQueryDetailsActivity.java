// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.VideoList;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.widget.ListAdapter;
import android.widget.GridView;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import java.io.Serializable;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.android.widget.StaticGridView;
import android.view.View;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SearchQueryDetailsActivity extends NetflixActivity
{
    private static final String EXTRA_ID = "extra_id";
    private static final String EXTRA_TITLE = "extra_title";
    private static final String EXTRA_TYPE = "extra_type";
    private static final String TAG = "SearchQueryDetailsActivity";
    private SimilarItemsGridViewAdapter adapter;
    private View content;
    private StaticGridView gridView;
    private String id;
    private boolean isLoading;
    private final ErrorWrapper.Callback leCallback;
    private LoadingAndErrorWrapper leWrapper;
    private ServiceManager manager;
    private long requestId;
    private String title;
    private TextView titleView;
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
            this.manager.fetchSimilarVideosForPerson(this.id, this.adapter.computeNumSimsToFetch(), new OnSimsFetchedCallback(this.requestId));
            return;
        }
        if (this.type == SearchQueryDetailsType.SEARCH_SUGGESTION) {
            Log.v("SearchQueryDetailsActivity", "Fetching data for suggestion, Id: " + this.id);
            this.manager.fetchSimilarVideosForQuerySuggestion(this.id, this.adapter.computeNumSimsToFetch(), new OnSimsFetchedCallback(this.requestId));
            return;
        }
        throw new IllegalStateException("Bad state");
    }
    
    private String getTitleForType(final SearchQueryDetailsType searchQueryDetailsType, final String s) {
        if (searchQueryDetailsType == SearchQueryDetailsType.PERSON) {
            return this.getString(2131493219, new Object[] { s });
        }
        if (searchQueryDetailsType == SearchQueryDetailsType.SEARCH_SUGGESTION) {
            return this.getString(2131493219, new Object[] { s });
        }
        throw new IllegalStateException("Bad type");
    }
    
    private void reloadData() {
        this.showLoadingView();
        this.fetchData();
    }
    
    public static void show(final Activity activity, final SearchQueryDetailsType searchQueryDetailsType, final String s, final String s2) {
        activity.startActivity(new Intent((Context)activity, (Class)SearchQueryDetailsActivity.class).putExtra("extra_type", (Serializable)searchQueryDetailsType).putExtra("extra_id", s).putExtra("extra_title", s2));
    }
    
    private void showContentView() {
        this.leWrapper.hide(true);
        AnimationUtils.showView(this.content, true);
    }
    
    private void showErrorView() {
        this.leWrapper.showErrorView(true);
        AnimationUtils.hideView(this.content, true);
    }
    
    private void showLoadingView() {
        this.leWrapper.showLoadingView(true);
        AnimationUtils.hideView(this.content, true);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                SearchQueryDetailsActivity.this.manager = serviceManager;
                SearchQueryDetailsActivity.this.fetchData();
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
                SearchQueryDetailsActivity.this.manager = null;
            }
        };
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
        final Intent intent = this.getIntent();
        this.type = (SearchQueryDetailsType)intent.getSerializableExtra("extra_type");
        this.id = intent.getStringExtra("extra_id");
        this.title = intent.getStringExtra("extra_title");
        this.setContentView(2130903110);
        this.leWrapper = new LoadingAndErrorWrapper(this.findViewById(2131099906), this.leCallback);
        final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(2131361849);
        (this.content = this.findViewById(2131099907)).setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        this.content.setVisibility(4);
        this.findViewById(2131099917).setVisibility(0);
        (this.titleView = (TextView)this.findViewById(2131099918)).setText((CharSequence)this.getTitleForType(this.type, this.title));
        this.titleView.setVisibility(0);
        (this.gridView = (StaticGridView)this.findViewById(2131099919)).setLayoutAnimation(AnimationUtils.createGridLayoutAnimator((Context)this));
        this.gridView.setFocusable(false);
        this.adapter = new SimilarItemsGridViewAdapter(this, this.gridView, false);
        this.gridView.setAdapter((ListAdapter)this.adapter);
    }
    
    private class OnSimsFetchedCallback extends LoggingManagerCallback
    {
        private final long requestId;
        
        public OnSimsFetchedCallback(final long requestId) {
            super("SearchQueryDetailsActivity");
            this.requestId = requestId;
        }
        
        @Override
        public void onSimilarVideosFetched(final VideoList list, final int n) {
            super.onSimilarVideosFetched(list, n);
            if (this.requestId != SearchQueryDetailsActivity.this.requestId || AndroidUtils.isActivityFinishedOrDestroyed(SearchQueryDetailsActivity.this)) {
                Log.v("SearchQueryDetailsActivity", "Ignoring stale callback");
                return;
            }
            SearchQueryDetailsActivity.this.isLoading = false;
            if (n != 0) {
                Log.w("SearchQueryDetailsActivity", "Invalid status code");
                SearchQueryDetailsActivity.this.showErrorView();
                return;
            }
            if (list == null || list.size() < 1) {
                Log.v("SearchQueryDetailsActivity", "No details in response");
                SearchQueryDetailsActivity.this.showErrorView();
                return;
            }
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
