// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;

public final class ea
{
    private final String pM;
    
    public ea(final String s) {
        this.pM = eg.f(s);
    }
    
    public boolean K(final int n) {
        return Log.isLoggable(this.pM, n);
    }
    
    public void a(final String s, final String s2, final Throwable t) {
        if (this.K(6)) {
            Log.e(s, s2, t);
        }
    }
    
    public void c(final String s, final String s2) {
        if (this.K(5)) {
            Log.w(s, s2);
        }
    }
    
    public void d(final String s, final String s2) {
        if (this.K(6)) {
            Log.e(s, s2);
        }
    }
}
