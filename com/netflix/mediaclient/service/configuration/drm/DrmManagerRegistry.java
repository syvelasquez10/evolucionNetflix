// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.MediaDrmUtils;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;

public final class DrmManagerRegistry
{
    public static final String DRM_SYSTEM_ID_FORCE_LEGACY = "FORCE_LEGACY";
    public static final String DRM_SYSTEM_ID_LEGACY = "LEGACY";
    public static final String DRM_SYSTEM_ID_M_PLUS_MGK = "M_PLUS_MGK";
    protected static final String TAG = "nf_drm";
    private static String currentDrmSystem;
    private static DrmManager instance;
    private static WidevineMediaDrmEngine mWidevineMediaDrmEngine;
    private static CryptoProvider previousCryptoProvider;
    private static String previousDrmSystem;
    
    private static void clearKeys(final String s) {
        getWidevineDrmManager().clearKeys(s);
    }
    
    private static void clearLicense(final byte[] array) {
        getWidevineMediaDrmEngine().clearLicense(array);
    }
    
    public static DrmManager createDrmManager(Context instance, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final ServiceAgent$UserAgentInterface serviceAgent$UserAgentInterface, final ErrorLogging errorLogging, final IErrorHandler errorHandler, final DrmManager$DrmReadyCallback drmManager$DrmReadyCallback) {
        CryptoProvider cryptoProvider;
        String currentDrmSystem;
        Label_0067_Outer:Label_0094_Outer:
        while (true) {
            while (true) {
                Label_0312: {
                    while (true) {
                    Label_0306:
                        while (true) {
                            Label_0258: {
                                synchronized (DrmManagerRegistry.class) {
                                    setPreviousDrm(instance);
                                    cryptoProvider = MediaDrmUtils.getCryptoProvider(instance, serviceAgent$ConfigurationAgentInterface);
                                    try {
                                        if (cryptoProvider == CryptoProvider.WIDEVINE_L1) {
                                            Log.d("nf_drm", "WidevineDrmManager L1 created");
                                            DrmManagerRegistry.instance = new WidevineL1DrmManager(instance, serviceAgent$UserAgentInterface, serviceAgent$ConfigurationAgentInterface, errorLogging, errorHandler, drmManager$DrmReadyCallback);
                                        }
                                        else {
                                            if (cryptoProvider != CryptoProvider.WIDEVINE_L3) {
                                                break Label_0258;
                                            }
                                            Log.d("nf_drm", "WidevineDrmManager L3 created");
                                            DrmManagerRegistry.instance = new WidevineL3DrmManager(instance, serviceAgent$UserAgentInterface, serviceAgent$ConfigurationAgentInterface, errorLogging, errorHandler, drmManager$DrmReadyCallback);
                                        }
                                        if (cryptoProvider != CryptoProvider.LEGACY) {
                                            break Label_0312;
                                        }
                                        if (AndroidUtils.getAndroidVersion() > 22) {
                                            currentDrmSystem = "M_PLUS_MGK";
                                            PreferenceUtils.putStringPref(instance, "nf_drm_system_id", DrmManagerRegistry.currentDrmSystem = currentDrmSystem);
                                            PreferenceUtils.putStringPref(instance, "nf_drm_crypto_provider", CryptoProvider.LEGACY.name());
                                            if (Log.isLoggable()) {
                                                Log.d("nf_drm", "currentDrmSystem : " + DrmManagerRegistry.currentDrmSystem + ", previousDrmSystem : " + DrmManagerRegistry.previousDrmSystem);
                                                Log.d("nf_drm", "current crypto : " + DrmManagerRegistry.instance.getCryptoProvider() + ", previous crypto : " + DrmManagerRegistry.previousCryptoProvider);
                                            }
                                            instance = (Context)DrmManagerRegistry.instance;
                                            return (DrmManager)instance;
                                        }
                                        break Label_0306;
                                    }
                                    catch (Throwable t) {
                                        Log.e("nf_drm", "Unable to create WidevineDrmManager, default to LegacyDrmManager", t);
                                        DrmManagerRegistry.instance = new LegacyDrmManager(drmManager$DrmReadyCallback);
                                        continue Label_0067_Outer;
                                    }
                                    continue Label_0067_Outer;
                                }
                            }
                            if (Log.isLoggable()) {
                                Log.d("nf_drm", "LegacyDrmManager for devices running android version = " + AndroidUtils.getAndroidVersion());
                            }
                            DrmManagerRegistry.instance = new LegacyDrmManager(drmManager$DrmReadyCallback);
                            continue Label_0067_Outer;
                        }
                        currentDrmSystem = "LEGACY";
                        continue Label_0094_Outer;
                    }
                }
                DrmManagerRegistry.currentDrmSystem = PreferenceUtils.getStringPref(instance, "nf_drm_system_id", null);
                continue;
            }
        }
    }
    
    private static void createWidevineMediaDrmEngine() {
        DrmManagerRegistry.mWidevineMediaDrmEngine = new WidevineMediaDrmEngine(DrmManagerRegistry.instance.getCryptoProvider() == CryptoProvider.WIDEVINE_L3);
    }
    
    private static byte[] decrypt(final byte[] array, final byte[] array2) {
        return getWidevineDrmManager().decrypt(array, array2);
    }
    
    public static boolean drmSupportsHd() {
        return isCurrentDrmWidevine() && MediaDrmUtils.isWidevineSecurityLevelL1();
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
        if (DrmManagerRegistry.instance.getCryptoProvider() == CryptoProvider.WIDEVINE_L3) {
            return "WVL3";
        }
        if (DrmManagerRegistry.instance.getCryptoProvider() == CryptoProvider.WIDEVINE_L1) {
            return "WVL1";
        }
        if (DrmManagerRegistry.instance.getCryptoProvider() == CryptoProvider.LEGACY) {
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
        if (MediaDrmUtils.isWidevineDrmAllowed()) {
            return (WidevineDrmManager)DrmManagerRegistry.instance;
        }
        throw new IllegalStateException("Private static method is called through JNI on non Widevine supported JB MR2+ device. That should not happen!");
    }
    
    public static WidevineMediaDrmEngine getWidevineMediaDrmEngine() {
        if (MediaDrmUtils.isWidevineDrmAllowed() && DrmManagerRegistry.mWidevineMediaDrmEngine == null) {
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
        else if (!StringUtils.isEmpty(DrmManagerRegistry.previousDrmSystem)) {
            int n;
            if (isLegacyDrmSystem(DrmManagerRegistry.currentDrmSystem) && isLegacyDrmSystem(DrmManagerRegistry.previousDrmSystem)) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                Log.d("nf_drm", "Both previous and current DEM is legacy, ESN is NOT changed");
                return false;
            }
            if (!DrmManagerRegistry.currentDrmSystem.equals(DrmManagerRegistry.previousDrmSystem)) {
                Log.d("nf_drm", "Widevine System ID changed, ESN is changed");
                return true;
            }
            Log.d("nf_drm", "Widevine System ID is NOT changed, verify if security level is changed");
            if (DrmManagerRegistry.instance.getCryptoProvider() != DrmManagerRegistry.previousCryptoProvider) {
                if (Log.isLoggable()) {
                    Log.d("nf_drm", "Crypto provider is changed from " + DrmManagerRegistry.previousCryptoProvider + " to " + DrmManagerRegistry.instance.getCryptoProvider());
                }
                return true;
            }
            if (Log.isLoggable()) {
                Log.d("nf_drm", "Same crypto provider " + DrmManagerRegistry.previousCryptoProvider + ". No change!");
                return false;
            }
        }
        return false;
    }
    
    public static boolean isCurrentDrmWidevine() {
        boolean b = false;
        if (DrmManagerRegistry.instance != null) {
            b = b;
            if (DrmManagerRegistry.instance.getCryptoProvider() != CryptoProvider.LEGACY) {
                b = true;
            }
        }
        return b;
    }
    
    public static boolean isLegacyDrmSystem(final String s) {
        return StringUtils.isEmpty(s) || "LEGACY".equals(s) || "FORCE_LEGACY".equals(s) || "M_PLUS_MGK".equals(s);
    }
    
    private static boolean isPlatformDrmSupported() {
        return MediaDrmUtils.isWidevineDrmAllowed();
    }
    
    private static void releaseSecureStops(final byte[] array) {
        getWidevineMediaDrmEngine().releaseSecureStops(array);
    }
    
    private static boolean restoreKeys(final String s, final String s2, final String s3) {
        return getWidevineDrmManager().restoreKeys(s, s2, s3);
    }
    
    private static void setPreviousDrm(final Context context) {
        DrmManagerRegistry.previousDrmSystem = PreferenceUtils.getStringPref(context, "nf_drm_system_id", null);
        DrmManagerRegistry.previousCryptoProvider = CryptoProvider.fromName(PreferenceUtils.getStringPref(context, "nf_drm_crypto_provider", null));
        if (StringUtils.isNotEmpty(DrmManagerRegistry.previousDrmSystem) && DrmManagerRegistry.previousCryptoProvider == null) {
            if (!isLegacyDrmSystem(DrmManagerRegistry.previousDrmSystem)) {
                Log.d("nf_drm", "Previous crypto provider was Widevine L1...");
                DrmManagerRegistry.previousCryptoProvider = CryptoProvider.WIDEVINE_L1;
                return;
            }
            Log.d("nf_drm", "Previous crypto provider was legacy...");
            DrmManagerRegistry.previousCryptoProvider = CryptoProvider.LEGACY;
        }
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
