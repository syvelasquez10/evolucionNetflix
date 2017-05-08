// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.utils;

public class LogUtils
{
    private static final int MAX_TAG_LENGTH = 23;
    
    public static String getTag(final Class clazz) {
        String s2;
        final String s = s2 = clazz.getSimpleName();
        if (s.length() > 23) {
            s2 = s.substring(0, 23);
        }
        return s2;
    }
}
