// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import android.os.Parcelable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.SocialUtils;
import android.os.Bundle;
import android.app.Activity;
import java.util.List;
import java.util.ArrayList;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import com.netflix.mediaclient.StatusCode;
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.Status;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.servicemgr.interface_.search.SocialNotificationsList;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.content.Context;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import android.view.View$OnClickListener;

class NotificationsFrag$3 implements View$OnClickListener
{
    final /* synthetic */ NotificationsFrag this$0;
    final /* synthetic */ SocialNotificationsListSummary val$listSummary;
    final /* synthetic */ int val$position;
    final /* synthetic */ String val$requestId;
    final /* synthetic */ SocialNotificationSummary val$summary;
    final /* synthetic */ String val$videoId;
    final /* synthetic */ VideoType val$videoType;
    
    NotificationsFrag$3(final NotificationsFrag this$0, final SocialNotificationSummary val$summary, final String val$requestId, final SocialNotificationsListSummary val$listSummary, final VideoType val$videoType, final String val$videoId, final int val$position) {
        this.this$0 = this$0;
        this.val$summary = val$summary;
        this.val$requestId = val$requestId;
        this.val$listSummary = val$listSummary;
        this.val$videoType = val$videoType;
        this.val$videoId = val$videoId;
        this.val$position = val$position;
    }
    
    public void onClick(final View view) {
        if (this.this$0.mServiceManager != null && this.this$0.mServiceManager.getBrowse() != null && this.val$summary != null && this.this$0.mNotifications != null) {
            if (!this.val$summary.getWasRead()) {
                this.this$0.mServiceManager.getBrowse().markNotificationAsRead(this.val$summary);
            }
            SocialLoggingUtils.reportRecommendImplicitFeedbackReadEvent((Context)this.this$0.getActivity(), this.val$summary.getId(), this.val$summary.getVideoId(), this.this$0.mNotifications.getSocialNotificationsListSummary().getBaseTrackId());
            DetailsActivityLauncher.show(this.this$0.getNetflixActivity(), this.val$videoType, this.val$videoId, this.val$summary.getVideoTitle(), new PlayContextImp(this.val$requestId, this.val$listSummary.getMDPTrackId(), 0, 0), "SocialNotif");
            UIViewLogUtils.reportUIViewCommandStarted((Context)this.this$0.getActivity(), UIViewLogging$UIViewCommandName.viewTitleDetails, IClientLogging$ModalView.menuPanel, null, null, this.this$0.getModelObject(this.val$summary, this.val$position));
            UIViewLogUtils.reportUIViewCommandEnded((Context)this.this$0.getActivity());
        }
        else if (Log.isLoggable()) {
            Log.e(NotificationsFrag.TAG, "onClick() got weird use case when mServiceManager is: " + this.this$0.mServiceManager);
        }
    }
}
