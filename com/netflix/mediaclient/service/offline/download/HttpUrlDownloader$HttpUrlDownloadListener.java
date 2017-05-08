// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.android.volley.VolleyError;

public interface HttpUrlDownloader$HttpUrlDownloadListener
{
    void onHttpResponseStart(final long p0);
    
    void onNetworkError(final VolleyError p0);
    
    void onProgress(final HttpUrlDownloader p0);
    
    void onUrlDownloadDiskIOError();
    
    void onUrlDownloadSessionEnd();
}
