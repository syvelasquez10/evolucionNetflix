// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.widget.PopupMenu$OnMenuItemClickListener;
import android.view.View;
import android.widget.PopupMenu;
import android.support.v7.app.AlertDialog$Builder;
import android.app.Dialog;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.content.DialogInterface;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;

final class DownloadButtonDialogHelper$11 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Context val$context;
    final /* synthetic */ String val$playableId;
    
    DownloadButtonDialogHelper$11(final Context val$context, final String val$playableId) {
        this.val$context = val$context;
        this.val$playableId = val$playableId;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final OfflineAgentInterface access$000 = getOfflineAgent(this.val$context);
        if (access$000 != null) {
            access$000.deleteOfflinePlayable(this.val$playableId);
            DownloadButton.removePreQueued(this.val$playableId);
        }
        dialogInterface.dismiss();
    }
}
