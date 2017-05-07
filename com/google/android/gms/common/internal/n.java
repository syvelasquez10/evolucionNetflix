// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.text.TextUtils;
import android.os.Looper;

public final class n
{
    public static void I(final boolean b) {
        if (!b) {
            throw new IllegalStateException();
        }
    }
    
    public static void K(final boolean b) {
        if (!b) {
            throw new IllegalArgumentException();
        }
    }
    
    public static void a(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalStateException(String.valueOf(o));
        }
    }
    
    public static void a(final boolean b, final String s, final Object... array) {
        if (!b) {
            throw new IllegalStateException(String.format(s, array));
        }
    }
    
    public static void aT(final String s) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException(s);
        }
    }
    
    public static void aU(final String s) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(s);
        }
    }
    
    public static String aZ(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        return s;
    }
    
    public static <T> T b(final T t, final Object o) {
        if (t == null) {
            throw new NullPointerException(String.valueOf(o));
        }
        return t;
    }
    
    public static String b(final String s, final Object o) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
        return s;
    }
    
    public static void b(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
    }
    
    public static void b(final boolean b, final String s, final Object... array) {
        if (!b) {
            throw new IllegalArgumentException(String.format(s, array));
        }
    }
    
    public static <T> T i(final T t) {
        if (t == null) {
            throw new NullPointerException("null reference");
        }
        return t;
    }
}
