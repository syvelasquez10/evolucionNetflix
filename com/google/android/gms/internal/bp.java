// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.d;
import android.graphics.drawable.Drawable;

@ez
public class bp extends bs.a implements bq.a
{
    private final Object mw;
    private final String pl;
    private final Drawable pm;
    private final String pn;
    private final String pp;
    private bq pt;
    private final Drawable pu;
    private final String pv;
    
    public bp(final String pl, final Drawable pm, final String pn, final Drawable pu, final String pp, final String pv) {
        this.mw = new Object();
        this.pl = pl;
        this.pm = pm;
        this.pn = pn;
        this.pu = pu;
        this.pp = pp;
        this.pv = pv;
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
                gs.T("Attempt to record impression before content ad initialized.");
                return;
            }
            this.pt.as();
        }
    }
    
    public d bA() {
        return e.k(this.pu);
    }
    
    public String bB() {
        return this.pv;
    }
    
    public String bt() {
        return this.pl;
    }
    
    public d bu() {
        return e.k(this.pm);
    }
    
    public String bw() {
        return this.pp;
    }
    
    public String getBody() {
        return this.pn;
    }
    
    public void i(final int n) {
        synchronized (this.mw) {
            if (this.pt == null) {
                gs.T("Attempt to perform click before content ad initialized.");
                return;
            }
            this.pt.b("1", n);
        }
    }
}
