// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import android.os.Parcelable;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.SocialUtils;
import android.os.Bundle;
import android.app.Activity;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.servicemgr.interface_.search.SocialNotificationsList;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.content.Context;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

class NotificationsFrag$NotificationsListAdapter$2 implements View$OnClickListener
{
    final /* synthetic */ NotificationsFrag$NotificationsListAdapter this$1;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ SocialNotificationsListSummary val$listSummary;
    final /* synthetic */ String val$requestId;
    final /* synthetic */ SocialNotificationSummary val$summary;
    final /* synthetic */ String val$videoId;
    final /* synthetic */ VideoType val$videoType;
    
    NotificationsFrag$NotificationsListAdapter$2(final NotificationsFrag$NotificationsListAdapter this$1, final SocialNotificationSummary val$summary, final String val$requestId, final SocialNotificationsListSummary val$listSummary, final NetflixActivity val$activity, final VideoType val$videoType, final String val$videoId) {
        this.this$1 = this$1;
        this.val$summary = val$summary;
        this.val$requestId = val$requestId;
        this.val$listSummary = val$listSummary;
        this.val$activity = val$activity;
        this.val$videoType = val$videoType;
        this.val$videoId = val$videoId;
    }
    
    public void onClick(final View view) {
        if (this.this$1.this$0.mServiceManager != null && this.this$1.this$0.mServiceManager.getBrowse() != null && this.val$summary != null && this.this$1.this$0.mNotifications != null) {
            this.this$1.this$0.mServiceManager.getBrowse().markNotificationAsRead(this.val$summary);
            SocialLoggingUtils.reportRecommendImplicitFeedbackReadEvent((Context)this.this$1.this$0.getActivity(), this.val$summary.getId(), this.val$summary.getVideoId(), this.this$1.this$0.mNotifications.getSocialNotificationsListSummary().getBaseTrackId());
            DetailsActivityLauncher.show(this.val$activity, this.val$videoType, this.val$videoId, this.val$summary.getVideoTitle(), new PlayContextImp(this.val$requestId, this.val$listSummary.getMDPTrackId(), 0, 0), "SocialNotif");
            return;
        }
        ErrorLoggingManager.logHandledException("SPY-8019: prevented crash inside NotificationsFrag mServiceManager: " + this.this$1.this$0.mServiceManager);
    }
}
