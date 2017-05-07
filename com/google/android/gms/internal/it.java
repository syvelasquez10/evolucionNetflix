// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONObject;
import android.os.SystemClock;

public final class it
{
    private static final ip Gr;
    public static final Object Hz;
    private long Hv;
    private long Hw;
    private long Hx;
    private is Hy;
    
    static {
        Gr = new ip("RequestTracker");
        Hz = new Object();
    }
    
    public it(final long hv) {
        this.Hv = hv;
        this.Hw = -1L;
        this.Hx = 0L;
    }
    
    private void fV() {
        this.Hw = -1L;
        this.Hy = null;
        this.Hx = 0L;
    }
    
    public void a(final long hw, final is hy) {
        synchronized (it.Hz) {
            final is hy2 = this.Hy;
            final long hw2 = this.Hw;
            this.Hw = hw;
            this.Hy = hy;
            this.Hx = SystemClock.elapsedRealtime();
            // monitorexit(it.Hz)
            if (hy2 != null) {
                hy2.n(hw2);
            }
        }
    }
    
    public boolean b(final long n, final int n2, final JSONObject jsonObject) {
        while (true) {
            boolean b = true;
            is hy = null;
            while (true) {
                synchronized (it.Hz) {
                    if (this.Hw != -1L && this.Hw == n) {
                        it.Gr.b("request %d completed", this.Hw);
                        hy = this.Hy;
                        this.fV();
                        // monitorexit(it.Hz)
                        if (hy != null) {
                            hy.a(n, n2, jsonObject);
                        }
                        return b;
                    }
                }
                b = false;
                continue;
            }
        }
    }
    
    public void clear() {
        synchronized (it.Hz) {
            if (this.Hw != -1L) {
                this.fV();
            }
        }
    }
    
    public boolean d(final long n, final int n2) {
        return this.b(n, n2, null);
    }
    
    public boolean e(long hw, final int n) {
        while (true) {
            boolean b = true;
            final long n2 = 0L;
            while (true) {
                synchronized (it.Hz) {
                    if (this.Hw != -1L && hw - this.Hx >= this.Hv) {
                        it.Gr.b("request %d timed out", this.Hw);
                        hw = this.Hw;
                        final is hy = this.Hy;
                        this.fV();
                        // monitorexit(it.Hz)
                        if (hy != null) {
                            hy.a(hw, n, null);
                        }
                        return b;
                    }
                }
                b = false;
                final is hy = null;
                hw = n2;
                continue;
            }
        }
    }
    
    public boolean fW() {
        while (true) {
            synchronized (it.Hz) {
                if (this.Hw != -1L) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean p(final long n) {
        while (true) {
            synchronized (it.Hz) {
                if (this.Hw != -1L && this.Hw == n) {
                    return true;
                }
            }
            return false;
        }
    }
}
