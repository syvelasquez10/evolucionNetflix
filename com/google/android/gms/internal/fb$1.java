// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

class fb$1 implements Runnable
{
    final /* synthetic */ fb ta;
    
    fb$1(final fb ta) {
        this.ta = ta;
    }
    
    @Override
    public void run() {
        this.ta.onStop();
    }
}
