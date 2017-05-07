// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Releasable;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.it;
import com.google.android.gms.internal.gn;
import android.content.Context;
import com.google.android.gms.internal.c;
import com.google.android.gms.internal.gl;
import android.os.Looper;
import com.google.android.gms.common.api.a;

class o extends com.google.android.gms.common.api.a.a<ContainerHolder>
{
    private final Looper AS;
    private final String WJ;
    private long WO;
    private final TagManager WW;
    private final d WZ;
    private final gl Wv;
    private final cf Xa;
    private final int Xb;
    private f Xc;
    private volatile n Xd;
    private volatile boolean Xe;
    private com.google.android.gms.internal.c.j Xf;
    private String Xg;
    private e Xh;
    private a Xi;
    private final Context mContext;
    
    o(final Context mContext, final TagManager ww, final Looper looper, final String wj, final int xb, final f xc, final e xh, final gl wv, final cf xa) {
        Looper mainLooper;
        if (looper == null) {
            mainLooper = Looper.getMainLooper();
        }
        else {
            mainLooper = looper;
        }
        super(mainLooper);
        this.mContext = mContext;
        this.WW = ww;
        Looper mainLooper2 = looper;
        if (looper == null) {
            mainLooper2 = Looper.getMainLooper();
        }
        this.AS = mainLooper2;
        this.WJ = wj;
        this.Xb = xb;
        this.Xc = xc;
        this.Xh = xh;
        this.WZ = new d();
        this.Xf = new com.google.android.gms.internal.c.j();
        this.Wv = wv;
        this.Xa = xa;
        if (this.kk()) {
            this.br(cd.kT().kV());
        }
    }
    
    public o(final Context context, final TagManager tagManager, final Looper looper, final String s, final int n, final r r) {
        this(context, tagManager, looper, s, n, (f)new cp(context, s), (e)new co(context, s, r), gn.ft(), new bf(30, 900000L, 5000L, "refreshing", gn.ft()));
    }
    
    private void C(final boolean b) {
        this.Xc.a(new b());
        this.Xh.a(new c());
        final cq.c ca = this.Xc.ca(this.Xb);
        if (ca != null) {
            this.Xd = new n(this.WW, this.AS, new Container(this.mContext, this.WW.getDataLayer(), this.WJ, 0L, ca), (n.a)this.WZ);
        }
        this.Xi = (a)new a() {
            @Override
            public boolean b(final Container container) {
                if (b) {
                    if (container.getLastRefreshTime() + 43200000L < o.this.Wv.currentTimeMillis()) {
                        return false;
                    }
                }
                else if (container.isDefault()) {
                    return false;
                }
                return true;
            }
        };
        if (this.kk()) {
            this.Xh.d(0L, "");
            return;
        }
        this.Xc.km();
    }
    
    private void a(final com.google.android.gms.internal.c.j aaZ) {
        synchronized (this) {
            if (this.Xc != null) {
                final it.a a = new it.a();
                a.aaY = this.WO;
                a.fK = new com.google.android.gms.internal.c.f();
                a.aaZ = aaZ;
                this.Xc.b(a);
            }
        }
    }
    
    private void a(final com.google.android.gms.internal.c.j xf, final long wo, final boolean b) {
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
                            if (!this.Xe) {
                                if (!((com.google.android.gms.common.api.a.a)this).isReady() || this.Xd == null) {}
                                this.Xf = xf;
                                this.WO = wo;
                                this.t(Math.max(0L, Math.min(43200000L, this.WO + 43200000L - this.Wv.currentTimeMillis())));
                                container = new Container(this.mContext, this.WW.getDataLayer(), this.WJ, wo, xf);
                                if (this.Xd != null) {
                                    break Label_0169;
                                }
                                this.Xd = new n(this.WW, this.AS, container, (n.a)this.WZ);
                                if (!((com.google.android.gms.common.api.a.a)this).isReady() && this.Xi.b(container)) {
                                    ((com.google.android.gms.common.api.a.a<n>)this).a(this.Xd);
                                }
                            }
                            return;
                        }
                        finally {
                        }
                        // monitorexit(this)
                    }
                    this.Xd.a(container);
                    continue;
                }
            }
        }
    }
    
    private boolean kk() {
        final cd kt = cd.kT();
        return (kt.kU() == cd.a.YU || kt.kU() == cd.a.YV) && this.WJ.equals(kt.getContainerId());
    }
    
    private void t(final long n) {
        synchronized (this) {
            if (this.Xh == null) {
                bh.z("Refresh requested, but no network load scheduler.");
            }
            else {
                this.Xh.d(n, this.Xf.fL);
            }
        }
    }
    
    protected ContainerHolder ac(final Status status) {
        if (this.Xd != null) {
            return this.Xd;
        }
        if (status == Status.By) {
            bh.w("timer expired: setting result to failure");
        }
        return new n(status);
    }
    
    void br(final String xg) {
        synchronized (this) {
            this.Xg = xg;
            if (this.Xh != null) {
                this.Xh.bu(xg);
            }
        }
    }
    
    String ke() {
        synchronized (this) {
            return this.Xg;
        }
    }
    
    public void kh() {
        final cq.c ca = this.Xc.ca(this.Xb);
        if (ca != null) {
            ((com.google.android.gms.common.api.a.a<n>)this).a(new n(this.WW, this.AS, new Container(this.mContext, this.WW.getDataLayer(), this.WJ, 0L, ca), (n.a)new n.a() {
                @Override
                public void br(final String s) {
                    o.this.br(s);
                }
                
                @Override
                public String ke() {
                    return o.this.ke();
                }
                
                @Override
                public void kg() {
                    bh.z("Refresh ignored: container loaded as default only.");
                }
            }));
        }
        else {
            bh.w("Default was requested, but no default container was found");
            ((com.google.android.gms.common.api.a.a<ContainerHolder>)this).a(this.ac(new Status(10, "Default was requested, but no default container was found", null)));
        }
        this.Xh = null;
        this.Xc = null;
    }
    
    public void ki() {
        this.C(false);
    }
    
    public void kj() {
        this.C(true);
    }
    
    interface a
    {
        boolean b(final Container p0);
    }
    
    private class b implements bg<it.a>
    {
        public void a(final it.a a) {
            com.google.android.gms.internal.c.j aaZ;
            if (a.aaZ != null) {
                aaZ = a.aaZ;
            }
            else {
                final com.google.android.gms.internal.c.f fk = a.fK;
                aaZ = new com.google.android.gms.internal.c.j();
                aaZ.fK = fk;
                aaZ.fJ = null;
                aaZ.fL = fk.fg;
            }
            o.this.a(aaZ, a.aaY, true);
        }
        
        @Override
        public void a(final bg.a a) {
            if (!o.this.Xe) {
                o.this.t(0L);
            }
        }
        
        @Override
        public void kl() {
        }
    }
    
    private class c implements bg<com.google.android.gms.internal.c.j>
    {
        @Override
        public void a(final bg.a a) {
            if (o.this.Xd != null) {
                ((a.a<n>)o.this).a(o.this.Xd);
            }
            else {
                ((a.a<ContainerHolder>)o.this).a(o.this.ac(Status.By));
            }
            o.this.t(3600000L);
        }
        
        public void b(final com.google.android.gms.internal.c.j j) {
            synchronized (o.this) {
                if (j.fK == null) {
                    if (o.this.Xf.fK == null) {
                        bh.w("Current resource is null; network resource is also null");
                        o.this.t(3600000L);
                        return;
                    }
                    j.fK = o.this.Xf.fK;
                }
                o.this.a(j, o.this.Wv.currentTimeMillis(), false);
                bh.y("setting refresh time to current time: " + o.this.WO);
                if (!o.this.kk()) {
                    o.this.a(j);
                }
            }
        }
        
        @Override
        public void kl() {
        }
    }
    
    private class d implements n.a
    {
        @Override
        public void br(final String s) {
            o.this.br(s);
        }
        
        @Override
        public String ke() {
            return o.this.ke();
        }
        
        @Override
        public void kg() {
            if (o.this.Xa.cS()) {
                o.this.t(0L);
            }
        }
    }
    
    interface e extends Releasable
    {
        void a(final bg<com.google.android.gms.internal.c.j> p0);
        
        void bu(final String p0);
        
        void d(final long p0, final String p1);
    }
    
    interface f extends Releasable
    {
        void a(final bg<it.a> p0);
        
        void b(final it.a p0);
        
        cq.c ca(final int p0);
        
        void km();
    }
}
