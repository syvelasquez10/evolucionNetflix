// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.references.CloseableReference;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.references.ResourceReleaser;

public class FlexByteArrayPool
{
    final FlexByteArrayPool$SoftRefByteArrayPool mDelegatePool;
    private final ResourceReleaser<byte[]> mResourceReleaser;
    
    public FlexByteArrayPool(final MemoryTrimmableRegistry memoryTrimmableRegistry, final PoolParams poolParams) {
        Preconditions.checkArgument(poolParams.maxNumThreads > 0);
        this.mDelegatePool = new FlexByteArrayPool$SoftRefByteArrayPool(memoryTrimmableRegistry, poolParams, NoOpPoolStatsTracker.getInstance());
        this.mResourceReleaser = new FlexByteArrayPool$1(this);
    }
    
    public CloseableReference<byte[]> get(final int n) {
        return CloseableReference.of(this.mDelegatePool.get(n), this.mResourceReleaser);
    }
    
    public void release(final byte[] array) {
        this.mDelegatePool.release(array);
    }
}
