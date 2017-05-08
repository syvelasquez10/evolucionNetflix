// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.logging;

import java.util.Locale;

public class FLog
{
    private static LoggingDelegate sHandler;
    
    static {
        FLog.sHandler = FLogDefaultLoggingDelegate.getInstance();
    }
    
    public static void d(final Class<?> clazz, final String s, final Object o) {
        if (FLog.sHandler.isLoggable(3)) {
            FLog.sHandler.d(getTag(clazz), formatString(s, o));
        }
    }
    
    public static void d(final String s, final String s2) {
        if (FLog.sHandler.isLoggable(3)) {
            FLog.sHandler.d(s, s2);
        }
    }
    
    public static void e(final Class<?> clazz, final String s) {
        if (FLog.sHandler.isLoggable(6)) {
            FLog.sHandler.e(getTag(clazz), s);
        }
    }
    
    public static void e(final Class<?> clazz, final String s, final Throwable t) {
        if (FLog.sHandler.isLoggable(6)) {
            FLog.sHandler.e(getTag(clazz), s, t);
        }
    }
    
    public static void e(final Class<?> clazz, final String s, final Object... array) {
        if (FLog.sHandler.isLoggable(6)) {
            FLog.sHandler.e(getTag(clazz), formatString(s, array));
        }
    }
    
    public static void e(final Class<?> clazz, final Throwable t, final String s, final Object... array) {
        if (FLog.sHandler.isLoggable(6)) {
            FLog.sHandler.e(getTag(clazz), formatString(s, array), t);
        }
    }
    
    public static void e(final String s, final String s2) {
        if (FLog.sHandler.isLoggable(6)) {
            FLog.sHandler.e(s, s2);
        }
    }
    
    public static void e(final String s, final String s2, final Throwable t) {
        if (FLog.sHandler.isLoggable(6)) {
            FLog.sHandler.e(s, s2, t);
        }
    }
    
    private static String formatString(final String s, final Object... array) {
        return String.format(null, s, array);
    }
    
    private static String getTag(final Class<?> clazz) {
        return clazz.getSimpleName();
    }
    
    public static void i(final Class<?> clazz, final String s) {
        if (FLog.sHandler.isLoggable(4)) {
            FLog.sHandler.i(getTag(clazz), s);
        }
    }
    
    public static void i(final String s, final String s2) {
        if (FLog.sHandler.isLoggable(4)) {
            FLog.sHandler.i(s, s2);
        }
    }
    
    public static boolean isLoggable(final int n) {
        return FLog.sHandler.isLoggable(n);
    }
    
    public static void v(final Class<?> clazz, final String s) {
        if (FLog.sHandler.isLoggable(2)) {
            FLog.sHandler.v(getTag(clazz), s);
        }
    }
    
    public static void v(final Class<?> clazz, final String s, final Object o) {
        if (FLog.sHandler.isLoggable(2)) {
            FLog.sHandler.v(getTag(clazz), formatString(s, o));
        }
    }
    
    public static void v(final Class<?> clazz, final String s, final Object o, final Object o2) {
        if (FLog.sHandler.isLoggable(2)) {
            FLog.sHandler.v(getTag(clazz), formatString(s, o, o2));
        }
    }
    
    public static void v(final Class<?> clazz, final String s, final Object o, final Object o2, final Object o3) {
        if (isLoggable(2)) {
            v(clazz, formatString(s, o, o2, o3));
        }
    }
    
    public static void v(final Class<?> clazz, final String s, final Object o, final Object o2, final Object o3, final Object o4) {
        if (FLog.sHandler.isLoggable(2)) {
            FLog.sHandler.v(getTag(clazz), formatString(s, o, o2, o3, o4));
        }
    }
    
    public static void v(final Class<?> clazz, final String s, final Object... array) {
        if (FLog.sHandler.isLoggable(2)) {
            FLog.sHandler.v(getTag(clazz), formatString(s, array));
        }
    }
    
    public static void v(final String s, final String s2, final Object... array) {
        if (FLog.sHandler.isLoggable(2)) {
            FLog.sHandler.v(s, formatString(s2, array));
        }
    }
    
    public static void w(final Class<?> clazz, final String s) {
        if (FLog.sHandler.isLoggable(5)) {
            FLog.sHandler.w(getTag(clazz), s);
        }
    }
    
    public static void w(final Class<?> clazz, final String s, final Throwable t) {
        if (FLog.sHandler.isLoggable(5)) {
            FLog.sHandler.w(getTag(clazz), s, t);
        }
    }
    
    public static void w(final Class<?> clazz, final String s, final Object... array) {
        if (FLog.sHandler.isLoggable(5)) {
            FLog.sHandler.w(getTag(clazz), formatString(s, array));
        }
    }
    
    public static void w(final Class<?> clazz, final Throwable t, final String s, final Object... array) {
        if (isLoggable(5)) {
            w(clazz, formatString(s, array), t);
        }
    }
    
    public static void w(final String s, final String s2) {
        if (FLog.sHandler.isLoggable(5)) {
            FLog.sHandler.w(s, s2);
        }
    }
    
    public static void w(final String s, final String s2, final Throwable t) {
        if (FLog.sHandler.isLoggable(5)) {
            FLog.sHandler.w(s, s2, t);
        }
    }
    
    public static void w(final String s, final String s2, final Object... array) {
        if (FLog.sHandler.isLoggable(5)) {
            FLog.sHandler.w(s, formatString(s2, array));
        }
    }
    
    public static void wtf(final Class<?> clazz, final String s, final Throwable t) {
        if (FLog.sHandler.isLoggable(6)) {
            FLog.sHandler.wtf(getTag(clazz), s, t);
        }
    }
    
    public static void wtf(final Class<?> clazz, final String s, final Object... array) {
        if (FLog.sHandler.isLoggable(6)) {
            FLog.sHandler.wtf(getTag(clazz), formatString(s, array));
        }
    }
    
    public static void wtf(final String s, final String s2, final Object... array) {
        if (FLog.sHandler.isLoggable(6)) {
            FLog.sHandler.wtf(s, formatString(s2, array));
        }
    }
}
