// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import java.util.Map;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

final class DownloadGeoPlayabilityHelper$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ DownloadGeoPlayabilityHelper$GeoPlayabilityCallBack val$geoPlayabilityCallBack;
    
    DownloadGeoPlayabilityHelper$1(final DownloadGeoPlayabilityHelper$GeoPlayabilityCallBack val$geoPlayabilityCallBack) {
        this.val$geoPlayabilityCallBack = val$geoPlayabilityCallBack;
    }
    
    @Override
    public void onOfflineGeoPlayabilityReceived(final Map<String, Boolean> map, final Status status) {
        if (Log.isLoggable()) {
            Log.d("nf_downloadGeoPlay", "onOfflineGeoPlayabilityReceived res=" + status);
        }
        this.val$geoPlayabilityCallBack.onGeoPlayabilityResponse(map);
    }
}
