// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Releasable;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ok;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import android.content.Context;
import com.google.android.gms.internal.c;
import android.os.Looper;
import com.google.android.gms.common.api.BaseImplementation;

class o extends AbstractPendingResult<ContainerHolder>
{
    private final Looper IB;
    private final String anR;
    private long anW;
    private final TagManager aod;
    private final d aog;
    private final cg aoh;
    private final int aoi;
    private f aoj;
    private volatile n aok;
    private volatile boolean aol;
    private com.google.android.gms.internal.c.j aom;
    private String aon;
    private e aoo;
    private a aop;
    private final Context mContext;
    private final ju yD;
    
    o(final Context mContext, final TagManager aod, final Looper looper, final String anR, final int aoi, final f aoj, final e aoo, final ju yd, final cg aoh) {
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
        this.aog = new d();
        this.aom = new com.google.android.gms.internal.c.j();
        this.yD = yd;
        this.aoh = aoh;
        if (this.nY()) {
            this.co(ce.oH().oJ());
        }
    }
    
    public o(final Context context, final TagManager tagManager, final Looper looper, final String s, final int n, final r r) {
        this(context, tagManager, looper, s, n, (f)new cq(context, s), (e)new cp(context, s, r), jw.hA(), new bf(30, 900000L, 5000L, "refreshing", jw.hA()));
    }
    
    private void T(final boolean b) {
        this.aoj.a(new b());
        this.aoo.a(new c());
        final cr.c fe = this.aoj.fe(this.aoi);
        if (fe != null) {
            this.aok = new n(this.aod, this.IB, new Container(this.mContext, this.aod.getDataLayer(), this.anR, 0L, fe), (n.a)this.aog);
        }
        this.aop = (a)new a() {
            @Override
            public boolean b(final Container container) {
                if (b) {
                    if (container.getLastRefreshTime() + 43200000L < o.this.yD.currentTimeMillis()) {
                        return false;
                    }
                }
                else if (container.isDefault()) {
                    return false;
                }
                return true;
            }
        };
        if (this.nY()) {
            this.aoo.e(0L, "");
            return;
        }
        this.aoj.oa();
    }
    
    private void a(final com.google.android.gms.internal.c.j ash) {
        synchronized (this) {
            if (this.aoj != null) {
                final ok.a a = new ok.a();
                a.asg = this.anW;
                a.gs = new com.google.android.gms.internal.c.f();
                a.ash = ash;
                this.aoj.b(a);
            }
        }
    }
    
    private void a(final com.google.android.gms.internal.c.j aom, final long anW, final boolean b) {
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
                                if (!((BaseImplementation.AbstractPendingResult)this).isReady() || this.aok == null) {}
                                this.aom = aom;
                                this.anW = anW;
                                this.w(Math.max(0L, Math.min(43200000L, this.anW + 43200000L - this.yD.currentTimeMillis())));
                                container = new Container(this.mContext, this.aod.getDataLayer(), this.anR, anW, aom);
                                if (this.aok != null) {
                                    break Label_0169;
                                }
                                this.aok = new n(this.aod, this.IB, container, (n.a)this.aog);
                                if (!((BaseImplementation.AbstractPendingResult)this).isReady() && this.aop.b(container)) {
                                    ((BaseImplementation.AbstractPendingResult<n>)this).b(this.aok);
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
        return (oh.oI() == ce.a.apX || oh.oI() == ce.a.apY) && this.anR.equals(oh.getContainerId());
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
        final cr.c fe = this.aoj.fe(this.aoi);
        if (fe != null) {
            ((BaseImplementation.AbstractPendingResult<n>)this).b(new n(this.aod, this.IB, new Container(this.mContext, this.aod.getDataLayer(), this.anR, 0L, fe), (n.a)new n.a() {
                @Override
                public void co(final String s) {
                    o.this.co(s);
                }
                
                @Override
                public String nS() {
                    return o.this.nS();
                }
                
                @Override
                public void nU() {
                    bh.W("Refresh ignored: container loaded as default only.");
                }
            }));
        }
        else {
            bh.T("Default was requested, but no default container was found");
            ((BaseImplementation.AbstractPendingResult<ContainerHolder>)this).b(this.aE(new Status(10, "Default was requested, but no default container was found", null)));
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
    
    interface a
    {
        boolean b(final Container p0);
    }
    
    private class b implements bg<ok.a>
    {
        public void a(final ok.a a) {
            com.google.android.gms.internal.c.j ash;
            if (a.ash != null) {
                ash = a.ash;
            }
            else {
                final com.google.android.gms.internal.c.f gs = a.gs;
                ash = new com.google.android.gms.internal.c.j();
                ash.gs = gs;
                ash.gr = null;
                ash.gt = gs.version;
            }
            o.this.a(ash, a.asg, true);
        }
        
        @Override
        public void a(final bg.a a) {
            if (!o.this.aol) {
                o.this.w(0L);
            }
        }
        
        @Override
        public void nZ() {
        }
    }
    
    private class c implements bg<com.google.android.gms.internal.c.j>
    {
        @Override
        public void a(final bg.a a) {
            if (o.this.aok != null) {
                ((BaseImplementation.AbstractPendingResult<n>)o.this).b(o.this.aok);
            }
            else {
                ((BaseImplementation.AbstractPendingResult<ContainerHolder>)o.this).b(o.this.aE(Status.Jr));
            }
            o.this.w(3600000L);
        }
        
        public void b(final com.google.android.gms.internal.c.j j) {
            synchronized (o.this) {
                if (j.gs == null) {
                    if (o.this.aom.gs == null) {
                        bh.T("Current resource is null; network resource is also null");
                        o.this.w(3600000L);
                        return;
                    }
                    j.gs = o.this.aom.gs;
                }
                o.this.a(j, o.this.yD.currentTimeMillis(), false);
                bh.V("setting refresh time to current time: " + o.this.anW);
                if (!o.this.nY()) {
                    o.this.a(j);
                }
            }
        }
        
        @Override
        public void nZ() {
        }
    }
    
    private class d implements n.a
    {
        @Override
        public void co(final String s) {
            o.this.co(s);
        }
        
        @Override
        public String nS() {
            return o.this.nS();
        }
        
        @Override
        public void nU() {
            if (o.this.aoh.eK()) {
                o.this.w(0L);
            }
        }
    }
    
    interface e extends Releasable
    {
        void a(final bg<com.google.android.gms.internal.c.j> p0);
        
        void cr(final String p0);
        
        void e(final long p0, final String p1);
    }
    
    interface f extends Releasable
    {
        void a(final bg<ok.a> p0);
        
        void b(final ok.a p0);
        
        cr.c fe(final int p0);
        
        void oa();
    }
}
