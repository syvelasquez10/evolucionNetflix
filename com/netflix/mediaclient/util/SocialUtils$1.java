// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.app.Fragment;
import com.netflix.mediaclient.ui.details.DetailsFrag;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationsActivity;
import android.view.MenuItem;
import java.math.BigInteger;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.widget.LinearLayout$LayoutParams;
import android.app.NotificationManager;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.ui.details.RecommendToFriendsFrag;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.res.Resources;
import java.util.Set;
import android.os.Parcelable;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.Menu;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

final class SocialUtils$1 implements View$OnClickListener
{
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ String val$videoId;
    final /* synthetic */ String val$videoTitle;
    final /* synthetic */ VideoType val$videoType;
    
    SocialUtils$1(final NetflixActivity val$activity, final String val$videoId, final String val$videoTitle, final VideoType val$videoType) {
        this.val$activity = val$activity;
        this.val$videoId = val$videoId;
        this.val$videoTitle = val$videoTitle;
        this.val$videoType = val$videoType;
    }
    
    public void onClick(final View view) {
        startShare((Context)this.val$activity, this.val$videoId, this.val$videoTitle, this.val$videoType);
    }
}
