// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.util.StringUtils;
import android.os.Build;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;

public final class DrmManagerRegistry
{
    private static final String DRM_SYSTEM_ID_FORCE_LEGACY = "FORCE_LEGACY";
    private static final String DRM_SYSTEM_ID_LEGACY = "LEGACY";
    private static final String NEXUS7_DEB_DEVICE = "deb";
    private static final String NEXUS7_FLO_DEVICE = "flo";
    protected static final String TAG = "nf_drm";
    private static String currentDrmSystem;
    private static boolean disableWidevine;
    private static DrmManager instance;
    private static boolean mPlayerRequiredAdaptivePlayback;
    private static String mSecurityLevel;
    private static WidevineMediaDrmEngine mWidevineMediaDrmEngine;
    private static String previousDrmSystem;
    
    private static void clearLicense(final byte[] array) {
        getWidevineMediaDrmEngine().clearLicense(array);
    }
    
    public static DrmManager createDrmManager(Context instance, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final ServiceAgent.UserAgentInterface userAgentInterface, final ErrorLogging errorLogging, final DrmManager.DrmReadyCallback drmReadyCallback) {
        int androidVersion;
        Label_0102_Outer:Label_0112_Outer:
        while (true) {
            while (true) {
                Label_0257: {
                    while (true) {
                        synchronized (DrmManagerRegistry.class) {
                            androidVersion = AndroidUtils.getAndroidVersion();
                            DrmManagerRegistry.previousDrmSystem = PreferenceUtils.getStringPref(instance, "nf_drm_system_id", "LEGACY");
                            if (androidVersion >= 19) {
                                DrmManagerRegistry.mSecurityLevel = WidevineDrmManager.getMediaDrmSecurityLevels();
                                DrmManagerRegistry.mPlayerRequiredAdaptivePlayback = PlayerTypeFactory.isJPlayer2(PlayerTypeFactory.getCurrentType(instance));
                            }
                            while (true) {
                                while (true) {
                                    try {
                                        DrmManagerRegistry.disableWidevine = configurationAgentInterface.isDisableWidevineFlagSet();
                                        if (androidVersion >= 18 && isWidevineDrmAllowed()) {
                                            DrmManagerRegistry.instance = new WidevineDrmManager(instance, configurationAgentInterface, userAgentInterface, errorLogging, drmReadyCallback);
                                        }
                                        else {
                                            if (Log.isLoggable("nf_drm", 3)) {
                                                Log.d("nf_drm", "LegacyDrmManager for devices running android version = " + androidVersion);
                                            }
                                            DrmManagerRegistry.instance = new LegacyDrmManager(drmReadyCallback);
                                        }
                                        if (DrmManagerRegistry.instance.getDrmType() != 0) {
                                            break Label_0257;
                                        }
                                        if (DrmManagerRegistry.disableWidevine) {
                                            DrmManagerRegistry.currentDrmSystem = "FORCE_LEGACY";
                                            PreferenceUtils.putStringPref(instance, "nf_drm_system_id", DrmManagerRegistry.currentDrmSystem);
                                            if (Log.isLoggable("nf_drm", 3)) {
                                                Log.d("nf_drm", "currentDrmSystem : " + DrmManagerRegistry.currentDrmSystem + ", previousDrmSystem : " + DrmManagerRegistry.previousDrmSystem);
                                            }
                                            instance = (Context)DrmManagerRegistry.instance;
                                            return (DrmManager)instance;
                                        }
                                        break;
                                    }
                                    catch (Throwable t) {
                                        Log.e("nf_drm", "Unable to create WidevineDrmManager, default to LegacyDrmManager", t);
                                        DrmManagerRegistry.instance = new LegacyDrmManager(drmReadyCallback);
                                        continue Label_0102_Outer;
                                    }
                                    continue Label_0102_Outer;
                                }
                            }
                        }
                        DrmManagerRegistry.currentDrmSystem = "LEGACY";
                        continue Label_0112_Outer;
                    }
                }
                DrmManagerRegistry.currentDrmSystem = PreferenceUtils.getStringPref(instance, "nf_drm_system_id", null);
                continue;
            }
        }
    }
    
    private static void createWidevineMediaDrmEngine() {
        DrmManagerRegistry.mWidevineMediaDrmEngine = new WidevineMediaDrmEngine();
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
            return "WV" + DrmManagerRegistry.mSecurityLevel;
        }
        if (DrmManagerRegistry.instance.getDrmType() == 0) {
            return "MGK";
        }
        return "X";
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
    
    public static boolean isChangeToWidevineFromLegacy() {
        return isDrmSystemChanged() && "FORCE_LEGACY".equals(DrmManagerRegistry.previousDrmSystem);
    }
    
    private static boolean isCryptoReady() {
        return getWidevineDrmManager().isNccpCryptoFactoryReady();
    }
    
    public static boolean isCurrentDrmWidevine() {
        return DrmManagerRegistry.instance != null && DrmManagerRegistry.instance.getDrmType() == 1;
    }
    
    public static boolean isDevicePredeterminedToUseWV() {
        return "flo".equals(Build.DEVICE) || "deb".equals(Build.DEVICE);
    }
    
    public static boolean isDrmSystemChanged() {
        if (Log.isLoggable("nf_drm", 3)) {
            Log.d("nf_drm", "currentDrmSystem : " + DrmManagerRegistry.currentDrmSystem + ", previousDrmSystem : " + DrmManagerRegistry.previousDrmSystem);
        }
        if (StringUtils.isEmpty(DrmManagerRegistry.currentDrmSystem)) {
            Log.e("nf_drm", "DrmManager instance is not created");
        }
        else if (!DrmManagerRegistry.currentDrmSystem.equals(DrmManagerRegistry.previousDrmSystem) && (!isLegacyDrmSystem(DrmManagerRegistry.currentDrmSystem) || !isLegacyDrmSystem(DrmManagerRegistry.previousDrmSystem))) {
            Log.d("nf_drm", "DrmSystemChanged");
            return true;
        }
        return false;
    }
    
    private static boolean isLegacyDrmSystem(final String s) {
        return StringUtils.isEmpty(s) || "LEGACY".equals(s) || "FORCE_LEGACY".equals(s);
    }
    
    private static boolean isPlatformDrmSupported() {
        return isWidevineDrmAllowed();
    }
    
    public static boolean isWidevineDrmAllowed() {
        boolean b = true;
        if (!DrmManagerRegistry.disableWidevine) {
            final int androidVersion = AndroidUtils.getAndroidVersion();
            if (androidVersion > 18) {
                if (!WidevineDrmManager.isWidewineSupported() || !isWidevineLevel1Supported() || !DrmManagerRegistry.mPlayerRequiredAdaptivePlayback) {
                    b = false;
                }
                return b;
            }
            if (androidVersion == 18 && isDevicePredeterminedToUseWV() && WidevineDrmManager.isWidewineSupported()) {
                if (Log.isLoggable("nf_drm", 3)) {
                    Log.d("nf_drm", "API level = " + androidVersion + " and Build.DEVICE =" + Build.DEVICE + ". Using WidevineDrmManager");
                    Log.d("nf_drm", "Flo/Deb devices running JB MR2  WITH Widevine support");
                }
                return true;
            }
        }
        return false;
    }
    
    private static boolean isWidevineLevel1Supported() {
        return "L1".equalsIgnoreCase(DrmManagerRegistry.mSecurityLevel);
    }
    
    private static void releaseSecureStops(final byte[] array) {
        getWidevineMediaDrmEngine().releaseSecureStops(array);
    }
    
    private static byte[] sign(final byte[] array) {
        return getWidevineDrmManager().sign(array);
    }
    
    private static byte[] storeLicense(final byte[] array) {
        return getWidevineMediaDrmEngine().storeLicense(array);
    }
    
    private static boolean updateKeyResponse(final byte[] array, final byte[] array2, final byte[] array3) {
        return getWidevineDrmManager().updateNccpSessionKeyResponse(array, array2, array3);
    }
    
    private static boolean verify(final byte[] array, final byte[] array2) {
        return getWidevineDrmManager().verify(array, array2);
    }
}
