// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public class DefaultFlexByteArrayPoolParams
{
    public static final int DEFAULT_MAX_NUM_THREADS;
    
    static {
        DEFAULT_MAX_NUM_THREADS = Runtime.getRuntime().availableProcessors();
    }
    
    public static SparseIntArray generateBuckets(int i, final int n, final int n2) {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        while (i <= n) {
            sparseIntArray.put(i, n2);
            i *= 2;
        }
        return sparseIntArray;
    }
    
    public static PoolParams get() {
        return new PoolParams(4194304, DefaultFlexByteArrayPoolParams.DEFAULT_MAX_NUM_THREADS * 4194304, generateBuckets(131072, 4194304, DefaultFlexByteArrayPoolParams.DEFAULT_MAX_NUM_THREADS), 131072, 4194304, DefaultFlexByteArrayPoolParams.DEFAULT_MAX_NUM_THREADS);
    }
}
