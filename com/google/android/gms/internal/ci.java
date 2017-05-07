// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.pm.PackageInfo;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.content.pm.ResolveInfo;
import android.net.NetworkInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.telephony.TelephonyManager;
import java.util.Locale;
import android.net.ConnectivityManager;
import android.media.AudioManager;
import android.content.Context;

public final class ci
{
    public final int hZ;
    public final boolean ia;
    public final boolean ib;
    public final String ic;
    public final String id;
    public final boolean ie;
    public final boolean if;
    public final boolean ig;
    public final String ih;
    public final String ii;
    public final int ij;
    public final int ik;
    public final int il;
    public final int im;
    public final int in;
    public final int io;
    public final float ip;
    public final int iq;
    public final int ir;
    
    public ci(final Context context) {
        final boolean b = true;
        final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final Locale default1 = Locale.getDefault();
        final PackageManager packageManager = context.getPackageManager();
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        this.hZ = audioManager.getMode();
        this.ia = (a(packageManager, "geo:0,0?q=donuts") != null);
        this.ib = (a(packageManager, "http://www.google.com") != null && b);
        this.ic = telephonyManager.getNetworkOperator();
        this.id = default1.getCountry();
        this.ie = cs.ax();
        this.if = audioManager.isMusicActive();
        this.ig = audioManager.isSpeakerphoneOn();
        this.ih = default1.getLanguage();
        this.ii = a(packageManager);
        this.ij = audioManager.getStreamVolume(3);
        this.ik = a(context, connectivityManager, packageManager);
        this.il = telephonyManager.getNetworkType();
        this.im = telephonyManager.getPhoneType();
        this.in = audioManager.getRingerMode();
        this.io = audioManager.getStreamVolume(2);
        this.ip = displayMetrics.density;
        this.iq = displayMetrics.widthPixels;
        this.ir = displayMetrics.heightPixels;
    }
    
    private static int a(final Context context, final ConnectivityManager connectivityManager, final PackageManager packageManager) {
        int type = -2;
        if (co.a(packageManager, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return -1;
            }
            type = activeNetworkInfo.getType();
        }
        return type;
    }
    
    private static ResolveInfo a(final PackageManager packageManager, final String s) {
        return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)), 65536);
    }
    
    private static String a(final PackageManager packageManager) {
        final ResolveInfo a = a(packageManager, "market://details?id=com.google.android.gms.ads");
        if (a != null) {
            final ActivityInfo activityInfo = a.activityInfo;
            if (activityInfo != null) {
                try {
                    final PackageInfo packageInfo = packageManager.getPackageInfo(activityInfo.packageName, 0);
                    if (packageInfo != null) {
                        return packageInfo.versionCode + "." + activityInfo.packageName;
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    return null;
                }
            }
        }
        return null;
    }
}
