// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;

public final class pc$a$a$a extends pg<pc$a$a$a>
{
    public byte[] avX;
    public String avY;
    public double avZ;
    public float awa;
    public long awb;
    public int awc;
    public int awd;
    public boolean awe;
    public pc$a[] awf;
    public pc$a$a[] awg;
    public String[] awh;
    public long[] awi;
    public float[] awj;
    public long awk;
    
    public pc$a$a$a() {
        this.qf();
    }
    
    @Override
    public void a(final pf pf) {
        final int n = 0;
        if (!Arrays.equals(this.avX, pp.awS)) {
            pf.a(1, this.avX);
        }
        if (!this.avY.equals("")) {
            pf.b(2, this.avY);
        }
        if (Double.doubleToLongBits(this.avZ) != Double.doubleToLongBits(0.0)) {
            pf.a(3, this.avZ);
        }
        if (Float.floatToIntBits(this.awa) != Float.floatToIntBits(0.0f)) {
            pf.b(4, this.awa);
        }
        if (this.awb != 0L) {
            pf.b(5, this.awb);
        }
        if (this.awc != 0) {
            pf.s(6, this.awc);
        }
        if (this.awd != 0) {
            pf.t(7, this.awd);
        }
        if (this.awe) {
            pf.b(8, this.awe);
        }
        if (this.awf != null && this.awf.length > 0) {
            for (int i = 0; i < this.awf.length; ++i) {
                final pc$a pc$a = this.awf[i];
                if (pc$a != null) {
                    pf.a(9, pc$a);
                }
            }
        }
        if (this.awg != null && this.awg.length > 0) {
            for (int j = 0; j < this.awg.length; ++j) {
                final pc$a$a pc$a$a = this.awg[j];
                if (pc$a$a != null) {
                    pf.a(10, pc$a$a);
                }
            }
        }
        if (this.awh != null && this.awh.length > 0) {
            for (int k = 0; k < this.awh.length; ++k) {
                final String s = this.awh[k];
                if (s != null) {
                    pf.b(11, s);
                }
            }
        }
        if (this.awi != null && this.awi.length > 0) {
            for (int l = 0; l < this.awi.length; ++l) {
                pf.b(12, this.awi[l]);
            }
        }
        if (this.awk != 0L) {
            pf.b(13, this.awk);
        }
        if (this.awj != null && this.awj.length > 0) {
            for (int n2 = n; n2 < this.awj.length; ++n2) {
                pf.b(14, this.awj[n2]);
            }
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        final int n = 0;
        int c;
        final int n2 = c = super.c();
        if (!Arrays.equals(this.avX, pp.awS)) {
            c = n2 + pf.b(1, this.avX);
        }
        int n3 = c;
        if (!this.avY.equals("")) {
            n3 = c + pf.j(2, this.avY);
        }
        int n4 = n3;
        if (Double.doubleToLongBits(this.avZ) != Double.doubleToLongBits(0.0)) {
            n4 = n3 + pf.b(3, this.avZ);
        }
        int n5 = n4;
        if (Float.floatToIntBits(this.awa) != Float.floatToIntBits(0.0f)) {
            n5 = n4 + pf.c(4, this.awa);
        }
        int n6 = n5;
        if (this.awb != 0L) {
            n6 = n5 + pf.d(5, this.awb);
        }
        int n7 = n6;
        if (this.awc != 0) {
            n7 = n6 + pf.u(6, this.awc);
        }
        int n8 = n7;
        if (this.awd != 0) {
            n8 = n7 + pf.v(7, this.awd);
        }
        int n9 = n8;
        if (this.awe) {
            n9 = n8 + pf.c(8, this.awe);
        }
        int n10 = n9;
        if (this.awf != null) {
            n10 = n9;
            if (this.awf.length > 0) {
                int n11;
                for (int i = 0; i < this.awf.length; ++i, n9 = n11) {
                    final pc$a pc$a = this.awf[i];
                    n11 = n9;
                    if (pc$a != null) {
                        n11 = n9 + pf.c(9, pc$a);
                    }
                }
                n10 = n9;
            }
        }
        int n12 = n10;
        if (this.awg != null) {
            n12 = n10;
            if (this.awg.length > 0) {
                n12 = n10;
                int n13;
                for (int j = 0; j < this.awg.length; ++j, n12 = n13) {
                    final pc$a$a pc$a$a = this.awg[j];
                    n13 = n12;
                    if (pc$a$a != null) {
                        n13 = n12 + pf.c(10, pc$a$a);
                    }
                }
            }
        }
        int n14 = n12;
        if (this.awh != null) {
            n14 = n12;
            if (this.awh.length > 0) {
                int k = 0;
                int n15 = 0;
                int n16 = 0;
                while (k < this.awh.length) {
                    final String s = this.awh[k];
                    int n17 = n15;
                    int n18 = n16;
                    if (s != null) {
                        n18 = n16 + 1;
                        n17 = n15 + pf.df(s);
                    }
                    ++k;
                    n15 = n17;
                    n16 = n18;
                }
                n14 = n12 + n15 + n16 * 1;
            }
        }
        int n19 = n14;
        if (this.awi != null) {
            n19 = n14;
            if (this.awi.length > 0) {
                int n20 = 0;
                for (int l = n; l < this.awi.length; ++l) {
                    n20 += pf.D(this.awi[l]);
                }
                n19 = n14 + n20 + this.awi.length * 1;
            }
        }
        int n21 = n19;
        if (this.awk != 0L) {
            n21 = n19 + pf.d(13, this.awk);
        }
        int n22 = n21;
        if (this.awj != null) {
            n22 = n21;
            if (this.awj.length > 0) {
                n22 = n21 + this.awj.length * 4 + this.awj.length * 1;
            }
        }
        return n22;
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
            if (o instanceof pc$a$a$a) {
                final pc$a$a$a pc$a$a$a = (pc$a$a$a)o;
                b2 = b;
                if (Arrays.equals(this.avX, pc$a$a$a.avX)) {
                    if (this.avY == null) {
                        b2 = b;
                        if (pc$a$a$a.avY != null) {
                            return b2;
                        }
                    }
                    else if (!this.avY.equals(pc$a$a$a.avY)) {
                        return false;
                    }
                    b2 = b;
                    if (Double.doubleToLongBits(this.avZ) == Double.doubleToLongBits(pc$a$a$a.avZ)) {
                        b2 = b;
                        if (Float.floatToIntBits(this.awa) == Float.floatToIntBits(pc$a$a$a.awa)) {
                            b2 = b;
                            if (this.awb == pc$a$a$a.awb) {
                                b2 = b;
                                if (this.awc == pc$a$a$a.awc) {
                                    b2 = b;
                                    if (this.awd == pc$a$a$a.awd) {
                                        b2 = b;
                                        if (this.awe == pc$a$a$a.awe) {
                                            b2 = b;
                                            if (pk.equals(this.awf, pc$a$a$a.awf)) {
                                                b2 = b;
                                                if (pk.equals(this.awg, pc$a$a$a.awg)) {
                                                    b2 = b;
                                                    if (pk.equals(this.awh, pc$a$a$a.awh)) {
                                                        b2 = b;
                                                        if (pk.equals(this.awi, pc$a$a$a.awi)) {
                                                            b2 = b;
                                                            if (pk.equals(this.awj, pc$a$a$a.awj)) {
                                                                b2 = b;
                                                                if (this.awk == pc$a$a$a.awk) {
                                                                    return this.a(pc$a$a$a);
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
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = Arrays.hashCode(this.avX);
        int hashCode2;
        if (this.avY == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.avY.hashCode();
        }
        final long doubleToLongBits = Double.doubleToLongBits(this.avZ);
        final int n = (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        final int floatToIntBits = Float.floatToIntBits(this.awa);
        final int n2 = (int)(this.awb ^ this.awb >>> 32);
        final int awc = this.awc;
        final int awd = this.awd;
        int n3;
        if (this.awe) {
            n3 = 1231;
        }
        else {
            n3 = 1237;
        }
        return (((((((n3 + ((((((hashCode2 + (hashCode + 527) * 31) * 31 + n) * 31 + floatToIntBits) * 31 + n2) * 31 + awc) * 31 + awd) * 31) * 31 + pk.hashCode(this.awf)) * 31 + pk.hashCode(this.awg)) * 31 + pk.hashCode(this.awh)) * 31 + pk.hashCode(this.awi)) * 31 + pk.hashCode(this.awj)) * 31 + (int)(this.awk ^ this.awk >>> 32)) * 31 + this.qx();
    }
    
    public pc$a$a$a qf() {
        this.avX = pp.awS;
        this.avY = "";
        this.avZ = 0.0;
        this.awa = 0.0f;
        this.awb = 0L;
        this.awc = 0;
        this.awd = 0;
        this.awe = false;
        this.awf = pc$a.qb();
        this.awg = pc$a$a.qd();
        this.awh = pp.awQ;
        this.awi = pp.awM;
        this.awj = pp.awN;
        this.awk = 0L;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    public pc$a$a$a t(final pe pe) {
    Label_0161:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0161;
                    }
                    continue;
                }
                case 0: {
                    break Label_0161;
                }
                case 10: {
                    this.avX = pe.readBytes();
                    continue;
                }
                case 18: {
                    this.avY = pe.readString();
                    continue;
                }
                case 25: {
                    this.avZ = pe.readDouble();
                    continue;
                }
                case 37: {
                    this.awa = pe.readFloat();
                    continue;
                }
                case 40: {
                    this.awb = pe.qi();
                    continue;
                }
                case 48: {
                    this.awc = pe.qj();
                    continue;
                }
                case 56: {
                    this.awd = pe.ql();
                    continue;
                }
                case 64: {
                    this.awe = pe.qk();
                    continue;
                }
                case 74: {
                    final int b = pp.b(pe, 74);
                    int length;
                    if (this.awf == null) {
                        length = 0;
                    }
                    else {
                        length = this.awf.length;
                    }
                    final pc$a[] awf = new pc$a[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.awf, 0, awf, 0, length);
                        i = length;
                    }
                    while (i < awf.length - 1) {
                        pe.a(awf[i] = new pc$a());
                        pe.qg();
                        ++i;
                    }
                    pe.a(awf[i] = new pc$a());
                    this.awf = awf;
                    continue;
                }
                case 82: {
                    final int b2 = pp.b(pe, 82);
                    int length2;
                    if (this.awg == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.awg.length;
                    }
                    final pc$a$a[] awg = new pc$a$a[b2 + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.awg, 0, awg, 0, length2);
                        j = length2;
                    }
                    while (j < awg.length - 1) {
                        pe.a(awg[j] = new pc$a$a());
                        pe.qg();
                        ++j;
                    }
                    pe.a(awg[j] = new pc$a$a());
                    this.awg = awg;
                    continue;
                }
                case 90: {
                    final int b3 = pp.b(pe, 90);
                    int length3;
                    if (this.awh == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.awh.length;
                    }
                    final String[] awh = new String[b3 + length3];
                    int k = length3;
                    if (length3 != 0) {
                        System.arraycopy(this.awh, 0, awh, 0, length3);
                        k = length3;
                    }
                    while (k < awh.length - 1) {
                        awh[k] = pe.readString();
                        pe.qg();
                        ++k;
                    }
                    awh[k] = pe.readString();
                    this.awh = awh;
                    continue;
                }
                case 96: {
                    final int b4 = pp.b(pe, 96);
                    int length4;
                    if (this.awi == null) {
                        length4 = 0;
                    }
                    else {
                        length4 = this.awi.length;
                    }
                    final long[] awi = new long[b4 + length4];
                    int l = length4;
                    if (length4 != 0) {
                        System.arraycopy(this.awi, 0, awi, 0, length4);
                        l = length4;
                    }
                    while (l < awi.length - 1) {
                        awi[l] = pe.qi();
                        pe.qg();
                        ++l;
                    }
                    awi[l] = pe.qi();
                    this.awi = awi;
                    continue;
                }
                case 98: {
                    final int go = pe.go(pe.qn());
                    final int position = pe.getPosition();
                    int n = 0;
                    while (pe.qs() > 0) {
                        pe.qi();
                        ++n;
                    }
                    pe.gq(position);
                    int length5;
                    if (this.awi == null) {
                        length5 = 0;
                    }
                    else {
                        length5 = this.awi.length;
                    }
                    final long[] awi2 = new long[n + length5];
                    int n2 = length5;
                    if (length5 != 0) {
                        System.arraycopy(this.awi, 0, awi2, 0, length5);
                        n2 = length5;
                    }
                    while (n2 < awi2.length) {
                        awi2[n2] = pe.qi();
                        ++n2;
                    }
                    this.awi = awi2;
                    pe.gp(go);
                    continue;
                }
                case 104: {
                    this.awk = pe.qi();
                    continue;
                }
                case 117: {
                    final int b5 = pp.b(pe, 117);
                    int length6;
                    if (this.awj == null) {
                        length6 = 0;
                    }
                    else {
                        length6 = this.awj.length;
                    }
                    final float[] awj = new float[b5 + length6];
                    int n3 = length6;
                    if (length6 != 0) {
                        System.arraycopy(this.awj, 0, awj, 0, length6);
                        n3 = length6;
                    }
                    while (n3 < awj.length - 1) {
                        awj[n3] = pe.readFloat();
                        pe.qg();
                        ++n3;
                    }
                    awj[n3] = pe.readFloat();
                    this.awj = awj;
                    continue;
                }
                case 114: {
                    final int qn = pe.qn();
                    final int go2 = pe.go(qn);
                    final int n4 = qn / 4;
                    int length7;
                    if (this.awj == null) {
                        length7 = 0;
                    }
                    else {
                        length7 = this.awj.length;
                    }
                    final float[] awj2 = new float[n4 + length7];
                    int n5 = length7;
                    if (length7 != 0) {
                        System.arraycopy(this.awj, 0, awj2, 0, length7);
                        n5 = length7;
                    }
                    while (n5 < awj2.length) {
                        awj2[n5] = pe.readFloat();
                        ++n5;
                    }
                    this.awj = awj2;
                    pe.gp(go2);
                    continue;
                }
            }
        }
        return this;
    }
}
