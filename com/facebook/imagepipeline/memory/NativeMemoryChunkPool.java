// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import android.util.SparseIntArray;
import com.facebook.common.memory.MemoryTrimmableRegistry;

public class NativeMemoryChunkPool extends BasePool<NativeMemoryChunk>
{
    private final int[] mBucketSizes;
    
    public NativeMemoryChunkPool(final MemoryTrimmableRegistry memoryTrimmableRegistry, final PoolParams poolParams, final PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        final SparseIntArray bucketSizes = poolParams.bucketSizes;
        this.mBucketSizes = new int[bucketSizes.size()];
        for (int i = 0; i < this.mBucketSizes.length; ++i) {
            this.mBucketSizes[i] = bucketSizes.keyAt(i);
        }
        this.initialize();
    }
    
    @Override
    protected NativeMemoryChunk alloc(final int n) {
        return new NativeMemoryChunk(n);
    }
    
    @Override
    protected void free(final NativeMemoryChunk nativeMemoryChunk) {
        Preconditions.checkNotNull(nativeMemoryChunk);
        nativeMemoryChunk.close();
    }
    
    @Override
    protected int getBucketedSize(final int n) {
        if (n <= 0) {
            throw new BasePool$InvalidSizeException(n);
        }
        final int[] mBucketSizes = this.mBucketSizes;
        final int length = mBucketSizes.length;
        int n2 = 0;
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= length) {
                break;
            }
            n3 = mBucketSizes[n2];
            if (n3 >= n) {
                break;
            }
            ++n2;
        }
        return n3;
    }
    
    @Override
    protected int getBucketedSizeForValue(final NativeMemoryChunk nativeMemoryChunk) {
        Preconditions.checkNotNull(nativeMemoryChunk);
        return nativeMemoryChunk.getSize();
    }
    
    public int getMinBufferSize() {
        return this.mBucketSizes[0];
    }
    
    @Override
    protected int getSizeInBytes(final int n) {
        return n;
    }
    
    @Override
    protected boolean isReusable(final NativeMemoryChunk nativeMemoryChunk) {
        Preconditions.checkNotNull(nativeMemoryChunk);
        return !nativeMemoryChunk.isClosed();
    }
}
