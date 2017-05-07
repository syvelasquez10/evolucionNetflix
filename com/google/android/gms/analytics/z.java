// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

class z implements ad
{
    private final long vm;
    private final int vn;
    private double vo;
    private long vp;
    private final Object vq;
    private final String vr;
    
    public z(final int vn, final long vm, final String vr) {
        this.vq = new Object();
        this.vn = vn;
        this.vo = this.vn;
        this.vm = vm;
        this.vr = vr;
    }
    
    public z(final String s) {
        this(60, 2000L, s);
    }
    
    @Override
    public boolean cS() {
        synchronized (this.vq) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.vo < this.vn) {
                final double n = (currentTimeMillis - this.vp) / this.vm;
                if (n > 0.0) {
                    this.vo = Math.min(this.vn, n + this.vo);
                }
            }
            this.vp = currentTimeMillis;
            if (this.vo >= 1.0) {
                --this.vo;
                return true;
            }
            aa.z("Excessive " + this.vr + " detected; call ignored.");
            return false;
        }
    }
}
