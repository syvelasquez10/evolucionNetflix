// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.app.Activity;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputMethod;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.view.MenuItem;
import android.content.Context;
import android.widget.PopupMenu$OnMenuItemClickListener;

final class DownloadButtonDialogHelper$3 implements PopupMenu$OnMenuItemClickListener
{
    final /* synthetic */ Context val$context;
    final /* synthetic */ String val$playableId;
    
    DownloadButtonDialogHelper$3(final Context val$context, final String val$playableId) {
        this.val$context = val$context;
        this.val$playableId = val$playableId;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        if (menuItem.getItemId() == 2131690415) {
            if (!ConnectivityUtils.hasInternet(this.val$context)) {
                DownloadButtonDialogHelper.createNoInternetDialog(this.val$context, this.val$playableId, true).show();
                return true;
            }
            final boolean requiresUnmeteredNetwork = ((NetflixActivity)this.val$context).getServiceManager().getOfflineAgent().getRequiresUnmeteredNetwork();
            boolean b;
            if (ConnectivityUtils.isNetworkTypeCellular(this.val$context) && ConnectivityUtils.hasCellular(this.val$context) && !ConnectivityUtils.isWiFiConnected(this.val$context)) {
                b = true;
            }
            else {
                b = false;
            }
            if (!requiresUnmeteredNetwork || !b) {
                UIViewLogUtils.reportUIViewCommand(this.val$context, UIViewLogging$UIViewCommandName.ResumeDownloadCommand, IClientLogging$ModalView.resumeDownloadButton, CommandEndedEvent$InputMethod.gesture, null);
                ((NetflixActivity)this.val$context).getServiceManager().getOfflineAgent().resumeDownload(this.val$playableId);
                return true;
            }
            DownloadButtonDialogHelper.createNoWifiDialog(this.val$context, this.val$playableId, RealmUtils.getOfflineVideoDetails(this.val$playableId).getType(), true).show();
        }
        else {
            if (menuItem.getItemId() == 2131690416) {
                ((NetflixActivity)this.val$context).getServiceManager().getOfflineAgent().deleteOfflinePlayable(this.val$playableId);
                DownloadButton.removePreQueued(this.val$playableId);
                return true;
            }
            if (menuItem.getItemId() == 2131690417) {
                this.val$context.startActivity(OfflineActivity.showAllDownloads((Activity)this.val$context));
                return true;
            }
        }
        return true;
    }
}
