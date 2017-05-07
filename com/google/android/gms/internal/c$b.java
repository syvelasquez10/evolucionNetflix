// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class c$b extends pg<c$b>
{
    private static volatile c$b[] fp;
    public int[] fq;
    public int fr;
    public boolean fs;
    public boolean ft;
    public int name;
    
    public c$b() {
        this.e();
    }
    
    public static c$b[] d() {
        Label_0027: {
            if (c$b.fp != null) {
                break Label_0027;
            }
            synchronized (pk.awI) {
                if (c$b.fp == null) {
                    c$b.fp = new c$b[0];
                }
                return c$b.fp;
            }
        }
    }
    
    @Override
    public void a(final pf pf) {
        if (this.ft) {
            pf.b(1, this.ft);
        }
        pf.s(2, this.fr);
        if (this.fq != null && this.fq.length > 0) {
            for (int i = 0; i < this.fq.length; ++i) {
                pf.s(3, this.fq[i]);
            }
        }
        if (this.name != 0) {
            pf.s(4, this.name);
        }
        if (this.fs) {
            pf.b(6, this.fs);
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        int n = 0;
        int c = super.c();
        if (this.ft) {
            c += pf.c(1, this.ft);
        }
        final int n2 = pf.u(2, this.fr) + c;
        int n3;
        if (this.fq != null && this.fq.length > 0) {
            for (int i = 0; i < this.fq.length; ++i) {
                n += pf.gv(this.fq[i]);
            }
            n3 = n2 + n + this.fq.length * 1;
        }
        else {
            n3 = n2;
        }
        int n4 = n3;
        if (this.name != 0) {
            n4 = n3 + pf.u(4, this.name);
        }
        int n5 = n4;
        if (this.fs) {
            n5 = n4 + pf.c(6, this.fs);
        }
        return n5;
    }
    
    public c$b c(final pe pe) {
    Label_0081:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0081;
                    }
                    continue;
                }
                case 0: {
                    break Label_0081;
                }
                case 8: {
                    this.ft = pe.qk();
                    continue;
                }
                case 16: {
                    this.fr = pe.qj();
                    continue;
                }
                case 24: {
                    final int b = pp.b(pe, 24);
                    int length;
                    if (this.fq == null) {
                        length = 0;
                    }
                    else {
                        length = this.fq.length;
                    }
                    final int[] fq = new int[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.fq, 0, fq, 0, length);
                        i = length;
                    }
                    while (i < fq.length - 1) {
                        fq[i] = pe.qj();
                        pe.qg();
                        ++i;
                    }
                    fq[i] = pe.qj();
                    this.fq = fq;
                    continue;
                }
                case 26: {
                    final int go = pe.go(pe.qn());
                    final int position = pe.getPosition();
                    int n = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n;
                    }
                    pe.gq(position);
                    int length2;
                    if (this.fq == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.fq.length;
                    }
                    final int[] fq2 = new int[n + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.fq, 0, fq2, 0, length2);
                        j = length2;
                    }
                    while (j < fq2.length) {
                        fq2[j] = pe.qj();
                        ++j;
                    }
                    this.fq = fq2;
                    pe.gp(go);
                    continue;
                }
                case 32: {
                    this.name = pe.qj();
                    continue;
                }
                case 48: {
                    this.fs = pe.qk();
                    continue;
                }
            }
        }
        return this;
    }
    
    public c$b e() {
        this.fq = pp.awL;
        this.fr = 0;
        this.name = 0;
        this.fs = false;
        this.ft = false;
        this.awy = null;
        this.awJ = -1;
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
            if (o instanceof c$b) {
                final c$b c$b = (c$b)o;
                b2 = b;
                if (pk.equals(this.fq, c$b.fq)) {
                    b2 = b;
                    if (this.fr == c$b.fr) {
                        b2 = b;
                        if (this.name == c$b.name) {
                            b2 = b;
                            if (this.fs == c$b.fs) {
                                b2 = b;
                                if (this.ft == c$b.ft) {
                                    return this.a(c$b);
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        int n = 1231;
        final int hashCode = pk.hashCode(this.fq);
        final int fr = this.fr;
        final int name = this.name;
        int n2;
        if (this.fs) {
            n2 = 1231;
        }
        else {
            n2 = 1237;
        }
        if (!this.ft) {
            n = 1237;
        }
        return ((n2 + (((hashCode + 527) * 31 + fr) * 31 + name) * 31) * 31 + n) * 31 + this.qx();
    }
}
