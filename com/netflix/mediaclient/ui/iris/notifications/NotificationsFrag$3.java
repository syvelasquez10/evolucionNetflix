// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iris.notifications;

import android.os.Parcelable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.app.Activity;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.util.IrisUtils;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import android.view.View$OnClickListener;

class NotificationsFrag$3 implements View$OnClickListener
{
    final /* synthetic */ NotificationsFrag this$0;
    final /* synthetic */ IrisNotificationsListSummary val$listSummary;
    final /* synthetic */ int val$position;
    final /* synthetic */ String val$requestId;
    final /* synthetic */ IrisNotificationSummary val$summary;
    final /* synthetic */ String val$videoId;
    final /* synthetic */ VideoType val$videoType;
    
    NotificationsFrag$3(final NotificationsFrag this$0, final IrisNotificationSummary val$summary, final String val$requestId, final IrisNotificationsListSummary val$listSummary, final VideoType val$videoType, final String val$videoId, final int val$position) {
        this.this$0 = this$0;
        this.val$summary = val$summary;
        this.val$requestId = val$requestId;
        this.val$listSummary = val$listSummary;
        this.val$videoType = val$videoType;
        this.val$videoId = val$videoId;
        this.val$position = val$position;
    }
    
    public void onClick(final View view) {
        final ServiceManager serviceManager = this.this$0.getServiceManager();
        if (serviceManager != null && serviceManager.getBrowse() != null && this.val$summary != null && this.this$0.mNotifications != null) {
            if (!this.val$summary.getWasRead()) {
                serviceManager.getBrowse().markNotificationAsRead(this.val$summary);
            }
            DetailsActivityLauncher.show(this.this$0.getNetflixActivity(), this.val$videoType, this.val$videoId, this.val$summary.getVideoTitle(), new PlayContextImp(this.val$requestId, this.val$listSummary.getMDPTrackId(), 0, 0), "SocialNotif");
            UIViewLogUtils.reportUIViewCommandStarted((Context)this.this$0.getActivity(), UIViewLogging$UIViewCommandName.viewTitleDetails, IClientLogging$ModalView.menuPanel, (DataContext)null, (String)null, this.this$0.getModelObject(this.val$summary, this.val$position));
            UIViewLogUtils.reportUIViewCommandEnded((Context)this.this$0.getActivity());
        }
        else if (Log.isLoggable()) {
            Log.e(NotificationsFrag.TAG, "onClick() got weird use case when mServiceManager is: " + serviceManager);
        }
    }
}
