// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import java.util.Iterator;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.util.Log;

public class VolleyLog
{
    public static final boolean DEBUG;
    private static final boolean LOG_VERBOSE;
    public static String TAG;
    
    static {
        VolleyLog.TAG = "Volley";
        DEBUG = Log.isLoggable(VolleyLog.TAG, 2);
        if (VolleyLog.DEBUG) {}
        LOG_VERBOSE = false;
    }
    
    private static String buildMessage(String format, final Object... array) {
        if (array != null) {
            format = String.format(Locale.US, format, array);
        }
        final StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        final String s = "<unknown>";
        int n = 2;
        String string;
        while (true) {
            string = s;
            if (n >= stackTrace.length) {
                break;
            }
            if (!stackTrace[n].getClass().equals(VolleyLog.class)) {
                final String className = stackTrace[n].getClassName();
                final String substring = className.substring(className.lastIndexOf(46) + 1);
                string = substring.substring(substring.lastIndexOf(36) + 1) + "." + stackTrace[n].getMethodName();
                break;
            }
            ++n;
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), string, format);
    }
    
    public static void d(final String s, final Object... array) {
        Log.d(VolleyLog.TAG, buildMessage(s, array));
    }
    
    public static void e(final String s, final Object... array) {
        Log.e(VolleyLog.TAG, buildMessage(s, array));
    }
    
    public static void e(final Throwable t, final String s, final Object... array) {
        Log.e(VolleyLog.TAG, buildMessage(s, array), t);
    }
    
    public static void v(final String s, final Object... array) {
        if (VolleyLog.LOG_VERBOSE) {
            Log.v(VolleyLog.TAG, buildMessage(s, array));
        }
    }
    
    public static void wtf(final String s, final Object... array) {
        Log.wtf(VolleyLog.TAG, buildMessage(s, array));
    }
    
    public static void wtf(final Throwable t, final String s, final Object... array) {
        Log.wtf(VolleyLog.TAG, buildMessage(s, array), t);
    }
    
    static class MarkerLog
    {
        public static final boolean ENABLED;
        private static final long MIN_DURATION_FOR_LOGGING_MS = 0L;
        private boolean mFinished;
        private final List<Marker> mMarkers;
        
        static {
            ENABLED = VolleyLog.DEBUG;
        }
        
        MarkerLog() {
            this.mMarkers = new ArrayList<Marker>();
            this.mFinished = false;
        }
        
        private long getTotalDuration() {
            if (this.mMarkers.size() == 0) {
                return 0L;
            }
            return this.mMarkers.get(this.mMarkers.size() - 1).time - this.mMarkers.get(0).time;
        }
        
        public void add(final String s, final long n) {
            synchronized (this) {
                if (this.mFinished) {
                    throw new IllegalStateException("Marker added to finished log");
                }
            }
            final String s2;
            this.mMarkers.add(new Marker(s2, n, SystemClock.elapsedRealtime()));
        }
        // monitorexit(this)
        
        @Override
        protected void finalize() throws Throwable {
            if (!this.mFinished) {
                this.finish("Request on the loose");
                VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }
        
        public void finish(final String s) {
            synchronized (this) {
                this.mFinished = true;
                final long totalDuration = this.getTotalDuration();
                if (totalDuration > 0L) {
                    long time = this.mMarkers.get(0).time;
                    VolleyLog.d("(%-4d ms) %s", totalDuration, s);
                    for (final Marker marker : this.mMarkers) {
                        final long time2 = marker.time;
                        VolleyLog.d("(+%-4d) [%2d] %s", time2 - time, marker.thread, marker.name);
                        time = time2;
                    }
                }
            }
        }
        
        private static class Marker
        {
            public final String name;
            public final long thread;
            public final long time;
            
            public Marker(final String name, final long thread, final long time) {
                this.name = name;
                this.thread = thread;
                this.time = time;
            }
        }
    }
}
