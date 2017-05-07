// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

public final class NumberUtils
{
    public static boolean isPositiveWholeNumber(final String s) {
        if (!StringUtils.isEmpty(s)) {
            final char[] charArray = s.toCharArray();
            for (int length = charArray.length, i = 0; i < length; ++i) {
                if (!Character.isDigit(charArray[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
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
