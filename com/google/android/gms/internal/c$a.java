// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class c$a extends pg<c$a>
{
    public int fn;
    public int fo;
    public int level;
    
    public c$a() {
        this.b();
    }
    
    public c$a a(final pe pe) {
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
                    final int qj = pe.qj();
                    switch (qj) {
                        default: {
                            continue;
                        }
                        case 1:
                        case 2:
                        case 3: {
                            this.level = qj;
                            continue;
                        }
                    }
                    break;
                }
                case 16: {
                    this.fn = pe.qj();
                    continue;
                }
                case 24: {
                    this.fo = pe.qj();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    public void a(final pf pf) {
        if (this.level != 1) {
            pf.s(1, this.level);
        }
        if (this.fn != 0) {
            pf.s(2, this.fn);
        }
        if (this.fo != 0) {
            pf.s(3, this.fo);
        }
        super.a(pf);
    }
    
    public c$a b() {
        this.level = 1;
        this.fn = 0;
        this.fo = 0;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    @Override
    protected int c() {
        int c;
        final int n = c = super.c();
        if (this.level != 1) {
            c = n + pf.u(1, this.level);
        }
        int n2 = c;
        if (this.fn != 0) {
            n2 = c + pf.u(2, this.fn);
        }
        int n3 = n2;
        if (this.fo != 0) {
            n3 = n2 + pf.u(3, this.fo);
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
            if (o instanceof c$a) {
                final c$a c$a = (c$a)o;
                b2 = b;
                if (this.level == c$a.level) {
                    b2 = b;
                    if (this.fn == c$a.fn) {
                        b2 = b;
                        if (this.fo == c$a.fo) {
                            return this.a(c$a);
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return (((this.level + 527) * 31 + this.fn) * 31 + this.fo) * 31 + this.qx();
    }
}
