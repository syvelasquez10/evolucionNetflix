// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.advisory.ExpiringContentAdvisory$ContentAction;
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
import com.netflix.mediaclient.ui.iris.notifications.SlidingMenuNotificationsFrag;
import java.io.Reader;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import android.content.Context;
import java.util.concurrent.CountDownLatch;
import android.content.BroadcastReceiver;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.falkor.ServiceProvider;
import com.netflix.mediaclient.service.ServiceAgent;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$11 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent this$0;
    final /* synthetic */ BrowseAgentCallback val$cb;
    final /* synthetic */ int val$n;
    final /* synthetic */ int val$standardListNumber;
    final /* synthetic */ boolean val$useCacheOnly;
    
    FalkorAgent$11(final FalkorAgent this$0, final int val$standardListNumber, final int val$n, final boolean val$useCacheOnly, final BrowseAgentCallback val$cb) {
        this.this$0 = this$0;
        this.val$standardListNumber = val$standardListNumber;
        this.val$n = val$n;
        this.val$useCacheOnly = val$useCacheOnly;
        this.val$cb = val$cb;
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        boolean b = false;
        if (status.isSucces()) {
            final Iterator<LoMo> iterator = list.iterator();
            int n = 0;
            while (true) {
                b = b;
                if (!iterator.hasNext()) {
                    break;
                }
                final LoMo loMo = iterator.next();
                if (!LoMoType.isRegularLomoForPreApp(loMo.getType())) {
                    continue;
                }
                if (n == this.val$standardListNumber) {
                    if (Log.isLoggable()) {
                        Log.d("FalkorAgent", String.format("fetchRecommendedListFromCache listTitle: %s, listId: %s", loMo.getTitle(), loMo.getId()));
                    }
                    this.this$0.cmp.fetchVideos(loMo, 0, this.val$n - 1, this.val$useCacheOnly, false, false, true, this.val$cb);
                    b = true;
                    break;
                }
                ++n;
            }
        }
        if (!b) {
            this.val$cb.onVideosFetched(null, status);
        }
    }
}
