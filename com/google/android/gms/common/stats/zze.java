// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.util.Log;
import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;

public class zze
{
    private final long zzacF;
    private final int zzacG;
    private final SimpleArrayMap<String, Long> zzacH;
    
    public zze() {
        this.zzacF = 60000L;
        this.zzacG = 10;
        this.zzacH = new SimpleArrayMap<String, Long>(10);
    }
    
    public zze(final int zzacG, final long zzacF) {
        this.zzacF = zzacF;
        this.zzacG = zzacG;
        this.zzacH = new SimpleArrayMap<String, Long>();
    }
    
    private void zzc(final long n, final long n2) {
        for (int i = this.zzacH.size() - 1; i >= 0; --i) {
            if (n2 - this.zzacH.valueAt(i) > n) {
                this.zzacH.removeAt(i);
            }
        }
    }
    
    public Long zzcp(final String s) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        long zzacF = this.zzacF;
        synchronized (this) {
            while (this.zzacH.size() >= this.zzacG) {
                this.zzc(zzacF, elapsedRealtime);
                zzacF /= 2L;
                Log.w("ConnectionTracker", "The max capacity " + this.zzacG + " is not enough. Current durationThreshold is: " + zzacF);
            }
        }
        final String s2;
        // monitorexit(this)
        return this.zzacH.put(s2, elapsedRealtime);
    }
    
    public boolean zzcq(final String s) {
        while (true) {
            synchronized (this) {
                if (this.zzacH.remove(s) != null) {
                    return true;
                }
            }
            return false;
        }
    }
}
