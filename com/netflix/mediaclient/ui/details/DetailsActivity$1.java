// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.MenuItem;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import android.app.Activity;
import com.netflix.mediaclient.util.Coppola1Utils;
import java.util.HashMap;
import com.netflix.mediaclient.util.IrisUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.os.Bundle;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.Log;
import java.util.Map;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;

class DetailsActivity$1 implements LoadingStatus$LoadingStatusCallback
{
    final /* synthetic */ DetailsActivity this$0;
    
    DetailsActivity$1(final DetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDataLoaded(final Status status) {
        this.this$0.endDPTTISession(null);
        this.this$0.setLoadingStatusCallback(null);
        if (!this.this$0.isFinishing()) {
            Log.d("DetailsActivity", "DetailsPage is loaded, reporting navigate.ended for movieDetails");
            UserActionLogUtils.reportNavigationActionEnded((Context)this.this$0, this.this$0.getUiScreen(), IClientLogging$CompletionReason.success, (UIError)null);
            if (status.isError()) {
                this.this$0.handleFalkorAgentErrors(status);
            }
        }
    }
}
