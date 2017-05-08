// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.ui.barker_kids.search.BarkerKidsSearchActionBar;
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
import android.content.Context;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.SearchView$OnQueryTextListener;
import com.netflix.mediaclient.android.widget.SearchActionBar;
import android.os.Bundle;
import android.view.View;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class SearchActivity$4 implements ManagerStatusListener
{
    final /* synthetic */ SearchActivity this$0;
    
    SearchActivity$4(final SearchActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.serviceManager = serviceManager;
        if (this.this$0.pendingAction != null) {
            this.this$0.pendingAction.run();
        }
        if (!this.this$0.mVoiceSearchAvailable) {
            Log.w("SearchActivity", "SPY-8468 - Voice search not available. The device has no voice search capabilities");
            this.this$0.getServiceManager().getClientLogging().getErrorLogging().logHandledException("SPY-8468 - Voice search not available. The device has no voice search capabilities");
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
    }
}
