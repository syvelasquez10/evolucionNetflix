// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public class TailAppendingInputStream extends FilterInputStream
{
    private int mMarkedTailOffset;
    private final byte[] mTail;
    private int mTailOffset;
    
    public TailAppendingInputStream(final InputStream inputStream, final byte[] mTail) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        }
        if (mTail == null) {
            throw new NullPointerException();
        }
        this.mTail = mTail;
    }
    
    private int readNextTailByte() {
        if (this.mTailOffset >= this.mTail.length) {
            return -1;
        }
        return this.mTail[this.mTailOffset++] & 0xFF;
    }
    
    @Override
    public void mark(final int n) {
        if (this.in.markSupported()) {
            super.mark(n);
            this.mMarkedTailOffset = this.mTailOffset;
        }
    }
    
    @Override
    public int read() {
        final int read = this.in.read();
        if (read != -1) {
            return read;
        }
        return this.readNextTailByte();
    }
    
    @Override
    public int read(final byte[] array) {
        return this.read(array, 0, array.length);
    }
    
    @Override
    public int read(final byte[] array, int n, final int n2) {
        final int read = this.in.read(array, n, n2);
        if (read != -1) {
            n = read;
        }
        else {
            if (n2 == 0) {
                return 0;
            }
            int i;
            for (i = 0; i < n2; ++i) {
                final int nextTailByte = this.readNextTailByte();
                if (nextTailByte == -1) {
                    break;
                }
                array[n + i] = (byte)nextTailByte;
            }
            if ((n = i) <= 0) {
                return -1;
            }
        }
        return n;
    }
    
    @Override
    public void reset() {
        if (this.in.markSupported()) {
            this.in.reset();
            this.mTailOffset = this.mMarkedTailOffset;
            return;
        }
        throw new IOException("mark is not supported");
    }
}
