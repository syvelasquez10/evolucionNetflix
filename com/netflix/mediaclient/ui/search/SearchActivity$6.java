// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.kubrick_kids.search.KubrickKidsSearchActionBar;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View$OnFocusChangeListener;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.SearchActionBar;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import android.text.TextUtils;
import android.content.Context;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import android.widget.SearchView$OnQueryTextListener;

class SearchActivity$6 implements SearchView$OnQueryTextListener
{
    final /* synthetic */ SearchActivity this$0;
    
    SearchActivity$6(final SearchActivity this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onQueryTextChange(final String s) {
        this.this$0.handleQueryUpdate(s);
        SearchLogUtils.reportSearchEditChange(this.this$0.requestId, (Context)this.this$0, this.this$0.getUiScreen(), s);
        if (TextUtils.isEmpty((CharSequence)s) && this.this$0.resultsFrag != null) {
            this.this$0.resultsFrag.clearSelectedStack();
            this.this$0.resultsFrag.clearGridSelected();
        }
        return true;
    }
    
    public boolean onQueryTextSubmit(final String s) {
        Log.v("SearchActivity", "onQueryTextSubmit: " + s);
        this.this$0.searchActionBar.clearFocus();
        DeviceUtils.hideSoftKeyboard(this.this$0);
        return true;
    }
}
