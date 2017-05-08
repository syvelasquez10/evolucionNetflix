// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.advisory.ExpiringContentAdvisory$ContentAction;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.util.Coppola2Utils;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.falkor.task.CmpTaskDetails;
import com.netflix.mediaclient.ui.player.PostPlayRequestContext;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.Asset;
import java.io.File;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.job.NetflixJobExecutor;
import com.netflix.mediaclient.service.job.NetflixJob;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import java.util.Iterator;
import com.netflix.mediaclient.ui.iris.notifications.SlidingMenuNotificationsFrag;
import java.io.Reader;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import android.content.BroadcastReceiver;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.falkor.ServiceProvider;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.ui.iris.notifications.type.BaseNotification;
import android.content.Context;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.ui.iris.notifications.NotificationsStaticFactory;
import com.netflix.mediaclient.util.IrisUtils;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$8 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent this$0;
    final /* synthetic */ MessageData val$msg;
    final /* synthetic */ boolean val$sendNotificationToStatusbar;
    
    FalkorAgent$8(final FalkorAgent this$0, final boolean val$sendNotificationToStatusbar, final MessageData val$msg) {
        this.this$0 = this$0;
        this.val$sendNotificationToStatusbar = val$sendNotificationToStatusbar;
        this.val$msg = val$msg;
    }
    
    @Override
    public void onNotificationsListFetched(final IrisNotificationsList list, final Status status) {
        boolean b = true;
        if (list != null && status.getStatusCode() == StatusCode.OK) {
            final IrisNotificationSummary access$600 = this.this$0.getFirstUnreadNotification(list);
            final Context context = this.this$0.getContext();
            final boolean b2 = access$600 != null;
            if (list.getSocialNotifications() == null || list.getSocialNotifications().size() <= 0) {
                b = false;
            }
            IrisUtils.notifyOthersOfUnreadNotifications(context, b2, b);
            if (this.val$sendNotificationToStatusbar && this.this$0.shouldBeNotificationSentToStatusBar(access$600)) {
                final BaseNotification notificationByType = NotificationsStaticFactory.getNotificationByType(access$600.getType());
                if (notificationByType.supportsStatusBar()) {
                    notificationByType.sendNotificationToStatusbar(access$600, list.getSocialNotificationsListSummary(), this.this$0.getService().getImageLoader(), this.val$msg, this.this$0.getContext());
                }
            }
        }
    }
}
