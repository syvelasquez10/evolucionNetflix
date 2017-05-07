// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.events;

import java.util.Iterator;
import java.util.Map;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;
import android.os.Handler;

public abstract class EventIncrementCache
{
    final Object aaf;
    private Handler aag;
    private boolean aah;
    private HashMap<String, AtomicInteger> aai;
    private int aaj;
    
    public EventIncrementCache(final Looper looper, final int aaj) {
        this.aaf = new Object();
        this.aag = new Handler(looper);
        this.aai = new HashMap<String, AtomicInteger>();
        this.aaj = aaj;
    }
    
    private void kN() {
        synchronized (this.aaf) {
            this.aah = false;
            this.flush();
        }
    }
    
    public void flush() {
        synchronized (this.aaf) {
            for (final Map.Entry<String, AtomicInteger> entry : this.aai.entrySet()) {
                this.q(entry.getKey(), entry.getValue().get());
            }
        }
        this.aai.clear();
    }
    // monitorexit(o)
    
    protected abstract void q(final String p0, final int p1);
    
    public void w(final String s, final int n) {
        synchronized (this.aaf) {
            if (!this.aah) {
                this.aah = true;
                this.aag.postDelayed((Runnable)new EventIncrementCache$1(this), (long)this.aaj);
            }
            AtomicInteger atomicInteger;
            if ((atomicInteger = this.aai.get(s)) == null) {
                atomicInteger = new AtomicInteger();
                this.aai.put(s, atomicInteger);
            }
            atomicInteger.addAndGet(n);
        }
    }
}
