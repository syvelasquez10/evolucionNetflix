// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

public final class n
{
    public static String A(final int n) {
        return d("&promo", n);
    }
    
    public static String B(final int n) {
        return d("pi", n);
    }
    
    public static String C(final int n) {
        return d("&il", n);
    }
    
    public static String D(final int n) {
        return d("cd", n);
    }
    
    public static String E(final int n) {
        return d("cm", n);
    }
    
    private static String d(final String s, final int n) {
        if (n < 1) {
            z.T("index out of range for " + s + " (" + n + ")");
            return "";
        }
        return s + n;
    }
    
    static String x(final int n) {
        return d("&cd", n);
    }
    
    static String y(final int n) {
        return d("&cm", n);
    }
    
    public static String z(final int n) {
        return d("&pr", n);
    }
}
