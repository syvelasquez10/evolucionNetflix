// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;

public final class AndroidManifestUtils
{
    public static final String APP_PACKAGE_NAME = "com.netflix.mediaclient";
    
    public static String getVersion(final Context context) {
        final String versionName = getVersionName(context);
        if (versionName == null) {
            return "";
        }
        final int index = versionName.indexOf(" ");
        String substring = versionName;
        if (index > 0) {
            substring = versionName.substring(0, index);
        }
        return substring;
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
