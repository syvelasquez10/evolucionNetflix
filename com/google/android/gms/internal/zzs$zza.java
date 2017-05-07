// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

class zzs$zza
{
    public static final boolean zzak;
    private final List<zzs$zza$zza> zzal;
    private boolean zzam;
    
    static {
        zzak = zzs.DEBUG;
    }
    
    zzs$zza() {
        this.zzal = new ArrayList<zzs$zza$zza>();
        this.zzam = false;
    }
    
    private long zzx() {
        if (this.zzal.size() == 0) {
            return 0L;
        }
        return this.zzal.get(this.zzal.size() - 1).time - this.zzal.get(0).time;
    }
    
    @Override
    protected void finalize() {
        if (!this.zzam) {
            this.zzd("Request on the loose");
            zzs.zzc("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }
    }
    
    public void zza(final String s, final long n) {
        synchronized (this) {
            if (this.zzam) {
                throw new IllegalStateException("Marker added to finished log");
            }
        }
        final String s2;
        this.zzal.add(new zzs$zza$zza(s2, n, SystemClock.elapsedRealtime()));
    }
    // monitorexit(this)
    
    public void zzd(final String s) {
        synchronized (this) {
            this.zzam = true;
            final long zzx = this.zzx();
            if (zzx > 0L) {
                long time = this.zzal.get(0).time;
                zzs.zzb("(%-4d ms) %s", zzx, s);
                for (final zzs$zza$zza zzs$zza$zza : this.zzal) {
                    final long time2 = zzs$zza$zza.time;
                    zzs.zzb("(+%-4d) [%2d] %s", time2 - time, zzs$zza$zza.zzan, zzs$zza$zza.name);
                    time = time2;
                }
            }
        }
    }
}
