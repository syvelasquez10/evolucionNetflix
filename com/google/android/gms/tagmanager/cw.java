// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

class cw implements cg
{
    private final long AN;
    private final int AO;
    private double AP;
    private final Object AR;
    private long are;
    
    public cw() {
        this(60, 2000L);
    }
    
    public cw(final int ao, final long an) {
        this.AR = new Object();
        this.AO = ao;
        this.AP = this.AO;
        this.AN = an;
    }
    
    @Override
    public boolean eK() {
        synchronized (this.AR) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.AP < this.AO) {
                final double n = (currentTimeMillis - this.are) / this.AN;
                if (n > 0.0) {
                    this.AP = Math.min(this.AO, n + this.AP);
                }
            }
            this.are = currentTimeMillis;
            if (this.AP >= 1.0) {
                --this.AP;
                return true;
            }
            bh.W("No more tokens available.");
            return false;
        }
    }
}
