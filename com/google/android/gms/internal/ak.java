// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.ArrayList;

@ez
public class ak
{
    private final Object mw;
    private final int nf;
    private final int ng;
    private final int nh;
    private final ap ni;
    private ArrayList<String> nj;
    private int nk;
    private int nl;
    private int nm;
    private int nn;
    private String no;
    
    public ak(final int nf, final int ng, final int nh, final int n) {
        this.mw = new Object();
        this.nj = new ArrayList<String>();
        this.nk = 0;
        this.nl = 0;
        this.nm = 0;
        this.no = "";
        this.nf = nf;
        this.ng = ng;
        this.nh = nh;
        this.ni = new ap(n);
    }
    
    private String a(final ArrayList<String> list, final int n) {
        String string;
        if (list.isEmpty()) {
            string = "";
        }
        else {
            final StringBuffer sb = new StringBuffer();
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                sb.append(' ');
                if (sb.length() > n) {
                    break;
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            final String s = string = sb.toString();
            if (s.length() >= n) {
                return s.substring(0, n);
            }
        }
        return string;
    }
    
    private void j(final String s) {
        if (s == null || s.length() < this.nh) {
            return;
        }
        synchronized (this.mw) {
            this.nj.add(s);
            this.nk += s.length();
        }
    }
    
    int a(final int n, final int n2) {
        return this.nf * n + this.ng * n2;
    }
    
    public boolean aN() {
        while (true) {
            synchronized (this.mw) {
                if (this.nm == 0) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public String aO() {
        return this.no;
    }
    
    public void aP() {
        synchronized (this.mw) {
            this.nn -= 100;
        }
    }
    
    public void aQ() {
        synchronized (this.mw) {
            --this.nm;
        }
    }
    
    public void aR() {
        synchronized (this.mw) {
            ++this.nm;
        }
    }
    
    public void aS() {
        synchronized (this.mw) {
            final int a = this.a(this.nk, this.nl);
            if (a > this.nn) {
                this.nn = a;
                this.no = this.ni.a(this.nj);
            }
        }
    }
    
    int aT() {
        return this.nk;
    }
    
    public void c(final int nl) {
        this.nl = nl;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof ak) {
            if (o == this) {
                return true;
            }
            final ak ak = (ak)o;
            if (ak.aO() != null && ak.aO().equals(this.aO())) {
                return true;
            }
        }
        return false;
    }
    
    public int getScore() {
        return this.nn;
    }
    
    public void h(final String s) {
        this.j(s);
        synchronized (this.mw) {
            if (this.nm < 0) {
                gs.S("ActivityContent: negative number of WebViews.");
            }
            this.aS();
        }
    }
    
    @Override
    public int hashCode() {
        return this.aO().hashCode();
    }
    
    public void i(final String s) {
        this.j(s);
    }
    
    @Override
    public String toString() {
        return "ActivityContent fetchId: " + this.nl + " score:" + this.nn + " total_length:" + this.nk + "\n text: " + this.a(this.nj, 200) + "\n signture: " + this.no;
    }
}
