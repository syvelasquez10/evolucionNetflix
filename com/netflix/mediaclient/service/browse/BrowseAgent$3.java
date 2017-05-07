// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.service.webclient.model.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.CWVideo;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.Random;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationsStaticFactory;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.pushnotification.MessageData;

class BrowseAgent$3 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent this$0;
    final /* synthetic */ MessageData val$msg;
    final /* synthetic */ boolean val$sendNotificationToStatusbar;
    
    BrowseAgent$3(final BrowseAgent this$0, final boolean val$sendNotificationToStatusbar, final MessageData val$msg) {
        this.this$0 = this$0;
        this.val$sendNotificationToStatusbar = val$sendNotificationToStatusbar;
        this.val$msg = val$msg;
    }
    
    @Override
    public void onSocialNotificationsListFetched(final SocialNotificationsList list, final Status status) {
        boolean b = false;
        if (list != null && status.getStatusCode() == StatusCode.OK) {
            if (!this.this$0.isSameAsCachedNotifications(list)) {
                this.this$0.clearExistingNotificationsInCache();
                this.this$0.insertNotificationsInCache(0, list);
            }
            final SocialNotificationSummary access$3200 = this.this$0.getFirstUnreadNotification(list);
            final BrowseAgent this$0 = this.this$0;
            if (access$3200 != null) {
                b = true;
            }
            this$0.notifyOthersOfUnreadNotifications(b);
            if (this.val$sendNotificationToStatusbar && access$3200 != null && !NetflixApplication.isActivityVisible() && SocialNotificationsUtils.isSocialNotificationsFeatureSupported(this.this$0.getService().getCurrentProfile(), this.this$0.getContext()) && this.this$0.getService().getPushNotification().isOptIn()) {
                SocialNotificationsStaticFactory.getNotificationByType(access$3200.getType()).sendNotificationToStatusbar(access$3200, list.getSocialNotificationsListSummary(), this.this$0.getService().getImageLoader(), this.val$msg, this.this$0.getContext());
            }
        }
    }
}
