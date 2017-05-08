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
import com.netflix.mediaclient.servicemgr.OfflineDialogLogblob;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.util.UserVisibleErrorCodeGenerator;
import com.netflix.mediaclient.StatusCode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog$Builder;
import android.app.Dialog;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.offline.WatchState;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class OfflineErrorDialog$3 implements DialogInterface$OnClickListener
{
    final /* synthetic */ OfflineErrorDialog this$0;
    
    OfflineErrorDialog$3(final OfflineErrorDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final NetflixActivity access$200 = this.this$0.getNetflixActivity();
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)access$200)) {
            if (ConnectivityUtils.isConnected((Context)access$200)) {
                final OfflineAgentInterface access$201 = this.this$0.getOfflineAgent();
                if (access$201 != null) {
                    access$201.resumeDownload(this.this$0.getPlayableId());
                }
                else {
                    Log.i("offlineErrorDialog", "downloadResumeAction offlineAgent is null");
                }
            }
            else {
                Toast.makeText((Context)access$200, 2131231341, 0).show();
            }
        }
        dialogInterface.dismiss();
    }
}
