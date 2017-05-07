// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzlm;

class zzbe implements zzcd
{
    private final long zzOe;
    private final int zzOf;
    private double zzOg;
    private long zzOh;
    private final Object zzOi;
    private final String zzOj;
    private final long zzaQB;
    private final zzlm zzpO;
    
    public zzbe(final int zzOf, final long zzOe, final long zzaQB, final String zzOj, final zzlm zzpO) {
        this.zzOi = new Object();
        this.zzOf = zzOf;
        this.zzOg = this.zzOf;
        this.zzOe = zzOe;
        this.zzaQB = zzaQB;
        this.zzOj = zzOj;
        this.zzpO = zzpO;
    }
    
    @Override
    public boolean zzkp() {
        synchronized (this.zzOi) {
            final long currentTimeMillis = this.zzpO.currentTimeMillis();
            if (currentTimeMillis - this.zzOh < this.zzaQB) {
                zzbg.zzaE("Excessive " + this.zzOj + " detected; call ignored.");
                return false;
            }
            if (this.zzOg < this.zzOf) {
                final double n = (currentTimeMillis - this.zzOh) / this.zzOe;
                if (n > 0.0) {
                    this.zzOg = Math.min(this.zzOf, n + this.zzOg);
                }
            }
            this.zzOh = currentTimeMillis;
            if (this.zzOg >= 1.0) {
                --this.zzOg;
                return true;
            }
        }
        zzbg.zzaE("Excessive " + this.zzOj + " detected; call ignored.");
        // monitorexit(o)
        return false;
    }
}
