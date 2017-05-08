// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.memory.MemoryTrimmableRegistry;

class FlexByteArrayPool$SoftRefByteArrayPool extends GenericByteArrayPool
{
    public FlexByteArrayPool$SoftRefByteArrayPool(final MemoryTrimmableRegistry memoryTrimmableRegistry, final PoolParams poolParams, final PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
    }
    
    @Override
    Bucket<byte[]> newBucket(final int n) {
        return new OOMSoftReferenceBucket<byte[]>(this.getSizeInBytes(n), this.mPoolParams.maxNumThreads, 0);
    }
}
