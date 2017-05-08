// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public class DefaultBitmapPoolParams
{
    private static final SparseIntArray DEFAULT_BUCKETS;
    
    static {
        DEFAULT_BUCKETS = new SparseIntArray(0);
    }
    
    public static PoolParams get() {
        return new PoolParams(0, getMaxSizeHardCap(), DefaultBitmapPoolParams.DEFAULT_BUCKETS);
    }
    
    private static int getMaxSizeHardCap() {
        final int n = (int)Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (n > 16777216) {
            return n / 4 * 3;
        }
        return n / 2;
    }
}
