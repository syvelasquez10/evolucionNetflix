// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.SystemClock;
import com.google.android.gms.dynamic.c;
import android.os.RemoteException;
import android.content.Context;

public final class ax implements a
{
    private final bb ed;
    private final v eq;
    private final String fR;
    private final long fS;
    private final at fT;
    private final x fU;
    private final cu fV;
    private bc fW;
    private int fX;
    private final Object fx;
    private final Context mContext;
    
    public ax(final Context mContext, final String fr, final bb ed, final au au, final at ft, final v eq, final x fu, final cu fv) {
        this.fx = new Object();
        this.fX = -2;
        this.mContext = mContext;
        this.fR = fr;
        this.ed = ed;
        long fj;
        if (au.fJ != -1L) {
            fj = au.fJ;
        }
        else {
            fj = 10000L;
        }
        this.fS = fj;
        this.fT = ft;
        this.eq = eq;
        this.fU = fu;
        this.fV = fv;
    }
    
    private bc V() {
        ct.t("Instantiating mediation adapter: " + this.fR);
        try {
            return this.ed.l(this.fR);
        }
        catch (RemoteException ex) {
            ct.a("Could not instantiate mediation adapter: " + this.fR, (Throwable)ex);
            return null;
        }
    }
    
    private void a(final long n, final long n2, final long n3, final long n4) {
        while (this.fX == -2) {
            this.b(n, n2, n3, n4);
        }
    }
    
    private void a(final aw aw) {
        try {
            if (this.fV.iL < 4100000) {
                if (this.fU.eG) {
                    this.fW.a(c.h(this.mContext), this.eq, this.fT.fH, aw);
                    return;
                }
                this.fW.a(c.h(this.mContext), this.fU, this.eq, this.fT.fH, aw);
                return;
            }
        }
        catch (RemoteException ex) {
            ct.b("Could not request ad from mediation adapter.", (Throwable)ex);
            this.f(5);
            return;
        }
        if (this.fU.eG) {
            this.fW.a(c.h(this.mContext), this.eq, this.fT.fH, this.fT.adJson, aw);
            return;
        }
        this.fW.a(c.h(this.mContext), this.fU, this.eq, this.fT.fH, this.fT.adJson, aw);
    }
    
    private void b(long n, long n2, final long n3, final long n4) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        n = n2 - (elapsedRealtime - n);
        n2 = n4 - (elapsedRealtime - n3);
        if (n <= 0L || n2 <= 0L) {
            ct.t("Timed out waiting for adapter.");
            this.fX = 3;
            return;
        }
        try {
            this.fx.wait(Math.min(n, n2));
        }
        catch (InterruptedException ex) {
            this.fX = -1;
        }
    }
    
    public ay b(final long n, final long n2) {
        synchronized (this.fx) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final aw aw = new aw();
            cs.iI.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    synchronized (ax.this.fx) {
                        if (ax.this.fX != -2) {
                            return;
                        }
                        ax.this.fW = ax.this.V();
                        if (ax.this.fW == null) {
                            ax.this.f(4);
                            return;
                        }
                    }
                    aw.a(ax.this);
                    ax.this.a(aw);
                }
                // monitorexit(o)
            });
            this.a(elapsedRealtime, this.fS, n, n2);
            return new ay(this.fT, this.fW, this.fR, aw, this.fX);
        }
    }
    
    public void cancel() {
        synchronized (this.fx) {
            while (true) {
                try {
                    if (this.fW != null) {
                        this.fW.destroy();
                    }
                    this.fX = -1;
                    this.fx.notify();
                }
                catch (RemoteException ex) {
                    ct.b("Could not destroy mediation adapter.", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public void f(final int fx) {
        synchronized (this.fx) {
            this.fX = fx;
            this.fx.notify();
        }
    }
}
