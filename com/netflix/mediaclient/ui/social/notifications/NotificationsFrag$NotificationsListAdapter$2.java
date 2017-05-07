// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContextImp;
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
    
    NotificationsFrag$NotificationsListAdapter$2(final NotificationsFrag$NotificationsListAdapter this$1, final String val$requestId, final SocialNotificationsListSummary val$listSummary, final NetflixActivity val$activity, final VideoType val$videoType, final String val$videoId, final SocialNotificationSummary val$summary) {
        this.this$1 = this$1;
        this.val$requestId = val$requestId;
        this.val$listSummary = val$listSummary;
        this.val$activity = val$activity;
        this.val$videoType = val$videoType;
        this.val$videoId = val$videoId;
        this.val$summary = val$summary;
    }
    
    public void onClick(final View view) {
        DetailsActivityLauncher.show(this.val$activity, this.val$videoType, this.val$videoId, this.val$summary.getVideo().getTitle(), new PlayContextImp(this.val$requestId, this.val$listSummary.getMDPTrackId(), 0, 0), "SocialNotif");
    }
}
