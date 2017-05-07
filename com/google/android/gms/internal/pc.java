// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class pc extends pg<pc>
{
    public pc$a[] avS;
    
    public pc() {
        this.qa();
    }
    
    public static pc n(final byte[] array) {
        return pm.a(new pc(), array);
    }
    
    @Override
    public void a(final pf pf) {
        if (this.avS != null && this.avS.length > 0) {
            for (int i = 0; i < this.avS.length; ++i) {
                final pc$a pc$a = this.avS[i];
                if (pc$a != null) {
                    pf.a(1, pc$a);
                }
            }
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        int c;
        int n = c = super.c();
        if (this.avS != null) {
            c = n;
            if (this.avS.length > 0) {
                int n2 = 0;
                while (true) {
                    c = n;
                    if (n2 >= this.avS.length) {
                        break;
                    }
                    final pc$a pc$a = this.avS[n2];
                    int n3 = n;
                    if (pc$a != null) {
                        n3 = n + pf.c(1, pc$a);
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
            if (o instanceof pc) {
                final pc pc = (pc)o;
                b2 = b;
                if (pk.equals(this.avS, pc.avS)) {
                    return this.a(pc);
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return (pk.hashCode(this.avS) + 527) * 31 + this.qx();
    }
    
    public pc q(final pe pe) {
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
                    if (this.avS == null) {
                        length = 0;
                    }
                    else {
                        length = this.avS.length;
                    }
                    final pc$a[] avS = new pc$a[b + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.avS, 0, avS, 0, length);
                        i = length;
                    }
                    while (i < avS.length - 1) {
                        pe.a(avS[i] = new pc$a());
                        pe.qg();
                        ++i;
                    }
                    pe.a(avS[i] = new pc$a());
                    this.avS = avS;
                    continue;
                }
            }
        }
        return this;
    }
    
    public pc qa() {
        this.avS = pc$a.qb();
        this.awy = null;
        this.awJ = -1;
        return this;
    }
}
