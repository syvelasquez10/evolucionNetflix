// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.imagepipeline.memory.PooledByteBuffer;

final class EncodedCountingMemoryCacheFactory$1 implements ValueDescriptor<PooledByteBuffer>
{
    @Override
    public int getSizeInBytes(final PooledByteBuffer pooledByteBuffer) {
        return pooledByteBuffer.size();
    }
}
