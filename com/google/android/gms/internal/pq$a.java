// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class pq$a extends pg<pq$a>
{
    public String[] awT;
    public String[] awU;
    public int[] awV;
    
    public pq$a() {
        this.qH();
    }
    
    @Override
    public void a(final pf pf) {
        final int n = 0;
        if (this.awT != null && this.awT.length > 0) {
            for (int i = 0; i < this.awT.length; ++i) {
                final String s = this.awT[i];
                if (s != null) {
                    pf.b(1, s);
                }
            }
        }
        if (this.awU != null && this.awU.length > 0) {
            for (int j = 0; j < this.awU.length; ++j) {
                final String s2 = this.awU[j];
                if (s2 != null) {
                    pf.b(2, s2);
                }
            }
        }
        if (this.awV != null && this.awV.length > 0) {
            for (int k = n; k < this.awV.length; ++k) {
                pf.s(3, this.awV[k]);
            }
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        final int n = 0;
        final int c = super.c();
        int n6;
        if (this.awT != null && this.awT.length > 0) {
            int i = 0;
            int n2 = 0;
            int n3 = 0;
            while (i < this.awT.length) {
                final String s = this.awT[i];
                int n4 = n2;
                int n5 = n3;
                if (s != null) {
                    n5 = n3 + 1;
                    n4 = n2 + pf.df(s);
                }
                ++i;
                n2 = n4;
                n3 = n5;
            }
            n6 = c + n2 + n3 * 1;
        }
        else {
            n6 = c;
        }
        int n7 = n6;
        if (this.awU != null) {
            n7 = n6;
            if (this.awU.length > 0) {
                int j = 0;
                int n8 = 0;
                int n9 = 0;
                while (j < this.awU.length) {
                    final String s2 = this.awU[j];
                    int n10 = n8;
                    int n11 = n9;
                    if (s2 != null) {
                        n11 = n9 + 1;
                        n10 = n8 + pf.df(s2);
                    }
                    ++j;
                    n8 = n10;
                    n9 = n11;
                }
                n7 = n6 + n8 + n9 * 1;
            }
        }
        int n12 = n7;
        if (this.awV != null) {
            n12 = n7;
            if (this.awV.length > 0) {
                int n13 = 0;
                for (int k = n; k < this.awV.length; ++k) {
                    n13 += pf.gv(this.awV[k]);
                }
                n12 = n7 + n13 + this.awV.length * 1;
            }
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
            if (o instanceof pq$a) {
                final pq$a pq$a = (pq$a)o;
                b2 = b;
                if (pk.equals(this.awT, pq$a.awT)) {
                    b2 = b;
                    if (pk.equals(this.awU, pq$a.awU)) {
                        b2 = b;
                        if (pk.equals(this.awV, pq$a.awV)) {
                            return this.a(pq$a);
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return (((pk.hashCode(this.awT) + 527) * 31 + pk.hashCode(this.awU)) * 31 + pk.hashCode(this.awV)) * 31 + this.qx();
    }
    
    public pq$a qH() {
        this.awT = pp.awQ;
        this.awU = pp.awQ;
        this.awV = pp.awL;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    public pq$a v(final pe pe) {
    Label_0065:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0065;
                    }
                    continue;
                }
                case 0: {
                    break Label_0065;
                }
                case 10: {
                    final int b = pp.b(pe, 10);
                    int length;
                    if (this.awT == null) {
                        length = 0;
                    }
                    else {
                        length = this.awT.length;
                    }
                    final String[] awT = new String[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.awT, 0, awT, 0, length);
                        i = length;
                    }
                    while (i < awT.length - 1) {
                        awT[i] = pe.readString();
                        pe.qg();
                        ++i;
                    }
                    awT[i] = pe.readString();
                    this.awT = awT;
                    continue;
                }
                case 18: {
                    final int b2 = pp.b(pe, 18);
                    int length2;
                    if (this.awU == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.awU.length;
                    }
                    final String[] awU = new String[b2 + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.awU, 0, awU, 0, length2);
                        j = length2;
                    }
                    while (j < awU.length - 1) {
                        awU[j] = pe.readString();
                        pe.qg();
                        ++j;
                    }
                    awU[j] = pe.readString();
                    this.awU = awU;
                    continue;
                }
                case 24: {
                    final int b3 = pp.b(pe, 24);
                    int length3;
                    if (this.awV == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.awV.length;
                    }
                    final int[] awV = new int[b3 + length3];
                    int k = length3;
                    if (length3 != 0) {
                        System.arraycopy(this.awV, 0, awV, 0, length3);
                        k = length3;
                    }
                    while (k < awV.length - 1) {
                        awV[k] = pe.qj();
                        pe.qg();
                        ++k;
                    }
                    awV[k] = pe.qj();
                    this.awV = awV;
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
                    int length4;
                    if (this.awV == null) {
                        length4 = 0;
                    }
                    else {
                        length4 = this.awV.length;
                    }
                    final int[] awV2 = new int[n + length4];
                    int l = length4;
                    if (length4 != 0) {
                        System.arraycopy(this.awV, 0, awV2, 0, length4);
                        l = length4;
                    }
                    while (l < awV2.length) {
                        awV2[l] = pe.qj();
                        ++l;
                    }
                    this.awV = awV2;
                    pe.gp(go);
                    continue;
                }
            }
        }
        return this;
    }
}
