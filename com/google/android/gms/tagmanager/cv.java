// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

class cv implements cf
{
    private long aab;
    private final long vm;
    private final int vn;
    private double vo;
    private final Object vq;
    
    public cv() {
        this(60, 2000L);
    }
    
    public cv(final int vn, final long vm) {
        this.vq = new Object();
        this.vn = vn;
        this.vo = this.vn;
        this.vm = vm;
    }
    
    @Override
    public boolean cS() {
        synchronized (this.vq) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.vo < this.vn) {
                final double n = (currentTimeMillis - this.aab) / this.vm;
                if (n > 0.0) {
                    this.vo = Math.min(this.vn, n + this.vo);
                }
            }
            this.aab = currentTimeMillis;
            if (this.vo >= 1.0) {
                --this.vo;
                return true;
            }
            bh.z("No more tokens available.");
            return false;
        }
    }
}
