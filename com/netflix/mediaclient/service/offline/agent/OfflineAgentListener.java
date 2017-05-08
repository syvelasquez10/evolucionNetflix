// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.android.app.Status;

public interface OfflineAgentListener
{
    boolean isListenerDestroyed();
    
    void onAllPlayablesDeleted(final Status p0);
    
    void onCreateRequestResponse(final String p0, final Status p1);
    
    void onDownloadCompleted(final OfflinePlayableViewData p0);
    
    void onDownloadResumedByUser(final OfflinePlayableViewData p0);
    
    void onDownloadStopped(final OfflinePlayableViewData p0, final StopReason p1);
    
    void onError(final Status p0);
    
    void onLicenseRefreshDone(final OfflinePlayableViewData p0, final Status p1);
    
    void onOfflinePlayableDeleted(final String p0, final Status p1);
    
    void onOfflinePlayableProgress(final OfflinePlayableViewData p0, final int p1);
    
    void onOfflinePlayablesDeleted(final List<String> p0, final Status p1);
    
    void onOfflineStorageVolumeAddedOrRemoved(final boolean p0);
    
    void onPlayWindowRenewDone(final OfflinePlayableViewData p0, final Status p1);
}
