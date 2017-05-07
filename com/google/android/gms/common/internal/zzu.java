// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.text.TextUtils;
import android.os.Looper;

public final class zzu
{
    public static void zzU(final boolean b) {
        if (!b) {
            throw new IllegalStateException();
        }
    }
    
    public static void zzV(final boolean b) {
        if (!b) {
            throw new IllegalArgumentException();
        }
    }
    
    public static void zza(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalStateException(String.valueOf(o));
        }
    }
    
    public static <T> T zzb(final T t, final Object o) {
        if (t == null) {
            throw new NullPointerException(String.valueOf(o));
        }
        return t;
    }
    
    public static void zzb(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
    }
    
    public static void zzbY(final String s) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException(s);
        }
    }
    
    public static void zzbZ(final String s) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(s);
        }
    }
    
    public static String zzcj(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        return s;
    }
    
    public static String zzh(final String s, final Object o) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
        return s;
    }
    
    public static <T> T zzu(final T t) {
        if (t == null) {
            throw new NullPointerException("null reference");
        }
        return t;
    }
}
