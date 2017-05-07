// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class ny$e
{
    public static int cf(final String s) {
        if (s.equals("person")) {
            return 0;
        }
        if (s.equals("page")) {
            return 1;
        }
        throw new IllegalArgumentException("Unknown objectType string: " + s);
    }
}
