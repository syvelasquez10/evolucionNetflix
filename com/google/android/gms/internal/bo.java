// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.d;
import android.graphics.drawable.Drawable;

@ez
public class bo extends br$a implements bq$a
{
    private final Object mw;
    private final String pl;
    private final Drawable pm;
    private final String pn;
    private final Drawable po;
    private final String pp;
    private final double pq;
    private final String pr;
    private final String ps;
    private bq pt;
    
    public bo(final String pl, final Drawable pm, final String pn, final Drawable po, final String pp, final double pq, final String pr, final String ps) {
        this.mw = new Object();
        this.pl = pl;
        this.pm = pm;
        this.pn = pn;
        this.po = po;
        this.pp = pp;
        this.pq = pq;
        this.pr = pr;
        this.ps = ps;
    }
    
    @Override
    public void a(final bq pt) {
        synchronized (this.mw) {
            this.pt = pt;
        }
    }
    
    public void as() {
        synchronized (this.mw) {
            if (this.pt == null) {
                gs.T("Attempt to record impression before app install ad initialized.");
                return;
            }
            this.pt.as();
        }
    }
    
    public String bt() {
        return this.pl;
    }
    
    public d bu() {
        return e.k(this.pm);
    }
    
    public d bv() {
        return e.k(this.po);
    }
    
    public String bw() {
        return this.pp;
    }
    
    public double bx() {
        return this.pq;
    }
    
    public String by() {
        return this.pr;
    }
    
    public String bz() {
        return this.ps;
    }
    
    public String getBody() {
        return this.pn;
    }
    
    public void i(final int n) {
        synchronized (this.mw) {
            if (this.pt == null) {
                gs.T("Attempt to perform click before app install ad initialized.");
                return;
            }
            this.pt.b("2", n);
        }
    }
}
