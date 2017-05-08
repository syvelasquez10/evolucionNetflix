// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.ui.verifyplay.PinVerifier$PinType;
import android.app.Notification;
import com.netflix.mediaclient.service.logging.perf.Events;
import android.os.Process;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.service.job.NetflixJobSchedulerSelector;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.service.webclient.model.leafs.ThumbMessaging;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import com.netflix.mediaclient.servicemgr.IPushNotification;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.servicemgr.NrdpComponent;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.servicemgr.IDiagnosis;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.IBrowseInterface;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import java.util.List;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.mediaclient.service.user.UserAgent$UserAgentCallback;
import com.netflix.mediaclient.servicemgr.IMSLClient$NetworkRequestInspector;
import android.os.SystemClock;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.service.pservice.logging.PServiceLogging;
import com.netflix.mediaclient.servicemgr.INetflixServiceCallback;
import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.content.Intent;
import java.util.HashSet;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.HashMap;
import com.netflix.mediaclient.service.voip.WhistleVoipAgent;
import com.netflix.mediaclient.service.user.UserAgent;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.pushnotification.PushNotificationAgent;
import com.netflix.mediaclient.service.preapp.PreAppAgent;
import java.util.Set;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.pdslogging.PdsAgent;
import com.netflix.mediaclient.service.player.exoplayback.ExoPlayback;
import com.netflix.mediaclient.service.offline.agent.OfflineAgent;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import com.netflix.mediaclient.service.job.NetflixJobExecutor;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import java.util.Map;
import com.netflix.mediaclient.service.msl.MSLAgent;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import com.netflix.mediaclient.service.falkor.FalkorAgent;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.error.ErrorAgent;
import com.netflix.mediaclient.service.diagnostics.DiagnosisAgent;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import com.netflix.mediaclient.media.BookmarkStore;
import android.os.IBinder;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.INetflixService;
import android.app.Service;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import java.util.ArrayList;

class NetflixService$2 implements ServiceAgent$InitCallback
{
    private final ArrayList<ServiceAgent> agentsToInitOnError;
    final /* synthetic */ NetflixService this$0;
    final /* synthetic */ ServiceAgent$InitCallback val$agentsInitCallback;
    
    NetflixService$2(final NetflixService this$0, final ServiceAgent$InitCallback val$agentsInitCallback) {
        this.this$0 = this$0;
        this.val$agentsInitCallback = val$agentsInitCallback;
        this.agentsToInitOnError = new NetflixService$2$1(this);
    }
    
    @Override
    public void onInitComplete(final ServiceAgent serviceAgent, final Status status) {
        ThreadUtils.assertOnMain();
        if (status.isError()) {
            Log.e("NetflixService", "NetflixService init failed with ServiceAgent " + serviceAgent.getClass().getSimpleName() + " statusCode=" + status.getStatusCode());
            this.this$0.mInitStatusCode = status;
            for (final ServiceAgent serviceAgent2 : this.agentsToInitOnError) {
                if (!serviceAgent2.isInitCalled()) {
                    serviceAgent2.init(this.this$0.agentContext, this);
                }
            }
            this.this$0.initCompleted();
            this.this$0.stopSelf();
            return;
        }
        if (Log.isLoggable()) {
            Log.i("NetflixService", "NetflixService successfully inited ServiceAgent " + serviceAgent.getClass().getSimpleName());
        }
        this.this$0.mMslAgent.init(this.this$0.agentContext, this.val$agentsInitCallback);
    }
}
