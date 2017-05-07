// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class c$e extends pg<c$e>
{
    private static volatile c$e[] fD;
    public int key;
    public int value;
    
    public c$e() {
        this.j();
    }
    
    public static c$e[] i() {
        Label_0027: {
            if (c$e.fD != null) {
                break Label_0027;
            }
            synchronized (pk.awI) {
                if (c$e.fD == null) {
                    c$e.fD = new c$e[0];
                }
                return c$e.fD;
            }
        }
    }
    
    @Override
    public void a(final pf pf) {
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
            if (o instanceof c$e) {
                final c$e c$e = (c$e)o;
                b2 = b;
                if (this.key == c$e.key) {
                    b2 = b;
                    if (this.value == c$e.value) {
                        return this.a(c$e);
                    }
                }
            }
        }
        return b2;
    }
    
    public c$e f(final pe pe) {
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
    
    public c$e j() {
        this.key = 0;
        this.value = 0;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
}
