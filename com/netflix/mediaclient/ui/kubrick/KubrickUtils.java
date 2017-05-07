// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick;

import java.io.Serializable;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;

public class KubrickUtils
{
    private static final int LARGE_DETAIL_PAGE_THRESHOLD_DP = 1024;
    public static final int NUM_CW_VIDEOS_LANDSCAPE = 3;
    public static final int NUM_CW_VIDEOS_PORTRAIT = 2;
    public static final int NUM_IQ_VIDEOS_LANDSCAPE = 3;
    public static final int NUM_IQ_VIDEOS_PORTRAIT = 2;
    public static final int NUM_VIDEOS_LANDSCAPE = 4;
    public static final int NUM_VIDEOS_PORTRAIT = 3;
    private static final String TAG = "Kubrick";
    
    public static int getDetailsPageContentWidth(final Context context) {
        if (DeviceUtils.getScreenWidthInDPs(context) >= 1024) {
            return Math.min(DeviceUtils.getScreenHeightInPixels(context), DeviceUtils.getScreenWidthInPixels(context));
        }
        return DeviceUtils.getScreenWidthInPixels(context);
    }
    
    public static boolean shouldShowKubrickExperience(final NetflixActivity netflixActivity) {
        if (netflixActivity == null) {
            Log.w("Kubrick", "Activity is null - should not happen");
            return false;
        }
        if (netflixActivity.isKubrick()) {
            Log.v("Kubrick", "Should show Kubrick because we're in a Kubrick activity");
            return true;
        }
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        final boolean b = serviceManager != null && serviceManager.getConfiguration() != null && serviceManager.getConfiguration().getDeviceCategory() == DeviceCategory.TABLET && serviceManager.getConfiguration().getKubrickConfiguration() != null && serviceManager.getConfiguration().getKubrickConfiguration().isKubrickEnabled();
        if (Log.isLoggable("Kubrick", 2)) {
            if (serviceManager == null || serviceManager.getConfiguration() == null) {
                String s;
                if (serviceManager == null) {
                    s = "null service manager";
                }
                else {
                    s = "null config interface";
                }
                Log.v("Kubrick", s);
            }
            else {
                final DeviceCategory deviceCategory = serviceManager.getConfiguration().getDeviceCategory();
                Serializable value;
                if (serviceManager.getConfiguration().getKubrickConfiguration() == null) {
                    value = "null Kubrick config";
                }
                else {
                    value = serviceManager.getConfiguration().getKubrickConfiguration().isKubrickEnabled();
                }
                Log.v("Kubrick", String.format("Device cat: %s, Kubrick enabled: %s, showing Kubrick: %s", deviceCategory, value, b));
            }
        }
        return b;
    }
}
