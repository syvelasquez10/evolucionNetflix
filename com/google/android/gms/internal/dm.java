// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;

public class dm
{
    private final Object li;
    private final String qA;
    private int qV;
    private int qW;
    private final dj qx;
    
    dm(final dj qx, final String qa) {
        this.li = new Object();
        this.qx = qx;
        this.qA = qa;
    }
    
    public dm(final String s) {
        this(dj.bq(), s);
    }
    
    public void a(final int qv, final int qw) {
        synchronized (this.li) {
            this.qV = qv;
            this.qW = qw;
            this.qx.a(this.qA, this);
        }
    }
    
    public Bundle toBundle() {
        synchronized (this.li) {
            final Bundle bundle = new Bundle();
            bundle.putInt("pmnli", this.qV);
            bundle.putInt("pmnll", this.qW);
            return bundle;
        }
    }
}
