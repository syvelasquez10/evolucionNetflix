// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Locale;
import android.util.Log;

public class zzs
{
    public static boolean DEBUG;
    public static String TAG;
    
    static {
        zzs.TAG = "Volley";
        zzs.DEBUG = Log.isLoggable(zzs.TAG, 2);
    }
    
    public static void zza(final String s, final Object... array) {
        if (zzs.DEBUG) {
            Log.v(zzs.TAG, zzd(s, array));
        }
    }
    
    public static void zza(final Throwable t, final String s, final Object... array) {
        Log.e(zzs.TAG, zzd(s, array), t);
    }
    
    public static void zzb(final String s, final Object... array) {
        Log.d(zzs.TAG, zzd(s, array));
    }
    
    public static void zzc(final String s, final Object... array) {
        Log.e(zzs.TAG, zzd(s, array));
    }
    
    private static String zzd(String format, final Object... array) {
        if (array != null) {
            format = String.format(Locale.US, format, array);
        }
        final StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        for (int i = 2; i < stackTrace.length; ++i) {
            if (!stackTrace[i].getClass().equals(zzs.class)) {
                final String className = stackTrace[i].getClassName();
                final String substring = className.substring(className.lastIndexOf(46) + 1);
                final String string = substring.substring(substring.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), string, format);
            }
        }
        final String string = "<unknown>";
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), string, format);
    }
}
