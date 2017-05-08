// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;

public class NativePooledByteBufferOutputStream extends PooledByteBufferOutputStream
{
    private CloseableReference<NativeMemoryChunk> mBufRef;
    private int mCount;
    private final NativeMemoryChunkPool mPool;
    
    public NativePooledByteBufferOutputStream(final NativeMemoryChunkPool nativeMemoryChunkPool) {
        this(nativeMemoryChunkPool, nativeMemoryChunkPool.getMinBufferSize());
    }
    
    public NativePooledByteBufferOutputStream(final NativeMemoryChunkPool nativeMemoryChunkPool, final int n) {
        Preconditions.checkArgument(n > 0);
        this.mPool = Preconditions.checkNotNull(nativeMemoryChunkPool);
        this.mCount = 0;
        this.mBufRef = CloseableReference.of(this.mPool.get(n), this.mPool);
    }
    
    private void ensureValid() {
        if (!CloseableReference.isValid(this.mBufRef)) {
            throw new NativePooledByteBufferOutputStream$InvalidStreamException();
        }
    }
    
    @Override
    public void close() {
        CloseableReference.closeSafely(this.mBufRef);
        this.mBufRef = null;
        this.mCount = -1;
        super.close();
    }
    
    void realloc(final int n) {
        this.ensureValid();
        if (n <= this.mBufRef.get().getSize()) {
            return;
        }
        final NativeMemoryChunk nativeMemoryChunk = this.mPool.get(n);
        this.mBufRef.get().copy(0, nativeMemoryChunk, 0, this.mCount);
        this.mBufRef.close();
        this.mBufRef = CloseableReference.of(nativeMemoryChunk, this.mPool);
    }
    
    @Override
    public int size() {
        return this.mCount;
    }
    
    @Override
    public NativePooledByteBuffer toByteBuffer() {
        this.ensureValid();
        return new NativePooledByteBuffer(this.mBufRef, this.mCount);
    }
    
    @Override
    public void write(final int n) {
        this.write(new byte[] { (byte)n });
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        if (n < 0 || n2 < 0 || n + n2 > array.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + array.length + "; regionStart=" + n + "; regionLength=" + n2);
        }
        this.ensureValid();
        this.realloc(this.mCount + n2);
        this.mBufRef.get().write(this.mCount, array, n, n2);
        this.mCount += n2;
    }
}
