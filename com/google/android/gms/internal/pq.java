// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.io.IOException;

public interface pq
{
    public static final class a extends pg<a>
    {
        public String[] awT;
        public String[] awU;
        public int[] awV;
        
        public a() {
            this.qH();
        }
        
        @Override
        public void a(final pf pf) throws IOException {
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
                if (o instanceof a) {
                    final a a = (a)o;
                    b2 = b;
                    if (pk.equals(this.awT, a.awT)) {
                        b2 = b;
                        if (pk.equals(this.awU, a.awU)) {
                            b2 = b;
                            if (pk.equals(this.awV, a.awV)) {
                                return this.a(a);
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
        
        public a qH() {
            this.awT = pp.awQ;
            this.awU = pp.awQ;
            this.awV = pp.awL;
            this.awy = null;
            this.awJ = -1;
            return this;
        }
        
        public a v(final pe pe) throws IOException {
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
    
    public static final class b extends pg<b>
    {
        public int awW;
        public String awX;
        public String version;
        
        public b() {
            this.qI();
        }
        
        @Override
        public void a(final pf pf) throws IOException {
            if (this.awW != 0) {
                pf.s(1, this.awW);
            }
            if (!this.awX.equals("")) {
                pf.b(2, this.awX);
            }
            if (!this.version.equals("")) {
                pf.b(3, this.version);
            }
            super.a(pf);
        }
        
        @Override
        protected int c() {
            int c;
            final int n = c = super.c();
            if (this.awW != 0) {
                c = n + pf.u(1, this.awW);
            }
            int n2 = c;
            if (!this.awX.equals("")) {
                n2 = c + pf.j(2, this.awX);
            }
            int n3 = n2;
            if (!this.version.equals("")) {
                n3 = n2 + pf.j(3, this.version);
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
                if (o instanceof b) {
                    final b b3 = (b)o;
                    b2 = b;
                    if (this.awW == b3.awW) {
                        if (this.awX == null) {
                            b2 = b;
                            if (b3.awX != null) {
                                return b2;
                            }
                        }
                        else if (!this.awX.equals(b3.awX)) {
                            return false;
                        }
                        if (this.version == null) {
                            b2 = b;
                            if (b3.version != null) {
                                return b2;
                            }
                        }
                        else if (!this.version.equals(b3.version)) {
                            return false;
                        }
                        return this.a(b3);
                    }
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            final int awW = this.awW;
            int hashCode2;
            if (this.awX == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.awX.hashCode();
            }
            if (this.version != null) {
                hashCode = this.version.hashCode();
            }
            return ((hashCode2 + (awW + 527) * 31) * 31 + hashCode) * 31 + this.qx();
        }
        
        public b qI() {
            this.awW = 0;
            this.awX = "";
            this.version = "";
            this.awy = null;
            this.awJ = -1;
            return this;
        }
        
        public b w(final pe pe) throws IOException {
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
                            case 0:
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
                            case 17:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22: {
                                this.awW = qj;
                                continue;
                            }
                        }
                        break;
                    }
                    case 18: {
                        this.awX = pe.readString();
                        continue;
                    }
                    case 26: {
                        this.version = pe.readString();
                        continue;
                    }
                }
            }
            return this;
        }
    }
    
    public static final class c extends pg<c>
    {
        public long awY;
        public int awZ;
        public int axa;
        public boolean axb;
        public d[] axc;
        public b axd;
        public byte[] axe;
        public byte[] axf;
        public byte[] axg;
        public a axh;
        public String axi;
        public String tag;
        
        public c() {
            this.qJ();
        }
        
        @Override
        public void a(final pf pf) throws IOException {
            if (this.awY != 0L) {
                pf.b(1, this.awY);
            }
            if (!this.tag.equals("")) {
                pf.b(2, this.tag);
            }
            if (this.axc != null && this.axc.length > 0) {
                for (int i = 0; i < this.axc.length; ++i) {
                    final d d = this.axc[i];
                    if (d != null) {
                        pf.a(3, d);
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
                        final d d = this.axc[i];
                        n4 = n2;
                        if (d != null) {
                            n4 = n2 + pf.c(3, d);
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
                if (o instanceof c) {
                    final c c = (c)o;
                    b2 = b;
                    if (this.awY == c.awY) {
                        if (this.tag == null) {
                            b2 = b;
                            if (c.tag != null) {
                                return b2;
                            }
                        }
                        else if (!this.tag.equals(c.tag)) {
                            return false;
                        }
                        b2 = b;
                        if (this.awZ == c.awZ) {
                            b2 = b;
                            if (this.axa == c.axa) {
                                b2 = b;
                                if (this.axb == c.axb) {
                                    b2 = b;
                                    if (pk.equals(this.axc, c.axc)) {
                                        if (this.axd == null) {
                                            b2 = b;
                                            if (c.axd != null) {
                                                return b2;
                                            }
                                        }
                                        else if (!this.axd.equals(c.axd)) {
                                            return false;
                                        }
                                        b2 = b;
                                        if (Arrays.equals(this.axe, c.axe)) {
                                            b2 = b;
                                            if (Arrays.equals(this.axf, c.axf)) {
                                                b2 = b;
                                                if (Arrays.equals(this.axg, c.axg)) {
                                                    if (this.axh == null) {
                                                        b2 = b;
                                                        if (c.axh != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.axh.equals(c.axh)) {
                                                        return false;
                                                    }
                                                    if (this.axi == null) {
                                                        b2 = b;
                                                        if (c.axi != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.axi.equals(c.axi)) {
                                                        return false;
                                                    }
                                                    return this.a(c);
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
        
        public c qJ() {
            this.awY = 0L;
            this.tag = "";
            this.awZ = 0;
            this.axa = 0;
            this.axb = false;
            this.axc = d.qK();
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
        
        public c x(final pe pe) throws IOException {
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
                        final d[] axc = new d[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.axc, 0, axc, 0, length);
                            i = length;
                        }
                        while (i < axc.length - 1) {
                            pe.a(axc[i] = new d());
                            pe.qg();
                            ++i;
                        }
                        pe.a(axc[i] = new d());
                        this.axc = axc;
                        continue;
                    }
                    case 50: {
                        this.axe = pe.readBytes();
                        continue;
                    }
                    case 58: {
                        if (this.axh == null) {
                            this.axh = new a();
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
                            this.axd = new b();
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
    
    public static final class d extends pg<d>
    {
        private static volatile d[] axj;
        public String fv;
        public String value;
        
        public d() {
            this.qL();
        }
        
        public static d[] qK() {
            Label_0027: {
                if (d.axj != null) {
                    break Label_0027;
                }
                synchronized (pk.awI) {
                    if (d.axj == null) {
                        d.axj = new d[0];
                    }
                    return d.axj;
                }
            }
        }
        
        @Override
        public void a(final pf pf) throws IOException {
            if (!this.fv.equals("")) {
                pf.b(1, this.fv);
            }
            if (!this.value.equals("")) {
                pf.b(2, this.value);
            }
            super.a(pf);
        }
        
        @Override
        protected int c() {
            int c;
            final int n = c = super.c();
            if (!this.fv.equals("")) {
                c = n + pf.j(1, this.fv);
            }
            int n2 = c;
            if (!this.value.equals("")) {
                n2 = c + pf.j(2, this.value);
            }
            return n2;
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
                if (o instanceof d) {
                    final d d = (d)o;
                    if (this.fv == null) {
                        b2 = b;
                        if (d.fv != null) {
                            return b2;
                        }
                    }
                    else if (!this.fv.equals(d.fv)) {
                        return false;
                    }
                    if (this.value == null) {
                        b2 = b;
                        if (d.value != null) {
                            return b2;
                        }
                    }
                    else if (!this.value.equals(d.value)) {
                        return false;
                    }
                    return this.a(d);
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            int hashCode2;
            if (this.fv == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.fv.hashCode();
            }
            if (this.value != null) {
                hashCode = this.value.hashCode();
            }
            return ((hashCode2 + 527) * 31 + hashCode) * 31 + this.qx();
        }
        
        public d qL() {
            this.fv = "";
            this.value = "";
            this.awy = null;
            this.awJ = -1;
            return this;
        }
        
        public d y(final pe pe) throws IOException {
        Label_0049:
            while (true) {
                final int qg = pe.qg();
                switch (qg) {
                    default: {
                        if (!this.a(pe, qg)) {
                            break Label_0049;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0049;
                    }
                    case 10: {
                        this.fv = pe.readString();
                        continue;
                    }
                    case 18: {
                        this.value = pe.readString();
                        continue;
                    }
                }
            }
            return this;
        }
    }
}
