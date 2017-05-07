// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.List;
import android.content.Context;
import java.util.concurrent.Future;

@ez
public class fn extends gg
{
    private final Object mw;
    private final fk sZ;
    private final fo tU;
    private Future<fz> tV;
    private final fd.a tm;
    private final fz.a tn;
    
    public fn(final Context context, final u u, final ai ai, final fz.a a, final fd.a a2) {
        this(a, a2, new fo(context, u, ai, new go(), a));
    }
    
    fn(final fz.a tn, final fd.a tm, final fo tu) {
        this.mw = new Object();
        this.tn = tn;
        this.sZ = tn.vw;
        this.tm = tm;
        this.tU = tu;
    }
    
    private fz r(final int n) {
        return new fz(this.tn.vv.tx, null, null, n, null, null, this.sZ.orientation, this.sZ.qj, this.tn.vv.tA, false, null, null, null, null, null, this.sZ.tJ, this.tn.lH, this.sZ.tH, this.tn.vs, this.sZ.tM, this.sZ.tN, this.tn.vp, null);
    }
    
    @Override
    public void cp() {
    Label_0046_Outer:
        while (true) {
            while (true) {
                int n;
                while (true) {
                    try {
                        Object o = this.mw;
                        synchronized (o) {
                            this.tV = gi.submit((Callable<fz>)this.tU);
                            // monitorexit(o)
                            o = this.tV.get(60000L, TimeUnit.MILLISECONDS);
                            n = -2;
                            if (o != null) {
                                gr.wC.post((Runnable)new Runnable() {
                                    @Override
                                    public void run() {
                                        fn.this.tm.a(o);
                                    }
                                });
                                return;
                            }
                        }
                    }
                    catch (TimeoutException ex) {
                        gs.W("Timed out waiting for native ad.");
                        n = 2;
                        final Object o = null;
                        continue Label_0046_Outer;
                    }
                    catch (ExecutionException ex2) {
                        n = 0;
                        final Object o = null;
                        continue Label_0046_Outer;
                    }
                    catch (InterruptedException ex3) {
                        final Object o = null;
                        n = -1;
                        continue Label_0046_Outer;
                    }
                    catch (CancellationException ex4) {
                        final Object o = null;
                        n = -1;
                        continue Label_0046_Outer;
                    }
                    break;
                }
                Object o = this.r(n);
                continue;
            }
        }
    }
    
    @Override
    public void onStop() {
        synchronized (this.mw) {
            if (this.tV != null) {
                this.tV.cancel(true);
            }
        }
    }
}
