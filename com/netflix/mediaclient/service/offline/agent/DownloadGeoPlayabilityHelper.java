// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import java.util.List;
import com.netflix.mediaclient.Log;

class DownloadGeoPlayabilityHelper
{
    private static final String TAG = "nf_downloadGeoPlay";
    
    static boolean hasGeoCountryChanged(final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.i("nf_downloadGeoPlay", "updateGeoPlayabilityIfCountryChanged registryCountryCode=" + s + " configCountryCode=" + s2);
        }
        return s2 != null && !s2.equals(s);
    }
    
    static void sendGeoPlayabilityRequest(final List<String> list, final ServiceAgent$BrowseAgentInterface serviceAgent$BrowseAgentInterface, final DownloadGeoPlayabilityHelper$GeoPlayabilityCallBack downloadGeoPlayabilityHelper$GeoPlayabilityCallBack) {
        Log.i("nf_downloadGeoPlay", "sendGeoPlayabilityRequest");
        if (serviceAgent$BrowseAgentInterface == null) {
            Log.i("nf_downloadGeoPlay", "browseAgent null");
            return;
        }
        if (list.size() <= 0) {
            Log.i("nf_downloadGeoPlay", "videoIdList is empty");
            return;
        }
        Log.d("nf_downloadGeoPlay", "sendGeoPlayabilityRequest calling... updateOfflineGeoPlayability");
        serviceAgent$BrowseAgentInterface.updateOfflineGeoPlayability(list, new DownloadGeoPlayabilityHelper$1(downloadGeoPlayabilityHelper$GeoPlayabilityCallBack));
    }
}
