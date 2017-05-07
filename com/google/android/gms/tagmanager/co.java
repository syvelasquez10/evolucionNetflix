// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import android.content.Context;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import com.google.android.gms.internal.c;

class co implements e
{
    private final String WJ;
    private String Xg;
    private bg<com.google.android.gms.internal.c.j> Zf;
    private r Zg;
    private final ScheduledExecutorService Zi;
    private final a Zj;
    private ScheduledFuture<?> Zk;
    private boolean mClosed;
    private final Context mContext;
    
    public co(final Context context, final String s, final r r) {
        this(context, s, r, null, null);
    }
    
    co(final Context mContext, final String wj, final r zg, final b b, final a zj) {
        this.Zg = zg;
        this.mContext = mContext;
        this.WJ = wj;
        Object o = b;
        if (b == null) {
            o = new b() {
                @Override
                public ScheduledExecutorService la() {
                    return Executors.newSingleThreadScheduledExecutor();
                }
            };
        }
        this.Zi = ((b)o).la();
        if (zj == null) {
            this.Zj = (a)new a() {
                @Override
                public cn a(final r r) {
                    return new cn(co.this.mContext, co.this.WJ, r);
                }
            };
            return;
        }
        this.Zj = zj;
    }
    
    private cn bK(final String s) {
        final cn a = this.Zj.a(this.Zg);
        a.a(this.Zf);
        a.bu(this.Xg);
        a.bJ(s);
        return a;
    }
    
    private void kZ() {
        synchronized (this) {
            if (this.mClosed) {
                throw new IllegalStateException("called method after closed");
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void a(final bg<com.google.android.gms.internal.c.j> zf) {
        synchronized (this) {
            this.kZ();
            this.Zf = zf;
        }
    }
    
    @Override
    public void bu(final String xg) {
        synchronized (this) {
            this.kZ();
            this.Xg = xg;
        }
    }
    
    @Override
    public void d(final long n, final String s) {
        synchronized (this) {
            bh.y("loadAfterDelay: containerId=" + this.WJ + " delay=" + n);
            this.kZ();
            if (this.Zf == null) {
                throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
            }
        }
        if (this.Zk != null) {
            this.Zk.cancel(false);
        }
        final String s2;
        this.Zk = this.Zi.schedule(this.bK(s2), n, TimeUnit.MILLISECONDS);
    }
    // monitorexit(this)
    
    @Override
    public void release() {
        synchronized (this) {
            this.kZ();
            if (this.Zk != null) {
                this.Zk.cancel(false);
            }
            this.Zi.shutdown();
            this.mClosed = true;
        }
    }
    
    interface a
    {
        cn a(final r p0);
    }
    
    interface b
    {
        ScheduledExecutorService la();
    }
}
