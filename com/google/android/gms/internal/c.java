// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public interface c
{
    public static final class a extends kp<a>
    {
        public int eE;
        public int eF;
        public int level;
        
        public a() {
            this.b();
        }
        
        public a a(final kn kn) throws IOException {
        Label_0057:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0057;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0057;
                    }
                    case 8: {
                        final int mk = kn.mk();
                        switch (mk) {
                            default: {
                                continue;
                            }
                            case 1:
                            case 2:
                            case 3: {
                                this.level = mk;
                                continue;
                            }
                        }
                        break;
                    }
                    case 16: {
                        this.eE = kn.mk();
                        continue;
                    }
                    case 24: {
                        this.eF = kn.mk();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            if (this.level != 1) {
                ko.i(1, this.level);
            }
            if (this.eE != 0) {
                ko.i(2, this.eE);
            }
            if (this.eF != 0) {
                ko.i(3, this.eF);
            }
            super.a(ko);
        }
        
        public a b() {
            this.level = 1;
            this.eE = 0;
            this.eF = 0;
            this.adU = null;
            this.adY = -1;
            return this;
        }
        
        @Override
        public int c() {
            int c;
            final int n = c = super.c();
            if (this.level != 1) {
                c = n + ko.j(1, this.level);
            }
            int n2 = c;
            if (this.eE != 0) {
                n2 = c + ko.j(2, this.eE);
            }
            int adY = n2;
            if (this.eF != 0) {
                adY = n2 + ko.j(3, this.eF);
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
                    if (this.level == a.level) {
                        b2 = b;
                        if (this.eE == a.eE) {
                            b2 = b;
                            if (this.eF == a.eF) {
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
            return b2;
        }
        
        @Override
        public int hashCode() {
            final int level = this.level;
            final int ee = this.eE;
            final int ef = this.eF;
            int hashCode;
            if (this.adU == null || this.adU.isEmpty()) {
                hashCode = 0;
            }
            else {
                hashCode = this.adU.hashCode();
            }
            return hashCode + (((level + 527) * 31 + ee) * 31 + ef) * 31;
        }
    }
    
    public static final class b extends kp<b>
    {
        private static volatile b[] eG;
        public int[] eH;
        public int eI;
        public boolean eJ;
        public boolean eK;
        public int name;
        
        public b() {
            this.e();
        }
        
        public static b[] d() {
            Label_0027: {
                if (b.eG != null) {
                    break Label_0027;
                }
                synchronized (kr.adX) {
                    if (b.eG == null) {
                        b.eG = new b[0];
                    }
                    return b.eG;
                }
            }
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            if (this.eK) {
                ko.a(1, this.eK);
            }
            ko.i(2, this.eI);
            if (this.eH != null && this.eH.length > 0) {
                for (int i = 0; i < this.eH.length; ++i) {
                    ko.i(3, this.eH[i]);
                }
            }
            if (this.name != 0) {
                ko.i(4, this.name);
            }
            if (this.eJ) {
                ko.a(6, this.eJ);
            }
            super.a(ko);
        }
        
        @Override
        public int c() {
            int n = 0;
            int c = super.c();
            if (this.eK) {
                c += ko.b(1, this.eK);
            }
            final int n2 = ko.j(2, this.eI) + c;
            int n3;
            if (this.eH != null && this.eH.length > 0) {
                for (int i = 0; i < this.eH.length; ++i) {
                    n += ko.cX(this.eH[i]);
                }
                n3 = n2 + n + this.eH.length * 1;
            }
            else {
                n3 = n2;
            }
            int n4 = n3;
            if (this.name != 0) {
                n4 = n3 + ko.j(4, this.name);
            }
            int adY = n4;
            if (this.eJ) {
                adY = n4 + ko.b(6, this.eJ);
            }
            return this.adY = adY;
        }
        
        public b c(final kn kn) throws IOException {
        Label_0081:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0081;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0081;
                    }
                    case 8: {
                        this.eK = kn.ml();
                        continue;
                    }
                    case 16: {
                        this.eI = kn.mk();
                        continue;
                    }
                    case 24: {
                        final int b = kw.b(kn, 24);
                        int length;
                        if (this.eH == null) {
                            length = 0;
                        }
                        else {
                            length = this.eH.length;
                        }
                        final int[] eh = new int[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.eH, 0, eh, 0, length);
                            i = length;
                        }
                        while (i < eh.length - 1) {
                            eh[i] = kn.mk();
                            kn.mh();
                            ++i;
                        }
                        eh[i] = kn.mk();
                        this.eH = eh;
                        continue;
                    }
                    case 26: {
                        final int cr = kn.cR(kn.mn());
                        final int position = kn.getPosition();
                        int n = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n;
                        }
                        kn.cT(position);
                        int length2;
                        if (this.eH == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.eH.length;
                        }
                        final int[] eh2 = new int[n + length2];
                        int j = length2;
                        if (length2 != 0) {
                            System.arraycopy(this.eH, 0, eh2, 0, length2);
                            j = length2;
                        }
                        while (j < eh2.length) {
                            eh2[j] = kn.mk();
                            ++j;
                        }
                        this.eH = eh2;
                        kn.cS(cr);
                        continue;
                    }
                    case 32: {
                        this.name = kn.mk();
                        continue;
                    }
                    case 48: {
                        this.eJ = kn.ml();
                        continue;
                    }
                }
            }
            return this;
        }
        
        public b e() {
            this.eH = kw.aea;
            this.eI = 0;
            this.name = 0;
            this.eJ = false;
            this.eK = false;
            this.adU = null;
            this.adY = -1;
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
                    if (kr.equals(this.eH, b3.eH)) {
                        b2 = b;
                        if (this.eI == b3.eI) {
                            b2 = b;
                            if (this.name == b3.name) {
                                b2 = b;
                                if (this.eJ == b3.eJ) {
                                    b2 = b;
                                    if (this.eK == b3.eK) {
                                        if (this.adU == null || this.adU.isEmpty()) {
                                            if (b3.adU != null) {
                                                b2 = b;
                                                if (!b3.adU.isEmpty()) {
                                                    return b2;
                                                }
                                            }
                                            return true;
                                        }
                                        return this.adU.equals(b3.adU);
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
            final int hashCode = kr.hashCode(this.eH);
            final int ei = this.eI;
            final int name = this.name;
            int n2;
            if (this.eJ) {
                n2 = 1231;
            }
            else {
                n2 = 1237;
            }
            if (!this.eK) {
                n = 1237;
            }
            int hashCode2;
            if (this.adU == null || this.adU.isEmpty()) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.adU.hashCode();
            }
            return hashCode2 + ((n2 + (((hashCode + 527) * 31 + ei) * 31 + name) * 31) * 31 + n) * 31;
        }
    }
    
    public static final class c extends kp<c>
    {
        private static volatile c[] eL;
        public String eM;
        public long eN;
        public long eO;
        public boolean eP;
        public long eQ;
        
        public c() {
            this.g();
        }
        
        public static c[] f() {
            Label_0027: {
                if (c.eL != null) {
                    break Label_0027;
                }
                synchronized (kr.adX) {
                    if (c.eL == null) {
                        c.eL = new c[0];
                    }
                    return c.eL;
                }
            }
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            if (!this.eM.equals("")) {
                ko.b(1, this.eM);
            }
            if (this.eN != 0L) {
                ko.b(2, this.eN);
            }
            if (this.eO != 2147483647L) {
                ko.b(3, this.eO);
            }
            if (this.eP) {
                ko.a(4, this.eP);
            }
            if (this.eQ != 0L) {
                ko.b(5, this.eQ);
            }
            super.a(ko);
        }
        
        @Override
        public int c() {
            int c;
            final int n = c = super.c();
            if (!this.eM.equals("")) {
                c = n + ko.g(1, this.eM);
            }
            int n2 = c;
            if (this.eN != 0L) {
                n2 = c + ko.d(2, this.eN);
            }
            int n3 = n2;
            if (this.eO != 2147483647L) {
                n3 = n2 + ko.d(3, this.eO);
            }
            int n4 = n3;
            if (this.eP) {
                n4 = n3 + ko.b(4, this.eP);
            }
            int adY = n4;
            if (this.eQ != 0L) {
                adY = n4 + ko.d(5, this.eQ);
            }
            return this.adY = adY;
        }
        
        public c d(final kn kn) throws IOException {
        Label_0073:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0073;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0073;
                    }
                    case 10: {
                        this.eM = kn.readString();
                        continue;
                    }
                    case 16: {
                        this.eN = kn.mj();
                        continue;
                    }
                    case 24: {
                        this.eO = kn.mj();
                        continue;
                    }
                    case 32: {
                        this.eP = kn.ml();
                        continue;
                    }
                    case 40: {
                        this.eQ = kn.mj();
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
                    if (this.eM == null) {
                        b2 = b;
                        if (c.eM != null) {
                            return b2;
                        }
                    }
                    else if (!this.eM.equals(c.eM)) {
                        return false;
                    }
                    b2 = b;
                    if (this.eN == c.eN) {
                        b2 = b;
                        if (this.eO == c.eO) {
                            b2 = b;
                            if (this.eP == c.eP) {
                                b2 = b;
                                if (this.eQ == c.eQ) {
                                    if (this.adU == null || this.adU.isEmpty()) {
                                        if (c.adU != null) {
                                            b2 = b;
                                            if (!c.adU.isEmpty()) {
                                                return b2;
                                            }
                                        }
                                        return true;
                                    }
                                    return this.adU.equals(c.adU);
                                }
                            }
                        }
                    }
                }
            }
            return b2;
        }
        
        public c g() {
            this.eM = "";
            this.eN = 0L;
            this.eO = 2147483647L;
            this.eP = false;
            this.eQ = 0L;
            this.adU = null;
            this.adY = -1;
            return this;
        }
        
        @Override
        public int hashCode() {
            final boolean b = false;
            int hashCode;
            if (this.eM == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.eM.hashCode();
            }
            final int n = (int)(this.eN ^ this.eN >>> 32);
            final int n2 = (int)(this.eO ^ this.eO >>> 32);
            int n3;
            if (this.eP) {
                n3 = 1231;
            }
            else {
                n3 = 1237;
            }
            final int n4 = (int)(this.eQ ^ this.eQ >>> 32);
            int hashCode2 = b ? 1 : 0;
            if (this.adU != null) {
                if (this.adU.isEmpty()) {
                    hashCode2 = (b ? 1 : 0);
                }
                else {
                    hashCode2 = this.adU.hashCode();
                }
            }
            return ((n3 + (((hashCode + 527) * 31 + n) * 31 + n2) * 31) * 31 + n4) * 31 + hashCode2;
        }
    }
    
    public static final class d extends kp<d>
    {
        public com.google.android.gms.internal.d.a[] eR;
        public com.google.android.gms.internal.d.a[] eS;
        public c[] eT;
        
        public d() {
            this.h();
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            final int n = 0;
            if (this.eR != null && this.eR.length > 0) {
                for (int i = 0; i < this.eR.length; ++i) {
                    final com.google.android.gms.internal.d.a a = this.eR[i];
                    if (a != null) {
                        ko.a(1, a);
                    }
                }
            }
            if (this.eS != null && this.eS.length > 0) {
                for (int j = 0; j < this.eS.length; ++j) {
                    final com.google.android.gms.internal.d.a a2 = this.eS[j];
                    if (a2 != null) {
                        ko.a(2, a2);
                    }
                }
            }
            if (this.eT != null && this.eT.length > 0) {
                for (int k = n; k < this.eT.length; ++k) {
                    final c c = this.eT[k];
                    if (c != null) {
                        ko.a(3, c);
                    }
                }
            }
            super.a(ko);
        }
        
        @Override
        public int c() {
            final int n = 0;
            int c;
            int n2 = c = super.c();
            if (this.eR != null) {
                c = n2;
                if (this.eR.length > 0) {
                    int n3;
                    for (int i = 0; i < this.eR.length; ++i, n2 = n3) {
                        final com.google.android.gms.internal.d.a a = this.eR[i];
                        n3 = n2;
                        if (a != null) {
                            n3 = n2 + ko.b(1, a);
                        }
                    }
                    c = n2;
                }
            }
            int n4 = c;
            if (this.eS != null) {
                n4 = c;
                if (this.eS.length > 0) {
                    n4 = c;
                    int n5;
                    for (int j = 0; j < this.eS.length; ++j, n4 = n5) {
                        final com.google.android.gms.internal.d.a a2 = this.eS[j];
                        n5 = n4;
                        if (a2 != null) {
                            n5 = n4 + ko.b(2, a2);
                        }
                    }
                }
            }
            int adY = n4;
            if (this.eT != null) {
                adY = n4;
                if (this.eT.length > 0) {
                    int n6 = n;
                    while (true) {
                        adY = n4;
                        if (n6 >= this.eT.length) {
                            break;
                        }
                        final c c2 = this.eT[n6];
                        int n7 = n4;
                        if (c2 != null) {
                            n7 = n4 + ko.b(3, c2);
                        }
                        ++n6;
                        n4 = n7;
                    }
                }
            }
            return this.adY = adY;
        }
        
        public d e(final kn kn) throws IOException {
        Label_0057:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0057;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0057;
                    }
                    case 10: {
                        final int b = kw.b(kn, 10);
                        int length;
                        if (this.eR == null) {
                            length = 0;
                        }
                        else {
                            length = this.eR.length;
                        }
                        final com.google.android.gms.internal.d.a[] er = new com.google.android.gms.internal.d.a[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.eR, 0, er, 0, length);
                            i = length;
                        }
                        while (i < er.length - 1) {
                            kn.a(er[i] = new com.google.android.gms.internal.d.a());
                            kn.mh();
                            ++i;
                        }
                        kn.a(er[i] = new com.google.android.gms.internal.d.a());
                        this.eR = er;
                        continue;
                    }
                    case 18: {
                        final int b2 = kw.b(kn, 18);
                        int length2;
                        if (this.eS == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.eS.length;
                        }
                        final com.google.android.gms.internal.d.a[] es = new com.google.android.gms.internal.d.a[b2 + length2];
                        int j = length2;
                        if (length2 != 0) {
                            System.arraycopy(this.eS, 0, es, 0, length2);
                            j = length2;
                        }
                        while (j < es.length - 1) {
                            kn.a(es[j] = new com.google.android.gms.internal.d.a());
                            kn.mh();
                            ++j;
                        }
                        kn.a(es[j] = new com.google.android.gms.internal.d.a());
                        this.eS = es;
                        continue;
                    }
                    case 26: {
                        final int b3 = kw.b(kn, 26);
                        int length3;
                        if (this.eT == null) {
                            length3 = 0;
                        }
                        else {
                            length3 = this.eT.length;
                        }
                        final c[] et = new c[b3 + length3];
                        int k = length3;
                        if (length3 != 0) {
                            System.arraycopy(this.eT, 0, et, 0, length3);
                            k = length3;
                        }
                        while (k < et.length - 1) {
                            kn.a(et[k] = new c());
                            kn.mh();
                            ++k;
                        }
                        kn.a(et[k] = new c());
                        this.eT = et;
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
                    if (kr.equals(this.eR, d.eR)) {
                        b2 = b;
                        if (kr.equals(this.eS, d.eS)) {
                            b2 = b;
                            if (kr.equals(this.eT, d.eT)) {
                                if (this.adU == null || this.adU.isEmpty()) {
                                    if (d.adU != null) {
                                        b2 = b;
                                        if (!d.adU.isEmpty()) {
                                            return b2;
                                        }
                                    }
                                    return true;
                                }
                                return this.adU.equals(d.adU);
                            }
                        }
                    }
                }
            }
            return b2;
        }
        
        public d h() {
            this.eR = com.google.android.gms.internal.d.a.r();
            this.eS = com.google.android.gms.internal.d.a.r();
            this.eT = c.f();
            this.adU = null;
            this.adY = -1;
            return this;
        }
        
        @Override
        public int hashCode() {
            final int hashCode = kr.hashCode(this.eR);
            final int hashCode2 = kr.hashCode(this.eS);
            final int hashCode3 = kr.hashCode(this.eT);
            int hashCode4;
            if (this.adU == null || this.adU.isEmpty()) {
                hashCode4 = 0;
            }
            else {
                hashCode4 = this.adU.hashCode();
            }
            return hashCode4 + (((hashCode + 527) * 31 + hashCode2) * 31 + hashCode3) * 31;
        }
    }
    
    public static final class e extends kp<e>
    {
        private static volatile e[] eU;
        public int key;
        public int value;
        
        public e() {
            this.j();
        }
        
        public static e[] i() {
            Label_0027: {
                if (e.eU != null) {
                    break Label_0027;
                }
                synchronized (kr.adX) {
                    if (e.eU == null) {
                        e.eU = new e[0];
                    }
                    return e.eU;
                }
            }
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            ko.i(1, this.key);
            ko.i(2, this.value);
            super.a(ko);
        }
        
        @Override
        public int c() {
            return this.adY = super.c() + ko.j(1, this.key) + ko.j(2, this.value);
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
                            if (this.adU == null || this.adU.isEmpty()) {
                                if (e.adU != null) {
                                    b2 = b;
                                    if (!e.adU.isEmpty()) {
                                        return b2;
                                    }
                                }
                                return true;
                            }
                            return this.adU.equals(e.adU);
                        }
                    }
                }
            }
            return b2;
        }
        
        public e f(final kn kn) throws IOException {
        Label_0049:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0049;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0049;
                    }
                    case 8: {
                        this.key = kn.mk();
                        continue;
                    }
                    case 16: {
                        this.value = kn.mk();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        public int hashCode() {
            final int key = this.key;
            final int value = this.value;
            int hashCode;
            if (this.adU == null || this.adU.isEmpty()) {
                hashCode = 0;
            }
            else {
                hashCode = this.adU.hashCode();
            }
            return hashCode + ((key + 527) * 31 + value) * 31;
        }
        
        public e j() {
            this.key = 0;
            this.value = 0;
            this.adU = null;
            this.adY = -1;
            return this;
        }
    }
    
    public static final class f extends kp<f>
    {
        public String[] eV;
        public String[] eW;
        public com.google.android.gms.internal.d.a[] eX;
        public e[] eY;
        public b[] eZ;
        public b[] fa;
        public b[] fb;
        public g[] fc;
        public String fd;
        public String fe;
        public String ff;
        public String fg;
        public a fh;
        public float fi;
        public boolean fj;
        public String[] fk;
        public int fl;
        
        public f() {
            this.k();
        }
        
        public static f a(final byte[] array) throws ks {
            return kt.a(new f(), array);
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            final int n = 0;
            if (this.eW != null && this.eW.length > 0) {
                for (int i = 0; i < this.eW.length; ++i) {
                    final String s = this.eW[i];
                    if (s != null) {
                        ko.b(1, s);
                    }
                }
            }
            if (this.eX != null && this.eX.length > 0) {
                for (int j = 0; j < this.eX.length; ++j) {
                    final com.google.android.gms.internal.d.a a = this.eX[j];
                    if (a != null) {
                        ko.a(2, a);
                    }
                }
            }
            if (this.eY != null && this.eY.length > 0) {
                for (int k = 0; k < this.eY.length; ++k) {
                    final e e = this.eY[k];
                    if (e != null) {
                        ko.a(3, e);
                    }
                }
            }
            if (this.eZ != null && this.eZ.length > 0) {
                for (int l = 0; l < this.eZ.length; ++l) {
                    final b b = this.eZ[l];
                    if (b != null) {
                        ko.a(4, b);
                    }
                }
            }
            if (this.fa != null && this.fa.length > 0) {
                for (int n2 = 0; n2 < this.fa.length; ++n2) {
                    final b b2 = this.fa[n2];
                    if (b2 != null) {
                        ko.a(5, b2);
                    }
                }
            }
            if (this.fb != null && this.fb.length > 0) {
                for (int n3 = 0; n3 < this.fb.length; ++n3) {
                    final b b3 = this.fb[n3];
                    if (b3 != null) {
                        ko.a(6, b3);
                    }
                }
            }
            if (this.fc != null && this.fc.length > 0) {
                for (int n4 = 0; n4 < this.fc.length; ++n4) {
                    final g g = this.fc[n4];
                    if (g != null) {
                        ko.a(7, g);
                    }
                }
            }
            if (!this.fd.equals("")) {
                ko.b(9, this.fd);
            }
            if (!this.fe.equals("")) {
                ko.b(10, this.fe);
            }
            if (!this.ff.equals("0")) {
                ko.b(12, this.ff);
            }
            if (!this.fg.equals("")) {
                ko.b(13, this.fg);
            }
            if (this.fh != null) {
                ko.a(14, this.fh);
            }
            if (Float.floatToIntBits(this.fi) != Float.floatToIntBits(0.0f)) {
                ko.b(15, this.fi);
            }
            if (this.fk != null && this.fk.length > 0) {
                for (int n5 = 0; n5 < this.fk.length; ++n5) {
                    final String s2 = this.fk[n5];
                    if (s2 != null) {
                        ko.b(16, s2);
                    }
                }
            }
            if (this.fl != 0) {
                ko.i(17, this.fl);
            }
            if (this.fj) {
                ko.a(18, this.fj);
            }
            if (this.eV != null && this.eV.length > 0) {
                for (int n6 = n; n6 < this.eV.length; ++n6) {
                    final String s3 = this.eV[n6];
                    if (s3 != null) {
                        ko.b(19, s3);
                    }
                }
            }
            super.a(ko);
        }
        
        @Override
        public int c() {
            final int n = 0;
            final int c = super.c();
            int n6;
            if (this.eW != null && this.eW.length > 0) {
                int i = 0;
                int n2 = 0;
                int n3 = 0;
                while (i < this.eW.length) {
                    final String s = this.eW[i];
                    int n4 = n2;
                    int n5 = n3;
                    if (s != null) {
                        n5 = n3 + 1;
                        n4 = n2 + ko.cf(s);
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
            if (this.eX != null) {
                n7 = n6;
                if (this.eX.length > 0) {
                    n7 = n6;
                    int n8;
                    for (int j = 0; j < this.eX.length; ++j, n7 = n8) {
                        final com.google.android.gms.internal.d.a a = this.eX[j];
                        n8 = n7;
                        if (a != null) {
                            n8 = n7 + ko.b(2, a);
                        }
                    }
                }
            }
            int n9 = n7;
            if (this.eY != null) {
                n9 = n7;
                if (this.eY.length > 0) {
                    int n10;
                    for (int k = 0; k < this.eY.length; ++k, n7 = n10) {
                        final e e = this.eY[k];
                        n10 = n7;
                        if (e != null) {
                            n10 = n7 + ko.b(3, e);
                        }
                    }
                    n9 = n7;
                }
            }
            int n11 = n9;
            if (this.eZ != null) {
                n11 = n9;
                if (this.eZ.length > 0) {
                    n11 = n9;
                    int n12;
                    for (int l = 0; l < this.eZ.length; ++l, n11 = n12) {
                        final b b = this.eZ[l];
                        n12 = n11;
                        if (b != null) {
                            n12 = n11 + ko.b(4, b);
                        }
                    }
                }
            }
            int n13 = n11;
            if (this.fa != null) {
                n13 = n11;
                if (this.fa.length > 0) {
                    int n15;
                    for (int n14 = 0; n14 < this.fa.length; ++n14, n11 = n15) {
                        final b b2 = this.fa[n14];
                        n15 = n11;
                        if (b2 != null) {
                            n15 = n11 + ko.b(5, b2);
                        }
                    }
                    n13 = n11;
                }
            }
            int n16 = n13;
            if (this.fb != null) {
                n16 = n13;
                if (this.fb.length > 0) {
                    n16 = n13;
                    int n18;
                    for (int n17 = 0; n17 < this.fb.length; ++n17, n16 = n18) {
                        final b b3 = this.fb[n17];
                        n18 = n16;
                        if (b3 != null) {
                            n18 = n16 + ko.b(6, b3);
                        }
                    }
                }
            }
            int n19 = n16;
            if (this.fc != null) {
                n19 = n16;
                if (this.fc.length > 0) {
                    int n21;
                    for (int n20 = 0; n20 < this.fc.length; ++n20, n16 = n21) {
                        final g g = this.fc[n20];
                        n21 = n16;
                        if (g != null) {
                            n21 = n16 + ko.b(7, g);
                        }
                    }
                    n19 = n16;
                }
            }
            int n22 = n19;
            if (!this.fd.equals("")) {
                n22 = n19 + ko.g(9, this.fd);
            }
            int n23 = n22;
            if (!this.fe.equals("")) {
                n23 = n22 + ko.g(10, this.fe);
            }
            int n24 = n23;
            if (!this.ff.equals("0")) {
                n24 = n23 + ko.g(12, this.ff);
            }
            int n25 = n24;
            if (!this.fg.equals("")) {
                n25 = n24 + ko.g(13, this.fg);
            }
            int n26 = n25;
            if (this.fh != null) {
                n26 = n25 + ko.b(14, this.fh);
            }
            int n27 = n26;
            if (Float.floatToIntBits(this.fi) != Float.floatToIntBits(0.0f)) {
                n27 = n26 + ko.c(15, this.fi);
            }
            int n28 = n27;
            if (this.fk != null) {
                n28 = n27;
                if (this.fk.length > 0) {
                    int n29 = 0;
                    int n30 = 0;
                    int n31 = 0;
                    while (n29 < this.fk.length) {
                        final String s2 = this.fk[n29];
                        int n32 = n30;
                        int n33 = n31;
                        if (s2 != null) {
                            n33 = n31 + 1;
                            n32 = n30 + ko.cf(s2);
                        }
                        ++n29;
                        n30 = n32;
                        n31 = n33;
                    }
                    n28 = n27 + n30 + n31 * 2;
                }
            }
            int n34 = n28;
            if (this.fl != 0) {
                n34 = n28 + ko.j(17, this.fl);
            }
            int n35 = n34;
            if (this.fj) {
                n35 = n34 + ko.b(18, this.fj);
            }
            int adY = n35;
            if (this.eV != null) {
                adY = n35;
                if (this.eV.length > 0) {
                    int n36 = 0;
                    int n37 = 0;
                    int n39;
                    int n40;
                    for (int n38 = n; n38 < this.eV.length; ++n38, n36 = n39, n37 = n40) {
                        final String s3 = this.eV[n38];
                        n39 = n36;
                        n40 = n37;
                        if (s3 != null) {
                            n40 = n37 + 1;
                            n39 = n36 + ko.cf(s3);
                        }
                    }
                    adY = n35 + n36 + n37 * 2;
                }
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
                if (o instanceof f) {
                    final f f = (f)o;
                    b2 = b;
                    if (kr.equals(this.eV, f.eV)) {
                        b2 = b;
                        if (kr.equals(this.eW, f.eW)) {
                            b2 = b;
                            if (kr.equals(this.eX, f.eX)) {
                                b2 = b;
                                if (kr.equals(this.eY, f.eY)) {
                                    b2 = b;
                                    if (kr.equals(this.eZ, f.eZ)) {
                                        b2 = b;
                                        if (kr.equals(this.fa, f.fa)) {
                                            b2 = b;
                                            if (kr.equals(this.fb, f.fb)) {
                                                b2 = b;
                                                if (kr.equals(this.fc, f.fc)) {
                                                    if (this.fd == null) {
                                                        b2 = b;
                                                        if (f.fd != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.fd.equals(f.fd)) {
                                                        return false;
                                                    }
                                                    if (this.fe == null) {
                                                        b2 = b;
                                                        if (f.fe != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.fe.equals(f.fe)) {
                                                        return false;
                                                    }
                                                    if (this.ff == null) {
                                                        b2 = b;
                                                        if (f.ff != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.ff.equals(f.ff)) {
                                                        return false;
                                                    }
                                                    if (this.fg == null) {
                                                        b2 = b;
                                                        if (f.fg != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.fg.equals(f.fg)) {
                                                        return false;
                                                    }
                                                    if (this.fh == null) {
                                                        b2 = b;
                                                        if (f.fh != null) {
                                                            return b2;
                                                        }
                                                    }
                                                    else if (!this.fh.equals(f.fh)) {
                                                        return false;
                                                    }
                                                    b2 = b;
                                                    if (Float.floatToIntBits(this.fi) == Float.floatToIntBits(f.fi)) {
                                                        b2 = b;
                                                        if (this.fj == f.fj) {
                                                            b2 = b;
                                                            if (kr.equals(this.fk, f.fk)) {
                                                                b2 = b;
                                                                if (this.fl == f.fl) {
                                                                    if (this.adU == null || this.adU.isEmpty()) {
                                                                        if (f.adU != null) {
                                                                            b2 = b;
                                                                            if (!f.adU.isEmpty()) {
                                                                                return b2;
                                                                            }
                                                                        }
                                                                        return true;
                                                                    }
                                                                    return this.adU.equals(f.adU);
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
        
        public f g(final kn kn) throws IOException {
        Label_0169:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0169;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0169;
                    }
                    case 10: {
                        final int b = kw.b(kn, 10);
                        int length;
                        if (this.eW == null) {
                            length = 0;
                        }
                        else {
                            length = this.eW.length;
                        }
                        final String[] ew = new String[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.eW, 0, ew, 0, length);
                            i = length;
                        }
                        while (i < ew.length - 1) {
                            ew[i] = kn.readString();
                            kn.mh();
                            ++i;
                        }
                        ew[i] = kn.readString();
                        this.eW = ew;
                        continue;
                    }
                    case 18: {
                        final int b2 = kw.b(kn, 18);
                        int length2;
                        if (this.eX == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.eX.length;
                        }
                        final com.google.android.gms.internal.d.a[] ex = new com.google.android.gms.internal.d.a[b2 + length2];
                        int j = length2;
                        if (length2 != 0) {
                            System.arraycopy(this.eX, 0, ex, 0, length2);
                            j = length2;
                        }
                        while (j < ex.length - 1) {
                            kn.a(ex[j] = new com.google.android.gms.internal.d.a());
                            kn.mh();
                            ++j;
                        }
                        kn.a(ex[j] = new com.google.android.gms.internal.d.a());
                        this.eX = ex;
                        continue;
                    }
                    case 26: {
                        final int b3 = kw.b(kn, 26);
                        int length3;
                        if (this.eY == null) {
                            length3 = 0;
                        }
                        else {
                            length3 = this.eY.length;
                        }
                        final e[] ey = new e[b3 + length3];
                        int k = length3;
                        if (length3 != 0) {
                            System.arraycopy(this.eY, 0, ey, 0, length3);
                            k = length3;
                        }
                        while (k < ey.length - 1) {
                            kn.a(ey[k] = new e());
                            kn.mh();
                            ++k;
                        }
                        kn.a(ey[k] = new e());
                        this.eY = ey;
                        continue;
                    }
                    case 34: {
                        final int b4 = kw.b(kn, 34);
                        int length4;
                        if (this.eZ == null) {
                            length4 = 0;
                        }
                        else {
                            length4 = this.eZ.length;
                        }
                        final b[] ez = new b[b4 + length4];
                        int l = length4;
                        if (length4 != 0) {
                            System.arraycopy(this.eZ, 0, ez, 0, length4);
                            l = length4;
                        }
                        while (l < ez.length - 1) {
                            kn.a(ez[l] = new b());
                            kn.mh();
                            ++l;
                        }
                        kn.a(ez[l] = new b());
                        this.eZ = ez;
                        continue;
                    }
                    case 42: {
                        final int b5 = kw.b(kn, 42);
                        int length5;
                        if (this.fa == null) {
                            length5 = 0;
                        }
                        else {
                            length5 = this.fa.length;
                        }
                        final b[] fa = new b[b5 + length5];
                        int n = length5;
                        if (length5 != 0) {
                            System.arraycopy(this.fa, 0, fa, 0, length5);
                            n = length5;
                        }
                        while (n < fa.length - 1) {
                            kn.a(fa[n] = new b());
                            kn.mh();
                            ++n;
                        }
                        kn.a(fa[n] = new b());
                        this.fa = fa;
                        continue;
                    }
                    case 50: {
                        final int b6 = kw.b(kn, 50);
                        int length6;
                        if (this.fb == null) {
                            length6 = 0;
                        }
                        else {
                            length6 = this.fb.length;
                        }
                        final b[] fb = new b[b6 + length6];
                        int n2 = length6;
                        if (length6 != 0) {
                            System.arraycopy(this.fb, 0, fb, 0, length6);
                            n2 = length6;
                        }
                        while (n2 < fb.length - 1) {
                            kn.a(fb[n2] = new b());
                            kn.mh();
                            ++n2;
                        }
                        kn.a(fb[n2] = new b());
                        this.fb = fb;
                        continue;
                    }
                    case 58: {
                        final int b7 = kw.b(kn, 58);
                        int length7;
                        if (this.fc == null) {
                            length7 = 0;
                        }
                        else {
                            length7 = this.fc.length;
                        }
                        final g[] fc = new g[b7 + length7];
                        int n3 = length7;
                        if (length7 != 0) {
                            System.arraycopy(this.fc, 0, fc, 0, length7);
                            n3 = length7;
                        }
                        while (n3 < fc.length - 1) {
                            kn.a(fc[n3] = new g());
                            kn.mh();
                            ++n3;
                        }
                        kn.a(fc[n3] = new g());
                        this.fc = fc;
                        continue;
                    }
                    case 74: {
                        this.fd = kn.readString();
                        continue;
                    }
                    case 82: {
                        this.fe = kn.readString();
                        continue;
                    }
                    case 98: {
                        this.ff = kn.readString();
                        continue;
                    }
                    case 106: {
                        this.fg = kn.readString();
                        continue;
                    }
                    case 114: {
                        if (this.fh == null) {
                            this.fh = new a();
                        }
                        kn.a(this.fh);
                        continue;
                    }
                    case 125: {
                        this.fi = kn.readFloat();
                        continue;
                    }
                    case 130: {
                        final int b8 = kw.b(kn, 130);
                        int length8;
                        if (this.fk == null) {
                            length8 = 0;
                        }
                        else {
                            length8 = this.fk.length;
                        }
                        final String[] fk = new String[b8 + length8];
                        int n4 = length8;
                        if (length8 != 0) {
                            System.arraycopy(this.fk, 0, fk, 0, length8);
                            n4 = length8;
                        }
                        while (n4 < fk.length - 1) {
                            fk[n4] = kn.readString();
                            kn.mh();
                            ++n4;
                        }
                        fk[n4] = kn.readString();
                        this.fk = fk;
                        continue;
                    }
                    case 136: {
                        this.fl = kn.mk();
                        continue;
                    }
                    case 144: {
                        this.fj = kn.ml();
                        continue;
                    }
                    case 154: {
                        final int b9 = kw.b(kn, 154);
                        int length9;
                        if (this.eV == null) {
                            length9 = 0;
                        }
                        else {
                            length9 = this.eV.length;
                        }
                        final String[] ev = new String[b9 + length9];
                        int n5 = length9;
                        if (length9 != 0) {
                            System.arraycopy(this.eV, 0, ev, 0, length9);
                            n5 = length9;
                        }
                        while (n5 < ev.length - 1) {
                            ev[n5] = kn.readString();
                            kn.mh();
                            ++n5;
                        }
                        ev[n5] = kn.readString();
                        this.eV = ev;
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        public int hashCode() {
            final boolean b = false;
            final int hashCode = kr.hashCode(this.eV);
            final int hashCode2 = kr.hashCode(this.eW);
            final int hashCode3 = kr.hashCode(this.eX);
            final int hashCode4 = kr.hashCode(this.eY);
            final int hashCode5 = kr.hashCode(this.eZ);
            final int hashCode6 = kr.hashCode(this.fa);
            final int hashCode7 = kr.hashCode(this.fb);
            final int hashCode8 = kr.hashCode(this.fc);
            int hashCode9;
            if (this.fd == null) {
                hashCode9 = 0;
            }
            else {
                hashCode9 = this.fd.hashCode();
            }
            int hashCode10;
            if (this.fe == null) {
                hashCode10 = 0;
            }
            else {
                hashCode10 = this.fe.hashCode();
            }
            int hashCode11;
            if (this.ff == null) {
                hashCode11 = 0;
            }
            else {
                hashCode11 = this.ff.hashCode();
            }
            int hashCode12;
            if (this.fg == null) {
                hashCode12 = 0;
            }
            else {
                hashCode12 = this.fg.hashCode();
            }
            int hashCode13;
            if (this.fh == null) {
                hashCode13 = 0;
            }
            else {
                hashCode13 = this.fh.hashCode();
            }
            final int floatToIntBits = Float.floatToIntBits(this.fi);
            int n;
            if (this.fj) {
                n = 1231;
            }
            else {
                n = 1237;
            }
            final int hashCode14 = kr.hashCode(this.fk);
            final int fl = this.fl;
            int hashCode15 = b ? 1 : 0;
            if (this.adU != null) {
                if (this.adU.isEmpty()) {
                    hashCode15 = (b ? 1 : 0);
                }
                else {
                    hashCode15 = this.adU.hashCode();
                }
            }
            return (((n + ((hashCode13 + (hashCode12 + (hashCode11 + (hashCode10 + (hashCode9 + ((((((((hashCode + 527) * 31 + hashCode2) * 31 + hashCode3) * 31 + hashCode4) * 31 + hashCode5) * 31 + hashCode6) * 31 + hashCode7) * 31 + hashCode8) * 31) * 31) * 31) * 31) * 31) * 31 + floatToIntBits) * 31) * 31 + hashCode14) * 31 + fl) * 31 + hashCode15;
        }
        
        public f k() {
            this.eV = kw.aef;
            this.eW = kw.aef;
            this.eX = com.google.android.gms.internal.d.a.r();
            this.eY = e.i();
            this.eZ = b.d();
            this.fa = b.d();
            this.fb = b.d();
            this.fc = g.l();
            this.fd = "";
            this.fe = "";
            this.ff = "0";
            this.fg = "";
            this.fh = null;
            this.fi = 0.0f;
            this.fj = false;
            this.fk = kw.aef;
            this.fl = 0;
            this.adU = null;
            this.adY = -1;
            return this;
        }
    }
    
    public static final class g extends kp<g>
    {
        private static volatile g[] fm;
        public int[] fn;
        public int[] fo;
        public int[] fp;
        public int[] fq;
        public int[] fr;
        public int[] fs;
        public int[] ft;
        public int[] fu;
        public int[] fv;
        public int[] fw;
        
        public g() {
            this.m();
        }
        
        public static g[] l() {
            Label_0027: {
                if (g.fm != null) {
                    break Label_0027;
                }
                synchronized (kr.adX) {
                    if (g.fm == null) {
                        g.fm = new g[0];
                    }
                    return g.fm;
                }
            }
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            final int n = 0;
            if (this.fn != null && this.fn.length > 0) {
                for (int i = 0; i < this.fn.length; ++i) {
                    ko.i(1, this.fn[i]);
                }
            }
            if (this.fo != null && this.fo.length > 0) {
                for (int j = 0; j < this.fo.length; ++j) {
                    ko.i(2, this.fo[j]);
                }
            }
            if (this.fp != null && this.fp.length > 0) {
                for (int k = 0; k < this.fp.length; ++k) {
                    ko.i(3, this.fp[k]);
                }
            }
            if (this.fq != null && this.fq.length > 0) {
                for (int l = 0; l < this.fq.length; ++l) {
                    ko.i(4, this.fq[l]);
                }
            }
            if (this.fr != null && this.fr.length > 0) {
                for (int n2 = 0; n2 < this.fr.length; ++n2) {
                    ko.i(5, this.fr[n2]);
                }
            }
            if (this.fs != null && this.fs.length > 0) {
                for (int n3 = 0; n3 < this.fs.length; ++n3) {
                    ko.i(6, this.fs[n3]);
                }
            }
            if (this.ft != null && this.ft.length > 0) {
                for (int n4 = 0; n4 < this.ft.length; ++n4) {
                    ko.i(7, this.ft[n4]);
                }
            }
            if (this.fu != null && this.fu.length > 0) {
                for (int n5 = 0; n5 < this.fu.length; ++n5) {
                    ko.i(8, this.fu[n5]);
                }
            }
            if (this.fv != null && this.fv.length > 0) {
                for (int n6 = 0; n6 < this.fv.length; ++n6) {
                    ko.i(9, this.fv[n6]);
                }
            }
            if (this.fw != null && this.fw.length > 0) {
                for (int n7 = n; n7 < this.fw.length; ++n7) {
                    ko.i(10, this.fw[n7]);
                }
            }
            super.a(ko);
        }
        
        @Override
        public int c() {
            final int n = 0;
            final int c = super.c();
            int n3;
            if (this.fn != null && this.fn.length > 0) {
                int i = 0;
                int n2 = 0;
                while (i < this.fn.length) {
                    n2 += ko.cX(this.fn[i]);
                    ++i;
                }
                n3 = c + n2 + this.fn.length * 1;
            }
            else {
                n3 = c;
            }
            int n4 = n3;
            if (this.fo != null) {
                n4 = n3;
                if (this.fo.length > 0) {
                    int j = 0;
                    int n5 = 0;
                    while (j < this.fo.length) {
                        n5 += ko.cX(this.fo[j]);
                        ++j;
                    }
                    n4 = n3 + n5 + this.fo.length * 1;
                }
            }
            int n6 = n4;
            if (this.fp != null) {
                n6 = n4;
                if (this.fp.length > 0) {
                    int k = 0;
                    int n7 = 0;
                    while (k < this.fp.length) {
                        n7 += ko.cX(this.fp[k]);
                        ++k;
                    }
                    n6 = n4 + n7 + this.fp.length * 1;
                }
            }
            int n8 = n6;
            if (this.fq != null) {
                n8 = n6;
                if (this.fq.length > 0) {
                    int l = 0;
                    int n9 = 0;
                    while (l < this.fq.length) {
                        n9 += ko.cX(this.fq[l]);
                        ++l;
                    }
                    n8 = n6 + n9 + this.fq.length * 1;
                }
            }
            int n10 = n8;
            if (this.fr != null) {
                n10 = n8;
                if (this.fr.length > 0) {
                    int n11 = 0;
                    int n12 = 0;
                    while (n11 < this.fr.length) {
                        n12 += ko.cX(this.fr[n11]);
                        ++n11;
                    }
                    n10 = n8 + n12 + this.fr.length * 1;
                }
            }
            int n13 = n10;
            if (this.fs != null) {
                n13 = n10;
                if (this.fs.length > 0) {
                    int n14 = 0;
                    int n15 = 0;
                    while (n14 < this.fs.length) {
                        n15 += ko.cX(this.fs[n14]);
                        ++n14;
                    }
                    n13 = n10 + n15 + this.fs.length * 1;
                }
            }
            int n16 = n13;
            if (this.ft != null) {
                n16 = n13;
                if (this.ft.length > 0) {
                    int n17 = 0;
                    int n18 = 0;
                    while (n17 < this.ft.length) {
                        n18 += ko.cX(this.ft[n17]);
                        ++n17;
                    }
                    n16 = n13 + n18 + this.ft.length * 1;
                }
            }
            int n19 = n16;
            if (this.fu != null) {
                n19 = n16;
                if (this.fu.length > 0) {
                    int n20 = 0;
                    int n21 = 0;
                    while (n20 < this.fu.length) {
                        n21 += ko.cX(this.fu[n20]);
                        ++n20;
                    }
                    n19 = n16 + n21 + this.fu.length * 1;
                }
            }
            int n22 = n19;
            if (this.fv != null) {
                n22 = n19;
                if (this.fv.length > 0) {
                    int n23 = 0;
                    int n24 = 0;
                    while (n23 < this.fv.length) {
                        n24 += ko.cX(this.fv[n23]);
                        ++n23;
                    }
                    n22 = n19 + n24 + this.fv.length * 1;
                }
            }
            int adY = n22;
            if (this.fw != null) {
                adY = n22;
                if (this.fw.length > 0) {
                    int n25 = 0;
                    for (int n26 = n; n26 < this.fw.length; ++n26) {
                        n25 += ko.cX(this.fw[n26]);
                    }
                    adY = n22 + n25 + this.fw.length * 1;
                }
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
                if (o instanceof g) {
                    final g g = (g)o;
                    b2 = b;
                    if (kr.equals(this.fn, g.fn)) {
                        b2 = b;
                        if (kr.equals(this.fo, g.fo)) {
                            b2 = b;
                            if (kr.equals(this.fp, g.fp)) {
                                b2 = b;
                                if (kr.equals(this.fq, g.fq)) {
                                    b2 = b;
                                    if (kr.equals(this.fr, g.fr)) {
                                        b2 = b;
                                        if (kr.equals(this.fs, g.fs)) {
                                            b2 = b;
                                            if (kr.equals(this.ft, g.ft)) {
                                                b2 = b;
                                                if (kr.equals(this.fu, g.fu)) {
                                                    b2 = b;
                                                    if (kr.equals(this.fv, g.fv)) {
                                                        b2 = b;
                                                        if (kr.equals(this.fw, g.fw)) {
                                                            if (this.adU == null || this.adU.isEmpty()) {
                                                                if (g.adU != null) {
                                                                    b2 = b;
                                                                    if (!g.adU.isEmpty()) {
                                                                        return b2;
                                                                    }
                                                                }
                                                                return true;
                                                            }
                                                            return this.adU.equals(g.adU);
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
        
        public g h(final kn kn) throws IOException {
        Label_0193:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0193;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0193;
                    }
                    case 8: {
                        final int b = kw.b(kn, 8);
                        int length;
                        if (this.fn == null) {
                            length = 0;
                        }
                        else {
                            length = this.fn.length;
                        }
                        final int[] fn = new int[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.fn, 0, fn, 0, length);
                            i = length;
                        }
                        while (i < fn.length - 1) {
                            fn[i] = kn.mk();
                            kn.mh();
                            ++i;
                        }
                        fn[i] = kn.mk();
                        this.fn = fn;
                        continue;
                    }
                    case 10: {
                        final int cr = kn.cR(kn.mn());
                        final int position = kn.getPosition();
                        int n = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n;
                        }
                        kn.cT(position);
                        int length2;
                        if (this.fn == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.fn.length;
                        }
                        final int[] fn2 = new int[n + length2];
                        int j = length2;
                        if (length2 != 0) {
                            System.arraycopy(this.fn, 0, fn2, 0, length2);
                            j = length2;
                        }
                        while (j < fn2.length) {
                            fn2[j] = kn.mk();
                            ++j;
                        }
                        this.fn = fn2;
                        kn.cS(cr);
                        continue;
                    }
                    case 16: {
                        final int b2 = kw.b(kn, 16);
                        int length3;
                        if (this.fo == null) {
                            length3 = 0;
                        }
                        else {
                            length3 = this.fo.length;
                        }
                        final int[] fo = new int[b2 + length3];
                        int k = length3;
                        if (length3 != 0) {
                            System.arraycopy(this.fo, 0, fo, 0, length3);
                            k = length3;
                        }
                        while (k < fo.length - 1) {
                            fo[k] = kn.mk();
                            kn.mh();
                            ++k;
                        }
                        fo[k] = kn.mk();
                        this.fo = fo;
                        continue;
                    }
                    case 18: {
                        final int cr2 = kn.cR(kn.mn());
                        final int position2 = kn.getPosition();
                        int n2 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n2;
                        }
                        kn.cT(position2);
                        int length4;
                        if (this.fo == null) {
                            length4 = 0;
                        }
                        else {
                            length4 = this.fo.length;
                        }
                        final int[] fo2 = new int[n2 + length4];
                        int l = length4;
                        if (length4 != 0) {
                            System.arraycopy(this.fo, 0, fo2, 0, length4);
                            l = length4;
                        }
                        while (l < fo2.length) {
                            fo2[l] = kn.mk();
                            ++l;
                        }
                        this.fo = fo2;
                        kn.cS(cr2);
                        continue;
                    }
                    case 24: {
                        final int b3 = kw.b(kn, 24);
                        int length5;
                        if (this.fp == null) {
                            length5 = 0;
                        }
                        else {
                            length5 = this.fp.length;
                        }
                        final int[] fp = new int[b3 + length5];
                        int n3 = length5;
                        if (length5 != 0) {
                            System.arraycopy(this.fp, 0, fp, 0, length5);
                            n3 = length5;
                        }
                        while (n3 < fp.length - 1) {
                            fp[n3] = kn.mk();
                            kn.mh();
                            ++n3;
                        }
                        fp[n3] = kn.mk();
                        this.fp = fp;
                        continue;
                    }
                    case 26: {
                        final int cr3 = kn.cR(kn.mn());
                        final int position3 = kn.getPosition();
                        int n4 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n4;
                        }
                        kn.cT(position3);
                        int length6;
                        if (this.fp == null) {
                            length6 = 0;
                        }
                        else {
                            length6 = this.fp.length;
                        }
                        final int[] fp2 = new int[n4 + length6];
                        int n5 = length6;
                        if (length6 != 0) {
                            System.arraycopy(this.fp, 0, fp2, 0, length6);
                            n5 = length6;
                        }
                        while (n5 < fp2.length) {
                            fp2[n5] = kn.mk();
                            ++n5;
                        }
                        this.fp = fp2;
                        kn.cS(cr3);
                        continue;
                    }
                    case 32: {
                        final int b4 = kw.b(kn, 32);
                        int length7;
                        if (this.fq == null) {
                            length7 = 0;
                        }
                        else {
                            length7 = this.fq.length;
                        }
                        final int[] fq = new int[b4 + length7];
                        int n6 = length7;
                        if (length7 != 0) {
                            System.arraycopy(this.fq, 0, fq, 0, length7);
                            n6 = length7;
                        }
                        while (n6 < fq.length - 1) {
                            fq[n6] = kn.mk();
                            kn.mh();
                            ++n6;
                        }
                        fq[n6] = kn.mk();
                        this.fq = fq;
                        continue;
                    }
                    case 34: {
                        final int cr4 = kn.cR(kn.mn());
                        final int position4 = kn.getPosition();
                        int n7 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n7;
                        }
                        kn.cT(position4);
                        int length8;
                        if (this.fq == null) {
                            length8 = 0;
                        }
                        else {
                            length8 = this.fq.length;
                        }
                        final int[] fq2 = new int[n7 + length8];
                        int n8 = length8;
                        if (length8 != 0) {
                            System.arraycopy(this.fq, 0, fq2, 0, length8);
                            n8 = length8;
                        }
                        while (n8 < fq2.length) {
                            fq2[n8] = kn.mk();
                            ++n8;
                        }
                        this.fq = fq2;
                        kn.cS(cr4);
                        continue;
                    }
                    case 40: {
                        final int b5 = kw.b(kn, 40);
                        int length9;
                        if (this.fr == null) {
                            length9 = 0;
                        }
                        else {
                            length9 = this.fr.length;
                        }
                        final int[] fr = new int[b5 + length9];
                        int n9 = length9;
                        if (length9 != 0) {
                            System.arraycopy(this.fr, 0, fr, 0, length9);
                            n9 = length9;
                        }
                        while (n9 < fr.length - 1) {
                            fr[n9] = kn.mk();
                            kn.mh();
                            ++n9;
                        }
                        fr[n9] = kn.mk();
                        this.fr = fr;
                        continue;
                    }
                    case 42: {
                        final int cr5 = kn.cR(kn.mn());
                        final int position5 = kn.getPosition();
                        int n10 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n10;
                        }
                        kn.cT(position5);
                        int length10;
                        if (this.fr == null) {
                            length10 = 0;
                        }
                        else {
                            length10 = this.fr.length;
                        }
                        final int[] fr2 = new int[n10 + length10];
                        int n11 = length10;
                        if (length10 != 0) {
                            System.arraycopy(this.fr, 0, fr2, 0, length10);
                            n11 = length10;
                        }
                        while (n11 < fr2.length) {
                            fr2[n11] = kn.mk();
                            ++n11;
                        }
                        this.fr = fr2;
                        kn.cS(cr5);
                        continue;
                    }
                    case 48: {
                        final int b6 = kw.b(kn, 48);
                        int length11;
                        if (this.fs == null) {
                            length11 = 0;
                        }
                        else {
                            length11 = this.fs.length;
                        }
                        final int[] fs = new int[b6 + length11];
                        int n12 = length11;
                        if (length11 != 0) {
                            System.arraycopy(this.fs, 0, fs, 0, length11);
                            n12 = length11;
                        }
                        while (n12 < fs.length - 1) {
                            fs[n12] = kn.mk();
                            kn.mh();
                            ++n12;
                        }
                        fs[n12] = kn.mk();
                        this.fs = fs;
                        continue;
                    }
                    case 50: {
                        final int cr6 = kn.cR(kn.mn());
                        final int position6 = kn.getPosition();
                        int n13 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n13;
                        }
                        kn.cT(position6);
                        int length12;
                        if (this.fs == null) {
                            length12 = 0;
                        }
                        else {
                            length12 = this.fs.length;
                        }
                        final int[] fs2 = new int[n13 + length12];
                        int n14 = length12;
                        if (length12 != 0) {
                            System.arraycopy(this.fs, 0, fs2, 0, length12);
                            n14 = length12;
                        }
                        while (n14 < fs2.length) {
                            fs2[n14] = kn.mk();
                            ++n14;
                        }
                        this.fs = fs2;
                        kn.cS(cr6);
                        continue;
                    }
                    case 56: {
                        final int b7 = kw.b(kn, 56);
                        int length13;
                        if (this.ft == null) {
                            length13 = 0;
                        }
                        else {
                            length13 = this.ft.length;
                        }
                        final int[] ft = new int[b7 + length13];
                        int n15 = length13;
                        if (length13 != 0) {
                            System.arraycopy(this.ft, 0, ft, 0, length13);
                            n15 = length13;
                        }
                        while (n15 < ft.length - 1) {
                            ft[n15] = kn.mk();
                            kn.mh();
                            ++n15;
                        }
                        ft[n15] = kn.mk();
                        this.ft = ft;
                        continue;
                    }
                    case 58: {
                        final int cr7 = kn.cR(kn.mn());
                        final int position7 = kn.getPosition();
                        int n16 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n16;
                        }
                        kn.cT(position7);
                        int length14;
                        if (this.ft == null) {
                            length14 = 0;
                        }
                        else {
                            length14 = this.ft.length;
                        }
                        final int[] ft2 = new int[n16 + length14];
                        int n17 = length14;
                        if (length14 != 0) {
                            System.arraycopy(this.ft, 0, ft2, 0, length14);
                            n17 = length14;
                        }
                        while (n17 < ft2.length) {
                            ft2[n17] = kn.mk();
                            ++n17;
                        }
                        this.ft = ft2;
                        kn.cS(cr7);
                        continue;
                    }
                    case 64: {
                        final int b8 = kw.b(kn, 64);
                        int length15;
                        if (this.fu == null) {
                            length15 = 0;
                        }
                        else {
                            length15 = this.fu.length;
                        }
                        final int[] fu = new int[b8 + length15];
                        int n18 = length15;
                        if (length15 != 0) {
                            System.arraycopy(this.fu, 0, fu, 0, length15);
                            n18 = length15;
                        }
                        while (n18 < fu.length - 1) {
                            fu[n18] = kn.mk();
                            kn.mh();
                            ++n18;
                        }
                        fu[n18] = kn.mk();
                        this.fu = fu;
                        continue;
                    }
                    case 66: {
                        final int cr8 = kn.cR(kn.mn());
                        final int position8 = kn.getPosition();
                        int n19 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n19;
                        }
                        kn.cT(position8);
                        int length16;
                        if (this.fu == null) {
                            length16 = 0;
                        }
                        else {
                            length16 = this.fu.length;
                        }
                        final int[] fu2 = new int[n19 + length16];
                        int n20 = length16;
                        if (length16 != 0) {
                            System.arraycopy(this.fu, 0, fu2, 0, length16);
                            n20 = length16;
                        }
                        while (n20 < fu2.length) {
                            fu2[n20] = kn.mk();
                            ++n20;
                        }
                        this.fu = fu2;
                        kn.cS(cr8);
                        continue;
                    }
                    case 72: {
                        final int b9 = kw.b(kn, 72);
                        int length17;
                        if (this.fv == null) {
                            length17 = 0;
                        }
                        else {
                            length17 = this.fv.length;
                        }
                        final int[] fv = new int[b9 + length17];
                        int n21 = length17;
                        if (length17 != 0) {
                            System.arraycopy(this.fv, 0, fv, 0, length17);
                            n21 = length17;
                        }
                        while (n21 < fv.length - 1) {
                            fv[n21] = kn.mk();
                            kn.mh();
                            ++n21;
                        }
                        fv[n21] = kn.mk();
                        this.fv = fv;
                        continue;
                    }
                    case 74: {
                        final int cr9 = kn.cR(kn.mn());
                        final int position9 = kn.getPosition();
                        int n22 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n22;
                        }
                        kn.cT(position9);
                        int length18;
                        if (this.fv == null) {
                            length18 = 0;
                        }
                        else {
                            length18 = this.fv.length;
                        }
                        final int[] fv2 = new int[n22 + length18];
                        int n23 = length18;
                        if (length18 != 0) {
                            System.arraycopy(this.fv, 0, fv2, 0, length18);
                            n23 = length18;
                        }
                        while (n23 < fv2.length) {
                            fv2[n23] = kn.mk();
                            ++n23;
                        }
                        this.fv = fv2;
                        kn.cS(cr9);
                        continue;
                    }
                    case 80: {
                        final int b10 = kw.b(kn, 80);
                        int length19;
                        if (this.fw == null) {
                            length19 = 0;
                        }
                        else {
                            length19 = this.fw.length;
                        }
                        final int[] fw = new int[b10 + length19];
                        int n24 = length19;
                        if (length19 != 0) {
                            System.arraycopy(this.fw, 0, fw, 0, length19);
                            n24 = length19;
                        }
                        while (n24 < fw.length - 1) {
                            fw[n24] = kn.mk();
                            kn.mh();
                            ++n24;
                        }
                        fw[n24] = kn.mk();
                        this.fw = fw;
                        continue;
                    }
                    case 82: {
                        final int cr10 = kn.cR(kn.mn());
                        final int position10 = kn.getPosition();
                        int n25 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n25;
                        }
                        kn.cT(position10);
                        int length20;
                        if (this.fw == null) {
                            length20 = 0;
                        }
                        else {
                            length20 = this.fw.length;
                        }
                        final int[] fw2 = new int[n25 + length20];
                        int n26 = length20;
                        if (length20 != 0) {
                            System.arraycopy(this.fw, 0, fw2, 0, length20);
                            n26 = length20;
                        }
                        while (n26 < fw2.length) {
                            fw2[n26] = kn.mk();
                            ++n26;
                        }
                        this.fw = fw2;
                        kn.cS(cr10);
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        public int hashCode() {
            final int hashCode = kr.hashCode(this.fn);
            final int hashCode2 = kr.hashCode(this.fo);
            final int hashCode3 = kr.hashCode(this.fp);
            final int hashCode4 = kr.hashCode(this.fq);
            final int hashCode5 = kr.hashCode(this.fr);
            final int hashCode6 = kr.hashCode(this.fs);
            final int hashCode7 = kr.hashCode(this.ft);
            final int hashCode8 = kr.hashCode(this.fu);
            final int hashCode9 = kr.hashCode(this.fv);
            final int hashCode10 = kr.hashCode(this.fw);
            int hashCode11;
            if (this.adU == null || this.adU.isEmpty()) {
                hashCode11 = 0;
            }
            else {
                hashCode11 = this.adU.hashCode();
            }
            return hashCode11 + ((((((((((hashCode + 527) * 31 + hashCode2) * 31 + hashCode3) * 31 + hashCode4) * 31 + hashCode5) * 31 + hashCode6) * 31 + hashCode7) * 31 + hashCode8) * 31 + hashCode9) * 31 + hashCode10) * 31;
        }
        
        public g m() {
            this.fn = kw.aea;
            this.fo = kw.aea;
            this.fp = kw.aea;
            this.fq = kw.aea;
            this.fr = kw.aea;
            this.fs = kw.aea;
            this.ft = kw.aea;
            this.fu = kw.aea;
            this.fv = kw.aea;
            this.fw = kw.aea;
            this.adU = null;
            this.adY = -1;
            return this;
        }
    }
    
    public static final class h extends kp<h>
    {
        public static final kq<com.google.android.gms.internal.d.a, h> fx;
        private static final h[] fy;
        public int[] fA;
        public int[] fB;
        public int fC;
        public int[] fD;
        public int fE;
        public int fF;
        public int[] fz;
        
        static {
            fx = kq.a(11, h.class, 810);
            fy = new h[0];
        }
        
        public h() {
            this.n();
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            final int n = 0;
            if (this.fz != null && this.fz.length > 0) {
                for (int i = 0; i < this.fz.length; ++i) {
                    ko.i(1, this.fz[i]);
                }
            }
            if (this.fA != null && this.fA.length > 0) {
                for (int j = 0; j < this.fA.length; ++j) {
                    ko.i(2, this.fA[j]);
                }
            }
            if (this.fB != null && this.fB.length > 0) {
                for (int k = 0; k < this.fB.length; ++k) {
                    ko.i(3, this.fB[k]);
                }
            }
            if (this.fC != 0) {
                ko.i(4, this.fC);
            }
            if (this.fD != null && this.fD.length > 0) {
                for (int l = n; l < this.fD.length; ++l) {
                    ko.i(5, this.fD[l]);
                }
            }
            if (this.fE != 0) {
                ko.i(6, this.fE);
            }
            if (this.fF != 0) {
                ko.i(7, this.fF);
            }
            super.a(ko);
        }
        
        @Override
        public int c() {
            final int n = 0;
            final int c = super.c();
            int n3;
            if (this.fz != null && this.fz.length > 0) {
                int i = 0;
                int n2 = 0;
                while (i < this.fz.length) {
                    n2 += ko.cX(this.fz[i]);
                    ++i;
                }
                n3 = c + n2 + this.fz.length * 1;
            }
            else {
                n3 = c;
            }
            int n4 = n3;
            if (this.fA != null) {
                n4 = n3;
                if (this.fA.length > 0) {
                    int j = 0;
                    int n5 = 0;
                    while (j < this.fA.length) {
                        n5 += ko.cX(this.fA[j]);
                        ++j;
                    }
                    n4 = n3 + n5 + this.fA.length * 1;
                }
            }
            int n6 = n4;
            if (this.fB != null) {
                n6 = n4;
                if (this.fB.length > 0) {
                    int k = 0;
                    int n7 = 0;
                    while (k < this.fB.length) {
                        n7 += ko.cX(this.fB[k]);
                        ++k;
                    }
                    n6 = n4 + n7 + this.fB.length * 1;
                }
            }
            int n8 = n6;
            if (this.fC != 0) {
                n8 = n6 + ko.j(4, this.fC);
            }
            int n9 = n8;
            if (this.fD != null) {
                n9 = n8;
                if (this.fD.length > 0) {
                    int n10 = 0;
                    for (int l = n; l < this.fD.length; ++l) {
                        n10 += ko.cX(this.fD[l]);
                    }
                    n9 = n8 + n10 + this.fD.length * 1;
                }
            }
            int n11 = n9;
            if (this.fE != 0) {
                n11 = n9 + ko.j(6, this.fE);
            }
            int adY = n11;
            if (this.fF != 0) {
                adY = n11 + ko.j(7, this.fF);
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
                if (o instanceof h) {
                    final h h = (h)o;
                    b2 = b;
                    if (kr.equals(this.fz, h.fz)) {
                        b2 = b;
                        if (kr.equals(this.fA, h.fA)) {
                            b2 = b;
                            if (kr.equals(this.fB, h.fB)) {
                                b2 = b;
                                if (this.fC == h.fC) {
                                    b2 = b;
                                    if (kr.equals(this.fD, h.fD)) {
                                        b2 = b;
                                        if (this.fE == h.fE) {
                                            b2 = b;
                                            if (this.fF == h.fF) {
                                                if (this.adU == null || this.adU.isEmpty()) {
                                                    if (h.adU != null) {
                                                        b2 = b;
                                                        if (!h.adU.isEmpty()) {
                                                            return b2;
                                                        }
                                                    }
                                                    return true;
                                                }
                                                return this.adU.equals(h.adU);
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
            final int hashCode = kr.hashCode(this.fz);
            final int hashCode2 = kr.hashCode(this.fA);
            final int hashCode3 = kr.hashCode(this.fB);
            final int fc = this.fC;
            final int hashCode4 = kr.hashCode(this.fD);
            final int fe = this.fE;
            final int ff = this.fF;
            int hashCode5;
            if (this.adU == null || this.adU.isEmpty()) {
                hashCode5 = 0;
            }
            else {
                hashCode5 = this.adU.hashCode();
            }
            return hashCode5 + (((((((hashCode + 527) * 31 + hashCode2) * 31 + hashCode3) * 31 + fc) * 31 + hashCode4) * 31 + fe) * 31 + ff) * 31;
        }
        
        public h i(final kn kn) throws IOException {
        Label_0121:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0121;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0121;
                    }
                    case 8: {
                        final int b = kw.b(kn, 8);
                        int length;
                        if (this.fz == null) {
                            length = 0;
                        }
                        else {
                            length = this.fz.length;
                        }
                        final int[] fz = new int[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.fz, 0, fz, 0, length);
                            i = length;
                        }
                        while (i < fz.length - 1) {
                            fz[i] = kn.mk();
                            kn.mh();
                            ++i;
                        }
                        fz[i] = kn.mk();
                        this.fz = fz;
                        continue;
                    }
                    case 10: {
                        final int cr = kn.cR(kn.mn());
                        final int position = kn.getPosition();
                        int n = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n;
                        }
                        kn.cT(position);
                        int length2;
                        if (this.fz == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.fz.length;
                        }
                        final int[] fz2 = new int[n + length2];
                        int j = length2;
                        if (length2 != 0) {
                            System.arraycopy(this.fz, 0, fz2, 0, length2);
                            j = length2;
                        }
                        while (j < fz2.length) {
                            fz2[j] = kn.mk();
                            ++j;
                        }
                        this.fz = fz2;
                        kn.cS(cr);
                        continue;
                    }
                    case 16: {
                        final int b2 = kw.b(kn, 16);
                        int length3;
                        if (this.fA == null) {
                            length3 = 0;
                        }
                        else {
                            length3 = this.fA.length;
                        }
                        final int[] fa = new int[b2 + length3];
                        int k = length3;
                        if (length3 != 0) {
                            System.arraycopy(this.fA, 0, fa, 0, length3);
                            k = length3;
                        }
                        while (k < fa.length - 1) {
                            fa[k] = kn.mk();
                            kn.mh();
                            ++k;
                        }
                        fa[k] = kn.mk();
                        this.fA = fa;
                        continue;
                    }
                    case 18: {
                        final int cr2 = kn.cR(kn.mn());
                        final int position2 = kn.getPosition();
                        int n2 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n2;
                        }
                        kn.cT(position2);
                        int length4;
                        if (this.fA == null) {
                            length4 = 0;
                        }
                        else {
                            length4 = this.fA.length;
                        }
                        final int[] fa2 = new int[n2 + length4];
                        int l = length4;
                        if (length4 != 0) {
                            System.arraycopy(this.fA, 0, fa2, 0, length4);
                            l = length4;
                        }
                        while (l < fa2.length) {
                            fa2[l] = kn.mk();
                            ++l;
                        }
                        this.fA = fa2;
                        kn.cS(cr2);
                        continue;
                    }
                    case 24: {
                        final int b3 = kw.b(kn, 24);
                        int length5;
                        if (this.fB == null) {
                            length5 = 0;
                        }
                        else {
                            length5 = this.fB.length;
                        }
                        final int[] fb = new int[b3 + length5];
                        int n3 = length5;
                        if (length5 != 0) {
                            System.arraycopy(this.fB, 0, fb, 0, length5);
                            n3 = length5;
                        }
                        while (n3 < fb.length - 1) {
                            fb[n3] = kn.mk();
                            kn.mh();
                            ++n3;
                        }
                        fb[n3] = kn.mk();
                        this.fB = fb;
                        continue;
                    }
                    case 26: {
                        final int cr3 = kn.cR(kn.mn());
                        final int position3 = kn.getPosition();
                        int n4 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n4;
                        }
                        kn.cT(position3);
                        int length6;
                        if (this.fB == null) {
                            length6 = 0;
                        }
                        else {
                            length6 = this.fB.length;
                        }
                        final int[] fb2 = new int[n4 + length6];
                        int n5 = length6;
                        if (length6 != 0) {
                            System.arraycopy(this.fB, 0, fb2, 0, length6);
                            n5 = length6;
                        }
                        while (n5 < fb2.length) {
                            fb2[n5] = kn.mk();
                            ++n5;
                        }
                        this.fB = fb2;
                        kn.cS(cr3);
                        continue;
                    }
                    case 32: {
                        this.fC = kn.mk();
                        continue;
                    }
                    case 40: {
                        final int b4 = kw.b(kn, 40);
                        int length7;
                        if (this.fD == null) {
                            length7 = 0;
                        }
                        else {
                            length7 = this.fD.length;
                        }
                        final int[] fd = new int[b4 + length7];
                        int n6 = length7;
                        if (length7 != 0) {
                            System.arraycopy(this.fD, 0, fd, 0, length7);
                            n6 = length7;
                        }
                        while (n6 < fd.length - 1) {
                            fd[n6] = kn.mk();
                            kn.mh();
                            ++n6;
                        }
                        fd[n6] = kn.mk();
                        this.fD = fd;
                        continue;
                    }
                    case 42: {
                        final int cr4 = kn.cR(kn.mn());
                        final int position4 = kn.getPosition();
                        int n7 = 0;
                        while (kn.ms() > 0) {
                            kn.mk();
                            ++n7;
                        }
                        kn.cT(position4);
                        int length8;
                        if (this.fD == null) {
                            length8 = 0;
                        }
                        else {
                            length8 = this.fD.length;
                        }
                        final int[] fd2 = new int[n7 + length8];
                        int n8 = length8;
                        if (length8 != 0) {
                            System.arraycopy(this.fD, 0, fd2, 0, length8);
                            n8 = length8;
                        }
                        while (n8 < fd2.length) {
                            fd2[n8] = kn.mk();
                            ++n8;
                        }
                        this.fD = fd2;
                        kn.cS(cr4);
                        continue;
                    }
                    case 48: {
                        this.fE = kn.mk();
                        continue;
                    }
                    case 56: {
                        this.fF = kn.mk();
                        continue;
                    }
                }
            }
            return this;
        }
        
        public h n() {
            this.fz = kw.aea;
            this.fA = kw.aea;
            this.fB = kw.aea;
            this.fC = 0;
            this.fD = kw.aea;
            this.fE = 0;
            this.fF = 0;
            this.adU = null;
            this.adY = -1;
            return this;
        }
    }
    
    public static final class i extends kp<i>
    {
        private static volatile i[] fG;
        public com.google.android.gms.internal.d.a fH;
        public d fI;
        public String name;
        
        public i() {
            this.p();
        }
        
        public static i[] o() {
            Label_0027: {
                if (i.fG != null) {
                    break Label_0027;
                }
                synchronized (kr.adX) {
                    if (i.fG == null) {
                        i.fG = new i[0];
                    }
                    return i.fG;
                }
            }
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            if (!this.name.equals("")) {
                ko.b(1, this.name);
            }
            if (this.fH != null) {
                ko.a(2, this.fH);
            }
            if (this.fI != null) {
                ko.a(3, this.fI);
            }
            super.a(ko);
        }
        
        @Override
        public int c() {
            int c;
            final int n = c = super.c();
            if (!this.name.equals("")) {
                c = n + ko.g(1, this.name);
            }
            int n2 = c;
            if (this.fH != null) {
                n2 = c + ko.b(2, this.fH);
            }
            int adY = n2;
            if (this.fI != null) {
                adY = n2 + ko.b(3, this.fI);
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
                    if (this.fH == null) {
                        b2 = b;
                        if (i.fH != null) {
                            return b2;
                        }
                    }
                    else if (!this.fH.equals(i.fH)) {
                        return false;
                    }
                    if (this.fI == null) {
                        b2 = b;
                        if (i.fI != null) {
                            return b2;
                        }
                    }
                    else if (!this.fI.equals(i.fI)) {
                        return false;
                    }
                    if (this.adU == null || this.adU.isEmpty()) {
                        if (i.adU != null) {
                            b2 = b;
                            if (!i.adU.isEmpty()) {
                                return b2;
                            }
                        }
                        return true;
                    }
                    return this.adU.equals(i.adU);
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            final boolean b = false;
            int hashCode;
            if (this.name == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.name.hashCode();
            }
            int hashCode2;
            if (this.fH == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.fH.hashCode();
            }
            int hashCode3;
            if (this.fI == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.fI.hashCode();
            }
            int hashCode4 = b ? 1 : 0;
            if (this.adU != null) {
                if (this.adU.isEmpty()) {
                    hashCode4 = (b ? 1 : 0);
                }
                else {
                    hashCode4 = this.adU.hashCode();
                }
            }
            return (hashCode3 + (hashCode2 + (hashCode + 527) * 31) * 31) * 31 + hashCode4;
        }
        
        public i j(final kn kn) throws IOException {
        Label_0057:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0057;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0057;
                    }
                    case 10: {
                        this.name = kn.readString();
                        continue;
                    }
                    case 18: {
                        if (this.fH == null) {
                            this.fH = new com.google.android.gms.internal.d.a();
                        }
                        kn.a(this.fH);
                        continue;
                    }
                    case 26: {
                        if (this.fI == null) {
                            this.fI = new d();
                        }
                        kn.a(this.fI);
                        continue;
                    }
                }
            }
            return this;
        }
        
        public i p() {
            this.name = "";
            this.fH = null;
            this.fI = null;
            this.adU = null;
            this.adY = -1;
            return this;
        }
    }
    
    public static final class j extends kp<j>
    {
        public i[] fJ;
        public f fK;
        public String fL;
        
        public j() {
            this.q();
        }
        
        public static j b(final byte[] array) throws ks {
            return kt.a(new j(), array);
        }
        
        @Override
        public void a(final ko ko) throws IOException {
            if (this.fJ != null && this.fJ.length > 0) {
                for (int i = 0; i < this.fJ.length; ++i) {
                    final i j = this.fJ[i];
                    if (j != null) {
                        ko.a(1, j);
                    }
                }
            }
            if (this.fK != null) {
                ko.a(2, this.fK);
            }
            if (!this.fL.equals("")) {
                ko.b(3, this.fL);
            }
            super.a(ko);
        }
        
        @Override
        public int c() {
            int c;
            int n = c = super.c();
            if (this.fJ != null) {
                c = n;
                if (this.fJ.length > 0) {
                    int n2 = 0;
                    while (true) {
                        c = n;
                        if (n2 >= this.fJ.length) {
                            break;
                        }
                        final i i = this.fJ[n2];
                        int n3 = n;
                        if (i != null) {
                            n3 = n + ko.b(1, i);
                        }
                        ++n2;
                        n = n3;
                    }
                }
            }
            int n4 = c;
            if (this.fK != null) {
                n4 = c + ko.b(2, this.fK);
            }
            int adY = n4;
            if (!this.fL.equals("")) {
                adY = n4 + ko.g(3, this.fL);
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
                if (o instanceof j) {
                    final j j = (j)o;
                    b2 = b;
                    if (kr.equals(this.fJ, j.fJ)) {
                        if (this.fK == null) {
                            b2 = b;
                            if (j.fK != null) {
                                return b2;
                            }
                        }
                        else if (!this.fK.equals(j.fK)) {
                            return false;
                        }
                        if (this.fL == null) {
                            b2 = b;
                            if (j.fL != null) {
                                return b2;
                            }
                        }
                        else if (!this.fL.equals(j.fL)) {
                            return false;
                        }
                        if (this.adU == null || this.adU.isEmpty()) {
                            if (j.adU != null) {
                                b2 = b;
                                if (!j.adU.isEmpty()) {
                                    return b2;
                                }
                            }
                            return true;
                        }
                        return this.adU.equals(j.adU);
                    }
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            final boolean b = false;
            final int hashCode = kr.hashCode(this.fJ);
            int hashCode2;
            if (this.fK == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.fK.hashCode();
            }
            int hashCode3;
            if (this.fL == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.fL.hashCode();
            }
            int hashCode4 = b ? 1 : 0;
            if (this.adU != null) {
                if (this.adU.isEmpty()) {
                    hashCode4 = (b ? 1 : 0);
                }
                else {
                    hashCode4 = this.adU.hashCode();
                }
            }
            return (hashCode3 + (hashCode2 + (hashCode + 527) * 31) * 31) * 31 + hashCode4;
        }
        
        public j k(final kn kn) throws IOException {
        Label_0057:
            while (true) {
                final int mh = kn.mh();
                switch (mh) {
                    default: {
                        if (!this.a(kn, mh)) {
                            break Label_0057;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0057;
                    }
                    case 10: {
                        final int b = kw.b(kn, 10);
                        int length;
                        if (this.fJ == null) {
                            length = 0;
                        }
                        else {
                            length = this.fJ.length;
                        }
                        final i[] fj = new i[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.fJ, 0, fj, 0, length);
                            i = length;
                        }
                        while (i < fj.length - 1) {
                            kn.a(fj[i] = new i());
                            kn.mh();
                            ++i;
                        }
                        kn.a(fj[i] = new i());
                        this.fJ = fj;
                        continue;
                    }
                    case 18: {
                        if (this.fK == null) {
                            this.fK = new f();
                        }
                        kn.a(this.fK);
                        continue;
                    }
                    case 26: {
                        this.fL = kn.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        public j q() {
            this.fJ = i.o();
            this.fK = null;
            this.fL = "";
            this.adU = null;
            this.adY = -1;
            return this;
        }
    }
}
