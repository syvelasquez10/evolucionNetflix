// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.android.app.Status;

interface CdnUrlDownloadListener
{
    void onCdnUrlDownloadSessionEnd(final CdnUrlDownloader p0);
    
    void onCdnUrlExpiredOrMoved(final CdnUrlDownloader p0, final Status p1);
    
    void onCdnUrlGeoCheckFailure(final CdnUrlDownloader p0, final Status p1);
    
    void onNetworkError(final CdnUrlDownloader p0, final Status p1);
    
    void onUrlDownloadDiskIOError(final CdnUrlDownloader p0);
}
