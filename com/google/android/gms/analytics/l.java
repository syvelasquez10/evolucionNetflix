// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.util.Log;

class l implements Logger
{
    private int sz;
    
    l() {
        this.sz = 1;
    }
    
    private String E(final String s) {
        return Thread.currentThread().toString() + ": " + s;
    }
    
    @Override
    public void error(final Exception ex) {
        if (this.sz <= 3) {
            Log.e("GAV4", (String)null, (Throwable)ex);
        }
    }
    
    @Override
    public void error(final String s) {
        if (this.sz <= 3) {
            Log.e("GAV4", this.E(s));
        }
    }
    
    @Override
    public int getLogLevel() {
        return this.sz;
    }
    
    @Override
    public void info(final String s) {
        if (this.sz <= 1) {
            Log.i("GAV4", this.E(s));
        }
    }
    
    @Override
    public void setLogLevel(final int sz) {
        this.sz = sz;
    }
    
    @Override
    public void verbose(final String s) {
        if (this.sz <= 0) {
            Log.v("GAV4", this.E(s));
        }
    }
    
    @Override
    public void warn(final String s) {
        if (this.sz <= 2) {
            Log.w("GAV4", this.E(s));
        }
    }
}
