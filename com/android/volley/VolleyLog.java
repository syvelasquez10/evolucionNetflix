// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import java.util.Locale;
import android.util.Log;

public class VolleyLog
{
    public static final boolean DEBUG;
    private static final boolean LOG_VERBOSE;
    public static String TAG;
    
    static {
        VolleyLog.TAG = "Volley";
        if (Log.isLoggable(VolleyLog.TAG, 2)) {}
        DEBUG = false;
        if (VolleyLog.DEBUG) {}
        LOG_VERBOSE = false;
    }
    
    private static String buildMessage(String format, final Object... array) {
        if (array != null) {
            format = String.format(Locale.US, format, array);
        }
        final StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        for (int i = 2; i < stackTrace.length; ++i) {
            if (!stackTrace[i].getClass().equals(VolleyLog.class)) {
                final String className = stackTrace[i].getClassName();
                final String substring = className.substring(className.lastIndexOf(46) + 1);
                final String string = substring.substring(substring.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), string, format);
            }
        }
        final String string = "<unknown>";
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), string, format);
    }
    
    public static void d(final String s, final Object... array) {
        if (VolleyLog.LOG_VERBOSE) {
            Log.d(VolleyLog.TAG, buildMessage(s, array));
        }
    }
    
    public static void e(final String s, final Object... array) {
        if (VolleyLog.LOG_VERBOSE) {
            Log.e(VolleyLog.TAG, buildMessage(s, array));
        }
    }
    
    public static void e(final Throwable t, final String s, final Object... array) {
        if (VolleyLog.LOG_VERBOSE) {
            Log.e(VolleyLog.TAG, buildMessage(s, array), t);
        }
    }
    
    public static void v(final String s, final Object... array) {
        if (VolleyLog.LOG_VERBOSE) {
            Log.v(VolleyLog.TAG, buildMessage(s, array));
        }
    }
}
