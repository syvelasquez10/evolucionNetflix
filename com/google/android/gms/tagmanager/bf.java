// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gl;

class bf implements cf
{
    private final gl Wv;
    private final long Yx;
    private final long vm;
    private final int vn;
    private double vo;
    private long vp;
    private final Object vq;
    private final String vr;
    
    public bf(final int vn, final long vm, final long yx, final String vr, final gl wv) {
        this.vq = new Object();
        this.vn = vn;
        this.vo = this.vn;
        this.vm = vm;
        this.Yx = yx;
        this.vr = vr;
        this.Wv = wv;
    }
    
    @Override
    public boolean cS() {
        synchronized (this.vq) {
            final long currentTimeMillis = this.Wv.currentTimeMillis();
            if (currentTimeMillis - this.vp < this.Yx) {
                bh.z("Excessive " + this.vr + " detected; call ignored.");
                return false;
            }
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
        }
        bh.z("Excessive " + this.vr + " detected; call ignored.");
        // monitorexit(o)
        return false;
    }
}
