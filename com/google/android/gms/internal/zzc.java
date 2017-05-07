// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class zzc extends Thread
{
    private static final boolean DEBUG;
    private final BlockingQueue<zzk<?>> zzh;
    private final BlockingQueue<zzk<?>> zzi;
    private final zzb zzj;
    private final zzn zzk;
    private volatile boolean zzl;
    
    static {
        DEBUG = zzs.DEBUG;
    }
    
    public zzc(final BlockingQueue<zzk<?>> zzh, final BlockingQueue<zzk<?>> zzi, final zzb zzj, final zzn zzk) {
        this.zzl = false;
        this.zzh = zzh;
        this.zzi = zzi;
        this.zzj = zzj;
        this.zzk = zzk;
    }
    
    public void quit() {
        this.zzl = true;
        this.interrupt();
    }
    
    @Override
    public void run() {
        if (zzc.DEBUG) {
            zzs.zza("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzj.zza();
    Label_0029_Outer:
        while (true) {
            while (true) {
                zzk<?> zzk;
                try {
                    while (true) {
                        zzk = this.zzh.take();
                        zzk.zzc("cache-queue-take");
                        if (!zzk.isCanceled()) {
                            break;
                        }
                        zzk.zzd("cache-discard-canceled");
                    }
                }
                catch (InterruptedException ex) {
                    if (this.zzl) {
                        return;
                    }
                    continue Label_0029_Outer;
                }
                final zzb$zza zza = this.zzj.zza(zzk.zzh());
                if (zza == null) {
                    zzk.zzc("cache-miss");
                    this.zzi.put(zzk);
                    continue;
                }
                if (zza.zzb()) {
                    zzk.zzc("cache-hit-expired");
                    zzk.zza(zza);
                    this.zzi.put(zzk);
                    continue;
                }
                zzk.zzc("cache-hit");
                final zzm<?> zza2 = zzk.zza(new zzi(zza.data, zza.zzg));
                zzk.zzc("cache-hit-parsed");
                if (!zza.zzc()) {
                    this.zzk.zza(zzk, zza2);
                    continue;
                }
                zzk.zzc("cache-hit-refresh-needed");
                zzk.zza(zza);
                zza2.zzai = true;
                this.zzk.zza(zzk, zza2, new zzc$1(this, zzk));
                continue;
            }
        }
    }
}
