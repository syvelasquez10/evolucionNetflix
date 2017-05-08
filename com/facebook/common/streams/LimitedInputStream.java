// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public class LimitedInputStream extends FilterInputStream
{
    private int mBytesToRead;
    private int mBytesToReadWhenMarked;
    
    public LimitedInputStream(final InputStream inputStream, final int mBytesToRead) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        }
        if (mBytesToRead < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        }
        this.mBytesToRead = mBytesToRead;
        this.mBytesToReadWhenMarked = -1;
    }
    
    @Override
    public int available() {
        return Math.min(this.in.available(), this.mBytesToRead);
    }
    
    @Override
    public void mark(final int n) {
        if (this.in.markSupported()) {
            this.in.mark(n);
            this.mBytesToReadWhenMarked = this.mBytesToRead;
        }
    }
    
    @Override
    public int read() {
        if (this.mBytesToRead == 0) {
            return -1;
        }
        final int read = this.in.read();
        if (read != -1) {
            --this.mBytesToRead;
        }
        return read;
    }
    
    @Override
    public int read(final byte[] array, int n, int n2) {
        if (this.mBytesToRead == 0) {
            n = -1;
        }
        else {
            n2 = Math.min(n2, this.mBytesToRead);
            n2 = this.in.read(array, n, n2);
            if ((n = n2) > 0) {
                this.mBytesToRead -= n2;
                return n2;
            }
        }
        return n;
    }
    
    @Override
    public void reset() {
        if (!this.in.markSupported()) {
            throw new IOException("mark is not supported");
        }
        if (this.mBytesToReadWhenMarked == -1) {
            throw new IOException("mark not set");
        }
        this.in.reset();
        this.mBytesToRead = this.mBytesToReadWhenMarked;
    }
    
    @Override
    public long skip(long n) {
        n = Math.min(n, this.mBytesToRead);
        n = this.in.skip(n);
        this.mBytesToRead -= (int)n;
        return n;
    }
}
