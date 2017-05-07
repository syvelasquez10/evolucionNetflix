// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.util.Log;
import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;

public class zze
{
    private final long zzafP;
    private final int zzafQ;
    private final SimpleArrayMap<String, Long> zzafR;
    
    public zze() {
        this.zzafP = 60000L;
        this.zzafQ = 10;
        this.zzafR = new SimpleArrayMap<String, Long>(10);
    }
    
    public zze(final int zzafQ, final long zzafP) {
        this.zzafP = zzafP;
        this.zzafQ = zzafQ;
        this.zzafR = new SimpleArrayMap<String, Long>();
    }
    
    private void zzb(final long n, final long n2) {
        for (int i = this.zzafR.size() - 1; i >= 0; --i) {
            if (n2 - this.zzafR.valueAt(i) > n) {
                this.zzafR.removeAt(i);
            }
        }
    }
    
    public Long zzcy(final String s) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        long zzafP = this.zzafP;
        synchronized (this) {
            while (this.zzafR.size() >= this.zzafQ) {
                this.zzb(zzafP, elapsedRealtime);
                zzafP /= 2L;
                Log.w("ConnectionTracker", "The max capacity " + this.zzafQ + " is not enough. Current durationThreshold is: " + zzafP);
            }
        }
        final String s2;
        // monitorexit(this)
        return this.zzafR.put(s2, elapsedRealtime);
    }
    
    public boolean zzcz(final String s) {
        while (true) {
            synchronized (this) {
                if (this.zzafR.remove(s) != null) {
                    return true;
                }
            }
            return false;
        }
    }
}
