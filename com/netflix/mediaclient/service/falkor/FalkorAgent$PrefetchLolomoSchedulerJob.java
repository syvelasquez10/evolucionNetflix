// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.IrisUtils;
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
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.servicemgr.interface_.ExpiringContentAction;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.service.job.NetflixJob;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import java.util.Iterator;
import com.netflix.mediaclient.ui.iris.notifications.SlidingMenuNotificationsFrag;
import java.io.Reader;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import android.content.BroadcastReceiver;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.falkor.ServiceProvider;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import android.os.SystemClock;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.job.NetflixJobExecutor;

class FalkorAgent$PrefetchLolomoSchedulerJob implements NetflixJobExecutor
{
    final /* synthetic */ FalkorAgent this$0;
    
    private FalkorAgent$PrefetchLolomoSchedulerJob(final FalkorAgent this$0) {
        this.this$0 = this$0;
    }
    
    private void handleUntimelyJobRequest(final long n) {
        if (Log.isLoggable()) {
            Log.d("FalkorAgent", "handleUntimelyJobRequest: Start job request is invalidated because the last request ran before start delay ms");
        }
        this.this$0.notifyJobFinished(true, false, false);
        if (n <= 60000L) {
            this.this$0.cancelPrefetchLolomoSchedulerJob();
            this.this$0.schedulePrefetchLolomoJob();
        }
    }
    
    private boolean isStartJobUntimely(final long n) {
        return n < 3600000L;
    }
    
    @Override
    public void onNetflixStartJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (!this.this$0.isInPrefetchLolomoTest(this.this$0.getContext())) {
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "startLolomoFetchJob: is not in test return early.");
            }
            this.this$0.cancelPrefetchLolomoSchedulerJob();
            this.this$0.notifyJobFinished(true, false, false);
        }
        else {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final long n = elapsedRealtime - PrefetchLolomoABTestUtils.getLastJobStartTime(this.this$0.getContext());
            if (this.isStartJobUntimely(n)) {
                this.handleUntimelyJobRequest(n);
                return;
            }
            PreferenceUtils.putLongPref((Context)this.this$0.getService(), "prefs_prefetch_lolomo_job_last_start_time_ms", elapsedRealtime);
            UserActionLogUtils.reportPrefetchLolomoJobStarted(this.this$0.getContext(), null, null);
            if (Log.isLoggable()) {
                Log.d("FalkorAgent", "onNetflixStartJob: jobId = " + netflixJob$NetflixJobId);
            }
            this.this$0.stopPrefetchLolomoSchedulerJob = false;
            if (!this.this$0.startLolomoFetchJob(false)) {
                this.this$0.notifyJobFinished(false, true, true);
            }
        }
    }
    
    @Override
    public void onNetflixStopJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (Log.isLoggable()) {
            Log.d("FalkorAgent", "onNetflixStopJob: jobId = " + netflixJob$NetflixJobId);
        }
        this.this$0.stopPrefetchLolomoSchedulerJob = true;
    }
}