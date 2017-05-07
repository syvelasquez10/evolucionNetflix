// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class pc$a extends pg<pc$a>
{
    private static volatile pc$a[] avT;
    public pc$a$a avU;
    public String name;
    
    public pc$a() {
        this.qc();
    }
    
    public static pc$a[] qb() {
        Label_0027: {
            if (pc$a.avT != null) {
                break Label_0027;
            }
            synchronized (pk.awI) {
                if (pc$a.avT == null) {
                    pc$a.avT = new pc$a[0];
                }
                return pc$a.avT;
            }
        }
    }
    
    @Override
    public void a(final pf pf) {
        pf.b(1, this.name);
        if (this.avU != null) {
            pf.a(2, this.avU);
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        int n = super.c() + pf.j(1, this.name);
        if (this.avU != null) {
            n += pf.c(2, this.avU);
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
            if (o instanceof pc$a) {
                final pc$a pc$a = (pc$a)o;
                if (this.name == null) {
                    b2 = b;
                    if (pc$a.name != null) {
                        return b2;
                    }
                }
                else if (!this.name.equals(pc$a.name)) {
                    return false;
                }
                if (this.avU == null) {
                    b2 = b;
                    if (pc$a.avU != null) {
                        return b2;
                    }
                }
                else if (!this.avU.equals(pc$a.avU)) {
                    return false;
                }
                return this.a(pc$a);
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
        if (this.avU != null) {
            hashCode = this.avU.hashCode();
        }
        return ((hashCode2 + 527) * 31 + hashCode) * 31 + this.qx();
    }
    
    public pc$a qc() {
        this.name = "";
        this.avU = null;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    public pc$a r(final pe pe) {
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
                    this.name = pe.readString();
                    continue;
                }
                case 18: {
                    if (this.avU == null) {
                        this.avU = new pc$a$a();
                    }
                    pe.a(this.avU);
                    continue;
                }
            }
        }
        return this;
    }
}
