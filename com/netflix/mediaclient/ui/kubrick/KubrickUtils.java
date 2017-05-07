// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick;

import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import java.io.Serializable;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.SparseIntArray;
import android.util.SparseArray;

public class KubrickUtils
{
    private static final int LARGE_DETAIL_PAGE_THRESHOLD_DP = 1024;
    public static final int NUM_CW_VIDEOS_LANDSCAPE = 3;
    public static final int NUM_CW_VIDEOS_PORTRAIT = 2;
    public static final int NUM_GALLERY_VIDEOS_LANDSCAPE = 8;
    public static final int NUM_GALLERY_VIDEOS_PORTRAIT = 6;
    public static final int NUM_IQ_VIDEOS_LANDSCAPE = 3;
    public static final int NUM_IQ_VIDEOS_PORTRAIT = 2;
    public static final int NUM_VIDEOS_LANDSCAPE = 4;
    public static final int NUM_VIDEOS_PORTRAIT = 3;
    private static final String TAG = "Kubrick";
    private static final SparseArray<SparseIntArray> numCharactersPerPageTable;
    
    static {
        numCharactersPerPageTable = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(3, 3);
        sparseIntArray.put(4, 4);
        KubrickUtils.numCharactersPerPageTable.put(1, (Object)sparseIntArray);
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(3, 5);
        sparseIntArray2.put(4, 6);
        KubrickUtils.numCharactersPerPageTable.put(2, (Object)sparseIntArray2);
    }
    
    public static KubrickUtils$KubrickExperience computeKubrickExperience(final NetflixActivity netflixActivity) {
        if (netflixActivity == null) {
            Log.w("Kubrick", "Activity is null - should not happen");
            return KubrickUtils$KubrickExperience.NON_KUBRICK;
        }
        if (netflixActivity.isKubrick()) {
            Log.v("Kubrick", "Should show Kubrick because we're in a Kubrick activity");
            if (netflixActivity.isForKids()) {
                return KubrickUtils$KubrickExperience.KUBRICK_KIDS;
            }
            return KubrickUtils$KubrickExperience.KUBRICK;
        }
        else {
            final ServiceManager serviceManager = netflixActivity.getServiceManager();
            if (serviceManager == null) {
                Log.w("Kubrick", "ServiceManager is null - should not happen");
                return KubrickUtils$KubrickExperience.NON_KUBRICK;
            }
            return computeKubrickExperience(serviceManager.getConfiguration(), serviceManager.getCurrentProfile());
        }
    }
    
    public static KubrickUtils$KubrickExperience computeKubrickExperience(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final UserProfile userProfile) {
        final boolean b = serviceAgent$ConfigurationAgentInterface != null && serviceAgent$ConfigurationAgentInterface.getDeviceCategory() == DeviceCategory.TABLET && serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration() != null && serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration().isKubrickEnabled();
        int n;
        if (userProfile != null && userProfile.isKidsProfile()) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (Log.isLoggable("Kubrick", 2)) {
            if (serviceAgent$ConfigurationAgentInterface == null) {
                Log.v("Kubrick", "null config interface");
            }
            else {
                final DeviceCategory deviceCategory = serviceAgent$ConfigurationAgentInterface.getDeviceCategory();
                Serializable value;
                if (serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration() == null) {
                    value = "null Kubrick config";
                }
                else {
                    value = serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration().isKubrickEnabled();
                }
                Log.v("Kubrick", String.format("Device cat: %s, Kubrick enabled: %s, showing Kubrick: %s", deviceCategory, value, b));
                Serializable value2;
                if (userProfile == null) {
                    value2 = "null profile";
                }
                else {
                    value2 = userProfile.isKidsProfile();
                }
                Log.v("Kubrick", String.format("Profile: %s, is kids profile: %s", userProfile, value2));
            }
        }
        if (!b) {
            return KubrickUtils$KubrickExperience.NON_KUBRICK;
        }
        if (n != 0) {
            return KubrickUtils$KubrickExperience.KUBRICK_KIDS;
        }
        return KubrickUtils$KubrickExperience.KUBRICK;
    }
    
    public static int computeNumCharactersPerPage(final NetflixActivity netflixActivity) {
        return ((SparseIntArray)KubrickUtils.numCharactersPerPageTable.get(DeviceUtils.getBasicScreenOrientation((Context)netflixActivity))).get(DeviceUtils.getScreenSizeCategory((Context)netflixActivity));
    }
    
    public static int getDetailsPageContentWidth(final Context context) {
        if (DeviceUtils.getScreenWidthInDPs(context) >= 1024) {
            return Math.min(DeviceUtils.getScreenHeightInPixels(context), DeviceUtils.getScreenWidthInPixels(context));
        }
        return DeviceUtils.getScreenWidthInPixels(context);
    }
    
    public static boolean shouldShowKubrickExperience(final NetflixActivity netflixActivity) {
        final KubrickUtils$KubrickExperience computeKubrickExperience = computeKubrickExperience(netflixActivity);
        return computeKubrickExperience == KubrickUtils$KubrickExperience.KUBRICK || computeKubrickExperience == KubrickUtils$KubrickExperience.KUBRICK_KIDS;
    }
}
