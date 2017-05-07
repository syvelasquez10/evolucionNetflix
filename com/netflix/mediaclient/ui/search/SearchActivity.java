// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.app.Fragment;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.app.ActionBar;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.SearchResults;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import android.content.Context;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.SearchView$OnQueryTextListener;
import com.netflix.mediaclient.android.widget.SearchActionBar;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.activity.NetflixActivity;

@TargetApi(11)
public class SearchActivity extends NetflixActivity
{
    private static final String EXTRA_SUBMIT_QUERY = "submit";
    private static final int MIN_SEARCH_QUERY_LENGTH = 1;
    private static final String TAG = "SearchActivity";
    private static final String VIDEOS_FRAG_TAG = "videos_frag";
    private final ErrorWrapper.Callback errorsCallback;
    private ViewGroup fragGroup;
    private final Runnable handleQueryUpdateRunnable;
    private boolean isLoading;
    private LoadingAndErrorWrapper leWrapper;
    private Runnable pendingAction;
    private String query;
    private long requestId;
    private SearchResultsFrag resultsFrag;
    private SearchActionBar searchBar;
    private final SearchView$OnQueryTextListener searchQueryTextListener;
    private ServiceManager serviceManager;
    
    public SearchActivity() {
        this.errorsCallback = new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                SearchActivity.this.handleQueryUpdate(SearchActivity.this.query);
            }
        };
        this.handleQueryUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                Log.v("SearchActivity", "handleQueryUpdateRunnable: \"" + SearchActivity.this.query + "\", request id: " + SearchActivity.this.requestId);
                SearchActivity.this.isLoading = true;
                SearchActivity.this.searchBar.showProgressSpinner();
                LogUtils.reportSearchActionStarted(SearchActivity.this.requestId, (Context)SearchActivity.this, null, SearchActivity.this.getUiScreen(), SearchActivity.this.query);
                SearchActivity.this.serviceManager.searchNetflix(SearchActivity.this.query, new FetchSearchResultsHandler(SearchActivity.this.requestId));
            }
        };
        this.searchQueryTextListener = (SearchView$OnQueryTextListener)new SearchView$OnQueryTextListener() {
            public boolean onQueryTextChange(final String s) {
                SearchActivity.this.handleQueryUpdate(s);
                return true;
            }
            
            public boolean onQueryTextSubmit(final String s) {
                Log.v("SearchActivity", "onQueryTextSubmit: " + s);
                SearchActivity.this.searchBar.clearFocus();
                DeviceUtils.hideSoftKeyboard(SearchActivity.this);
                return true;
            }
        };
    }
    
    public static Intent create(final Context context) {
        return new Intent(context, (Class)SearchActivity.class).setAction("android.intent.action.VIEW");
    }
    
    private void handleNewIntent(final Intent intent) {
        final String action = intent.getAction();
        Log.v("SearchActivity", "Received intent with action: " + action);
        if ("android.intent.action.VIEW".equals(action)) {
            return;
        }
        if ("android.intent.action.SEARCH".equals(action)) {
            final String stringExtra = intent.getStringExtra("query");
            this.searchBar.setQuery(stringExtra, intent.getBooleanExtra("submit", false));
            this.handleQueryUpdate(stringExtra);
            return;
        }
        throw new IllegalStateException("Unknown action: " + action);
    }
    
    private void handleQueryUpdate(final String s) {
        this.query = s.trim();
        ++this.requestId;
        if (this.query.length() == 0) {
            this.showInitState();
        }
        else if (this.query.length() >= 1) {
            this.pendingAction = null;
            if (this.serviceManager == null) {
                this.pendingAction = this.handleQueryUpdateRunnable;
                return;
            }
            this.handleQueryUpdateRunnable.run();
        }
    }
    
    public static void search(final Activity activity, final String s) {
        activity.startActivity(new Intent((Context)activity, (Class)SearchActivity.class).setAction("android.intent.action.SEARCH").putExtra("query", s).putExtra("submit", true));
    }
    
    private void showEmpty() {
        this.leWrapper.showErrorView(2131493160, false, false);
        this.fragGroup.setVisibility(4);
        this.searchBar.hideProgressSpinner();
    }
    
    private void showError() {
        this.leWrapper.showErrorView(2131493159, true, false);
        this.fragGroup.setVisibility(4);
        this.searchBar.hideProgressSpinner();
    }
    
    private void showInitState() {
        this.leWrapper.showErrorView(2131493142, false, false);
        this.fragGroup.setVisibility(4);
        this.searchBar.hideProgressSpinner();
    }
    
    private void showResults(final SearchResults searchResults) {
        this.leWrapper.hide(false);
        this.fragGroup.setVisibility(0);
        this.resultsFrag.update(searchResults);
    }
    
    @Override
    protected NetflixActionBar createActionBar(final ActionBar actionBar) {
        (this.searchBar = new SearchActionBar(this, actionBar, this.createUpActionRunnable())).setOnQueryTextListener(this.searchQueryTextListener);
        this.searchBar.hide();
        return this.searchBar;
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                SearchActivity.this.serviceManager = serviceManager;
                SearchActivity.this.searchBar.show();
                if (SearchActivity.this.pendingAction != null) {
                    SearchActivity.this.pendingAction.run();
                }
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
            }
        };
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.search;
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.searchBar.hideProgressSpinner();
        this.setContentView(2130903109);
        (this.leWrapper = new LoadingAndErrorWrapper(this.findViewById(2131099904), this.errorsCallback)).hide(false);
        this.fragGroup = (ViewGroup)this.findViewById(2131099905);
        if (bundle == null) {
            this.resultsFrag = SearchResultsFrag.create();
            this.getFragmentManager().beginTransaction().add(2131099905, (Fragment)this.resultsFrag, "videos_frag").setTransition(4099).commit();
            this.showInitState();
        }
        else {
            this.resultsFrag = (SearchResultsFrag)this.getFragmentManager().findFragmentByTag("videos_frag");
        }
        if (bundle == null) {
            DeviceUtils.showSoftKeyboard(this);
        }
        this.handleNewIntent(this.getIntent());
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        this.handleNewIntent(intent);
    }
    
    private class FetchSearchResultsHandler extends LoggingManagerCallback
    {
        private final long requestId;
        
        public FetchSearchResultsHandler(final long requestId) {
            super("SearchActivity");
            this.requestId = requestId;
        }
        
        @Override
        public void onSearchResultsFetched(final SearchResults searchResults, final int n) {
            super.onSearchResultsFetched(searchResults, n);
            if (this.requestId != SearchActivity.this.requestId) {
                Log.v("SearchActivity", "Ignoring stale onSearchResultsFetched callback");
                LogUtils.reportSearchActionEnded(this.requestId, (Context)SearchActivity.this, IClientLogging.CompletionReason.canceled, null);
                return;
            }
            SearchActivity.this.isLoading = false;
            SearchActivity.this.searchBar.hideProgressSpinner();
            final LogUtils.LogReportErrorArgs logReportErrorArgs = new LogUtils.LogReportErrorArgs(n, ActionOnUIError.displayedError, "", null);
            if (n != 0) {
                Log.w("SearchActivity", "Invalid status code");
                SearchActivity.this.showError();
                LogUtils.reportSearchActionEnded(this.requestId, (Context)SearchActivity.this, logReportErrorArgs.getReason(), logReportErrorArgs.getError());
                return;
            }
            if (searchResults == null || !searchResults.hasResults()) {
                Log.v("SearchActivity", "No results from server");
                SearchActivity.this.showEmpty();
                LogUtils.reportSearchActionEnded(this.requestId, (Context)SearchActivity.this, logReportErrorArgs.getReason(), logReportErrorArgs.getError());
                return;
            }
            Log.d("SearchActivity", String.format("searchResults size %d trackId %d", searchResults.getNumResults(), searchResults.getTrackId()));
            SearchActivity.this.reportUiViewChanged(IClientLogging.ModalView.searchResults);
            SearchActivity.this.showResults(searchResults);
            LogUtils.reportSearchActionEnded(this.requestId, (Context)SearchActivity.this, logReportErrorArgs.getReason(), logReportErrorArgs.getError());
        }
    }
}
