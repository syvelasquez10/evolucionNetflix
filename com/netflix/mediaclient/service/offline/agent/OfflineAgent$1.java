// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolumeUiList;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManager$LicenseSyncResponseCallback;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import com.android.volley.Network;
import com.android.volley.Cache;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.NoCache;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmProfile;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmIncompleteVideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable$PlayableMaintenanceCallBack;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.service.offline.log.OfflineErrorLogblob;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.android.app.BaseStatus;
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
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.offline.download.OfflinePlayableImpl;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import com.netflix.mediaclient.service.offline.download.PlayableProgressInfo;
import com.netflix.mediaclient.service.resfetcher.volley.ResourceHttpStack;
import com.android.volley.toolbox.HttpStack;
import android.os.Looper;
import java.util.Iterator;
import com.netflix.mediaclient.service.offline.registry.RegistryData;
import com.netflix.mediaclient.service.offline.registry.OfflineRegistry$RegistryEnumerator;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.NetflixService;
import java.util.HashMap;
import java.util.ArrayList;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.user.UserAgent;
import com.netflix.mediaclient.service.offline.registry.OfflineStorageMonitor;
import com.netflix.mediaclient.service.offline.registry.OfflineStorageMonitor$StorageChangeListener;
import com.android.volley.RequestQueue;
import io.realm.Realm;
import com.netflix.mediaclient.service.offline.registry.OfflineRegistry;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiListImpl;
import java.util.List;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;
import java.util.Map;
import com.netflix.mediaclient.service.offline.manifest.OfflineManifestManager;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManager;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.os.HandlerThread;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.service.IntentCommandHandler;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable;
import com.netflix.mediaclient.service.offline.download.OfflinePlayableListener;

class OfflineAgent$1 implements OfflinePlayableListener
{
    final /* synthetic */ OfflineAgent this$0;
    
    OfflineAgent$1(final OfflineAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDownloadCompletedSuccess(final OfflinePlayable offlinePlayable) {
        final String playableId = offlinePlayable.getPlayableId();
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onDownloadCompletedSuccess playableId=" + playableId);
        }
        this.this$0.saveToRegistry();
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "handling onDownloadCompletedSuccess playableId=" + playableId);
        }
        this.this$0.sendDownloadCompleted(offlinePlayable);
        this.this$0.mDownloadController.onDownloadedSuccessfully(playableId);
        this.this$0.startDownloadIfAllowed();
    }
    
    @Override
    public void onInitialized(final OfflinePlayable offlinePlayable, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onInitialized playableId=" + offlinePlayable.getPlayableId() + " status=" + status);
        }
        this.this$0.sendResponseForCreate(offlinePlayable.getPlayableId(), status);
    }
    
    @Override
    public void onLicenseDeleteSuccessfully(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        this.this$0.mOfflineRegistry.removeFromDeletedList(offlinePlayablePersistentData);
        this.requestSaveToRegistry();
    }
    
    @Override
    public void onNetworkError(final OfflinePlayable offlinePlayable, final Status status) {
        final String playableId = offlinePlayable.getPlayableId();
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onNetworkError playableId=" + playableId + " status=" + status);
        }
        this.this$0.saveToRegistry();
        Log.i("nf_offlineAgent", "handling onNetworkError playableId=" + playableId);
        this.this$0.sendDownloadStopped(offlinePlayable);
        this.this$0.mDownloadController.onNetworkError(offlinePlayable.getPlayableId());
    }
    
    @Override
    public void onProgress(final OfflinePlayable offlinePlayable) {
        this.this$0.sendProgress(offlinePlayable, offlinePlayable.getPercentageDownloaded());
    }
    
    @Override
    public void onStorageError(final OfflinePlayable offlinePlayable, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onStorageError status=" + status);
        }
        this.this$0.saveToRegistry();
        this.this$0.sendDownloadStopped(offlinePlayable);
        this.this$0.mDownloadController.onStorageError();
    }
    
    @Override
    public void onUnRecoverableError(final OfflinePlayable offlinePlayable, final Status status) {
        if (Log.isLoggable()) {
            Log.e("nf_offlineAgent", "onUnRecoverableError playableId=" + offlinePlayable.getPlayableId() + " status=" + status);
        }
        this.this$0.saveToRegistry();
        this.this$0.sendDownloadStopped(offlinePlayable);
        this.this$0.mDownloadController.onUnRecoverableError(offlinePlayable.getPlayableId(), status);
        this.this$0.startDownloadIfAllowed();
    }
    
    @Override
    public void requestSaveToRegistry() {
        this.this$0.saveToRegistry();
    }
}
