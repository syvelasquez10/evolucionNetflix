// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class pq$d extends pg<pq$d>
{
    private static volatile pq$d[] axj;
    public String fv;
    public String value;
    
    public pq$d() {
        this.qL();
    }
    
    public static pq$d[] qK() {
        Label_0027: {
            if (pq$d.axj != null) {
                break Label_0027;
            }
            synchronized (pk.awI) {
                if (pq$d.axj == null) {
                    pq$d.axj = new pq$d[0];
                }
                return pq$d.axj;
            }
        }
    }
    
    @Override
    public void a(final pf pf) {
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
            if (o instanceof pq$d) {
                final pq$d pq$d = (pq$d)o;
                if (this.fv == null) {
                    b2 = b;
                    if (pq$d.fv != null) {
                        return b2;
                    }
                }
                else if (!this.fv.equals(pq$d.fv)) {
                    return false;
                }
                if (this.value == null) {
                    b2 = b;
                    if (pq$d.value != null) {
                        return b2;
                    }
                }
                else if (!this.value.equals(pq$d.value)) {
                    return false;
                }
                return this.a(pq$d);
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
    
    public pq$d qL() {
        this.fv = "";
        this.value = "";
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    public pq$d y(final pe pe) {
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
