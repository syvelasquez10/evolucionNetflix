// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import com.netflix.mediaclient.javabridge.ui.Nrdp;

public class OfflineNrdpLoggerNoOp implements OfflineNrdpLogger
{
    OfflineNrdpLoggerNoOp(final Nrdp nrdp) {
    }
    
    @Override
    public void onCdnUrlExpiredOrMoved(final OfflinePlayablePersistentData offlinePlayablePersistentData, final Status status) {
    }
    
    @Override
    public void onCdnUrlGeoCheckError(final OfflinePlayablePersistentData offlinePlayablePersistentData, final Status status) {
    }
    
    @Override
    public void onCdnUrlNetworkError(final OfflinePlayablePersistentData offlinePlayablePersistentData, final Status status) {
    }
    
    @Override
    public void onDownloadFinished(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
    }
    
    @Override
    public void onFirstTimeLicenseError(final OfflinePlayablePersistentData offlinePlayablePersistentData, final Status status) {
    }
    
    @Override
    public void onFirstTimeLicenseReceived(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
    }
    
    @Override
    public void onFirstTimeManifestFailed(final OfflinePlayablePersistentData offlinePlayablePersistentData, final Status status) {
    }
    
    @Override
    public void onFirstTimeManifestReceived(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
    }
    
    @Override
    public void onNetflixStartJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
    }
    
    @Override
    public void onNetflixStopJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
    }
    
    @Override
    public void onOfflinePlayableDownloadDelete(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
    }
    
    @Override
    public void onOfflinePlayableDownloadStart(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
    }
    
    @Override
    public void onOfflinePlayableDownloadStop(final OfflinePlayablePersistentData offlinePlayablePersistentData, final StopReason stopReason) {
    }
    
    @Override
    public void onOfflinePlayableInitialize(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
    }
    
    @Override
    public void onRequestingNewManifestFromServer(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
    }
    
    @Override
    public void onUrlDownloadDiskIOError(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
    }
}
