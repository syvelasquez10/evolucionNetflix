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

class u
{
    private static final u tH;
    private SortedSet<a> tE;
    private StringBuilder tF;
    private boolean tG;
    
    static {
        tH = new u();
    }
    
    private u() {
        this.tE = new TreeSet<a>();
        this.tF = new StringBuilder();
        this.tG = false;
    }
    
    public static u cy() {
        return u.tH;
    }
    
    public void a(final a a) {
        synchronized (this) {
            if (!this.tG) {
                this.tE.add(a);
                this.tF.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(a.ordinal()));
            }
        }
    }
    
    public String cA() {
        synchronized (this) {
            if (this.tF.length() > 0) {
                this.tF.insert(0, ".");
            }
            final String string = this.tF.toString();
            this.tF = new StringBuilder();
            return string;
        }
    }
    
    public String cz() {
        synchronized (this) {
            final StringBuilder sb = new StringBuilder();
            int n = 6;
            int n2 = 0;
            while (this.tE.size() > 0) {
                final a a = this.tE.first();
                this.tE.remove(a);
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
            this.tE.clear();
            return sb.toString();
        }
    }
    
    public void t(final boolean tg) {
        synchronized (this) {
            this.tG = tg;
        }
    }
    
    public enum a
    {
        tI, 
        tJ, 
        tK, 
        tL, 
        tM, 
        tN, 
        tO, 
        tP, 
        tQ, 
        tR, 
        tS, 
        tT, 
        tU, 
        tV, 
        tW, 
        tX, 
        tY, 
        tZ, 
        uA, 
        uB, 
        uC, 
        uD, 
        uE, 
        uF, 
        uG, 
        uH, 
        uI, 
        uJ, 
        uK, 
        uL, 
        uM, 
        uN, 
        uO, 
        uP, 
        uQ, 
        uR, 
        uS, 
        ua, 
        ub, 
        uc, 
        ud, 
        ue, 
        uf, 
        ug, 
        uh, 
        ui, 
        uj, 
        uk, 
        ul, 
        um, 
        un, 
        uo, 
        up, 
        uq, 
        ur, 
        us, 
        ut, 
        uu, 
        uv, 
        uw, 
        ux, 
        uy, 
        uz;
    }
}
