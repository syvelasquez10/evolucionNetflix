// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.view.View;
import android.content.Context;
import java.util.ArrayList;
import java.util.WeakHashMap;

@ez
public final class ae implements ag
{
    private final Object mw;
    private final WeakHashMap<fz, af> mx;
    private final ArrayList<af> my;
    
    public ae() {
        this.mw = new Object();
        this.mx = new WeakHashMap<fz, af>();
        this.my = new ArrayList<af>();
    }
    
    public af a(final Context context, final ay ay, final fz fz, final View view, final gt gt) {
        synchronized (this.mw) {
            if (this.c(fz)) {
                return this.mx.get(fz);
            }
            final af af = new af(context, ay, fz, view, gt);
            af.a(this);
            this.mx.put(fz, af);
            this.my.add(af);
            return af;
        }
    }
    
    public af a(final ay ay, final fz fz) {
        return this.a(fz.rN.getContext(), ay, fz, (View)fz.rN, fz.rN.dy());
    }
    
    @Override
    public void a(final af af) {
        synchronized (this.mw) {
            if (!af.aH()) {
                this.my.remove(af);
            }
        }
    }
    
    public boolean c(final fz fz) {
        while (true) {
            synchronized (this.mw) {
                final af af = this.mx.get(fz);
                if (af != null && af.aH()) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public void d(final fz fz) {
        synchronized (this.mw) {
            final af af = this.mx.get(fz);
            if (af != null) {
                af.aF();
            }
        }
    }
    
    public void pause() {
        synchronized (this.mw) {
            final Iterator<af> iterator = this.my.iterator();
            while (iterator.hasNext()) {
                iterator.next().pause();
            }
        }
    }
    // monitorexit(o)
    
    public void resume() {
        synchronized (this.mw) {
            final Iterator<af> iterator = this.my.iterator();
            while (iterator.hasNext()) {
                iterator.next().resume();
            }
        }
    }
    // monitorexit(o)
    
    public void stop() {
        synchronized (this.mw) {
            final Iterator<af> iterator = this.my.iterator();
            while (iterator.hasNext()) {
                iterator.next().stop();
            }
        }
    }
    // monitorexit(o)
}
