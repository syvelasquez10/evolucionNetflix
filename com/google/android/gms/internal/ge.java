// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;

@ez
public class ge
{
    private final Object mw;
    private final String vA;
    private final gb vx;
    private int wc;
    private int wd;
    
    ge(final gb vx, final String va) {
        this.mw = new Object();
        this.vx = vx;
        this.vA = va;
    }
    
    public ge(final String s) {
        this(gb.cV(), s);
    }
    
    public void d(final int wc, final int wd) {
        synchronized (this.mw) {
            this.wc = wc;
            this.wd = wd;
            this.vx.a(this.vA, this);
        }
    }
    
    public Bundle toBundle() {
        synchronized (this.mw) {
            final Bundle bundle = new Bundle();
            bundle.putInt("pmnli", this.wc);
            bundle.putInt("pmnll", this.wd);
            return bundle;
        }
    }
}
