// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.repository;

import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.servicemgr.IVoip$UserType;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IVoip$AuthorizationTokens;
import android.content.Context;

public final class SecurityRepository
{
    private static final String BOOTLOADER_PARAMETER_CERTIFICATION_VERSION = "certification_version";
    private static final String BOOTLOADER_PARAMETER_DEVICE_CATEGORY = "device_cat";
    private static final String BOOTLOADER_PARAMETER_DEVICE_TYPE = "device_type";
    private static final String BOOTLOADER_PARAMETER_FULL_ESN = "e";
    private static final String BOOTLOADER_PARAMETER_OS = "os";
    private static final String BOOTLOADER_PARAMETER_SDK_VERSION = "sdk_version";
    private static final String BOOTLOADER_PARAMETER_SOFTWARE_VERSION = "sw_version";
    private static final String BOOTLOADER_PARAMETER_WEB_API_VERSION = "v";
    private static final String BOOTLOADER_URL = "https://www.netflix.com";
    private static final String BOOTLOADER_WEB_API_VERSION = "2.0";
    private static final int CONSTANT_CRITTERCISM_APP_ID = 2;
    private static final int CONSTANT_DEVICE_ID_TOKEN = 1;
    public static final String CUSTOMER_SUPPORT_FILE_NAME = "cs.dat";
    private static final String ESN_DELIM = "-";
    private static final String MDXJS_VERSION_VALUE = "1.1.6-android";
    private static final String MDXLIB_VERSION_VALUE = "2013.3";
    private static final String MODEL_DELIM = "_";
    public static final String NCCP_VERSION = "NCCP/2.15";
    private static final String NRDAPP_VERSION_VALUE = "2013.2";
    private static final String NRDLIB_VERSION_VALUE = "2013.2";
    private static final String NRD_SDK_VERSION_VALUE = "4.0.4";
    public static final String SENDER_ID = "484286080282";
    private static final String TAG = "SEC";
    private static String crittercismAppId;
    private static String deviceIdToken;
    private static String esnPrefix;
    private static boolean sLoaded;
    
    public static String getBootloaderParameterCertificationVersion() {
        return "certification_version";
    }
    
    public static String getBootloaderParameterDeviceCategory() {
        return "device_cat";
    }
    
    public static String getBootloaderParameterDeviceType() {
        return "device_type";
    }
    
    public static String getBootloaderParameterFullEsn() {
        return "e";
    }
    
    public static String getBootloaderParameterOs() {
        return "os";
    }
    
    public static String getBootloaderParameterSdkVersion() {
        return "sdk_version";
    }
    
    public static String getBootloaderParameterSoftwareVersion() {
        return "sw_version";
    }
    
    public static String getBootloaderParameterWebApiVersion() {
        return "v";
    }
    
    public static String getBootloaderUrl() {
        return "https://www.netflix.com";
    }
    
    public static String getBootloaderWebApiVersion() {
        return "2.0";
    }
    
    public static String getCrittercismAppId() {
        return SecurityRepository.crittercismAppId;
    }
    
    public static IVoip$AuthorizationTokens getDefaultTokens(final Context context) {
        final String absolutePath = context.getFilesDir().getAbsolutePath();
        Label_0153: {
            if (!absolutePath.endsWith("/")) {
                break Label_0153;
            }
            String s = absolutePath + "cs.dat";
            while (true) {
                if (Log.isLoggable()) {
                    Log.d("SEC", "Load customer support secure data from " + s);
                }
                final String native_loadVoipTokens = native_loadVoipTokens(s);
                if (Log.isLoggable()) {
                    Log.d("SEC", "Received" + native_loadVoipTokens);
                }
                try {
                    final JSONObject jsonObject = new JSONObject(native_loadVoipTokens);
                    final String string = jsonObject.getString("userToken");
                    final String string2 = jsonObject.getString("encToken");
                    IVoip$AuthorizationTokens voip$AuthorizationTokens;
                    if (StringUtils.isEmpty(string) || StringUtils.isEmpty(string2)) {
                        Log.e("SEC", "Credentials are empty!");
                        voip$AuthorizationTokens = null;
                    }
                    else {
                        final IVoip$AuthorizationTokens voip$AuthorizationTokens2 = voip$AuthorizationTokens = new IVoip$AuthorizationTokens(string, string2, IVoip$UserType.CS_DEFAULT, System.currentTimeMillis() + 1471228928L);
                        if (Log.isLoggable()) {
                            Log.d("SEC", "getDefaultTokens: " + voip$AuthorizationTokens2);
                            return voip$AuthorizationTokens2;
                        }
                    }
                    return voip$AuthorizationTokens;
                    s = absolutePath + "/cs.dat";
                    continue;
                }
                catch (Throwable t) {
                    Log.e("SEC", "Failed to load default tokens", t);
                    return null;
                }
                break;
            }
        }
    }
    
    public static String getDeviceIdToken() {
        return SecurityRepository.deviceIdToken;
    }
    
    public static String getEsnDelim() {
        return "-";
    }
    
    public static String getEsnPrefix() {
        return SecurityRepository.esnPrefix;
    }
    
    public static final int getLibraryVersion() {
        return native_getLibraryVersion();
    }
    
    public static String getMdxJsVersion() {
        return "1.1.6-android";
    }
    
    public static String getMdxLibVersion() {
        return "2013.3";
    }
    
    public static String getModelDelim() {
        return "_";
    }
    
    public static String getNrdAppVersion() {
        return "2013.2";
    }
    
    public static String getNrdLibVersion() {
        return "2013.2";
    }
    
    public static String getNrdSdkVersion() {
        return "4.0.4";
    }
    
    public static String getSystemPropety(final String s) {
        return native_getSystemProperty(s);
    }
    
    public static boolean isLoaded() {
        return SecurityRepository.sLoaded;
    }
    
    public static boolean loadLibraries(final Context context) {
        boolean sLoaded = true;
        synchronized (SecurityRepository.class) {
            if (SecurityRepository.sLoaded) {
                Log.w("SEC", "We already loaded native libraries!");
            }
            else {
                FileUtils.copyFileFromAssetToFS(context, "cs.dat", "cs.dat", false);
                Log.d("SEC", "We copied cs.dat");
                SecurityRepository.sLoaded = DeviceUtils.loadNativeLibrary(context, "netflixmp_jni");
                if (SecurityRepository.sLoaded) {
                    native_init(new byte[0]);
                    SecurityRepository.deviceIdToken = native_getConstant(1);
                    SecurityRepository.crittercismAppId = native_getConstant(2);
                }
                sLoaded = SecurityRepository.sLoaded;
            }
            return sLoaded;
        }
    }
    
    private static final native String native_getConstant(final int p0);
    
    private static final native int native_getLibraryVersion();
    
    private static native String native_getSystemProperty(final String p0);
    
    private static final native void native_init(final byte[] p0);
    
    private static native String native_loadVoipTokens(final String p0);
}
