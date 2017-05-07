// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

public final class NumberUtils
{
    public static Integer toIntegerSafely(final String s, final Integer n) {
        if (s == null) {
            return null;
        }
        try {
            return Integer.valueOf(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
}
