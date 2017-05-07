// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.SystemClock;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import android.content.Context;

public final class bm implements a
{
    private final ah kX;
    private final bq ky;
    private final Object li;
    private final Context mContext;
    private final String nn;
    private final long no;
    private final bi np;
    private final ak nq;
    private final dx nr;
    private br ns;
    private int nt;
    
    public bm(final Context mContext, final String nn, final bq ky, final bj bj, final bi np, final ah kx, final ak nq, final dx nr) {
        this.li = new Object();
        this.nt = -2;
        this.mContext = mContext;
        this.nn = nn;
        this.ky = ky;
        long nd;
        if (bj.nd != -1L) {
            nd = bj.nd;
        }
        else {
            nd = 10000L;
        }
        this.no = nd;
        this.np = np;
        this.kX = kx;
        this.nq = nq;
        this.nr = nr;
    }
    
    private void a(final long n, final long n2, final long n3, final long n4) {
        while (this.nt == -2) {
            this.b(n, n2, n3, n4);
        }
    }
    
    private void a(final bl bl) {
        try {
            if (this.nr.rs < 4100000) {
                if (this.nq.lT) {
                    this.ns.a(e.h(this.mContext), this.kX, this.np.nb, bl);
                    return;
                }
                this.ns.a(e.h(this.mContext), this.nq, this.kX, this.np.nb, bl);
                return;
            }
        }
        catch (RemoteException ex) {
            dw.c("Could not request ad from mediation adapter.", (Throwable)ex);
            this.f(5);
            return;
        }
        if (this.nq.lT) {
            this.ns.a(e.h(this.mContext), this.kX, this.np.nb, this.np.mW, bl);
            return;
        }
        this.ns.a(e.h(this.mContext), this.nq, this.kX, this.np.nb, this.np.mW, bl);
    }
    
    private br aJ() {
        dw.x("Instantiating mediation adapter: " + this.nn);
        try {
            return this.ky.m(this.nn);
        }
        catch (RemoteException ex) {
            dw.a("Could not instantiate mediation adapter: " + this.nn, (Throwable)ex);
            return null;
        }
    }
    
    private void b(long n, long n2, final long n3, final long n4) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        n = n2 - (elapsedRealtime - n);
        n2 = n4 - (elapsedRealtime - n3);
        if (n <= 0L || n2 <= 0L) {
            dw.x("Timed out waiting for adapter.");
            this.nt = 3;
            return;
        }
        try {
            this.li.wait(Math.min(n, n2));
        }
        catch (InterruptedException ex) {
            this.nt = -1;
        }
    }
    
    public bn b(final long n, final long n2) {
        synchronized (this.li) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final bl bl = new bl();
            dv.rp.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    synchronized (bm.this.li) {
                        if (bm.this.nt != -2) {
                            return;
                        }
                        bm.this.ns = bm.this.aJ();
                        if (bm.this.ns == null) {
                            bm.this.f(4);
                            return;
                        }
                    }
                    bl.a(bm.this);
                    bm.this.a(bl);
                }
                // monitorexit(o)
            });
            this.a(elapsedRealtime, this.no, n, n2);
            return new bn(this.np, this.ns, this.nn, bl, this.nt);
        }
    }
    
    public void cancel() {
        synchronized (this.li) {
            while (true) {
                try {
                    if (this.ns != null) {
                        this.ns.destroy();
                    }
                    this.nt = -1;
                    this.li.notify();
                }
                catch (RemoteException ex) {
                    dw.c("Could not destroy mediation adapter.", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public void f(final int nt) {
        synchronized (this.li) {
            this.nt = nt;
            this.li.notify();
        }
    }
}
