// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;

public abstract class SimpleOfflineAgentListener implements OfflineAgentListener
{
    @Override
    public void onAllPlayablesDeleted(final Status status) {
    }
    
    @Override
    public void onCreateRequestResponse(final String s, final Status status) {
    }
    
    @Override
    public void onDownloadCompleted(final OfflinePlayableViewData offlinePlayableViewData) {
    }
    
    @Override
    public void onDownloadResumedByUser(final OfflinePlayableViewData offlinePlayableViewData) {
    }
    
    @Override
    public void onDownloadStopped(final OfflinePlayableViewData offlinePlayableViewData, final StopReason stopReason) {
    }
    
    @Override
    public void onError(final Status status) {
    }
    
    @Override
    public void onLicenseRefreshDone(final OfflinePlayableViewData offlinePlayableViewData, final Status status) {
    }
    
    @Override
    public void onOfflinePlayableDeleted(final String s, final Status status) {
    }
    
    @Override
    public void onOfflinePlayableProgress(final OfflinePlayableViewData offlinePlayableViewData, final int n) {
    }
    
    @Override
    public void onPlayWindowRenewDone(final OfflinePlayableViewData offlinePlayableViewData, final Status status) {
    }
}
