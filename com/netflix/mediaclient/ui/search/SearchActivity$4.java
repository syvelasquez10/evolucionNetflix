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
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class SearchActivity$4 implements ErrorWrapper$Callback
{
    final /* synthetic */ SearchActivity this$0;
    
    SearchActivity$4(final SearchActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        this.this$0.handleQueryUpdate(this.this$0.query);
    }
}
