// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

@ez
public abstract class gg
{
    private final Runnable mk;
    private volatile Thread wf;
    
    public gg() {
        this.mk = new gg$1(this);
    }
    
    public final void cancel() {
        this.onStop();
        if (this.wf != null) {
            this.wf.interrupt();
        }
    }
    
    public abstract void cp();
    
    public abstract void onStop();
    
    public final void start() {
        gi.a(this.mk);
    }
}
