// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient;

public final class Log
{
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    private static final int LOGCAT_ENTRY_MAX_LEN = 4000;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static boolean debug;
    
    static {
        Log.debug = false;
    }
    
    public static int d(final String s, final String s2) {
        if (Log.debug) {
            return android.util.Log.d(s, s2);
        }
        return 0;
    }
    
    public static int d(final String s, final String s2, final Throwable t) {
        if (Log.debug) {
            return android.util.Log.d(s, s2, t);
        }
        return 0;
    }
    
    public static void dumpVerbose(final String s, final String s2) {
        if (s2 != null) {
            if (s2.length() <= 4000) {
                v(s, s2);
                return;
            }
            for (int n = s2.length() / 4000, i = 0; i <= n; ++i) {
                final int n2 = (i + 1) * 4000;
                if (n2 >= s2.length()) {
                    v(s, s2.substring(i * 4000));
                }
                else {
                    v(s, s2.substring(i * 4000, n2));
                }
            }
        }
    }
    
    public static int e(final String s, final String s2) {
        if (Log.debug) {
            return android.util.Log.e(s, s2);
        }
        return 0;
    }
    
    public static int e(final String s, final String s2, final Throwable t) {
        if (Log.debug) {
            return android.util.Log.e(s, s2, t);
        }
        return 0;
    }
    
    public static void handleException(final String s, final Exception ex) {
        e(s, ex.getMessage(), ex);
    }
    
    public static int i(final String s, final String s2) {
        if (Log.debug) {
            return android.util.Log.i(s, s2);
        }
        return 0;
    }
    
    public static int i(final String s, final String s2, final Throwable t) {
        if (Log.debug) {
            return android.util.Log.i(s, s2, t);
        }
        return 0;
    }
    
    public static boolean isLoggable(final String s, final int n) {
        return Log.debug;
    }
    
    public static void logByteArray(final String s, final String s2, final byte[] array) {
        if (Log.debug) {
            if (array == null) {
                android.util.Log.d(s, s2 + ". null array ");
                return;
            }
            final StringBuilder sb = new StringBuilder("[ ");
            int n = 1;
            for (int i = 0; i < array.length; ++i) {
                if (n != 0) {
                    n = 0;
                }
                else {
                    sb.append(", ");
                }
                sb.append(array[i]);
            }
            sb.append("]");
            android.util.Log.d(s, s2 + ". Length " + array.length + " bytes. Array: ");
            android.util.Log.d(s, sb.toString());
        }
    }
    
    public static void printStackTrace(final String s) {
        v(s, android.util.Log.getStackTraceString((Throwable)new PrintStackTrace()));
    }
    
    public static void printStackTrace(final String s, final Throwable t) {
        v(s, android.util.Log.getStackTraceString(t));
    }
    
    public static int v(final String s, final String s2) {
        if (Log.debug) {
            return android.util.Log.v(s, s2);
        }
        return 0;
    }
    
    public static int v(final String s, final String s2, final Throwable t) {
        if (Log.debug) {
            return android.util.Log.v(s, s2, t);
        }
        return 0;
    }
    
    public static int w(final String s, final String s2) {
        if (Log.debug) {
            return android.util.Log.w(s, s2);
        }
        return 0;
    }
    
    public static int w(final String s, final String s2, final Throwable t) {
        if (Log.debug) {
            return android.util.Log.w(s, s2, t);
        }
        return 0;
    }
    
    public static int w(final String s, final Throwable t) {
        if (Log.debug) {
            return android.util.Log.w(s, t);
        }
        return 0;
    }
    
    public static int wtf(final String s, final String s2) {
        return wtf(s, s2, null);
    }
    
    public static int wtf(final String s, final String s2, final Throwable t) {
        if (Log.debug) {
            return android.util.Log.wtf(s, s2, t);
        }
        return 0;
    }
    
    public static int wtf(final String s, final Throwable t) {
        return wtf(s, t.getMessage(), t);
    }
    
    private static class PrintStackTrace extends Throwable
    {
    }
}
