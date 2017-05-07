// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class pd
{
    private final byte[] awl;
    private int awm;
    private int awn;
    
    public pd(final byte[] array) {
        this.awl = new byte[256];
        for (int i = 0; i < 256; ++i) {
            this.awl[i] = (byte)i;
        }
        int n = 0;
        for (int j = 0; j < 256; ++j) {
            n = (n + this.awl[j] + array[j % array.length] & 0xFF);
            final byte b = this.awl[j];
            this.awl[j] = this.awl[n];
            this.awl[n] = b;
        }
        this.awm = 0;
        this.awn = 0;
    }
    
    public void o(final byte[] array) {
        int awm = this.awm;
        int awn = this.awn;
        for (int i = 0; i < array.length; ++i) {
            awm = (awm + 1 & 0xFF);
            awn = (awn + this.awl[awm] & 0xFF);
            final byte b = this.awl[awm];
            this.awl[awm] = this.awl[awn];
            this.awl[awn] = b;
            array[i] ^= this.awl[this.awl[awm] + this.awl[awn] & 0xFF];
        }
        this.awm = awm;
        this.awn = awn;
    }
}
