// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.View;

class an$1 implements Runnable
{
    final /* synthetic */ View nA;
    final /* synthetic */ an nB;
    
    an$1(final an nb, final View na) {
        this.nB = nb;
        this.nA = na;
    }
    
    @Override
    public void run() {
        this.nB.h(this.nA);
    }
}
