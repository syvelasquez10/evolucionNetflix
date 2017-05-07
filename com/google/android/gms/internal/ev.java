// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONObject;
import android.os.SystemClock;

public final class ev
{
    public static final Object Ab;
    private static final er zb;
    private eu Aa;
    private long zX;
    private long zY;
    private long zZ;
    
    static {
        zb = new er("RequestTracker");
        Ab = new Object();
    }
    
    public ev(final long zx) {
        this.zX = zx;
        this.zY = -1L;
        this.zZ = 0L;
    }
    
    private void dT() {
        this.zY = -1L;
        this.Aa = null;
        this.zZ = 0L;
    }
    
    public void a(final long zy, final eu aa) {
        synchronized (ev.Ab) {
            final eu aa2 = this.Aa;
            final long zy2 = this.zY;
            this.zY = zy;
            this.Aa = aa;
            this.zZ = SystemClock.elapsedRealtime();
            // monitorexit(ev.Ab)
            if (aa2 != null) {
                aa2.l(zy2);
            }
        }
    }
    
    public boolean b(final long n, final int n2, final JSONObject jsonObject) {
        while (true) {
            boolean b = true;
            eu aa = null;
            while (true) {
                synchronized (ev.Ab) {
                    if (this.zY != -1L && this.zY == n) {
                        ev.zb.b("request %d completed", this.zY);
                        aa = this.Aa;
                        this.dT();
                        // monitorexit(ev.Ab)
                        if (aa != null) {
                            aa.a(n, n2, jsonObject);
                        }
                        return b;
                    }
                }
                b = false;
                continue;
            }
        }
    }
    
    public boolean c(final long n, final int n2) {
        return this.b(n, n2, null);
    }
    
    public void clear() {
        synchronized (ev.Ab) {
            if (this.zY != -1L) {
                this.dT();
            }
        }
    }
    
    public boolean d(long zy, final int n) {
        while (true) {
            boolean b = true;
            final long n2 = 0L;
            while (true) {
                synchronized (ev.Ab) {
                    if (this.zY != -1L && zy - this.zZ >= this.zX) {
                        ev.zb.b("request %d timed out", this.zY);
                        zy = this.zY;
                        final eu aa = this.Aa;
                        this.dT();
                        // monitorexit(ev.Ab)
                        if (aa != null) {
                            aa.a(zy, n, null);
                        }
                        return b;
                    }
                }
                b = false;
                final eu aa = null;
                zy = n2;
                continue;
            }
        }
    }
    
    public boolean dU() {
        while (true) {
            synchronized (ev.Ab) {
                if (this.zY != -1L) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean n(final long n) {
        while (true) {
            synchronized (ev.Ab) {
                if (this.zY != -1L && this.zY == n) {
                    return true;
                }
            }
            return false;
        }
    }
}
