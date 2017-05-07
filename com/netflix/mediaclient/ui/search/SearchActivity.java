// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View$OnFocusChangeListener;
import java.io.Serializable;
import com.netflix.mediaclient.ui.kids.search.KidsSearchActivity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import android.text.TextUtils;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import android.content.Context;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.SearchView$OnQueryTextListener;
import com.netflix.mediaclient.android.widget.SearchActionBar;
import android.view.View;
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
    long focusSessionId;
    private ViewGroup fragGroup;
    private final Runnable handleQueryUpdateRunnable;
    private boolean isLoading;
    protected LoadingAndErrorWrapper leWrapper;
    private View loadingWrapper;
    long loggingSessionId;
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
                SearchActivity.this.serviceManager.getBrowse().searchNetflix(SearchActivity.this.query, new FetchSearchResultsHandler(SearchActivity.this.requestId));
            }
        };
        this.searchQueryTextListener = (SearchView$OnQueryTextListener)new SearchView$OnQueryTextListener() {
            public boolean onQueryTextChange(final String s) {
                SearchActivity.this.handleQueryUpdate(s);
                SearchLogUtils.reportSearchEditChange(SearchActivity.this.requestId, (Context)SearchActivity.this, SearchActivity.this.getUiScreen(), s);
                if (TextUtils.isEmpty((CharSequence)s) && SearchActivity.this.resultsFrag != null) {
                    SearchActivity.this.resultsFrag.clearSelectedStack();
                }
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
    
    private void addResetQueryOnTouch() {
        this.searchBar.setOnTouchTextListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                SearchActivity.this.handleQueryUpdate(SearchActivity.this.query);
                return false;
            }
        });
    }
    
    public static Intent create(final NetflixActivity netflixActivity) {
        Serializable s;
        if (netflixActivity.isForKids()) {
            s = KidsSearchActivity.class;
        }
        else {
            s = SearchActivity.class;
        }
        return new Intent((Context)netflixActivity, (Class)s).setAction("android.intent.action.VIEW");
    }
    
    private void findViews() {
        this.fragGroup = (ViewGroup)this.findViewById(2131165614);
        this.loadingWrapper = this.findViewById(2131165613);
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
        if (s != null) {
            this.query = s.trim();
            ++this.requestId;
            if (this.query.length() == 0) {
                this.showInitState();
                return;
            }
            if (this.query.length() >= 1) {
                this.pendingAction = null;
                if (this.serviceManager == null) {
                    this.pendingAction = this.handleQueryUpdateRunnable;
                    return;
                }
                this.handleQueryUpdateRunnable.run();
            }
        }
    }
    
    public static void search(final Activity activity, final String s) {
        activity.startActivity(new Intent((Context)activity, (Class)SearchActivity.class).setAction("android.intent.action.SEARCH").putExtra("query", s).putExtra("submit", true));
    }
    
    private void setupActionBar() {
        (this.searchBar = (SearchActionBar)this.getNetflixActionBar()).setOnQueryTextListener(this.searchQueryTextListener);
        this.searchBar.setOnFocusChangeListener((View$OnFocusChangeListener)new View$OnFocusChangeListener() {
            public void onFocusChange(final View view, final boolean b) {
                if (b) {
                    SearchActivity.this.focusSessionId = SearchLogUtils.reportSearchFocusSessionStarted(SearchActivity.this.requestId, (Context)SearchActivity.this, SearchActivity.this.getUiScreen(), SearchActivity.this.query);
                }
                else if (SearchActivity.this.focusSessionId != 0L) {
                    SearchLogUtils.reportSearchFocusSessionEnded(SearchActivity.this.requestId, (Context)SearchActivity.this, SearchActivity.this.focusSessionId);
                }
            }
        });
        if (SearchUtils.shouldResetQueryOnTouch()) {
            this.addResetQueryOnTouch();
        }
        this.searchBar.hideProgressSpinner();
        this.searchBar.hide();
    }
    
    private void setupFragments(final Bundle bundle) {
        if (bundle == null) {
            (this.resultsFrag = SearchResultsFrag.create()).setServiceManager(this.serviceManager);
            this.getFragmentManager().beginTransaction().add(2131165614, (Fragment)this.resultsFrag, "videos_frag").setTransition(4099).commit();
            this.showInitState();
            return;
        }
        this.resultsFrag = (SearchResultsFrag)this.getFragmentManager().findFragmentByTag("videos_frag");
    }
    
    private void setupLoadingWrapper() {
        (this.leWrapper = new LoadingAndErrorWrapper(this.loadingWrapper, this.errorsCallback)).hide(false);
    }
    
    private void showEmpty() {
        this.leWrapper.showErrorView(2131493223, false, false);
        this.fragGroup.setVisibility(4);
        this.searchBar.hideProgressSpinner();
    }
    
    private void showInitState() {
        this.leWrapper.showErrorView(this.getInitMessageStringId(), false, false);
        this.fragGroup.setVisibility(4);
        this.searchBar.hideProgressSpinner();
    }
    
    private void showResults(final ISearchResults searchResults) {
        this.leWrapper.hide(false);
        this.fragGroup.setVisibility(0);
        if (this.resultsFrag != null) {
            this.resultsFrag.update(searchResults, this.query);
        }
    }
    
    @Override
    protected NetflixActionBar createActionBar(final ActionBar actionBar) {
        return new SearchActionBar(this);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                SearchActivity.this.serviceManager = serviceManager;
                if (SearchActivity.this.resultsFrag != null) {
                    SearchActivity.this.resultsFrag.setServiceManager(SearchActivity.this.serviceManager);
                }
                SearchActivity.this.searchBar.show();
                if (SearchActivity.this.pendingAction != null) {
                    SearchActivity.this.pendingAction.run();
                }
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
            }
        };
    }
    
    protected int getInitMessageStringId() {
        return 2131493205;
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.search;
    }
    
    @Override
    protected boolean handleBackPressed() {
        if (SearchUtils.handleBackPress() && this.resultsFrag != null) {
            return this.resultsFrag.showLastSelectedItem();
        }
        return super.handleBackPressed();
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setupActionBar();
        this.setContentView(2130903167);
        this.findViews();
        this.setupLoadingWrapper();
        this.setupFragments(bundle);
        if (bundle == null) {
            DeviceUtils.showSoftKeyboard(this);
        }
        this.loggingSessionId = SearchLogUtils.reportSearchSessionStarted(this.requestId, (Context)this, this.getUiScreen(), this.query);
        this.handleNewIntent(this.getIntent());
    }
    
    @Override
    protected void onDestroy() {
        SearchLogUtils.reportSearchSessionEnded(this.requestId, (Context)this, this.loggingSessionId);
        super.onDestroy();
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        this.handleNewIntent(intent);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
    }
    
    public void showError() {
        this.leWrapper.showErrorView(2131493222, true, false);
        this.fragGroup.setVisibility(4);
        this.searchBar.hideProgressSpinner();
    }
    
    private class FetchSearchResultsHandler extends LoggingManagerCallback
    {
        private final long requestId;
        
        public FetchSearchResultsHandler(final long requestId) {
            super("SearchActivity");
            this.requestId = requestId;
        }
        
        @Override
        public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
            super.onSearchResultsFetched(searchResults, status);
            if (this.requestId != SearchActivity.this.requestId) {
                Log.v("SearchActivity", "Ignoring stale onSearchResultsFetched callback");
                LogUtils.reportSearchActionEnded(this.requestId, (Context)SearchActivity.this, IClientLogging.CompletionReason.canceled, null);
                return;
            }
            SearchActivity.this.isLoading = false;
            SearchActivity.this.searchBar.hideProgressSpinner();
            final LogUtils.LogReportErrorArgs logReportErrorArgs = new LogUtils.LogReportErrorArgs(status, ActionOnUIError.displayedError, "", null);
            if (status.isError()) {
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
            Log.d("SearchActivity", String.format("searchResults size %d ", searchResults.getNumResults()));
            SearchActivity.this.reportUiViewChanged(IClientLogging.ModalView.searchResults);
            SearchActivity.this.showResults(searchResults);
            LogUtils.reportSearchActionEnded(this.requestId, (Context)SearchActivity.this, logReportErrorArgs.getReason(), logReportErrorArgs.getError());
        }
    }
}
