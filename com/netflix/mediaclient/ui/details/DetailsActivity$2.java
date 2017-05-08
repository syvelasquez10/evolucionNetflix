// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.view.MenuItem;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import android.app.Activity;
import com.netflix.mediaclient.util.Coppola1Utils;
import java.util.Collections;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
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
import java.util.Map;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class DetailsActivity$2 extends BroadcastReceiver
{
    final /* synthetic */ DetailsActivity this$0;
    
    DetailsActivity$2(final DetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0)) {
            Log.v("DetailsActivity", "Received request to reload data");
            if (this.this$0.getServiceManager() != null && this.this$0.getServiceManager().isReady()) {
                this.this$0.reloadData();
            }
        }
    }
}
