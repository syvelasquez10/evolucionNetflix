// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import java.util.List;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.os.SystemClock;
import android.content.Context;

@ez
public class fe extends gg implements gw$a
{
    private final ct lq;
    private final Context mContext;
    private final gv md;
    private final Object mw;
    private cm pR;
    private final Object sV;
    private fk sZ;
    private final fd$a tm;
    private final fz$a tn;
    private boolean to;
    private ck tp;
    private cq tq;
    
    public fe(final Context mContext, final fz$a tn, final gv md, final ct lq, final fd$a tm) {
        this.sV = new Object();
        this.mw = new Object();
        this.to = false;
        this.mContext = mContext;
        this.tn = tn;
        this.sZ = tn.vw;
        this.md = md;
        this.lq = lq;
        this.tm = tm;
        this.pR = tn.vq;
    }
    
    private void a(final fi fi, final long n) {
        synchronized (this.sV) {
            this.tp = new ck(this.mContext, fi, this.lq, this.pR);
            // monitorexit(this.sV)
            this.tq = this.tp.a(n, 60000L);
            switch (this.tq.qx) {
                default: {
                    throw new fe$a("Unexpected mediation result: " + this.tq.qx, 0);
                }
                case 1: {
                    break;
                }
                case 0: {
                    return;
                }
            }
        }
        throw new fe$a("No fill from any mediation ad networks.", 3);
    }
    
    private boolean c(long n) {
        n = 60000L - (SystemClock.elapsedRealtime() - n);
        if (n <= 0L) {
            return false;
        }
        try {
            this.mw.wait(n);
            return true;
        }
        catch (InterruptedException ex) {
            throw new fe$a("Ad request cancelled.", -1);
        }
    }
    
    private void f(final long n) {
        gr.wC.post((Runnable)new fe$3(this));
        this.h(n);
    }
    
    private void h(final long n) {
        while (this.c(n)) {
            if (this.to) {
                return;
            }
        }
        throw new fe$a("Timed out waiting for WebView to finish loading.", 2);
    }
    
    @Override
    public void a(final gv gv) {
        synchronized (this.mw) {
            gs.S("WebView finished loading.");
            this.to = true;
            this.mw.notify();
        }
    }
    
    @Override
    public void cp() {
        Object o;
        int n = 0;
        long n2 = 0L;
        av tx;
        gv md;
        List<String> qf;
        List<String> qg;
        List<String> tk;
        int orientation;
        String ta;
        boolean ti;
        cu qz = null;
        String s;
        cm pr;
        co qb;
        Label_0140_Outer:Label_0156_Outer:
        while (true) {
            while (true) {
                Label_0454:Label_0194_Outer:
                while (true) {
                    Label_0448: {
                        while (true) {
                            Label_0442:Label_0364_Outer:
                            while (true) {
                                Label_0431: {
                                    while (true) {
                                    Label_0409:
                                        while (true) {
                                        Label_0398:
                                            while (true) {
                                                Label_0390: {
                                                    synchronized (this.mw) {
                                                        gs.S("AdRendererBackgroundTask started.");
                                                        o = this.tn.vv;
                                                        n = this.tn.errorCode;
                                                        try {
                                                            n2 = SystemClock.elapsedRealtime();
                                                            if (this.sZ.tI) {
                                                                this.a((fi)o, n2);
                                                            }
                                                            else {
                                                                if (!this.sZ.tO) {
                                                                    break Label_0390;
                                                                }
                                                                this.g(n2);
                                                            }
                                                            tx = ((fi)o).tx;
                                                            md = this.md;
                                                            qf = this.sZ.qf;
                                                            qg = this.sZ.qg;
                                                            tk = this.sZ.tK;
                                                            orientation = this.sZ.orientation;
                                                            n2 = this.sZ.qj;
                                                            ta = ((fi)o).tA;
                                                            ti = this.sZ.tI;
                                                            if (this.tq == null) {
                                                                break Label_0448;
                                                            }
                                                            o = this.tq.qy;
                                                            if (this.tq == null) {
                                                                break Label_0454;
                                                            }
                                                            qz = this.tq.qz;
                                                            if (this.tq == null) {
                                                                break Label_0431;
                                                            }
                                                            s = this.tq.qA;
                                                            pr = this.pR;
                                                            if (this.tq != null) {
                                                                qb = this.tq.qB;
                                                                o = new fz(tx, md, qf, n, qg, tk, orientation, n2, ta, ti, (cl)o, qz, s, pr, qb, this.sZ.tJ, this.tn.lH, this.sZ.tH, this.tn.vs, this.sZ.tM, this.sZ.tN, this.tn.vp, null);
                                                                gr.wC.post((Runnable)new fe$2(this, (fz)o));
                                                                return;
                                                            }
                                                            break Label_0442;
                                                        }
                                                        catch (fe$a qz) {
                                                            n = ((fe$a)qz).getErrorCode();
                                                            if (n != 3 && n != -1) {
                                                                break Label_0398;
                                                            }
                                                            gs.U(((Throwable)qz).getMessage());
                                                            if (this.sZ == null) {
                                                                this.sZ = new fk(n);
                                                                gr.wC.post((Runnable)new fe$1(this));
                                                                continue Label_0140_Outer;
                                                            }
                                                            break Label_0409;
                                                        }
                                                        continue Label_0140_Outer;
                                                    }
                                                }
                                                this.f(n2);
                                                continue Label_0140_Outer;
                                            }
                                            gs.W(((Throwable)qz).getMessage());
                                            continue Label_0364_Outer;
                                        }
                                        this.sZ = new fk(n, this.sZ.qj);
                                        continue;
                                    }
                                }
                                s = AdMobAdapter.class.getName();
                                continue Label_0194_Outer;
                            }
                            qb = null;
                            continue;
                        }
                    }
                    o = null;
                    continue Label_0156_Outer;
                }
                qz = null;
                continue;
            }
        }
    }
    
    protected void g(final long n) {
        final ay y = this.md.Y();
        int n2;
        int n3;
        if (y.og) {
            n2 = this.mContext.getResources().getDisplayMetrics().widthPixels;
            n3 = this.mContext.getResources().getDisplayMetrics().heightPixels;
        }
        else {
            n2 = y.widthPixels;
            n3 = y.heightPixels;
        }
        final fc fc = new fc(this, this.md, n2, n3);
        gr.wC.post((Runnable)new fe$4(this, fc));
        this.h(n);
        if (fc.cB()) {
            gs.S("Ad-Network indicated no fill with passback URL.");
            throw new fe$a("AdNetwork sent passback url", 3);
        }
        if (!fc.cC()) {
            throw new fe$a("AdNetwork timed out", 2);
        }
    }
    
    @Override
    public void onStop() {
        synchronized (this.sV) {
            this.md.stopLoading();
            gj.a(this.md);
            if (this.tp != null) {
                this.tp.cancel();
            }
        }
    }
}
