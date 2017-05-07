// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

public final class o
{
    private static String b(final String s, final int n) {
        if (n < 1) {
            aa.w("index out of range for " + s + " (" + n + ")");
            return "";
        }
        return s + n;
    }
    
    static String q(final int n) {
        return b("&cd", n);
    }
    
    static String r(final int n) {
        return b("&cm", n);
    }
}
