// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.pm.PackageInfo;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import android.content.Context;

public final class AndroidManifestUtils
{
    public static final String APP_PACKAGE_NAME = "com.netflix.mediaclient";
    private static final String TAG = "nf_utils";
    
    public static String getClientVersion(final Context context) {
        final String version = getVersion(context);
        final int versionCode = getVersionCode(context);
        PlayerType playerType;
        if ((playerType = PlayerTypeFactory.getCurrentType(context)) == null) {
            Log.e("nf_utils", "This should not happen, player type was null at this point! Use default.");
            playerType = PlayerTypeFactory.findDefaultPlayerType();
        }
        final String mapPlayerTypeForLogging = PlayerType.mapPlayerTypeForLogging(playerType);
        final StringBuilder sb = new StringBuilder();
        sb.append(version).append('-').append(versionCode);
        sb.append(' ').append("R").append(' ').append("2013.2");
        sb.append(" android-").append(AndroidUtils.getAndroidVersion()).append('-').append(mapPlayerTypeForLogging);
        return sb.toString();
    }
    
    public static String getVersion(final Context context) {
        final String versionName = getVersionName(context);
        String s;
        if (versionName == null) {
            s = "";
        }
        else {
            final int index = versionName.indexOf(" ");
            s = versionName;
            if (index > 0) {
                return versionName.substring(0, index);
            }
        }
        return s;
    }
    
    public static int getVersionCode(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        try {
            final PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.netflix.mediaclient", 0);
            if (packageInfo == null) {
                return -1;
            }
            return packageInfo.versionCode;
        }
        catch (Throwable t) {
            return -1;
        }
    }
    
    public static String getVersionName(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        try {
            final PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.netflix.mediaclient", 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.versionName;
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static boolean isAppUpgraded(final Context context) {
        return PreferenceUtils.getBooleanPref(context, "appUpgraded", false);
    }
    
    private static void setAppUpgradedPrefBool(final Context context, final boolean b) {
        PreferenceUtils.putBooleanPref(context, "appUpgraded", b);
    }
    
    public static void updateAppUpgradedPrefs(final Context context) {
        final int intPref = PreferenceUtils.getIntPref(context, "manifestVersionCode", -1);
        final int versionCode = getVersionCode(context);
        final boolean b = versionCode > intPref && intPref != -1;
        final boolean b2 = versionCode != intPref;
        Log.i("nf_utils", "onApplicationStart lastVersionCode=%d currentVersionCode=%d appUpgraded=%b updateManifestVersionCode=%b", intPref, versionCode, b, b2);
        if (b) {
            Log.i("nf_utils", "setting app upgraded pref");
            setAppUpgradedPrefBool(context, true);
        }
        else if (isAppUpgraded(context)) {
            Log.i("nf_utils", "resetting app upgraded pref");
            setAppUpgradedPrefBool(context, false);
        }
        if (b2) {
            PreferenceUtils.putIntPref(context, "manifestVersionCode", versionCode);
        }
    }
}
