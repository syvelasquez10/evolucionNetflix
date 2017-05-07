// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.util.client;

import com.google.android.gms.internal.zzby;
import android.util.Log;
import com.google.android.gms.internal.zzgr;

@zzgr
public final class zzb
{
    public static void e(final String s) {
        if (zzN(6)) {
            Log.e("Ads", s);
        }
    }
    
    public static void v(final String s) {
        if (zzN(2)) {
            Log.v("Ads", s);
        }
    }
    
    public static boolean zzN(final int n) {
        return (n >= 5 || Log.isLoggable("Ads", n)) && (n != 2 || zzgU());
    }
    
    public static void zza(final String s, final Throwable t) {
        if (zzN(3)) {
            Log.d("Ads", s, t);
        }
    }
    
    public static void zzaF(final String s) {
        if (zzN(3)) {
            Log.d("Ads", s);
        }
    }
    
    public static void zzaG(final String s) {
        if (zzN(4)) {
            Log.i("Ads", s);
        }
    }
    
    public static void zzaH(final String s) {
        if (zzN(5)) {
            Log.w("Ads", s);
        }
    }
    
    public static void zzb(final String s, final Throwable t) {
        if (zzN(6)) {
            Log.e("Ads", s, t);
        }
    }
    
    public static void zzd(final String s, final Throwable t) {
        if (zzN(5)) {
            Log.w("Ads", s, t);
        }
    }
    
    public static boolean zzgU() {
        return zzby.zzvl.get();
    }
}
