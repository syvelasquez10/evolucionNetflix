// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class pq$b extends pg<pq$b>
{
    public int awW;
    public String awX;
    public String version;
    
    public pq$b() {
        this.qI();
    }
    
    @Override
    public void a(final pf pf) {
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
            if (o instanceof pq$b) {
                final pq$b pq$b = (pq$b)o;
                b2 = b;
                if (this.awW == pq$b.awW) {
                    if (this.awX == null) {
                        b2 = b;
                        if (pq$b.awX != null) {
                            return b2;
                        }
                    }
                    else if (!this.awX.equals(pq$b.awX)) {
                        return false;
                    }
                    if (this.version == null) {
                        b2 = b;
                        if (pq$b.version != null) {
                            return b2;
                        }
                    }
                    else if (!this.version.equals(pq$b.version)) {
                        return false;
                    }
                    return this.a(pq$b);
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
    
    public pq$b qI() {
        this.awW = 0;
        this.awX = "";
        this.version = "";
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    public pq$b w(final pe pe) {
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
