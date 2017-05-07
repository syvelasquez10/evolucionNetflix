// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class ok$a extends pg<ok$a>
{
    public long asg;
    public c$j ash;
    public c$f gs;
    
    public ok$a() {
        this.pJ();
    }
    
    public static ok$a l(final byte[] array) {
        return pm.a(new ok$a(), array);
    }
    
    @Override
    public void a(final pf pf) {
        pf.b(1, this.asg);
        if (this.gs != null) {
            pf.a(2, this.gs);
        }
        if (this.ash != null) {
            pf.a(3, this.ash);
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        int n2;
        final int n = n2 = super.c() + pf.d(1, this.asg);
        if (this.gs != null) {
            n2 = n + pf.c(2, this.gs);
        }
        int n3 = n2;
        if (this.ash != null) {
            n3 = n2 + pf.c(3, this.ash);
        }
        return n3;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (o == this) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o instanceof ok$a) {
                final ok$a ok$a = (ok$a)o;
                b2 = b;
                if (this.asg == ok$a.asg) {
                    if (this.gs == null) {
                        b2 = b;
                        if (ok$a.gs != null) {
                            return b2;
                        }
                    }
                    else if (!this.gs.equals(ok$a.gs)) {
                        return false;
                    }
                    if (this.ash == null) {
                        b2 = b;
                        if (ok$a.ash != null) {
                            return b2;
                        }
                    }
                    else if (!this.ash.equals(ok$a.ash)) {
                        return false;
                    }
                    return this.a(ok$a);
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int n = (int)(this.asg ^ this.asg >>> 32);
        int hashCode2;
        if (this.gs == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.gs.hashCode();
        }
        if (this.ash != null) {
            hashCode = this.ash.hashCode();
        }
        return ((hashCode2 + (n + 527) * 31) * 31 + hashCode) * 31 + this.qx();
    }
    
    public ok$a p(final pe pe) {
    Label_0057:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0057;
                    }
                    continue;
                }
                case 0: {
                    break Label_0057;
                }
                case 8: {
                    this.asg = pe.qi();
                    continue;
                }
                case 18: {
                    if (this.gs == null) {
                        this.gs = new c$f();
                    }
                    pe.a(this.gs);
                    continue;
                }
                case 26: {
                    if (this.ash == null) {
                        this.ash = new c$j();
                    }
                    pe.a(this.ash);
                    continue;
                }
            }
        }
        return this;
    }
    
    public ok$a pJ() {
        this.asg = 0L;
        this.gs = null;
        this.ash = null;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
}
