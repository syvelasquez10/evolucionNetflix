// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream implements BitWriter
{
    private long nrBits;
    private OutputStream out;
    private int unwritten;
    private int vacant;
    
    public BitOutputStream(final OutputStream out) {
        this.nrBits = 0L;
        this.vacant = 8;
        this.out = out;
    }
    
    @Override
    public long nrBits() {
        return this.nrBits;
    }
    
    @Override
    public void one() {
        this.write(1, 1);
    }
    
    @Override
    public void pad(int i) {
        final int n = i - (int)(this.nrBits % i);
        final int n2 = n & 0x7;
        i = n;
        if (n2 > 0) {
            this.write(0, n2);
            i = n - n2;
        }
        while (i > 0) {
            this.write(0, 8);
            i -= 8;
        }
        this.out.flush();
    }
    
    @Override
    public void write(final int n, int i) {
        if (n != 0 || i != 0) {
            if (i <= 0 || i > 32) {
                throw new IOException("Bad write width.");
            }
            while (i > 0) {
                int vacant;
                if (i > this.vacant) {
                    vacant = this.vacant;
                }
                else {
                    vacant = i;
                }
                this.unwritten |= (n >>> i - vacant & BitInputStream.mask[vacant]) << this.vacant - vacant;
                final int n2 = i - vacant;
                this.nrBits += vacant;
                this.vacant -= vacant;
                i = n2;
                if (this.vacant == 0) {
                    this.out.write(this.unwritten);
                    this.unwritten = 0;
                    this.vacant = 8;
                    i = n2;
                }
            }
        }
    }
    
    @Override
    public void zero() {
        this.write(0, 1);
    }
}
