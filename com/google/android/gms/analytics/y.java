// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

class y implements ac
{
    private final long AN;
    private final int AO;
    private double AP;
    private long AQ;
    private final Object AR;
    private final String AS;
    
    public y(final int ao, final long an, final String as) {
        this.AR = new Object();
        this.AO = ao;
        this.AP = this.AO;
        this.AN = an;
        this.AS = as;
    }
    
    public y(final String s) {
        this(60, 2000L, s);
    }
    
    @Override
    public boolean eK() {
        synchronized (this.AR) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.AP < this.AO) {
                final double n = (currentTimeMillis - this.AQ) / this.AN;
                if (n > 0.0) {
                    this.AP = Math.min(this.AO, n + this.AP);
                }
            }
            this.AQ = currentTimeMillis;
            if (this.AP >= 1.0) {
                --this.AP;
                return true;
            }
            z.W("Excessive " + this.AS + " detected; call ignored.");
            return false;
        }
    }
}
