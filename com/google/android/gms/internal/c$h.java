// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class c$h extends pg<c$h>
{
    public static final ph<d$a, c$h> gf;
    private static final c$h[] gg;
    public int[] gh;
    public int[] gi;
    public int[] gj;
    public int gk;
    public int[] gl;
    public int gm;
    public int gn;
    
    static {
        gf = ph.a(11, c$h.class, 810);
        gg = new c$h[0];
    }
    
    public c$h() {
        this.n();
    }
    
    @Override
    public void a(final pf pf) {
        final int n = 0;
        if (this.gh != null && this.gh.length > 0) {
            for (int i = 0; i < this.gh.length; ++i) {
                pf.s(1, this.gh[i]);
            }
        }
        if (this.gi != null && this.gi.length > 0) {
            for (int j = 0; j < this.gi.length; ++j) {
                pf.s(2, this.gi[j]);
            }
        }
        if (this.gj != null && this.gj.length > 0) {
            for (int k = 0; k < this.gj.length; ++k) {
                pf.s(3, this.gj[k]);
            }
        }
        if (this.gk != 0) {
            pf.s(4, this.gk);
        }
        if (this.gl != null && this.gl.length > 0) {
            for (int l = n; l < this.gl.length; ++l) {
                pf.s(5, this.gl[l]);
            }
        }
        if (this.gm != 0) {
            pf.s(6, this.gm);
        }
        if (this.gn != 0) {
            pf.s(7, this.gn);
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        final int n = 0;
        final int c = super.c();
        int n3;
        if (this.gh != null && this.gh.length > 0) {
            int i = 0;
            int n2 = 0;
            while (i < this.gh.length) {
                n2 += pf.gv(this.gh[i]);
                ++i;
            }
            n3 = c + n2 + this.gh.length * 1;
        }
        else {
            n3 = c;
        }
        int n4 = n3;
        if (this.gi != null) {
            n4 = n3;
            if (this.gi.length > 0) {
                int j = 0;
                int n5 = 0;
                while (j < this.gi.length) {
                    n5 += pf.gv(this.gi[j]);
                    ++j;
                }
                n4 = n3 + n5 + this.gi.length * 1;
            }
        }
        int n6 = n4;
        if (this.gj != null) {
            n6 = n4;
            if (this.gj.length > 0) {
                int k = 0;
                int n7 = 0;
                while (k < this.gj.length) {
                    n7 += pf.gv(this.gj[k]);
                    ++k;
                }
                n6 = n4 + n7 + this.gj.length * 1;
            }
        }
        int n8 = n6;
        if (this.gk != 0) {
            n8 = n6 + pf.u(4, this.gk);
        }
        int n9 = n8;
        if (this.gl != null) {
            n9 = n8;
            if (this.gl.length > 0) {
                int n10 = 0;
                for (int l = n; l < this.gl.length; ++l) {
                    n10 += pf.gv(this.gl[l]);
                }
                n9 = n8 + n10 + this.gl.length * 1;
            }
        }
        int n11 = n9;
        if (this.gm != 0) {
            n11 = n9 + pf.u(6, this.gm);
        }
        int n12 = n11;
        if (this.gn != 0) {
            n12 = n11 + pf.u(7, this.gn);
        }
        return n12;
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
            if (o instanceof c$h) {
                final c$h c$h = (c$h)o;
                b2 = b;
                if (pk.equals(this.gh, c$h.gh)) {
                    b2 = b;
                    if (pk.equals(this.gi, c$h.gi)) {
                        b2 = b;
                        if (pk.equals(this.gj, c$h.gj)) {
                            b2 = b;
                            if (this.gk == c$h.gk) {
                                b2 = b;
                                if (pk.equals(this.gl, c$h.gl)) {
                                    b2 = b;
                                    if (this.gm == c$h.gm) {
                                        b2 = b;
                                        if (this.gn == c$h.gn) {
                                            return this.a(c$h);
                                        }
                                    }
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
        return (((((((pk.hashCode(this.gh) + 527) * 31 + pk.hashCode(this.gi)) * 31 + pk.hashCode(this.gj)) * 31 + this.gk) * 31 + pk.hashCode(this.gl)) * 31 + this.gm) * 31 + this.gn) * 31 + this.qx();
    }
    
    public c$h i(final pe pe) {
    Label_0121:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0121;
                    }
                    continue;
                }
                case 0: {
                    break Label_0121;
                }
                case 8: {
                    final int b = pp.b(pe, 8);
                    int length;
                    if (this.gh == null) {
                        length = 0;
                    }
                    else {
                        length = this.gh.length;
                    }
                    final int[] gh = new int[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.gh, 0, gh, 0, length);
                        i = length;
                    }
                    while (i < gh.length - 1) {
                        gh[i] = pe.qj();
                        pe.qg();
                        ++i;
                    }
                    gh[i] = pe.qj();
                    this.gh = gh;
                    continue;
                }
                case 10: {
                    final int go = pe.go(pe.qn());
                    final int position = pe.getPosition();
                    int n = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n;
                    }
                    pe.gq(position);
                    int length2;
                    if (this.gh == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.gh.length;
                    }
                    final int[] gh2 = new int[n + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.gh, 0, gh2, 0, length2);
                        j = length2;
                    }
                    while (j < gh2.length) {
                        gh2[j] = pe.qj();
                        ++j;
                    }
                    this.gh = gh2;
                    pe.gp(go);
                    continue;
                }
                case 16: {
                    final int b2 = pp.b(pe, 16);
                    int length3;
                    if (this.gi == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.gi.length;
                    }
                    final int[] gi = new int[b2 + length3];
                    int k = length3;
                    if (length3 != 0) {
                        System.arraycopy(this.gi, 0, gi, 0, length3);
                        k = length3;
                    }
                    while (k < gi.length - 1) {
                        gi[k] = pe.qj();
                        pe.qg();
                        ++k;
                    }
                    gi[k] = pe.qj();
                    this.gi = gi;
                    continue;
                }
                case 18: {
                    final int go2 = pe.go(pe.qn());
                    final int position2 = pe.getPosition();
                    int n2 = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n2;
                    }
                    pe.gq(position2);
                    int length4;
                    if (this.gi == null) {
                        length4 = 0;
                    }
                    else {
                        length4 = this.gi.length;
                    }
                    final int[] gi2 = new int[n2 + length4];
                    int l = length4;
                    if (length4 != 0) {
                        System.arraycopy(this.gi, 0, gi2, 0, length4);
                        l = length4;
                    }
                    while (l < gi2.length) {
                        gi2[l] = pe.qj();
                        ++l;
                    }
                    this.gi = gi2;
                    pe.gp(go2);
                    continue;
                }
                case 24: {
                    final int b3 = pp.b(pe, 24);
                    int length5;
                    if (this.gj == null) {
                        length5 = 0;
                    }
                    else {
                        length5 = this.gj.length;
                    }
                    final int[] gj = new int[b3 + length5];
                    int n3 = length5;
                    if (length5 != 0) {
                        System.arraycopy(this.gj, 0, gj, 0, length5);
                        n3 = length5;
                    }
                    while (n3 < gj.length - 1) {
                        gj[n3] = pe.qj();
                        pe.qg();
                        ++n3;
                    }
                    gj[n3] = pe.qj();
                    this.gj = gj;
                    continue;
                }
                case 26: {
                    final int go3 = pe.go(pe.qn());
                    final int position3 = pe.getPosition();
                    int n4 = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n4;
                    }
                    pe.gq(position3);
                    int length6;
                    if (this.gj == null) {
                        length6 = 0;
                    }
                    else {
                        length6 = this.gj.length;
                    }
                    final int[] gj2 = new int[n4 + length6];
                    int n5 = length6;
                    if (length6 != 0) {
                        System.arraycopy(this.gj, 0, gj2, 0, length6);
                        n5 = length6;
                    }
                    while (n5 < gj2.length) {
                        gj2[n5] = pe.qj();
                        ++n5;
                    }
                    this.gj = gj2;
                    pe.gp(go3);
                    continue;
                }
                case 32: {
                    this.gk = pe.qj();
                    continue;
                }
                case 40: {
                    final int b4 = pp.b(pe, 40);
                    int length7;
                    if (this.gl == null) {
                        length7 = 0;
                    }
                    else {
                        length7 = this.gl.length;
                    }
                    final int[] gl = new int[b4 + length7];
                    int n6 = length7;
                    if (length7 != 0) {
                        System.arraycopy(this.gl, 0, gl, 0, length7);
                        n6 = length7;
                    }
                    while (n6 < gl.length - 1) {
                        gl[n6] = pe.qj();
                        pe.qg();
                        ++n6;
                    }
                    gl[n6] = pe.qj();
                    this.gl = gl;
                    continue;
                }
                case 42: {
                    final int go4 = pe.go(pe.qn());
                    final int position4 = pe.getPosition();
                    int n7 = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n7;
                    }
                    pe.gq(position4);
                    int length8;
                    if (this.gl == null) {
                        length8 = 0;
                    }
                    else {
                        length8 = this.gl.length;
                    }
                    final int[] gl2 = new int[n7 + length8];
                    int n8 = length8;
                    if (length8 != 0) {
                        System.arraycopy(this.gl, 0, gl2, 0, length8);
                        n8 = length8;
                    }
                    while (n8 < gl2.length) {
                        gl2[n8] = pe.qj();
                        ++n8;
                    }
                    this.gl = gl2;
                    pe.gp(go4);
                    continue;
                }
                case 48: {
                    this.gm = pe.qj();
                    continue;
                }
                case 56: {
                    this.gn = pe.qj();
                    continue;
                }
            }
        }
        return this;
    }
    
    public c$h n() {
        this.gh = pp.awL;
        this.gi = pp.awL;
        this.gj = pp.awL;
        this.gk = 0;
        this.gl = pp.awL;
        this.gm = 0;
        this.gn = 0;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
}
