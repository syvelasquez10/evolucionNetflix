// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class c$i extends pg<c$i>
{
    private static volatile c$i[] go;
    public d$a gp;
    public c$d gq;
    public String name;
    
    public c$i() {
        this.p();
    }
    
    public static c$i[] o() {
        Label_0027: {
            if (c$i.go != null) {
                break Label_0027;
            }
            synchronized (pk.awI) {
                if (c$i.go == null) {
                    c$i.go = new c$i[0];
                }
                return c$i.go;
            }
        }
    }
    
    @Override
    public void a(final pf pf) {
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
            if (o instanceof c$i) {
                final c$i c$i = (c$i)o;
                if (this.name == null) {
                    b2 = b;
                    if (c$i.name != null) {
                        return b2;
                    }
                }
                else if (!this.name.equals(c$i.name)) {
                    return false;
                }
                if (this.gp == null) {
                    b2 = b;
                    if (c$i.gp != null) {
                        return b2;
                    }
                }
                else if (!this.gp.equals(c$i.gp)) {
                    return false;
                }
                if (this.gq == null) {
                    b2 = b;
                    if (c$i.gq != null) {
                        return b2;
                    }
                }
                else if (!this.gq.equals(c$i.gq)) {
                    return false;
                }
                return this.a(c$i);
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
    
    public c$i j(final pe pe) {
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
                        this.gp = new d$a();
                    }
                    pe.a(this.gp);
                    continue;
                }
                case 26: {
                    if (this.gq == null) {
                        this.gq = new c$d();
                    }
                    pe.a(this.gq);
                    continue;
                }
            }
        }
        return this;
    }
    
    public c$i p() {
        this.name = "";
        this.gp = null;
        this.gq = null;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
}
