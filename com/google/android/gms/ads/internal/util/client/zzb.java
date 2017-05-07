// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.util.client;

import com.google.android.gms.internal.zzby;
import android.util.Log;
import com.google.android.gms.internal.zzgk;

@zzgk
public final class zzb
{
    public static void e(final String s) {
        if (zzM(6)) {
            Log.e("Ads", s);
        }
    }
    
    public static void v(final String s) {
        if (zzM(2)) {
            Log.v("Ads", s);
        }
    }
    
    public static boolean zzM(final int n) {
        return (n >= 5 || Log.isLoggable("Ads", n)) && (n != 2 || zzgJ());
    }
    
    public static void zza(final String s, final Throwable t) {
        if (zzM(3)) {
            Log.d("Ads", s, t);
        }
    }
    
    public static void zzaC(final String s) {
        if (zzM(3)) {
            Log.d("Ads", s);
        }
    }
    
    public static void zzaD(final String s) {
        if (zzM(4)) {
            Log.i("Ads", s);
        }
    }
    
    public static void zzaE(final String s) {
        if (zzM(5)) {
            Log.w("Ads", s);
        }
    }
    
    public static void zzb(final String s, final Throwable t) {
        if (zzM(6)) {
            Log.e("Ads", s, t);
        }
    }
    
    public static void zzd(final String s, final Throwable t) {
        if (zzM(5)) {
            Log.w("Ads", s, t);
        }
    }
    
    public static boolean zzgJ() {
        return zzby.zzuW.get();
    }
}
