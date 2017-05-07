// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;

public final class eo
{
    public static void W(final String s) throws IllegalArgumentException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Namespace cannot be null or empty");
        }
        if (s.length() > 128) {
            throw new IllegalArgumentException("Invalid namespace length");
        }
        if (!s.startsWith("urn:x-cast:")) {
            throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\"");
        }
        if (s.length() == "urn:x-cast:".length()) {
            throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\" and have non-empty suffix");
        }
    }
    
    public static String X(final String s) {
        return "urn:x-cast:" + s;
    }
    
    public static <T> boolean a(final T t, final T t2) {
        return (t == null && t2 == null) || (t != null && t2 != null && t.equals(t2));
    }
    
    public static long b(final double n) {
        return (long)(1000.0 * n);
    }
    
    public static double m(final long n) {
        return n / 1000.0;
    }
}
