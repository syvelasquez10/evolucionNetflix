// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeSet;
import java.util.SortedSet;

class t
{
    private static final t ze;
    private SortedSet<a> zb;
    private StringBuilder zc;
    private boolean zd;
    
    static {
        ze = new t();
    }
    
    private t() {
        this.zb = new TreeSet<a>();
        this.zc = new StringBuilder();
        this.zd = false;
    }
    
    public static t eq() {
        return t.ze;
    }
    
    public void B(final boolean zd) {
        synchronized (this) {
            this.zd = zd;
        }
    }
    
    public void a(final a a) {
        synchronized (this) {
            if (!this.zd) {
                this.zb.add(a);
                this.zc.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(a.ordinal()));
            }
        }
    }
    
    public String er() {
        synchronized (this) {
            final StringBuilder sb = new StringBuilder();
            int n = 6;
            int n2 = 0;
            while (this.zb.size() > 0) {
                final a a = this.zb.first();
                this.zb.remove(a);
                while (a.ordinal() >= n) {
                    sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(n2));
                    n += 6;
                    n2 = 0;
                }
                n2 += 1 << a.ordinal() % 6;
            }
            if (n2 > 0 || sb.length() == 0) {
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(n2));
            }
            this.zb.clear();
            return sb.toString();
        }
    }
    
    public String es() {
        synchronized (this) {
            if (this.zc.length() > 0) {
                this.zc.insert(0, ".");
            }
            final String string = this.zc.toString();
            this.zc = new StringBuilder();
            return string;
        }
    }
    
    public enum a
    {
        Aa, 
        Ab, 
        Ac, 
        Ad, 
        Ae, 
        Af, 
        Ag, 
        Ah, 
        Ai, 
        Aj, 
        Ak, 
        Al, 
        Am, 
        An, 
        Ao, 
        Ap, 
        zA, 
        zB, 
        zC, 
        zD, 
        zE, 
        zF, 
        zG, 
        zH, 
        zI, 
        zJ, 
        zK, 
        zL, 
        zM, 
        zN, 
        zO, 
        zP, 
        zQ, 
        zR, 
        zS, 
        zT, 
        zU, 
        zV, 
        zW, 
        zX, 
        zY, 
        zZ, 
        zf, 
        zg, 
        zh, 
        zi, 
        zj, 
        zk, 
        zl, 
        zm, 
        zn, 
        zo, 
        zp, 
        zq, 
        zr, 
        zs, 
        zt, 
        zu, 
        zv, 
        zw, 
        zx, 
        zy, 
        zz;
    }
}
