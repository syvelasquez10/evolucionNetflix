// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

final class do$a
{
    private final Runnable mk;
    private volatile boolean sc;
    
    public do$a(final do do1) {
        this.sc = false;
        this.mk = new do$a$1(this, do1);
    }
    
    public void cancel() {
        this.sc = true;
        gr.wC.removeCallbacks(this.mk);
    }
    
    public void ck() {
        gr.wC.postDelayed(this.mk, 250L);
    }
}
