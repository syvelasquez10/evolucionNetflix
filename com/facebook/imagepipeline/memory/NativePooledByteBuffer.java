// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;

public class NativePooledByteBuffer implements PooledByteBuffer
{
    CloseableReference<NativeMemoryChunk> mBufRef;
    private final int mSize;
    
    public NativePooledByteBuffer(final CloseableReference<NativeMemoryChunk> closeableReference, final int mSize) {
        Preconditions.checkNotNull(closeableReference);
        Preconditions.checkArgument(mSize >= 0 && mSize <= closeableReference.get().getSize());
        this.mBufRef = closeableReference.clone();
        this.mSize = mSize;
    }
    
    @Override
    public void close() {
        synchronized (this) {
            CloseableReference.closeSafely(this.mBufRef);
            this.mBufRef = null;
        }
    }
    
    void ensureValid() {
        synchronized (this) {
            if (this.isClosed()) {
                throw new PooledByteBuffer$ClosedException();
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public boolean isClosed() {
        synchronized (this) {
            return !CloseableReference.isValid(this.mBufRef);
        }
    }
    
    @Override
    public byte read(final int n) {
        final boolean b = true;
        synchronized (this) {
            this.ensureValid();
            Preconditions.checkArgument(n >= 0);
            Preconditions.checkArgument(n < this.mSize && b);
            return this.mBufRef.get().read(n);
        }
    }
    
    @Override
    public void read(final int n, final byte[] array, final int n2, final int n3) {
        synchronized (this) {
            this.ensureValid();
            Preconditions.checkArgument(n + n3 <= this.mSize);
            this.mBufRef.get().read(n, array, n2, n3);
        }
    }
    
    @Override
    public int size() {
        synchronized (this) {
            this.ensureValid();
            return this.mSize;
        }
    }
}
