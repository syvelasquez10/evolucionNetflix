// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@ez
public class al
{
    private final Object mw;
    private int np;
    private List<ak> nq;
    
    public al() {
        this.mw = new Object();
        this.nq = new LinkedList<ak>();
    }
    
    public boolean a(final ak ak) {
        synchronized (this.mw) {
            return this.nq.contains(ak);
        }
    }
    
    public ak aU() {
        while (true) {
            final ak ak = null;
            while (true) {
                Label_0146: {
                    synchronized (this.mw) {
                        if (this.nq.size() == 0) {
                            gs.S("Queue empty");
                            return null;
                        }
                        if (this.nq.size() >= 2) {
                            int n = Integer.MIN_VALUE;
                            final Iterator<ak> iterator = this.nq.iterator();
                            if (!iterator.hasNext()) {
                                this.nq.remove(ak);
                                return ak;
                            }
                            final int score = iterator.next().getScore();
                            if (score > n) {
                                n = score;
                                break Label_0146;
                            }
                            break Label_0146;
                        }
                    }
                    break;
                }
                continue;
            }
        }
        final ak ak2 = this.nq.get(0);
        ak2.aP();
        // monitorexit(o)
        return ak2;
    }
    
    public boolean b(final ak ak) {
        synchronized (this.mw) {
            for (final ak ak2 : this.nq) {
                if (ak != ak2 && ak2.aO().equals(ak.aO())) {
                    this.nq.remove(ak);
                    return true;
                }
            }
            return false;
        }
    }
    
    public void c(final ak ak) {
        synchronized (this.mw) {
            if (this.nq.size() >= 10) {
                gs.S("Queue is full, current size = " + this.nq.size());
                this.nq.remove(0);
            }
            ak.c(this.np++);
            this.nq.add(ak);
        }
    }
}
