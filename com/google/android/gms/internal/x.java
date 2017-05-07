// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Handler;
import java.lang.ref.WeakReference;

public final class x
{
    private final a kV;
    private final Runnable kW;
    private ah kX;
    private boolean kY;
    private boolean kZ;
    private long la;
    
    public x(final v v) {
        this(v, new a(dv.rp));
    }
    
    x(final v v, final a kv) {
        this.kY = false;
        this.kZ = false;
        this.la = 0L;
        this.kV = kv;
        this.kW = new Runnable() {
            private final WeakReference<v> lb = new WeakReference<v>(v);
            
            @Override
            public void run() {
                x.this.kY = false;
                final v v = this.lb.get();
                if (v != null) {
                    v.b(x.this.kX);
                }
            }
        };
    }
    
    public void a(final ah kx, final long la) {
        if (this.kY) {
            dw.z("An ad refresh is already scheduled.");
        }
        else {
            this.kX = kx;
            this.kY = true;
            this.la = la;
            if (!this.kZ) {
                dw.x("Scheduling ad refresh " + la + " milliseconds from now.");
                this.kV.postDelayed(this.kW, la);
            }
        }
    }
    
    public void cancel() {
        this.kY = false;
        this.kV.removeCallbacks(this.kW);
    }
    
    public void d(final ah ah) {
        this.a(ah, 60000L);
    }
    
    public void pause() {
        this.kZ = true;
        if (this.kY) {
            this.kV.removeCallbacks(this.kW);
        }
    }
    
    public void resume() {
        this.kZ = false;
        if (this.kY) {
            this.kY = false;
            this.a(this.kX, this.la);
        }
    }
    
    public static class a
    {
        private final Handler mHandler;
        
        public a(final Handler mHandler) {
            this.mHandler = mHandler;
        }
        
        public boolean postDelayed(final Runnable runnable, final long n) {
            return this.mHandler.postDelayed(runnable, n);
        }
        
        public void removeCallbacks(final Runnable runnable) {
            this.mHandler.removeCallbacks(runnable);
        }
    }
}
