// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import android.util.SparseIntArray;

public class PoolParams
{
    public final SparseIntArray bucketSizes;
    public final int maxBucketSize;
    public final int maxNumThreads;
    public final int maxSizeHardCap;
    public final int maxSizeSoftCap;
    public final int minBucketSize;
    
    public PoolParams(final int n, final int n2, final SparseIntArray sparseIntArray) {
        this(n, n2, sparseIntArray, 0, Integer.MAX_VALUE, -1);
    }
    
    public PoolParams(final int maxSizeSoftCap, final int maxSizeHardCap, final SparseIntArray bucketSizes, final int minBucketSize, final int maxBucketSize, final int maxNumThreads) {
        Preconditions.checkState(maxSizeSoftCap >= 0 && maxSizeHardCap >= maxSizeSoftCap);
        this.maxSizeSoftCap = maxSizeSoftCap;
        this.maxSizeHardCap = maxSizeHardCap;
        this.bucketSizes = bucketSizes;
        this.minBucketSize = minBucketSize;
        this.maxBucketSize = maxBucketSize;
        this.maxNumThreads = maxNumThreads;
    }
}
