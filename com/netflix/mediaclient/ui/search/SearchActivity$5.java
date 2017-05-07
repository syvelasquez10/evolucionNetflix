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
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.Log;

class SearchActivity$5 implements Runnable
{
    final /* synthetic */ SearchActivity this$0;
    
    SearchActivity$5(final SearchActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.v("SearchActivity", "handleQueryUpdateRunnable: \"" + this.this$0.query + "\", request id: " + this.this$0.requestId);
        this.this$0.isLoading = true;
        this.this$0.searchActionBar.showProgressSpinner();
        UserActionLogUtils.reportSearchActionStarted(this.this$0.requestId, (Context)this.this$0, null, this.this$0.getUiScreen(), this.this$0.query);
        this.this$0.serviceManager.getBrowse().searchNetflix(this.this$0.query, new SearchActivity$FetchSearchResultsHandler(this.this$0, this.this$0.requestId));
    }
}
