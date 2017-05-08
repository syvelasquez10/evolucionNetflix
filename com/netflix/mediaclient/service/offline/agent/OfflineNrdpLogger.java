// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;

public interface OfflineNrdpLogger
{
    void onCdnUrlExpiredOrMoved(final OfflinePlayablePersistentData p0, final Status p1);
    
    void onCdnUrlGeoCheckError(final OfflinePlayablePersistentData p0, final Status p1);
    
    void onCdnUrlNetworkError(final OfflinePlayablePersistentData p0, final Status p1);
    
    void onDownloadFinished(final OfflinePlayablePersistentData p0);
    
    void onFirstTimeLicenseError(final OfflinePlayablePersistentData p0, final Status p1);
    
    void onFirstTimeLicenseReceived(final OfflinePlayablePersistentData p0);
    
    void onFirstTimeManifestFailed(final OfflinePlayablePersistentData p0, final Status p1);
    
    void onFirstTimeManifestReceived(final OfflinePlayablePersistentData p0);
    
    void onNetflixStartJob(final NetflixJob$NetflixJobId p0);
    
    void onNetflixStopJob(final NetflixJob$NetflixJobId p0);
    
    void onOfflinePlayableDownloadDelete(final OfflinePlayablePersistentData p0);
    
    void onOfflinePlayableDownloadStart(final OfflinePlayablePersistentData p0);
    
    void onOfflinePlayableDownloadStop(final OfflinePlayablePersistentData p0, final StopReason p1);
    
    void onOfflinePlayableInitialize(final OfflinePlayablePersistentData p0);
    
    void onRequestingNewManifestFromServer(final OfflinePlayablePersistentData p0);
    
    void onUrlDownloadDiskIOError(final OfflinePlayablePersistentData p0);
}
