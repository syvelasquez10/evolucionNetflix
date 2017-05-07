// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import java.util.Iterator;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

class VolleyLog$MarkerLog
{
    public static final boolean ENABLED;
    private static final long MIN_DURATION_FOR_LOGGING_MS = 0L;
    private boolean mFinished;
    private final List<VolleyLog$MarkerLog$Marker> mMarkers;
    
    static {
        ENABLED = VolleyLog.DEBUG;
    }
    
    VolleyLog$MarkerLog() {
        this.mMarkers = new ArrayList<VolleyLog$MarkerLog$Marker>();
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
        this.mMarkers.add(new VolleyLog$MarkerLog$Marker(s2, n, SystemClock.elapsedRealtime()));
    }
    // monitorexit(this)
    
    @Override
    protected void finalize() {
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
                for (final VolleyLog$MarkerLog$Marker volleyLog$MarkerLog$Marker : this.mMarkers) {
                    final long time2 = volleyLog$MarkerLog$Marker.time;
                    VolleyLog.d("(+%-4d) [%2d] %s", time2 - time, volleyLog$MarkerLog$Marker.thread, volleyLog$MarkerLog$Marker.name);
                    time = time2;
                }
            }
        }
    }
}
