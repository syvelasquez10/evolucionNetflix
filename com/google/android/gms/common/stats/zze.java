// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.util.Log;
import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;

public class zze
{
    private final long zzahV;
    private final int zzahW;
    private final SimpleArrayMap<String, Long> zzahX;
    
    public zze() {
        this.zzahV = 60000L;
        this.zzahW = 10;
        this.zzahX = new SimpleArrayMap<String, Long>(10);
    }
    
    public zze(final int zzahW, final long zzahV) {
        this.zzahV = zzahV;
        this.zzahW = zzahW;
        this.zzahX = new SimpleArrayMap<String, Long>();
    }
    
    private void zzb(final long n, final long n2) {
        for (int i = this.zzahX.size() - 1; i >= 0; --i) {
            if (n2 - this.zzahX.valueAt(i) > n) {
                this.zzahX.removeAt(i);
            }
        }
    }
    
    public Long zzcx(final String s) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        long zzahV = this.zzahV;
        synchronized (this) {
            while (this.zzahX.size() >= this.zzahW) {
                this.zzb(zzahV, elapsedRealtime);
                zzahV /= 2L;
                Log.w("ConnectionTracker", "The max capacity " + this.zzahW + " is not enough. Current durationThreshold is: " + zzahV);
            }
        }
        final String s2;
        // monitorexit(this)
        return this.zzahX.put(s2, elapsedRealtime);
    }
    
    public boolean zzcy(final String s) {
        while (true) {
            synchronized (this) {
                if (this.zzahX.remove(s) != null) {
                    return true;
                }
            }
            return false;
        }
    }
}
