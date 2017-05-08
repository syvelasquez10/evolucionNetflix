// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.app.Activity;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputMethod;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.content.DialogInterface;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;

final class DownloadButtonDialogHelper$5 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Context val$context;
    
    DownloadButtonDialogHelper$5(final Context val$context) {
        this.val$context = val$context;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        UIViewLogUtils.reportUIViewCommand(this.val$context, UIViewLogging$UIViewCommandName.ShowMyDownloads, IClientLogging$ModalView.myDownloads, CommandEndedEvent$InputMethod.gesture, null);
        this.val$context.startActivity(OfflineActivity.showAllDownloads((Activity)this.val$context));
        dialogInterface.dismiss();
    }
}
