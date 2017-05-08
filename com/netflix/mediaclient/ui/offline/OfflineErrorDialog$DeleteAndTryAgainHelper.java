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
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.util.UserVisibleErrorCodeGenerator;
import com.netflix.mediaclient.StatusCode;
import android.support.v7.app.AlertDialog;
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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.servicemgr.interface_.offline.SimpleOfflineAgentListener;

class OfflineErrorDialog$DeleteAndTryAgainHelper extends SimpleOfflineAgentListener
{
    final /* synthetic */ OfflineErrorDialog this$0;
    
    OfflineErrorDialog$DeleteAndTryAgainHelper(final OfflineErrorDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void cleanUp() {
        final OfflineAgentInterface access$000 = this.this$0.getOfflineAgent();
        if (access$000 != null) {
            access$000.removeOfflineAgentListener(this);
        }
    }
    
    @Override
    public boolean isListenerDestroyed() {
        return AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getNetflixActivity());
    }
    
    @Override
    public void onOfflinePlayableDeleted(final String s, final Status status) {
        super.onOfflinePlayableDeleted(s, status);
        if (Log.isLoggable()) {
            Log.i("offlineErrorDialog", "DeleteAndTryAgainHelper onOfflinePlayableDeleted " + s);
        }
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getNetflixActivity())) {
            this.cleanUp();
            return;
        }
        if (status.isSucces() && this.this$0.getPlayableId().equals(s)) {
            this.this$0.getNetflixActivity().runInUiThread(new OfflineErrorDialog$DeleteAndTryAgainHelper$1(this));
            return;
        }
        Log.i("offlineErrorDialog", "onOfflinePlayableDeleted ignoring");
    }
    
    public void retry() {
        final OfflineAgentInterface access$000 = this.this$0.getOfflineAgent();
        if (access$000 != null) {
            access$000.addOfflineAgentListener(this);
            access$000.deleteOfflinePlayable(this.this$0.getPlayableId());
            DownloadButton.removePreQueued(this.this$0.getPlayableId());
            return;
        }
        Log.i("offlineErrorDialog", "retry offlineAgent is null");
    }
}
