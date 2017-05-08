// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public class DefaultByteArrayPoolParams
{
    public static PoolParams get() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(16384, 5);
        return new PoolParams(81920, 1048576, sparseIntArray);
    }
}
