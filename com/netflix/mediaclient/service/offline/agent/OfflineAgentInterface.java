// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolumeUiList;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import java.util.List;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;

public interface OfflineAgentInterface
{
    public static final String CATEGORY_NF_OFFLINE = "com.netflix.mediaclient.intent.category.offline";
    public static final String OFFLINE_INTENT_OSV_SPACE_USAGE_UPDATED = "com.netflix.mediaclient.intent.offline.osv.space.usage.updated";
    
    void addOfflineAgentListener(final OfflineAgentListener p0);
    
    void deleteAllOfflineContent();
    
    void deleteAndTryAgain(final String p0, final VideoType p1, final PlayContext p2);
    
    void deleteOfflinePlayable(final String p0);
    
    void deleteOfflinePlayables(final List<String> p0);
    
    DownloadVideoQuality getCurrentDownloadVideoQuality();
    
    OfflinePlayableUiList getLatestOfflinePlayableList();
    
    OfflineStorageVolumeUiList getOfflineStorageVolumeList();
    
    boolean getRequiresUnmeteredNetwork();
    
    boolean isOfflineContentPresent();
    
    boolean isOfflineFeatureEnabled();
    
    void pauseDownload(final String p0);
    
    void recalculateSpaceUsageForOfflineStorageVolumes();
    
    void refreshUIData();
    
    void removeOfflineAgentListener(final OfflineAgentListener p0);
    
    void requestGeoPlayabilityUpdate();
    
    void requestOfflinePdsData(final String p0, final OfflineAgentInterface$OfflinePdsDataCallback p1);
    
    void requestOfflineViewing(final String p0, final VideoType p1, final PlayContext p2);
    
    void requestRefreshLicenseForPlayable(final String p0);
    
    void requestRenewPlayWindowForPlayable(final String p0);
    
    void resumeDownload(final String p0);
    
    void setCurrentOfflineStorageVolume(final int p0);
    
    void setDownloadVideoQuality(final DownloadVideoQuality p0);
    
    void setRequiresUnmeteredNetwork(final boolean p0);
    
    boolean setSkipAdultContent(final boolean p0);
}
