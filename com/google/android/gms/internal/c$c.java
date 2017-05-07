// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class c$c extends pg<c$c>
{
    private static volatile c$c[] fu;
    public String fv;
    public long fw;
    public long fx;
    public boolean fy;
    public long fz;
    
    public c$c() {
        this.g();
    }
    
    public static c$c[] f() {
        Label_0027: {
            if (c$c.fu != null) {
                break Label_0027;
            }
            synchronized (pk.awI) {
                if (c$c.fu == null) {
                    c$c.fu = new c$c[0];
                }
                return c$c.fu;
            }
        }
    }
    
    @Override
    public void a(final pf pf) {
        if (!this.fv.equals("")) {
            pf.b(1, this.fv);
        }
        if (this.fw != 0L) {
            pf.b(2, this.fw);
        }
        if (this.fx != 2147483647L) {
            pf.b(3, this.fx);
        }
        if (this.fy) {
            pf.b(4, this.fy);
        }
        if (this.fz != 0L) {
            pf.b(5, this.fz);
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
        if (this.fw != 0L) {
            n2 = c + pf.d(2, this.fw);
        }
        int n3 = n2;
        if (this.fx != 2147483647L) {
            n3 = n2 + pf.d(3, this.fx);
        }
        int n4 = n3;
        if (this.fy) {
            n4 = n3 + pf.c(4, this.fy);
        }
        int n5 = n4;
        if (this.fz != 0L) {
            n5 = n4 + pf.d(5, this.fz);
        }
        return n5;
    }
    
    public c$c d(final pe pe) {
    Label_0073:
        while (true) {
            final int qg = pe.qg();
            switch (qg) {
                default: {
                    if (!this.a(pe, qg)) {
                        break Label_0073;
                    }
                    continue;
                }
                case 0: {
                    break Label_0073;
                }
                case 10: {
                    this.fv = pe.readString();
                    continue;
                }
                case 16: {
                    this.fw = pe.qi();
                    continue;
                }
                case 24: {
                    this.fx = pe.qi();
                    continue;
                }
                case 32: {
                    this.fy = pe.qk();
                    continue;
                }
                case 40: {
                    this.fz = pe.qi();
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
            if (o instanceof c$c) {
                final c$c c$c = (c$c)o;
                if (this.fv == null) {
                    b2 = b;
                    if (c$c.fv != null) {
                        return b2;
                    }
                }
                else if (!this.fv.equals(c$c.fv)) {
                    return false;
                }
                b2 = b;
                if (this.fw == c$c.fw) {
                    b2 = b;
                    if (this.fx == c$c.fx) {
                        b2 = b;
                        if (this.fy == c$c.fy) {
                            b2 = b;
                            if (this.fz == c$c.fz) {
                                return this.a(c$c);
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public c$c g() {
        this.fv = "";
        this.fw = 0L;
        this.fx = 2147483647L;
        this.fy = false;
        this.fz = 0L;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.fv == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.fv.hashCode();
        }
        final int n = (int)(this.fw ^ this.fw >>> 32);
        final int n2 = (int)(this.fx ^ this.fx >>> 32);
        int n3;
        if (this.fy) {
            n3 = 1231;
        }
        else {
            n3 = 1237;
        }
        return ((n3 + (((hashCode + 527) * 31 + n) * 31 + n2) * 31) * 31 + (int)(this.fz ^ this.fz >>> 32)) * 31 + this.qx();
    }
}
