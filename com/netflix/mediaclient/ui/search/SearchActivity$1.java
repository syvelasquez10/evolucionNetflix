// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.kubrick_kids.search.KubrickKidsSearchActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.app.Fragment;
import android.app.Activity;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
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
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import android.view.View;
import android.view.View$OnFocusChangeListener;

class SearchActivity$1 implements View$OnFocusChangeListener
{
    final /* synthetic */ SearchActivity this$0;
    
    SearchActivity$1(final SearchActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onFocusChange(final View view, final boolean b) {
        if (b) {
            this.this$0.focusSessionId = SearchLogUtils.reportSearchFocusSessionStarted(this.this$0.requestId, (Context)this.this$0, this.this$0.getUiScreen(), this.this$0.query);
        }
        else if (this.this$0.focusSessionId != 0L) {
            SearchLogUtils.reportSearchFocusSessionEnded(this.this$0.requestId, (Context)this.this$0, this.this$0.focusSessionId);
        }
    }
}
