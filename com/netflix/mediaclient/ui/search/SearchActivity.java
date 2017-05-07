// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View$OnFocusChangeListener;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import java.io.Serializable;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.search.KidsSearchActivity;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.SearchView$OnQueryTextListener;
import com.netflix.mediaclient.android.widget.SearchActionBar;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.activity.NetflixActivity;

@TargetApi(11)
public class SearchActivity extends NetflixActivity
{
    private static final String EXTRA_SUBMIT_QUERY = "submit";
    private static final int MIN_SEARCH_QUERY_LENGTH = 1;
    private static final String TAG = "SearchActivity";
    private static final String VIDEOS_FRAG_TAG = "videos_frag";
    private final ErrorWrapper$Callback errorsCallback;
    private long focusSessionId;
    private ViewGroup fragGroup;
    private final Runnable handleQueryUpdateRunnable;
    private boolean isLoading;
    protected LoadingAndErrorWrapper leWrapper;
    private View loadingWrapper;
    private long loggingSessionId;
    private Runnable pendingAction;
    private String query;
    private long requestId;
    private SearchResultsFrag resultsFrag;
    private SearchActionBar searchActionBar;
    private final SearchView$OnQueryTextListener searchQueryTextListener;
    private ServiceManager serviceManager;
    
    public SearchActivity() {
        this.errorsCallback = new SearchActivity$4(this);
        this.handleQueryUpdateRunnable = new SearchActivity$5(this);
        this.searchQueryTextListener = (SearchView$OnQueryTextListener)new SearchActivity$6(this);
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    private void addResetQueryOnTouch() {
        this.searchActionBar.setOnTouchTextListener((View$OnTouchListener)new SearchActivity$2(this));
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
        this.fragGroup = (ViewGroup)this.findViewById(2131165631);
        this.loadingWrapper = this.findViewById(2131165630);
    }
    
    private void handleNewIntent(final Intent intent) {
        final String action = intent.getAction();
        Log.v("SearchActivity", "Received intent with action: " + action);
        if ("android.intent.action.VIEW".equals(action)) {
            return;
        }
        if ("android.intent.action.SEARCH".equals(action)) {
            final String stringExtra = intent.getStringExtra("query");
            this.searchActionBar.setQuery(stringExtra, intent.getBooleanExtra("submit", false));
            this.handleQueryUpdate(stringExtra);
            return;
        }
        throw new IllegalStateException("Unknown action: " + action);
    }
    
    private void handleQueryUpdate(final String s) {
        if (s != null) {
            this.resultsFrag.clearGridSelected();
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
        this.searchActionBar = (SearchActionBar)this.getNetflixActionBar();
        if (this.searchActionBar != null) {
            this.searchActionBar.setOnQueryTextListener(this.searchQueryTextListener);
            this.searchActionBar.setOnFocusChangeListener((View$OnFocusChangeListener)new SearchActivity$1(this));
            if (SearchUtils.shouldResetQueryOnTouch()) {
                this.addResetQueryOnTouch();
            }
            this.searchActionBar.hideProgressSpinner();
            this.searchActionBar.hide(false);
        }
        this.searchActionBar.requestFocus();
    }
    
    private void setupFragments(final Bundle bundle) {
        if (bundle == null) {
            (this.resultsFrag = SearchResultsFrag.create()).setServiceManager(this.serviceManager);
            this.getFragmentManager().beginTransaction().add(2131165631, (Fragment)this.resultsFrag, "videos_frag").setTransition(4099).commit();
            this.showInitState();
            return;
        }
        this.resultsFrag = (SearchResultsFrag)this.getFragmentManager().findFragmentByTag("videos_frag");
    }
    
    private void setupLoadingWrapper() {
        (this.leWrapper = new LoadingAndErrorWrapper(this.loadingWrapper, this.errorsCallback)).hide(false);
    }
    
    private void showEmpty() {
        this.leWrapper.showErrorView(2131493190, false, false);
        this.fragGroup.setVisibility(4);
        this.searchActionBar.hideProgressSpinner();
    }
    
    private void showInitState() {
        this.leWrapper.showErrorView(this.getInitMessageStringId(), false, false);
        this.fragGroup.setVisibility(4);
        this.searchActionBar.hideProgressSpinner();
    }
    
    private void showResults(final ISearchResults searchResults) {
        this.leWrapper.hide(false);
        this.fragGroup.setVisibility(0);
        if (this.resultsFrag != null) {
            this.resultsFrag.update(searchResults, this.query);
        }
    }
    
    @Override
    protected NetflixActionBar createActionBar() {
        return new SearchActionBar(this);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new SearchActivity$3(this);
    }
    
    protected int getInitMessageStringId() {
        return 2131493176;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.search;
    }
    
    @Override
    protected boolean handleBackPressed() {
        if (SearchUtils.handleBackPress() && this.resultsFrag != null) {
            return this.resultsFrag.showLastSelectedItem();
        }
        return super.handleBackPressed();
    }
    
    @Override
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903170);
        this.setupActionBar();
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
    
    @Override
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
        this.leWrapper.showErrorView(2131493189, true, false);
        this.fragGroup.setVisibility(4);
        this.searchActionBar.hideProgressSpinner();
    }
}
