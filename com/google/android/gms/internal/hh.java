// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.BlockingQueue;

public class hh
{
    private static int Bj;
    private static int Bk;
    private final int Be;
    private final String Bl;
    private final BlockingQueue<hd.a> Bm;
    
    static {
        hh.Bj = 10000;
        hh.Bk = 1000;
    }
    
    public void a(final hd.a.a a) {
        a.as(this.Bl);
        a.bd(this.Be);
        this.Bm.offer(a.ek());
    }
}
