// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;

class ai$1 implements Runnable
{
    final /* synthetic */ Context mV;
    final /* synthetic */ gt mW;
    final /* synthetic */ gk mX;
    final /* synthetic */ String mY;
    final /* synthetic */ ai mZ;
    
    ai$1(final ai mz, final Context mv, final gt mw, final gk mx, final String my) {
        this.mZ = mz;
        this.mV = mv;
        this.mW = mw;
        this.mX = mx;
        this.mY = my;
    }
    
    @Override
    public void run() {
        this.mZ.a(this.mV, this.mW, this.mX).f(this.mY);
    }
}
