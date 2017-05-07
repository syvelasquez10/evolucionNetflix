// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class c$f extends pg<c$f>
{
    public String[] fE;
    public String[] fF;
    public d$a[] fG;
    public c$e[] fH;
    public c$b[] fI;
    public c$b[] fJ;
    public c$b[] fK;
    public c$g[] fL;
    public String fM;
    public String fN;
    public String fO;
    public c$a fP;
    public float fQ;
    public boolean fR;
    public String[] fS;
    public int fT;
    public String version;
    
    public c$f() {
        this.k();
    }
    
    public static c$f a(final byte[] array) {
        return pm.a(new c$f(), array);
    }
    
    @Override
    public void a(final pf pf) {
        final int n = 0;
        if (this.fF != null && this.fF.length > 0) {
            for (int i = 0; i < this.fF.length; ++i) {
                final String s = this.fF[i];
                if (s != null) {
                    pf.b(1, s);
                }
            }
        }
        if (this.fG != null && this.fG.length > 0) {
            for (int j = 0; j < this.fG.length; ++j) {
                final d$a d$a = this.fG[j];
                if (d$a != null) {
                    pf.a(2, d$a);
                }
            }
        }
        if (this.fH != null && this.fH.length > 0) {
            for (int k = 0; k < this.fH.length; ++k) {
                final c$e c$e = this.fH[k];
                if (c$e != null) {
                    pf.a(3, c$e);
                }
            }
        }
        if (this.fI != null && this.fI.length > 0) {
            for (int l = 0; l < this.fI.length; ++l) {
                final c$b c$b = this.fI[l];
                if (c$b != null) {
                    pf.a(4, c$b);
                }
            }
        }
        if (this.fJ != null && this.fJ.length > 0) {
            for (int n2 = 0; n2 < this.fJ.length; ++n2) {
                final c$b c$b2 = this.fJ[n2];
                if (c$b2 != null) {
                    pf.a(5, c$b2);
                }
            }
        }
        if (this.fK != null && this.fK.length > 0) {
            for (int n3 = 0; n3 < this.fK.length; ++n3) {
                final c$b c$b3 = this.fK[n3];
                if (c$b3 != null) {
                    pf.a(6, c$b3);
                }
            }
        }
        if (this.fL != null && this.fL.length > 0) {
            for (int n4 = 0; n4 < this.fL.length; ++n4) {
                final c$g c$g = this.fL[n4];
                if (c$g != null) {
                    pf.a(7, c$g);
                }
            }
        }
        if (!this.fM.equals("")) {
            pf.b(9, this.fM);
        }
        if (!this.fN.equals("")) {
            pf.b(10, this.fN);
        }
        if (!this.fO.equals("0")) {
            pf.b(12, this.fO);
        }
        if (!this.version.equals("")) {
            pf.b(13, this.version);
        }
        if (this.fP != null) {
            pf.a(14, this.fP);
        }
        if (Float.floatToIntBits(this.fQ) != Float.floatToIntBits(0.0f)) {
            pf.b(15, this.fQ);
        }
        if (this.fS != null && this.fS.length > 0) {
            for (int n5 = 0; n5 < this.fS.length; ++n5) {
                final String s2 = this.fS[n5];
                if (s2 != null) {
                    pf.b(16, s2);
                }
            }
        }
        if (this.fT != 0) {
            pf.s(17, this.fT);
        }
        if (this.fR) {
            pf.b(18, this.fR);
        }
        if (this.fE != null && this.fE.length > 0) {
            for (int n6 = n; n6 < this.fE.length; ++n6) {
                final String s3 = this.fE[n6];
                if (s3 != null) {
                    pf.b(19, s3);
                }
            }
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        final int n = 0;
        final int c = super.c();
        int n6;
        if (this.fF != null && this.fF.length > 0) {
            int i = 0;
            int n2 = 0;
            int n3 = 0;
            while (i < this.fF.length) {
                final String s = this.fF[i];
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
        if (this.fG != null) {
            n7 = n6;
            if (this.fG.length > 0) {
                n7 = n6;
                int n8;
                for (int j = 0; j < this.fG.length; ++j, n7 = n8) {
                    final d$a d$a = this.fG[j];
                    n8 = n7;
                    if (d$a != null) {
                        n8 = n7 + pf.c(2, d$a);
                    }
                }
            }
        }
        int n9 = n7;
        if (this.fH != null) {
            n9 = n7;
            if (this.fH.length > 0) {
                int n10;
                for (int k = 0; k < this.fH.length; ++k, n7 = n10) {
                    final c$e c$e = this.fH[k];
                    n10 = n7;
                    if (c$e != null) {
                        n10 = n7 + pf.c(3, c$e);
                    }
                }
                n9 = n7;
            }
        }
        int n11 = n9;
        if (this.fI != null) {
            n11 = n9;
            if (this.fI.length > 0) {
                n11 = n9;
                int n12;
                for (int l = 0; l < this.fI.length; ++l, n11 = n12) {
                    final c$b c$b = this.fI[l];
                    n12 = n11;
                    if (c$b != null) {
                        n12 = n11 + pf.c(4, c$b);
                    }
                }
            }
        }
        int n13 = n11;
        if (this.fJ != null) {
            n13 = n11;
            if (this.fJ.length > 0) {
                int n15;
                for (int n14 = 0; n14 < this.fJ.length; ++n14, n11 = n15) {
                    final c$b c$b2 = this.fJ[n14];
                    n15 = n11;
                    if (c$b2 != null) {
                        n15 = n11 + pf.c(5, c$b2);
                    }
                }
                n13 = n11;
            }
        }
        int n16 = n13;
        if (this.fK != null) {
            n16 = n13;
            if (this.fK.length > 0) {
                n16 = n13;
                int n18;
                for (int n17 = 0; n17 < this.fK.length; ++n17, n16 = n18) {
                    final c$b c$b3 = this.fK[n17];
                    n18 = n16;
                    if (c$b3 != null) {
                        n18 = n16 + pf.c(6, c$b3);
                    }
                }
            }
        }
        int n19 = n16;
        if (this.fL != null) {
            n19 = n16;
            if (this.fL.length > 0) {
                int n21;
                for (int n20 = 0; n20 < this.fL.length; ++n20, n16 = n21) {
                    final c$g c$g = this.fL[n20];
                    n21 = n16;
                    if (c$g != null) {
                        n21 = n16 + pf.c(7, c$g);
                    }
                }
                n19 = n16;
            }
        }
        int n22 = n19;
        if (!this.fM.equals("")) {
            n22 = n19 + pf.j(9, this.fM);
        }
        int n23 = n22;
        if (!this.fN.equals("")) {
            n23 = n22 + pf.j(10, this.fN);
        }
        int n24 = n23;
        if (!this.fO.equals("0")) {
            n24 = n23 + pf.j(12, this.fO);
        }
        int n25 = n24;
        if (!this.version.equals("")) {
            n25 = n24 + pf.j(13, this.version);
        }
        int n26 = n25;
        if (this.fP != null) {
            n26 = n25 + pf.c(14, this.fP);
        }
        int n27 = n26;
        if (Float.floatToIntBits(this.fQ) != Float.floatToIntBits(0.0f)) {
            n27 = n26 + pf.c(15, this.fQ);
        }
        int n28 = n27;
        if (this.fS != null) {
            n28 = n27;
            if (this.fS.length > 0) {
                int n29 = 0;
                int n30 = 0;
                int n31 = 0;
                while (n29 < this.fS.length) {
                    final String s2 = this.fS[n29];
                    int n32 = n30;
                    int n33 = n31;
                    if (s2 != null) {
                        n33 = n31 + 1;
                        n32 = n30 + pf.df(s2);
                    }
                    ++n29;
                    n30 = n32;
                    n31 = n33;
                }
                n28 = n27 + n30 + n31 * 2;
            }
        }
        int n34 = n28;
        if (this.fT != 0) {
            n34 = n28 + pf.u(17, this.fT);
        }
        int n35 = n34;
        if (this.fR) {
            n35 = n34 + pf.c(18, this.fR);
        }
        int n36 = n35;
        if (this.fE != null) {
            n36 = n35;
            if (this.fE.length > 0) {
                int n37 = 0;
                int n38 = 0;
                int n40;
                int n41;
                for (int n39 = n; n39 < this.fE.length; ++n39, n37 = n40, n38 = n41) {
                    final String s3 = this.fE[n39];
                    n40 = n37;
                    n41 = n38;
                    if (s3 != null) {
                        n41 = n38 + 1;
                        n40 = n37 + pf.df(s3);
                    }
                }
                n36 = n35 + n37 + n38 * 2;
            }
        }
        return n36;
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
            if (o instanceof c$f) {
                final c$f c$f = (c$f)o;
                b2 = b;
                if (pk.equals(this.fE, c$f.fE)) {
                    b2 = b;
                    if (pk.equals(this.fF, c$f.fF)) {
                        b2 = b;
                        if (pk.equals(this.fG, c$f.fG)) {
                            b2 = b;
                            if (pk.equals(this.fH, c$f.fH)) {
                                b2 = b;
                                if (pk.equals(this.fI, c$f.fI)) {
                                    b2 = b;
                                    if (pk.equals(this.fJ, c$f.fJ)) {
                                        b2 = b;
                                        if (pk.equals(this.fK, c$f.fK)) {
                                            b2 = b;
                                            if (pk.equals(this.fL, c$f.fL)) {
                                                if (this.fM == null) {
                                                    b2 = b;
                                                    if (c$f.fM != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.fM.equals(c$f.fM)) {
                                                    return false;
                                                }
                                                if (this.fN == null) {
                                                    b2 = b;
                                                    if (c$f.fN != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.fN.equals(c$f.fN)) {
                                                    return false;
                                                }
                                                if (this.fO == null) {
                                                    b2 = b;
                                                    if (c$f.fO != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.fO.equals(c$f.fO)) {
                                                    return false;
                                                }
                                                if (this.version == null) {
                                                    b2 = b;
                                                    if (c$f.version != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.version.equals(c$f.version)) {
                                                    return false;
                                                }
                                                if (this.fP == null) {
                                                    b2 = b;
                                                    if (c$f.fP != null) {
                                                        return b2;
                                                    }
                                                }
                                                else if (!this.fP.equals(c$f.fP)) {
                                                    return false;
                                                }
                                                b2 = b;
                                                if (Float.floatToIntBits(this.fQ) == Float.floatToIntBits(c$f.fQ)) {
                                                    b2 = b;
                                                    if (this.fR == c$f.fR) {
                                                        b2 = b;
                                                        if (pk.equals(this.fS, c$f.fS)) {
                                                            b2 = b;
                                                            if (this.fT == c$f.fT) {
                                                                return this.a(c$f);
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
    
    public c$f g(final pe pe) {
    Label_0169:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0169;
                    }
                    continue;
                }
                case 0: {
                    break Label_0169;
                }
                case 10: {
                    final int b = pp.b(pe, 10);
                    int length;
                    if (this.fF == null) {
                        length = 0;
                    }
                    else {
                        length = this.fF.length;
                    }
                    final String[] ff = new String[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.fF, 0, ff, 0, length);
                        i = length;
                    }
                    while (i < ff.length - 1) {
                        ff[i] = pe.readString();
                        pe.qg();
                        ++i;
                    }
                    ff[i] = pe.readString();
                    this.fF = ff;
                    continue;
                }
                case 18: {
                    final int b2 = pp.b(pe, 18);
                    int length2;
                    if (this.fG == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.fG.length;
                    }
                    final d$a[] fg = new d$a[b2 + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.fG, 0, fg, 0, length2);
                        j = length2;
                    }
                    while (j < fg.length - 1) {
                        pe.a(fg[j] = new d$a());
                        pe.qg();
                        ++j;
                    }
                    pe.a(fg[j] = new d$a());
                    this.fG = fg;
                    continue;
                }
                case 26: {
                    final int b3 = pp.b(pe, 26);
                    int length3;
                    if (this.fH == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.fH.length;
                    }
                    final c$e[] fh = new c$e[b3 + length3];
                    int k = length3;
                    if (length3 != 0) {
                        System.arraycopy(this.fH, 0, fh, 0, length3);
                        k = length3;
                    }
                    while (k < fh.length - 1) {
                        pe.a(fh[k] = new c$e());
                        pe.qg();
                        ++k;
                    }
                    pe.a(fh[k] = new c$e());
                    this.fH = fh;
                    continue;
                }
                case 34: {
                    final int b4 = pp.b(pe, 34);
                    int length4;
                    if (this.fI == null) {
                        length4 = 0;
                    }
                    else {
                        length4 = this.fI.length;
                    }
                    final c$b[] fi = new c$b[b4 + length4];
                    int l = length4;
                    if (length4 != 0) {
                        System.arraycopy(this.fI, 0, fi, 0, length4);
                        l = length4;
                    }
                    while (l < fi.length - 1) {
                        pe.a(fi[l] = new c$b());
                        pe.qg();
                        ++l;
                    }
                    pe.a(fi[l] = new c$b());
                    this.fI = fi;
                    continue;
                }
                case 42: {
                    final int b5 = pp.b(pe, 42);
                    int length5;
                    if (this.fJ == null) {
                        length5 = 0;
                    }
                    else {
                        length5 = this.fJ.length;
                    }
                    final c$b[] fj = new c$b[b5 + length5];
                    int n = length5;
                    if (length5 != 0) {
                        System.arraycopy(this.fJ, 0, fj, 0, length5);
                        n = length5;
                    }
                    while (n < fj.length - 1) {
                        pe.a(fj[n] = new c$b());
                        pe.qg();
                        ++n;
                    }
                    pe.a(fj[n] = new c$b());
                    this.fJ = fj;
                    continue;
                }
                case 50: {
                    final int b6 = pp.b(pe, 50);
                    int length6;
                    if (this.fK == null) {
                        length6 = 0;
                    }
                    else {
                        length6 = this.fK.length;
                    }
                    final c$b[] fk = new c$b[b6 + length6];
                    int n2 = length6;
                    if (length6 != 0) {
                        System.arraycopy(this.fK, 0, fk, 0, length6);
                        n2 = length6;
                    }
                    while (n2 < fk.length - 1) {
                        pe.a(fk[n2] = new c$b());
                        pe.qg();
                        ++n2;
                    }
                    pe.a(fk[n2] = new c$b());
                    this.fK = fk;
                    continue;
                }
                case 58: {
                    final int b7 = pp.b(pe, 58);
                    int length7;
                    if (this.fL == null) {
                        length7 = 0;
                    }
                    else {
                        length7 = this.fL.length;
                    }
                    final c$g[] fl = new c$g[b7 + length7];
                    int n3 = length7;
                    if (length7 != 0) {
                        System.arraycopy(this.fL, 0, fl, 0, length7);
                        n3 = length7;
                    }
                    while (n3 < fl.length - 1) {
                        pe.a(fl[n3] = new c$g());
                        pe.qg();
                        ++n3;
                    }
                    pe.a(fl[n3] = new c$g());
                    this.fL = fl;
                    continue;
                }
                case 74: {
                    this.fM = pe.readString();
                    continue;
                }
                case 82: {
                    this.fN = pe.readString();
                    continue;
                }
                case 98: {
                    this.fO = pe.readString();
                    continue;
                }
                case 106: {
                    this.version = pe.readString();
                    continue;
                }
                case 114: {
                    if (this.fP == null) {
                        this.fP = new c$a();
                    }
                    pe.a(this.fP);
                    continue;
                }
                case 125: {
                    this.fQ = pe.readFloat();
                    continue;
                }
                case 130: {
                    final int b8 = pp.b(pe, 130);
                    int length8;
                    if (this.fS == null) {
                        length8 = 0;
                    }
                    else {
                        length8 = this.fS.length;
                    }
                    final String[] fs = new String[b8 + length8];
                    int n4 = length8;
                    if (length8 != 0) {
                        System.arraycopy(this.fS, 0, fs, 0, length8);
                        n4 = length8;
                    }
                    while (n4 < fs.length - 1) {
                        fs[n4] = pe.readString();
                        pe.qg();
                        ++n4;
                    }
                    fs[n4] = pe.readString();
                    this.fS = fs;
                    continue;
                }
                case 136: {
                    this.fT = pe.qj();
                    continue;
                }
                case 144: {
                    this.fR = pe.qk();
                    continue;
                }
                case 154: {
                    final int b9 = pp.b(pe, 154);
                    int length9;
                    if (this.fE == null) {
                        length9 = 0;
                    }
                    else {
                        length9 = this.fE.length;
                    }
                    final String[] fe = new String[b9 + length9];
                    int n5 = length9;
                    if (length9 != 0) {
                        System.arraycopy(this.fE, 0, fe, 0, length9);
                        n5 = length9;
                    }
                    while (n5 < fe.length - 1) {
                        fe[n5] = pe.readString();
                        pe.qg();
                        ++n5;
                    }
                    fe[n5] = pe.readString();
                    this.fE = fe;
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int hashCode2 = pk.hashCode(this.fE);
        final int hashCode3 = pk.hashCode(this.fF);
        final int hashCode4 = pk.hashCode(this.fG);
        final int hashCode5 = pk.hashCode(this.fH);
        final int hashCode6 = pk.hashCode(this.fI);
        final int hashCode7 = pk.hashCode(this.fJ);
        final int hashCode8 = pk.hashCode(this.fK);
        final int hashCode9 = pk.hashCode(this.fL);
        int hashCode10;
        if (this.fM == null) {
            hashCode10 = 0;
        }
        else {
            hashCode10 = this.fM.hashCode();
        }
        int hashCode11;
        if (this.fN == null) {
            hashCode11 = 0;
        }
        else {
            hashCode11 = this.fN.hashCode();
        }
        int hashCode12;
        if (this.fO == null) {
            hashCode12 = 0;
        }
        else {
            hashCode12 = this.fO.hashCode();
        }
        int hashCode13;
        if (this.version == null) {
            hashCode13 = 0;
        }
        else {
            hashCode13 = this.version.hashCode();
        }
        if (this.fP != null) {
            hashCode = this.fP.hashCode();
        }
        final int floatToIntBits = Float.floatToIntBits(this.fQ);
        int n;
        if (this.fR) {
            n = 1231;
        }
        else {
            n = 1237;
        }
        return (((n + (((hashCode13 + (hashCode12 + (hashCode11 + (hashCode10 + ((((((((hashCode2 + 527) * 31 + hashCode3) * 31 + hashCode4) * 31 + hashCode5) * 31 + hashCode6) * 31 + hashCode7) * 31 + hashCode8) * 31 + hashCode9) * 31) * 31) * 31) * 31) * 31 + hashCode) * 31 + floatToIntBits) * 31) * 31 + pk.hashCode(this.fS)) * 31 + this.fT) * 31 + this.qx();
    }
    
    public c$f k() {
        this.fE = pp.awQ;
        this.fF = pp.awQ;
        this.fG = d$a.r();
        this.fH = c$e.i();
        this.fI = c$b.d();
        this.fJ = c$b.d();
        this.fK = c$b.d();
        this.fL = c$g.l();
        this.fM = "";
        this.fN = "";
        this.fO = "0";
        this.version = "";
        this.fP = null;
        this.fQ = 0.0f;
        this.fR = false;
        this.fS = pp.awQ;
        this.fT = 0;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
}
