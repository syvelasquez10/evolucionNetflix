// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;

public class dk
{
    private final Object li;
    private final String qL;
    private int qQ;
    private long qR;
    private long qS;
    private int qT;
    private int qU;
    
    public dk(final String ql) {
        this.li = new Object();
        this.qQ = 0;
        this.qR = -1L;
        this.qS = -1L;
        this.qT = 0;
        this.qU = -1;
        this.qL = ql;
    }
    
    public void b(final ah ah, final long n) {
        synchronized (this.li) {
            if (this.qS == -1L) {
                this.qS = n;
                this.qR = this.qS;
            }
            else {
                this.qR = n;
            }
            if (ah.extras != null && ah.extras.getInt("gw", 2) == 1) {
                return;
            }
        }
        ++this.qU;
    }
    // monitorexit(o)
    
    public void bk() {
        synchronized (this.li) {
            ++this.qT;
        }
    }
    
    public void bl() {
        synchronized (this.li) {
            ++this.qQ;
        }
    }
    
    public long bw() {
        return this.qS;
    }
    
    public Bundle q(final String s) {
        synchronized (this.li) {
            final Bundle bundle = new Bundle();
            bundle.putString("session_id", this.qL);
            bundle.putLong("basets", this.qS);
            bundle.putLong("currts", this.qR);
            bundle.putString("seq_num", s);
            bundle.putInt("preqs", this.qU);
            bundle.putInt("pclick", this.qQ);
            bundle.putInt("pimp", this.qT);
            return bundle;
        }
    }
}
