// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class dh
{
    public static <T> boolean a(final T t, final T t2) {
        return (t == null && t2 == null) || (t != null && t2 != null && t.equals(t2));
    }
    
    public static long b(final double n) {
        return (long)(1000.0 * n);
    }
    
    public static double h(final long n) {
        return n / 1000.0;
    }
}
