// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

public class zzad
{
    private final long zzOe;
    private final int zzOf;
    private double zzOg;
    private long zzOh;
    private final Object zzOi;
    private final String zzOj;
    
    public zzad(final int zzOf, final long zzOe, final String zzOj) {
        this.zzOi = new Object();
        this.zzOf = zzOf;
        this.zzOg = this.zzOf;
        this.zzOe = zzOe;
        this.zzOj = zzOj;
    }
    
    public zzad(final String s) {
        this(60, 2000L, s);
    }
    
    public boolean zzkp() {
        synchronized (this.zzOi) {
            final long currentTimeMillis = System.currentTimeMillis();
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
            zzae.zzaE("Excessive " + this.zzOj + " detected; call ignored.");
            return false;
        }
    }
}
