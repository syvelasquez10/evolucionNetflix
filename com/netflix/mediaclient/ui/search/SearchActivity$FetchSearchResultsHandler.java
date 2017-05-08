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
import com.netflix.mediaclient.util.StringUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.view.View$OnTouchListener;
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
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class SearchActivity$FetchSearchResultsHandler extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ SearchActivity this$0;
    
    public SearchActivity$FetchSearchResultsHandler(final SearchActivity this$0, final long requestId) {
        this.this$0 = this$0;
        super("SearchActivity");
        this.requestId = requestId;
    }
    
    @Override
    public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
        super.onSearchResultsFetched(searchResults, status);
        if (this.requestId != this.this$0.requestId) {
            Log.v("SearchActivity", "Ignoring stale onSearchResultsFetched callback");
            UserActionLogUtils.reportSearchActionEnded(this.requestId, (Context)this.this$0, IClientLogging$CompletionReason.canceled, null);
            return;
        }
        this.this$0.isLoading = false;
        this.this$0.setSearchProgressVisibility(false);
        if (status.isError()) {
            Log.w("SearchActivity", "Invalid status code");
            this.this$0.showError();
            UserActionLogUtils.reportSearchActionEnded(this.requestId, (Context)this.this$0, IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, this.this$0.getString(2131231007), ActionOnUIError.displayedError));
            return;
        }
        if (searchResults == null || !searchResults.hasResults()) {
            Log.v("SearchActivity", "No results from server");
            this.this$0.showEmpty();
            UserActionLogUtils.reportSearchActionEnded(this.requestId, (Context)this.this$0, IClientLogging$CompletionReason.success, null);
            return;
        }
        Log.d("SearchActivity", String.format("searchResults size %d ", searchResults.getNumResults()));
        this.this$0.reportUiViewChanged(IClientLogging$ModalView.searchResults);
        this.this$0.showResults(searchResults);
        UserActionLogUtils.reportSearchActionEnded(this.requestId, (Context)this.this$0, IClientLogging$CompletionReason.success, null);
    }
}
