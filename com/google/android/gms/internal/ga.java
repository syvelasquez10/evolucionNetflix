// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.ArrayList;
import android.os.Bundle;
import android.os.SystemClock;
import java.util.LinkedList;

@ez
public class ga
{
    private final Object mw;
    private boolean uC;
    private final String vA;
    private long vB;
    private long vC;
    private long vD;
    private long vE;
    private long vF;
    private long vG;
    private final gb vx;
    private final LinkedList<ga$a> vy;
    private final String vz;
    
    public ga(final gb vx, final String vz, final String va) {
        this.mw = new Object();
        this.vB = -1L;
        this.vC = -1L;
        this.uC = false;
        this.vD = -1L;
        this.vE = 0L;
        this.vF = -1L;
        this.vG = -1L;
        this.vx = vx;
        this.vz = vz;
        this.vA = va;
        this.vy = new LinkedList<ga$a>();
    }
    
    public ga(final String s, final String s2) {
        this(gb.cV(), s, s2);
    }
    
    public void cP() {
        synchronized (this.mw) {
            if (this.vG != -1L && this.vC == -1L) {
                this.vC = SystemClock.elapsedRealtime();
                this.vx.a(this);
            }
            final gb vx = this.vx;
            gb.cZ().cP();
        }
    }
    
    public void cQ() {
        synchronized (this.mw) {
            if (this.vG != -1L) {
                final ga$a ga$a = new ga$a();
                ga$a.cU();
                this.vy.add(ga$a);
                ++this.vE;
                final gb vx = this.vx;
                gb.cZ().cQ();
                this.vx.a(this);
            }
        }
    }
    
    public void cR() {
        synchronized (this.mw) {
            if (this.vG != -1L && !this.vy.isEmpty()) {
                final ga$a ga$a = this.vy.getLast();
                if (ga$a.cS() == -1L) {
                    ga$a.cT();
                    this.vx.a(this);
                }
            }
        }
    }
    
    public void e(final av av) {
        synchronized (this.mw) {
            this.vF = SystemClock.elapsedRealtime();
            final gb vx = this.vx;
            gb.cZ().b(av, this.vF);
        }
    }
    
    public void j(final long vg) {
        synchronized (this.mw) {
            this.vG = vg;
            if (this.vG != -1L) {
                this.vx.a(this);
            }
        }
    }
    
    public void k(final long vb) {
        synchronized (this.mw) {
            if (this.vG != -1L) {
                this.vB = vb;
                this.vx.a(this);
            }
        }
    }
    
    public void t(final boolean b) {
        synchronized (this.mw) {
            if (this.vG != -1L) {
                this.vD = SystemClock.elapsedRealtime();
                if (!b) {
                    this.vC = this.vD;
                    this.vx.a(this);
                }
            }
        }
    }
    
    public Bundle toBundle() {
        final ArrayList<Bundle> list;
        synchronized (this.mw) {
            final Bundle bundle = new Bundle();
            bundle.putString("seq_num", this.vz);
            bundle.putString("slotid", this.vA);
            bundle.putBoolean("ismediation", this.uC);
            bundle.putLong("treq", this.vF);
            bundle.putLong("tresponse", this.vG);
            bundle.putLong("timp", this.vC);
            bundle.putLong("tload", this.vD);
            bundle.putLong("pcc", this.vE);
            bundle.putLong("tfetch", this.vB);
            list = new ArrayList<Bundle>();
            final Iterator<ga$a> iterator = this.vy.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().toBundle());
            }
        }
        final Bundle bundle2;
        bundle2.putParcelableArrayList("tclick", (ArrayList)list);
        // monitorexit(o)
        return bundle2;
    }
    
    public void u(final boolean uc) {
        synchronized (this.mw) {
            if (this.vG != -1L) {
                this.uC = uc;
                this.vx.a(this);
            }
        }
    }
}
