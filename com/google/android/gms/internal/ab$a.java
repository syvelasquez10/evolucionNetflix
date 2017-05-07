// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Handler;

public class ab$a
{
    private final Handler mHandler;
    
    public ab$a(final Handler mHandler) {
        this.mHandler = mHandler;
    }
    
    public boolean postDelayed(final Runnable runnable, final long n) {
        return this.mHandler.postDelayed(runnable, n);
    }
    
    public void removeCallbacks(final Runnable runnable) {
        this.mHandler.removeCallbacks(runnable);
    }
}
