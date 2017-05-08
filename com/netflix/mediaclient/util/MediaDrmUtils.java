// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.media.MediaDrm;
import com.netflix.mediaclient.Log;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import android.os.Build$VERSION;
import java.util.UUID;

public class MediaDrmUtils
{
    public static final String FAILURE_TO_RELEASE_MEDIADRM = "RELEASE_FAILURE";
    public static final String PROPERTY_SECURITY_LEVEL = "securityLevel";
    public static final String PROPERTY_SYSTEM_ID = "systemId";
    public static final String SECURITY_LEVEL_FAILURE_TO_RETRIEVE = "SECURITY_LEVEL_GET_FAILURE";
    public static final String SYSTEM_ID_FAILURE_TO_RETRIEVE = "SYSTEMID_GET_FAILURE";
    private static String TAG;
    private static final MediaDrmUtils$WidevineSupport WIDEVINE;
    public static final UUID WIDEVINE_SCHEME;
    
    static {
        MediaDrmUtils.TAG = "MediaDrmUtils";
        WIDEVINE_SCHEME = new UUID(-1301668207276963122L, -6645017420763422227L);
        WIDEVINE = new MediaDrmUtils$WidevineSupport(null);
    }
    
    private static String getAndroidVersion() {
        String release;
        if (StringUtils.isEmpty(release = Build$VERSION.RELEASE)) {
            release = "";
        }
        return release;
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
        if (!DrmManagerRegistry.isCurrentDrmWidevine()) {
            return CryptoProvider.LEGACY.NCCP_VALUE;
        }
        if (DrmManagerRegistry.isWidevineInstanceL3()) {
            return CryptoProvider.WIDEVINE_L3.NCCP_VALUE;
        }
        return CryptoProvider.WIDEVINE_L1.NCCP_VALUE;
    }
    
    public static String getWidevineSystemId() {
        if (MediaDrmUtils.WIDEVINE.systemId == null) {
            return "";
        }
        return MediaDrmUtils.WIDEVINE.systemId;
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
}
