// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Handler;
import java.lang.ref.WeakReference;

@ez
public class ab
{
    private final a mj;
    private final Runnable mk;
    private av ml;
    private boolean mm;
    private boolean mn;
    private long mo;
    
    public ab(final u u) {
        this(u, new a(gr.wC));
    }
    
    ab(final u u, final a mj) {
        this.mm = false;
        this.mn = false;
        this.mo = 0L;
        this.mj = mj;
        this.mk = new Runnable() {
            private final WeakReference<u> mp = new WeakReference<u>(u);
            
            @Override
            public void run() {
                ab.this.mm = false;
                final u u = this.mp.get();
                if (u != null) {
                    u.b(ab.this.ml);
                }
            }
        };
    }
    
    public void a(final av ml, final long mo) {
        if (this.mm) {
            gs.W("An ad refresh is already scheduled.");
        }
        else {
            this.ml = ml;
            this.mm = true;
            this.mo = mo;
            if (!this.mn) {
                gs.U("Scheduling ad refresh " + mo + " milliseconds from now.");
                this.mj.postDelayed(this.mk, mo);
            }
        }
    }
    
    public boolean ay() {
        return this.mm;
    }
    
    public void c(final av av) {
        this.a(av, 60000L);
    }
    
    public void cancel() {
        this.mm = false;
        this.mj.removeCallbacks(this.mk);
    }
    
    public void pause() {
        this.mn = true;
        if (this.mm) {
            this.mj.removeCallbacks(this.mk);
        }
    }
    
    public void resume() {
        this.mn = false;
        if (this.mm) {
            this.mm = false;
            this.a(this.ml, this.mo);
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
