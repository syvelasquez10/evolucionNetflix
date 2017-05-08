// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.app.Activity;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputMethod;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.MenuItem;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.content.Context;
import android.widget.PopupMenu$OnMenuItemClickListener;

final class DownloadButtonDialogHelper$1 implements PopupMenu$OnMenuItemClickListener
{
    final /* synthetic */ Context val$context;
    final /* synthetic */ PlayContext val$playContext;
    final /* synthetic */ String val$playableId;
    final /* synthetic */ VideoType val$videoType;
    
    DownloadButtonDialogHelper$1(final Context val$context, final String val$playableId, final VideoType val$videoType, final PlayContext val$playContext) {
        this.val$context = val$context;
        this.val$playableId = val$playableId;
        this.val$videoType = val$videoType;
        this.val$playContext = val$playContext;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        if (menuItem.getItemId() == 2131690413) {
            UIViewLogUtils.reportUIViewCommand(this.val$context, UIViewLogging$UIViewCommandName.StartCachedPlay, IClientLogging$ModalView.offlineShows, CommandEndedEvent$InputMethod.gesture, null);
            if (this.val$context instanceof PlayerActivity) {
                ((PlayerActivity)this.val$context).finish();
            }
            OfflineUiHelper.startOfflinePlayback(this.val$context, this.val$playableId, this.val$videoType, this.val$playContext);
        }
        else if (menuItem.getItemId() == 2131690415) {
            UIViewLogUtils.reportUIViewCommand(this.val$context, UIViewLogging$UIViewCommandName.RemoveCachedVideoCommand, IClientLogging$ModalView.removeCachedVideoButton, CommandEndedEvent$InputMethod.gesture, null);
            ((NetflixActivity)this.val$context).getServiceManager().getOfflineAgent().deleteOfflinePlayable(this.val$playableId);
            DownloadButton.removePreQueued(this.val$playableId);
        }
        else if (menuItem.getItemId() == 2131690418) {
            this.val$context.startActivity(OfflineActivity.showAllDownloads((Activity)this.val$context));
        }
        return true;
    }
}
