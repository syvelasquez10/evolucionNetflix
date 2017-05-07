// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

class zzcs implements zzcd
{
    private final long zzOe;
    private final int zzOf;
    private double zzOg;
    private final Object zzOi;
    private long zzaRW;
    
    public zzcs() {
        this(60, 2000L);
    }
    
    public zzcs(final int zzOf, final long zzOe) {
        this.zzOi = new Object();
        this.zzOf = zzOf;
        this.zzOg = this.zzOf;
        this.zzOe = zzOe;
    }
    
    @Override
    public boolean zzkp() {
        synchronized (this.zzOi) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.zzOg < this.zzOf) {
                final double n = (currentTimeMillis - this.zzaRW) / this.zzOe;
                if (n > 0.0) {
                    this.zzOg = Math.min(this.zzOf, n + this.zzOg);
                }
            }
            this.zzaRW = currentTimeMillis;
            if (this.zzOg >= 1.0) {
                --this.zzOg;
                return true;
            }
            zzbg.zzaE("No more tokens available.");
            return false;
        }
    }
}
