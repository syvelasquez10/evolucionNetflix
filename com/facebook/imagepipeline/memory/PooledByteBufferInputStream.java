// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import java.io.InputStream;

public class PooledByteBufferInputStream extends InputStream
{
    int mMark;
    int mOffset;
    final PooledByteBuffer mPooledByteBuffer;
    
    public PooledByteBufferInputStream(final PooledByteBuffer pooledByteBuffer) {
        Preconditions.checkArgument(!pooledByteBuffer.isClosed());
        this.mPooledByteBuffer = Preconditions.checkNotNull(pooledByteBuffer);
        this.mOffset = 0;
        this.mMark = 0;
    }
    
    @Override
    public int available() {
        return this.mPooledByteBuffer.size() - this.mOffset;
    }
    
    @Override
    public void mark(final int n) {
        this.mMark = this.mOffset;
    }
    
    @Override
    public boolean markSupported() {
        return true;
    }
    
    @Override
    public int read() {
        if (this.available() <= 0) {
            return -1;
        }
        return this.mPooledByteBuffer.read(this.mOffset++) & 0xFF;
    }
    
    @Override
    public int read(final byte[] array) {
        return this.read(array, 0, array.length);
    }
    
    @Override
    public int read(final byte[] array, final int n, int min) {
        if (n < 0 || min < 0 || n + min > array.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + array.length + "; regionStart=" + n + "; regionLength=" + min);
        }
        final int available = this.available();
        if (available <= 0) {
            return -1;
        }
        if (min <= 0) {
            return 0;
        }
        min = Math.min(available, min);
        this.mPooledByteBuffer.read(this.mOffset, array, n, min);
        this.mOffset += min;
        return min;
    }
    
    @Override
    public void reset() {
        this.mOffset = this.mMark;
    }
    
    @Override
    public long skip(final long n) {
        Preconditions.checkArgument(n >= 0L);
        final int min = Math.min((int)n, this.available());
        this.mOffset += min;
        return min;
    }
}
