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

@ez
public final class dy extends eg.a
{
    private Context mContext;
    private String mv;
    private String su;
    private ArrayList<String> sv;
    
    public dy(final String su, final ArrayList<String> sv, final Context mContext, final String mv) {
        this.su = su;
        this.sv = sv;
        this.mv = mv;
        this.mContext = mContext;
    }
    
    private void cr() {
        try {
            this.mContext.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter").getDeclaredMethod("reportWithProductId", Context.class, String.class, String.class, Boolean.TYPE).invoke(null, this.mContext, this.su, "", true);
        }
        catch (ClassNotFoundException ex2) {
            gs.W("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        }
        catch (NoSuchMethodException ex3) {
            gs.W("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        }
        catch (Exception ex) {
            gs.d("Fail to report a conversion.", ex);
        }
    }
    
    protected String a(String replaceAll, final HashMap<String, String> hashMap) {
        final String packageName = this.mContext.getPackageName();
        String versionName;
        long elapsedRealtime;
        long di;
        while (true) {
            try {
                versionName = this.mContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
                elapsedRealtime = SystemClock.elapsedRealtime();
                di = gb.cZ().di();
                for (final String s : hashMap.keySet()) {
                    replaceAll = replaceAll.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", s), String.format("$1%s$2", hashMap.get(s)));
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                gs.d("Error to retrieve app version", (Throwable)ex);
                versionName = "";
                continue;
            }
            break;
        }
        return replaceAll.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "sessionid"), String.format("$1%s$2", gb.vK)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "appid"), String.format("$1%s$2", packageName)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "osversion"), String.format("$1%s$2", String.valueOf(Build$VERSION.SDK_INT))).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "sdkversion"), String.format("$1%s$2", this.mv)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "appversion"), String.format("$1%s$2", versionName)).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "timestamp"), String.format("$1%s$2", String.valueOf(elapsedRealtime - di))).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "[^@]+"), String.format("$1%s$2", "")).replaceAll("@@", "@");
    }
    
    public String getProductId() {
        return this.su;
    }
    
    protected int o(final int n) {
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
            this.cr();
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("google_play_status", String.valueOf(n));
        hashMap.put("sku", this.su);
        hashMap.put("status", String.valueOf(this.o(n)));
        final Iterator<String> iterator = this.sv.iterator();
        while (iterator.hasNext()) {
            new gq(this.mContext, this.mv, this.a(iterator.next(), hashMap)).start();
        }
    }
    
    public void recordResolution(final int n) {
        if (n == 1) {
            this.cr();
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("status", String.valueOf(n));
        hashMap.put("sku", this.su);
        final Iterator<String> iterator = this.sv.iterator();
        while (iterator.hasNext()) {
            new gq(this.mContext, this.mv, this.a(iterator.next(), hashMap)).start();
        }
    }
}
