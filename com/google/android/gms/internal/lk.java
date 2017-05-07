// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public interface lk
{
    public static final class a extends pg<lk.a>
    {
        public lk.a.a[] adt;
        
        public a() {
            this.lN();
        }
        
        @Override
        public void a(final pf pf) throws IOException {
            if (this.adt != null && this.adt.length > 0) {
                for (int i = 0; i < this.adt.length; ++i) {
                    final lk.a.a a = this.adt[i];
                    if (a != null) {
                        pf.a(1, a);
                    }
                }
            }
            super.a(pf);
        }
        
        @Override
        protected int c() {
            int c;
            int n = c = super.c();
            if (this.adt != null) {
                c = n;
                if (this.adt.length > 0) {
                    int n2 = 0;
                    while (true) {
                        c = n;
                        if (n2 >= this.adt.length) {
                            break;
                        }
                        final lk.a.a a = this.adt[n2];
                        int n3 = n;
                        if (a != null) {
                            n3 = n + pf.c(1, a);
                        }
                        ++n2;
                        n = n3;
                    }
                }
            }
            return c;
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
                if (o instanceof lk.a) {
                    final lk.a a = (lk.a)o;
                    b2 = b;
                    if (pk.equals(this.adt, a.adt)) {
                        return this.a(a);
                    }
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            return (pk.hashCode(this.adt) + 527) * 31 + this.qx();
        }
        
        public lk.a lN() {
            this.adt = lk.a.a.lO();
            this.awy = null;
            this.awJ = -1;
            return this;
        }
        
        public lk.a n(final pe pe) throws IOException {
        Label_0041:
            while (true) {
                final int qg = pe.qg();
                switch (qg) {
                    default: {
                        if (!this.a(pe, qg)) {
                            break Label_0041;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0041;
                    }
                    case 10: {
                        final int b = pp.b(pe, 10);
                        int length;
                        if (this.adt == null) {
                            length = 0;
                        }
                        else {
                            length = this.adt.length;
                        }
                        final lk.a.a[] adt = new lk.a.a[b + length];
                        int i = length;
                        if (length != 0) {
                            System.arraycopy(this.adt, 0, adt, 0, length);
                            i = length;
                        }
                        while (i < adt.length - 1) {
                            pe.a(adt[i] = new lk.a.a());
                            pe.qg();
                            ++i;
                        }
                        pe.a(adt[i] = new lk.a.a());
                        this.adt = adt;
                        continue;
                    }
                }
            }
            return this;
        }
        
        public static final class a extends pg<a>
        {
            private static volatile a[] adu;
            public String adv;
            public String adw;
            public int viewId;
            
            public a() {
                this.lP();
            }
            
            public static a[] lO() {
                Label_0027: {
                    if (a.adu != null) {
                        break Label_0027;
                    }
                    synchronized (pk.awI) {
                        if (a.adu == null) {
                            a.adu = new a[0];
                        }
                        return a.adu;
                    }
                }
            }
            
            @Override
            public void a(final pf pf) throws IOException {
                if (!this.adv.equals("")) {
                    pf.b(1, this.adv);
                }
                if (!this.adw.equals("")) {
                    pf.b(2, this.adw);
                }
                if (this.viewId != 0) {
                    pf.s(3, this.viewId);
                }
                super.a(pf);
            }
            
            @Override
            protected int c() {
                int c;
                final int n = c = super.c();
                if (!this.adv.equals("")) {
                    c = n + pf.j(1, this.adv);
                }
                int n2 = c;
                if (!this.adw.equals("")) {
                    n2 = c + pf.j(2, this.adw);
                }
                int n3 = n2;
                if (this.viewId != 0) {
                    n3 = n2 + pf.u(3, this.viewId);
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
                        if (this.adv == null) {
                            b2 = b;
                            if (a.adv != null) {
                                return b2;
                            }
                        }
                        else if (!this.adv.equals(a.adv)) {
                            return false;
                        }
                        if (this.adw == null) {
                            b2 = b;
                            if (a.adw != null) {
                                return b2;
                            }
                        }
                        else if (!this.adw.equals(a.adw)) {
                            return false;
                        }
                        b2 = b;
                        if (this.viewId == a.viewId) {
                            return this.a(a);
                        }
                    }
                }
                return b2;
            }
            
            @Override
            public int hashCode() {
                int hashCode = 0;
                int hashCode2;
                if (this.adv == null) {
                    hashCode2 = 0;
                }
                else {
                    hashCode2 = this.adv.hashCode();
                }
                if (this.adw != null) {
                    hashCode = this.adw.hashCode();
                }
                return (((hashCode2 + 527) * 31 + hashCode) * 31 + this.viewId) * 31 + this.qx();
            }
            
            public a lP() {
                this.adv = "";
                this.adw = "";
                this.viewId = 0;
                this.awy = null;
                this.awJ = -1;
                return this;
            }
            
            public a o(final pe pe) throws IOException {
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
                            this.adv = pe.readString();
                            continue;
                        }
                        case 18: {
                            this.adw = pe.readString();
                            continue;
                        }
                        case 24: {
                            this.viewId = pe.qj();
                            continue;
                        }
                    }
                }
                return this;
            }
        }
    }
}
