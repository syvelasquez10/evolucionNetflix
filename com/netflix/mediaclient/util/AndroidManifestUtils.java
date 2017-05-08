// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import com.netflix.mediaclient.media.PlayerType;
import android.util.Log;
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
        catch (PackageManager$NameNotFoundException ex) {
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
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
}
