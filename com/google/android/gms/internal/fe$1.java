// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

class fe$1 implements Runnable
{
    final /* synthetic */ fe tr;
    
    fe$1(final fe tr) {
        this.tr = tr;
    }
    
    @Override
    public void run() {
        this.tr.onStop();
    }
}
