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
public class fe extends gg implements gw.a
{
    private final ct lq;
    private final Context mContext;
    private final gv md;
    private final Object mw;
    private cm pR;
    private final Object sV;
    private fk sZ;
    private final fd.a tm;
    private final fz.a tn;
    private boolean to;
    private ck tp;
    private cq tq;
    
    public fe(final Context mContext, final fz.a tn, final gv md, final ct lq, final fd.a tm) {
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
    
    private void a(final fi fi, final long n) throws a {
        synchronized (this.sV) {
            this.tp = new ck(this.mContext, fi, this.lq, this.pR);
            // monitorexit(this.sV)
            this.tq = this.tp.a(n, 60000L);
            switch (this.tq.qx) {
                default: {
                    throw new a("Unexpected mediation result: " + this.tq.qx, 0);
                }
                case 1: {
                    break;
                }
                case 0: {
                    return;
                }
            }
        }
        throw new a("No fill from any mediation ad networks.", 3);
    }
    
    private boolean c(long n) throws a {
        n = 60000L - (SystemClock.elapsedRealtime() - n);
        if (n <= 0L) {
            return false;
        }
        try {
            this.mw.wait(n);
            return true;
        }
        catch (InterruptedException ex) {
            throw new a("Ad request cancelled.", -1);
        }
    }
    
    private void f(final long n) throws a {
        gr.wC.post((Runnable)new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (fe.this.mw) {
                        if (fe.this.sZ.errorCode != -2) {
                            return;
                        }
                        fe.this.md.dv().a((gw.a)fe.this);
                        if (fe.this.sZ.errorCode == -3) {
                            gs.V("Loading URL in WebView: " + fe.this.sZ.rP);
                            fe.this.md.loadUrl(fe.this.sZ.rP);
                            return;
                        }
                    }
                    gs.V("Loading HTML in WebView.");
                    fe.this.md.loadDataWithBaseURL(gj.L(fe.this.sZ.rP), fe.this.sZ.tG, "text/html", "UTF-8", (String)null);
                }
            }
        });
        this.h(n);
    }
    
    private void h(final long n) throws a {
        while (this.c(n)) {
            if (this.to) {
                return;
            }
        }
        throw new a("Timed out waiting for WebView to finish loading.", 2);
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
                                                                gr.wC.post((Runnable)new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        synchronized (fe.this.mw) {
                                                                            fe.this.tm.a(o);
                                                                        }
                                                                    }
                                                                });
                                                                return;
                                                            }
                                                            break Label_0442;
                                                        }
                                                        catch (a qz) {
                                                            n = ((a)qz).getErrorCode();
                                                            if (n != 3 && n != -1) {
                                                                break Label_0398;
                                                            }
                                                            gs.U(((Throwable)qz).getMessage());
                                                            if (this.sZ == null) {
                                                                this.sZ = new fk(n);
                                                                gr.wC.post((Runnable)new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        fe.this.onStop();
                                                                    }
                                                                });
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
    
    protected void g(final long n) throws a {
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
        gr.wC.post((Runnable)new Runnable() {
            @Override
            public void run() {
                synchronized (fe.this.mw) {
                    if (fe.this.sZ.errorCode != -2) {
                        return;
                    }
                    fe.this.md.dv().a((gw.a)fe.this);
                    fc.b(fe.this.sZ);
                }
            }
        });
        this.h(n);
        if (fc.cB()) {
            gs.S("Ad-Network indicated no fill with passback URL.");
            throw new a("AdNetwork sent passback url", 3);
        }
        if (!fc.cC()) {
            throw new a("AdNetwork timed out", 2);
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
    
    private static final class a extends Exception
    {
        private final int tc;
        
        public a(final String s, final int tc) {
            super(s);
            this.tc = tc;
        }
        
        public int getErrorCode() {
            return this.tc;
        }
    }
}
