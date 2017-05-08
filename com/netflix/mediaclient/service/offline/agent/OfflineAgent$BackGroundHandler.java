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
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable$PlayableMaintenanceCallBack;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.service.offline.log.OfflineErrorLogblob;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
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
import java.util.Iterator;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import com.netflix.mediaclient.service.offline.registry.RegistryData;
import com.netflix.mediaclient.Log;
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
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class OfflineAgent$BackGroundHandler extends Handler
{
    static final int TYPE_AGENT_DESTROY = 5;
    static final int TYPE_AGENT_INIT = 0;
    static final int TYPE_CREATE = 1;
    static final int TYPE_DELETE = 2;
    static final int TYPE_DELETE_ALL_PLAYABLES = 8;
    static final int TYPE_DOWNLOAD_MAINTENANCE_JOB = 7;
    static final int TYPE_DOWNLOAD_RESUME_JOB = 6;
    static final int TYPE_HANDLE_MAINTENANCE_DONE = 10;
    static final int TYPE_NOTIFY_PLAYABLE_PLAYBACK_START_30SEC = 9;
    static final int TYPE_PAUSE = 3;
    static final int TYPE_RESUME = 4;
    static final int TYPE_UPDATE_GEO_PLAYABILITY = 11;
    final /* synthetic */ OfflineAgent this$0;
    
    OfflineAgent$BackGroundHandler(final OfflineAgent this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {}
            case 0: {
                this.this$0.handleAgentInitRequest();
            }
            case 1: {
                this.this$0.handleCreateRequest((OfflineAgent$CreateRequest)message.obj);
            }
            case 2: {
                this.this$0.handleDeleteRequest((String)message.obj);
            }
            case 3: {
                this.this$0.handlePauseRequest((String)message.obj);
            }
            case 4: {
                this.this$0.handleResumeRequest((String)message.obj);
            }
            case 5: {
                this.this$0.handleAgentDestroyRequest();
            }
            case 6: {
                this.this$0.handleDownloadResumeJob();
            }
            case 7: {
                this.this$0.handleDownloadMaintenanceJob();
            }
            case 8: {
                this.this$0.handleDeleteAllRequest(false);
            }
            case 9: {
                this.this$0.handleOfflinePlaybackStart30Second((Long)message.obj);
            }
            case 10: {
                this.this$0.mDownloadController.onMaintenanceJobDone();
                this.this$0.mMaintenanceJobHandler = null;
            }
            case 11: {
                this.this$0.handleRequestForGeoPlayability();
            }
        }
    }
}
