// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Supplier;

public class DefaultEncodedMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams>
{
    private int getMaxCacheSize() {
        final int n = (int)Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (n < 16777216) {
            return 1048576;
        }
        if (n < 33554432) {
            return 2097152;
        }
        return 4194304;
    }
    
    @Override
    public MemoryCacheParams get() {
        final int maxCacheSize = this.getMaxCacheSize();
        return new MemoryCacheParams(maxCacheSize, Integer.MAX_VALUE, maxCacheSize, Integer.MAX_VALUE, maxCacheSize / 8);
    }
}
