// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

class gg$1 implements Runnable
{
    final /* synthetic */ gg wg;
    
    gg$1(final gg wg) {
        this.wg = wg;
    }
    
    @Override
    public final void run() {
        this.wg.wf = Thread.currentThread();
        this.wg.cp();
    }
}
