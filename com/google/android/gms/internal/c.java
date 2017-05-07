// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public interface c
{
    public static final class a extends pg<a>
    {
        public int fn;
        public int fo;
        public int level;
        
        public a() {
            this.b();
        }
        
        public a a(final pe pe) throws IOException {
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
                            case 1:
                            case 2:
                            case 3: {
                                this.level = qj;
                                continue;
                            }
                        }
                        break;
                    }
                    case 16: {
                        this.fn = pe.qj();
                        continue;
                    }
                    case 24: {
                        this.fo = pe.qj();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        public void a(final pf pf) throws IOException {
            if (this.level != 1) {
                pf.s(1, this.level);
            }
            if (this.fn != 0) {
                pf.s(2, this.fn);
            }
            if (this.fo != 0) {
                pf.s(3, this.fo);
            }
            super.a(pf);
        }
        
        public a b() {
            this.level = 1;
            this.fn = 0;
            this.fo = 0;
            this.awy = null;
            this.awJ = -1;
            return this;
        }
        
        @Override
        protected int c() {
            int c;
            final int n = c = super.c();
            if (this.level != 1) {
                c = n + pf.u(1, this.level);
            }
            int n2 = c;
            if (this.fn != 0) {
                n2 = c + pf.u(2, this.fn);
            }
            int n3 = n2;
            if (this.fo != 0) {
                n3 = n2 + pf.u(3, this.fo);
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
                if (o instanceof a) {
                    final a a = (a)o;
                    b2 = b;
                    if (this.level == a.level) {
                        b2 = b;
                        if (this.fn == a.fn) {
                            b2 = b;
                            if (this.fo == a.fo) {
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
            return (((this.level + 527) * 31 + this.fn) * 31 + this.fo) * 31 + this.qx();
        }
    }
    
    public static final class b extends pg<b>
    {
        private static volatile b[] fp;
        public int[] fq;
        public int fr;
        public boolean fs;
        public boolean ft;
        public int name;
        
        public b() {
            this.e();
        }
        
        public static b[] d() {
            Label_0027: {
                if (b.fp != null) {
                    break Label_0027;
                }
                synchronized (pk.awI) {
                    if (b.fp == null) {
                        b.fp = new b[0];
                    }
                    return b.fp;
                }
            }
        }
        
        @Override
        public void a(final pf pf) throws IOException {
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
        
        public b c(final pe pe) throws IOException {
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
        
        public b e() {
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
                if (o instanceof b) {
                    final b b3 = (b)o;
                    b2 = b;
                    if (pk.equals(this.fq, b3.fq)) {
                        b2 = b;
                        if (this.fr == b3.fr) {
                            b2 = b;
                            if (this.name == b3.name) {
                                b2 = b;
                                if (this.fs == b3.fs) {
                                    b2 = b;
                                    if (this.ft == b3.ft) {
                                        return this.a(b3);
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
    
    public static final class c extends pg<c>
    {
        private static volatile c[] fu;
        public String fv;
        public long fw;
        public long fx;
        public boolean fy;
        public long fz;
        
        public c() {
            this.g();
        }
        
        public static c[] f() {
            Label_0027: {
                if (c.fu != null) {
                    break Label_0027;
                }
                synchronized (pk.awI) {
                    if (c.fu == null) {
                        c.fu = new c[0];
                    }
                    return c.fu;
                }
            }
        }
        
        @Override
        public void a(final pf pf) throws IOException {
            if (!this.fv.equals("")) {
                pf.b(1, this.fv);
            }
            if (this.fw != 0L) {
                pf.b(2, this.fw);
            }
            if (this.fx != 2147483647L) {
                pf.b(3, this.fx);
            }
            if (this.fy) {
                pf.b(4, this.fy);
            }
            if (this.fz != 0L) {
                pf.b(5, this.fz);
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
            if (this.fw != 0L) {
                n2 = c + pf.d(2, this.fw);
            }
            int n3 = n2;
            if (this.fx != 2147483647L) {
                n3 = n2 + pf.d(3, this.fx);
            }
            int n4 = n3;
            if (this.fy) {
                n4 = n3 + pf.c(4, this.fy);
            }
            int n5 = n4;
            if (this.fz != 0L) {
                n5 = n4 + pf.d(5, this.fz);
            }
            return n5;
        }
        
        public c d(final pe pe) throws IOException {
        Label_0073:
            while (true) {
                final int qg = pe.qg();
                switch (qg) {
                    default: {
                        if (!this.a(pe, qg)) {
                            break Label_0073;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0073;
                    }
                    case 10: {
                        this.fv = pe.readString();
                        continue;
                    }
                    case 16: {
                        this.fw = pe.qi();
                        continue;
                    }
                    case 24: {
                        this.fx = pe.qi();
                        continue;
                    }
                    case 32: {
                        this.fy = pe.qk();
                        continue;
                    }
                    case 40: {
                        this.fz = pe.qi();
                        continue;
                    }
                }
            }
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
                if (o instanceof c) {
                    final c c = (c)o;
                    if (this.fv == null) {
                        b2 = b;
                        if (c.fv != null) {
                            return b2;
                        }
                    }
                    else if (!this.fv.equals(c.fv)) {
                        return false;
                    }
                    b2 = b;
                    if (this.fw == c.fw) {
                        b2 = b;
                        if (this.fx == c.fx) {
                            b2 = b;
                            if (this.fy == c.fy) {
                                b2 = b;
                                if (this.fz == c.fz) {
                                    return this.a(c);
                                }
                            }
                        }
                    }
                }
            }
            return b2;
        }
        
        public c g() {
            this.fv = "";
            this.fw = 0L;
            this.fx = 2147483647L;
            this.fy = false;
            this.fz = 0L;
            this.awy = null;
            this.awJ = -1;
            return this;
        }
        
        @Override
        public int hashCode() {
            int hashCode;
            if (this.fv == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.fv.hashCode();
            }
            final int n = (int)(this.fw ^ this.fw >>> 32);
            final int n2 = (int)(this.fx ^ this.fx >>> 32);
            int n3;
            if (this.fy) {
                n3 = 1231;
            }
            else {
                n3 = 1237;
            }
            return ((n3 + (((hashCode + 527) * 31 + n) * 31 + n2) * 31) * 31 + (int)(this.fz ^ this.fz >>> 32)) * 31 + this.qx();
        }
    }
    
    public static final class d extends pg<d>
    {
        public com.google.android.gms.internal.d.a[] fA;
        public com.google.android.gms.internal.d.a[] fB;
        public c[] fC;
        
        public d() {
            this.h();
        }
        
        @Override
        public void a(final pf pf) throws IOException {
            final int n = 0;
            if (this.fA != null && this.fA.length > 0) {
                for (int i = 0; i < this.fA.length; ++i) {
                    final com.google.android.gms.internal.d.a a = this.fA[i];
                    if (a != null) {
                        pf.a(1, a);
                    }
                }
            }
            if (this.fB != null && this.fB.length > 0) {
                for (int j = 0; j < this.fB.length; ++j) {
                    final com.google.android.gms.internal.d.a a2 = this.fB[j];
                    if (a2 != null) {
                        pf.a(2, a2);
                    }
                }
            }
            if (this.fC != null && this.fC.length > 0) {
                for (int k = n; k < this.fC.length; ++k) {
                    final c c = this.fC[k];
                    if (c != null) {
                        pf.a(3, c);
                    }
                }
            }
            super.a(pf);
        }
        
        @Override
        protected int c() {
            final int n = 0;
            int c;
            int n2 = c = super.c();
            if (this.fA != null) {
                c = n2;
                if (this.fA.length > 0) {
                    int n3;
                    for (int i = 0; i < this.fA.length; ++i, n2 = n3) {
                        final com.google.android.gms.internal.d.a a = this.fA[i];
                        n3 = n2;
                        if (a != null) {
                            n3 = n2 + pf.c(1, a);
                        }
                    }
                    c = n2;
                }
            }
            int n4 = c;
            if (this.fB != null) {
                n4 = c;
                if (this.fB.length > 0) {
                    n4 = c;
                    int n5;
                    for (int j = 0; j < this.fB.length; ++j, n4 = n5) {
                        final com.google.android.gms.internal.d.a a2 = this.fB[j];
                        n5 = n4;
                        if (a2 != null) {
                            n5 = n4 + pf.c(2, a2);
                        }
                    }
                }
            }
            int n6 = n4;
            if (this.fC != null) {
                n6 = n4;
                if (this.fC.length > 0) {
                    int n7 = n;
                    while (true) {
                        n6 = n4;
                        if (n7 >= this.fC.length) {
                            break;
                        }
                        final c c2 = this.fC[n7];
                        int n8 = n4;
                        if (c2 != null) {
                            n8 = n4 + pf.c(3, c2);
                        }
                        ++n7;
                        n4 = n8;
                    }
                }
            }
            return n6;
        }
        
        public d e(final pe pe) throws IOException {
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
                    case 10: {
                        final int b = pp.b(pe, 10);
                        int length;
                        if (this.fA == null) {
                            length = 0;
                        }
                        else {
                            length = this.fA.length;
                        }
                        final com.google.android.gms.internal.d.a[] fa = new com.google.android.gms.internal.d.a[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.fA, 0, fa, 0, length);
                            i = length;
                        }
                        while (i < fa.length - 1) {
                            pe.a(fa[i] = new com.google.android.gms.internal.d.a());
                            pe.qg();
                            ++i;
                        }
                        pe.a(fa[i] = new com.google.android.gms.internal.d.a());
                        this.fA = fa;
                        continue;
                    }
                    case 18: {
                        final int b2 = pp.b(pe, 18);
                        int length2;
                        if (this.fB == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.fB.length;
                        }
                        final com.google.android.gms.internal.d.a[] fb = new com.google.android.gms.internal.d.a[b2 + length2];
                        int j = length2;
                        if (length2 != 0) {
                            System.arraycopy(this.fB, 0, fb, 0, length2);
                            j = length2;
                        }
                        while (j < fb.length - 1) {
                            pe.a(fb[j] = new com.google.android.gms.internal.d.a());
                            pe.qg();
                            ++j;
                        }
                        pe.a(fb[j] = new com.google.android.gms.internal.d.a());
                        this.fB = fb;
                        continue;
                    }
                    case 26: {
                        final int b3 = pp.b(pe, 26);
                        int length3;
                        if (this.fC == null) {
                            length3 = 0;
                        }
                        else {
                            length3 = this.fC.length;
                        }
                        final c[] fc = new c[b3 + length3];
                        int k = length3;
                        if (length3 != 0) {
                            System.arraycopy(this.fC, 0, fc, 0, length3);
                            k = length3;
                        }
                        while (k < fc.length - 1) {
                            pe.a(fc[k] = new c());
                            pe.qg();
                            ++k;
                        }
                        pe.a(fc[k] = new c());
                        this.fC = fc;
                        continue;
                    }
                }
            }
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
                if (o instanceof d) {
                    final d d = (d)o;
                    b2 = b;
                    if (pk.equals(this.fA, d.fA)) {
                        b2 = b;
                        if (pk.equals(this.fB, d.fB)) {
                            b2 = b;
                            if (pk.equals(this.fC, d.fC)) {
                                return this.a(d);
                            }
                        }
                    }
                }
            }
            return b2;
        }
        
        public d h() {
            this.fA = com.google.android.gms.internal.d.a.r();
            this.fB = com.google.android.gms.internal.d.a.r();
            this.fC = c.f();
            this.awy = null;
            this.awJ = -1;
            return this;
        }
        
        @Override
        public int hashCode() {
            return (((pk.hashCode(this.fA) + 527) * 31 + pk.hashCode(this.fB)) * 31 + pk.hashCode(this.fC)) * 31 + this.qx();
        }
    }
    
    public static final class e extends pg<e>
    {
        private static volatile e[] fD;
        public int key;
        public int value;
        
        public e() {
            this.j();
        }
        
        public static e[] i() {
            Label_0027: {
                if (e.fD != null) {
                    break Label_0027;
                }
                synchronized (pk.awI) {
                    if (e.fD == null) {
                        e.fD = new e[0];
                    }
                    return e.fD;
                }
            }
        }
        
        @Override
        public void a(final pf pf) throws IOException {
            pf.s(1, this.key);
            pf.s(2, this.value);
            super.a(pf);
        }
        
        @Override
        protected int c() {
            return super.c() + pf.u(1, this.key) + pf.u(2, this.value);
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
                if (o instanceof e) {
                    final e e = (e)o;
                    b2 = b;
                    if (this.key == e.key) {
                        b2 = b;
                        if (this.value == e.value) {
                            return this.a(e);
                        }
                    }
                }
            }
            return b2;
        }
        
        public e f(final pe pe) throws IOException {
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
                    case 8: {
                        this.key = pe.qj();
                        continue;
                    }
                    case 16: {
                        this.value = pe.qj();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        public int hashCode() {
            return ((this.key + 527) * 31 + this.value) * 31 + this.qx();
        }
        
        public e j() {
            this.key = 0;
            this.value = 0;
            this.awy = null;
            this.awJ = -1;
            return this;
        }
    }
    
    public static final class f extends pg<f>
    {
        public String[] fE;
        public String[] fF;
        public com.google.android.gms.internal.d.a[] fG;
        public e[] fH;
        public b[] fI;
        public b[] fJ;
        public b[] fK;
        public g[] fL;
        public String fM;
        public String fN;
        public String fO;
        public a fP;
        public float fQ;
        public boolean fR;
        public String[] fS;
        public int fT;
        public String version;
        
        public f() {
            this.k();
        }
        
        public static f a(final byte[] array) throws pl {
            return pm.a(new f(), array);
        }
        
        @Override
        public void a(final pf pf) throws IOException {
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
                    final com.google.android.gms.internal.d.a a = this.fG[j];
                    if (a != null) {
                        pf.a(2, a);
                    }
                }
            }
            if (this.fH != null && this.fH.length > 0) {
                for (int k = 0; k < this.fH.length; ++k) {
                    final e e = this.fH[k];
                    if (e != null) {
                        pf.a(3, e);
                    }
                }
            }
            if (this.fI != null && this.fI.length > 0) {
                for (int l = 0; l < this.fI.length; ++l) {
                    final b b = this.fI[l];
                    if (b != null) {
                        pf.a(4, b);
                    }
                }
            }
            if (this.fJ != null && this.fJ.length > 0) {
                for (int n2 = 0; n2 < this.fJ.length; ++n2) {
                    final b b2 = this.fJ[n2];
                    if (b2 != null) {
                        pf.a(5, b2);
                    }
                }
            }
            if (this.fK != null && this.fK.length > 0) {
                for (int n3 = 0; n3 < this.fK.length; ++n3) {
                    final b b3 = this.fK[n3];
                    if (b3 != null) {
                        pf.a(6, b3);
                    }
                }
            }
            if (this.fL != null && this.fL.length > 0) {
                for (int n4 = 0; n4 < this.fL.length; ++n4) {
                    final g g = this.fL[n4];
                    if (g != null) {
                        pf.a(7, g);
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
                        final com.google.android.gms.internal.d.a a = this.fG[j];
                        n8 = n7;
                        if (a != null) {
                            n8 = n7 + pf.c(2, a);
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
                        final e e = this.fH[k];
                        n10 = n7;
                        if (e != null) {
                            n10 = n7 + pf.c(3, e);
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
                        final b b = this.fI[l];
                        n12 = n11;
                        if (b != null) {
                            n12 = n11 + pf.c(4, b);
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
                        final b b2 = this.fJ[n14];
                        n15 = n11;
                        if (b2 != null) {
                            n15 = n11 + pf.c(5, b2);
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
                        final b b3 = this.fK[n17];
                        n18 = n16;
                        if (b3 != null) {
                            n18 = n16 + pf.c(6, b3);
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
                        final g g = this.fL[n20];
                        n21 = n16;
                        if (g != null) {
                            n21 = n16 + pf.c(7, g);
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
                if (o instanceof f) {
                    final f f = (f)o;
                    b2 = b;
                    if (pk.equals(this.fE, f.fE)) {
                        b2 = b;
                        if (pk.equals(this.fF, f.fF)) {
                            b2 = b;
                            if (pk.equals(this.fG, f.fG)) {
                                b2 = b;
                                if (pk.equals(this.fH, f.fH)) {
                                    b2 = b;
                                    if (pk.equals(this.fI, f.fI)) {
                                        b2 = b;
                                        if (pk.equals(this.fJ, f.fJ)) {
                                            b2 = b;
                                            if (pk.equals(this.fK, f.fK)) {
                                                b2 = b;
                                                if (pk.equals(this.fL, f.fL)) {
                                                    if (this.fM == null) {
                                                        b2 = b;
                                                        if (f.fM != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.fM.equals(f.fM)) {
                                                        return false;
                                                    }
                                                    if (this.fN == null) {
                                                        b2 = b;
                                                        if (f.fN != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.fN.equals(f.fN)) {
                                                        return false;
                                                    }
                                                    if (this.fO == null) {
                                                        b2 = b;
                                                        if (f.fO != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.fO.equals(f.fO)) {
                                                        return false;
                                                    }
                                                    if (this.version == null) {
                                                        b2 = b;
                                                        if (f.version != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.version.equals(f.version)) {
                                                        return false;
                                                    }
                                                    if (this.fP == null) {
                                                        b2 = b;
                                                        if (f.fP != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.fP.equals(f.fP)) {
                                                        return false;
                                                    }
                                                    b2 = b;
                                                    if (Float.floatToIntBits(this.fQ) == Float.floatToIntBits(f.fQ)) {
                                                        b2 = b;
                                                        if (this.fR == f.fR) {
                                                            b2 = b;
                                                            if (pk.equals(this.fS, f.fS)) {
                                                                b2 = b;
                                                                if (this.fT == f.fT) {
                                                                    return this.a(f);
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
        
        public f g(final pe pe) throws IOException {
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
                        final com.google.android.gms.internal.d.a[] fg = new com.google.android.gms.internal.d.a[b2 + length2];
                        int j = length2;
                        if (length2 != 0) {
                            System.arraycopy(this.fG, 0, fg, 0, length2);
                            j = length2;
                        }
                        while (j < fg.length - 1) {
                            pe.a(fg[j] = new com.google.android.gms.internal.d.a());
                            pe.qg();
                            ++j;
                        }
                        pe.a(fg[j] = new com.google.android.gms.internal.d.a());
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
                        final e[] fh = new e[b3 + length3];
                        int k = length3;
                        if (length3 != 0) {
                            System.arraycopy(this.fH, 0, fh, 0, length3);
                            k = length3;
                        }
                        while (k < fh.length - 1) {
                            pe.a(fh[k] = new e());
                            pe.qg();
                            ++k;
                        }
                        pe.a(fh[k] = new e());
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
                        final b[] fi = new b[b4 + length4];
                        int l = length4;
                        if (length4 != 0) {
                            System.arraycopy(this.fI, 0, fi, 0, length4);
                            l = length4;
                        }
                        while (l < fi.length - 1) {
                            pe.a(fi[l] = new b());
                            pe.qg();
                            ++l;
                        }
                        pe.a(fi[l] = new b());
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
                        final b[] fj = new b[b5 + length5];
                        int n = length5;
                        if (length5 != 0) {
                            System.arraycopy(this.fJ, 0, fj, 0, length5);
                            n = length5;
                        }
                        while (n < fj.length - 1) {
                            pe.a(fj[n] = new b());
                            pe.qg();
                            ++n;
                        }
                        pe.a(fj[n] = new b());
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
                        final b[] fk = new b[b6 + length6];
                        int n2 = length6;
                        if (length6 != 0) {
                            System.arraycopy(this.fK, 0, fk, 0, length6);
                            n2 = length6;
                        }
                        while (n2 < fk.length - 1) {
                            pe.a(fk[n2] = new b());
                            pe.qg();
                            ++n2;
                        }
                        pe.a(fk[n2] = new b());
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
                        final g[] fl = new g[b7 + length7];
                        int n3 = length7;
                        if (length7 != 0) {
                            System.arraycopy(this.fL, 0, fl, 0, length7);
                            n3 = length7;
                        }
                        while (n3 < fl.length - 1) {
                            pe.a(fl[n3] = new g());
                            pe.qg();
                            ++n3;
                        }
                        pe.a(fl[n3] = new g());
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
                            this.fP = new a();
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
        
        public f k() {
            this.fE = pp.awQ;
            this.fF = pp.awQ;
            this.fG = com.google.android.gms.internal.d.a.r();
            this.fH = e.i();
            this.fI = b.d();
            this.fJ = b.d();
            this.fK = b.d();
            this.fL = g.l();
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
    
    public static final class g extends pg<g>
    {
        private static volatile g[] fU;
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
        
        public g() {
            this.m();
        }
        
        public static g[] l() {
            Label_0027: {
                if (g.fU != null) {
                    break Label_0027;
                }
                synchronized (pk.awI) {
                    if (g.fU == null) {
                        g.fU = new g[0];
                    }
                    return g.fU;
                }
            }
        }
        
        @Override
        public void a(final pf pf) throws IOException {
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
                if (o instanceof g) {
                    final g g = (g)o;
                    b2 = b;
                    if (pk.equals(this.fV, g.fV)) {
                        b2 = b;
                        if (pk.equals(this.fW, g.fW)) {
                            b2 = b;
                            if (pk.equals(this.fX, g.fX)) {
                                b2 = b;
                                if (pk.equals(this.fY, g.fY)) {
                                    b2 = b;
                                    if (pk.equals(this.fZ, g.fZ)) {
                                        b2 = b;
                                        if (pk.equals(this.ga, g.ga)) {
                                            b2 = b;
                                            if (pk.equals(this.gb, g.gb)) {
                                                b2 = b;
                                                if (pk.equals(this.gc, g.gc)) {
                                                    b2 = b;
                                                    if (pk.equals(this.gd, g.gd)) {
                                                        b2 = b;
                                                        if (pk.equals(this.ge, g.ge)) {
                                                            return this.a(g);
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
        
        public g h(final pe pe) throws IOException {
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
        
        public g m() {
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
    
    public static final class h extends pg<h>
    {
        public static final ph<com.google.android.gms.internal.d.a, h> gf;
        private static final h[] gg;
        public int[] gh;
        public int[] gi;
        public int[] gj;
        public int gk;
        public int[] gl;
        public int gm;
        public int gn;
        
        static {
            gf = ph.a(11, h.class, 810);
            gg = new h[0];
        }
        
        public h() {
            this.n();
        }
        
        @Override
        public void a(final pf pf) throws IOException {
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
                if (o instanceof h) {
                    final h h = (h)o;
                    b2 = b;
                    if (pk.equals(this.gh, h.gh)) {
                        b2 = b;
                        if (pk.equals(this.gi, h.gi)) {
                            b2 = b;
                            if (pk.equals(this.gj, h.gj)) {
                                b2 = b;
                                if (this.gk == h.gk) {
                                    b2 = b;
                                    if (pk.equals(this.gl, h.gl)) {
                                        b2 = b;
                                        if (this.gm == h.gm) {
                                            b2 = b;
                                            if (this.gn == h.gn) {
                                                return this.a(h);
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
        
        public h i(final pe pe) throws IOException {
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
        
        public h n() {
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
    
    public static final class i extends pg<i>
    {
        private static volatile i[] go;
        public com.google.android.gms.internal.d.a gp;
        public d gq;
        public String name;
        
        public i() {
            this.p();
        }
        
        public static i[] o() {
            Label_0027: {
                if (i.go != null) {
                    break Label_0027;
                }
                synchronized (pk.awI) {
                    if (i.go == null) {
                        i.go = new i[0];
                    }
                    return i.go;
                }
            }
        }
        
        @Override
        public void a(final pf pf) throws IOException {
            if (!this.name.equals("")) {
                pf.b(1, this.name);
            }
            if (this.gp != null) {
                pf.a(2, this.gp);
            }
            if (this.gq != null) {
                pf.a(3, this.gq);
            }
            super.a(pf);
        }
        
        @Override
        protected int c() {
            int c;
            final int n = c = super.c();
            if (!this.name.equals("")) {
                c = n + pf.j(1, this.name);
            }
            int n2 = c;
            if (this.gp != null) {
                n2 = c + pf.c(2, this.gp);
            }
            int n3 = n2;
            if (this.gq != null) {
                n3 = n2 + pf.c(3, this.gq);
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
                if (o instanceof i) {
                    final i i = (i)o;
                    if (this.name == null) {
                        b2 = b;
                        if (i.name != null) {
                            return b2;
                        }
                    }
                    else if (!this.name.equals(i.name)) {
                        return false;
                    }
                    if (this.gp == null) {
                        b2 = b;
                        if (i.gp != null) {
                            return b2;
                        }
                    }
                    else if (!this.gp.equals(i.gp)) {
                        return false;
                    }
                    if (this.gq == null) {
                        b2 = b;
                        if (i.gq != null) {
                            return b2;
                        }
                    }
                    else if (!this.gq.equals(i.gq)) {
                        return false;
                    }
                    return this.a(i);
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            int hashCode2;
            if (this.name == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.name.hashCode();
            }
            int hashCode3;
            if (this.gp == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.gp.hashCode();
            }
            if (this.gq != null) {
                hashCode = this.gq.hashCode();
            }
            return ((hashCode3 + (hashCode2 + 527) * 31) * 31 + hashCode) * 31 + this.qx();
        }
        
        public i j(final pe pe) throws IOException {
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
                    case 10: {
                        this.name = pe.readString();
                        continue;
                    }
                    case 18: {
                        if (this.gp == null) {
                            this.gp = new com.google.android.gms.internal.d.a();
                        }
                        pe.a(this.gp);
                        continue;
                    }
                    case 26: {
                        if (this.gq == null) {
                            this.gq = new d();
                        }
                        pe.a(this.gq);
                        continue;
                    }
                }
            }
            return this;
        }
        
        public i p() {
            this.name = "";
            this.gp = null;
            this.gq = null;
            this.awy = null;
            this.awJ = -1;
            return this;
        }
    }
    
    public static final class j extends pg<j>
    {
        public i[] gr;
        public f gs;
        public String gt;
        
        public j() {
            this.q();
        }
        
        public static j b(final byte[] array) throws pl {
            return pm.a(new j(), array);
        }
        
        @Override
        public void a(final pf pf) throws IOException {
            if (this.gr != null && this.gr.length > 0) {
                for (int i = 0; i < this.gr.length; ++i) {
                    final i j = this.gr[i];
                    if (j != null) {
                        pf.a(1, j);
                    }
                }
            }
            if (this.gs != null) {
                pf.a(2, this.gs);
            }
            if (!this.gt.equals("")) {
                pf.b(3, this.gt);
            }
            super.a(pf);
        }
        
        @Override
        protected int c() {
            int c;
            int n = c = super.c();
            if (this.gr != null) {
                c = n;
                if (this.gr.length > 0) {
                    int n2 = 0;
                    while (true) {
                        c = n;
                        if (n2 >= this.gr.length) {
                            break;
                        }
                        final i i = this.gr[n2];
                        int n3 = n;
                        if (i != null) {
                            n3 = n + pf.c(1, i);
                        }
                        ++n2;
                        n = n3;
                    }
                }
            }
            int n4 = c;
            if (this.gs != null) {
                n4 = c + pf.c(2, this.gs);
            }
            int n5 = n4;
            if (!this.gt.equals("")) {
                n5 = n4 + pf.j(3, this.gt);
            }
            return n5;
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
                if (o instanceof j) {
                    final j j = (j)o;
                    b2 = b;
                    if (pk.equals(this.gr, j.gr)) {
                        if (this.gs == null) {
                            b2 = b;
                            if (j.gs != null) {
                                return b2;
                            }
                        }
                        else if (!this.gs.equals(j.gs)) {
                            return false;
                        }
                        if (this.gt == null) {
                            b2 = b;
                            if (j.gt != null) {
                                return b2;
                            }
                        }
                        else if (!this.gt.equals(j.gt)) {
                            return false;
                        }
                        return this.a(j);
                    }
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            final int hashCode2 = pk.hashCode(this.gr);
            int hashCode3;
            if (this.gs == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.gs.hashCode();
            }
            if (this.gt != null) {
                hashCode = this.gt.hashCode();
            }
            return ((hashCode3 + (hashCode2 + 527) * 31) * 31 + hashCode) * 31 + this.qx();
        }
        
        public j k(final pe pe) throws IOException {
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
                    case 10: {
                        final int b = pp.b(pe, 10);
                        int length;
                        if (this.gr == null) {
                            length = 0;
                        }
                        else {
                            length = this.gr.length;
                        }
                        final i[] gr = new i[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.gr, 0, gr, 0, length);
                            i = length;
                        }
                        while (i < gr.length - 1) {
                            pe.a(gr[i] = new i());
                            pe.qg();
                            ++i;
                        }
                        pe.a(gr[i] = new i());
                        this.gr = gr;
                        continue;
                    }
                    case 18: {
                        if (this.gs == null) {
                            this.gs = new f();
                        }
                        pe.a(this.gs);
                        continue;
                    }
                    case 26: {
                        this.gt = pe.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        public j q() {
            this.gr = i.o();
            this.gs = null;
            this.gt = "";
            this.awy = null;
            this.awJ = -1;
            return this;
        }
    }
}
