// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import android.util.SparseIntArray;
import com.facebook.common.memory.MemoryTrimmableRegistry;

public class GenericByteArrayPool extends BasePool<byte[]> implements ByteArrayPool
{
    private final int[] mBucketSizes;
    
    public GenericByteArrayPool(final MemoryTrimmableRegistry memoryTrimmableRegistry, final PoolParams poolParams, final PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        final SparseIntArray bucketSizes = poolParams.bucketSizes;
        this.mBucketSizes = new int[bucketSizes.size()];
        for (int i = 0; i < bucketSizes.size(); ++i) {
            this.mBucketSizes[i] = bucketSizes.keyAt(i);
        }
        this.initialize();
    }
    
    @Override
    protected byte[] alloc(final int n) {
        return new byte[n];
    }
    
    @Override
    protected void free(final byte[] array) {
        Preconditions.checkNotNull(array);
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
    protected int getBucketedSizeForValue(final byte[] array) {
        Preconditions.checkNotNull(array);
        return array.length;
    }
    
    @Override
    protected int getSizeInBytes(final int n) {
        return n;
    }
}
