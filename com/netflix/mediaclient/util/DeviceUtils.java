// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.service.configuration.esn.BaseEsnProvider;
import android.view.KeyCharacterMap;
import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import com.netflix.mediaclient.ui.details.DeviceCapabilityProvider;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.app.Activity;
import android.os.Build;
import android.os.Build$VERSION;
import java.util.concurrent.atomic.AtomicBoolean;

public final class DeviceUtils
{
    public static final boolean DEVICE_WITH_MENU_BUTTON_BUG;
    public static final int SCREEN_SIZE_LARGE = 3;
    public static final int SCREEN_SIZE_NORMAL = 2;
    public static final int SCREEN_SIZE_SMALL = 1;
    public static final int SCREEN_SIZE_XLARGE = 4;
    private static final String SYSTEM_PROPERTY_LOCAL_PLAYBACK_ENABLED = "ro.nrdp.playback.enable";
    private static final String SYSTEM_PROPERTY_REMOTE_CONTROLS_ENABLED = "ro.nrdp.mdx.remotecontrols";
    private static final String TAG = "nf_device_utils";
    private static AtomicBoolean sFirstStartAfterInstall;
    
    static {
        DEVICE_WITH_MENU_BUTTON_BUG = (Build$VERSION.SDK_INT <= 16 && Build.MANUFACTURER.compareTo("LGE") == 0);
    }
    
    public static void forceHideKeyboard(final Activity activity, final EditText editText) {
        if (activity != null) {
            forceHideKeyboard((InputMethodManager)activity.getSystemService("input_method"), editText);
        }
    }
    
    public static void forceHideKeyboard(final InputMethodManager inputMethodManager, final EditText editText) {
        if (inputMethodManager != null && editText != null) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
    
    public static int getBasicScreenOrientation(final Context context) {
        return context.getResources().getConfiguration().orientation;
    }
    
    public static String getCertificationVersion(final Context context) {
        final String trim = getSoftwareVersion(context).trim();
        if (Log.isLoggable()) {
            Log.d("nf_device_utils", "SV: " + trim);
        }
        final int index = trim.indexOf(" ");
        String substring = trim;
        if (index > 0) {
            substring = trim.substring(0, index);
        }
        if (Log.isLoggable()) {
            Log.d("nf_device_utils", "CV: " + substring);
        }
        return substring;
    }
    
    public static long getFirstStartTime(final Context context) {
        final long n = -1L;
        // monitorenter(DeviceUtils.class)
        long longPref = n;
        try {
            if (DeviceUtils.sFirstStartAfterInstall != null) {
                if (DeviceUtils.sFirstStartAfterInstall.get()) {
                    longPref = n;
                }
                else {
                    longPref = PreferenceUtils.getLongPref(context, "nf_first_start_after_install", -1L);
                }
            }
            return longPref;
        }
        finally {
        }
        // monitorexit(DeviceUtils.class)
    }
    
    public static DeviceCapabilityProvider getLocalCapabilities(final ServiceManager serviceManager) {
        return new DeviceUtils$1(serviceManager);
    }
    
    public static String getNativeLibraryDirectory(final Context context) {
        final ApplicationInfo applicationInfo = context.getApplicationInfo();
        if ((applicationInfo.flags & 0x80) != 0x0 || (applicationInfo.flags & 0x1) == 0x0) {
            return applicationInfo.nativeLibraryDir;
        }
        return null;
    }
    
    public static int getScreenHeightInDPs(final Context context) {
        return context.getResources().getConfiguration().screenHeightDp;
    }
    
    public static int getScreenHeightInPixels(final Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    
    public static String getScreenResolutionCategoryString(final Context context) {
        switch (getScreenResolutionDpi(context)) {
            default: {
                return "unknown";
            }
            case 240: {
                return "hdpi";
            }
            case 120: {
                return "ldpi";
            }
            case 160: {
                return "mdpi";
            }
            case 213: {
                return "tvdpi";
            }
            case 320: {
                return "xhdpi";
            }
            case 480: {
                return "xxhdpi";
            }
            case 640: {
                return "xxxhdpi";
            }
        }
    }
    
    public static int getScreenResolutionDpi(final Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }
    
    @SuppressLint({ "InlinedApi" })
    public static int getScreenSensorOrientation(final Context context) {
        if (isLandscape(context)) {
            return 6;
        }
        return 7;
    }
    
    public static int getScreenSizeCategory(final Context context) {
        return context.getResources().getConfiguration().screenLayout & 0xF;
    }
    
    public static String getScreenSizeCategoryString(final Context context) {
        switch (getScreenSizeCategory(context)) {
            default: {
                return "unknown";
            }
            case 1: {
                return "small";
            }
            case 2: {
                return "normal";
            }
            case 3: {
                return "large";
            }
            case 4: {
                return "xlarge";
            }
            case 0: {
                return "undefined";
            }
        }
    }
    
    public static int getScreenWidthInDPs(final Context context) {
        return context.getResources().getConfiguration().screenWidthDp;
    }
    
    public static int getScreenWidthInPixels(final Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    
    public static String getSoftwareVersion(final Context context) {
        String version;
        if ((version = AndroidManifestUtils.getVersion(context)) == null) {
            version = "N/A";
        }
        return version;
    }
    
    public static boolean hasHardwareNavigationKeys() {
        final boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        final boolean deviceHasKey2 = KeyCharacterMap.deviceHasKey(3);
        return deviceHasKey && deviceHasKey2;
    }
    
    private static long hashCode(final String s) {
        long n = 0L;
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            n = n * 31L + charArray[i];
        }
        return n;
    }
    
    public static void hideSoftKeyboard(final Activity activity) {
        activity.getWindow().setSoftInputMode(2);
    }
    
    public static boolean isDeviceEnabled(final Context context, final int n) {
        boolean b;
        boolean b2;
        long hashCode = 0L;
        int n2;
        int n3 = 0;
        final String s;
        Label_0114_Outer:Label_0195_Outer:
        while (true) {
            b = true;
            b2 = true;
            while (true) {
                while (true) {
                    Label_0201: {
                        synchronized (DeviceUtils.class) {
                            if (Log.isLoggable()) {
                                Log.d("nf_device_utils", "isDeviceEnabled:: Disabled percentage: " + n);
                            }
                            if (n <= 0) {
                                Log.d("nf_device_utils", "Everybody is enabled");
                            }
                            else if (n >= 100) {
                                Log.d("nf_device_utils", "Everybody is disabled");
                                b2 = false;
                            }
                            else {
                                hashCode = hashCode(BaseEsnProvider.getHashedDeviceId(context));
                                n2 = (int)(hashCode % 100L);
                                if ((n3 = n2) < 0) {
                                    n3 = n2 + 100;
                                }
                                break Label_0201;
                            }
                            Label_0054: {
                                return b2;
                            }
                            while (true) {
                                Log.d("nf_device_utils", "isDeviceEnabled:: deviceID " + s + ", hash " + hashCode + ", bucket " + n3 + ", enabled " + b);
                                b2 = b;
                                return b2;
                                b2 = b;
                                continue Label_0114_Outer;
                            }
                        }
                        // iftrue(Label_0054:, !Log.isLoggable())
                        b = false;
                        continue Label_0195_Outer;
                    }
                    if (n3 <= 100 - n) {
                        continue Label_0195_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    public static boolean isDeviceHd(final NetflixActivity netflixActivity) {
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        return serviceManager != null && serviceManager.isDeviceHd();
    }
    
    public static boolean isFirstApplicationStartAfterInstallation(final Context context) {
        Label_0059: {
            if (DeviceUtils.sFirstStartAfterInstall != null) {
                break Label_0059;
            }
            synchronized (DeviceUtils.class) {
                if (DeviceUtils.sFirstStartAfterInstall == null) {
                    final boolean b = PreferenceUtils.getLongPref(context, "nf_first_start_after_install", -1L) < 0L;
                    DeviceUtils.sFirstStartAfterInstall = new AtomicBoolean(b);
                    if (b) {
                        PreferenceUtils.putLongPref(context, "nf_first_start_after_install", System.currentTimeMillis());
                    }
                }
                // monitorexit(DeviceUtils.class)
                return DeviceUtils.sFirstStartAfterInstall.get();
            }
        }
    }
    
    public static boolean isLandscape(final Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
    
    public static boolean isLocalPlaybackEnabled() {
        final String systemPropety = SecurityRepository.getSystemPropety("ro.nrdp.playback.enable");
        final boolean b = StringUtils.isEmpty(systemPropety) || "true".equalsIgnoreCase(systemPropety);
        if (Log.isLoggable()) {
            Log.d("nf_device_utils", "isLocalPlaybackEnabled:: value: " + systemPropety + ", enabled: " + b);
        }
        return b;
    }
    
    public static boolean isNotTabletByContext(final Context context) {
        return !isTabletByContext(context);
    }
    
    public static boolean isPortrait(final Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }
    
    public static boolean isRemoteControlEnabled() {
        final String systemPropety = SecurityRepository.getSystemPropety("ro.nrdp.mdx.remotecontrols");
        final boolean b = StringUtils.isEmpty(systemPropety) || "true".equalsIgnoreCase(systemPropety);
        if (Log.isLoggable()) {
            Log.d("nf_device_utils", "isRemoteControlEnabled:: value: " + systemPropety + ", enabled: " + b);
        }
        return b;
    }
    
    public static boolean isTabletByContext(final Context context) {
        if (context == null) {
            Log.e("nf_device_utils", "AndroidUtils#isTabletByContext:: context is null!");
            return false;
        }
        final Resources resources = context.getResources();
        if (resources == null) {
            Log.e("nf_device_utils", "AndroidUtils#isTabletByContext:: context.getResources() is null!");
            return false;
        }
        final Configuration configuration = resources.getConfiguration();
        if (configuration == null) {
            Log.e("nf_device_utils", "AndroidUtils#isTabletByContext:: context.getResources().getConfiguration() is null!");
            return false;
        }
        final int n = configuration.screenLayout & 0xF;
        if (Log.isLoggable()) {
            Log.d("nf_device_utils", "Screen size: " + n);
        }
        switch (n) {
            default: {
                Log.d("nf_device_utils", "Screen size is at least XL - tablet UI");
                return true;
            }
            case 0: {
                Log.d("nf_device_utils", "Screen size undefined - mobile UI");
                return false;
            }
            case 2: {
                Log.d("nf_device_utils", "Screen size normal - mobile UI");
                return false;
            }
            case 1: {
                Log.d("nf_device_utils", "Screen size small - mobile UI");
                return false;
            }
            case 3: {
                Log.d("nf_device_utils", "Screen size large - tablet UI");
                return true;
            }
        }
    }
    
    public static boolean loadNativeLibrary(final Context context, final String s) {
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Library name not provided!");
        }
        final String nativeLibraryDirectory = getNativeLibraryDirectory(context);
        while (true) {
            if (nativeLibraryDirectory != null) {
                try {
                    if (Log.isLoggable()) {
                        Log.d("nf_device_utils", "Loading library " + s + " from app file system. Installed or updated app.");
                    }
                    final String string = nativeLibraryDirectory + "/lib" + s + ".so";
                    if (Log.isLoggable()) {
                        Log.d("nf_device_utils", "Loading from " + string);
                    }
                    System.load(string);
                    return true;
                    // iftrue(Label_0173:, !Log.isLoggable())
                    while (true) {
                        Block_6: {
                            break Block_6;
                            System.loadLibrary(s);
                            return true;
                        }
                        Log.d("nf_device_utils", "Loading library " + s + " leaving to android to find mapping. Preloaded app.");
                        continue;
                    }
                }
                catch (Throwable t) {
                    Log.e("nf_device_utils", "Failed to load library from assumed location", t);
                    ErrorLoggingManager.logHandledException(t);
                    return false;
                }
                return true;
            }
            continue;
        }
    }
    
    public static boolean shouldShow3DIcon(final DeviceCapabilityProvider deviceCapabilityProvider, final FeatureEnabledProvider featureEnabledProvider) {
        return deviceCapabilityProvider.is3dSupported() && featureEnabledProvider.isVideo3D();
    }
    
    public static boolean shouldShow5dot1Icon(final DeviceCapabilityProvider deviceCapabilityProvider, final FeatureEnabledProvider featureEnabledProvider) {
        return deviceCapabilityProvider.is5dot1Supported() && featureEnabledProvider.isVideo5dot1();
    }
    
    public static boolean shouldShowHdIcon(final NetflixActivity netflixActivity, final FeatureEnabledProvider featureEnabledProvider) {
        return isDeviceHd(netflixActivity) && featureEnabledProvider.isVideoHd();
    }
    
    public static boolean shouldShowHdIcon(final DeviceCapabilityProvider deviceCapabilityProvider, final FeatureEnabledProvider featureEnabledProvider) {
        return deviceCapabilityProvider.isHdSupported() && featureEnabledProvider.isVideoHd();
    }
    
    public static boolean shouldShowUhdIcon(final DeviceCapabilityProvider deviceCapabilityProvider, final FeatureEnabledProvider featureEnabledProvider) {
        return deviceCapabilityProvider.isUltraHdSupported() && featureEnabledProvider.isVideoUhd();
    }
    
    public static void showSoftKeyboard(final Activity activity) {
        activity.getWindow().setSoftInputMode(36);
    }
}
