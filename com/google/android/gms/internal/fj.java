// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;

public final class fj
{
    private final String DH;
    
    public fj(final String s) {
        this.DH = fq.f(s);
    }
    
    public boolean P(final int n) {
        return Log.isLoggable(this.DH, n);
    }
    
    public void a(final String s, final String s2, final Throwable t) {
        if (this.P(6)) {
            Log.e(s, s2, t);
        }
    }
    
    public void f(final String s, final String s2) {
        if (this.P(2)) {
            Log.v(s, s2);
        }
    }
    
    public void g(final String s, final String s2) {
        if (this.P(5)) {
            Log.w(s, s2);
        }
    }
    
    public void h(final String s, final String s2) {
        if (this.P(6)) {
            Log.e(s, s2);
        }
    }
}
