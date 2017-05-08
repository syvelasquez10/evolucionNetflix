// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import com.netflix.mediaclient.android.app.Status;

public interface OfflinePlayableViewData
{
    long getCurrentEstimatedSpace();
    
    long getDownloadContextInitTimeMs();
    
    int getDownloadContextListPos();
    
    String getDownloadContextRequestId();
    
    int getDownloadContextTrackId();
    
    int getDownloadContextVideoPos();
    
    DownloadState getDownloadState();
    
    String getDxId();
    
    long getExpiringInMillis();
    
    Status getLastPersistentErrorStatus();
    
    String getOxId();
    
    int getPercentageDownloaded();
    
    String getPlayableId();
    
    String getProfileGuidOfDownloadRequester();
    
    StopReason getStopReason();
    
    long getTotalEstimatedSpace();
    
    WatchState getWatchState();
}
