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

class cp implements e
{
    private final String anR;
    private String aon;
    private bg<com.google.android.gms.internal.c.j> aqi;
    private r aqj;
    private final ScheduledExecutorService aql;
    private final a aqm;
    private ScheduledFuture<?> aqn;
    private boolean mClosed;
    private final Context mContext;
    
    public cp(final Context context, final String s, final r r) {
        this(context, s, r, null, null);
    }
    
    cp(final Context mContext, final String anR, final r aqj, final b b, final a aqm) {
        this.aqj = aqj;
        this.mContext = mContext;
        this.anR = anR;
        Object o = b;
        if (b == null) {
            o = new b() {
                @Override
                public ScheduledExecutorService oO() {
                    return Executors.newSingleThreadScheduledExecutor();
                }
            };
        }
        this.aql = ((b)o).oO();
        if (aqm == null) {
            this.aqm = (a)new a() {
                @Override
                public co a(final r r) {
                    return new co(cp.this.mContext, cp.this.anR, r);
                }
            };
            return;
        }
        this.aqm = aqm;
    }
    
    private co cH(final String s) {
        final co a = this.aqm.a(this.aqj);
        a.a(this.aqi);
        a.cr(this.aon);
        a.cG(s);
        return a;
    }
    
    private void oN() {
        synchronized (this) {
            if (this.mClosed) {
                throw new IllegalStateException("called method after closed");
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void a(final bg<com.google.android.gms.internal.c.j> aqi) {
        synchronized (this) {
            this.oN();
            this.aqi = aqi;
        }
    }
    
    @Override
    public void cr(final String aon) {
        synchronized (this) {
            this.oN();
            this.aon = aon;
        }
    }
    
    @Override
    public void e(final long n, final String s) {
        synchronized (this) {
            bh.V("loadAfterDelay: containerId=" + this.anR + " delay=" + n);
            this.oN();
            if (this.aqi == null) {
                throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
            }
        }
        if (this.aqn != null) {
            this.aqn.cancel(false);
        }
        final String s2;
        this.aqn = this.aql.schedule(this.cH(s2), n, TimeUnit.MILLISECONDS);
    }
    // monitorexit(this)
    
    @Override
    public void release() {
        synchronized (this) {
            this.oN();
            if (this.aqn != null) {
                this.aqn.cancel(false);
            }
            this.aql.shutdown();
            this.mClosed = true;
        }
    }
    
    interface a
    {
        co a(final r p0);
    }
    
    interface b
    {
        ScheduledExecutorService oO();
    }
}
