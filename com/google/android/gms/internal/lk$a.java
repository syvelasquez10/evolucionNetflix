// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class lk$a extends pg<lk$a>
{
    public lk$a$a[] adt;
    
    public lk$a() {
        this.lN();
    }
    
    @Override
    public void a(final pf pf) {
        if (this.adt != null && this.adt.length > 0) {
            for (int i = 0; i < this.adt.length; ++i) {
                final lk$a$a lk$a$a = this.adt[i];
                if (lk$a$a != null) {
                    pf.a(1, lk$a$a);
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
                    final lk$a$a lk$a$a = this.adt[n2];
                    int n3 = n;
                    if (lk$a$a != null) {
                        n3 = n + pf.c(1, lk$a$a);
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
            if (o instanceof lk$a) {
                final lk$a lk$a = (lk$a)o;
                b2 = b;
                if (pk.equals(this.adt, lk$a.adt)) {
                    return this.a(lk$a);
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return (pk.hashCode(this.adt) + 527) * 31 + this.qx();
    }
    
    public lk$a lN() {
        this.adt = lk$a$a.lO();
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    public lk$a n(final pe pe) {
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
                    final lk$a$a[] adt = new lk$a$a[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.adt, 0, adt, 0, length);
                        i = length;
                    }
                    while (i < adt.length - 1) {
                        pe.a(adt[i] = new lk$a$a());
                        pe.qg();
                        ++i;
                    }
                    pe.a(adt[i] = new lk$a$a());
                    this.adt = adt;
                    continue;
                }
            }
        }
        return this;
    }
}
