// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class c$g extends pg<c$g>
{
    private static volatile c$g[] fU;
    public int[] fV;
    public int[] fW;
    public int[] fX;
    public int[] fY;
    public int[] fZ;
    public int[] ga;
    public int[] gb;
    public int[] gc;
    public int[] gd;
    public int[] ge;
    
    public c$g() {
        this.m();
    }
    
    public static c$g[] l() {
        Label_0027: {
            if (c$g.fU != null) {
                break Label_0027;
            }
            synchronized (pk.awI) {
                if (c$g.fU == null) {
                    c$g.fU = new c$g[0];
                }
                return c$g.fU;
            }
        }
    }
    
    @Override
    public void a(final pf pf) {
        final int n = 0;
        if (this.fV != null && this.fV.length > 0) {
            for (int i = 0; i < this.fV.length; ++i) {
                pf.s(1, this.fV[i]);
            }
        }
        if (this.fW != null && this.fW.length > 0) {
            for (int j = 0; j < this.fW.length; ++j) {
                pf.s(2, this.fW[j]);
            }
        }
        if (this.fX != null && this.fX.length > 0) {
            for (int k = 0; k < this.fX.length; ++k) {
                pf.s(3, this.fX[k]);
            }
        }
        if (this.fY != null && this.fY.length > 0) {
            for (int l = 0; l < this.fY.length; ++l) {
                pf.s(4, this.fY[l]);
            }
        }
        if (this.fZ != null && this.fZ.length > 0) {
            for (int n2 = 0; n2 < this.fZ.length; ++n2) {
                pf.s(5, this.fZ[n2]);
            }
        }
        if (this.ga != null && this.ga.length > 0) {
            for (int n3 = 0; n3 < this.ga.length; ++n3) {
                pf.s(6, this.ga[n3]);
            }
        }
        if (this.gb != null && this.gb.length > 0) {
            for (int n4 = 0; n4 < this.gb.length; ++n4) {
                pf.s(7, this.gb[n4]);
            }
        }
        if (this.gc != null && this.gc.length > 0) {
            for (int n5 = 0; n5 < this.gc.length; ++n5) {
                pf.s(8, this.gc[n5]);
            }
        }
        if (this.gd != null && this.gd.length > 0) {
            for (int n6 = 0; n6 < this.gd.length; ++n6) {
                pf.s(9, this.gd[n6]);
            }
        }
        if (this.ge != null && this.ge.length > 0) {
            for (int n7 = n; n7 < this.ge.length; ++n7) {
                pf.s(10, this.ge[n7]);
            }
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        final int n = 0;
        final int c = super.c();
        int n3;
        if (this.fV != null && this.fV.length > 0) {
            int i = 0;
            int n2 = 0;
            while (i < this.fV.length) {
                n2 += pf.gv(this.fV[i]);
                ++i;
            }
            n3 = c + n2 + this.fV.length * 1;
        }
        else {
            n3 = c;
        }
        int n4 = n3;
        if (this.fW != null) {
            n4 = n3;
            if (this.fW.length > 0) {
                int j = 0;
                int n5 = 0;
                while (j < this.fW.length) {
                    n5 += pf.gv(this.fW[j]);
                    ++j;
                }
                n4 = n3 + n5 + this.fW.length * 1;
            }
        }
        int n6 = n4;
        if (this.fX != null) {
            n6 = n4;
            if (this.fX.length > 0) {
                int k = 0;
                int n7 = 0;
                while (k < this.fX.length) {
                    n7 += pf.gv(this.fX[k]);
                    ++k;
                }
                n6 = n4 + n7 + this.fX.length * 1;
            }
        }
        int n8 = n6;
        if (this.fY != null) {
            n8 = n6;
            if (this.fY.length > 0) {
                int l = 0;
                int n9 = 0;
                while (l < this.fY.length) {
                    n9 += pf.gv(this.fY[l]);
                    ++l;
                }
                n8 = n6 + n9 + this.fY.length * 1;
            }
        }
        int n10 = n8;
        if (this.fZ != null) {
            n10 = n8;
            if (this.fZ.length > 0) {
                int n11 = 0;
                int n12 = 0;
                while (n11 < this.fZ.length) {
                    n12 += pf.gv(this.fZ[n11]);
                    ++n11;
                }
                n10 = n8 + n12 + this.fZ.length * 1;
            }
        }
        int n13 = n10;
        if (this.ga != null) {
            n13 = n10;
            if (this.ga.length > 0) {
                int n14 = 0;
                int n15 = 0;
                while (n14 < this.ga.length) {
                    n15 += pf.gv(this.ga[n14]);
                    ++n14;
                }
                n13 = n10 + n15 + this.ga.length * 1;
            }
        }
        int n16 = n13;
        if (this.gb != null) {
            n16 = n13;
            if (this.gb.length > 0) {
                int n17 = 0;
                int n18 = 0;
                while (n17 < this.gb.length) {
                    n18 += pf.gv(this.gb[n17]);
                    ++n17;
                }
                n16 = n13 + n18 + this.gb.length * 1;
            }
        }
        int n19 = n16;
        if (this.gc != null) {
            n19 = n16;
            if (this.gc.length > 0) {
                int n20 = 0;
                int n21 = 0;
                while (n20 < this.gc.length) {
                    n21 += pf.gv(this.gc[n20]);
                    ++n20;
                }
                n19 = n16 + n21 + this.gc.length * 1;
            }
        }
        int n22 = n19;
        if (this.gd != null) {
            n22 = n19;
            if (this.gd.length > 0) {
                int n23 = 0;
                int n24 = 0;
                while (n23 < this.gd.length) {
                    n24 += pf.gv(this.gd[n23]);
                    ++n23;
                }
                n22 = n19 + n24 + this.gd.length * 1;
            }
        }
        int n25 = n22;
        if (this.ge != null) {
            n25 = n22;
            if (this.ge.length > 0) {
                int n26 = 0;
                for (int n27 = n; n27 < this.ge.length; ++n27) {
                    n26 += pf.gv(this.ge[n27]);
                }
                n25 = n22 + n26 + this.ge.length * 1;
            }
        }
        return n25;
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
            if (o instanceof c$g) {
                final c$g c$g = (c$g)o;
                b2 = b;
                if (pk.equals(this.fV, c$g.fV)) {
                    b2 = b;
                    if (pk.equals(this.fW, c$g.fW)) {
                        b2 = b;
                        if (pk.equals(this.fX, c$g.fX)) {
                            b2 = b;
                            if (pk.equals(this.fY, c$g.fY)) {
                                b2 = b;
                                if (pk.equals(this.fZ, c$g.fZ)) {
                                    b2 = b;
                                    if (pk.equals(this.ga, c$g.ga)) {
                                        b2 = b;
                                        if (pk.equals(this.gb, c$g.gb)) {
                                            b2 = b;
                                            if (pk.equals(this.gc, c$g.gc)) {
                                                b2 = b;
                                                if (pk.equals(this.gd, c$g.gd)) {
                                                    b2 = b;
                                                    if (pk.equals(this.ge, c$g.ge)) {
                                                        return this.a(c$g);
                                                    }
                                                }
                                            }
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
    
    public c$g h(final pe pe) {
    Label_0193:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0193;
                    }
                    continue;
                }
                case 0: {
                    break Label_0193;
                }
                case 8: {
                    final int b = pp.b(pe, 8);
                    int length;
                    if (this.fV == null) {
                        length = 0;
                    }
                    else {
                        length = this.fV.length;
                    }
                    final int[] fv = new int[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.fV, 0, fv, 0, length);
                        i = length;
                    }
                    while (i < fv.length - 1) {
                        fv[i] = pe.qj();
                        pe.qg();
                        ++i;
                    }
                    fv[i] = pe.qj();
                    this.fV = fv;
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
                    if (this.fV == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.fV.length;
                    }
                    final int[] fv2 = new int[n + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.fV, 0, fv2, 0, length2);
                        j = length2;
                    }
                    while (j < fv2.length) {
                        fv2[j] = pe.qj();
                        ++j;
                    }
                    this.fV = fv2;
                    pe.gp(go);
                    continue;
                }
                case 16: {
                    final int b2 = pp.b(pe, 16);
                    int length3;
                    if (this.fW == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.fW.length;
                    }
                    final int[] fw = new int[b2 + length3];
                    int k = length3;
                    if (length3 != 0) {
                        System.arraycopy(this.fW, 0, fw, 0, length3);
                        k = length3;
                    }
                    while (k < fw.length - 1) {
                        fw[k] = pe.qj();
                        pe.qg();
                        ++k;
                    }
                    fw[k] = pe.qj();
                    this.fW = fw;
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
                    if (this.fW == null) {
                        length4 = 0;
                    }
                    else {
                        length4 = this.fW.length;
                    }
                    final int[] fw2 = new int[n2 + length4];
                    int l = length4;
                    if (length4 != 0) {
                        System.arraycopy(this.fW, 0, fw2, 0, length4);
                        l = length4;
                    }
                    while (l < fw2.length) {
                        fw2[l] = pe.qj();
                        ++l;
                    }
                    this.fW = fw2;
                    pe.gp(go2);
                    continue;
                }
                case 24: {
                    final int b3 = pp.b(pe, 24);
                    int length5;
                    if (this.fX == null) {
                        length5 = 0;
                    }
                    else {
                        length5 = this.fX.length;
                    }
                    final int[] fx = new int[b3 + length5];
                    int n3 = length5;
                    if (length5 != 0) {
                        System.arraycopy(this.fX, 0, fx, 0, length5);
                        n3 = length5;
                    }
                    while (n3 < fx.length - 1) {
                        fx[n3] = pe.qj();
                        pe.qg();
                        ++n3;
                    }
                    fx[n3] = pe.qj();
                    this.fX = fx;
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
                    if (this.fX == null) {
                        length6 = 0;
                    }
                    else {
                        length6 = this.fX.length;
                    }
                    final int[] fx2 = new int[n4 + length6];
                    int n5 = length6;
                    if (length6 != 0) {
                        System.arraycopy(this.fX, 0, fx2, 0, length6);
                        n5 = length6;
                    }
                    while (n5 < fx2.length) {
                        fx2[n5] = pe.qj();
                        ++n5;
                    }
                    this.fX = fx2;
                    pe.gp(go3);
                    continue;
                }
                case 32: {
                    final int b4 = pp.b(pe, 32);
                    int length7;
                    if (this.fY == null) {
                        length7 = 0;
                    }
                    else {
                        length7 = this.fY.length;
                    }
                    final int[] fy = new int[b4 + length7];
                    int n6 = length7;
                    if (length7 != 0) {
                        System.arraycopy(this.fY, 0, fy, 0, length7);
                        n6 = length7;
                    }
                    while (n6 < fy.length - 1) {
                        fy[n6] = pe.qj();
                        pe.qg();
                        ++n6;
                    }
                    fy[n6] = pe.qj();
                    this.fY = fy;
                    continue;
                }
                case 34: {
                    final int go4 = pe.go(pe.qn());
                    final int position4 = pe.getPosition();
                    int n7 = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n7;
                    }
                    pe.gq(position4);
                    int length8;
                    if (this.fY == null) {
                        length8 = 0;
                    }
                    else {
                        length8 = this.fY.length;
                    }
                    final int[] fy2 = new int[n7 + length8];
                    int n8 = length8;
                    if (length8 != 0) {
                        System.arraycopy(this.fY, 0, fy2, 0, length8);
                        n8 = length8;
                    }
                    while (n8 < fy2.length) {
                        fy2[n8] = pe.qj();
                        ++n8;
                    }
                    this.fY = fy2;
                    pe.gp(go4);
                    continue;
                }
                case 40: {
                    final int b5 = pp.b(pe, 40);
                    int length9;
                    if (this.fZ == null) {
                        length9 = 0;
                    }
                    else {
                        length9 = this.fZ.length;
                    }
                    final int[] fz = new int[b5 + length9];
                    int n9 = length9;
                    if (length9 != 0) {
                        System.arraycopy(this.fZ, 0, fz, 0, length9);
                        n9 = length9;
                    }
                    while (n9 < fz.length - 1) {
                        fz[n9] = pe.qj();
                        pe.qg();
                        ++n9;
                    }
                    fz[n9] = pe.qj();
                    this.fZ = fz;
                    continue;
                }
                case 42: {
                    final int go5 = pe.go(pe.qn());
                    final int position5 = pe.getPosition();
                    int n10 = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n10;
                    }
                    pe.gq(position5);
                    int length10;
                    if (this.fZ == null) {
                        length10 = 0;
                    }
                    else {
                        length10 = this.fZ.length;
                    }
                    final int[] fz2 = new int[n10 + length10];
                    int n11 = length10;
                    if (length10 != 0) {
                        System.arraycopy(this.fZ, 0, fz2, 0, length10);
                        n11 = length10;
                    }
                    while (n11 < fz2.length) {
                        fz2[n11] = pe.qj();
                        ++n11;
                    }
                    this.fZ = fz2;
                    pe.gp(go5);
                    continue;
                }
                case 48: {
                    final int b6 = pp.b(pe, 48);
                    int length11;
                    if (this.ga == null) {
                        length11 = 0;
                    }
                    else {
                        length11 = this.ga.length;
                    }
                    final int[] ga = new int[b6 + length11];
                    int n12 = length11;
                    if (length11 != 0) {
                        System.arraycopy(this.ga, 0, ga, 0, length11);
                        n12 = length11;
                    }
                    while (n12 < ga.length - 1) {
                        ga[n12] = pe.qj();
                        pe.qg();
                        ++n12;
                    }
                    ga[n12] = pe.qj();
                    this.ga = ga;
                    continue;
                }
                case 50: {
                    final int go6 = pe.go(pe.qn());
                    final int position6 = pe.getPosition();
                    int n13 = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n13;
                    }
                    pe.gq(position6);
                    int length12;
                    if (this.ga == null) {
                        length12 = 0;
                    }
                    else {
                        length12 = this.ga.length;
                    }
                    final int[] ga2 = new int[n13 + length12];
                    int n14 = length12;
                    if (length12 != 0) {
                        System.arraycopy(this.ga, 0, ga2, 0, length12);
                        n14 = length12;
                    }
                    while (n14 < ga2.length) {
                        ga2[n14] = pe.qj();
                        ++n14;
                    }
                    this.ga = ga2;
                    pe.gp(go6);
                    continue;
                }
                case 56: {
                    final int b7 = pp.b(pe, 56);
                    int length13;
                    if (this.gb == null) {
                        length13 = 0;
                    }
                    else {
                        length13 = this.gb.length;
                    }
                    final int[] gb = new int[b7 + length13];
                    int n15 = length13;
                    if (length13 != 0) {
                        System.arraycopy(this.gb, 0, gb, 0, length13);
                        n15 = length13;
                    }
                    while (n15 < gb.length - 1) {
                        gb[n15] = pe.qj();
                        pe.qg();
                        ++n15;
                    }
                    gb[n15] = pe.qj();
                    this.gb = gb;
                    continue;
                }
                case 58: {
                    final int go7 = pe.go(pe.qn());
                    final int position7 = pe.getPosition();
                    int n16 = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n16;
                    }
                    pe.gq(position7);
                    int length14;
                    if (this.gb == null) {
                        length14 = 0;
                    }
                    else {
                        length14 = this.gb.length;
                    }
                    final int[] gb2 = new int[n16 + length14];
                    int n17 = length14;
                    if (length14 != 0) {
                        System.arraycopy(this.gb, 0, gb2, 0, length14);
                        n17 = length14;
                    }
                    while (n17 < gb2.length) {
                        gb2[n17] = pe.qj();
                        ++n17;
                    }
                    this.gb = gb2;
                    pe.gp(go7);
                    continue;
                }
                case 64: {
                    final int b8 = pp.b(pe, 64);
                    int length15;
                    if (this.gc == null) {
                        length15 = 0;
                    }
                    else {
                        length15 = this.gc.length;
                    }
                    final int[] gc = new int[b8 + length15];
                    int n18 = length15;
                    if (length15 != 0) {
                        System.arraycopy(this.gc, 0, gc, 0, length15);
                        n18 = length15;
                    }
                    while (n18 < gc.length - 1) {
                        gc[n18] = pe.qj();
                        pe.qg();
                        ++n18;
                    }
                    gc[n18] = pe.qj();
                    this.gc = gc;
                    continue;
                }
                case 66: {
                    final int go8 = pe.go(pe.qn());
                    final int position8 = pe.getPosition();
                    int n19 = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n19;
                    }
                    pe.gq(position8);
                    int length16;
                    if (this.gc == null) {
                        length16 = 0;
                    }
                    else {
                        length16 = this.gc.length;
                    }
                    final int[] gc2 = new int[n19 + length16];
                    int n20 = length16;
                    if (length16 != 0) {
                        System.arraycopy(this.gc, 0, gc2, 0, length16);
                        n20 = length16;
                    }
                    while (n20 < gc2.length) {
                        gc2[n20] = pe.qj();
                        ++n20;
                    }
                    this.gc = gc2;
                    pe.gp(go8);
                    continue;
                }
                case 72: {
                    final int b9 = pp.b(pe, 72);
                    int length17;
                    if (this.gd == null) {
                        length17 = 0;
                    }
                    else {
                        length17 = this.gd.length;
                    }
                    final int[] gd = new int[b9 + length17];
                    int n21 = length17;
                    if (length17 != 0) {
                        System.arraycopy(this.gd, 0, gd, 0, length17);
                        n21 = length17;
                    }
                    while (n21 < gd.length - 1) {
                        gd[n21] = pe.qj();
                        pe.qg();
                        ++n21;
                    }
                    gd[n21] = pe.qj();
                    this.gd = gd;
                    continue;
                }
                case 74: {
                    final int go9 = pe.go(pe.qn());
                    final int position9 = pe.getPosition();
                    int n22 = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n22;
                    }
                    pe.gq(position9);
                    int length18;
                    if (this.gd == null) {
                        length18 = 0;
                    }
                    else {
                        length18 = this.gd.length;
                    }
                    final int[] gd2 = new int[n22 + length18];
                    int n23 = length18;
                    if (length18 != 0) {
                        System.arraycopy(this.gd, 0, gd2, 0, length18);
                        n23 = length18;
                    }
                    while (n23 < gd2.length) {
                        gd2[n23] = pe.qj();
                        ++n23;
                    }
                    this.gd = gd2;
                    pe.gp(go9);
                    continue;
                }
                case 80: {
                    final int b10 = pp.b(pe, 80);
                    int length19;
                    if (this.ge == null) {
                        length19 = 0;
                    }
                    else {
                        length19 = this.ge.length;
                    }
                    final int[] ge = new int[b10 + length19];
                    int n24 = length19;
                    if (length19 != 0) {
                        System.arraycopy(this.ge, 0, ge, 0, length19);
                        n24 = length19;
                    }
                    while (n24 < ge.length - 1) {
                        ge[n24] = pe.qj();
                        pe.qg();
                        ++n24;
                    }
                    ge[n24] = pe.qj();
                    this.ge = ge;
                    continue;
                }
                case 82: {
                    final int go10 = pe.go(pe.qn());
                    final int position10 = pe.getPosition();
                    int n25 = 0;
                    while (pe.qs() > 0) {
                        pe.qj();
                        ++n25;
                    }
                    pe.gq(position10);
                    int length20;
                    if (this.ge == null) {
                        length20 = 0;
                    }
                    else {
                        length20 = this.ge.length;
                    }
                    final int[] ge2 = new int[n25 + length20];
                    int n26 = length20;
                    if (length20 != 0) {
                        System.arraycopy(this.ge, 0, ge2, 0, length20);
                        n26 = length20;
                    }
                    while (n26 < ge2.length) {
                        ge2[n26] = pe.qj();
                        ++n26;
                    }
                    this.ge = ge2;
                    pe.gp(go10);
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    public int hashCode() {
        return ((((((((((pk.hashCode(this.fV) + 527) * 31 + pk.hashCode(this.fW)) * 31 + pk.hashCode(this.fX)) * 31 + pk.hashCode(this.fY)) * 31 + pk.hashCode(this.fZ)) * 31 + pk.hashCode(this.ga)) * 31 + pk.hashCode(this.gb)) * 31 + pk.hashCode(this.gc)) * 31 + pk.hashCode(this.gd)) * 31 + pk.hashCode(this.ge)) * 31 + this.qx();
    }
    
    public c$g m() {
        this.fV = pp.awL;
        this.fW = pp.awL;
        this.fX = pp.awL;
        this.fY = pp.awL;
        this.fZ = pp.awL;
        this.ga = pp.awL;
        this.gb = pp.awL;
        this.gc = pp.awL;
        this.gd = pp.awL;
        this.ge = pp.awL;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
}
