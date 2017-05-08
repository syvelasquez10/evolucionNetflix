// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public class DefaultNativeMemoryChunkPoolParams
{
    public static PoolParams get() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1024, 5);
        sparseIntArray.put(2048, 5);
        sparseIntArray.put(4096, 5);
        sparseIntArray.put(8192, 5);
        sparseIntArray.put(16384, 5);
        sparseIntArray.put(32768, 5);
        sparseIntArray.put(65536, 5);
        sparseIntArray.put(131072, 5);
        sparseIntArray.put(262144, 2);
        sparseIntArray.put(524288, 2);
        sparseIntArray.put(1048576, 2);
        return new PoolParams(getMaxSizeSoftCap(), getMaxSizeHardCap(), sparseIntArray);
    }
    
    private static int getMaxSizeHardCap() {
        final int n = (int)Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (n < 16777216) {
            return n / 2;
        }
        return n / 4 * 3;
    }
    
    private static int getMaxSizeSoftCap() {
        final int n = (int)Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (n < 16777216) {
            return 3145728;
        }
        if (n < 33554432) {
            return 6291456;
        }
        return 12582912;
    }
}
