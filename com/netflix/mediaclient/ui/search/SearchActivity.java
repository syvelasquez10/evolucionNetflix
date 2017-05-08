// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.kubrick_kids.search.KubrickKidsSearchActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.app.Fragment;
import android.view.View$OnFocusChangeListener;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputMethod;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import java.util.Iterator;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
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
import android.os.Bundle;
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
    private static final String SEARCH_VIEW_FOCUSED_STATE = "search_view_focused_state";
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
    private boolean mVoiceSearchAvailable;
    private View mVoiceSearchBtn;
    private Runnable pendingAction;
    private String query;
    private long requestId;
    private SearchResultsFrag resultsFrag;
    private Bundle savedInstanceState;
    private SearchActionBar searchActionBar;
    private final SearchView$OnQueryTextListener searchQueryTextListener;
    private boolean searchViewFocused;
    private ServiceManager serviceManager;
    private AtomicBoolean voiceSearch;
    
    public SearchActivity() {
        this.searchViewFocused = true;
        this.voiceSearch = new AtomicBoolean(false);
        this.mVoiceSearchAvailable = true;
        this.errorsCallback = new SearchActivity$5(this);
        this.handleQueryUpdateRunnable = new SearchActivity$6(this);
        this.searchQueryTextListener = (SearchView$OnQueryTextListener)new SearchActivity$7(this);
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    private void addResetQueryOnTouch() {
        this.searchActionBar.setOnTouchTextListener((View$OnTouchListener)new SearchActivity$3(this));
    }
    
    public static Intent create(final NetflixActivity netflixActivity) {
        return new Intent((Context)netflixActivity, (Class)SearchActivity.class).setAction("android.intent.action.VIEW");
    }
    
    private void createUI() {
        this.setContentView(2130903229);
        this.setupActionBar();
        this.findViews();
        this.setupLoadingWrapper();
        this.setupFragments(this.savedInstanceState);
        if (BrowseExperience.showKidsExperience()) {
            this.leWrapper.getErrorMessageTextView().setTextColor(this.getResources().getColor(2131558457));
            this.leWrapper.getErrorMessageTextView().setBackgroundColor(this.getResources().getColor(2131558608));
            ((ViewGroup)this.leWrapper.getErrorMessageTextView().getParent()).setBackgroundColor(-1);
            ViewUtils.removeShadow(this.leWrapper.getErrorMessageTextView());
        }
    }
    
    private void findViews() {
        this.fragGroup = (ViewGroup)this.findViewById(2131624625);
        this.loadingWrapper = this.findViewById(2131624623);
        (this.mVoiceSearchBtn = this.findViewById(2131624624)).setOnClickListener((View$OnClickListener)new SearchActivity$1(this));
    }
    
    private int getSearchHintPlaceholderStringId() {
        if (BrowseExperience.showKidsExperience()) {
            return 2131165633;
        }
        return 2131165731;
    }
    
    private void handleKeyboardVisibility() {
        boolean showSoftKeyboard = false;
        if (StringUtils.isEmpty(this.query)) {
            showSoftKeyboard = VoiceSearchABTestUtils.showSoftKeyboard((Context)this);
            if (this.savedInstanceState != null) {
                showSoftKeyboard = showSoftKeyboard;
                if (this.searchViewFocused) {
                    showSoftKeyboard = true;
                }
            }
        }
        this.showHideKeyboard(showSoftKeyboard);
    }
    
    private void handleNewIntent(final Intent intent) {
        final String action = intent.getAction();
        if (Log.isLoggable()) {
            Log.d("SearchActivity", "Received intent with action: " + action);
            Log.d("SearchActivity", intent);
        }
        if (!"android.intent.action.VIEW".equals(action)) {
            if (!"android.intent.action.SEARCH".equals(action)) {
                throw new IllegalStateException("Unknown action: " + action);
            }
            final String stringExtra = intent.getStringExtra("query");
            final boolean booleanExtra = intent.getBooleanExtra("submit", false);
            this.voiceSearch.set(this.isVoiceSearch(intent));
            if (this.voiceSearch.get()) {
                this.hideSoftKeyboard();
            }
            if (this.searchActionBar != null) {
                this.searchActionBar.setQuery(stringExtra, booleanExtra);
                this.handleQueryUpdate(stringExtra);
            }
        }
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
    
    private void hideSoftKeyboard() {
        DeviceUtils.hideSoftKeyboard(this);
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
    
    private void onVoiceClicked() {
        if (this.mVoiceSearchAvailable) {
            this.searchActionBar.getVoiceSearchBtn().performClick();
            UIViewLogUtils.reportUIViewCommand((Context)this, UIViewLogging$UIViewCommandName.search, this.getUiScreen(), CommandEndedEvent$InputMethod.voice, this.getDataContext());
            return;
        }
        Log.e("SearchActivity", "Voice search button was clicked but no voice search icon in SearchView to trigger voice search dialog");
    }
    
    public static void search(final Activity activity, final String s) {
        activity.startActivity(new Intent((Context)activity, (Class)SearchActivity.class).setAction("android.intent.action.SEARCH").putExtra("query", s).putExtra("submit", true));
    }
    
    private void setSearchActionBarQueryHint(final String searchQueryHint) {
        if (this.searchActionBar != null) {
            this.searchActionBar.setSearchQueryHint(searchQueryHint);
        }
    }
    
    private void setSearchProgressVisibility(final boolean b) {
        if (this.searchActionBar != null) {
            if (!b) {
                this.searchActionBar.hideProgressSpinner();
                return;
            }
            this.searchActionBar.showProgressSpinner();
        }
    }
    
    private void setVoiceSearchVisibility(final boolean b) {
        int visibility;
        final int n = visibility = 8;
        if (b) {
            visibility = n;
            if (this.mVoiceSearchAvailable) {
                visibility = 0;
                this.leWrapper.hide(true);
            }
        }
        if (Log.isLoggable()) {
            Log.d("SearchActivity", "Setting voice search visibility - " + visibility);
        }
        this.mVoiceSearchBtn.setVisibility(visibility);
    }
    
    private void setupActionBar() {
        this.searchActionBar = (SearchActionBar)this.getNetflixActionBar();
        if (this.searchActionBar != null) {
            this.searchActionBar.setOnQueryTextListener(this.searchQueryTextListener);
            this.searchActionBar.setOnFocusChangeListener((View$OnFocusChangeListener)new SearchActivity$2(this));
            if (SearchUtils.shouldResetQueryOnTouch()) {
                this.addResetQueryOnTouch();
            }
            this.mVoiceSearchAvailable = (this.searchActionBar.getVoiceSearchBtn() != null && this.searchActionBar.getVoiceSearchBtn().getVisibility() != 8);
        }
    }
    
    private void setupFragments(final Bundle bundle) {
        if (bundle == null) {
            this.resultsFrag = SearchResultsFrag.create();
            this.getFragmentManager().beginTransaction().add(2131624625, (Fragment)this.resultsFrag, "videos_frag").setTransition(4099).commit();
            this.showInitState();
            return;
        }
        this.resultsFrag = (SearchResultsFrag)this.getFragmentManager().findFragmentByTag("videos_frag");
    }
    
    private void setupLoadingWrapper() {
        (this.leWrapper = new LoadingAndErrorWrapper(this.loadingWrapper, this.errorsCallback)).hide(false);
    }
    
    private void showEmpty() {
        this.leWrapper.showErrorView(2131165579, false, false);
        this.fragGroup.setVisibility(4);
        this.setVoiceSearchVisibility(false);
        this.setSearchProgressVisibility(false);
    }
    
    private void showHideKeyboard(final boolean b) {
        if (b) {
            if (this.searchActionBar != null) {
                this.searchActionBar.requestFocus();
            }
            DeviceUtils.showSoftKeyboard(this);
            return;
        }
        if (this.searchActionBar != null) {
            this.searchActionBar.clearFocus();
        }
        this.hideSoftKeyboard();
    }
    
    private void showInitState() {
        this.fragGroup.setVisibility(4);
        this.setSearchProgressVisibility(false);
        this.setSearchActionBarQueryHint(this.getString(this.getSearchHintPlaceholderStringId()));
        final boolean showVoiceSearchInLayout = VoiceSearchABTestUtils.showVoiceSearchInLayout((Context)this);
        this.setVoiceSearchVisibility(showVoiceSearchInLayout);
        if (showVoiceSearchInLayout) {
            this.leWrapper.hide(true);
            return;
        }
        this.leWrapper.showErrorView(this.getInitMessageStringId(), false, false);
    }
    
    private void showResults(final ISearchResults searchResults) {
        this.leWrapper.hide(false);
        this.setVoiceSearchVisibility(false);
        this.fragGroup.setVisibility(0);
        if (this.voiceSearch.get()) {
            this.hideSoftKeyboard();
        }
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
        if (BrowseExperience.showKidsExperience()) {
            return new KubrickKidsSearchActionBar(this);
        }
        return new SearchActionBar(this);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new SearchActivity$4(this);
    }
    
    protected int getInitMessageStringId() {
        if (BrowseExperience.showKidsExperience()) {
            return 2131165633;
        }
        return 2131165730;
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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        if (DeviceUtils.isTabletByContext((Context)this)) {
            SearchUtils.setSearchExperience(BrowseExperience.getSearchExperience());
        }
        else {
            SearchUtils.setSearchExperience(SearchUtils$SearchExperience.PHONE);
        }
        if (BrowseExperience.showKidsExperience()) {
            this.setTheme(2131362176);
        }
        this.createUI();
        if (savedInstanceState != null) {
            this.searchViewFocused = savedInstanceState.getBoolean("search_view_focused_state");
        }
        if (VoiceSearchABTestUtils.startVoiceSearch((Context)this) && savedInstanceState == null) {
            this.onVoiceClicked();
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
        this.handleKeyboardVisibility();
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putBoolean("search_view_focused_state", this.searchViewFocused);
    }
    
    public void showError() {
        this.leWrapper.showErrorView(2131165459, true, false);
        this.fragGroup.setVisibility(4);
        this.setVoiceSearchVisibility(false);
        this.setSearchProgressVisibility(false);
    }
}
