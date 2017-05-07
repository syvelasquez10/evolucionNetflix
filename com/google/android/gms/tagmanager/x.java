// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.util.Log;

class x implements bi
{
    private int xW;
    
    x() {
        this.xW = 5;
    }
    
    @Override
    public void S(final String s) {
        if (this.xW <= 3) {
            Log.d("GoogleTagManager", s);
        }
    }
    
    @Override
    public void T(final String s) {
        if (this.xW <= 6) {
            Log.e("GoogleTagManager", s);
        }
    }
    
    @Override
    public void U(final String s) {
        if (this.xW <= 4) {
            Log.i("GoogleTagManager", s);
        }
    }
    
    @Override
    public void V(final String s) {
        if (this.xW <= 2) {
            Log.v("GoogleTagManager", s);
        }
    }
    
    @Override
    public void W(final String s) {
        if (this.xW <= 5) {
            Log.w("GoogleTagManager", s);
        }
    }
    
    @Override
    public void b(final String s, final Throwable t) {
        if (this.xW <= 6) {
            Log.e("GoogleTagManager", s, t);
        }
    }
    
    @Override
    public void d(final String s, final Throwable t) {
        if (this.xW <= 5) {
            Log.w("GoogleTagManager", s, t);
        }
    }
    
    @Override
    public void setLogLevel(final int xw) {
        this.xW = xw;
    }
}
