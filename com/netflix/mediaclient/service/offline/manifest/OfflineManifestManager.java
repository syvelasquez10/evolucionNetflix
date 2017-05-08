// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.manifest;

import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.service.pdslogging.DownloadContext;

public interface OfflineManifestManager
{
    void abortAllRequestsForPlayable(final String p0);
    
    void notifyDeletingPlayable(final String p0);
    
    void onTrimMemory(final int p0);
    
    void requestOfflineManifestFromCache(final String p0, final String p1, final String p2, final String p3, final OfflineManifestCallback p4);
    
    void requestOfflineManifestFromServer(final String p0, final String p1, final String p2, final DownloadContext p3, final String p4, final DownloadVideoQuality p5, final OfflineManifestCallback p6);
    
    void requestOfflineManifestRefreshFromServer(final String p0, final String p1, final String p2, final String p3, final DownloadVideoQuality p4, final OfflineManifestCallback p5);
}
