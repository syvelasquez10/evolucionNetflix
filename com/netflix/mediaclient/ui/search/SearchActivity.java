// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.kubrick_kids.search.KubrickKidsSearchActionBar;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View$OnFocusChangeListener;
import android.app.Activity;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import java.util.concurrent.atomic.AtomicBoolean;
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
    private AtomicBoolean voiceSearch;
    
    public SearchActivity() {
        this.voiceSearch = new AtomicBoolean(false);
        this.errorsCallback = new SearchActivity$4(this);
        this.handleQueryUpdateRunnable = new SearchActivity$5(this);
        this.searchQueryTextListener = (SearchView$OnQueryTextListener)new SearchActivity$6(this);
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    private void addResetQueryOnTouch() {
        this.searchActionBar.setOnTouchTextListener((View$OnTouchListener)new SearchActivity$2(this));
    }
    
    public static Intent create(final NetflixActivity netflixActivity) {
        return new Intent((Context)netflixActivity, (Class)SearchActivity.class).setAction("android.intent.action.VIEW");
    }
    
    private void findViews() {
        this.fragGroup = (ViewGroup)this.findViewById(2131624513);
        this.loadingWrapper = this.findViewById(2131624512);
    }
    
    private void handleNewIntent(final Intent intent) {
        final String action = intent.getAction();
        if (Log.isLoggable()) {
            Log.d("SearchActivity", "Received intent with action: " + action);
            Log.d("SearchActivity", intent);
        }
        if ("android.intent.action.VIEW".equals(action)) {
            return;
        }
        if ("android.intent.action.SEARCH".equals(action)) {
            final String stringExtra = intent.getStringExtra("query");
            final boolean booleanExtra = intent.getBooleanExtra("submit", false);
            this.voiceSearch.set(this.isVoiceSearch(intent));
            this.searchActionBar.setQuery(stringExtra, booleanExtra);
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
    
    private boolean isVoiceSearch(final Intent intent) {
        final Iterator<String> iterator = intent.getExtras().keySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().startsWith("android.speech.extra")) {
                Log.d("SearchActivity", "Voice search");
                return true;
            }
        }
        Log.w("SearchActivity", "Not voice search?");
        return false;
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
            this.getFragmentManager().beginTransaction().add(2131624513, (Fragment)this.resultsFrag, "videos_frag").setTransition(4099).commit();
            this.showInitState();
            return;
        }
        this.resultsFrag = (SearchResultsFrag)this.getFragmentManager().findFragmentByTag("videos_frag");
    }
    
    private void setupLoadingWrapper() {
        (this.leWrapper = new LoadingAndErrorWrapper(this.loadingWrapper, this.errorsCallback)).hide(false);
    }
    
    private void showEmpty() {
        this.leWrapper.showErrorView(2131165476, false, false);
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
    protected boolean canApplyBrowseExperience() {
        return true;
    }
    
    @Override
    protected NetflixActionBar createActionBar() {
        if (BrowseExperience.isKubrickKids()) {
            return new KubrickKidsSearchActionBar(this);
        }
        return new SearchActionBar(this);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new SearchActivity$3(this);
    }
    
    protected int getInitMessageStringId() {
        if (BrowseExperience.isKubrickKids()) {
            return 2131165770;
        }
        return 2131165610;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.search;
    }
    
    @Override
    protected boolean handleBackPressed() {
        if (SearchUtils.shouldHandleBackPress() && this.resultsFrag != null) {
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
        SearchUtils.setSearchExperience(BrowseExperience.getSearchExperience());
        if (BrowseExperience.isKubrickKids()) {
            this.setTheme(2131362162);
        }
        this.setContentView(2130903215);
        this.setupActionBar();
        this.findViews();
        this.setupLoadingWrapper();
        this.setupFragments(bundle);
        if (bundle == null) {
            DeviceUtils.showSoftKeyboard(this);
        }
        this.loggingSessionId = SearchLogUtils.reportSearchSessionStarted(this.requestId, (Context)this, this.getUiScreen(), this.query);
        this.handleNewIntent(this.getIntent());
        if (BrowseExperience.isKubrickKids()) {
            this.leWrapper.getErrorMessageTextView().setTextColor(this.getResources().getColor(2131558460));
            this.leWrapper.getErrorMessageTextView().setBackgroundColor(this.getResources().getColor(2131558601));
            ((ViewGroup)this.leWrapper.getErrorMessageTextView().getParent()).setBackgroundColor(-1);
            ViewUtils.removeShadow(this.leWrapper.getErrorMessageTextView());
        }
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
        this.leWrapper.showErrorView(2131165414, true, false);
        this.fragGroup.setVisibility(4);
        this.searchActionBar.hideProgressSpinner();
    }
}
