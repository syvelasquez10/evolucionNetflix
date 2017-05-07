// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;

@ez
public final class gs
{
    public static void S(final String s) {
        if (u(3)) {
            Log.d("Ads", s);
        }
    }
    
    public static void T(final String s) {
        if (u(6)) {
            Log.e("Ads", s);
        }
    }
    
    public static void U(final String s) {
        if (u(4)) {
            Log.i("Ads", s);
        }
    }
    
    public static void V(final String s) {
        if (u(2)) {
            Log.v("Ads", s);
        }
    }
    
    public static void W(final String s) {
        if (u(5)) {
            Log.w("Ads", s);
        }
    }
    
    public static void a(final String s, final Throwable t) {
        if (u(3)) {
            Log.d("Ads", s, t);
        }
    }
    
    public static void b(final String s, final Throwable t) {
        if (u(6)) {
            Log.e("Ads", s, t);
        }
    }
    
    public static void c(final String s, final Throwable t) {
        if (u(4)) {
            Log.i("Ads", s, t);
        }
    }
    
    public static void d(final String s, final Throwable t) {
        if (u(5)) {
            Log.w("Ads", s, t);
        }
    }
    
    public static boolean u(final int n) {
        return (n >= 5 || Log.isLoggable("Ads", n)) && n != 2;
    }
}
