// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

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
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.SearchActionBar;
import android.os.Bundle;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.text.TextUtils;
import android.content.Context;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.ISearchLogging$InputMode;
import com.netflix.mediaclient.Log;
import android.widget.SearchView$OnQueryTextListener;

class SearchActivity$7 implements SearchView$OnQueryTextListener
{
    final /* synthetic */ SearchActivity this$0;
    
    SearchActivity$7(final SearchActivity this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onQueryTextChange(final String s) {
        if (Log.isLoggable()) {
            Log.v("SearchActivity", "onQueryTextChange triggers query update, query: " + s + ", voice search " + this.this$0.voiceSearch.get());
        }
        if (!this.this$0.voiceSearch.get()) {
            this.this$0.handleQueryUpdate(s);
        }
        ISearchLogging$InputMode searchLogging$InputMode;
        if (this.this$0.voiceSearch.getAndSet(false)) {
            searchLogging$InputMode = ISearchLogging$InputMode.speech;
        }
        else {
            searchLogging$InputMode = ISearchLogging$InputMode.keyboard;
        }
        SearchLogUtils.reportSearchEditChange(this.this$0.requestId, (Context)this.this$0, this.this$0.getUiScreen(), s, searchLogging$InputMode);
        if (TextUtils.isEmpty((CharSequence)s) && this.this$0.resultsFrag != null) {
            this.this$0.resultsFrag.clearSelectedStack();
            this.this$0.resultsFrag.clearGridSelected();
        }
        return true;
    }
    
    public boolean onQueryTextSubmit(final String s) {
        Log.v("SearchActivity", "onQueryTextSubmit: " + s);
        if (this.this$0.searchActionBar != null) {
            this.this$0.searchActionBar.clearFocus();
        }
        this.this$0.hideSoftKeyboard();
        return true;
    }
}
