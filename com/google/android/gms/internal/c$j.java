// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class c$j extends pg<c$j>
{
    public c$i[] gr;
    public c$f gs;
    public String gt;
    
    public c$j() {
        this.q();
    }
    
    public static c$j b(final byte[] array) {
        return pm.a(new c$j(), array);
    }
    
    @Override
    public void a(final pf pf) {
        if (this.gr != null && this.gr.length > 0) {
            for (int i = 0; i < this.gr.length; ++i) {
                final c$i c$i = this.gr[i];
                if (c$i != null) {
                    pf.a(1, c$i);
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
                    final c$i c$i = this.gr[n2];
                    int n3 = n;
                    if (c$i != null) {
                        n3 = n + pf.c(1, c$i);
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
            if (o instanceof c$j) {
                final c$j c$j = (c$j)o;
                b2 = b;
                if (pk.equals(this.gr, c$j.gr)) {
                    if (this.gs == null) {
                        b2 = b;
                        if (c$j.gs != null) {
                            return b2;
                        }
                    }
                    else if (!this.gs.equals(c$j.gs)) {
                        return false;
                    }
                    if (this.gt == null) {
                        b2 = b;
                        if (c$j.gt != null) {
                            return b2;
                        }
                    }
                    else if (!this.gt.equals(c$j.gt)) {
                        return false;
                    }
                    return this.a(c$j);
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
    
    public c$j k(final pe pe) {
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
                    final c$i[] gr = new c$i[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.gr, 0, gr, 0, length);
                        i = length;
                    }
                    while (i < gr.length - 1) {
                        pe.a(gr[i] = new c$i());
                        pe.qg();
                        ++i;
                    }
                    pe.a(gr[i] = new c$i());
                    this.gr = gr;
                    continue;
                }
                case 18: {
                    if (this.gs == null) {
                        this.gs = new c$f();
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
    
    public c$j q() {
        this.gr = c$i.o();
        this.gs = null;
        this.gt = "";
        this.awy = null;
        this.awJ = -1;
        return this;
    }
}
