// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class lk$a$a extends pg<lk$a$a>
{
    private static volatile lk$a$a[] adu;
    public String adv;
    public String adw;
    public int viewId;
    
    public lk$a$a() {
        this.lP();
    }
    
    public static lk$a$a[] lO() {
        Label_0027: {
            if (lk$a$a.adu != null) {
                break Label_0027;
            }
            synchronized (pk.awI) {
                if (lk$a$a.adu == null) {
                    lk$a$a.adu = new lk$a$a[0];
                }
                return lk$a$a.adu;
            }
        }
    }
    
    @Override
    public void a(final pf pf) {
        if (!this.adv.equals("")) {
            pf.b(1, this.adv);
        }
        if (!this.adw.equals("")) {
            pf.b(2, this.adw);
        }
        if (this.viewId != 0) {
            pf.s(3, this.viewId);
        }
        super.a(pf);
    }
    
    @Override
    protected int c() {
        int c;
        final int n = c = super.c();
        if (!this.adv.equals("")) {
            c = n + pf.j(1, this.adv);
        }
        int n2 = c;
        if (!this.adw.equals("")) {
            n2 = c + pf.j(2, this.adw);
        }
        int n3 = n2;
        if (this.viewId != 0) {
            n3 = n2 + pf.u(3, this.viewId);
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
            if (o instanceof lk$a$a) {
                final lk$a$a lk$a$a = (lk$a$a)o;
                if (this.adv == null) {
                    b2 = b;
                    if (lk$a$a.adv != null) {
                        return b2;
                    }
                }
                else if (!this.adv.equals(lk$a$a.adv)) {
                    return false;
                }
                if (this.adw == null) {
                    b2 = b;
                    if (lk$a$a.adw != null) {
                        return b2;
                    }
                }
                else if (!this.adw.equals(lk$a$a.adw)) {
                    return false;
                }
                b2 = b;
                if (this.viewId == lk$a$a.viewId) {
                    return this.a(lk$a$a);
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.adv == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.adv.hashCode();
        }
        if (this.adw != null) {
            hashCode = this.adw.hashCode();
        }
        return (((hashCode2 + 527) * 31 + hashCode) * 31 + this.viewId) * 31 + this.qx();
    }
    
    public lk$a$a lP() {
        this.adv = "";
        this.adw = "";
        this.viewId = 0;
        this.awy = null;
        this.awJ = -1;
        return this;
    }
    
    public lk$a$a o(final pe pe) {
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
                    this.adv = pe.readString();
                    continue;
                }
                case 18: {
                    this.adw = pe.readString();
                    continue;
                }
                case 24: {
                    this.viewId = pe.qj();
                    continue;
                }
            }
        }
        return this;
    }
}
