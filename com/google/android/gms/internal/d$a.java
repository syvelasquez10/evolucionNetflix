// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class d$a extends pg<d$a>
{
    private static volatile d$a[] gu;
    public String gA;
    public long gB;
    public boolean gC;
    public d$a[] gD;
    public int[] gE;
    public boolean gF;
    public String gv;
    public d$a[] gw;
    public d$a[] gx;
    public d$a[] gy;
    public String gz;
    public int type;
    
    public d$a() {
        this.s();
    }
    
    public static d$a[] r() {
        Label_0027: {
            if (d$a.gu != null) {
                break Label_0027;
            }
            synchronized (pk.awI) {
                if (d$a.gu == null) {
                    d$a.gu = new d$a[0];
                }
                return d$a.gu;
            }
        }
    }
    
    @Override
    public void a(final pf pf) {
        final int n = 0;
        pf.s(1, this.type);
        if (!this.gv.equals("")) {
            pf.b(2, this.gv);
        }
        if (this.gw != null && this.gw.length > 0) {
            for (int i = 0; i < this.gw.length; ++i) {
                final d$a d$a = this.gw[i];
                if (d$a != null) {
                    pf.a(3, d$a);
                }
            }
        }
        if (this.gx != null && this.gx.length > 0) {
            for (int j = 0; j < this.gx.length; ++j) {
                final d$a d$a2 = this.gx[j];
                if (d$a2 != null) {
                    pf.a(4, d$a2);
                }
            }
        }
        if (this.gy != null && this.gy.length > 0) {
            for (int k = 0; k < this.gy.length; ++k) {
                final d$a d$a3 = this.gy[k];
                if (d$a3 != null) {
                    pf.a(5, d$a3);
                }
            }
        }
        if (!this.gz.equals("")) {
            pf.b(6, this.gz);
        }
        if (!this.gA.equals("")) {
            pf.b(7, this.gA);
        }
        if (this.gB != 0L) {
            pf.b(8, this.gB);
        }
        if (this.gF) {
            pf.b(9, this.gF);
        }
        if (this.gE != null && this.gE.length > 0) {
            for (int l = 0; l < this.gE.length; ++l) {
                pf.s(10, this.gE[l]);
            }
        }
        if (this.gD != null && this.gD.length > 0) {
            for (int n2 = n; n2 < this.gD.length; ++n2) {
                final d$a d$a4 = this.gD[n2];
                if (d$a4 != null) {
                    pf.a(11, d$a4);
                }
            }
        }
        if (this.gC) {
            pf.b(12, this.gC);
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        final int n = 0;
        int n3;
        final int n2 = n3 = super.c() + pf.u(1, this.type);
        if (!this.gv.equals("")) {
            n3 = n2 + pf.j(2, this.gv);
        }
        int n4 = n3;
        if (this.gw != null) {
            n4 = n3;
            if (this.gw.length > 0) {
                int n5;
                for (int i = 0; i < this.gw.length; ++i, n3 = n5) {
                    final d$a d$a = this.gw[i];
                    n5 = n3;
                    if (d$a != null) {
                        n5 = n3 + pf.c(3, d$a);
                    }
                }
                n4 = n3;
            }
        }
        int n6 = n4;
        if (this.gx != null) {
            n6 = n4;
            if (this.gx.length > 0) {
                n6 = n4;
                int n7;
                for (int j = 0; j < this.gx.length; ++j, n6 = n7) {
                    final d$a d$a2 = this.gx[j];
                    n7 = n6;
                    if (d$a2 != null) {
                        n7 = n6 + pf.c(4, d$a2);
                    }
                }
            }
        }
        int n8 = n6;
        if (this.gy != null) {
            n8 = n6;
            if (this.gy.length > 0) {
                int n9;
                for (int k = 0; k < this.gy.length; ++k, n6 = n9) {
                    final d$a d$a3 = this.gy[k];
                    n9 = n6;
                    if (d$a3 != null) {
                        n9 = n6 + pf.c(5, d$a3);
                    }
                }
                n8 = n6;
            }
        }
        int n10 = n8;
        if (!this.gz.equals("")) {
            n10 = n8 + pf.j(6, this.gz);
        }
        int n11 = n10;
        if (!this.gA.equals("")) {
            n11 = n10 + pf.j(7, this.gA);
        }
        int n12 = n11;
        if (this.gB != 0L) {
            n12 = n11 + pf.d(8, this.gB);
        }
        int n13 = n12;
        if (this.gF) {
            n13 = n12 + pf.c(9, this.gF);
        }
        int n14 = n13;
        if (this.gE != null) {
            n14 = n13;
            if (this.gE.length > 0) {
                int l = 0;
                int n15 = 0;
                while (l < this.gE.length) {
                    n15 += pf.gv(this.gE[l]);
                    ++l;
                }
                n14 = n13 + n15 + this.gE.length * 1;
            }
        }
        int n16 = n14;
        if (this.gD != null) {
            n16 = n14;
            if (this.gD.length > 0) {
                int n17 = n;
                while (true) {
                    n16 = n14;
                    if (n17 >= this.gD.length) {
                        break;
                    }
                    final d$a d$a4 = this.gD[n17];
                    int n18 = n14;
                    if (d$a4 != null) {
                        n18 = n14 + pf.c(11, d$a4);
                    }
                    ++n17;
                    n14 = n18;
                }
            }
        }
        int n19 = n16;
        if (this.gC) {
            n19 = n16 + pf.c(12, this.gC);
        }
        return n19;
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
            if (o instanceof d$a) {
                final d$a d$a = (d$a)o;
                b2 = b;
                if (this.type == d$a.type) {
                    if (this.gv == null) {
                        b2 = b;
                        if (d$a.gv != null) {
                            return b2;
                        }
                    }
                    else if (!this.gv.equals(d$a.gv)) {
                        return false;
                    }
                    b2 = b;
                    if (pk.equals(this.gw, d$a.gw)) {
                        b2 = b;
                        if (pk.equals(this.gx, d$a.gx)) {
                            b2 = b;
                            if (pk.equals(this.gy, d$a.gy)) {
                                if (this.gz == null) {
                                    b2 = b;
                                    if (d$a.gz != null) {
                                        return b2;
                                    }
                                }
                                else if (!this.gz.equals(d$a.gz)) {
                                    return false;
                                }
                                if (this.gA == null) {
                                    b2 = b;
                                    if (d$a.gA != null) {
                                        return b2;
                                    }
                                }
                                else if (!this.gA.equals(d$a.gA)) {
                                    return false;
                                }
                                b2 = b;
                                if (this.gB == d$a.gB) {
                                    b2 = b;
                                    if (this.gC == d$a.gC) {
                                        b2 = b;
                                        if (pk.equals(this.gD, d$a.gD)) {
                                            b2 = b;
                                            if (pk.equals(this.gE, d$a.gE)) {
                                                b2 = b;
                                                if (this.gF == d$a.gF) {
                                                    return this.a(d$a);
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
    
    @Override
    public int hashCode() {
        int n = 1231;
        int hashCode = 0;
        final int type = this.type;
        int hashCode2;
        if (this.gv == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.gv.hashCode();
        }
        final int hashCode3 = pk.hashCode(this.gw);
        final int hashCode4 = pk.hashCode(this.gx);
        final int hashCode5 = pk.hashCode(this.gy);
        int hashCode6;
        if (this.gz == null) {
            hashCode6 = 0;
        }
        else {
            hashCode6 = this.gz.hashCode();
        }
        if (this.gA != null) {
            hashCode = this.gA.hashCode();
        }
        final int n2 = (int)(this.gB ^ this.gB >>> 32);
        int n3;
        if (this.gC) {
            n3 = 1231;
        }
        else {
            n3 = 1237;
        }
        final int hashCode7 = pk.hashCode(this.gD);
        final int hashCode8 = pk.hashCode(this.gE);
        if (!this.gF) {
            n = 1237;
        }
        return ((((n3 + (((hashCode6 + ((((hashCode2 + (type + 527) * 31) * 31 + hashCode3) * 31 + hashCode4) * 31 + hashCode5) * 31) * 31 + hashCode) * 31 + n2) * 31) * 31 + hashCode7) * 31 + hashCode8) * 31 + n) * 31 + this.qx();
    }
    
    public d$a l(final pe pe) {
    Label_0137:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0137;
                    }
                    continue;
                }
                case 0: {
                    break Label_0137;
                }
                case 8: {
                    final int qj = pe.qj();
                    switch (qj) {
                        default: {
                            continue;
                        }
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8: {
                            this.type = qj;
                            continue;
                        }
                    }
                    break;
                }
                case 18: {
                    this.gv = pe.readString();
                    continue;
                }
                case 26: {
                    final int b = pp.b(pe, 26);
                    int length;
                    if (this.gw == null) {
                        length = 0;
                    }
                    else {
                        length = this.gw.length;
                    }
                    final d$a[] gw = new d$a[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.gw, 0, gw, 0, length);
                        i = length;
                    }
                    while (i < gw.length - 1) {
                        pe.a(gw[i] = new d$a());
                        pe.qg();
                        ++i;
                    }
                    pe.a(gw[i] = new d$a());
                    this.gw = gw;
                    continue;
                }
                case 34: {
                    final int b2 = pp.b(pe, 34);
                    int length2;
                    if (this.gx == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.gx.length;
                    }
                    final d$a[] gx = new d$a[b2 + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.gx, 0, gx, 0, length2);
                        j = length2;
                    }
                    while (j < gx.length - 1) {
                        pe.a(gx[j] = new d$a());
                        pe.qg();
                        ++j;
                    }
                    pe.a(gx[j] = new d$a());
                    this.gx = gx;
                    continue;
                }
                case 42: {
                    final int b3 = pp.b(pe, 42);
                    int length3;
                    if (this.gy == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.gy.length;
                    }
                    final d$a[] gy = new d$a[b3 + length3];
                    int k = length3;
                    if (length3 != 0) {
                        System.arraycopy(this.gy, 0, gy, 0, length3);
                        k = length3;
                    }
                    while (k < gy.length - 1) {
                        pe.a(gy[k] = new d$a());
                        pe.qg();
                        ++k;
                    }
                    pe.a(gy[k] = new d$a());
                    this.gy = gy;
                    continue;
                }
                case 50: {
                    this.gz = pe.readString();
                    continue;
                }
                case 58: {
                    this.gA = pe.readString();
                    continue;
                }
                case 64: {
                    this.gB = pe.qi();
                    continue;
                }
                case 72: {
                    this.gF = pe.qk();
                    continue;
                }
                case 80: {
                    final int b4 = pp.b(pe, 80);
                    final int[] ge = new int[b4];
                    int l = 0;
                    int n = 0;
                    while (l < b4) {
                        if (l != 0) {
                            pe.qg();
                        }
                        final int qj2 = pe.qj();
                        switch (qj2) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17: {
                                final int n2 = n + 1;
                                ge[n] = qj2;
                                n = n2;
                                break;
                            }
                        }
                        ++l;
                    }
                    if (n == 0) {
                        continue;
                    }
                    int length4;
                    if (this.gE == null) {
                        length4 = 0;
                    }
                    else {
                        length4 = this.gE.length;
                    }
                    if (length4 == 0 && n == ge.length) {
                        this.gE = ge;
                        continue;
                    }
                    final int[] ge2 = new int[length4 + n];
                    if (length4 != 0) {
                        System.arraycopy(this.gE, 0, ge2, 0, length4);
                    }
                    System.arraycopy(ge, 0, ge2, length4, n);
                    this.gE = ge2;
                    continue;
                }
                case 82: {
                    final int go = pe.go(pe.qn());
                    final int position = pe.getPosition();
                    int n3 = 0;
                    while (pe.qs() > 0) {
                        switch (pe.qj()) {
                            default: {
                                continue;
                            }
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17: {
                                ++n3;
                                continue;
                            }
                        }
                    }
                    if (n3 != 0) {
                        pe.gq(position);
                        int length5;
                        if (this.gE == null) {
                            length5 = 0;
                        }
                        else {
                            length5 = this.gE.length;
                        }
                        final int[] ge3 = new int[n3 + length5];
                        int n4 = length5;
                        if (length5 != 0) {
                            System.arraycopy(this.gE, 0, ge3, 0, length5);
                            n4 = length5;
                        }
                        while (pe.qs() > 0) {
                            final int qj3 = pe.qj();
                            switch (qj3) {
                                default: {
                                    continue;
                                }
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17: {
                                    ge3[n4] = qj3;
                                    ++n4;
                                    continue;
                                }
                            }
                        }
                        this.gE = ge3;
                    }
                    pe.gp(go);
                    continue;
                }
                case 90: {
                    final int b5 = pp.b(pe, 90);
                    int length6;
                    if (this.gD == null) {
                        length6 = 0;
                    }
                    else {
                        length6 = this.gD.length;
                    }
                    final d$a[] gd = new d$a[b5 + length6];
                    int n5 = length6;
                    if (length6 != 0) {
                        System.arraycopy(this.gD, 0, gd, 0, length6);
                        n5 = length6;
                    }
                    while (n5 < gd.length - 1) {
                        pe.a(gd[n5] = new d$a());
                        pe.qg();
                        ++n5;
                    }
                    pe.a(gd[n5] = new d$a());
                    this.gD = gd;
                    continue;
                }
                case 96: {
                    this.gC = pe.qk();
                    continue;
                }
            }
        }
        return this;
    }
    
    public d$a s() {
        this.type = 1;
        this.gv = "";
        this.gw = r();
        this.gx = r();
        this.gy = r();
        this.gz = "";
        this.gA = "";
        this.gB = 0L;
        this.gC = false;
        this.gD = r();
        this.gE = pp.awL;
        this.gF = false;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
}
