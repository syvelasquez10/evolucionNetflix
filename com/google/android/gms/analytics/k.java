// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.util.Log;

class k implements Logger
{
    private int xW;
    
    k() {
        this.xW = 2;
    }
    
    private String ae(final String s) {
        return Thread.currentThread().toString() + ": " + s;
    }
    
    @Override
    public void error(final Exception ex) {
        if (this.xW <= 3) {
            Log.e("GAV4", (String)null, (Throwable)ex);
        }
    }
    
    @Override
    public void error(final String s) {
        if (this.xW <= 3) {
            Log.e("GAV4", this.ae(s));
        }
    }
    
    @Override
    public int getLogLevel() {
        return this.xW;
    }
    
    @Override
    public void info(final String s) {
        if (this.xW <= 1) {
            Log.i("GAV4", this.ae(s));
        }
    }
    
    @Override
    public void setLogLevel(final int xw) {
        this.xW = xw;
    }
    
    @Override
    public void verbose(final String s) {
        if (this.xW <= 0) {
            Log.v("GAV4", this.ae(s));
        }
    }
    
    @Override
    public void warn(final String s) {
        if (this.xW <= 2) {
            Log.w("GAV4", this.ae(s));
        }
    }
}
