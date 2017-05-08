// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.util.Base64;
import android.annotation.SuppressLint;
import android.os.Build;
import java.util.Arrays;
import android.media.MediaDrm$OnEventListener;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import android.os.Build$VERSION;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import java.util.UUID;

public class MediaDrmUtils
{
    private static final String NETFLIX_MEDIADRM_APPID = "com.netflix.mediaclient";
    private static final String NEXUS7_DEB_DEVICE = "deb";
    private static final String NEXUS7_FLO_DEVICE = "flo";
    public static final String PROPERTY_SECURITY_LEVEL = "securityLevel";
    public static final String PROPERTY_SYSTEM_ID = "systemId";
    private static final String SCHEMA_NOT_SUPPORTED_PLACEHOLDER = "NETFLIX_NA";
    public static final String SECURITY_LEVEL_FAILURE_TO_RETRIEVE = "SECURITY_LEVEL_GET_FAILURE";
    public static final String SYSTEM_ID_FAILURE_TO_RETRIEVE = "SYSTEMID_GET_FAILURE";
    private static String TAG;
    private static final MediaDrmUtils$WidevineSupport WIDEVINE;
    public static final UUID WIDEVINE_SCHEME;
    private static CryptoProvider sCryptoProvider;
    
    static {
        MediaDrmUtils.TAG = "MediaDrmUtils";
        WIDEVINE_SCHEME = new UUID(-1301668207276963122L, -6645017420763422227L);
        WIDEVINE = new MediaDrmUtils$WidevineSupport(null);
    }
    
    public static void dumpKeyStatus(final String s, final MediaDrm mediaDrm, final byte[] array) {
        Log.logByteArrayRaw(s, "SessionId", array);
        Log.d(s, "===== key status ======");
        while (true) {
            try {
                Log.d(s, mediaDrm.queryKeyStatus(array).toString());
                Log.d(s, "===== end of key status ======");
            }
            catch (Exception ex) {
                Log.w(s, "failed to queryKeyStatus()");
                continue;
            }
            break;
        }
    }
    
    private static CryptoProvider findCryptoProvider(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        while (true) {
        Label_0170:
            while (true) {
                Label_0158: {
                    synchronized (MediaDrmUtils.class) {
                        CryptoProvider cryptoProvider;
                        if (!isWidewineSupported()) {
                            Log.w(MediaDrmUtils.TAG, "getCryptoProvider: Widevine is NOT supported on this device.");
                            cryptoProvider = CryptoProvider.LEGACY;
                        }
                        else {
                            final boolean jPlayer2 = PlayerTypeFactory.isJPlayer2(PlayerTypeFactory.getCurrentType(context));
                            if (!serviceAgent$ConfigurationAgentInterface.isWidevineL1Enabled()) {
                                break Label_0158;
                            }
                            Log.d(MediaDrmUtils.TAG, "Widevine L1 is enabled, check if we failed before");
                            if (isWidevineL1FailedOnDevice(context)) {
                                Log.w(MediaDrmUtils.TAG, "Widevine L1 was whitelisted, but it failed on this device, see fallback option.");
                            }
                            else {
                                Log.d(MediaDrmUtils.TAG, "Widevine L1 did not failed on this device and L1 was whitelisted, check if device really supports L1. PlayerRequiredAdaptivePlayback : %b", jPlayer2);
                                if (isWidevineSecurityLevelL1() && jPlayer2) {
                                    Log.d(MediaDrmUtils.TAG, "getCryptoProvider:Widevine L1 will be used");
                                    cryptoProvider = CryptoProvider.WIDEVINE_L1;
                                    return cryptoProvider;
                                }
                                Log.w(MediaDrmUtils.TAG, "getCryptoProvider:Widevine L1 is not supported on device or it has problem in playback, go for fallback");
                            }
                            if (!serviceAgent$ConfigurationAgentInterface.shouldForceLegacyCrypto()) {
                                break Label_0170;
                            }
                            Log.w(MediaDrmUtils.TAG, "getCryptoProvider: Widevine is supported on this device, but we have configuration override to force legacy crypto!");
                            cryptoProvider = CryptoProvider.LEGACY;
                        }
                        return cryptoProvider;
                    }
                }
                Log.w(MediaDrmUtils.TAG, "Widevine L1 is NOT enabled, see fallback option.");
                continue;
            }
            final Context context2;
            if (isWidevineL3FailedOnDevice(context2)) {
                Log.w(MediaDrmUtils.TAG, "Widevine L3 failed on this device, fallback to MGK.");
            }
            else {
                if (isValidWidevineL3SystemID()) {
                    Log.d(MediaDrmUtils.TAG, "getCryptoProvider: for kitkat: Widevine L3");
                    return CryptoProvider.WIDEVINE_L3;
                }
                Log.d(MediaDrmUtils.TAG, "getCryptoProvider: use legacy crypto because KK device can not support L3!");
            }
            return CryptoProvider.LEGACY;
        }
    }
    
    private static String getAndroidVersion() {
        String release;
        if (StringUtils.isEmpty(release = Build$VERSION.RELEASE)) {
            release = "";
        }
        return release;
    }
    
    public static CryptoProvider getCryptoProvider(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        synchronized (MediaDrmUtils.class) {
            if (MediaDrmUtils.sCryptoProvider == null) {
                MediaDrmUtils.sCryptoProvider = findCryptoProvider(context, serviceAgent$ConfigurationAgentInterface);
            }
            return MediaDrmUtils.sCryptoProvider;
        }
    }
    
    public static String getDrmInfo() {
        final CryptoProvider cryptoProvider = CryptoManagerRegistry.getCryptoManager().getCryptoProvider();
        if (cryptoProvider == CryptoProvider.WIDEVINE_L3) {
            return "WVL3";
        }
        if (cryptoProvider == CryptoProvider.WIDEVINE_L1) {
            return "WVL1";
        }
        return "PLRD";
    }
    
    public static MediaDrm getNewMediaDrmInstance(final MediaDrm$OnEventListener onEventListener) {
        final MediaDrm mediaDrm = new MediaDrm(MediaDrmUtils.WIDEVINE_SCHEME);
        if (onEventListener != null) {
            mediaDrm.setOnEventListener(onEventListener);
        }
        setSecurityLevelIfNeeded(mediaDrm);
        setAppId(mediaDrm);
        return mediaDrm;
    }
    
    public static String getPropertyByteArrayAsStringSafely(final MediaDrm mediaDrm, final String s) {
        try {
            return Arrays.toString(mediaDrm.getPropertyByteArray(s));
        }
        catch (Throwable t) {
            Log.e(MediaDrmUtils.TAG, t, "Failed to retrieve MediaDrm property %s", s);
            return null;
        }
    }
    
    public static String getPropertyStringSafely(final MediaDrm mediaDrm, final String s) {
        try {
            return mediaDrm.getPropertyString(s);
        }
        catch (Throwable t) {
            Log.e(MediaDrmUtils.TAG, t, "Failed to retrieve MediaDrm property %s", s);
            return null;
        }
    }
    
    private static int getSupportedCryptoProvider() {
        if (!MediaDrmUtils.WIDEVINE.isSupported()) {
            return CryptoProvider.LEGACY.NCCP_VALUE;
        }
        if (MediaDrmUtils.WIDEVINE.isL1()) {
            return CryptoProvider.WIDEVINE_L1.NCCP_VALUE;
        }
        return CryptoProvider.WIDEVINE_L3.NCCP_VALUE;
    }
    
    private static int getUsedCryptoProvider() {
        return MediaDrmUtils.sCryptoProvider.NCCP_VALUE;
    }
    
    public static String getWidevineSystemId() {
        if (MediaDrmUtils.WIDEVINE.systemId == null) {
            return "";
        }
        return MediaDrmUtils.WIDEVINE.systemId;
    }
    
    public static boolean isDevicePredeterminedToUseWV() {
        return "flo".equals(Build.DEVICE) || "deb".equals(Build.DEVICE);
    }
    
    private static boolean isValidWidevineL3SystemID() {
        final String access$100 = MediaDrmUtils.WIDEVINE.systemId;
        if (Log.isLoggable()) {
            Log.d(MediaDrmUtils.TAG, "MediaDrm system ID is: " + access$100);
        }
        if (!access$100.equalsIgnoreCase("4266") && !StringUtils.isEmpty(access$100) && access$100.trim().length() <= 5) {
            Log.d(MediaDrmUtils.TAG, "Valid System ID.");
            return true;
        }
        return false;
    }
    
    public static boolean isWidevineDrmAllowed() {
        return MediaDrmUtils.sCryptoProvider != CryptoProvider.LEGACY;
    }
    
    private static boolean isWidevineL1FailedOnDevice(final Context context) {
        return PreferenceUtils.getBooleanPref(context, "disable_widevine", false);
    }
    
    private static boolean isWidevineL3FailedOnDevice(final Context context) {
        return PreferenceUtils.getBooleanPref(context, "disable_widevine_l3", false);
    }
    
    public static boolean isWidevineSecurityLevelL1() {
        return MediaDrmUtils.WIDEVINE.isL1;
    }
    
    public static boolean isWidevineSecurityLevelL3() {
        return MediaDrmUtils.WIDEVINE.isL3;
    }
    
    @SuppressLint({ "NewApi" })
    public static boolean isWidewineSupported() {
        return MediaDrmUtils.WIDEVINE.supported;
    }
    
    public static String safeBase64Encode(final byte[] array) {
        if (array == null) {
            return "[null]";
        }
        if (array.length < 1) {
            return "[empty]";
        }
        return Base64.encodeToString(array, 2);
    }
    
    private static void setAppId(final MediaDrm mediaDrm) {
        if (AndroidUtils.getAndroidVersion() < 22) {
            return;
        }
        try {
            mediaDrm.setPropertyString("appId", "com.netflix.mediaclient");
        }
        catch (Exception ex) {
            Log.e("WidevineMediaDrm", "ignore exceptions", ex);
        }
    }
    
    private static void setSecurityLevelIfNeeded(final MediaDrm securityLevelL3) {
        if (CryptoManagerRegistry.getCryptoManager().getCryptoProvider() == CryptoProvider.WIDEVINE_L3) {
            setSecurityLevelL3(securityLevelL3);
        }
    }
    
    public static void setSecurityLevelL3(final MediaDrm mediaDrm) {
        if (mediaDrm == null) {
            throw new IllegalStateException("MediaDrm is null");
        }
        Log.d(MediaDrmUtils.TAG, "Forcing L3 security level...");
        mediaDrm.setPropertyString("securityLevel", "L3");
    }
    
    public static void updateCryptoProvideToLegacy() {
        synchronized (MediaDrmUtils.class) {
            MediaDrmUtils.sCryptoProvider = CryptoProvider.LEGACY;
        }
    }
    
    public static boolean useWidevineSecurityLevelL1(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        return serviceAgent$ConfigurationAgentInterface.isWidevineL1Enabled() && isWidevineSecurityLevelL1();
    }
}
