// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.c$f;
import com.google.android.gms.internal.ok$a;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import android.content.Context;
import com.google.android.gms.internal.c$j;
import android.os.Looper;
import com.google.android.gms.common.api.BaseImplementation$AbstractPendingResult;

class o extends BaseImplementation$AbstractPendingResult<ContainerHolder>
{
    private final Looper IB;
    private final String anR;
    private long anW;
    private final TagManager aod;
    private final o$d aog;
    private final cg aoh;
    private final int aoi;
    private o$f aoj;
    private volatile n aok;
    private volatile boolean aol;
    private c$j aom;
    private String aon;
    private o$e aoo;
    private o$a aop;
    private final Context mContext;
    private final ju yD;
    
    o(final Context mContext, final TagManager aod, final Looper looper, final String anR, final int aoi, final o$f aoj, final o$e aoo, final ju yd, final cg aoh) {
        Looper mainLooper;
        if (looper == null) {
            mainLooper = Looper.getMainLooper();
        }
        else {
            mainLooper = looper;
        }
        super(mainLooper);
        this.mContext = mContext;
        this.aod = aod;
        Looper mainLooper2 = looper;
        if (looper == null) {
            mainLooper2 = Looper.getMainLooper();
        }
        this.IB = mainLooper2;
        this.anR = anR;
        this.aoi = aoi;
        this.aoj = aoj;
        this.aoo = aoo;
        this.aog = new o$d(this, null);
        this.aom = new c$j();
        this.yD = yd;
        this.aoh = aoh;
        if (this.nY()) {
            this.co(ce.oH().oJ());
        }
    }
    
    public o(final Context context, final TagManager tagManager, final Looper looper, final String s, final int n, final r r) {
        this(context, tagManager, looper, s, n, new cq(context, s), new cp(context, s, r), jw.hA(), new bf(30, 900000L, 5000L, "refreshing", jw.hA()));
    }
    
    private void T(final boolean b) {
        this.aoj.a(new o$b(this, null));
        this.aoo.a(new o$c(this, null));
        final cr$c fe = this.aoj.fe(this.aoi);
        if (fe != null) {
            this.aok = new n(this.aod, this.IB, new Container(this.mContext, this.aod.getDataLayer(), this.anR, 0L, fe), this.aog);
        }
        this.aop = new o$2(this, b);
        if (this.nY()) {
            this.aoo.e(0L, "");
            return;
        }
        this.aoj.oa();
    }
    
    private void a(final c$j ash) {
        synchronized (this) {
            if (this.aoj != null) {
                final ok$a ok$a = new ok$a();
                ok$a.asg = this.anW;
                ok$a.gs = new c$f();
                ok$a.ash = ash;
                this.aoj.b(ok$a);
            }
        }
    }
    
    private void a(final c$j aom, final long anW, final boolean b) {
        // monitorenter(this)
        Label_0021: {
            if (!b) {
                break Label_0021;
            }
            while (true) {
                while (true) {
                    Container container = null;
                    Label_0169: {
                        try {
                            if (!this.aol) {
                                if (!this.isReady() || this.aok == null) {}
                                this.aom = aom;
                                this.anW = anW;
                                this.w(Math.max(0L, Math.min(43200000L, this.anW + 43200000L - this.yD.currentTimeMillis())));
                                container = new Container(this.mContext, this.aod.getDataLayer(), this.anR, anW, aom);
                                if (this.aok != null) {
                                    break Label_0169;
                                }
                                this.aok = new n(this.aod, this.IB, container, this.aog);
                                if (!this.isReady() && this.aop.b(container)) {
                                    ((BaseImplementation$AbstractPendingResult<n>)this).b(this.aok);
                                }
                            }
                            return;
                        }
                        finally {
                        }
                        // monitorexit(this)
                    }
                    this.aok.a(container);
                    continue;
                }
            }
        }
    }
    
    private boolean nY() {
        final ce oh = ce.oH();
        return (oh.oI() == ce$a.apX || oh.oI() == ce$a.apY) && this.anR.equals(oh.getContainerId());
    }
    
    private void w(final long n) {
        synchronized (this) {
            if (this.aoo == null) {
                bh.W("Refresh requested, but no network load scheduler.");
            }
            else {
                this.aoo.e(n, this.aom.gt);
            }
        }
    }
    
    protected ContainerHolder aE(final Status status) {
        if (this.aok != null) {
            return this.aok;
        }
        if (status == Status.Jr) {
            bh.T("timer expired: setting result to failure");
        }
        return new n(status);
    }
    
    void co(final String aon) {
        synchronized (this) {
            this.aon = aon;
            if (this.aoo != null) {
                this.aoo.cr(aon);
            }
        }
    }
    
    String nS() {
        synchronized (this) {
            return this.aon;
        }
    }
    
    public void nV() {
        final cr$c fe = this.aoj.fe(this.aoi);
        if (fe != null) {
            ((BaseImplementation$AbstractPendingResult<n>)this).b(new n(this.aod, this.IB, new Container(this.mContext, this.aod.getDataLayer(), this.anR, 0L, fe), new o$1(this)));
        }
        else {
            bh.T("Default was requested, but no default container was found");
            this.b(this.aE(new Status(10, "Default was requested, but no default container was found", null)));
        }
        this.aoo = null;
        this.aoj = null;
    }
    
    public void nW() {
        this.T(false);
    }
    
    public void nX() {
        this.T(true);
    }
}
