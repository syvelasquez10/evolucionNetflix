// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzx;
import android.os.Handler;

abstract class zzt
{
    private static volatile Handler zzMR;
    private final zzf zzLy;
    private volatile long zzMS;
    private boolean zzMT;
    private final Runnable zzx;
    
    zzt(final zzf zzLy) {
        com.google.android.gms.common.internal.zzx.zzv(zzLy);
        this.zzLy = zzLy;
        this.zzx = new zzt$1(this);
    }
    
    private Handler getHandler() {
        if (zzt.zzMR != null) {
            return zzt.zzMR;
        }
        synchronized (zzt.class) {
            if (zzt.zzMR == null) {
                zzt.zzMR = new Handler(this.zzLy.getContext().getMainLooper());
            }
            return zzt.zzMR;
        }
    }
    
    public void cancel() {
        this.zzMS = 0L;
        this.getHandler().removeCallbacks(this.zzx);
    }
    
    public abstract void run();
    
    public boolean zzbr() {
        return this.zzMS != 0L;
    }
    
    public long zzjR() {
        if (this.zzMS == 0L) {
            return 0L;
        }
        return Math.abs(this.zzLy.zzid().currentTimeMillis() - this.zzMS);
    }
    
    public void zzt(final long n) {
        this.cancel();
        if (n >= 0L) {
            this.zzMS = this.zzLy.zzid().currentTimeMillis();
            if (!this.getHandler().postDelayed(this.zzx, n)) {
                this.zzLy.zzie().zze("Failed to schedule delayed post. time", n);
            }
        }
    }
    
    public void zzu(long n) {
        final long n2 = 0L;
        if (this.zzbr()) {
            if (n < 0L) {
                this.cancel();
                return;
            }
            n -= Math.abs(this.zzLy.zzid().currentTimeMillis() - this.zzMS);
            if (n < 0L) {
                n = n2;
            }
            this.getHandler().removeCallbacks(this.zzx);
            if (!this.getHandler().postDelayed(this.zzx, n)) {
                this.zzLy.zzie().zze("Failed to adjust delayed post. time", n);
            }
        }
    }
}
