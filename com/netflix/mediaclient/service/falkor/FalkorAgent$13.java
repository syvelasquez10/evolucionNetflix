// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.advisory.ExpiringContentAdvisory$ContentAction;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.util.Coppola2Utils;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
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
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import android.content.BroadcastReceiver;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.falkor.ServiceProvider;
import com.netflix.mediaclient.service.ServiceAgent;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.Log;
import java.util.concurrent.CountDownLatch;

class FalkorAgent$13 implements Runnable
{
    final /* synthetic */ FalkorAgent this$0;
    final /* synthetic */ CountDownLatch val$jobSchedulerCountdown;
    
    FalkorAgent$13(final FalkorAgent this$0, final CountDownLatch val$jobSchedulerCountdown) {
        this.this$0 = this$0;
        this.val$jobSchedulerCountdown = val$jobSchedulerCountdown;
    }
    
    @Override
    public void run() {
    Label_0070_Outer:
        while (true) {
            while (true) {
                while (true) {
                    try {
                        if (Log.isLoggable()) {
                            Log.d("FalkorAgent", "notifyJobSchedulerFinishedAsync: waiting on jobSchedulerCountdown latch");
                        }
                        this.val$jobSchedulerCountdown.await(300000L, TimeUnit.MILLISECONDS);
                        if (Log.isLoggable()) {
                            Log.d("FalkorAgent", "Prefetch lolomo job finished");
                        }
                        this.this$0.stopPrefetchLolomoSchedulerJob = false;
                        final FalkorAgent this$0 = this.this$0;
                        if (this.val$jobSchedulerCountdown.getCount() == 0L) {
                            final boolean b = true;
                            this$0.notifyJobFinished(b, false, true, "notifyJobSchedulerFinishedAsync");
                            return;
                        }
                    }
                    catch (InterruptedException ex) {
                        if (Log.isLoggable()) {
                            Log.d("FalkorAgent", "cacheLolomoImages: interrupter exception - " + ex.getMessage());
                        }
                        continue Label_0070_Outer;
                    }
                    break;
                }
                final boolean b = false;
                continue;
            }
        }
    }
}
