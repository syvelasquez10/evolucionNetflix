// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

class m implements k
{
    private ix dR;
    private byte[] dS;
    private final int dT;
    
    public m(final int dt) {
        this.dT = dt;
        this.reset();
    }
    
    @Override
    public void b(final int n, final long n2) throws IOException {
        this.dR.b(n, n2);
    }
    
    @Override
    public void b(final int n, final String s) throws IOException {
        this.dR.b(n, s);
    }
    
    @Override
    public byte[] h() throws IOException {
        final int ge = this.dR.ge();
        if (ge < 0) {
            throw new IOException();
        }
        if (ge == 0) {
            return this.dS;
        }
        final byte[] array = new byte[this.dS.length - ge];
        System.arraycopy(this.dS, 0, array, 0, array.length);
        return array;
    }
    
    @Override
    public void reset() {
        this.dS = new byte[this.dT];
        this.dR = ix.i(this.dS);
    }
}
