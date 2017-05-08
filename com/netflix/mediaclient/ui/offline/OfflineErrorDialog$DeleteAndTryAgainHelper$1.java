// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$ViewType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.OfflineDialogLogblob;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.util.UserVisibleErrorCodeGenerator;
import com.netflix.mediaclient.StatusCode;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog$Builder;
import android.app.Dialog;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.offline.WatchState;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.Log;

class OfflineErrorDialog$DeleteAndTryAgainHelper$1 implements Runnable
{
    final /* synthetic */ OfflineErrorDialog$DeleteAndTryAgainHelper this$1;
    
    OfflineErrorDialog$DeleteAndTryAgainHelper$1(final OfflineErrorDialog$DeleteAndTryAgainHelper this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        Log.i("offlineErrorDialog", "DeleteAndTryAgainHelper retrying");
        final OfflineAgentInterface access$000 = this.this$1.this$0.getOfflineAgent();
        if (access$000 != null) {
            access$000.requestOfflineViewing(this.this$1.this$0.getPlayableId(), this.this$1.this$0.getVideoType(), this.this$1.this$0.getPlayContextSafely());
            this.this$1.cleanUp();
            return;
        }
        Log.i("offlineErrorDialog", "RetryHelper agent is null");
    }
}
