// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.SystemClock;
import android.os.Process;
import android.net.TrafficStats;
import android.os.Build$VERSION;
import java.util.concurrent.BlockingQueue;

public class zzg extends Thread
{
    private final zzb zzj;
    private final zzn zzk;
    private volatile boolean zzl;
    private final BlockingQueue<zzk<?>> zzy;
    private final zzf zzz;
    
    public zzg(final BlockingQueue<zzk<?>> zzy, final zzf zzz, final zzb zzj, final zzn zzk) {
        this.zzl = false;
        this.zzy = zzy;
        this.zzz = zzz;
        this.zzj = zzj;
        this.zzk = zzk;
    }
    
    private void zzb(final zzk<?> zzk) {
        if (Build$VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(zzk.zzg());
        }
    }
    
    private void zzb(final zzk<?> zzk, zzr zzb) {
        zzb = zzk.zzb(zzb);
        this.zzk.zza(zzk, zzb);
    }
    
    public void quit() {
        this.zzl = true;
        this.interrupt();
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            zzk<?> zzk = null;
            try {
                zzk = this.zzy.take();
                try {
                    zzk.zzc("network-queue-take");
                    if (!zzk.isCanceled()) {
                        goto Label_0075;
                    }
                    zzk.zzd("network-discard-cancelled");
                }
                catch (zzr zzr) {
                    zzr.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.zzb(zzk, zzr);
                }
                catch (Exception ex) {
                    zzs.zza(ex, "Unhandled exception %s", ex.toString());
                    final zzr zzr2 = new zzr(ex);
                    zzr2.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.zzk.zza(zzk, zzr2);
                }
                continue;
            }
            catch (InterruptedException ex2) {}
            final zzi zzi;
            final zzm<?> zza = zzk.zza(zzi);
            zzk.zzc("network-parse-complete");
            if (zzk.zzr() && zza.zzag != null) {
                this.zzj.zza(zzk.zzh(), zza.zzag);
                zzk.zzc("network-cache-written");
            }
            zzk.zzv();
            this.zzk.zza(zzk, zza);
        }
    }
}
