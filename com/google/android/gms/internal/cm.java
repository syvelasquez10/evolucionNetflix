// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public abstract class cm
{
    private final Runnable ep;
    private volatile Thread ix;
    
    public cm() {
        this.ep = new Runnable() {
            @Override
            public final void run() {
                cm.this.ix = Thread.currentThread();
                cm.this.ai();
            }
        };
    }
    
    public abstract void ai();
    
    public final void cancel() {
        this.onStop();
        if (this.ix != null) {
            this.ix.interrupt();
        }
    }
    
    public abstract void onStop();
    
    public final void start() {
        cn.execute(this.ep);
    }
}
