// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

class zzs implements Logger
{
    private boolean zzKz;
    private int zzMQ;
    
    zzs() {
        this.zzMQ = 2;
    }
    
    @Override
    public void error(final String s) {
    }
    
    @Override
    public int getLogLevel() {
        return this.zzMQ;
    }
    
    @Override
    public void setLogLevel(final int zzMQ) {
        this.zzMQ = zzMQ;
        if (!this.zzKz) {
            Log.i((String)zzy.zzNa.get(), "Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag." + zzy.zzNa.get() + " DEBUG");
            this.zzKz = true;
        }
    }
    
    @Override
    public void warn(final String s) {
    }
}
