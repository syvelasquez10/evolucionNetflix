// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.KeyCharacterMap;
import android.annotation.SuppressLint;
import android.util.DisplayMetrics;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import android.content.Context;

public final class DeviceUtils
{
    public static final int SCREEN_SIZE_LARGE = 3;
    public static final int SCREEN_SIZE_NORMAL = 2;
    public static final int SCREEN_SIZE_SMALL = 1;
    public static final int SCREEN_SIZE_XLARGE = 4;
    private static final String TAG = "nf_device_utils";
    
    public static int getBasicScreenOrientation(final Context context) {
        return context.getResources().getConfiguration().orientation;
    }
    
    public static String getCertificationVersion(final Context context) {
        final String trim = getSoftwareVersion(context).trim();
        if (Log.isLoggable("nf_device_utils", 3)) {
            Log.d("nf_device_utils", "SV: " + trim);
        }
        final int index = trim.indexOf(" ");
        String substring = trim;
        if (index > 0) {
            substring = trim.substring(0, index);
        }
        if (Log.isLoggable("nf_device_utils", 3)) {
            Log.d("nf_device_utils", "CV: " + substring);
        }
        return substring;
    }
    
    public static int getScreenHeightInPixels(final Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    
    public static String getScreenResolutionCategoryString(final Activity activity) {
        switch (getScreenResolutionDpi(activity)) {
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
    
    public static int getScreenResolutionDpi(final Activity activity) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
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
    
    public static int getScreenWidthInPixels(final Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    
    public static String getSoftwareVersion(final Context context) {
        String versionName;
        if ((versionName = AndroidManifestUtils.getVersionName(context)) == null) {
            versionName = "N/A";
        }
        return versionName;
    }
    
    public static boolean hasHardwareNavigationKeys() {
        final boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        final boolean deviceHasKey2 = KeyCharacterMap.deviceHasKey(3);
        Log.d("@@@", "Back " + deviceHasKey);
        Log.d("@@@", "Home " + deviceHasKey2);
        return deviceHasKey && deviceHasKey2;
    }
    
    public static void hideSoftKeyboard(final Activity activity) {
        activity.getWindow().setSoftInputMode(2);
    }
    
    public static boolean isLandscape(final Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
    
    public static boolean isNotTabletByContext(final Context context) {
        return !isTabletByContext(context);
    }
    
    public static boolean isPortrait(final Context context) {
        return context.getResources().getConfiguration().orientation == 1;
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
        if (Log.isLoggable("nf_device_utils", 3)) {
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
    
    public static void showSoftKeyboard(final Activity activity) {
        activity.getWindow().setSoftInputMode(4);
    }
}
