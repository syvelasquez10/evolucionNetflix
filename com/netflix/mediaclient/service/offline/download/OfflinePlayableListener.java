// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.android.app.Status;

public interface OfflinePlayableListener
{
    void onDownloadCompletedSuccess(final OfflinePlayable p0);
    
    void onInitialized(final OfflinePlayable p0, final Status p1);
    
    void onNetworkError(final OfflinePlayable p0, final Status p1);
    
    void onProgress(final OfflinePlayable p0);
    
    void onStorageError(final OfflinePlayable p0, final Status p1);
    
    void onUnRecoverableError(final OfflinePlayable p0, final Status p1);
    
    void requestSaveToRegistry();
}
