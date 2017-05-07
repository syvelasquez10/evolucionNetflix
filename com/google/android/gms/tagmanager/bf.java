// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.ju;

class bf implements cg
{
    private final long AN;
    private final int AO;
    private double AP;
    private long AQ;
    private final Object AR;
    private final String AS;
    private final long apA;
    private final ju yD;
    
    public bf(final int ao, final long an, final long apA, final String as, final ju yd) {
        this.AR = new Object();
        this.AO = ao;
        this.AP = this.AO;
        this.AN = an;
        this.apA = apA;
        this.AS = as;
        this.yD = yd;
    }
    
    @Override
    public boolean eK() {
        synchronized (this.AR) {
            final long currentTimeMillis = this.yD.currentTimeMillis();
            if (currentTimeMillis - this.AQ < this.apA) {
                bh.W("Excessive " + this.AS + " detected; call ignored.");
                return false;
            }
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
        }
        bh.W("Excessive " + this.AS + " detected; call ignored.");
        // monitorexit(o)
        return false;
    }
}
