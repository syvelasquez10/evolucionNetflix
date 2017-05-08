// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

interface DownloadController$DownloadControllerListener
{
    void continueDownloadOnBackOff();
    
    void continueDownloadOnNetworkChanged();
    
    void continueDownloadOnStreamingStopped();
    
    void stopDownloadOnStreamingStarted();
    
    void stopDownloadsNoNetwork();
    
    void stopDownloadsNotAllowedByNetwork();
}
