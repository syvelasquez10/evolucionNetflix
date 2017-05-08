// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$ViewType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.util.UserVisibleErrorCodeGenerator;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog$Builder;
import android.app.Dialog;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.offline.WatchState;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.Toast;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class OfflineErrorDialog$6 implements DialogInterface$OnClickListener
{
    final /* synthetic */ OfflineErrorDialog this$0;
    
    OfflineErrorDialog$6(final OfflineErrorDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final NetflixActivity access$500 = this.this$0.getNetflixActivity();
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)access$500)) {
            if (!ConnectivityUtils.isConnected((Context)access$500)) {
                Toast.makeText((Context)access$500, 2131231344, 0).show();
            }
            else {
                final OfflineErrorDialog$6$1 offlineErrorDialog$6$1 = new OfflineErrorDialog$6$1(this, access$500);
                access$500.getHandler().postDelayed((Runnable)offlineErrorDialog$6$1, 10000L);
                access$500.getServiceManager().createAutoLoginToken(3600000L, new OfflineErrorDialog$6$2(this, access$500, offlineErrorDialog$6$1));
            }
        }
        dialogInterface.dismiss();
    }
}
