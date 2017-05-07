// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.os.Build$VERSION;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.SystemClock;
import java.util.HashMap;
import java.util.ArrayList;
import android.content.Context;

public final class cm extends cn.a
{
    private String lh;
    private Context mContext;
    private String oD;
    private ArrayList<String> oE;
    
    public cm(final String od, final ArrayList<String> oe, final Context mContext, final String lh) {
        this.oD = od;
        this.oE = oe;
        this.lh = lh;
        this.mContext = mContext;
    }
    
    private void aX() {
        try {
            this.mContext.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter").getDeclaredMethod("reportWithProductId", Context.class, String.class, String.class, Boolean.TYPE).invoke(null, this.mContext, this.oD, "", true);
        }
        catch (ClassNotFoundException ex2) {
            dw.z("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        }
        catch (NoSuchMethodException ex3) {
            dw.z("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        }
        catch (Exception ex) {
            dw.c("Fail to report a conversion.", ex);
        }
    }
    
    protected String a(String replaceAll, final HashMap<String, String> hashMap) {
        final String packageName = this.mContext.getPackageName();
        String versionName;
        long elapsedRealtime;
        long bw;
        while (true) {
            try {
                versionName = this.mContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
                elapsedRealtime = SystemClock.elapsedRealtime();
                bw = dj.bu().bw();
                for (final String s : hashMap.keySet()) {
                    replaceAll = replaceAll.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", s), String.format("$1%s$2", hashMap.get(s)));
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                dw.c("Error to retrieve app version", (Throwable)ex);
                versionName = "";
                continue;
            }
            break;
        }
        return replaceAll.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "sessionid"), String.format("$1%s$2", dj.qK)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "appid"), String.format("$1%s$2", packageName)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "osversion"), String.format("$1%s$2", String.valueOf(Build$VERSION.SDK_INT))).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "sdkversion"), String.format("$1%s$2", this.lh)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "appversion"), String.format("$1%s$2", versionName)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "timestamp"), String.format("$1%s$2", String.valueOf(elapsedRealtime - bw))).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "[^@]+"), String.format("$1%s$2", "")).replaceAll("@@", "@");
    }
    
    public String getProductId() {
        return this.oD;
    }
    
    protected int j(final int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        if (n == 4) {
            return 3;
        }
        return 0;
    }
    
    public void recordPlayBillingResolution(final int n) {
        if (n == 0) {
            this.aX();
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("google_play_status", String.valueOf(n));
        hashMap.put("sku", this.oD);
        hashMap.put("status", String.valueOf(this.j(n)));
        final Iterator<String> iterator = this.oE.iterator();
        while (iterator.hasNext()) {
            new du(this.mContext, this.lh, this.a(iterator.next(), hashMap)).start();
        }
    }
    
    public void recordResolution(final int n) {
        if (n == 1) {
            this.aX();
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("status", String.valueOf(n));
        hashMap.put("sku", this.oD);
        final Iterator<String> iterator = this.oE.iterator();
        while (iterator.hasNext()) {
            new du(this.mContext, this.lh, this.a(iterator.next(), hashMap)).start();
        }
    }
}
