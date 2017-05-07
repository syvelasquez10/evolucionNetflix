// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

class p implements n
{
    private pf kY;
    private byte[] kZ;
    private final int la;
    
    public p(final int la) {
        this.la = la;
        this.reset();
    }
    
    @Override
    public byte[] A() {
        final int qv = this.kY.qv();
        if (qv < 0) {
            throw new IOException();
        }
        if (qv == 0) {
            return this.kZ;
        }
        final byte[] array = new byte[this.kZ.length - qv];
        System.arraycopy(this.kZ, 0, array, 0, array.length);
        return array;
    }
    
    @Override
    public void b(final int n, final long n2) {
        this.kY.b(n, n2);
    }
    
    @Override
    public void b(final int n, final String s) {
        this.kY.b(n, s);
    }
    
    @Override
    public void reset() {
        this.kZ = new byte[this.la];
        this.kY = pf.q(this.kZ);
    }
}
