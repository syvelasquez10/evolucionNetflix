// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;

public final class pq$c extends pg<pq$c>
{
    public long awY;
    public int awZ;
    public int axa;
    public boolean axb;
    public pq$d[] axc;
    public pq$b axd;
    public byte[] axe;
    public byte[] axf;
    public byte[] axg;
    public pq$a axh;
    public String axi;
    public String tag;
    
    public pq$c() {
        this.qJ();
    }
    
    @Override
    public void a(final pf pf) {
        if (this.awY != 0L) {
            pf.b(1, this.awY);
        }
        if (!this.tag.equals("")) {
            pf.b(2, this.tag);
        }
        if (this.axc != null && this.axc.length > 0) {
            for (int i = 0; i < this.axc.length; ++i) {
                final pq$d pq$d = this.axc[i];
                if (pq$d != null) {
                    pf.a(3, pq$d);
                }
            }
        }
        if (!Arrays.equals(this.axe, pp.awS)) {
            pf.a(6, this.axe);
        }
        if (this.axh != null) {
            pf.a(7, this.axh);
        }
        if (!Arrays.equals(this.axf, pp.awS)) {
            pf.a(8, this.axf);
        }
        if (this.axd != null) {
            pf.a(9, this.axd);
        }
        if (this.axb) {
            pf.b(10, this.axb);
        }
        if (this.awZ != 0) {
            pf.s(11, this.awZ);
        }
        if (this.axa != 0) {
            pf.s(12, this.axa);
        }
        if (!Arrays.equals(this.axg, pp.awS)) {
            pf.a(13, this.axg);
        }
        if (!this.axi.equals("")) {
            pf.b(14, this.axi);
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        int c;
        final int n = c = super.c();
        if (this.awY != 0L) {
            c = n + pf.d(1, this.awY);
        }
        int n2 = c;
        if (!this.tag.equals("")) {
            n2 = c + pf.j(2, this.tag);
        }
        int n3 = n2;
        if (this.axc != null) {
            n3 = n2;
            if (this.axc.length > 0) {
                int n4;
                for (int i = 0; i < this.axc.length; ++i, n2 = n4) {
                    final pq$d pq$d = this.axc[i];
                    n4 = n2;
                    if (pq$d != null) {
                        n4 = n2 + pf.c(3, pq$d);
                    }
                }
                n3 = n2;
            }
        }
        int n5 = n3;
        if (!Arrays.equals(this.axe, pp.awS)) {
            n5 = n3 + pf.b(6, this.axe);
        }
        int n6 = n5;
        if (this.axh != null) {
            n6 = n5 + pf.c(7, this.axh);
        }
        int n7 = n6;
        if (!Arrays.equals(this.axf, pp.awS)) {
            n7 = n6 + pf.b(8, this.axf);
        }
        int n8 = n7;
        if (this.axd != null) {
            n8 = n7 + pf.c(9, this.axd);
        }
        int n9 = n8;
        if (this.axb) {
            n9 = n8 + pf.c(10, this.axb);
        }
        int n10 = n9;
        if (this.awZ != 0) {
            n10 = n9 + pf.u(11, this.awZ);
        }
        int n11 = n10;
        if (this.axa != 0) {
            n11 = n10 + pf.u(12, this.axa);
        }
        int n12 = n11;
        if (!Arrays.equals(this.axg, pp.awS)) {
            n12 = n11 + pf.b(13, this.axg);
        }
        int n13 = n12;
        if (!this.axi.equals("")) {
            n13 = n12 + pf.j(14, this.axi);
        }
        return n13;
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
            if (o instanceof pq$c) {
                final pq$c pq$c = (pq$c)o;
                b2 = b;
                if (this.awY == pq$c.awY) {
                    if (this.tag == null) {
                        b2 = b;
                        if (pq$c.tag != null) {
                            return b2;
                        }
                    }
                    else if (!this.tag.equals(pq$c.tag)) {
                        return false;
                    }
                    b2 = b;
                    if (this.awZ == pq$c.awZ) {
                        b2 = b;
                        if (this.axa == pq$c.axa) {
                            b2 = b;
                            if (this.axb == pq$c.axb) {
                                b2 = b;
                                if (pk.equals(this.axc, pq$c.axc)) {
                                    if (this.axd == null) {
                                        b2 = b;
                                        if (pq$c.axd != null) {
                                            return b2;
                                        }
                                    }
                                    else if (!this.axd.equals(pq$c.axd)) {
                                        return false;
                                    }
                                    b2 = b;
                                    if (Arrays.equals(this.axe, pq$c.axe)) {
                                        b2 = b;
                                        if (Arrays.equals(this.axf, pq$c.axf)) {
                                            b2 = b;
                                            if (Arrays.equals(this.axg, pq$c.axg)) {
                                                if (this.axh == null) {
                                                    b2 = b;
                                                    if (pq$c.axh != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.axh.equals(pq$c.axh)) {
                                                    return false;
                                                }
                                                if (this.axi == null) {
                                                    b2 = b;
                                                    if (pq$c.axi != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.axi.equals(pq$c.axi)) {
                                                    return false;
                                                }
                                                return this.a(pq$c);
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
        int hashCode = 0;
        final int n = (int)(this.awY ^ this.awY >>> 32);
        int hashCode2;
        if (this.tag == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.tag.hashCode();
        }
        final int awZ = this.awZ;
        final int axa = this.axa;
        int n2;
        if (this.axb) {
            n2 = 1231;
        }
        else {
            n2 = 1237;
        }
        final int hashCode3 = pk.hashCode(this.axc);
        int hashCode4;
        if (this.axd == null) {
            hashCode4 = 0;
        }
        else {
            hashCode4 = this.axd.hashCode();
        }
        final int hashCode5 = Arrays.hashCode(this.axe);
        final int hashCode6 = Arrays.hashCode(this.axf);
        final int hashCode7 = Arrays.hashCode(this.axg);
        int hashCode8;
        if (this.axh == null) {
            hashCode8 = 0;
        }
        else {
            hashCode8 = this.axh.hashCode();
        }
        if (this.axi != null) {
            hashCode = this.axi.hashCode();
        }
        return ((hashCode8 + ((((hashCode4 + ((n2 + (((hashCode2 + (n + 527) * 31) * 31 + awZ) * 31 + axa) * 31) * 31 + hashCode3) * 31) * 31 + hashCode5) * 31 + hashCode6) * 31 + hashCode7) * 31) * 31 + hashCode) * 31 + this.qx();
    }
    
    public pq$c qJ() {
        this.awY = 0L;
        this.tag = "";
        this.awZ = 0;
        this.axa = 0;
        this.axb = false;
        this.axc = pq$d.qK();
        this.axd = null;
        this.axe = pp.awS;
        this.axf = pp.awS;
        this.axg = pp.awS;
        this.axh = null;
        this.axi = "";
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    public pq$c x(final pe pe) {
    Label_0129:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0129;
                    }
                    continue;
                }
                case 0: {
                    break Label_0129;
                }
                case 8: {
                    this.awY = pe.qi();
                    continue;
                }
                case 18: {
                    this.tag = pe.readString();
                    continue;
                }
                case 26: {
                    final int b = pp.b(pe, 26);
                    int length;
                    if (this.axc == null) {
                        length = 0;
                    }
                    else {
                        length = this.axc.length;
                    }
                    final pq$d[] axc = new pq$d[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.axc, 0, axc, 0, length);
                        i = length;
                    }
                    while (i < axc.length - 1) {
                        pe.a(axc[i] = new pq$d());
                        pe.qg();
                        ++i;
                    }
                    pe.a(axc[i] = new pq$d());
                    this.axc = axc;
                    continue;
                }
                case 50: {
                    this.axe = pe.readBytes();
                    continue;
                }
                case 58: {
                    if (this.axh == null) {
                        this.axh = new pq$a();
                    }
                    pe.a(this.axh);
                    continue;
                }
                case 66: {
                    this.axf = pe.readBytes();
                    continue;
                }
                case 74: {
                    if (this.axd == null) {
                        this.axd = new pq$b();
                    }
                    pe.a(this.axd);
                    continue;
                }
                case 80: {
                    this.axb = pe.qk();
                    continue;
                }
                case 88: {
                    this.awZ = pe.qj();
                    continue;
                }
                case 96: {
                    this.axa = pe.qj();
                    continue;
                }
                case 106: {
                    this.axg = pe.readBytes();
                    continue;
                }
                case 114: {
                    this.axi = pe.readString();
                    continue;
                }
            }
        }
        return this;
    }
}
