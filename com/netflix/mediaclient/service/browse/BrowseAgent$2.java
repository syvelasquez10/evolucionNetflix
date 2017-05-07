// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.content.Intent;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.ui.social.notifications.types.SocialNotification;
import android.content.Context;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationsStaticFactory;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.pushnotification.MessageData;

class BrowseAgent$2 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent this$0;
    final /* synthetic */ MessageData val$msg;
    final /* synthetic */ boolean val$sendNotificationToStatusbar;
    
    BrowseAgent$2(final BrowseAgent this$0, final boolean val$sendNotificationToStatusbar, final MessageData val$msg) {
        this.this$0 = this$0;
        this.val$sendNotificationToStatusbar = val$sendNotificationToStatusbar;
        this.val$msg = val$msg;
    }
    
    @Override
    public void onSocialNotificationsListFetched(final SocialNotificationsList list, final Status status) {
        boolean b = true;
        if (list != null && status.getStatusCode() == StatusCode.OK) {
            if (!this.this$0.isSameAsCachedNotifications(list)) {
                this.this$0.clearExistingNotificationsInCache();
                this.this$0.insertNotificationsInCache(0, list);
            }
            final SocialNotificationSummary access$3100 = this.this$0.getFirstUnreadNotification(list);
            final Context access$3101 = this.this$0.getContext();
            final boolean b2 = access$3100 != null;
            if (list.getSocialNotifications() == null || list.getSocialNotifications().size() <= 0) {
                b = false;
            }
            SocialUtils.notifyOthersOfUnreadNotifications(access$3101, b2, b);
            if (this.val$sendNotificationToStatusbar && access$3100 != null && !NetflixApplication.isActivityVisible() && SocialUtils.isNotificationsFeatureSupported(this.this$0.getService().getCurrentProfile(), this.this$0.getContext()) && this.this$0.getService().getPushNotification().isOptIn()) {
                final SocialNotification notificationByType = SocialNotificationsStaticFactory.getNotificationByType(access$3100.getType());
                if (notificationByType.supportsStatusBar()) {
                    notificationByType.sendNotificationToStatusbar(access$3100, list.getSocialNotificationsListSummary(), this.this$0.getService().getImageLoader(), this.val$msg, this.this$0.getContext());
                }
            }
        }
    }
}
