// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;

public final class ct
{
    public static void a(final String s, final Throwable t) {
        if (n(3)) {
            Log.d("Ads", s, t);
        }
    }
    
    public static void b(final String s, final Throwable t) {
        if (n(5)) {
            Log.w("Ads", s, t);
        }
    }
    
    public static boolean n(final int n) {
        return (n >= 5 || Log.isLoggable("Ads", n)) && n != 2;
    }
    
    public static void r(final String s) {
        if (n(3)) {
            Log.d("Ads", s);
        }
    }
    
    public static void s(final String s) {
        if (n(6)) {
            Log.e("Ads", s);
        }
    }
    
    public static void t(final String s) {
        if (n(4)) {
            Log.i("Ads", s);
        }
    }
    
    public static void u(final String s) {
        if (n(2)) {
            Log.v("Ads", s);
        }
    }
    
    public static void v(final String s) {
        if (n(5)) {
            Log.w("Ads", s);
        }
    }
}
