// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.browse.BrowseAgent$BillboardActivityType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import java.util.Iterator;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.falkor.ServiceProvider;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationsStaticFactory;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$6 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent this$0;
    final /* synthetic */ MessageData val$msg;
    final /* synthetic */ boolean val$sendNotificationToStatusbar;
    
    FalkorAgent$6(final FalkorAgent this$0, final boolean val$sendNotificationToStatusbar, final MessageData val$msg) {
        this.this$0 = this$0;
        this.val$sendNotificationToStatusbar = val$sendNotificationToStatusbar;
        this.val$msg = val$msg;
    }
    
    @Override
    public void onSocialNotificationsListFetched(final SocialNotificationsList list, final Status status) {
        if (list != null && status.getStatusCode() == StatusCode.OK) {
            final SocialNotificationSummary access$600 = this.this$0.getFirstUnreadNotification(list);
            this.this$0.notifyOthersOfUnreadNotifications(access$600 != null);
            if (this.val$sendNotificationToStatusbar && this.this$0.shouldBeNotificationSentToStatusBar(access$600)) {
                SocialNotificationsStaticFactory.getNotificationByType(access$600.getType()).sendNotificationToStatusbar(access$600, list.getSocialNotificationsListSummary(), this.this$0.getService().getImageLoader(), this.val$msg, this.this$0.getContext());
            }
        }
    }
}