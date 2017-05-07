// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public interface d
{
    public static final class a extends kp<a>
    {
        private static volatile a[] fM;
        public String fN;
        public a[] fO;
        public a[] fP;
        public a[] fQ;
        public String fR;
        public String fS;
        public long fT;
        public boolean fU;
        public a[] fV;
        public int[] fW;
        public boolean fX;
        public int type;
        
        public a() {
            this.s();
        }
        
        public static a[] r() {
            Label_0027: {
                if (a.fM != null) {
                    break Label_0027;
                }
                synchronized (kr.adX) {
                    if (a.fM == null) {
                        a.fM = new a[0];
                    }
                    return a.fM;
                }
            }
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            final int n = 0;
            ko.i(1, this.type);
            if (!this.fN.equals("")) {
                ko.b(2, this.fN);
            }
            if (this.fO != null && this.fO.length > 0) {
                for (int i = 0; i < this.fO.length; ++i) {
                    final a a = this.fO[i];
                    if (a != null) {
                        ko.a(3, a);
                    }
                }
            }
            if (this.fP != null && this.fP.length > 0) {
                for (int j = 0; j < this.fP.length; ++j) {
                    final a a2 = this.fP[j];
                    if (a2 != null) {
                        ko.a(4, a2);
                    }
                }
            }
            if (this.fQ != null && this.fQ.length > 0) {
                for (int k = 0; k < this.fQ.length; ++k) {
                    final a a3 = this.fQ[k];
                    if (a3 != null) {
                        ko.a(5, a3);
                    }
                }
            }
            if (!this.fR.equals("")) {
                ko.b(6, this.fR);
            }
            if (!this.fS.equals("")) {
                ko.b(7, this.fS);
            }
            if (this.fT != 0L) {
                ko.b(8, this.fT);
            }
            if (this.fX) {
                ko.a(9, this.fX);
            }
            if (this.fW != null && this.fW.length > 0) {
                for (int l = 0; l < this.fW.length; ++l) {
                    ko.i(10, this.fW[l]);
                }
            }
            if (this.fV != null && this.fV.length > 0) {
                for (int n2 = n; n2 < this.fV.length; ++n2) {
                    final a a4 = this.fV[n2];
                    if (a4 != null) {
                        ko.a(11, a4);
                    }
                }
            }
            if (this.fU) {
                ko.a(12, this.fU);
            }
            super.a(ko);
        }
        
        @Override
        public int c() {
            final int n = 0;
            int n3;
            final int n2 = n3 = super.c() + ko.j(1, this.type);
            if (!this.fN.equals("")) {
                n3 = n2 + ko.g(2, this.fN);
            }
            int n4 = n3;
            if (this.fO != null) {
                n4 = n3;
                if (this.fO.length > 0) {
                    int n5;
                    for (int i = 0; i < this.fO.length; ++i, n3 = n5) {
                        final a a = this.fO[i];
                        n5 = n3;
                        if (a != null) {
                            n5 = n3 + ko.b(3, a);
                        }
                    }
                    n4 = n3;
                }
            }
            int n6 = n4;
            if (this.fP != null) {
                n6 = n4;
                if (this.fP.length > 0) {
                    n6 = n4;
                    int n7;
                    for (int j = 0; j < this.fP.length; ++j, n6 = n7) {
                        final a a2 = this.fP[j];
                        n7 = n6;
                        if (a2 != null) {
                            n7 = n6 + ko.b(4, a2);
                        }
                    }
                }
            }
            int n8 = n6;
            if (this.fQ != null) {
                n8 = n6;
                if (this.fQ.length > 0) {
                    int n9;
                    for (int k = 0; k < this.fQ.length; ++k, n6 = n9) {
                        final a a3 = this.fQ[k];
                        n9 = n6;
                        if (a3 != null) {
                            n9 = n6 + ko.b(5, a3);
                        }
                    }
                    n8 = n6;
                }
            }
            int n10 = n8;
            if (!this.fR.equals("")) {
                n10 = n8 + ko.g(6, this.fR);
            }
            int n11 = n10;
            if (!this.fS.equals("")) {
                n11 = n10 + ko.g(7, this.fS);
            }
            int n12 = n11;
            if (this.fT != 0L) {
                n12 = n11 + ko.d(8, this.fT);
            }
            int n13 = n12;
            if (this.fX) {
                n13 = n12 + ko.b(9, this.fX);
            }
            int n14 = n13;
            if (this.fW != null) {
                n14 = n13;
                if (this.fW.length > 0) {
                    int l = 0;
                    int n15 = 0;
                    while (l < this.fW.length) {
                        n15 += ko.cX(this.fW[l]);
                        ++l;
                    }
                    n14 = n13 + n15 + this.fW.length * 1;
                }
            }
            int n16 = n14;
            if (this.fV != null) {
                n16 = n14;
                if (this.fV.length > 0) {
                    int n17 = n;
                    while (true) {
                        n16 = n14;
                        if (n17 >= this.fV.length) {
                            break;
                        }
                        final a a4 = this.fV[n17];
                        int n18 = n14;
                        if (a4 != null) {
                            n18 = n14 + ko.b(11, a4);
                        }
                        ++n17;
                        n14 = n18;
                    }
                }
            }
            int adY = n16;
            if (this.fU) {
                adY = n16 + ko.b(12, this.fU);
            }
            return this.adY = adY;
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
                    if (this.type == a.type) {
                        if (this.fN == null) {
                            b2 = b;
                            if (a.fN != null) {
                                return b2;
                            }
                        }
                        else if (!this.fN.equals(a.fN)) {
                            return false;
                        }
                        b2 = b;
                        if (kr.equals(this.fO, a.fO)) {
                            b2 = b;
                            if (kr.equals(this.fP, a.fP)) {
                                b2 = b;
                                if (kr.equals(this.fQ, a.fQ)) {
                                    if (this.fR == null) {
                                        b2 = b;
                                        if (a.fR != null) {
                                            return b2;
                                        }
                                    }
                                    else if (!this.fR.equals(a.fR)) {
                                        return false;
                                    }
                                    if (this.fS == null) {
                                        b2 = b;
                                        if (a.fS != null) {
                                            return b2;
                                        }
                                    }
                                    else if (!this.fS.equals(a.fS)) {
                                        return false;
                                    }
                                    b2 = b;
                                    if (this.fT == a.fT) {
                                        b2 = b;
                                        if (this.fU == a.fU) {
                                            b2 = b;
                                            if (kr.equals(this.fV, a.fV)) {
                                                b2 = b;
                                                if (kr.equals(this.fW, a.fW)) {
                                                    b2 = b;
                                                    if (this.fX == a.fX) {
                                                        if (this.adU == null || this.adU.isEmpty()) {
                                                            if (a.adU != null) {
                                                                b2 = b;
                                                                if (!a.adU.isEmpty()) {
                                                                    return b2;
                                                                }
                                                            }
                                                            return true;
                                                        }
                                                        return this.adU.equals(a.adU);
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
            final boolean b = false;
            final int type = this.type;
            int hashCode;
            if (this.fN == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.fN.hashCode();
            }
            final int hashCode2 = kr.hashCode(this.fO);
            final int hashCode3 = kr.hashCode(this.fP);
            final int hashCode4 = kr.hashCode(this.fQ);
            int hashCode5;
            if (this.fR == null) {
                hashCode5 = 0;
            }
            else {
                hashCode5 = this.fR.hashCode();
            }
            int hashCode6;
            if (this.fS == null) {
                hashCode6 = 0;
            }
            else {
                hashCode6 = this.fS.hashCode();
            }
            final int n2 = (int)(this.fT ^ this.fT >>> 32);
            int n3;
            if (this.fU) {
                n3 = 1231;
            }
            else {
                n3 = 1237;
            }
            final int hashCode7 = kr.hashCode(this.fV);
            final int hashCode8 = kr.hashCode(this.fW);
            if (!this.fX) {
                n = 1237;
            }
            int hashCode9 = b ? 1 : 0;
            if (this.adU != null) {
                if (this.adU.isEmpty()) {
                    hashCode9 = (b ? 1 : 0);
                }
                else {
                    hashCode9 = this.adU.hashCode();
                }
            }
            return ((((n3 + ((hashCode6 + (hashCode5 + ((((hashCode + (type + 527) * 31) * 31 + hashCode2) * 31 + hashCode3) * 31 + hashCode4) * 31) * 31) * 31 + n2) * 31) * 31 + hashCode7) * 31 + hashCode8) * 31 + n) * 31 + hashCode9;
        }
        
        public a l(final kn kn) throws IOException {
        Label_0137:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0137;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0137;
                    }
                    case 8: {
                        final int mk = kn.mk();
                        switch (mk) {
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
                                this.type = mk;
                                continue;
                            }
                        }
                        break;
                    }
                    case 18: {
                        this.fN = kn.readString();
                        continue;
                    }
                    case 26: {
                        final int b = kw.b(kn, 26);
                        int length;
                        if (this.fO == null) {
                            length = 0;
                        }
                        else {
                            length = this.fO.length;
                        }
                        final a[] fo = new a[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.fO, 0, fo, 0, length);
                            i = length;
                        }
                        while (i < fo.length - 1) {
                            kn.a(fo[i] = new a());
                            kn.mh();
                            ++i;
                        }
                        kn.a(fo[i] = new a());
                        this.fO = fo;
                        continue;
                    }
                    case 34: {
                        final int b2 = kw.b(kn, 34);
                        int length2;
                        if (this.fP == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.fP.length;
                        }
                        final a[] fp = new a[b2 + length2];
                        int j = length2;
                        if (length2 != 0) {
                            System.arraycopy(this.fP, 0, fp, 0, length2);
                            j = length2;
                        }
                        while (j < fp.length - 1) {
                            kn.a(fp[j] = new a());
                            kn.mh();
                            ++j;
                        }
                        kn.a(fp[j] = new a());
                        this.fP = fp;
                        continue;
                    }
                    case 42: {
                        final int b3 = kw.b(kn, 42);
                        int length3;
                        if (this.fQ == null) {
                            length3 = 0;
                        }
                        else {
                            length3 = this.fQ.length;
                        }
                        final a[] fq = new a[b3 + length3];
                        int k = length3;
                        if (length3 != 0) {
                            System.arraycopy(this.fQ, 0, fq, 0, length3);
                            k = length3;
                        }
                        while (k < fq.length - 1) {
                            kn.a(fq[k] = new a());
                            kn.mh();
                            ++k;
                        }
                        kn.a(fq[k] = new a());
                        this.fQ = fq;
                        continue;
                    }
                    case 50: {
                        this.fR = kn.readString();
                        continue;
                    }
                    case 58: {
                        this.fS = kn.readString();
                        continue;
                    }
                    case 64: {
                        this.fT = kn.mj();
                        continue;
                    }
                    case 72: {
                        this.fX = kn.ml();
                        continue;
                    }
                    case 80: {
                        final int b4 = kw.b(kn, 80);
                        final int[] fw = new int[b4];
                        int l = 0;
                        int n = 0;
                        while (l < b4) {
                            if (l != 0) {
                                kn.mh();
                            }
                            final int mk2 = kn.mk();
                            switch (mk2) {
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
                                    fw[n] = mk2;
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
                        if (this.fW == null) {
                            length4 = 0;
                        }
                        else {
                            length4 = this.fW.length;
                        }
                        if (length4 == 0 && n == fw.length) {
                            this.fW = fw;
                            continue;
                        }
                        final int[] fw2 = new int[length4 + n];
                        if (length4 != 0) {
                            System.arraycopy(this.fW, 0, fw2, 0, length4);
                        }
                        System.arraycopy(fw, 0, fw2, length4, n);
                        this.fW = fw2;
                        continue;
                    }
                    case 82: {
                        final int cr = kn.cR(kn.mn());
                        final int position = kn.getPosition();
                        int n3 = 0;
                        while (kn.ms() > 0) {
                            switch (kn.mk()) {
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
                            kn.cT(position);
                            int length5;
                            if (this.fW == null) {
                                length5 = 0;
                            }
                            else {
                                length5 = this.fW.length;
                            }
                            final int[] fw3 = new int[n3 + length5];
                            int n4 = length5;
                            if (length5 != 0) {
                                System.arraycopy(this.fW, 0, fw3, 0, length5);
                                n4 = length5;
                            }
                            while (kn.ms() > 0) {
                                final int mk3 = kn.mk();
                                switch (mk3) {
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
                                        fw3[n4] = mk3;
                                        ++n4;
                                        continue;
                                    }
                                }
                            }
                            this.fW = fw3;
                        }
                        kn.cS(cr);
                        continue;
                    }
                    case 90: {
                        final int b5 = kw.b(kn, 90);
                        int length6;
                        if (this.fV == null) {
                            length6 = 0;
                        }
                        else {
                            length6 = this.fV.length;
                        }
                        final a[] fv = new a[b5 + length6];
                        int n5 = length6;
                        if (length6 != 0) {
                            System.arraycopy(this.fV, 0, fv, 0, length6);
                            n5 = length6;
                        }
                        while (n5 < fv.length - 1) {
                            kn.a(fv[n5] = new a());
                            kn.mh();
                            ++n5;
                        }
                        kn.a(fv[n5] = new a());
                        this.fV = fv;
                        continue;
                    }
                    case 96: {
                        this.fU = kn.ml();
                        continue;
                    }
                }
            }
            return this;
        }
        
        public a s() {
            this.type = 1;
            this.fN = "";
            this.fO = r();
            this.fP = r();
            this.fQ = r();
            this.fR = "";
            this.fS = "";
            this.fT = 0L;
            this.fU = false;
            this.fV = r();
            this.fW = kw.aea;
            this.fX = false;
            this.adU = null;
            this.adY = -1;
            return this;
        }
    }
}
