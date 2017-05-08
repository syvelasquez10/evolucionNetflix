// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import android.content.Intent;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import com.android.volley.Network;
import com.android.volley.Cache;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.NoCache;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable$PlayableMaintenanceCallBack;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.service.offline.log.OfflineErrorLogblob;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.log.OfflineLogUtils;
import com.netflix.mediaclient.util.NetflixTransactionIdGenerator;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import android.media.UnsupportedSchemeException;
import android.media.NotProvisionedException;
import com.netflix.mediaclient.service.offline.registry.ChecksumException;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManagerImpl;
import com.netflix.mediaclient.service.offline.manifest.OfflineManifestManagerImpl;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.offline.download.OfflinePlayableImpl;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import com.netflix.mediaclient.service.offline.download.PlayableProgressInfo;
import com.netflix.mediaclient.service.resfetcher.volley.ResourceHttpStack;
import com.android.volley.toolbox.HttpStack;
import android.os.Looper;
import java.util.Iterator;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import com.netflix.mediaclient.service.offline.registry.RegistryData;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import android.content.Context;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.android.app.Status;
import java.util.HashMap;
import java.util.ArrayList;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.user.UserAgent;
import com.android.volley.RequestQueue;
import io.realm.Realm;
import com.netflix.mediaclient.service.offline.registry.OfflineRegistry;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiListImpl;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable;
import java.util.List;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;
import java.util.Map;
import com.netflix.mediaclient.service.offline.manifest.OfflineManifestManager;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManager;
import com.netflix.mediaclient.service.offline.download.OfflinePlayableListener;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.os.HandlerThread;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.service.IntentCommandHandler;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.service.job.NetflixJobExecutor;

class OfflineAgent$DownloadJobExecutor implements NetflixJobExecutor
{
    final /* synthetic */ OfflineAgent this$0;
    
    private OfflineAgent$DownloadJobExecutor(final OfflineAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onNetflixStartJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onNetflixStartJob jobId=" + netflixJob$NetflixJobId);
        }
        if (!this.this$0.mUserAgent.isUserLoggedIn()) {
            return;
        }
        switch (OfflineAgent$21.$SwitchMap$com$netflix$mediaclient$service$job$NetflixJob$NetflixJobId[netflixJob$NetflixJobId.ordinal()]) {
            default: {}
            case 1: {
                this.this$0.addRequestToHandler(6);
            }
            case 2: {
                final long n = System.currentTimeMillis() - OfflineAgentHelper.getLastMaintenanceJobStartTime(this.this$0.getContext());
                if (Log.isLoggable()) {
                    Log.i("nf_offlineAgent", "timeSinceLastMtJob seconds=" + TimeUnit.MILLISECONDS.toSeconds(n));
                }
                if (n > this.this$0.mDownloadController.getMtJobPeriodInMsFromOfflineConfig() / 20L) {
                    Log.i("nf_offlineAgent", "fetchCW from network");
                    this.this$0.getBrowseAgent().fetchCWFromNetwork(10, new OfflineAgent$DownloadJobExecutor$1(this));
                    OfflineAgentHelper.setLastMaintenanceJobStartTime(this.this$0.getContext(), System.currentTimeMillis());
                    this.this$0.addRequestToHandler(7);
                    return;
                }
                Log.i("nf_offlineAgent", "maintenanceJob too early");
                this.this$0.mDownloadController.onMaintenanceJobRunningTooEarly();
            }
        }
    }
    
    @Override
    public void onNetflixStopJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onNetflixStopJob jobId=" + netflixJob$NetflixJobId);
        }
        if (!this.this$0.mUserAgent.isUserLoggedIn()) {}
    }
}
