// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

class SocialNotificationsFrag$3 implements View$OnClickListener
{
    final /* synthetic */ SocialNotificationsFrag this$0;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ SocialNotificationsListSummary val$listSummary;
    final /* synthetic */ String val$requestId;
    final /* synthetic */ SocialNotificationSummary val$summary;
    final /* synthetic */ String val$videoId;
    final /* synthetic */ VideoType val$videoType;
    
    SocialNotificationsFrag$3(final SocialNotificationsFrag this$0, final String val$requestId, final SocialNotificationsListSummary val$listSummary, final NetflixActivity val$activity, final VideoType val$videoType, final String val$videoId, final SocialNotificationSummary val$summary) {
        this.this$0 = this$0;
        this.val$requestId = val$requestId;
        this.val$listSummary = val$listSummary;
        this.val$activity = val$activity;
        this.val$videoType = val$videoType;
        this.val$videoId = val$videoId;
        this.val$summary = val$summary;
    }
    
    public void onClick(final View view) {
        DetailsActivity.show(this.val$activity, this.val$videoType, this.val$videoId, this.val$summary.getVideoSummary().getTitle(), new PlayContextImp(this.val$requestId, this.val$listSummary.getMDPTrackId(), 0, 0));
    }
}
