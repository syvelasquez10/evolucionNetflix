// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface$PlayableRefreshLicenseCallBack;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$OfflineRefreshInvoke;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.ClientActionFromLase;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface$OfflinePdsDataCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;

public interface OfflinePlayable extends OfflinePlayableViewData
{
    boolean canResumeWithoutUserAction();
    
    Status deleteDownload();
    
    void destroy();
    
    void doMaintenanceWork(final OfflinePlayable$PlayableMaintenanceCallBack p0);
    
    void getOfflinePdsData(final OfflineAgentInterface$OfflinePdsDataCallback p0);
    
    OfflinePlayablePersistentData getOfflineViewablePersistentData();
    
    void initialize();
    
    void notifyOfflinePlayStarted();
    
    void onLicenseSync(final ClientActionFromLase p0);
    
    void refreshLicenseIfNeeded(final IBladeRunnerClient$OfflineRefreshInvoke p0, final OfflineAgentInterface$PlayableRefreshLicenseCallBack p1);
    
    void requestManifestForPlayback(final OfflinePlayable$PlayableManifestCallBack p0);
    
    void startDownload();
    
    void stopDownload(final StopReason p0);
}
