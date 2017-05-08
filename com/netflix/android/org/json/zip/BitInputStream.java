// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

import java.io.IOException;
import java.io.InputStream;

public class BitInputStream implements BitReader
{
    static final int[] mask;
    private int available;
    private InputStream in;
    private long nrBits;
    private int unread;
    
    static {
        mask = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255 };
    }
    
    public BitInputStream(final InputStream in) {
        this.available = 0;
        this.unread = 0;
        this.nrBits = 0L;
        this.in = in;
    }
    
    public BitInputStream(final InputStream in, final int unread) {
        this.available = 0;
        this.unread = 0;
        this.nrBits = 0L;
        this.in = in;
        this.unread = unread;
        this.available = 8;
    }
    
    @Override
    public boolean bit() {
        return this.read(1) != 0;
    }
    
    @Override
    public long nrBits() {
        return this.nrBits;
    }
    
    @Override
    public boolean pad(final int n) {
        final int n2 = (int)(this.nrBits % n);
        boolean b = true;
        for (int i = 0; i < n - n2; ++i) {
            if (this.bit()) {
                b = false;
            }
        }
        return b;
    }
    
    @Override
    public int read(int i) {
        if (i == 0) {
            return 0;
        }
        if (i < 0 || i > 32) {
            throw new IOException("Bad read width.");
        }
        int n = 0;
        while (i > 0) {
            if (this.available == 0) {
                this.unread = this.in.read();
                if (this.unread < 0) {
                    throw new IOException("Attempt to read past end.");
                }
                this.available = 8;
            }
            int available;
            if (i > this.available) {
                available = this.available;
            }
            else {
                available = i;
            }
            n |= (this.unread >>> this.available - available & BitInputStream.mask[available]) << i - available;
            this.nrBits += available;
            this.available -= available;
            i -= available;
        }
        return n;
    }
}
