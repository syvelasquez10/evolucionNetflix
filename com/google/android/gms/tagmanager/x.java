// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.util.Log;

class x implements bi
{
    private int sz;
    
    x() {
        this.sz = 5;
    }
    
    @Override
    public void b(final String s, final Throwable t) {
        if (this.sz <= 6) {
            Log.e("GoogleTagManager", s, t);
        }
    }
    
    @Override
    public void c(final String s, final Throwable t) {
        if (this.sz <= 5) {
            Log.w("GoogleTagManager", s, t);
        }
    }
    
    @Override
    public void setLogLevel(final int sz) {
        this.sz = sz;
    }
    
    @Override
    public void v(final String s) {
        if (this.sz <= 3) {
            Log.d("GoogleTagManager", s);
        }
    }
    
    @Override
    public void w(final String s) {
        if (this.sz <= 6) {
            Log.e("GoogleTagManager", s);
        }
    }
    
    @Override
    public void x(final String s) {
        if (this.sz <= 4) {
            Log.i("GoogleTagManager", s);
        }
    }
    
    @Override
    public void y(final String s) {
        if (this.sz <= 2) {
            Log.v("GoogleTagManager", s);
        }
    }
    
    @Override
    public void z(final String s) {
        if (this.sz <= 5) {
            Log.w("GoogleTagManager", s);
        }
    }
}
