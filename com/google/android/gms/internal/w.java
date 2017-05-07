// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;
import java.util.Iterator;
import android.view.MotionEvent;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.List;

@ez
class w implements g, Runnable
{
    private u.b lr;
    private final List<Object[]> me;
    private final AtomicReference<g> mf;
    CountDownLatch mg;
    
    public w(final u.b lr) {
        this.me = new Vector<Object[]>();
        this.mf = new AtomicReference<g>();
        this.mg = new CountDownLatch(1);
        this.lr = lr;
        if (gr.dt()) {
            gi.a(this);
            return;
        }
        this.run();
    }
    
    private void ax() {
        if (!this.me.isEmpty()) {
            for (final Object[] array : this.me) {
                if (array.length == 1) {
                    this.mf.get().a((MotionEvent)array[0]);
                }
                else {
                    if (array.length != 3) {
                        continue;
                    }
                    this.mf.get().a((int)array[0], (int)array[1], (int)array[2]);
                }
            }
        }
    }
    
    @Override
    public String a(final Context context) {
        this.aw();
        final g g = this.mf.get();
        if (g != null) {
            this.ax();
            return g.a(context);
        }
        return "";
    }
    
    @Override
    public String a(final Context context, final String s) {
        this.aw();
        final g g = this.mf.get();
        if (g != null) {
            this.ax();
            return g.a(context, s);
        }
        return "";
    }
    
    @Override
    public void a(final int n, final int n2, final int n3) {
        final g g = this.mf.get();
        if (g != null) {
            this.ax();
            g.a(n, n2, n3);
            return;
        }
        this.me.add(new Object[] { n, n2, n3 });
    }
    
    @Override
    public void a(final MotionEvent motionEvent) {
        final g g = this.mf.get();
        if (g != null) {
            this.ax();
            g.a(motionEvent);
            return;
        }
        this.me.add(new Object[] { motionEvent });
    }
    
    protected void a(final g g) {
        this.mf.set(g);
    }
    
    protected void aw() {
        try {
            this.mg.await();
        }
        catch (InterruptedException ex) {
            gs.d("Interrupted during GADSignals creation.", ex);
        }
    }
    
    @Override
    public void run() {
        try {
            this.a(j.a(this.lr.lD.wD, this.lr.lB));
        }
        finally {
            this.mg.countDown();
            this.lr = null;
        }
    }
}
