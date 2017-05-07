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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.servicemgr.interface_.search.SocialNotificationsList;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.view.View;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

class NotificationsFrag$NotificationsListAdapter$3 implements View$OnClickListener
{
    final /* synthetic */ NotificationsFrag$NotificationsListAdapter this$1;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ SocialNotificationSummary val$summary;
    final /* synthetic */ View val$thanksButton;
    
    NotificationsFrag$NotificationsListAdapter$3(final NotificationsFrag$NotificationsListAdapter this$1, final View val$thanksButton, final SocialNotificationSummary val$summary, final NetflixActivity val$activity) {
        this.this$1 = this$1;
        this.val$thanksButton = val$thanksButton;
        this.val$summary = val$summary;
        this.val$activity = val$activity;
    }
    
    public void onClick(final View view) {
        this.val$thanksButton.setEnabled(false);
        UserActionLogUtils.reportSayThanksActionStarted((Context)this.this$1.this$0.getActivity(), null, IClientLogging$ModalView.socialNotifications);
        this.this$1.this$0.mServiceManager.getBrowse().sendThanksToSocialNotification(this.val$summary, new NotificationsFrag$NotificationsListAdapter$3$1(this, NotificationsFrag.TAG));
    }
}
