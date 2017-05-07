// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.WeakHashMap;

public final class aa implements ac
{
    private final Object li;
    private WeakHashMap<dh, ab> lj;
    private ArrayList<ab> lk;
    
    public aa() {
        this.li = new Object();
        this.lj = new WeakHashMap<dh, ab>();
        this.lk = new ArrayList<ab>();
    }
    
    public ab a(final ak ak, final dh dh) {
        synchronized (this.li) {
            if (this.c(dh)) {
                return this.lj.get(dh);
            }
            final ab ab = new ab(ak, dh);
            ab.a(this);
            this.lj.put(dh, ab);
            this.lk.add(ab);
            return ab;
        }
    }
    
    @Override
    public void a(final ab ab) {
        synchronized (this.li) {
            if (!ab.at()) {
                this.lk.remove(ab);
            }
        }
    }
    
    public boolean c(final dh dh) {
        while (true) {
            synchronized (this.li) {
                final ab ab = this.lj.get(dh);
                if (ab != null && ab.at()) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public void d(final dh dh) {
        synchronized (this.li) {
            final ab ab = this.lj.get(dh);
            if (ab != null) {
                ab.ar();
            }
        }
    }
}
