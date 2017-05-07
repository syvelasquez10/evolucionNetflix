// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

class u$a implements j$a<v>
{
    private final v Ar;
    
    public u$a() {
        this.Ar = new v();
    }
    
    @Override
    public void c(final String s, final int at) {
        if ("ga_dispatchPeriod".equals(s)) {
            this.Ar.At = at;
            return;
        }
        z.W("int configuration name not recognized:  " + s);
    }
    
    @Override
    public void d(final String s, final boolean b) {
        if ("ga_dryRun".equals(s)) {
            final v ar = this.Ar;
            int au;
            if (b) {
                au = 1;
            }
            else {
                au = 0;
            }
            ar.Au = au;
            return;
        }
        z.W("bool configuration name not recognized:  " + s);
    }
    
    public v et() {
        return this.Ar;
    }
    
    @Override
    public void f(final String s, final String s2) {
    }
    
    @Override
    public void g(final String s, final String as) {
        if ("ga_appName".equals(s)) {
            this.Ar.xL = as;
            return;
        }
        if ("ga_appVersion".equals(s)) {
            this.Ar.xM = as;
            return;
        }
        if ("ga_logLevel".equals(s)) {
            this.Ar.As = as;
            return;
        }
        z.W("string configuration name not recognized:  " + s);
    }
}
