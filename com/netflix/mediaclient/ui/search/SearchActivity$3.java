// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.util.DeviceUtils;
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
import android.widget.SearchView$OnQueryTextListener;
import com.netflix.mediaclient.android.widget.SearchActionBar;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class SearchActivity$3 implements ManagerStatusListener
{
    final /* synthetic */ SearchActivity this$0;
    
    SearchActivity$3(final SearchActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.serviceManager = serviceManager;
        if (this.this$0.resultsFrag != null) {
            this.this$0.resultsFrag.setServiceManager(this.this$0.serviceManager);
        }
        this.this$0.searchActionBar.show(false);
        if (this.this$0.pendingAction != null) {
            this.this$0.pendingAction.run();
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
    }
}
