// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.pm.PackageInfo;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.net.Uri;
import android.content.pm.ResolveInfo;
import android.net.NetworkInfo;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.os.Build$VERSION;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import java.util.Locale;
import android.net.ConnectivityManager;
import android.media.AudioManager;
import android.content.Context;

@ez
public final class fw
{
    public final int uS;
    public final boolean uT;
    public final boolean uU;
    public final String uV;
    public final String uW;
    public final boolean uX;
    public final boolean uY;
    public final boolean uZ;
    public final String va;
    public final String vb;
    public final int vc;
    public final int vd;
    public final int ve;
    public final int vf;
    public final int vg;
    public final int vh;
    public final float vi;
    public final int vj;
    public final int vk;
    public final double vl;
    public final boolean vm;
    public final boolean vn;
    public final int vo;
    
    public fw(final Context context) {
        final boolean b = true;
        final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final Locale default1 = Locale.getDefault();
        final PackageManager packageManager = context.getPackageManager();
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        final Intent registerReceiver = context.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        this.uS = audioManager.getMode();
        this.uT = (a(packageManager, "geo:0,0?q=donuts") != null);
        this.uU = (a(packageManager, "http://www.google.com") != null);
        this.uV = telephonyManager.getNetworkOperator();
        this.uW = default1.getCountry();
        this.uX = gr.ds();
        this.uY = audioManager.isMusicActive();
        this.uZ = audioManager.isSpeakerphoneOn();
        this.va = default1.getLanguage();
        this.vb = a(packageManager);
        this.vc = audioManager.getStreamVolume(3);
        this.vd = a(context, connectivityManager, packageManager);
        this.ve = telephonyManager.getNetworkType();
        this.vf = telephonyManager.getPhoneType();
        this.vg = audioManager.getRingerMode();
        this.vh = audioManager.getStreamVolume(2);
        this.vi = displayMetrics.density;
        this.vj = displayMetrics.widthPixels;
        this.vk = displayMetrics.heightPixels;
        if (registerReceiver != null) {
            final int intExtra = registerReceiver.getIntExtra("status", -1);
            this.vl = registerReceiver.getIntExtra("level", -1) / registerReceiver.getIntExtra("scale", -1);
            boolean vm = b;
            if (intExtra != 2) {
                vm = (intExtra == 5 && b);
            }
            this.vm = vm;
        }
        else {
            this.vl = -1.0;
            this.vm = false;
        }
        if (Build$VERSION.SDK_INT < 16) {
            this.vn = false;
            this.vo = -1;
            return;
        }
        this.vn = connectivityManager.isActiveNetworkMetered();
        if (connectivityManager.getActiveNetworkInfo() != null) {
            this.vo = connectivityManager.getActiveNetworkInfo().getDetailedState().ordinal();
            return;
        }
        this.vo = -1;
    }
    
    private static int a(final Context context, final ConnectivityManager connectivityManager, final PackageManager packageManager) {
        int type = -2;
        if (gj.a(packageManager, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
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
