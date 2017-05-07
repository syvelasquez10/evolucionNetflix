// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONObject;
import android.os.SystemClock;

public final class do
{
    private static final dk lA;
    public static final Object mw;
    private long ms;
    private long mt;
    private long mu;
    private dn mv;
    
    static {
        lA = new dk("RequestTracker");
        mw = new Object();
    }
    
    public do(final long ms) {
        this.ms = ms;
        this.mt = -1L;
        this.mu = 0L;
    }
    
    private void be() {
        this.mt = -1L;
        this.mv = null;
        this.mu = 0L;
    }
    
    public void a(final long mt, final dn mv) {
        synchronized (do.mw) {
            final dn mv2 = this.mv;
            final long mt2 = this.mt;
            this.mt = mt;
            this.mv = mv;
            this.mu = SystemClock.elapsedRealtime();
            // monitorexit(do.mw)
            if (mv2 != null) {
                mv2.g(mt2);
            }
        }
    }
    
    public boolean b(final long n, final int n2, final JSONObject jsonObject) {
        while (true) {
            boolean b = true;
            dn mv = null;
            while (true) {
                synchronized (do.mw) {
                    if (this.mt != -1L && this.mt == n) {
                        do.lA.b("request %d completed", this.mt);
                        mv = this.mv;
                        this.be();
                        // monitorexit(do.mw)
                        if (mv != null) {
                            mv.a(n, n2, jsonObject);
                        }
                        return b;
                    }
                }
                b = false;
                continue;
            }
        }
    }
    
    public boolean bf() {
        while (true) {
            synchronized (do.mw) {
                if (this.mt != -1L) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean c(final long n, final int n2) {
        return this.b(n, n2, null);
    }
    
    public void clear() {
        synchronized (do.mw) {
            if (this.mt != -1L) {
                this.be();
            }
        }
    }
    
    public boolean d(long mt, final int n) {
        while (true) {
            boolean b = true;
            final long n2 = 0L;
            while (true) {
                synchronized (do.mw) {
                    if (this.mt != -1L && mt - this.mu >= this.ms) {
                        do.lA.b("request %d timed out", this.mt);
                        mt = this.mt;
                        final dn mv = this.mv;
                        this.be();
                        // monitorexit(do.mw)
                        if (mv != null) {
                            mv.a(mt, n, null);
                        }
                        return b;
                    }
                }
                b = false;
                final dn mv = null;
                mt = n2;
                continue;
            }
        }
    }
    
    public boolean i(final long n) {
        while (true) {
            synchronized (do.mw) {
                if (this.mt != -1L && this.mt == n) {
                    return true;
                }
            }
            return false;
        }
    }
}
