// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.ArrayList;
import android.os.Bundle;
import android.os.SystemClock;
import java.util.LinkedList;

public class di
{
    private final Object li;
    private boolean pV;
    private final String qA;
    private long qB;
    private long qC;
    private long qD;
    private long qE;
    private long qF;
    private long qG;
    private final dj qx;
    private final LinkedList<a> qy;
    private final String qz;
    
    public di(final dj qx, final String qz, final String qa) {
        this.li = new Object();
        this.qB = -1L;
        this.qC = -1L;
        this.pV = false;
        this.qD = -1L;
        this.qE = 0L;
        this.qF = -1L;
        this.qG = -1L;
        this.qx = qx;
        this.qz = qz;
        this.qA = qa;
        this.qy = new LinkedList<a>();
    }
    
    public di(final String s, final String s2) {
        this(dj.bq(), s, s2);
    }
    
    public void bk() {
        synchronized (this.li) {
            if (this.qG != -1L && this.qC == -1L) {
                this.qC = SystemClock.elapsedRealtime();
                this.qx.a(this);
            }
            final dj qx = this.qx;
            dj.bu().bk();
        }
    }
    
    public void bl() {
        synchronized (this.li) {
            if (this.qG != -1L) {
                final a a = new a();
                a.bp();
                this.qy.add(a);
                ++this.qE;
                final dj qx = this.qx;
                dj.bu().bl();
                this.qx.a(this);
            }
        }
    }
    
    public void bm() {
        synchronized (this.li) {
            if (this.qG != -1L && !this.qy.isEmpty()) {
                final a a = this.qy.getLast();
                if (a.bn() == -1L) {
                    a.bo();
                    this.qx.a(this);
                }
            }
        }
    }
    
    public void f(final ah ah) {
        synchronized (this.li) {
            this.qF = SystemClock.elapsedRealtime();
            final dj qx = this.qx;
            dj.bu().b(ah, this.qF);
        }
    }
    
    public void h(final long qg) {
        synchronized (this.li) {
            this.qG = qg;
            if (this.qG != -1L) {
                this.qx.a(this);
            }
        }
    }
    
    public void i(final long qb) {
        synchronized (this.li) {
            if (this.qG != -1L) {
                this.qB = qb;
                this.qx.a(this);
            }
        }
    }
    
    public void m(final boolean b) {
        synchronized (this.li) {
            if (this.qG != -1L) {
                this.qD = SystemClock.elapsedRealtime();
                if (!b) {
                    this.qC = this.qD;
                    this.qx.a(this);
                }
            }
        }
    }
    
    public void n(final boolean pv) {
        synchronized (this.li) {
            if (this.qG != -1L) {
                this.pV = pv;
                this.qx.a(this);
            }
        }
    }
    
    public Bundle toBundle() {
        final ArrayList<Bundle> list;
        synchronized (this.li) {
            final Bundle bundle = new Bundle();
            bundle.putString("seqnum", this.qz);
            bundle.putString("slotid", this.qA);
            bundle.putBoolean("ismediation", this.pV);
            bundle.putLong("treq", this.qF);
            bundle.putLong("tresponse", this.qG);
            bundle.putLong("timp", this.qC);
            bundle.putLong("tload", this.qD);
            bundle.putLong("pcc", this.qE);
            bundle.putLong("tfetch", this.qB);
            list = new ArrayList<Bundle>();
            final Iterator<a> iterator = this.qy.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().toBundle());
            }
        }
        final Bundle bundle2;
        bundle2.putParcelableArrayList("tclick", (ArrayList)list);
        // monitorexit(o)
        return bundle2;
    }
    
    private static final class a
    {
        private long qH;
        private long qI;
        
        public a() {
            this.qH = -1L;
            this.qI = -1L;
        }
        
        public long bn() {
            return this.qI;
        }
        
        public void bo() {
            this.qI = SystemClock.elapsedRealtime();
        }
        
        public void bp() {
            this.qH = SystemClock.elapsedRealtime();
        }
        
        public Bundle toBundle() {
            final Bundle bundle = new Bundle();
            bundle.putLong("topen", this.qH);
            bundle.putLong("tclose", this.qI);
            return bundle;
        }
    }
}
