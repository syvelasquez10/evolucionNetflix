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

public final class dg
{
    public final int pZ;
    public final boolean qa;
    public final boolean qb;
    public final String qc;
    public final String qd;
    public final boolean qe;
    public final boolean qf;
    public final boolean qg;
    public final String qh;
    public final String qi;
    public final int qj;
    public final int qk;
    public final int ql;
    public final int qm;
    public final int qn;
    public final int qo;
    public final float qp;
    public final int qq;
    public final int qr;
    
    public dg(final Context context) {
        final boolean b = true;
        final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final Locale default1 = Locale.getDefault();
        final PackageManager packageManager = context.getPackageManager();
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        this.pZ = audioManager.getMode();
        this.qa = (a(packageManager, "geo:0,0?q=donuts") != null);
        this.qb = (a(packageManager, "http://www.google.com") != null && b);
        this.qc = telephonyManager.getNetworkOperator();
        this.qd = default1.getCountry();
        this.qe = dv.bC();
        this.qf = audioManager.isMusicActive();
        this.qg = audioManager.isSpeakerphoneOn();
        this.qh = default1.getLanguage();
        this.qi = a(packageManager);
        this.qj = audioManager.getStreamVolume(3);
        this.qk = a(context, connectivityManager, packageManager);
        this.ql = telephonyManager.getNetworkType();
        this.qm = telephonyManager.getPhoneType();
        this.qn = audioManager.getRingerMode();
        this.qo = audioManager.getStreamVolume(2);
        this.qp = displayMetrics.density;
        this.qq = displayMetrics.widthPixels;
        this.qr = displayMetrics.heightPixels;
    }
    
    private static int a(final Context context, final ConnectivityManager connectivityManager, final PackageManager packageManager) {
        int type = -2;
        if (dq.a(packageManager, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
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
