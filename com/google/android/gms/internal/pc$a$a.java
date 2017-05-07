// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class pc$a$a extends pg<pc$a$a>
{
    private static volatile pc$a$a[] avV;
    public pc$a$a$a avW;
    public int type;
    
    public pc$a$a() {
        this.qe();
    }
    
    public static pc$a$a[] qd() {
        Label_0027: {
            if (pc$a$a.avV != null) {
                break Label_0027;
            }
            synchronized (pk.awI) {
                if (pc$a$a.avV == null) {
                    pc$a$a.avV = new pc$a$a[0];
                }
                return pc$a$a.avV;
            }
        }
    }
    
    @Override
    public void a(final pf pf) {
        pf.s(1, this.type);
        if (this.avW != null) {
            pf.a(2, this.avW);
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        int n = super.c() + pf.u(1, this.type);
        if (this.avW != null) {
            n += pf.c(2, this.avW);
        }
        return n;
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
            if (o instanceof pc$a$a) {
                final pc$a$a pc$a$a = (pc$a$a)o;
                b2 = b;
                if (this.type == pc$a$a.type) {
                    if (this.avW == null) {
                        b2 = b;
                        if (pc$a$a.avW != null) {
                            return b2;
                        }
                    }
                    else if (!this.avW.equals(pc$a$a.avW)) {
                        return false;
                    }
                    return this.a(pc$a$a);
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        final int type = this.type;
        int hashCode;
        if (this.avW == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.avW.hashCode();
        }
        return (hashCode + (type + 527) * 31) * 31 + this.qx();
    }
    
    public pc$a$a qe() {
        this.type = 1;
        this.avW = null;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    public pc$a$a s(final pe pe) {
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
                    final int qj = pe.qj();
                    switch (qj) {
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
                        case 15: {
                            this.type = qj;
                            continue;
                        }
                    }
                    break;
                }
                case 18: {
                    if (this.avW == null) {
                        this.avW = new pc$a$a$a();
                    }
                    pe.a(this.avW);
                    continue;
                }
            }
        }
        return this;
    }
}
