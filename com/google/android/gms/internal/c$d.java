// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class c$d extends pg<c$d>
{
    public d$a[] fA;
    public d$a[] fB;
    public c$c[] fC;
    
    public c$d() {
        this.h();
    }
    
    @Override
    public void a(final pf pf) {
        final int n = 0;
        if (this.fA != null && this.fA.length > 0) {
            for (int i = 0; i < this.fA.length; ++i) {
                final d$a d$a = this.fA[i];
                if (d$a != null) {
                    pf.a(1, d$a);
                }
            }
        }
        if (this.fB != null && this.fB.length > 0) {
            for (int j = 0; j < this.fB.length; ++j) {
                final d$a d$a2 = this.fB[j];
                if (d$a2 != null) {
                    pf.a(2, d$a2);
                }
            }
        }
        if (this.fC != null && this.fC.length > 0) {
            for (int k = n; k < this.fC.length; ++k) {
                final c$c c$c = this.fC[k];
                if (c$c != null) {
                    pf.a(3, c$c);
                }
            }
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        final int n = 0;
        int c;
        int n2 = c = super.c();
        if (this.fA != null) {
            c = n2;
            if (this.fA.length > 0) {
                int n3;
                for (int i = 0; i < this.fA.length; ++i, n2 = n3) {
                    final d$a d$a = this.fA[i];
                    n3 = n2;
                    if (d$a != null) {
                        n3 = n2 + pf.c(1, d$a);
                    }
                }
                c = n2;
            }
        }
        int n4 = c;
        if (this.fB != null) {
            n4 = c;
            if (this.fB.length > 0) {
                n4 = c;
                int n5;
                for (int j = 0; j < this.fB.length; ++j, n4 = n5) {
                    final d$a d$a2 = this.fB[j];
                    n5 = n4;
                    if (d$a2 != null) {
                        n5 = n4 + pf.c(2, d$a2);
                    }
                }
            }
        }
        int n6 = n4;
        if (this.fC != null) {
            n6 = n4;
            if (this.fC.length > 0) {
                int n7 = n;
                while (true) {
                    n6 = n4;
                    if (n7 >= this.fC.length) {
                        break;
                    }
                    final c$c c$c = this.fC[n7];
                    int n8 = n4;
                    if (c$c != null) {
                        n8 = n4 + pf.c(3, c$c);
                    }
                    ++n7;
                    n4 = n8;
                }
            }
        }
        return n6;
    }
    
    public c$d e(final pe pe) {
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
                case 10: {
                    final int b = pp.b(pe, 10);
                    int length;
                    if (this.fA == null) {
                        length = 0;
                    }
                    else {
                        length = this.fA.length;
                    }
                    final d$a[] fa = new d$a[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.fA, 0, fa, 0, length);
                        i = length;
                    }
                    while (i < fa.length - 1) {
                        pe.a(fa[i] = new d$a());
                        pe.qg();
                        ++i;
                    }
                    pe.a(fa[i] = new d$a());
                    this.fA = fa;
                    continue;
                }
                case 18: {
                    final int b2 = pp.b(pe, 18);
                    int length2;
                    if (this.fB == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.fB.length;
                    }
                    final d$a[] fb = new d$a[b2 + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.fB, 0, fb, 0, length2);
                        j = length2;
                    }
                    while (j < fb.length - 1) {
                        pe.a(fb[j] = new d$a());
                        pe.qg();
                        ++j;
                    }
                    pe.a(fb[j] = new d$a());
                    this.fB = fb;
                    continue;
                }
                case 26: {
                    final int b3 = pp.b(pe, 26);
                    int length3;
                    if (this.fC == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.fC.length;
                    }
                    final c$c[] fc = new c$c[b3 + length3];
                    int k = length3;
                    if (length3 != 0) {
                        System.arraycopy(this.fC, 0, fc, 0, length3);
                        k = length3;
                    }
                    while (k < fc.length - 1) {
                        pe.a(fc[k] = new c$c());
                        pe.qg();
                        ++k;
                    }
                    pe.a(fc[k] = new c$c());
                    this.fC = fc;
                    continue;
                }
            }
        }
        return this;
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
            if (o instanceof c$d) {
                final c$d c$d = (c$d)o;
                b2 = b;
                if (pk.equals(this.fA, c$d.fA)) {
                    b2 = b;
                    if (pk.equals(this.fB, c$d.fB)) {
                        b2 = b;
                        if (pk.equals(this.fC, c$d.fC)) {
                            return this.a(c$d);
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public c$d h() {
        this.fA = d$a.r();
        this.fB = d$a.r();
        this.fC = c$c.f();
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    @Override
    public int hashCode() {
        return (((pk.hashCode(this.fA) + 527) * 31 + pk.hashCode(this.fB)) * 31 + pk.hashCode(this.fC)) * 31 + this.qx();
    }
}
