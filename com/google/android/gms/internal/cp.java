// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import android.os.SystemClock;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import android.content.Context;

@ez
public final class cp implements cq$a
{
    private final ct lq;
    private final Context mContext;
    private final av ml;
    private final Object mw;
    private final String qo;
    private final long qp;
    private final cl qq;
    private final ay qr;
    private final gt qs;
    private cu qt;
    private int qu;
    
    public cp(final Context mContext, final String qo, final ct lq, final cm cm, final cl qq, final av ml, final ay qr, final gt qs) {
        this.mw = new Object();
        this.qu = -2;
        this.mContext = mContext;
        this.lq = lq;
        this.qq = qq;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(qo)) {
            this.qo = this.bE();
        }
        else {
            this.qo = qo;
        }
        long qe;
        if (cm.qe != -1L) {
            qe = cm.qe;
        }
        else {
            qe = 10000L;
        }
        this.qp = qe;
        this.ml = ml;
        this.qr = qr;
        this.qs = qs;
    }
    
    private void a(final long n, final long n2, final long n3, final long n4) {
        while (this.qu == -2) {
            this.b(n, n2, n3, n4);
        }
    }
    
    private void a(final co co) {
        try {
            if (this.qs.wF < 4100000) {
                if (this.qr.og) {
                    this.qt.a(e.k(this.mContext), this.ml, this.qq.qc, co);
                    return;
                }
                this.qt.a(e.k(this.mContext), this.qr, this.ml, this.qq.qc, co);
                return;
            }
        }
        catch (RemoteException ex) {
            gs.d("Could not request ad from mediation adapter.", (Throwable)ex);
            this.j(5);
            return;
        }
        if (this.qr.og) {
            this.qt.a(e.k(this.mContext), this.ml, this.qq.qc, this.qq.pW, co);
            return;
        }
        this.qt.a(e.k(this.mContext), this.qr, this.ml, this.qq.qc, this.qq.pW, co);
    }
    
    private void b(long n, long n2, final long n3, final long n4) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        n = n2 - (elapsedRealtime - n);
        n2 = n4 - (elapsedRealtime - n3);
        if (n <= 0L || n2 <= 0L) {
            gs.U("Timed out waiting for adapter.");
            this.qu = 3;
            return;
        }
        try {
            this.mw.wait(Math.min(n, n2));
        }
        catch (InterruptedException ex) {
            this.qu = -1;
        }
    }
    
    private String bE() {
        try {
            if (!TextUtils.isEmpty((CharSequence)this.qq.qa)) {
                if (this.lq.y(this.qq.qa)) {
                    return "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
                }
                return "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        }
        catch (RemoteException ex) {
            gs.W("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }
    
    private cu bF() {
        gs.U("Instantiating mediation adapter: " + this.qo);
        try {
            return this.lq.x(this.qo);
        }
        catch (RemoteException ex) {
            gs.a("Could not instantiate mediation adapter: " + this.qo, (Throwable)ex);
            return null;
        }
    }
    
    public cq b(final long n, final long n2) {
        synchronized (this.mw) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final co co = new co();
            gr.wC.post((Runnable)new cp$1(this, co));
            this.a(elapsedRealtime, this.qp, n, n2);
            return new cq(this.qq, this.qt, this.qo, co, this.qu);
        }
    }
    
    public void cancel() {
        synchronized (this.mw) {
            while (true) {
                try {
                    if (this.qt != null) {
                        this.qt.destroy();
                    }
                    this.qu = -1;
                    this.mw.notify();
                }
                catch (RemoteException ex) {
                    gs.d("Could not destroy mediation adapter.", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public void j(final int qu) {
        synchronized (this.mw) {
            this.qu = qu;
            this.mw.notify();
        }
    }
}
