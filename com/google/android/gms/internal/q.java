// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

class q implements o
{
    private ko kk;
    private byte[] kl;
    private final int km;
    
    public q(final int km) {
        this.km = km;
        this.reset();
    }
    
    @Override
    public void b(final int n, final long n2) throws IOException {
        this.kk.b(n, n2);
    }
    
    @Override
    public void b(final int n, final String s) throws IOException {
        this.kk.b(n, s);
    }
    
    @Override
    public void reset() {
        this.kl = new byte[this.km];
        this.kk = ko.o(this.kl);
    }
    
    @Override
    public byte[] z() throws IOException {
        final int mv = this.kk.mv();
        if (mv < 0) {
            throw new IOException();
        }
        if (mv == 0) {
            return this.kl;
        }
        final byte[] array = new byte[this.kl.length - mv];
        System.arraycopy(this.kl, 0, array, 0, array.length);
        return array;
    }
}
