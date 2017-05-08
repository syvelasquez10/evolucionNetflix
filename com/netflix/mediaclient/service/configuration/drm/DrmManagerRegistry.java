// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import android.os.Build;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.content.Context;

public final class DrmManagerRegistry
{
    private static final String DRM_SYSTEM_ID_FORCE_LEGACY = "FORCE_LEGACY";
    private static final String DRM_SYSTEM_ID_LEGACY = "LEGACY";
    private static final String DRM_SYSTEM_ID_M_PLUS_MGK = "M_PLUS_MGK";
    private static final String NEXUS7_DEB_DEVICE = "deb";
    private static final String NEXUS7_FLO_DEVICE = "flo";
    protected static final String TAG = "nf_drm";
    private static String currentDrmSystem;
    private static boolean enableWidevineL1;
    private static boolean enableWidevineL3;
    private static boolean enableWidevineL3_ABTest;
    private static DrmManager instance;
    private static String mMaxSecurityLevel;
    private static boolean mPlayerRequiredAdaptivePlayback;
    private static WidevineMediaDrmEngine mWidevineMediaDrmEngine;
    private static String previousDrmSystem;
    private static boolean widevineInstanceL3;
    
    static {
        DrmManagerRegistry.widevineInstanceL3 = false;
    }
    
    private static void clearKeys(final String s) {
        getWidevineDrmManager().clearKeys(s);
    }
    
    private static void clearLicense(final byte[] array) {
        getWidevineMediaDrmEngine().clearLicense(array);
    }
    
    public static DrmManager createDrmManager(Context instance, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final ServiceAgent$UserAgentInterface serviceAgent$UserAgentInterface, final ErrorLogging errorLogging, final IErrorHandler errorHandler, final DrmManager$DrmReadyCallback drmManager$DrmReadyCallback) {
    Label_0202_Outer:
        while (true) {
            while (true) {
                Label_0405: {
                    while (true) {
                    Label_0166:
                        while (true) {
                            final int androidVersion;
                            synchronized (DrmManagerRegistry.class) {
                                androidVersion = AndroidUtils.getAndroidVersion();
                                DrmManagerRegistry.previousDrmSystem = PreferenceUtils.getStringPref(instance, "nf_drm_system_id", null);
                                if (androidVersion >= 19) {
                                    DrmManagerRegistry.mMaxSecurityLevel = WidevineDrmManager.getMediaDrmMaxSecurityLevel();
                                    DrmManagerRegistry.mPlayerRequiredAdaptivePlayback = PlayerTypeFactory.isJPlayer2(PlayerTypeFactory.getCurrentType(instance));
                                }
                                while (true) {
                                    try {
                                        DrmManagerRegistry.enableWidevineL1 = serviceAgent$ConfigurationAgentInterface.isWidevineL1Enabled();
                                        DrmManagerRegistry.enableWidevineL3 = serviceAgent$ConfigurationAgentInterface.isWidevineL3Enabled();
                                        DrmManagerRegistry.enableWidevineL3_ABTest = serviceAgent$ConfigurationAgentInterface.isWidevineL3ABTestEnabled();
                                        Log.d("nf_drm", "EnableWV L1: " + DrmManagerRegistry.enableWidevineL1 + " EnableWV L3: " + DrmManagerRegistry.enableWidevineL3 + " enableWidevineL3AB: " + DrmManagerRegistry.enableWidevineL3_ABTest);
                                        if (androidVersion >= 18 && isWidevineDrmAllowed() && DrmManagerRegistry.enableWidevineL1) {
                                            Log.d("nf_drm", "WidevineDrmManager L1 created");
                                            DrmManagerRegistry.instance = new WidevineDrmManager(instance, serviceAgent$UserAgentInterface, errorLogging, errorHandler, drmManager$DrmReadyCallback, false);
                                            DrmManagerRegistry.widevineInstanceL3 = false;
                                        }
                                        else {
                                            if (androidVersion < 19 || !isWidevineDrmAllowed() || (!DrmManagerRegistry.enableWidevineL3 && !DrmManagerRegistry.enableWidevineL3_ABTest)) {
                                                break;
                                            }
                                            Log.d("nf_drm", "WidevineDrmManager L3 created");
                                            DrmManagerRegistry.instance = new WidevineDrmManager(instance, serviceAgent$UserAgentInterface, errorLogging, errorHandler, drmManager$DrmReadyCallback, true);
                                            DrmManagerRegistry.widevineInstanceL3 = true;
                                        }
                                        if (DrmManagerRegistry.instance.getDrmType() != 0) {
                                            break Label_0405;
                                        }
                                        if (AndroidUtils.getAndroidVersion() > 22) {
                                            final String currentDrmSystem = "M_PLUS_MGK";
                                            PreferenceUtils.putStringPref(instance, "nf_drm_system_id", DrmManagerRegistry.currentDrmSystem = currentDrmSystem);
                                            if (Log.isLoggable()) {
                                                Log.d("nf_drm", "currentDrmSystem : " + DrmManagerRegistry.currentDrmSystem + ", previousDrmSystem : " + DrmManagerRegistry.previousDrmSystem);
                                            }
                                            instance = (Context)DrmManagerRegistry.instance;
                                            return (DrmManager)instance;
                                        }
                                        break Label_0166;
                                    }
                                    catch (Throwable t) {
                                        Log.e("nf_drm", "Unable to create WidevineDrmManager, default to LegacyDrmManager", t);
                                        DrmManagerRegistry.instance = new LegacyDrmManager(drmManager$DrmReadyCallback);
                                        DrmManagerRegistry.widevineInstanceL3 = false;
                                        continue Label_0166;
                                    }
                                    continue Label_0166;
                                }
                            }
                            if (Log.isLoggable()) {
                                Log.d("nf_drm", "LegacyDrmManager for devices running android version = " + androidVersion);
                            }
                            DrmManagerRegistry.instance = new LegacyDrmManager(drmManager$DrmReadyCallback);
                            DrmManagerRegistry.widevineInstanceL3 = false;
                            continue Label_0166;
                        }
                        final String currentDrmSystem = "LEGACY";
                        continue Label_0202_Outer;
                    }
                }
                DrmManagerRegistry.currentDrmSystem = PreferenceUtils.getStringPref(instance, "nf_drm_system_id", null);
                continue;
            }
        }
    }
    
    private static void createWidevineMediaDrmEngine() {
        DrmManagerRegistry.mWidevineMediaDrmEngine = new WidevineMediaDrmEngine(DrmManagerRegistry.widevineInstanceL3);
    }
    
    private static byte[] decrypt(final byte[] array, final byte[] array2) {
        return getWidevineDrmManager().decrypt(array, array2);
    }
    
    public static boolean drmSupportsHd() {
        return isCurrentDrmWidevine() && isWidevineLevel1Supported();
    }
    
    private static byte[] encrypt(final byte[] array, final byte[] array2) {
        return getWidevineDrmManager().encrypt(array, array2);
    }
    
    private static byte[] getChallenge(final byte[] array) {
        return getWidevineMediaDrmEngine().getChallenge(array);
    }
    
    public static String getDrmInfo() {
        if (DrmManagerRegistry.instance == null) {
            return "";
        }
        if (DrmManagerRegistry.instance.getDrmType() == 1) {
            if (DrmManagerRegistry.widevineInstanceL3) {
                return "WVL3";
            }
            return "WVL1";
        }
        else {
            if (DrmManagerRegistry.instance.getDrmType() == 0) {
                return "MGK";
            }
            return "X";
        }
    }
    
    private static byte[] getNccpSessionKeyRequest() {
        return getWidevineDrmManager().getNccpSessionKeyRequest();
    }
    
    private static byte[][] getSecureStops() {
        return (byte[][])getWidevineMediaDrmEngine().getSecureStops().toArray();
    }
    
    private static WidevineDrmManager getWidevineDrmManager() {
        if (isWidevineDrmAllowed()) {
            return (WidevineDrmManager)DrmManagerRegistry.instance;
        }
        throw new IllegalStateException("Private static method is called through JNI on non Widevine supported JB MR2+ device. That should not happen!");
    }
    
    public static WidevineMediaDrmEngine getWidevineMediaDrmEngine() {
        if (isWidevineDrmAllowed() && DrmManagerRegistry.mWidevineMediaDrmEngine == null) {
            createWidevineMediaDrmEngine();
        }
        return DrmManagerRegistry.mWidevineMediaDrmEngine;
    }
    
    public static boolean hasEsnChanged() {
        if (Log.isLoggable()) {
            Log.d("nf_drm", "currentDrmSystem : " + DrmManagerRegistry.currentDrmSystem + ", previousDrmSystem : " + DrmManagerRegistry.previousDrmSystem);
        }
        if (StringUtils.isEmpty(DrmManagerRegistry.currentDrmSystem)) {
            Log.e("nf_drm", "DrmManager instance is not created");
        }
        else if (!StringUtils.isEmpty(DrmManagerRegistry.previousDrmSystem) && !DrmManagerRegistry.currentDrmSystem.equals(DrmManagerRegistry.previousDrmSystem) && (!isLegacyDrmSystem(DrmManagerRegistry.currentDrmSystem) || !isLegacyDrmSystem(DrmManagerRegistry.previousDrmSystem))) {
            Log.d("nf_drm", "DrmSystemChanged");
            return true;
        }
        return false;
    }
    
    public static boolean isCurrentDrmWidevine() {
        return DrmManagerRegistry.instance != null && DrmManagerRegistry.instance.getDrmType() == 1;
    }
    
    public static boolean isDevicePredeterminedToUseWV() {
        return "flo".equals(Build.DEVICE) || "deb".equals(Build.DEVICE);
    }
    
    private static boolean isLegacyDrmSystem(final String s) {
        return StringUtils.isEmpty(s) || "LEGACY".equals(s) || "FORCE_LEGACY".equals(s);
    }
    
    private static boolean isPlatformDrmSupported() {
        return isWidevineDrmAllowed();
    }
    
    public static boolean isWidevineDrmAllowed() {
        final int androidVersion = AndroidUtils.getAndroidVersion();
        Label_0126: {
            if (!DrmManagerRegistry.enableWidevineL1) {
                break Label_0126;
            }
            if (androidVersion > 18) {
                Log.d("nf_drm", "Widevine level 1 check");
                if (!WidevineDrmManager.isWidewineSupported() || !isWidevineLevel1Supported() || !DrmManagerRegistry.mPlayerRequiredAdaptivePlayback) {
                    return false;
                }
            }
            else {
                if (androidVersion != 18 || !isDevicePredeterminedToUseWV() || !WidevineDrmManager.isWidewineSupported()) {
                    break Label_0126;
                }
                if (Log.isLoggable()) {
                    Log.d("nf_drm", "API level = " + androidVersion + " and Build.DEVICE =" + Build.DEVICE + ". Using WidevineDrmManager");
                    Log.d("nf_drm", "Flo/Deb devices running JB MR2  WITH Widevine support");
                    return true;
                }
            }
            return true;
        }
        if (DrmManagerRegistry.enableWidevineL3 || DrmManagerRegistry.enableWidevineL3_ABTest) {
            if (androidVersion == 19 && WidevineDrmManager.isWidewineSupported() && WidevineDrmManager.isValidKitKatWidevineL3SystemID()) {
                Log.d("nf_drm", "isWidevineDrmAllowed for kitkat: true");
                return true;
            }
            if (androidVersion >= 21 && WidevineDrmManager.isWidewineSupported()) {
                return true;
            }
        }
        Log.d("nf_drm", "isWidevineDrmAllowed: false");
        return false;
    }
    
    private static boolean isWidevineLevel1Supported() {
        return "L1".equalsIgnoreCase(DrmManagerRegistry.mMaxSecurityLevel);
    }
    
    private static void releaseSecureStops(final byte[] array) {
        getWidevineMediaDrmEngine().releaseSecureStops(array);
    }
    
    private static boolean restoreKeys(final String s, final String s2, final String s3) {
        return getWidevineDrmManager().restoreKeys(s, s2, s3);
    }
    
    private static byte[] sign(final byte[] array) {
        return getWidevineDrmManager().sign(array);
    }
    
    private static byte[] storeLicense(final byte[] array) {
        return getWidevineMediaDrmEngine().storeLicense(array);
    }
    
    private static boolean updateKeyResponse(final byte[] array, final byte[] array2, final byte[] array3, final String s) {
        return getWidevineDrmManager().updateNccpSessionKeyResponse(array, array2, array3, s);
    }
    
    private static boolean verify(final byte[] array, final byte[] array2) {
        return getWidevineDrmManager().verify(array, array2);
    }
}
