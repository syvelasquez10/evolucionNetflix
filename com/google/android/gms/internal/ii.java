// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;

public abstract class ii
{
    protected final ip Go;
    private final String Gp;
    private ir Gq;
    
    protected ii(final String gp, final String s, final String s2) {
        ik.aF(gp);
        this.Gp = gp;
        this.Go = new ip(s);
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            this.Go.aK(s2);
        }
    }
    
    public final void a(final ir gq) {
        this.Gq = gq;
        if (this.Gq == null) {
            this.fB();
        }
    }
    
    protected final void a(final String s, final long n, final String s2) {
        this.Go.a("Sending text message: %s to: %s", s, s2);
        this.Gq.a(this.Gp, s, n, s2);
    }
    
    public void aD(final String s) {
    }
    
    public void b(final long n, final int n2) {
    }
    
    protected final long fA() {
        return this.Gq.fy();
    }
    
    public void fB() {
    }
    
    public final String getNamespace() {
        return this.Gp;
    }
}
