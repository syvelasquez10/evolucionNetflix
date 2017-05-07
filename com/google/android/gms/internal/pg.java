// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public abstract class pg<M extends pg<M>> extends pm
{
    protected pi awy;
    
    public final <T> T a(final ph<M, T> ph) {
        if (this.awy != null) {
            final pj gd = this.awy.gD(pp.gH(ph.tag));
            if (gd != null) {
                return gd.b(ph);
            }
        }
        return null;
    }
    
    @Override
    public void a(final pf pf) {
        if (this.awy != null) {
            for (int i = 0; i < this.awy.size(); ++i) {
                this.awy.gE(i).a(pf);
            }
        }
    }
    
    protected final boolean a(final pe pe, final int n) {
        final int position = pe.getPosition();
        if (!pe.gm(n)) {
            return false;
        }
        final int gh = pp.gH(n);
        final po po = new po(n, pe.r(position, pe.getPosition() - position));
        pj gd = null;
        if (this.awy == null) {
            this.awy = new pi();
        }
        else {
            gd = this.awy.gD(gh);
        }
        pj pj = gd;
        if (gd == null) {
            pj = new pj();
            this.awy.a(gh, pj);
        }
        pj.a(po);
        return true;
    }
    
    protected final boolean a(final M m) {
        if (this.awy == null || this.awy.isEmpty()) {
            return m.awy == null || m.awy.isEmpty();
        }
        return this.awy.equals(m.awy);
    }
    
    @Override
    protected int c() {
        int n = 0;
        int n3;
        if (this.awy != null) {
            int n2 = 0;
            while (true) {
                n3 = n2;
                if (n >= this.awy.size()) {
                    break;
                }
                n2 += this.awy.gE(n).c();
                ++n;
            }
        }
        else {
            n3 = 0;
        }
        return n3;
    }
    
    protected final int qx() {
        if (this.awy == null || this.awy.isEmpty()) {
            return 0;
        }
        return this.awy.hashCode();
    }
}
